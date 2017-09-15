package com.hd.kzscrm.service.vo.business;


public class CrmDepartmentVO{
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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	/**
	 * @return the level
	 */
	public Short getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Short level) {
		this.level = level;
	}
	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}
	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
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
	/**
	 * @return the agentBusinessId
	 */
	public Long getAgentBusinessId() {
		return agentBusinessId;
	}
	/**
	 * @param agentBusinessId the agentBusinessId to set
	 */
	public void setAgentBusinessId(Long agentBusinessId) {
		this.agentBusinessId = agentBusinessId;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the createUid
	 */
	public Long getCreateUid() {
		return createUid;
	}
	/**
	 * @param createUid the createUid to set
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}
	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the updateUid
	 */
	public Long getUpdateUid() {
		return updateUid;
	}
	/**
	 * @param updateUid the updateUid to set
	 */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	
}
