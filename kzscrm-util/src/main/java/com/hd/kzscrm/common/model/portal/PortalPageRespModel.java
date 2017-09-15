package com.hd.kzscrm.common.model.portal;

import org.apache.commons.lang.StringUtils;

/**
 * 公共分页model封装
 *
 * @author kzs admin
 */
public class PortalPageRespModel extends PortalRespModel {

	/**
	 * 记录总数
	 */
	private int totalSize;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 当前页码
	 */
	private int currentPage;

	/**
	 * 每页多少条
	 */
	private int pageSize;

	public PortalPageRespModel() {
	}

	public PortalPageRespModel(int code, String desc) {
		super(code, desc);
	}

	public PortalPageRespModel(int code, String desc, Object rows) {
		super(code, desc, rows);
	}

	public PortalPageRespModel(int code, String desc, Object rows, Integer totalSize, Integer totalPage, Integer currentPage,
			Integer pageSize) {
		super(code, desc, rows);
		this.totalSize = totalSize;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public static PortalPageRespModel success(Object rows, Integer totalSize, Integer totalPage, Integer currentPage, Integer pageSize) {
		return new PortalPageRespModel(RespCode.SUCCESS.getStatusCode(), RespCode.SUCCESS.getDesc(), rows, totalSize, totalPage,
				currentPage, pageSize);
	}

	public static PortalPageRespModel failure(String msg) {
		msg = StringUtils.isNotBlank(msg) ? RespCode.SYS_EXCEPTION.getDesc() : msg;
		return new PortalPageRespModel(RespCode.SYS_EXCEPTION.getStatusCode(), msg);
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
