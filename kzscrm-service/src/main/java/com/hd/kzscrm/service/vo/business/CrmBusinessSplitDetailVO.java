package com.hd.kzscrm.service.vo.business;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.annotation.DataInject;
import com.hd.kzscrm.dao.entity.business.CrmBusinessSplitDetailPO;
import java.math.*;
import java.util.Date;
import java.util.List;
import com.hd.wolverine.aop.*;

public class CrmBusinessSplitDetailVO extends CrmBusinessSplitDetailPO {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 业务员id
	 */
	private Long businessId;

	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	/**
	 * 创建人id
	 */
	private Long createUid;

	/**
	 * 是否删除，0删除，1正常
	 */
	private Integer delFlag;

	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;

	/**
	 * 更新人id
	 */
	private Long updateUid;

	/**
	 * 代理商金额
	 */
	private BigDecimal agentMoney;

	/**
	 * 抽成比例
	 */
	private BigDecimal cutPercent;
	/**
	 * 业务员金额
	 */
	private BigDecimal businessMoney;

	/**
	 * 平台金额
	 */
	private BigDecimal platformMoney;

	/**
	 * 代理商结算状态
	 */
	private Integer agentStatus;

	/**
	 * 业务元结算状态
	 */
	private Integer businessStatus;

	/**
	 * 平台结算状态
	 */
	private Integer platformStatus;

	/**
	 * 代理商用户编号
	 */
	private Long agentUserId;

	/**
	 * 业务员用户编号
	 */
	private Long businessUserId;

	/**
	 * 注文番号
	 */
	private String orderNo;
	
	
	/**
	 * 通道费
	 */
	private BigDecimal channelMoney;

	/**
	 * 区域名称
	 */
	@DataInject(DatabaseTableNameEnum.base_area)
	private String areaName;

	/**
	 * 代理商名称
	 */
	private String agentName;

	/**
	 * 业务员名称
	 */
	private String businessName;

	/**
	 * 代理商id
	 */
	private Long agentId;

	/**
	 * 注册时间
	 */
	private java.util.Date registerTime;

	/**
	 * 分账开始时间
	 */
	private String stratTimes;

	/**
	 * 分账结束时间
	 */
	private String endTimes;

	/**
	 * 订单流水
	 */
	private String orderFlowNo;

	/**
	 * 分账时间
	 */
	private Date generalLedgerDate;

	/**
	 * 支付方式
	 */
	private Integer payModel;
	
	/**
	 * 支付方式格式化
	 */
	private String payModelName;
	

	/**
	 * 抽成金额
	 */
	private BigDecimal cutMoney;

	/**
	 * 实付金额
	 */
	private BigDecimal realMoney;

	/**
	 * 食堂名称
	 */
	private String canteenName;

	private Long orderId;

	/**
	 * 客户数量
	 */
	private BigDecimal agentNumber;

	/**
	 * 抽成比例
	 */
	private BigDecimal cutRatio;

	/**
	 * 分账金额
	 */
	private BigDecimal splitMoney;
	
	/**
	 * 查询开始时间
	 */
	private String startTime;
	
	/**
	 * 查询结束时间
	 */
	private String endTime;

	/**
	 * 入职时间
	 */
	private Date workTime;

	/**
	 * 团队名称
	 */
	private String teamName;

	/**
	 * 职业状态
	 */
	private Integer jobStatus;
	
	/**
	 * 职业名称
	 */
	private String jobStatusName;

	/**
	 * 正式客户数量统计
	 */
	private BigDecimal clientNatureNum;

	/**
	 * 在职人数
	 */
	private Integer onJobNum;

	/**
	 * 离职人数
	 */
	private Integer quitJobNum;
	
	/**
	 * 食堂分账金额 canteenSplitMoney
	 */
	private BigDecimal canteenSplitMoney;

	/**
	 * 代理商分账金额  agentSplitMoney
	 */
	private BigDecimal agentSplitMoney;
	
	/**
	 * 业务员分账金额 businessSplitMoney
	 */
	private BigDecimal businessSplitMoney;
	
	/**
	 * 平台分账金额
	 */
	private BigDecimal platformSplitMoney;
	
	private String orderNum;
	
	/**
	 * 入住时间
	 */
	private Date createrTime;
	
	/**
	 * 代理商分账金额
	 */
	private BigDecimal splitAgentMoney;
	
	/**
	 * 食堂分账比例
	 */
	private BigDecimal canteenSplitPercent;
	
	/**
	 * 食堂分账金额
	 */
	private BigDecimal splitCanteenMoney;
	
	/**
	 * 食堂名称
	 */
	private String name;
	
	/**
	 * 业务员分帐比例
	 */
	private BigDecimal businssSplitPercent;

	/**
	 * 代理商分账比例
	 */
	private BigDecimal agentSplitPercent;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	public BigDecimal getAgentMoney() {
		return agentMoney;
	}

	public void setAgentMoney(BigDecimal agentMoney) {
		this.agentMoney = agentMoney;
	}

