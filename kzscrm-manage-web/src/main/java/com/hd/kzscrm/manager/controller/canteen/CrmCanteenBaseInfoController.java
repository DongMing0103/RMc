package com.hd.kzscrm.manager.controller.canteen;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenExtInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenBaseInfoVO;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenExtInfoVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmCanteenBaseInfo CRMCANTEENBASE情報
 * @author system code gen
 * 食堂基本信息表
 */
@Controller
@RequestMapping("/crmcanteenbaseinfo")
public class CrmCanteenBaseInfoController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmCanteenBaseInfoController.class);
    @Autowired
    ICrmCanteenBaseInfoService iCrmCanteenBaseInfoService;
    /**
     * 代理商
     */
    @Resource
    private ICrmAgentService iCrmAgentService;
    /**
     * 业务员
     */
    @Resource
    private ICrmBusinessService iCrmBusinessService;
    
    @Resource
    private ICrmTeamService iCrmTeamService;
    
    @Resource
    private ICrmClientResourceService crmClientResourceService;
    //商家扩展表
    @Resource
    private ICrmCanteenExtInfoService canteenExtInfoService;
    
    @Value("${img.view.address}")
    private String imgViewAddress;
     
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
     	
    	ModelAndView view = new ModelAndView("/crmcanteenbaseinfo/mainCrmCanteenBaseInfo");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmCanteenBaseInfo");
    	//行业
    	 
		return view;
    }
    
   /* @RequestMapping(value="/addInit")
    public ModelAndView addInit(){
    	//当前登录的用户
    	CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
    	//当前登录的代理商
    	CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
    	Long userId = sessionUser.getId();
    	Integer userType = sessionUser.getUserType();
    	ModelAndView model = new  ModelAndView("/index/add_canteen");
    	//如果不是管理员或者代理商，就直接返回
    	if(!(userType==CrmAccountUserType.ADMIN.getCode()||userType==CrmAccountUserType.AGENT.getCode())){
    		return model; 
    	}
    	//设置查询参数
    	CrmCanteenBaseInfoParam param=new CrmCanteenBaseInfoParam();
    	param.setCrmAccountParam(BeanConvertor.copy(sessionUser, CrmAccountParam.class));
    	if(BeanUtils.isNotEmpty(sessionAgentUser)){
    		param.setCrmAgentParam(BeanConvertor.copy(sessionAgentUser, CrmAgentParam.class));
    	}
    	
    	model.addAllObjects(iCrmCanteenBaseInfoService.getAgentAndTeam(param));
    	return model;
    }*/
    
    /**
     * 获取编辑食堂的信息（企业，食堂扩展，客户资源）
     * @author 黄霄仪
     * @date 2017年6月22日 上午9:26:26
     */
    /*@RequestMapping("/editInit")
    @ResponseBody
    public ModelAndView editInit(CrmCanteenBaseInfoParam crmCanteenBaseInfoparam){
    	//当前登录的用户
    	CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
    	//当前登录的代理商
    	CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
    	Integer userType = sessionUser.getUserType();
    	ModelAndView model = new  ModelAndView("/index/add_canteen");
    	if(!(userType==CrmAccountUserType.ADMIN.getCode()||userType==CrmAccountUserType.AGENT.getCode())){
    		return model; 
    	}
    	crmCanteenBaseInfoparam.setCrmAccountParam(BeanConvertor.copy(sessionUser, CrmAccountParam.class));
    	if(BeanUtils.isNotEmpty(sessionAgentUser)){
    		crmCanteenBaseInfoparam.setCrmAgentParam(BeanConvertor.copy(sessionAgentUser, CrmAgentParam.class));
    	}
    	model.addAllObjects(iCrmCanteenBaseInfoService.getCrmCanteenBaseInfo(crmCanteenBaseInfoparam));
		return model;
    }*/
    
    /**
     * 新增食堂信息（企业，食堂扩展，客户资源）
     *
     * @return 
     *
     */
   /* @RequestMapping(value = "addOrUpdateCrmCanteenBaseInfo", method = RequestMethod.POST)
    @ResponseBody
	public RespModel addOrUpdateCrmCanteenBaseInfo(
			@RequestBody CrmCanteenBaseInfoParam crmCanteenBaseInfoparam) {
    	Long id = crmCanteenBaseInfoparam.getId();
    	if(BeanUtils.isEmpty(id)){
    		crmCanteenBaseInfoparam.setCreaterUid(CrmControllerHelper.getSessionUserId());
    	}
    	crmCanteenBaseInfoparam.setUpdaterUid(CrmControllerHelper.getSessionUserId());
    	return iCrmCanteenBaseInfoService.addOrUpdateCrmCanteenBaseInfo(crmCanteenBaseInfoparam);
	}*/
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmCanteenBaseInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmCanteenBaseInfo(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmcanteenbaseinfo/mainAddOrEditCrmCanteenBaseInfo");
         CrmCanteenBaseInfoParam CrmCanteenBaseInfoparam = new CrmCanteenBaseInfoParam();
         CrmCanteenBaseInfoPO po = iCrmCanteenBaseInfoService.get(CrmCanteenBaseInfoPO.class,id );
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
			iCrmCanteenBaseInfoService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmCanteenBaseInfoParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmCanteenBaseInfoPO po=BeanConvertor.convertBean(param, CrmCanteenBaseInfoPO.class);
        iCrmCanteenBaseInfoService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmCanteenBaseInfoParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmCanteenBaseInfoPO po=BeanConvertor.convertBean(param, CrmCanteenBaseInfoPO.class);
       iCrmCanteenBaseInfoService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmCanteenBaseInfoParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmCanteenBaseInfoVO> pages = iCrmCanteenBaseInfoService.queryPage(param);
    	//model.setRows(Lists.transform(pages.result,trans));
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    
		
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmCanteenBaseInfoParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmCanteenBaseInfo/CrmCanteenBaseInfo_Detail");
//		SplitAccountRolePO po = mainService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmCanteenBaseInfoPO());
//			return view;
//		}
		return view;
	} 
	
	/**
	 * 
	 * @Title: editCanteenInfo 
	 * @Description: 客户列表编辑食堂客户信息 (作废)
	 * @param @param clientId
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月20日 下午4:31:18
	 */
	/*@RequestMapping(value="/editCanteenInfo")
	public ModelAndView editCanteenInfo(Long clientId){
		ModelAndView modelAndView = new ModelAndView("index/add_canteen");
		//查询客户信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) crmClientResourceService.listByExample(crmClientResourcePO);
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		if(1 == clientType){
			return modelAndView;
		}else{
			modelAndView.addObject("clientResourcePO", crmClientResourcePO);
		}
		
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();//食堂Id
		
		//查询食堂信息
		
		
		//查询该食堂对应合作信息
		
		return modelAndView;
	}*/
	 /**
	  * 平台客户  客户类型为食堂的 编辑
	 * @Title: addInit 
	 * @author : lcl
	 * @time : 2017年6月29日 上午10:56:52
	 * @return ModelAndView    返回类型 
	 * @throws
	  */
    @RequestMapping(value="/editInit")
    public ModelAndView editInit(Long id){
    	//当前登录的用户
    	CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
    	//当前登录的代理商
    	CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
    	Long userId = sessionUser.getId();
    	Integer userType = sessionUser.getUserType();
    	ModelAndView model = new  ModelAndView("/index/add_canteen");
    	//如果不是管理员或者代理商，就直接返回
    	if(!(userType==CrmAccountUserType.ADMIN.getCode()||userType==CrmAccountUserType.AGENT.getCode())){
    		return model; 
    	}
    	
    	if(BeanUtils.isNotEmpty(id)){
    		//通过主键查询 客户资源表
    		CrmClientResourcePO clientResourcePO = crmClientResourceService.findById(id);
    		if(BeanUtils.isNotEmpty(clientResourcePO)){
    			Long  canteenId = clientResourcePO.getAgentCanteenId();
    			if(BeanUtils.isNotEmpty(canteenId)){//查询对应的食堂
    				CrmCanteenBaseInfoPO cBaseInfoPO = this.iCrmCanteenBaseInfoService.findByCanteenId(canteenId);
    				if(BeanUtils.isNotEmpty(cBaseInfoPO)){
    					CrmCanteenBaseInfoVO	crmCanteenBaseInfoVO = BeanConvertor.convertBean(cBaseInfoPO, CrmCanteenBaseInfoVO.class);
    					crmCanteenBaseInfoVO.setClientType(clientResourcePO.getClientType());
    					model.addObject("crmClientResourceVO", crmCanteenBaseInfoVO);
    					
    					if(BeanUtils.isNotEmpty(crmCanteenBaseInfoVO)){
    						Long baseInfoId = crmCanteenBaseInfoVO.getId();
    						if(BeanUtils.isNotEmpty(baseInfoId)){
    							CrmCanteenExtInfoVO cExtInfoVO = canteenExtInfoService.findByBaseInfoId(baseInfoId);
    							model.addObject("crmCanteenExtInfoVO", cExtInfoVO);
    							
    						}
    					}
    				}
    			}
    			
    			model.addObject("crmClientResourceVO", clientResourcePO);
    		}
    	}
    	
    	
    	
    	
    	
    	/*//设置查询参数
    	CrmCanteenBaseInfoParam param=new CrmCanteenBaseInfoParam();
    	param.setCrmAccountParam(BeanConvertor.copy(sessionUser, CrmAccountParam.class));
    	if(BeanUtils.isNotEmpty(sessionAgentUser)){
    		param.setCrmAgentParam(BeanConvertor.copy(sessionAgentUser, CrmAgentParam.class));
    	}
    	
    	model.addAllObjects(iCrmCanteenBaseInfoService.getAgentAndTeam(param));*/
    	return model;
    }
	
	
	
	
}