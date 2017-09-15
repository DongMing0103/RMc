package com.hd.kzscrm.common.enums;


/**
 * 网站常量定义
 * @author: wing
 * @since:  
 */
public enum SiteConstEnum {

    SITE_TITLE("1", "筷子说");
    String code; // 状态编号
    String name; //状态名称

    SiteConstEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (SiteConstEnum orderStatusEnum : SiteConstEnum.values()) {
            if (orderStatusEnum.getCode().equals(code)) {
                return orderStatusEnum.getName();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
