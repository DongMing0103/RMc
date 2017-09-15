package com.hd.kzscrm.service.param.business;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hd.kzscrm.common.param.PageParam;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CrmTeamParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;

	private List<Long> ids;
	
	private String idS;
	/**
	 * 所属代理商，crm_agent表主键ID，如果为空，是平台团队，否则就是代理商团队
	 */
	private Long agentId;
	private List<Long> agentIds;
	/**
	 * 团队等级起始值
	 */
	private Integer startLevel;
	/**
	 * 团队等级结束值
	 */
	private Integer endLevel;
	/**
	 * 团队名称
	 */
	private String name;
	/**
	 * 团队级别
	 */
	private Integer level;
	/**
	 * 区域编码：业务范围
	 */
	private   Long   areaCode;
	/**
	 * 删除标识 0删除 1正常
	 */
	private Integer delFlag;
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
	
	private Long userId;
	/**
	 * 类型: 1.平台，2.代理商
	 */
	private Integer type;
	
	/**
	 * 大区
	*/
	private Long region;
	
	/**
	 * 省
	*/
	private Integer prov1;
	
	/**
	 * 市
	*/
	private Integer city1;
	

	public Integer getProv1() {
		return prov1;
	}

	public void setProv1(Integer prov1) {
		this.prov1 = prov1;
	}


	public Integer getCity1() {
		return city1;
	}

	public void setCity1(Integer city1) {
		this.city1 = city1;
	}

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}

	/**
	 * 排序用
	 */
	private String orderBy;
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

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 团队名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 团队名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 删除标识 0删除 1正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 删除标识 0删除 1正常
	 */
	public Integer getDelFlag() {
		return this.delFlag;
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

	/**
	 * 创建人
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	/**
	 * @return 创建人
	 */
	public Long getCreateUid() {
		return this.createUid;
	}

	/**
	 * @return the agentId
	 */
	public Long getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
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
	 * @return the areaCode
	 */
	public Long getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIdS() {
		return idS;
	}

	public void setIdS(String idS) {
		this.idS = idS;
	}

	/**
	 * @return the startLevel
	 */
	public Integer getStartLevel() {
		return startLevel;
	}

	/**
	 * @param startLevel the startLevel to set
	 */
	public void setStartLevel(Integer startLevel) {
		this.startLevel = startLevel;
	}

	/**
	 * @return the endLevel
	 */
	public Integer getEndLevel() {
		return endLevel;
	}

	/**
	 * @param endLevel the endLevel to set
	 */
	public void setEndLevel(Integer endLevel) {
		this.endLevel = endLevel;
	}

	public List<Long> getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(List<Long> agentIds) {
		this.agentIds = agentIds;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


}
