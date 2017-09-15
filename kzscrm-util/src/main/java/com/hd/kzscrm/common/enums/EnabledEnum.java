package com.hd.kzscrm.common.enums;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum EnabledEnum {
    OPEN("0", "启用"), STOP("1", "停用");

    String code; // 状态编号
    String name; //状态名称

    EnabledEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public static List<EnabledEnum> getStarLevelEnumList(){
        List<EnabledEnum> list = Arrays.asList(EnabledEnum.values());
        Collections.sort(list,new Comparator<EnabledEnum>(){
            public int compare(EnabledEnum obj1, EnabledEnum obj2) {
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
        for (EnabledEnum statusEnum : EnabledEnum.values()) {
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
    public Short getShortCode() {
        return Short.valueOf(code);
    }
    public Integer getIntCode() {
        return Integer.valueOf(code);
    }

    public void setName(String name) {
        this.name = name;
    }
}
