package com.hd.kzscrm.dao.entity.business;

import java.math.BigDecimal;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * 订单信息商品附属表（order表对应order_goods_item表为一对多关系）
 * @author Administrator
 *
 */
@Entity
@Table(name="order_goods_item")
public class OrderGoodsItemPO {
	@Column(name="id")
	@Id
	private Long id;
	 /**
	  * 订单Id
	  */
	@Column(name="canteen_order_id")
	private Long canteenOrderId;
	/**
	 * 商品数量
	 */
	@Column(name="goods_num")
	private Integer goodsNum;
	/**
	 * 商品价格
	 */
	@Column(name="goods_price")
	private BigDecimal goodsPrice;
	/**
	 * 包装费用
	 */
	@Column(name="package_price")
	private BigDecimal packagePrice;
	/**
	 * 商品名
	 */
	@Column(name="goods_name")
	private String goodsName;
	/**
	 * 删除标识 0 删除 1 存在
	 */
	@Column(name="del_flag")
	private Integer delFlag;
	/**
	 * 商品id
	 */
	@Column(name="goods_id")
	private Long goodsId;
	/**
	 * 商品编号
	 */
	@Column(name="goods_no")
	private String goodsNo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCanteenOrderId() {
		return canteenOrderId;
	}
	public void setCanteenOrderId(Long canteenOrderId) {
		this.canteenOrderId = canteenOrderId;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public BigDecimal getPackagePrice() {
		return packagePrice;
	}
	public void setPackagePrice(BigDecimal packagePrice) {
		this.packagePrice = packagePrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	

	
	
	
	
	
	
	
	
	
}
