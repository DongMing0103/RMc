package com.hd.kzscrm.dao.entity.business;

import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

@Entity
@Table(name="canteen_health_pic")
public class CanteenHealthPicPO {
	
	/**
	 * 主键ID
	 */
	@Column(name="id")
	@Id
	private Long id;
	
	/**
	 * 餐厅ID
	 */
	@Column(name="canteen_id")
	private Long canteenId;
	
	/**
	 * 卫生许可证
	 */
	@Column(name="business_health_pic")
	private String  businessHealthPic;
	
	/**
	 * 创建人
	 */
	@Column(name="creater_uid")
	private Integer createrUid;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 备注
	 */
	@Column(name="remark")
	private String remark;
	/**
	 * 是否允许删除 
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	
	/**
	 * crm_canteen_base_info的主键Id
	 */
	@Column(name="crm_canteen_id")
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
