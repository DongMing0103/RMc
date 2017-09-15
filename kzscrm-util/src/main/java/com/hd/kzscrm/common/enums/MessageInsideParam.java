/**
 *
 */
package com.hd.kzscrm.common.enums;

import com.hd.kzscrm.common.param.message.MessageParam;



/**
 * 短信内容
 *
 * @author: luochao
 * @since: 2016年8月9日  上午10:45:34
 * @history:
 */
public class MessageInsideParam extends MessageParam {

    /**
     * 接收人
     */
    //@NotNull(message = "接收人不能为空")
    private Long userIdRecv;

    /**
     * 消息发送人
     */
    //@NotNull(message = "发送人不能为空")
    private Long userIdSend;

    /**
     * 消息类型 MessageInsideTypeEnum
     */
    //@NotEmpty(message = "消息类型不能为空")
    private String type;

    public MessageInsideParam(Long userIdRecv, Long userIdSend, String type, MessageTemplateEnum messageTemplateEnum, Long companyId,boolean isBackGroundFlag, String... param) {
        this.userIdRecv = userIdRecv;
        this.userIdSend = userIdSend;
        this.type = type;
        this.param = param;
        this.messageTemplateEnum = messageTemplateEnum;
        this.setCompanyId(companyId);
        this.setBackGroundFlag(isBackGroundFlag);
    }

    /*public MessageInsideParam(Long userIdRecv, Long userIdSend, String type, MessageTemplateEnum messageTemplateEnum, Long companyId, String... param) {
        this.userIdRecv = userIdRecv;
        this.userIdSend = userIdSend;
        this.type = type;
        this.param = param;
        this.messageTemplateEnum = messageTemplateEnum;
        this.setCompanyId(companyId);
    }*/

    public Long getUserIdRecv() {
        return userIdRecv;
    }

    public void setUserIdRecv(Long userIdRecv) {
        this.userIdRecv = userIdRecv;
    }

    public Long getUserIdSend() {
        return userIdSend;
    }

    public void setUserIdSend(Long userIdSend) {
        this.userIdSend = userIdSend;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
