package com.hd.kzscrm.service.vo.business;



public class CrmBusinessTailRecordVO {
	
	
	/**
     * 主键
   */
   // @AutoIncrease
   private   Long   id ;
   
   private Integer delFlag;
   
   /**
     * 1.电话联系，2.上门拜访
   */
	private   Integer   tailNature ;
	private   String   tailNatureName ;
   
   
   /**
     * 拜访开始时间
   */
	private   java.util.Date   tailTimeStart ;
   
   
   /**
     * 拜访结束时间
   */
	private   java.util.Date   tailTimeEnd ;
	/**
	 * 拜访时间段
	 */
	private   String    tailTimeStartAndEnd ;
   
   
   /**
     * 拜访人姓名
   */
	private   String   accessName ;
   
   
   /**
     * 拜访人电话
   */
	private   String   accessMobile ;
   
   
   /**
     * 1代理商，2.食堂
   */
	private   Integer   accessType ;
   
   
   /**
     * 客户ID,根据access_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info表id
   */
	private   Long   accessAgentCanteenId ;
   
   
   /**
     * 创建时间
   */
	private   java.util.Date   createTime ;
   
   
   /**
     * 创建人id
   */
	private   Long   createUid ;
   
   
   
   /**
     * 修改时间
   */
	private   java.util.Date   updateTime ;
   
   
   /**
     * 修改人id
   */
	private   Long   updateUid ;
   
	   /**
    * 业务员id
    */
   private Long businessId;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 业务员姓名
	 */
	private String  businessName;

	/**
	 * 拜访时间段
	 */
	private String tailTimeQuantum;
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTailTimeQuantum() {
		return tailTimeQuantum;
	}

	public void setTailTimeQuantum(String tailTimeQuantum) {
		this.tailTimeQuantum = tailTimeQuantum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getTailNature() {
		return tailNature;
	}

	public void setTailNature(Integer tailNature) {
		this.tailNature = tailNature;
	}
    
	public String getTailNatureName() {
		return tailNatureName;
	}

	public void setTailNatureName(String tailNatureName) {
		this.tailNatureName = tailNatureName;
	}

	public java.util.Date getTailTimeStart() {
		return tailTimeStart;
	}

	public void setTailTimeStart(java.util.Date tailTimeStart) {
		this.tailTimeStart = tailTimeStart;
	}
	
	public String getTailTimeStartAndEnd() {
		return tailTimeStartAndEnd;
	}

	public void setTailTimeStartAndEnd(String tailTimeStartAndEnd) {
		this.tailTimeStartAndEnd = tailTimeStartAndEnd;
	}

	public java.util.Date getTailTimeEnd() {
		return tailTimeEnd;
	}

	public void setTailTimeEnd(java.util.Date tailTimeEnd) {
		this.tailTimeEnd = tailTimeEnd;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public String getAccessMobile() {
		return accessMobile;
	}

	public void setAccessMobile(String accessMobile) {
		this.accessMobile = accessMobile;
	}

	public Integer getAccessType() {
		return accessType;
	}

	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}

	public Long getAccessAgentCanteenId() {
		return accessAgentCanteenId;
	}

	public void setAccessAgentCanteenId(Long accessAgentCanteenId) {
		this.accessAgentCanteenId = accessAgentCanteenId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
