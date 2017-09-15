package com.hd.kzscrm.service.param.business;

import java.util.Date;

import com.hd.kzscrm.common.param.PageParam;

public class CrmContractPicParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	 /**
     * AGENTENTERID
     * 根据type来判断ID的来源是crm_agent,或crm_canteen_base_inf
   */
	private   Long   agentCanteenId ;
	/**
	 * 类型(1.代理商 2.食堂)
	 */
	private Integer type;
	/**
	 * 删除标识 0删除 1正常
	 */
	private Integer delFlag;
	/**
	 * 图片路径
	 */
	private String picPath;
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
	 * 类型(1.代理商 2.食堂)
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return 类型(1.代理商 2.食堂)
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 删除标识 0删除 1正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 删除标识 0删除 1正常
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
	 * @return the agentCanteenId
	 */
	public Long getAgentCanteenId() {
		return agentCanteenId;
	}

	/**
	 * @param agentCanteenId the agentCanteenId to set
	 */
	public void setAgentCanteenId(Long agentCanteenId) {
		this.agentCanteenId = agentCanteenId;
	}

	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}

	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
