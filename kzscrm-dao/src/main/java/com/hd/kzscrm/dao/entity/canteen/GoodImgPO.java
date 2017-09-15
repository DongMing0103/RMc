package com.hd.kzscrm.dao.entity.canteen;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 菜单图片表
 * @author lcl
 *
 */
@Entity
@Table(name="goods_img")
public class GoodImgPO {

	 /**主键*/
    @Column(name="id")
    @Id
    private Long id;
    
    /**商品ID*/
    @Column(name="goods_id")
    private Long goodId;
    /**菜单图片*/
    @Column(name="goods_img")
    private String goodImg;
    /**删除标识（0 删除  1存在）*/
    @Column(name="del_flag")
    private Integer delFlag;
    
	/**
	 * 图片名称
	 * @author jyt 2017年3月18日 下午5:48:05
	 */
    @Column(name="img_name")
	private String imgName;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodId() {
		return goodId;
	}
	public void setGoodId(Long goodId) {
		this.goodId = goodId;
	}
	public String getGoodImg() {
		return goodImg;
	}
	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
}
