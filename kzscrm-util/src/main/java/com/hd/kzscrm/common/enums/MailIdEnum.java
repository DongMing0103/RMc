package com.hd.kzscrm.common.enums;

/**
 * 交易平台枚举
 * Created by liuming on 2016/7/8.
 */
public enum MailIdEnum {

    MAIL_ID_Z("1001", "总平台"),

    MAIL_ID_H("1002", "化工"),

    MAIL_ID_Y("1003", "有色"),

    MAIL_ID_W("1004", "物流平台"),

    MAIL_ID_Q("1005", "企业中心"),

    MAIL_ID_J("1020", "筷子说平台");

    /**
     * 枚举code
     */
    private String code;

    /**
     * 枚举value
     */
    private String value;

    /**
     * 构造函数
     *
     * @param code
     * @param value
     */
    private MailIdEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 通过枚举<code>code</code>获得枚举。
     *
     * @param code 权限值
     * @return testEnum 权限值枚举
     */
    public static String getValueByCode(String code) {
        for (MailIdEnum billEnum : MailIdEnum.values()) {
            if (code.equals(billEnum.getCode())) {
                return billEnum.getValue();
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
