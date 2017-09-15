package com.hd.kzscrm.service.param.perm;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmPermRoleParam extends PageParam {

	/**
	 * 自增id
	 */
	private Long id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色名称
	 */
	private String nameLike;
	/**
	 * 所属代理商ID
	 */
	private Long agentId;
	/**
	 * 用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
	 */
	private Integer userType;
	/**
	 * 角色编号
	 */
	private String code;
	/**
	 * 角色类型：1.平台，2.代理商
	 */
	private Integer type;
	/**
	 * 角色描述
	 */
	private String description;
	/**
	 * 状态 1-启用 2-停用
	 */
	private Integer status;
	/**
	 * 0:删除 1：启用
	 */
	private Integer delFlag;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人：crm_user
	 */
	private Long createId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新人：crm_user
	 */
	private Long updateId;
	/**
	 * 查询全部的数据，有值就查询全部
	 */
	private Integer delFlagAll;
	/**
	 * 角色，菜单映射数据
	 */
	private CrmPermRoleMenuParam crmPermRoleMenuParam;
	/**
	 * 角色，菜单映射数据
	 */
	private List<CrmPermRoleMenuParam> crmPermRoleMenuParams;

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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public Date getCreateTime() {
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
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
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
	 * @return the delFlagAll
	 */
	public Integer getDelFlagAll() {
		return delFlagAll;
	}

	/**
	 * @param delFlagAll the delFlagAll to set
	 */
	public void setDelFlagAll(Integer delFlagAll) {
		this.delFlagAll = delFlagAll;
	}

	/**
	 * @return the nameLike
	 */
	public String getNameLike() {
		return nameLike;
	}

	/**
	 * @param nameLike the nameLike to set
	 */
	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	/**
	 * @return the crmPermRoleMenuParam
	 */
	public CrmPermRoleMenuParam getCrmPermRoleMenuParam() {
		return crmPermRoleMenuParam;
	}

	/**
	 * @param crmPermRoleMenuParam the crmPermRoleMenuParam to set
	 */
	public void setCrmPermRoleMenuParam(CrmPermRoleMenuParam crmPermRoleMenuParam) {
		this.crmPermRoleMenuParam = crmPermRoleMenuParam;
	}

	/**
	 * @return the crmPermRoleMenuParams
	 */
	public List<CrmPermRoleMenuParam> getCrmPermRoleMenuParams() {
		return crmPermRoleMenuParams;
	}

	/**
	 * @param crmPermRoleMenuParams the crmPermRoleMenuParams to set
	 */
	public void setCrmPermRoleMenuParams(
			List<CrmPermRoleMenuParam> crmPermRoleMenuParams) {
		this.crmPermRoleMenuParams = crmPermRoleMenuParams;
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
