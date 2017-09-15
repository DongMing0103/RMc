package com.hd.kzscrm.common.enums;

/** 
* 消息类型
* @author: luochao
* @since: 2016年7月19日  下午7:55:26
* @history:
*/
public enum MessageTypeEnum {
    /**短信*/
    SMS("1", "短信"),
    /**站内信*/
    INSIDE_MESSAGE("2", "站内信");

    String code; // 状态编号
    String name; //状态名称

    MessageTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (MessageTypeEnum orderStatusEnum : MessageTypeEnum.values()) {
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
