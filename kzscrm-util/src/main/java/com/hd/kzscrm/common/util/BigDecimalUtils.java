/**
 * 
 */
package com.hd.kzscrm.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.util.Assert;

/**
 * @author 黄霄仪
 * @date 2017年7月17日 下午3:52:06
 * 
 */
public class BigDecimalUtils extends BigDecimal{

	private static final long serialVersionUID = 1L;
	/**
	 * @param val
	 */
	public BigDecimalUtils(BigInteger val) {
		super(val);
	}
	
	public static final BigDecimal HUNDRED =BigDecimal.valueOf(100);
	/**
	 * 获取指定数值的百分比
	 * @author 黄霄仪
	 * @date 2017年7月17日 下午3:57:49
	 * @param bigDecimal 未除100前的数值
	 */
	public static final BigDecimal percent(BigDecimal bigDecimal){
		Assert.notNull(bigDecimal,"数值不能为空");
		return bigDecimal.divide(HUNDRED);
	}
}
