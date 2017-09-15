package com.hd.kzscrm.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.net.HttpHeaders;
import com.hd.wolverine.common.log.LogHelper;
import com.hd.kzscrm.common.model.vo.ResponseVo;

/**
 * @Filename WebUtils.java
 * @Data 2016-10-18 11:34
 * @Author xing.shi
 */
public abstract class WebUtils {
    private static final LogHelper logHelper = LogHelper.getLogger(WebUtils.class);

    /**
     * 判断是否为XHR请求
     * @param request
     * @return
     */
    public static boolean isXhr(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.X_REQUESTED_WITH) != null;
    }


    /**
     * 返回Ajax请求
     * @param response
     * @param responseVo
     */
    public static void responseAjax(HttpServletResponse response, ResponseVo responseVo) {
        try {
            String result = JSON.toJSONString(responseVo);
            response.getOutputStream().write(result.getBytes("UTF-8"));
            response.getOutputStream().flush();
        } catch (IOException e) {
            logHelper.getBuilder().error("Ajax返回值异常", e);
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                logHelper.getBuilder().error("写流关闭失败", e);
            }
        }
    }
}
