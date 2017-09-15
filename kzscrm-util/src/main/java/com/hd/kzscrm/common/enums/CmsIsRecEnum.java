package com.hd.kzscrm.common.enums;


/**
 * 文章装填
 * @author: lichangchao
 * @since: 2016年9月14日13:31:41
 */
public enum CmsIsRecEnum {

    TOP_NO("0", "不置顶"), TOP_YES("1", "置顶");
    String code; // 状态编号
    String name; //状态名称

    CmsIsRecEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (CmsIsRecEnum orderStatusEnum : CmsIsRecEnum.values()) {
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

    public  short getShortCode(){
        return  Short.valueOf(code);
    }
}
