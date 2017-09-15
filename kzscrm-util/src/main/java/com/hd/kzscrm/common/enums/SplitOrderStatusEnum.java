package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 分账抽成状态枚举
 * @description TODO
 * @author zyg
 *
 * @date 2017年4月28日 下午7:07:34
 */
public enum SplitOrderStatusEnum {
	ORDER_SUCCESS_INIT(1,"初始状态"),
	ORDER_CUTING(2,"计算中"),
	ORDER_CUT_END(3,"计算完成"),
	ORDER_OUT_MONEY(4,"退款"),
	ORDER_CUT_ERROR(5,"计算异常"),
	ORDER_CUT_OVER(6,"结束"),
	;
	private final int statusCode;
	private final String statusDesc;
	SplitOrderStatusEnum(int statusCode,String statusDesc){
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
