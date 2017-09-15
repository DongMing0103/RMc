package com.hd.kzscrm.common.model.portal;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 公共model封装
 *
 * @author kzs admin
 */
public class PortalRespModel {
	/**
	 * 	200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
		201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
		202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
		204 NO CONTENT - [DELETE]：用户删除数据成功。
		400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
		401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
		403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
		404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
		406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
		410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
		422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
		500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
	 *  
	 * 返回状态码
	 * @since  2016年11月24日 下午3:10:37
	 * @author zhouguobao
	 * @history
	 */
    public enum RespCode {
        SUCCESS(200, "操作成功"),
        SYS_EXCEPTION(400, "系统异常"),
        NO_PRIVILEGE(401, "没有权限"),
        NOT_FOUND(404, "资源不存在"),
        PARAM_EXCEPTION(406, "参数异常"),//用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）
        GONE_EXCEPTION(410, "资源被删除"),//用户请求的资源被永久删除，且不会再得到的
        VALIDATE_EXCEPTION(422, "校验失败"),//当创建一个对象时，发生一个验证错误
        SERVER_EXCEPTION(500, "系统异常"),//服务器发生错误，用户将无法判断发出的请求是否成功
        USERINFO_NOT_EXISTS(700,"用户信息不存在"),
        BALANCE_INSUFFICIENT(701,"余额不足"),
        PAY_PASSWORD_EXISTS(702,"支付密码已存在"),
        ADD_PAY_PASSWORD_FAIL(703,"新增支付密码失败"),
        UPDATE_PAY_PASSWORD_FAIL(704,"修改支付密码失败"),
        PAY_PASSWORD_ERROR(705,"支付密码错误"),
        AUTH_CODE_TIMEOUT(706,"验证码超时"),
        AUTH_CODE_ERROR(707,"验证码输入有误"),
        NO_AUTH_CODE_INFO(708,"没有验证码信息"),
        ORDER_PAY_EMPTY(709,"订单编号为空"),
        PAY_PASSWORD_MODIFY_SUCCESS(710,"支付密码修改成功"),
        NO_SUPPORT_BALANCE(711,"不支持余额支付"),
        TOKEN_ERROR(712,"token验证错误"),
        PARAMETER_NULL(713,"参数为空"),
        ORDER_PAY_NO_EMPTY(714,"订单编号不存在"),
        APP_ONLY_PERSON(715,"只允许个人用户登录手机APP"),
        ORDER_NUM_ERROR(716,"订单数有误"),
        SUPPLY_TIME_IS_NULL(717,"供应时间没有配置"),
        SOFTWAREVERSION_IS_NULL(718,"版本信息不存在"),
        SOFTWAREVERSION_NEWEST(719,"已经是最新版本"),
        NULL_POINTER(720,"空指针"),
        DATA_EXCEPTION(721,"数据异常"),
        CUSTOM_MESSAGE(722,"自定义消息的异常，desc由自定义"),
        USER_DELETE_OR_FREEZE(723,"用户信息已删除或被注销"),
        USERNAME_OR_PASSWORD_CANNOT_BE_EMPTY(724,"用户名或密码不能为空"),
        ALREADY_COMMENTS_GOODS(725,"该商品已评价"),
        SOFTWAREVERSION_UPDATE(726,"有新版本，请更新"),
        SOFTWAREVERSION_NOT_EXISTS(727,"APP不存在该版本");

        private final int statusCode;

        private final String desc;

        RespCode(int statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 响应码
     */
    private int statusCode = RespCode.SUCCESS.getStatusCode();

    /**
     * 响应码描述
     */
    private String desc = RespCode.SUCCESS.getDesc();

    /**
     * 具体数据
     */
    @JsonInclude(Include.NON_EMPTY)
    private Object rows;

    public PortalRespModel() {
    }

    public PortalRespModel(Object rows) {
        this.rows = rows;
    }

    public PortalRespModel(int statusCode, String desc) {
        this.statusCode = statusCode;
        this.desc = desc;
    }

    public PortalRespModel(int statusCode, String desc, Object rows) {
        this.statusCode = statusCode;
        this.desc = desc;
        this.rows = rows;
    }
    
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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
	
	public static PortalRespModel success(String msg) {
    	msg = StringUtils.isNotBlank(msg) ? RespCode.SUCCESS.getDesc() : msg;
    	return new PortalRespModel(RespCode.SUCCESS.getStatusCode(), msg);
    }
	
	public static PortalRespModel success(Object rows) {
		return new PortalRespModel(RespCode.SUCCESS.getStatusCode(), RespCode.SUCCESS.getDesc(), rows);
	}
    
    public static PortalRespModel failure(String msg) {
    	msg = StringUtils.isNotBlank(msg) ? RespCode.SYS_EXCEPTION.getDesc() : msg;
    	return new PortalRespModel(RespCode.SYS_EXCEPTION.getStatusCode(), msg);
    }
}
