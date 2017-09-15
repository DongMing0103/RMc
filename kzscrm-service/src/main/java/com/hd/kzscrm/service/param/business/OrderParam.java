package com.hd.kzscrm.service.param.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alipay.api.AlipayClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hd.kzscrm.common.param.PageParam;
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderParam extends PageParam {
	private Long id;
	/**
	 * 多个订单ID
	 */
	private List<Long> ids;
	
	private String idS;
	/**
	 * 订单编号
	 */
	private String orderNo;
	private String orderNoLike;
	/**
	 * 抽成比例
	 */
	private BigDecimal cutPercent;
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
	 * 手机号
	 */
	private String mobilephone;
	/**
	 * APP版本
	 */
	private String version;
	/**
	 * 业务员id
	 */
	private Long businessId;
	/**
	 * 支付宝充值流水号
	 */
	private String orderFlowNo;
	/**
	 * 删除标识：0。删除，1.存在
	 */
	private Integer delFlag;
	/**
	 * 合并订单支付单号(多订单时使用)
	 */
	private String combineOrderNo;
	/**
	 * 订单状态集合
	 */
	private List<Integer> statuses;
	/**
	 * 订单编号列表
	 */
	private List<String> orderNos;
	/**
	 * 充值流水号(如支付宝，微信的流水号)
	 */
	private String rechargeFlowNo;
	/**
	 * 充值单号
	 */
	private String rechargeNo;
	/**
	 * 用户订单的投诉状态,状态（0.未投诉，1 投诉中  2 已关闭  3 已退款）
	 */
	private String userCommplaintStatus;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 商家id
	 */
	private Long canteenId;
	/**
	 * 商家 IDS
	 */
	private List<Long> canteenIds;
	/**
	 * 餐具数量
	 */
	private Integer tablewareNum;
	/**
	 * 图片读取地址
	 */
	private String imgReadIpAddress;
	/**
	 * 用餐时间
	 */
	private String eatTimeCopy;
	private Date eatTime;
	
	/**
	 *	开始支付时间 
	 */
	private Date startPayTime;
	/**
	 *  结束支付时间
	 */
	private Date endPayTime;
	
	
	/**
	 * 用餐开始时间段
	 */
	private String eatTimeStart;
	/*支付宝支付*/
	private AlipayClient alipayClient;
	
	/*支付宝退款*/
	private AlipayClient alipayClientReturn;
	/**
	 * 是否更新待取餐
	 * 0.不更新，1.更新
	 */
	private Integer updateWaitMeal;//是否更新待取餐
	/**
	 * 充值金额
	 */
	private BigDecimal rechargeMoney;
	/**
	 * 订单支付金额
	 */
	private BigDecimal orderPayMoeny;
	
	/**
	 * 支付宝回调接口类型，1.用户充值，2.订单支付
	 */
	private Integer alypayNotifyType;
	/**
	 * 用餐结束时间段
	 */
	private String eatTimeEnd;
	/**
	 * 供应类目
	 */
	private Long supplyCategoryId;
	/**
	 * 订单的IDS集合字符串表示法，以逗号分隔ID
	 */
	private String orderIdsStr;
	/**
	 * 供应类目名称
	 */
	private String supplyCategoryName;
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
	 * 搜索内容
	 */
	private String condition;
	
	/**
	 * 搜索条件
	 */
	private String selectNum;
 	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createTime;
	
	private String createTime1;
	/**
	 * 创建时间开始
	 */
	private String createTimeStart;
	/**
	 * 创建时间结束
	 */
	private String createTimeEnd;
	/**
	 * 支付时间
	 */
	private Date payTime;
 /** 
    * 支付开始时间
    */ 
    private String payTimeStart;
   
    /** 
    * 支付结束时间
    */ 
    private String payTimeEnd;
	/**
	 * 接单时间
	 */
	private String acceptTime;
	/**
	 * 配送时间
	 */
	private Date sentTime;
	/**
	 * 评价时间
	 */
	private Date commentTime;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 1已下单(未支付)、 2已支付 、3已接单、4已送达、5已完成、6已评价
	 */
	private Integer status;
	/**订单状态集合*/
	private List<Integer> statusList;
	private Integer consumeStatus;// 消费状态，1.食堂就餐，2.外来用餐，3.外卖

	private BigDecimal discountMoney;// 折扣金额

	private BigDecimal cutMoney;// 抽成金额

	private BigDecimal realMoney;// 实际金额
	private String userName;//下单用户
	private String canteenName;//商家名称
	private List<Long>  userIds;//模糊查询的得到用户集合
	/**
	 * 食材名
	 */
	private String materialName1;
	/**
	 * 支付方式 1,余额，2.支付宝，3.微信
	 */
	private Integer payModel; 
	/**
	 * 三个按钮 成功 失败 全部
	 */
	private Integer cashFlowStatus;

	
/*	*//**
	 * 商品信息
	 *//*
	private List<OrderGoodsItemParam> orderGoodsItemParams;
	*//**
	 * 供应类目
	 *//*
	private SupplyCategoryParam supplyCategoryParam;*/
	/**
	 * 查询  开始时间
	 *@Description : TODO
	 *@author : lcl
	 *@time : 2017年3月6日 下午5:45:57
	 */
	private String stratTimes;
	private String stratTime;
	
	/**
	 * 查询 结束时间
	 *@Description : TODO
	 *@author : lcl
	 *@time : 2017年3月6日 下午5:45:57
	 */
	private String endTimes;
	
	/**
	 * 预订时间
	 */
/*	@JsonSerialize(using = MyDateJsonSerializerAndroid.class)  
	@JsonDeserialize(using = MyDateJsonDeserializerAndroid.class)  
	private Date orderTime;*/
	
	
	/**
	 * 输入框内容
	 *@Description : TODO
	 *@author : lcl
	 *@time : 2017年3月6日 下午20:48:57
	 */
	private String inputContent;
	/**
	 * 选择查询类型
	 */
	private Integer leixing;
	/**
	 * 订单ids
	 */
	private List<Long> orderIds;
	/**
	 * 商品Id
	 */
	private Long goodsId;
	/**
	 * 商品Ids
	 */
	private List<Long> goodsIds;
	/**
	 * 标志
	 */
	private Integer flag;
	
	/**
	 * 批量导出
	 */
	private List<Long> idBatch;
	
	
	/** 
	* excel ids集合
	*/ 
	private String orderId;
	
	private String userIds1;
	
	private String canteenIds1;
	
	private String searchName;
	
	/**
	 * 工作月
	 */
	private String applyMonth;
	
	public Integer getCashFlowStatus() {
		return cashFlowStatus;
	}


	public void setCashFlowStatus(Integer cashFlowStatus) {
		this.cashFlowStatus = cashFlowStatus;
	}

	public List<Integer> getStatusList() {
		return statusList;
	}


	public String getCreateTime1() {
		return createTime1;
	}




	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}


	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}


	public String getCanteenIds1() {
		return canteenIds1;
	}


	public void setCanteenIds1(String canteenIds1) {
		this.canteenIds1 = canteenIds1;
	}


	public String getUserIds1() {
		return userIds1;
	}


	public void setUserIds1(String userIds1) {
		this.userIds1 = userIds1;
	}


	public String getOrderId() {
		return orderId;
	}


	public String getPayTimeStart() {
		return payTimeStart;
	}


	public void setPayTimeStart(String payTimeStart) {
		this.payTimeStart = payTimeStart;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<Long> getIdBatch() {
		return idBatch;
	}

	public void setIdBatch(List<Long> idBatch) {
		this.idBatch = idBatch;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getMaterialName1() {
		return materialName1;
	}

	public void setMaterialName1(String materialName1) {
		this.materialName1 = materialName1;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public List<Long> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Long> orderIds) {
		this.orderIds = orderIds;
	}

	public String getInputContent() {
		return inputContent;
	}

	public void setInputContent(String inputContent) {
		this.inputContent = inputContent;
	}

	public Integer getLeixing() {
		return leixing;
	}

	public void setLeixing(Integer leixing) {
		this.leixing = leixing;
	}

	public String getStratTimes() {
		return stratTimes;
	}


	/** 
	 * 用户帐号    订单   商业值
	* @Fields selectValue : TODO
	*/ 
	private String selectValue;
	/**
	 * 用户帐号    订单   商业  id
	 */
	private Integer selectId;
	
	
	public List<Long> getUserIds() {
		return userIds;
	}


	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}


	public String getEatTimeCopy() {
		return eatTimeCopy;
	}

	public void setEatTimeCopy(String eatTimeCopy) {
		this.eatTimeCopy = eatTimeCopy;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public Integer getSelectId() {
		return selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}


	public void setStratTimes(String stratTimes) {
		this.stratTimes = stratTimes;
	}

	public String getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderNoLike() {
		return orderNoLike;
	}


	public void setOrderNoLike(String orderNoLike) {
		this.orderNoLike = orderNoLike;
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

	
	public String getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public String getSupplyCategoryName() {
		return supplyCategoryName;
	}

	public void setSupplyCategoryName(String supplyCategoryName) {
		this.supplyCategoryName = supplyCategoryName;
	}

	public String getEatTimeStart() {
		return eatTimeStart;
	}

	public void setEatTimeStart(String eatTimeStart) {
		this.eatTimeStart = eatTimeStart;
	}

	public String getEatTimeEnd() {
		return eatTimeEnd;
	}

	public void setEatTimeEnd(String eatTimeEnd) {
		this.eatTimeEnd = eatTimeEnd;
	}



	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	/*public List<OrderGoodsItemParam> getOrderGoodsItemParams() {
		return orderGoodsItemParams;
	}

	public void setOrderGoodsItemParams(List<OrderGoodsItemParam> orderGoodsItemParams) {
		this.orderGoodsItemParams = orderGoodsItemParams;
	}

	public SupplyCategoryParam getSupplyCategoryParam() {
		return supplyCategoryParam;
	}

	public void setSupplyCategoryParam(SupplyCategoryParam supplyCategoryParam) {
		this.supplyCategoryParam = supplyCategoryParam;
	}
*/
	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	/*public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}*/

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public List<Long> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}

	public List<Long> getCanteenIds() {
		return canteenIds;
	}

	public void setCanteenIds(List<Long> canteenIds) {
		this.canteenIds = canteenIds;
	}

	public String getOrderIdsStr() {
		return orderIdsStr;
	}

	public void setOrderIdsStr(String orderIdsStr) {
		this.orderIdsStr = orderIdsStr;
	}

	public List<String> getOrderNos() {
		return orderNos;
	}

	public String getImgReadIpAddress() {
		return imgReadIpAddress;
	}

	public void setImgReadIpAddress(String imgReadIpAddress) {
		this.imgReadIpAddress = imgReadIpAddress;
	}

	public void setOrderNos(List<String> orderNos) {
		this.orderNos = orderNos;
	}


	public Integer getAlypayNotifyType() {
		return alypayNotifyType;
	}


	public String getMobilephone() {
		return mobilephone;
	}


	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}


	public void setAlypayNotifyType(Integer alypayNotifyType) {
		this.alypayNotifyType = alypayNotifyType;
	}


	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}


	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}


	public BigDecimal getOrderPayMoeny() {
		return orderPayMoeny;
	}


	public void setOrderPayMoeny(BigDecimal orderPayMoeny) {
		this.orderPayMoeny = orderPayMoeny;
	}


	public String getRechargeNo() {
		return rechargeNo;
	}


	public String getRechargeFlowNo() {
		return rechargeFlowNo;
	}


	public void setRechargeFlowNo(String rechargeFlowNo) {
		this.rechargeFlowNo = rechargeFlowNo;
	}


	public void setRechargeNo(String rechargeNo) {
		this.rechargeNo = rechargeNo;
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


	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	/**
	 * @return the statuses
	 */
	public List<Integer> getStatuses() {
		return statuses;
	}


	/**
	 * @param statuses the statuses to set
	 */
	public void setStatuses(List<Integer> statuses) {
		this.statuses = statuses;
	}


	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}


	/**
	 * @return the userCommplaintStatus
	 */
	public String getUserCommplaintStatus() {
		return userCommplaintStatus;
	}


	/**
	 * @param userCommplaintStatus the userCommplaintStatus to set
	 */
	public void setUserCommplaintStatus(String userCommplaintStatus) {
		this.userCommplaintStatus = userCommplaintStatus;
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
	 * @param generalLedgerDate the generalLedgerDate to set
	 */
	public void setGeneralLedgerDate(Date generalLedgerDate) {
		this.generalLedgerDate = generalLedgerDate;
	}


	/**
	 * @return the updateWaitMeal
	 */
	public Integer getUpdateWaitMeal() {
		return updateWaitMeal;
	}


	/**
	 * @param updateWaitMeal the updateWaitMeal to set
	 */
	public void setUpdateWaitMeal(Integer updateWaitMeal) {
		this.updateWaitMeal = updateWaitMeal;
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
	 * @return the alipayClient
	 */
	public AlipayClient getAlipayClient() {
		return alipayClient;
	}


	/**
	 * @param alipayClient the alipayClient to set
	 */
	public void setAlipayClient(AlipayClient alipayClient) {
		this.alipayClient = alipayClient;
	}


	/**
	 * @return the alipayClientReturn
	 */
	public AlipayClient getAlipayClientReturn() {
		return alipayClientReturn;
	}


	/**
	 * @param alipayClientReturn the alipayClientReturn to set
	 */
	public void setAlipayClientReturn(AlipayClient alipayClientReturn) {
		this.alipayClientReturn = alipayClientReturn;
	}


	public Date getStartPayTime() {
		return startPayTime;
	}


	public void setStartPayTime(Date startPayTime) {
		this.startPayTime = startPayTime;
	}


	public Date getEndPayTime() {
		return endPayTime;
	}


	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}


	public String getIdS() {
		return idS;
	}


	public void setIdS(String idS) {
		this.idS = idS;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public String getSelectNum() {
		return selectNum;
	}


	public void setSelectNum(String selectNum) {
		this.selectNum = selectNum;
	}


	public String getApplyMonth() {
		return applyMonth;
	}


	public void setApplyMonth(String applyMonth) {
		this.applyMonth = applyMonth;
	}


	public Long getBusinessId() {
		return businessId;
	}


	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}


	public String getStratTime() {
		return stratTime;
	}


	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}


}
