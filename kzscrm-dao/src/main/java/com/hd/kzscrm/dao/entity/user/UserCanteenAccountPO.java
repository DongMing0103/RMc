package com.hd.kzscrm.dao.entity.user;

import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 用户商家关联（子账号）
 * @author jyt 2017年3月21日 下午8:09:43
 *
 */
@Entity
@Table(name="user_canteen_account")
public class UserCanteenAccountPO {
	/**
	 * ID
	 */
	@Id
	@Column(name="id")
	private Long id;
	/**
	 * 用户ID
	 */
	@Column(name="user_id")
	private Long userId;
	/**
	 * 食堂id
	 */
	@Column(name="canteen_id")
	private Long canteenId;
	/**
	 * 是否主账号 1 主账号  2 子账号
	 */
	@Column(name="is_main")
	private Integer isMain;
	/**
	 * 状态 1 正常  2 冻结
	 */
	@Column(name="status")
	private Integer status;
	
	/**
	 * 删除标识（0 删除  1存在）
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	
	/**
	 * 创建用户
	 * */
	@Column(name="create_uid")
	private Long createUid;
	
	/**
	 * 创建时间
	 * */
	@Column(name="create_time")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCanteenId() {
		return canteenId;
	}

	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}

	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Long getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
