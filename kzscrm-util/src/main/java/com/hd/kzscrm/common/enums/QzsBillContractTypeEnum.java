package com.hd.kzscrm.common.enums;

public enum QzsBillContractTypeEnum {
	PRODUCT("产品", 1),
	PROJECT("解决方案", 2);
	private String name;
	private Integer type;
	private QzsBillContractTypeEnum(String name, Integer type) {
		this.type = type;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public static Integer getTypeByName(String name) {
		for (QzsBillContractTypeEnum typeEnum : QzsBillContractTypeEnum.values()) {
			if (typeEnum.getName().equals(name)) {
				return typeEnum.getType();
			}
		}
		return null;
	}
}
