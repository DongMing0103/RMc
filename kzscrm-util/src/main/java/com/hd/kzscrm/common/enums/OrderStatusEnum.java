/**
 * 
 */
package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 订单状态
 * @author 黄霄仪
 * @date 2017年3月9日 上午11:03:34
 * 订单状态1、已下单   2、已支付  3、已接单、4已送达、5已完成、6已评价、7已取消、8待评价、9待取餐（由后台根据当前时间与用餐时间对比设置，不能从数据库中设置）10、已撤诉,11.已退款
 */
/**
 * 订单状态增加
 * @author jyt 2017年4月12日 上午10:46:14
 *
 */
public enum OrderStatusEnum {
	ALREADY_ORDER(1,"已下单"),
	ALREADY_PAY(2,"已支付"),
	ALREADY_RECEIVE_ORDER(3,"已接单"),
	ALREADY_DELIVERY(4,"已送达"),
	ALREADY_FINISH(5,"已完成"),
	ALREADY_COMMENTS(6,"已评价"),
	ALREADY_CANCEL(7,"已取消"),
	PENDING_EVALUATION(8,"待评价"),
	WAITING_MEAL(9,"待取餐"),
	ALREADY_WITHDRAWAL(10,"已撤诉"),
	ALREADY_REFUND(11,"已退款");
	private final int statusCode;
	private final String statusDesc;
	OrderStatusEnum(int statusCode,String statusDesc){
		this.statusCode=statusCode;
		this.statusDesc=statusDesc;
	}
	
	
	public static List<OrderStatusEnum> getEnumList(){
        List<OrderStatusEnum> list = Arrays.asList(OrderStatusEnum.values());
        Collections.sort(list,new Comparator<OrderStatusEnum>(){
            public int compare(OrderStatusEnum obj1, OrderStatusEnum obj2) {
                //比较每个ArrayList的第二个元素
                int code1= obj1.getStatusCode();
                int code2= obj2.getStatusCode();
                if (code1 == code2){
                    return 0;
                } else if(code1 < code2){
                    return -1;
                } else{
                    return 1;
                }
            }
        });
        return list;
    }


    public static String getName(int code) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (statusEnum.getStatusCode() == code) {
                return statusEnum.getStatusDesc();
            }
        }
        return null;
    }
	
	public int getStatusCode() {
		return statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
}
