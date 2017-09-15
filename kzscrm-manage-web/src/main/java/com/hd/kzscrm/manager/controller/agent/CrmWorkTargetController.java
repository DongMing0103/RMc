package com.hd.kzscrm.manager.controller.agent;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
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

import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.model.RespModel.RespCode;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.DateUtil;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmWorkTargetPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.manager.controller.BaseController;
import com.hd.kzscrm.service.param.agent.CrmWorkTargetParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmWorkTargetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.vo.agent.CrmWorkTargetVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmWorkTarget CRMWORKTARGET
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmworktarget")
public class CrmWorkTargetController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmWorkTargetController.class);
    @Autowired
    ICrmWorkTargetService crmWorkTargetService;
    /**团队表*/
    @Autowired
    ICrmTeamService crmTeamService;
    /**
     * 用户表
     */
    @Autowired
    ICrmUserService crmUserService;
    
	@Resource
    ICrmPermUserRoleService iCrmPermUserRoleService;
	@Autowired
	ICrmPermRoleSourcesService roleSourcesService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
    ICrmPermSourcesService  pSourcesService;
	/**
	 * 业务员表
	 */
	@Resource
	ICrmBusinessService iCrmBusinessService;
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
    @RequestMapping("/init")
    @ResponseBody
    public ModelAndView init() {
     	
    	ModelAndView view = new ModelAndView("/crmworktarget/mainCrmWorkTarget");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmWorkTarget");
    	//行业
    	 
		return view;
    }
    /**
     * 个人目标新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmWorkPersonTargetInit")
    @ResponseBody
    public ModelAndView addCrmWorkPersonTargetInit() {
    	ModelAndView modelAndView=new ModelAndView("index/user_target_declare");
         return modelAndView;
    }
    /**
     * 团队目标新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmWorkTeamTargetInit")
    @ResponseBody
    public ModelAndView addCrmWorkTeamTargetInit() {
    	ModelAndView modelAndView=new ModelAndView("index/team_target_declare");
        
    	
         return modelAndView;
    }
    /**
     * 工作目标修正
     * @author 黄霄仪
     * @date 2017年8月1日 下午3:14:44
     */
    @RequestMapping(value = "crmWorkTargetModifyInit")
    @ResponseBody
    public ModelAndView crmWorkTargetModifyInit(CrmWorkTargetParam param) {
    	ModelAndView modelAndView=new ModelAndView("index/work_target_declare_modify");
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getId())){
    		CrmWorkTargetPO crmWorkTargetPO = this.crmWorkTargetService.findById(param.getId());//根据主键查询
    		if(BeanUtils.isNotEmpty(crmWorkTargetPO)){
    			modelAndView.addObject("crmWorkTargetPO", crmWorkTargetPO);
    		}
    	}
    	
         return modelAndView;
    }
    /**
     * 工作目标新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmWorkTargetInit")
    @ResponseBody
    public ModelAndView addCrmWorkTargetInit(CrmWorkTargetParam param) {
    	ModelAndView modelAndView=new ModelAndView("index/work_target_declare");
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getId())){
    		CrmWorkTargetPO crmWorkTargetPO = this.crmWorkTargetService.findById(param.getId());//根据主键查询
    		if(BeanUtils.isNotEmpty(crmWorkTargetPO)){
    			modelAndView.addObject("crmWorkTargetPO", crmWorkTargetPO);
    		}
    	}
    	
         return modelAndView;
    }
    
    @RequestMapping(value = "editCrmWorkTargetInit")
    @ResponseBody
    public ModelAndView editCrmWorkTargetInit(CrmWorkTargetParam param) {
    	ModelAndView modelAndView=new ModelAndView("agent/work_target_declare");
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getId())){
    		CrmWorkTargetPO crmWorkTargetPO = this.crmWorkTargetService.findById(param.getId());//根据主键查询
    		if(BeanUtils.isNotEmpty(crmWorkTargetPO)){
    			modelAndView.addObject("crmWorkTargetPO", crmWorkTargetPO);
    		}
    	}
    	
         return modelAndView;
    }
    
    
    /**
     * 工作目标审核
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmWorkTargetAuditInit")
    @ResponseBody
    public ModelAndView addCrmWorkTargetAuditInit() {
    	ModelAndView modelAndView=new ModelAndView("index/work_target_audit");
    	modelAndView.addObject("workTargetMonth",DateUtil.dateToString(new Date(),"yyyy年MM月"));
         return modelAndView;
    }
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmWorkTarget", method = RequestMethod.POST)
    @ResponseBody
    public RespModel addCrmWorkTarget(@RequestBody CrmWorkTargetParam crmWorkTargetParam,HttpServletRequest request) {
    	RespModel respModel=RespModel.setRespCode(RespCode.SUCCESS);
    	if(BeanUtils.isNotEmpty(crmWorkTargetParam.getId())){
    		//去更新
    		return doUpdate(crmWorkTargetParam,request);
    	}
    	Long userId = CrmControllerHelper.getSessionUserId();
    	//查询business信息
    	CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
    	crmBusinessParam.setUserId(userId);
    	crmBusinessParam.setDelFlag(1);
    	List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
    	if(crmBusinessPOs.size() != 1){
    		return  RespModel.setRespCode(RespCode.SYS_EXCEPTION);
    	}
    	CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
    	try {
    		CrmAccountPO crmAccountPO = CrmControllerHelper.getSessionUser();
    		crmWorkTargetParam.setApplyUserId(crmAccountPO.getId());
    		crmWorkTargetParam.setUpdateId(crmAccountPO.getId());
    		crmWorkTargetParam.setBusinessId(crmBusinessPO.getId());
    		crmWorkTargetParam.setTeamId(crmBusinessPO.getTeamId());
    		crmWorkTargetService.add(crmWorkTargetParam);
		} catch (Exception e) {
			e.printStackTrace();
			return RespModel.failure(e.getMessage());
		}
         return respModel;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmWorkTarget/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmWorkTarget(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmworktarget/mainAddOrEditCrmWorkTarget");
         CrmWorkTargetParam CrmWorkTargetparam = new CrmWorkTargetParam();
         CrmWorkTargetPO po = crmWorkTargetService.get(CrmWorkTargetPO.class,id );
         modelAndView.addObject("po",po);
         modelAndView.addObject("type","update");
         return modelAndView;
    }
   /**
     * 删除
     *
     * @param 
     * @return
     */
    @RequestMapping(value = "doDelete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public RespModel delete(@PathVariable Long id) {
        RespModel res = new RespModel();
        try {
        	crmWorkTargetService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmWorkTargetParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmWorkTargetPO po=BeanConvertor.convertBean(param, CrmWorkTargetPO.class);
       	crmWorkTargetService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmWorkTargetParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmWorkTargetPO po=BeanConvertor.convertBean(param, CrmWorkTargetPO.class);
       crmWorkTargetService.updateEntity(po);
       return res;
    }
    
    /**
     * 同意目标审核
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "doUpdateWorkTarget", method = RequestMethod.POST)
    @ResponseBody
    public RespModel doUpdateWorkTarget(CrmWorkTargetPO crmWorkTargetPO) {
       RespModel res = new RespModel();
       crmWorkTargetPO.setApplyStatus(2);
       crmWorkTargetService.update(crmWorkTargetPO);
       res.setCode(RespModel.RespCode.SUCCESS.getCode());
       res.setDesc(RespModel.RespCode.SUCCESS.getDesc());
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmWorkTargetParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmWorkTargetVO> pages = crmWorkTargetService.queryPage(param);
    	//model.setRows(Lists.transform(pages.result,trans));
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    
    @RequestMapping(value="/targetCheckPass",method=RequestMethod.POST)
    @ResponseBody
    public RespModel targetCheckPass(@RequestBody CrmWorkTargetParam crmWorkTargetParam){
    	RespModel respCode = RespModel.setRespCode(RespCode.SUCCESS);
    	crmWorkTargetService.update(crmWorkTargetParam);
    	return respCode;
    }
    
    /**
     * 初始化目标审核页面
     * @author 黄霄仪
     * @date 2017年6月5日 上午10:43:57
     */
    @RequestMapping("/initTargetCheck")
    @ResponseBody
	public ModelAndView initTargetCheck(){
		ModelAndView modelAndView=new ModelAndView("index/team_audit");
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs",cSourcesVOs);
		modelAndView.addObject("workTargetMonth",DateUtil.dateToString(new Date(),"yyyy年MM月"));
		return modelAndView;
	}
    /**
     * 工作目标审核查询
     * @author 黄霄仪
     * @date 2017年8月3日 下午2:07:31
     * 只有管理员，代理商，业务经理可以查找工作目标审核
     */
    @RequestMapping(value="/workTargetCheckQuery",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel workTargetCheckQuery(@RequestBody CrmWorkTargetParam crmWorkTargetParam)throws Exception{
    	PageRespModel model = new PageRespModel();
    	CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
    	Integer userType=sessionUser.getUserType();
    	/**
    	 * 业务员不能使用审核
    	 */
    	if(userType==CrmAccountUserType.BUSINESS.getCode()){
    		return null;
    	}
    	//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
    	crmWorkTargetParam.setUserType(userType);
    	crmWorkTargetParam.setType(sessionUser.getType());
    	crmWorkTargetParam.setUserId(sessionUser.getId());
    	if(userType==CrmAccountUserType.AGENT.getCode()){
    		crmWorkTargetParam.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
    	}
    	Page<CrmWorkTargetVO> crmWorkTargetVOPage = crmWorkTargetService.workTargetCheckQuery(crmWorkTargetParam);
    	model.setTotal(crmWorkTargetVOPage.getTotalResult());
    	model.setRows(crmWorkTargetVOPage.result);
    	if(BeanUtils.isEmpty(crmWorkTargetVOPage.result)){
    		model.setRows(new ArrayList<>());
    	}
    	
    	return model;
    }
    /**
     * 工作目标审核查询
     * @author 黄霄仪
     * @date 2017年8月3日 下午2:07:31
     * 只有管理员，代理商，业务经理可以查找工作目标审核
     */
    @RequestMapping(value="/queryPageBasic",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel queryPageBasic(@RequestBody CrmWorkTargetParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
    	//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
    	param.setUserType(CrmControllerHelper.getSessionUserType());
    	param.setType(CrmControllerHelper.getSessionType());
    	
    	CrmBusinessPO crmBusinessPO = CrmControllerHelper.getSessionBusinessUser();
    	if(BeanUtils.isNotEmpty(crmBusinessPO)){
    		param.setTeamId(crmBusinessPO.getTeamId());
    	}
    	//翻页查询
    	Page<CrmWorkTargetVO> pages = crmWorkTargetService.queryPageBasic(param);
    	//model.setRows(Lists.transform(pages.result,trans));
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	if(BeanUtils.isEmpty(pages.result)){
    		model.setRows(new ArrayList<>());
    	}
    	
    	return model;
    }
	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmWorkTargetParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmWorkTarget/CrmWorkTarget_Detail");
//		SplitAccountRolePO po = mainService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmWorkTargetPO());
//			return view;
//		}
		return view;
	} 
	
	/**
	 * 
	 * @Title: teamTargetInit 
	 * @Description: 团队目标记录初始化 (包括团队目标考核)
	 * @return ModelAndView    返回类型 
	 * @author LuGaogao
	 * @date 2017年5月28日 上午11:18:41
	 */
	@RequestMapping(value="/teamTargetInit")
	public ModelAndView teamTargetInit(){
		ModelAndView modelAndView = new ModelAndView("target_record/team_target");
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//获取登录者Id
		//根据用户类型查询团队信息
		crmWorkTargetService.teamTargetInit(sessionUserId,modelAndView);
		//根据用户类型查询团队信息
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
 		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
    	
		
		return modelAndView;
	}
	 
	/**
	 * 
	 * @Title: getTeamTargetDetails 
	 * @Description: 获取团队目标信息 (包括团队目标考核)
	 * @param crmWorkTargetParam 工作目标参数
	 * @return RespModel    返回类型 
	 * @author LuGaogao
	 * @throws ParseException 
	 * @date 2017年5月28日 下午1:55:38
	 */
	@RequestMapping(value="/getTeamTargetDetails")
	@ResponseBody
	public PageRespModel getTeamTargetDetails(CrmWorkTargetParam crmWorkTargetParam) throws ParseException{
		logger.info("#####CrmWorkTargetController###getTeamTargetDetails##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		String applyMonthStr = crmWorkTargetParam.getApplyMonthStr();
		if(BeanUtils.isNotEmpty(applyMonthStr)){
			SimpleDateFormat adf = new SimpleDateFormat("yyyy年MM月");
			Date applyMonth = adf.parse(applyMonthStr);
			crmWorkTargetParam.setApplyMonth(applyMonth);
		}
		
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//获取登录者Id
		crmWorkTargetParam.setUserId(sessionUserId);
		
		PageRespModel pageRespModel = new PageRespModel();
		Boolean flag = crmWorkTargetService.getTeamTargetDetails(crmWorkTargetParam,pageRespModel);//获取团队目标信息 (包括团队目标考核)
		if(flag) return pageRespModel;
		
		return pageRespModel;
	}
	/**
	 * 
	 * @Title: agentTargetInit 
	 * @Description: 代理商目标初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年5月31日 下午8:26:50
	 */
	@RequestMapping(value="/agentTargetInit")
	public ModelAndView agentTargetInit(){
		ModelAndView modelAndView = new ModelAndView("target_record/agent_target");
//		CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
// 		Long userId = sessionUser.getId();
		Integer userType = CrmControllerHelper.getSessionUserType();////用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		if(1 == userType){
			modelAndView.addObject("agentCheckTitle", "代理商考核");
		}
 		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
 	
 		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
    	
		
		
		return modelAndView;
	}
	/**
	 * 
	 * @Title: getAgentTargetDetails 
	 * @Description: 获取代理商目标信息 
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws ParseException 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年5月31日 下午8:31:19
	 */
	@RequestMapping(value="/getAgentTargetDetails")
	@ResponseBody
	public PageRespModel getAgentTargetDetails(CrmWorkTargetParam crmWorkTargetParam) throws ParseException{
		logger.info("#####CrmWorkTargetController###getAgentTargetDetails##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		
		String applyMonthStr = crmWorkTargetParam.getApplyMonthStr();
		if(BeanUtils.isNotEmpty(applyMonthStr)){
			SimpleDateFormat adf = new SimpleDateFormat("yyyy年MM月");
			Date applyMonth = adf.parse(applyMonthStr);
			crmWorkTargetParam.setApplyMonth(applyMonth);
		}
		
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//获取登录者Id
		crmWorkTargetParam.setUserId(sessionUserId);//传入UserId
		
		PageRespModel pageRespModel = new PageRespModel();
		Boolean flag = crmWorkTargetService.getAgentTargetDetails(crmWorkTargetParam,pageRespModel);
		if(flag) return pageRespModel;
		
		return pageRespModel;
	}
	
	
	/**
	 * 
	 * @Title: businessTargetInit 
	 * @Description: 业务员目标初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午4:49:25 
	 */
	@RequestMapping(value="/businessTargetInit")
	public ModelAndView businessTargetInit(){
		ModelAndView modelAndView = new ModelAndView("target_record/salesman_target");
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//获取登录者Id
		//根据用户类型查询团队信息
		crmWorkTargetService.businessTargetInit(sessionUserId,modelAndView);
		//根据用户类型查询团队信息
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
 		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: getBusinessTargetDetails 
	 * @Description: 获取业务员(业务员个人,不含下属业务员业绩)目标信息 
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws ParseException 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午4:59:47
	 */
	@RequestMapping(value="/getBusinessTargetDetails")
	@ResponseBody
	public PageRespModel getBusinessTargetDetails(CrmWorkTargetParam crmWorkTargetParam) throws ParseException{
		logger.info("#####CrmWorkTargetController###getBusinessTargetDetails##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		
		String applyMonthStr = crmWorkTargetParam.getApplyMonthStr();
		if(BeanUtils.isNotEmpty(applyMonthStr)){
			SimpleDateFormat adf = new SimpleDateFormat("yyyy年MM月");
			Date applyMonth = adf.parse(applyMonthStr);
			crmWorkTargetParam.setApplyMonth(applyMonth);
		}
		
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//获取登录者Id
		crmWorkTargetParam.setUserId(sessionUserId);//传入UserId
		
		//获取业务员(业务员个人,不含下属业务员业绩)目标信息 
		PageRespModel pageRespModel = new PageRespModel();
		Boolean flag = crmWorkTargetService.getBusinessTargetDetails(crmWorkTargetParam,pageRespModel);
		if(flag) return pageRespModel;
		
		return pageRespModel;
	}
	
	
	
	/**
	 * 
	 * @Title: personalTargetInit 
	 * @Description: 个人目标初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午4:50:27
	 */
	@RequestMapping(value="/personalTargetInit")
	public ModelAndView personalTargetInit(){
		ModelAndView modelAndView = new ModelAndView("target_record/personal_target");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: getPersonalTargetDetails 
	 * @Description: 获取个人(业务员个人,不含下属业务员业绩)目标信息 
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws ParseException 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午4:59:22
	 */
	@RequestMapping(value="/getPersonalTargetDetails")
	@ResponseBody
	public PageRespModel getPersonalTargetDetails(CrmWorkTargetParam crmWorkTargetParam) throws ParseException{
		logger.info("#####CrmWorkTargetController###getPersonalTargetDetails##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		
		String applyMonthStr = crmWorkTargetParam.getApplyMonthStr();
		if(BeanUtils.isNotEmpty(applyMonthStr)){
			SimpleDateFormat adf = new SimpleDateFormat("yyyy年MM月");
			Date applyMonth = adf.parse(applyMonthStr);
			crmWorkTargetParam.setApplyMonth(applyMonth);
		}
		
		Long sessionUserId = CrmControllerHelper.getSessionUserId();//获取登录者Id
		crmWorkTargetParam.setUserId(sessionUserId);//传入UserId
		
		//获取个人(业务员个人,不含下属业务员业绩)目标信息
		PageRespModel pageRespModel = new PageRespModel();
		Boolean flag = crmWorkTargetService.getPersonalTargetDetails(crmWorkTargetParam,pageRespModel);
		if(flag) return pageRespModel;
		
		return pageRespModel;
	}
	
	/**
	 * 
	 * @Title: teamTargetDetailsExcelOut 
	 * @Description: 团队目标(包括团队考核)详情导出 
	 * @param @param response
	 * @param @param crmWorkTargetParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午9:58:54
	 */
	@RequestMapping(value="/teamTargetDetailsExcelOut")
	public void teamTargetDetailsExcelOut(HttpServletResponse response , CrmWorkTargetParam crmWorkTargetParam){
		logger.info("#####CrmWorkTargetController###teamTargetDetailsExcelOut##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("teamName","团队名称");
		map.put("applyMonth","工作月");
		map.put("agentNum","发展代理商数量");
		map.put("completeAgentNum","完成量");
		map.put("canteenNum","发展食堂数量");
		map.put("completeCanteenNum","完成量");
		map.put("orderMoney","订单总金额（￥）");
		map.put("completeOrderMoney","完成量");
		map.put("percentageCompletion","完成度（%）");
		
		
		List<CrmWorkTargetVO> crmWorkTargetVOs = crmWorkTargetService.teamTargetDetailsExcelOut(crmWorkTargetParam);
		try {
			ExcelUtil.writeXls(response,"团队目标", map,crmWorkTargetVOs,CrmWorkTargetVO.class);
		} catch (Exception e) {
			logger.error("CrmWorkTargetController:teamTargetDetailsExcelOut:" , e);
		}
		
	}
	
	/**
	 * 
	 * @Title: agentTargetDetailsExcelOut 
	 * @Description: 代理商目标(包括代理商考核)详情导出  
	 * @param @param response
	 * @param @param crmWorkTargetParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午9:58:59
	 */
	@RequestMapping(value="/agentTargetDetailsExcelOut")
	public void agentTargetDetailsExcelOut(HttpServletResponse response , CrmWorkTargetParam crmWorkTargetParam){
		logger.info("#####CrmWorkTargetController###agentTargetDetailsExcelOut##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("agentName","代理商名称");
		map.put("applyMonth","工作月");
		map.put("agentNum","发展代理商数量");
		map.put("completeAgentNum","完成量");
		map.put("canteenNum","发展食堂数量");
		map.put("completeCanteenNum","完成量");
		map.put("orderMoney","订单总金额（￥）");
		map.put("completeOrderMoney","完成量");
		map.put("percentageCompletion","完成度（%）");
		
		List<CrmWorkTargetVO> crmWorkTargetVOs = crmWorkTargetService.agentTargetDetailsExcelOut(crmWorkTargetParam);
		try {
			ExcelUtil.writeXls(response,"代理商目标", map,crmWorkTargetVOs,CrmWorkTargetVO.class);
		} catch (Exception e) {
			logger.error("CrmWorkTargetController:agentTargetDetailsExcelOut:" , e);
		}
	}
	
	/**
	 * 
	 * @Title: businessTargetDetailsExcelOut 
	 * @Description: 业务员目标(包括业务员考核)详情导出  
	 * @param @param response
	 * @param @param crmWorkTargetParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午9:59:03
	 */
	@RequestMapping(value="/businessTargetDetailsExcelOut")
	public void businessTargetDetailsExcelOut(HttpServletResponse response , CrmWorkTargetParam crmWorkTargetParam){
		logger.info("#####CrmWorkTargetController###businessTargetDetailsExcelOut##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("businessName","业务员姓名");
		map.put("applyMonth","工作月");
		map.put("teamName","所属团队");
		map.put("agentNum","发展代理商数量");
		map.put("completeAgentNum","完成量");
		map.put("canteenNum","发展食堂数量");
		map.put("completeCanteenNum","完成量");
		map.put("orderMoney","订单总金额（￥）");
		map.put("completeOrderMoney","完成量");
		map.put("percentageCompletion","完成度（%）");
		
		List<CrmWorkTargetVO> crmWorkTargetVOs = crmWorkTargetService.businessTargetDetailsExcelOut(crmWorkTargetParam);
		try {
			ExcelUtil.writeXls(response,"业务员目标", map,crmWorkTargetVOs,CrmWorkTargetVO.class);
		} catch (Exception e) {
			logger.error("CrmWorkTargetController:businessTargetDetailsExcelOut:" , e);
		}
	}
	
	/**
	 * 
	 * @Title: personalTargetDetailsExcelOut 
	 * @Description: 个人目标详情导出   
	 * @param @param response
	 * @param @param crmWorkTargetParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午9:59:07
	 */
	@RequestMapping(value="/personalTargetDetailsExcelOut")
	public void personalTargetDetailsExcelOut(HttpServletResponse response , CrmWorkTargetParam crmWorkTargetParam){
		logger.info("#####CrmWorkTargetController###personalTargetDetailsExcelOut##crmWorkTargetParam:"+crmWorkTargetParam.toString());
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
		map.put("applyMonth","工作月");
		map.put("agentNum","发展代理商数量");
		map.put("completeAgentNum","完成量");
		map.put("canteenNum","发展食堂数量");
		map.put("completeCanteenNum","完成量");
		map.put("orderMoney","订单总金额（￥）");
		map.put("completeOrderMoney","完成量");
		map.put("percentageCompletion","完成度（%）");
		
		List<CrmWorkTargetVO> crmWorkTargetVOs = crmWorkTargetService.personalTargetDetailsExcelOut(crmWorkTargetParam);
		try {
			ExcelUtil.writeXls(response,"个人目标", map,crmWorkTargetVOs,CrmWorkTargetVO.class);
		} catch (Exception e) {
			logger.error("CrmWorkTargetController:personalTargetDetailsExcelOut:" , e);
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
	
	
	
}