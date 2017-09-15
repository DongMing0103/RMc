package com.hd.kzscrm.service.vo.enterprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 企业信息表
 * @author Administrator
 * upate 苏常松
* @date 2017年3月7日 下午2:36:56
 */
public class CrmEnterpriseVO{
	
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * enterprise的ID
	 */
	private Long enterpriseId;
	
	/**
	 * 企业Ids
	 */
	private List<Long> ids;
	/**
	 * APP版本
	 */
	private String version;
	/**
	 *用户id
	 */
	private Long userId;
	/**
	 * 坐标轴
	 */
	private List<Double> positionAxis=new ArrayList<>(2);
	/**
	 * 企业编号
	 */
	private String idNo;
	
	/**
	 * 企业编号
	 */
	private List<String> idNos;
	/**
	 * 企业名称
	 */
	private String eName;
	/**
	 * 企业名称(移动端提交参数)
	 */
	private String eNameM;
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
	 * 联系人(移动端提交参数)
	 */
	private String contactM;
	/**
	 * 手机号
	 */
	private String mobilephone;
	/**
	 * 手机号(移动端提交参数)
	 */
	private String mobilephoneM;
	/**
	 * 联系人电话
	 */
	private String contactMobilephone;
	/**
	 * 座机号码
	 */
	private String telephone;
	/**
	 * 座机号码(移动端提交参数)
	 */
	private String telephoneM;
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
	 *  状态（1未审核、2已通过 、3已注销 4.未通过）
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
	 * 省代码
	 */
	private Integer provCode;
	/**
	 * 市代码
	 */
	private Integer cityCode;
	
	/**
	 * 地区代码
	 */
	private List<Integer> areaCodeS;
	
	/**
	 * 是否接受商家服务请求（0否  1是）
	 */
	private Long serviceReqFlag;
	/**
	 * 接受页面编码或者名称查询
	 * create 苏常松
	 */
	private String enterNoName;
	/**
	 * 企业名称模糊查询字段
	 */
	private String eNameLike;
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	/*
	 * 商家企业合作表id
	 */
	private Long businessInfoId;
	
	/**id集合*/
	private String enterids;
	 
	
	
	public String geteNameM() {
		return eNameM;
	}
	public void seteNameM(String eNameM) {
		this.eNameM = eNameM;
	}
	public String getContactM() {
		return contactM;
	}
	public void setContactM(String contactM) {
		this.contactM = contactM;
	}
	public String getMobilephoneM() {
		return mobilephoneM;
	}
	public void setMobilephoneM(String mobilephoneM) {
		this.mobilephoneM = mobilephoneM;
	}
	public String getTelephoneM() {
		return telephoneM;
	}
	public void setTelephoneM(String telephoneM) {
		this.telephoneM = telephoneM;
	}
	public String getEnterids() {
		return enterids;
	}
	public void setEnterids(String enterids) {
		this.enterids = enterids;
	}
	public Long getBusinessInfoId() {
		return businessInfoId;
	}
	public void setBusinessInfoId(Long businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
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
	public Integer getDelFlag() {
		return delFlag;
	}
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
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public String getEnterNoName() {
		return enterNoName;
	}
	public void setEnterNoName(String enterNoName) {
		this.enterNoName = enterNoName;
	}
	public String geteNameLike() {
		return eNameLike;
	}
	public void seteNameLike(String eNameLike) {
		this.eNameLike = eNameLike;
	}
	/**
	 * @return the positionAxis
	 */
	public List<Double> getPositionAxis() {
		return positionAxis;
	}
	/**
	 * @param positionAxis the positionAxis to set
	 */
	public void setPositionAxis(List<Double> positionAxis) {
		this.positionAxis = positionAxis;
	}
	public List<Integer> getAreaCodeS() {
		return areaCodeS;
	}
	public void setAreaCodeS(List<Integer> areaCodeS) {
		this.areaCodeS = areaCodeS;
	}
	
	public Integer getProvCode() {
		return provCode;
	}
	public void setProvCode(Integer provCode) {
		this.provCode = provCode;
	}
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the contactMobilephone
	 */
	public String getContactMobilephone() {
		return contactMobilephone;
	}
	/**
	 * @param contactMobilephone the contactMobilephone to set
	 */
	public void setContactMobilephone(String contactMobilephone) {
		this.contactMobilephone = contactMobilephone;
	}
	/**
	 * @return the idNos
	 */
	public List<String> getIdNos() {
		return idNos;
	}
	/**
	 * @param idNos the idNos to set
	 */
	public void setIdNos(List<String> idNos) {
		this.idNos = idNos;
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
