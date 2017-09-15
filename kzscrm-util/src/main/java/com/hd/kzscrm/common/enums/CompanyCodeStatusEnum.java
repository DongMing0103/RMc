package com.hd.kzscrm.common.enums;

/**
 * Created by 111 on 2016/7/26. 0待审核 1审核中 2审核通过 3审核不通过
 */
public enum CompanyCodeStatusEnum {

	APPROVE_WAIT("0", "暂存"),

	APPROVE_LOADING("1", "待审核"),

	APPROVE_PASS("2", "审核不通过"),

	APPROVE_REFUSE("4", "审核通过"),

	APPROVE_STOP("8", "停用");

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
	private CompanyCodeStatusEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public Integer getIntCode() {
		return Integer.valueOf(code);
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
		for (CompanyCodeStatusEnum codeStatusEnum : CompanyCodeStatusEnum.values()) {
			if (code.equals(codeStatusEnum.getCode())) {
				return codeStatusEnum.getValue();
			}
		}
		return null;
	}

	public static String getName(String code) {
		for (CompanyCodeStatusEnum articleTypeEnum : CompanyCodeStatusEnum.values()) {
			if (articleTypeEnum.getCode().equals(code)) {
				return articleTypeEnum.getValue();
			}
		}
		return null;
	}

}
