package com.hd.kzscrm.common.enums;

/**
 * 货币种类枚举
 * @author caiwl
 */
public enum IsRecommendEnum {
	YES(Short.valueOf("1"), "是"),
	NO(Short.valueOf("0"), "否");
	
	private Short code;
	private String name;
	
	private IsRecommendEnum(Short code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Short getNameByCode(String name) {
		for (IsRecommendEnum isReadEnum : IsRecommendEnum.values()) {
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
