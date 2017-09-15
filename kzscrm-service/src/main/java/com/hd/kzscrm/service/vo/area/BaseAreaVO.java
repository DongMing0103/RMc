package com.hd.kzscrm.service.vo.area;


public class BaseAreaVO {

	/**
	 * 主键，地区代码前2码为国家， 第3-4码为大区， 5-6位表示省级编码 7-8码市级编码
	 */
	private Long areaCode;
	/**
	 * 地区名称
	 */
	private String areaName;
	/**
	 * 级别 0国家 1大区 2省级 3地市级 4县市级
	 */
	private Long areaLevel;
	/**
	 * 父code
	 */
	private Long parentCode;
	/**
	 * LANGUAGEコード
	 */
	private Long languageCode;
	/**
	 * 追加時間
	 */
	private String createTime;
	/**
	 * 追加ID
	 */
	private Long createId;
	/**
	 * 更新時間
	 */
	private String updateTime;
	/**
	 * 更新ID
	 */
	private Long updateId;
	/**
	 * 削除フラグ
	 */
	private String deleteFlag;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 主键，地区代码前2码为国家， 第3-4码为大区， 5-6位表示省级编码 7-8码市级编码
	 */
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return 主键，地区代码前2码为国家， 第3-4码为大区， 5-6位表示省级编码 7-8码市级编码
	 */
	public Long getAreaCode() {
		return this.areaCode;
	}

	/**
	 * 地区名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return 地区名称
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * 级别 0国家 1大区 2省级 3地市级 4县市级
	 */
	public void setAreaLevel(Long areaLevel) {
		this.areaLevel = areaLevel;
	}

	/**
	 * @return 级别 0国家 1大区 2省级 3地市级 4县市级
	 */
	public Long getAreaLevel() {
		return this.areaLevel;
	}

	/**
	 * 父code
	 */
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return 父code
	 */
	public Long getParentCode() {
		return this.parentCode;
	}

	/**
	 * LANGUAGEコード
	 */
	public void setLanguageCode(Long languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return LANGUAGEコード
	 */
	public Long getLanguageCode() {
		return this.languageCode;
	}

	/**
	 * 追加時間
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 追加時間
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 追加ID
	 */
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	/**
	 * @return 追加ID
	 */
	public Long getCreateId() {
		return this.createId;
	}

	/**
	 * 更新時間
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 更新時間
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 更新ID
	 */
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	/**
	 * @return 更新ID
	 */
	public Long getUpdateId() {
		return this.updateId;
	}

	/**
	 * 削除フラグ
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * @return 削除フラグ
	 */
	public String getDeleteFlag() {
		return this.deleteFlag;
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

}
