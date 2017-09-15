package com.hd.kzscrm.service.vo.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hd.kzscrm.common.annotation.BeanRelevanceField;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;

@JsonInclude(Include.NON_EMPTY)
public class OrderGoodsItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
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
	 * 食堂类型
	 */
	private Integer canteenType;
	/**
	 * 商品id goods_info表
	 */
	@BeanRelevanceField(tableNames = { DatabaseTableNameEnum.goods_info,
			DatabaseTableNameEnum.goods_img }, tableFieldBorders = { "2", "3" }, tableFieldNames = {
			"good_no", "window_num", "goods_img" }, injectBeanFieldNames = {
			"goodsNo", "windowNum", "goodsLogo" },tableSearchFieldNames={"id","goods_id"})
	private Long goodsId;
	/**
	 * 商品编号
	 */
	private String goodsNo;
	/**
	 * 取餐窗口
	 */
	private Integer windowNum;
	/**
	 * 商品LOGO
	 */
	private String goodsLogo;
	/**
	 * 食材名
	 */
	private String materialName;
	/**
	 * 食材采购份数
	 */
	private Long materialNum;
	/**
	 * 食材采购重量g
	 */
	private Long materialPurchase;
	/**
	 * 就餐时间
	 */
	private Date eatTime;
	private String eatTimeStr;
	/**
	 * 供应类目Id
	 */
	private Long supplyCategoryId;
	/**
	 * 供应类目名
	 */
	private String supplyCategoryName;
	/**
	 * 订单Id集合
	 */
	private String orderIds;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 订单创建时间
	 */
	private Date createTime;
	private String createTimeStr;
	/**
	 * 品种名
	 */
	private String varietieFoodName;
	/**
	 * 消费状态
	 */
	private Integer consumeStatus;// 消费状态，1.食堂就餐，2.外来用餐，3.外卖
	/**
	 * 消费状态名
	 */
	private String consumeStatusName;
	/**
	 * 商品订单总价
	 */
	private BigDecimal orderTotalPrice;
	/**
	 * 订购详情
	 */
	private String orderDetails;

	/**
	 * 商品图片
	 * @author jyt 2017年3月21日 下午2:33:27
	 */
	private String goodsImg;
	
	/**商品Id*/
	private Long goods_id;
	
	/**商品编号*/
	private String goodNo;
	
	/**上架时间*/
	private  Date shelfTime; 
	
	/**商家名字*/
	private String canteenName;
	
	/**商家id*/
	private Long enterpriseId;
	
	
	/**销售额*/
	private Double goodsMoney;
	
	/**评价总额*/
	private Double starSum;
	/**商家id*/
	private Long canteenId;
	/**
	 * 批量导出
	 */
	private List<Long> idBatch;
	
	private List list;
	
	
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public List<Long> getIdBatch() {
		return idBatch;
	}
	public void setIdBatch(List<Long> idBatch) {
		this.idBatch = idBatch;
	}
	public Long getCanteenId() {
		return canteenId;
	}
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}
	public Double getStarSum() {
		return starSum;
	}
	public void setStarSum(Double starSum) {
		this.starSum = starSum;
	}
	public Double getGoodsMoney() {
		return goodsMoney;
	}
	public void setGoodsMoney(Double goodsMoney) {
		this.goodsMoney = goodsMoney;
	}
	
	
	public String getCanteenName() {
		return canteenName;
	}
	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Long getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	public Date getShelfTime() {
		return shelfTime;
	}
	public void setShelfTime(Date shelfTime) {
		this.shelfTime = shelfTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSupplyCategoryName() {
		return supplyCategoryName;
	}
	public void setSupplyCategoryName(String supplyCategoryName) {
		this.supplyCategoryName = supplyCategoryName;
	}
	public String getEatTimeStr() {
		return eatTimeStr;
	}
	public void setEatTimeStr(String eatTimeStr) {
		this.eatTimeStr = eatTimeStr;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getConsumeStatusName() {
		return consumeStatusName;
	}
	public void setConsumeStatusName(String consumeStatusName) {
		this.consumeStatusName = consumeStatusName;
	}
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getVarietieFoodName() {
		return varietieFoodName;
	}
	public void setVarietieFoodName(String varietieFoodName) {
		this.varietieFoodName = varietieFoodName;
	}
	public Integer getConsumeStatus() {
		return consumeStatus;
	}
	public void setConsumeStatus(Integer consumeStatus) {
		this.consumeStatus = consumeStatus;
	}
	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public String getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
	public Date getEatTime() {
		return eatTime;
	}
	public void setEatTime(Date eatTime) {
		this.eatTime = eatTime;
	}
	public Long getSupplyCategoryId() {
		return supplyCategoryId;
	}
	public void setSupplyCategoryId(Long supplyCategoryId) {
		this.supplyCategoryId = supplyCategoryId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Long getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(Long materialNum) {
		this.materialNum = materialNum;
	}
	public Long getMaterialPurchase() {
		return materialPurchase;
	}
	public void setMaterialPurchase(Long materialPurchase) {
		this.materialPurchase = materialPurchase;
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
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public Integer getWindowNum() {
		return windowNum;
	}
	public void setWindowNum(Integer windowNum) {
		this.windowNum = windowNum;
	}
	public String getGoodsLogo() {
		return goodsLogo;
	}
	public void setGoodsLogo(String goodsLogo) {
		this.goodsLogo = goodsLogo;
	}
	/**
	 * @return the canteenType
	 */
	public Integer getCanteenType() {
		return canteenType;
	}
	/**
	 * @param canteenType the canteenType to set
	 */
	public void setCanteenType(Integer canteenType) {
		this.canteenType = canteenType;
	}

	
}
