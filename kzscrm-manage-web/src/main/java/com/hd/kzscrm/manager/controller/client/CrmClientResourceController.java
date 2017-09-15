package com.hd.kzscrm.manager.controller.client;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.commons.collections.map.HashedMap;
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

import scala.annotation.meta.param;

import com.hd.kzscrm.common.enums.EnterpriseStyleEnum;
import com.hd.kzscrm.common.enums.client.CrmClientResourceEnum.CrmClientResourceClientType;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.AreaCodeUtils;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.common.util.ListUtils;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentAreaPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenExtInfoPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmBusinessTailRecordParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenExtInfoParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.param.user.CrmAccountParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentAreaService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenExtInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.vo.agent.CrmAgentVO;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;


/**
 * crmClientResource CRMCLIENTRESOURCE
 * @author system code gen
 * 客户资源列表
 */
@Controller
@RequestMapping("/crmclientresource")
public class CrmClientResourceController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmClientResourceController.class);
    @Autowired
    ICrmClientResourceService icrmClientResourceService;
    @Autowired
    ICrmCanteenBaseInfoService iCrmCanteenBaseInfoService;
    //用户表
	 @Autowired
	ICrmUserService crmUserService;
	//业务员表
	@Autowired 
    ICrmBusinessService businessService;
	//代理商
	@Autowired
	ICrmAgentService iCrmAgentService;
	//抽成设置表
	@Autowired
	ICrmSplitAssignSetService assignSetService;
	@Resource
    ICrmPermUserRoleService iCrmPermUserRoleService;
	@Autowired
	ICrmPermRoleSourcesService roleSourcesService;
	@Resource
	private ICrmAgentAreaService iCrmAgentAreaService;
	/**
	 * 企业(学校)信息
	 */
	@Autowired
	ICrmEnterpriseService iCrmEnterpriseService;
	@Resource
	private IBaseAreaService iBaseAreaService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
    ICrmPermSourcesService  pSourcesService;
	/**
	 * 团队表
	 */
	@Autowired
	ICrmTeamService iCrmTeamService;
	
	@Resource
	ICrmCanteenExtInfoService iCrmCanteenExtInfoService;
     
    /**
     ** 预留实现
     */
    public void initControler(HttpServletRequest request, Map<String, Object> map) {
     
    }
    /**
    *
    * 初始化
    * @param param
    * @return
    */
   @RequestMapping("/initByUrl")
   @ResponseBody
   public ModelAndView initByUrl(String url) {
	   return new ModelAndView(url);
   }
   
    /**
     *
     * 初始化
     * @param param
     * @return
     */
    @RequestMapping("/init")
    @ResponseBody
    public ModelAndView init() {
     	
    	ModelAndView view = new ModelAndView("/index/customer_resource");
    	
    	Integer sessionUserType = CrmControllerHelper.getSessionUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		view.addObject("userType", sessionUserType);
		return view;
    }
    
    /**
     * 新增食堂页面
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmClientResourceByCanteenInit", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmClientResourceByCanteenInit() {
    	//当前登录的用户
    	CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
    	Integer type = sessionUser.getType();//1.平台，2.代理商
    	//当前登录的代理商
    	CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
    	Long userId = sessionUser.getId();
    	Integer userType = sessionUser.getUserType();
    	ModelAndView model = new  ModelAndView("/index/add_client_resource_canteen");
    	//如果不是管理员或者代理商，就直接返回
    	/*if(!(userType==CrmAccountUserType.ADMIN.getCode()||userType==CrmAccountUserType.AGENT.getCode())){
    		return model; 
    	}*/
    	//设置查询参数
    	CrmCanteenBaseInfoParam param=new CrmCanteenBaseInfoParam();
    	if(userType==CrmAccountUserType.BUSINESS.getCode()||userType==CrmAccountUserType.BUSINESS_MANAGER.getCode()){
    		//当前登录的业务员
    		CrmBusinessPO crmBusinessPO = CrmControllerHelper.getSessionBusinessUser();
    		Long teamId = crmBusinessPO.getTeamId();
    		Long businessId = crmBusinessPO.getId();
    		model.addObject("teamId", teamId);
    		model.addObject("businessId", businessId);
    	}
    	
    	param.setType(type);
    	param.setCrmAccountParam(BeanConvertor.copy(sessionUser, CrmAccountParam.class));
    	if(BeanUtils.isNotEmpty(sessionAgentUser)){
    		param.setCrmAgentParam(BeanConvertor.copy(sessionAgentUser, CrmAgentParam.class));
    	}
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		model.addObject("cSourcesVOs", cSourcesVOs);
		param.setUserId(userId);
		Map<String,Object> map = iCrmCanteenBaseInfoService.getAgentAndTeam(param);
		if(BeanUtils.isNotEmpty(map) && BeanUtils.isNotEmpty(map.get("isRemove")) && map.get("isRemove").equals("true")){
			model.addObject("teamId", null);
    		model.addObject("businessId", null);
		}
    	model.addAllObjects(map);
    	return model;
    }
    /**
     * 新增编辑食堂的信息（企业，食堂扩展，客户资源）
     * @author 黄霄仪
     * @date 2017年6月22日 上午9:26:26
     */
    @RequestMapping(value = "addCrmClientResourceByCanteen", method = RequestMethod.POST)
    @ResponseBody
	public RespModel addCrmClientResourceByCanteen(
			@RequestBody CrmCanteenBaseInfoParam crmCanteenBaseInfoparam) {
		crmCanteenBaseInfoparam.setCreaterUid(CrmControllerHelper.getSessionUserId());
//    	crmCanteenBaseInfoparam.setUpdaterUid(CrmControllerHelper.getSessionUserId());
    	return icrmClientResourceService.addCrmClientResourceByCanteen(crmCanteenBaseInfoparam);
	}
    
    
    @RequestMapping(value = "addCrmClientResourceByCanteen1", method = RequestMethod.POST)
    @ResponseBody
    public RespModel addCrmClientResourceByCanteen1(
    		@RequestBody CrmCanteenBaseInfoParam param) {
    	RespModel respModel = new RespModel();
    	// 食堂扩展表0
		CrmCanteenExtInfoParam crmCanteenExtInfoParam = param.getCrmCanteenExtInfoParam();
		//判断负责人手机号是否重复
		CrmCanteenExtInfoParam crmCEIParam=new CrmCanteenExtInfoParam();
		if(BeanUtils.isNotEmpty(crmCanteenExtInfoParam.getHeadPhone())){
			crmCEIParam.setHeadPhone(crmCanteenExtInfoParam.getHeadPhone());
			List<CrmCanteenExtInfoPO> listByParam = iCrmCanteenExtInfoService.listByParam(crmCEIParam);
			if(BeanUtils.isNotEmpty(listByParam)){
				respModel.setCode(-4);
				respModel.setDesc("商家负责人手机号码已使用！");
			}
		}else{
			respModel.setCode(-4);
			respModel.setDesc("商家负责人手机号码不能为空！");
		}
		return respModel;
    }
    
    /**
     * 获取编辑食堂的信息（企业，食堂扩展，客户资源）
     * @author 黄霄仪
     * @date 2017年6月22日 上午9:26:26
     */
    @RequestMapping("/editCrmClientResourceInit")
    @ResponseBody
    public ModelAndView editCrmClientResourceByCanteenInit(CrmCanteenBaseInfoParam crmCanteenBaseInfoparam){
    	//当前登录的用户
    	CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
    	//当前登录的代理商
    	CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
    	Integer userType = sessionUser.getUserType();
    	ModelAndView model = new  ModelAndView("/index/edit_client_resource_canteen");
    	/*if(!(userType==CrmAccountUserType.ADMIN.getCode()||userType==CrmAccountUserType.AGENT.getCode())){
    		return model; 
    	}*/
    	crmCanteenBaseInfoparam.setCrmAccountParam(BeanConvertor.copy(sessionUser, CrmAccountParam.class));
    	if(BeanUtils.isNotEmpty(sessionAgentUser)){
    		crmCanteenBaseInfoparam.setCrmAgentParam(BeanConvertor.copy(sessionAgentUser, CrmAgentParam.class));
    	}
    	crmCanteenBaseInfoparam.setType(sessionUser.getType());
    	model.addAllObjects(icrmClientResourceService.getCrmCanteenBaseInfo(crmCanteenBaseInfoparam));
		return model;
    }
    /**
     * 编辑食堂(企业，食堂扩展，客户资)
     * @author 黄霄仪
     * @date 2017年6月27日 上午10:51:48
     */
    @RequestMapping(value="/editCrmClientResourceByCanteen",method=RequestMethod.POST)
    @ResponseBody
    public RespModel editCrmClientResourceByCanteen(@RequestBody CrmCanteenBaseInfoParam crmCanteenBaseInfoparam){
    	crmCanteenBaseInfoparam.setUpdaterUid(CrmControllerHelper.getSessionUserId());
		return icrmClientResourceService.editCrmClientResourceByCanteen(crmCanteenBaseInfoparam);
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmClientResource/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmClientResource(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmclientresource/mainAddOrEditCrmClientResource");
         CrmClientResourceParam CrmClientResourceparam = new CrmClientResourceParam();
         CrmClientResourcePO po = icrmClientResourceService.get(CrmClientResourcePO.class,id );
         modelAndView.addObject("po",po);
         modelAndView.addObject("type","update");
         return modelAndView;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping(value="/queryPage",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel queryPage(@RequestBody CrmClientResourceParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
    	Page<CrmClientResourceVO> pages = icrmClientResourceService.queryPage(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }

    
    /**
     * 查询当前登录用户的客户列表
     * @param param
     * @return
     */
    @RequestMapping("/queryPageByUserId/{clientNature}")
    @ResponseBody
    public PageRespModel queryPageByUserId(CrmClientResourceParam param,@PathVariable Integer clientNature) throws Exception {
     	PageRespModel model = new PageRespModel();
     	CrmBusinessPO crmBusinessPO = CrmControllerHelper.getSessionBusinessUser();
     	if(BeanUtils.isNotEmpty(crmBusinessPO)){
     		param.setBusinessId(crmBusinessPO.getId());
     		param.setClientNature(clientNature);
     		Page<CrmClientResourceVO> pages = icrmClientResourceService.queryPageBasic(param);
     		model.setTotal(pages.getTotalResult());
     		model.setRows(pages.result);
     		
     	}else{
     		return null;
     	}
    	
    	return model;
    }
		
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmClientResourceParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("customer_management/customer_resource_look");
		//查询客户资源及食堂或代理商信息
		Boolean flag = icrmClientResourceService.customerResourceLook(id,view);
		if(flag) return view;
		return view;
	} 
	
	/**
	 * 跟踪记录
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/traceRecord")
	@ResponseBody
	public ModelAndView traceRecord(CrmBusinessTailRecordParam param) {
		ModelAndView modelAndView = new ModelAndView("system_operate/track_record");
		modelAndView.addObject("customerId", param.getCustomerId());
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: protectCustomerInit 
	 * @Description: 保护客户页面初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午7:42:03
	 */
	@RequestMapping(value="/protectCustomerInit")
	public ModelAndView protectCustomerInit(){
		ModelAndView modelAndView = new ModelAndView("my_customer/protect_customer");
		Integer userType = CrmControllerHelper.getSessionUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		if(4 == userType){
			CrmBusinessPO crmBusinessPO = CrmControllerHelper.getSessionBusinessUser();
			Long teamId = crmBusinessPO.getTeamId();
			CrmTeamParam crmTeamParam = new CrmTeamParam();
			crmTeamParam.setParentId(teamId);
			crmTeamParam.setDelFlag(1);
			List<CrmTeamPO> childTeam = iCrmTeamService.getChildTeam(crmTeamParam);
			modelAndView.addObject("teamPOs", childTeam);
			if(CollectionUtils.isNotEmpty(childTeam)){
				List<Long> teamIds = new LinkedList<Long>();
				for (CrmTeamPO crmTeamPO : childTeam) {
					teamIds.add(crmTeamPO.getId());
				}
				
				CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
				crmBusinessParam.setTeamIds(teamIds);
				crmBusinessParam.setDelFlag(1);
				List<CrmBusinessPO> CrmBusinessPOs = businessService.commonQuery(crmBusinessParam);
				modelAndView.addObject("businessPOs", CrmBusinessPOs);
			}
		}
		
		modelAndView.addObject("userType", userType);
		return modelAndView;
	}
	 
	/***
	 * 
	 * @Title: getProtectCustomerDetails 
	 * @Description: 获取保护客户信息 
	 * @param @param clientResourceParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午7:42:46
	 */
	@RequestMapping(value="/getProtectCustomerDetails")
	@ResponseBody
	public PageRespModel getProtectCustomerDetails(CrmClientResourceParam clientResourceParam){
		logger.info("#####CrmClientResourceController###getProtectCustomerDetails##clientResourceParam:"+clientResourceParam.toString());
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//登录UserId
		clientResourceParam.setUserId(sessionUserId);
		
		PageRespModel pageRespModel = new PageRespModel();
		Boolean flag = icrmClientResourceService.getProtectCustomerDetails(clientResourceParam,pageRespModel);//获取保护客户信息
		if(flag) return pageRespModel;
		
		return pageRespModel;
	}
	/**
	 * 
	 * @Title: formalCustomerInit 
	 * @Description: 正式客户页面初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午7:42:28
	 */
	@RequestMapping(value="/formalCustomerInit")
	public ModelAndView formalCustomerInit(){
		ModelAndView modelAndView = new ModelAndView("my_customer/formal_customer");
		Integer userType = CrmControllerHelper.getSessionUserType();//用户类型
		if(4 == userType){
			CrmBusinessPO crmBusinessPO = CrmControllerHelper.getSessionBusinessUser();
			Long teamId = crmBusinessPO.getTeamId();
			CrmTeamParam crmTeamParam = new CrmTeamParam();
			crmTeamParam.setParentId(teamId);
			crmTeamParam.setDelFlag(1);
			List<CrmTeamPO> childTeam = iCrmTeamService.getChildTeam(crmTeamParam);
			modelAndView.addObject("teamPOs", childTeam);
			if(CollectionUtils.isNotEmpty(childTeam)){
				List<Long> teamIds = new LinkedList<Long>();
				for (CrmTeamPO crmTeamPO : childTeam) {
					teamIds.add(crmTeamPO.getId());
				}
				
				CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
				crmBusinessParam.setTeamIds(teamIds);
				crmBusinessParam.setDelFlag(1);
				List<CrmBusinessPO> CrmBusinessPOs = businessService.commonQuery(crmBusinessParam);
				modelAndView.addObject("businessPOs", CrmBusinessPOs);
			}
		}
		modelAndView.addObject("userType", userType);
		
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: getFormalCustomerDetails 
	 * @Description: 获取正式客户信息  
	 * @param @param clientResourceParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午7:43:14
	 */
	@RequestMapping(value="/getFormalCustomerDetails")
	@ResponseBody
	public PageRespModel getFormalCustomerDetails(CrmClientResourceParam clientResourceParam){
		logger.info("#####CrmClientResourceController###getFormalCustomerDetails##clientResourceParam:"+clientResourceParam.toString());
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//登录UserId
		clientResourceParam.setUserId(sessionUserId);
		
		PageRespModel pageRespModel = new PageRespModel();
		Boolean flag = icrmClientResourceService.getFormalCustomerDetails(clientResourceParam,pageRespModel);//获取正式客户信息
		if(flag) return pageRespModel;
		
		return pageRespModel;
	}
	/**
     * 新客户审核
     * @param param
     * @return
     */
    @RequestMapping(value="/queryPageBasic",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel queryPageBasic(@RequestBody CrmClientResourceParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
    	Page<CrmClientResourceVO> pages = icrmClientResourceService.queryPageBasic(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/clientNature/{clientNature}")
	@ResponseBody
	public ModelAndView jumpProtectOrNormalView(CrmClientResourceParam param, @PathVariable Integer clientNature) {
		ModelAndView view = null;
		if(clientNature==2){//保护客户
			view = new ModelAndView("/my_customer/protect_customer");
		}else if(clientNature==3){//正式客户
			view = new ModelAndView("/my_customer/formal_customer");
		}else{//意外情况 一般不可能存在
			view = new ModelAndView("/my_customer/protect_customer");
		}
		return view;
	} 
	
	/**
	 * 
	 * @Title: clientListInit 
	 * @Description: 客户列表初始化 
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月7日 下午5:09:36
	 */
	@RequestMapping(value="/clientListInit")
	public ModelAndView clientListInit(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###clientListInit##crmClientResourceParam:"+crmClientResourceParam.toString());
		ModelAndView modelAndView = new ModelAndView("system_operate/customer_list");
		
		//是否需要统计客户类型
		try {
			
			/*Map<String ,Object> map = new HashedMap();
			map.put("all", 0);
			map.put("protect", 0);
			map.put("formal", 0);
			
			List<CrmClientResourcePO> clientResourcePOs = icrmClientResourceService.listByParam(crmClientResourceParam);
			map.put("all", clientResourcePOs.size());
			crmClientResourceParam.setClientNature(2);//保护客户
			List<CrmClientResourcePO> clientResourcePOs1 = icrmClientResourceService.listByParam(crmClientResourceParam);
			map.put("protect", clientResourcePOs1.size());
			crmClientResourceParam.setClientNature(3);//正式客户
			List<CrmClientResourcePO> clientResourcePOs2 = icrmClientResourceService.listByParam(crmClientResourceParam);
			map.put("formal", clientResourcePOs2.size());
			if(CollectionUtils.isNotEmpty(clientResourcePOs)){
				map.put("all", clientResourcePOs.size());
			}
			if(CollectionUtils.isNotEmpty(clientResourcePOs1)){
				map.put("protect", clientResourcePOs1.size());
			}
			if(CollectionUtils.isNotEmpty(clientResourcePOs2)){
				map.put("formal", clientResourcePOs2.size());
			}
			
			modelAndView.addObject("map", map);*/
			icrmClientResourceService.clientListInit(crmClientResourceParam,modelAndView);//客户列表初始化
		} catch (ParseException e) {
			logger.error("CrmClientResourceController:clientListInit:"+e.getMessage());
			e.printStackTrace();
		}
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value="/businessListClientListInit")
	public ModelAndView businessListClientListInit(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###clientListInit##crmClientResourceParam:"+crmClientResourceParam.toString());
		ModelAndView modelAndView = new ModelAndView("salesman_operate/customer_list");
		//是否需要统计客户类型
		try {
			
			Map<String ,Object> map = new HashedMap();
			map.put("all", 0);
			map.put("protect", 0);
			map.put("formal", 0);
			
			List<CrmClientResourcePO> clientResourcePOs = icrmClientResourceService.listByParam(crmClientResourceParam);
			map.put("all", clientResourcePOs.size());
			crmClientResourceParam.setClientNature(2);//保护客户
			List<CrmClientResourcePO> clientResourcePOs1 = icrmClientResourceService.listByParam(crmClientResourceParam);
			map.put("protect", clientResourcePOs1.size());
			crmClientResourceParam.setClientNature(3);//正式客户
			List<CrmClientResourcePO> clientResourcePOs2 = icrmClientResourceService.listByParam(crmClientResourceParam);
			map.put("formal", clientResourcePOs2.size());
			if(CollectionUtils.isNotEmpty(clientResourcePOs)){
				map.put("all", clientResourcePOs.size());
			}
			if(CollectionUtils.isNotEmpty(clientResourcePOs1)){
				map.put("protect", clientResourcePOs1.size());
			}
			if(CollectionUtils.isNotEmpty(clientResourcePOs2)){
				map.put("formal", clientResourcePOs2.size());
			}
			
			modelAndView.addObject("map", map);
			icrmClientResourceService.clientListInit(crmClientResourceParam,modelAndView);//客户列表初始化
		} catch (ParseException e) {
			logger.error("CrmClientResourceController:clientListInit:"+e.getMessage());
			e.printStackTrace();
		}
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: getClientList 
	 * @Description: 获取客户列表信息 
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月7日 下午5:13:32
	 */
	@RequestMapping(value="/getClientList")
	@ResponseBody
	public PageRespModel getClientList(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###getClientList##crmClientResourceParam:"+crmClientResourceParam.toString());
		PageRespModel pageRespModel = new PageRespModel();
		
		//获取客户列表信息
		Boolean flag = icrmClientResourceService.getClientList(crmClientResourceParam,pageRespModel);
		if(flag)return pageRespModel;
		
		return pageRespModel;
	}
	@RequestMapping(value="/byBusinessGetClientList")
	@ResponseBody
	public PageRespModel byBusinessGetClientList(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###getClientList##crmClientResourceParam:"+crmClientResourceParam.toString());
		PageRespModel pageRespModel = new PageRespModel();
		
		//获取客户列表信息
		Boolean flag = icrmClientResourceService.byBusinessGetClientList(crmClientResourceParam,pageRespModel);
		if(flag)return pageRespModel;
		
		return pageRespModel;
	}
	
	/**
	 * 
	 * @Title: agentCustomerInit 
	 * @Description: 代理商客户页面初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月9日 下午4:04:58
	 */
	@RequestMapping(value="/agentCustomerInit")
	public ModelAndView agentCustomerInit(){
		Integer sessionUserType = CrmControllerHelper.getSessionUserType();
		ModelAndView modelAndView = new ModelAndView("my_customer/agent_customer");
		if(sessionUserType==CrmAccountUserType.AGENT.getCode()){
			CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
			Long id = sessionAgentUser.getId();
			CrmAgentAreaPO crmAgentAreaPO = iCrmAgentAreaService.findByAgentId(id);
			modelAndView.addObject("areaCodes",ListUtils.substringList(AreaCodeUtils.getAllOfAreaCode(crmAgentAreaPO.getAreaCode()),2, null));
		}
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
		
	}
	/**
	 * 客户列表 代理商客户
	* @Title: agentCustomer 
	* @author : lcl
	* @time : 2017年6月20日 下午5:25:18
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping(value="/agentCustomer")
	public ModelAndView agentCustomer(){
		ModelAndView modelAndView = new ModelAndView("customer_management/agent_customer");
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
		
	}
	
	/**
	 * 
	 * @Title: agentCustomerDetails 
	 * @Description: 代理商客户列表详情 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月9日 下午4:07:18
	 */
	@RequestMapping(value="/agentCustomerDetails")
	@ResponseBody
	public PageRespModel agentCustomerDetails(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###agentCustomerDetails##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		PageRespModel pageRespModel = new PageRespModel();
		CrmAgentPO crmAgentPO = CrmControllerHelper.getSessionAgentUser();//查询登录代理商信息
		if(BeanUtils.isEmpty(crmAgentPO)){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return pageRespModel;
		}
		
		//代理商客户列表详情
		Boolean flag = icrmClientResourceService.agentCustomerDetails(crmClientResourceParam,pageRespModel,crmAgentPO);
		if(flag)return pageRespModel;
		
		return pageRespModel;
	}
	
	
	/**
	 * 
	 * @Title: salesmanCustomerInit 
	 * @Description: 业务员客户页面初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月9日 下午4:09:42
	 */
	@RequestMapping(value="/salesmanCustomerInit")
	public ModelAndView salesmanCustomerInit(){
		ModelAndView modelAndView = new ModelAndView("my_customer/salesman_customer");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: salesmanCustomerDetails 
	 * @Description: 业务员客户详情 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月9日 下午4:12:07
	 */
	@RequestMapping(value="/salesmanCustomerDetails")
	@ResponseBody
	public PageRespModel salesmanCustomerDetails(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###salesmanCustomerDetails##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		PageRespModel pageRespModel = new PageRespModel();
		CrmAgentPO crmAgentPO = CrmControllerHelper.getSessionAgentUser();//查询登录代理商信息
		if(BeanUtils.isEmpty(crmAgentPO)){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return pageRespModel;
		}
		
		//业务员客户详情
		Boolean flag = icrmClientResourceService.salesmanCustomerDetails(crmClientResourceParam,pageRespModel,crmAgentPO);
		if(flag)return pageRespModel;
		
		return pageRespModel;
	}
	/**
	 * 分页查询平台客户初始化 
	 * @author : lcl
	* @time : 2017年6月16日 下午4:36:00
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping(value="/platformCustomerInit")
	public ModelAndView platformCustomerInit(){
		ModelAndView model = new ModelAndView("customer_management/platform_customer");
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		model.addObject("cSourcesVOs", cSourcesVOs);
		return model;
	}
	
	
	/**
	 * 分页查询平台客户
	* @Title: platformCustomer 
	* @author : lcl
	* @time : 2017年6月20日 上午10:59:50
	* @return PageRespModel    返回类型 
	* @throws
	 */
	@RequestMapping(value="/platformCustomer")
	@ResponseBody
	public PageRespModel platformCustomer(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###salesmanCustomerDetails##crmClientResourceParam:"+crmClientResourceParam.toString());
		PageRespModel pageRespModel = new PageRespModel();
		//crmClientResourceParam.setCustomerAttribute(crmClientResourceParam.getCustomerAttribute());
		crmClientResourceParam.setClientNature(3);
		//平台客户不查询代理商
		Integer[] status = {2,3,4};//客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		crmClientResourceParam.setClientTypes(Arrays.asList(status));
		Page<CrmClientResourcePO> clientPage = icrmClientResourceService.findPageList(crmClientResourceParam);
		if(clientPage!=null){
			List<CrmClientResourcePO>  clientResourcePOs = clientPage.result;
			List<CrmClientResourceVO> clientResourceVOs = new  ArrayList<CrmClientResourceVO>();//返回到前端
			if(CollectionUtils.isNotEmpty(clientResourcePOs)){
				for(int i=0;i<clientResourcePOs.size();i++){
					CrmClientResourceVO cResourceVO = BeanConvertor.convertBean(clientResourcePOs.get(i), CrmClientResourceVO.class);
					if(BeanUtils.isNotEmpty(cResourceVO)){
						Long businessId = cResourceVO.getBusinessId();//根据业务员id查询对象
						CrmBusinessPO cBusinessPO = this.businessService.findByBusinessId(businessId);
						if(BeanUtils.isNotEmpty(cBusinessPO)){
							cResourceVO.setClientTypeName(cBusinessPO.getName());//业务员名字
						}
						Long agentCanteenId = cResourceVO.getAgentCanteenId();//抽成设置表的 canteen_id 客户资源id
						if(cResourceVO.getClientType()!=CrmClientResourceClientType.AGENT.getCode()){
							CrmSplitAssignSetPO cAssignSetPO = this.assignSetService.findByCanteenId(agentCanteenId);
							if(BeanUtils.isNotEmpty(cAssignSetPO)){
								cResourceVO.setCanteenSplitPercent(cAssignSetPO.getCanteenSplitPercent());//食堂的分账比例
							}
						}
						
						Long agentId = cResourceVO.getAgentId();//获取代理商id
						if(BeanUtils.isNotEmpty(agentId)){
							CrmAgentPO crmAgentPO = this.iCrmAgentService.findByAgentId(agentId);
							if(BeanUtils.isNotEmpty(crmAgentPO)){
								cResourceVO.setAgentName(crmAgentPO.getName());
							}
						}
					}
					
					if(BeanUtils.isNotEmpty(cResourceVO)){
						if(cResourceVO.getClientType().equals(1)){
							cResourceVO.setClientTypeStr("代理商");
						}else if(cResourceVO.getClientType().equals(2)){
							cResourceVO.setClientTypeStr("厂内食堂");
						}else if(cResourceVO.getClientType().equals(3)){
							cResourceVO.setClientTypeStr("校内食堂");
						}else if(cResourceVO.getClientType().equals(4)){
							cResourceVO.setClientTypeStr("独立食堂");
						}else {
							cResourceVO.setClientTypeStr("-");
						}
					}
					clientResourceVOs.add(i, cResourceVO);
				}
			}
			pageRespModel.setRows(clientResourceVOs);
			pageRespModel.setTotal(clientPage.getTotalResult());
		}
		return pageRespModel;
	}
	/**
	 * 
	 * @Title: clientListExcelOut 
	 * @Description: 客户列表详情导出 
	 * @param @param response
	 * @param @param crmClientResourceParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月19日 上午10:23:57
	 */
	@RequestMapping(value="/clientListExcelOut")
	public void clientListExcelOut(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###clientListExcelOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","编号");
		map.put("name","名称");
		map.put("clientTypeStr","客户分类");
		map.put("clientNatureStr","合作状态");
		map.put("address","地理信息");
		map.put("applyApproveTime","时间");
		map.put("canteenSplitPercentStr","分账比例（%）");
		map.put("contact","联系人");
		map.put("businessName","业务员");
		map.put("mobile","联系电话");
		
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.clientListExcelOut(crmClientResourceParam);//客户列表详情
		
		try {
			ExcelUtil.writeXls(response,"客户列表", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:clientListExcelOut:",e);
		}
		
	}
	/**
	 * 客户管理   代理商客户customerAttribute 为 1
	* @Title: excelOut 
	* @author : lcl
	* @time : 2017年6月21日 上午9:16:26
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="excelOut")
	public  void excelOut(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###clientListExcelOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","编号");
		map.put("name","名称");
		map.put("clientTypeStr","客户分类");
		map.put("clientNatureStr","合作状态");
		map.put("address","地理信息");
		map.put("applyApproveTime","时间");
		map.put("canteenSplitPercentStr","分账比例（%）");
		map.put("contact","联系人");
		map.put("businessName","业务员");
		map.put("mobile","联系电话");
		crmClientResourceParam.setCustomerAttribute(1);
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.clientListExcelOut(crmClientResourceParam);//客户列表详情
		
		try {
			ExcelUtil.writeXls(response,"平台客户", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:clientListExcelOut:",e);
		}
	}
	
	
	/**
	 * 客户管理  平台客户customerAttribute 为 2
	* @Title: excelOut1 
	* @author : lcl
	* @time : 2017年6月21日 上午11:31:16
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/excelOut1")
	public  void excelOut1(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###clientListExcelOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","编号");
		map.put("name","名称");
		map.put("applyApproveTime","入驻时间");
		map.put("clientNatureStr","客户类别");
		map.put("businessName","业务员");
		map.put("address","地理信息");
		map.put("canteenSplitPercentStr","分账比例（%）");
		map.put("mobile","联系电话");
		crmClientResourceParam.setCustomerAttribute(2);
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.clientListExcelOut(crmClientResourceParam);//客户列表详情
		
		try {
			ExcelUtil.writeXls(response,"代理商客户", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:clientListExcelOut:",e);
		}
	}
	
	
	
	
	
    /**
     * 调整保护时间
    * @Title: updataStatus 
    * @author : lcl
    * @time : 2017年6月19日 下午3:26:16
    * @return RespModel    返回类型 
    * @throws
     */
    @RequestMapping(value = "/updataStatus")
    @ResponseBody
    public RespModel updataStatus0(CrmClientResourceParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       param.setId(param.getId());
       param.setProtectDeadline(param.getProtectDeadline());
       //时间转化 String -> Date
       CrmClientResourcePO po=BeanConvertor.convertBean(param, CrmClientResourcePO.class);
       icrmClientResourceService.updateEntity(po);
       
       return res;
    }
	
	
	
	
	
	/***
	 * 
	 * @Title: protectCustomerDetailsExcelOut 
	 * @Description: 保护客户导出 
	 * @param @param response
	 * @param @param crmClientResourceParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月19日 下午4:55:21
	 */
	@RequestMapping(value="/protectCustomerDetailsExcelOut")
	public void protectCustomerDetailsExcelOut(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###protectCustomerDetailsExcelOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		Integer userType = CrmControllerHelper.getSessionUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","ID");
		map.put("name","名称");
		map.put("clientTypeStr","客户类别");
		map.put("address","地理信息");
		if(4 == userType){
			map.put("businessName","业务员");
		}
		map.put("protectDeadline","保护期到");
		map.put("contact","联系人");
		map.put("mobile","联系电话");
		map.put("tailNum","跟踪记录");
		
		//客户详情
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.protectCustomerDetailsExcelOut(crmClientResourceParam,userType);
		
		try {
			ExcelUtil.writeXls(response,"保护客户", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:clientListExcelOut:",e);
		}
	}
	
	/**
	 * 
	 * @Title: formalCustomerDetailsExcelOut 
	 * @Description: 正式客户导出 
	 * @param @param response
	 * @param @param crmClientResourceParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月19日 下午4:55:27
	 */
	@RequestMapping(value="/formalCustomerDetailsExcelOut")
	public void formalCustomerDetailsExcelOut(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###formalCustomerDetailsExcelOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		Integer userType = CrmControllerHelper.getSessionUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","ID");
		map.put("name","名称");
		map.put("clientTypeStr","客户类别");
		map.put("address","地理信息");
		if(4 == userType){
			map.put("businessName","业务员");
		}
		map.put("applyApproveTime","签约时间");
		map.put("contact","联系人");
		map.put("mobile","联系电话");
		map.put("canteenSplitPercentStr","分账比例（%）");
		
		//客户详情
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.formalCustomerDetailsExcelOut(crmClientResourceParam,userType);
		
		try {
			ExcelUtil.writeXls(response,"正式客户", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:clientListExcelOut:",e);
		}
	}
	
	/**
	 * 
	 * @Title: agentCustomerDetailsExcleOut 
	 * @Description: 代理商客户导出 
	 * @param @param response
	 * @param @param crmClientResourceParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月20日 上午10:15:52
	 */
	@RequestMapping(value="/agentCustomerDetailsExcleOut")
	public void agentCustomerDetailsExcleOut(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###agentCustomerDetailsExcleOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","ID");
		map.put("name","名称");
		map.put("clientTypeStr","客户类别");
		map.put("agentName","代理商");
		map.put("address","地理信息");
		map.put("applyApproveTime","入驻时间");
		map.put("canteenSplitPercentStr","分账比例（%）");
		map.put("mobile","联系电话");
		
		//客户详情
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.agentCustomerDetailsExcleOut(crmClientResourceParam);
		
		try {
			ExcelUtil.writeXls(response,"代理商客户", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:agentCustomerDetailsExcleOut:",e);
		}
	}
	
	
	/**
	 * 
	 * @Title: salesmanCustomerDetailsExcleOut 
	 * @Description: 业务员客户导出 
	 * @param @param response
	 * @param @param crmClientResourceParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月20日 上午10:15:57
	 */
	@RequestMapping(value="/salesmanCustomerDetailsExcleOut")
	public void salesmanCustomerDetailsExcleOut(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###salesmanCustomerDetailsExcleOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","ID");
		map.put("name","名称");
		map.put("clientTypeStr","客户类别");
		map.put("businessName","业务员");
		map.put("address","地理信息");
		map.put("applyApproveTime","入驻时间");
		map.put("canteenSplitPercentStr","分账比例（%）");
		map.put("mobile","联系电话");
		
		//客户详情
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.salesmanCustomerDetailsExcleOut(crmClientResourceParam);
		
		try {
			ExcelUtil.writeXls(response,"业务员客户", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:salesmanCustomerDetailsExcleOut:",e);
		}
	}
	
	/**
	 * 
	 * @Title: customerResourceLook 
	 * @Description: 客户资源查看 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月22日 下午7:57:06
	 */
	@RequestMapping(value="/customerResourceLook")
	public ModelAndView customerResourceLook(Long clientId){
		ModelAndView modelAndView = new ModelAndView("system_operate/customer_resource_look");
		
		//查询客户资源及食堂或代理商信息
		Boolean flag = icrmClientResourceService.customerResourceLook(clientId,modelAndView);
		if(flag) return modelAndView;
		
		return modelAndView;
	}
	
	
	/**
	 * 客户管理  代理商客户的 食堂查看
	* @Title: agentCustomerLook 
	* @author : lcl
	* @time : 2017年7月19日 下午2:37:49
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping(value="/agentCustomerLook")
	public ModelAndView agentCustomerLook(Long clientId){
		ModelAndView modelAndView = new ModelAndView("system_set/customer_resource_look");
		
		//查询客户资源及食堂或代理商信息
		Boolean flag = icrmClientResourceService.customerResourceLook(clientId,modelAndView);
		if(flag) return modelAndView;
		
		return modelAndView;
	}
	
	
	/**
	 * 食堂分账 初始化
	* @Title: canteenSplitAssignSetInit 
	* @author : lcl
	* @time : 2017年6月22日 下午3:25:06
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping(value="/canteenClienResourceInit")
	public ModelAndView canteenClienResourceInit(){
		ModelAndView model = new ModelAndView("collecting_statistics/cateen_fashinable");
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		model.addObject("cSourcesVOs", cSourcesVOs);
		return model;
	}
	/**
	 * 查询食堂分账列表
	* @Title: findPageByParam 
	* @author : lcl
	* @time : 2017年6月23日 下午9:14:13
	* @return Page<CrmSplitAssignSetPO>    返回类型 
	* @throws
	 */
	@RequestMapping(value="/canteenClienResource")
	@ResponseBody
	public PageRespModel canteenClienResource(CrmClientResourceParam crmClientResourceParam){
		PageRespModel model = new PageRespModel();
		Page<CrmClientResourceVO> page = this.icrmClientResourceService.findPageBycanteenClien(crmClientResourceParam);
		if(CollectionUtils.isNotEmpty(page.result)){
			model.setRows(page.result);
			model.setTotal(page.getTotalResult());
		}
		return model;
	}
	/**
	 * 食堂分账导出
	* @Title: excelOut 
	* @author : lcl
	* @time : 2017年6月23日 下午5:10:01
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/excelOuts")
	public void excelOuts(HttpServletResponse response,CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###salesmanCustomerDetailsExcleOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("id","ID");
		map.put("name","名称");
		map.put("customerAttributeName","客户属性");
		map.put("clientTypeName","客户类别");
		map.put("canteenSplitPercent","分账比例（%）");
		map.put("orderNum","订单数量");
		map.put("sumMomey","实付金额");
		map.put("splitMomey","分账金额");
		Page<CrmClientResourceVO> page = icrmClientResourceService.findPageBycanteenClien(crmClientResourceParam);
		List<CrmClientResourceVO> list =page.result;
		try {
			ExcelUtil.writeXls(response,"食堂分账", map,list,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:salesmanCustomerDetailsExcleOut:",e);
		}
	}
	
	/**
     * 代理商列表查看
    * @Title: see 
    * @author : lcl
    * @time : 2017年6月27日 下午2:09:06
    * @return ModelAndView    返回类型 
    * @throws
     */
   @RequestMapping(value="/seeAgent")
   public  ModelAndView seeAgent(CrmAgentParam param){
	   ModelAndView model = new  ModelAndView("customer_management/agent_list_look");
	   	Long clientId  =param.getClientId();//此id不是代理商id  是 客户资源表id
	   	
	   	
	   	if(BeanUtils.isNotEmpty(clientId)){//通过id查询 对象详情
	   		CrmClientResourcePO cResourcePO = icrmClientResourceService.getById(clientId);
	   		if(BeanUtils.isNotEmpty(cResourcePO)){
	   			Long agentCanteenId =cResourcePO.getAgentCanteenId();//食堂id
	   			if(BeanUtils.isNotEmpty(agentCanteenId)){
	   				CrmAgentPO agentPO = iCrmAgentService.findByAgentId(agentCanteenId);
	   				if(BeanUtils.isNotEmpty(agentPO)){
	   					CrmAgentVO agentVO = BeanConvertor.convertBean(agentPO, CrmAgentVO.class);
	   					if(agentVO.getBusinessId()!=null){
	   						//查询的是业务员
	   						CrmBusinessPO cBusinessPO = businessService.findByBusinessId(agentVO.getBusinessId());
	   						if(BeanUtils.isNotEmpty(cBusinessPO)){
	   							agentVO.setBusinessName(cBusinessPO.getName());
	   						}
	   					}
	   					if(BeanUtils.isNotEmpty(agentVO.getAgentNature())){
	   						if(agentVO.getAgentNature().equals(1)){
	   							agentVO.setAgentNatureName("企业法人");
	   						}else if(agentVO.getAgentNature().equals(2)){
	   							agentVO.setAgentNatureName("非企业法人");
	   						}else{
	   							agentVO.setAgentNatureName("暂无数据");
	   						}
	   						
	   					}
	   					
	   					model.addObject("agentVO", agentVO);
	   				}else{
	   					
	   				}
	   				
	   			}
	   		}
	   	}
   		return model;
   }
	
	/**
	 *   区域代理表
	* @Title: areaAgent 
	* @author : lcl
	* @time : 2017年6月27日 下午8:27:55
	* @return ModelAndView    返回类型 
	* @throws
	 */
   @RequestMapping(value="/areaAgent")
   public  ModelAndView areaAgent(CrmAgentParam param){
	   ModelAndView model = new ModelAndView("/index/area_agent");
	   	
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		model.addObject("cSourcesVOs", cSourcesVOs);
		model.addObject("agentStatus", 1);
	   return model;
   }
	/**
	 * 区域代理表
	* @Title: queryPage 
	* @author : lcl
	* @time : 2017年6月27日 下午8:54:05
	* @return PageRespModel    返回类型 
	* @throws
	 */
   @RequestMapping("/areaAgentPage")
   @ResponseBody
   public PageRespModel areaAgentPage(CrmAgentParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
   	Page<CrmAgentVO> pages = iCrmAgentService.queryPage(param);
   	model.setTotal(pages.getTotalResult());
   	model.setRows(pages.result);
   	
   	return model;
   }
	/**
	 * 
	 * @Title: kickOutClient 
	 * @Description: 踢出(保护客户) 
	 * @param @param clientId
	 * @param @return  
	 * @return RespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月27日 下午5:09:35
	 */
	@RequestMapping(value="/kickOutClient")
	@ResponseBody
	public RespModel kickOutClient(Long clientId){
		logger.info("#####CrmClientResourceController###kickOutClient##clientId:"+clientId);
		Long sessionUserId = CrmControllerHelper.getSessionUserId();
		RespModel respModel = new RespModel();
		try {
			Boolean flag = icrmClientResourceService.kickOutClient(clientId,sessionUserId,respModel);//更新客户资源信息
			if(flag) return respModel;
		} catch (Exception e) {
			logger.error("CrmClientResourceController:kickOutClient:"+ e);
			respModel.setDesc("系统出错");
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			return respModel;
		}
		
		return respModel;
	}
	
	//areaAgentExcelOut
   
	@RequestMapping(value="/areaAgentExcelOut")
	public void  excelOut(HttpServletResponse response,CrmAgentParam param){
		Page<CrmAgentVO> pages = iCrmAgentService.queryPage(param);
		List<CrmAgentVO> crmAgentVOs = pages.result;
		Iterator<CrmAgentVO> oIterator = crmAgentVOs.iterator();
		while (oIterator.hasNext()) {
			CrmAgentVO cAgentVO = oIterator.next();
			if(BeanUtils.isNotEmpty(cAgentVO.getAgentNature())){
				if(cAgentVO.getAgentNature().equals(1)){
					cAgentVO.setAgentNatureName("企业法人");
				}else if(cAgentVO.getAgentNature().equals(2)){
					cAgentVO.setAgentNatureName("非企业法人");
				}else{
					cAgentVO.setAgentNatureName("暂无数据");
				}
				
			}
			
			if(BeanUtils.isNotEmpty(cAgentVO.getAgentStatus())){
				if(cAgentVO.getAgentStatus().equals(0)){
					cAgentVO.setAgentStatusName("失效");
				}else if(cAgentVO.getAgentStatus().equals(1)){
					cAgentVO.setAgentStatusName("有效");
				}else{
					cAgentVO.setAgentStatusName("暂无数据");
				}
			}
			if(BeanUtils.isNotEmpty(cAgentVO.getLevel())){
				if(cAgentVO.getLevel().equals(1)){
					cAgentVO.setLevelName("一级代理商");
				}else if(!cAgentVO.getLevel().equals(1) && BeanUtils.isNotEmpty(cAgentVO.getLevel())){
					cAgentVO.setLevelName("下级代理");
				}else{
					cAgentVO.setLevelName("暂无数据");
				}
			}
			
		}
		if(crmAgentVOs!=null){
			LinkedHashMap<String, String> map =new LinkedHashMap<String, String>();
			map.put("agentNo", "客户编号");
			map.put("name", "名称");
			map.put("agentNatureName", "性质");
			map.put("levelName", "代理类型");
			map.put("signTime", "签约时间");
			map.put("agentStatusName", "代理状态");
			map.put("areaName", "代理区域");
			map.put("businessName", "联系业务员");
			map.put("principalName", "联系联系人");
			map.put("mobilephoe", "联系人电话");
			try {
				ExcelUtil.writeXls(response, "区域代理表", map, crmAgentVOs, CrmAgentVO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
	
	/**
	 * 
	 * @Title: businessClientListInit 
	 * @Description: 业务员目标(业务员考核)查看客户列表初始化 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月14日 下午5:15:11
	 */
	@RequestMapping(value="/businessClientListInit")
	public ModelAndView businessClientListInit(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###businessClientListInit##crmClientResourceParam:"+crmClientResourceParam.toString());
		ModelAndView modelAndView = new ModelAndView("customer/customer_list");
		modelAndView.addObject("businessId", crmClientResourceParam.getBusinessId());
		modelAndView.addObject("applyMonth", crmClientResourceParam.getApplyMonth());
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: getBusinessClientList 
	 * @Description: 业务员目标(业务员考核)查看客户列表详情 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月14日 下午4:06:09
	 */
	@RequestMapping(value="/getBusinessClientList")
	@ResponseBody
	public PageRespModel getBusinessClientList(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###getBusinessClientList##crmClientResourceParam:"+crmClientResourceParam.toString());
		PageRespModel pageRespModel = new PageRespModel();
		
		//获取客户列表信息
		Boolean flag = icrmClientResourceService.getBusinessClientList(crmClientResourceParam,pageRespModel);
		if(flag)return pageRespModel;
		
		return pageRespModel;
	}
	
	/**
	 * 
	 * @Title: businessClientListExcleOut 
	 * @Description: 业务员客户列表导出 
	 * @param @param response
	 * @param @param crmClientResourceParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月17日 上午10:51:31
	 */
	@RequestMapping(value="/businessClientListExcleOut")
	public void businessClientListExcleOut(HttpServletResponse response, CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###businessClientListExcleOut##crmClientResourceParam:"+crmClientResourceParam.toString());
		
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("clientNum","编号");
		map.put("name","名称");
		map.put("clientTypeStr","客户分类");
		map.put("address","地理信息");
		map.put("applyApproveTime","时间");
		map.put("orderTotalNum","订单量");
		map.put("orderTotalMoney","订单金额(￥)");
		map.put("canteenSplitPercentStr","分账比例(%)");
		map.put("contact","联系人");
		map.put("mobile","联系电话");
		
		List<CrmClientResourceVO> crmClientResourceVOs = icrmClientResourceService.businessClientListExcleOut(crmClientResourceParam);//业务员客户列表详情
		
		try {
			ExcelUtil.writeXls(response,"客户列表", map,crmClientResourceVOs,CrmClientResourceVO.class);
		} catch (Exception e) {
			logger.error("CrmClientResourceController:businessClientListExcleOut:",e);
		}
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
		//根据客户资源id查询对象
		CrmClientResourcePO crmClientResourcePO = icrmClientResourceService.getById(id);
		if(BeanUtils.isNotEmpty(crmClientResourcePO) && BeanUtils.isNotEmpty(crmClientResourcePO.getEnterpriseId())){
			
			CrmEnterprisePO epo = iCrmEnterpriseService.findById(crmClientResourcePO.getEnterpriseId());
			if (BeanUtils.isNotEmpty(epo)) {
				// 获取详细地址
				String area = iBaseAreaService.getAreaName(CommUtil.parselong(epo.getAreaCode()));
				epo.setAddress(area + "/" + epo.getAddress());
				
				if (epo != null && epo.getAreaCode() != null) {
					// 获取到区
					BaseAreaPO cityQ = this.iBaseAreaService.getCityById((long) epo.getAreaCode());
					// 获取到市
					BaseAreaPO cityS = this.iBaseAreaService.getCityById(cityQ.getParentCode());
					
					modelAndView.addObject("prov", cityS.getParentCode());
					modelAndView.addObject("city", cityS.getAreaCode());
					modelAndView.addObject("dist", cityQ.getAreaCode());
				}
			}
			modelAndView.addObject("epo", epo);
		}
		modelAndView.addObject("crmClientResourcePO", crmClientResourcePO);
		modelAndView.addObject("enterpriseStyleEnum", EnterpriseStyleEnum.getEnumList());
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: clientProtect 
	 * @Description: (散客)客户保护 
	 * @param @param clientId
	 * @param @return  
	 * @return RespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月24日 下午2:45:52
	 */
	@RequestMapping(value="/clientProtect")
	@ResponseBody
	public RespModel clientProtect(Long id){
		logger.info("#####CrmClientResourceController###clientProtect##id:"+id);
		RespModel respModel = new RespModel();
		CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
		Integer userType = sessionUser.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		if(!(3 == userType || 4 == userType)){
			respModel.setDesc("权限不匹配");
			respModel.setCode(RespModel.RespCode.NO_PRIVILEGE.getCode());
			return respModel;
		}
		
		Long userId = sessionUser.getId();
		
		try {
			Boolean flag = icrmClientResourceService.clientProtect(id,userId,respModel);
			if(flag)return respModel;
		} catch (Exception e) {
			logger.error("CrmClientResourceController:clientProtect:"+e);
			respModel.setDesc("系统异常");
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			return respModel;
		}
		
		return respModel;
	}
	
	
	
	@RequestMapping(value="/businessClientList")
	public ModelAndView businessClientList(CrmClientResourceParam crmClientResourceParam){
		logger.info("#####CrmClientResourceController###clientListInit##crmClientResourceParam:"+crmClientResourceParam.toString());
		ModelAndView modelAndView = new ModelAndView("salesman_operate/customer_list");
		//不需要统计客户类型
		try {
			
			icrmClientResourceService.businessClientListInit(crmClientResourceParam,modelAndView);//客户列表初始化
		} catch (ParseException e) {
			logger.error("CrmClientResourceController:clientListInit:"+e.getMessage());
			e.printStackTrace();
		}
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: customsResourceInit 
	 * @Description: 客户资源库页面初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月27日 上午9:41:59
	 */
	@RequestMapping("/customsResourceInit")
    public ModelAndView customsResourceInit() {
    	ModelAndView view = new ModelAndView("customer/customer_resource");
    	Integer sessionUserType = CrmControllerHelper.getSessionUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		view.addObject("userType", sessionUserType);
		return view;
    }
	/**
	 * 
	 * @Title: queryCustomsResource 
	 * @Description: 客户资源库详情
	 * @param @param param
	 * @param @return
	 * @param @throws Exception  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月27日 上午9:45:43
	 */
	@RequestMapping(value="/queryCustomsResource",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel queryCustomsResource(@RequestBody CrmClientResourceParam param) throws Exception {
		CrmAccountPO userPO = CrmControllerHelper.getSessionUser();//用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
		
    	PageRespModel pageRespModel = new PageRespModel();
    	Boolean flag = icrmClientResourceService.queryCustomsResource(param,userPO,pageRespModel);
    	if(flag) return pageRespModel;
    	
    	return pageRespModel;
    } 
}