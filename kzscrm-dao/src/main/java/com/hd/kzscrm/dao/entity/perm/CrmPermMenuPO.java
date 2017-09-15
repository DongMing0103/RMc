package com.hd.kzscrm.dao.entity.perm;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

@Entity
@Table(name = "crm_perm_menu")
public class CrmPermMenuPO implements Serializable {

	/**
	 * 自增id
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;

	/**
	 * 资源名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 资源地址
	 */
	@Column(name = "url")
	private String url;

	/**
	 * 父资源
	 */
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 排序
	 */
	@Column(name = "sort_no")
	private Integer sortNo;

	/**
	 * 节点深度
	 */
	@Column(name = "deepth")
	private Integer deepth;

	/**
	 * 是否叶子节点
	 */
	@Column(name = "is_leaf")
	private Integer isLeaf;

	/**
	 * 状态
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 资源描述
	 */
	@Column(name = "description")
	private String description;

	/**
	 * 资源编码
	 */
	@Column(name = "code")
	private String code;

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

	/**
	 * CSS图标
	 */
	@Column(name = "icon")
	private String icon;

	// 默认空构造函数
	public CrmPermMenuPO() {

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

}
