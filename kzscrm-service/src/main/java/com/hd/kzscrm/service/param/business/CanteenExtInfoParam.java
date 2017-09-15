package com.hd.kzscrm.service.param.business;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hd.kzscrm.common.param.PageParam;
/**
 * 商家信息扩展表
 * @author jyt 2017年3月7日 上午11:36:10
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CanteenExtInfoParam extends PageParam{
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 手机号
	 */
	private String mobilephone;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 基础信息表id
	 */
	private Long baseInfoId;
	/**
	 * 地理信息
	 */
	private Long areaCode;
	/**
	 * 商家logo
	 */
	private String logo;
	/**
	 * 负责人
	 */
	private String headRealname;
	/**
	 * 负责人身份证件
	 */
	private String headIdcard;
	/**
	 * 卫生许可证到期时间
	 */
	private Date healthEndTime;
	/**
	 * 负责人身份证图片
	 */
	private String headIdcardPic;
	/**
	 * 卫生许可证
	 */
	private String businessHealthPic;
	/**
	 * 营业执照
	 */
	private String businessPic;
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
	 * 配菜窗口数量
	 */
	private Integer windowNum;
	/**
	 * 入驻时间
	 */
	private Date enterTime;
	/**
	 * 是否对外开放 0 否 1 是
	 */
	private Integer openOutsideFlag;
	/**
	 * 是否允许外送 0否 1 是
	 */
	private Integer openSendFlag;
	/**
	 * 起送价
	 */
	private BigDecimal sendBasePrice;
	/**
	 * 配送范围
	 */
	private String sendScope;
	/**
	 * 负责人电话
	 */
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
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
}
