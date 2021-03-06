package com.hd.kzscrm.service.vo.canteen;

import java.util.Date;
import java.util.List;

public class CrmCanteenBaseInfoVO {

	/**
	 * 主键
	 */
	private Long id;
	private List<Long> ids;
	/**
	 * 业务员id
	 */
	private Long businessId;
	/**
	 * 业务员姓名
	 */
	private String businessName;
	
	/**
	 * 商家id
	 */
	private Long canteenId;
	/**
	 * 地理位置（坐标）
	 */
	private String position;
	 /**
     * 客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
   */
	private   Integer   clientType ;
	/**
     * 客户性质	1.散客，2.保护客户，3.正式客户
   */
	private   Integer   clientNature ;
	/**
	 * 用户id(crm_user)
	 */
	private Long userId;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 商家编号
	 */
	private String canteenNo;
	/**
	 * 商家简称
	 */
	private String name;
	/**
	 * 商家名称
	 */
	private String aliasName;
	
	/**
	 * 商家业务性质id
	 */
	private Long canteenCategoryId;
	/**
	 * 联系人姓名
	 */
	private String contactRealname;
	/**
	 * 联系电话
	 */
	private String contactPhone;
	/**
	 * 微信
	 */
	private String weixin;
	/**
	 * 岗位
	 */
	private Long positionId;
	/**
	 * 地区编号
	 */
	private Long areaCode;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 状态（1未审核、2已通过 、3已注销 4.未通过）
	 */
	private Integer status;
	/**
	 * 创建人
	 */
	private Long createrUid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private Long updaterUid;
	/**
	 * 修改时时间
	 */
	private Date updateTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否删除 0删除 1 正常
	 */
	private Integer delFlag;
	
	/**
	 * 商家logo
	 */
	private String logo;

	/**
	 * 负责人
	 */
	private String headRealname;
	
	/**
	 * 负责人电话
	 */
	private String headPhone;
	
	/**
	 * 负责人身份证件
	 */
	private String headIdcard;
	
	/**
	 * 负责人身份证图片
	 */
	private String headIdcardPic;
	
	/**
	 * 营业执照
	 */
	private String businessPic;
	/**
	 * 单位名
	 */
	private String Ename;
	/**
	 * 单位性质
	 */
	private String Estyle;
	/**
	 * 单位地址
	 */
	private String Eaddress;
	/**
	 * 食堂属性 1.法人  2.非法人
	 */
	private Integer nature;
	/**
	 * 企业注册号
	 */
	private String businessNo;
	/**
	 * 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 主键
	 */
	public Long getId() {
		return this.id;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	/**
	 * 企业id
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return 企业id
	 */
	public Long getBusinessId() {
		return this.businessId;
	}

	/**
	 * 商家id
	 */
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	/**
	 * @return 商家id
	 */
	public Long getCanteenId() {
		return this.canteenId;
	}

	/**
	 * 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return 用户id
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * 等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return 等级
	 */
	public Integer getLevel() {
		return this.level;
	}

	/**
	 * 商家编号
	 */
	public void setCanteenNo(String canteenNo) {
		this.canteenNo = canteenNo;
	}

	/**
	 * @return 商家编号
	 */
	public String getCanteenNo() {
		return this.canteenNo;
	}

	/**
	 * 商家名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 商家名称
	 */
	public String getName() {
		return this.name;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	/**
	 * 商家业务性质id
	 */
	public void setCanteenCategoryId(Long canteenCategoryId) {
		this.canteenCategoryId = canteenCategoryId;
	}

	/**
	 * @return 商家业务性质id
	 */
	public Long getCanteenCategoryId() {
		return this.canteenCategoryId;
	}

	/**
	 * 联系人姓名
	 */
	public void setContactRealname(String contactRealname) {
		this.contactRealname = contactRealname;
	}

	/**
	 * @return 联系人姓名
	 */
	public String getContactRealname() {
		return this.contactRealname;
	}

	/**
	 * 微信
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	/**
	 * @return 微信
	 */
	public String getWeixin() {
		return this.weixin;
	}

	/**
	 * 岗位
	 */
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return 岗位
	 */
	public Long getPositionId() {
		return this.positionId;
	}

	/**
	 * 地区编号
	 */
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return 地区编号
	 */
	public Long getAreaCode() {
		return this.areaCode;
	}

	/**
	 * 地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return 地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 状态
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 创建人
	 */
	public void setCreaterUid(Long createrUid) {
		this.createrUid = createrUid;
	}

	/**
	 * @return 创建人
	 */
	public Long getCreaterUid() {
		return this.createrUid;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 修改人
	 */
	public void setUpdaterUid(Long updaterUid) {
		this.updaterUid = updaterUid;
	}

	/**
	 * @return 修改人
	 */
	public Long getUpdaterUid() {
		return this.updaterUid;
	}

	/**
	 * 修改时时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 修改时时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 是否删除 0删除 1 正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 是否删除 0删除 1 正常
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

	/**
	 * @return the clientType
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * @param clientType the clientType to set
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * @return the clientNature
	 */
	public Integer getClientNature() {
		return clientNature;
	}

	/**
	 * @param clientNature the clientNature to set
	 */
	public void setClientNature(Integer clientNature) {
		this.clientNature = clientNature;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getHeadRealname() {
		return headRealname;
	}

	public void setHeadRealname(String headRealname) {
		this.headRealname = headRealname;
	}

	public String getHeadPhone() {
		return headPhone;
	}

	public void setHeadPhone(String headPhone) {
		this.headPhone = headPhone;
	}

	public String getHeadIdcard() {
		return headIdcard;
	}

	public void setHeadIdcard(String headIdcard) {
		this.headIdcard = headIdcard;
	}

	public String getHeadIdcardPic() {
		return headIdcardPic;
	}

	public void setHeadIdcardPic(String headIdcardPic) {
		this.headIdcardPic = headIdcardPic;
	}

	public String getBusinessPic() {
		return businessPic;
	}

	public void setBusinessPic(String businessPic) {
		this.businessPic = businessPic;
	}

	public String getEname() {
		return Ename;
	}

	public void setEname(String ename) {
		Ename = ename;
	}

	public String getEstyle() {
		return Estyle;
	}

	public void setEstyle(String estyle) {
		Estyle = estyle;
	}

	public String getEaddress() {
		return Eaddress;
	}

	public void setEaddress(String eaddress) {
		Eaddress = eaddress;
	}

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

}
