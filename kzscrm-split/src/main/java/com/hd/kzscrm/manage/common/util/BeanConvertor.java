package com.hd.kzscrm.manage.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;/*
import com.jumore.jdt.manage.enums.BaseExceptionEnum;*/
import com.hd.kzscrm.common.enums.BaseExceptionEnum;

/**
 * Bean转化类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class BeanConvertor {

    private static final Logger logger = LoggerFactory.getLogger(BeanConvertor.class);

    /**
     * 功能说明：将bean转化成另一种Bean的实体
     *
     * @param object
     * @param entityClass
     * @return T
     */
    public static <T> T convertBean(Object object, Class<T> entityClass) {
        if (null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);

    }

    /**
     * 对象转换
     *
     * @param source           原对象
     * @param target           目标对象
     * @param ignoreProperties 排除要copy的属性
     * @return
     * @create 2016年7月18日 上午11:14:35 luochao
     * @history
     */
    public static <T> T copy(Object source, Class<T> target, String... ignoreProperties) {
        T targetInstance = null;
        try {
            targetInstance = target.newInstance();
        } catch (Exception e) {
            logger.error(BaseExceptionEnum.INSTANTIATION_EXCEPTION.getMsg(), e);
        }
        if (ArrayUtils.isEmpty(ignoreProperties)) {
            BeanUtils.copyProperties(source, targetInstance);
        } else {
            BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
        }
        return targetInstance;
    }

    /**
     * 对象转换（list）
     *
     * @param list             原数据
     * @param target           目标对象
     * @param ignoreProperties 排除要copy的属性
     * @return
     * @create 2016年7月18日 上午11:15:42 luochao
     * @history
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target, String... ignoreProperties) {
        List<T> targetList = new ArrayList<T>();
        if (CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for (E e : list) {
            targetList.add(copy(e, target, ignoreProperties));
        }
        return targetList;
    }

    /**
     * map转对象
     *
     * @param map
     * @param t
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @history
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> t) throws IllegalAccessException, InvocationTargetException,
            InstantiationException {
        T instance = t.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(instance, map);
        return instance;
    }

    /**
     * 对象转map
     *
     * @param obj
     * @return
     * @history
     */
    public static Map<?, ?> objectToMap(Object obj) {
        return convertBean(obj, Map.class);
    }

}
