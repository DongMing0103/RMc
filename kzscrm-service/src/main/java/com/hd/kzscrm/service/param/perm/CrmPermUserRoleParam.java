package com.hd.kzscrm.service.param.perm;

import com.hd.kzscrm.common.param.PageParam;

public class CrmPermUserRoleParam extends PageParam {

	/**
	 * 自增id
	 */
	private Long id;
	/**
	 * 用户id(crm_user)
	 */
	private Long userId;
	/**
	 * 角色id(crm_perm_role)
	 */
	private Long roleId;

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
	 * 用户id(crm_user)
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return 用户id(crm_user)
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * 角色id(crm_perm_role)
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return 角色id(crm_perm_role)
	 */
	public Long getRoleId() {
		return this.roleId;
	}

}
