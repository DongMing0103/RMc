package com.hd.kzscrm.service.vo.agent;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hd.kzscrm.service.vo.BaseVO;

public class CrmWorkTargetVO implements BaseVO {
	 /**
     * 主键
   */
   private   Long   id ;
   /**
    * 删除标识 0.删除，1.存在
    */
   private Integer delFlag;
   /**
     * 工作月
   */
   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private   Date   applyMonth ;
   
   /**
	 * 订单金额
	 */
	private BigDecimal orderMoney;
	/**
	 * 订单额完成量
	 */
	private BigDecimal completeOrderMoney;
	/**完成百分比*/
	private String percentageCompletion;
	
   /**
     * 发展代理商数量
   */
	private   Long   agentNum ;
   /**
    * 发展代理商完成量
    */
   private Long completeAgentNum;
   /**
     * 发展食堂数量
   */
	private   Long   canteenNum ;
   /**
    * 发展食堂完成量
    */
	private Long completeCanteenNum;
   
   /**
     * 申请人
   */
	private   Long   applyUserId ;
	/**
	 * 申请人姓名
	 */
	private   String   applyUserName;
   
   
   /**
     * 更新人
   */
	private   Long   updateId ;
   
   
   /**
     * 创建时间
   */
	private   java.util.Date   creatTime ;
   
   
   /**
     * 更新时间
   */
	private   java.util.Date   updateTime ;
   
   
   /**
     * 申请状态(1.申请中 2通过)
   */
	private   Integer   applyStatus ;
   
   
   /**
     * 目标类型(1.团队 2.个人，3.代理商)
   */
	private   Integer   targetType ;
	/**
	 * 团队id
	 */
	private Long teamId;
	
	/**
	 * 业务员Id
	 */
	private Long businessId;
	
	/**
	 * 业务员名称
	 */
	private String businessName;
	/**
	 * 代理商id
	 */
	private Long agentId;
	
	/**
	 * 代理商名称
	 */
	private String agentName;
	
	public Long getBusinessId() {
		return businessId;
	}


	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}


	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public Long getAgentId() {
		return agentId;
	}


	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}


	public String getAgentName() {
		return agentName;
	}


	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	/**
	 * 团队名称
	 */
	private String teamName;
	
	
	public String getPercentageCompletion() {
		return percentageCompletion;
	}


	public void setPercentageCompletion(String percentageCompletion) {
		this.percentageCompletion = percentageCompletion;
	}


	public BigDecimal getCompleteOrderMoney() {
		return completeOrderMoney;
	}


	public void setCompleteOrderMoney(BigDecimal completeOrderMoney) {
		this.completeOrderMoney = completeOrderMoney;
	}


//get set 方法
   /**
     * 主键
   */
   public void setId(Long id){
     this.id=id;    
   }
    
   
   public Long getTeamId() {
	return teamId;
}


public void setTeamId(Long teamId) {
	this.teamId = teamId;
}


public String getTeamName() {
	return teamName;
}


public void setTeamName(String teamName) {
	this.teamName = teamName;
}


/**
     * @return 主键
   */
   public Long getId(){ 
     return this.id;    
   } 
   /**
     * 工作月
   */
   public void setApplyMonth(java.util.Date applyMonth){
     this.applyMonth=applyMonth;    
   }
    
   /**
     * @return 工作月
   */
   public java.util.Date getApplyMonth(){ 
     return this.applyMonth;    
   } 
   /**
     * 发展代理商数量
   */
   public void setAgentNum(Long agentNum){
     this.agentNum=agentNum;    
   }
    
   /**
     * @return 发展代理商数量
   */
   public Long getAgentNum(){ 
     return this.agentNum;    
   } 
   /**
     * 发展食堂数量
   */
   public void setCanteenNum(Long canteenNum){
     this.canteenNum=canteenNum;    
   }
    
   /**
     * @return 发展食堂数量
   */
   public Long getCanteenNum(){ 
     return this.canteenNum;    
   } 
   /**
     * 申请人
   */
   public void setApplyUserId(Long applyUserId){
     this.applyUserId=applyUserId;    
   }
    
   /**
     * @return 申请人
   */
   public Long getApplyUserId(){ 
     return this.applyUserId;    
   } 
   /**
     * 更新人
   */
   public void setUpdateId(Long updateId){
     this.updateId=updateId;    
   }
    
   /**
     * @return 更新人
   */
   public Long getUpdateId(){ 
     return this.updateId;    
   } 
   /**
     * 创建时间
   */
   public void setCreatTime(java.util.Date creatTime){
     this.creatTime=creatTime;    
   }
    
   /**
     * @return 创建时间
   */
   public java.util.Date getCreatTime(){ 
     return this.creatTime;    
   } 
   /**
     * 更新时间
   */
   public void setUpdateTime(java.util.Date updateTime){
     this.updateTime=updateTime;    
   }
    
   /**
     * @return 更新时间
   */
   public java.util.Date getUpdateTime(){ 
     return this.updateTime;    
   } 
   /**
     * 申请状态(1.申请中 2通过)
   */
   public void setApplyStatus(Integer applyStatus){
     this.applyStatus=applyStatus;    
   }
    
   /**
     * @return 申请状态(1.申请中 2通过)
   */
   public Integer getApplyStatus(){ 
     return this.applyStatus;    
   } 
   /**
     * 目标类型(1.团队 2.个人)
   */
   public void setTargetType(Integer targetType){
     this.targetType=targetType;    
   }
    
   /**
     * @return 目标类型(1.团队 2.个人)
   */
   public Integer getTargetType(){ 
     return this.targetType;    
   }


	public Integer getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return the orderMoney
	 */
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	/**
	 * @param orderMoney the orderMoney to set
	 */
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}


	/**
	 * @return the applyUserName
	 */
	public String getApplyUserName() {
		return applyUserName;
	}


	/**
	 * @param applyUserName the applyUserName to set
	 */
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}


	public Long getCompleteAgentNum() {
		return completeAgentNum;
	}


	public void setCompleteAgentNum(Long completeAgentNum) {
		this.completeAgentNum = completeAgentNum;
	}


	public Long getCompleteCanteenNum() {
		return completeCanteenNum;
	}


	public void setCompleteCanteenNum(Long completeCanteenNum) {
		this.completeCanteenNum = completeCanteenNum;
	}


}
