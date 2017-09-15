package com.hd.kzscrm.service.param.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hd.kzscrm.common.param.PageParam;

public class CrmBusinessOrderParam extends PageParam {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 订单实际金额
	 */
	private BigDecimal orderRealMoney;
	
	/**
	 * 业务员ID
	 */
	private Long businessId;
	private List<Long> businessIds;
	/**
	 * 食堂ID
	 */
	private Long canteenId;
	/**
	 * 食堂编号
	 */
	private String canteenNo;
	/**
	 * 实际金额
	 */
	private BigDecimal realMoney;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建人
	 */
	private Long createUid;
	/**
	 * 是否允许删除 0删 1正常
	 */
	private Integer delFlag;
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 修改人
	 */
	private Long updateUid;
	/**工作月*/
	private Date applyMonth;
	
	public Date getApplyMonth() {
		return applyMonth;
	}

	public void setApplyMonth(Date applyMonth) {
		this.applyMonth = applyMonth;
	}

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getOrderRealMoney() {
		return orderRealMoney;
	}

	public void setOrderRealMoney(BigDecimal orderRealMoney) {
		this.orderRealMoney = orderRealMoney;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

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
	 * 订单ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return 订单ID
	 */
	public Long getOrderId() {
		return this.orderId;
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

	/**
	 * 食堂编号
	 */
	public void setCanteenNo(String canteenNo) {
		this.canteenNo = canteenNo;
	}

	/**
	 * @return 食堂编号
	 */
	public String getCanteenNo() {
		return this.canteenNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	/**
	 * 是否允许删除 0删 1正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return 是否允许删除 0删 1正常
	 */
	public Integer getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 修改时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 修改时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 修改人
	 */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}

	/**
	 * @return 修改人
	 */
	public Long getUpdateUid() {
		return this.updateUid;
	}

}
