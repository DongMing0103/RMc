package com.hd.kzscrm.manager.controller.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.UserPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessOrderPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessSplitDetailPO;
import com.hd.kzscrm.dao.entity.business.CrmDepartmentPO;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
import com.hd.kzscrm.dao.entity.business.OrderGoodsItemPO;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.canteen.GoodImgPO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmBusinessSplitDetailParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessSplitDetailService;
import com.hd.kzscrm.service.serviceInter.business.ICrmDepartmentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmPositionService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmGoodImgService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.split.ISplitCutInfoService;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.vo.agent.CrmAgentVO;
import com.hd.kzscrm.service.vo.agent.CrmSplitAssignSetVO;
import com.hd.kzscrm.service.vo.business.CrmBusinessSplitDetailVO;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;

/**
 * 代理商分账 crmBusinessSplitDetail CRMBUSINESSSPLIT詳細
 * 
 * 平台分账 
 * 
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmbusinesssplitdetail")
public class CrmBusinessSplitDetailController extends BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessSplitDetailController.class);
	@Autowired
	ICrmBusinessSplitDetailService mainService;

	@Autowired
	ICrmAgentService icCrmAgentService; // 代理商

	@Autowired
	ICrmOrderService iCrmOrderService; // 订单信息

	@Autowired
	ISplitCutInfoService iSplitCutInfoService; // 抽成明细
	@Autowired
	ICrmBusinessSplitDetailService iCrmBusinessSplitDetailService; //分账明细

	@Autowired
	ICrmCanteenBaseInfoService iCrmCanteenBaseInfoService; // 食堂信息

	@Autowired
	ICrmSplitAssignSetService crmSplitAssignSetService;

	@Autowired
	ICrmBusinessOrderService crmBusinessOrderService;

	@Autowired
	ICrmBusinessService iCrmBusinessService; // 业务员基本信息
	//订单表
	@Autowired
	ICrmOrderService orderService;
	// 用户表
	@Autowired
	UserService userService;

	@Autowired
	ICrmClientResourceService iCrmClientResourceService; // 客户列表

	@Autowired
	ICrmDepartmentService iCrmDepartmentService;

	@Autowired
	ICrmPositionService iCrmPositionService;
	@Resource
    ICrmPermUserRoleService iCrmPermUserRoleService;
	@Autowired
	ICrmPermRoleSourcesService roleSourcesService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
    ICrmPermSourcesService  pSourcesService;
	
	@Autowired
	ICrmEnterpriseService CrmEnterpriseService; // 企业信息
	/**
	 * 商品图片
	 */
	@Autowired
	ICrmGoodImgService goodImgService;
	/**
	 ** 预留实现
	 */
	public void initControler(HttpServletRequest request, Map<String, Object> map) {

	}

	/**
	 *
	 * 初始化
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/init")
	@ResponseBody
	public ModelAndView init() {
		ModelAndView view = new ModelAndView("/collecting_statistics/agent_fashinable");
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		return view;
	}

	/**
	 * 平台分账 
	 *    初始化
	 */
	@RequestMapping("/inits")
	public ModelAndView inits() {
		ModelAndView view = new ModelAndView("/collecting_statistics/platform_fashinable");
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		return view;
	}
	
	/**
	 * 一览查询  (平台分账)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPages")
	@ResponseBody
	public PageRespModel queryPages(CrmBusinessSplitDetailParam param) throws Exception{
		PageRespModel model = new PageRespModel();
		param.setDelFlag(1);
		Page<CrmBusinessSplitDetailVO> pages = mainService.findPageByParams(param);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
		
	}
	
	/**
	 * 一览查询
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public PageRespModel queryPage(CrmBusinessSplitDetailParam param) throws Exception {
		PageRespModel model = new PageRespModel();
		Page<CrmBusinessSplitDetailVO> pages = mainService.queryPage(param);
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<CrmBusinessSplitDetailVO>();

		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list) {
			Long agentUserId = crmBusinessSplitDetailVO.getAgentUserId();

			CrmAgentPO crmAgentPO = icCrmAgentService.findByAgentNo(agentUserId);
			Long agentId = crmAgentPO.getId(); // 代理商id
			crmBusinessSplitDetailVO.setAgentId(agentId);
			crmBusinessSplitDetailVO.setAgentName(crmAgentPO.getName()); // 代理商名称
			crmBusinessSplitDetailVO.setRegisterTime(crmAgentPO.getRegisterTime());

			// 根据代理商id 统计该代理商的客户数量
			CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
			crmClientResourceParam.setAgentId(agentId);
			BigDecimal agentNum = iCrmClientResourceService.getAgentNumber(crmClientResourceParam);
			crmBusinessSplitDetailVO.setAgentNumber(agentNum);

			// 区域名称
			Long areaCode = crmAgentPO.getAreaCode();
			if (areaCode != null) {
				BeanUtils.databaseInjectField(crmBusinessSplitDetailVO, "areaName",
						new String[] { "area_code=" + areaCode }, new String[] { "area_name areaName" });
			}

			listNew.add(crmBusinessSplitDetailVO);
		}
		pages.result = listNew;
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);

		return model;
	}

	/**
	 * 代理商导出功能
	 * 
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/excelOut")
	@ResponseBody
	public void excelOut(HttpServletResponse response, CrmBusinessSplitDetailParam param) {
		Page<CrmBusinessSplitDetailVO> pages = mainService.queryPage(param);
		List<CrmBusinessSplitDetailVO> crmAgentVOs = pages.result;
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<CrmBusinessSplitDetailVO>();

		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list) {
			Long agentUserId = crmBusinessSplitDetailVO.getAgentUserId();

			CrmAgentPO crmAgentPO = icCrmAgentService.findByAgentNo(agentUserId);
			Long agentId = crmAgentPO.getId(); // 代理商id
			crmBusinessSplitDetailVO.setAgentId(agentId);
			crmBusinessSplitDetailVO.setAgentName(crmAgentPO.getName()); // 代理商名称
			crmBusinessSplitDetailVO.setRegisterTime(crmAgentPO.getRegisterTime());

			// 区域名称
			Long areaCode = crmAgentPO.getAreaCode();
			if (areaCode != null) {
				BeanUtils.databaseInjectField(crmBusinessSplitDetailVO, "areaName",
						new String[] { "area_code=" + areaCode }, new String[] { "area_name areaName" });
			}

			CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
			crmClientResourceParam.setAgentId(agentId);
			BigDecimal agentNum = iCrmClientResourceService.getAgentNumber(crmClientResourceParam);
			crmBusinessSplitDetailVO.setAgentNumber(agentNum);

			listNew.add(crmBusinessSplitDetailVO);
		}
		if (crmAgentVOs != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("agentId", "ID");
			map.put("agentName", "名称");
			map.put("areaName", "代理商区域");
			map.put("registerTime", "加盟时间");
			map.put("agentNumber", "客户数量");
			map.put("agentMoney", "抽成金额（￥）");
			map.put("agentMoney", "分账金额（￥）");
			try {
				ExcelUtil.writeXls(response, "代理商分账", map, crmAgentVOs, CrmAgentVO.class);
			} catch (Exception e) {
				logger.error("CrmBusinessSplitDetailController:excelOut:", e);
			}
		}
	}
	
	/**
	 * 平台分账主页面导出
	 */
	@RequestMapping(value="/platformExcelOut")
	@ResponseBody
	public void platformExcelOut(HttpServletResponse response, CrmBusinessSplitDetailParam param){
		Page<CrmBusinessSplitDetailVO> pages = mainService.findPageByParams(param);
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<>();
		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list){
			String orderNo = crmBusinessSplitDetailVO.getOrderNo();
//			Long orderId =crmBusinessSplitDetailVO.getOrderId();
			OrderPO orderPO = iCrmOrderService.findByOrderNo(orderNo);
			CrmBusinessSplitDetailPO splitDetailPO = mainService.findByOrderNo(orderNo);
//	    	CrmSplitAssignSetPO assignSetPO = crmSplitAssignSetService.findByOrderId(orderId);
//			SplitCutInfoPO splitCutInfoPO = iSplitCutInfoService.findByOrderNo(orderNo);  //根据订单编号查询抽成明细
			
			if (BeanUtils.isNotEmpty(orderPO)) {
				crmBusinessSplitDetailVO.setOrderNo(orderPO.getOrderNo());
				crmBusinessSplitDetailVO.setOrderFlowNo(orderPO.getOrderFlowNo());
				crmBusinessSplitDetailVO.setPayModel(orderPO.getPayModel());
				crmBusinessSplitDetailVO.setChannelMoney(orderPO.getChannelMoney());
			}
			if (BeanUtils.isNotEmpty(splitDetailPO)) {
				crmBusinessSplitDetailVO.setCreateTime(splitDetailPO.getCreateTime());
				crmBusinessSplitDetailVO.setPlatformMoney(splitDetailPO.getPlatformMoney()); //平台分账金额
				crmBusinessSplitDetailVO.setAgentMoney(splitDetailPO.getAgentMoney());   //获取代理商分账金额
				crmBusinessSplitDetailVO.setBusinessMoney(splitDetailPO.getBusinessMoney()); //获取业务员分账金额
			}	
			
//			Double canteenSplitPercent = assignSetPO.getCanteenSplitPercent();
//			BigDecimal businessSplitPercent = assignSetPO.getBusinssSplitPercent();
//			BigDecimal agentSplitPercent = assignSetPO.getAgentSplitPercent();
//			
////			BigDecimal canteenSplitPercent2 = new BigDecimal(Double.toString(canteenSplitPercent));
//			
//			crmBusinessSplitDetailVO.setBusinessSplitMoney((splitDetailPO.getBusinessMoney()).multiply(businessSplitPercent).divide(BigDecimal.valueOf(100)));
//			crmBusinessSplitDetailVO.setAgentSplitMoney((splitDetailPO.getAgentMoney()).multiply(agentSplitPercent).divide(BigDecimal.valueOf(100)));
//			
//			//查询食堂分账金额   实际金额 * 抽成比例
//			if (BeanUtils.isNotEmpty(splitCutInfoPO)) {
//				BigDecimal cutRatio = splitCutInfoPO.getCutRatio();      
//				BigDecimal orderRealMoney = splitCutInfoPO.getRealMoney();
//				BigDecimal canteenSplitMoney = orderRealMoney.multiply(cutRatio);
//				crmBusinessSplitDetailVO.setCanteenSplitMoney(canteenSplitMoney);
//			}
			
			listNew.add(crmBusinessSplitDetailVO);
		}
		if (listNew != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("orderNo", "订单编号");
			map.put("orderFlowNo", "支付流水号");
			map.put("payModel", "支付方式");
			map.put("createTime", "支付时间");
			map.put("canteenSplitMoney", "食堂分账");
			map.put("agentSplitMoney", "代理商分账");
			map.put("businessSplitMoney", "业务员分账");
			map.put("channelMoney", "通道费");
			map.put("platformMoney", "平台分账");
			try {
				ExcelUtil.writeXls(response, "平台分账", map, listNew, CrmBusinessSplitDetailVO.class);
			} catch (Exception e) {
				logger.error("CrmBusinessSplitDetailController:excelOut:", e);
			}
		}
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{agentId}")
	@ResponseBody
	public ModelAndView viewInfo(CrmBusinessSplitDetailParam param, @PathVariable Long agentId) {
		ModelAndView view = new ModelAndView("collecting_statistics/fashionable_detail");
		view.addObject("agentId", agentId);
		//List<CrmCanteenBaseInfoPO> allCanteen = iCrmCanteenBaseInfoService.getCanteenByIds();
		
		//查询该代理商子孙代理商及其自身id
		CrmAgentParam crmAgentParam = new CrmAgentParam();
		crmAgentParam.setParentId(agentId);
		crmAgentParam.setDelFlag(1);
		List<Long> childAgentIds = icCrmAgentService.getChildAgentIds(crmAgentParam);
		
		//查询这些代理商业务员
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setAgentIds(childAgentIds);
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
		List<Long> businessIds = new LinkedList<Long>();
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			Long id = crmBusinessPO.getId();
			businessIds.add(id);
		}
		
		
		//查询正式客户(食堂)
		CrmCanteenBaseInfoParam crmCanteenBaseInfoParam = new CrmCanteenBaseInfoParam();
		crmCanteenBaseInfoParam.setBusinessIds(businessIds);
		//crmCanteenBaseInfoParam.setStatus(2);//1未审核、2已通过 、3已注销 4.未通过 5.申请中
		crmCanteenBaseInfoParam.setClientNature(3);//1.散客，2.保护客户，3.正式客户
		crmCanteenBaseInfoParam.setDelFlag(1);
		List<CrmCanteenBaseInfoPO> allCanteen = iCrmCanteenBaseInfoService.commonQuery(crmCanteenBaseInfoParam );
		
		
		view.addObject("allCanteen", allCanteen);
		return view;
	}
	
	/**
	 * 平台分账查看页面跳转
	 * @author xu
	 */
	@RequestMapping(value="/viewInfos/{orderId}")
	@ResponseBody
	public ModelAndView viewInfoPlatForm(CrmBusinessSplitDetailParam param, @PathVariable Long orderId){
		ModelAndView view = new ModelAndView("split_detail/platform_fashionable_detail");
		view.addObject("orderId", orderId);
		OrderPO orderPOs = orderService.findById(orderId);
		view.addObject("orderDetail",orderPOs);
		return view;
		
	}

	/**
	 * lcl
	 * 
	 */
	@RequestMapping(value = "/viewInfoInit")
	@ResponseBody
	public ModelAndView viewInfoInit(CrmBusinessSplitDetailParam param) {
		logger.info("#####CrmBusinessSplitDetailController###Param:" + param.toString());
		ModelAndView view = new ModelAndView("collecting_statistics/fashionable_detail");
		Long id = param.getId();// 获取平台客户(客户资源id)id 
		CrmClientResourcePO clientResourcePO = this.iCrmClientResourceService.findById(id);
		if(BeanUtils.isNotEmpty(clientResourcePO) && BeanUtils.isNotEmpty(clientResourcePO.getAgentCanteenId())){
			
			//根据客户资源id查询对应的食堂
			CrmCanteenBaseInfoPO canteenBaseInfoPO = this.iCrmCanteenBaseInfoService.getById(clientResourcePO.getAgentCanteenId());
			if(BeanUtils.isNotEmpty(canteenBaseInfoPO)){
				view.addObject("canteenId", canteenBaseInfoPO.getId());
			}
			
		}
		
		
		
		
		CrmBusinessSplitDetailPO businessSplitDetailPO = mainService.getById(id);
		if (BeanUtils.isNotEmpty(businessSplitDetailPO)) {
//			Long business = businessSplitDetailPO.getBusinessId();
			List<CrmCanteenBaseInfoPO> canteenBaseInfoPOs = this.iCrmCanteenBaseInfoService.findByBusinessIds(param.getBusinessId());
			if(CollectionUtils.isNotEmpty(canteenBaseInfoPOs)){
				view.addObject("allCanteen", canteenBaseInfoPOs);
			}
			
		}
		Long businessId = param.getBusinessId();
		
		//查询业务员所有的正是客户
		CrmCanteenBaseInfoParam crmCanteenBaseInfoParam = new CrmCanteenBaseInfoParam();
		crmCanteenBaseInfoParam.setBusinessId(businessId);
		//crmCanteenBaseInfoParam.setStatus(2);//1未审核、2已通过 、3已注销 4.未通过 5.申请中
		crmCanteenBaseInfoParam.setClientNature(3);//1.散客，2.保护客户，3.正式客户
		crmCanteenBaseInfoParam.setDelFlag(1);
		List<CrmCanteenBaseInfoPO> allCanteen = iCrmCanteenBaseInfoService.commonQuery(crmCanteenBaseInfoParam );
		view.addObject("allCanteen", allCanteen);
		
		view.addObject("agentId", param.getAgentId());
		view.addObject("businessId", businessId);
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		return view;
	}

	
	/**
	 * 查看页面查询
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewQueryPage")
	@ResponseBody
	public PageRespModel viewQueryPage(CrmBusinessSplitDetailParam param) throws Exception {
		logger.info("#####CrmBusinessSplitDetailController###Param:" + param.toString());
		PageRespModel model = new PageRespModel();
		Page<CrmBusinessSplitDetailVO> pages = mainService.queryPage(param);
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<CrmBusinessSplitDetailVO>();
		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list) {

			String orderNo = crmBusinessSplitDetailVO.getOrderNo();
			OrderPO orderPO = iCrmOrderService.findByOrderNo(orderNo); // 根据订单编号
																		// 查询订单信息
			crmBusinessSplitDetailVO.setOrderId(orderPO.getId());
			crmBusinessSplitDetailVO.setOrderNo(orderPO.getOrderNo());
			crmBusinessSplitDetailVO.setOrderFlowNo(orderPO.getOrderFlowNo());
			crmBusinessSplitDetailVO.setPayModel(orderPO.getPayModel());
			crmBusinessSplitDetailVO.setGeneralLedgerDate(orderPO.getGeneralLedgerDate());

			SplitCutInfoPO splitCutInfoPO = iSplitCutInfoService.findByOrderNo(orderNo); // 根据订单编号查询抽成明细
			if (BeanUtils.isNotEmpty(splitCutInfoPO)){
				crmBusinessSplitDetailVO.setRealMoney(splitCutInfoPO.getOrderRealMoney());
				crmBusinessSplitDetailVO.setCutMoney(splitCutInfoPO.getCutMoney());
				crmBusinessSplitDetailVO.setCutRatio(splitCutInfoPO.getCutRatio());
				BigDecimal cutRatio = splitCutInfoPO.getCutRatio(); // 分账比例
				BigDecimal orderRealMoney = splitCutInfoPO.getOrderRealMoney(); // 实际金额
				BigDecimal splitMoney = cutRatio.multiply(orderRealMoney);
				crmBusinessSplitDetailVO.setSplitMoney(splitMoney); // 获取分账金额
			}

//			Long agentUserId = crmBusinessSplitDetailVO.getAgentUserId();
//			CrmAgentPO crmAgentPO = icCrmAgentService.findByAgentNo(agentUserId); // 根据代理商编号查询代理商信息
	
				CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = iCrmCanteenBaseInfoService.findByBusinessId(crmBusinessSplitDetailVO.getBusinessId()); // 根据业务员id查询食堂信息
				if(BeanUtils.isNotEmpty(crmCanteenBaseInfoPO)){
					crmBusinessSplitDetailVO.setCanteenName(crmCanteenBaseInfoPO.getName());
				}
			
			listNew.add(crmBusinessSplitDetailVO);
		}
		pages.result = listNew;
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
	}
	
	/**
	 * 平台分账查看页面查询
	 * @author xu 
	 */
	@RequestMapping(value = "/viewPlatformDetailPage")
	@ResponseBody
	public PageRespModel viewPlatformDetailPage(CrmBusinessSplitDetailParam param) throws Exception{
		PageRespModel model = new PageRespModel();
	    Page<CrmBusinessSplitDetailVO> pages = mainService.queryPage(param);
	    List<CrmBusinessSplitDetailVO> list= pages.result;
	    List<CrmBusinessSplitDetailVO> listNew = new ArrayList<>();
	    
		CrmBusinessSplitDetailVO splitDetailVO = list.get(0);
		String orderNo = splitDetailVO.getOrderNo();
//		Long orderId = splitDetailVO.getOrderId();
		OrderPO orderPO = iCrmOrderService.findByOrderNo(orderNo);
		if (BeanUtils.isNotEmpty(orderPO)) {
			Long canteenId = orderPO.getCanteenId();
			CrmCanteenBaseInfoPO canteenBaseInfoPO = iCrmCanteenBaseInfoService.findByCanteenId(canteenId);
			splitDetailVO.setOrderId(orderPO.getId());
			splitDetailVO.setOrderNo(orderNo);
			splitDetailVO.setOrderFlowNo(orderPO.getOrderFlowNo());
			splitDetailVO.setPayModel(orderPO.getPayModel());
			splitDetailVO.setCanteenName(canteenBaseInfoPO.getName());   //获取食堂名称
			splitDetailVO.setRealMoney(orderPO.getRealMoney());    //获取实付金额
			splitDetailVO.setCutMoney(orderPO.getCutMoney());     //获取抽成金额
			splitDetailVO.setRealMoney(orderPO.getRealMoney());   //获取实付金额
			splitDetailVO.setCutPercent(orderPO.getCutPercent());  //获取分账比例
			splitDetailVO.setSplitMoney((orderPO.getRealMoney()).multiply(orderPO.getCutPercent()));
		}
		
		CrmBusinessSplitDetailPO crmBusinessSplitDetailPO = iCrmBusinessSplitDetailService.findByOrderNo(orderNo);
		if (BeanUtils.isNotEmpty(crmBusinessSplitDetailPO)) {
			splitDetailVO.setCreateTime(crmBusinessSplitDetailPO.getCreateTime());  //分账时间
		}
		listNew.add(splitDetailVO);
		pages.result = listNew;
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
		
	}

	
	/**
	 * 分账明细导出
	 * 
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/viewExcelOul")
	@ResponseBody
	public void viewExcelOul(HttpServletResponse response, CrmBusinessSplitDetailParam param) {
		
		Page<CrmBusinessSplitDetailVO> pages = mainService.queryPage(param);
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<CrmBusinessSplitDetailVO>();
		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list) {

			String orderNo = crmBusinessSplitDetailVO.getOrderNo();
			OrderPO orderPO = iCrmOrderService.findByOrderNo(orderNo); // 根据订单编号
																		// 查询订单信息
			crmBusinessSplitDetailVO.setOrderNo(orderPO.getOrderNo());
			crmBusinessSplitDetailVO.setOrderFlowNo(orderPO.getOrderFlowNo());
			crmBusinessSplitDetailVO.setGeneralLedgerDate(orderPO.getGeneralLedgerDate());
			crmBusinessSplitDetailVO.setPayModel(orderPO.getPayModel());
			if (BeanUtils.isNotEmpty(crmBusinessSplitDetailVO)) {
				if (crmBusinessSplitDetailVO.getPayModel().equals("1")) {
					crmBusinessSplitDetailVO.setPayModelName("余额");
				}
				if (crmBusinessSplitDetailVO.getPayModel().equals("2")) {
					crmBusinessSplitDetailVO.setPayModelName("支付宝");
				}
				if (crmBusinessSplitDetailVO.getPayModel().equals("3")) {
					crmBusinessSplitDetailVO.setPayModelName("微信");
				}
			}
			crmBusinessSplitDetailVO.setGeneralLedgerDate(orderPO.getGeneralLedgerDate());
			if (BeanUtils.isNotEmpty(orderPO.getPayModel())) {
				if (orderPO.getPayModel().equals(1)) {
					crmBusinessSplitDetailVO.setPayModelName("余额");
				} else if (orderPO.getPayModel().equals(2)) {
					crmBusinessSplitDetailVO.setPayModelName("支付宝");
				} else {
					crmBusinessSplitDetailVO.setPayModelName("微信");
				}
			}
			SplitCutInfoPO splitCutInfoPO = iSplitCutInfoService.findByOrderNo(orderNo); // 根据订单编号查询抽成明细
			crmBusinessSplitDetailVO.setRealMoney(splitCutInfoPO.getOrderRealMoney());
			crmBusinessSplitDetailVO.setCutMoney(splitCutInfoPO.getCutMoney());
			crmBusinessSplitDetailVO.setCutRatio(splitCutInfoPO.getCutRatio());
			BigDecimal cutRatio = splitCutInfoPO.getCutRatio(); // 分账比例
			BigDecimal orderRealMoney = splitCutInfoPO.getOrderRealMoney(); // 实际金额
			BigDecimal splitMoney = cutRatio.multiply(orderRealMoney);
			crmBusinessSplitDetailVO.setSplitMoney(splitMoney);

			Long agentUserId = crmBusinessSplitDetailVO.getAgentUserId();
			CrmAgentPO crmAgentPO = icCrmAgentService.findByAgentNo(agentUserId); // 根据代理商编号查询代理商信息
			Long businessId = crmAgentPO.getBusinessId();
			CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = iCrmCanteenBaseInfoService.findByBusinessId(businessId); // 根据业务员id查询食堂信息
			crmBusinessSplitDetailVO.setCanteenName(crmCanteenBaseInfoPO.getName());

			listNew.add(crmBusinessSplitDetailVO);
		}

		if (list != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("orderNo", "订单编号");
			map.put("orderFlowNo", "支付流水号");
			map.put("payModelName", "支付方式");
			map.put("generalLedgerDate", "分账时间");
			//map.put("canteenName", "食堂名称");
			map.put("realMoney", "实付金额（￥）");
			map.put("cutMoney", "抽成金额（￥）");
			map.put("cutRatio", "分账比例（%）");
			map.put("splitMoney", "分账金额（￥）");
			try {
				ExcelUtil.writeXls(response, "分账明细", map, list, CrmBusinessSplitDetailVO.class);
			} catch (Exception e) {
				logger.error("CrmBusinessSplitDetailController:viewExcelOul:", e);
			}
		}
	}
	/**
	 * 分账明细 导出
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/viewExcelOulNew")
	@ResponseBody
	public void viewExcelOulNew(HttpServletResponse response, CrmBusinessSplitDetailParam param) {
		
		//传商家id订单
		Page<OrderVO>  cPage = orderService.findByCanteenId(param);
		List<OrderVO> list = cPage.result;
		for (OrderVO orderVO : list) {
			Integer payModel = orderVO.getPayModel();
			if(CommUtil.parseInteger(payModel)>0){
				String payModelName = "";
				if(payModel.equals(1)){
					payModelName = "余额";
				}
				if(payModel.equals(2)){
					payModelName = "支付宝";
				}
				if(payModel.equals(3)){
					payModelName = "微信";
				}
				orderVO.setPayModelName(payModelName);
			}
			
			//分账时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String shelfTimeString = "";
			if (BeanUtils.isNotEmpty(orderVO.getGeneralLedgerDate())) {
				shelfTimeString = sdf.format(orderVO.getGeneralLedgerDate());
			}
			orderVO.setRemark(shelfTimeString);//用餐时间借用
		}
		if (list != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("orderNo", "订单编号");
			map.put("cashFlowNo", "支付流水号");
			map.put("payModelName", "支付方式");
			map.put("remark", "分账时间");
			//map.put("canteenName", "食堂名称");
			map.put("realMoney", "实付金额（￥）");
			map.put("cutMoney", "抽成金额（￥）");
			map.put("canteenSplitPercent", "分账比例（%）");
			map.put("splitMoney", "分账金额（￥）");
			try {
				ExcelUtil.writeXls(response, "分账明细", map, list, OrderVO.class);
			} catch (Exception e) {
				logger.error("CrmBusinessSplitDetailController:viewExcelOul:", e);
			}
		}
	}

	/**
	 * 查看
	 * 
	 * @param param
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/orderView/{orderNo}")
	@ResponseBody
	public ModelAndView orderView(HttpServletRequest request, @PathVariable String orderNo) {
		ModelAndView modelAndView = new ModelAndView("/collecting_statistics/order_fashionalbe_detail");
		try {
			List<CrmBusinessSplitDetailPO> po = iCrmBusinessSplitDetailService.findByOrderNos(orderNo);
			if (BeanUtils.isNotEmpty(po) && BeanUtils.isNotEmpty(po.get(0))) {
				CrmBusinessSplitDetailPO setPO = po.get(0);
				// CrmSplitAssignSetVO cAssignSetVO =
				// BeanConvertor.convertBean(setPO, CrmSplitAssignSetVO.class);
				// // 抽成分配

				CrmBusinessSplitDetailVO split = BeanConvertor.convertBean(setPO, CrmBusinessSplitDetailVO.class); // 抽成分配
				OrderPO orderPOs = iCrmOrderService.findByOrderNo(orderNo);
				Long id = orderPOs.getId();
				OrderPO oPO = new OrderPO(); // 订单信息
				OrderVO oVO = new OrderVO();
				UserPO UserPO = new UserPO(); // 个人信息
				CrmCanteenBaseInfoPO canteenBaseInfoPO = new CrmCanteenBaseInfoPO(); // 食堂信息

				// 根据订单id获取订单商品信息
				try {
					List<OrderGoodsItemPO> ogip = iCrmOrderService.findByOrderId(id);
					OrderGoodsItemPO orderGoodsItemPO = ogip.get(0);
					modelAndView.addObject("orderGoodsItemPO", orderGoodsItemPO); // 抽成分配设置表
					
					// 获取商品图片
					Long goodsId = orderGoodsItemPO.getGoodsId();
					List<GoodImgPO> goodImgPO = goodImgService.findByGoodsId(goodsId);
					GoodImgPO goodImgPOs = goodImgPO.get(0);
					modelAndView.addObject("goodImgPOs", goodImgPOs);
				} catch (Exception e) {
					logger.error("#OrderCutController:抽成订单信息:", e);
				}

				Long businessId = setPO.getBusinessId(); // 获取业务员信息
				CrmSplitAssignSetPO splitAssignSetPO = crmSplitAssignSetService.findByBusinessId(businessId);
				if (orderPOs != null) {
					CrmBusinessOrderPO businessOrderPO = crmBusinessOrderService.findByBusinessId(businessId);// 根据业务员id查询订单
					oPO = iCrmOrderService.findById(id);
					if (BeanUtils.isNotEmpty(businessOrderPO)) {
						// Long orderId =
						// businessOrderPO.getOrderId();//订单表orderPO
						if (BeanUtils.isNotEmpty(oPO) && BeanUtils.isNotEmpty(oPO.getUserId())) {
							Long userId = oPO.getUserId();// 获取用户资料
							UserPO = userService.getById(userId);

						}
						if (BeanUtils.isNotEmpty(oPO) && BeanUtils.isNotEmpty(oPO.getCanteenId())) {// 根据商家id查询对应信息
							canteenBaseInfoPO = iCrmCanteenBaseInfoService.findByCanteenId(oPO.getCanteenId());
						}

						// 供应类目和消费状态 去查order表 没有对应的实体类

						BigDecimal splitPercent = splitAssignSetPO.getBusinssSplitPercent();// 获取业务员分账比例
						split.setOrderNum(businessOrderPO.getOrderNo());// 获取订单id
						split.setRealMoney(businessOrderPO.getOrderRealMoney());// 订单的总金额
						split.setCreaterTime(businessOrderPO.getCreateTime());// 分账时间
						if (BeanUtils.isNotEmpty(businessOrderPO.getOrderRealMoney())
								&& BeanUtils.isNotEmpty(splitPercent) && splitPercent.compareTo(BigDecimal.ZERO) > 0) {
							// BigDecimal的乘法计算
							split.setSplitMoney(businessOrderPO.getOrderRealMoney()
									.multiply(splitPercent.divide(new BigDecimal(100))));
							split.setBusinssSplitPercent(splitPercent);
						}
					}

					// 获取平台利润
					BigDecimal cutmoney = oPO.getCutMoney();
					BigDecimal channelmoney = oPO.getChannelMoney();
					if (BeanUtils.isNotEmptyAnd(cutmoney, channelmoney)) {
						BigDecimal profitMoney = cutmoney.subtract(channelmoney);
						oVO.setProfitMoney(profitMoney);
					}

					CrmBusinessPO crmBusinessPO = iCrmBusinessService.findByBusinessId(businessId); // 获取业务员名称
					split.setBusinessName(crmBusinessPO.getName());

					Long agentId = splitAssignSetPO.getAgentId(); // 获取代理商id
					CrmAgentPO crmAgentPO = icCrmAgentService.findByAgentId(agentId); // 获取代理商名称
					split.setAgentName(crmAgentPO.getName());

					BigDecimal agentSplit = splitAssignSetPO.getAgentSplitPercent(); // 获取代理商分账比例
					if (BeanUtils.isNotEmpty(agentSplit)) {
						CrmBusinessSplitDetailPO crmBusinessSplitDetailPO = iCrmBusinessSplitDetailService
								.findByAgentNo(crmAgentPO.getAgentNo());
						if (BeanUtils.isNotEmpty(crmBusinessSplitDetailPO)
								&& BeanUtils.isNotEmpty(crmBusinessSplitDetailPO.getAgentMoney())
								&& BeanUtils.isNotEmpty(agentSplit) && agentSplit.compareTo(BigDecimal.ZERO) > 0) { // 代理商分账金额
							split.setSplitAgentMoney(crmBusinessSplitDetailPO.getAgentMoney()
									.multiply(agentSplit.divide(new BigDecimal(100))));
							
							split.setAgentSplitPercent(agentSplit);
						}
					}

					// 食堂分账金额
					BigDecimal canteenSplit = splitAssignSetPO.getCanteenSplitPercent(); // 分账比例
					if (BeanUtils.isNotEmpty(oPO.getOrderRealMoney()) && BeanUtils.isNotEmpty(canteenSplit)
							&& canteenSplit.compareTo(BigDecimal.ZERO) > 0) {
						split.setCanteenSplitPercent(canteenSplit);
						split.setSplitCanteenMoney(
								oPO.getOrderRealMoney().multiply(canteenSplit.divide(new BigDecimal(100))));
					}


					// 根据代理商id 获取岗位名称 部门名称
					try {
						// 查询企业信息
						Long userId = oPO.getUserId();
						if (BeanUtils.isNotEmpty(userId)) {
							CrmEnterprisePO crmEnterprisePO = CrmEnterpriseService.getByUserId(userId);
							oVO.setEnterpriseName(crmEnterprisePO.geteName());
						}
						
						CrmDepartmentPO crmDepartmentPO = iCrmDepartmentService.findByAgentId(agentId);
						if (crmDepartmentPO != null) {
							oVO.setdName(crmDepartmentPO.getDName());
						}
						CrmPositionPO crmPositionPO = iCrmPositionService.findByAgentId(agentId);
						if (BeanUtils.isNotEmpty(crmPositionPO)) {
							oVO.setpName(crmPositionPO.getName());
						}
					} catch (Exception e) {
						logger.error("#OrderCutController:#orderView:代理商岗位信息:", e);
					}

					Long canteenId = splitAssignSetPO.getCanteenId();// 获取食堂信息
					if (BeanUtils.isNotEmpty(canteenId)) {
						CrmCanteenBaseInfoPO cpInfoPO = iCrmCanteenBaseInfoService.findByCanteenId(canteenId);
						if (BeanUtils.isNotEmpty(cpInfoPO)) {// 查询食堂名称
							split.setName(cpInfoPO.getName());
						}
					}
					// 支付状态
					Integer status = oPO.getStatus();
					if (BeanUtils.isNotEmpty(status)) {
						switch (status) {
						case 1:
							oVO.setStatusName("未支付 ");
							break;
						case 2:
							oVO.setStatusName("已支付 ");
							break;
						case 3:
							oVO.setStatusName("已接单");
							break;
						case 4:
							oVO.setStatusName("已送达 ");
							break;
						case 5:
							oVO.setStatusName("已完成 ");
							break;
						case 6:
							oVO.setStatusName("已评价 ");
							break;
						case 7:
							oVO.setStatusName("已取消 ");
							break;
						case 8:
							oVO.setStatusName("待评价 ");
							break;
						case 9:
							oVO.setStatusName("待取餐 ");
							break;
						default:
							break;
						}
					}

				}

				modelAndView.addObject("canteenBaseInfoPO", canteenBaseInfoPO);
				modelAndView.addObject("oPO", oPO);
				modelAndView.addObject("oVO", oVO);
				modelAndView.addObject("cUserPO", UserPO); // 个人用户信息
				modelAndView.addObject("po", po);
				modelAndView.addObject("split", split); // 抽成分配设置表
				modelAndView.addObject("setPO", setPO); // 抽成分配设置表
				return modelAndView;
			}
		} catch (Exception e) {
			logger.error("##crmbusinesssplitdetail:orderView:", e);
		}
		return modelAndView;

	}


    /**
     * 通过食堂分账查询 分账明细
    * @Title: canteenSplit 
    * @author : lcl
    * @time : 2017年6月26日 上午11:41:09
    * @return ModelAndView    返回类型 
    * @throws
     */
	@RequestMapping(value = "/canteenSplit")
	@ResponseBody
	public ModelAndView canteenSplit(CrmBusinessSplitDetailParam param,HttpServletRequest request) {
		logger.info("#####CrmBusinessSplitDetailController###Param:" + param.toString());
		ModelAndView view = new ModelAndView("index/fashionable_detail");
		//根据食堂id查询订单集合
		String   clientId = request.getParameter("clientId");//食堂id
		Long  clientId1 =param.getClientId();
		/*List<CrmCanteenBaseInfoPO> allCanteen = iCrmCanteenBaseInfoService.getCanteenByIds();
		view.addObject("allCanteen", allCanteen);*/
		view.addObject("clientId", clientId);
		return view;
	}
	/**
	 *根据食堂id查询对应分账明细
	* @Title: canteenSplitPage 
	* @author : lcl
	* @time : 2017年6月26日 上午11:43:07
	* @return PageRespModel    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/canteenSplitPage")
	@ResponseBody
	public  PageRespModel canteenSplitPage(CrmBusinessSplitDetailParam param) {
		logger.info("#####CrmBusinessSplitDetailController###Param:" + param.toString());
		PageRespModel model = new PageRespModel();
		//传商家id订单
		Long canteenId = param.getClientId();
		param.setCanteenId(canteenId);
		Page<OrderVO>  cPage = orderService.findByCanteenId(param);
		model.setRows(cPage.result);
		model.setTotal(cPage.getTotalResult());
		return model;
	
	}
	/**
	 * 订单分账明细的 查看
	* @Title: orderDetailsInit 
	* @author : lcl
	* @time : 2017年6月26日 下午5:07:02
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/orderDetailsInit")
	@ResponseBody
	public ModelAndView orderDetailsInit(CrmBusinessSplitDetailParam param) {
		logger.info("#####CrmBusinessSplitDetailController###Param:" + param.toString());
		ModelAndView view = new ModelAndView("index/order_fashionalbe_detail");
		//根据食堂id查询订单集合
		Long  orderId =param.getOrderId();
		//查询订单集合
		OrderPO orderPO = orderService.findById(orderId);
		if(BeanUtils.isNotEmpty(orderPO)){
			view.addObject("orderPO", orderPO);
			if(BeanUtils.isNotEmpty(orderPO.getUserId())){//获取用户id 并取得 企业信息
				
			}
		}
		
		
		view.addObject("orderId", orderId);
		return view;
	}
	
	public List<CrmPermSourcesVO> getPermSourceVOs(){
		CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
		Long userId = sessionUser.getId();
		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(userId);//用户，角色映射

		Set<Long> roleSourceSet = new HashSet<Long>();
		if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
			Long roleId = crmPermUserRolePO.getRoleId();
			List<CrmPermRoleSourcesPO> permRoleSourcesPOs = roleSourcesService.findByRoleId(roleId);
			if(CollectionUtils.isNotEmpty(permRoleSourcesPOs)){
				for(CrmPermRoleSourcesPO cRoleSourcesPO : permRoleSourcesPOs){
					roleSourceSet.add(cRoleSourcesPO.getSourcesId());
				}
			}
			
		}

      //查询 角色的按钮资源
		CrmPermSourcesParam permSourcesParam = new  CrmPermSourcesParam();
		permSourcesParam.setDelFlagAll(1);//非空就全查询
		List<CrmPermSourcesPO> cRoleSourcesPOs = pSourcesService.listByParam(permSourcesParam);
		List<CrmPermSourcesVO> cSourcesVOs = BeanConvertor.copyList(cRoleSourcesPOs, CrmPermSourcesVO.class);
		for(CrmPermSourcesVO cSourcesVO : cSourcesVOs){
			if(BeanUtils.isNotEmpty(cSourcesVO) && BeanUtils.isNotEmpty(cSourcesVO.getChecked())){
				if(cSourcesVO.getChecked()==1){
					cSourcesVO.setCheck(true);
				}else{
					cSourcesVO.setCheck(false);
				}
			}
		}
		return cSourcesVOs;
	}
}