package com.hd.kzscrm.common.enums;

/**
 * Created by jiangjianwei
 * DATE: 2016/8/1.
 */
public enum UserLinkTypeEnum {
    //关联类型，0未明确，1主账户，2子账户

    TYPE_NOT_CONFIRM("0", "未明确"),

    TYPE_MAIN_ACCOUNT("1", "主账户"),

    TYPE_SUB_ACCOUNT("2", "子账户");

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
    private UserLinkTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
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

    /**
     * 通过枚举<code>code</code>获得枚举。
     *
     * @param code 权限值
     * @return testEnum 权限值枚举
     */
    public static String getValueByCode(String code) {
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (code.equals(userTypeEnum.getCode())) {
                return userTypeEnum.getValue();
            }
        }
        return null;
    }
}
