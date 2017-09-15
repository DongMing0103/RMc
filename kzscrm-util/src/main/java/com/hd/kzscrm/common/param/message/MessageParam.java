package com.hd.kzscrm.common.param.message;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.hd.kzscrm.common.enums.MessageTemplateEnum;
import com.hd.kzscrm.common.param.PageParam;

/**
 * Created by jiangjianwei
 * DATE: 2016/6/30.
 */
public class MessageParam extends PageParam{
    private Long id;

    /**是否群发*/
    private Long isGroupSend;

    /**消息发送人*/
    private Long userIdSend;

    /**消息接收人*/
    private Long userIdRecv;

    /**0 未读  1已读*/
    private Long isRead;

    /**1 交易消息  2账户消息  3采购单消息  4供应意向单消息   5供应单消息    6采购意向单消息   7采购订单消息   8供应订单消息*/
    private Long msgType;

    /**发送时间*/
    private Date createTime;

    /**接收时间*/
    private Date recvTime;

    /**消息内容*/
    private String content;

    private String prevMenu;

    private String menuId;

    /**true:是发送给后台人员   false：发送给中台人员*/
    private boolean isBackGroundFlag = false;

    public boolean isBackGroundFlag() {
        return isBackGroundFlag;
    }

    public void setBackGroundFlag(boolean backGroundFlag) {
        isBackGroundFlag = backGroundFlag;
    }

    /* add by shuweifei at 2016.9.20 --start */
    /** 公司ID */
    private Long companyId;
    public Long getCompanyId() {return companyId;}
    public void setCompanyId(Long companyId) {this.companyId = companyId;}
    /* add by shuweifei at 2016.9.20 --end */

    public String getPrevMenu() {
        return prevMenu;
    }

    public void setPrevMenu(String prevMenu) {
        this.prevMenu = prevMenu;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Long getIsGroupSend() {
        return isGroupSend;
    }

    public void setIsGroupSend(Long isGroupSend) {
        this.isGroupSend = isGroupSend;
    }

    public Long getIsRead() {
        return isRead;
    }

    public void setIsRead(Long isRead) {
        this.isRead = isRead;
    }

    public Long getMsgType() {
        return msgType;
    }

    public void setMsgType(Long msgType) {
        this.msgType = msgType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserIdSend() {
        return userIdSend;
    }

    public void setUserIdSend(Long userIdSend) {
        this.userIdSend = userIdSend;
    }

    public Long getUserIdRecv() {
        return userIdRecv;
    }

    public void setUserIdRecv(Long userIdRecv) {
        this.userIdRecv = userIdRecv;
    }




    /**消息模板*/
    protected //@NotNull(message = "消息模板不能为空")
    MessageTemplateEnum messageTemplateEnum;

    public MessageTemplateEnum getMessageTemplateEnum() {
        return messageTemplateEnum;
    }

    public void setMessageTemplateEnum(MessageTemplateEnum messageTemplateEnum) {
        this.messageTemplateEnum = messageTemplateEnum;
    }

    /**消息模版参数*/
    protected String[] param;

    public String[] getParam() {
        return param;
    }

    public void setParam(String... param) {
        this.param = param;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
