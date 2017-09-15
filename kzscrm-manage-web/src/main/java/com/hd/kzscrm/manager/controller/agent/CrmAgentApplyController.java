package com.hd.kzscrm.manager.controller.agent;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.dao.entity.agent.CrmAgentApplyPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.agent.CrmAgentApplyParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.vo.agent.CrmAgentApplyVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmAgentApply CRMAGENTAPPLY
 * @author system code gen
 * 代理商
 */
@Controller
@RequestMapping("/crmagentapply")
public class CrmAgentApplyController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmAgentApplyController.class);
    @Autowired
    ICrmAgentApplyService crmAgentApplyService;
    @Autowired
    ICrmAgentService crmAgentService;
	/**
	 * 地理信息
	 */
	 @Resource
	 private IBaseAreaService iBaseAreaService;
	 @Resource
	 private ICrmClientResourceService clientResourceService;
     
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
     	
    	ModelAndView view = new ModelAndView("/crmagentapply/mainCrmAgentApply");
    	ParamMap paramMap =  new ParamMap();
    	 //代理状态
    	Map<String, Object> map = new  HashMap<String,Object>();
    	CrmAgentApplyParam param = new  CrmAgentApplyParam();
    	
    	//查询全部
    	Page<CrmAgentApplyVO> pages = crmAgentApplyService.queryPage(param);
    	param.setAgentStatus(0);//无效状态
    	Page<CrmAgentApplyVO> pages0 = crmAgentApplyService.queryPage(param);
    	param.setAgentStatus(1);//有效状态
    	Page<CrmAgentApplyVO> pages1 = crmAgentApplyService.queryPage(param);
    	//默认没有值时
    	map.put("all", 0);
    	map.put("vali", 0);
    	map.put("novali",0);
    	
    	if(pages!=null){//全部
    		map.put("all", pages.result.size());
    	}
    	if(pages0!=null){//有效
    		map.put("vali", pages0.result.size());
    	}
    	if(pages1!=null){//无效
    		map.put("novali", pages0.result.size());
    	}
    	//菜单
    	view.addObject("active","CrmAgentApply");
    	view.addObject("map",map);
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmAgentApply", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmAgentApply() {
         ModelAndView modelAndView = new ModelAndView("/crmagentapply/mainAddOrEditCrmAgentApply");
		 CrmAgentApplyParam CrmAgentApplyparam = new CrmAgentApplyParam();
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmAgentApply/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmAgentApply(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmagentapply/mainAddOrEditCrmAgentApply");
         CrmAgentApplyParam CrmAgentApplyparam = new CrmAgentApplyParam();
         CrmAgentApplyPO po = crmAgentApplyService.get(CrmAgentApplyPO.class,id );
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
			crmAgentApplyService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmAgentApplyParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmAgentApplyPO po=BeanConvertor.convertBean(param, CrmAgentApplyPO.class);
        crmAgentApplyService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmAgentApplyParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmAgentApplyPO po=BeanConvertor.convertBean(param, CrmAgentApplyPO.class);
       crmAgentApplyService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmAgentApplyParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmAgentApplyVO> pages = crmAgentApplyService.queryPage(param);
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
	public ModelAndView viewInfo(CrmAgentApplyParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmAgentApply/CrmAgentApply_Detail");
//		SplitAccountRolePO po = crmAgentApplyService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmAgentApplyPO());
//			return view;
//		}
		return view;
	} 
	
	/**
	 * 
	 * @Title: agentJoinApplyInit 
	 * @Description: 代理商加盟申请初始化 
	 * @param @param clientId
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月14日 下午4:12:09
	 */
	@RequestMapping(value="/agentJoinApplyInit")
	public ModelAndView agentJoinApplyInit(Long clientId){
		logger.info("#####CrmAgentApplyController###agentJoinApplyInit##clientId:"+clientId);
		ModelAndView modelAndView = new ModelAndView("salesman_operate/join_apply");
		CrmClientResourcePO clientResourcePO = this.clientResourceService.findById(clientId);
		if(BeanUtils.isNotEmpty(clientResourcePO)){
			Long areaCode = clientResourcePO.getAreaCode();
			if(BeanUtils.isNotEmpty(areaCode)){
				BaseAreaPO cityQ =  iBaseAreaService.getCityById(areaCode);
				//获取到市
				if(BeanUtils.isNotEmpty(cityQ)){
					BaseAreaPO cityS = iBaseAreaService.getCityById(cityQ.getParentCode());
					modelAndView.addObject("prov",cityS.getParentCode());
					modelAndView.addObject("city", cityS.getAreaCode());
					modelAndView.addObject("dist", cityQ.getAreaCode());
				}
			}
		}
		
		
		modelAndView.addObject("clientId", clientId);
		
		//若为续约
		Boolean flag = crmAgentApplyService.agentContractExtension(clientId,modelAndView);
		if(flag) return modelAndView;
		
		return modelAndView;
	}
	
	/**
	 * 代理商续约
	 */
	@RequestMapping(value="/agentApplyInit")
	public ModelAndView agentApplyInit(Long agentId){
		logger.info("#####CrmAgentApplyController###agentJoinApplyInit##clientId:"+agentId);
		ModelAndView modelAndView = new ModelAndView("system_operate/join_apply");
		modelAndView.addObject("agentId", agentId);
		
		//若为续约
		Boolean flag = crmAgentApplyService.agentExtension(agentId,modelAndView);
		if(flag) return modelAndView;
		
		return modelAndView;
	}
	
	
	/**
	 * 
	 * @Title: saveAgentJoinApply 
	 * @Description: 代理商加盟申请信息保存 
	 * @param @param crmAgentApplyParam
	 * @param @return  
	 * @return RespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月14日 下午4:12:13
	 */
	@RequestMapping(value="/saveAgentJoinApply", method = RequestMethod.POST)
	@ResponseBody
	public RespModel saveAgentJoinApply(CrmAgentApplyParam crmAgentApplyParam){
		logger.info("#####CrmAgentApplyController###saveAgentJoinApply##crmAgentApplyParam:"+crmAgentApplyParam.toString());
		RespModel respModel = new RespModel();
		
		crmAgentApplyParam.setCreateUid(CrmControllerHelper.getSessionUserId());//登录者userId
		//信息保存
		try {
			Boolean flag = crmAgentApplyService.saveAgentJoinApply(crmAgentApplyParam,respModel);
			if(flag) return respModel;
		} catch (Exception e) {
			logger.error("CrmAgentApplyController:saveAgentJoinApply:"+e);
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("系统出错");
			return respModel;
		}
		
		return respModel;
	}
	
	
	@RequestMapping(value="/AgentJoinApply", method = RequestMethod.POST)
	@ResponseBody
	public RespModel AgentJoinApply(CrmAgentApplyParam crmAgentApplyParam){
		logger.info("#####CrmAgentApplyController###saveAgentJoinApply##crmAgentApplyParam:"+crmAgentApplyParam.toString());
		RespModel respModel = new RespModel();
		Date now = new Date();
		crmAgentApplyParam.setCreateUid(CrmControllerHelper.getSessionUserId());//登录者userId
		if(CommUtil.parseLong(crmAgentApplyParam.getId())>0){//代理商续约
//			crmAgentApplyParam.setAgentId(crmAgentApplyParam.getAgentId());
			crmAgentApplyParam.setDelFlag(1);
			crmAgentApplyParam.setCheckStatus(1);//审核状态,0.申请中，1.申请通过，2.申请失败
			//List<CrmAgentApplyPO> crmAgentApplyPOs = this.crmAgentApplyService.commonQuery(crmAgentApplyParam);
			
		//信息保存
		try {
			CrmAgentApplyPO crmAgentApplyPO = crmAgentApplyService.getById(CommUtil.parseLong(crmAgentApplyParam.getId()));
			if(BeanUtils.isNotEmpty(crmAgentApplyPO)){
				//日期解析
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String cooperationEndTimeStr = crmAgentApplyParam.getCooperationEndTimeStr();
				crmAgentApplyParam.setCooperationEndTime(sdf.parse(cooperationEndTimeStr));
				
				String cooperationStartTimeStr = crmAgentApplyParam.getCooperationStartTimeStr();
				crmAgentApplyParam.setCooperationStartTime(sdf.parse(cooperationStartTimeStr));
				
				
					crmAgentApplyPO.setCooperationStartTime(crmAgentApplyParam.getCooperationStartTime());
					crmAgentApplyPO.setCooperationEndTime(crmAgentApplyParam.getCooperationEndTime());
					crmAgentApplyPO.setContractImgPath(crmAgentApplyParam.getContractImgPath());
					crmAgentApplyPO.setAreaCode(crmAgentApplyParam.getAreaCode());
					crmAgentApplyService.update(crmAgentApplyPO);
				
				//不知道是否需要联动
				CrmAgentPO crmAgentPO = crmAgentService.findByAgentId(crmAgentApplyParam.getAgentId());
				if(BeanUtils.isNotEmpty(crmAgentPO)){
					crmAgentPO.setAreaCode(crmAgentApplyParam.getAreaCode());
					crmAgentPO.setAgentStatus(1);
					crmAgentPO.setUpdateTime(now);
					crmAgentService.update(crmAgentPO);
				}
			}
			return respModel;
		} catch (Exception e) {
			logger.error("CrmAgentApplyController:saveAgentJoinApply:"+e);
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("系统出错");
			return respModel;
		}
		}
		return respModel;
	}
}