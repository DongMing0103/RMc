package com.hd.kzscrm.dao.entity.user;

import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

@Entity
@Table(name="qzs_message")
public class QzsMessagePO {
	/***/
    @Column(name="id")
    @Id
    //@Sequence
    private Long id;

    /**0:否   1:是*/
    @Column(name="is_groupsend")
    private Long isGroupsend;

    /**语言*/
    @Column(name="language_code")
    private Long languageCode;
    
    /**消息发送人*/
    @Column(name="user_id_send")
    private Long userIdSend;

    /**消息接收人*/
    @Column(name="user_id_recv")
    private Long userIdRecv;

    /**连接关联处理时的参数  可为空，预留*/
    @Column(name="content")
    private String content;

    /**0:未读   1:已读    2:未读未处理    3:已读未处理    7:已读已处理*/
    @Column(name="is_read")
    private Short isRead;

    /**1:交易消息     2:账户消息*/
    @Column(name="msg_type")
    private Short msgType;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="recv_time")
    private Date recvedTime;

    @Column(name="company_id")
    private Long companyId;

    /**行业ID*/
    @Column(name="industry_id")
    private Long industryId;

    /**消息所属单据编号*/
    @Column(name="link_id")
    private Long linkId;

    @Column(name="link_process_url")
    private String linkProcessUrl;
    
    @Column(name="delete_flag")
    private String deleteFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsGroupsend() {
		return isGroupsend;
	}

	public void setIsGroupsend(Long isGroupsend) {
		this.isGroupsend = isGroupsend;
	}

	public Long getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(Long languageCode) {
		this.languageCode = languageCode;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Short getIsRead() {
		return isRead;
	}

	public void setIsRead(Short isRead) {
		this.isRead = isRead;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRecvedTime() {
		return recvedTime;
	}

	public void setRecvedTime(Date recvedTime) {
		this.recvedTime = recvedTime;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public String getLinkProcessUrl() {
		return linkProcessUrl;
	}

	public void setLinkProcessUrl(String linkProcessUrl) {
		this.linkProcessUrl = linkProcessUrl;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

}