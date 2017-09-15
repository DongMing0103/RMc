package com.hd.kzscrm.dao.entity.canteen;

import java.io.Serializable;
import java.util.Date;

import scala.math.BigInt;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 商家基础信息表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "canteen_base_info")
public class CanteenBaseInfoPO implements Serializable {

	/**
	 * 
	 */
	private final transient long serialVersionUID = -2380171752321193918L;

	@Column(name = "id")
	@Id
	private Long id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Long userId;
	/**
	 * 商家等级（1，2，3）
	 */
	@Column(name = "level")
	private Integer level;
	/**
	 * 平台类型，1.CRM端
	 */
	@Column(name = "platform_type")
	private Integer platformType;
	/**
	 * 商家编号
	 */
	@Column(name = "canteen_no")
	private String canteenNo;
	/**
	 * 商家简称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 商家分类id
	 */
	@Column(name = "canteen_category_id")
	private Long canteenCategoryId;
	/**
	 * 联系人
	 */
	@Column(name = "contact_realname")
	private String contactRealname;
	/**
	 * 联系电话
	 */
	@Column(name = "contact_phone")
	private String contactPhone;
	/**
	 * 微信
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
	 * 状态（1未审核、2已通过 、3已注销 4.未通过）
	 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 创建人
	 */
	@Column(name = "creater_uid")
	private Integer createrUid;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@Column(name = "updater_uid")
	private Integer updaterUid;
	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	/**
	 * 是否允许删除
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	
	/**
	 * 头像
	 * */
	@Column(name = "head_img")
	private String headImg;
	
	/**
	 * 所在城市
	 */
	@Column(name="area_code")
	private Long areaCode;

	/**
	 * 是否接受请求
	 */
	@Column(name="service_req_flag")
	private BigInt serviceReqFlag; 
	
	/**
	 * 企业注册号
	*/
	@Column(name="business_no")
	private String businessNo;
	
	
	/**
	 * 商家名称
	*/
	@Column(name="alias_name")
	private String aliasName;
	
	/**
	 * 食堂属性 1.法人 2.非法人
	*/
	@Column(name="nature")
	private Integer nature;
	
	
	/**
	 * 简称更新时间
	*/
	@Column(name="name_update_time")
	private Date nameUpdateTime;
	
	
	
	public Date getNameUpdateTime() {
		return nameUpdateTime;
	}
	public void setNameUpdateTime(Date nameUpdateTime) {
		this.nameUpdateTime = nameUpdateTime;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public Integer getNature() {
		return nature;
	}
	public void setNature(Integer nature) {
		this.nature = nature;
	}
	public BigInt getServiceReqFlag() {
		return serviceReqFlag;
	}
	public void setServiceReqFlag(BigInt serviceReqFlag) {
		this.serviceReqFlag = serviceReqFlag;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Long getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}
	@Override
	public String toString() {
		return "CanteenBaseInfoPO [serialVersionUID=" + serialVersionUID
				+ ", id=" + id + ", userId=" + userId + ", level=" + level
				+ ", canteenNo=" + canteenNo + ", name=" + name
				+ ", canteenCategoryId=" + canteenCategoryId
				+ ", contactRealname=" + contactRealname + ", contactPhone="
				+ contactPhone + ", weixin=" + weixin + ", position="
				+ position + ", address=" + address + ", status=" + status
				+ ", createrUid=" + createrUid + ", createTime=" + createTime
				+ ", updaterUid=" + updaterUid + ", updateTime=" + updateTime
				+ ", remark=" + remark + ", delFlag=" + delFlag + ", headImg="
				+ headImg + ", areaCode=" + areaCode + ", serviceReqFlag="
				+ serviceReqFlag + ", getServiceReqFlag()="
				+ getServiceReqFlag() + ", getId()=" + getId()
				+ ", getUserId()=" + getUserId() + ", getLevel()=" + getLevel()
				+ ", getCanteenNo()=" + getCanteenNo() + ", getName()="
				+ getName() + ", getCanteenCategoryId()="
				+ getCanteenCategoryId() + ", getContactRealname()="
				+ getContactRealname() + ", getContactPhone()="
				+ getContactPhone() + ", getWeixin()=" + getWeixin()
				+ ", getPosition()=" + getPosition() + ", getAddress()="
				+ getAddress() + ", getStatus()=" + getStatus()
				+ ", getCreaterUid()=" + getCreaterUid() + ", getCreateTime()="
				+ getCreateTime() + ", getUpdaterUid()=" + getUpdaterUid()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getRemark()="
				+ getRemark() + ", getDelFlag()=" + getDelFlag()
				+ ", getHeadImg()=" + getHeadImg() + ", getAreaCode()="
				+ getAreaCode() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
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
