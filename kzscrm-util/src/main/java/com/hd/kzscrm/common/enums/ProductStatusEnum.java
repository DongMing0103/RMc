package com.hd.kzscrm.common.enums;


/**
 * 文章装填
 * @author: wb-u16
 * @since: 2016-12-1
 */
public enum ProductStatusEnum {
   DRAFT("1","暂存"),PULL_UP("2","下架"),PREPARE_UP("3","待上架"), PUT_UP("4", "上架")  ;
    String code; // 状态编号
    String name; //状态名称

    ProductStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (ProductStatusEnum orderStatusEnum : ProductStatusEnum.values()) {
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

    public  Short getShortCode(){
        return  Short.valueOf(code);
    }
}
