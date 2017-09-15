package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmbusinessamt

@Entity
@Table(name = "crm_business_amt")
public class CrmBusinessAmtPO implements Serializable {

	/**
	 * 主键
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;

	/**
	 * 业务员id
	 */
	@Column(name = "business_id")
	private Long businessId;

	/**
	 * 用户ID（crm_user的ID）
	 */
	@Column(name = "user_id")
	private Long userId;
	/**
	 * 代理商ID
	 */
	@Column(name = "agent_id")
	private Long agentId;

	/**
	 * 总入账
	 */
	@Column(name = "total_income")
	private BigDecimal totalIncome;

	/**
	 * 总支出
	 */
	@Column(name = "total_consume")
	private BigDecimal totalConsume;

	/**
	 * 余额
	 */
	@Column(name = "balance")
	private BigDecimal balance;

	/**
	 * 可提现金额
	 */
	@Column(name = "can_withdraw_deposit")
	private BigDecimal canWithdrawDeposit;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 * 创建人id
	 */
	@Column(name = "create_uid")
	private Long createUid;

	/**
	 * 是否删除，0删除，1正常
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private java.util.Date updateTime;

	/**
	 * 更新人id
	 */
	@Column(name = "update_uid")
	private Long updateUid;

	/**
	 * 冻结金额
	 */
	@Column(name = "frozen_money")
	private BigDecimal frozenMoney;

	/**
	 * 交易时间戳
	 * */
	@Column(name = "pay_timestamp")
	private String payTimestamp;
	/**
	 * 业务类型 1代理商 2业务员,业务经理
	 */
	@Column(name = "user_type")
	private Integer userType;

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getPayTimestamp() {
		return payTimestamp;
	}

	public void setPayTimestamp(String payTimestamp) {
		this.payTimestamp = payTimestamp;
	}

	// 默认空构造函数
	public CrmBusinessAmtPO() {

	}

	// get set 方法
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
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public java.util.Date getCreateTime() {
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
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 更新时间
	 */
	public java.util.Date getUpdateTime() {
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
	 * @param userId
	 *            the userId to set
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
	 * @param agentId
	 *            the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

}
