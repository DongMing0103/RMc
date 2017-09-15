package com.hd.kzscrm.common.enums;

/**
 * @author lichangchao
 *  异步监听枚举
 */
public enum AsyncEventEnum {
    ACCOUNT_STOP(0, "账号停用后监听事件"),
    SHOP_STOP(1, "店铺停用后监听事件");
    Integer code; // 状态编号
    String name; //状态名称

    AsyncEventEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
