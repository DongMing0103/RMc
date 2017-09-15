package com.hd.kzscrm.service.vo.split;

import java.math.BigDecimal;

import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.wolverine.aop.Column;

public class SplitCutInfoVO extends SplitCutInfoPO {
	private Long id;

	/**
	 * 商家编号
	 */
	@Column(name = "canteen_id")
	private Long canteenId;

	/**
	 * 商家用户编号
	 */
	@Column(name = "canteen_user_id")
	private Long canteenUserId;

	/**
	 * 订单号
	 */
	@Column(name = "order_no")
	private String orderNo;

	/**
	 * 用户编号
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 新建时间
	 */
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 * 商家金额
	 */
	@Column(name = "real_money")
	private BigDecimal realMoney;

	/**
	 * 平台抽成金额
	 */
	@Column(name = "cut_money")
	private BigDecimal cutMoney;

	/**
	 * 抽成比例
	 */
	@Column(name = "cut_ratio")
	private BigDecimal cutRatio;

	/**
	 * 通道费
	 */
	@Column(name = "channel_money")
	private BigDecimal channelMoney;

	/**
	 * 抽成状态
	 */
	@Column(name = "cut_status")
	private Integer cutStatus;

	/**
	 * 抽成时间
	 */
	@Column(name = "cut_time")
	private java.util.Date cutTime;

	/**
	 * 取消时间
	 */
	@Column(name = "cancel_time")
	private java.util.Date cancelTime;

	/**
	 * 退款订单号
	 */
	@Column(name = "refund_order_no")
	private String refundOrderNo;

	/**
	 * 删除，0.删除，1.存在
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	/**
	 * 订单实际金额
	 */
	@Column(name="order_read_money")
	private BigDecimal orderRealMoney;
	
	/**
	 * 支付方式
	 */
	@Column(name="pay_model")
	private Integer payModel;
	private String canteenName;
	/**
	 * 业务分账 0 为分账 1已分账
	 */
	@Column(name="business_cut_status")
	private Integer businessCutStatus;
	
	

	public Integer getBusinessCutStatus() {
		return businessCutStatus;
	}

	public void setBusinessCutStatus(Integer businessCutStatus) {
		this.businessCutStatus = businessCutStatus;
	}
	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCanteenId() {
		return canteenId;
	}

	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	public Long getCanteenUserId() {
		return canteenUserId;
	}

	public void setCanteenUserId(Long canteenUserId) {
		this.canteenUserId = canteenUserId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	public BigDecimal getCutMoney() {
		return cutMoney;
	}

	public void setCutMoney(BigDecimal cutMoney) {
		this.cutMoney = cutMoney;
	}

	public BigDecimal getCutRatio() {
		return cutRatio;
	}

	public void setCutRatio(BigDecimal cutRatio) {
		this.cutRatio = cutRatio;
	}

	public BigDecimal getChannelMoney() {
		return channelMoney;
	}

	public void setChannelMoney(BigDecimal channelMoney) {
		this.channelMoney = channelMoney;
	}

	public Integer getCutStatus() {
		return cutStatus;
	}

	public void setCutStatus(Integer cutStatus) {
		this.cutStatus = cutStatus;
	}

	public java.util.Date getCutTime() {
		return cutTime;
	}

	public void setCutTime(java.util.Date cutTime) {
		this.cutTime = cutTime;
	}

	public java.util.Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(java.util.Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getRefundOrderNo() {
		return refundOrderNo;
	}

	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public BigDecimal getOrderRealMoney() {
		return orderRealMoney;
	}

	public void setOrderRealMoney(BigDecimal orderRealMoney) {
		this.orderRealMoney = orderRealMoney;
	}

	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}
	
	
}
