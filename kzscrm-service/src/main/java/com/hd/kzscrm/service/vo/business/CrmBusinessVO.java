package com.hd.kzscrm.service.vo.business;

import java.math.BigDecimal;
import java.util.Date;

public class CrmBusinessVO {
    
    /**
      * 主键
    */
    private   Long   id ;
    
    /**
      * 姓名
    */
	private   String   name ;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 身份证
	 */
	private String headIdCard;
	/**
	 * crm_user的ID
	 */
	private Long userId;
	/**
	 * 业务员类型，1.内部业务员，2.外部业务员
	 */
	private Integer type;
    
    /**
      * 电话
    */
	private   String   mobilephone ;
	/**
     * 所属团队id
     */
    private Long teamId;
	/**
	 * 下级分成
	 */
	private Integer juniorDivide;
	
	/**
	 * 在职人数
	 */
	private Integer onJobNum;
	
	/**
	 * 离职人数
	 */
	private Integer quitJobNum;
    
    /**
      * 地址
    */
	private   String   address ;
    
    
    /**
      * 部门ID
    */
	private   Long   departmentId ;
    
    
    /**
      * 岗位ID
    */
	private   Long   positionId ;
    
    
    /**
      * 更新人ID
    */
	private   Long   updateId ;
    
    
    /**
      * 创建人ID
    */
	private   Long   createId ;
    
    
    /**
      * 父ID
    */
	private   Long   parentId ;
    
    
    /**
      * 代理区域编号
    */
	private   Long   agentAreaCode ;
    
    
    /**
      * 用户类型	1.业务员，2.业务员经理,3.代理商总监
    */
	private   Integer   userType ;
    
    
    /**
      * 性别 1男 2女 3其他
    */
	private   Integer   sex ;
 
    /**
      * 注册时间
    */
	private   java.util.Date   registerTime ;
    


	/**
      * 头像
    */
	private   String   headerImg ;
    
    
    /**
      * 最后登录时间
    */
	private   java.util.Date   lastLogin ;
    
    
    /**
      * 邮箱
    */
	private   String   mail ;
    
    
    /**
      * QQ号
    */
	private   Long   qq ;
    
	private String weixin;
    
    /**
      * 认证状态	0.申请中，1.已认证，2.认证通过，3.认证失败
    */
	private   Integer   authenticationStatus ;
    
    
    /**
      * 删除标识	1.存在0.删除
    */
	private   Integer   delFlag ;
    
    
    /**
      * 创建时间
    */
	private   java.util.Date   createTime ;
    
    
    /**
      * 在职状态	0.离职，1.在职
    */
	private   Integer   jobStatus ;
	private   String jobStatusName;
    
    
    /**
      * 所属代理商ID,代理商ID,如果是外部业务员，此值不为空
    */
	private   Long   agentId ;
    
    
    /**
      * 密码
    */
	private   String   password ;

	/**
	 * 岗位职务名称
	 */
	private String positionName;
	/**
	 * 团队名称
	 */
	private String teamName;
	/**
	 * 团队信息
	 */
	private CrmTeamVO crmTeamVO;
	/**
	 * 入职时间
	 */
	private java.util.Date workTime;
	
	/**
	 * 统计正式客户数量 
	 * @return
	 */
	private BigDecimal clientNatureNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getJobStatusName() {
		return jobStatusName;
	}

	public void setJobStatusName(String jobStatusName) {
		this.jobStatusName = jobStatusName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getAgentAreaCode() {
		return agentAreaCode;
	}

	public void setAgentAreaCode(Long agentAreaCode) {
		this.agentAreaCode = agentAreaCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
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

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	public java.util.Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(java.util.Date workTime) {
		this.workTime = workTime;
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

	public Integer getOnJobNum() {
		return onJobNum;
	}

	public void setOnJobNum(Integer onJobNum) {
		this.onJobNum = onJobNum;
	}

	public Integer getQuitJobNum() {
		return quitJobNum;
	}

	public void setQuitJobNum(Integer quitJobNum) {
		this.quitJobNum = quitJobNum;
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

	/**
	 * @return the crmTeamVO
	 */
	public CrmTeamVO getCrmTeamVO() {
		return crmTeamVO;
	}

	/**
	 * @return the teamId
	 */
	public Long getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	/**
	 * @param crmTeamVO the crmTeamVO to set
	 */
	public void setCrmTeamVO(CrmTeamVO crmTeamVO) {
		this.crmTeamVO = crmTeamVO;
	}

	public BigDecimal getClientNatureNum() {
		return clientNatureNum;
	}

	public void setClientNatureNum(BigDecimal clientNatureNum) {
		this.clientNatureNum = clientNatureNum;
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
	 * @return the headIdCard
	 */
	public String getHeadIdCard() {
		return headIdCard;
	}

	/**
	 * @param headIdCard the headIdCard to set
	 */
	public void setHeadIdCard(String headIdCard) {
		this.headIdCard = headIdCard;
	}
	
}
