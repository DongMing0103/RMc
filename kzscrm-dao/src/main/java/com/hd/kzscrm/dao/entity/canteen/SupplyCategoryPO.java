package com.hd.kzscrm.dao.entity.canteen;

import java.sql.Time;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 供应类目
 * @author wcf
 *
 */
@Entity
@Table(name = "canteen_time_category")
public class SupplyCategoryPO {

	/**
	 * 主键
	 */
	@Column(name = "id")
	@Id
	//@AutoIncrease
	private Long Id;
	
	/**
	 * 类目名
	 */
	@Column(name = "time_name")
	private String timeName;
	
	/**
	 * 来源类型（1 系统  2 自定义）
	 */
	@Column(name = "source_type")
	private Integer sourceType;
	
	/**
	 * 商家id
	 */
	@Column(name = "canteen_id")
	private Long canteenId;
	/**
	 *用餐开始时间
	 */
	@Column(name="eat_starttime")
	private Time eatStarttime;
	/**
	 * 用餐截止时间
	 */
	@Column(name="eat_endtime")
	private Time eatEndtime;
	/**
	 * 点餐截止时间
	 */
	@Column(name="order_endtime")
	private Time orderEndtime;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 是否删除 默认1
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	
	/**
	 * 供应类目ID
	 * */
	@Column(name="sys_time_category_id")
	private Long sysTimeCategoryId;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTimeName() {
		return timeName;
	}

	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Long getCanteenId() {
		return canteenId;
	}

	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Time getEatStarttime() {
		return eatStarttime;
	}

	public void setEatStarttime(Time eatStarttime) {
		this.eatStarttime = eatStarttime;
	}

	public Time getEatEndtime() {
		return eatEndtime;
	}

	public void setEatEndtime(Time eatEndtime) {
		this.eatEndtime = eatEndtime;
	}

	public Time getOrderEndtime() {
		return orderEndtime;
	}

	public void setOrderEndtime(Time orderEndtime) {
		this.orderEndtime = orderEndtime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getSysTimeCategoryId() {
		return sysTimeCategoryId;
	}

	public void setSysTimeCategoryId(Long sysTimeCategoryId) {
		this.sysTimeCategoryId = sysTimeCategoryId;
	}

	
}
