package com.hd.kzscrm.dao.entity.userRpt;

import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

@Entity
@Table(name="qzs_message")
public class BaseUserMessagePO {
    /***/
    @Column(name="id")
    @Id
    //@Sequence
    private Long id;

    /**是否群发，0 否 1是*/
    @Column(name="is_group_send")
    private String isGroupSend;

    /**消息发送人*/
    @Column(name="user_id_send")
    private Long userIdSend;

    /**消息接收人*/
    @Column(name="user_id_recv")
    private Long userIdRecv;

    /**0 未读  1已读*/
    @Column(name="is_read")
    private Integer isRead;

    /**1 交易消息  2账户消息  3采购单消息  4供应意向单消息   5供应单消息    6采购意向单消息   7采购订单消息   8供应订单消息*/
    @Column(name="msg_type")
    private String msgType;

    /**接收时间*/
    @Column(name="recv_time")
    private Date recvTime;

    /**消息内容*/
    @Column(name="content")
    private String content;

    /***/
    @Column(name="language_code")
    private String languageCode;

    /***/
    @Column(name="create_time")
    private Date createTime;

    /***/
    @Column(name="create_id")
    private Long createId;

    /***/
    @Column(name="update_time")
    private Date updateTime;

    /***/
    @Column(name="update_id")
    private Long updateId;

    /**删除标记，0正常，1已删除*/
    @Column(name="delete_flag")
    private String deleteFlag;

    /* add by shuweifei at 2016.9.20 --start */
    /** 公司ID */
    @Column(name="company_id")
    private Long companyId;
    public Long getCompanyId() {return companyId;}
    public void setCompanyId(Long companyId) {this.companyId = companyId;}
    /* add by shuweifei at 2016.9.20 --end */



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsGroupSend() {
        return isGroupSend;
    }

    public void setIsGroupSend(String isGroupSend) {
        this.isGroupSend = isGroupSend == null ? null : isGroupSend.trim();
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

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
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
        this.content = content == null ? null : content.trim();
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode == null ? null : languageCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }
}