package com.hd.kzscrm.dao.entity.split;


import java.io.Serializable;
import java.math.*;

import com.hd.wolverine.aop.AutoIncrease;
import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//splitcutinfo

@Entity
@Table(name = "split_cut_info")
public class SplitCutInfoPO implements Serializable {

	/**
	 * 主键
	 */
	@Column(name = "id")
	@Id
	@AutoIncrease
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
 

	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	public void setOrderRealMoney(BigDecimal orderRealMoney) {
		this.orderRealMoney = orderRealMoney;
	}

	public BigDecimal getOrderRealMoney() {
		return orderRealMoney;
	}

	
	// 默认空构造函数
	public SplitCutInfoPO() {

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
	 * 商家编号
	 */
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	/**
	 * @return 商家编号
	 */
	public Long getCanteenId() {
		return this.canteenId;
	}

	/**
	 * 商家用户编号
	 */
	public void setCanteenUserId(Long canteenUserId) {
		this.canteenUserId = canteenUserId;
	}

	/**
	 * @return 商家用户编号
	 */
	public Long getCanteenUserId() {
		return this.canteenUserId;
	}

	/**
	 * 订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return 订单号
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 用户编号
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return 用户编号
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * 新建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 新建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 商家金额
	 */
	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	/**
	 * @return 商家金额
	 */
	public BigDecimal getRealMoney() {
		return this.realMoney;
	}

	/**
	 * 平台抽成金额
	 */
	public void setCutMoney(BigDecimal cutMoney) {
		this.cutMoney = cutMoney;
	}

	/**
	 * @return 平台抽成金额
	 */
	public BigDecimal getCutMoney() {
		return this.cutMoney;
	}

	/**
	 * 抽成比例
	 */
	public void setCutRatio(BigDecimal cutRatio) {
		this.cutRatio = cutRatio;
	}

	/**
	 * @return 抽成比例
	 */
	public BigDecimal getCutRatio() {
		return this.cutRatio;
	}

	/**
	 * 通道费
	 */
	public void setChannelMoney(BigDecimal channelMoney) {
		this.channelMoney = channelMoney;
	}

	/**
	 * @return 通道费
	 */
	public BigDecimal getChannelMoney() {
		return this.channelMoney;
	}

	/**
	 * 抽成状态
	 */
	public void setCutStatus(Integer cutStatus) {
		this.cutStatus = cutStatus;
	}

	/**
	 * @return 抽成状态
	 */
	public Integer getCutStatus() {
		return this.cutStatus;
	}

	/**
	 * 抽成时间
	 */
	public void setCutTime(java.util.Date cutTime) {
		this.cutTime = cutTime;
	}

	/**
	 * @return 抽成时间
	 */
	public java.util.Date getCutTime() {
		return this.cutTime;
	}

	/**
	 * 取消时间
	 */
	public void setCancelTime(java.util.Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	/**
	 * @return 取消时间
	 */
	public java.util.Date getCancelTime() {
		return this.cancelTime;
	}

	/**
	 * 退款订单号
	 */
	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}

	/**
	 * @return 退款订单号
	 */
	public String getRefundOrderNo() {
		return this.refundOrderNo;
	}

	/**
	 * 删除，0.删除，1.存在
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 删除，0.删除，1.存在
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

}
