package com.hd.kzscrm.manage.common;
/**
 * 分账公共参数
 * @description TODO
 * @author zyg
 *
 * @date 2017年5月2日 下午2:36:32
 */
public class SplitConstants {
	/**
	 * 私有化构造方法
	 */
	private SplitConstants(){
		
	}
	
	//订单成功明细前缀
	public static final String SPLITORDERSUCCESS = "split_ordersuccessno:";
	//订单抽成明细前缀
	public static final String SPLITCUTT1 = "split_cutt1:";
	//集合后缀
	public static final String REDISLIST_POSTFIX  = "_list";
	//异常后缀
	public static final String ERROR_POSTTFIX = "_error";
	//商家金额前缀
	public static final String SPLITCANTEENMONEY = "split_canteen_money:";
	//业务分账前缀
	public static final String SPLITBUSINESSMANAGER = "split_bussiness_manager:";
	//业务金额前缀
	public static final String SPLITBUSINESSMONEY = "split_bussiness_money:";
	//代理金额前缀
	public static final String SPLITAGENTMONEY = "split_agent_money:";
	//平台前缀
	public static final String SPLITPLATFROMMONEY = "split_platfrom_money:";
	
}
