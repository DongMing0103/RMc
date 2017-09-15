package com.hd.kzscrm.service.vo.business;

import java.math.BigDecimal;
import java.util.Date;

public class OrderVO {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 抽成比例
	 */
	private BigDecimal cutPercent;
	/**
	 * 支付方式 1,余额，2.支付宝，3.微信
	 */
	private Integer payModel; 
	private String payModelName;
	/**
	 * 通道费
	 */
	private BigDecimal channelMoney;
	/**
	 * 分帐时间
	 */
	private Date generalLedgerDate;
	/**
	 * 分帐状态，0.未分帐，1.已分帐
	 */
	private Integer generalLedgerStatus;
	/**
	 * 订单实际金额
	 */
	private BigDecimal orderRealMoney;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 删除标识。0.删除，1.存在
	 */
	private Integer delFlag;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 商家id
	 */
	private Long canteenId;
	/**
	 * 餐具数量
	 */
	private Integer tablewareNum;
	/**
	 * 用餐时间
	 */
	private Date eatTime;
	/**
	 * 供应类目
	 */
	private Long supplyCategoryId;
	/**
	 * 订单总价格
	 */
	private BigDecimal totalMoney;
	/**
	 * 是否外送 0否 1是
	 */
	private Integer needSendFlag;
	/**
	 * 用户收货地址信息ID
	 */
	private Integer userReceiveAddressId;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 支付宝充值流水号
	 */
	private String orderFlowNo;
	/**
	 * 合并支付单号(多订单时使用)
	 */
	private String combineOrderNo;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 接单时间
	 */
	private Date acceptTime;
	/**
	 * 配送时间
	 */
	private Date sentTime;
	/**
	 * 评价时间
	 */
	private Date commentTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 订单状态1、已下单   2、已支付  3、已接单、4已送达、5已完成、6已评价、7已取消、8待评价、9待取餐（由后台根据当前时间与用餐时间对比设置，不能从数据库中设置）10、已撤诉
	 */
	private Integer status;

	private Integer consumeStatus;// 消费状态，1.食堂就餐，2.外来用餐，3.外卖

	/**
	 * 折扣金额
	 */
	private BigDecimal discountMoney;

	/**
	 * 抽成金额
	 */
	private BigDecimal cutMoney;
	
	/**
	 * 业务员分账比例
	 */
	private BigDecimal businessSplitPercent;

	/**
	 * 实际金额
	 */
	private BigDecimal realMoney;
	
	private String canteenName;
	
	/**
	 * 状态名称
	 */
	private String statusName;
	
	/**
	 * 部门名称
	 */
	private String dName;
	
	/**
	 * 商家名称
	 */
	private String name;
	
	/**
	 * 职位名称
	 */
	private String pName;
	
	/**
	 * 利润
	 * @return
	 */
	private BigDecimal profitMoney;
	
	/**
	 * 商家金额
	 */
	private BigDecimal canteenMoney;
	//食堂分账比例
	private BigDecimal canteenSplitPercent;
	
	/**
	 * 业务员分账比例
	 */
	private BigDecimal businssSplitPercent;
	//现金流水号
	private String cashFlowNo;
	//分账金额
	private BigDecimal splitMoney;

	/**
	 * 企业名称
	 */
	private String enterpriseName;
	
	
	public BigDecimal getSplitMoney() {
		return splitMoney;
	}

	public void setSplitMoney(BigDecimal splitMoney) {
		this.splitMoney = splitMoney;
	}

	public String getCashFlowNo() {
		return cashFlowNo;
	}

	public void setCashFlowNo(String cashFlowNo) {
		this.cashFlowNo = cashFlowNo;
	}

	public BigDecimal getCanteenSplitPercent() {
		return canteenSplitPercent;
	}

