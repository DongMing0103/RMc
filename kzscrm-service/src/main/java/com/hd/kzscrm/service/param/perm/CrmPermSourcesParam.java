package com.hd.kzscrm.service.param.perm;

import java.util.Date;

import com.hd.kzscrm.common.param.PageParam;

public class CrmPermSourcesParam extends PageParam {

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 菜单id
	 */
	private Long menuId;
	/**
	 * 资源名称
	 */
	private String sourcesName;
	/**
	 * 资源路径,以逗号分隔多URL
	 */
	private String sourcesUrls;
	/**
	 * 标识
	 */
	private String frontContrlName;
	/**
	 * 父资源id(crm_perm_sources)
	 */
	private Long parentId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createUid;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private Long updateUid;
	/**
	 * 0:删除 1：启用
	 */
	private Integer delFlag;
	/**
	 * 非空就全查
	 */
	private Integer delFlagAll;
	/**
	 * CSS图标
	 */
	private String icon;
	//角色id
	private Long roleId;
	//用户id
	private Long userId;
	//是否启用
	private Integer checked;
	
	
	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Integer getDelFlagAll() {
		return delFlagAll;
	}

	public void setDelFlagAll(Integer delFlagAll) {
		this.delFlagAll = delFlagAll;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 主键id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 菜单id
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return 菜单id
	 */
	public Long getMenuId() {
		return this.menuId;
	}

	/**
	 * 资源名称
	 */
	public void setSourcesName(String sourcesName) {
		this.sourcesName = sourcesName;
	}

	/**
	 * @return 资源名称
	 */
	public String getSourcesName() {
		return this.sourcesName;
	}


	/**
	 * 标识
	 */
	public void setFrontContrlName(String frontContrlName) {
		this.frontContrlName = frontContrlName;
	}

	/**
	 * @return 标识
	 */
	public String getFrontContrlName() {
		return this.frontContrlName;
	}

	/**
	 * 父资源id(crm_perm_sources)
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return 父资源id(crm_perm_sources)
	 */
	public Long getParentId() {
		return this.parentId;
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
	 * 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 修改人
	 */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	/**
	 * @return 修改人
	 */
	public Long getUpdateUid() {
		return this.updateUid;
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
	 * @return the sourcesUrls
	 */
	public String getSourcesUrls() {
		return sourcesUrls;
	}

	/**
	 * @param sourcesUrls the sourcesUrls to set
	 */
	public void setSourcesUrls(String sourcesUrls) {
		this.sourcesUrls = sourcesUrls;
	}

}
