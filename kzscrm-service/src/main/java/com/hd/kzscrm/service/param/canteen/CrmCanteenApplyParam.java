package com.hd.kzscrm.service.param.canteen;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hd.kzscrm.common.param.PageParam;
import com.hd.kzscrm.service.param.agent.CrmSplitAssignSetParam;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CrmCanteenApplyParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 申请时间
	 */
	private String applyTime;
	/**
	 * 审核状态,0.申请中，1.申请通过，2.申请失败
	 */
	private Integer checkStatus;
	/**
	 * 审核人ID
	 */
	private Long checkUserId;
	/**
	 * 审核时间
	 */
	private String checkTime;
	/**
	 * 食堂ID
	 */
	private Long canteenId;
	/**
	 * 客户资源表Id
	 */
	private   Long   clientId ;
	/**
	 * 入住时间
	 */
	private Date enterTime;
	private String enterTimeStr;
	/**
	 * 食堂分账比例
	 */
	private BigDecimal canteenSplitPercent;
	/**
	 * 合同图片路径
	 */
	private String contractImgPath;
	/**
	 * 分帐配置
	 */
	private CrmSplitAssignSetParam crmSplitAssignSetParam;
	
	/**
	 * 创建人id(当前操作人/登录人Id)
	 */
	private   Long   createUid ;
	
	private Integer delFlag;

	/**
	 * 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 主键
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 申请时间
	 */
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * @return 申请时间
	 */
	public String getApplyTime() {
		return this.applyTime;
	}

	/**
	 * 审核状态
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * @return 审核状态
	 */
	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	/**
	 * 审核人ID
	 */
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	/**
	 * @return 审核人ID
	 */
	public Long getCheckUserId() {
		return this.checkUserId;
	}

	/**
	 * 审核时间
	 */
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * @return 审核时间
	 */
	public String getCheckTime() {
		return this.checkTime;
	}

	/**
	 * 食堂ID
	 */
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	/**
	 * @return 食堂ID
	 */
	public Long getCanteenId() {
		return this.canteenId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public String getEnterTimeStr() {
		return enterTimeStr;
	}

	public void setEnterTimeStr(String enterTimeStr) {
		this.enterTimeStr = enterTimeStr;
	}

	public BigDecimal getCanteenSplitPercent() {
		return canteenSplitPercent;
	}

	public void setCanteenSplitPercent(BigDecimal canteenSplitPercent) {
		this.canteenSplitPercent = canteenSplitPercent;
	}

	public String getContractImgPath() {
		return contractImgPath;
	}

	public void setContractImgPath(String contractImgPath) {
		this.contractImgPath = contractImgPath;
	}

	public Long getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return the crmSplitAssignSetParam
	 */
	public CrmSplitAssignSetParam getCrmSplitAssignSetParam() {
		return crmSplitAssignSetParam;
	}

	/**
	 * @param crmSplitAssignSetParam the crmSplitAssignSetParam to set
	 */
	public void setCrmSplitAssignSetParam(
			CrmSplitAssignSetParam crmSplitAssignSetParam) {
		this.crmSplitAssignSetParam = crmSplitAssignSetParam;
	}

}
