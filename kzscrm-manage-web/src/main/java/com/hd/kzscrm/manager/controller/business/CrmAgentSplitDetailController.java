package com.hd.kzscrm.manager.controller.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.business.OrderGoodsItemPO;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.canteen.GoodImgPO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmBusinessSplitDetailParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
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
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmGoodImgService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.split.ISplitCutInfoService;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.vo.agent.CrmSplitAssignSetVO;
import com.hd.kzscrm.service.vo.business.CrmBusinessSplitDetailVO;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;

/**
 * 代理商分账 crmAgentSplitDetail CRMBUSINESSSPLIT詳細
 * 
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmagentsplitdetail")
public class CrmAgentSplitDetailController extends BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(CrmAgentSplitDetailController.class);
	@Autowired
	ICrmBusinessService mainService;

	/**
	 * 代理商
	 */
	@Autowired
	ICrmAgentService icCrmAgentService;

	/**
	 * 订单信息
	 */
	@Autowired
	ICrmOrderService iCrmOrderService;

	/**
	 * 抽成明细
	 */
	@Autowired
	ISplitCutInfoService iSplitCutInfoService;

	/**
	 * 食堂信息
	 */
	@Autowired
	ICrmCanteenBaseInfoService iCrmCanteenBaseInfoService;

	@Autowired
	ICrmSplitAssignSetService crmSplitAssignSetService;

	@Autowired
	ICrmBusinessOrderService crmBusinessOrderService;

	@Resource
	ICrmPermUserRoleService iCrmPermUserRoleService;
	@Autowired
	ICrmPermRoleSourcesService roleSourcesService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
	ICrmPermSourcesService pSourcesService;

	/**
	 * 业务员基本信息
	 */
	@Autowired
	ICrmBusinessService iCrmBusinessService;
	@Autowired
	ICrmBusinessSplitDetailService businessSplitDetailService;

	/**
	 * 用户表
	 */
	@Autowired
	UserService userService;
	/**
	 * 企业信息
	 */
	@Autowired
	ICrmEnterpriseService CrmEnterpriseService;
	/**
	 * 商品图片
	 */
	@Autowired
	ICrmGoodImgService goodImgService;

	/**
	 * 客户列表
	 */
	@Autowired
	ICrmClientResourceService iCrmClientResourceService;

	@Autowired
	ICrmDepartmentService iCrmDepartmentService;

	@Autowired
	ICrmPositionService iCrmPositionService;

	/**
	 * 团队信息
	 */
	@Autowired
	ICrmTeamService iCrmTeamService;

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
	public ModelAndView init(CrmBusinessSplitDetailParam param) {
		ModelAndView view = new ModelAndView("/collecting_statistics/salesman_fashinable");
		// List<CrmTeamPO> allTeam = iCrmTeamService.getTeamByIds();
		// 查询平台团队信息
		CrmTeamParam crmTeamParam = new CrmTeamParam();
		crmTeamParam.setDelFlag(1);
		crmTeamParam.setType(1);// 团队类型，1.平台，2.代理商
		List<CrmTeamPO> allTeam = iCrmTeamService.commonQuery(crmTeamParam);
		if (CollectionUtils.isNotEmpty(allTeam)) {
			List<Long> teamIds = new LinkedList<Long>();
			for (CrmTeamPO crmTeamPO : allTeam) {
				teamIds.add(crmTeamPO.getId());
			}

			// 查询团队的业务员
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setTeamIds(teamIds);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
			view.addObject("businessPOs", crmBusinessPOs);
		}

		CrmBusinessSplitDetailParam param2 = new CrmBusinessSplitDetailParam();
		Page<CrmBusinessSplitDetailVO> pages = businessSplitDetailService.queryPageList(param2);
		int num = pages.getTotalResult();
		param2.setJobStatus(0);// 离职
		Page<CrmBusinessSplitDetailVO> pages2 = businessSplitDetailService.queryPageList(param2);
		int num2 = pages2.getTotalResult();
		param2.setJobStatus(1);// 在职
		Page<CrmBusinessSplitDetailVO> pages3 = businessSplitDetailService.queryPageList(param2);
		int num3 = pages3.getTotalResult();

		Map<String, Object> map = new HashMap<>();
		map.put("all", 0);
		map.put("onjob", 0);
		map.put("quitjob", 0);
		if (BeanUtils.isNotEmpty(pages)) {// 全部
			map.put("all", num);
		}
		if (BeanUtils.isNotEmpty(pages2)) {
			map.put("quitjob", num2);
		}
		if (BeanUtils.isNotEmpty(pages3)) {
			map.put("onjob", num3);
		}
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		view.addObject("map", map);
		view.addObject("allTeam", allTeam);
		return view;
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
		Page<CrmBusinessSplitDetailVO> pages = businessSplitDetailService.queryPage(param);
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<CrmBusinessSplitDetailVO>();

		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list) {
			Long businessId = crmBusinessSplitDetailVO.getBusinessId(); // 根据businessId获取业务员基本信息
			CrmBusinessPO crmBusinessPO = mainService.findByBusinessId(businessId);
			Long teamId = crmBusinessPO.getTeamId(); // 获取teamid
			crmBusinessSplitDetailVO.setBusinessId(crmBusinessPO.getId());
			crmBusinessSplitDetailVO.setBusinessName(crmBusinessPO.getName());
			crmBusinessSplitDetailVO.setWorkTime(crmBusinessPO.getWorkTime());
			crmBusinessSplitDetailVO.setJobStatus(crmBusinessPO.getJobStatus());

			CrmTeamPO crmTeamPO = iCrmTeamService.findByTeamId(teamId); // 根据teamId查询team名称
			if (BeanUtils.isNotEmpty(crmTeamPO)) {
				crmBusinessSplitDetailVO.setTeamName(crmTeamPO.getName());
			}

			CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
			crmClientResourceParam.setBusinessId(businessId);
			BigDecimal clientNatureNum = iCrmClientResourceService.getBusinessNumber(crmClientResourceParam);
			crmBusinessSplitDetailVO.setClientNatureNum(clientNatureNum);
			// 根据businessId获取业务员金额
			CrmSplitAssignSetPO crmSplitAssignSetPO = crmSplitAssignSetService.findByBusinessId(businessId);
			if (BeanUtils.isNotEmpty(crmSplitAssignSetPO)) {
				// 业务员分账金额
				BigDecimal splitPercent = crmSplitAssignSetPO.getBusinssSplitPercent(); // 业务员分账比例
				BigDecimal businessSplitMoney = null;
				if (BeanUtils.isNotEmpty(crmBusinessSplitDetailVO.getBusinessMoney()) && BeanUtils.isNotEmpty(splitPercent)
						&& splitPercent.compareTo(BigDecimal.ZERO) > 0) {
					BigDecimal businessMoney = crmBusinessSplitDetailVO.getBusinessMoney();
					businessSplitMoney = businessMoney.multiply(splitPercent).divide(new BigDecimal(100));
					crmBusinessSplitDetailVO.setBusinessSplitMoney(businessSplitMoney);
				}
				// 抽成金额
				OrderPO orderPO = iCrmOrderService.findByOrderNo(crmBusinessSplitDetailVO.getOrderNo()); // 根据订单编号获取实际订单金额
				BigDecimal orderRealMoney = orderPO.getOrderRealMoney(); // 订单实际金额
				BigDecimal cutMoney = orderRealMoney.subtract(businessSplitMoney);
				crmBusinessSplitDetailVO.setCutMoney(cutMoney);
			}


			listNew.add(crmBusinessSplitDetailVO);
		}

		pages.result = listNew;
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);

		return model;
	}

	/**
	 * 业务员导出功能
	 * 
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/excelOut")
	@ResponseBody
	public void excelOut(HttpServletResponse response, CrmBusinessSplitDetailParam param) {
		Page<CrmBusinessSplitDetailVO> pages = businessSplitDetailService.queryPage(param);
		List<CrmBusinessSplitDetailVO> crmAgentVOs = pages.result;
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<CrmBusinessSplitDetailVO>();

		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list) {
			Long businessId = crmBusinessSplitDetailVO.getBusinessId(); // 根据businessId获取业务员基本信息
			CrmBusinessPO crmBusinessPO = mainService.findByBusinessId(businessId);
			Long teamId = crmBusinessPO.getTeamId(); // 获取teamid
			crmBusinessSplitDetailVO.setBusinessId(crmBusinessPO.getId());
			crmBusinessSplitDetailVO.setBusinessName(crmBusinessPO.getName());
			crmBusinessSplitDetailVO.setWorkTime(crmBusinessPO.getWorkTime());
			if (BeanUtils.isNotEmpty(crmBusinessPO.getJobStatus())) {
				if (crmBusinessPO.getJobStatus().equals(0)) {
					crmBusinessSplitDetailVO.setJobStatusName("离职");
				} else {
					crmBusinessSplitDetailVO.setJobStatusName("在职");
				}
			}

			CrmTeamPO crmTeamPO = iCrmTeamService.findByTeamId(teamId); // 根据teamId查询team名称
			crmBusinessSplitDetailVO.setTeamName(crmTeamPO.getName());

			CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
			crmClientResourceParam.setBusinessId(businessId);
			BigDecimal clientNatureNum = iCrmClientResourceService.getBusinessNumber(crmClientResourceParam);
			crmBusinessSplitDetailVO.setClientNatureNum(clientNatureNum);
			// 根据businessId获取业务员金额
			CrmSplitAssignSetPO crmSplitAssignSetPO = crmSplitAssignSetService.findByBusinessId(businessId);
			// 业务员分账金额
			BigDecimal splitPercent = crmSplitAssignSetPO.getBusinssSplitPercent(); // 业务员分账比例
			BigDecimal businessSplitMoney = null;
			if (BeanUtils.isNotEmpty(crmBusinessSplitDetailVO.getBusinessMoney()) && BeanUtils.isNotEmpty(splitPercent)
					&& splitPercent.compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal businessMoney = crmBusinessSplitDetailVO.getBusinessMoney();
				businessSplitMoney = businessMoney.multiply(splitPercent).divide(new BigDecimal(100));
				crmBusinessSplitDetailVO.setBusinessSplitMoney(businessSplitMoney);
			}

			// 抽成金额
			OrderPO orderPO = iCrmOrderService.findByOrderNo(crmBusinessSplitDetailVO.getOrderNo()); // 根据订单编号获取实际订单金额
			BigDecimal orderRealMoney = orderPO.getOrderRealMoney(); // 订单实际金额
			BigDecimal cutMoney = orderRealMoney.subtract(businessSplitMoney);
			crmBusinessSplitDetailVO.setCutMoney(cutMoney);

			listNew.add(crmBusinessSplitDetailVO);
		}
		if (crmAgentVOs != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("businessId", "ID");
			map.put("businessName", "名称");
			map.put("teamName", "所属团队");
			map.put("workTime", "入职时间");
			map.put("jobStatusName", "职业状态");
			map.put("clientNatureNum", "正式客户");
			map.put("cutMoney", "抽成金额");
			map.put("businessSplitMoney", "分账金额");
			try {
				ExcelUtil.writeXls(response, "业务员分账", map, crmAgentVOs, CrmBusinessSplitDetailVO.class);
			} catch (Exception e) {
				logger.error("CrmAgentSplitDetailController:excelOut:", e);
			}
		}
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{businessId}")
	@ResponseBody
	public ModelAndView viewInfo(CrmBusinessSplitDetailParam param, @PathVariable Long businessId) {
		ModelAndView view = new ModelAndView("collecting_statistics/business_fashionable_detail");
		view.addObject("businessId", businessId);
		// List<CrmCanteenBaseInfoPO> allCanteen =
		// iCrmCanteenBaseInfoService.getCanteenByIds();
		CrmCanteenBaseInfoParam crmCanteenBaseInfoParam = new CrmCanteenBaseInfoParam();
		crmCanteenBaseInfoParam.setBusinessId(businessId);
		// crmCanteenBaseInfoParam.setStatus(2);//1未审核、2已通过 、3已注销 4.未通过 5.申请中
		crmCanteenBaseInfoParam.setClientNature(3);// 1.散客，2.保护客户，3.正式客户
		crmCanteenBaseInfoParam.setDelFlag(1);
		List<CrmCanteenBaseInfoPO> allCanteen = iCrmCanteenBaseInfoService.commonQuery(crmCanteenBaseInfoParam);
		view.addObject("allCanteen", allCanteen);
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
		Long id = param.getId();// 获取平台客户id
		CrmBusinessSplitDetailPO businessSplitDetailPO = businessSplitDetailService.getById(id);
		if (BeanUtils.isNotEmpty(businessSplitDetailPO)) {
			Long business = businessSplitDetailPO.getBusinessId();
			view.addObject("businessId", business);
		}
		view.addObject("agentId", param.getAgentId());
		view.addObject("businessId", param.getBusinessId());
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
		Page<CrmBusinessSplitDetailVO> pages = businessSplitDetailService.queryPage(param);
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
			if (BeanUtils.isNotEmpty(splitCutInfoPO)) {
				crmBusinessSplitDetailVO.setRealMoney(splitCutInfoPO.getOrderRealMoney());
				crmBusinessSplitDetailVO.setCutMoney(splitCutInfoPO.getCutMoney());
				crmBusinessSplitDetailVO.setCutRatio(splitCutInfoPO.getCutRatio());
				BigDecimal cutRatio = splitCutInfoPO.getCutRatio(); // 分账比例
				BigDecimal orderRealMoney = splitCutInfoPO.getOrderRealMoney(); // 实际金额
				BigDecimal splitMoney = cutRatio.multiply(orderRealMoney);
				crmBusinessSplitDetailVO.setSplitMoney(splitMoney); // 获取分账金额
			}

			Long agentUserId = crmBusinessSplitDetailVO.getAgentUserId();
			CrmAgentPO crmAgentPO = icCrmAgentService.findByAgentNo(agentUserId); // 根据代理商编号查询代理商信息
			if (BeanUtils.isNotEmpty(crmAgentPO.getBusinessId())) {
				CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = iCrmCanteenBaseInfoService
						.findByBusinessId(crmAgentPO.getBusinessId()); // 根据业务员id查询食堂信息
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
	 * 分账明细导出
	 * 
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/viewExcelOul")
	@ResponseBody
	public void viewExcelOul(HttpServletResponse response, CrmBusinessSplitDetailParam param) {
		Page<CrmBusinessSplitDetailVO> pages = businessSplitDetailService.queryPage(param);
		List<CrmBusinessSplitDetailVO> list = pages.result;
		List<CrmBusinessSplitDetailVO> listNew = new ArrayList<CrmBusinessSplitDetailVO>();
		for (CrmBusinessSplitDetailVO crmBusinessSplitDetailVO : list) {
			try {
				String orderNo = crmBusinessSplitDetailVO.getOrderNo();
				OrderPO orderPO = iCrmOrderService.findByOrderNo(orderNo); // 根据订单编号
																			// 查询订单信息
				crmBusinessSplitDetailVO.setOrderNo(orderPO.getOrderNo());
				crmBusinessSplitDetailVO.setOrderFlowNo(orderPO.getOrderFlowNo());
				crmBusinessSplitDetailVO.setGeneralLedgerDate(orderPO.getGeneralLedgerDate());
				crmBusinessSplitDetailVO.setPayModel(orderPO.getPayModel());
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
			} catch (Exception e) {
				logger.error("#CrmAgentSplitDetailController#viewExcelOul:", e);
			}
			listNew.add(crmBusinessSplitDetailVO);
		}

		if (list != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("orderNo", "订单编号");
			map.put("orderFlowNo", "支付流水号");
			map.put("payModelName", "支付方式");
			map.put("generalLedgerDate", "分账时间");
			map.put("canteenName", "食堂名称");
			map.put("realMoney", "实付金额");
			map.put("cutMoney", "抽成金额");
			map.put("cutRatio", "分账比例（￥）");
			map.put("splitMoney", "分账金额（￥）");
			try {
				ExcelUtil.writeXls(response, "分账明细", map, list, CrmBusinessSplitDetailVO.class);
			} catch (Exception e) {
				logger.error("CrmBusinessSplitDetailController:viewExcelOul:", e);
			}
		}
	}

	/**
	 * 分账明细查看
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
			List<CrmBusinessSplitDetailPO> po = businessSplitDetailService.findByOrderNos(orderNo);
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
						CrmBusinessSplitDetailPO crmBusinessSplitDetailPO = businessSplitDetailService
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

	public List<CrmPermSourcesVO> getPermSourceVOs() {
		CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
		Long userId = sessionUser.getId();
		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(userId);// 用户，角色映射

		Set<Long> roleSourceSet = new HashSet<Long>();
		if (BeanUtils.isNotEmpty(crmPermUserRolePO)) {
			Long roleId = crmPermUserRolePO.getRoleId();
			List<CrmPermRoleSourcesPO> permRoleSourcesPOs = roleSourcesService.findByRoleId(roleId);
			if (CollectionUtils.isNotEmpty(permRoleSourcesPOs)) {
				for (CrmPermRoleSourcesPO cRoleSourcesPO : permRoleSourcesPOs) {
					roleSourceSet.add(cRoleSourcesPO.getSourcesId());
				}
			}

		}

		// 查询 角色的按钮资源
		CrmPermSourcesParam permSourcesParam = new CrmPermSourcesParam();
		permSourcesParam.setDelFlagAll(1);// 非空就全查询
		List<CrmPermSourcesPO> cRoleSourcesPOs = pSourcesService.listByParam(permSourcesParam);
		List<CrmPermSourcesVO> cSourcesVOs = BeanConvertor.copyList(cRoleSourcesPOs, CrmPermSourcesVO.class);
		for (CrmPermSourcesVO cSourcesVO : cSourcesVOs) {
			if (BeanUtils.isNotEmpty(cSourcesVO) && BeanUtils.isNotEmpty(cSourcesVO.getChecked())) {
				if (cSourcesVO.getChecked() == 1) {
					cSourcesVO.setCheck(true);
				} else {
					cSourcesVO.setCheck(false);
				}
			}
		}
		return cSourcesVOs;
	}
}