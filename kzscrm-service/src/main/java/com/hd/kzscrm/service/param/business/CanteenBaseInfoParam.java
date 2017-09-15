package com.hd.kzscrm.service.param.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;


/**
 * 商家基本信息表
 * @author Administrator
 *
 */
public class CanteenBaseInfoParam extends PageParam{
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * APP版本
	 */
	private String version;
	/**
	 * 商家等级（1，2，3）
	 */
	private Integer level;
	/**
	 * 商家等级（1-A 2-B 3-C 4-D）
	 */
	private String levels;
	private Double ratio;
	/**
	 * 抽成设置表的roleNane
	 */
	private String roleName;
	/**
	 * 商家编号
	 */
	private String canteenNo;
	/**
	 * 图片读取地址
	 */
	private String imgReadIpAddress;

	/**
	 * 用户TOKEN
	 */
	private String userToken;
	/**
	 * 用户手机号，用于验证TOKEN
	 */
	private String mobilephone;
	/**
	 * 商家分类id 如餐饮，办公用品等
	 */
	private Long canteenCategoryId;
	/**
	 * 商家分类名称 如餐饮，办公用品等
	 */
	private String canteenCategoryName;
	/**
	 * 联系人
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
	 * 地区代码
	 */
	private Long areaCode;
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
	
	//排序字段，1.distance，距离,2.discount，折扣
	private Integer sortField;
	
	
	/**
	 * 坐标轴
	 */
	private List<Double> positionAxis=new ArrayList<>(2);
	/**
	 * 状态（1未审核、2已通过 、3已注销 4.未通过）
	 */
	private Integer status;
	/**
	 * 创建人
	 */
	private Integer createrUid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private Integer updaterUid;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否允许删除
	 */
	private Integer delFlag;
	
	/**
	 *	编号名称查询 
	 */
	private String canteenNoName;
	/**
	 * 是否关联企业，1是关联，其它为未关联：0未认证，1已认证，2.新人注册
	 */
	private Integer authenFlag;
	/**
	 * 食堂类型，1.内部食堂，2.外部食堂
	 */
	private Integer type;
	/**
	 * 平台类型，1.CRM端
	 */
	private Integer platformType;

	/**
	 * 来着附属表
	 * 下三列
	 */
	private String headRealname;//负责人
	private String headRealnameM;//负责人(移动端提交参数)
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
	private String headIdcard;// 负责人身份证件
	private String headPhone;//负责人电话
	private String headPhoneM;//负责人电话(移动端提交参数)
	private List<Long> canteenIds;//商家id的集合
	private Long canteenId;//商家id
	private String logo;//商家logo
	private String businessPic;//营业执照
	private String headIdcardPic;//身份证照片
	/**
	 * 菜单日期
	 */
	private Date menuDate;
	private String canteenName;//商家名称
	private String canteenNameM;//商家名称(移动端提交参数)
	//年份选择
	private String years;
	//月份
	private String months;
	//周
	private String weeks;
	//日
	private String days;
	
	/**
	 * 用于查询寻找食堂时过滤已合作的商家id
	 * */
	private List<Long> notInIds;
	/**
	 * 用于查询寻找食堂时根据类目查商家id
	 * */
	private List<Long> inIds;
	/**
	 * 商家名
	 * */
	private String name;
	
	/**商家id集合 excel*/
	private String canteenids;
	
	/**
	 * 商家编号集合
	 * @author jyt 2017年4月10日 下午7:53:14
	 */
	private List<String> canteenNos;
	
	/**
	 * 卫生许可证
	 */
	private String businessHealthPic;
	
