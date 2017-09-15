package com.hd.kzscrm.service.param.perm;

import java.util.Date;

import com.hd.kzscrm.common.param.PageParam;

public class CrmPermMenuParam extends PageParam {

	/**
	 * 自增id
	 */
	private Long id;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 资源地址
	 */
	private String url;
	/**
	 * 非空就全查
	 */
	private Integer delFlagAll;
	/**
	 * 父资源
	 */
	private Long parentId;
	/**
	 * 排序
	 */
	private Integer sortNo;
	/**
	 * 节点深度
	 */
	private Integer deepth;
	/**
	 * 菜单等级
	 */
	private Integer level;
	/**
	 * 是否叶子节点
	 */
	private Integer isLeaf;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 资源描述
	 */
	private String description;
	/**
	 * 资源编码
	 */
	private String code;
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
	 * CSS图标
	 */
	private String icon;
	/**
	 * 角色
	 */
	private CrmPermRoleParam crmPermRoleParam;

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
	 * 资源名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 资源名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 资源地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return 资源地址
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 父资源
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return 父资源
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**
	 * 排序
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return 排序
	 */
	public Integer getSortNo() {
		return this.sortNo;
	}

	/**
	 * 节点深度
	 */
	public void setDeepth(Integer deepth) {
		this.deepth = deepth;
	}

	/**
	 * @return 节点深度
	 */
	public Integer getDeepth() {
		return this.deepth;
	}

	/**
	 * 是否叶子节点
	 */
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return 是否叶子节点
	 */
	public Integer getIsLeaf() {
		return this.isLeaf;
	}

	/**
	 * 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 状态
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 资源描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return 资源描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 资源编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 资源编码
	 */
	public String getCode() {
		return this.code;
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
	 * CSS图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return CSS图标
	 */
	public String getIcon() {
		return this.icon;
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
	 * @return the crmPermRoleParam
	 */
	public CrmPermRoleParam getCrmPermRoleParam() {
		return crmPermRoleParam;
	}

	/**
	 * @param crmPermRoleParam the crmPermRoleParam to set
	 */
	public void setCrmPermRoleParam(CrmPermRoleParam crmPermRoleParam) {
		this.crmPermRoleParam = crmPermRoleParam;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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


}
