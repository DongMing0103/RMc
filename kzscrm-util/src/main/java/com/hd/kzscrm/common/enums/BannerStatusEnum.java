package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum BannerStatusEnum {
	NORMAL("0", "启用"), BLOCK_UP("1", "停用");

    String code; // 状态编号
    String name; //状态名称

    BannerStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    public static List<BannerStatusEnum> getStarLevelEnumList(){
        List<BannerStatusEnum> list = Arrays.asList(BannerStatusEnum.values());
        Collections.sort(list,new Comparator<BannerStatusEnum>(){
            public int compare(BannerStatusEnum obj1, BannerStatusEnum obj2) {
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
        for (BannerStatusEnum statusEnum : BannerStatusEnum.values()) {
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
