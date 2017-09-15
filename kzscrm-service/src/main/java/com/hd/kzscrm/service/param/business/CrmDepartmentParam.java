package com.hd.kzscrm.service.param.business;

import com.hd.kzscrm.common.param.PageParam;

public class CrmDepartmentParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 父ID
	 */
	private Long parentId;
	/**
	 * 级别
	 */
	private Short level;
	/**
	 * 编号
	 */
	private String idNo;
	/**
	 * 类型，1.平台，2.代理商
	 */
	private Integer type;
	/**
	 * 名称
	 */
	private String dName;
	/**
	 * 代理商家id
	 */
	private Long agentBusinessId;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建人id
	 */
	private Long createUid;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 更新人id
	 */
	private Long updateUid;
	/**
	 * 状态，0停用，1正常
	 */
	private Integer status;
	/**
	 * 是否删除，0删除，1正常
	 */
	private Integer delFlag;

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
	 * 父ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return 父ID
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**
	 * 级别
	 */
	public void setLevel(Short level) {
		this.level = level;
	}

	/**
	 * @return 级别
	 */
	public Short getLevel() {
		return this.level;
	}

	/**
	 * 编号
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * @return 编号
	 */
	public String getIdNo() {
		return this.idNo;
	}

	/**
	 * 名称
	 */
	public void setDName(String dName) {
		this.dName = dName;
	}

	/**
	 * @return 名称
	 */
	public String getDName() {
		return this.dName;
	}

	/**
	 * 代理商家id
	 */
	public void setAgentBusinessId(Long agentBusinessId) {
		this.agentBusinessId = agentBusinessId;
	}

	/**
	 * @return 代理商家id
	 */
	public Long getAgentBusinessId() {
		return this.agentBusinessId;
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
	 * 创建人id
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	/**
	 * @return 创建人id
	 */
	public Long getCreateUid() {
		return this.createUid;
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
	 * 更新人id
	 */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	/**
	 * @return 更新人id
	 */
	public Long getUpdateUid() {
		return this.updateUid;
	}

	/**
	 * 状态，0停用，1正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 状态，0停用，1正常
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 是否删除，0删除，1正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 是否删除，0删除，1正常
	 */
	public Integer getDelFlag() {
		return this.delFlag;
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
	 * @return the dName
	 */
	public String getdName() {
		return dName;
	}

	/**
	 * @param dName the dName to set
	 */
	public void setdName(String dName) {
		this.dName = dName;
	}

}
