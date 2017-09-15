/**
 * 
 */
package com.hd.kzscrm.manager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hd.kzscrm.common.model.portal.PortalRespModel;
import com.hd.kzscrm.common.model.portal.PortalRespModel.RespCode;
import com.hd.wolverine.common.log.LogHelper;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;
import com.hd.wolverine.util.UuidUtil;

/**
 * @author 黄霄仪
 * @date 2017年3月7日 下午1:53:53
 * 
 */
public class BaseController {
	@Deprecated
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final LogHelper logHelper = LogHelper.getLogger(this.getClass());
	 
	/**
	 * 图片读取地址
	 */
	@Value("${img.view.address}")
	protected String imgReadIpAddress;
	
	/**
	 * 是否为DEBUG模式
	 */
	@Value("${debug.model}")
	protected boolean debugModel;
	/**
	 * 测试环境下的验证码
	 */
	@Value("${defalt.auth.code}")
	protected String defaltAuthCode;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	/**
	 * 获取基本PortalRespModel
	 * @author 黄霄仪
	 * @param respCode 响应状态
	 * @date 2017年3月7日 下午1:59:54
	 */
	public static PortalRespModel getPortalRespModel(RespCode respCode,Object rows){
		PortalRespModel portalRespModel=new PortalRespModel();
		portalRespModel.setDesc(respCode.getDesc());
		portalRespModel.setStatusCode(respCode.getStatusCode());
		portalRespModel.setRows(rows);
		return portalRespModel;
	}

	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public ParamMap getPageData() {
		return new ParamMap(this.getRequest());
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID() {
		return UuidUtil.get32UUID();
	}

	/**
	 * 得到分页列表的信息
	 * 
	 * @return
	 */
	public Page getPage() {
		return new Page();
	}
}
