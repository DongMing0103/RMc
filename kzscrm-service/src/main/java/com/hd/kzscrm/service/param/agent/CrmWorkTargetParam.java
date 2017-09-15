package com.hd.kzscrm.service.param.agent;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hd.kzscrm.common.param.PageParam;

public class CrmWorkTargetParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	private List<Long> ids;
	/**
	 * 工作月
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date applyMonth;
	private String applyMonthStr;
	//代理商名称
	private String agentName;
	private String agentNameLike;
	
	//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
	private Integer userType;
	/**
	 * 业务员名称
	 */
	private String businessName;
	private String businessNameLike;
	//搜索时间
	private String stratTimes;
	
	private String startTime;
	
	private String endTimes;
	/**
	 * 当前对象的查询条件
	 */
	private CrmWorkTargetParam where;
	/**
	 * 1.平台，2.代理商
	 */
	private Integer type;

	/**
	 * 订单金额
	 */
	private BigDecimal orderMoney;
	/**
	 * 发展代理商数量
	 */
	private Long agentNum;
	/**
	 * 发展食堂数量
	 */
	private Long canteenNum;
	/**
	 * 申请人
	 */
	private Long applyUserId;
	/**
	 * 更新人
	 */
	private Long updateId;
	/**
	 * 创建时间
	 */
	private Date creatTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 申请状态(1.申请中 2通过)
	 */
	private Integer applyStatus;
	/**
	 * 目标类型(1.团队 2.个人，3.代理商)
	 */
	private Integer targetType;
	
	private Integer delFlag;
	/**
	 * 团队id
	 */
	private Long teamId;
	private String teamIdsStr;
	/**
	 * 业务员id
	 */
	private Long businessId;
	private List<Long> businessIds;
	/**
	 *  代理商id
	 */
	private Long agentId;
	//模糊查询的代理商id
	private List<Long> agentIds;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 代理商名(可用于模糊查询)
	 */
	
	/**
	 * 是否查询当月工作目标   1否  0或空查询当月
	 * */
	private Integer isSameMonth;
	
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessNameLike() {
		return businessNameLike;
	}

	public void setBusinessNameLike(String businessNameLike) {
		this.businessNameLike = businessNameLike;
	}

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public List<Long> getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(List<Long> agentIds) {
		this.agentIds = agentIds;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentNameLike() {
		return agentNameLike;
	}

	public void setAgentNameLike(String agentNameLike) {
		this.agentNameLike = agentNameLike;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}


	public String getTeamIdsStr() {
		return teamIdsStr;
	}

	public void setTeamIdsStr(String teamIdsStr) {
		this.teamIdsStr = teamIdsStr;
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


	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	/**
	 * 发展代理商数量
	 */
	public void setAgentNum(Long agentNum) {
		this.agentNum = agentNum;
	}

	/**
	 * @return 发展代理商数量
	 */
	public Long getAgentNum() {
		return this.agentNum;
	}

	/**
	 * 发展食堂数量
	 */
	public void setCanteenNum(Long canteenNum) {
		this.canteenNum = canteenNum;
	}

	/**
	 * @return 发展食堂数量
	 */
	public Long getCanteenNum() {
		return this.canteenNum;
	}

	/**
	 * 申请人
	 */
	public void setApplyUserId(Long applyUserId) {
		this.applyUserId = applyUserId;
	}

	/**
	 * @return 申请人
	 */
	public Long getApplyUserId() {
		return this.applyUserId;
	}

	/**
	 * 更新人
	 */
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	/**
	 * @return 更新人
	 */
	public Long getUpdateId() {
		return this.updateId;
	}

	/**
	 * 申请状态(1.申请中 2通过)
	 */
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	/**
	 * @return 申请状态(1.申请中 2通过)
	 */
	public Integer getApplyStatus() {
		return this.applyStatus;
	}

	/**
	 * 目标类型(1.团队 2.个人)
	 */
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	/**
	 * @return 目标类型(1.团队 2.个人)
	 */
	public Integer getTargetType() {
		return this.targetType;
	}

	/**
	 * @return the applyMonth
	 */
	public Date getApplyMonth() {
		return applyMonth;
	}

	/**
	 * @param applyMonth the applyMonth to set
	 */
	public void setApplyMonth(Date applyMonth) {
		this.applyMonth = applyMonth;
	}

	public String getApplyMonthStr() {
		return applyMonthStr;
	}

	public void setApplyMonthStr(String applyMonthStr) {
		this.applyMonthStr = applyMonthStr;
	}

	/**
	 * @return the orderMoney
	 */
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	/**
	 * @param orderMoney the orderMoney to set
	 */
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	/**
	 * @return the creatTime
	 */
	public Date getCreatTime() {
		return creatTime;
	}

	/**
	 * @param creatTime the creatTime to set
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return the userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return the where
	 */
	public CrmWorkTargetParam getWhere() {
		return where;
	}

	/**
	 * @param where the where to set
	 */
	public void setWhere(CrmWorkTargetParam where) {
		this.where = where;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsSameMonth() {
		return isSameMonth;
	}

	public void setIsSameMonth(Integer isSameMonth) {
		this.isSameMonth = isSameMonth;
	}
}