	public BigDecimal getBusinessMoney() {
		return businessMoney;
	}

	public void setBusinessMoney(BigDecimal businessMoney) {
		this.businessMoney = businessMoney;
	}

	public BigDecimal getPlatformMoney() {
		return platformMoney;
	}

	public void setPlatformMoney(BigDecimal platformMoney) {
		this.platformMoney = platformMoney;
	}

	public Integer getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
	}

	public Integer getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(Integer businessStatus) {
		this.businessStatus = businessStatus;
	}

	public Integer getPlatformStatus() {
		return platformStatus;
	}

	public void setPlatformStatus(Integer platformStatus) {
		this.platformStatus = platformStatus;
	}

	public Long getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(Long agentUserId) {
		this.agentUserId = agentUserId;
	}

	public Long getBusinessUserId() {
		return businessUserId;
	}

	public void setBusinessUserId(Long businessUserId) {
		this.businessUserId = businessUserId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public java.util.Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
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

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getOrderFlowNo() {
		return orderFlowNo;
	}

	public void setOrderFlowNo(String orderFlowNo) {
		this.orderFlowNo = orderFlowNo;
	}

	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	public BigDecimal getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(BigDecimal cutPercent) {
		this.cutPercent = cutPercent;
	}

	public Date getGeneralLedgerDate() {
		return generalLedgerDate;
	}

	public void setGeneralLedgerDate(Date generalLedgerDate) {
		this.generalLedgerDate = generalLedgerDate;
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

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAgentNumber() {
		return agentNumber;
	}

	public void setAgentNumber(BigDecimal agentNumber) {
		this.agentNumber = agentNumber;
	}

	public BigDecimal getCutRatio() {
		return cutRatio;
	}

	public void setCutRatio(BigDecimal cutRatio) {
		this.cutRatio = cutRatio;
	}

	public BigDecimal getSplitMoney() {
		return splitMoney;
	}

	public void setSplitMoney(BigDecimal splitMoney) {
		this.splitMoney = splitMoney;
	}

	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public BigDecimal getClientNatureNum() {
		return clientNatureNum;
	}

	public void setClientNatureNum(BigDecimal clientNatureNum) {
		this.clientNatureNum = clientNatureNum;
	}

	public Integer getOnJobNum() {
		return onJobNum;
	}

	public void setOnJobNum(Integer onJobNum) {
		this.onJobNum = onJobNum;
	}

	public Integer getQuitJobNum() {
		return quitJobNum;
	}

	public void setQuitJobNum(Integer quitJobNum) {
		this.quitJobNum = quitJobNum;
	}

	public BigDecimal getChannelMoney() {
		return channelMoney;
	}

	public void setChannelMoney(BigDecimal channelMoney) {
		this.channelMoney = channelMoney;
	}

	public BigDecimal getCanteenSplitMoney() {
		return canteenSplitMoney;
	}

	public void setCanteenSplitMoney(BigDecimal canteenSplitMoney) {
		this.canteenSplitMoney = canteenSplitMoney;
	}

	public BigDecimal getAgentSplitMoney() {
		return agentSplitMoney;
	}

	public void setAgentSplitMoney(BigDecimal agentSplitMoney) {
		this.agentSplitMoney = agentSplitMoney;
	}

	public BigDecimal getBusinessSplitMoney() {
		return businessSplitMoney;
	}

	public void setBusinessSplitMoney(BigDecimal businessSplitMoney) {
		this.businessSplitMoney = businessSplitMoney;
	}

	public BigDecimal getPlatformSplitMoney() {
		return platformSplitMoney;
	}

	public void setPlatformSplitMoney(BigDecimal platformSplitMoney) {
		this.platformSplitMoney = platformSplitMoney;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getJobStatusName() {
		return jobStatusName;
	}

	public void setJobStatusName(String jobStatusName) {
		this.jobStatusName = jobStatusName;
	}

	public String getPayModelName() {
		return payModelName;
	}

	public void setPayModelName(String payModelName) {
		this.payModelName = payModelName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}

	public BigDecimal getSplitAgentMoney() {
		return splitAgentMoney;
	}

	public void setSplitAgentMoney(BigDecimal splitAgentMoney) {
		this.splitAgentMoney = splitAgentMoney;
	}

	public BigDecimal getCanteenSplitPercent() {
		return canteenSplitPercent;
	}

	public void setCanteenSplitPercent(BigDecimal canteenSplitPercent) {
		this.canteenSplitPercent = canteenSplitPercent;
	}

	public BigDecimal getSplitCanteenMoney() {
		return splitCanteenMoney;
	}

	public void setSplitCanteenMoney(BigDecimal splitCanteenMoney) {
		this.splitCanteenMoney = splitCanteenMoney;
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

	public BigDecimal getAgentSplitPercent() {
		return agentSplitPercent;
	}

	public void setAgentSplitPercent(BigDecimal agentSplitPercent) {
		this.agentSplitPercent = agentSplitPercent;
	}

}
