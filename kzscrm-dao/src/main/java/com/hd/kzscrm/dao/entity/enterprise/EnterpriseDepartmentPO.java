package com.hd.kzscrm.dao.entity.enterprise;

import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;
/**
 * 部门表
 * @author Administrator
 *
 */
@Entity
@Table(name="enterprise_department")
public class EnterpriseDepartmentPO {
	/**
	 * 主键
	 */
	@Id
	@Column(name="id")
	private Long id;
	
	/**
	 * 父ID
	 * 
	 */
	@Column(name="parent_id")
	private Long parentId;
	
	/**
	 * 不同等级
	 */
	@Column(name="level")
	private Integer level;
	
	/**
	 * 部门编号
	 */
	@Column(name="id_no")
	private Long idNo;
	/**
	 * 部门名称
	 */
	@Column(name="d_name")
	private String dName;
	/**
	 * 企业Id
	 */
	@Column(name="enterprise_id")
	private Long enterpriseId;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 创建人
	 */
	@Column(name="create_uid")
	private Long createUid;
	/**
	 * 修改时间
	 */
	@Column(name="update_time")
	private Date updateTime;
	/**
	 * 修改人
	 */
	@Column(name="update_uid")
	private Long updateUid;
	/**
	 * 状态
	 */
	@Column(name="status")
	private Integer status;
	/**
	 * 是否允许删除
	 */
	@Column(name="del_flag")
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
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
