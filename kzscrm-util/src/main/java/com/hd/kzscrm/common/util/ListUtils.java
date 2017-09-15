/*
 * 文件名称: ListUtils.java
 * 版权信息: Copyright 2013-2014 chunchen technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: wangdaobin
 * 修改日期: 2014-06-11
 * 修改内容: 
 */
package com.hd.kzscrm.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.Assert;

/**
 * List工具类
 * @author wangdaobin created on 2014-06-11
 */
public class ListUtils extends org.apache.commons.collections.ListUtils {

    /**
     * 把list转换为一个用逗号分隔的字符串 
     * @param list
     * @return
     * @author wangdaobin created on 2014-06-11
     */
	public static String listToString(List<?> list) {  
	    if (list != null && list.size() > 0) {
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append(list.get(i) + ",");  
	            } else {  
	                sb.append(list.get(i));  
	            }  
	        }  
	        return sb.toString();  
	    }
	    else
	        return "";
	}
	
	public static String listToString(Set<?> list) {  
        if (list != null && list.size() > 0) {
            StringBuilder sb = new StringBuilder();  
            for (Object item : list) {  
                if (sb.length() > 0)
                    sb.append(",");
                sb.append(item.toString());
            }  
            return sb.toString();  
        }
        else
            return "";
    }
	
	 /**
     * 把list转换为一个用逗号分隔的字符串 ,同时每一项更一个后缀
     * @param list
     * @return
     * @author wangdaobin created on 2014-06-11
     */
	public static String listToString(List<?> list, String suffix) {  
        if (list != null && list.size() > 0) {
            StringBuilder sb = new StringBuilder();  
            String item;
            for (int i = 0; i < list.size(); i++) {  
                item = list.get(i) + suffix;
                if (i < list.size() - 1) {  
                    sb.append(item + ",");  
                } else {  
                    sb.append(item);  
                }  
            }  
            return sb.toString();  
        }
        else
            return "";
    }
	
	public static List<Long> stringToLongList(String token, String separator) {
        if (token == null || token.length() == 0)
            return null;
        List<Long> ret = new ArrayList<Long>();
        String[] segs = org.apache.commons.lang.StringUtils.splitByWholeSeparator(token, separator);
        for (String seg : segs) {
            ret.add(Long.parseLong(seg));
        }
        return ret;
    }
	
	public static List<Long> stringToLongList(String token) {
	    if (token == null || token.length() == 0)
	        return null;
	    List<Long> ret = new ArrayList<Long>();
	    String[] segs = org.apache.commons.lang.StringUtils.splitByWholeSeparator(token, ",");
	    for (String seg : segs) {
	        ret.add(Long.parseLong(seg));
	    }
	    return ret;
	}
    
    public static List<Long> stringToLongListNoRepeat(String token) {
        if (token == null || token.length() == 0)
            return null;
        List<Long> ret = new ArrayList<Long>();
        String[] segs = org.apache.commons.lang.StringUtils.splitByWholeSeparator(token, ",");
        for (String seg : segs) {
            Long ls = Long.parseLong(seg);
            if (!ret.contains(ls))
                ret.add(ls);
        }
        return ret;
    }
    
    public static Set<Long> stringToLongSet(String token) {
        if (token == null || token.length() == 0)
            return null;
        Set<Long> ret = new HashSet<Long>();
        String[] segs = org.apache.commons.lang.StringUtils.splitByWholeSeparator(token, ",");
        for (String seg : segs) {
            ret.add(Long.parseLong(seg));
        }
        return ret;
    }
    
    public static List<Integer> stringToIntegerList(String token) {
        if (token == null || token.length() == 0)
            return null;
        List<Integer> ret = new ArrayList<Integer>();
        String[] segs = org.apache.commons.lang.StringUtils.splitByWholeSeparator(token, ",");
        for (String seg : segs) {
            ret.add(Integer.parseInt(seg));
        }
        return ret;
    }
    
    public static List<String> stringToList(String token) {
        if (token == null || token.length() == 0)
            return null;
        List<String> ret = new ArrayList<String> ();
        String[] segs = org.apache.commons.lang.StringUtils.splitByWholeSeparator(token, ",");
        for (String seg : segs) {
            ret.add(seg);
        }
        return ret;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}
	
	/**
	 * list转换为字符数组 
	 * @param list
	 * @return
	 * @Author:guodong.zhang created on 2016年11月18日 下午1:45:21
	 */
	public static String[] listToArray(List<?> list) {
		String[] array = new String[list.size()];
		for(int i = 0,j = list.size(); i < j; i++){
			array[i]=list.get(i).toString();
		}
		return array;
	}
	
	public static <T> List<T> substringList(List<T> list,Integer fromIndex,Integer toIndex){
		if(toIndex==null){
			toIndex=list.size();
		}
		Assert.isTrue(fromIndex<=toIndex, "fromIndex不能大于toIndex");
		Assert.notEmpty(list, "列表不能为空");
		if(fromIndex>list.size()){
			return org.apache.commons.collections.ListUtils.EMPTY_LIST;
		}
		
		return list.subList(fromIndex, toIndex);
	}
	
	public static void main(String args[]) {
		Set<Integer> list = new HashSet<Integer>();
		list.add(1);
		list.add(2);
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(3);
		System.out.println(listToString(new ArrayList<>(list)));
	}
}
