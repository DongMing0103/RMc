package com.hd.kzscrm.manager.controller.perm;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.dao.entity.perm.CrmPermMenuPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.perm.CrmPermMenuParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermMenuService;
import com.hd.kzscrm.service.vo.perm.CrmPermMenuVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmPermMenu CRMPERMMENU
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmpermmenu")
public class CrmPermMenuController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmPermMenuController.class);
    @Autowired
    ICrmPermMenuService iCrmPermMenuService;
    
    
     
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
     	
    	ModelAndView view = new ModelAndView("/crmpermmenu/mainCrmPermMenu");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmPermMenu");
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmPermMenuInit", method = RequestMethod.GET)
    @ResponseBody
	public ModelAndView addCrmPermMenuInit() {
		ModelAndView modelAndView = new ModelAndView("/perm/menuAdd");
		CrmPermMenuParam crmPermMenuParam = new CrmPermMenuParam();
		crmPermMenuParam.setParentId(0l);// 查询1级菜单
		List<CrmPermMenuPO> crmPermMenuPOs = iCrmPermMenuService
				.listByParam(crmPermMenuParam);
		modelAndView.addObject("crmPermMenuVOs", crmPermMenuPOs);
		return modelAndView;
	}
    
    /**
	 * 新增
	 *
	 * @return
	 *
	 */
	@RequestMapping(value = "addCrmPermMenu", method = RequestMethod.POST)
	@ResponseBody
	public RespModel addCrmPermMenu(
			@RequestBody CrmPermMenuParam crmPermMenuParam) {
		Long sessionUserId = CrmControllerHelper.getSessionUserId();
		crmPermMenuParam.setCreateId(sessionUserId);
		crmPermMenuParam.setUpdateId(sessionUserId);
		iCrmPermMenuService.addMenu(crmPermMenuParam);
		return RespModel.success("添加成功");
	}
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmPermMenu/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmPermMenu(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmpermmenu/mainAddOrEditCrmPermMenu");
         CrmPermMenuParam CrmPermMenuparam = new CrmPermMenuParam();
         CrmPermMenuPO po = iCrmPermMenuService.get(CrmPermMenuPO.class,id );
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
			iCrmPermMenuService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmPermMenuParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmPermMenuPO po=BeanConvertor.convertBean(param, CrmPermMenuPO.class);
        iCrmPermMenuService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmPermMenuParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmPermMenuPO po=BeanConvertor.convertBean(param, CrmPermMenuPO.class);
       iCrmPermMenuService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmPermMenuParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmPermMenuVO> pages = iCrmPermMenuService.queryPage(param);
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
	public ModelAndView viewInfo(CrmPermMenuParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmPermMenu/CrmPermMenu_Detail");
		CrmPermMenuPO po = iCrmPermMenuService.get(CrmPermMenuPO.class, id);
		if (po != null  ) {
	       view.addObject("firstPO", po);
		} else {
			view.addObject("firstPo", new CrmPermMenuPO());
			return view;
		}
		return view;
	} 
	
	
	 
}