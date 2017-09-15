package com.hd.kzscrm.dao.entity.perm;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmpermrolesources

@Entity
@Table(name = "crm_perm_role_sources")
public class CrmPermRoleSourcesPO implements Serializable {

	/**
	 * id
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
	 * 资源id(crm_perm_sources)
	 */
	@Column(name = "sources_id")
	private Long sourcesId;

	/**
	 * 0:删除 1：启用
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	// 默认空构造函数
	public CrmPermRoleSourcesPO() {

	}

	// get set 方法
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
