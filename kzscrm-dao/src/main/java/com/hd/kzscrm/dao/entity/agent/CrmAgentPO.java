package com.hd.kzscrm.dao.entity.agent;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmagent

@Entity
@Table(name="crm_agent")
public class CrmAgentPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        /**
    	 * 下级分成
    	 */
    	@Column(name="junior_divide")
    	private Integer juniorDivide;
    	
    	@Column(name="user_id")
        private Long userId;
    	
    	/**
    	 * 更新时间
    	 */
    	@Column(name="update_time")
    	private Date updateTime;
        /**
         * 代理商编号
         */
        @Column(name="agent_no")
        private String agentNo;
	    
		
		/**
		 * 团队Id
		 */
		@Column(name="team_id")
        private Long teamId;
		




		/**
          * 代理商姓名
        */
		@Column(name="name")
		private   String   name ;
		/**
		 * 负责人姓名
		 */
		@Column(name="principal_name")
	    private  String principalName;
        
        /**
          * 电话
        */
		@Column(name="mobilephoe")
		private   String   mobilephoe ;
	    
        
        /**
          * 地址
        */
		@Column(name="address")
		private   String   address ;
	    
        
        /**
          * 岗位ID
        */
		@Column(name="position_id")
		private   Long   positionId ;
	    
        
        /**
          * 更新人ID
        */
		@Column(name="update_id")
		private   Long   updateId ;
	    
        
        /**
          * 创建人ID
        */
		@Column(name="create_id")
		private   Long   createId ;
	    
        
        /**
          * 父ID
        */
		@Column(name="parent_id")
		private   Long   parentId ;
		
		/**
		 * 父辈ids(包括父辈的父辈,也包括本身的Id),用','隔开.
		 */
		@Column(name = "parent_ids")
		private String parentIds;
		/**
		 * 顶级父ID
		 */
		@Column(name="top_parent_id")
		private   Long   topParentId ;
	    
        
        /**
          * 区域CODE，2位是国家，4位是大区，5位是省，7位是市
        */
		@Column(name="area_code")
		private   Long   areaCode ;
	    
        
        /**
          * 代理商等级	目前至多支持三级
        */
		@Column(name="level")
		private   Integer   level ;
	    
        
        /**
          * 性别
        */
		@Column(name="sex")
		private   String   sex ;
	    
        
        /**
          * 注册时间
        */
		@Column(name="register_time")
		private   java.util.Date   registerTime ;
	    
        
        /**
          * 头像
        */
		@Column(name="header_img")
		private   String   headerImg ;
	    
        
        /**
          * 最后登录时间
        */
		@Column(name="last_login")
		private   java.util.Date   lastLogin ;
	    
        
        /**
          * 邮箱
        */
		@Column(name="mail")
		private   String   mail ;
	    
        
        /**
          * QQ号
        */
		@Column(name="qq")
		private   Long   qq ;
	    
        
        /**
          * 认证状态	0.申请中，1.认证通过，2.认证失败,3.未申请
        */
		@Column(name="authentication_status")
		private   Integer   authenticationStatus ;
	    
        
        /**
          * 删除标识	1.存在0.删除
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
        
        /**
          * 创建时间
        */
		@Column(name="create_time")
		private   java.util.Date   createTime ;
	    
		

        /**
          * 代理性质	1.企业法人，2.非企业法人
        */
		@Column(name="agent_nature")
		private   Integer   agentNature ;
	    
        
        /**
          * 代理状态	0.失效，1.有效
        */
		@Column(name="agent_status")
		private   Integer   agentStatus;
		/**
		 * 身份证照片
		 */
		@Column(name="head_idcard")
	    private String headIdcard;
		/**
		 * 营业执照
		 */
		@Column(name="business_pic")
		private String businesspic;
		@Column(name="weixin")
		private String weixin;
        
        /**
          * 业务员ID	业务员发展的代理商，1级代理商此值为空
        */
		@Column(name="business_id")
		private   Long   businessId ;
	    
        
        /**
          * 签约状态	1.正常状态，2.解约状态
        */
		@Column(name="sign_contract_status")
		private   Integer   signContractStatus ;
		
		/**
		 * 身份证号码
		 * */
		@Column(name="identity_card")
		private String identityCard ;
		
		/**
		 * 联系人姓名
		 * */
		@Column(name="contact_realname")
		private String contactRealname;

		/**
		 * 联系电话
		 * */
		@Column(name="contact_phone")
		private String contactPhone;
	    
    
     
        //默认空构造函数
	    public  CrmAgentPO(){
	
	    }
	
	
		//get set 方法
        /**
          * 主键
        */
        public void setId(Long id){
	      this.id=id;    
        }
         
	    /**
          * @return 主键
        */
        public Long getId(){ 
	      return this.id;    
        }


		public String getAgentNo() {
			return agentNo;
		}


		public void setAgentNo(String agentNo) {
			this.agentNo = agentNo;
		}




		
		public String getName() {
			return name;
		}

		
		public Long getTeamId() {
			return teamId;
		}
		
		
		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}

		public void setName(String name) {
			this.name = name;
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


		public void setParentId(Long parentId) {
			this.parentId = parentId;
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
}
