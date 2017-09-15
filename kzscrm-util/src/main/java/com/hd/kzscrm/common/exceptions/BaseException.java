package com.hd.kzscrm.common.exceptions;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;

/**
 * 项目异常基类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class BaseException extends Exception {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -758729270479446205L;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误参数,使用<code>String.format()</code>的方式替换errorMessage中的特殊字符
     * <p>%s:字符串</p>
     * <p>%b:布尔类型，如true</p>
     * <p>%d:整数类型(十进制)</p>
     * <p>%f:浮点类型</p>
     * <p>%tF%n:年-月-日格式日期</p>
     * <p></p>
     */
    private Object[] args;

    public BaseException(String errorCode, String errorMessage, Object... args) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.args = args;
    }

    public BaseException(String errorCode, String errorMessage, Throwable cause, Object... args) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.args = args;
    }

    public BaseException(BaseExceptionEnum exceptionEnum, Object... args) {
        this.errorCode = exceptionEnum.getCode();
        this.errorMessage = exceptionEnum.getMsg();
        this.args = args;
    }

    public BaseException(BaseExceptionEnum exceptionEnum, Throwable cause, Object... args) {
        super(cause);
        this.errorCode = exceptionEnum.getCode();
        this.errorMessage = exceptionEnum.getMsg();
        this.args = args;
    }

    public String getMessage() {
        return String.format(errorMessage, args);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

}

