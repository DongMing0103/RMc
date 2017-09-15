package com.hd.kzscrm.service.vo.agent;

import java.math.BigDecimal;
import java.util.Date;

import com.hd.kzscrm.service.vo.BaseVO;

public class CrmSplitAssignSetVO implements BaseVO {

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 订单编号
	 */
	private Long orderNo;

	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 订单金额
	 */
	private BigDecimal realMoney;
	private String orderNum;
	// 分账金额
	private BigDecimal splitMoney;
	/**
	 * 食堂名称
	 */
	private String name;

	/**
	 * 代理商名称
	 */
	private String agentName;

	/**
	 * 业务员姓名
	 */
	private String businessName;

	/**
	 * 支付流水号
	 */
	private Long orderFlowNo;

	/**
	 * 现金流水号
	 * 
	 * @return
	 */
	private String cashFlowNo;
	// 客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
	private Integer clientType;
	// 客户属性 1 代理商客户 2平台客户
	private Integer customerAttribute;
	// 订单数量
	private Long orderNumber;

	// 代理商分账金额
	private BigDecimal splitAgentMoney;

	// 食堂分账金额
	private BigDecimal splitCanteenMoney;

	

	/**
	 * 入驻时间
	 */
	private java.util.Date enterTime;

	/**
	 * 客户资源ID
	 */
	private Long canteenId;

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
	private java.util.Date createrTime;
	/**
	 * 生效时间(精确到天)
	 */
	private Date effectiveTime;

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

	// get set 方法
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

	/**
	 * 入驻时间
	 */
	public void setEnterTime(java.util.Date enterTime) {
		this.enterTime = enterTime;
	}

	/**
	 * @return 入驻时间
	 */
	public java.util.Date getEnterTime() {
		return this.enterTime;
	}

	/**
	 * 客户资源ID
	 */
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	/**
	 * @return 客户资源ID
	 */
	public Long getCanteenId() {
		return this.canteenId;
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
	public void setCreaterTime(java.util.Date createrTime) {
		this.createrTime = createrTime;
	}

	/**
	 * @return 创建时间
	 */
	public java.util.Date getCreaterTime() {
		return this.createrTime;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getCustomerAttribute() {
		return customerAttribute;
	}

	public void setCustomerAttribute(Integer customerAttribute) {
		this.customerAttribute = customerAttribute;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getCashFlowNo() {
		return cashFlowNo;
	}

	public void setCashFlowNo(String cashFlowNo) {
		this.cashFlowNo = cashFlowNo;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public BigDecimal getSplitMoney() {
		return splitMoney;
	}

	public void setSplitMoney(BigDecimal splitMoney) {
		this.splitMoney = splitMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrderFlowNo() {
		return orderFlowNo;
	}

	public void setOrderFlowNo(Long orderFlowNo) {
		this.orderFlowNo = orderFlowNo;
	}
	public BigDecimal getSplitAgentMoney() {
		return splitAgentMoney;
	}

	public void setSplitAgentMoney(BigDecimal splitAgentMoney) {
		this.splitAgentMoney = splitAgentMoney;
	}

	public BigDecimal getSplitCanteenMoney() {
		return splitCanteenMoney;
	}

	public void setSplitCanteenMoney(BigDecimal splitCanteenMoney) {
		this.splitCanteenMoney = splitCanteenMoney;
	}

}
