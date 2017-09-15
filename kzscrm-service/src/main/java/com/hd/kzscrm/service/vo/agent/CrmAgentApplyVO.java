package com.hd.kzscrm.service.vo.agent;

import java.util.Date;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.annotation.DataInject;
import com.hd.kzscrm.service.vo.BaseVO;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;

public class CrmAgentApplyVO implements BaseVO {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;

	private Integer delFlag;

	/**
	 * 申请时间
	 */
	private java.util.Date applyTime;
	
	private CrmAgentVO crmAgentVO;

	/**
	 * 代理商ID
	 */
	private Long agentId;
	/**
	 * 加盟时间
	 */
	private Date enterTime;

	/**
	 * 审核状态 ,0.申请中，1.申请通过，2.申请失败
	 */
	private Integer checkStatus;
	/**
	 * 客户资源
	 */
	private CrmClientResourceVO crmClientResourceVO;

	/**
	 * 审核人ID
	 */
	private Long checkUserId;

	/**
	 * 审核时间
	 */
	private java.util.Date checkTime;
	/**
	 * 客户资源表Id
	 */
	private Long clientId;
	/**
	 * 地区编号
	 */
	private Long areaCode;
	
	/**
	 * 区域名字
	 */
	@DataInject(DatabaseTableNameEnum.base_area)
	private String areaName;

	/**
	 * 业务员ID 业务员发展的代理商，1级代理商此值为空
	 */
	private Long businessId;
	
	/**
	 * 业务员名称
	 */
	private String businessName;
	
	/**
	 * 业务员团队
	 */
	private String businessTeamName;

	/**
	 * 合作时间(开始)
	 */
	private Date cooperationStartTime;

	/**
	 * 合作时间(结束)
	 */
	private Date cooperationEndTime;

	/**
	 * 合同图片路径
	 */
	private String contractImgPath;

	/**
	 * 创建人id(当前操作人/登录人Id)
	 */
	private Long createUid;
	
	/**
	 * 地址
	 */
	private String address;

	/**
	 * 注册时间
	 */
	private java.util.Date registerTime;

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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessTeamName() {
		return businessTeamName;
	}

	public void setBusinessTeamName(String businessTeamName) {
		this.businessTeamName = businessTeamName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public java.util.Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * @return the crmAgentVO
	 */
	public CrmAgentVO getCrmAgentVO() {
		return crmAgentVO;
	}

	/**
	 * @param crmAgentVO the crmAgentVO to set
	 */
	public void setCrmAgentVO(CrmAgentVO crmAgentVO) {
		this.crmAgentVO = crmAgentVO;
	}

	/**
	 * @return the crmClientResourceVO
	 */
	public CrmClientResourceVO getCrmClientResourceVO() {
		return crmClientResourceVO;
	}

	/**
	 * @param crmClientResourceVO the crmClientResourceVO to set
	 */
	public void setCrmClientResourceVO(CrmClientResourceVO crmClientResourceVO) {
		this.crmClientResourceVO = crmClientResourceVO;
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
