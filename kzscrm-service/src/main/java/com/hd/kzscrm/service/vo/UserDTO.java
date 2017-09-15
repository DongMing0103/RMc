/**
 * 
 */
package com.hd.kzscrm.service.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author 黄霄仪
 * @date 2017年3月6日 下午2:06:03
 * 
 */
@JsonInclude(Include.NON_EMPTY)
public class UserDTO implements Serializable,BaseVO {
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
	 * 员工表删除标识
	 */
	private Integer enterpriseEmployeesLinkDelFlag;
	/**
	 * 是否有消息,0.没有，1.有
	 */
	private Integer isMessage;
	/**
	 * 密码
	 */

	private String password;
	/**
	 * 用户token
	 */

	private String userToken;
	/**
	 * 消费代码
	 */

	private String consumeCode;
	/**
	 * 手机
	 */

	private String mobilephone;
	/**
	 * 地区代码
	 */

	private Integer areaCode;
	/**
	 * 用户类型 1 企业 2商家 3个人
	 */

	private Integer userType;
	/**
	 * 用户状态 1 正常 2 冻结
	 */

	private Integer userStatus;
	/**
	 * 图片上传地址
	 */
	private String imgReadIpAddress;
	/**
	 * 删除标识（0 删除 1存在）
	 */

	private Integer delFlag;
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
	 * 注册日期
	 */
	private Date regiserTime;
	/**
	 * 创建人ID
	 */

	private Long createId;
	/**
	 * 是否启用了支付密码
	 */
	private boolean isPayPassword;
	/**
	 * 是否设置了兴趣爱好,1.是，0.否
	 */
	private Integer isHabit;
	/**
	 * 身份证号码
	 */
	private String idCard;
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
	 * 支付宝，微信 的orderInfo
	 */
	private String orderInfo;
	/**
	 * 订单No
	 */
	private List<String> orderNos;
	/**
	 * （0 未认证 1 已认证 2 无此人 3 停用）
	 * 是否已认证，如果在enterprise_employees_link表为空或者表字段authentication_status值为0
	 * ，2为未认证，1为已认证
	 */

	private Integer authenticationStatus;
	/**
	 * 认证时间
	 */
	private Date authenticationTime;
	/**
	 * 手机验证码
	 */

	private String smsCode;// 手机验证码
	/**
	 * 职务名称
	 */
	private String positionName;

	/**
	 * 系统兴趣爱好
	private List<SysDefHabitDTO> sysDefHabitDTOs;
	 */

	/**
	 * 余额
	 */

	private BigDecimal balance;
	/**
	 * 提现金额
	 */
	private BigDecimal withdrawAmount;
	/**
	 * 年消费
	 */

	private BigDecimal yearConsume;
	/**
	 * 总下单量
	 */
	private Integer totalOrders;
	/**
	 * 企业ID
	 */
	/*@BeanRelevanceField(tableNames = { DatabaseTableNameEnum.enterprise}, tableFieldBorders = { "1" }, tableFieldNames = {
	"e_name" }, injectBeanFieldNames = {"enterpriseName"})
	private Long enterpriseId;
	*//**
	 * 岗位ID
	 *//*
	@BeanRelevanceField(tableNames = { DatabaseTableNameEnum.enterprise_department}, tableFieldBorders = { "1" }, tableFieldNames = {
			"d_name" }, injectBeanFieldNames = {"positionName"})
	private Long positionId;
	*//**
	 * 部门ID
	 *//*
	@BeanRelevanceField(tableNames = { DatabaseTableNameEnum.enterprise_user_position}, tableFieldBorders = { "1" }, tableFieldNames = {
	"name" }, injectBeanFieldNames = {"departmentName"})
	private Long departmentId;
	*//**
	 * 企业名称
	 *//*
	@FieldRetrieve(tableName = DatabaseTableNameEnum.enterprise, referTableField = "id", refer = "enterpriseId", relevanceFieldName = "e_name", condition = "del_flag=1")
	private String enterpriseName;*/
	/**
	 * 部门名称
	 */
	private String departmentName;
	/**
	 * 用户上传的文件
	 */
	private String imgs;
	/**
	 * 订单
	private List<OrderDTO> orderDTOs;
	 */
	
	/** 
	* 编号
	*/ 
	private String userNo;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
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

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
/*
	public List<SysDefHabitDTO> getSysDefHabitDTOs() {
		return sysDefHabitDTOs;
	}

	public void setSysDefHabitDTOs(List<SysDefHabitDTO> sysDefHabitDTOs) {
		this.sysDefHabitDTOs = sysDefHabitDTOs;
	}*/

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getYearConsume() {
		return yearConsume;
	}

	public void setYearConsume(BigDecimal yearConsume) {
		this.yearConsume = yearConsume;
	}

	public Integer getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Integer totalOrders) {
		this.totalOrders = totalOrders;
	}
/*
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
*/
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getAuthenticationStatus() {
		return authenticationStatus;
	}

	public void setAuthenticationStatus(Integer authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}

	/*public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
*/
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
/*
	public List<OrderDTO> getOrderDTOs() {
		return orderDTOs;
	}

	public void setOrderDTOs(List<OrderDTO> orderDTOs) {
		this.orderDTOs = orderDTOs;
	}*/

	public void setPlatform(String value) {
		// TODO Auto-generated method stub

	}

	public void setCredential(String credential) {
		// TODO Auto-generated method stub

	}

	public void setNewPassword(String pwd) {
		// TODO Auto-generated method stub

	}

	public void setMobileNo(String mobileNo) {
		// TODO Auto-generated method stub

	}

	public UserDTO getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMobileNo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getRegisterTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDataSourceType(int i) {
		// TODO Auto-generated method stub

	}

	public void setQueryRegisterTimeStart(String regTimeStart) {
		// TODO Auto-generated method stub

	}

	public void setQueryRegisterTimeEnd(String regTimeEnd) {
		// TODO Auto-generated method stub

	}

	public void setQueryStatusList(List<Integer> statusList) {
		// TODO Auto-generated method stub

	}

	public void setStatus(int parseInt) {
		// TODO Auto-generated method stub

	}

	public Date getRegiserTime() {
		return regiserTime;
	}

	public void setRegiserTime(Date regiserTime) {
		this.regiserTime = regiserTime;
	}

	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public boolean isPayPassword() {
		return isPayPassword;
	}

	public void setPayPassword(boolean isPayPassword) {
		this.isPayPassword = isPayPassword;
	}

	public Date getAuthenticationTime() {
		return authenticationTime;
	}

	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
	}

	/*public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}*/

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public List<String> getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(List<String> orderNos) {
		this.orderNos = orderNos;
	}

	public Integer getIsHabit() {
		return isHabit;
	}

	public void setIsHabit(Integer isHabit) {
		this.isHabit = isHabit;
	}

	public Integer getEnterpriseEmployeesLinkDelFlag() {
		return enterpriseEmployeesLinkDelFlag;
	}

	public void setEnterpriseEmployeesLinkDelFlag(
			Integer enterpriseEmployeesLinkDelFlag) {
		this.enterpriseEmployeesLinkDelFlag = enterpriseEmployeesLinkDelFlag;
	}

	public Integer getIsMessage() {
		return isMessage;
	}

	public String getImgReadIpAddress() {
		return imgReadIpAddress;
	}

	public void setImgReadIpAddress(String imgReadIpAddress) {
		this.imgReadIpAddress = imgReadIpAddress;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage = isMessage;
	}

}
