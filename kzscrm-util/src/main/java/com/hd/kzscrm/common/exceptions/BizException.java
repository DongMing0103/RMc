package com.hd.kzscrm.common.exceptions;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;

/**
 * 业务异常类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class BizException extends BaseException {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2653145822151544474L;

    public BizException(String errorCode, String errorMessage, Object... args) {
        super(errorCode, errorMessage, args);
    }

    public BizException(String errorCode, String errorMessage, Throwable cause, Object... args) {
        super(errorCode, errorMessage, cause, args);
    }

    public BizException(BaseExceptionEnum exceptionEnum, Object... args) {
        super(exceptionEnum, args);
    }

    public BizException(BaseExceptionEnum exceptionEnum, Throwable cause, Object... args) {
        super(exceptionEnum, cause, args);
    }
}
