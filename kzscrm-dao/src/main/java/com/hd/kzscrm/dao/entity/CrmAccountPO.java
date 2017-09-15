package com.hd.kzscrm.dao.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

@Entity
@Table(name = "crm_user")
public class CrmAccountPO implements Serializable {
    /**
     * 序列号
     */
    private static final transient long serialVersionUID = 8918361010744298030L;

    /**
     * 用户ID
     */
    @Column(name = "id")
    @Id
   // @Sequence
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 代理商ID
     */
    @Column(name = "agent_id")
    private Long agentId;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 状态：0待审核  1正常   2停用
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 电话号码
     */
    @Column(name = "mobilephone")
    private String mobilephone;

    /**
     * 邮箱
     */
    @Column(name = "mail")
    private String mail;

    /**
     * QQ
     */
    @Column(name = "qq")
    private String qq;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private Date birthday;


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
    @Column(name = "language_code")
    private String languageCode;

    /***/
    @Column(name = "create_time")
    private Date createTime;

    /***/
    @Column(name = "create_id")
    private Long createId;

    /***/
    @Column(name = "update_time")
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
     * 0.删除，1.存在
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * 是否接受短信，0接受，1拒绝
     */
    @Column(name = "is_receive_msg")
    private String isReceiveMsg;
    /**
     * 帐户类型：1.平台，2.代理商
     */
    @Column(name = "type")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
	 * @return the userStatus
	 */
	public Integer getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the mobilephone
	 */
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * @param mobilephone the mobilephone to set
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
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

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode == null ? null : languageCode.trim();
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


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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