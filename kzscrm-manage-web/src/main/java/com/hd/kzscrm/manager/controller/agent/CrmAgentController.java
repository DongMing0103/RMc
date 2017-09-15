package com.hd.kzscrm.manager.controller.agent;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentApplyPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentAreaPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRolePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.manage.common.util.SystemControllerHelper;
import com.hd.kzscrm.service.param.agent.CrmAgentApplyParam;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.param.perm.CrmPermUserRoleParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentAreaService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.vo.agent.CrmAgentApplyVO;
import com.hd.kzscrm.service.vo.agent.CrmAgentVO;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.MD5;
import com.hd.wolverine.util.ParamMap;



/**
 * crmAgent CRMAGENT
 * @author system code gen
 *	代理商
 */
@Controller
@RequestMapping("/crmagent")
public class CrmAgentController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmAgentController.class);
    @Resource
    ICrmAgentService iCrmAgentService;
    @Resource
    private ICrmPermRoleService iCrmPermRoleService;
    //客户资源
    @Resource
    ICrmClientResourceService clientResourceService;
    //业务员
    @Autowired
    ICrmBusinessService businessService;
	@Resource
	private ICrmTeamService iCrmTeamService;
    /**
     * 团队表
     */
    @Autowired
    ICrmTeamService crmTeamService;
    /**
     * 用户表
     */
    @Autowired
    ICrmUserService userService;
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
     * 代理商申请审核
     */
    @Autowired
    ICrmAgentApplyService iCrmAgentApplyService;
    
    /**
     * 业务员信息
     */
    @Autowired
    ICrmBusinessService iCrmBusinessService;
    
    /**
     * 地理信息
     */
    @Autowired
    IBaseAreaService iBaseAreaService;
    /**
     * 代理商区域
     */
    @Autowired
    ICrmAgentAreaService agentAreaService;
    
    /**
     ** 预留实现
     */
    public void initControler(HttpServletRequest request, Map<String, Object> map) {
     
    }
    /**
     * 代理商表信息展示
     * @author 黄霄仪
     * @date 2017年6月6日 下午1:46:34
     */
    @RequestMapping("/initShow")
    @ResponseBody
    public ModelAndView initShow(){
    	ModelAndView modelAndView=new ModelAndView("index/agent_audit");
    	List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
    	modelAndView.addObject("cSourcesVOs",cSourcesVOs);
    	return modelAndView;
    }

    /**
     * 代理商审核
     * @author 黄霄仪
     * @date 2017年6月6日 下午1:46:46
     */
    @RequestMapping(value="/showAgent",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel showAgent(CrmAgentApplyParam param){
    	PageRespModel model = new PageRespModel();
    	param.setCheckStatus(0);
    	param.setDelFlag(1);
    	Page<CrmAgentApplyVO> pages = iCrmAgentApplyService.queryPageBasic(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	return model;
    }
    
    /**
     * 代理商审核页面
     * @return
     */
    @RequestMapping(value = "/reviewAgent/{id}")
    @ResponseBody
    public ModelAndView reviewAgent(@PathVariable Long id) {
    	ModelAndView view = new ModelAndView("/agent/audit_agent");
    	try {
    		CrmAgentApplyPO crmAgentApplyPO = iCrmAgentApplyService.getById(id);
    		Long agentId = crmAgentApplyPO.getAgentId();
    		Long areaCode = crmAgentApplyPO.getAreaCode();	// 获取代理商区域
    		CrmAgentApplyVO crmAgentApplyVO = new CrmAgentApplyVO();
    		if (BeanUtils.isNotEmpty(areaCode)) {
    			
    			//获取到区 
				if(BeanUtils.isNotEmpty(areaCode)){
					//获取到区
					BaseAreaPO cityQ =  iBaseAreaService.getCityById(areaCode);
					//获取到市
					if(BeanUtils.isNotEmpty(cityQ)){
						BaseAreaPO cityS = iBaseAreaService.getCityById(cityQ.getParentCode());
						view.addObject("prov",cityS.getParentCode());
						view.addObject("city", cityS.getAreaCode());
						view.addObject("dist", cityQ.getAreaCode());
					}
				}
    			/*List<Long> areaCodes = AreaCodeUtils.getAllOfAreaCode(areaCode);
    			// 获取区域名称
    			List<BaseAreaPO> baseAreaPOs = iBaseAreaService.findByAreaCodes(areaCodes);
    			List<String> areaNames = new LinkedList<>();
    			for (BaseAreaPO baseAreaPO : baseAreaPOs) {
    				areaNames.add(baseAreaPO.getAreaName());
    			}
    			crmAgentApplyVO.setAreaName(StringUtils.join(areaNames, "-"));*/
    		}
    		view.addObject("crmAgentApplyVO", crmAgentApplyVO);
    		view.addObject("crmAgentApplyPO", crmAgentApplyPO);
    		view.addObject("id", id);
    		view.addObject("agentId", agentId);
    		if(BeanUtils.isNotEmpty(crmAgentApplyPO)){
    			view.addObject("clientId", crmAgentApplyPO.getClientId());
    		}
		} catch (Exception e) {
			logger.error("#crmagent#reviweAgent:", e);
		}
    	return view;
    }
    
    /**
     * 审核
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "auditing")
    @ResponseBody
    public RespModel auditing(CrmAgentApplyParam param, HttpServletRequest request) {
    	RespModel res = new RespModel();
    	Long sessionUserId = CrmControllerHelper.getSessionUserId();
    	try {
    		CrmAgentApplyPO po = BeanConvertor.convertBean(param, CrmAgentApplyPO.class);
        	po.setCheckStatus(1);
        	po.setCheckTime(new Date());
        	iCrmAgentApplyService.update(po);
        	
        	//更新客户资源信息
        	Date currentTime = new Date();
        	Long clientId = po.getClientId();
        	CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
        	crmClientResourcePO.setId(clientId);
        	crmClientResourcePO.setClientNature(3);//客户性质 1.散客，2.保护客户，3.正式客户
        	crmClientResourcePO.setCheckStatus(1);//审核状态,0.申请中，1.申请通过，2.申请失败
        	clientResourceService.update(crmClientResourcePO);
        	
        	//更新代理商信息
        	Long agentId = po.getAgentId();
        	CrmAgentPO crmAgentPO = iCrmAgentService.findByAgentId(agentId);
        	//查询关于发展该代理商的业务员信息,及业务员归属平台还是代理商信息
        	Long businessId = crmAgentPO.getBusinessId();
        	CrmBusinessPO crmBusinessPO = new CrmBusinessPO();
        	crmBusinessPO.setId(businessId);
        	crmBusinessPO.setDelFlag(1);
        	crmBusinessPO = (CrmBusinessPO) iCrmBusinessService.getByExample(crmBusinessPO);
        	Integer type = crmBusinessPO.getType();//1.平台业务员，2.代理商业务员
        	if(null != type){
        		if(1 == type){//1.平台业务员
        			crmAgentPO.setParentId(agentId);//父id指向自己的Id
        			crmAgentPO.setParentIds(agentId.toString());//设置父辈ids
        			crmAgentPO.setTopParentId(agentId);//设置顶级父Id
        			crmAgentPO.setLevel(1);//设置等级
        		}else{//2.代理商业务员
        			Long aId = crmBusinessPO.getAgentId();//该业务员所处代理商
        			CrmAgentPO agentPO = new CrmAgentPO();
        			agentPO.setId(aId);
        			agentPO.setDelFlag(1);
        			agentPO = (CrmAgentPO) iCrmAgentService.getByExample(agentPO);
        			crmAgentPO.setParentId(aId);
        			crmAgentPO.setParentIds(agentPO.getParentIds()+","+agentId);
        			crmAgentPO.setTopParentId(agentPO.getTopParentId());
        			crmAgentPO.setLevel(agentPO.getLevel()+1);
        		}
        	}
        	
        	crmAgentPO.setAuthenticationStatus(1);
        	crmAgentPO.setAgentStatus(1);
        	crmAgentPO.setUpdateId(sessionUserId);
        	crmAgentPO.setUpdateTime(currentTime);
        	iCrmAgentService.update(crmAgentPO);
        	
        	//更新用户信息
        	Long userId = crmAgentPO.getUserId();
        	CrmUserPO crmUserPO = new CrmUserPO();
        	crmUserPO.setId(userId);
        	crmUserPO.setUpdateId(sessionUserId);
        	crmUserPO.setUpdateTime(currentTime);
        	crmUserPO.setAuthenticationStatus(1);
        	userService.update(crmUserPO);
        	
        	//配置权限信息
        	CrmPermUserRolePO crmPermUserRolePO = new CrmPermUserRolePO();
        	crmPermUserRolePO.setId(CrmControllerHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_user_role.name()));
        	crmPermUserRolePO.setUserId(userId);
        	crmPermUserRolePO.setRoleId(2L);
        	iCrmPermUserRoleService.save(crmPermUserRolePO);
        	
		} catch (Exception e) {
			logger.error("#crmagentcontroller#auditing:", e);
		}
    	return res;
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
     	
    	ModelAndView view = new ModelAndView("/team_management/agent_list");
    	ParamMap paramMap =  new ParamMap();	
    	CrmAgentParam param = new CrmAgentParam();
    	Long userId =SystemControllerHelper.getSessionUserId();
    	Integer userType = CrmControllerHelper.getSessionUserType();
    	//菜单
    	view.addObject("active","CrmAgent");
    	view.addObject("userId",userId);
    	view.addObject("userType",userType);
       	view.addObject("agentStatus",1);
    	//行业
    	CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
		Long userId1 = sessionUser.getId();
		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(userId1);//用户，角色映射

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
		view.addObject("cSourcesVOs", cSourcesVOs);
    	
    	
    	 
		return view;
    }
    
    
    /**
    *
    * 我的代理商
    * @param param
    * @return
    */
   @RequestMapping("/myAgentInit")
   public ModelAndView myAgentInit() {
   	ModelAndView view = new ModelAndView("/my_team/my_agent");
   	ParamMap paramMap =  new ParamMap();
	view.addObject("userId", SystemControllerHelper.getSessionUserId());
   	CrmAgentParam param = new CrmAgentParam();
   	param.setUserId(SystemControllerHelper.getSessionUserId());
   	Page<CrmAgentVO> cList =  iCrmAgentService.queryPage(param);
   	int cListNum = cList.getTotalResult();
   	param.setAgentStatus(1);//有效
   	Page<CrmAgentVO> cList1 =  iCrmAgentService.queryPage(param);
   	int cListNum1 = cList1.getTotalResult();
   	param.setAgentStatus(0);//无效
   	Page<CrmAgentVO> cList0 =  iCrmAgentService.queryPage(param);
   	int cListNum0 = cList0.getTotalResult();
   	Map<String,Object> map=new HashMap<String,Object>();
   	map.put("all",0);
		map.put("vali",0);
		map.put("novali",0);

   	//全部数量
		//全部数量
				if(cList!=null){
					map.put("all",cListNum);

				} 
				if(cList1!=null){
					map.put("vali",cListNum1);
				}
				if(cList0!=null){
					map.put("novali",cListNum0);
				}
   	//菜单
   	view.addObject("active","CrmAgent");

   	view.addObject("map",map);
   
   	//行业
	return view;
   }
    
    /**
     * 代理商列表查看
    * @Title: see 
    * @author : lcl
    * @time : 2017年6月27日 下午2:09:06
    * @return ModelAndView    返回类型 
    * @throws
     */
   @RequestMapping(value="/see")
   public  ModelAndView see(CrmAgentParam param){
	   ModelAndView model = new  ModelAndView("customer_management/agent_list_look");
	   	Long agentId  =param.getAgentId();
	   	if(BeanUtils.isNotEmpty(agentId)){//通过id查询 对象详情
	   		CrmAgentPO agentPO = iCrmAgentService.findByAgentId(agentId);
	   		CrmAgentApplyPO cApplyPO = iCrmAgentApplyService.findByAgentId(agentId);
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
	   			if(BeanUtils.isEmpty(agentVO.getContactRealname())){
	   				agentVO.setContactRealname(agentVO.getPrincipalName());
	   				agentVO.setContactPhone(agentVO.getMobilephoe());
	   			}
	   			if(BeanUtils.isNotEmpty(cApplyPO)){//代理时间段
	   				Date startTime = cApplyPO.getCooperationStartTime();
	   				Date endTime = cApplyPO.getCooperationEndTime();
	   				SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
	   				String startTimes = df.format(startTime);
	   				String endTimes = df.format(endTime);
	   				if(BeanUtils.isEmpty(startTimes) && BeanUtils.isEmpty(endTimes)){
	   					agentVO.setTimeSlot("-");
	   				}
	   				agentVO.setTimeSlot(startTimes+" -  "+endTimes);
	   				
	   			}
	   			if(BeanUtils.isNotEmpty(agentVO.getAreaCode())){
	   				String areaName = iBaseAreaService.getAreaName(agentVO.getAreaCode());
					if(BeanUtils.isNotEmpty(areaName)){
						if(areaName.indexOf("/")>-1){
							areaName = areaName.replaceAll("/", "");
						}
						agentVO.setAreaName(areaName);
					}else{
						BaseAreaPO baseAreaPO = iBaseAreaService.findByAreaCode(agentVO.getAreaCode());
						if (BeanUtils.isNotEmpty(baseAreaPO)) {
							agentVO.setAreaName(baseAreaPO.getAreaName());
						}
					}

	   			}
	   			
	   			
	   			
	   			model.addObject("agentVO", agentVO);
	   		}
	   	}
   		return model;
   }
   /**
    * 查看代理商资料
    * @author 黄霄仪
    * @date 2017年7月5日 上午9:55:18
    */
   @RequestMapping(value="/lookupInit/{id}")
   public ModelAndView lookupInit(@PathVariable Long id){
		ModelAndView modelAndView = new ModelAndView("index/edit_agent");
		modelAndView.addObject("type", "lookup");
		CrmAgentVO crmAgentVO= iCrmAgentService.findById(id);
		modelAndView.addObject("crmAgentVO",crmAgentVO);
		return modelAndView;
   }
    
    /**
     * 编辑 
    * @Title: editInit 
    * @author : lcl
    * @time : 2017年6月13日 下午4:55:17
    * @return ModelAndView    返回类型 
    * @throws
     */
    @RequestMapping(value="/editInit")
    public  ModelAndView editInit(CrmAgentParam param){
    	ModelAndView model = new ModelAndView("/index/add_agent");
    	
        CrmAccountPO cAccountPO =  SystemControllerHelper.getSessionUser();
        List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
        if(BeanUtils.isNotEmpty(param.getId())){
        	CrmAgentPO crmAgentPO = iCrmAgentService.getById(param.getId());
        	param.setBusinessId(crmAgentPO.getBusinessId());
        }
        if(BeanUtils.isNotEmpty(cAccountPO)){
        	String userName = cAccountPO.getUserName();
        	param.setUserName(userName);
        	param.setUserId(cAccountPO.getId());
        	param.setUserType(cAccountPO.getUserType());//用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
        }
    	Boolean flag = iCrmAgentService.addAgent(param,model);
    	if(flag) return model;
    	
    	return model;
    }
    
    /**
     * 新增
    * @Title: addInit 
    * @author : lcl
    * @time : 2017年8月7日 下午7:24:10
    * @return ModelAndView    返回类型 
    * @throws
     */
    @RequestMapping(value="/addInit")
    public  ModelAndView addInit(CrmAgentParam param){
    	ModelAndView model = new ModelAndView("/check/add_agent");
    	ParamMap paramMap = new  ParamMap();
		//ModelAndView model = new ModelAndView("/index/add_agent");
		Integer userType = CrmControllerHelper.getSessionType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Long userId = CrmControllerHelper.getSessionUserId();
		CrmTeamParam crmTeamParam = new CrmTeamParam();
		CrmBusinessPO crmBusinessPO = null;
		CrmBusinessParam businessParam = new CrmBusinessParam();
	    if(1 == userType){//1 管理员
			crmTeamParam.setType(1);
			
			businessParam.setType(1);//1.平台业务员，2.代理商业务员
			businessParam.setBusinessId(param.getBusinessId());
		}else if(2 == userType){//2.代理商
			
			CrmAgentParam crmAgentParam = new CrmAgentParam();
			crmAgentParam.setUserId(userId);
			crmAgentParam.setDelFlag(1);
			List<CrmAgentPO> crmAgentPOs = this.iCrmAgentService.commonQuery(crmAgentParam);
			CrmAgentPO crmAgentPO = crmAgentPOs.get(0);
			
			Long agentId = crmAgentPO.getId();
			crmTeamParam.setType(2);
			crmTeamParam.setAgentId(agentId);
			
			businessParam.setType(2);
			businessParam.setAgentId(agentId);
			
		}else if(3 == userType || 4 == userType){//3.业务员，4.业务经理
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setUserId(userId);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
			crmBusinessPO = crmBusinessPOs.get(0);
			Integer type = crmBusinessPO.getType();//1.平台业务员，2.代理商业务员
			if(1 == type){
				crmTeamParam.setType(1);
				
				businessParam.setType(1);//1.平台业务员，2.代理商业务员
			}else{
				Long agentId = crmBusinessPO.getAgentId();
				
				crmTeamParam.setType(2);
				crmTeamParam.setAgentId(agentId);
				
				businessParam.setType(2);
				businessParam.setAgentId(agentId);
			}
		}else{
			return model;
		}
    	
	    List<CrmTeamPO> cTeamPOs = this.iCrmTeamService.commonQuery(crmTeamParam);//查询团队信息
		List<CrmBusinessPO> CrmBusinessPOs = iCrmBusinessService.commonQuery(businessParam);//查询业务员信息
		Map<Long,Integer> businessIdWithProtectedNum = new HashMap<Long, Integer>();//业务员id和其保护客户数量
		for (CrmBusinessPO businessPO : CrmBusinessPOs) {
			//统计业务员的保护客户数量
			Long businessId = businessPO.getId();
			businessIdWithProtectedNum.put(businessId, null);
		}
		//查询保护客户数量
		CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
		crmClientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdWithProtectedNum.keySet()));
		crmClientResourceParam.setDelFlag(1);
		crmClientResourceParam.setClientNature(2);//客户性质	1.散客，2.保护客户，3.正式客户
		//businessIdWithProtectedNum.clear();
		Map<Long,Integer> businessIdWithProtectedNum2 = clientResourceService.countProtectedClientNum(crmClientResourceParam);//统计业务员的保护客户数量
		if(BeanUtils.isNotEmpty(businessIdWithProtectedNum2)){
			for (Long bId : businessIdWithProtectedNum2.keySet()) {
				Integer num = CommUtil.parseInteger(businessIdWithProtectedNum2.get(bId));//得到每个key多对用value的值
				businessIdWithProtectedNum.put(bId, num);
	        }
		}
		
		
		model.addObject("cTeamPOs", cTeamPOs);
	//	model.addObject("businessPOs", businessPOs);
    	return model;
    }
    /**
     * 本月工作目标
    * @Title: monthGoals 
    * @author : lcl
    * @time : 2017年6月13日 下午4:55:40
    * @return ModelAndView    返回类型 
    * @throws
     */
    @RequestMapping(value="/monthGoals")
    public ModelAndView monthGoals(CrmAgentParam param){
    	return iCrmAgentService.monthGoals(param);
    }
    /**
     * 本月工作目标
    * @Title: monthGoalsList 
    * @author : lcl
    * @time : 2017年6月13日 下午7:58:25
    * @return PageRespModel    返回类型 
    * @throws
     */
    @RequestMapping(value="/monthGoalsList")
    @ResponseBody
    public PageRespModel monthGoalsList(CrmAgentParam param){
    	return iCrmAgentService.findMonthGoalsList(param);
    }
    
    /**
     * 对应的
     * 业务员列表
    * @Title: businessList 
    * @author : lcl
    * @time : 2017年6月13日 下午8:56:14
    * @return ModelAndView    返回类型 
    * @throws
     */
    @RequestMapping(value="/businessListInit")
    public ModelAndView businessListInit(CrmAgentParam param){
    	ModelAndView view = new ModelAndView("/system_operate/salesman_list");
    	ParamMap paramMap =  new ParamMap();
    	CrmTeamParam param3 = new CrmTeamParam();
    	List<CrmTeamPO>  crmTeamPOs = crmTeamService.findAll(param3);
    	view.addObject("crmTeamPOs", crmTeamPOs);
    	CrmBusinessParam param2 = new CrmBusinessParam();
    	param2.setAgentId(param.getAgentId());
    	Page<CrmBusinessVO> pages = businessService.queryPageList(param2);
    	int num = pages.getTotalResult();
    	param2.setJobStatus(0);//离职
    	Page<CrmBusinessVO> pages2 = businessService.queryPageList(param2);
    	int num2 = pages2.getTotalResult();
    	param2.setJobStatus(1);//在职
    	Page<CrmBusinessVO> pages3 = businessService.queryPageList(param2);
    	int num3 = pages3.getTotalResult();
    	Map<String, Object> map = new HashMap<>();
    	map.put("all", 0);
    	map.put("onjob", 0);
    	map.put("quitjob", 0);
    	if(BeanUtils.isNotEmpty(pages)){//全部
    		map.put("all", num);
    	}
    	if(BeanUtils.isNotEmpty(pages2)){
    		map.put("quitjob", num2);
    	}
    	if(BeanUtils.isNotEmpty(pages3)){
    		map.put("onjob", num3);
    	}
    	view.addObject("map", map);
    	//菜单
    	view.addObject("active","CrmBusiness");
    	view.addObject("agentId", param.getAgentId());
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getAgentId())){
    		view.addObject("agentId", param.getAgentId());
    	//行业
    	}
    	List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
    	view.addObject("cSourcesVOs", cSourcesVOs);
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmAgent", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmAgent() {
         ModelAndView modelAndView = new ModelAndView("/crmagent/mainAddOrEditCrmAgent");
		 CrmAgentParam CrmAgentparam = new CrmAgentParam();
		 List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		 modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmAgent/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmAgent(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmagent/mainAddOrEditCrmAgent");
         CrmAgentParam CrmAgentparam = new CrmAgentParam();
         CrmAgentPO po = iCrmAgentService.get(CrmAgentPO.class,id );
		 List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		 modelAndView.addObject("cSourcesVOs", cSourcesVOs);
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
			iCrmAgentService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    /**
     * 保存
    * @Title: doSave 
    * @author : lcl
    * @time : 2017年6月19日 下午7:15:16
    * @return RespModel    返回类型 
    * @throws
     */
    @RequestMapping(value = "/doSave")
    @ResponseBody
    public RespModel doSave(CrmAgentParam param, HttpServletRequest request) {
    	Long sessionUserId = CrmControllerHelper.getSessionUserId();
        RespModel res = new RespModel();
        Long areaCode = param.getAreaCode();
        Integer provCode = param.getProvCode();
        Integer cityCode = param.getCityCode();
        if(BeanUtils.isNotEmpty(areaCode)||BeanUtils.isNotEmpty(provCode)||BeanUtils.isNotEmpty(cityCode)){
        	BaseAreaPO baseAreaPO = iBaseAreaService.findByAreaCode(areaCode);
        	BaseAreaPO pAreaPO = iBaseAreaService.findByAreaCode((long)provCode);
        	BaseAreaPO cAreaPO = iBaseAreaService.findByAreaCode((long)cityCode);
        	String provName = null;
        	String cityName = null;
        	String areaName = null;
        	if(BeanUtils.isNotEmpty(baseAreaPO)){
        		areaName = baseAreaPO.getAreaName();
        	}
        	if(BeanUtils.isNotEmpty(cAreaPO)){
        		cityName = cAreaPO.getAreaName();
        	}
        	if(BeanUtils.isNotEmpty(pAreaPO)){
        		provName = pAreaPO.getAreaName();
        	}
        	param.setAreaName(provName+cityName+areaName);
        	
        }
       
        
        String mobilePhone = param.getMobilephoe();
        CrmUserPO cuPO = new CrmUserPO();
        cuPO.setMobilephone(mobilePhone);
        cuPO.setUserType(2);
        cuPO.setDelFlag(1);
        CrmUserPO cPO = (CrmUserPO) userService.getByExample(cuPO);
        
        try {
        	if(BeanUtils.isNotEmpty(cPO)){
        		if(CommUtil.parseLong(param.getUserId())>0){
        			if(cPO.getId().equals(param.getUserId())){
        			}else{
        				return RespModel.failure("负责人电话已存在，请重新输入");
        			}
        		}else{
        			return RespModel.failure("负责人电话已存在，请重新输入");
        		}
        	}
        	if(BeanUtils.isEmpty(param.getId())){
        		//客户资源列表
        		Date nowTime = new Date();
        		param.setCreateTime(nowTime);
        		param.setAgentStatus(0);//代理状态为0无效 
        		param.setDelFlag(1);//
        		param.setBusinessId(param.getBusinessId());//业务员id
        		param.setId(SystemControllerHelper.genNextIDValue(DatabaseTableNameEnum.crm_agent));//代理商id也是 客户资源中的agentCanteenId
        	
        		CrmClientResourcePO clientResourcePO = new CrmClientResourcePO();
        		clientResourcePO.setId(SystemControllerHelper.genNextIDValue(DatabaseTableNameEnum.crm_client_resource));
        		clientResourcePO.setClientType(1);//代理商
        		clientResourcePO.setAgentCanteenId(param.getId());//代理商的id
        		
        		//客户编号的生成（）clientResourcePO.setClientNum();
        		if(BeanUtils.isNotEmpty(param.getBusinessId())){
        			clientResourcePO.setClientNature(2);//关联到业务员 就是 保护客户 且每个业务员只能保护10个客户
        			clientResourcePO.setBusinessId(param.getBusinessId());
        		}else{
        			clientResourcePO.setClientNature(1);//1散客 
        		}
        		Date sevenDay = getDateAddSeven(nowTime);//保护时间 加七天
        		clientResourcePO.setName(param.getName());
        		clientResourcePO.setContact(param.getPrincipalName());//联系人姓名
        		clientResourcePO.setMobile(param.getMobilephoe());
        		clientResourcePO.setCreateTime(nowTime);
        		clientResourcePO.setProtectDeadline(sevenDay);
        		clientResourcePO.setDelFlag(1);
        		clientResourcePO.setCustomerAttribute(CrmControllerHelper.getSessionType());//获取市平台客户或者是代理商客户
        		clientResourcePO.setAddress(param.getAreaName()+param.getAddress());
        		clientResourcePO.setAreaCode(param.getAreaCode());
        		//代理商编号的生成格式不知道
        		clientResourceService.save(clientResourcePO);
        		//保存代理商
        		
        		//现在是资源 
        		CrmUserPO crmUserPO = new  CrmUserPO();
        		Long userId = SystemControllerHelper.genNextIDValue(DatabaseTableNameEnum.crm_user);
				crmUserPO.setId(userId);
        		param.setUserId(crmUserPO.getId());//业务员表中的user_ID
        		crmUserPO.setUserName(param.getName());
        		crmUserPO.setPassword(MD5.md5("123456"));//待修改 
        		crmUserPO.setMobilephone(param.getMobilephoe());//可能用于登录
        		crmUserPO.setAccount(param.getMobilephoe());//负责人电话即登陆账号
        		crmUserPO.setUserType(2);//代表  代理商
        		crmUserPO.setUserStatus(1);//状态为正常1
        		crmUserPO.setDelFlag(1);
        		crmUserPO.setType(2);//代理商
        		crmUserPO.setCreateTime(nowTime);
        		crmUserPO.setUpdateTime(nowTime);
        		crmUserPO.setCreateId(sessionUserId);
        		crmUserPO.setUpdateId(sessionUserId);
        		crmUserPO.setAddress(clientResourcePO.getAddress());
        		crmUserPO.setAreaCode(param.getAreaCode());
        		crmUserPO.setAuthenticationStatus(0);//未认证  等代理商加盟后才能修改成1
        		//crmUserPO.setType(1); //1平台 2 代理商
        		userService.save(crmUserPO);//保存业务员的userId
        		
        		CrmAgentPO po=BeanConvertor.convertBean(param, CrmAgentPO.class);
        		
        		
        		
        		iCrmAgentService.saveEntity(po);
        		
        		//代理商区域表
        		CrmAgentAreaPO cAgentAreaPO = new CrmAgentAreaPO();
        		cAgentAreaPO.setId(SystemControllerHelper.genNextIDValue(DatabaseTableNameEnum.crm_agent_area));
        		cAgentAreaPO.setAreaCode(param.getAreaCode());
        		cAgentAreaPO.setAgentId(po.getId());
        		cAgentAreaPO.setCreateTime(nowTime);
        		//cAgentAreaPO.setCreateUid(createUid);
        		cAgentAreaPO.setDelFlag(1);
        		agentAreaService.save(cAgentAreaPO);
        		
        		//添加默认代理商角色
        		List<CrmPermRolePO> crmPermRolePOs = iCrmPermRoleService.findByUserType(2);//代理商角色
        		if(BeanUtils.isNotEmpty(crmPermRolePOs)){
        			CrmPermRolePO crmPermRolePO = crmPermRolePOs.get(0);
        			
        			CrmPermUserRoleParam crmPermUserRoleParam=new CrmPermUserRoleParam();
        			crmPermUserRoleParam.setRoleId(crmPermRolePO.getId());
        			crmPermUserRoleParam.setUserId(userId);
        			iCrmPermUserRoleService.saveEntity(crmPermUserRoleParam);
        		}
        		
        		
        	}else{
        		doUpdate(param,request);
        		return res;
        	}
			
		} catch (Exception e) {
			logger.error("dosave", e);
			return RespModel.failure(e.getMessage());
		}
        
        
        
               
        return res;
    }
    /**
     * 修改 
    * @Title: doUpdate 
    * @author : lcl
    * @time : 2017年6月19日 下午7:14:54
    * @return RespModel    返回类型 
    * @throws
     */
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmAgentParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       try {
    	   Long agentId = param.getId();//客户资源表id
    	   if(BeanUtils.isNotEmpty(agentId)){
    		   CrmAgentPO cAgentPO = this.iCrmAgentService.getById(agentId);
    		   if(BeanUtils.isNotEmpty(cAgentPO)){
//    			  
//    			   
//    			   Long agentId = clientResourcePO.getAgentCanteenId();//获取代理商id
    			
    					   cAgentPO=BeanConvertor.convertBean(param, CrmAgentPO.class);
    					   iCrmAgentService.updateEntity(cAgentPO);//保存代理商
    					   if(BeanUtils.isNotEmpty(cAgentPO.getId())){
    						   CrmClientResourcePO clientResourcePO = this.clientResourceService.findByAgentCanteenId(cAgentPO.getId());
    						   clientResourcePO.setName(param.getName());
    		    			   clientResourcePO.setContact(param.getPrincipalName());
    		    			   clientResourcePO.setMobile(param.getMobilephoe());
    		    			   clientResourcePO.setAddress(param.getAddress());
    		    			   clientResourcePO.setAreaCode(param.getAreaCode());
    		    			   clientResourceService.updateEntity(clientResourcePO);//更新客户资源
    					   }
    					   
    					   
    					   if(BeanUtils.isNotEmpty(cAgentPO.getUserId())){
    						   Long userId = cAgentPO.getUserId();
    						   CrmUserPO crmUserPO = userService.getById(userId);
    						   if(BeanUtils.isNotEmpty(crmUserPO)){
    							   crmUserPO.setMobilephone(param.getMobilephoe());
    							   cAgentPO.setName(param.getPrincipalName());
    					   }
    			   }
    					   
    					   
    					   if(BeanUtils.isNotEmpty(cAgentPO.getTeamId())){//修改团队
    						   
    					   }
    		   }
    	   }
		
	} catch (Exception e) {
		logger.error("update", e);
	}
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmAgentParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
    	CrmAccountPO  accountPO =SystemControllerHelper.getSessionUser(); 
    	if(BeanUtils.isNotEmpty(accountPO) && accountPO.getUserType()!=1 && accountPO.getUserType()!=0 ){
    		Long userId = accountPO.getId();
    		param.setUserId(userId);
    	}
    	Page<CrmAgentVO> pages = iCrmAgentService.queryPage(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    /**
     * 客户资源 列表初始化
    * @Title: clientResourceInit 
    * @author : lcl
    * @time : 2017年6月16日 上午11:05:25
    * @return ModelAndView    返回类型 
    * @throws
     */
    @RequestMapping(value="/clientResourceInit")
    public ModelAndView clientResourceInit(CrmAgentParam param){
    	ModelAndView model = new  ModelAndView("system_operate/customer_list");
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getAgentId())){
    		model.addObject("agentId", param.getAgentId());
    		
    		List<Long> businessIds = new  ArrayList<Long>();
    		//先查询对应的业务员
    		CrmBusinessParam businessParam = new  CrmBusinessParam();
    		businessParam.setAgentId(param.getAgentId());//通过 代理商id查询对应的 业务员集合
    		Page<CrmBusinessVO> cPage =  businessService.queryPage(businessParam);
    		List<CrmBusinessVO> cBusinessVOs = cPage.result;
    		List<CrmClientResourceVO> clientResourceVOs = new ArrayList<CrmClientResourceVO>();
    		if(CollectionUtils.isNotEmpty(cBusinessVOs)){
    			for(int i=0;i<cBusinessVOs.size();i++){
    				//循环查询业务员下的客户列表
    				CrmBusinessVO cBusinessVO = cBusinessVOs.get(i);
    				if(BeanUtils.isNotEmpty(cBusinessVO)){
    					Long businessId = cBusinessVO.getId();
    					if(BeanUtils.isNotEmpty(businessId)){//根据业务员 查询业务员下的客户
    						//把 代理商下的多有业务员 
    						businessIds.add(i, businessId);
    					}
    				}
    				
    			}
    		}
    		model.addObject("businessIds", businessIds);
    	}
    	return model;
    }
    /**
     * 下级代理列表
    * @Title: LowerAgentListInit 
    * @author : lcl
    * @time : 2017年6月15日 下午3:13:06
    * @return ModelAndView    返回类型 
    * @throws
     */
	@RequestMapping(value="/LowerAgentListInit")
    public ModelAndView LowerAgentListInit(CrmAgentParam param){
    	ModelAndView model  = new ModelAndView("system_operate/lower_agent");
    	//统计 全部去 有效 无效
    	param.setDelFlag(1);
		param.setAgentStatus(null); 
    	List<CrmAgentPO> list0 = this.iCrmAgentService.findLowerAgentList(param);
    	Map<String , Object>  map = new  HashMap<String, Object>();
    	//查询有效
    	param.setAgentStatus(1);
    	List<CrmAgentPO> list1 = this.iCrmAgentService.findLowerAgentList(param);
    	//查询无聊
    	param.setAgentStatus(0);
    	List<CrmAgentPO> list2 = this.iCrmAgentService.findLowerAgentList(param);
    	map.put("all", 0);
    	map.put("effective", 0);
    	map.put("invalid", 0);
    	if(CollectionUtils.isNotEmpty(list0)){
    		map.put("all", list0.size());
    	}
    	if(CollectionUtils.isNotEmpty(list1)){
    		map.put("effective", list1.size());
    	}
    	if(CollectionUtils.isNotEmpty(list2)){
    		map.put("invalid", list2.size());
    	}
    	model.addObject("map",map);
    	model.addObject("agentId", param.getAgentId());
    	
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		model.addObject("cSourcesVOs", cSourcesVOs);
    	return model;
    }
    
    /**
     * 查询下级代理商
    * @Title: getLowerAgentList 
    * @author : lcl
    * @time : 2017年6月15日 上午10:04:48
    * @return PageRespModel    返回类型 
    * @throws
     */
    @RequestMapping(value="/getLowerAgentList")
    @ResponseBody
    public  PageRespModel getLowerAgentList(CrmAgentParam param){
    /*	if(BeanUtils.isNotEmpty(param)&& param.getAgentStatus().equals(0)){
    		
    		return iCrmAgentService.getLowerAgentList(param);
    	}
    	if(BeanUtils.isNotEmpty(param) && param.getAgentStatus().equals(2)){
    		return iCrmAgentService.getLowerAgentList(param);
    	}
		param.setAgentStatus(1);//查询 有效的 
*/		return iCrmAgentService.getLowerAgentList(param);
    }
    /**
     * 分账明细 跳转页面
    * @Title: subsidiaryLedgerInit 
    * @author : lcl
    * @time : 2017年6月16日 下午4:26:15
    * @return ModelAndView    返回类型 
    * @throws
     */
    @RequestMapping(value="/subsidiaryLedgerInit")
    public ModelAndView  subsidiaryLedgerInit(CrmAgentParam param){
    	ModelAndView model = new ModelAndView("/system_operate/fashionable_detail");
    	model.addObject("agentId",param.getAgentId() );
    	return model;
    }
    /**
     * 代理商列表 分账明细 
    * @Title: subsidiaryLedger 
    * @author : lcl
    * @time : 2017年6月16日 下午3:46:14
    * @return PageRespModel    返回类型 
    * @throws
     */
    @RequestMapping(value="/subsidiaryLedger")
    @ResponseBody
    public PageRespModel subsidiaryLedger(CrmAgentParam param){
    	PageRespModel model = new PageRespModel();
    	Page<CrmAgentVO> pages = iCrmAgentService.queryPage(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    
	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmAgentParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmAgent/CrmAgent_Detail");
//		SplitAccountRolePO po = mainService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmAgentPO());
//			return view;
//		}
		return view;
	} 
	
	@RequestMapping(value="/excelOut")
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
			
		}
		if(crmAgentVOs!=null){
			LinkedHashMap<String, String> map =new LinkedHashMap<String, String>();
			map.put("id", "ID");
			map.put("name", "名称");
			map.put("agentNatureName", "性质");
			map.put("agentStatusName", "代理状态");
			map.put("areaName", "代理区域");
			map.put("businessName", "联系业务员");
			map.put("principalName", "联系联系人");
			map.put("mobilephoe", "联系人电话");
			try {
				ExcelUtil.writeXls(response, "我的代理商", map, crmAgentVOs, CrmAgentVO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	 
	/**
	 * 解约  续约 
	* @Title: updateStatus 
	* @author : lcl
	* @time : 2017年6月9日 下午3:50:11
	* @return CommResponse    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	public RespModel  updateStatus(@RequestBody CrmAgentParam crmAgentParam){//批量 或单个  不同类型
		List<Long> ids = crmAgentParam.getIds();
		if(BeanUtils.isEmpty(ids)){
			return  RespModel.failure("请选择要处理的数据！");
		}
		return iCrmAgentService.updateStatus(ids);
	}
	

	/**
	 * 
	 * @Title: editAgent 
	 * @Description: 编辑代理商 
	 * @param @param clientId
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月3日 上午10:20:04
	 */
	@RequestMapping(value="/editAgent")
	public ModelAndView editAgent(Long clientId){
		logger.info("#####CrmAgentController###editAgent##clientId:"+clientId);
		ModelAndView modelAndView = new ModelAndView("agent/edit_agent");
		
		//CrmAccountPO userPO = CrmControllerHelper.getSessionUser();//获取当前登录者信息
		Boolean flag = iCrmAgentService.editAgent(clientId,modelAndView);
		if(flag) return modelAndView;
		
		return modelAndView;
		
	}
	
	
	/**
	 * 
	 * @Title: saveAgentEditInfo 
	 * @Description: 更新代理商编辑信息 
	 * @param @param crmAgentParam
	 * @param @return  
	 * @return RespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月3日 上午11:17:22
	 */
	@RequestMapping(value="/updateAgentEditInfo")
	@ResponseBody
	public RespModel updateAgentEditInfo(CrmAgentParam crmAgentParam){
		logger.info("#####CrmAgentController###updateAgentEditInfo##crmAgentParam:"+crmAgentParam.toString());
		Long userId = CrmControllerHelper.getSessionUserId();//登录者Id
		RespModel respModel = new RespModel();
		try {
			//更新相关信息
			Boolean flag = iCrmAgentService.updateAgentEditInfo(crmAgentParam,userId,respModel);
			if(flag) return respModel;
		} catch (Exception e) {
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("出错了");
			logger.error("CrmAgentController:updateAgentEditInfo:" + e);
			return respModel;
		}
		
		return respModel;
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
	 * 保护时间  
	* @Title: getDateAddSwven 
	* @author : lcl
	* @time : 2017年7月12日 下午1:47:21
	* @return Date    返回类型 
	* @throws
	 */
	public Date getDateAddSeven(Date date){
		Calendar calendar = Calendar.getInstance();//获取时间对象
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		date = calendar.getTime();
		return date;
		
	}
	
	
	
	
}