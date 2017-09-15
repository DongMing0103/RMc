/**
 * 
 */
package com.hd.kzscrm.manage.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.hd.kzscrm.common.util.BeanUtils;
 

/**自定义velocity工具
 * @author 黄霄仪
 * @date 2017年3月4日 下午1:22:27
 * 
 */
public class VelocityCustomerTools{
	// 判断字符，集合，Map为空
	@SuppressWarnings("rawtypes")
	public boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			return true;
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	// 判断字符，集合，Map不为空
	public boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	// 判断List不为空，并且只有一个
	public boolean isNotEmptyUniqueList(List list) {
		if (isNotEmpty(list) && list.size() == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 加法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal add(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.add(b).setScale(newScale);
	}
	/**
	 * 减法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal subtract(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.subtract(b).setScale(newScale);
	}
	/**
	 * 乘法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal multiply(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.multiply(b).setScale(newScale);
	}
	/**
	 * 除法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal divide(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.divide(b).setScale(newScale);
	}
	
	/**
	 * 加法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal addRound(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.add(b).setScale(newScale,RoundingMode.HALF_UP);
	}
	/**
	 * 减法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal subtractRound(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.subtract(b).setScale(newScale,RoundingMode.HALF_UP);
	}
	/**
	 * 乘法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal multiplyRound(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.multiply(b).setScale(newScale,RoundingMode.HALF_UP);
	}
	/**
	 * 除法
	 * @author 黄霄仪
	 * @date 2017年3月4日 下午2:49:31
	 */
	public BigDecimal divideRound(BigDecimal a,BigDecimal b,Integer newScale){
		if(BeanUtils.isEmpty(a)||BeanUtils.isEmpty(b)){
			return null;
		}
		return a.divide(b).setScale(newScale,RoundingMode.HALF_UP);
	}
}
