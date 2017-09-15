package com.hd.kzscrm.dao.entity.business;

import java.math.BigDecimal;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 订单表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "order")
public class OrderPO {

	@Column(name = "id")
	@Id
	private Long id;
	/**
	 * 抽成比例
	 */
	@Column(name = "cut_percent")
	private BigDecimal cutPercent;
	/**
	 * 支付方式 1,余额，2.支付宝，3.微信
	 */
	@Column(name = "pay_model")
	private Integer payModel; 
	/**
	 * 通道费
	 */
	@Column(name = "channel_money")
	private BigDecimal channelMoney;
	/**
	 * 分帐时间
	 */
	@Column(name = "general_ledger_date")
	private Date generalLedgerDate;
	/**
	 * 分帐状态，0.未分帐，1.已分帐
	 */
	@Column(name = "general_ledger_status")
	private Integer generalLedgerStatus;
	/**
	 * 订单实际金额
	 */
	@Column(name = "order_real_money")
	private BigDecimal orderRealMoney;
	/**
	 * 订单编号
	 */
	@Column(name = "order_no")
	private String orderNo;
	/**
	 * 删除标识。0.删除，1.存在
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Long userId;
	/**
	 * 商家id
	 */
	@Column(name = "canteen_id")
	private Long canteenId;
	
	/**
	 * 订单Id  orderId
	 */
	private Long orderId;
	/**
	 * 餐具数量
	 */
	@Column(name = "tableware_num")
	private Integer tablewareNum;
	/**
	 * 用餐时间
	 */
	@Column(name = "eat_time")
	private Date eatTime;
	/**
	 * 供应类目
	 */
	@Column(name = "supply_category_id")
	private Long supplyCategoryId;
	/**
	 * 订单总价格
	 */
	@Column(name = "total_money")
	private BigDecimal totalMoney;
	/**
	 * 是否外送 0否 1是
	 */
	@Column(name = "need_send_flag")
	private Integer needSendFlag;
	/**
	 * 用户收货地址信息ID
	 */
	@Column(name = "user_receive_address_id")
	private Integer userReceiveAddressId;
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	/**
	 * 支付宝充值流水号
	 */
	@Column(name="order_flow_no")
	private String orderFlowNo;
	/**
	 * 合并支付单号(多订单时使用)
	 */
	@Column(name="combine_order_no")
	private String combineOrderNo;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 支付时间
	 */
	@Column(name = "pay_time")
	private Date payTime;
	/**
	 * 接单时间
	 */
	@Column(name = "accept_time")
	private Date acceptTime;
	/**
	 * 配送时间
	 */
	@Column(name = "sent_time")
	private Date sentTime;
	/**
	 * 评价时间
	 */
	@Column(name = "comment_time")
	private Date commentTime;
	/**
	 * 结束时间
	 */
	@Column(name = "end_time")
	private Date endTime;
	/**
	 * 订单状态1、已下单   2、已支付  3、已接单、4已送达、5已完成、6已评价、7已取消、8待评价、9待取餐（由后台根据当前时间与用餐时间对比设置，不能从数据库中设置）10、已撤诉
	 */
	@Column(name = "status")
	private Integer status;

	@Column(name = "consume_status")
	private Integer consumeStatus;// 消费状态，1.食堂就餐，2.外来用餐，3.外卖

	/**
	 * 折扣金额
	 */
	@Column(name = "discount_money")
	private BigDecimal discountMoney;

	/**
	 * 抽成金额
	 */
	@Column(name = "cut_oney")
	private BigDecimal cutMoney;

	/**
	 * 实际金额
	 */
	@Column(name = "real_money")
	private BigDecimal realMoney;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCanteenId() {
		return canteenId;
	}

	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getTablewareNum() {
		return tablewareNum;
	}

	public void setTablewareNum(Integer tablewareNum) {
		this.tablewareNum = tablewareNum;
	}

	public Date getEatTime() {
		return eatTime;
	}

	public void setEatTime(Date eatTime) {
		this.eatTime = eatTime;
	}

	public Long getSupplyCategoryId() {
		return supplyCategoryId;
	}

