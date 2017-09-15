package com.hd.kzscrm.service.param.client;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmClientResourceParam extends PageParam {
		
	
	/**
	 * ID
	 */
	private Long id;
	private List<Long> ids;
	private String idStr;
	private String  clientIds;
	/**
	 * 客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
	 */
	private Integer clientType;
	/**
	 * 客户类型集合
	 */
	private List<Integer> clientTypes;
	private String clientTypeS;
	/**
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * 如果有值，清空企业ID
	 */
	private Long enterpriseIdNull;
	/**
	 * 客户性质 1.散客，2.保护客户，3.正式客户
	 */
	private Integer clientNature;
	/**
	 * 客户性质 
	 */
	private List<Integer> clientNatures;
	/**
	 * 代理商不为正式客户
	 */
	private Integer agentNotFormalClientFlag;
	/**
	 * 单位名称
	 */
	private String name;
	private String nameLike;
	/**
	 * 申请日期
	 */
	private Date applyTime;
	/**
	 * 申请通过日期
	 */
	private Date applyApproveTime;
	/**
	 * 审核状态,0.申请中，1.申请通过，2.申请失败
	 */
	private Integer checkStatus;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系人电话
	 */
	private String mobile;
	/**
     * 代理商ID，如果不为空，表示属于代理商的保护客户，当代理商分配给代理商的业务员时，business_id也不为空
     */
	private Long agentId;
	/**
	 * 该值非空就置空该值
	 */
	private Long agentIdNull;
	/**
	 * 团队id
	 */
	private Long teamId;
	/**
	 * 省代码
	 */
	private Integer provCode;
	/**
	 * 市代码
	 */
	private Integer cityCode;
	/**
	 * 区域CODE，2位是国家，4位是大区，5位是省，7位是市
	 */
	private Long areaCode;
	
	/**
	 * 区域CODE集合，2位是国家，4位是大区，5位是省，7位是市
	 */
	private List<Long> areaCodeList;
	/**
	 * 业务员ID
	 */
	private Long businessId;
	/**
	 * 该值非空就置空该值
	 */
	private Long businessIdNull;
	private List<Long> businessIds;
	private String businessIdS;
	//客户属性 1 代理商客户 2平台客户
	private Integer customerAttribute;
	/**
	 * 保护截止期
	 */
	private Date protectDeadline;
	/**
	 * 保护截止期开始时间
	 */
	private Date protectDeadlineStart;
	/**
	 * 保护截止期结束时间
	 */
	private Date protectDeadlineEnd;
	/**
	 * 非空的话，置空保护截止期
	 */
	private Date protectDeadlineNull;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 删除标识 1.存在0.删除
	 */
	private Integer delFlag;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 跟踪次数
	 */
	private Long tailNum;
	/**
	 * 客户ID 根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
	 */
	private Long agentCanteenId;
	/**
	 * 客户编号
	 */
	private Long clientNum;
	private String clientNumLike;
	/**
	 * 当前登录者Id
	 */
	private Long userId;
	
	/**
	 * 开始时间
	 */
	private String stratTimes;
	/**
	 * 结束时间
	 */
	private String endTimes;
	/**
	 * 入驻时间(开始)
	 */
	private String enterTimeStart;
	/**
	 * 入驻时间(结束)
	 */
	private String enterTimeEnd;
	
	/**
	 * 查询条件
	 */
	private Integer selectNum;
	
	/**
	 * 查询条件内容
	 */
	private String condition;
	
	
	/**
	 * 工作月
	 */
	private String applyMonth;
	/**
	 * 搜索条件 1名称,2编号
	 */
	private Integer searchCriteria;
	/**
	 * 搜索内容
	 */
	private String searchContent;
	private List<Long>  areaCodes;
	
	/**
	 * 客户id
	 */
	private Long customerId;
	
	/**
     * 上一次保护关联的业务员Id
     */
	private   Long   lastProtectBusinessId ;
	
	/**
     * 解除保护关系(踢出,成为散客)时间
     */
	private   Date   relieveProtectTime ;
	
	/**
	 * @return the areaCodes
	 */
	public List<Long> getAreaCodes() {
		return areaCodes;
	}

	/**
	 * @param areaCodes the areaCodes to set
	 */
	public void setAreaCodes(List<Long> areaCodes) {
		this.areaCodes = areaCodes;
	}

	public Integer getProvCode() {
		return provCode;
	}

	public void setProvCode(Integer provCode) {
		this.provCode = provCode;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public Long getAreaCode() {
		return areaCode;
	}

	
	public String getClientIds() {
		return clientIds;
	}

	public void setClientIds(String clientIds) {
		this.clientIds = clientIds;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public List<Long> getAreaCodeList() {
		return areaCodeList;
	}

	public void setAreaCodeList(List<Long> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	/**
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	/**
	 * 客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	
	public Integer getCustomerAttribute() {
		return customerAttribute;
	}

	public void setCustomerAttribute(Integer customerAttribute) {
		this.customerAttribute = customerAttribute;
	}

	/**
	 * @return 客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
	 */
	public Integer getClientType() {
		return this.clientType;
	}
	/**
	 * 客户性质 1.散客，2.保护客户，3.正式客户
	 */
	public void setClientNature(Integer clientNature) {
		this.clientNature = clientNature;
	}

	/**
	 * @return 客户性质 1.散客，2.保护客户，3.正式客户
	 */
	public Integer getClientNature() {
		return this.clientNature;
	}

	public Integer getAgentNotFormalClientFlag() {
		return agentNotFormalClientFlag;
	}

	public void setAgentNotFormalClientFlag(Integer agentNotFormalClientFlag) {
		this.agentNotFormalClientFlag = agentNotFormalClientFlag;
	}

	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return 联系人
	 */
	public String getContact() {
		return this.contact;
	}


	/**
	 * 联系人
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return 联系人
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 业务员ID
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return 业务员ID
	 */
	public Long getBusinessId() {
		return this.businessId;
	}

	/**
	 * 保护截止期
	 */
	public void setProtectDeadline(Date protectDeadline) {
		this.protectDeadline = protectDeadline;
	}

	/**
	 * @return 保护截止期
	 */
	public Date getProtectDeadline() {
		return this.protectDeadline;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 删除标识 1.存在0.删除
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 删除标识 1.存在0.删除
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return 地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 跟踪次数
	 */
	public void setTailNum(Long tailNum) {
		this.tailNum = tailNum;
	}

	/**
	 * @return 跟踪次数
	 */
	public Long getTailNum() {
		return this.tailNum;
	}

	/**
	 * 客户ID 根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
	 */
	public void setAgentCanteenId(Long agentCanteenId) {
		this.agentCanteenId = agentCanteenId;
	}

	/**
	 * @return 客户ID 根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
	 */
	public Long getAgentCanteenId() {
		return this.agentCanteenId;
	}

	/**
	 * 客户编号
	 */
	public void setClientNum(Long clientNum) {
		this.clientNum = clientNum;
	}

	/**
	 * @return 客户编号
	 */
	public Long getClientNum() {
		return this.clientNum;
	}

	/**
	 * @return the checkStatus
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}

	/**
	 * @param checkStatus
	 *            the checkStatus to set
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * @return the applyTime
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * @param applyTime
	 *            the applyTime to set
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyApproveTime() {
		return applyApproveTime;
	}

	public void setApplyApproveTime(Date applyApproveTime) {
		this.applyApproveTime = applyApproveTime;
	}

	public String getStratTimes() {
		return stratTimes;
	}

	public void setStratTimes(String stratTimes) {
		this.stratTimes = stratTimes;
	}

	public String getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}

	
	public String getEnterTimeStart() {
		return enterTimeStart;
	}

	public void setEnterTimeStart(String enterTimeStart) {
		this.enterTimeStart = enterTimeStart;
	}

	public String getEnterTimeEnd() {
		return enterTimeEnd;
	}

	public void setEnterTimeEnd(String enterTimeEnd) {
		this.enterTimeEnd = enterTimeEnd;
	}

	public Integer getSelectNum() {
		return selectNum;
	}

	public void setSelectNum(Integer selectNum) {
		this.selectNum = selectNum;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getApplyMonth() {
		return applyMonth;
	}

	public void setApplyMonth(String applyMonth) {
		this.applyMonth = applyMonth;
	}

	public Integer getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(Integer searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getClientNumLike() {
		return clientNumLike;
	}

	public void setClientNumLike(String clientNumLike) {
		this.clientNumLike = clientNumLike;
	}

	public String getBusinessIdS() {
		return businessIdS;
	}

	public void setBusinessIdS(String businessIdS) {
		this.businessIdS = businessIdS;
	}

	/**
	 * @return the clientTypes
	 */
	public List<Integer> getClientTypes() {
		return clientTypes;
	}

	/**
	 * @param clientTypes the clientTypes to set
	 */
	public void setClientTypes(List<Integer> clientTypes) {
		this.clientTypes = clientTypes;
	}

	/**
	 * @return the enterpriseId
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * @param enterpriseId the enterpriseId to set
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * @return the enterpriseIdNull
	 */
	public Long getEnterpriseIdNull() {
		return enterpriseIdNull;
	}

	/**
	 * @param enterpriseIdNull the enterpriseIdNull to set
	 */
	public void setEnterpriseIdNull(Long enterpriseIdNull) {
		this.enterpriseId=null;
		this.enterpriseIdNull = enterpriseIdNull;
	}

	/**
	 * @return the protectDeadlineNull
	 */
	public Date getProtectDeadlineNull() {
		return protectDeadlineNull;
	}

	/**
	 * @param protectDeadlineNull the protectDeadlineNull to set
	 */
	public void setProtectDeadlineNull(Date protectDeadlineNull) {
		this.protectDeadline=null;
		this.protectDeadlineNull = protectDeadlineNull;
	}

	/**
	 * @return the agentIdNull
	 */
	public Long getAgentIdNull() {
		return agentIdNull;
	}

	/**
	 * @param agentIdNull the agentIdNull to set
	 */
	public void setAgentIdNull(Long agentIdNull) {
		this.agentId=null;
		this.agentIdNull = agentIdNull;
	}

	/**
	 * @return the businessIdNull
	 */
	public Long getBusinessIdNull() {
		return businessIdNull;
	}

	/**
	 * @param businessIdNull the businessIdNull to set
	 */
	public void setBusinessIdNull(Long businessIdNull) {
		this.businessId=null;
		this.businessIdNull = businessIdNull;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the protectDeadlineStart
	 */
	public Date getProtectDeadlineStart() {
		return protectDeadlineStart;
	}

	/**
	 * @param protectDeadlineStart the protectDeadlineStart to set
	 */
	public void setProtectDeadlineStart(Date protectDeadlineStart) {
		this.protectDeadlineStart = protectDeadlineStart;
	}

	/**
	 * @return the protectDeadlineEnd
	 */
	public Date getProtectDeadlineEnd() {
		return protectDeadlineEnd;
	}

	/**
	 * @param protectDeadlineEnd the protectDeadlineEnd to set
	 */
	public void setProtectDeadlineEnd(Date protectDeadlineEnd) {
		this.protectDeadlineEnd = protectDeadlineEnd;
	}

	public Long getLastProtectBusinessId() {
		return lastProtectBusinessId;
	}

	public void setLastProtectBusinessId(Long lastProtectBusinessId) {
		this.lastProtectBusinessId = lastProtectBusinessId;
	}

	public Date getRelieveProtectTime() {
		return relieveProtectTime;
	}

	public void setRelieveProtectTime(Date relieveProtectTime) {
		this.relieveProtectTime = relieveProtectTime;
	}

	public String getClientTypeS() {
		return clientTypeS;
	}

	public void setClientTypeS(String clientTypeS) {
		this.clientTypeS = clientTypeS;
	}

	/**
	 * @return the clientNatures
	 */
	public List<Integer> getClientNatures() {
		return clientNatures;
	}

	/**
	 * @param clientNatures the clientNatures to set
	 */
	public void setClientNatures(List<Integer> clientNatures) {
		this.clientNatures = clientNatures;
	}

}
