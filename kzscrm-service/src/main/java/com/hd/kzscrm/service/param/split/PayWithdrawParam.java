package com.hd.kzscrm.service.param.split;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class PayWithdrawParam extends PageParam{
	/**
	 * 主键
	 */
	private Long id;
	private List<Long> ids;
	
	/**
	 * 查询开始时间
	 */
	private String starTime;
	/**
	 * 查询结束时间
	 */
	private String endTime;
	/**
	 * 手机号
	 */
	private String mobilephone;
	
	/**
	 * 提现账单编号
	 */
	private String withdrawalsFlowNo;
	/**
	 * 用户ID
	 */
	private Long userId;
	private List<Long> userIds;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 应收金额
	 */
	private BigDecimal initialMoney;
	/**
	 * 手续费
	 */
	private BigDecimal poundageMoney;
	/**
	 * 平台佣金
	 */
	private BigDecimal commissionMoney;
	/**
	 * 实收金额
	 */
	private BigDecimal realMoney;
	/**
	 * 状态（1申请中 2 已通过 3 未通过）
	 */
	private Integer status;
	/**
	 * 删除标识（0删除 1 存在）
	 */
	private Integer delFlag;
	/**
	 * 操作人名
	 */
	private Integer operationUserName;
	/**
	 * 操作时间
	 */
	private Date operationTime;
	/**
	 * 令牌
	 */
	private String userToken;
	/**
	 * 用户类型 1 企业  2商家  3个人
	 */
	private Integer userType;
	
	private Double withdrawBalance;
	
	private Long canteenId;
	
	/**
	 * 提现银行卡id
	 */
	private Long bankCardsId;
	
	private String version;
	
	//搜索条件
	private String selectNum;
	
	//搜索内容
	private String condition;
	
	/**
	 * 提现角色
	 * @return
	 */
	private String selectRole;
	
	
	
	/**
	 * 团队资源Ids
	 * @return
	 */
	private List<Long> teamIds;
	
	/**
	 * 代理商资源Ids
	 * @return
	 */
	private List<Long> agentIds;
	
	/**
	 * 业务员资源Ids
	 * @return
	 */
	private List<Long> businessIds;
 	
	
	public List<Long> getTeamIds() {
		return teamIds;
	}
	public void setTeamids(List<Long> teamids) {
		this.teamIds = teamids;
	}
	public String getSelectRole() {
		return selectRole;
	}
	public void setSelectRole(String selectRole) {
		this.selectRole = selectRole;
	}
	public List<Long> getAgentIds() {
		return agentIds;
	}
	public void setAgentIds(List<Long> agentIds) {
		this.agentIds = agentIds;
	}
	public List<Long> getBusinessIds() {
		return businessIds;
	}
	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}
	
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public void setTeamIds(List<Long> teamIds) {
		this.teamIds = teamIds;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSelectNum() {
		return selectNum;
	}
	public void setSelectNum(String selectNum) {
		this.selectNum = selectNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWithdrawalsFlowNo() {
		return withdrawalsFlowNo;
	}
	public void setWithdrawalsFlowNo(String withdrawalsFlowNo) {
		this.withdrawalsFlowNo = withdrawalsFlowNo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getInitialMoney() {
		return initialMoney;
	}
	public void setInitialMoney(BigDecimal initialMoney) {
		this.initialMoney = initialMoney;
	}
	public BigDecimal getPoundageMoney() {
		return poundageMoney;
	}
	public void setPoundageMoney(BigDecimal poundageMoney) {
		this.poundageMoney = poundageMoney;
	}
	public BigDecimal getCommissionMoney() {
		return commissionMoney;
	}
	public void setCommissionMoney(BigDecimal commissionMoney) {
		this.commissionMoney = commissionMoney;
	}
	public BigDecimal getRealMoney() {
		return realMoney;
	}
	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getOperationUserName() {
		return operationUserName;
	}
	public void setOperationUserName(Integer operationUserName) {
		this.operationUserName = operationUserName;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public Double getWithdrawBalance() {
		return withdrawBalance;
	}
	public void setWithdrawBalance(Double withdrawBalance) {
		this.withdrawBalance = withdrawBalance;
	}
	public Long getCanteenId() {
		return canteenId;
	}
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}
	public Long getBankCardsId() {
		return bankCardsId;
	}
	public void setBankCardsId(Long bankCardsId) {
		this.bankCardsId = bankCardsId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
