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

import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRolePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.manage.common.util.BeanConvertor;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.perm.CrmPermMenuParam;
import com.hd.kzscrm.service.param.perm.CrmPermRoleParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.vo.perm.CrmPermMenuVO;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;


/**
 * crmPermRole CRMPERMROLE
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmpermrole")
public class CrmPermRoleController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmPermRoleController.class);
    @Autowired
    ICrmPermRoleService iCrmPermRoleService;
    
    @Resource
    private ICrmPermMenuService iCrmPermMenuService;
	@Resource
	private  ICrmPermUserRoleService iCrmPermUserRoleService;
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
     * 角色列表
     * @author 黄霄仪
     * @date 2017年6月14日 上午9:39:30
     */
    @RequestMapping(value="/roleList")
    public ModelAndView roleList(){
    	ModelAndView model = new  ModelAndView("/perm/role_list");
    	/*CrmPermSourceHelper cHelper =new  CrmPermSourceHelper();
    	List<CrmPermSourcesVO> cSourcesVOs =cHelper.getPermSourceVOs();*/
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
			//Long sourceId = cSourcesVO.getId();
		/*	if(roleSourceSet.contains(sourceId)){
				cSourcesVO.setChecked(true);
			}else{
				cSourcesVO.setChecked(false);
			}*/
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
     *
     * 初始化
     * @param param
     * @return
     */
    @RequestMapping("/init")
    @ResponseBody
    public ModelAndView init() {
     	
    	ModelAndView view = new ModelAndView("/crmpermrole/mainCrmPermRole");
//    	ParamMap param =  new ParamMap();
    	
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmPermRole", method = RequestMethod.GET)
    @ResponseBody
	public ModelAndView addCrmPermRole() {
		ModelAndView modelAndView = new ModelAndView(
				"/perm/mainAddOrEditPermRole");
		// 菜单
		List<CrmPermMenuVO> crmPermMenuVOs = iCrmPermMenuService.getMenuAndSource(new CrmPermMenuParam());
		modelAndView.addObject("crmPermMenuVOs", crmPermMenuVOs);
		return modelAndView;
	}
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmPermRole/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmPermRole(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView(
				"/perm/mainAddOrEditPermRole");
		CrmPermRoleParam CrmPermRoleparam = new CrmPermRoleParam();
		CrmPermRolePO crmPermRolePO = iCrmPermRoleService.get(
				CrmPermRolePO.class, id);
		modelAndView.addObject("crmPermRoleVO", crmPermRolePO);

		// 菜单映射
		CrmPermMenuParam crmPermMenuParam = new CrmPermMenuParam();
		crmPermMenuParam.setRoleId(id);
		List<CrmPermMenuVO> crmPermMenuVOs = iCrmPermMenuService
				.getAuthority(crmPermMenuParam);
		modelAndView.addObject("crmPermMenuVOs", crmPermMenuVOs);
		modelAndView.addObject("type", "update");
		return modelAndView;
    }
   /**
     * 删除
     *
     * @param 
     * @return
     */
    @RequestMapping(value = "doDelete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RespModel delete(@PathVariable Long id) {
        RespModel res = new RespModel();
        try {
			iCrmPermRoleService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave" ,method=RequestMethod.POST)
    @ResponseBody
    public RespModel doSave(@RequestBody CrmPermRoleParam param, HttpServletRequest request) {
    	Long sessionUserId = CrmControllerHelper.getSessionUserId();
        RespModel res = RespModel.success("保存成功");
       	param.setCreateId(sessionUserId);
       	param.setUpdateId(sessionUserId);
       	param.setType(CrmControllerHelper.getSessionType());
       	if(CrmAccountUserType.AGENT.getCode()==CrmControllerHelper.getSessionUserType()){
       		param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
       	}
        iCrmPermRoleService.doSave(param);
        return res;
    }
    
	@RequestMapping(value = "doUpdate",method=RequestMethod.POST)
	@ResponseBody
	public RespModel doUpdate(@RequestBody CrmPermRoleParam param, HttpServletRequest request) {
		Long sessionUserId = CrmControllerHelper.getSessionUserId();
		param.setUpdateId(sessionUserId);
		param.setType(CrmControllerHelper.getSessionType());
       	if(CrmAccountUserType.AGENT.getCode()==CrmControllerHelper.getSessionUserType()){
       		param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
       	}
		RespModel res = RespModel.success("修改成功");
		iCrmPermRoleService.updateEntity(param);
		return res;
	}
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping(value="/queryPage",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel queryPage(@RequestBody CrmPermRoleParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	param.setType(CrmControllerHelper.getSessionType());
    	if(CrmAccountUserType.AGENT.getCode()==CrmControllerHelper.getSessionUserType()){
       		param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
       	}
    	Page<CrmPermRoleVO> pages = iCrmPermRoleService.queryPage(param);
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
	public ModelAndView viewInfo(CrmPermRoleParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmPermRole/CrmPermRole_Detail");
		CrmPermRolePO po = iCrmPermRoleService.get(CrmPermRolePO.class, id);
		if (po != null  ) {
	       view.addObject("firstPO", po);
		} else {
			view.addObject("firstPo", new CrmPermRolePO());
			return view;
		}
		return view;
	} 
	
	
	 
}