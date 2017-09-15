package com.hd.kzscrm.service.param.agent;

import com.hd.kzscrm.common.param.PageParam;

public class CrmAgentAreaParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 区域代码
	 */
	private Long areaCode;
	/**
	 * 代理商id
	 */
	private Long agentId;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建人id
	 */
	private Long createUid;
	/**
	 * 是否删除
	 */
	private Integer defFlag;
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 修改人id
	 */
	private Long updateUid;

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
	 * 区域代码
	 */
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return 区域代码
	 */
	public Long getAreaCode() {
		return this.areaCode;
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
	 * 是否删除
	 */
	public void setDefFlag(Integer defFlag) {
		this.defFlag = defFlag;
	}

	/**
	 * @return 是否删除
	 */
	public Integer getDefFlag() {
		return this.defFlag;
	}

	/**
	 * 修改时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 修改时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 修改人id
	 */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	/**
	 * @return 修改人id
	 */
	public Long getUpdateUid() {
		return this.updateUid;
	}

}
