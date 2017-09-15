package com.hd.kzscrm.service.vo.user;

import java.util.Date;

import com.hd.kzscrm.dao.entity.user.CrmUserPO;

public class CrmUserVO extends CrmUserPO {
	  
    /**
      * 用户id
    */
    private   Long   id ;
    
    /**
      * 用户名
    */
	private   String   userName ;
    
    
    /**
      * 性别 0女 1男 2其他
    */
	private   Integer   sex ;
	/**
	 * 所属代理商ID
	 */
	private Long agentId;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
    
    
    /**
      * 密码
    */
	private   String   password ;
	/**
	 * 角色ID
	 */
	private Long roleId;
    
    
    /**
      * 消费代码
    */
	private   String   consumeCode ;
    
    
    /**
      * 注册时间
    */
	private   java.util.Date   regiserTime ;
    
    
    /**
      * 手机号码
    */
	private   String   mobilephone ;
    
    
    /**
      * 地区代码
    */
	private   Long   areaCode ;
    
    
    /**
      * 用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
    */
	private   Integer   userType ;
    
    
    /**
      * 状态：0待审核  1正常   2停用
    */
	private   Integer   userStatus ;
    
    
    /**
      * 删除标识（0 删除  1存在）
    */
	private   Integer   delFlag ;
    
    
    /**
      * NICK名
    */
	private   String   nickName ;
    
    
    /**
      * 帐号
    */
	private   String   account ;
    
    
    /**
      * 追加時間
    */
	private   Date   createTime ;
    
    
    /**
      * 追加ID
    */
	private   Long   createId ;
    
    
    /**
      * 更新ID
    */
	private   Long   updateId ;
    
    
    /**
      * 头像
    */
	private   String   headImg ;
    
    
    /**
      * 最后一次登录时间
    */
	private   Date   lastLogin ;
    
    
    /**
      * 是否接受短信，0接受，1拒绝
    */
	private   Integer   isReceiveMsg ;
    
    
    /**
      * メール
    */
	private   String   mail ;
    
    
    /**
      * QQ
    */
	private   String   qq ;
    
    
    /**
      * 住所
    */
	private   String   address ;
    
    
    /**
      * BIRTH日
    */
	private   Date   birthday ;
    
    
    /**
      * 编号
    */
	private   String   userNo ;
    
    
    /**
      * 认证状态 0、未认证   1、认证
    */
	private   Integer   authenticationStatus ;
    
    



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
