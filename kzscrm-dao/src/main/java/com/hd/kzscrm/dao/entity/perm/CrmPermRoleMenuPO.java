package com.hd.kzscrm.dao.entity.perm;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmpermrolemenu

@Entity
@Table(name = "crm_perm_role_menu")
public class CrmPermRoleMenuPO implements Serializable {

	/**
	 * ID
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;

	/**
	 * 角色id(crm_perm_role)
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * 系统菜单id(crm_perm_menu)
	 */
	@Column(name = "menu_id")
	private Long menuId;

	// 默认空构造函数
	public CrmPermRoleMenuPO() {

	}

	// get set 方法
	/**
	 * ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return ID
	 */
	public Long getId() {
		return this.id;
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

	/**
	 * 系统菜单id(crm_perm_menu)
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return 系统菜单id(crm_perm_menu)
	 */
	public Long getMenuId() {
		return this.menuId;
	}

}
