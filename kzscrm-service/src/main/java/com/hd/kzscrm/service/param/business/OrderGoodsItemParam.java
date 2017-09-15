package com.hd.kzscrm.service.param.business;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hd.kzscrm.common.annotation.BeanRelevanceField;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.param.PageParam;

/**
 * 
 * @author 黄霄仪
 *
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderGoodsItemParam extends PageParam {
	
	private Long id;
	/**
	 * token
	 */
	private String userToken;
	/**
	 * 手机号
	 */
	private String mobilephone;
	/**
	 * 订单附属Id集合
	 */
	private List<Long> ids;
	 /**
	  * 订单Id
	  */
	private Long canteenOrderId;
	/**
	 * 商品数量
	 */
	private Integer goodsNum;
	/**
	 * 商品价格
	 */
	private BigDecimal goodsPrice;
	/**
	 * 包装费用
	 */
	private BigDecimal packagePrice;
	/**
	 * 商品名
	 */
	private String goodsName;
	/**
	 * 删除标识 0 删除 1 存在
	 */
	private Integer delFlag;
	/**
	 * 商品id
	 */
	@BeanRelevanceField(tableNames = { DatabaseTableNameEnum.goods_info}, tableFieldBorders = { "1" }, tableFieldNames = {
			"good_no","name" }, injectBeanFieldNames = {"goodsNo","goodsName"})
	private Long goodsId;
	/**
	 * 商品编号
	 */
	private String goodsNo;
	/**
	 * 订单id集合
	 */
	private List<Long> orderIds;
	
	/** 
	* 菜品分析  排序状态
	*/ 
	private String sortDesc;
	
	public String getSortDesc() {
		return sortDesc;
	}
	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public List<Long> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<Long> orderIds) {
		this.orderIds = orderIds;
	}
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
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
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
