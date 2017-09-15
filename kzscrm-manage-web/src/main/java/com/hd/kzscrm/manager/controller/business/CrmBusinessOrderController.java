package com.hd.kzscrm.manager.controller.business;


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
import com.hd.kzscrm.dao.entity.business.CrmBusinessOrderPO;
import com.hd.kzscrm.service.param.business.CrmBusinessOrderParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.vo.business.CrmBusinessOrderVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmBusinessOrder CRMBUSINESS注文
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmbusinessorder")
public class CrmBusinessOrderController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessOrderController.class);
    @Autowired
    ICrmBusinessOrderService mainService;
    
    
     
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
     	
    	ModelAndView view = new ModelAndView("/crmbusinessorder/mainCrmBusinessOrder");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmBusinessOrder");
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmBusinessOrder", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmBusinessOrder() {
         ModelAndView modelAndView = new ModelAndView("/crmbusinessorder/mainAddOrEditCrmBusinessOrder");
		 CrmBusinessOrderParam CrmBusinessOrderparam = new CrmBusinessOrderParam();
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmBusinessOrder/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmBusinessOrder(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmbusinessorder/mainAddOrEditCrmBusinessOrder");
         CrmBusinessOrderParam CrmBusinessOrderparam = new CrmBusinessOrderParam();
         CrmBusinessOrderPO po = mainService.get(CrmBusinessOrderPO.class,id );
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
			mainService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmBusinessOrderParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmBusinessOrderPO po=BeanConvertor.convertBean(param, CrmBusinessOrderPO.class);
        mainService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmBusinessOrderParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmBusinessOrderPO po=BeanConvertor.convertBean(param, CrmBusinessOrderPO.class);
       mainService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmBusinessOrderParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmBusinessOrderVO> pages = mainService.queryPage(param);
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
	public ModelAndView viewInfo(CrmBusinessOrderParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmBusinessOrder/CrmBusinessOrder_Detail");
//		SplitAccountRolePO po = mainService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmBusinessOrderPO());
//			return view;
//		}
		return view;
	} 
	
	
	 
}