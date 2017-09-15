package com.hd.kzscrm.common.enums;

/**
 * 货币种类枚举
 * @author caiwl
 */
public enum IsReadEnum {
	UNREAD(Short.valueOf("0"), "未读"),
	READ(Short.valueOf("1"), "已读"),
	UNREAD_UNTREATED(Short.valueOf("2"), "未读未处理"),
	READ_UNTREATED(Short.valueOf("3"), "已读未处理"),
	READ_TREATED(Short.valueOf("7"), "已读已处理");
	
	private Short code;
	private String name;
	
	private IsReadEnum(Short code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Short getNameByCode(String name) {
		for (IsReadEnum isReadEnum : IsReadEnum.values()) {
			if (isReadEnum.getName().equals(name)) {
				return isReadEnum.getCode();
			}
		}
		return null;
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
