package com.hd.kzscrm.dao.entity.perm;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmpermuserrole

@Entity
@Table(name = "crm_perm_user_role")
public class CrmPermUserRolePO implements Serializable {

	/**
	 * 自增id
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;

	/**
	 * 用户id(crm_user)
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 角色id(crm_perm_role)
	 */
	@Column(name = "role_id")
	private Long roleId;

	// 默认空构造函数
	public CrmPermUserRolePO() {

	}

	// get set 方法
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
