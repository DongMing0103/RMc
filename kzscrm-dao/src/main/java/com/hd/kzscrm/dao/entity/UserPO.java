/**
 * 
 */
package com.hd.kzscrm.dao.entity;

import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 用户表
 * @author 黄霄仪
 * @date 2017年3月6日 上午11:18:06
 * 
 */
@Entity
@Table(name="user")
public class UserPO {
	/**
	 * 主键
	 */
	@Column(name="id")
	@Id
	private Long id;
	
	/**
	 * 用户名
	 */
	@Column(name="user_name")
	private String userName;
	/**
	 * 密码
	 */
	@Column(name="password")
	private String password;
	/**
	 * 消费代码
	 */
	@Column(name="consume_code")
	private String consumeCode;
	/**
	 * 注册时间
	 */
	@Column(name="regiser_time")
	private Date regiserTime;
	/**
	 * 手机
	 */
	@Column(name="mobilephone")
	private String mobilephone;
	/**
	 * 地区代码
	 */
	@Column(name="area_code")
	private Integer areaCode;
	/**
	 * 用户类型 1 企业  2商家  3个人
	 */
	@Column(name="user_type")
	private Integer userType;
	/**
	 * 用户状态 1 正常  2 冻结
	 */
	@Column(name="user_status")
	private Integer userStatus;
	
	/**
	 * 用户认证状态：0 未认证  1 认证
	 */
	@Column(name="authentication_status")
	private Integer authenticationStatus;
	
	/**
	 * 删除标识（0 删除  1存在）
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	/**
	 * 昵称
	 */
	@Column(name="nick_name")
	private String nickName;
	/**
	 * 帐号
	 */
	@Column(name="account")
	private String account;
	/**
	 * 创建日期
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 创建人ID
	 */
	@Column(name="create_id")
	private Long createId;
	/**
	 * 更新人ID
	 */
	@Column(name="update_id")
	private Long updateId;
	/**
	 * 头像地址
	 */
	@Column(name="head_img")
	private String headImg;
	/**
	 * 最后一次登录时间
	 */
	@Column(name="last_login")
	private Date lastLogin;
	/**
	 * 是否接受短信，0接受，1拒绝
	 */
	@Column(name="is_receive_msg")
	private Integer isReceiveMsg;
	/**
	 * 邮箱
	 */
	@Column(name="mail")
	private String mail;
	/**
	 * QQ
	 */
	@Column(name="qq")
	private String qq;
	/**
	 * 地址
	 */
	@Column(name="address")
	private String address;
	/**
	 * 生日
	 */
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="sex")
	private Integer sex;
	
	/** 
	* 编号
	*/ 
	@Column(name="user_no")
	private String userNo;

	
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public Date getRegiserTime() {
		return regiserTime;
	}

	public void setRegiserTime(Date regiserTime) {
		this.regiserTime = regiserTime;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getIsReceiveMsg() {
		return isReceiveMsg;
	}

	public void setIsReceiveMsg(Integer isReceiveMsg) {
		this.isReceiveMsg = isReceiveMsg;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "UserPO [id=" + id + ", userName=" + userName + ", password="
				+ password + ", consumeCode=" + consumeCode + ", regiserTime="
				+ regiserTime + ", mobilephone=" + mobilephone + ", areaCode="
				+ areaCode + ", userType=" + userType + ", userStatus="
				+ userStatus + ", delFlag=" + delFlag + ", nickName="
				+ nickName + ", account=" + account + ", createTime="
				+ createTime + ", createId=" + createId + ", updateId="
				+ updateId + ", headImg=" + headImg + ", lastLogin="
				+ lastLogin + ", isReceiveMsg=" + isReceiveMsg + ", mail="
				+ mail + ", qq=" + qq + ", address=" + address + ", birthday="
				+ birthday + ", sex=" + sex + ", userNo=" + userNo
				+ ", getUserNo()=" + getUserNo() + ", getSex()=" + getSex()
				+ ", getId()=" + getId() + ", getUserName()=" + getUserName()
				+ ", getPassword()=" + getPassword() + ", getConsumeCode()="
				+ getConsumeCode() + ", getRegiserTime()=" + getRegiserTime()
				+ ", getMobilephone()=" + getMobilephone() + ", getAreaCode()="
				+ getAreaCode() + ", getUserType()=" + getUserType()
				+ ", getUserStatus()=" + getUserStatus() + ", getDelFlag()="
				+ getDelFlag() + ", getNickName()=" + getNickName()
				+ ", getAccount()=" + getAccount() + ", getCreateTime()="
				+ getCreateTime() + ", getCreateId()=" + getCreateId()
				+ ", getUpdateId()=" + getUpdateId() + ", getHeadImg()="
				+ getHeadImg() + ", getLastLogin()=" + getLastLogin()
				+ ", getIsReceiveMsg()=" + getIsReceiveMsg() + ", getMail()="
				+ getMail() + ", getQq()=" + getQq() + ", getAddress()="
				+ getAddress() + ", getBirthday()=" + getBirthday()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	} 
	
}
