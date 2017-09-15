package com.hd.kzscrm.common.enums;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum ProductEnabledEnum {
    OPEN("0", "启用"), STOP("1", "停用");

    String code; // 状态编号
    String name; //状态名称

    ProductEnabledEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public static List<ProductEnabledEnum> getStarLevelEnumList(){
        List<ProductEnabledEnum> list = Arrays.asList(ProductEnabledEnum.values());
        Collections.sort(list,new Comparator<ProductEnabledEnum>(){
            public int compare(ProductEnabledEnum obj1, ProductEnabledEnum obj2) {
                //比较每个ArrayList的第二个元素
                int code1= Integer.parseInt(obj1.getCode());
                int code2= Integer.parseInt(obj2.getCode());
                if (code1 == code2){
                    return 0;
                } else if(code1 < code2){
                    return -1;
                } else{
                    return 1;
                }
            }
        });
        return list;
    }


    public static String getName(String code) {
        for (ProductEnabledEnum statusEnum : ProductEnabledEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getName();
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
    public Integer getIntegerCode() {
        return Integer.valueOf(code);
    }
    public Integer getIntCode() {
        return Integer.valueOf(code);
    }

    public void setName(String name) {
        this.name = name;
    }
}
