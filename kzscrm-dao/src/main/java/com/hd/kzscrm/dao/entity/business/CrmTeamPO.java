package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmteam

@Entity
@Table(name = "crm_team")
public class CrmTeamPO implements Serializable {

	/**
	 * 主键
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;
	/**
	 * 1.平台，2.代理商
	 */
	@Column(name="type")
	private Integer type;
	
	/**
	 * 团队级别
	 */
	@Column(name="level")
	private Integer level;

	/**
	 * 团队名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 团队父ID，上级主管
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
	/**
	 * 父辈ids(包括父辈的父辈,也包括本身的Id),用','隔开.
	 */
	@Column(name = "parent_ids")
	private String parentIds;
	
	
	/**
	 * 顶级父ID
	 */
	@Column(name="top_parent_id")
	private   Long   topParentId ;
	/**
	 * 删除标识 0删除 1正常
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	/**
	 * 创建时间
	 */
	@Column(name = "creater_time")
	private Date createrTime;
	/**
	 * 所属代理商，crm_agent表主键ID，如果为空，是平台团队，否则就是代理商团队
	 */
	@Column(name = "agent_id")
	private Long agentId;

	/**
	 * 创建人
	 */
	@Column(name = "create_uid")
	private Long createUid;

	/**
	 * 区域编码：业务范围
	 */
	@Column(name = "area_code")
	private Long areaCode;

	// 默认空构造函数
	public CrmTeamPO() {

	}

	// get set 方法
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
	public void setCreaterTime(java.util.Date createrTime) {
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

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public Long getAgentId() {
		return agentId;
	}

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
	 * @param parentId
	 *            the parentId to set
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

}
