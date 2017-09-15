package com.hd.kzscrm.dao.entity.perm;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmpermrole

@Entity
@Table(name = "crm_perm_role")
public class CrmPermRolePO implements Serializable {

	/**
	 * 自增id
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;
	/**
	 * 用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
	 */
	@Column(name="user_type")
	private Integer userType;
	/**
	 * 所属代理商ID
	 */
	@Column(name="agent_id")
	private Long agentId;

	/**
	 * 角色名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 角色类型：1.平台，2.代理商
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 角色编号
	 */
	@Column(name = "code")
	private String code;

	/**
	 * 角色描述
	 */
	@Column(name = "description")
	private String description;

	/**
	 * 状态 1-启用 2-停用
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 0:删除 1：启用
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 * 创建人：crm_user
	 */
	@Column(name = "create_id")
	private Long createId;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private java.util.Date updateTime;

	/**
	 * 更新人：crm_user
	 */
	@Column(name = "update_id")
	private Long updateId;

	// 默认空构造函数
	public CrmPermRolePO() {

	}

	// get set 方法
	/**
	 * 自增id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 自增id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 角色名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 角色编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 角色编号
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 角色描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return 角色描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 状态 1-启用 2-停用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 状态 1-启用 2-停用
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 0:删除 1：启用
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 0:删除 1：启用
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 创建人：crm_user
	 */
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	/**
	 * @return 创建人：crm_user
	 */
	public Long getCreateId() {
		return this.createId;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 更新时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 更新人：crm_user
	 */
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	/**
	 * @return 更新人：crm_user
	 */
	public Long getUpdateId() {
		return this.updateId;
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


}
