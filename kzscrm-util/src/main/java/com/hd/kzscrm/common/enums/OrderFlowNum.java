/**
 * 
 */
package com.hd.kzscrm.common.enums;

/**
 * 单据流水编号枚举，redis键，带有combine结尾的非表名，是对原表单号的扩充，当多订单时，生成一个混全单号与之统一
 * @author 黄霄仪
 * @date 2017年4月10日 下午7:03:47
 * 
 */
public enum OrderFlowNum {
	
	PAY_CANTEEN_CASHFLOW,
	RECHARGE,
	RECHARGE_COMBINE,
	ORDER,
	ORDER_COMBINE,
	PAY_WITHDRAW
}
