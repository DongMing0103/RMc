package com.hd.kzscrm.manager.controller.perm;


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
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleSourcesParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmPermRoleSources CRMPERMROLESOURCES
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmpermrolesources")
public class CrmPermRoleSourcesController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmPermRoleSourcesController.class);
    @Autowired
    ICrmPermRoleSourcesService iCrmPermRoleSourcesService;
    
    
     
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
     	
    	ModelAndView view = new ModelAndView("/crmpermrolesources/mainCrmPermRoleSources");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmPermRoleSources");
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmPermRoleSources", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmPermRoleSources() {
         ModelAndView modelAndView = new ModelAndView("/crmpermrolesources/mainAddOrEditCrmPermRoleSources");
		 CrmPermRoleSourcesParam CrmPermRoleSourcesparam = new CrmPermRoleSourcesParam();
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmPermRoleSources/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmPermRoleSources(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmpermrolesources/mainAddOrEditCrmPermRoleSources");
         CrmPermRoleSourcesParam CrmPermRoleSourcesparam = new CrmPermRoleSourcesParam();
         CrmPermRoleSourcesPO po = iCrmPermRoleSourcesService.get(CrmPermRoleSourcesPO.class,id );
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
			iCrmPermRoleSourcesService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmPermRoleSourcesParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmPermRoleSourcesPO po=BeanConvertor.convertBean(param, CrmPermRoleSourcesPO.class);
        iCrmPermRoleSourcesService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmPermRoleSourcesParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmPermRoleSourcesPO po=BeanConvertor.convertBean(param, CrmPermRoleSourcesPO.class);
       iCrmPermRoleSourcesService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmPermRoleSourcesParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmPermRoleSourcesVO> pages = iCrmPermRoleSourcesService.queryPage(param);
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
	public ModelAndView viewInfo(CrmPermRoleSourcesParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmPermRoleSources/CrmPermRoleSources_Detail");
		CrmPermRoleSourcesPO po = iCrmPermRoleSourcesService.get(CrmPermRoleSourcesPO.class, id);
		if (po != null  ) {
	       view.addObject("firstPO", po);
		} else {
			view.addObject("firstPo", new CrmPermRoleSourcesPO());
			return view;
		}
		return view;
	} 
	
	
	 
}