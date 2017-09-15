package com.hd.kzscrm.manager.controller.business;


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
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmPositionParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmPositionService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.vo.business.CrmPositionVO;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;


/**
 * crmPosition CRMPOSITION
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmposition")
public class CrmPositionController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmPositionController.class);
    @Autowired
    ICrmPositionService iCrmPositionService;
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
	 * 业务员表
	 */
	@Resource
	ICrmBusinessService iCrmBusinessService;
     
    /**
     ** 预留实现
     */
    public void initControler(HttpServletRequest request, Map<String, Object> map) {
     
    }
    
    
	/**
	 *
	 * 初始化
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/initShow")
	@ResponseBody
	public ModelAndView initShow(String url) {

		ModelAndView view = new ModelAndView(url);
		return view;
	}
	/**
	 * 跳转岗位页面
	 * @author 黄霄仪
	 * @date 2017年6月30日 上午10:54:03
	 */
	@RequestMapping("/showPositionInit")
	@ResponseBody
	public ModelAndView showPositionInit() {

		ModelAndView view = new ModelAndView("system_set/position");
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
			view.addObject("cSourcesVOs", cSourcesVOs);
		return view;
	}
	
	@RequestMapping(value="/showPosition",method=RequestMethod.POST)
	@ResponseBody
	public PageRespModel showPosition(@RequestBody CrmPositionParam param){
		PageRespModel model = new PageRespModel();
//		param.setCreateUid(SystemControllerHelper.getSessionUserId());
		Long agentId=null;
		if(CrmControllerHelper.getSessionUserType()==CrmAccountUserType.AGENT.getCode()){
			agentId=CrmControllerHelper.getSessionAgentUser().getId();
		}
		param.setAgentId(agentId);
		param.setType(CrmControllerHelper.getSessionType());
    	//翻页查询
    	Page<CrmPositionVO> pages = iCrmPositionService.queryPage(param);
    	//model.setRows(Lists.transform(pages.result,trans));
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
	}
	/**
	 * 添加或更新
	 * @author 黄霄仪
	 * @date 2017年6月7日 下午4:29:13
	 * @update 苏常松 添加岗位职位判断    
	 */
	@RequestMapping(value="/addOrUpdatePosition",method=RequestMethod.POST)
	@ResponseBody
	public RespModel addOrUpdatePosition(@RequestBody CrmPositionParam param){
		
		RespModel respModel=RespModel.setRespCode(RespModel.RespCode.SUCCESS);
		param.setType(CrmControllerHelper.getSessionType());//类型
		if(CrmControllerHelper.getSessionUserType()==CrmAccountUserType.AGENT.getCode()){
			param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
		}
		param.setCreateUid(CrmControllerHelper.getSessionUserId());
		param.setUpdateUid(CrmControllerHelper.getSessionUserId());
		
		/* 添加职位岗位的时候判断是否存在  */
		if(BeanUtils.isEmpty(param.getId())){
			List<CrmPositionPO> crmPositionPOs = this.iCrmPositionService.listByParam(param);
			if(CollectionUtils.isNotEmpty(crmPositionPOs)){
				respModel.setDesc("岗位职务已存在！");
				respModel.setRespCode(RespModel.RespCode.PARAMETER_NULL);
				return respModel;
			}
		}
		
		iCrmPositionService.addOrUpdatePosition(param);
		return respModel;
	}
    
	@RequestMapping(value="/deletePosition",method=RequestMethod.POST)
	@ResponseBody
	public RespModel deletePosition(@RequestBody CrmPositionParam param){
		Long id = param.getId();
		if(BeanUtils.isEmpty(id)){
			return new RespModel(RespModel.RespCode.PARAM_EXCEPTION.getCode(), "ID不能为空");
		}
		RespModel respModel=RespModel.setRespCode(RespModel.RespCode.SUCCESS);
		
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setPositionId(id);
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPO = iCrmBusinessService.commonQuery(crmBusinessParam);
		if(CollectionUtils.isNotEmpty(crmBusinessPO)){
			respModel.setDesc("该职务被引用,不可删除");
			respModel.setRespCode(RespModel.RespCode.PARAMETER_NULL);
			return respModel;
		}
		
		try {
			iCrmPositionService.deleteById(id);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RespModel.failure(e.getMessage());
		}
		return respModel;
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
     	
    	ModelAndView view = new ModelAndView("/crmposition/mainCrmPosition");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmPosition");
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmPosition", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmPosition() {
         ModelAndView modelAndView = new ModelAndView("/crmposition/mainAddOrEditCrmPosition");
		 CrmPositionParam CrmPositionparam = new CrmPositionParam();
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmPosition/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmPosition(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmposition/mainAddOrEditCrmPosition");
         CrmPositionParam CrmPositionparam = new CrmPositionParam();
         CrmPositionPO po = iCrmPositionService.get(CrmPositionPO.class,id );
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
			iCrmPositionService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmPositionParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmPositionPO po=BeanConvertor.convertBean(param, CrmPositionPO.class);
        iCrmPositionService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmPositionParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmPositionPO po=BeanConvertor.convertBean(param, CrmPositionPO.class);
       iCrmPositionService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmPositionParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmPositionVO> pages = iCrmPositionService.queryPage(param);
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
	public ModelAndView viewInfo(CrmPositionParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmPosition/CrmPosition_Detail");
//		SplitAccountRolePO po = mainService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmPositionPO());
//			return view;
//		}
		return view;
	} 
	
	
	 
}