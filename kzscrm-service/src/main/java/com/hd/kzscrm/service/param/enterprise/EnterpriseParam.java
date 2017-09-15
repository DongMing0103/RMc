package com.hd.kzscrm.service.param.enterprise;

import java.util.Date;

import com.hd.kzscrm.common.param.PageParam;
import com.hd.wolverine.aop.Column;

/**
 * 企业信息表
 * 
 * @author wing
 *
 */
public class EnterpriseParam  extends PageParam {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 企业编号
	 */
	private String idNo;
	/**
	 * 企业名称
	 */
	private String eName;

	/**
	 * 入驻时间
	 */
	private Date enterTime;
	/**
	 * 企业类型
	 */
	private Long eStyle;

	/**
	 * 营业执照
	 */
	private String eLicence;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 手机号
	 */
	private String mobilephone;

	/**
	 * 座机号码
	 */
	private String telephone;
	/**
	 * 微信号
	 */
	private String weixin;

	/**
	 * 地理位置
	 */
	private String position;
	/**
	 * 详细地址
	 */
	@Column(name = "address")
	private String address;
	/**
	 * 邮政编码
	 */
	private Long postcode;

	/**
	 * 企业网址
	 */
	private String companyURL;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 产品商标
	 */
	private String brand;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createUid;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private Long updateUid;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 是否允许删除
	 */
	private Integer delFlag;
	/**
	 * 地区代码
	 */
	private Integer areaCode;
	/**
	 * 是否接受商家服务请求（0否 1是）
	 */
	private Long serviceReqFlag;
	/**
	 * 平台类型，1.CRM端
	 */
	private Integer platformType;

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
	 * @return the platformType
	 */
	public Integer getPlatformType() {
		return platformType;
	}

	/**
	 * @param platformType the platformType to set
	 */
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

}
