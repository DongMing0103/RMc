package com.hd.kzscrm.service.param.enterprise;

import com.hd.kzscrm.common.param.PageParam;


public class EnterpriseUserPositionParam extends PageParam {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 岗位名称
	 */
	private String name;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 手机号
	 */
	private String mobilephone;
	/**
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 删除标识（0删除 1存在）
	 */
	private Integer delFlag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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
