package com.hd.kzscrm.service.param.enterprise;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;


public class EnterpriseEmployeesLinkParam extends PageParam{
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
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * APP版本
	 */
	private String version;
	/**
	 * 企业Ids
	 */
	private List<Long> enterpriseIds;
	/**
	 * 用户手机号，用于验证userToken
	 */
	private String mobilephone;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 岗位职务ID
	 */
	private Long positionId;
	/**
	 * 岗位等级
	 */
	private Integer positionLevelId;
	
	/**
	 * 身份证号码，15到18位
	 */
	private String idCard;
	
	/**
	 * 证件类型  1身份证 2护照 3学生证 （详细：IDTypeEnum.java）
	 * */
	private Integer idType;
	/**
	 * 企业认证状态（0 未认证   1 已认证  2 无此人）
	 */
	private Integer authenticationStatus;
	/**
	 * 企业认证时间
	 */
	private Date authenticationTime;
	/**
	 * 创建时间(申请认证时间)
	 */
	private Date createTime;
	/**
	 * 企业谁的操作人ID
	 */
	private Long operateUid;
	/**
	 * 删除标识	（0 删除 1未删除）
	 */
	private Integer delFlag;
	/**
	 * 开始时间(创建)
	 */
	private Date createTimeStart;
	/**
	 * 结束时间(创建)
	 */
	private Date createTimeEnd;
	/**
	 * 预充值订单id
	 * */
	private Long preRechargeOrderId;
	
	/**
	 * 多用户查询
	 * */
	private List<Long> userIds;
	/**
	 * 批量导出
	 */
	private List<Long> ids;
	
	private Integer rechargeModel;
	
	public List<Long> getEnterpriseIds() {
		return enterpriseIds;
	}
	public void setEnterpriseIds(List<Long> enterpriseIds) {
		this.enterpriseIds = enterpriseIds;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getPositionLevelId() {
		return positionLevelId;
	}
	public void setPositionLevelId(Integer positionLevelId) {
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
	public Long getOperateUid() {
		return operateUid;
	}
	public void setOperateUid(Long operateUid) {
		this.operateUid = operateUid;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Long getPreRechargeOrderId() {
		return preRechargeOrderId;
	}
	public void setPreRechargeOrderId(Long preRechargeOrderId) {
		this.preRechargeOrderId = preRechargeOrderId;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public Integer getRechargeModel() {
		return rechargeModel;
	}
	public void setRechargeModel(Integer rechargeModel) {
		this.rechargeModel = rechargeModel;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
}
