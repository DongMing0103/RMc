package com.hd.kzscrm.dao.entity.business;

import java.math.BigDecimal;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

import scala.math.BigInt;

/**
 * 商家合作表
 * @author Administrator
 *
 */
@Entity
@Table(name="business_info")
public class BusinessInfoPO {
	/**
	 * 主键ID
	 */
	@Column(name="id")
	@Id
	private Long id;
	
	/**
	 * 企业id
	 */
	@Column(name="enterprise_id")
	private Long enterpriseId; 
	
	/**
	 * 餐厅ID
	 */
	@Column(name="canteen_id")
	private Long canteenId;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 合作类型：1内部供餐 2议价供餐
	 */
	@Column(name="cooperation_type")
	private Integer cooperationType;
	/**
	 * 配置类型：1已配置 2未配置
	 */
	@Column(name="canteen_status")
	private Integer canteenStatus;
	/**
	 * 折扣
	 */
	@Column(name="discount")
	private Double discount;
	
	/**
	 * 删除标识
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	
	/**
	 * 企业名称
	 */
	@Column(name="e_name")
	private String eName;
	
	/**
	 * 详细地址（街道门牌号等）
	 */
	@Column(name="address")
	private String address;
	
	/**
	 * 入驻时间
	 */
	@Column(name="enter_time")
	private Date enterTime;
	
	
	/**
	 * 联系人
	 * @return
	 */
	@Column(name="contact")
	private String contact;
	/**
	 * 手机号码
	 * @return
	 */
	@Column(name="mobilephone")
	private String mobilephone;
	/**
	 * 座机
	 */
	@Column(name="telephone")
	private String telephone;
	
	/**
	 * 坐标
	 */
	@Column(name="position")
	private String position;
	
	/**
	 * 处理状态：1 申请中  2 已同意 3 不同意
	 * @return
	 */
	@Column(name="status")
	private Integer status;
	/**
	 * 发起人角色 1 企业  2 商家
	 * @return
	 */
	@Column(name="originator_role")
	private Integer originatorRole;
	
	@Column(name="service_req_flag")
	private BigInt serviceReqFlag;
	
	/**企业员工数量*/
	private Integer employeesNum;
	
	/**
	 * 距离，KM
	 */
	private BigDecimal distance;
	
	
	public Integer getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(Integer employeesNum) {
		this.employeesNum = employeesNum;
	}

	public BigInt getServiceReqFlag() {
		return serviceReqFlag;
	}

	public void setServiceReqFlag(BigInt serviceReqFlag) {
		this.serviceReqFlag = serviceReqFlag;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getOriginatorRole() {
		return originatorRole;
	}

	public void setOriginatorRole(Integer originatorRole) {
		this.originatorRole = originatorRole;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getCanteenId() {
		return canteenId;
	}

	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCooperationType() {
		return cooperationType;
	}

	public void setCooperationType(Integer cooperationType) {
		this.cooperationType = cooperationType;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getCanteenStatus() {
		return canteenStatus;
	}

	public void setCanteenStatus(Integer canteenStatus) {
		this.canteenStatus = canteenStatus;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
	
}
