package com.hd.kzscrm.common.model;

import com.hd.kzscrm.common.enums.StatusCodeEnum;


/**
 * 响应格式
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class CommResponse {
	
    /**
     * 返回状态
     *
     * @see StatusCodeEnum
     */
    private Integer statusCode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private Object data;

    public CommResponse() {
        this.statusCode = StatusCodeEnum.OK.getCode();
    }

    public CommResponse(Object data) {
        this.statusCode = StatusCodeEnum.OK.getCode();
        this.data = data;
    }

    public CommResponse(StatusCodeEnum statusCode, String message) {
        if (null != statusCode) {
            this.statusCode = statusCode.getCode();
        }
        this.message = message;
    }

    public CommResponse(Object data, StatusCodeEnum statusCode, String message) {
        this.data = data;
        if (null != statusCode) {
            this.statusCode = statusCode.getCode();
        }
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    /**
     * 功能说明：返回一个请求成功的响应模板
     *
     * @return CommResponse
     */
    public static CommResponse success() {
        CommResponse response = new CommResponse();
        response.setStatusCode(StatusCodeEnum.OK.getCode());
        response.setMessage(RESULT_MSG_SUCCESS);
        return response;
    }

    /**
     * 功能说明：返回一个请求成功的响应模板
     *
     * @param data
     * @return CommResponse
     */
    public static CommResponse success(Object data) {
        CommResponse response = new CommResponse(data);
        response.setStatusCode(StatusCodeEnum.OK.getCode());
        response.setMessage(RESULT_MSG_SUCCESS);
        return response;
    }

    /**
     * 功能说明：返回一个请求失败的响应模板
     *
     * @param message
     * @return CommResponse 错误类别为默认的INVALID_REQUEST（请求错误）
     */
    public static CommResponse failure(String message) {
        CommResponse response = new CommResponse();
        response.setMessage(message);
        response.setStatusCode(StatusCodeEnum.INVALID_REQUEST.getCode());
        return response;
    }

    /**
     * 功能说明：返回一个请求失败的响应模板
     *
     * @param e 异常参数不能为空
     * @return CommResponse
     */
    public static CommResponse failure(Exception e) {
        CommResponse response = new CommResponse();
        response.setMessage(RESULT_MSG_FAILURE);
        response.setStatusCode(StatusCodeEnum.INTERNAL_SERVER_ERROR.getCode());
        return response;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 错误返回结果msg
     */
    public static final String RESULT_MSG_FAILURE = "操作失败";

    /**
     * 正确返回结果msg
     */
    public static final String RESULT_MSG_SUCCESS = "操作成功";
}