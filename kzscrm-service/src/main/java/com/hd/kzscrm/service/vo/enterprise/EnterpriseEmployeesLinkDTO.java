package com.hd.kzscrm.service.vo.enterprise;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 企业员工DTO
 * @author 黄霄仪
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class EnterpriseEmployeesLinkDTO {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 手机号
	 */
	private String mobilephone;
	/**性别*/
	private String sexName;
	/**
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * 企业地址
	 */
	private String enterpriseAddress;
	/**
	 * 企业名称
	 */
	private String enterpriseName;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 部门名称
	 */
	private String departmentName;
	/**
	 * 岗位职务ID
	 */
	private Long positionId;
	/**
	 * 岗位职务名称
	 */
	private String positionName;
	/**
	 * 岗位等级
	 */
	private Long positionLevelId;
	/**
	 * 岗位等级name
	 */
	private String positionLevelName;
	/**
	 * 身份证号码
	 */
	private String idCard;
	
	/**
	 * 证件类型  1身份证 2护照 3学生证 （详细：IDTypeEnum.java）
	 * */
	private Integer idType;
	/**
	 * 认证状态（0 未认证   1 已认证  2 无此人）
	 */
	private Integer authenticationStatus;
	/**
	 * 认证时间
	 */
	private Date authenticationTime;
	
	/**
	 * 认证时间名称
	 */
	private String  authenticationTimeName;
	/**
	 * 创建时间(申请认证时间)
	 */
	private Date createTime;
	/**
	 * 操作人用户ID
	 */
	private Integer operateUid;
	/**
	 * 删除标识（0 删除 1未删除）
	 */
	private Integer delFlag;
	/**
	 * 兴趣爱好
	 */
	private String habitName;
	/**
	 *  兴趣类型（1、用餐偏好  2、口味偏好  3、菜系偏好  4、其他）
	 *@Description : TODO
	 *@author : lcl
	 *@time : 2017年3月15日 上午10:55:17
	 */
	private Integer habitType;
	
	
	/**开始时间*/
	private String startDate;
	
	
	/**结束时间*/
	private String endDate;
	
	
	/**用餐时间*/
	private Date eatTime;
	/** 用餐时间 */
	private String eatTimeName;
	
	/**
	 * 部门名字
	 */
	private String dName;
	
	/**食堂名字*/ 
	private String canteenName;
	
	/**岗位等级name*/
	private String positionlevelName;
	/** 消费状态 */
	private Integer consumeStatus;
	/** 消费状态名称 */
	private String consumeStatusName;
	
	/**用餐金额*/
	private Double totalMoney;
	
	/**
	 * 充值金额
	 * @author jyt 2017年3月20日 下午7:56:09
	 */
	private BigDecimal rechargeMoney;
	
	/**
	 * 余额
	 * @author jyt 2017年3月20日 下午7:55:09
	 */
	private BigDecimal balance;
	/**
	 * 用户名字
	 */
	private String userName;
	/**用户头像*/
	private String headImg;
	
	/** 
	* 编号
	*/
	private String userNo;
	
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getEatTimeName() {
		return eatTimeName;
	}
	public void setEatTimeName(String eatTimeName) {
		this.eatTimeName = eatTimeName;
	}
	public String getConsumeStatusName() {
		return consumeStatusName;
	}
	public void setConsumeStatusName(String consumeStatusName) {
		this.consumeStatusName = consumeStatusName;
	}
	public String getAuthenticationTimeName() {
		return authenticationTimeName;
	}
	public void setAuthenticationTimeName(String authenticationTimeName) {
		this.authenticationTimeName = authenticationTimeName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getEatTime() {
		return eatTime;
	}
	public void setEatTime(Date eatTime) {
		this.eatTime = eatTime;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getCanteenName() {
		return canteenName;
	}
	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}
	public String getPositionlevelName() {
		return positionlevelName;
	}
	public void setPositionlevelName(String positionlevelName) {
		this.positionlevelName = positionlevelName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	public Long getPositionLevelId() {
		return positionLevelId;
	}
	public void setPositionLevelId(Long positionLevelId) {
		this.positionLevelId = positionLevelId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getAuthenticationStatus() {
		return authenticationStatus;
	}
	public void setAuthenticationStatus(Integer authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}
	public Date getAuthenticationTime() {
		return authenticationTime;
	}
	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
	}
	public Integer getOperateUid() {
		return operateUid;
	}
	public void setOperateUid(Integer operateUid) {
		this.operateUid = operateUid;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Integer getHabitType() {
		return habitType;
	}
	public void setHabitType(Integer habitType) {
		this.habitType = habitType;
	}
	public String getHabitName() {
		return habitName;
	}
	public void setHabitName(String habitName) {
		this.habitName = habitName;
	}
	public String getPositionLevelName() {
		return positionLevelName;
	}
	public void setPositionLevelName(String positionLevelName) {
		this.positionLevelName = positionLevelName;
	}
	public Integer getConsumeStatus() {
		return consumeStatus;
	}
	public void setConsumeStatus(Integer consumeStatus) {
		this.consumeStatus = consumeStatus;
	}
	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}
	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
}
