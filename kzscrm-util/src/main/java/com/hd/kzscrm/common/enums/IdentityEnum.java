package com.hd.kzscrm.common.enums;

/**
 * Created by 111 on 2016/7/26.
 */
public enum IdentityEnum {

    CUSTOMER_USER ("0","客户"),

    PARTNER_USER("1", "拍档"),

    FINANCIAL_INSTITUTION_USER("2","金融机构用户");

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
    private IdentityEnum(String code, String value) {
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
        for (IdentityEnum identityEnum: IdentityEnum.values()) {
            if (code.equals(identityEnum.getCode())) {
                return identityEnum.getValue();
            }
        }
        return null;
    }
}
