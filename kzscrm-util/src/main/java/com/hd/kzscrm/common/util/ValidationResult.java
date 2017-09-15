package com.hd.kzscrm.common.util;

import java.util.Map;

/**
 * 校验结果类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class ValidationResult {

    //校验结果是否有错，默认校验通过
    private boolean hasErrors = false;

    //校验错误信息
    private Map<String, String> errorMsg;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        if (null == errorMsg) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String key : errorMsg.keySet()) {
            sb.append(errorMsg.get(key));
            sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg=" + errorMsg + "]";
    }

}
