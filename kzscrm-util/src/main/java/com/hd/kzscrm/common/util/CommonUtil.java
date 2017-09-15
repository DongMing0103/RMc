package com.hd.kzscrm.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.hd.wolverine.common.audit.OperationLogBuilder;
import com.hd.wolverine.common.audit.OperationLogParam;
import com.hd.wolverine.common.audit.OperationType;

/**
 * 通用工具类
 *
 * @author  
 */
public class CommonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    public static <S, D> List<D> convertList(List<S> srcList, Class<D> destClass) {
        List<D> destList = new ArrayList<>();
        if (CollectionUtils.isEmpty(srcList) || destClass == null) {
            return destList;
        }
        try {
            for (S s : srcList) {
                D d = destClass.newInstance();
                BeanUtils.copyProperties(s, d);
                destList.add(d);
            }
        } catch (Exception e) {
            LOGGER.error("bean拷贝异常", e);
        }
        return destList;
    }

    /**
     * @param num     目标数值的最大值(不包含num)---[0,num)
     * @param figures 位数
     * @return 返回0到num之间figures位的随机数(前面补零)
     * 注：不支持位数截取
     */
    public static String getRandomNum(int num, int figures) {

        if (num < 0)
            return null;

        // 生成 [0,num) 之间的随机数(整数)
        Random random = new Random();
        String ret = String.valueOf(random.nextInt(num));

        // 位数不够的场合，前面补零
        return getNumByFigures(ret, figures);
    }

    /**
     * @param num     待转换数值(字符型)
     * @param figures 位数
     * @return 返回figures位的字符串(数字前面补零)
     */
    public static String getNumByFigures(String num, int figures) {

        if (StringUtils.isEmpty(num) || figures < 1)
            return null;

        // 位数不够的场合，前面补零
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < figures - num.length(); i++) {
            buf.append("0");
        }

        return buf.toString() + num;

    }

    /**
     * 记录新增操作的LOG
     *
     * @param userId
     * @param remark
     * @param vo
     */
    public static void optionLogAdd(Long userId, String remark, Object vo) {
        OperationLogBuilder.get(OperationType.add)
                .set(OperationLogParam.userId, userId)
                .set(OperationLogParam.remark, remark)
                .set(OperationLogParam.newData, vo)
                .save();
    }

    /**
     * 记录删除操作的LOG
     *
     * @param userId
     * @param remark
     * @param vo
     */
    public static void optionLogDel(Long userId, String remark, Object vo) {
        OperationLogBuilder.get(OperationType.delete)
                .set(OperationLogParam.userId, userId)
                .set(OperationLogParam.remark, remark)
                .set(OperationLogParam.oldData, vo)
                .save();
    }

    /**
     * 记录更新操作的LOG
     *
     * @param userId
     * @param remark
     */
    public static void optionLogUpdate(Long userId, String remark, Object oldVO, Object newVO) {
        OperationLogBuilder.get(OperationType.update)
                .set(OperationLogParam.userId, userId)
                .set(OperationLogParam.remark, remark)
                .set(OperationLogParam.oldData, oldVO)
                .set(OperationLogParam.newData, newVO)
                .save();
    }

    /**
     * 记录除了批量删除，更新等一些特殊操作的LOG
     *
     * @param userId
     * @param remark
     */
    public static void optionLogOther(Long userId, String remark) {
        OperationLogBuilder.get(OperationType.other)
                .set(OperationLogParam.userId, userId)
                .set(OperationLogParam.remark, remark)
                .save();
    }

    /**
     * 去掉小数点后无效的零
     *
     * @param param
     * @return 去掉小数点后无效的零后的数值
     */
    public static String subZeroAndDot(Double param) {
        if (param == null) {
            param = 0d;
        }

        BigDecimal bg = new BigDecimal(param);
        String str = bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

        return str;
    }
    /**
     * 针对'\r\n\</script>五种特殊字符处理
     * @param word
     * @return
     */
    public static String exchangeSpecialWord(String word) {
        if (!StringUtils.isEmpty(word)) {
            word = word.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'").replaceAll("\\r\\n", "\\\\n").replaceAll("</script>","＜/script>");
        }
        return word;
    }

    /**
     * 针对<>等html转义特殊字符处理
     * @param word
     * @return
     */
    public static String exchangeSpecialWordTwo(String word) {
        if (!StringUtils.isEmpty(word)) {
            word = HtmlUtils.htmlEscape(word);
        }
        return word;
    }

    /**
     * wolverine框架 未加特殊字符处理之前方法
     * 针对特殊字符（'\r\n\）的处理
     * @param flag 1:针对'\r\n\四中特殊字符处理 2:针对<>两个特殊字符处理
     * @param model
     */
    @Deprecated
    public static void exchangeStringProperties(Object model,int flag) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field.getGenericType().toString();
            if (type.equals("class java.lang.String")) {
                String value = null;
                try {
                    Method m = model.getClass().getMethod("get" + name);
                    value = (String) m.invoke(model);
                    if(flag==1){
                        value = exchangeSpecialWord(value);
                    }else if(flag==2){
                        value = exchangeSpecialWordTwo(value);
                    }
                    field.set(model, value);
                } catch (NoSuchMethodException e) {
                    LOGGER.error("处理特殊字符时异常：", e);
                } catch (IllegalAccessException e) {
                    LOGGER.error("处理特殊字符时异常：", e);
                } catch (InvocationTargetException e) {
                    LOGGER.error("处理特殊字符时异常：", e);
                }
            }
        }
    }

    /**
     * 针对js页面渲染时特殊字符（'\r\n\）的处理
     * @param model
     */
    public static void exchangeStringPropertiesForJs(Object model) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field.getGenericType().toString();
            if (type.equals("class java.lang.String")) {
                String value = null;
                try {
                    Method m = model.getClass().getMethod("get" + name);
                    value = (String) m.invoke(model);
                    value = exchangeSpecialWord(value);
                    field.set(model, value);
                } catch (NoSuchMethodException e) {
                    LOGGER.error("处理特殊字符时异常：", e);
                } catch (IllegalAccessException e) {
                    LOGGER.error("处理特殊字符时异常：", e);
                } catch (InvocationTargetException e) {
                    LOGGER.error("处理特殊字符时异常：", e);
                }
            }
        }
    }

    /**
     * 查询结果Map集合，特殊字符处理
     * @param m
     */
    public static void exchangeStringPropertiesMapForJs(Map m) {
        for (Object o : m.keySet()) {
            if (m.get(o).getClass() == String.class) {
                m.put(o, exchangeSpecialWord(m.get(o).toString()));
            }
        }
    }
}
