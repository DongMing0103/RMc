package com.hd.kzscrm.dao.entity.enterprise;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 企业信息表
 * 
 * @author wing
 *
 */
@Entity
@Table(name = "crm_enterprise")
public class CrmEnterprisePO implements Serializable {

	/**
	 * 
	 */
	private static final transient long serialVersionUID = -6594632241500330595L;
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	private Long id;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Long userId;
	/**
	 * 企业编号
	 */
	@Column(name = "id_no")
	private String idNo;
	/**
	 * 企业名称
	 */
	@Column(name = "e_name")
	private String eName;
	/**
	 * enterprise的ID
	 */
	@Column(name="enterprise_id")
	private Long enterpriseId;

	/**
	 * 入驻时间
	 */
	@Column(name = "enter_time")
	private Date enterTime;
	/**
	 * 企业类型
	 */
	@Column(name = "e_style")
	private Long eStyle;

	/**
	 * 营业执照
	 */
	@Column(name = "e_licence")
	private String eLicence;
	/**
	 * 联系人
	 */
	@Column(name = "contact")
	private String contact;
	/**
	 * 手机号
	 */
	@Column(name = "mobilephone")
	private String mobilephone;

	/**
	 * 座机号码
	 */
	@Column(name = "telephone")
	private String telephone;
	/**
	 * 微信号
	 */
	@Column(name = "weixin")
	private String weixin;

	/**
	 * 地理位置
	 */
	@Column(name = "position")
	private String position;
	/**
	 * 详细地址
	 */
	@Column(name = "address")
	private String address;
	/**
	 * 邮政编码
	 */
	@Column(name = "postcode")
	private Long postcode;

	/**
	 * 企业网址
	 */
	@Column(name = "company_URL")
	private String companyURL;
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;
	/**
	 * 产品商标
	 */
	@Column(name = "brand")
	private String brand;
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 创建人
	 */
	@Column(name = "create_uid")
	private Long createUid;
	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	/**
	 * 修改人
	 */
	@Column(name = "update_uid")
	private Long updateUid;
	/**
	 * 状态（1未审核、2已通过 、3已注销 4.未通过）
	 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 是否允许删除
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	/**
	 * 地区代码
	 */
	@Column(name = "area_code")
	private Integer areaCode;
	/**
	 * 是否接受商家服务请求（0否 1是）
	 */
	@Column(name = "service_req_flag")
	private Long serviceReqFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Long geteStyle() {
		return eStyle;
	}

	public void seteStyle(Long eStyle) {
		this.eStyle = eStyle;
	}

	public String geteLicence() {
		return eLicence;
	}

	public void seteLicence(String eLicence) {
		this.eLicence = eLicence;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPostcode() {
		return postcode;
	}

	public void setPostcode(Long postcode) {
		this.postcode = postcode;
	}

	public String getCompanyURL() {
		return companyURL;
	}

	public void setCompanyURL(String companyURL) {
		this.companyURL = companyURL;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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


	/**
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public Long getServiceReqFlag() {
		return serviceReqFlag;
	}

	public void setServiceReqFlag(Long serviceReqFlag) {
		this.serviceReqFlag = serviceReqFlag;
	}

	/**
	 * @return the enterpriseId
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * @param enterpriseId the enterpriseId to set
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
