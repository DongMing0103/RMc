package com.hd.kzscrm.dao.entity.canteen;

import java.math.BigDecimal;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 商家现金流水表
 * @author wcf
 *
 */
@Entity
@Table(name="crm_pay_canteen_cashflow")
public class CrmPayCanteenCashflowPO {
	
    @Column(name="id")
    @Id
    private Long id;
    
    /**
     * 现金流水号
     */
    @Column(name="cash_flow_no")
    private String cashFlowNo;
    
    /**
     * 用户Id
     */
    @Column(name="user_id")
    private Long userId;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;
    
    /**
     * 流水性质(1 收入  2 支出）
     */
    @Column(name="cash_flow_nature")
    private Integer cashFlowNature;
    
    /**
     * 金额
     */
    @Column(name="cash_flow_money")
    private BigDecimal cashFlowMoney;

    /**
     * 余额
     */
    @Column(name="balance")
    private BigDecimal balance;
    
    /**
     * 商家ID
     */
    @Column(name="canteen_id")
    private Long canteenId;

    /**
     * 订单Id
     */
    @Column(name="canteen_order_id")
    private Long canteenOrderId;
    
    /**
     * 状态（1 成功   2失败）
     */
    @Column(name="cash_flow_status")
    private Integer cashFlowStatus;
    
    /**
     * 用户类型 1 企业  2商家  3个人
     */
    @Column(name="user_type")
    private Integer userType;
    
    /**
     *  流水类型（1.充值，2.提现，3.订单,4.退款）
     * */
    @Column(name="cashflow_type")
    private Integer cashflowType;
    /**
     * 支付方式(1,余额，2.支付宝，3.微信  4.企业)
     */
    @Column(name="pay_model")
    private Integer payModel;
    
    /**
     * 退款类型（1,商家取消退款  2,系统强制退款）
     */
    @Column(name="refund_type")
    private Integer refundType;

    /**
     * 交易时间戳
     * */
    @Column(name="pay_timestamp")
    private String payTimestamp;
    /**
     * 订单号
     */
    @Column(name="order_no")
    private String orderNo;
    
    
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCashFlowNo() {
		return cashFlowNo;
	}

	public void setCashFlowNo(String cashFlowNo) {
		this.cashFlowNo = cashFlowNo;
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
	/**
	 * @return the cashFlowNature
	 */
	public Integer getCashFlowNature() {
		return cashFlowNature;
	}

	/**
	 * @param cashFlowNature the cashFlowNature to set
	 */
	public void setCashFlowNature(Integer cashFlowNature) {
		this.cashFlowNature = cashFlowNature;
	}

	public BigDecimal getCashFlowMoney() {
		return cashFlowMoney;
	}

	public void setCashFlowMoney(BigDecimal cashFlowMoney) {
		this.cashFlowMoney = cashFlowMoney;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Long getCanteenId() {
		return canteenId;
	}

	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	public Long getCanteenOrderId() {
		return canteenOrderId;
	}

	public void setCanteenOrderId(Long canteenOrderId) {
		this.canteenOrderId = canteenOrderId;
	}

	public Integer getCashFlowStatus() {
		return cashFlowStatus;
	}

	public void setCashFlowStatus(Integer cashFlowStatus) {
		this.cashFlowStatus = cashFlowStatus;
	}

	public Integer getCashflowType() {
		return cashflowType;
	}

	public void setCashflowType(Integer cashflowType) {
		this.cashflowType = cashflowType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserType() {
		return userType;
	}

	@Override
	public String toString() {
		return "PayCanteenCashflowPO [id=" + id + ", cashFlowNo=" + cashFlowNo
				+ ", userId=" + userId + ", createTime=" + createTime
				+ ", cashFlowType=" + cashFlowNature + ", cashFlowMoney="
				+ cashFlowMoney + ", balance=" + balance + ", canteenId="
				+ canteenId + ", canteenOrderId=" + canteenOrderId
				+ ", cashFlowStatus=" + cashFlowStatus + ", userType="
				+ userType + ", cashflowType=" + cashflowType + ", getId()="
				+ getId() + ", getCashFlowNo()=" + getCashFlowNo()
				+ ", getUserId()=" + getUserId() + ", getCreateTime()="
				+ getCreateTime() + ", getCashFlowType()=" + getCashFlowNature()
				+ ", getCashFlowMoney()=" + getCashFlowMoney()
				+ ", getBalance()=" + getBalance() + ", getCanteenId()="
				+ getCanteenId() + ", getCanteenOrderId()="
				+ getCanteenOrderId() + ", getCashFlowStatus()="
				+ getCashFlowStatus() + ", getCashflowType()="
				+ getCashflowType() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	/**
	 * @return the payModel
	 */
	public Integer getPayModel() {
		return payModel;
	}

	/**
	 * @param payModel the payModel to set
	 */
	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	public String getPayTimestamp() {
		return payTimestamp;
	}

	public void setPayTimestamp(String payTimestamp) {
		this.payTimestamp = payTimestamp;
	}
    
}

