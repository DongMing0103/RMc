package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 后台用户账号状态
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public enum AccountStatusEnum {

    CHECK_PENDING("0", "待审核"), NORMAL("1", "启用"), BLOCK_UP("8", "停用");

    String code; // 状态编号
    String name; //状态名称

    AccountStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public static List<AccountStatusEnum> getAccountStatusEnumList(){
        List<AccountStatusEnum> list = Arrays.asList(AccountStatusEnum.values());
        Collections.sort(list,new Comparator<AccountStatusEnum>(){
            public int compare(AccountStatusEnum obj1, AccountStatusEnum obj2) {
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
        for (AccountStatusEnum orderStatusEnum : AccountStatusEnum.values()) {
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
