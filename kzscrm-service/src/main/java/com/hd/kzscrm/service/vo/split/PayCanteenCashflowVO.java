package com.hd.kzscrm.service.vo.split;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PayCanteenCashflowVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 现金流水号
	 */
	private String cashFlowNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 用餐时间-用于excel格式化
	 * @author jyt 2017年3月15日 上午10:17:14
	 */
	private String eatTimeStr;
	/**
	 * 流水性质(1 收入  2 支出）
	 */
	private Integer cashFlowType;
	 /**
     * 流水类型（1.充值，2.提现，3.订单,4.退款）
     * 
     */
    private Integer cashflowType;
	/**
	 * 用于导出流水性质的名字
	 */
	private String cashFlowTypes;
	/**
	 * 金额
	 */
	private BigDecimal cashFlowMoney;
	
	/**
	 * 总支出
	 */
	private BigDecimal totalConsume;
	/**
	 * 余额 类型不同
	 */
	private BigDecimal balances;
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 商家ID
	 */
	private Long canteenId;
	/**
	 * 状态（1 成功   2失败,3.业务进行中）
	 */
	private Integer cashFlowStatus;
	
	

	/**
	 * 状态转换格式
	 */
	private String cashFlowStatusName;

	 /**
     * 用户Id
     */
    private Long userId;
    
    /** 
    * 订单id
    */ 
    private Long canteenOrderId;
    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 订单id
     */
    private Long orderId;
    
    /** 
    * 用户帐号 
    */ 
    private String mobilephone;
    
    /** 
    * 商家帐号
    */ 
    private String contactPhone;
    
    /** 
    * 支付时间 
    */ 
    private Date payTime;
    
    /** 
    * 订单状态
    */ 
    private Integer status;
    
    /**
     * 支付方式
     */
    private Integer payModel;
    
    /**
     * 支付方式 str 用于导出
     * */
    private String payModelStr;
    
    /** 
    * 订单状态中文
    */ 
    private String statusName;
    
    /** 
    * 订单金额
    */ 
    private BigDecimal totalMoney;
    
    /** 
    * 余额统计
    */ 
    private Double balanceSum;
    
    /**
     * 退款类型（1,商家取消退款  2,系统强制退款）
     */
    private Integer refundType;
    
    /**
     * 个数
     * */
    private Integer countNum;
    
    /**
     * 交易时间戳
     * */
    private String payTimestamp;
    
	public Double getBalanceSum() {
		return balanceSum;
	}
	public void setBalanceSum(Double balanceSum) {
		this.balanceSum = balanceSum;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
    
    public BigDecimal getTotalConsume() {
		return totalConsume;
	}
	public void setTotalConsume(BigDecimal totalConsume) {
		this.totalConsume = totalConsume;
	}

	/**
     * 订单实付金额
     * */
    private BigDecimal orderRealMoney;
    /**
     * 订单商家金额
     * */
    private BigDecimal realMoney;
    /**
     * 订单抽成
     * */
    private BigDecimal cutMoney;
    /**
     * 订单通道费
     * */
    private BigDecimal channelMoney;
    
    /**
     * 投诉状态中文
     */
    private String comStatusName;
    
    /**
     * 投诉状态
     */
    private Integer comStatus;
    

	public Integer getComStatus() {
		return comStatus;
	}
	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}
	public String getComStatusName() {
		return comStatusName;
	}
	public void setComStatusName(String comStatusName) {
		this.comStatusName = comStatusName;
	}
	public BigDecimal getBalances() {
		return balances;
	}
	public void setBalances(BigDecimal balances) {
		this.balances = balances;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public Long getCanteenOrderId() {
		return canteenOrderId;
	}
	public void setCanteenOrderId(Long canteenOrderId) {
		this.canteenOrderId = canteenOrderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCashFlowStatusName() {
		return cashFlowStatusName;
	}
	public void setCashFlowStatusName(String cashFlowStatusName) {
		this.cashFlowStatusName = cashFlowStatusName;
	}
	public String getEatTimeStr() {
		return eatTimeStr;
	}
	public void setEatTimeStr(String eatTimeStr) {
		this.eatTimeStr = eatTimeStr;
	}
	public String getCashFlowTypes() {
		return cashFlowTypes;
	}
	public void setCashFlowTypes(String cashFlowTypes) {
		this.cashFlowTypes = cashFlowTypes;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCashFlowType() {
		return cashFlowType;
	}
	public void setCashFlowType(Integer cashFlowType) {
		this.cashFlowType = cashFlowType;
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
	public Integer getCashFlowStatus() {
		return cashFlowStatus;
	}
	public void setCashFlowStatus(Integer cashFlowStatus) {
		this.cashFlowStatus = cashFlowStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PayCanteenCashflowDTO [id=" + id + ", cashFlowNo=" + cashFlowNo
				+ ", createTime=" + createTime + ", eatTimeStr=" + eatTimeStr
				+ ", cashFlowType=" + cashFlowType + ", cashFlowTypes="
				+ cashFlowTypes + ", cashFlowMoney=" + cashFlowMoney
				+ ", balance=" + balance + ", canteenId=" + canteenId
				+ ", cashFlowStatus=" + cashFlowStatus
				+ ", cashFlowStatusName=" + cashFlowStatusName + ", userId="
				+ userId + ", canteenOrderId=" + canteenOrderId
				+ ", mobilephone=" + mobilephone + ", contactPhone="
				+ contactPhone + ", payTime=" + payTime + ", status=" + status
				+ "]";
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getPayModel() {
		return payModel;
	}
	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getPayModelStr() {
		return payModelStr;
	}
	public void setPayModelStr(String payModelStr) {
		this.payModelStr = payModelStr;
	}
	public BigDecimal getOrderRealMoney() {
		return orderRealMoney;
	}
	public void setOrderRealMoney(BigDecimal orderRealMoney) {
		this.orderRealMoney = orderRealMoney;
	}
	public BigDecimal getCutMoney() {
		return cutMoney;
	}
	public void setCutMoney(BigDecimal cutMoney) {
		this.cutMoney = cutMoney;
	}
	public BigDecimal getChannelMoney() {
		return channelMoney;
	}
	public void setChannelMoney(BigDecimal channelMoney) {
		this.channelMoney = channelMoney;
	}
	public BigDecimal getRealMoney() {
		return realMoney;
	}
	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}
	public Integer getRefundType() {
		return refundType;
	}
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}
	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public String getPayTimestamp() {
		return payTimestamp;
	}
	public void setPayTimestamp(String payTimestamp) {
		this.payTimestamp = payTimestamp;
	}
}
