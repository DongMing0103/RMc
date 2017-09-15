package com.hd.kzscrm.dao.entity.agent;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmagentapply

@Entity
@Table(name = "crm_agent_apply")
public class CrmAgentApplyPO implements Serializable {

	/**
	 * 主键
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;

	@Column(name = "del_flag")
	private Integer delFlag;
	/**
	 * 加盟时间
	 */
	@Column(name = "enter_time")
	private Date enterTime;

	/**
	 * 申请时间
	 */
	@Column(name = "apply_time")
	private java.util.Date applyTime;

	/**
	 * 代理商ID
	 */
	@Column(name = "agent_id")
	private Long agentId;

	/**
	 * 审核状态 ,0.申请中，1.申请通过，2.申请失败
	 */
	@Column(name = "check_status")
	private Integer checkStatus;

	/**
	 * 审核人ID
	 */
	@Column(name = "check_user_id")
	private Long checkUserId;

	/**
	 * 审核时间
	 */
	@Column(name = "check_time")
	private java.util.Date checkTime;

	/**
	 * 客户资源表Id
	 */
	@Column(name = "client_id")
	private Long clientId;

	/**
	 * 地区编号
	 */
	@Column(name = "area_code")
	private Long areaCode;

	/**
	 * 合作时间(开始)
	 */
	@Column(name = "cooperation_start_time")
	private Date cooperationStartTime;

	/**
	 * 合作时间(结束)
	 */
	@Column(name = "cooperation_end_time")
	private Date cooperationEndTime;

	/**
	 * 合同图片路径
	 */
	@Column(name = "contract_img_path")
	private String contractImgPath;

	/**
	 * 创建人id(当前操作人/登录人Id)
	 */
	@Column(name = "create_uid")
	private Long createUid;

	// 默认空构造函数
	public CrmAgentApplyPO() {

	}

	/**
	 * 申请时间
	 */
	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * @return 申请时间
	 */
	public java.util.Date getApplyTime() {
		return this.applyTime;
	}

	/**
	 * 食堂ID
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return 食堂ID
	 */
	public Long getAgentId() {
		return this.agentId;
	}

	/**
	 * 审核状态
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * @return 审核状态
	 */
	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	/**
	 * 审核人ID
	 */
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	/**
	 * @return 审核人ID
	 */
	public Long getCheckUserId() {
		return this.checkUserId;
	}

	/**
	 * 审核时间
	 */
	public void setCheckTime(java.util.Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * @return 审核时间
	 */
	public java.util.Date getCheckTime() {
		return this.checkTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public Date getCooperationStartTime() {
		return cooperationStartTime;
	}

	public void setCooperationStartTime(Date cooperationStartTime) {
		this.cooperationStartTime = cooperationStartTime;
	}

	public Date getCooperationEndTime() {
		return cooperationEndTime;
	}

	public void setCooperationEndTime(Date cooperationEndTime) {
		this.cooperationEndTime = cooperationEndTime;
	}

	public String getContractImgPath() {
		return contractImgPath;
	}

	public void setContractImgPath(String contractImgPath) {
		this.contractImgPath = contractImgPath;
	}

	public Long getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	/**
	 * @return the enterTime
	 */
	public Date getEnterTime() {
		return enterTime;
	}

	/**
	 * @param enterTime
	 *            the enterTime to set
	 */
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

}
