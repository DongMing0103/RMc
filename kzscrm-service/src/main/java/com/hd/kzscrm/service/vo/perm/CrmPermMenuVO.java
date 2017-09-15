package com.hd.kzscrm.service.vo.perm;


public class CrmPermMenuVO {

	/**
	 * 自增id
	 */
	private Long id;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 资源地址
	 */
	private String url;
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
	 * 是否叶子节点
	 */
	private Integer isLeaf;
	/**
	 * 是否为选中状态
	 */
	private boolean checked;
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
	private String createTime;
	/**
	 * 创建人：crm_user
	 */
	private Long createId;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 更新人：crm_user
	 */
	private Long updateId;
	/**
	 * CSS图标
	 */
	private String icon;

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
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public String getCreateTime() {
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
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 更新时间
	 */
	public String getUpdateTime() {
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
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
