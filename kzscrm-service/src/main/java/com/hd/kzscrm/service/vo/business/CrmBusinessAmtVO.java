package com.hd.kzscrm.service.vo.business;

import java.math.BigDecimal;
import java.util.Date;

public class CrmBusinessAmtVO{

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 业务员id
	 */
	private Long businessId;
	/**
	 * 总入账
	 */
	private BigDecimal totalIncome;
	/**
	 * 用户ID（crm_user的ID）
	 */
	private Long userId;
	/**
	 * 代理商ID
	 */
	private Long agentId;
	/**
	 * 总支出
	 */
	private BigDecimal totalConsume;
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 可提现金额
	 */
	private BigDecimal canWithdrawDeposit;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人id
	 */
	private Long createUid;
	/**
	 * 是否删除，0删除，1正常
	 */
	private Integer delFlag;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新人id
	 */
	private Long updateUid;
	/**
	 * 业务类型 1代理商 2业务员,业务经理
	 */
	private Integer userType;
	/**
	 * 冻结金额
	 */
	private BigDecimal frozenMoney;

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
	 * 业务员id
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return 业务员id
	 */
	public Long getBusinessId() {
		return this.businessId;
	}

	/**
	 * 总入账
	 */
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	/**
	 * @return 总入账
	 */
	public BigDecimal getTotalIncome() {
		return this.totalIncome;
	}

	/**
	 * 总支出
	 */
	public void setTotalConsume(BigDecimal totalConsume) {
		this.totalConsume = totalConsume;
	}

	/**
	 * @return 总支出
	 */
	public BigDecimal getTotalConsume() {
		return this.totalConsume;
	}

	/**
	 * 余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * @return 余额
	 */
	public BigDecimal getBalance() {
		return this.balance;
	}

	/**
	 * 可提现金额
	 */
	public void setCanWithdrawDeposit(BigDecimal canWithdrawDeposit) {
		this.canWithdrawDeposit = canWithdrawDeposit;
	}

	/**
	 * @return 可提现金额
	 */
	public BigDecimal getCanWithdrawDeposit() {
		return this.canWithdrawDeposit;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 创建人id
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	/**
	 * @return 创建人id
	 */
	public Long getCreateUid() {
		return this.createUid;
	}

	/**
	 * 是否删除，0删除，1正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 是否删除，0删除，1正常
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 更新人id
	 */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	/**
	 * @return 更新人id
	 */
	public Long getUpdateUid() {
		return this.updateUid;
	}

	/**
	 * 冻结金额
	 */
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}

	/**
	 * @return 冻结金额
	 */
	public BigDecimal getFrozenMoney() {
		return this.frozenMoney;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the agentId
	 */
	public Long getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return the userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