	public void setCanteenSplitPercent(BigDecimal canteenSplitPercent) {
		this.canteenSplitPercent = canteenSplitPercent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(BigDecimal cutPercent) {
		this.cutPercent = cutPercent;
	}

	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	public BigDecimal getChannelMoney() {
		return channelMoney;
	}

	public void setChannelMoney(BigDecimal channelMoney) {
		this.channelMoney = channelMoney;
	}

	public Date getGeneralLedgerDate() {
		return generalLedgerDate;
	}

	public void setGeneralLedgerDate(Date generalLedgerDate) {
		this.generalLedgerDate = generalLedgerDate;
	}

	public Integer getGeneralLedgerStatus() {
		return generalLedgerStatus;
	}

	public void setGeneralLedgerStatus(Integer generalLedgerStatus) {
		this.generalLedgerStatus = generalLedgerStatus;
	}

	public BigDecimal getOrderRealMoney() {
		return orderRealMoney;
	}

	public void setOrderRealMoney(BigDecimal orderRealMoney) {
		this.orderRealMoney = orderRealMoney;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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
	

	public BigDecimal getBusinessSplitPercent() {
		return businessSplitPercent;
	}

	public void setBusinessSplitPercent(BigDecimal businessSplitPercent) {
		this.businessSplitPercent = businessSplitPercent;
	}

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}
	

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	
	public BigDecimal getProfitMoney() {
		return profitMoney;
	}

	public void setProfitMoney(BigDecimal profitMoney) {
		this.profitMoney = profitMoney;
	}

	public BigDecimal getCanteenMoney() {
		return canteenMoney;
	}

	public void setCanteenMoney(BigDecimal canteenMoney) {
		this.canteenMoney = canteenMoney;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getPayModelName() {
		return payModelName;
	}

	public void setPayModelName(String payModelName) {
		this.payModelName = payModelName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBusinssSplitPercent() {
		return businssSplitPercent;
	}

	public void setBusinssSplitPercent(BigDecimal businssSplitPercent) {
		this.businssSplitPercent = businssSplitPercent;
	}

	@Override
	public String toString() {
		return "OrderVO [id=" + id + ", cutPercent=" + cutPercent + ", payModel=" + payModel + ", payModelName="
				+ payModelName + ", channelMoney=" + channelMoney + ", generalLedgerDate=" + generalLedgerDate
				+ ", generalLedgerStatus=" + generalLedgerStatus + ", orderRealMoney=" + orderRealMoney + ", orderNo="
				+ orderNo + ", delFlag=" + delFlag + ", userId=" + userId + ", canteenId=" + canteenId
				+ ", tablewareNum=" + tablewareNum + ", eatTime=" + eatTime + ", supplyCategoryId=" + supplyCategoryId
				+ ", totalMoney=" + totalMoney + ", needSendFlag=" + needSendFlag + ", userReceiveAddressId="
				+ userReceiveAddressId + ", remark=" + remark + ", orderFlowNo=" + orderFlowNo + ", combineOrderNo="
				+ combineOrderNo + ", createTime=" + createTime + ", payTime=" + payTime + ", acceptTime=" + acceptTime
				+ ", sentTime=" + sentTime + ", commentTime=" + commentTime + ", endTime=" + endTime + ", status="
				+ status + ", consumeStatus=" + consumeStatus + ", discountMoney=" + discountMoney + ", cutMoney="
				+ cutMoney + ", realMoney=" + realMoney + ", canteenName=" + canteenName + ", statusName=" + statusName
				+ ", dName=" + dName + ", pName=" + pName + ", profitMoney=" + profitMoney + ", canteenMoney="
				+ canteenMoney + ", canteenSplitPercent=" + canteenSplitPercent + ", cashFlowNo=" + cashFlowNo
				+ ", splitMoney=" + splitMoney + ", enterpriseName=" + enterpriseName + ", getSplitMoney()="
				+ getSplitMoney() + ", getCashFlowNo()=" + getCashFlowNo() + ", getCanteenSplitPercent()="
				+ getCanteenSplitPercent() + ", getId()=" + getId() + ", getCutPercent()=" + getCutPercent()
				+ ", getPayModel()=" + getPayModel() + ", getChannelMoney()=" + getChannelMoney()
				+ ", getGeneralLedgerDate()=" + getGeneralLedgerDate() + ", getGeneralLedgerStatus()="
				+ getGeneralLedgerStatus() + ", getOrderRealMoney()=" + getOrderRealMoney() + ", getOrderNo()="
				+ getOrderNo() + ", getDelFlag()=" + getDelFlag() + ", getUserId()=" + getUserId() + ", getCanteenId()="
				+ getCanteenId() + ", getTablewareNum()=" + getTablewareNum() + ", getEatTime()=" + getEatTime()
				+ ", getSupplyCategoryId()=" + getSupplyCategoryId() + ", getTotalMoney()=" + getTotalMoney()
				+ ", getNeedSendFlag()=" + getNeedSendFlag() + ", getUserReceiveAddressId()="
				+ getUserReceiveAddressId() + ", getRemark()=" + getRemark() + ", getOrderFlowNo()=" + getOrderFlowNo()
				+ ", getCombineOrderNo()=" + getCombineOrderNo() + ", getCreateTime()=" + getCreateTime()
				+ ", getPayTime()=" + getPayTime() + ", getAcceptTime()=" + getAcceptTime() + ", getSentTime()="
				+ getSentTime() + ", getCommentTime()=" + getCommentTime() + ", getEndTime()=" + getEndTime()
				+ ", getStatus()=" + getStatus() + ", getConsumeStatus()=" + getConsumeStatus()
				+ ", getDiscountMoney()=" + getDiscountMoney() + ", getCutMoney()=" + getCutMoney()
				+ ", getRealMoney()=" + getRealMoney() + ", getCanteenName()=" + getCanteenName() + ", getStatusName()="
				+ getStatusName() + ", getdName()=" + getdName() + ", getpName()=" + getpName() + ", getProfitMoney()="
				+ getProfitMoney() + ", getCanteenMoney()=" + getCanteenMoney() + ", getEnterpriseName()="
				+ getEnterpriseName() + ", getPayModelName()=" + getPayModelName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
