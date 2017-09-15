package com.hd.kzscrm.service.vo.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.annotation.DataInject;




public class CrmClientResourceVO {


    /**
      * ID
    */
    // @AutoIncrease
    private   Long   id ;
    
    /**
      * 客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
    */
	private   Integer   clientType ;
	private String clientTypeStr;
	
	
    private  String clientTypeName;
    /**
	 * 企业ID
	 */
	private Long enterpriseId;
    /**
     * 代理商ID，如果不为空，表示属于代理商的保护客户，当代理商分配给代理商的业务员时，business_id也不为空
     */
	private Long agentId;
	/**
	 * 代理商ID，按代理商等级顺序填充
	 */
	private List<Long> agentIds;
	/**
	 * 申请日期
	 */
	private Date applyTime;
	/**
	 * 申请通过日期
	 */
	private Date applyApproveTime;
    /**
      * 客户性质	1.散客，2.保护客户，3.正式客户
    */
	private   Integer   clientNature ;
	private   String   clientNatureStr ;
	
    private   String    clientNatureName;
    //客户属性  1代理商客户 2平台客户
    private  Integer customerAttribute;
    private  String customerAttributeName;
	/**
	 * 审核状态,0.申请中，1.申请通过，2.申请失败
	 */
	private Integer checkStatus;
	
    /**
      * 客户名称
    */
	private   String   name ;
    
	/**
	 * 业务员姓名
	 */
    private String businessName;
    /**
     * 所属团队
     */
    private String teamName;
    /**
      * 联系人
    */
	private   String   contact ;
    
    
    
    /**
      * 联系人电话
    */
	private   String   mobile ;
    
    
    /**
      * 业务员ID
    */
	private   Long   businessId ;
	/**
	 * 业务员团队名称
	 */
	private String businessTeamName;
	/**
	 * 业务员团队ID
	 */
	private Long businessTeamId;
    
    
    /**
      * 保护截止期
    */
	private   Date   protectDeadline ;
    
    
    /**
      * 创建时间
    */
	private   Date   createTime ;
    
    
    /**
      * 删除标识	1.存在0.删除
    */
	private   Integer   delFlag ;
    
    
    /**
      * 地址
    */
	private   String   address ;
    
    
    /**
      * 跟踪次数
    */
	private   Long   tailNum ;
    
    
    /**
      * 客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
    */
	private   Long   agentCanteenId ;
    
    
    /**
      * 客户编号
    */
	private   Long   clientNum ;
	
	/**
	 * 开始时间
	 */
	private String stratTimes;
	
	/**
	 * 结束时间
	 */
	private String endTimes;
	
	/**
	 * 食堂分账比例
	 */
	private BigDecimal canteenSplitPercent;
	private String canteenSplitPercentStr;
	/**
	 * 代理商名
	 */
	private String agentName;
	
	/**
	 * 区域编码
	 * @return
	 */
	private Long areaCode;
	
	
	/**
	 * 区域名称
	 * @return
	 */
	@DataInject(DatabaseTableNameEnum.base_area)
	private String areaName;
	//订单数量
	private Long orderNum;
	//实付金额 总和
	private BigDecimal sumMomey;
	//分账金额
	private BigDecimal splitMomey;
	
	//总订单量
	private Integer orderTotalNum;
	//总订单金额
	private BigDecimal orderTotalMoney;
	
	 /**
     * 上一次保护关联的业务员Id
     */
	private   Long   lastProtectBusinessId ;
	
	/**
     * 解除保护关系(踢出,成为散客)时间
     */
	private   Date   relieveProtectTime ;
	
	/**
	 * 个人客户标志(用于区分业务经理的客户与其所管辖业务员客户)
	 */
	private Integer selfClientFlag;
	
	
	public String getCustomerAttributeName() {
		return customerAttributeName;
	}


	public void setCustomerAttributeName(String customerAttributeName) {
		this.customerAttributeName = customerAttributeName;
	}


	public BigDecimal getSplitMomey() {
		return splitMomey;
	}


	public void setSplitMomey(BigDecimal splitMomey) {
		this.splitMomey = splitMomey;
	}


	public BigDecimal getSumMomey() {
		return sumMomey;
	}
	

	public Integer getCustomerAttribute() {
		return customerAttribute;
	}


