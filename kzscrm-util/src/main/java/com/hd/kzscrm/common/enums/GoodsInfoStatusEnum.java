/**
 * 
 */
package com.hd.kzscrm.common.enums;

/**
 * 商品信息表status字段上下架枚举
 * @author 黄霄仪
 * @date 2017年3月8日 下午5:34:46
 * 
 */
public enum GoodsInfoStatusEnum {
	DOWN_SHELVES(1,"待上架"),//下架
	UP_SHELVES(2,"已上架");
	private final int statusCode;
	private final String statusDesc;
	GoodsInfoStatusEnum(int statusCode,String statusDesc){
		this.statusCode=statusCode;
		this.statusDesc=statusDesc;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
}
