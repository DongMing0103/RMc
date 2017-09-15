package com.hd.kzscrm.service.param.canteen;

import java.util.List;

import com.hd.kzscrm.common.param.PageParam;
/**
 * 菜单图片表
 * @author Administrator
 *
 */
public class GoodImgParam extends PageParam {
	/**
	 * 主键  
	 */
	private Long id;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 商品Id
	 */
	private Long goodId;
	/**
	 * 菜单图片
	 */
	private String goodImg;
	/**
	 * 删除标识 （0删除 1存在）
	 */
	private Integer delFlag;
	
	/**
	 * 商品ID
	 */
	private List<Long> goodsIds;
	
	/**
	 * 图片名称
	 * @author jyt 2017年3月18日 下午5:48:05
	 */
	private String imgName;
	/**
	 * 用户手机号，用于验证TOKEN
	 */
	private String mobilephone;
	
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
	public List<Long> getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(List<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
}
