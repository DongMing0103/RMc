package com.hd.kzscrm.manager.filter;


import com.hd.wolverine.common.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常统一处理
 * Created by liuming on 2016/7/27.
 */
public class PortalExceptionHandler implements HandlerExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.error("Catch Exception:", ex);//捕捉漏网的异常日志
        if (ex instanceof BusinessException) {
            //设置编码
            response.setStatus(500);
            ////response.setCharacterEncoding("UTF-8");
            //服务器响应类型
            response.setContentType("text/html");
            //获得输出流
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                LOGGER.error("Catch Exception:", e);//捕捉漏网的异常日志
                out.print("捕获业务异常时,发生IO异常");
                out.close();
            }
            out.print(ex.getMessage());
            out.close();
        }
        // 放入异常信息
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);

        // 获取session中存放的静态文件域名
//        String staticUrl = (String) SecurityUtils.getSubject().getSession().getAttribute(LgCenterConstants.STATIC_DOMAIN_URL);

        // 可以根据不同的异常(ex)类型跳转到不同页面,便于以后扩展
//        return new ModelAndView("/error/error2", model).addObject("staticUrl", staticUrl);
        return null;
    }
}
