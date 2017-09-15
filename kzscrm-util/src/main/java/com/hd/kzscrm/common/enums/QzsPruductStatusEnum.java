package com.hd.kzscrm.common.enums;

/**
 * 产品状态状态枚举值
 * @author wing5
 *
 */
public enum QzsPruductStatusEnum {
	   DRAFT(Short.valueOf("0"),"暂存"),PULL_UP(Short.valueOf("2"),"上架"),PREPARE_UP(Short.valueOf("3"),"下架"), PUT_UP(Short.valueOf("8"), "待上架");
	   Short code; // 状态编号
	    String name; //状态名称

	    QzsPruductStatusEnum(Short code, String name) {
	        this.code = code;
	        this.name = name;
	    }

	    public static String getName(String code) {
	        for (CmsStatusEnum orderStatusEnum : CmsStatusEnum.values()) {
	            if (orderStatusEnum.getCode().equals(code)) {
	                return orderStatusEnum.getName();
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

	    public  short getShortCode(){
	        return  Short.valueOf(code);
	    }
	 
}
