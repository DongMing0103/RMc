package com.hd.kzscrm.manager.controller.business;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.business.CrmBusinessTailRecordParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessTailRecordService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.vo.business.CrmBusinessTailRecordVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmBusinessTailRecord CRMBUSINESSTAILRECORD
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmbusinesstailrecord")
public class CrmBusinessTailRecordController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessTailRecordController.class);
    // 业务员跟踪记录表
    @Autowired
    ICrmBusinessTailRecordService crmBusinessTailRecordService;
    //客户资源
    @Resource
    ICrmClientResourceService clientResourceService;
    
     
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
     	
    	ModelAndView view = new ModelAndView("/crmbusinesstailrecord/mainCrmBusinessTailRecord");
    	ParamMap param =  new ParamMap();
    	
    	//菜单
    	view.addObject("active","CrmBusinessTailRecord");
    	//行业
    	 
		return view;
    }
    
   
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmBusinessTailRecordParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
    	Page<CrmBusinessTailRecordVO> pages = crmBusinessTailRecordService.queryPage(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	return model;
    }
    
		
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmBusinessTailRecordParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmBusinessTailRecord/CrmBusinessTailRecord_Detail");

		return view;
	} 
	
	/**
	 * 客户列表 的跟踪记录
	* @Title: initClint 
	* @author : lcl
	* @time : 2017年6月21日 下午4:31:19
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping("/initClint")
    @ResponseBody
    public ModelAndView initClint(CrmBusinessTailRecordParam param) {
     	
    	ModelAndView view = new ModelAndView("/system_operate/track_record");
    	ParamMap paramMap =  new ParamMap();
    	view.addObject("customerId", param.getCustomerId());//获取到客户资源id
    	//根据客户资源id查询对象（）
    	CrmClientResourcePO clientResourcePO = clientResourceService.findById(param.getCustomerId());
    	if(BeanUtils.isNotEmpty(clientResourcePO)){
    		Long accessAgentCanteenId = clientResourcePO.getAgentCanteenId();
    		view.addObject("accessAgentCanteenId", accessAgentCanteenId);
    		Integer clientType = clientResourcePO.getClientType();
    		view.addObject("clientType", clientType);
    	}
    	
    	//菜单
    	view.addObject("active","CrmBusinessTailRecord");
    	//行业
    	 
		return view;
    }
    
	 /**
	  * 客户列表 根据 客户id查询对应的跟踪记录
	 * @Title: queryList 
	 * @author : lcl
	 * @time : 2017年6月21日 下午4:50:25
	 * @return PageRespModel    返回类型 
	 * @throws
	  */
    
    @RequestMapping("/queryList")
    @ResponseBody
    public PageRespModel queryList(CrmBusinessTailRecordParam param) throws Exception {
    	PageRespModel model = new PageRespModel();
    	Page<CrmBusinessTailRecordVO> pages = crmBusinessTailRecordService.queryList(param);
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	return model;
    }
    
   /**
    * 
    * @Title: trackRegisterInit 
    * @Description: 跟踪登记页面初始化  
    * @param @param customerId
    * @param @return  
    * @return ModelAndView    返回类型 
    * @throws 
    * @author LuGaogao
    * @date 2017年6月23日 下午4:56:32
    */
    @RequestMapping("/trackRegisterInit")
    public ModelAndView trackRegisterInit(Long customerId){
    	logger.info("#####CrmBusinessTailRecordController###trackRegisterInit##customerId:"+customerId);
    	ModelAndView modelAndView = new ModelAndView("system_operate/track_register");
    	modelAndView.addObject("customerId", customerId);
    	return modelAndView;
    }
    
    /**
     * 
     * @Title: trackRegisterDetails 
     * @Description: 跟踪记录详情 
     * @param @param crmBusinessTailRecordParam
     * @param @return  
     * @return PageRespModel    返回类型 
     * @throws 
     * @author LuGaogao
     * @date 2017年6月23日 下午4:56:39
     */
    @RequestMapping("/trackRegisterDetails")
    @ResponseBody
    public PageRespModel trackRegisterDetails(CrmBusinessTailRecordParam crmBusinessTailRecordParam){
    	logger.info("#####CrmBusinessTailRecordController###trackRegisterDetails##crmBusinessTailRecordParam:"+crmBusinessTailRecordParam.toString());
    	PageRespModel pageRespModel = new PageRespModel();
    	
    	//跟踪记录详情
    	Boolean flag = crmBusinessTailRecordService.trackRegisterDetails(crmBusinessTailRecordParam,pageRespModel);
    	if(flag) return pageRespModel;
    	
    	return pageRespModel;
    }
    
    /**
     * 
     * @Title: saveTrackRegisterMsg 
     * @Description: 保存跟踪登记信息 
     * @param @param crmBusinessTailRecordParam
     * @param @return  
     * @return RespModel    返回类型 
     * @throws 
     * @author LuGaogao
     * @date 2017年6月23日 下午4:57:03
     */
    @RequestMapping("/saveTrackRegisterMsg")
    @ResponseBody
    public RespModel saveTrackRegisterMsg(CrmBusinessTailRecordParam crmBusinessTailRecordParam){
    	logger.info("#####CrmBusinessTailRecordController###saveTrackRegisterMsg##crmBusinessTailRecordParam:"+crmBusinessTailRecordParam.toString());
    	RespModel respModel = new RespModel();
    	crmBusinessTailRecordParam.setCreateUid(CrmControllerHelper.getSessionUserId());//当前登录用户Id
    	
    	try {
			//保存跟踪登记信息
			Boolean flag = crmBusinessTailRecordService.saveTrackRegisterMsg(crmBusinessTailRecordParam,respModel);
			if(flag) return respModel;
		} catch (Exception e) {
			logger.error("CrmBusinessTailRecordController:saveTrackRegisterMsg:" + e);
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("出错了");
			return respModel;
		}
    	
    	return respModel;
    }
}