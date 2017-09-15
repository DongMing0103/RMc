package com.hd.kzscrm.dao.entity.perm;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.hd.kzscrm.dao.entity.enterprise.EnterprisePO;
import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 企业usersession n
 * 
 * @author wing
 *
 */
@Entity
@Table(name = "user")
public class EnterpriseAccountPO implements Serializable {
	/**
	 * 序列号
	 */
	private static final transient long serialVersionUID = 8918361010744298030L;

	/**
	 * `id` bigint(20) NOT NULL COMMENT '用户id', `user_name` varchar(20) DEFAULT
	 * NULL COMMENT '用户名', `password` varchar(20) DEFAULT NULL COMMENT '密码',
	 * `consume_code` varchar(20) DEFAULT NULL COMMENT '消费代码', `regiser_time`
	 * datetime(5) DEFAULT NULL COMMENT '注册时间', `mobilephone` int(20) DEFAULT
	 * NULL COMMENT '手机号码', `area_code` int(10) DEFAULT NULL COMMENT '地区代码',
	 * `user_type` int(1) DEFAULT NULL COMMENT '用户类型 1 企业 2商家 3个人',
	 * `user_status` int(1) DEFAULT NULL COMMENT '用户状态 1 正常 2 冻结', `del_flag`
	 * int(1) DEFAULT '1' COMMENT '删除标识（0 删除 1存在）',
	 * 
	 * 
	 * 
	 * /** 用户ID
	 */
	@Column(name = "id")
	@Id
	// @Sequence
	private Long id;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String name;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 用户类型：企业管理员，企业业务员，系统运营人员(暂时没有用)
	 */
	@Column(name = "user_type")
	private Integer userType;

	/**
	 * 状态：0待审核 1正常 4停用
	 */
	@Column(name = "user_status")
	private Integer status;

	/**
	 * 电话号码
	 */
	@Column(name = "mobilephone")
	private String mobileNo;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	private Integer sex;

	// /**
	// * 是否删除
	// */
	// @Column(name = "delete_flag")
	// private Integer deleteFlag;

	/**
	 * 帐号
	 */
	@Column(name = "account")
	private String account;

	/**
	 * 昵称
	 */
	@Column(name = "nick_name")
	private String nickName;

	/***/
	@Column(name = "area_code")
	private Integer areaCode;

	/***/
	@Column(name = "create_time")
	private Date createTime;

	/***/
	@Column(name = "create_id")
	private Long createId;

	/***/
	@Column(name = "regiser_time")
	private Date updateTime;

	/***/
	@Column(name = "update_id")
	private Long updateId;

	/**
	 * 头像
	 */
	@Column(name = "head_img")
	private String headImg;

	/**
	 * 最后一次登录时间
	 */
	@Column(name = "last_login")
	private Date lastLogin;

	/**
	 * 是否接受短信，0接受，1拒绝
	 */
	@Column(name = "is_receive_msg")
	private String isReceiveMsg;

	@Column(name = "mail")
	private String mail;

	@Column(name = "password")
	private String qq;

	@Column(name = "address")
	private String address;

	@Column(name = "birthday")
	private Date birthday;

	private EnterprisePO enterprisePO;
	
	/**
	 * 删除标识（0 删除  1存在）
	 * @author jyt 2017年4月7日 上午10:37:17
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo == null ? null : mobileNo.trim();
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail == null ? null : mail.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	//
	// public Integer getDeleteFlag() {
	// return deleteFlag;
	// }
	//
	// public void setDeleteFlag(Integer deleteFlag) {
	// this.deleteFlag = deleteFlag;
	// }

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
		this.headImg = headImg == null ? null : headImg.trim();
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getIsReceiveMsg() {
		return isReceiveMsg;
	}

	public void setIsReceiveMsg(String isReceiveMsg) {
		this.isReceiveMsg = isReceiveMsg;
	}

	public EnterprisePO getEnterprisePO() {
		return enterprisePO;
	}

	public void setEnterprisePO(EnterprisePO enterprisePO) {
		this.enterprisePO = enterprisePO;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}