package com.hd.kzscrm.service.vo.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.annotation.DataInject;
import com.hd.kzscrm.service.vo.area.BaseAreaVO;

public class CrmTeamVO {
	/**
	 * 主键
	 */
	// @AutoIncrease
	private Long id;

	/**
	 * 团队名称
	 */
	private String name;

	/**
	 * 删除标识 0删除 1正常
	 */
	private Integer delFlag;
	/**
	 * 1.平台，2.代理商
	 */
	private Integer type;
	/**
	 * 上级主管
	 */
	private String higherManager;
	/**
	 * 团队级别
	 */
	private Integer level;
	/**
	 * 创建时间
	 */
	private Date createrTime;
	/**
	 * 团队父ID，上级主管
	 */
	private Long parentId;
	/**
	 * 父辈ids(包括父辈的父辈,也包括本身的Id),用','隔开.
	 */
	private String parentIds;
	/**
	 * 顶级父ID
	 */
	private   Long   topParentId ;
	/**
	 * 创建人
	 */
	private Long createUid;

	/**
	 * 区域编码
	 */
	private Long areaCode;

	/**
	 * 所属代理商ID，crm_agent表主键ID，如果为空，是平台团队，否则就是代理商团队
	 */
	private Long agentId;
	private List<CrmTeamVO> agentIds;

	/**
	 * 业务员名称
	 */
	private String businessName;
	
	/**
	 * 业务经理名称
	 */
	private String businessAgent;

	/**
	 * 食堂名称
	 */
	private String developCateen;

	/**
	 * 区域名字
	 */
	@DataInject(DatabaseTableNameEnum.base_area)
	private String areaName;

	/**
	 * 订单额完成量
	 */
	private BigDecimal completeOrderMoney;
	/**
	 * 地区表集合
	 */
	private List<BaseAreaVO> baseAreaVOs;
	/**
	 * 业务员集合
	 */
	private List<CrmBusinessVO> crmBusinessVOs;
	/**
	 * 团队表集合
	 */
	private List<CrmTeamVO> crmTeamVOs;

	/**
	 * 订单数量
	 * 
	 * @return
	 */
	private BigDecimal realMoney;
	
	/**
	 * 团队数量
	 */
	private BigDecimal teamNumber;
	
	/**
	 * 食堂数量
	 * @return
	 */
	private BigDecimal canteenNum;
	private Long canteenNums;
	
	/**
	 * 代理商数量
	 * @return
	 */
	private BigDecimal agentNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public java.util.Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(java.util.Date createrTime) {
		this.createrTime = createrTime;
	}

	public Long getCreateUid() {
		return createUid;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}


	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getDevelopCateen() {
		return developCateen;
	}

	public void setDevelopCateen(String developCateen) {
		this.developCateen = developCateen;
	}

	public BigDecimal getCompleteOrderMoney() {
		return completeOrderMoney;
	}

	public void setCompleteOrderMoney(BigDecimal completeOrderMoney) {
		this.completeOrderMoney = completeOrderMoney;
	}


	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getTopParentId() {
		return topParentId;
	}

	public void setTopParentId(Long topParentId) {
		this.topParentId = topParentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the realMoney
	 */
	public BigDecimal getRealMoney() {
		return realMoney;
	}

	/**
	 * @param realMoney the realMoney to set
	 */
	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	/**
	 * @return the higherManager
	 */
	public String getHigherManager() {
		return higherManager;
	}

	/**
	 * @param higherManager the higherManager to set
	 */
	public void setHigherManager(String higherManager) {
		this.higherManager = higherManager;
	}

	public String getBusinessAgent() {
		return businessAgent;
	}

	public void setBusinessAgent(String businessAgent) {
		this.businessAgent = businessAgent;
	}
	
	/**
	 * @return the baseAreaVOs
	 */
	public List<BaseAreaVO> getBaseAreaVOs() {
		return baseAreaVOs;
	}

	/**
	 * @param baseAreaVOs the baseAreaVOs to set
	 */
	public void setBaseAreaVOs(List<BaseAreaVO> baseAreaVOs) {
		this.baseAreaVOs = baseAreaVOs;
	}

	/**
	 * @return the crmTeamVOs
	 */
	public List<CrmTeamVO> getCrmTeamVOs() {
		return crmTeamVOs;
	}

	/**
	 * @param crmTeamVOs the crmTeamVOs to set
	 */
	public void setCrmTeamVOs(List<CrmTeamVO> crmTeamVOs) {
		this.crmTeamVOs = crmTeamVOs;
	}

	public BigDecimal getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(BigDecimal teamNumber) {
		this.teamNumber = teamNumber;
	}

	public BigDecimal getCanteenNum() {
		return canteenNum;
	}

	public void setCanteenNum(BigDecimal canteenNum) {
		this.canteenNum = canteenNum;
	}

	public BigDecimal getAgentNum() {
		return agentNum;
	}

	public void setAgentNum(BigDecimal agentNum) {
		this.agentNum = agentNum;
	}

	/**
	 * @return the crmBusinessVOs
	 */
	public List<CrmBusinessVO> getCrmBusinessVOs() {
		return crmBusinessVOs;
	}

	/**
	 * @param crmBusinessVOs the crmBusinessVOs to set
	 */
	public void setCrmBusinessVOs(List<CrmBusinessVO> crmBusinessVOs) {
		this.crmBusinessVOs = crmBusinessVOs;
	}

	public Long getCanteenNums() {
		return canteenNums;
	}

	public void setCanteenNums(Long canteenNums) {
		this.canteenNums = canteenNums;
	}

	public List<CrmTeamVO> getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(List<CrmTeamVO> agentIds) {
		this.agentIds = agentIds;
	}

	
	

}