	public void setCustomerAttribute(Integer customerAttribute) {
		this.customerAttribute = customerAttribute;
	}


	public void setSumMomey(BigDecimal sumMomey) {
		this.sumMomey = sumMomey;
	}


	public Long getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getClientType() {
		return clientType;
	}


	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}


	public Integer getClientNature() {
		return clientNature;
	}


	public void setClientNature(Integer clientNature) {
		this.clientNature = clientNature;
	}


	public String getClientTypeName() {
		return clientTypeName;
	}


	public void setClientTypeName(String clientTypeName) {
		this.clientTypeName = clientTypeName;
	}


	public String getClientNatureName() {
		return clientNatureName;
	}


	public void setClientNatureName(String clientNatureName) {
		this.clientNatureName = clientNatureName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Long getBusinessId() {
		return businessId;
	}


	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}


	public java.util.Date getProtectDeadline() {
		return protectDeadline;
	}


	public void setProtectDeadline(java.util.Date protectDeadline) {
		this.protectDeadline = protectDeadline;
	}


	public java.util.Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}


	public Integer getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Long getTailNum() {
		return tailNum;
	}


	public void setTailNum(Long tailNum) {
		this.tailNum = tailNum;
	}


	public Long getAgentCanteenId() {
		return agentCanteenId;
	}


	public void setAgentCanteenId(Long agentCanteenId) {
		this.agentCanteenId = agentCanteenId;
	}


	public Long getClientNum() {
		return clientNum;
	}


	public void setClientNum(Long clientNum) {
		this.clientNum = clientNum;
	}


	/**
	 * @return the checkStatus
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}


	/**
	 * @param checkStatus the checkStatus to set
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
	 * @param applyTime the applyTime to set
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


	/**
	 * @return the businessTeamName
	 */
	public String getBusinessTeamName() {
		return businessTeamName;
	}


	/**
	 * @param businessTeamName the businessTeamName to set
	 */
	public void setBusinessTeamName(String businessTeamName) {
		this.businessTeamName = businessTeamName;
	}


	/**
	 * @return the businessTeamId
	 */
	public Long getBusinessTeamId() {
		return businessTeamId;
	}


	/**
	 * @param businessTeamId the businessTeamId to set
	 */
	public void setBusinessTeamId(Long businessTeamId) {
		this.businessTeamId = businessTeamId;
	}


	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}


	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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


	public BigDecimal getCanteenSplitPercent() {
		return canteenSplitPercent;
	}


	public void setCanteenSplitPercent(BigDecimal canteenSplitPercent) {
		this.canteenSplitPercent = canteenSplitPercent;
	}


	public String getAgentName() {
		return agentName;
	}


	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}


	public Long getAreaCode() {
		return areaCode;
	}


	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public String getClientTypeStr() {
		return clientTypeStr;
	}


	public void setClientTypeStr(String clientTypeStr) {
		this.clientTypeStr = clientTypeStr;
	}


	public String getClientNatureStr() {
		return clientNatureStr;
	}


	public void setClientNatureStr(String clientNatureStr) {
		this.clientNatureStr = clientNatureStr;
	}


	public String getCanteenSplitPercentStr() {
		return canteenSplitPercentStr;
	}


	public void setCanteenSplitPercentStr(String canteenSplitPercentStr) {
		this.canteenSplitPercentStr = canteenSplitPercentStr;
	}


	/**
	 * @return the agentId
	 */
	public Long getAgentId() {
		return agentId;
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
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}


	/**
	 * @return the agentIds
	 */
	public List<Long> getAgentIds() {
		return agentIds;
	}


	/**
	 * @param agentIds the agentIds to set
	 */
	public void setAgentIds(List<Long> agentIds) {
		this.agentIds = agentIds;
	}


	public Integer getOrderTotalNum() {
		return orderTotalNum;
	}


	public void setOrderTotalNum(Integer orderTotalNum) {
		this.orderTotalNum = orderTotalNum;
	}


	public BigDecimal getOrderTotalMoney() {
		return orderTotalMoney;
	}


	public void setOrderTotalMoney(BigDecimal orderTotalMoney) {
		this.orderTotalMoney = orderTotalMoney;
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


	public Integer getSelfClientFlag() {
		return selfClientFlag;
	}


	public void setSelfClientFlag(Integer selfClientFlag) {
		this.selfClientFlag = selfClientFlag;
	}


	

}
