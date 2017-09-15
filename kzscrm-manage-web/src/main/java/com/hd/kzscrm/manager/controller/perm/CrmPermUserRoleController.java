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
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.service.param.perm.CrmPermUserRoleParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.vo.perm.CrmPermUserRoleVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmPermUserRole CRMPERMユーザROLE
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmpermuserrole")
public class CrmPermUserRoleController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmPermUserRoleController.class);
    @Autowired
    ICrmPermUserRoleService iCrmPermUserRoleService;
    
    
     
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
     	
    	ModelAndView view = new ModelAndView("/crmpermuserrole/mainCrmPermUserRole");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmPermUserRole");
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmPermUserRole", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmPermUserRole() {
         ModelAndView modelAndView = new ModelAndView("/crmpermuserrole/mainAddOrEditCrmPermUserRole");
		 CrmPermUserRoleParam CrmPermUserRoleparam = new CrmPermUserRoleParam();
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmPermUserRole/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmPermUserRole(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmpermuserrole/mainAddOrEditCrmPermUserRole");
         CrmPermUserRoleParam CrmPermUserRoleparam = new CrmPermUserRoleParam();
         CrmPermUserRolePO po = iCrmPermUserRoleService.get(CrmPermUserRolePO.class,id );
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
			iCrmPermUserRoleService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmPermUserRoleParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
        iCrmPermUserRoleService.saveEntity(param);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmPermUserRoleParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmPermUserRolePO po=BeanConvertor.convertBean(param, CrmPermUserRolePO.class);
       iCrmPermUserRoleService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmPermUserRoleParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmPermUserRoleVO> pages = iCrmPermUserRoleService.queryPage(param);
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
	public ModelAndView viewInfo(CrmPermUserRoleParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmPermUserRole/CrmPermUserRole_Detail");
		CrmPermUserRolePO po = iCrmPermUserRoleService.get(CrmPermUserRolePO.class, id);
		if (po != null  ) {
	       view.addObject("firstPO", po);
		} else {
			view.addObject("firstPo", new CrmPermUserRolePO());
			return view;
		}
		return view;
	} 
	
	
	 
}