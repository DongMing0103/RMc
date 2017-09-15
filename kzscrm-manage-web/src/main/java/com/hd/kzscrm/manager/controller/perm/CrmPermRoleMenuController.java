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
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleMenuPO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleMenuParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleMenuService;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleMenuVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmPermRoleMenu CRMPERMROLEMENU
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmpermrolemenu")
public class CrmPermRoleMenuController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmPermRoleMenuController.class);
    @Autowired
    ICrmPermRoleMenuService iCrmPermRoleMenuService;
    
    
     
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
     	
    	ModelAndView view = new ModelAndView("/crmpermrolemenu/mainCrmPermRoleMenu");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmPermRoleMenu");
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmPermRoleMenu", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmPermRoleMenu() {
         ModelAndView modelAndView = new ModelAndView("/crmpermrolemenu/mainAddOrEditCrmPermRoleMenu");
		 CrmPermRoleMenuParam CrmPermRoleMenuparam = new CrmPermRoleMenuParam();
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmPermRoleMenu/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmPermRoleMenu(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmpermrolemenu/mainAddOrEditCrmPermRoleMenu");
         CrmPermRoleMenuParam CrmPermRoleMenuparam = new CrmPermRoleMenuParam();
         CrmPermRoleMenuPO po = iCrmPermRoleMenuService.get(CrmPermRoleMenuPO.class,id );
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
			iCrmPermRoleMenuService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmPermRoleMenuParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmPermRoleMenuPO po=BeanConvertor.convertBean(param, CrmPermRoleMenuPO.class);
        iCrmPermRoleMenuService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmPermRoleMenuParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmPermRoleMenuPO po=BeanConvertor.convertBean(param, CrmPermRoleMenuPO.class);
       iCrmPermRoleMenuService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmPermRoleMenuParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmPermRoleMenuVO> pages = iCrmPermRoleMenuService.queryPage(param);
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
	public ModelAndView viewInfo(CrmPermRoleMenuParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmPermRoleMenu/CrmPermRoleMenu_Detail");
		CrmPermRoleMenuPO po = iCrmPermRoleMenuService.get(CrmPermRoleMenuPO.class, id);
		if (po != null  ) {
	       view.addObject("firstPO", po);
		} else {
			view.addObject("firstPo", new CrmPermRoleMenuPO());
			return view;
		}
		return view;
	} 
	
	
	 
}