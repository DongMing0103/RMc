package com.hd.kzscrm.service.param.enterprise;

import java.util.Date;

import com.hd.kzscrm.common.param.PageParam;

	
public class EnterpriseDepartmentParam extends PageParam {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 父ID
	 * 
	 */
	private Long parentId;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 手机号
	 */
	private String mobilephone;
	
	/**
	 * 不同等级
	 */
	private Integer level;
	
	/**
	 * 部门编号
	 */
	private Long idNo;
	/**
	 * 部门名称
	 */
	private String dName;
	/**
	 * 企业Id
	 */
	private Long enterpriseId;
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
	 * 状态
	 */
	private Integer status;
	/**
	 * 是否允许删除
	 */
	private Integer delFlag;
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getIdNo() {
		return idNo;
	}
	public void setIdNo(Long idNo) {
		this.idNo = idNo;
	}
	
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
}
