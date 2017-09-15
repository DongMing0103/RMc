package com.hd.kzscrm.common.enums;

/**
 * 删除标准枚举类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public enum DeleteFlagEnum {

    // 未删除
    NORMAL("0"),
    // 已删除
    DELETED("1");

    /**
     * 0：未删除，1：已删除
     */
    String code;

    DeleteFlagEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Short getShortCode(){
        return  Short.valueOf(this.code);
    }
    public Integer getNormalCode() {
        return  Integer.valueOf(NORMAL.getCode());
    }
    public Long getLoneCode() {
        return  Long.valueOf(NORMAL.getCode());
    }
}
