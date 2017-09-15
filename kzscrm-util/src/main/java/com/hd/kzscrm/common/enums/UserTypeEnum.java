package com.hd.kzscrm.common.enums;

/**
 * Created by 111 on 2016/7/26.
 */
public enum UserTypeEnum {

    ENTERPRISE_USER("0", "企业用户"),

    PLATFORM_ADMINISTRATOR("1", "平台用户（管理员）"),

    PLATFORM_OPERATIONAL_STAFF("2", "平台用户（运营人员）"),

    LOGISTICS_SUPPLIER("3", "物流供应商"),

    LOGISTICS_CLIENT("4", "物流委托方"),

    PARTNER_USER ("5","拍档用户"),

    FINANCIAL_INSTITUTION_USER("6","金融机构用户");

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
    private UserTypeEnum(String code, String value) {
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
