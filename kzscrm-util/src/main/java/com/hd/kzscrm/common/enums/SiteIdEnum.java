package com.hd.kzscrm.common.enums;


/**
 * 网站ID
 *
 * @author: zhuwei
 * @since: 2016年8月3日  上午9:15:11
 * @history:
 */
public enum SiteIdEnum {
    MTL("0", "卖铜郎"), chemical("1", "化工"), jm_chemical("900", "聚贸化工"), logistics("800", "物流平台"), Qzs("1018", "筷子说");

    String code; // 状态编号
    String name; //状态名称

    SiteIdEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getCode(String code) {
        for (SiteIdEnum siteIdEnum : SiteIdEnum.values()) {
            if (siteIdEnum.getCode().equals(code)) {
                return siteIdEnum.getName();
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