	public void setSupplyCategoryId(Long supplyCategoryId) {
		this.supplyCategoryId = supplyCategoryId;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getNeedSendFlag() {
		return needSendFlag;
	}

	public void setNeedSendFlag(Integer needSendFlag) {
		this.needSendFlag = needSendFlag;
	}

	public Integer getUserReceiveAddressId() {
		return userReceiveAddressId;
	}

	public void setUserReceiveAddressId(Integer userReceiveAddressId) {
		this.userReceiveAddressId = userReceiveAddressId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getConsumeStatus() {
		return consumeStatus;
	}

	public void setConsumeStatus(Integer consumeStatus) {
		this.consumeStatus = consumeStatus;
	}

	public BigDecimal getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(BigDecimal discountMoney) {
		this.discountMoney = discountMoney;
	}

	public BigDecimal getCutMoney() {
		return cutMoney;
	}

	public void setCutMoney(BigDecimal cutMoney) {
		this.cutMoney = cutMoney;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	@Override
	public String toString() {
		return "OrderPO [id=" + id + ", orderNo=" + orderNo + ", userId="
				+ userId + ", canteenId=" + canteenId + ", tablewareNum="
				+ tablewareNum + ", eatTime=" + eatTime + ", supplyCategoryId="
				+ supplyCategoryId + ", totalMoney=" + totalMoney
				+ ", needSendFlag=" + needSendFlag + ", userReceiveAddressId="
				+ userReceiveAddressId + ", remark=" + remark + ", createTime="
				+ createTime + ", payTime=" + payTime + ", acceptTime="
				+ acceptTime + ", sentTime=" + sentTime + ", commentTime="
				+ commentTime + ", endTime=" + endTime + ", status=" + status
				+ ", consumeStatus=" + consumeStatus + ", discountMoney="
				+ discountMoney + ", cutMoney=" + cutMoney + ", realMoney="
				+ realMoney + ", getId()=" + getId() + ", getOrderNo()="
				+ getOrderNo() + ", getUserId()=" + getUserId()
				+ ", getCanteenId()=" + getCanteenId() + ", getTablewareNum()="
				+ getTablewareNum() + ", getEatTime()=" + getEatTime()
				+ ", getSupplyCategoryId()=" + getSupplyCategoryId()
				+ ", getTotalMoney()=" + getTotalMoney()
				+ ", getNeedSendFlag()=" + getNeedSendFlag()
				+ ", getUserReceiveAddressId()=" + getUserReceiveAddressId()
				+ ", getRemark()=" + getRemark() + ", getCreateTime()="
				+ getCreateTime() + ", getPayTime()=" + getPayTime()
				+ ", getAcceptTime()=" + getAcceptTime() + ", getSentTime()="
				+ getSentTime() + ", getCommentTime()=" + getCommentTime()
				+ ", getEndTime()=" + getEndTime() + ", getStatus()="
				+ getStatus() + ", getConsumeStatus()=" + getConsumeStatus()
				+ ", getDiscountMoney()=" + getDiscountMoney()
				+ ", getCutMoney()=" + getCutMoney() + ", getRealMoney()="
				+ getRealMoney() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


	public String getOrderFlowNo() {
		return orderFlowNo;
	}

	public void setOrderFlowNo(String orderFlowNo) {
		this.orderFlowNo = orderFlowNo;
	}

	public String getCombineOrderNo() {
		return combineOrderNo;
	}

	public void setCombineOrderNo(String combineOrderNo) {
		this.combineOrderNo = combineOrderNo;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return the cutPercent
	 */
	public BigDecimal getCutPercent() {
		return cutPercent;
	}

	/**
	 * @param cutPercent the cutPercent to set
	 */
	public void setCutPercent(BigDecimal cutPercent) {
		this.cutPercent = cutPercent;
	}

	/**
	 * @return the channelMoney
	 */
	public BigDecimal getChannelMoney() {
		return channelMoney;
	}

	/**
	 * @param channelMoney the channelMoney to set
	 */
	public void setChannelMoney(BigDecimal channelMoney) {
		this.channelMoney = channelMoney;
	}

	/**
	 * @return the generalLedgerDate
	 */
	public Date getGeneralLedgerDate() {
		return generalLedgerDate;
	}

	/**
	 * @param generalLedgerDate the generalLedgerDate to set
	 */
	public void setGeneralLedgerDate(Date generalLedgerDate) {
		this.generalLedgerDate = generalLedgerDate;
	}

	/**
	 * @return the generalLedgerStatus
	 */
	public Integer getGeneralLedgerStatus() {
		return generalLedgerStatus;
	}

	/**
	 * @param generalLedgerStatus the generalLedgerStatus to set
	 */
	public void setGeneralLedgerStatus(Integer generalLedgerStatus) {
		this.generalLedgerStatus = generalLedgerStatus;
	}

	/**
	 * @return the orderRealMoney
	 */
	public BigDecimal getOrderRealMoney() {
		return orderRealMoney;
	}

	/**
	 * @param orderRealMoney the orderRealMoney to set
	 */
	public void setOrderRealMoney(BigDecimal orderRealMoney) {
		this.orderRealMoney = orderRealMoney;
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

}
