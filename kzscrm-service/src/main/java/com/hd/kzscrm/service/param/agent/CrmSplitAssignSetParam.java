package com.hd.kzscrm.service.param.agent;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmSplitAssignSetParam extends PageParam {

	/**
	 * ID
	 */
	private Long id;
	private Long orderId;
	private List<Long>  orderIds;
	//客户资源id
	private Long clientId;
	//客户类型
	private Integer clientType;
	/**
	 * 入驻时间
	 */
	private String enterTime;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	//搜索条件
	private String  selectNum;
	//搜索内容
	private String  condition;
	/**
	 * 食堂资源ID
	 */
	private Long canteenId;
	private List<Long> canteenIds;
	/**
	 * 食堂分帐比例
	 */
	private BigDecimal canteenSplitPercent;
	/**
	 * 业务员分帐比例
	 */
	private BigDecimal businssSplitPercent;
	/**
	 * 代理商分账比例
	 */
	private BigDecimal agentSplitPercent;
	/**
	 * 创建人
	 */
	private Long createrUid;
	/**
	 * 创建时间
	 */
	private Date createrTime;
	
	/**
     * 生效时间(精确到天)
     */
	private   Date   effectiveTime ;
	private   String   effectiveTimeStr ;
	
	/**
	 * 判断生效时间
	 */
	private   Date   judgeEffectiveTime ;
	/**
	 * 书否允许删除 0删除 1正常
	 */
	private Integer delFlag;
	/**
	 * 业务员id
	 */
	private Long businessId;
	
	/**
	 * 代理商id
	 */
	private Long agentId;
	/**
	 * 1.一手，2.多手
	 */
	private Integer businessTakeType;
	/**
	 * 1.一手，2.多手
	 */
	private Integer agentTakeType;

	/**
	 * ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	
	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * 入驻时间
	 */
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	/**
	 * @return 入驻时间
	 */
	public String getEnterTime() {
		return this.enterTime;
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

	public String getSelectNum() {
		return selectNum;
	}

	public void setSelectNum(String selectNum) {
		this.selectNum = selectNum;
	}

	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * 食堂分帐比例
	 */
	public void setCanteenSplitPercent(BigDecimal canteenSplitPercent) {
		this.canteenSplitPercent = canteenSplitPercent;
	}

	/**
	 * @return 食堂分帐比例
	 */
	public BigDecimal getCanteenSplitPercent() {
		return this.canteenSplitPercent;
	}

	/**
	 * 业务员分帐比例
	 */
	public void setBusinssSplitPercent(BigDecimal businssSplitPercent) {
		this.businssSplitPercent = businssSplitPercent;
	}

	/**
	 * @return 业务员分帐比例
	 */
	public BigDecimal getBusinssSplitPercent() {
		return this.businssSplitPercent;
	}

	/**
	 * 代理商分账比例
	 */
	public void setAgentSplitPercent(BigDecimal agentSplitPercent) {
		this.agentSplitPercent = agentSplitPercent;
	}

	/**
	 * @return 代理商分账比例
	 */
	public BigDecimal getAgentSplitPercent() {
		return this.agentSplitPercent;
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

	public Integer getBusinessTakeType() {
		return businessTakeType;
	}

	public void setBusinessTakeType(Integer businessTakeType) {
		this.businessTakeType = businessTakeType;
	}

	public Integer getAgentTakeType() {
		return agentTakeType;
	}

	public void setAgentTakeType(Integer agentTakeType) {
		this.agentTakeType = agentTakeType;
	}

	/**
	 * 创建人
	 */
	public void setCreaterUid(Long createrUid) {
		this.createrUid = createrUid;
	}

	/**
	 * @return 创建人
	 */
	public Long getCreaterUid() {
		return this.createrUid;
	}

	/**
	 * 创建时间
	 */
	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}

	/**
	 * @return 创建时间
	 */
	public Date getCreaterTime() {
		return this.createrTime;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getEffectiveTimeStr() {
		return effectiveTimeStr;
	}

	public void setEffectiveTimeStr(String effectiveTimeStr) {
		this.effectiveTimeStr = effectiveTimeStr;
	}

	public Date getJudgeEffectiveTime() {
		return judgeEffectiveTime;
	}

	public void setJudgeEffectiveTime(Date judgeEffectiveTime) {
		this.judgeEffectiveTime = judgeEffectiveTime;
	}

	/**
	 * 书否允许删除 0删除 1正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 书否允许删除 0删除 1正常
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

	public List<Long> getCanteenIds() {
		return canteenIds;
	}

	public void setCanteenIds(List<Long> canteenIds) {
		this.canteenIds = canteenIds;
	}

	public List<Long> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Long> orderIds) {
		this.orderIds = orderIds;
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

}
