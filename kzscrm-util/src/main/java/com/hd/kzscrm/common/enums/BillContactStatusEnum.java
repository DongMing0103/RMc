package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 联系单状态  
 * @Author:guodong.zhang created on 2016年11月30日 上午9:58:43
 */
public enum BillContactStatusEnum {

    HAVE_NOT_FEEDBACK("0", "未反馈"), ALREADY_FEEDBACK("1", "已反馈"), CLOSE("2", "已关闭");

    String code; // 状态编号
    String name; //状态名称

    BillContactStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    public static List<BillContactStatusEnum> getStarLevelEnumList(){
        List<BillContactStatusEnum> list = Arrays.asList(BillContactStatusEnum.values());
        Collections.sort(list,new Comparator<BillContactStatusEnum>(){
            public int compare(BillContactStatusEnum obj1, BillContactStatusEnum obj2) {
                //比较每个ArrayList的第二个元素
                int code1= Integer.parseInt(obj1.getCode());
                int code2= Integer.parseInt(obj2.getCode());
                if (code1 == code2){
                    return 0;
                }else if(code1 < code2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        return list;
    }
    
    
    public static String getName(String code) {
        for (BillContactStatusEnum orderStatusEnum : BillContactStatusEnum.values()) {
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
