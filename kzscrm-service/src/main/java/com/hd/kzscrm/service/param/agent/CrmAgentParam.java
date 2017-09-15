package com.hd.kzscrm.service.param.agent;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;

public class CrmAgentParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	private List<Long> ids;
	//选中记录的id
	private String agentIds;
	/**
	 * 更新类型，1.单条更新，2.批量更新
	 */
	private Integer updateType;
	private Long agentId;
	private Long clientId;
	private String areaIds;
	private String businessName;
	private String workMonth;//当月
	private List<CrmPermSourcesVO> cSourcesVOs;
	
	/**
	 * 代理商名称
	 */
	private String name;
	private String areaName;
	/**
	 * 姓名模糊查询
	 */
	private String nameLike;
	/**
	 * crm_user的ID
	 */
	private Long userId;
	private String userName;
	/**
	 * 下级分成
	 */
	private Integer juniorDivide;
	/**
	 * 负责人姓名
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
	private List<Integer>  areaCodes;
	/**
	 * 代理商等级 目前至多支持三级
	 */
	private Integer level;
	/**
	 * 代理商等级起始值
	 */
	private Integer startLevel;
	/**
	 * 代理商等级结束值
	 */
	private Integer endLevel;
	
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 头像
	 */
	private String headerImg;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 最后登录时间
	 */
	private Date lastLogin;
	/**
	 * 邮箱
	 */
	private String mail;
	/**
	 * QQ号
	 */
	private Long qq;
	/**
	 * 身份证照片
	 */
    private String headIdcard;
	/**
	 * 营业执照
	 */
    private String businesspic;
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
	private Date createTime;
	/**
	 * 代理性质 1.企业法人，2.非企业法人
	 */
	private Integer agentNature;
	/**
	 * 代理状态 0.失效，1.有效
	 */
	private Integer agentStatus;
	/**
	 * 业务员ID 业务员发展的代理商，1级代理商此值为空
	 */
	private Long businessId;
	private List<Long> businessIds;
	/**
	 * 签约状态 1.正常状态，2.解约状态
	 */
	private Integer signContractStatus;
	/**
	 * 查询条件
	 */
	private String condition;
	private Integer selectNum;
	
	/**
	 * _(工作月)用于根据工作月起止时间查询注册时间
	 */
	private Date applyMonth;
	/**
	 * 省代码
	 */
	private Integer provCode;
	/**
	 * 市代码
	 */
	private Integer cityCode;
	
	/**
	 * 团队id
	 */
	private Long teamId;
	private String teamIds;
	
	private String weixin;
	/**
	 * 用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
	 */
	private Integer userType;
	
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
	
	public String getWorkMonth() {
		return workMonth;
	}

	public void setWorkMonth(String workMonth) {
		this.workMonth = workMonth;
	}

	public List<CrmPermSourcesVO> getcSourcesVOs() {
		return cSourcesVOs;
	}

	public void setcSourcesVOs(List<CrmPermSourcesVO> cSourcesVOs) {
		this.cSourcesVOs = cSourcesVOs;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(String agentIds) {
		this.agentIds = agentIds;
	}

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	public Date getApplyMonth() {
		return applyMonth;
	}

	public void setApplyMonth(Date applyMonth) {
		this.applyMonth = applyMonth;
	}

	
	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public List<Integer> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(List<Integer> areaCodes) {
		this.areaCodes = areaCodes;
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
	 * 姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	
	public Integer getSelectNum() {
		return selectNum;
	}

	public void setSelectNum(Integer selectNum) {
		this.selectNum = selectNum;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}


	/**
	 * @return 姓名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 电话
	 */
	public void setMobilephoe(String mobilephoe) {
		this.mobilephoe = mobilephoe;
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

	/**
	 * @return 电话
	 */
	public String getMobilephoe() {
		return this.mobilephoe;
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
	 * 岗位ID
	 */
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return 岗位ID
	 */
	public Long getPositionId() {
		return this.positionId;
	}

	/**
	 * 更新人ID
	 */
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	/**
	 * @return 更新人ID
	 */
	public Long getUpdateId() {
		return this.updateId;
	}

	/**
	 * 创建人ID
	 */
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	/**
	 * @return 创建人ID
	 */
	public Long getCreateId() {
		return this.createId;
	}

	/**
	 * 父ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return 父ID
	 */
	public Long getParentId() {
		return this.parentId;
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

	/**
	 * 区域CODE
	 */
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return 区域CODE
	 */
	public Long getAreaCode() {
		return this.areaCode;
	}

	/**
	 * 代理商等级 目前至多支持三级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return 代理商等级 目前至多支持三级
	 */
	public Integer getLevel() {
		return this.level;
	}

	/**
	 * 性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return 性别
	 */
	public String getSex() {
		return this.sex;
	}

	/**
	 * 注册时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * @return 注册时间
	 */
	public Date getRegisterTime() {
		return this.registerTime;
	}

	/**
	 * 头像
	 */
	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	/**
	 * @return 头像
	 */
	public String getHeaderImg() {
		return this.headerImg;
	}

	/**
	 * 最后登录时间
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return 最后登录时间
	 */
	public Date getLastLogin() {
		return this.lastLogin;
	}

	/**
	 * 邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return 邮箱
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * QQ号
	 */
	public void setQq(Long qq) {
		this.qq = qq;
	}

	/**
	 * @return QQ号
	 */
	public Long getQq() {
		return this.qq;
	}

	/**
	 * 认证状态 0.申请中，1.已认证，2.认证通过，3.认证失败
	 */
	public void setAuthenticationStatus(Integer authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}

	/**
	 * @return 认证状态 0.申请中，1.已认证，2.认证通过，3.认证失败
	 */
	public Integer getAuthenticationStatus() {
		return this.authenticationStatus;
	}

	/**
	 * 删除标识 1.存在0.删除
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 删除标识 1.存在0.删除
	 */
	public Integer getDelFlag() {
		return this.delFlag;
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
	 * 代理性质 1.企业法人，2.非企业法人
	 */
	public void setAgentNature(Integer agentNature) {
		this.agentNature = agentNature;
	}

	/**
	 * @return 代理性质 1.企业法人，2.非企业法人
	 */
	public Integer getAgentNature() {
		return this.agentNature;
	}

	/**
	 * 代理状态 0.失效，1.有效
	 */
	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
	}

	/**
	 * @return 代理状态 0.失效，1.有效
	 */
	public Integer getAgentStatus() {
		return this.agentStatus;
	}

	/**
	 * 业务员ID 业务员发展的代理商，1级代理商此值为空
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return 业务员ID 业务员发展的代理商，1级代理商此值为空
	 */
	public Long getBusinessId() {
		return this.businessId;
	}

	/**
	 * 签约状态 1.正常状态，2.解约状态
	 */
	public void setSignContractStatus(Integer signContractStatus) {
		this.signContractStatus = signContractStatus;
	}

	/**
	 * @return 签约状态 1.正常状态，2.解约状态
	 */
	public Integer getSignContractStatus() {
		return this.signContractStatus;
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

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the startLevel
	 */
	public Integer getStartLevel() {
		return startLevel;
	}

	/**
	 * @param startLevel the startLevel to set
	 */
	public void setStartLevel(Integer startLevel) {
		this.startLevel = startLevel;
	}

	/**
	 * @return the endLevel
	 */
	public Integer getEndLevel() {
		return endLevel;
	}

	/**
	 * @param endLevel the endLevel to set
	 */
	public void setEndLevel(Integer endLevel) {
		this.endLevel = endLevel;
	}

	/**
	 * @return the updateType
	 */
	public Integer getUpdateType() {
		return updateType;
	}

	/**
	 * @param updateType the updateType to set
	 */
	public void setUpdateType(Integer updateType) {
		this.updateType = updateType;
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public String getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(String teamIds) {
		this.teamIds = teamIds;
	}
	
}
