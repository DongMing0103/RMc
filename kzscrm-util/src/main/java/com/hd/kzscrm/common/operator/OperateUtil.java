package com.hd.kzscrm.common.operator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.google.common.collect.Lists;
import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.OperateEnum;
import com.hd.kzscrm.common.exceptions.BizException;

/**
 * 操作是否允许工具类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class OperateUtil {

    /**
     * 功能说明：校验操作权限
     *
     * @param statusEnum
     * @param operateEnum
     * @return boolean
     */
    public static void operateVaild(Enum<?> statusEnum, OperateEnum operateEnum) throws BizException {
        if (OperateEnum.ALL.equals(operateEnum)) {
            return;
        }
        if (statusEnum == null || operateEnum == null) {
            throw new BizException(BaseExceptionEnum.OPERATE_UNSUPPORTED);
        }
        OperateEnum[] operateEnums = getOperateEnum(statusEnum);
        if (operateEnums == null) {
            // 没有注解时认为该状态下可以进行任何操作
            return;
        }
        for (OperateEnum operate : operateEnums) {
            if (operate.equals(OperateEnum.ALL)) {
                return;
            }
            if (operate.equals(operateEnum)) {
                return;
            }
        }
        throw new BizException(BaseExceptionEnum.OPERATE_UNSUPPORTED);
    }

    /**
     * 功能说明：获取当前状态的所有可执行操作
     *
     * @param statusEnum
     * @return String[]
     */
    public static List<String> getOperates(Enum<?> statusEnum) {
        OperateEnum[] operateEnums = getOperateEnum(statusEnum);
        if (ArrayUtils.isEmpty(operateEnums)) {
            return Lists.newArrayList();
        }
        List<String> operates = new ArrayList<String>();
        for (OperateEnum operateEnum : operateEnums) {
            operates.add(operateEnum.name());
        }
        return operates;
    }

    /**
     * 根据枚举实例字段enumm获得其上的Operator注解中method内容
     *
     * @param enumm
     * @return
     * @throws Exception
     */
    private static OperateEnum[] getOperateEnum(Enum<?> enumm) {
        // 获取@Operator注解  
        try {
            // 先获取枚举名  
            String enumName = enumm.name();
            // 再获取注解  
            Annotation annotation = enumm.getClass().getField(enumName).getAnnotation(Operator.class);
            // 再获取想要的方法  
            Method method = Operator.class.getMethod("method");
            // 反射调用方法获取相关注解值  
            OperateEnum[] result = (OperateEnum[]) method.invoke(annotation);
            return result;
        } catch (Exception e) {
            // 该枚举上没有Operator注解
        }
        return null;
    }

}
