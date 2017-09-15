package com.hd.kzscrm.service.param.business;

import java.math.BigDecimal;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmBusinessSplitDetailParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 业务员id
	 */
	private Long businessId;
	private String businessIds;
	private Long agentId;
	private String agentIds;
	private Long orderId;
	//传递id 或 导出多选的ids 客户资源
	private Long clientId;
	/**
	 * 创建时间
	 */
	private String createTime;
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
	private String updateTime;
	/**
	 * 更新人id
	 */
	private Long updateUid;
	/**
	 * 代理商金额
	 */
	private BigDecimal agentMoney;
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
	 * 平台分账开始时间
	 */
	private String startTime;
	
	/**
	 * 代理商开始时间
	 */
	private String stratTime;
	
	/**
	 * 代理商结束时间
	 */
	private String endTime;
	
	/**
	 * 查询内容
	 */
	private String condition;
	private String conditions;
	
	/**
	 * 查询条件
	 */
	private String selectNum;
	private String selectNums;
	
	/**
	 * 区代码
	 */
	private Integer areaCode;
	private List<Integer>  areaCodes;
	
	/**
	 * 省代码
	 */
	private Integer provCode;
	
	/**
	 * 市代码
	 */
	private Integer cityCode;
	
	/**
	 * 代理商名称
	 */
	private String businessName;
	
	/**
	 * 区域名称
	 */
	private String areaName;
	
	/**
	 * 注册时间
	 */
	private String registerTime;
	
	/**
	 * 导出数据id
	 */
	private String ids;
	
	/**
	 * 订单Ids
	 */
	private List<Long> orderIds;
	
	/**
	 * 支付方式
	 */
	private Integer payModel;
	
	/**
	 * 食堂id
	 */
	private Long canteenId;
	
	/**
     * 所属团队id
     */
    private Long teamId;
    
	/**
	 * 在职状态 0.离职，1.在职
	 */
	private Integer jobStatus;
	
	
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

	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 业务员id
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return 业务员id
	 */
	public Long getBusinessId() {
		return this.businessId;
	}

	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 创建人id
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	/**
	 * @return 创建人id
	 */
	public Long getCreateUid() {
		return this.createUid;
	}

	/**
	 * 是否删除，0删除，1正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 是否删除，0删除，1正常
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 更新人id
	 */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	/**
	 * @return 更新人id
	 */
	public Long getUpdateUid() {
		return this.updateUid;
	}

	/**
	 * 代理商金额
	 */
	public void setAgentMoney(BigDecimal agentMoney) {
		this.agentMoney = agentMoney;
	}

	/**
	 * @return 代理商金额
	 */
	public BigDecimal getAgentMoney() {
		return this.agentMoney;
	}

	/**
	 * 业务员金额
	 */
	public void setBusinessMoney(BigDecimal businessMoney) {
		this.businessMoney = businessMoney;
	}

	/**
	 * @return 业务员金额
	 */
	public BigDecimal getBusinessMoney() {
		return this.businessMoney;
	}

	/**
	 * 平台金额
	 */
	public void setPlatformMoney(BigDecimal platformMoney) {
		this.platformMoney = platformMoney;
	}

	/**
	 * @return 平台金额
	 */
	public BigDecimal getPlatformMoney() {
		return this.platformMoney;
	}

	/**
	 * 代理商结算状态
	 */
	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
	}

	/**
	 * @return 代理商结算状态
	 */
	public Integer getAgentStatus() {
		return this.agentStatus;
	}

	/**
	 * 业务元结算状态
	 */
	public void setBusinessStatus(Integer businessStatus) {
		this.businessStatus = businessStatus;
	}

	/**
	 * @return 业务元结算状态
	 */
	public Integer getBusinessStatus() {
		return this.businessStatus;
	}

	/**
	 * 平台结算状态
	 */
	public void setPlatformStatus(Integer platformStatus) {
		this.platformStatus = platformStatus;
	}

	/**
	 * @return 平台结算状态
	 */
	public Integer getPlatformStatus() {
		return this.platformStatus;
	}

	/**
	 * 代理商用户编号
	 */
	public void setAgentUserId(Long agentUserId) {
		this.agentUserId = agentUserId;
	}

	/**
	 * @return 代理商用户编号
	 */
	public Long getAgentUserId() {
		return this.agentUserId;
	}

	/**
	 * 业务员用户编号
	 */
	public void setBusinessUserId(Long businessUserId) {
		this.businessUserId = businessUserId;
	}

	/**
	 * @return 业务员用户编号
	 */
	public Long getBusinessUserId() {
		return this.businessUserId;
	}

	/**
	 * 注文番号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return 注文番号
	 */
	public String getOrderNo() {
		return this.orderNo;
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

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getProvCode() {
		return provCode;
	}

	public void setProvCode(Integer provCode) {
		this.provCode = provCode;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public List<Integer> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(List<Integer> areaCodes) {
		this.areaCodes = areaCodes;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Long> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Long> orderIds) {
		this.orderIds = orderIds;
	}

	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	public Long getCanteenId() {
		return canteenId;
	}

	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	public String getStratTime() {
		return stratTime;
	}

	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
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

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getSelectNums() {
		return selectNums;
	}

	public void setSelectNums(String selectNums) {
		this.selectNums = selectNums;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(String businessIds) {
		this.businessIds = businessIds;
	}

	public String getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(String agentIds) {
		this.agentIds = agentIds;
	}

}
