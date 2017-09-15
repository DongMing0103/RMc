package com.hd.kzscrm.common.enums;

public enum PlatFormEnum {

	// 关联类型，0未明确，1主账户，2子账户

	AI("0", "未明确"),

	CANTEEN("1", "商户后台"), QY_MGR("2", "企业管理后台"), ADMIN_MGR("3", "系统平台");

	/**
	 * 枚举code
	 */
	private String code;

	/**
	 * 枚举value
	 */
	private String value;

	/**
	 * 构造函数
	 *
	 * @param code
	 * @param value
	 */
	private PlatFormEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举。
	 *
	 * @param code
	 *            权限值
	 * @return testEnum 权限值枚举
	 */
	public static String getValueByCode(String code) {
		for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
			if (code.equals(userTypeEnum.getCode())) {
				return userTypeEnum.getValue();
			}
		}
		return null;
	}
}
