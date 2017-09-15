package com.hd.kzscrm.common.exceptions.handler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.hd.wolverine.common.i18n.TranslatorHelper;
import com.hd.wolverine.common.log.LogHelper;
import com.hd.kzscrm.common.model.vo.ResponseVo;
import com.hd.kzscrm.common.util.Consts;
import com.hd.kzscrm.common.util.WebUtils;

/**
 * 自定义异常处理类
 * 区别对待Ajax请求和页面请求
 * Ajax请求返回json格式
 *
 * @Filename AppHandlerExceptionResolver.java
 * @Data 2016-10-13 12:14
 * @Author xing.shi
 */
public class AppHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
    private final LogHelper logHelper = LogHelper.getLogger(this.getClass());

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logHelper.getBuilder().error(ex.getMessage(), ex);
        boolean isXhr = WebUtils.isXhr(request);
        try {
            // 后台校验错误，返回200
            if(ex instanceof BindException) {
                return handleBindException(isXhr, (BindException)ex, request, response, handler);
            }
            // 后台校验错误，返回200
            if(ex instanceof MethodArgumentNotValidException) {
                return handleMethodArgumentNotValidException(isXhr, (MethodArgumentNotValidException)ex, request, response, handler);
            }
            // 400 错误请求，如语法错误
            if(ex instanceof MissingServletRequestParameterException
                    || ex instanceof ServletRequestBindingException
                    || ex instanceof TypeMismatchException
                    || ex instanceof HttpMessageNotReadableException
                    || ex instanceof MissingServletRequestPartException
                    || ex instanceof HttpRequestMethodNotSupportedException
                    || ex instanceof HttpMediaTypeNotSupportedException) {
                return handleDefaultException(isXhr, HttpServletResponse.SC_BAD_REQUEST, ex, request, response, handler);
            }
            // 401 登录失败或请求授权失败
            if(ex instanceof AuthenticationException) {
                handleDefaultException(isXhr, HttpServletResponse.SC_UNAUTHORIZED, ex, request, response, handler);
            }
            // 405
            if(ex instanceof HttpRequestMethodNotSupportedException) {
                return handleDefaultException(isXhr, HttpServletResponse.SC_METHOD_NOT_ALLOWED, ex, request, response, handler);
            }
            // 415
            if(ex instanceof HttpMediaTypeNotSupportedException) {
                return handleDefaultException(isXhr, HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, ex, request, response, handler);
            }
            // 其它错误
            return handleDefaultException(isXhr, ex, request, response, handler);
        } catch (Exception e) {
            logHelper.getBuilder().error("系统异常", ex);
            return null;
        }
    }


    /**
     * 处理后台校验失败异常，出现这种异常可能是恶意攻击
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    private ModelAndView handleBindException(
            boolean isAjaxRequest, BindException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        BindingResult bindingResult = ex.getBindingResult();
        return handleBindingResult(isAjaxRequest, bindingResult, request, response);
    }

    /**
     * 解析后台校验失败的错误消息
     * @param isAjaxRequest
     * @param bindingResult
     * @param request
     * @param response
     * @return
     */
    private ModelAndView handleBindingResult(
            boolean isAjaxRequest, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        // ajax请求返回JSON数据
        if(isAjaxRequest) {
            ResponseVo responseVo = ResponseVo.BUILDER().setCode(Consts.RESULT_CODE_ERROR).setDesc(buildErrorMessage(bindingResult));
            WebUtils.responseAjax(response, responseVo);
            return null;
        } else { // 页面请求返回到错误页面
            return buildResponseView(buildErrorMessage(bindingResult));
        }
    }

    /**
     * 处理后台校验失败异常，出现这种异常可能是恶意攻击
     * @param isAjaxRequest
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    private ModelAndView handleMethodArgumentNotValidException(
            boolean isAjaxRequest,MethodArgumentNotValidException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        BindingResult bindingResult = ex.getBindingResult();
        return handleBindingResult(isAjaxRequest, bindingResult, request, response);
    }

    /**
     * 将错误信息拼接为字符串，使用逗号分割
     * @param bindingResult
     * @return
     */
    private String buildErrorMessage(BindingResult bindingResult) {
        Joiner joiner = Joiner.on(",").skipNulls();
        List<ObjectError> errors = bindingResult.getAllErrors();
        List<String> errorList = Lists.transform(errors, new Function<ObjectError, String>() {
            @Override
            public String apply(ObjectError objectError) {
                return TranslatorHelper.getText(objectError.getDefaultMessage());
            }
        });
        // 按字典序排序
        List<String> sortErrorList = Lists.newArrayList(errorList);
        Collections.sort(sortErrorList);
        String errorMessage = joiner.join(sortErrorList);
        return errorMessage;
    }

    /**
     * 组建要返回的视图
     * @param desc
     * @return
     */
    private ModelAndView buildResponseView(String desc) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("desc", desc);
        return modelAndView;
    }

    /**
     * 处理其它异常，不再细分异常种类
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    protected ModelAndView handleDefaultException(
            boolean isAjaxRequest, Exception ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        return this.handleDefaultException(isAjaxRequest, 0, ex, request, response, handler);
    }

    /**
     * 处理其它异常，不再细分异常种类
     * @param ex
     * @param statusCode 重写http状态码
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    protected ModelAndView handleDefaultException(
            boolean isAjaxRequest, int statusCode,  Exception ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(statusCode != 0) {
            response.setStatus(statusCode);
        }
        // ajax请求处理返回JSON数据
        if(isAjaxRequest) {
            ResponseVo responseVo = ResponseVo.BUILDER().setCode(Consts.RESULT_CODE_ERROR).setDesc(ex.getMessage());
            WebUtils.responseAjax(response, responseVo);
            return null;
        } else { // 页面请求返回到错误页面
            return buildResponseView(ex.getMessage());
        }
    }
}
