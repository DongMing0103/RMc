package com.hd.kzscrm.common.enums;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum DisplayEnum {
    BLOCK("0", "显示"), NONE("1", "隐藏");

    String code; // 状态编号
    String name; //状态名称

    DisplayEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public static List<DisplayEnum> getStarLevelEnumList(){
        List<DisplayEnum> list = Arrays.asList(DisplayEnum.values());
        Collections.sort(list,new Comparator<DisplayEnum>(){
            public int compare(DisplayEnum obj1, DisplayEnum obj2) {
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
        for (DisplayEnum statusEnum : DisplayEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getName();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
    public Integer getIntCode() {
        return Integer.valueOf(code);
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Short getShortCode() {
        return Short.valueOf(code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
