package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum DataKindEnum {
	ARTIC("0", "文章"), PRODUCT("1", "产品"),SOLUTION("2","解决方案");
    String code; // 状态编号
    String name; //状态名称

    DataKindEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    public static List<DataKindEnum> getStarLevelEnumList(){
        List<DataKindEnum> list = Arrays.asList(DataKindEnum.values());
        Collections.sort(list,new Comparator<DataKindEnum>(){
            public int compare(DataKindEnum obj1, DataKindEnum obj2) {
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
        for (DataKindEnum statusEnum : DataKindEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getName();
            }
        }
        return null;
    }

    public Integer getIntCode() {
    	if (code != null)
    		return Integer.parseInt(code);
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
}
