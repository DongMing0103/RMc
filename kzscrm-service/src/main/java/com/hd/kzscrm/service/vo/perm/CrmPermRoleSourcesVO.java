package com.hd.kzscrm.service.vo.perm;


public class CrmPermRoleSourcesVO{

	/**
	 * id
	 */
	private Long id;
	/**
	 * 角色id(crm_perm_role)
	 */
	private Long roleId;
	/**
	 * 资源id(crm_perm_sources)
	 */
	private Long sourcesId;
	/**
	 * 0:删除 1：启用
	 */
	private Integer delFlag;

	/**
	 * id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return id
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
	 * 资源id(crm_perm_sources)
	 */
	public void setSourcesId(Long sourcesId) {
		this.sourcesId = sourcesId;
	}

	/**
	 * @return 资源id(crm_perm_sources)
	 */
	public Long getSourcesId() {
		return this.sourcesId;
	}

	/**
	 * 0:删除 1：启用
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 0:删除 1：启用
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

}
