package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 前台用户状态枚举 ,注意是用UC
 * @Author:guodong.zhang created on 2016年11月25日 下午1:43:03
 */
public enum StatusEnum {
	
	NORMAL("1", "启用"), BLOCK_UP("4", "停用");

    String code; // 状态编号
    String name; //状态名称

    StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public static List<StatusEnum> getStarLevelEnumList(){
        List<StatusEnum> list = Arrays.asList(StatusEnum.values());
        Collections.sort(list,new Comparator<StatusEnum>(){
            public int compare(StatusEnum obj1, StatusEnum obj2) {
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
        for (StatusEnum statusEnum : StatusEnum.values()) {
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

    public void setName(String name) {
        this.name = name;
    }
}
