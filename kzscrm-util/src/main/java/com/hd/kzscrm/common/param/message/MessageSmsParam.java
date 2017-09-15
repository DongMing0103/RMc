/**
 *
 */
package com.hd.kzscrm.common.param.message;


import com.hd.kzscrm.common.enums.MessageTemplateEnum;

/**
 * 短信内容
 *
 * @author: luochao
 * @since: 2016年8月9日  上午10:45:34
 * @history:
 */
public class MessageSmsParam extends MessageParam {
    /**
     * 短信简码
     */
    //@NotEmpty(message = "短信简码不能为空")
    private String functionNo;

    /**
     * 电话
     */
    //@NotEmpty(message = "电话不能为空")
    private String mobile;

    /**
     * 接收短信的用户id，此字段和mobile配合使用，当mobile为空时取userId去用户中心获取mobile,不为空时此字段无效
     */
    private Long userId;

    /**
     * 操作人
     */
    //@NotEmpty(message = "操作人不能为空")
    private String userIP;

    public MessageSmsParam(String mobile, String userIP, MessageTemplateEnum messageTemplateEnum, String... param) {
        this.functionNo = messageTemplateEnum.getOperation();
        this.mobile = mobile;
        this.userIP = userIP;
        this.param = param;
        this.messageTemplateEnum = messageTemplateEnum;
    }

    public MessageSmsParam(Long userId, String userIP, MessageTemplateEnum messageTemplateEnum, String... param) {
        this.functionNo = messageTemplateEnum.getOperation();
        this.userId = userId;
        this.userIP = userIP;
        this.param = param;
        this.messageTemplateEnum = messageTemplateEnum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }


    public String getFunctionNo() {
        return functionNo;
    }

    public void setFunctionNo(String functionNo) {
        this.functionNo = functionNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
