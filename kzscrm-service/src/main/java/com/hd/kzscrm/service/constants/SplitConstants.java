package com.hd.kzscrm.service.constants;
/**
 * 分账公共参数
 * @author dm
 *
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
}
