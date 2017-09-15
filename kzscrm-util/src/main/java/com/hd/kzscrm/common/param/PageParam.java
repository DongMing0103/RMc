package com.hd.kzscrm.common.param;

import java.util.LinkedHashMap;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分页参数封装 PS：属性名的命名耦合了bootstrap-table前端表格分页插件，让人很不爽
 *
 * @author kzs admin
 */
public class PageParam {

	/**
	 * 分页大小
	 */
	private int limit = 20;

	/**
	 * 索引位置，从0开始
	 */
	private int offset = 0;

	/**
	 * asc或desc
	 */
	private String order;

	/**
	 * 排序字段，PO中的属性名，尝试过在页面自定义排序字段为数据库中的表字段名，但bootstrap-table貌似无法支持
	 */
	private String sort;

	private LinkedHashMap sortConditionMap = new LinkedHashMap();
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void addOrder(String orderFiled, String orderType) {
		sortConditionMap.put(orderFiled, orderType);
	}

	public LinkedHashMap getSortConditionMap() {
		return sortConditionMap;
	}

}
