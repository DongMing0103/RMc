package com.hd.kzscrm.common.enums;


/**
 * 文章类型
 * @author: lichangchao
 * @since: 2016年9月14日13:31:41
 */
public enum ArtDataTypeEnum {
    ART(0, "文章"), TD(1, "技术发展"),SL(2,"解决方案");
    Integer code; // 状态编号
    String name; //状态名称

    ArtDataTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (ArtDataTypeEnum orderStatusEnum : ArtDataTypeEnum.values()) {
            if (orderStatusEnum.getCode().equals(code)) {
                return orderStatusEnum.getName();
            }
        }
        return null;
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
