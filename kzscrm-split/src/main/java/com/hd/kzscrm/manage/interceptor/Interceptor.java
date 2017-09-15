package com.hd.kzscrm.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hd.kzscrm.common.util.CommUtil;

/**
 * 聚贸通后台拦截器
 * 
 * @author liuming
 * @since 2016-06-15
 */
public class Interceptor extends HandlerInterceptorAdapter {

	public static final ThreadLocal<Integer> threadLocal=new ThreadLocal<>();
	// 文件上传路径
	@Value("${img.upload.address}")
	private String imgUploadAddress;

	// 文件查看路径
	@Value("${img.view.address}")
	private String imgViewAddress;

	//@Resource
	//private SysMessageService sysMessageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		CommUtil.showRequests(request);
		if(request.getRequestURI().equals("/doLogin")){
			String role=request.getParameter("role");
			threadLocal.set(Integer.valueOf(role));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			// 返回文件服务器地址
			modelAndView.addObject("imgUploadAddress", imgUploadAddress);
			modelAndView.addObject("imgViewAddress", imgViewAddress);

		 
		}

	}

}
