package com.hd.kzscrm.manager.controller.perm;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermMenuPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.perm.CrmPermMenuParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmPermSources CRMPERMSOURCES
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmpermsources")
public class CrmPermSourcesController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmPermSourcesController.class);
    @Autowired
    ICrmPermSourcesService iCrmPermSourcesService;
    @Resource
    private ICrmPermMenuService iCrmPermMenuService;
    //角色 用户表
    @Autowired
    private ICrmPermUserRoleService userRoleService;
	@Resource
    ICrmPermUserRoleService iCrmPermUserRoleService;
	@Autowired
	ICrmPermRoleSourcesService roleSourcesService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
    ICrmPermSourcesService  pSourcesService;
     
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
     	
    	ModelAndView view = new ModelAndView("/crmpermsources/mainCrmPermSources");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmPermSources");
    	//行业
    	 
		return view;
    }
    /**
     * 资源页面初始化
     * @author 黄霄仪
     * @date 2017年6月14日 上午9:39:01
     */
    @RequestMapping(value="/resourceList")
    public ModelAndView resourceList(){
    	ModelAndView model = new  ModelAndView("/perm/resource_list");
        CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
 		Long userId = sessionUser.getId();
 		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(userId);//用户，角色映射

 		Set<Long> roleSourceSet = new HashSet<Long>();
 		if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
 			Long roleId = crmPermUserRolePO.getRoleId();
 			List<CrmPermRoleSourcesPO> permRoleSourcesPOs = roleSourcesService.findByRoleId(roleId);
 			if(CollectionUtils.isNotEmpty(permRoleSourcesPOs)){
 				for(CrmPermRoleSourcesPO cRoleSourcesPO : permRoleSourcesPOs){
 					roleSourceSet.add(cRoleSourcesPO.getSourcesId());
 				}
 			}
 			
 		}

       //查询 角色的按钮资源
 		CrmPermSourcesParam permSourcesParam = new  CrmPermSourcesParam();
 		permSourcesParam.setDelFlagAll(1);//非空就全查询
 		List<CrmPermSourcesPO> cRoleSourcesPOs = pSourcesService.listByParam(permSourcesParam);
 		List<CrmPermSourcesVO> cSourcesVOs = BeanConvertor.copyList(cRoleSourcesPOs, CrmPermSourcesVO.class);
 		for(CrmPermSourcesVO cSourcesVO : cSourcesVOs){
 			if(BeanUtils.isNotEmpty(cSourcesVO) && BeanUtils.isNotEmpty(cSourcesVO.getChecked())){
				if(cSourcesVO.getChecked()==1){
					cSourcesVO.setCheck(true);
				}else{
					cSourcesVO.setCheck(false);
				}
			}
 		}
 		model.addObject("cSourcesVOs", cSourcesVOs);
    	return model;
    }
    /**
     * 新增
     *lcl
     * @return 
     *
     */
    @RequestMapping(value = "/addCrmPermSources", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmPermSources() {
		ModelAndView modelAndView = new ModelAndView(
				"/perm/addResourceOrEdit");
		CrmPermSourcesParam CrmPermSourcesparam = new CrmPermSourcesParam();
		CrmPermMenuParam crmPermMenuParam = new CrmPermMenuParam();
//		crmPermMenuParam.setParentId(0l);// 查询1级菜单
		List<CrmPermMenuPO> crmPermMenuPOs = iCrmPermMenuService
				.listByParam(crmPermMenuParam);
		modelAndView.addObject("crmPermMenuVOs", crmPermMenuPOs);
		modelAndView.addObject("type", "add");
		return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmPermSources/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmPermSources(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmpermsources/mainAddOrEditCrmPermSources");
         CrmPermSourcesParam CrmPermSourcesparam = new CrmPermSourcesParam();
         CrmPermSourcesPO po = iCrmPermSourcesService.get(CrmPermSourcesPO.class,id );
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
			iCrmPermSourcesService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave",method=RequestMethod.POST)
    @ResponseBody
    public RespModel doSave(@RequestBody CrmPermSourcesParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
        Long sessionUserId = CrmControllerHelper.getSessionUserId();
        if(BeanUtils.isNotEmpty(sessionUserId)){
        	CrmPermUserRolePO userRolePO = userRoleService.findByUserId(sessionUserId);//根据用户登录的id查询 对应角色id
        	if(BeanUtils.isNotEmpty(userRolePO)){
        		Long roleId = userRolePO.getRoleId();//角色id
        		if(BeanUtils.isNotEmpty(roleId)){
        			param.setRoleId(roleId);
        			param.setCreateUid(sessionUserId);
        			param.setUpdateUid(sessionUserId);
        			iCrmPermSourcesService.saveEntity(param);
        		}
        	}
        }
        return res;
    }
    
    @RequestMapping(value = "doUpdate",method=RequestMethod.POST)
    @ResponseBody
    public RespModel doUpdate(@RequestBody CrmPermSourcesParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmPermSourcesPO po=BeanConvertor.convertBean(param, CrmPermSourcesPO.class);
       iCrmPermSourcesService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmPermSourcesParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmPermSourcesVO> pages = iCrmPermSourcesService.queryPage(param);
    	//model.setRows(Lists.transform(pages.result,trans));
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPageBasic")
    @ResponseBody
    public PageRespModel queryPageBasic(CrmPermSourcesParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmPermSourcesVO> pages = iCrmPermSourcesService.queryPageBasic(param);
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
	public ModelAndView viewInfo(CrmPermSourcesParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmPermSources/CrmPermSources_Detail");
		CrmPermSourcesPO po = iCrmPermSourcesService.get(CrmPermSourcesPO.class, id);
		if (po != null  ) {
	       view.addObject("firstPO", po);
		} else {
			view.addObject("firstPo", new CrmPermSourcesPO());
			return view;
		}
		return view;
	} 
	
	
	 
}