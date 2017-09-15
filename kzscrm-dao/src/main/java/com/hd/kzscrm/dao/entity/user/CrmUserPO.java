package com.hd.kzscrm.dao.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmuser

@Entity
@Table(name="crm_user")
public class CrmUserPO implements Serializable {
   
        
        /**
          * 用户id
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 用户名
        */
		@Column(name="user_name")
		private   String   userName ;
		/**
		 * 更新时间
		 */
		@Column(name="update_time")
		private Date updateTime;
	    
        
        /**
          * 性别 0女 1男 2其他
        */
		@Column(name="sex")
		private   Integer   sex ;
	    
        
        /**
          * 密码
        */
		@Column(name="password")
		private   String   password ;
	    
        
        /**
          * 消费代码
        */
		@Column(name="consume_code")
		private   String   consumeCode ;
	    
        
        /**
          * 注册时间
        */
		@Column(name="regiser_time")
		private   java.util.Date   regiserTime ;
	    
        
        /**
          * 手机号码
        */
		@Column(name="mobilephone")
		private   String   mobilephone ;
	    
        
        /**
          * 地区代码
        */
		@Column(name="area_code")
		private   Long   areaCode ;
	    
        
        /**
          * 用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
        */
		@Column(name="user_type")
		private   Integer   userType ;
	    
        
        /**
          * 状态：0待审核  1正常   2停用
        */
		@Column(name="user_status")
		private   Integer   userStatus ;
	    
        
        /**
          * 删除标识（0 删除  1存在）
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
        
        /**
          * NICK名
        */
		@Column(name="nick_name")
		private   String   nickName ;
	    
        
        /**
          * 帐号
        */
		@Column(name="account")
		private   String   account ;
	    
        
        /**
          * 追加時間
        */
		@Column(name="create_time")
		private   java.util.Date   createTime ;
	    
        
        /**
          * 追加ID
        */
		@Column(name="create_id")
		private   Long   createId ;
	    
        
        /**
          * 更新ID
        */
		@Column(name="update_id")
		private   Long   updateId ;
	    
        
        /**
          * 头像
        */
		@Column(name="head_img")
		private   String   headImg ;
	    
        
        /**
          * 最后一次登录时间
        */
		@Column(name="last_login")
		private   java.util.Date   lastLogin ;
	    
        
        /**
          * 是否接受短信，0接受，1拒绝
        */
		@Column(name="is_receive_msg")
		private   Integer   isReceiveMsg ;
	    
        
        /**
          * メール
        */
		@Column(name="mail")
		private   String   mail ;
	    
        
        /**
          * QQ
        */
		@Column(name="qq")
		private   String   qq ;
	    
        
        /**
          * 住所
        */
		@Column(name="address")
		private   String   address ;
	    
        
        /**
          * BIRTH日
        */
		@Column(name="birthday")
		private   java.util.Date   birthday ;
	    
        
        /**
          * 编号
        */
		@Column(name="user_no")
		private   String   userNo ;
	    
        
        /**
          * 认证状态 0、未认证   1、认证
        */
		@Column(name="authentication_status")
		private   Integer   authenticationStatus ;
		
		@Column(name="type")
	    private Integer type;
		/**
		 * 所属代理商ID
		 */
		@Column(name="agent_id")
		private Long agentId;
        
    
     
        //默认空构造函数
	    public  CrmUserPO(){
	
	    }
	
	
		//get set 方法
        /**
          * 用户id
        */
        public void setId(Long id){
	      this.id=id;    
        }
         
	    /**
          * @return 用户id
        */
        public Long getId(){ 
	      return this.id;    
        } 
        /**
          * 用户名
        */
        public void setUserName(String userName){
	      this.userName=userName;    
        }
         
	    public Integer getType() {
			return type;
		}


		public void setType(Integer type) {
			this.type = type;
		}


		/**
          * @return 用户名
        */
        public String getUserName(){ 
	      return this.userName;    
        } 
        /**
          * 性别 0女 1男 2其他
        */
        public void setSex(Integer sex){
	      this.sex=sex;    
        }
         
