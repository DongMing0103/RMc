package com.hd.kzscrm.service.param.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hd.kzscrm.common.param.PageParam;

/**
 * 用户查询参数
 * @author 黄霄仪
 * @date 2017年3月6日 上午11:50:14
 * 
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserParam extends PageParam{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 登录方式：1.验证码，2.密码
	 */
	private Integer loginType;
	/**
	 * 1.登录，2.修改支付密码，3.修改登录密码
	 */
	private Integer smsType;
	/**
	 * APP版本
	 */
	private String version;
	/**
	 * ip地址
	 */
	private String imgReadIpAddress;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 短信验证码
	 */
	private String smsCode;

	/**
	 *  图片验证码
	 * @author jyt 2017年3月16日 上午11:37:44
	 */
	private String imgCode;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 消费代码
	 */
	private String consumeCode;
	/**
	 * 注册时间
	 */
	private Date regiserTime;
	/**
	 * 手机
	 */
	private String mobilephone;
	/**
	 * 地区代码
	 */
	private Integer areaCode;
	/**
	 * 用户类型 1 企业  2商家  3个人
	 */
	private Integer userType;
	/**
	 * 用户状态 1 正常  2 冻结
	 */
	private Integer userStatus;
	/**
	 * 删除标识（0 删除  1存在）
	 */
	private Integer delFlag;
	/**
	 * 支付方式 1,余额，2.支付宝，3.微信
	 */
	private Integer payModel;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 帐号
	 */
	private String account;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 订单编号
	 */
	private List<String> orderNos;
	/**
	 * 创建人ID
	 */
	private Long createId;
	/**
	 * 更新人ID
	 */
	private Long updateId;
	/**
	 * 头像地址
	 */
	private String headImg;
	/**
	 * 最后一次登录时间
	 */
	private Date lastLogin;
	/**
	 * 是否接受短信，0接受，1拒绝
	 */
	private Integer isReceiveMsg;
	/**
	 * 邮箱
	 */
	private String mail;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 支付密码
	 */
	private String payPassword;
	/**
	 * 提现金额
	 */
	private BigDecimal withdrawAmount;
	/**
	 * 企业名称
	 */
	private String enterpriseName;
	/**
	 * 岗位
	 */
	private String positionName;
	/**
	 * 1.提交订单，2.已提交未支付的订单再次申请支付.默认不填为1
	 */
	private Integer orderType;
	/**
	 * 部门
	 */
	private String departmentName;
	/** 
	* 编号
	*/ 
	private String userNo;
	/**
	 * 用餐偏好
	 */
	private Long mealPreference;
	/**
	 * 用餐
	 */
	private List<Long> mealPreferences;
	/**
	 * 口味偏好
	 */
	private Long tastePreference;
	/**
	 * 口味偏好
	 */
	private List<Long> tastePreferences;
	/**
	 * 菜系偏好
	 */
	private Long cuisinePreference;
	/**
	 * 菜系偏好
	 */
	private List<Long> cuisinePreferences;
	/**
	 * 上传的文件
	 */
	private MultipartFile[] files;
	
	/**
	 * 负责人身份证
	 * */
	private String headIdcard;
	/**
	 * 兴趣爱好ID集合
	 */
	List<Long> userHabbitIds;
	/**
	 * 头像文件
	 */
	private MultipartFile headImgFile;
	
	/**手机号或者密码*/
	private String telName;
	
	/**
	 * 本次需要支付的金额
	 */
	private BigDecimal payAmount;
	
	
	/**
	 * 搜索手机或名称
	 * @author jyt 2017年3月23日 下午2:49:25
	 */
	private String searchName;
	
	/**
	 * （0 未认证 1 已认证 2 无此人 3 停用）
	 * 是否已认证，如果在enterprise_employees_link表为空或者表字段authentication_status值为0
	 * ，2为未认证，1为已认证
	 */

	private Integer authenticationStatus;
	
	/*支付宝充值回调URL*/
	private String appRechargeNotifyUrl;
	
	/**
	 * 支付宝订单支付回调URL
	 */
	private String appOrderPayNotifyUrl;
	
	/**
	 * 微信内部借款访问地址
	 */
	private String weixinOrderPayUrl;
	/** 
	* 用户Id集合
	*/ 
	private String userIds;
	/**
	 * id集合
	 */
	private List<Long> ids;
	/** 
	* 状态中文
	*/ 
	private String authenticationStatusName;
	
	/** 
	* 标识符
	*/ 
	private Integer selectId;
	
	/*支付宝支付*/
	private AlipayClient alipayClient;
	
	/*支付宝退款*/
	private AlipayClient alipayClientReturn;
	
	
	public Integer getSelectId() {
		return selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getAuthenticationStatusName() {
		return authenticationStatusName;
	}

	public void setAuthenticationStatusName(String authenticationStatusName) {
		this.authenticationStatusName = authenticationStatusName;
	}

	public String getUserIds() {
	return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getTelName() {
		return telName;
	}

	public void setTelName(String telName) {
		this.telName = telName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Integer getAuthenticationStatus() {
		return authenticationStatus;
	}

	public void setAuthenticationStatus(Integer authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
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

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
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

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Long getMealPreference() {
		return mealPreference;
	}

	public void setMealPreference(Long mealPreference) {
		this.mealPreference = mealPreference;
	}

	public Long getTastePreference() {
		return tastePreference;
	}

	public void setTastePreference(Long tastePreference) {
		this.tastePreference = tastePreference;
	}

	public Long getCuisinePreference() {
		return cuisinePreference;
	}

	public void setCuisinePreference(Long cuisinePreference) {
		this.cuisinePreference = cuisinePreference;
	}

	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getHeadIdcard() {
		return headIdcard;
	}

	public void setHeadIdcard(String headIdcard) {
		this.headIdcard = headIdcard;
	}

	public MultipartFile getHeadImgFile() {
		return headImgFile;
	}

	public void setHeadImgFile(MultipartFile headImgFile) {
		this.headImgFile = headImgFile;
	}

	public List<Long> getMealPreferences() {
		return mealPreferences;
	}

	public void setMealPreferences(List<Long> mealPreferences) {
		this.mealPreferences = mealPreferences;
	}

	public List<Long> getTastePreferences() {
		return tastePreferences;
	}

	public void setTastePreferences(List<Long> tastePreferences) {
		this.tastePreferences = tastePreferences;
	}

	public List<Long> getCuisinePreferences() {
		return cuisinePreferences;
	}

	public void setCuisinePreferences(List<Long> cuisinePreferences) {
		this.cuisinePreferences = cuisinePreferences;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Long> getUserHabbitIds() {
		return userHabbitIds;
	}

	public void setUserHabbitIds(List<Long> userHabbitIds) {
		this.userHabbitIds = userHabbitIds;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public List<String> getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(List<String> orderNos) {
		this.orderNos = orderNos;
	}

	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	public String getPayPassword() {
		return payPassword;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public String getImgReadIpAddress() {
		return imgReadIpAddress;
	}

	public void setImgReadIpAddress(String imgReadIpAddress) {
		this.imgReadIpAddress = imgReadIpAddress;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	/**
	 * @return the smsType
	 */
	public Integer getSmsType() {
		return smsType;
	}

	/**
	 * @param smsType the smsType to set
	 */
	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	/**
	 * @return the alipayClient
	 */
	public AlipayClient getAlipayClient() {
		return alipayClient;
	}

	/**
	 * @param alipayClient the alipayClient to set
	 */
	public void setAlipayClient(AlipayClient alipayClient) {
		this.alipayClient = alipayClient;
	}

	/**
	 * @return the alipayClientReturn
	 */
	public AlipayClient getAlipayClientReturn() {
		return alipayClientReturn;
	}

	/**
	 * @param alipayClientReturn the alipayClientReturn to set
	 */
	public void setAlipayClientReturn(AlipayClient alipayClientReturn) {
		this.alipayClientReturn = alipayClientReturn;
	}

	/**
	 * @return the appRechargeNotifyUrl
	 */
	public String getAppRechargeNotifyUrl() {
		return appRechargeNotifyUrl;
	}

	/**
	 * @param appRechargeNotifyUrl the appRechargeNotifyUrl to set
	 */
	public void setAppRechargeNotifyUrl(String appRechargeNotifyUrl) {
		this.appRechargeNotifyUrl = appRechargeNotifyUrl;
	}

	/**
	 * @return the appOrderPayNotifyUrl
	 */
	public String getAppOrderPayNotifyUrl() {
		return appOrderPayNotifyUrl;
	}

	/**
	 * @param appOrderPayNotifyUrl the appOrderPayNotifyUrl to set
	 */
	public void setAppOrderPayNotifyUrl(String appOrderPayNotifyUrl) {
		this.appOrderPayNotifyUrl = appOrderPayNotifyUrl;
	}

	public String getWeixinOrderPayUrl() {
		return weixinOrderPayUrl;
	}

	public void setWeixinOrderPayUrl(String weixinOrderPayUrl) {
		this.weixinOrderPayUrl = weixinOrderPayUrl;
	}

	/**
	 * @return the loginType
	 */
	public Integer getLoginType() {
		return loginType;
	}

	/**
	 * @param loginType the loginType to set
	 */
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	
}
