package com.hd.kzscrm.manager.controller.split;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.service.param.split.PayCanteenCashflowParam;
import com.hd.kzscrm.service.serviceInter.split.PayCanteenCashflowService;
import com.hd.kzscrm.service.serviceInter.split.PayWithdrawService;
import com.hd.kzscrm.service.serviceInter.system.UserService;

/**
 * 账单总计controller
 * @author suchangsong
 *
 */
@Controller
@RequestMapping("payCanteenCashflow")
public class PayCanteenCashflowController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayCanteenCashflowController.class);
	@Resource
	private PayCanteenCashflowService payCanteenCashflowService;
	
	@Resource
	private UserService userService ;
	
	@Resource
	private PayWithdrawService payWithdrawaService;

	
	/** 
	* 初始化页面 
	* @return ModelAndView 
	* @author create 郁圆圆
	* @date create 2017年4月8日 上午10:54:57 
	*/
	@RequestMapping(value="/init")
	public ModelAndView init(){
		ModelAndView mvAndView = payCanteenCashflowService.init();
		return mvAndView;
	}
	
	/**
	 * excel 导出
	 * @param response
	 * @param param
	 */
	@RequestMapping(value="/excelPay")
	public void excelPay(HttpServletResponse response,PayCanteenCashflowParam param){
		LOGGER.info("#####PayCanteenCashflowController###excelPay##PayCanteenCashflowParam:"+param.toString());
		try {
			this.payCanteenCashflowService.excelCountPay(response,param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("PayCanteenCashflowController:excelPay"+e.getMessage());
		}
		
	}
	
	/**
	 * 用户订单统计初始化
	* @Title: orderTotal 
	* @author : lcl
	* @time : 2017年5月16日 下午7:50:45
	* @return ModelAndView    返回类型 
	* @throws
	 */
	public ModelAndView orderTotal(Long userId){
		ModelAndView model = new ModelAndView("");
		
		return  model;
	}
	
	public PageRespModel userOrderTotal(PayCanteenCashflowParam param){
		PageRespModel pageModel = payCanteenCashflowService.userOrderTotal(param);
		return pageModel;
	}
	
}