	    /**
          * @return 性别 0女 1男 2其他
        */
        public Integer getSex(){ 
	      return this.sex;    
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
          * 消费代码
        */
        public void setConsumeCode(String consumeCode){
	      this.consumeCode=consumeCode;    
        }
         
	    /**
          * @return 消费代码
        */
        public String getConsumeCode(){ 
	      return this.consumeCode;    
        } 
        /**
          * 注册时间
        */
        public void setRegiserTime(java.util.Date regiserTime){
	      this.regiserTime=regiserTime;    
        }
         
	    /**
          * @return 注册时间
        */
        public java.util.Date getRegiserTime(){ 
	      return this.regiserTime;    
        } 
        /**
          * 手机号码
        */
        public void setMobilephone(String mobilephone){
	      this.mobilephone=mobilephone;    
        }
         
	    /**
          * @return 手机号码
        */
        public String getMobilephone(){ 
	      return this.mobilephone;    
        } 
        /**
          * 地区代码
        */
        public void setAreaCode(Long areaCode){
	      this.areaCode=areaCode;    
        }
         
	    /**
          * @return 地区代码
        */
        public Long getAreaCode(){ 
	      return this.areaCode;    
        } 
        /**
          * 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
        */
        public void setUserType(Integer userType){
	      this.userType=userType;    
        }
         
	    /**
          * @return 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
        */
        public Integer getUserType(){ 
	      return this.userType;    
        } 
        /**
          * 状态：0待审核  1正常   2停用
        */
        public void setUserStatus(Integer userStatus){
	      this.userStatus=userStatus;    
        }
         
	    /**
          * @return 状态：0待审核  1正常   2停用
        */
        public Integer getUserStatus(){ 
	      return this.userStatus;    
        } 
        /**
          * 删除标识（0 删除  1存在）
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 删除标识（0 删除  1存在）
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
        } 
        /**
          * NICK名
        */
        public void setNickName(String nickName){
	      this.nickName=nickName;    
        }
         
	    /**
          * @return NICK名
        */
        public String getNickName(){ 
	      return this.nickName;    
        } 
        /**
          * 帐号
        */
        public void setAccount(String account){
	      this.account=account;    
        }
         
	    /**
          * @return 帐号
        */
        public String getAccount(){ 
	      return this.account;    
        } 
        /**
          * 追加時間
        */
        public void setCreateTime(java.util.Date createTime){
	      this.createTime=createTime;    
        }
         
	    /**
          * @return 追加時間
        */
        public java.util.Date getCreateTime(){ 
	      return this.createTime;    
        } 
        /**
          * 追加ID
        */
        public void setCreateId(Long createId){
	      this.createId=createId;    
        }
         
	    /**
          * @return 追加ID
        */
        public Long getCreateId(){ 
	      return this.createId;    
        } 
        /**
          * 更新ID
        */
        public void setUpdateId(Long updateId){
	      this.updateId=updateId;    
        }
         
	    /**
          * @return 更新ID
        */
        public Long getUpdateId(){ 
	      return this.updateId;    
        } 
        /**
          * 头像
        */
        public void setHeadImg(String headImg){
	      this.headImg=headImg;    
        }
         
	    /**
          * @return 头像
        */
        public String getHeadImg(){ 
	      return this.headImg;    
        } 
        /**
          * 最后一次登录时间
        */
        public void setLastLogin(java.util.Date lastLogin){
	      this.lastLogin=lastLogin;    
        }
         
	    /**
          * @return 最后一次登录时间
        */
        public java.util.Date getLastLogin(){ 
	      return this.lastLogin;    
        } 
        /**
          * 是否接受短信，0接受，1拒绝
        */
        public void setIsReceiveMsg(Integer isReceiveMsg){
	      this.isReceiveMsg=isReceiveMsg;    
        }
         
	    /**
          * @return 是否接受短信，0接受，1拒绝
        */
        public Integer getIsReceiveMsg(){ 
	      return this.isReceiveMsg;    
        } 
        /**
          * メール
        */
        public void setMail(String mail){
	      this.mail=mail;    
        }
         
	    /**
          * @return メール
        */
        public String getMail(){ 
	      return this.mail;    
        } 
        /**
          * QQ
        */
        public void setQq(String qq){
	      this.qq=qq;    
        }
         
	    /**
          * @return QQ
        */
        public String getQq(){ 
	      return this.qq;    
        } 
        /**
          * 住所
        */
        public void setAddress(String address){
	      this.address=address;    
        }
         
	    /**
          * @return 住所
        */
        public String getAddress(){ 
	      return this.address;    
        } 
        /**
          * BIRTH日
        */
        public void setBirthday(java.util.Date birthday){
	      this.birthday=birthday;    
        }
         
	    /**
          * @return BIRTH日
        */
        public java.util.Date getBirthday(){ 
	      return this.birthday;    
        } 
        /**
          * 编号
        */
        public void setUserNo(String userNo){
	      this.userNo=userNo;    
        }
         
	    /**
          * @return 编号
        */
        public String getUserNo(){ 
	      return this.userNo;    
        } 
        /**
          * 认证状态 0、未认证   1、认证
        */
        public void setAuthenticationStatus(Integer authenticationStatus){
	      this.authenticationStatus=authenticationStatus;    
        }
         
	    /**
          * @return 认证状态 0、未认证   1、认证
        */
        public Integer getAuthenticationStatus(){ 
	      return this.authenticationStatus;    
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
     
}
