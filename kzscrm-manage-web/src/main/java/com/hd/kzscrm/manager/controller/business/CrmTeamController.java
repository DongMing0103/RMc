package com.hd.kzscrm.manager.controller.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.EnterpriseStyleEnum;
import com.hd.kzscrm.common.enums.business.CrmBusinessEnum.CrmBusinessUserType;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.model.RespModel.RespCode;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.agent.CrmWorkTargetPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.business.CrmBusinessOrderParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmWorkTargetService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmPositionService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.kzscrm.service.vo.business.CrmTeamVO;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;

/**
 * crmTeam CRMTEAM
 * 
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmteam")
public class CrmTeamController extends BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(CrmTeamController.class);
	/**
	 * 团队信息
	 */
	@Autowired
	ICrmTeamService iCrmTeamService;
	/**
	 * 业务员
	 */
	@Autowired
	ICrmBusinessService iCrmBusinessService;

	/**
	 * 食堂信息
	 */
	@Autowired
	ICrmCanteenBaseInfoService iCrmCanteenBaseInfoService;

	/**
	 * 订单统计
	 */
	@Autowired
	ICrmBusinessOrderService iCrmBusinessOrderService;

	/**
	 * 工作目标
	 */
	@Autowired
	ICrmWorkTargetService iCrmWorkTargetService;

	/**
	 * 客户资源
	 */
	@Autowired
	ICrmClientResourceService iCrmClientResourceService;

	/**
	 * 代理商
	 */
	@Autowired
	ICrmAgentService iCrmAgentService;

	/**
	 * 岗位信息
	 */
	@Autowired
	ICrmPositionService iCrmPositionService;

	/**
	 * 企业(学校)信息
	 */
	@Autowired
	ICrmEnterpriseService iCrmEnterpriseService;

	@Resource
	private IBaseAreaService areaService;
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
	 ** 预留实现
	 */
	public void initControler(HttpServletRequest request, Map<String, Object> map) {

	}

	/**
	 * 根据团队ID，获取业务员经理
	 * 
	 * @author 黄霄仪
	 * @date 2017年7月26日 下午7:55:39
	 */
	@RequestMapping("/getBusinessManager")
	@ResponseBody
	public RespModel getBusinessManager(@RequestBody CrmTeamParam crmTeamParam) {
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setTeamId(crmTeamParam.getId());
		crmBusinessParam.setUserType(CrmBusinessUserType.BUSINESS_MANAGER.getCode());
		List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.listByParam(crmBusinessParam);
		RespModel respModel = RespModel.success("成功！");
		respModel.setRows(crmBusinessPOs);
		return respModel;
	}

	/**
	 * 获取团队
	 * @author 黄霄仪
	 * @date 2017年7月27日 上午11:48:03
	 */
	@RequestMapping("/getCrmTeam")
	@ResponseBody
	public RespModel getCrmTeam(@RequestBody CrmTeamParam crmTeamParam){
		crmTeamParam.setType(CrmControllerHelper.getSessionType());
		List<CrmTeamPO> crmTeamPOs = iCrmTeamService.listByParam(crmTeamParam);
		RespModel respModel=RespModel.success("成功！");
		respModel.setRows(crmTeamPOs);
		return respModel;
	}
	/**
	 *
	 * 业务团队初始化
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/init")
	@ResponseBody
	public ModelAndView init() {
		ModelAndView view = null;
		try {
			CrmAccountPO crmAccountPO = CrmControllerHelper.getSessionUser();
			// 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
			Integer type = crmAccountPO.getType();
			if (type == 1) {
				view = new ModelAndView("/team_management/business_team");
				view.addObject("type", type);
			} else if (type == 2) {
				view = new ModelAndView("/my_team/salesman_team");
				/*
				 * List<CrmTeamPO> crmTeamPOs = iCrmTeamService.getType(type);
				 * List<Long> agentIds = new LinkedList<>(); if
				 * (BeanUtils.isNotEmpty(crmTeamPOs)) { for (int i = 0; i <
				 * crmTeamPOs.size(); i++) {
				 * agentIds.add(crmTeamPOs.get(i).getAgentId()); } }
				 * view.addObject("agentIds", agentIds);
				 */
				view.addObject("type", type);
			}
			ParamMap param = new ParamMap();

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
			view.addObject("cSourcesVOs", cSourcesVOs);
		} catch (Exception e) {
			logger.error("#crmteam#init:", e);
		}
		return view;
	}

	/**
	 * 团队设置
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月6日 下午5:06:35
	 */
	@RequestMapping("/showTeamSet")
	@ResponseBody
	public ModelAndView showTeamSet(CrmTeamParam param) {
		ModelAndView view = new ModelAndView("/system_operate/add_or_edit_team_struct");
		Long id = param.getId();
		Integer sessionType = CrmControllerHelper.getSessionType();
		if(sessionType==CrmAccountUserType.AGENT.getCode()){
			param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
		}
		param.setType(sessionType);
		//编辑，编辑的时候，查找到
//		if(BeanUtils.isNotEmpty(id)){
//			param.setTopParentId(id);
//			param.setId(null);
//		}
		//不为空就是编辑，否则是新增
		if(BeanUtils.isNotEmpty(id)){
			CrmTeamPO crmTeamPO = iCrmTeamService.get(CrmTeamPO.class, id);
			List<Long> parentIds=new LinkedList<>();
			String parentIdsStr = crmTeamPO.getParentIds();
			if(BeanUtils.isNotEmpty(parentIdsStr)){
				String[] parentIdsSplit = parentIdsStr.split(",");
				for (String parentId : parentIdsSplit) {
					parentIds.add(Long.parseLong(parentId));
				}
				List<CrmTeamVO> crmTeamVOs = iCrmTeamService.findByIds(parentIds);
				List<CrmTeamVO> parentCrmTeamVOs=new LinkedList<>();
				for (CrmTeamVO crmTeamVO : crmTeamVOs) {
					//如果不等于当前要编辑的团队
					parentCrmTeamVOs.add(crmTeamVO);
				}
				//按等级排序
				Collections.sort(parentCrmTeamVOs, new Comparator<CrmTeamVO>(){
					@Override
					public int compare(CrmTeamVO o1, CrmTeamVO o2) {
						if(o1.getLevel()>o2.getLevel()){
							return 1;
						}else if(o1.getLevel()<o2.getLevel()){
							return -1;
						}
						return 0;
					}
					
				});
//				parentCrmTeamVOs.remove(parentCrmTeamVOs.size()-1);//不选择自己
				view.addObject("parentCrmTeamVOs", parentCrmTeamVOs);
			}
			//当前团队
			view.addObject("crmTeamVO", crmTeamPO);
		}
		//置空ID，查找所有团队
		param.setId(null);
		List<CrmTeamPO> crmTeamPOs = iCrmTeamService.listByParam(param);
		view.addObject("crmTeamVOs", crmTeamPOs);
		return view;
	}

	/**
	 * 删除团队
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月7日 下午5:09:27
	 */
	@RequestMapping(value = "deleteTeam", method = RequestMethod.POST)
	@ResponseBody
	public RespModel deleteTeam(@RequestBody CrmTeamParam param) {
		Long id = param.getId();
		RespModel respModel = RespModel.setRespCode(RespCode.SUCCESS);
		//判断是否有子团队
		List<CrmTeamPO> crmTeamList =  iCrmTeamService.findByParentId(id);
		if(CollectionUtils.isNotEmpty(crmTeamList) && crmTeamList.size()>1){
			return RespModel.failure("有子团队绑定的团队，不能删除");
		}
		List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.findByTeamId(id);
		if (BeanUtils.isNotEmpty(crmBusinessPOs)) {
			return RespModel.failure("有业务员绑定的团队，不能删除");
		}
		try {
			iCrmTeamService.deleteById(id);
		} catch (BizException e) {
			e.printStackTrace();
			return RespModel.failure(e.getMessage());
		}
		return respModel;
	}

	/**
	 * 初始化团队结构
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月2日 下午3:26:37
	 */
	@RequestMapping(value = "initTeamStrctrue", method = RequestMethod.GET)
	public ModelAndView initTeamStrctrue() {
		CrmAccountPO crmAccountPO = CrmControllerHelper.getSessionUser();
		// 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmAccountPO.getUserType();
		ModelAndView modelAndView = new ModelAndView("system_set/team_structure");
		// 如果是代理商或管理员就执行
		if (BeanUtils.isOr(userType == CrmAccountUserType.ADMIN.getCode(),
				userType == CrmAccountUserType.AGENT.getCode())) {
			// iCrmTeamService.showTeamStrctrue(param);
		}

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
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);

		return modelAndView;
	}

	/**
	 * 系统设置-团队结构
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月2日 下午1:41:08
	 */
	@RequestMapping(value = "showTeamStrctrue")
	@ResponseBody
	public PageRespModel showTeamStrctrue(CrmTeamParam param) {
		PageRespModel pageRespModel = new PageRespModel();
		CrmAccountPO crmAccountPO = CrmControllerHelper.getSessionUser();
		// 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmAccountPO.getUserType();
		param.setType(crmAccountPO.getType());
		if(userType==CrmAccountUserType.AGENT.getCode()){
			param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
		}
		Page<CrmTeamVO> showTeamStrctrue = iCrmTeamService.showTeamStrctrue(param);
		pageRespModel.setRows(showTeamStrctrue.result);
		pageRespModel.setTotal(showTeamStrctrue.getTotalResult());
		return pageRespModel;
	}

	/**
	 * 一览查询
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public PageRespModel queryPage(CrmTeamParam param) throws Exception {
		PageRespModel model = new PageRespModel();
		CrmAccountPO crmAccountPO = CrmControllerHelper.getSessionUser();
		// 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmAccountPO.getUserType();
		param.setType(crmAccountPO.getType());
		if(userType==CrmAccountUserType.AGENT.getCode()){
			param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
		}
		Page<CrmTeamVO> pages = iCrmTeamService.queryPage(param);
		List<CrmTeamVO> list = pages.result;
		List<CrmTeamVO> listNew = new ArrayList<CrmTeamVO>();
		for (CrmTeamVO crmTeamVO : list) {
			Long id = crmTeamVO.getId();
			Integer type = crmTeamVO.getType();

			// 区域名称
			Long areaCode = crmTeamVO.getAreaCode();
			if (areaCode != null) {
				BeanUtils.databaseInjectField(crmTeamVO, "areaName", new String[] { "area_code=" + areaCode },
						new String[] { "area_name areaName" });
			}
			
			// 统计代理商
			
			List<CrmBusinessPO> lcbp = iCrmBusinessService.findByTeamId(id);
			if (lcbp != null && lcbp.size() > 0) {
				String businessIds = "";
				for (CrmBusinessPO crmBusinessPO : lcbp) {
					businessIds += crmBusinessPO.getId() + ",";
					try {
						// crmBusinessPO = lcbp.get(0);
						Long businessId = crmBusinessPO.getId();

						// 获取业务经理信息 '用户类型 1.业务员，2.业务员经理,3.代理商总监'
						if (crmBusinessPO.getUserType() != null && crmBusinessPO.getUserType() == 2) { // 获取业务经理名称
							crmTeamVO.setBusinessName(crmBusinessPO.getName());
						}
						// 获取代理商数量信息
						CrmAgentParam crmAgentParam = new CrmAgentParam();
						crmAgentParam.setTeamId(id);
						crmAgentParam.setAuthenticationStatus(1);
						crmAgentParam.setAgentStatus(1);
						BigDecimal agentNum = iCrmAgentService.countAgentNum(crmAgentParam);
						crmTeamVO.setAgentNum(agentNum);
						
						// 获取团队人数
						CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
						crmBusinessParam.setTeamId(id);
						crmBusinessParam.setType(type);
						crmBusinessParam.setJobStatus(1);

						BigDecimal teamNumber = iCrmBusinessService.getTeamNumber(crmBusinessParam);
						crmTeamVO.setTeamNumber(teamNumber);

						// 获取订单金额
						CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
						businessOrderParam.setBusinessId(businessId);
						BigDecimal realMoney = iCrmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
						crmTeamVO.setRealMoney(realMoney);

						// 获取订单数量
						BigDecimal completeOrderMoney = iCrmBusinessOrderService.countOrderId(businessId);
						crmTeamVO.setCompleteOrderMoney(completeOrderMoney);
						
					} catch (Exception e) {
						logger.error("#crmteam#queryPage:", e);
					}
				}
				// 统计食堂数量
				businessIds = businessIds.substring(0, businessIds.length() - 1); // 获取多个businessId
				CrmClientResourceParam clientResourceParam = new CrmClientResourceParam();
				try {
					List<CrmClientResourcePO> clientList = iCrmClientResourceService
							.getByBusinessId(clientResourceParam); // 根据businessId
																	// 查询客户资源信息
					if (BeanUtils.isNotEmpty(clientList) && clientList.size() > 0) {
						String clientTypes = "";
						for (CrmClientResourcePO crmClientResourcePO : clientList) {
							clientTypes += crmClientResourcePO.getClientType();
							if (crmClientResourcePO.getClientType() != 1) { // 客户类型为食堂
								clientResourceParam.setBusinessIdS(businessIds);
								clientResourceParam.setClientTypeS(clientTypes);
								BigDecimal canteenNum = iCrmClientResourceService.countCanteen(clientResourceParam);
								crmTeamVO.setCanteenNum(canteenNum);
							}
						}
					}
				} catch (Exception e) {
					logger.error("##crmTeamController:", e);
				}

			}
			listNew.add(crmTeamVO);
		}
		pages.result = listNew;
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
	}

	/**
	 * 导出功能
	 */
	@RequestMapping("/excelOut")
	@ResponseBody
	public void excelOut(HttpServletResponse response, CrmTeamParam param) {
		if (BeanUtils.isNotEmpty(param.getIds())) {
			/*
			 * param.setIds(param.getIds().substring(0, param.getIds().size()
			 * -1));
			 */
		}
		Page<CrmTeamVO> page = iCrmTeamService.findPageSelect(param);
		List<CrmTeamVO> listVos = page.result;

		List<CrmTeamVO> listNew = new ArrayList<CrmTeamVO>();
		for (CrmTeamVO crmTeamVO : listVos) {
			Long id = crmTeamVO.getId();
			Integer type = crmTeamVO.getType();

			// 区域名称
			Long areaCode = crmTeamVO.getAreaCode();
			if (areaCode != null) {
				BeanUtils.databaseInjectField(crmTeamVO, "areaName", new String[] { "area_code=" + areaCode },
						new String[] { "area_name areaName" });
			}
			
			// 统计代理商
			
			List<CrmBusinessPO> lcbp = iCrmBusinessService.findByTeamId(id);
			if (lcbp != null && lcbp.size() > 0) {
				String businessIds = "";
				for (CrmBusinessPO crmBusinessPO : lcbp) {
					businessIds += crmBusinessPO.getId() + ",";
					try {
						// crmBusinessPO = lcbp.get(0);
						Long businessId = crmBusinessPO.getId();

						// 获取业务经理信息 '用户类型 1.业务员，2.业务员经理,3.代理商总监'
						if (crmBusinessPO.getUserType() != null && crmBusinessPO.getUserType() == 2) { // 获取业务经理名称
							crmTeamVO.setBusinessName(crmBusinessPO.getName());
						}
						// 获取代理商数量信息
						CrmAgentParam crmAgentParam = new CrmAgentParam();
						crmAgentParam.setTeamId(id);
						crmAgentParam.setAuthenticationStatus(1);
						crmAgentParam.setAgentStatus(1);
						BigDecimal agentNum = iCrmAgentService.countAgentNum(crmAgentParam);
						crmTeamVO.setAgentNum(agentNum);
						
						// 获取团队人数
						CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
						crmBusinessParam.setTeamId(id);
						crmBusinessParam.setType(type);
						crmBusinessParam.setJobStatus(1);

						BigDecimal teamNumber = iCrmBusinessService.getTeamNumber(crmBusinessParam);
						crmTeamVO.setTeamNumber(teamNumber);

						// 获取订单金额
						CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
						businessOrderParam.setBusinessId(businessId);
						BigDecimal realMoney = iCrmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
						crmTeamVO.setRealMoney(realMoney);

						// 获取订单数量
						BigDecimal completeOrderMoney = iCrmBusinessOrderService.countOrderId(businessId);
						crmTeamVO.setCompleteOrderMoney(completeOrderMoney);
						
					} catch (Exception e) {
						logger.error("#crmteam#queryPage:", e);
					}
				}
				// 统计食堂数量
				businessIds = businessIds.substring(0, businessIds.length() - 1); // 获取多个businessId
				CrmClientResourceParam clientResourceParam = new CrmClientResourceParam();
				try {
					List<CrmClientResourcePO> clientList = iCrmClientResourceService
							.getByBusinessId(clientResourceParam); // 根据businessId
																	// 查询客户资源信息
					if (BeanUtils.isNotEmpty(clientList) && clientList.size() > 0) {
						String clientTypes = "";
						for (CrmClientResourcePO crmClientResourcePO : clientList) {
							clientTypes += crmClientResourcePO.getClientType();
							if (crmClientResourcePO.getClientType() != 1) { // 客户类型为食堂
								clientResourceParam.setBusinessIdS(businessIds);
								clientResourceParam.setClientTypeS(clientTypes);
								BigDecimal canteenNum = iCrmClientResourceService.countCanteen(clientResourceParam);
								crmTeamVO.setCanteenNum(canteenNum);
							}
						}
					}
				} catch (Exception e) {
					logger.error("##crmTeamController:", e);
				}

			}
			listNew.add(crmTeamVO);
		}
		if (listVos != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("name", "团队名称");
			map.put("createrTime", "成立时间");
			map.put("areaName", "负责区域");
			map.put("businessName", "业务经理");
			map.put("teamNumber", "团队人数");
			map.put("agentNum", "发展代理商");
			map.put("canteenNum", "发展食堂");
			map.put("completeOrderMoney", "订单数量");
			map.put("realMoney", "订单金额");
			try {
				ExcelUtil.writeXls(response, "业务团队列表", map, listVos, CrmTeamVO.class);
			} catch (Exception e) {
				logger.error("CrmTeamController:excelOut", e);
			}
		}
	}

	/**
	 * 当月工作目标
	 */
	@RequestMapping(value = "/workTarget/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView workTarget(@PathVariable Long id) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/team_management/teamWork_targe");
		try {
			CrmWorkTargetPO crmWorkTargetPO = iCrmWorkTargetService.getByTeamId(id); // 根据团队id
																						// 获取该团队所有工作目标信息
			// 格式化工作月到月
			Date Month = crmWorkTargetPO.getApplyMonth();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String applyMonth = sdf.format(Month);

			// 格式化申报时间
			Date creatTime = crmWorkTargetPO.getCreatTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String applyTime = format.format(creatTime);

			modelAndView.addObject("crmWorkTargetPO", crmWorkTargetPO);
			modelAndView.addObject("applyMonth", applyMonth);
			modelAndView.addObject("applyTime", applyTime);
		} catch (Exception e) {
			logHelper.getBuilder().error(e.getMessage());
			logger.error(e.getMessage());
		}
		return modelAndView;
	}

	/**
	 * 客户列表
	 */
	@RequestMapping(value = "/customer/{id}")
	@ResponseBody
	public ModelAndView customer(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("/team_management/customer_list");
		List<CrmBusinessPO> lbp = iCrmBusinessService.findByTeamId(id);
		if (lbp.size() > 0 && lbp != null) {
			String businessIds = "";
			for (CrmBusinessPO crmBusinessPO : lbp) {
				businessIds += crmBusinessPO.getId() + ",";
			}
			businessIds = businessIds.substring(0, businessIds.length() - 1);
			modelAndView.addObject("businessIds", businessIds);
		}
		return modelAndView;
	}

	/**
	 * 客户列表查询
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/cusQueryPage")
	@ResponseBody
	public PageRespModel cusQueryPage(CrmClientResourceParam param) throws Exception {
		logger.info("#####CrmTeamController###Param:" + param.toString());
		PageRespModel model = new PageRespModel();
		if (BeanUtils.isNotEmpty(param.getBusinessIds())) {
			Page<CrmClientResourceVO> pages = iCrmClientResourceService.queryPage(param);
			List<CrmClientResourceVO> list = pages.result;
			List<CrmClientResourceVO> listNew = new ArrayList<CrmClientResourceVO>();
			for (CrmClientResourceVO crmClientResourceVO : list) {
				if (crmClientResourceVO.getClientType() == 1) { // '客户类型 1.代理商'

					Long agentId = crmClientResourceVO.getAgentCanteenId();
					CrmAgentPO crmAgentPO = iCrmAgentService.findByAgentId(agentId);
					if (crmAgentPO != null) { // 判断代理商信息是否为空
						// crmClientResourceVO.setName(crmAgentPO.getName()); //
						// 代理商名称
						crmClientResourceVO.setAreaCode(crmAgentPO.getAreaCode());
						Long areaCode = crmAgentPO.getAreaCode();
						if (areaCode != null) { // 区域名称
							BeanUtils.databaseInjectField(crmClientResourceVO, "areaName",
									new String[] { "area_code=" + areaCode }, new String[] { "area_name areaName" });
						}
					}

				} else { // 2.厂内食堂，3.校内食堂，4.独立食堂
					Long canteenId = crmClientResourceVO.getAgentCanteenId();
					CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = iCrmCanteenBaseInfoService.findByCanteenId(canteenId);
					if (crmCanteenBaseInfoPO != null) { // 判断食堂信息是否为空
						// crmClientResourceVO.setName(crmCanteenBaseInfoPO.getName());
						// // 食堂名称
						crmClientResourceVO.setAreaCode(crmCanteenBaseInfoPO.getAreaCode());
						Long areaCode = crmCanteenBaseInfoPO.getAreaCode();
						if (areaCode != null) { // 区域名称
							BeanUtils.databaseInjectField(crmClientResourceVO, "areaName",
									new String[] { "area_code=" + areaCode }, new String[] { "area_name areaName" });
						}
					}

				}
				listNew.add(crmClientResourceVO);
			}
			pages.result = list;
			model.setTotal(pages.getTotalResult());
			model.setRows(pages.result);
		}
		return model;
	}

	/**
	 * 客户列表查看
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}")
	@ResponseBody
	public ModelAndView view(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("/team_management/customer_resource_look");
		// 查询客户资源及食堂或代理商信息
		Boolean flag = iCrmClientResourceService.customerResourceLook(id, modelAndView);
		if (flag)
			return modelAndView;

		return modelAndView;
	}

	/**
	 * 学校、工厂信息
	 * 
	 * @param param
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/SchoolOrFactory/{id}")
	@ResponseBody
	public ModelAndView SchoolOrFactory(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("/team_management/schoolOrFactory_list");
		CrmEnterprisePO epo = iCrmEnterpriseService.findById(id);
		CrmClientResourcePO crmClientResourcePO = iCrmClientResourceService.getById(id);
		if (BeanUtils.isNotEmpty(epo)) {
			// 获取详细地址
			String area = areaService.getAreaName(CommUtil.parselong(epo.getAreaCode()));
			epo.setAddress(area + "/" + epo.getAddress());

			if (epo != null && epo.getAreaCode() != null) {
				// 获取到区
				BaseAreaPO cityQ = this.areaService.getCityById((long) epo.getAreaCode());
				// 获取到市
				BaseAreaPO cityS = this.areaService.getCityById(cityQ.getParentCode());

				modelAndView.addObject("prov", cityS.getParentCode());
				modelAndView.addObject("city", cityS.getAreaCode());
				modelAndView.addObject("dist", cityQ.getAreaCode());
			}
		}
		modelAndView.addObject("epo", epo);
		modelAndView.addObject("crmClientResourcePO", crmClientResourcePO);
		modelAndView.addObject("enterpriseStyleEnum", EnterpriseStyleEnum.getEnumList());
		return modelAndView;
	}

	/**
	 * 客户列表数据导出
	 * 
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/cusExcelOut")
	@ResponseBody
	public void cusExcelOut(HttpServletResponse response, CrmClientResourceParam param) {
		Page<CrmClientResourceVO> pages = iCrmClientResourceService.findPageSelect(param);
		List<CrmClientResourceVO> crmClientResourceVOs = pages.result;
		Iterator<CrmClientResourceVO> oiIterator = crmClientResourceVOs.iterator();
		while (oiIterator.hasNext()) {
			CrmClientResourceVO crmClientResourceVO = oiIterator.next();
			if (BeanUtils.isNotEmpty(crmClientResourceVO.getClientType())) {
				if (crmClientResourceVO.getClientType().equals(1)) {
					crmClientResourceVO.setClientTypeName("代理商");
				} else if (crmClientResourceVO.getClientType().equals(2)) {
					crmClientResourceVO.setClientTypeName("厂内食堂");
				} else if (crmClientResourceVO.getClientType().equals(3)) {
					crmClientResourceVO.setClientTypeName("校内食堂");
				} else {
					crmClientResourceVO.setClientTypeName("独立食堂");
				}
			}
		}
		if (crmClientResourceVOs != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("id", "ID");
			map.put("name", "名称");
			map.put("clientTypeName", "客户类型");
			map.put("address", "地理信息");
			map.put("createTime", "时间");
			map.put("contact", "联系人");
			map.put("businessName", "业务员");
			map.put("mobile", "联系电话");
			try {
				ExcelUtil.writeXls(response, "客户列表", map, crmClientResourceVOs, CrmClientResourceVO.class);
			} catch (Exception e) {
				logger.error("CrmTeamController:cusExcelOut", e);
			}
		}
	}

	/**
	 * 团队成员
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/teamMember/{id}")
	@ResponseBody
	public ModelAndView teamMember(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("/team_management/team_member");
		// 获取岗位信息
		List<CrmPositionPO> allPosition = iCrmPositionService.getPositionByTeamId(id);
		modelAndView.addObject("allPosition", allPosition);
		modelAndView.addObject("teamId", id);
		return modelAndView;
	}

	/**
	 * 团队成员进客户列表
	 */
	@RequestMapping(value = "/viewCustomer/{id}")
	@ResponseBody
	public ModelAndView viewCustomer(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("/team_management/customer_list");
		modelAndView.addObject("businessIds", id);
		return modelAndView;
	}

	/**
	 * 团队成员查询
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teamQueryPage")
	@ResponseBody
	public PageRespModel teamQueryPage(CrmBusinessParam param, HttpServletRequest request) throws Exception {
		PageRespModel model = new PageRespModel();
		// Page<CrmBusinessVO> pages = iCrmBusinessService.queryPage(param);
		CrmAccountPO crmAccountPO = CrmControllerHelper.getSessionUser();
		// 用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer type = crmAccountPO.getType();
		param.setType(type);
		param.setJobStatus(1);
		Page<CrmBusinessVO> pages = iCrmTeamService.queryBusiness(param);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
	}

	/**
	 * 团队成员导出
	 * 
	 * @param response
	 * @param param
	 */
	@RequestMapping(value = "/teamExcelOut")
	public void teamExcelOut(HttpServletResponse response, CrmBusinessParam param) {
		Page<CrmBusinessVO> pages = iCrmTeamService.queryBusiness(param);
		List<CrmBusinessVO> businessVOs = pages.result;
		if (businessVOs != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("id", "ID");
			map.put("name", "姓名");
			map.put("workTime", "入职时间");
			map.put("positionName", "岗位职务");
			map.put("mobilephone", "联系电话");
			map.put("weixin", "微信号码");
			try {
				ExcelUtil.writeXls(response, "业务员列表", map, businessVOs, CrmBusinessVO.class);
			} catch (Exception e) {
				logger.error("#crmteam:#teamExcelOul:团队成员导出", e);
			}
		}
	}

	/**
	 * 团队成员查询
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teamQueryPageById")
	@ResponseBody
	public PageRespModel teamQueryPageById(CrmBusinessParam param, HttpServletRequest request) throws Exception {
		PageRespModel model = new PageRespModel();
		// Page<CrmBusinessVO> pages = iCrmBusinessService.queryPage(param);
		Page<CrmBusinessVO> pages = iCrmTeamService.queryBusinessById(param);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
	}

	/**
	 * 新增团队
	 * 
	 * @param 传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 下午10:55:55
	 */
	@RequestMapping(value="/addTeam",method=RequestMethod.POST)
	@ResponseBody
	public RespModel addTeam(@RequestBody CrmTeamParam param){
		Integer sessionType = CrmControllerHelper.getSessionType();
		param.setType(sessionType);
		if(sessionType==CrmAccountUserType.AGENT.getCode()){
			param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
		}
		if(BeanUtils.isNotEmpty(param.getId())){
			return iCrmTeamService.updateTeam(param);
		}else{
			param.setCreateUid(CrmControllerHelper.getSessionUserId());
			return iCrmTeamService.addTeam(param);
		}

	}

	/**
	 * 筛选省份
	 * 
	 * @param 传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 下午1:10:48
	 */
	@RequestMapping("/provinceSee")
	@ResponseBody
	public RespModel provinceSee(CrmTeamParam param) {
		String areaCode = "";
		List<Map<String, Object>> areaList = new ArrayList<Map<String, Object>>();
		if (BeanUtils.isNotEmpty(param.getRegion())) {
			List<CrmTeamPO> teamList = iCrmTeamService.selectByAreaCodeLike(String.valueOf(param.getRegion()));
			if (CollectionUtils.isNotEmpty(teamList)) {
				Iterator<CrmTeamPO> teamIter = teamList.iterator();
				while (teamIter.hasNext()) {
					CrmTeamPO crmTeamPO = teamIter.next();
					crmTeamPO.setAreaCode(Long.parseLong(String.valueOf(crmTeamPO.getAreaCode()).substring(0, 5)));
					;
					areaCode = areaCode + crmTeamPO.getAreaCode() + ",";
				}
			}
			if (BeanUtils.isNotEmpty(areaCode)) {
				areaCode = areaCode.substring(0, areaCode.length() - 1);
				List<BaseAreaPO> baseAreaList = areaService.findByAreaCodes(areaCode);
				if (CollectionUtils.isNotEmpty(baseAreaList)) {
					Iterator<BaseAreaPO> baseAreaIter = baseAreaList.iterator();
					while (baseAreaIter.hasNext()) {
						Map<String, Object> map = new HashMap<String, Object>();
						BaseAreaPO bPo = baseAreaIter.next();
						map.put("areaName", bPo.getAreaName());
						map.put("areaCode", bPo.getAreaCode());
						areaList.add(map);
					}
				}
			}
		}
		RespModel respModel = new RespModel();
		respModel.setRows(areaList);
		return respModel;
	}

	/**
	 * 筛选团队
	 * 
	 * @param 传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 下午2:52:20
	 */
	@RequestMapping("/teamSee")
	@ResponseBody
	public RespModel teamSee(CrmTeamParam param) {
		List<Map<String, Object>> teamList = new ArrayList<Map<String, Object>>();
		if (BeanUtils.isNotEmpty(param.getAreaCode())) {
			List<CrmTeamPO> crmTreamList = iCrmTeamService.selectByAreaCodeLike(String.valueOf(param.getAreaCode()));
			if (CollectionUtils.isNotEmpty(crmTreamList)) {
				Iterator<CrmTeamPO> teamIter = crmTreamList.iterator();
				while (teamIter.hasNext()) {
					Map<String, Object> map = new HashMap<String, Object>();
					CrmTeamPO cPo = teamIter.next();
					map.put("id", cPo.getId());
					map.put("name", cPo.getName());
					teamList.add(map);
				}
			}
		}
		RespModel respModel = new RespModel();
		respModel.setRows(teamList);
		return respModel;
	}

	/**
	 * 团队地理位置
	 * 
	 * @param 传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月27日 上午9:56:05
	 */
	@RequestMapping("/teamPeople")
	@ResponseBody
	public RespModel teamPeople(CrmTeamParam param) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (BeanUtils.isNotEmpty(param.getParentId())) {
			CrmTeamPO cTp = iCrmTeamService.get(CrmTeamPO.class, param.getParentId());
			if (BeanUtils.isNotEmpty(cTp) && BeanUtils.isNotEmpty(cTp.getAreaCode())) {
				if (String.valueOf(cTp.getAreaCode()).length() >= 5) {
					// 省
					map.put("prov", String.valueOf(cTp.getAreaCode()).substring(0, 5));
				}
				if (String.valueOf(cTp.getAreaCode()).length() >= 7) {
					// 省
					map.put("prov", String.valueOf(cTp.getAreaCode()).substring(0, 5));
					// 市
					map.put("city", String.valueOf(cTp.getAreaCode()).substring(0, 7));
				}
				if (String.valueOf(cTp.getAreaCode()).length() > 7) {
					// 省
					map.put("prov", String.valueOf(cTp.getAreaCode()).substring(0, 5));
					// 市
					map.put("city", String.valueOf(cTp.getAreaCode()).substring(0, 7));
					// 区
					map.put("dist", cTp.getAreaCode());
				}
			}
		}
		RespModel rSp = new RespModel();
		rSp.setRows(map);
		return rSp;
	}
}