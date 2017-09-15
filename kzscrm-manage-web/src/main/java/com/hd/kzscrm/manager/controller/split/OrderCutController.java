package com.hd.kzscrm.manager.controller.split;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.hd.kzscrm.service.param.business.OrderParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.param.split.SplitCutInfoParam;
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
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.split.ISplitCutInfoService;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.vo.business.CrmBusinessSplitDetailVO;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;

/**
 * 订单分账
 * 
 * @description TODO
 * @author zyg
 *
 * @date 2017年6月13日 下午5:57:23
 */
@Controller
@RequestMapping("/orderCutController")
public class OrderCutController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderCutController.class);
	@Autowired
	ISplitCutInfoService mainService;

	@Autowired
	ICrmOrderService iCrmOrderService;

	@Autowired
	ICrmSplitAssignSetService crmSplitAssignSetService;

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
	 * 业务员跟踪记录表
	 */
	@Autowired
	ICrmBusinessOrderService businessOrderService;
	/**
	 * 食堂基本信息
	 */
	@Autowired
	ICrmCanteenBaseInfoService canteenBaseInfoService;
	/**
	 * 用户表
	 */
	@Autowired
	UserService userService;
	/**
	 * 业务员基本信息
	 */
	@Autowired
	ICrmBusinessService iCrmBusinessService;
	/**
	 * 代理商信息
	 */
	@Autowired
	ICrmAgentService iCrmAgentService;
	/**
	 * 部门信息
	 */
	@Autowired
	ICrmDepartmentService iCrmDepartmentService;
	/**
	 * 职位信息
	 */
	@Autowired
	ICrmPositionService iCrmPositionService;
	/**
	 * 企业信息
	 */
	@Autowired
	ICrmEnterpriseService CrmEnterpriseService;
	/**
	 * 抽成明细
	 */
	@Autowired
	ICrmBusinessSplitDetailService iCrmBusinessSplitDetailService;
	/**
	 * 商品图片
	 */
	@Autowired
	ICrmGoodImgService goodImgService;
	
	/**
	 * 初始化
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/init")
	@ResponseBody
	public ModelAndView init() {
		ModelAndView view = new ModelAndView("/collecting_statistics/order_fashinable");
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		return view;
	}

	/**
	 * 首页一览查询
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public PageRespModel queryPage(OrderParam param) throws Exception {
		LOGGER.info("#####OrderCutController###queryPage##RoleParam:" + param.toString());
		PageRespModel model = new PageRespModel();
		Page<OrderVO> pages = mainService.queryPage(param);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
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
			// List<CrmSplitAssignSetPO> po = crmSplitAssignSetService.findById(id);

			List<CrmBusinessSplitDetailPO> po = iCrmBusinessSplitDetailService.findByOrderNos(orderNo);
			if (BeanUtils.isNotEmpty(po) && BeanUtils.isNotEmpty(po.get(0))) {
				CrmBusinessSplitDetailPO setPO = po.get(0);
				// CrmSplitAssignSetVO cAssignSetVO = BeanConvertor.convertBean(setPO, CrmSplitAssignSetVO.class);
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
					LOGGER.error("#OrderCutController:抽成订单信息:", e);
				}

				Long businessId = setPO.getBusinessId(); // 获取业务员信息
				CrmSplitAssignSetPO splitAssignSetPO = crmSplitAssignSetService.findByBusinessId(businessId);
				if (orderPOs != null) {
					CrmBusinessOrderPO businessOrderPO = businessOrderService.findByBusinessId(businessId);// 根据业务员id查询订单
					oPO = iCrmOrderService.findById(id);
					if (BeanUtils.isNotEmpty(businessOrderPO)) {
						// Long orderId = businessOrderPO.getOrderId();//订单表orderPO
						if (BeanUtils.isNotEmpty(oPO) && BeanUtils.isNotEmpty(oPO.getUserId())) {
							Long userId = oPO.getUserId();// 获取用户资料
							UserPO = userService.getById(userId);
						}
						if (BeanUtils.isNotEmpty(oPO) && BeanUtils.isNotEmpty(oPO.getCanteenId())) {// 根据商家id查询对应信息
							canteenBaseInfoPO = canteenBaseInfoService.findByCanteenId(oPO.getCanteenId());
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
					CrmAgentPO crmAgentPO = iCrmAgentService.findByAgentId(agentId); // 获取代理商名称
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
						LOGGER.error("#OrderCutController:#orderView:代理商岗位信息:", e);
					}

					Long canteenId = splitAssignSetPO.getCanteenId();// 获取食堂信息
					if (BeanUtils.isNotEmpty(canteenId)) {
						CrmCanteenBaseInfoPO cpInfoPO = canteenBaseInfoService.findByCanteenId(canteenId);
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
			LOGGER.error("##orderCutController:orderView:", e);
		}
		return modelAndView;
	}

	/**
	 * 订单分账导出
	 * 
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/excelOut")
	@ResponseBody
	public void excelOut(HttpServletResponse response, OrderParam param) {
		Page<OrderVO> pages = iCrmOrderService.findPageSelect(param);
		List<OrderVO> pOrder = pages.result;
		Iterator<OrderVO> oIterator = pOrder.iterator();
		while (oIterator.hasNext()) {
			OrderVO orderVO = oIterator.next();
			if (BeanUtils.isNotEmpty(orderVO.getPayModel())) {
				if (orderVO.getPayModel().equals(1)) {
					orderVO.setPayModelName("余额");
				} else if (orderVO.getPayModel().equals(2)) {
					orderVO.setPayModelName("支付宝");
				} else {
					orderVO.setPayModelName("微信");
				}
			}

			if (BeanUtils.isNotEmpty(orderVO.getStatus())) {
				if (orderVO.getStatus().equals(1)) {
					orderVO.setStatusName("已下单");
				} else if (orderVO.getStatus().equals(2)) {
					orderVO.setStatusName("已支付");
				} else if (orderVO.getStatus().equals(3)) {
					orderVO.setStatusName("已接单");
				} else if (orderVO.getStatus().equals(4)) {
					orderVO.setStatusName("已送达");
				} else if (orderVO.getStatus().equals(5)) {
					orderVO.setStatusName("已完成");
				} else if (orderVO.getStatus().equals(6)) {
					orderVO.setStatusName("已评价");
				} else if (orderVO.getStatus().equals(7)) {
					orderVO.setStatusName("已取消");
				} else if (orderVO.getStatus().equals(8)) {
					orderVO.setStatusName("待评价");
				} else {
					orderVO.setStatusName("待取餐");
				}
			}
			if (BeanUtils.isNotEmpty(orderVO.getOrderNo())) {
				SplitCutInfoPO scp = mainService.findByOrderNo(orderVO.getOrderNo());
				if (BeanUtils.isNotEmpty(scp) && BeanUtils.isNotEmpty(scp.getRealMoney())) {
					orderVO.setCanteenMoney(scp.getRealMoney());
				}
			}
		}
		if (pOrder != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("orderFlowNo", "流水号");
			map.put("orderNo", "订单编号");
			map.put("realMoney", "实付金额");
			map.put("payModelName", "支付方式");
			map.put("payTime", "支付时间");
			map.put("canteenMoney", "商家金额");
			map.put("cutMoney", "抽成金额");
			map.put("channelMoney", "通道费");
			map.put("statusName", "状态");
			try {
				ExcelUtil.writeXls(response, "订单分账页面", map, pOrder, OrderVO.class);
			} catch (Exception e) {
				LOGGER.error("#OrderCutController:#excelOut:订单分账导出", e);
			}
		}
	}

	/**
	 * 一览查询
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/queryErrorPage")
	@ResponseBody
	public PageRespModel queryErrorPage(SplitCutInfoParam param) throws Exception {
		LOGGER.info("#####SplitOrderSuccessController###queryErrorPage##SplitCutInfoParam:" + param.toString());
		PageRespModel model = new PageRespModel();
		Page<SplitCutInfoParam> pages = mainService.queryErrorPage(param);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);

		return model;
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
