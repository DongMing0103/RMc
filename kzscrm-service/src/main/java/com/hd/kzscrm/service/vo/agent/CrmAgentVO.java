package com.hd.kzscrm.service.vo.agent;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.annotation.DataInject;
import com.hd.kzscrm.service.vo.BaseVO;

public class CrmAgentVO implements BaseVO {
	
	
	
   /**
    * 代理商编号
    */
   private String agentNo;
   
	
	/**
	 * 团队Id
	 */
   private Long teamId;
   /**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 *签约结束时间(结束)
	 */
	private Date cooperationEndTime;
   
   /**
     * 性别
   */
	private   String   sex ;
   
	/**
	 * 身份证照片
	 */
   private String headIdcard;
	/**
	 * 营业执照
	 */
	private String businesspic;
	private String weixin;

	// @AutoIncrease
	private Long id;

	/**
	 * 企业名称
	 */
	private String name;
	/**
	 * 区域的子编号
	 */
	private List<Long> agentAreaCodes;
	/**	
	 * crm_user的ID
	 */
	private Long userId;
	/**
	 * 下级分成
	 */
	private Integer juniorDivide;
	/**
	 * 业务员id
	 */
	private String businessName;
	/**
	 * 法定代表人
	 */
	private String principalName;

	/**
	 * 电话
	 */
	private String mobilephoe;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 岗位ID
	 */
	private Long positionId;


	/**
	 * 更新人ID
	 */
	private Long updateId;

	/**
	 * 创建人ID
	 */
	private Long createId;

	/**
	 * 父ID
	 */
	private Long parentId;
	/**
	 * 父辈ids(包括父辈的父辈,也包括本身的Id),用','隔开.
	 */
	private String parentIds;
	
	/**
	 * 顶级父ID
	 */
	private   Long   topParentId ;

	/**
	 * 区域CODE，2位是国家，4位是大区，5位是省，7位是市
	 */
	private Long areaCode;

	/**
	 * 区域名字
	 */
	@DataInject(DatabaseTableNameEnum.base_area)
	private String areaName;

	/**
	 * 代理商等级 目前至多支持三级
	 */
	private Integer level;
	private String  levelName;

	/**
	 * 性别
	 * 
	 * @Column(name="sex") private String sex ;
	 * 
	 * 
	 *                     /** 注册时间
	 */
	private java.util.Date registerTime;

	/**
	 * 头像
	 */
	private String headerImg;

	/**
	 * 最后登录时间
	 */
	private java.util.Date lastLogin;

	/**
	 * 邮箱
	 */
	private String mail;

	/**
	 * QQ号
	 */
	private Long qq;

	/**
	 * 认证状态	0.申请中，1.认证通过，2.认证失败,3.未申请
	 */
	private Integer authenticationStatus;

	/**
	 * 删除标识 1.存在0.删除
	 */
	private Integer delFlag;

	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	//签约时间
	private java.util.Date signTime;

	/**
	 * 代理性质 1.企业法人，2.非企业法人
	 */
	private Integer agentNature;
	private String  agentNatureName;

	/**
	 * 代理状态 0.失效，1.有效
	 */
	private Integer agentStatus;
	private String  agentStatusName;
	/**
	 * 业务员ID 业务员发展的代理商，1级代理商此值为空
	 */
	private Long businessId;
	/**
	 * 业务员团队
	 */
	private String businessTeamName;

	/**
	 * 签约状态 1.正常状态，2.解约状态
	 */
	private Integer signContractStatus;
	
	/**
	 * 身份证号码
	 * */
	private String identityCard ;

	/**
	 * 联系人姓名
	 * */
	private String contactRealname;

	/**
	 * 联系电话
	 * */
	private String contactPhone;
	//时间段
	private String timeSlot;
	
	
	
	
	
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getAgentNatureName() {
		return agentNatureName;
	}

	public void setAgentNatureName(String agentNatureName) {
		this.agentNatureName = agentNatureName;
	}

	public String getAgentStatusName() {
		return agentStatusName;
	}

	public void setAgentStatusName(String agentStatusName) {
		this.agentStatusName = agentStatusName;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeadIdcard() {
		return headIdcard;
	}

	public void setHeadIdcard(String headIdcard) {
		this.headIdcard = headIdcard;
	}

	public String getBusinesspic() {
		return businesspic;
	}

	public void setBusinesspic(String businesspic) {
		this.businesspic = businesspic;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Long getId() {
		return id;
	}

	public java.util.Date getSignTime() {
		return signTime;
	}

	public void setSignTime(java.util.Date signTime) {
		this.signTime = signTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	
	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}


	public String getMobilephoe() {
		return mobilephoe;
	}

	public void setMobilephoe(String mobilephoe) {
		this.mobilephoe = mobilephoe;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Long getParentId() {
		return parentId;
	}

	public Long getTopParentId() {
		return topParentId;
	}

	public void setTopParentId(Long topParentId) {
		this.topParentId = topParentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public java.util.Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	public java.util.Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(java.util.Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

	public Integer getAuthenticationStatus() {
		return authenticationStatus;
	}

	public void setAuthenticationStatus(Integer authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAgentNature() {
		return agentNature;
	}

	public void setAgentNature(Integer agentNature) {
		this.agentNature = agentNature;
	}

	public Integer getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Integer getSignContractStatus() {
		return signContractStatus;
	}

	public void setSignContractStatus(Integer signContractStatus) {
		this.signContractStatus = signContractStatus;
	}


	/**
	 * @return the juniorDivide
	 */
	public Integer getJuniorDivide() {
		return juniorDivide;
	}

	/**
	 * @param juniorDivide the juniorDivide to set
	 */
	public void setJuniorDivide(Integer juniorDivide) {
		this.juniorDivide = juniorDivide;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	/**
	 * @return the businessTeamName
	 */
	public String getBusinessTeamName() {
		return businessTeamName;
	}

	/**
	 * @param businessTeamName the businessTeamName to set
	 */
	public void setBusinessTeamName(String businessTeamName) {
		this.businessTeamName = businessTeamName;
	}

	/**
	 * @return the agentAreaCodes
	 */
	public List<Long> getAgentAreaCodes() {
		return agentAreaCodes;
	}

	/**
	 * @param agentAreaCodes the agentAreaCodes to set
	 */
	public void setAgentAreaCodes(List<Long> agentAreaCodes) {
		this.agentAreaCodes = agentAreaCodes;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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

	/**
	 * @return the cooperationEndTime
	 */
	public Date getCooperationEndTime() {
		return cooperationEndTime;
	}

	/**
	 * @param cooperationEndTime the cooperationEndTime to set
	 */
	public void setCooperationEndTime(Date cooperationEndTime) {
		this.cooperationEndTime = cooperationEndTime;
	}
}
