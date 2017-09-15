package com.hd.kzscrm.manager.controller.targetCheck;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.manager.controller.BaseController;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.agent.CrmWorkTargetParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmWorkTargetService;
import com.hd.kzscrm.service.vo.agent.CrmWorkTargetVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;

/**
 * 
 * @author Xu
 * 代理商考核控制器
 */
@Controller
@RequestMapping("/crmagentcheck")
public class CrmAgentCheckController extends BaseController{
	
	protected static final Logger Logger = LoggerFactory.getLogger(CrmAgentCheckController.class);
	
	@Autowired
	ICrmWorkTargetService targetService;
	
	@Resource
	ICrmAgentService agentService;

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
    	
   	ModelAndView view = new ModelAndView("/check/agent_check");
   	ParamMap param =  new ParamMap();
   	//查询全部代理商
   	CrmAgentParam param2 = new CrmAgentParam();
   	List<CrmAgentPO> crmAgentPOs = agentService.findAll(param2);
   	view.addObject("crmAgentPOs", crmAgentPOs);
   	 
   	//菜单
   	view.addObject("active","crmagentcheck");
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
   public PageRespModel queryPage(CrmWorkTargetParam param) {
   	PageRespModel model = new PageRespModel();
   	//翻页查询
   	param.setTargetType(3);
   	
   /*	String strTime=param.getStart_Time();
   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
	SimpleDateFormat form = new SimpleDateFormat("yyyy-MM");
	try {
		if(BeanUtils.isNotEmpty(param.getStart_Time())){ 
			Date d = (Date)sdf.parse(param.getStart_Time());
			String t = form.format(d);
			param.setStart_Time(t);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}*/
	
   	Page<CrmWorkTargetVO> pages = targetService.queryPage(param);
   	//model.setRows(Lists.transform(pages.result,trans));
   	model.setTotal(pages.getTotalResult());
   	model.setRows(pages.result);
   	
   	return model;
   }

}
