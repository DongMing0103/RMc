package com.hd.kzscrm.service.param.business;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmPositionParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	private List<Long> ids;
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
	 * 岗位名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 岗位名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 代理商id
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return 代理商id
	 */
	public Long getAgentId() {
		return this.agentId;
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
	 * @return the ids
	 */
	public List<Long> getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
