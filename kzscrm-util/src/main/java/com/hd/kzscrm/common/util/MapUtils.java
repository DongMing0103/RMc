/*
 * 文件名称: MapUtils.java
 * 版权信息: Copyright 2013-2014 chunchen technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: zhangyz
 * 修改日期: 2014-5-8
 * 修改内容: 
 */
package com.hd.kzscrm.common.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * map工具类
 * @author zhangyz created on 2014-5-8
 */
public class MapUtils extends org.apache.commons.collections.MapUtils{

    /**
     * 直接生成map，并传入key-value对
     * @param keysAndValues
     * @return
     * @author zhangyz created on 2014-5-8
     */
    public static Map<String, Object> genMap(Object... keysAndValues) {
        Map<String, Object> ret = new HashMap<String, Object> ();
        
        int len = keysAndValues.length;
        if (len == 0)
            return ret;
        
        if (len % 2 != 0)
          throw new IllegalArgumentException("传入的参数必须成对!");
        for (int i = 0; i < len; i += 2) {
          String key = String.valueOf(keysAndValues[i]);
          Object val = keysAndValues[i + 1];
          if (val != null)
              ret.put(key, val);
        }
        
        return ret;
    }

    /**
     * 直接生成map，并传入key-value对，若为空也加入
     * @param keysAndValues
     * @return
     * @author zhangyz created on 2014-5-8
     */
    public static Map<String, Object> genMapWithNull(Object... keysAndValues) {
        Map<String, Object> ret = new HashMap<String, Object> ();
        
        int len = keysAndValues.length;
        if (len == 0)
            return ret;
        
        if (len % 2 != 0)
          throw new IllegalArgumentException("传入的参数必须成对!");
        for (int i = 0; i < len; i += 2) {
          String key = String.valueOf(keysAndValues[i]);
          Object val = keysAndValues[i + 1];
          ret.put(key, val);              
        }
        
        return ret;
    }
    
    /**
     * 生成list列表
     * @param values
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> List<T> genList(T... values) {
        List<T> ret = new java.util.ArrayList<T>();
        if (values == null)
            return ret;

        for (T value : values) {
            ret.add(value);
        }
        return ret;
    }
    
    /**
     * Map转换为String 
     * @param map
     * @return
     * @Author:zhanggd created on 2015-7-15
     */
    public static String mapToString (Map<String, String> map) {
        if (map == null || map.size() ==0)
            return null;
        
        StringBuffer buffer = new StringBuffer();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            buffer.append(",");
            buffer.append(key).append(":").append(value);
        }
        return buffer.substring(1);
    }
    
    public static <T extends Map<Object,Object>> T mapNotEmptyValue(T t){
    	if(BeanUtils.isNotEmpty(t)){
    		Set<Entry<Object, Object>> entrySet = t.entrySet();
    		for (Iterator<Entry<Object, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
    			Entry<Object, Object> entry = iterator.next();
    			if(BeanUtils.isEmpty(entry.getKey())||BeanUtils.isEmpty(entry.getValue())){
    				iterator.remove();
    			}
    		}
    	}
    	return t;
    }
    /**
     * 获取Map中的值，转换成合适的类型,当为空时，给出默认值
     * @author 黄霄仪
     * @date 2017年4月24日 下午2:04:12
     */
    public static <T> T mapByKeyOfValue(Map<String,Object> map,String key,Class<T> clazz){
    	List<Class> baseClazz=new LinkedList<>();
    	Collections.addAll(baseClazz,Integer.class,Long.class,Float.class,Double.class);
    	Object o = map.get(key);
		if(BeanUtils.isNotEmpty(o)){
    		return clazz.cast(o);
    	}else{
    		if(clazz==String.class){
    			return (T)"";
    		}else if(clazz==BigDecimal.class){
    			return (T)BigDecimal.ZERO;
    		}else if(baseClazz.contains(clazz)){
    			return (T)Byte.valueOf("0");
    		}else if(clazz==Boolean.class){
    			return (T)Boolean.FALSE;
    		}
    		
    	}
    	return null;
    }
}
