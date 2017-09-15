package com.hd.kzscrm.manage.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hd.wolverine.common.BusinessException;

/**
 * 异常统一处理
 * @since 2016-11-1
 */
@Component
public class ExceptionInterceptor implements HandlerExceptionResolver {
   private static final Logger LOGGER= LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if(ex instanceof BusinessException){
            //设置编码
            response.setStatus(500);
            response.setCharacterEncoding("UTF-8");
            //服务器响应类型
            response.setContentType("text/html");
            //获得输出流
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                LOGGER.error("Catch Exception:",e);//捕捉漏网的异常日志
            } finally {
            	if (out != null) {
	            	out.print(ex.getMessage());
	            	out.close();
            	}
            }
            return null;
        }
        LOGGER.error("Catch Exception:",ex);//捕捉漏网的异常日志
        return null;
    }
}
