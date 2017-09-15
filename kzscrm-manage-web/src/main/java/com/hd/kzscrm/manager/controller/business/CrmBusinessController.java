package com.hd.kzscrm.manager.controller.business;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
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
import com.hd.kzscrm.service.param.canteen.CrmCanteenApplyParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.perm.CrmPermRoleParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.param.perm.CrmPermUserRoleParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmPositionService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenApplyService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.MD5;
import com.hd.wolverine.util.ParamMap;


/**
 * crmBusiness CRMBUSINESS
 * @author system code gen
 * 业务员列表
 */
@Controller
@RequestMapping("/crmbusiness")
public class CrmBusinessController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessController.class);
    @Autowired
    ICrmBusinessService mainService;
    @Autowired
    ICrmPositionService positionService;
    @Autowired
    ICrmTeamService teamService;
    @Autowired
    ICrmClientResourceService clientResourceService;
    /**
     * 团队表
     */
    @Autowired
    ICrmTeamService crmTeamService;
    @Resource
    private ICrmBusinessService iCrmBusinessService;
    /**
     * 团队
     */
    @Autowired
    private ICrmTeamService iCrmTeamService;
    /**
     * 用户表
     */
    @Autowired
    ICrmUserService userService;
	@Resource
    ICrmPermUserRoleService iCrmPermUserRoleService;
	@Autowired
	ICrmPermRoleSourcesService roleSourcesService;
	@Resource
    private ICrmPermRoleService iCrmPermRoleService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
    ICrmPermSourcesService  pSourcesService;
	
	@Resource
	private ICrmAgentService icrmAgentService;
	@Resource
	private ICrmAgentApplyService iCrmAgentApplyService;
	@Resource
	private ICrmCanteenBaseInfoService icrmCanteenBaseInfoService;
	@Resource
	private ICrmCanteenApplyService iCrmCanteenApplyService;
     
    /**
     ** 预留实现
     */
    public void initControler(HttpServletRequest request, Map<String, Object> map) {
     
    }
    /**
     * 根据参数，获取数据
     * @author 黄霄仪
     * @date 2017年7月26日 下午8:08:17
     */
    @RequestMapping("/getBusiness")
    @ResponseBody
    public RespModel getBusiness(@RequestBody CrmBusinessParam crmBusinessParam){
    	List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.listByParam(crmBusinessParam);
    	RespModel success = RespModel.success("成功");
    	success.setRows(crmBusinessPOs);
		return success;
    }
    
    /**
     *
     * 初始化
     * @param param
     * @return
     */
    @RequestMapping("/init")
    @ResponseBody
    public ModelAndView init(CrmTeamParam param) {

   
    	ModelAndView view = new ModelAndView("/team_management/salesman_list");
    	ParamMap paramMap =  new ParamMap();

    	List<CrmTeamPO>  crmTeamPOs = crmTeamService.findAll(param);
    	

    	view.addObject("crmTeamPOs", crmTeamPOs);
    	CrmBusinessParam param2 = new CrmBusinessParam();
    	Page<CrmBusinessVO> pages = mainService.queryPageList(param2);
    	int num = pages.getTotalResult();
    	param2.setJobStatus(0);//离职
    	Page<CrmBusinessVO> pages2 = mainService.queryPageList(param2);
    	int num2 = pages2.getTotalResult();
    	param2.setJobStatus(1);//在职
    	Page<CrmBusinessVO> pages3 = mainService.queryPageList(param2);
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
    	view.addObject("userType",CrmControllerHelper.getSessionUserType());
    	//菜单
    	view.addObject("active","CrmBusiness");
    	view.addObject("agentId", param.getAgentId());
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getAgentId())){
    		view.addObject("agentId", param.getAgentId());
    	}
    	//行业
    	List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
		return view;
    }
    
    @RequestMapping("/myInit")
    @ResponseBody
    public ModelAndView myInit() {
    	ModelAndView view = new ModelAndView("/my_team/my_salesman");
    	ParamMap paramMap =  new ParamMap();
    	CrmTeamParam param = new CrmTeamParam();
    	List<CrmTeamPO>  crmTeamPOs = crmTeamService.findAll(param);
    	view.addObject("crmTeamPOs", crmTeamPOs);
    	
    	CrmBusinessParam param2 = new CrmBusinessParam();
    	param2.setUserId(SystemControllerHelper.getSessionUserId());
    	Page<CrmBusinessVO> pages = mainService.queryPageList(param2);
    	int num =pages.getTotalResult();
    	param2.setJobStatus(0);//离职
    	Page<CrmBusinessVO> pages2 = mainService.queryPageList(param2);
    	int num2 =pages.getTotalResult();
    	param2.setJobStatus(1);//在职
    	Page<CrmBusinessVO> pages3 = mainService.queryPageList(param2);
    	int num1 =pages.getTotalResult();
    	Map<String, Object> map = new HashMap<>();
    	map.put("all", 0);
    	map.put("onjob", 0);
    	map.put("quitjob", 0);
    	if(pages.result.size()>0){//全部
    		map.put("all", num);
    	}
    	if(pages2.result.size()>0){
    		map.put("quitjob",num2);
    	}
    	if(pages3.result.size()>0){
    		map.put("onjob", num1);
    	}
    	view.addObject("map", map);
    	//菜单
    	view.addObject("active","CrmBusiness");
    	//行业
    	List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		view.addObject("cSourcesVOs", cSourcesVOs);
    	view.addObject("userId", SystemControllerHelper.getSessionUserId());
    	return view;
    }
    
    
    /**
     * 新增页面初始化
    * @Title: addInit 
    * @author : lcl
    * @time : 2017年5月26日 上午9:57:34
    * @return ModelAndView    返回类型 
    * @throws
     */
    @RequestMapping(value="/addInit")
    public ModelAndView addInit(CrmBusinessParam param){
    	ModelAndView model = new  ModelAndView("/index/add_salesman");
    	//查询全部岗位
    	Long agentId=null;
    	if(CrmControllerHelper.getSessionUserType()==CrmAccountUserType.AGENT.getCode()){
    		agentId=CrmControllerHelper.getSessionAgentUser().getId();
    	}
    	//查询全部岗位
    	List<CrmPositionPO> crmPositionPOs =  positionService.findByTypeAndAgentId(CrmControllerHelper.getSessionType(),agentId);//查询全部岗位
//    	List<CrmPositionPO> crmPositionPOs =  this.positionService.findAll();//查询全部岗位
    	CrmTeamParam param3 = new CrmTeamParam();
    	List<CrmTeamPO> crmTeamPOs = crmTeamService.findAll(param3);
    	//根据businessId查询对象
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getBusinessId())){
    		Long businessId = param.getBusinessId();
    		CrmBusinessPO cBusinessPO = this.mainService.findByBusinessId(businessId);
    		if(BeanUtils.isNotEmpty(cBusinessPO)){
    			CrmBusinessVO crmBusinessVO=BeanConvertor.copy(cBusinessPO, CrmBusinessVO.class);
    			CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(cBusinessPO.getUserId());
    			if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
    				crmBusinessVO.setRoleId(crmPermUserRolePO.getRoleId());
    			}
    			model.addObject("cBusinessVO", crmBusinessVO);
    		}
    	}
    	List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
    	
    	//查看角色，并且删除管理员和代理商的角色
    	List<CrmPermRolePO> crmPermRolePOs = iCrmPermRoleService.listByParam(new CrmPermRoleParam());
    	for (Iterator<CrmPermRolePO> iterator = crmPermRolePOs.iterator(); iterator.hasNext();) {
			CrmPermRolePO crmPermRolePO = iterator.next();
			Integer userType = crmPermRolePO.getUserType();
			if(userType.equals(1)||userType.equals(2)){
				iterator.remove();
			}
		}
    	model.addObject("crmPermRolePOs", crmPermRolePOs);
    	model.addObject("cSourcesVOs", cSourcesVOs);
    	model.addObject("crmPositionPOs", crmPositionPOs);
    	model.addObject("crmTeamPOs", crmTeamPOs);
    	return model;
    }
    
    /**
     * 查看页面
     */
    @RequestMapping(value="/viewInit")
    public ModelAndView viewInit(CrmBusinessParam param){
    	ModelAndView model = new  ModelAndView("/index/view_salesman");
    	Long agentId=null;
    	if(CrmControllerHelper.getSessionUserType()==CrmAccountUserType.AGENT.getCode()){
    		agentId=CrmControllerHelper.getSessionAgentUser().getId();
    	}
    	//查询全部岗位
    	List<CrmPositionPO> crmPositionPOs =  positionService.findByTypeAndAgentId(CrmControllerHelper.getSessionType(),agentId);//查询全部岗位
    	CrmTeamParam param3 = new CrmTeamParam();
    	List<CrmTeamPO> crmTeamPOs = crmTeamService.findAll(param3);
    	//是否有业务经理
    	boolean isBusinessManager=false;
    	Integer currentBusinessUserType=3;//默认业务员
    	//根据businessId查询对象
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getBusinessId())){
    		Long businessId = param.getBusinessId();
    		CrmBusinessPO cBusinessPO = this.mainService.findByBusinessId(businessId);
    		Long positionId = cBusinessPO.getPositionId();//岗位id
    		Long teamId = cBusinessPO.getTeamId();//团队id
    		if(BeanUtils.isNotEmpty(cBusinessPO)){
    			Integer userType = cBusinessPO.getUserType();
				currentBusinessUserType=userType==1?3:4;
    			List<CrmBusinessPO> businessManagers = iCrmBusinessService.findByTeamIdAndUserType(teamId, 2);
    			if(BeanUtils.isNotEmpty(businessManagers)){
    				isBusinessManager=true;
    			}
    			CrmBusinessVO crmBusinessVO=BeanConvertor.copy(cBusinessPO, CrmBusinessVO.class);
    			CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(cBusinessPO.getUserId());
    			if(BeanUtils.isNotEmpty(positionId)){
    				CrmPositionPO crmPositionPO = positionService.getById(positionId);
    				if(BeanUtils.isNotEmpty(crmPositionPO)){
    					model.addObject("crmPositionPO", crmPositionPO);
    				}
    			}
    			if(BeanUtils.isNotEmpty(teamId)){
    				CrmTeamPO teamPO = teamService.getById(teamId);
    				if(BeanUtils.isNotEmpty(teamPO)){
    					model.addObject("teamPO", teamPO);
    				}
    			}
    			if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
    				crmBusinessVO.setRoleId(crmPermUserRolePO.getRoleId());
    			}
    			
    			model.addObject("cBusinessVO", crmBusinessVO);
    		}
    	}
    	List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
    	
    	//查看角色，并且删除管理员和代理商的角色
    	List<CrmPermRolePO> crmPermRolePOs = iCrmPermRoleService.listByParam(new CrmPermRoleParam());
    	for (Iterator<CrmPermRolePO> iterator = crmPermRolePOs.iterator(); iterator.hasNext();) {
			CrmPermRolePO crmPermRolePO = iterator.next();
			Integer userType = crmPermRolePO.getUserType();
			//管理员和代理商删除，还有就是当遍历到业务经理时，如果当前编辑的是业务员，并且该团队有业务经理时，删除业务经理
			if(userType.equals(1)||userType.equals(2)||(isBusinessManager&&userType==4&&currentBusinessUserType==3)){
				iterator.remove();
			}
		}
    	model.addObject("crmPermRolePOs", crmPermRolePOs);
    	model.addObject("cSourcesVOs", cSourcesVOs);
    	model.addObject("crmPositionPOs", crmPositionPOs);
    	model.addObject("crmTeamPOs", crmTeamPOs);
    	return model;
    }
    @RequestMapping(value="/seeInit")
    public ModelAndView seeInit(CrmBusinessParam param){
    	ModelAndView model = new  ModelAndView("/team_management/business_list_look");
    	//查询全部岗位
    	List<CrmPositionPO> crmPositionPOs =  this.positionService.findAll();//查询全部岗位
    	CrmTeamParam param3 = new CrmTeamParam();
    	List<CrmTeamPO> crmTeamPOs = crmTeamService.findAll(param3);
    	//根据businessId查询对象
    	if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getBusinessId())){
    		Long businessId = param.getBusinessId();
    		CrmBusinessPO cBusinessPO = this.mainService.findByBusinessId(businessId);
    		if(BeanUtils.isNotEmpty(cBusinessPO)){
    			Long positionId = cBusinessPO.getPositionId();//岗位id
    			Long teamId = cBusinessPO.getTeamId();//团队id
    			CrmBusinessVO crmBusinessVO=BeanConvertor.copy(cBusinessPO, CrmBusinessVO.class);
    			CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(cBusinessPO.getUserId());
    			if(BeanUtils.isNotEmpty(crmBusinessVO.getJobStatus()) && crmBusinessVO.getJobStatus()==1){
    				crmBusinessVO.setJobStatusName("在职");
    			}else{
    				crmBusinessVO.setJobStatusName("离职");
    			}
    			if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
    				crmBusinessVO.setRoleId(crmPermUserRolePO.getRoleId());
    			}
    			if(BeanUtils.isNotEmpty(positionId)){
    				CrmPositionPO crmPositionPO = positionService.getById(positionId);
    				if(BeanUtils.isNotEmpty(crmPositionPO)){
    					model.addObject("position", crmPositionPO);
    				}
    			}
    			if(BeanUtils.isNotEmpty(teamId)){
    				CrmTeamPO teamPO = teamService.getById(teamId);
    				if(BeanUtils.isNotEmpty(teamPO)){
    					model.addObject("team", teamPO);
    				}
    			}
    			
    			
    			model.addObject("cBusinessVO", crmBusinessVO);
    		}
    	}
    	List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
    	
    	//查看角色，并且删除管理员和代理商的角色
    	List<CrmPermRolePO> crmPermRolePOs = iCrmPermRoleService.listByParam(new CrmPermRoleParam());
    	for (Iterator<CrmPermRolePO> iterator = crmPermRolePOs.iterator(); iterator.hasNext();) {
    		CrmPermRolePO crmPermRolePO = iterator.next();
    		Integer userType = crmPermRolePO.getUserType();
    		if(userType.equals(1)||userType.equals(2)){
    			iterator.remove();
    		}
    	}
    	model.addObject("crmPermRolePOs", crmPermRolePOs);
    	model.addObject("cSourcesVOs", cSourcesVOs);
    	model.addObject("crmPositionPOs", crmPositionPOs);
    	model.addObject("crmTeamPOs", crmTeamPOs);
    	return model;
    }
    
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmBusiness/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmBusiness(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmbusiness/mainAddOrEditCrmBusiness");
         CrmBusinessParam CrmBusinessparam = new CrmBusinessParam();
         CrmBusinessPO po = mainService.get(CrmBusinessPO.class,id );
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
			mainService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    /**
     * 修改或新增业务员
     * @author 黄霄仪
     * @date 2017年8月1日 下午2:43:47
     */
    @RequestMapping(value = "/doSave")
    @ResponseBody
    public RespModel doSave(CrmBusinessParam param, HttpServletRequest request) {
    	Long roleId = param.getRoleId();
    	Long teamId = param.getTeamId();
    	Long businessId=param.getBusinessId();//如果不为空，就是更新
    	CrmTeamPO crmTeamPO = iCrmTeamService.get(CrmTeamPO.class, teamId);
    	Long parentId = crmTeamPO.getParentId();//所属团队的父团队
    	Long teamIdTemp = crmTeamPO.getId();
    	CrmPermRolePO crmPermRolePO = iCrmPermRoleService.get(CrmPermRolePO.class, roleId);
    	Integer userTypeTemp = crmPermRolePO.getUserType();//角色类型
    	Integer businessUserType=1;//业务员类型
    	if(userTypeTemp==4){
    		businessUserType=2;//业务经理
    	}
    	//只有业务员和业务经理可以用
    	if(!(userTypeTemp==3||userTypeTemp==4)){
    		return RespModel.failure("只能选择业务员和业务");
    	}
    	
    	//查找领导团队中的业务经理
    	CrmBusinessParam crmBusinessParam=new CrmBusinessParam();    	
    	crmBusinessParam.setUserType(2);
		//如果是业务经理,查找上级的领导,否则查找当前团队的领导
		if(userTypeTemp==4){
			Assert.notNull(parentId,"父ID不能为空");
			crmBusinessParam.setTeamId(parentId);
		}else{
			//找到业务经理
			crmBusinessParam.setTeamId(teamIdTemp);
		}
		
    	//查找领导
    	List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.listByParam(crmBusinessParam);
    	if(crmBusinessPOs.size()>1){
    		return RespModel.failure("有多个领导");
    	}
    	//如果是添加业务经理，并且没有领导，并且父ID与当前归属的经理不一致，则提醒要添加父团队的业务经理
    	/*if(userTypeTemp==4&&crmBusinessPOs.size()==0&&parentId!=teamIdTemp){
    		CrmTeamPO parentCrmTeamPO = iCrmTeamService.get(CrmTeamPO.class, parentId);
    		return RespModel.failure(parentCrmTeamPO.getName()+"团队没有指定业务经理");
    	}else if(userTypeTemp==3&&crmBusinessPOs.size()==0){
    		return RespModel.failure(crmTeamPO.getName()+"团队没有指定业务经理");
    	}*/
    	
    	CrmBusinessPO parentCrmBusinessPO = crmBusinessPOs.size()==1?crmBusinessPOs.get(0):null;
    	String mobilephone = param.getMobilephone();
    
    	Integer userType = CrmControllerHelper.getSessionUserType();
    	Long createId = CrmControllerHelper.getSessionUserId();
    	Date currentDate=new Date();
        RespModel res = RespModel.success("添加业务员信息成功!并赋与了默认权限");
        CrmUserPO crmUserPOTemp = userService.findByAccountAndUserType(mobilephone,CrmAccountUserType.BUSINESS.getCode());
        //如果不为空，并且已修改的帐号和数据库中的不一致，就判断该帐号已存在
        if(BeanUtils.isNotEmpty(crmUserPOTemp)&& !crmUserPOTemp.getAccount().equals(mobilephone)){
        	return RespModel.failure("该业务员帐号已存在");
        }
        //更新
        /*if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getBusinessId())){
        	res = doUpdate(param, request);
        	return res;
        }*/
        

        //修改页面员
    	if(BeanUtils.isNotEmpty(businessId)){
    		CrmBusinessPO crmBusinessPOTemp=iCrmBusinessService.get(CrmBusinessPO.class, businessId);
    		String mobilephoneTemp = crmBusinessPOTemp.getMobilephone();
    		if(!mobilephoneTemp.equals(mobilephone)){
    			CrmBusinessPO crmBusinessPO = mainService.findByMobilPhone(mobilephone);
            	if(BeanUtils.isNotEmpty(mobilephone) && BeanUtils.isNotEmpty(crmBusinessPO)){
            		return RespModel.failure("手机号已存在");
            	}
    		}
    		crmBusinessPOTemp.setUserType(businessUserType);
    		crmBusinessPOTemp.setName(param.getName());
    		crmBusinessPOTemp.setWeixin(param.getWeixin());
    		crmBusinessPOTemp.setHeadIdCard(param.getHeadIdcard());
    		crmBusinessPOTemp.setTeamId(param.getTeamId());
    		crmBusinessPOTemp.setPositionId(param.getPositionId());
    		crmBusinessPOTemp.setMobilephone(param.getMobilephone());
    		if(BeanUtils.isNotEmpty(parentCrmBusinessPO)){
    			crmBusinessPOTemp.setParentId(parentCrmBusinessPO.getId());
    		}
    		crmBusinessPOTemp.setUpdateId(createId);
    		crmBusinessPOTemp.setUpdateTime(currentDate);
    		CrmUserPO crmUserPO = userService.get(CrmUserPO.class, crmBusinessPOTemp.getUserId());
    		crmUserPO.setMobilephone(mobilephoneTemp);
    		crmUserPO.setAccount(mobilephoneTemp);
    		crmUserPO.setUserName(crmBusinessPOTemp.getName());
    		crmUserPO.setUserType(userTypeTemp);
    		crmUserPO.setUpdateId(createId);
    		crmUserPO.setUpdateTime(currentDate);
    		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(crmBusinessPOTemp.getUserId());
    		if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
    			crmPermUserRolePO.setRoleId(roleId);
    			iCrmPermUserRoleService.update(crmPermUserRolePO);
    		}else{
    			CrmPermUserRoleParam crmPermUserRoleParam=new CrmPermUserRoleParam();
    			crmPermUserRoleParam.setRoleId(roleId);
    			crmPermUserRoleParam.setUserId(crmBusinessPOTemp.getUserId());
    			iCrmPermUserRoleService.saveEntity(crmPermUserRoleParam);
    		}
    		userService.update(crmUserPO);
    		iCrmBusinessService.update(crmBusinessPOTemp);
    	}else{
    		CrmBusinessPO crmBusinessPO = mainService.findByMobilPhone(mobilephone);
        	if(BeanUtils.isNotEmpty(mobilephone) && BeanUtils.isNotEmpty(crmBusinessPO)){
        		return RespModel.failure("手机号已存在");
        	}
    		businessId = SystemControllerHelper.genNextIDValue(DatabaseTableNameEnum.crm_business);
    		param.setId(businessId);//id
    		if(BeanUtils.isNotEmpty(parentCrmBusinessPO)){
    			param.setParentId(parentCrmBusinessPO.getId());
    		}else{
    			param.setParentId(businessId);
    		}
    		param.setDelFlag(1);
    		param.setJobStatus(1);//默认为在职员工
    		param.setPassword(MD5.md5("123456"));//默认密码
    		param.setCreateTime(new Date());//创建时间
    		param.setWorkTime(new Date());
    		param.setAuthenticationStatus(2);//认证状态 0申请中 1已认证 2认证通过3认证失败
    		param.setCreateTime(currentDate);
    		param.setUpdateTime(currentDate);
    		param.setRegisterTime(currentDate);
    		param.setCreateId(createId);
    		param.setUserType(businessUserType);
    		param.setUpdateId(createId);
    		param.setType(CrmControllerHelper.getSessionType());
    		if(userType==CrmAccountUserType.AGENT.getCode()){
    			CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
    			if(BeanUtils.isNotEmpty(sessionAgentUser)){
    				param.setAgentId(sessionAgentUser.getId());
    			}
    		}
    		//crm_user表 添加记录 用于登录
    		CrmUserPO crmUserPO = new  CrmUserPO();
    		Long userId = SystemControllerHelper.genNextIDValue(DatabaseTableNameEnum.crm_user);
    		crmUserPO.setId(userId);
    		param.setUserId(userId);//业务员表中的user_ID
    		crmUserPO.setUserName(param.getName());
    		crmUserPO.setPassword(MD5.md5("123456"));//待修改 
    		crmUserPO.setMobilephone(mobilephone);//可能用于登录
    		crmUserPO.setUserType(userTypeTemp);
    		crmUserPO.setUserStatus(1);//状态为正常1 
    		crmUserPO.setDelFlag(1);
    		crmUserPO.setAuthenticationStatus(1);//已认证
    		crmUserPO.setType(CrmControllerHelper.getSessionType());
    		crmUserPO.setAccount(mobilephone);
    		crmUserPO.setCreateTime(currentDate);
    		crmUserPO.setUpdateTime(currentDate);
    		crmUserPO.setCreateId(createId);
    		crmUserPO.setUpdateId(createId);
    		CrmPermUserRoleParam crmPermUserRoleParam=new CrmPermUserRoleParam();
    		crmPermUserRoleParam.setUserId(userId);
    		crmUserPO.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
    		/*if(BeanUtils.isEmpty(roleId)){
    			List<CrmPermRolePO> crmPermRolePOs = iCrmPermRoleService.findByUserType(CrmAccountUserType.BUSINESS.getCode());
    			
    			if(BeanUtils.isNotEmpty(crmPermRolePOs)){
    				roleId=crmPermRolePOs.get(0).getId();
    			}else{
    				res.setDesc("没有找到业务员角色配置信息，请先添加角色信息后，进行分配后登录使用");
    			}
    		}*/
    		crmPermUserRoleParam.setRoleId(roleId);
    		iCrmPermUserRoleService.saveEntity(crmPermUserRoleParam);
    		userService.save(crmUserPO);//保存业务员的userId
    		CrmBusinessPO po=BeanConvertor.convertBean(param, CrmBusinessPO.class);
    		mainService.saveEntity(po);
    	}
        return res;
    }
    /**
     * 修改状态
    * @Title: doUpdate 
    * @author : lcl
    * @time : 2017年6月19日 下午8:03:41
    * @return RespModel    返回类型 
    * @throws
     */
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmBusinessParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       param.setId(param.getBusinessId());
       Long teamId = param.getTeamId();
       Long roleId = param.getRoleId();
       Assert.notNull(teamId, "团队不能为空");
       CrmBusinessPO cBusinessPO = mainService.findByBusinessId(param.getBusinessId());//对比的基础
       CrmBusinessPO businessPOs = mainService.findByMobilPhone(param.getMobilephone());
