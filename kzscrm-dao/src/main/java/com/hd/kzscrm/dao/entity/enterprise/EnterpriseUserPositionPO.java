package com.hd.kzscrm.dao.entity.enterprise;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 部门岗位职务表
 * @author lcl
 *
 */
@Entity
@Table(name="enterprise_user_position")
public class EnterpriseUserPositionPO {
	/**
	 * 主键
	 */
	@Id
	@Column(name="id")
	private Long id;
	/**
	 * 岗位名称
	 */
	@Column(name="name")
	private String name;
	/**
	 * 企业ID
	 */
	@Column(name="enterprise_id")
	private Long enterpriseId;
	/**
	 * 部门ID
	 */
	@Column(name="department_id")
	private Long departmentId;
	/**
	 * 删除标识（0删除 1存在）
	 */
	@Column(name="del_flag")
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
	
	
	
	
	
	
}
