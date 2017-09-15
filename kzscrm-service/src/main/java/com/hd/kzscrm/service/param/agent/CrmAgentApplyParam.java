package com.hd.kzscrm.service.param.agent;

import java.util.Date;

import com.hd.kzscrm.common.param.PageParam;

public class CrmAgentApplyParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 申请时间
	 */
	private String applyTime;
	/**
	 * 加盟时间
	 */
	private Date enterTime;
	/**
	 * 代理商ID
	 */
	private Long agentId;
	/**
	 * 审核状态,0.申请中，1.申请通过，2.申请失败
	 */
	private Integer checkStatus;
	/**
	 * 审核人ID
	 */
	private Long checkUserId;
	/**
	 * 审核时间
	 */
	private String checkTime;
	//代理状态 0失效 1 有效
	private Integer agentStatus;
	
	/**
	 * 客户资源表Id
	 */
	private   Long   clientId ;
	/**
	 * 地区编号
	 */
	private Long areaCode;
	
	/**
	 * 合作时间(开始)
	 */
	private Date cooperationStartTime;
	private String cooperationStartTimeStr;
	
	/**
	 * 合作时间(结束)
	 */
	private Date cooperationEndTime;
	private String cooperationEndTimeStr;
	
	/**
	 * 合同图片路径
	 */
	private String contractImgPath;
	
	/**
	 * 创建人id(当前操作人/登录人Id)
	 */
	private   Long   createUid ;
	
	 private Integer delFlag;
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 主键
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 申请时间
	 */
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * @return 申请时间
	 */
	public String getApplyTime() {
		return this.applyTime;
	}

	
	public Integer getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
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
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * @return 审核时间
	 */
	public String getCheckTime() {
		return this.checkTime;
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

	public String getCooperationStartTimeStr() {
		return cooperationStartTimeStr;
	}

	public void setCooperationStartTimeStr(String cooperationStartTimeStr) {
		this.cooperationStartTimeStr = cooperationStartTimeStr;
	}

	public String getCooperationEndTimeStr() {
		return cooperationEndTimeStr;
	}

	public void setCooperationEndTimeStr(String cooperationEndTimeStr) {
		this.cooperationEndTimeStr = cooperationEndTimeStr;
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
	 * @param enterTime the enterTime to set
	 */
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

}
