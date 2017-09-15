package com.hd.kzscrm.service.vo.business;

import java.util.Date;


public class CrmPositionVO{
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 岗位名称
	 */
	private String name;
	/**
	 * 代理商id
	 */
	private Long agentId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人id
	 */
	private Long createUid;
	/**
	 * 是否删除，0删除，1正常
	 */
	private Integer delFlag;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新人id
	 */
	private Long updateUid;
	/**
	 * 类型，1.平台，2.代理商
	 */
	private Integer type;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the agentBusinessId
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
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
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
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
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
