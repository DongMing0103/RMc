package com.hd.kzscrm.common.model;

import org.springframework.util.StringUtils;

/**
 * 公共model封装
 *
 * @author kzs admin
 */
public class RespModel {

	public enum RespCode {

		SUCCESS(0, "操作成功"), SYS_EXCEPTION(-1, "系统异常"), NO_PRIVILEGE(-2, "没有权限"), PARAM_EXCEPTION(-3, "参数异常"),

		ALREADY_EXISTS(-4, "已经存在"), NOT_DATA(-5, "没有数据"), FIRST_STOP(-6, "由于一级分类已停用，该二级分类无法启用！"), PARAMETER_NULL(-7, "失败！");

		private final int code;

		private final String desc;

		RespCode(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public int getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public int getStatusCode() {
			return code;
		}
	}

	/**
	 * 响应码
	 */
	private int code = RespCode.SUCCESS.getCode();

	/**
	 * 响应码描述
	 */
	private String desc = RespCode.SUCCESS.getDesc();

	/**
	 * 具体数据
	 */
	private Object rows;

	public RespModel() {
	}

	public RespModel(Object rows) {
		this.rows = rows;
	}

	public RespModel(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public RespModel(int code, String desc, Object rows) {
		this.code = code;
		this.desc = desc;
		this.rows = rows;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public static RespModel setRespCode(RespCode respCode) {
		return new RespModel(respCode.getCode(), respCode.getDesc());
	}
	public static RespModel success(String msg) {
		msg = StringUtils.isEmpty(msg) ? RespCode.SUCCESS.getDesc() : msg;
		return new RespModel(RespCode.SUCCESS.getCode(), msg);
	}

	public static RespModel failure(String msg) {
		msg = StringUtils.isEmpty(msg) ? RespCode.SYS_EXCEPTION.getDesc() : msg;
		return new RespModel(RespCode.SYS_EXCEPTION.getCode(), msg);
	}
}
