package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum TagsStatusEnum {

	NORMAL("1", "启用"), BLOCK_UP("8", "停用");

    String code; // 状态编号
    String name; //状态名称

    TagsStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    public static List<TagsStatusEnum> getStarLevelEnumList(){
        List<TagsStatusEnum> list = Arrays.asList(TagsStatusEnum.values());
        Collections.sort(list,new Comparator<TagsStatusEnum>(){
            public int compare(TagsStatusEnum obj1, TagsStatusEnum obj2) {
                //比较每个ArrayList的第二个元素
                Integer code1= Integer.valueOf(obj1.getCode());
                Integer code2= Integer.valueOf(obj2.getCode());
                if (code1 == code2){
                    return 0;
                }else if(code1>code2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        return list;
    }
    
    
    public static String getName(String code) {
        for (TagsStatusEnum orderStatusEnum : TagsStatusEnum.values()) {
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
}