	/** 
	* 食堂分析排序分类
	*/ 
	private String sortDesc;
	
	
/*	*//** 商家卫生许可证img *//*
	private List<CanteenHealthPicPO> canteenHealthPicList;*/
	
	
	
	
	public String getHeadRealnameM() {
		return headRealnameM;
	}
	public void setHeadRealnameM(String headRealnameM) {
		this.headRealnameM = headRealnameM;
	}
	public String getHeadPhoneM() {
		return headPhoneM;
	}
	public void setHeadPhoneM(String headPhoneM) {
		this.headPhoneM = headPhoneM;
	}
	public String getCanteenNameM() {
		return canteenNameM;
	}
	public void setCanteenNameM(String canteenNameM) {
		this.canteenNameM = canteenNameM;
	}
	/*public List<CanteenHealthPicPO> getCanteenHealthPicList() {
		return canteenHealthPicList;
	}
	public void setCanteenHealthPicList(
			List<CanteenHealthPicPO> canteenHealthPicList) {
		this.canteenHealthPicList = canteenHealthPicList;
	}*/
	public String getSortDesc() {
		return sortDesc;
	}
	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
	}
	public Double getRatio() {
		return ratio;
	}
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public String getBusinessHealthPic() {
		return businessHealthPic;
	}
	public void setBusinessHealthPic(String businessHealthPic) {
		this.businessHealthPic = businessHealthPic;
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
	public List<Integer> getAreaCodeS() {
		return areaCodeS;
	}
	public void setAreaCodeS(List<Integer> areaCodeS) {
		this.areaCodeS = areaCodeS;
	}
	public String getBusinessPic() {
		return businessPic;
	}
	public void setBusinessPic(String businessPic) {
		this.businessPic = businessPic;
	}
	public String getHeadIdcardPic() {
		return headIdcardPic;
	}
	public void setHeadIdcardPic(String headIdcardPic) {
		this.headIdcardPic = headIdcardPic;
	}
	public String getCanteenids() {
		return canteenids;
	}
	public void setCanteenids(String canteenids) {
		this.canteenids = canteenids;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Long getCanteenId() {
		return canteenId;
	}
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public List<Long> getCanteenIds() {
		return canteenIds;
	}
	public void setCanteenIds(List<Long> canteenIds) {
		this.canteenIds = canteenIds;
	}
	public String getCanteenName() {
		return canteenName;
	}
	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public Long getPostcode() {
		return postcode;
	}
	public void setPostcode(Long postcode) {
		this.postcode = postcode;
	}
	public Long getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}
	public String getHeadRealname() {
		return headRealname;
	}
	public void setHeadRealname(String headRealname) {
		this.headRealname = headRealname;
	}
	public String getHeadIdcard() {
		return headIdcard;
	}
	public void setHeadIdcard(String headIdcard) {
		this.headIdcard = headIdcard;
	}
	public String getHeadPhone() {
		return headPhone;
	}
	public void setHeadPhone(String headPhone) {
		this.headPhone = headPhone;
	}
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getCanteenNo() {
		return canteenNo;
	}
	public void setCanteenNo(String canteenNo) {
		this.canteenNo = canteenNo;
	}

	public Long getCanteenCategoryId() {
		return canteenCategoryId;
	}
	public void setCanteenCategoryId(Long canteenCategoryId) {
		this.canteenCategoryId = canteenCategoryId;
	}
	public String getContactRealname() {
		return contactRealname;
	}
	public void setContactRealname(String contactRealname) {
		this.contactRealname = contactRealname;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreaterUid() {
		return createrUid;
	}
	public void setCreaterUid(Integer createrUid) {
		this.createrUid = createrUid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdaterUid() {
		return updaterUid;
	}
	public void setUpdaterUid(Integer updaterUid) {
		this.updaterUid = updaterUid;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getCanteenNoName() {
		return canteenNoName;
	}
	public void setCanteenNoName(String canteenNoName) {
		this.canteenNoName = canteenNoName;
	}
	public List<Double> getPositionAxis() {
		return positionAxis;
	}
	public void setPositionAxis(List<Double> positionAxis) {
		this.positionAxis = positionAxis;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public Integer getAuthenFlag() {
		return authenFlag;
	}
	public void setAuthenFlag(Integer authenFlag) {
		this.authenFlag = authenFlag;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getMenuDate() {
		return menuDate;
	}
	public void setMenuDate(Date menuDate) {
		this.menuDate = menuDate;
	}
	public List<Long> getNotInIds() {
		return notInIds;
	}
	public void setNotInIds(List<Long> notInIds) {
		this.notInIds = notInIds;
	}
	public List<Long> getInIds() {
		return inIds;
	}
	public void setInIds(List<Long> inIds) {
		this.inIds = inIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCanteenCategoryName() {
		return canteenCategoryName;
	}
	public void setCanteenCategoryName(String canteenCategoryName) {
		this.canteenCategoryName = canteenCategoryName;
	}
	public String getImgReadIpAddress() {
		return imgReadIpAddress;
	}
	public void setImgReadIpAddress(String imgReadIpAddress) {
		this.imgReadIpAddress = imgReadIpAddress;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public List<String> getCanteenNos() {
		return canteenNos;
	}
	public void setCanteenNos(List<String> canteenNos) {
		this.canteenNos = canteenNos;
	}
	/**
	 * @return the sortField
	 */
	public Integer getSortField() {
		return sortField;
	}
	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(Integer sortField) {
		this.sortField = sortField;
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
