package com.hd.kzscrm.dao.entity.split;

import java.math.BigDecimal;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;
/**
 * @Description: 用户提现流水表
 *
 */
@Entity
@Table(name="pay_withdraw")
public class PayWithdrawPO {
	
	/**
	 * 主键
	 */
	@Column(name="id")
	@Id
	private Long id;
	/**
	 * 提现账单编号
	 */
	@Column(name="withdrawals_flow_no")
	private String withdrawalsFlowNo;
	/**
	 * 现金流水表id
	 */
	@Column(name="pay_canteen_cashflow_id")
	private Long payCanteenCashFlowId;
	/**
	 * 用户ID
	 */
	@Column(name="user_id")
	private Long userId;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 应收金额
	 */
	@Column(name="initial_money")
	private BigDecimal initialMoney;
	/**
	 * 手续费
	 */
	@Column(name="poundage_money")
	private BigDecimal poundageMoney;
	/**
	 * 平台佣金
	 */
	@Column(name="commission_money")
	private BigDecimal commissionMoney;
	/**
	 * 实收金额
	 */
	@Column(name="real_money")
	private BigDecimal realMoney;
	/**
	 * 状态（1申请中 2 已通过 3 未通过）
	 */
	@Column(name="status")
	private Integer status;
	/**
	 * 删除标识（0删除 1 存在）
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	/**
	 * 操作人名
	 */
	@Column(name="operation_user_name")
	private Integer operationUserName;
	/**
	 * 操作时间
	 */
	@Column(name="operation_time")
	private Date operationTime;
	/**
	 * 用户类型 1 企业  2商家  3个人()
	 */
	@Column(name="user_type")
	private Integer userType;
	/**
	 * 审核事由
	 */
	@Column(name="cause")
	private String cause;
	
	/**
	 * 提现银行卡id
	 * @author jyt 2017年4月6日 下午4:46:33
	 */
	@Column(name="bank_cards_id")
	private Long bankCardsId;
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
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
	public Long getBankCardsId() {
		return bankCardsId;
	}
	public void setBankCardsId(Long bankCardsId) {
		this.bankCardsId = bankCardsId;
	}
	public Long getPayCanteenCashFlowId() {
		return payCanteenCashFlowId;
	}
	public void setPayCanteenCashFlowId(Long payCanteenCashFlowId) {
		this.payCanteenCashFlowId = payCanteenCashFlowId;
	}
	
	
}
