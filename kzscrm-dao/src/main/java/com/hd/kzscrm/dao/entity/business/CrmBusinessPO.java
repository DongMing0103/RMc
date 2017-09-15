package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmbusiness

@Entity
@Table(name="crm_business")
public class CrmBusinessPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
    	 * 更新时间
    	 */
        @Column(name="update_time")
    	private Date updateTime;
        /**
    	 * 下级分成
    	 */
    	@Column(name="junior_divide")
    	private Integer juniorDivide;
    	
    	@Column(name="level")
    	private Integer level;
        
        /**
         * 所属团队id
         */
        @Column(name="team_id")
        private Long teamId;
        /**
         * crm_user的ID
         */
        @Column(name="user_id")
        private Long userId;
        /**
          * 姓名
        */
		@Column(name="name")
		private   String   name ;
	    
        
        /**
          * 电话
        */
		@Column(name="mobilephone")
		private   String   mobilephone ;
	    /**
	     * 代理商ID,如果是外部业务员，此值不为空
	     */
		@Column(name="agent_id")
		private Long agentId;
		/**
		 * 业务员类型，1.内部业务员，2.外部业务员
		 */
		@Column(name="type")
		private Integer type;
        
        /**
          * 地址
        */
		@Column(name="address")
		private   String   address ;
	    
        
        /**
          * 部门ID
        */
		@Column(name="department_id")
		private   Long   departmentId ;
	    
        
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
          * 代理区域编号
        */
		@Column(name="agent_area_code")
		private   Long   agentAreaCode ;
	    
        
        /**
          * 用户类型	1.业务员，2.业务员经理,3.代理商总监
        */
		@Column(name="user_type")
		private   Integer   userType ;
	    
        
        /**
          * 性别 1男 2女 3其他
        */
		@Column(name="sex")
		private   Integer   sex ;
	 
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
	    
		@Column(name="weixin")
		private String weixin;
        
        /**
          * 认证状态	0.申请中，1.已认证，2.认证通过，3.认证失败
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
          * 在职状态	0.离职，1.在职
        */
		@Column(name="job_status")
		private   Integer   jobStatus ;
	    
        
        /**
          * 密码
        */
		@Column(name="password")
		private   String   password ;
	    /**
	     * 身份证
	     */
		@Column(name="head_id_card")
        private String headIdCard;
        
        /**
          * 工作时间
        */
		@Column(name="work_time")
		private   Date   workTime ;
	    
        //默认空构造函数
	    public  CrmBusinessPO(){
	
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
        
        
        public Long getTeamId() {
			return teamId;
		}


		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}


		/**
          * 姓名
        */
        public void setName(String name){
	      this.name=name;    
        }
         
	    /**
          * @return 姓名
        */
        public String getName(){ 
	      return this.name;    
        } 
        /**
          * 电话
        */
        public void setMobilephone(String mobilephone){
	      this.mobilephone=mobilephone;    
        }
         
	    /**
          * @return 电话
        */
        public String getMobilephone(){ 
	      return this.mobilephone;    
        } 
        
        public String getWeixin() {
			return weixin;
		}


		public void setWeixin(String weixin) {
			this.weixin = weixin;
		}

		
		public String getHeadIdCard() {
			return headIdCard;
		}


		public void setHeadIdCard(String headIdCard) {
			this.headIdCard = headIdCard;
		}


		/**
          * 地址
        */
        public void setAddress(String address){
	      this.address=address;    
        }
         
	    /**
          * @return 地址
        */
        public String getAddress(){ 
	      return this.address;    
        } 
        /**
          * 部门ID
        */
        public void setDepartmentId(Long departmentId){
	      this.departmentId=departmentId;    
        }
         
	    /**
          * @return 部门ID
        */
        public Long getDepartmentId(){ 
	      return this.departmentId;    
        } 
        /**
          * 岗位ID
        */
        public void setPositionId(Long positionId){
	      this.positionId=positionId;    
        }
         
	    /**
          * @return 岗位ID
        */
        public Long getPositionId(){ 
	      return this.positionId;    
        } 
        /**
          * 更新人ID
        */
        public void setUpdateId(Long updateId){
	      this.updateId=updateId;    
        }
         
	    /**
          * @return 更新人ID
        */
        public Long getUpdateId(){ 
	      return this.updateId;    
        } 
        /**
          * 创建人ID
        */
        public void setCreateId(Long createId){
	      this.createId=createId;    
        }
         
	    /**
          * @return 创建人ID
        */
        public Long getCreateId(){ 
	      return this.createId;    
        } 
        /**
          * 父ID
        */
        public void setParentId(Long parentId){
	      this.parentId=parentId;    
        }
         
	    /**
          * @return 父ID
        */
        public Long getParentId(){ 
	      return this.parentId;    
        } 
        /**
          * 代理区域编号
        */
        public void setAgentAreaCode(Long agentAreaCode){
	      this.agentAreaCode=agentAreaCode;    
        }
         
	    /**
          * @return 代理区域编号
        */
        public Long getAgentAreaCode(){ 
	      return this.agentAreaCode;    
        } 
        /**
          * 用户类型	1.业务员，2.业务员经理,3.代理商总监
        */
        public void setUserType(Integer userType){
	      this.userType=userType;    
        }
         
	    /**
          * @return 用户类型	1.业务员，2.业务员经理,3.代理商总监
        */
        public Integer getUserType(){ 
	      return this.userType;    
        } 
        /**
          * 性别 1男 2女 3其他
        */
        public void setSex(Integer sex){
	      this.sex=sex;    
        }
         
	    /**
          * @return 性别 1男 2女 3其他
        */
        public Integer getSex(){ 
	      return this.sex;    
        } 
        /**
          * 注册时间
        */
        public void setRegisterTime(java.util.Date registerTime){
	      this.registerTime=registerTime;    
        }
         
	    /**
          * @return 注册时间
        */
        public java.util.Date getRegisterTime(){ 
	      return this.registerTime;    
        } 
        /**
          * 头像
        */
        public void setHeaderImg(String headerImg){
	      this.headerImg=headerImg;    
        }
         
	    /**
          * @return 头像
        */
        public String getHeaderImg(){ 
	      return this.headerImg;    
        } 
        /**
          * 最后登录时间
        */
        public void setLastLogin(java.util.Date lastLogin){
	      this.lastLogin=lastLogin;    
        }
         
	    /**
          * @return 最后登录时间
        */
        public java.util.Date getLastLogin(){ 
	      return this.lastLogin;    
        } 
        /**
          * 邮箱
        */
        public void setMail(String mail){
	      this.mail=mail;    
        }
         
	    /**
          * @return 邮箱
        */
        public String getMail(){ 
	      return this.mail;    
        } 
        /**
          * QQ号
        */
        public void setQq(Long qq){
	      this.qq=qq;    
        }
         
	    /**
          * @return QQ号
        */
        public Long getQq(){ 
	      return this.qq;    
        } 
        /**
          * 认证状态	0.申请中，1.已认证，2.认证通过，3.认证失败
        */
        public void setAuthenticationStatus(Integer authenticationStatus){
	      this.authenticationStatus=authenticationStatus;    
        }
         
	    /**
          * @return 认证状态	0.申请中，1.已认证，2.认证通过，3.认证失败
        */
        public Integer getAuthenticationStatus(){ 
	      return this.authenticationStatus;    
        } 
        /**
          * 删除标识	1.存在0.删除
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 删除标识	1.存在0.删除
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
        } 
        /**
          * 创建时间
        */
        public void setCreateTime(java.util.Date createTime){
	      this.createTime=createTime;    
        }
         
	    /**
          * @return 创建时间
        */
        public java.util.Date getCreateTime(){ 
	      return this.createTime;    
        } 
        /**
          * 在职状态	0.离职，1.在职
        */
        public void setJobStatus(Integer jobStatus){
	      this.jobStatus=jobStatus;    
        }
         
	    /**
          * @return 在职状态	0.离职，1.在职
        */
        public Integer getJobStatus(){ 
	      return this.jobStatus;    
        } 
        /**
          * 密码
        */
        public void setPassword(String password){
	      this.password=password;    
        }
         
	    /**
          * @return 密码
        */
        public String getPassword(){ 
	      return this.password;    
        }


        /**
          * 工作时间
        */
		public java.util.Date getWorkTime() {
			return workTime;
		}


	    /**
          * @return 工作时间
        */
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


		/**
		 * @return the level
		 */
		public Integer getLevel() {
			return level;
		}


		/**
		 * @param level the level to set
		 */
		public void setLevel(Integer level) {
			this.level = level;
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
		 * @return the agentId
		 */
		public Long getAgentId() {
			return agentId;
		}


		/**
		 * @param agentId the agentId to set
		 */
		public void setAgentId(Long agentId) {
			this.agentId = agentId;
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
     
}
