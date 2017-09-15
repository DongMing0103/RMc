package com.hd.kzscrm.common.enums;


public enum FavoriteEnum {
    OBJ_TYPE_PRODUCT(Short.valueOf("1"), "产品"), OBJ_TYPE_SL(Short.valueOf("2"), "解决方案"),OBJ_TYPE_TECH(Short.valueOf("3"), "技术发展"),
    OPERATE_TYPE_COLLECT(Short.valueOf("1"),   "收藏"),OPERATE_TYPE_GOOD(Short.valueOf("2"),"点赞");
    Short code; // 状态编号
    String name; //状态名称

    FavoriteEnum(Short code, String name) {
        this.code = code;
        this.name = name;
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
