package com.hd.kzscrm.service.vo.perm;


public class CrmPermRoleMenuVO{

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 角色id(crm_perm_role)
	 */
	private Long roleId;
	/**
	 * 系统菜单id(crm_perm_menu)
	 */
	private Long menuId;

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
