package com.hd.kzscrm.manager.controller.order;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.manager.controller.canteen.CrmCanteenApplyController;
import com.hd.kzscrm.service.param.business.OrderParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderService;
import com.hd.kzscrm.service.vo.business.OrderDTO;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;

/**
 * @Description: TODO
 * @author LuGaogao
 * @date 2017年6月21日 下午8:20:55
 */
@Controller
@RequestMapping("/crmOrder")
public class CrmOrderController extends BaseController{
	/**
	 * 订单表
	 */
	@Resource
	ICrmOrderService crmOrderService;
	
	protected static final Logger logger = LoggerFactory.getLogger(CrmCanteenApplyController.class);
	 
	/**
	 * 
	 * @Title: orderListInit 
	 * @Description: 订单列表初始化 
	 * @param @param canteenId
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月21日 下午8:30:25
	 */
	@RequestMapping(value="/orderListInit")
	public ModelAndView orderListInit(Long canteenId){
		logger.info("#####CrmOrderController###orderListInit##canteenId:"+canteenId);
		 ModelAndView modelAndView = new ModelAndView("system_operate/order_list");
		 modelAndView.addObject("canteenId", canteenId);
		 return modelAndView;
	 }
	
	@RequestMapping(value="/orderListsInit")
	public ModelAndView orderListClientInit(Long id){
		logger.info("#####CrmOrderController###orderListInit##canteenId:"+id);
		ModelAndView modelAndView = new ModelAndView("system_operate/order_list");
		//通过id查询对象
		modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	/**
	 * 我的财富页面初始化
	 * @author xu
	 * @return
	 */
	@RequestMapping(value="/myWealthyListInit")
	public ModelAndView init(Long id){
		ModelAndView view = new ModelAndView("my_wealth/my_wealth");
		view.addObject("id", id);
		return view;
	}
	
	/**
	 * 我的财富查看页面跳转 
	 */
	@RequestMapping(value="/viewInfos/{orderId}")
	public ModelAndView viewInfoMywealthy(OrderParam param, @PathVariable Long orderId){
		ModelAndView view  = new ModelAndView("/system_operate/order_fashionalbe_detail");
		view.addObject("orderId", orderId);
		OrderPO orderPO = crmOrderService.findById(orderId);
		view.addObject("mywealthyDetail", orderPO);
		return view;
		
	}
	/***
	 * 
	 * @Title: orderListDetails 
	 * @Description: 订单列表详情 
	 * @param @param orderParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月22日 上午11:49:11
	 */
	@RequestMapping(value="/orderListDetails")
	@ResponseBody
	public PageRespModel orderListDetails(OrderParam orderParam){
		logger.info("#####CrmOrderController###orderListDetails##orderParam:"+orderParam.toString());
		
		PageRespModel pageRespModel = new PageRespModel();
		Boolean flag = crmOrderService.orderListDetails(orderParam,pageRespModel);//查询订单详情
		if(flag) return pageRespModel;
		
		return pageRespModel;
	}
	
	/**
	 * 
	 * @Title: orderDetails 
	 * @Description: 跳转订单详情 
	 * @param @param orderId
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月26日 上午9:51:47
	 */
	@RequestMapping(value="/orderDetails")
	public ModelAndView orderDetails(Long orderId){
		logger.info("#####CrmOrderController###orderDetails##orderId:"+orderId);
		ModelAndView modelAndView = new ModelAndView("order/order_detail");
		
		Boolean flag = crmOrderService.orderDetails(orderId,modelAndView);//查看订单详情
		if(flag) return modelAndView;
		
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: orderListDetailsExcelOut 
	 * @Description: 订单列表导出 
	 * @param @param response
	 * @param @param orderParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月29日 下午12:00:14
	 */
	@RequestMapping(value="/orderListDetailsExcelOut")
	public void orderListDetailsExcelOut(HttpServletResponse response,OrderParam orderParam){
		logger.info("#####CrmOrderController###orderListDetailsExcelOut##orderParam:"+orderParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("cashFlowNo","流水号");
		map.put("orderNo","订单编号");
		map.put("userMobilephone","用户账号");
		map.put("orderRealMoney","金额（￥）");
		map.put("payModelStr","支付方式");
		map.put("payTime","支付时间");
		map.put("realMoney","商家金额（￥）");
		map.put("cutMoney","抽成金额（￥）");
		map.put("channelMoney","通道费（￥）");
		map.put("statusName","状态");
		
		//查询订单列表
		List<OrderDTO> orderDTOs = crmOrderService.orderListDetailsExcelOut(orderParam);
		
		try {
			ExcelUtil.writeXls(response,"订单列表", map,orderDTOs,OrderDTO.class);
		} catch (Exception e) {
			logger.error("CrmOrderController:orderListDetailsExcelOut:",e);
		}
	}
	
	/**
	 * 我的财富一栏查询页面详情展示
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public PageRespModel queryPage(OrderParam param) throws Exception{
		PageRespModel model = new PageRespModel();
		//获取当前登录的业务员的userId
		Long userId = CrmControllerHelper.getSessionUserId();
		Page<OrderVO> pages = crmOrderService.queryPage(param, userId);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
		
	}
}
