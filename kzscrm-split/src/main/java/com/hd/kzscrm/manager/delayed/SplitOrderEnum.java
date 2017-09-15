package com.hd.kzscrm.manager.delayed;

/**
 * 订单状态
 * @author zyg
 * @date 2017年4月23日 下午12:02:56
 */
public enum SplitOrderEnum {
	ORDER_ALREADY_FINISH,//已完成订单
	CUT_T1_ALREADY_FINISH,//已完成抽成
	SPLIT_ORDER_BUSINESS_FINISH,//业务员分成
	SPLIT_ORDER_AGENT_FINISH,//代理商分成
	SPLIT_ORDER_PLATFROM_FINISH,//平台分成
	SPLIT_QUERYORDER_BUSINESS_FINISH,//业务员分成
	
}
