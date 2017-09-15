/**
 * 
 */
package com.hd.kzscrm.common.enums;

/**
 * 支付宝交易通知状态
 * @author 黄霄仪
 * @date 2017年4月11日 下午7:36:33
 * 
 */
public enum AlipayTradeStatus {
	WAIT_BUYER_PAY,//	交易创建，等待买家付款。
	TRADE_CLOSED,	//在指定时间段内未支付时关闭的交易；	在交易完成全额退款成功时关闭的交易。
	TRADE_SUCCESS,	//交易成功，且可对该交易做操作，如：多级分润、退款等。
	TRADE_FINISHED;	//交易成功且结束，即不可再做任何操作。
}
