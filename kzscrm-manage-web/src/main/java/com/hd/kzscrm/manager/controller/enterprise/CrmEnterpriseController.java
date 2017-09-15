package com.hd.kzscrm.manager.controller.enterprise;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.wolverine.controller.base.BaseController;


/**
 * crmClientResource CRMCLIENTRESOURCE
 * @author system code gen
 * 客户资源列表
 */
@Controller
@RequestMapping("/crmEnterprise")
public class CrmEnterpriseController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmEnterpriseController.class);
    @Autowired
    ICrmEnterpriseService iCrmEnterpriseService;
    
     
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
    * @Title: viewEnterpriseInformation 
    * @Description: 查看企业信息 
    * @param @param clientId
    * @param @return  
    * @return ModelAndView    返回类型 
    * @throws 
    * @author LuGaogao
    * @date 2017年6月27日 下午3:32:44
    */
   @RequestMapping("/viewEnterpriseInformation")
   public ModelAndView viewEnterpriseInformation(Long clientId){
	   logger.info("#####CrmEnterpriseController###viewEnterpriseInformation##clientId:"+clientId);
	   ModelAndView modelAndView = new ModelAndView("enterprise/qy_information");
	   
	   //企业信息
	   Boolean flag = iCrmEnterpriseService.viewEnterpriseInformation(clientId,modelAndView);
	   if(flag) return modelAndView;
	   
	   return modelAndView;
   }
   
}