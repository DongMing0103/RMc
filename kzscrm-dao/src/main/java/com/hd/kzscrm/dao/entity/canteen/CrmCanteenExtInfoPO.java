package com.hd.kzscrm.dao.entity.canteen;

import java.math.BigDecimal;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

@Entity
@Table(name="crm_canteen_ext_info")
public class CrmCanteenExtInfoPO {
	/**
	 * 主键
	 */
	@Column(name="id")
	@Id
	private Long id;
	/**
	 * 用户id
	 */
	@Column(name="user_id")
	private Long userId;
	/**
	 * 基础信息表id
	 */
	@Column(name="base_info_id")
	private Long baseInfoId;
	/**
	 * 地理信息
	 */
	@Column(name="area_code")
	private Long areaCode;
	/**
	 * 商家logo
	 */
	@Column(name="logo")
	private String logo;
	/**
	 * 负责人
	 */
	@Column(name="head_realname")
	private String headRealname;
	/**
	 * 负责人身份证件
	 */
	@Column(name="head_idcard")
	private String headIdcard;
	/**
	 * 卫生许可证到期时间
	 */
	@Column(name="health_end_time")
	private Date healthEndTime;
	/**
	 * 负责人身份证图片
	 */
	@Column(name="head_idcard_pic")
	private String headIdcardPic;
	/**
	 * 卫生许可证
	 */
	@Column(name="business_health_pic")
	private String businessHealthPic;
	/**
	 * 营业执照
	 */
	@Column(name="business_pic")
	private String businessPic;
	/**
	 * 创建人
	 */
	@Column(name="creater_uid")
	private Long createrUid;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@Column(name="updater_uid")
	private Long updaterUid;
	/**
	 * 修改时间
	 */
	@Column(name="update_time")
	private Date updateTime;
	/**
	 * 备注
	 */
	@Column(name="remark")
	private String remark;
	/**
	 * 是否允许删除 
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	/**
	 * 配菜窗口数量
	 */
	@Column(name="window_num")
	private Integer windowNum;
	/**
	 * 入驻时间
	 */
	@Column(name="enter_time")
	private Date enterTime;
	/**
	 * 是否对外开放 0 否 1 是
	 */
	@Column(name="open_outside_flag")
	private Integer openOutsideFlag;
	/**
	 * 是否允许外送 0否 1 是
	 */
	@Column(name="open_send_flag")
	private Integer openSendFlag;
	/**
	 * 起送价
	 */
	@Column(name="send_base_price")
	private BigDecimal sendBasePrice;
	/**
	 * 配送范围
	 */
	@Column(name="send_scope")
	private String sendScope;
	/**
	 * 负责人电话
	 */
	@Column(name="head_phone")
	private String headPhone;
	
	
	public Integer getWindowNum() {
		return windowNum;
	}
	public void setWindowNum(Integer windowNum) {
		this.windowNum = windowNum;
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
	public Long getBaseInfoId() {
		return baseInfoId;
	}
	public void setBaseInfoId(Long baseInfoId) {
		this.baseInfoId = baseInfoId;
	}
	public Long getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
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
	public String getHeadIdcard() {
		return headIdcard;
	}
	public void setHeadIdcard(String headIdcard) {
		this.headIdcard = headIdcard;
	}
	public Date getHealthEndTime() {
		return healthEndTime;
	}
	public void setHealthEndTime(Date healthEndTime) {
		this.healthEndTime = healthEndTime;
	}
	public String getHeadIdcardPic() {
		return headIdcardPic;
	}
	public void setHeadIdcardPic(String headIdcardPic) {
		this.headIdcardPic = headIdcardPic;
	}
	public String getBusinessHealthPic() {
		return businessHealthPic;
	}
	public void setBusinessHealthPic(String businessHealthPic) {
		this.businessHealthPic = businessHealthPic;
	}
	public String getBusinessPic() {
		return businessPic;
	}
	public void setBusinessPic(String businessPic) {
		this.businessPic = businessPic;
	}
	public Long getCreaterUid() {
		return createrUid;
	}
	public void setCreaterUid(Long createrUid) {
		this.createrUid = createrUid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdaterUid() {
		return updaterUid;
	}
	public void setUpdaterUid(Long updaterUid) {
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
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	public Integer getOpenOutsideFlag() {
		return openOutsideFlag;
	}
	public void setOpenOutsideFlag(Integer openOutsideFlag) {
		this.openOutsideFlag = openOutsideFlag;
	}
	public Integer getOpenSendFlag() {
		return openSendFlag;
	}
	public void setOpenSendFlag(Integer openSendFlag) {
		this.openSendFlag = openSendFlag;
	}
	public BigDecimal getSendBasePrice() {
		return sendBasePrice;
	}
	public void setSendBasePrice(BigDecimal sendBasePrice) {
		this.sendBasePrice = sendBasePrice;
	}
	public String getSendScope() {
		return sendScope;
	}
	public void setSendScope(String sendScope) {
		this.sendScope = sendScope;
	}
	public String getHeadPhone() {
		return headPhone;
	}
	public void setHeadPhone(String headPhone) {
		this.headPhone = headPhone;
	}
	
	
}
