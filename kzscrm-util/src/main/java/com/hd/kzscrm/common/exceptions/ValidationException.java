package com.hd.kzscrm.common.exceptions;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;

/**
 * 校验异常类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class ValidationException extends BaseException {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2653145822151544474L;

    public ValidationException(String errorCode, String errorMessage, Object... args) {
        super(errorCode, errorMessage, args);
    }

    public ValidationException(String errorCode, String errorMessage, Throwable cause, Object... args) {
        super(errorCode, errorMessage, cause, args);
    }

    public ValidationException(BaseExceptionEnum exceptionEnum, Object... args) {
        super(exceptionEnum, args);
    }

    public ValidationException(BaseExceptionEnum exceptionEnum, Throwable cause, Object... args) {
        super(exceptionEnum, cause, args);
    }
}
