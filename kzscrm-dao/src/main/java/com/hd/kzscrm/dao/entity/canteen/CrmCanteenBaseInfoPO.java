package com.hd.kzscrm.dao.entity.canteen;

import java.io.Serializable;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.annotation.DataInject;
import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmcanteenbaseinfo

@Entity
@Table(name = "crm_canteen_base_info")
public class CrmCanteenBaseInfoPO implements Serializable {

	/**
	 * 主键
	 */
	@Column(name = "id")
	@Id
	// @AutoIncrease
	private Long id;
	/**
	 * 地理位置
	 */
	@Column(name = "position")
	private String position;
	/**
	 * 客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
	 */
	@Column(name = "client_type")
	private Integer clientType;
	/**
	 * 客户性质 1.散客，2.保护客户，3.正式客户
	 */
	@Column(name = "client_nature")
	private Integer clientNature;

	/**
	 * 企业id
	 */
	@Column(name = "business_id")
	private Long businessId;

	/**
	 * 商家id 大后台canteenBaseInfo
	 */
	@Column(name = "canteen_id")
	private Long canteenId;

	/**
	 * 用户id(crm_user)
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 等级
	 */
	@Column(name = "level")
	private Integer level;

	/**
	 * 商家编号
	 */
	@Column(name = "canteen_no")
	private String canteenNo;

	/**
	 * 商家简称 (食堂简称)
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 商家名称 (食堂名称)
	 */
	@Column(name = "alias_name")
	private String aliasName;

	/**
	 * 商家业务性质id
	 */
	@Column(name = "canteen_category_id")
	private Long canteenCategoryId;

	/**
	 * 联系人姓名
	 */
	@Column(name = "contact_realname")
	private String contactRealname;
	/**
	 * 联系电话
	 */
	@Column(name = "contact_phone")
	private String contactPhone;
	/**
	 * 负责人姓名
	 */
	@Column(name = "head_realname")
	private String headRealname;

	/**
	 * 微信
	 */
	@Column(name = "weixin")
	private String weixin;


	/**
	 * 地区编号
	 */
	@Column(name = "area_code")
	private Long areaCode;

	/**
	 * 区域名称
	 */
	@DataInject(DatabaseTableNameEnum.base_area)
	private String areaName;

	/**
	 * 地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 状态（1未审核、2已通过 、3已注销 4.未通过 5.申请中）
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 创建人
	 */
	@Column(name = "creater_uid")
	private Long createrUid;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private java.util.Date createTime;

	/**
	 * 修改人
	 */
	@Column(name = "updater_uid")
	private Long updaterUid;

	/**
	 * 修改时时间
	 */
	@Column(name = "update_time")
	private java.util.Date updateTime;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 是否删除 0删除 1 正常
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	/**
	 * 食堂属性 1.法人  2.非法人
	 */
	@Column(name = "nature")
	private Integer nature;
	
	/**
	 * 企业注册号
	 */
	@Column(name = "business_no")
	private String businessNo;
	// 默认空构造函数
	public CrmCanteenBaseInfoPO() {

	}

	public String getHeadRealname() {
		return headRealname;
	}

	public void setHeadRealname(String headRealname) {
		this.headRealname = headRealname;
	}

	// get set 方法
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
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public java.util.Date getCreateTime() {
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
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 修改时时间
	 */
	public java.util.Date getUpdateTime() {
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

	public String getAreaName() {
		return areaName;
	}

	/**
	 * @return the clientType
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * @param clientType
	 *            the clientType to set
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
	 * @param clientNature
	 *            the clientNature to set
	 */
	public void setClientNature(Integer clientNature) {
		this.clientNature = clientNature;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 *            the contactPhone to set
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
