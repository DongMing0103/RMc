package com.hd.kzscrm.dao.entity.enterprise;

import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 企业员工表
 * @author 黄霄仪
 */
@Entity
@Table(name="enterprise_employees_link")
public class EnterpriseEmployeesLinkPO {
	@Column(name="id")
	@Id
	private Long id;
	/**
	 * 真实姓名
	 */
	@Column(name="real_name")
	private String realName;
	/**
	 * 用户id
	 */
	@Column(name="user_id")
	private Long userId;
	/**
	 * 商家id
	 */
	@Column(name="mobilephone")
	private String mobilephone;
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
	 * 岗位职务ID
	 */
	@Column(name="position_id")
	private Long positionId;
	/**
	 * 岗位等级
	 */
	@Column(name="position_level_id")
	private Integer positionLevelId;
	
	/**
	 * 身份证号码，15到18位
	 */
	@Column(name="id_card")
	private String idCard;
	/**
	 * 证件类型  1身份证 2护照 3学生证 （详细：IDTypeEnum.java）
	 * */
	@Column(name="id_type")
	private Integer idType;
	
	/**
	 * 企业认证状态（0 未认证   1 已认证  2 无此人）
	 */
	@Column(name="authentication_status")
	private Integer authenticationStatus;
	/**
	 * 企业认证时间
	 */
	@Column(name="authentication_time")
	private Date authenticationTime;
	/**
	 * 创建时间(申请认证时间)
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 企业谁的操作人ID
	 */
	@Column(name="operate_uid")
	private Long operateUid;
	/**
	 * 删除标识	（0 删除 1未删除）
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
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
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Integer getPositionLevelId() {
		return positionLevelId;
	}
	public void setPositionLevelId(Integer positionLevelId) {
		this.positionLevelId = positionLevelId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getAuthenticationStatus() {
		return authenticationStatus;
	}
	public void setAuthenticationStatus(Integer authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}
	public Date getAuthenticationTime() {
		return authenticationTime;
	}
	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getOperateUid() {
		return operateUid;
	}
	public void setOperateUid(Long operateUid) {
		this.operateUid = operateUid;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
}
