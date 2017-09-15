package com.hd.kzscrm.service.param.business;

import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmBusinessCanteenParam extends PageParam {

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 业务员ID crm_business
	 */
	private Long businessId;
	private List<Long> businessIds;
	/**
	 * 商家ID cantenn_base_info
	 */
	private Long canteenId;
	/**
	 * 食堂类型 1.厂内食堂，2.校内食堂，3.独立食堂
	 */
	private Integer canteenType;
	/**创建时间*/
	private Date createTime;
	/**
	 * 删除标识 1.存在0.删除
	 */
	private Integer delFlag;
	/**
	 * 工作月
	 */
	private Date applyMonth;

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	public Date getApplyMonth() {
		return applyMonth;
	}

	public void setApplyMonth(Date applyMonth) {
		this.applyMonth = applyMonth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 业务员ID crm_business
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return 业务员ID crm_business
	 */
	public Long getBusinessId() {
		return this.businessId;
	}

	/**
	 * 商家ID cantenn_base_info
	 */
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	/**
	 * @return 商家ID cantenn_base_info
	 */
	public Long getCanteenId() {
		return this.canteenId;
	}

	/**
	 * 食堂类型 1.厂内食堂，2.校内食堂，3.独立食堂
	 */
	public void setCanteenType(Integer canteenType) {
		this.canteenType = canteenType;
	}

	/**
	 * @return 食堂类型 1.厂内食堂，2.校内食堂，3.独立食堂
	 */
	public Integer getCanteenType() {
		return this.canteenType;
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

}
