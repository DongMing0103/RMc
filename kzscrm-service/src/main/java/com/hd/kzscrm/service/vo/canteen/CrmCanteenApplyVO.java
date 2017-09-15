package com.hd.kzscrm.service.vo.canteen;

import java.math.BigDecimal;
import java.util.Date;

import com.hd.kzscrm.service.vo.agent.CrmSplitAssignSetVO;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;

public class CrmCanteenApplyVO{
	 
    /**
      * 主键
    */
    private   Long   id ;
    
    private Integer delFlag;
    /**
      * 申请时间
    */
	private   java.util.Date   applyTime ;
    
    
    /**
      * 审核状态 ,0.申请中，1.申请通过，2.申请失败
    */
	private   Integer   checkStatus ;
	/**
	 * 客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
	 */
	private Integer clientType;
    
    
    /**
      * 审核人ID
    */
	private   Long   checkUserId ;
    
    
    /**
      * 审核时间
    */
	private   java.util.Date   checkTime ;
    
    
    /**
      * 食堂ID
    */
	private   Long   canteenId ;
    
	/**
	 * 客户资源表Id
	 */
	private   Long   clientId ;
	/**
	 * 入住时间
	 */
	private Date enterTime;
	
	/**
	 * 食堂分账比例
	 */
	private BigDecimal canteenSplitPercent;
	/**
	 * 客户资源
	 */
	private CrmClientResourceVO crmClientResourceVO;
	/**
	 * 分帐设置
	 */
	private CrmSplitAssignSetVO crmSplitAssignSetVO;
	
	/**
	 * 合同图片路径
	 */
	private String contractImgPath;
	
	/**
	 * 创建人id(当前操作人/登录人Id)
	 */
	private   Long   createUid ;
	
	private CrmCanteenBaseInfoVO crmCanteenBaseInfoVO;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the applyTime
	 */
	public java.util.Date getApplyTime() {
		return applyTime;
	}

	/**
	 * @param applyTime the applyTime to set
	 */
	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
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
	 * @return the checkUserId
	 */
	public Long getCheckUserId() {
		return checkUserId;
	}

	/**
	 * @param checkUserId the checkUserId to set
	 */
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	/**
	 * @return the checkTime
	 */
	public java.util.Date getCheckTime() {
		return checkTime;
	}

	/**
	 * @param checkTime the checkTime to set
	 */
	public void setCheckTime(java.util.Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * @return the canteenId
	 */
	public Long getCanteenId() {
		return canteenId;
	}

	/**
	 * @param canteenId the canteenId to set
	 */
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the enterTime
	 */
	public Date getEnterTime() {
		return enterTime;
	}

	/**
	 * @param enterTime the enterTime to set
	 */
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	/**
	 * @return the canteenSplitPercent
	 */
	public BigDecimal getCanteenSplitPercent() {
		return canteenSplitPercent;
	}

	/**
	 * @param canteenSplitPercent the canteenSplitPercent to set
	 */
	public void setCanteenSplitPercent(BigDecimal canteenSplitPercent) {
		this.canteenSplitPercent = canteenSplitPercent;
	}

	/**
	 * @return the contractImgPath
	 */
	public String getContractImgPath() {
		return contractImgPath;
	}

	/**
	 * @param contractImgPath the contractImgPath to set
	 */
	public void setContractImgPath(String contractImgPath) {
		this.contractImgPath = contractImgPath;
	}

	/**
	 * @return the createUid
	 */
	public Long getCreateUid() {
		return createUid;
	}

	/**
	 * @param createUid the createUid to set
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	/**
	 * @return the crmCanteenBaseInfoVO
	 */
	public CrmCanteenBaseInfoVO getCrmCanteenBaseInfoVO() {
		return crmCanteenBaseInfoVO;
	}

	/**
	 * @param crmCanteenBaseInfoVO the crmCanteenBaseInfoVO to set
	 */
	public void setCrmCanteenBaseInfoVO(CrmCanteenBaseInfoVO crmCanteenBaseInfoVO) {
		this.crmCanteenBaseInfoVO = crmCanteenBaseInfoVO;
	}

	/**
	 * @return the crmClientResourceVO
	 */
	public CrmClientResourceVO getCrmClientResourceVO() {
		return crmClientResourceVO;
	}

	/**
	 * @param crmClientResourceVO the crmClientResourceVO to set
	 */
	public void setCrmClientResourceVO(CrmClientResourceVO crmClientResourceVO) {
		this.crmClientResourceVO = crmClientResourceVO;
	}

	/**
	 * @return the crmSplitAssignSetVO
	 */
	public CrmSplitAssignSetVO getCrmSplitAssignSetVO() {
		return crmSplitAssignSetVO;
	}

	/**
	 * @param crmSplitAssignSetVO the crmSplitAssignSetVO to set
	 */
	public void setCrmSplitAssignSetVO(CrmSplitAssignSetVO crmSplitAssignSetVO) {
		this.crmSplitAssignSetVO = crmSplitAssignSetVO;
	}

	/**
	 * @return the clientType
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * @param clientType the clientType to set
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	
	
}
