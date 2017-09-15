package com.hd.kzscrm.service.vo.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class OrderDTO {
	private Long id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 支付方式 1,余额，2.支付宝，3.微信,4.APP支付宝网页支付
	 */
	private Integer payModel; 
	/**
	 * 取餐窗口
	 */
	private List<Integer> windowNums;
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
	 * 订单实际金额
	 */
	private BigDecimal orderRealMoney;
	/**
	 * 分帐状态，0.未分帐，1.已分帐
	 */
	private Integer generalLedgerStatus;
	/**
	 * 充值单号
	 */
	private String rechargeNo;
	/**
	 * 用户订单的投诉状态,状态（0.未投诉，1 投诉中  2 已关闭  3 已退款）
	 */
	private Integer userCommplaintStatus;
	/**
	 * 是否可以投诉.0.不能，1.能
	 */
	private Integer canCommplaint;
	/**
	 * 删除标识：0。删除，1.存在
	 */
	private Integer delFlag;
	/**
	 * 支付宝充值流水号
	 */
	private String orderFlowNo;
	/**
	 * 合并订单支付单号(多订单时使用)
	 */
	private String combineOrderNo;
	/**
	 * 充值流水号(如支付宝，微信的流水号)
	 */
	private String rechargeFlowNo;
	/**
	 * 用户名
	 */
	private String userName;
	private String userMobilephone;//用户手机号
	/**
	 * 商家id
	 */
	private Long canteenId;
	/**
	 * 食堂类型1.内部食堂，2.外部食堂
	 */
	private Integer canteenType;
	/**
	 * 商品ID
	 */
	private Long goodsId;
	/**
	 * 商家名称
	 */
	private String canteenName;
	/**
	 * 商品总份数
	 */
	private Integer goodsTotalNums;
	/**
	 * 餐具数量
	 */
	private Integer tablewareNum;
	/**
	 * 用餐时间
	 */
	private Date eatTime;
	/**
	 * 服务器当前时间
	 */
	private Date serverCurrentDate;
	
	/**
	 * 用餐时间-用于excel格式化
	 * @author jyt 2017年3月15日 上午10:17:14
	 */
	private String eatTimeStr;
	/**
	 * 供应类目
	 */
	private Long supplyCategoryId;
	/**
	 * 餐厅地址
	 */
	private String canteenAddress;
	
	/**
	 * 供应类目名称
	 */
	private String supplyCategoryName;
	/**
	 * 订单总价格
	 */
	private BigDecimal totalMoney;
	/**
	 * 食堂LOGO
	 */
	private String canteenLogo;
	/**
	 * 是否外送 0否 1是
	 */
	private Integer needSendFlag;
	/**
	 * 用户收货地址信息ID
	 */
	private Integer userReceiveAddressId;
	/**
	 * 用户默认收货地址
	 */
	private String address;
	/**
	 * 订单明细
	 */
/*	private List<OrderGoodsItemDTO> orderGoodsItemDTOs;
	private List<GoodImgDTO> goodImgDTO;*/
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 创建时间 -用于excel
	 * @author jyt 2017年3月15日 上午10:24:29
	 */
	private String createTimeStr;
	/**
	 * 投诉状态 1.已投诉，2.未投诉
	 */
	private Integer complainStatus;
	/**
	 * 是否可评价商家，30天内不可重复评价
	 */
	private boolean isCanRecommentCanteen;
	/**
	 * 是否已投诉
	 */
	private boolean isComplained;
	/**
	 * 订单的商品是否被评价
	 */
	private boolean isOrderCommentOfGoods;
	/**
	 * 取餐窗口
	 */
	private Integer windowNum;
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
	 * 评价时间
	 */
	private String commentTimes;//时间格式转换
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 1、已下单   2、已支付  3、已接单、4已送达、5已完成、6已评价
	 */
	private Integer status;
	private String statusName;
	/**
	 * 订单的IDS集合字符串表示法，以逗号分隔ID
	 */
	private String orderIdsStr;
	
	private Integer consumeStatus;//消费状态，1.食堂就餐，2.外来用餐，3.外卖
	private String consumeStatusName;//消费状态 数字转换成名称
	private BigDecimal discountMoney;//折扣金额
	
	private BigDecimal cutMoney;//抽成金额
	
	private BigDecimal realMoney;//实际金额
	
	private String enterpriseName;//企业名称
	private String mobilephone;//企业联系人电话
	private String depatementName;//部门名称
	private String positionLevelName;//岗位等级
	private Double turnover;//营业额
	private Date enterTime;//入驻时间
	private String commentsName;//评价名称
	private Integer ranking;//商家排名
	private String province;//省份
	private String city;//市级
	/**
	 * 订购详情
	 */
	private String orderDetails;
	/**
	 * 现金流水号
	 */
	private String cashFlowNo;
	
	
	
	public String getUserMobilephone() {
		return userMobilephone;
	}
	public void setUserMobilephone(String userMobilephone) {
		this.userMobilephone = userMobilephone;
	}
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	/**
	 * 商品
	 */
/*	private List<GoodsInfoDTO> goodsInfoDTOs;*/
	
/*	*//**
	 * 商品
	 *//*
	private List<GoodsInfoADTO> goodsInfoADTOs;
	*//**
	 * 企业员工DTO
	 *//*
	private EnterpriseEmployeesLinkDTO enterpriseEmployeesLinkDTO;
	*//**
	 * 供应类目
	 *//*
	public SupplyCategoryDTO supplyCategoryDTO; */

	/**
	 * 某商品的月销量
	 */
	public Integer goodsMonthAmount;
	/**
	 * 某食堂某月商品销量
	 */
	private Integer canteenMonthAmount;
	/**
	 * 某月某食堂订单数量
	 */
	private Integer canteenOrderAmount;
	/**
	 * 供应类目
	 */
	private String timeName;
	
	private List<Long> ids;
	/** 
	 * 用户帐号    订单   商业值
	* @Fields selectValue : TODO
	*/ 
	private String selectValue;
	/**
	 * 用户帐号    订单   商业  id
	 */
	private Integer selectId;
	
	private String payModelStr;
	
	/** 
	* 销量
	*/ 
	private Double sell;
	

	/** 
	* 销售额
	*/ 
	private Double sellMoney;
	
	/** 
	* 评价
	*/ 
	private Double grade;
	/**商家总综合评价*/
	private Double environmentalStar;
	
	/**
	 * 【app食堂端】 份数
	 * @author jyt 2017年4月19日 上午11:53:41
	 */
	private Integer nums;
	
	/** 
	* 食堂编号
	*/ 
	private String canteenNo;
	
	/**
	 * 用户投诉状态
	 */
	private Integer userComplaintStatus;
	
	public Integer getUserComplaintStatus() {
		return userComplaintStatus;
	}
	public void setUserComplaintStatus(Integer userComplaintStatus) {
		this.userComplaintStatus = userComplaintStatus;
	}
	public String getCanteenNo() {
		return canteenNo;
	}
	public void setCanteenNo(String canteenNo) {
		this.canteenNo = canteenNo;
	}
	public Double getEnvironmentalStar() {
		return environmentalStar;
	}
	public void setEnvironmentalStar(Double environmentalStar) {
		this.environmentalStar = environmentalStar;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public Double getSellMoney() {
		return sellMoney;
	}
	public void setSellMoney(Double sellMoney) {
		this.sellMoney = sellMoney;
	}
	public Double getSell() {
		return sell;
	}
	public void setSell(Double sell) {
		this.sell = sell;
	}

	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getPayModelStr() {
		return payModelStr;
	}
	public Integer getPayModel() {
		return payModel;
	}
/*	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
		if(BeanUtils.isEmpty(payModel)){
			return;
		}
		switch (payModel) {
		case 1:
			this.payModelStr = "余额";
			break;
		case 2:
			this.payModelStr = "支付宝";
			break;
		case 3:
			this.payModelStr = "微信";
			break;
		default:
			this.payModelStr = "--";
			break;
		}
	}
	*/
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	public String getCommentsName() {
		return commentsName;
	}
	public void setCommentsName(String commentsName) {
		this.commentsName = commentsName;
	}
	public String getTimeName() {
		return timeName;
	}
	public void setTimeName(String timeName) {
		this.timeName = timeName;
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
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	public Double getTurnover() {
		return turnover;
	}
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getDepatementName() {
		return depatementName;
	}
	public void setDepatementName(String depatementName) {
		this.depatementName = depatementName;
	}
	public String getPositionLevelName() {
		return positionLevelName;
	}
	public void setPositionLevelName(String positionLevelName) {
		this.positionLevelName = positionLevelName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getConsumeStatusName() {
		return consumeStatusName;
	}
	public void setConsumeStatusName(String consumeStatusName) {
		this.consumeStatusName = consumeStatusName;
	}
	public String getCommentTimes() {
		return commentTimes;
	}
	public void setCommentTimes(String commentTimes) {
		this.commentTimes = commentTimes;
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
	public String getSupplyCategoryName() {
		return supplyCategoryName;
	}
/*	public List<OrderGoodsItemDTO> getOrderGoodsItemDTOs() {
		return orderGoodsItemDTOs;
	}
	public void setOrderGoodsItemDTOs(List<OrderGoodsItemDTO> orderGoodsItemDTOs) {
		this.orderGoodsItemDTOs = orderGoodsItemDTOs;
	}
	public void setSupplyCategoryName(String supplyCategoryName) {
		this.supplyCategoryName = supplyCategoryName;
	}
	public EnterpriseEmployeesLinkDTO getEnterpriseEmployeesLinkDTO() {
		return enterpriseEmployeesLinkDTO;
	}
	public void setEnterpriseEmployeesLinkDTO(
			EnterpriseEmployeesLinkDTO enterpriseEmployeesLinkDTO) {
		this.enterpriseEmployeesLinkDTO = enterpriseEmployeesLinkDTO;
	}*/
	public String getCanteenAddress() {
		return canteenAddress;
	}
	public void setCanteenAddress(String canteenAddress) {
		this.canteenAddress = canteenAddress;
	}
	public Integer getComplainStatus() {
		return complainStatus;
	}
	public void setComplainStatus(Integer complainStatus) {
		this.complainStatus = complainStatus;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getCanteenType() {
		return canteenType;
	}
	public void setCanteenType(Integer canteenType) {
		this.canteenType = canteenType;
	}
/*	public List<GoodsInfoDTO> getGoodsInfoDTOs() {
		return goodsInfoDTOs;
	}
	public void setGoodsInfoDTOs(List<GoodsInfoDTO> goodsInfoDTOs) {
		this.goodsInfoDTOs = goodsInfoDTOs;
	}
	public SupplyCategoryDTO getSupplyCategoryDTO() {
		return supplyCategoryDTO;
	}
	public void setSupplyCategoryDTO(SupplyCategoryDTO supplyCategoryDTO) {
		this.supplyCategoryDTO = supplyCategoryDTO;
	}*/
	public Integer getWindowNum() {
		return windowNum;
	}
	public void setWindowNum(Integer windowNum) {
		this.windowNum = windowNum;
	}
	public String getEatTimeStr() {
		return eatTimeStr;
	}
	public void setEatTimeStr(String eatTimeStr) {
		this.eatTimeStr = eatTimeStr;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getCanteenLogo() {
		return canteenLogo;
	}
	public void setCanteenLogo(String canteenLogo) {
		this.canteenLogo = canteenLogo;
	}
	public Integer getGoodsMonthAmount() {
		return goodsMonthAmount;
	}
	public void setGoodsMonthAmount(Integer goodsMonthAmount) {
		this.goodsMonthAmount = goodsMonthAmount;
	}
	public String getOrderIdsStr() {
		return orderIdsStr;
	}
	public void setOrderIdsStr(String orderIdsStr) {
		this.orderIdsStr = orderIdsStr;
	}
	public Integer getCanteenMonthAmount() {
		return canteenMonthAmount;
	}
	public void setCanteenMonthAmount(Integer canteenMonthAmount) {
		this.canteenMonthAmount = canteenMonthAmount;
	}
	public Integer getCanteenOrderAmount() {
		return canteenOrderAmount;
	}
	public void setCanteenOrderAmount(Integer canteenOrderAmount) {
		this.canteenOrderAmount = canteenOrderAmount;
	}
/*	public List<GoodImgDTO> getGoodImgDTO() {
		return goodImgDTO;
	}
	public void setGoodImgDTO(List<GoodImgDTO> goodImgDTO) {
		this.goodImgDTO = goodImgDTO;
	}*/
	public Integer getGoodsTotalNums() {
		return goodsTotalNums;
	}
	public void setGoodsTotalNums(Integer goodsTotalNums) {
		this.goodsTotalNums = goodsTotalNums;
	}
	public List<Integer> getWindowNums() {
		return windowNums;
	}
	public void setWindowNums(List<Integer> windowNums) {
		this.windowNums = windowNums;
	}
	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", orderNo=" + orderNo + ", userId="
				+ userId + ", userName=" + userName + ", canteenId="
				+ canteenId + ", canteenType=" + canteenType + ", canteenName="
				+ canteenName + ", goodsTotalNums=" + goodsTotalNums
				+ ", tablewareNum=" + tablewareNum + ", eatTime=" + eatTime
				+ ", eatTimeStr=" + eatTimeStr + ", supplyCategoryId="
				+ supplyCategoryId + ", canteenAddress=" + canteenAddress
				+ ", supplyCategoryName=" + supplyCategoryName
				+ ", totalMoney=" + totalMoney + ", canteenLogo=" + canteenLogo
				+ ", needSendFlag=" + needSendFlag + ", userReceiveAddressId="
				+ userReceiveAddressId + ", address=" + address
				/*+ ", orderGoodsItemDTOs=" + orderGoodsItemDTOs
				+ ", goodImgDTO=" + goodImgDTO + ", remark=" + remark*/
				+ ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", complainStatus=" + complainStatus
				+ ", windowNum=" + windowNum + ", payTime=" + payTime
				+ ", acceptTime=" + acceptTime + ", sentTime=" + sentTime
				+ ", commentTime=" + commentTime + ", commentTimes="
				+ commentTimes + ", goodsName=" + goodsName + ", endTime="
				+ endTime + ", status=" + status + ", statusName=" + statusName
				+ ", orderIdsStr=" + orderIdsStr + ", consumeStatus="
				+ consumeStatus + ", consumeStatusName=" + consumeStatusName
				+ ", discountMoney=" + discountMoney + ", cutMoney=" + cutMoney
				+ ", realMoney=" + realMoney + ", enterpriseName="
				+ enterpriseName + ", mobilephone=" + mobilephone
				+ ", depatementName=" + depatementName + ", positionLevelName="
				+ positionLevelName + ", turnover=" + turnover + ", enterTime="
				+ enterTime + ", commentsName=" + commentsName + ", ranking="
				/*+ ranking + ", goodsInfoDTOs=" + goodsInfoDTOs
				+ ", enterpriseEmployeesLinkDTO=" + enterpriseEmployeesLinkDTO
				+ ", supplyCategoryDTO=" + supplyCategoryDTO*/
				+ ", goodsMonthAmount=" + goodsMonthAmount
				+ ", canteenMonthAmount=" + canteenMonthAmount
				+ ", canteenOrderAmount=" + canteenOrderAmount + ", timeName="
				+ timeName + ", ids=" + ids + ", selectValue=" + selectValue
				+ ", selectId=" + selectId + ", getIds()=" + getIds()
				+ ", getRanking()=" + getRanking() + ", getCommentsName()="
				+ getCommentsName() + ", getTimeName()=" + getTimeName()
				+ ", getSelectValue()=" + getSelectValue() + ", getSelectId()="
				+ getSelectId() + ", getEnterTime()=" + getEnterTime()
				+ ", getTurnover()=" + getTurnover() + ", getStatusName()="
				+ getStatusName() + ", getMobilephone()=" + getMobilephone()
				+ ", getEnterpriseName()=" + getEnterpriseName()
				+ ", getDepatementName()=" + getDepatementName()
				+ ", getPositionLevelName()=" + getPositionLevelName()
				+ ", getAddress()=" + getAddress() + ", getUserName()="
				+ getUserName() + ", getConsumeStatusName()="
				+ getConsumeStatusName() + ", getCommentTimes()="
				+ getCommentTimes() + ", getCanteenName()=" + getCanteenName()
				+ ", getId()=" + getId() + ", getOrderNo()=" + getOrderNo()
				+ ", getUserId()=" + getUserId() + ", getCanteenId()="
				+ getCanteenId() + ", getTablewareNum()=" + getTablewareNum()
				+ ", getEatTime()=" + getEatTime() + ", getSupplyCategoryId()="
				+ getSupplyCategoryId() + ", getTotalMoney()="
				+ getTotalMoney() + ", getNeedSendFlag()=" + getNeedSendFlag()
				+ ", getUserReceiveAddressId()=" + getUserReceiveAddressId()
				+ ", getRemark()=" + getRemark() + ", getCreateTime()="
				+ getCreateTime() + ", getPayTime()=" + getPayTime()
				+ ", getAcceptTime()=" + getAcceptTime() + ", getSentTime()="
				+ getSentTime() + ", getCommentTime()=" + getCommentTime()
				+ ", getEndTime()=" + getEndTime() + ", getStatus()="
				+ getStatus() + ", getConsumeStatus()=" + getConsumeStatus()
				+ ", getDiscountMoney()=" + getDiscountMoney()
				+ ", getCutMoney()=" + getCutMoney() + ", getRealMoney()="
				+ getRealMoney() + ", getSupplyCategoryName()="
				+ getSupplyCategoryName() + ", getOrderGoodsItemDTOs()="
			/*	+ getOrderGoodsItemDTOs()*/
				+ ", getEnterpriseEmployeesLinkDTO()="
				/*+ getEnterpriseEmployeesLinkDTO() + ", getCanteenAddress()="*/
				+ getCanteenAddress() + ", getComplainStatus()="
				+ getComplainStatus() + ", getGoodsName()=" + getGoodsName()
				+ ", getCanteenType()=" + getCanteenType()
				/*+ ", getGoodsInfoDTOs()=" + getGoodsInfoDTOs()
				+ ", getSupplyCategoryDTO()=" + getSupplyCategoryDTO()*/
				+ ", getWindowNum()=" + getWindowNum() + ", getEatTimeStr()="
				+ getEatTimeStr() + ", getCreateTimeStr()="
				+ getCreateTimeStr() + ", getCanteenLogo()=" + getCanteenLogo()
				+ ", getGoodsMonthAmount()=" + getGoodsMonthAmount()
				+ ", getOrderIdsStr()=" + getOrderIdsStr()
				+ ", getCanteenMonthAmount()=" + getCanteenMonthAmount()
				+ ", getCanteenOrderAmount()=" + getCanteenOrderAmount()
				/*+ ", getGoodImgDTO()=" + getGoodImgDTO()*/
				+ ", getGoodsTotalNums()=" + getGoodsTotalNums()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	/*public List<GoodsInfoADTO> getGoodsInfoADTOs() {
		return goodsInfoADTOs;
	}
	public void setGoodsInfoADTOs(List<GoodsInfoADTO> goodsInfoADTOs) {
		this.goodsInfoADTOs = goodsInfoADTOs;
	}*/
	public boolean isCanRecommentCanteen() {
		return isCanRecommentCanteen;
	}
	public void setCanRecommentCanteen(boolean isCanRecommentCanteen) {
		this.isCanRecommentCanteen = isCanRecommentCanteen;
	}
	public boolean isComplained() {
		return isComplained;
	}
	public void setComplained(boolean isComplained) {
		this.isComplained = isComplained;
	}
	public Date getServerCurrentDate() {
		return serverCurrentDate;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public void setPayModelStr(String payModelStr) {
		this.payModelStr = payModelStr;
	}
	public String getRechargeNo() {
		return rechargeNo;
	}
	public void setRechargeNo(String rechargeNo) {
		this.rechargeNo = rechargeNo;
	}
	public void setServerCurrentDate(Date serverCurrentDate) {
		this.serverCurrentDate = serverCurrentDate;
	}
	public String getRechargeFlowNo() {
		return rechargeFlowNo;
	}
	public void setRechargeFlowNo(String rechargeFlowNo) {
		this.rechargeFlowNo = rechargeFlowNo;
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
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	/**
	 * @return the isOrderCommentOfGoods
	 */
	public boolean isOrderCommentOfGoods() {
		return isOrderCommentOfGoods;
	}
	/**
	 * @param isOrderCommentOfGoods the isOrderCommentOfGoods to set
	 */
	public void setOrderCommentOfGoods(boolean isOrderCommentOfGoods) {
		this.isOrderCommentOfGoods = isOrderCommentOfGoods;
	}
	/**
	 * @return the userCommplaintStatus
	 */
	public Integer getUserCommplaintStatus() {
		return userCommplaintStatus;
	}
	/**
	 * @param userCommplaintStatus the userCommplaintStatus to set
	 */
	public void setUserCommplaintStatus(Integer userCommplaintStatus) {
		this.userCommplaintStatus = userCommplaintStatus;
	}
	/**
	 * @return the canCommplaint
	 */
	public Integer getCanCommplaint() {
		return canCommplaint;
	}
	/**
	 * @param canCommplaint the canCommplaint to set
	 */
	public void setCanCommplaint(Integer canCommplaint) {
		this.canCommplaint = canCommplaint;
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
	public String getCashFlowNo() {
		return cashFlowNo;
	}
	public void setCashFlowNo(String cashFlowNo) {
		this.cashFlowNo = cashFlowNo;
	}
	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}
	public void setSupplyCategoryName(String supplyCategoryName) {
		this.supplyCategoryName = supplyCategoryName;
	}
}
