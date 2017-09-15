package com.hd.kzscrm.service.param.split;


import java.math.BigDecimal;

import com.hd.kzscrm.common.param.PageParam;

/**商家现金流水表*/
public class PayCanteenCashflowParam extends PageParam{
	 /**主键*/
	private Long id;
	
	/**
	 * 查询开始时间
	 */
	private String starTime;
	/**
     * 支付方式(1,余额，2.支付宝，3.微信)
     */
    private Integer payModel;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 手机号
	 */
	private String mobilephone;
	/**
	 * 查询结束时间
	 */
	private String endTime;
	/**
     * 现金流水号
     */
    private String cashFlowNo;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 流水性质(1 收入  2 支出）
     */
    private String cashFlowType;
    /**
     * 流水类型（1.充值，2.提现，3.订单,4.退款）
     * 
     */
    private Integer cashflowType;
    /**
     * 金额
     */
    private BigDecimal cashFlowMoney;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 商家ID
     */
    private Long canteenId;
    /**
     * 订单id
     */
    private Long orderId;
    /**
    * 状态（1 成功   2失败,3.业务进行中）
    */
   private Integer cashFlowStatus;
	
   
   public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCashFlowType() {
		return cashFlowType;
	}
	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}
	public Long getCanteenId() {
		return canteenId;
	}
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}
	/**
	 * @return the cashFlowStatus
	 */
	public Integer getCashFlowStatus() {
		return cashFlowStatus;
	}
	/**
	 * @param cashFlowStatus the cashFlowStatus to set
	 */
	public void setCashFlowStatus(Integer cashFlowStatus) {
		this.cashFlowStatus = cashFlowStatus;
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
	/**
	 * @return the cashflowType
	 */
	public Integer getCashflowType() {
		return cashflowType;
	}
	/**
	 * @param cashflowType the cashflowType to set
	 */
	public void setCashflowType(Integer cashflowType) {
		this.cashflowType = cashflowType;
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
	/**
	 * @return the cashFlowMoney
	 */
	public BigDecimal getCashFlowMoney() {
		return cashFlowMoney;
	}
	/**
	 * @param cashFlowMoney the cashFlowMoney to set
	 */
	public void setCashFlowMoney(BigDecimal cashFlowMoney) {
		this.cashFlowMoney = cashFlowMoney;
	}
	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
   
   
   
    
}
