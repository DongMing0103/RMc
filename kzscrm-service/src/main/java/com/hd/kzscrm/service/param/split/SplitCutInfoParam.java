package com.hd.kzscrm.service.param.split;

import java.math.BigDecimal;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class SplitCutInfoParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 商家编号
	 */
	private Long canteenId;
	/**
	 * 商家用户编号
	 */
	private Long canteenUserId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 用户编号
	 */
	private Long userId;
	/**
	 * 新建时间
	 */
	private String createTime;
	/**
	 * 商家金额
	 */
	private BigDecimal realMoney;
	/**
	 * 平台抽成金额
	 */
	private BigDecimal cutMoney;
	/**
	 * 抽成比例
	 */
	private BigDecimal cutRatio;
	/**
	 * 通道费
	 */
	private BigDecimal channelMoney;
	/**
	 * 抽成状态
	 */
	private Integer cutStatus;
	/**
	 * 抽成时间
	 */
	private String cutTime;
	/**
	 * 取消时间
	 */
	private String cancelTime;
	/**
	 * 退款订单号
	 */
	private String refundOrderNo;
	/**
	 * 删除，0.删除，1.存在
	 */
	private Short delFlag;
	
	
	/**
	 * 查询  开始时间
	 *@Description : TODO
	 *@author : lcl
	 *@time : 2017年3月6日 下午5:45:57
	 */
	private String stratTimes;
	
	/**
	 * 查询 结束时间
	 *@Description : TODO
	 *@author : lcl
	 *@time : 2017年3月6日 下午5:45:57
	 */
	private String endTimes;
	
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
	 * 商家编号
	 */
	private List<Long> canteenIds;
	
	/**
	 * 订单实际金额
	 */
	private BigDecimal orderRealMoney;
	/**
	 * 商家名称
	 */
	private String canteenName;
	/**
	 * 异常消息
	 */
	private String errorMsg;
	
	/**
	 * 支付方式
	 */
	private Integer payModel;
	/**
	 * 业务分账 0 为分账 1已分账
	 */
	private Integer businessCutStatus;
	
	

	public Integer getBusinessCutStatus() {
		return businessCutStatus;
	}

	public void setBusinessCutStatus(Integer businessCutStatus) {
		this.businessCutStatus = businessCutStatus;
	}

	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public void setOrderRealMoney(BigDecimal orderRealMoney) {
		this.orderRealMoney = orderRealMoney;
	}

	public BigDecimal getOrderRealMoney() {
		return orderRealMoney;
	}

	public String getStratTimes() {
		return stratTimes;
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

	public List<Long> getCanteenIds() {
		return canteenIds;
	}

	public void setCanteenIds(List<Long> canteenIds) {
		this.canteenIds = canteenIds;
	}

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
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 新建时间
	 */
	public String getCreateTime() {
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
	public void setCutTime(String cutTime) {
		this.cutTime = cutTime;
	}

	/**
	 * @return 抽成时间
	 */
	public String getCutTime() {
		return this.cutTime;
	}

	/**
	 * 取消时间
	 */
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	/**
	 * @return 取消时间
	 */
	public String getCancelTime() {
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
	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 删除，0.删除，1.存在
	 */
	public Short getDelFlag() {
		return this.delFlag;
	}

}
