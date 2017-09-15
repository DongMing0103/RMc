package com.hd.kzscrm.dao.entity.perm;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmpermsources

@Entity
@Table(name = "crm_perm_sources")
public class CrmPermSourcesPO implements Serializable {

	/**
	 * 主键id
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;

	/**
	 * 菜单id
	 */
	@Column(name = "menu_id")
	private Long menuId;

	/**
	 * 资源名称
	 */
	@Column(name = "sources_name")
	private String sourcesName;

	/**
	 * 资源路径,以逗号分隔多URL
	 */
	@Column(name = "sources_urls")
	private String sourcesUrls;

	/**
	 * 标识
	 */
	@Column(name = "front_contrl_name")
	private String frontContrlName;

	/**
	 * 父资源id(crm_perm_sources)
	 */
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 * 创建人
	 */
	@Column(name = "create_uid")
	private Long createUid;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private java.util.Date updateTime;

	/**
	 * 修改人
	 */
	@Column(name = "update_uid")
	private Long updateUid;

	/**
	 * 0:删除 1：启用
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	/**
	 * CSS图标
	 */
	@Column(name = "icon")
	private String icon;
	/**
	 * 是不是勾选了权限
	 */
	@Column(name = "checked")
	private Integer checked;

	// 默认空构造函数
	public CrmPermSourcesPO() {

	}

	
	public Integer getChecked() {
		return checked;
	}


	public void setChecked(Integer checked) {
		this.checked = checked;
	}


	// get set 方法
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
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 修改时间
	 */
	public java.util.Date getUpdateTime() {
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
