/**
 * 
 */
package com.hd.kzscrm.common.enums;

/**
 * 删除标识
 * @author 黄霄仪
 * @date 2017年3月8日 下午5:32:01
 * 
 */
public enum DelFlagEnum {
	/**
	 * 所有表的删除状态
	 */
	DELETE(0,"删除"),
	NORMAL(1,"正常");
	private final int statusCode;
	private final String statusDesc;
	DelFlagEnum(int statusCode,String statusDesc){
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
