package com.hd.kzscrm.common.enums;

public enum QzsPdRecommendPositionEnum {
	
	POSITION_INDEX_NEW(1, "首页新品推荐"),
	POSITION_INDEX_FLOOR(2, "首页楼层推荐"),
	POSITION_PRODUCT_FIRST(3, "新品1号位推荐"),
	POSITION_PRODUCT_OTHER(4, "新品其他位推荐");
	
	private Integer code;
	private String name;
	private QzsPdRecommendPositionEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
