/**
 * 
 */
package com.hd.kzscrm.common.enums;

/**
 * @author 黄霄仪
 * @date 2017年3月8日 下午5:32:48
 * 
 */
public enum UserUserStatusEnum {
	/**
	 * User表的status字段状态
	 * @author 黄霄仪
	 * @date 2017年3月8日 下午5:23:11
	 *
	 */
	ACCOUNT_NORMAL(1, "帐号正常"), ACCOUNT_FREEZE(2, "帐号已冻结"), ACCOUNT_NO_EXISTS(3,"不存在");
	private final int statusCode;
	private final String statusDesc;

	UserUserStatusEnum(int statusCode, String statusDesc) {
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

}