//       List<String> mobilephones = new ArrayList<String>();
//       if(CollectionUtils.isNotEmpty(businessPOs)){
//    	   for(CrmBusinessPO c : businessPOs){
//    		   mobilephones.add(c.getMobilephone());
//    	   }
//       }
       if(BeanUtils.isNotEmpty(cBusinessPO) && BeanUtils.isNotEmpty(businessPOs)){
    	   //如果查到是一个对象则可以修改 否则
    	   if(!(cBusinessPO.getId().equals(businessPOs.getId())) && param.getMobilephone().equals(businessPOs.getMobilephone())){
    		     res.setDesc("手机号已存在！");
    		     res.setCode(-4);//已存在
    		     return res;
    	   }
       }
       CrmPermRolePO crmPermRolePO = iCrmPermRoleService.get(CrmPermRolePO.class, roleId);
       Integer userType=1;//业务员
       if(crmPermRolePO.getUserType()==CrmAccountUserType.BUSINESS_MANAGER.getCode()){
    	   userType=2;//业务经理
    	   //如果相等就不用判断是否有业务经理
    	   if(cBusinessPO.getUserType()!=userType){
    		   List<CrmBusinessPO> findByTeamIdAndUserType = mainService.findByTeamIdAndUserType(teamId,userType);
    		   if(BeanUtils.isNotEmpty(findByTeamIdAndUserType)){
    			   return RespModel.failure("已存在业务经理");
    		   }
    	   }
       }
       CrmUserPO crmUserPO = userService.get(CrmUserPO.class, cBusinessPO.getUserId());
       crmUserPO.setUserType(userType==1?3:4);
       crmUserPO.setUpdateTime(new Date());
       crmUserPO.setUpdateId(CrmControllerHelper.getSessionUserId());
       userService.update(crmUserPO);
       CrmPermUserRolePO crmPermUserRolePO = this.iCrmPermUserRoleService.findByUserId(cBusinessPO.getUserId());
       crmPermUserRolePO.setRoleId(roleId);
       this.iCrmPermUserRoleService.update(crmPermUserRolePO);
       
       CrmBusinessPO po=BeanConvertor.convertBean(param, CrmBusinessPO.class);
       po.setUserType(userType);
       mainService.updateEntity(po);
       //更新团队业务员关系
       mainService.updateTeamBusinessRelation(teamId);
       //更新业务员和代理的team_id
       mainService.updateBusinessAgentRelationTeamId(po,teamId);
       res.setCode(0);//成功
       res.setDesc("编辑成功");
       return res;
    }
    
    /**
     * 解雇
    * @Title: updataStatus 
    * @author : lcl
    * @time : 2017年6月19日 下午3:26:16
    * @return RespModel    返回类型 
    * @throws
     */
    @RequestMapping(value = "/updataStatus")
    @ResponseBody
    public RespModel updataStatus0(CrmBusinessParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       Long id = param.getBusinessId();
       param.setId(id);
       param.setJobStatus(0);//修改在职状态 0 离职 1 在职
       CrmBusinessPO po=BeanConvertor.convertBean(param, CrmBusinessPO.class);
       mainService.updateEntity(po);
       
       //解雇以后此业务员
       //正式客户业务员制空
       CrmClientResourceParam crmclientresourceParam = new CrmClientResourceParam(); 
       crmclientresourceParam.setBusinessId(id);
       crmclientresourceParam.setClientNature(3);
       List<CrmClientResourcePO> clientResourcePOs = clientResourceService.listByParam(crmclientresourceParam);
       if(BeanUtils.isNotEmpty(clientResourcePOs)){
    	   for(CrmClientResourcePO cPo : clientResourcePOs) {
    		   CrmClientResourceParam cParam = new CrmClientResourceParam();
    		   cParam.setId(cPo.getId());
    		   cParam.setBusinessId(null);
    		   cParam.setBusinessIdNull(1L);
        	   clientResourceService.commonUpdate(cParam);
    	   }
       }
       //保护客户修改为散客
       crmclientresourceParam.setClientNature(2);
       clientResourcePOs = clientResourceService.listByParam(crmclientresourceParam);
       if(BeanUtils.isNotEmpty(clientResourcePOs)){
    	   for(CrmClientResourcePO cPo : clientResourcePOs) {
    		   CrmClientResourceParam cParam = new CrmClientResourceParam();
    		   cParam.setId(cPo.getId());
    		   cParam.setClientNature(1);//设置为散客
    		   cParam.setLastProtectBusinessId(cPo.getBusinessId());//上一次保护关联的业务员Id
    		   cParam.setRelieveProtectTime(new Date());//解除保护关系(踢出,成为散客)时间
    		   clientResourceService.changeProtectedClientToIndividualTraveler(cParam);
    		   
    		   
    		   Integer clientType = cPo.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
    			Long agentCanteenId = cPo.getAgentCanteenId();
    			if(1 == clientType){//代理商
    				CrmAgentParam crmAgentParam = new CrmAgentParam();
    				crmAgentParam.setId(agentCanteenId);
    				crmAgentParam.setAgentStatus(0);
    				crmAgentParam.setJuniorDivide(0);
    				logger.info("#####CrmClientResourceServiceImpl###kickOutClient##更新crmAgentParam:"+crmAgentParam.toString());
    				icrmAgentService.changeProtectedClientToIndividualTraveler(crmAgentParam);
    				
    				//如存在申请信息,则删除
    				CrmAgentApplyParam crmAgentApplyParam = new CrmAgentApplyParam();
    				crmAgentApplyParam.setAgentId(agentCanteenId);
    				crmAgentApplyParam.setDelFlag(0);
    				logger.info("#####CrmClientResourceServiceImpl###kickOutClient##删除crmAgentApplyParam:"+crmAgentApplyParam.toString());
    				iCrmAgentApplyService.deleteAgentApplyEntity(crmAgentApplyParam);

    				
    			}else{//食堂
    				CrmCanteenBaseInfoParam crmCanteenBaseInfoParam = new CrmCanteenBaseInfoParam();
    				crmCanteenBaseInfoParam.setId(agentCanteenId);
    				crmCanteenBaseInfoParam.setUpdaterUid(CrmControllerHelper.getSessionUserId());
    				crmCanteenBaseInfoParam.setUpdateTime(new Date());
    				crmCanteenBaseInfoParam.setClientNature(1);//客户性质	1.散客，2.保护客户，3.正式客户
    				logger.info("#####CrmClientResourceServiceImpl###kickOutClient##更新crmCanteenBaseInfoParam:"+crmCanteenBaseInfoParam.toString());
    				icrmCanteenBaseInfoService.changeProtectedClientToIndividualTraveler(crmCanteenBaseInfoParam);
    				
    				//如存在申请信息,则删除
    				CrmCanteenApplyParam crmCanteenApplyParam = new CrmCanteenApplyParam();
    				crmCanteenApplyParam.setCanteenId(agentCanteenId);
    				crmCanteenApplyParam.setDelFlag(0);
    				logger.info("#####CrmClientResourceServiceImpl###kickOutClient##删除crmCanteenApplyParam:"+crmCanteenApplyParam.toString());
    				iCrmCanteenApplyService.deleteCanteenApplyEntity(crmCanteenApplyParam);
    				
    			}
    	   }
       }
       
       
       return res;
    }
    
    /**
     * 入职
    * @Title: updataStatus 
    * @author : lcl
    * @time : 2017年6月19日 下午3:26:16
    * @return RespModel    返回类型 
    * @throws
     */
    @RequestMapping(value = "/updataStatus1")
    @ResponseBody
    public RespModel updataStatus1(CrmBusinessParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       param.setId(param.getBusinessId());
       param.setJobStatus(1);//修改在职状态 0 离职 1 在职（入职）
       CrmBusinessPO po=BeanConvertor.convertBean(param, CrmBusinessPO.class);
       mainService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmBusinessParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
    	CrmAccountPO  accountPO =SystemControllerHelper.getSessionUser(); 
    	if(BeanUtils.isNotEmpty(accountPO) && accountPO.getUserType()!=1){
    		Long userId = accountPO.getId();
    		param.setUserId(userId);
    	}
    	Page<CrmBusinessVO> pages = mainService.queryPageList(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    
		
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmBusinessParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmBusiness/CrmBusiness_Detail");
//		SplitAccountRolePO po = mainService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmBusinessPO());
//			return view;
//		}
		return view;
	} 
	
	@RequestMapping(value="/excelOut")
	public void excelOut(HttpServletResponse response ,CrmBusinessParam param){
		Page<CrmBusinessVO> pages = mainService.queryPageList(param);
		List<CrmBusinessVO>  businessVOs = pages.result;
		Iterator<CrmBusinessVO> oIterator = businessVOs.iterator();
		while(oIterator.hasNext()){
			CrmBusinessVO cBusinessVO = oIterator.next();//获取值
				if(BeanUtils.isNotEmpty(cBusinessVO.getJobStatus())){
					if(cBusinessVO.getJobStatus().equals(1)){
						cBusinessVO.setJobStatusName("在职");
					}else if(cBusinessVO.getJobStatus().equals(0)){
						cBusinessVO.setJobStatusName("离职");
					}
			}
		}
		if(businessVOs!=null){
			LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();
			map.put("id", "ID");
			map.put("name", "姓名");
			map.put("workTime", "入职时间");
			map.put("teamName", "所属团队");
			map.put("positionName", "岗位职务");
			map.put("jobStatusName", "职业状态");
			map.put("mobilephone", "联系电话");
			map.put("weixin", "微信号码");
			try {
				ExcelUtil.writeXls(response, "业务员列表", map, businessVOs, CrmBusinessVO.class);
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
	
	@RequestMapping(value="/findPhoneIsExist")
	@ResponseBody
	public RespModel findPhoneIsExist(CrmBusinessParam param){
		RespModel res = new RespModel();
		if(BeanUtils.isNotEmpty(param) && BeanUtils.isNotEmpty(param.getMobilephone())){
			CrmBusinessPO crmBusinessPO = mainService.findByMobilPhone(param.getMobilephone());
			if(BeanUtils.isNotEmpty(crmBusinessPO)){
				res.setRows("手机号已存在");
				res.setCode(0);
				return res;
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @Title: salesmanListInit 
	 * @Description: 业务员列表初始化 
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月31日 下午5:42:01
	 */
	@RequestMapping(value="/salesmanListInit")
	public ModelAndView salesmanListInit(){
		ModelAndView modelAndView = new ModelAndView("/business_management/salesman_list");
		CrmAccountPO accountPO = CrmControllerHelper.getSessionUser();
		iCrmBusinessService.salesmanListInit(accountPO,modelAndView);
		List<CrmPermSourcesVO> cSourcesVOs = getPermSourceVOs();
		modelAndView.addObject("cSourcesVOs", cSourcesVOs);
		
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: occupationStatus 
	 * @Description: 统计职业状态 
	 * @param @return  
	 * @return RespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年8月1日 上午9:33:22
	 */
	@RequestMapping(value = "/occupationStatus")
    @ResponseBody
    public RespModel occupationStatus() {
       RespModel respModel = new RespModel();
       Integer userType = CrmControllerHelper.getSessionUserType();
       if(1 == userType){
    	   CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
    	   crmBusinessParam.setType(1);//业务员类型，1.平台业务员，2.代理商业务员
    	   crmBusinessParam.setDelFlag(1);
    	   Map<Integer,Integer> occupationStatusWithNum = iCrmBusinessService.countOccupationStatus(crmBusinessParam);
    	   
    	   respModel.setRows(occupationStatusWithNum);
       }
       return respModel;
    }
	
	/**
	 * 
	 * @Title: salesmanListDetail 
	 * @Description: 业务员列表详情 
	 * @param @param param
	 * @param @return  
	 * @return PageRespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年8月1日 上午11:03:35
	 */
	@RequestMapping("/salesmanListDetail")
    @ResponseBody
    public PageRespModel salesmanListDetail(CrmBusinessParam crmBusinessParam){
    	PageRespModel pageRespModel = new PageRespModel();
    	CrmAccountPO userPO = CrmControllerHelper.getSessionUser();
    	Boolean flag = iCrmBusinessService.salesmanListDetail(crmBusinessParam,userPO,pageRespModel);
    	if(flag) return pageRespModel;
    	
    	return pageRespModel;
    }
}