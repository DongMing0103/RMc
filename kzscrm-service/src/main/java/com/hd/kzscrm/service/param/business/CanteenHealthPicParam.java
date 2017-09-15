package com.hd.kzscrm.service.param.business;

import java.util.Date;

/**
 * 商家卫生许可证
 * @author lcl
 *	2017年5月12日
 */
public class CanteenHealthPicParam {
	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 餐厅ID
	 */
	private Long canteenId;
	
	/**
	 * 卫生许可证
	 */
	private String businessHealthPic;
	
	/**
	 * 创建人
	 */
	private Integer createrUid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否允许删除 
	 */
	private Integer delFlag;
	
	/**
	 * crm_canteen_base_info的主键Id
	 */
	private Long crmCanteenId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCanteenId() {
		return canteenId;
	}
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}
	public String getBusinessHealthPic() {
		return businessHealthPic;
	}
	public void setBusinessHealthPic(String businessHealthPic) {
		this.businessHealthPic = businessHealthPic;
	}
	public Integer getCreaterUid() {
		return createrUid;
	}
	public void setCreaterUid(Integer createrUid) {
		this.createrUid = createrUid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Long getCrmCanteenId() {
		return crmCanteenId;
	}
	public void setCrmCanteenId(Long crmCanteenId) {
		this.crmCanteenId = crmCanteenId;
	}
}
