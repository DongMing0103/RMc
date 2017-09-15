package com.hd.kzscrm.service.param.business;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmBusinessParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	private List<Long> ids;
	/**
	 * 姓名
	 */
	private String name;
	private String nameLike;
	private Long businessId;
	private String businessIds;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 父团队
	 */
	private Long parentTeamId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	
	
	private String weixin;
	private String headIdcard;
	/**
	 * 电话
	 */
	private String mobilephone;
	/**
	 * 业务员类型，1.平台业务员，2.代理商业务员
	 */
	private Integer type;
	/**
	 * crm_usre的ID
	 */
	private Long userId;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 下级分成
	 */
	private Integer juniorDivide;
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
	 * 代理区域编号
	 */
	private Long agentAreaCode;
	/**
	 * 用户类型 1.业务员，2.业务员经理,3.代理商总监
	 */
	private Integer userType;
	/**
	 * 性别 1男 2女 3其他
	 */
	private Integer sex;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 头像
	 */
	private String headerImg;
	/**
	 * 最后登录时间
	 */
	private String lastLogin;
	/**
	 * 邮箱
	 */
	private String mail;
	/**
	 * QQ号
	 */
	private Long qq;
	/**
	 * 认证状态 0.申请中，1.已认证，2.认证通过，3.认证失败
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
	 * 在职状态 0.离职，1.在职
	 */
	private Integer jobStatus;
	/**
	 * 条件
	 */
	private Integer selectNum;
	/**
	 * 条件内容
	 */
	private String condition;
	
	/**
	 * 岗位名称
	 */
	private String positionName;
	
	/**
	 * 团队id
	 */
	private Long teamId;
	private List<Long> teamIds;
	private String teamIdsStr;
	/**
	 * 所属代理商ID,代理商ID,如果是外部业务员，此值不为空
	 */
	private Long agentId;
	/**
	 * 代理商集合
	 */
	private List<Long> agentIds;
	
	/**
	 * 密码
	 */
	private String password;
	//入职时间
	private Date workTime;

	/**
	 * 是否查询一级  1是    0或空否
	 * */
	private Integer isHighest;
	
	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public List<Long> getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(List<Long> teamIds) {
		this.teamIds = teamIds;
	}

	
	public String getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(String businessIds) {
		this.businessIds = businessIds;
	}

	
	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getHeadIdcard() {
		return headIdcard;
	}

	public void setHeadIdcard(String headIdcard) {
		this.headIdcard = headIdcard;
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

	
	public Integer getSelectNum() {
		return selectNum;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public void setSelectNum(Integer selectNum) {
		this.selectNum = selectNum;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamIdsStr() {
		return teamIdsStr;
	}

	public void setTeamIdsStr(String teamIdsStr) {
		this.teamIdsStr = teamIdsStr;
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
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	/**
	 * @return 电话
	 */
	public String getMobilephone() {
		return this.mobilephone;
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
	 * 部门ID
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return 部门ID
	 */
	public Long getDepartmentId() {
		return this.departmentId;
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

	/**
	 * 代理区域编号
	 */
	public void setAgentAreaCode(Long agentAreaCode) {
		this.agentAreaCode = agentAreaCode;
	}

	/**
	 * @return 代理区域编号
	 */
	public Long getAgentAreaCode() {
		return this.agentAreaCode;
	}

	/**
	 * 用户类型 1.业务员，2.业务员经理,3.代理商总监
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return 用户类型 1.业务员，2.业务员经理,3.代理商总监
	 */
	public Integer getUserType() {
		return this.userType;
	}

	/**
	 * 性别 1男 2女 3其他
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return 性别 1男 2女 3其他
	 */
	public Integer getSex() {
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
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return 最后登录时间
	 */
	public String getLastLogin() {
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
	 * 在职状态 0.离职，1.在职
	 */
	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * @return 在职状态 0.离职，1.在职
	 */
	public Integer getJobStatus() {
		return this.jobStatus;
	}

	/**
	 * 所属代理商ID
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return 所属代理商ID
	 */
	public Long getAgentId() {
		return this.agentId;
	}

	public List<Long> getAgentIds() {
		return agentIds;
	}

	public void setAgentIds(List<Long> agentIds) {
		this.agentIds = agentIds;
	}

	/**
	 * 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return 密码
	 */
	public String getPassword() {
		return this.password;
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

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
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

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the parentTeamId
	 */
	public Long getParentTeamId() {
		return parentTeamId;
	}

	/**
	 * @param parentTeamId the parentTeamId to set
	 */
	public void setParentTeamId(Long parentTeamId) {
		this.parentTeamId = parentTeamId;
	}

	public Integer getIsHighest() {
		return isHighest;
	}

	public void setIsHighest(Integer isHighest) {
		this.isHighest = isHighest;
	}
}
