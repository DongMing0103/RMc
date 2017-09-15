/**
 * 
 */
package com.hd.kzscrm.service.vo.split;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author 黄霄仪
 * @date 2017年3月7日 下午7:56:28
 * 
 */
@JsonInclude(Include.NON_EMPTY)
public class PayWithdrawVO {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 查询开始时间
	 */
	private String starTime;
	/**
	 * 查询结束时间
	 */
	private String endTime;
	
	/**
	 * 提现账单编号
	 */
	private String withdrawalsFlowNo;
	/**
	 * 用户ID
	 */
	private Long userId;
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
	private String statusName;
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
	 * 用户类型 1 企业  2商家  3个人(Crm:4 平台 5 代理商 6 业务员)
	 */
	private Integer userType;
	private String userTypeName;
	
	/**
	 * 账户余额
	 * @return
	 */
	private BigDecimal balance;
	
	/**
	 * 现金流水号
	 * @return
	 */
	private String cashFlowNo;
	
	/**
	 * 团队名称
	 * @return
	 */
	private String teamName;
	
	/**
	 * 代理商名称
	 * @return
	 */
	private String agentName;
	
	/**
	 * 业务员名称
	 * @return
	 */
	private String businessName;
	
	/**
	 * 页面渲染的名称
	 * @return
	 */
	private String allName;
	
	
	public String getAllName() {
		return allName;
	}
	public void setAllName(String allName) {
		this.allName = allName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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
	public BigDecimal getBalance() {
		return balance;
	}
	
	
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getCashFlowNo() {
		return cashFlowNo;
	}
	public void setCashFlowNo(String cashFlowNo) {
		this.cashFlowNo = cashFlowNo;
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
}
