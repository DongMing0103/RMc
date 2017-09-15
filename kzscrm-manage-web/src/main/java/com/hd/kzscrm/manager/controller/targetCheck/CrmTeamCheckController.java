package com.hd.kzscrm.manager.controller.targetCheck;

import java.util.List;
import java.util.Map;

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
import com.hd.kzscrm.dao.entity.agent.CrmAgentAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.manager.controller.BaseController;
import com.hd.kzscrm.service.param.agent.CrmAgentAreaParam;
import com.hd.kzscrm.service.param.agent.CrmWorkTargetParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmWorkTargetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.vo.agent.CrmAgentAreaVO;
import com.hd.kzscrm.service.vo.agent.CrmWorkTargetVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;

/**
 * 
 * 团队考核控制器
 * @author xushengquan
 *
 */
@Controller
@RequestMapping("/crmteamcheck")
public class CrmTeamCheckController extends BaseController{
	
	protected static final Logger Logger = LoggerFactory.getLogger(CrmTeamCheckController.class);
	
	@Autowired
	ICrmWorkTargetService iCrmWorkTargetService;
	
	
	//团队表
	@Autowired
	ICrmTeamService teamService;
 
	/**
	 * 预留实现
	 */
	public void initController(HttpServletRequest request, Map<String , Object> map) {
		
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
    	
   	ModelAndView view = new ModelAndView("/check/mainTeam_check");
   	ParamMap param =  new ParamMap();
   	//查询全部团队
   	CrmTeamParam param2 = new CrmTeamParam();
   	List<CrmTeamPO> crmTeamPOs = teamService.findAll(param2);
   	view.addObject("crmTeamPOs", crmTeamPOs);
   	
   	
   	//菜单
   	view.addObject("active","crmteamcheck");
   	//行业
   	 
	return view;
   }
   
  
   /**
    * 查看详情
    * @param param	
    * @return
    */
   @RequestMapping("/queryPage")
   @ResponseBody
   public PageRespModel queryPage(CrmWorkTargetParam param) throws Exception {
   	PageRespModel model = new PageRespModel();

   	
   	//翻页查询
   	param.setTargetType(1);//1代表 团队
   	Page<CrmWorkTargetVO> pages = iCrmWorkTargetService.queryPage(param);
   	//model.setRows(Lists.transform(pages.result,trans));
   	if(pages.result!=null){
   		model.setTotal(pages.getTotalResult());
   		model.setRows(pages.result);
   	}
   	
   	return model;
   }
   
  /**
   * 导出功能
   */
   public void excelOut(){
	   
   }
   
}
