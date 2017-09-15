package com.hd.kzscrm.manager.controller.user;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hd.kzscrm.common.model.RespModel.RespCode;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRolePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.manager.controller.BaseController;
import com.hd.kzscrm.service.constants.QzsWebConstants;
import com.hd.kzscrm.service.param.perm.CrmPermRoleParam;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.param.user.CrmUserParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.kzscrm.service.vo.user.CrmUserVO;
import com.hd.wolverine.cache.CacheService;
import com.hd.wolverine.cache.WolverineJedisCluster;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.MD5;


/**
 * crmClientResource CRMCLIENTRESOURCE
 * @author system code gen
 * 客户资源列表
 */
@Controller
@RequestMapping("/crmUser")
public class CrmUserController  extends BaseController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CrmUserController.class);
    @Resource
    private ICrmUserService iCrmUserService;
    @Resource
    private WolverineJedisCluster wolverineJedisCluster;
    @Resource
    private ICrmPermRoleService iCrmPermRoleService;
    @Resource
    private ICrmPermUserRoleService iCrmPermUserRoleService;
	
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
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/initShow")
	@ResponseBody
	public ModelAndView initShow(String url) {
		LOGGER.info("com.hd.kzscrm.manager.controller.user.CrmUserController.initShow(String)-url:"+url);
		ModelAndView view = new ModelAndView(url);
		return view;
	}
	 /**
     * 一览查询（权限设置-用户管理）
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmUserParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	if(CrmControllerHelper.getSessionUserType()==CrmAccountUserType.AGENT.getCode()){
    		param.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
    	}
    	param.setType(CrmControllerHelper.getSessionType());
    	
    	Page<CrmUserVO> pages = iCrmUserService.queryPage(param);
    	//model.setRows(Lists.transform(pages.result,trans));
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    
    /**
     * 删除
     * @param id 获取用户id
     * @return
     */
    @RequestMapping(value = "deleteById")
    @ResponseBody
    public RespModel deleteById(Long id) {
    	LOGGER.info("#####PermAccountController###delete##id:"+id);
        try {
        	if(BeanUtils.isEmpty(id)){
        		return RespModel.failure("该用户不存在！");
        	}
        	iCrmUserService.deleteById(id);
			return RespModel.success("操作成功！");
		} catch (BizException e) {
			LOGGER.error("PermAccountController:delete"+e.getMessage());
		}
        LOGGER.info("PermAccountController:delete"+RespModel.success("操作失败！").toString());
        return RespModel.success("操作失败！");
    }
    
    /**
     * 重置密码
     * @param id 获取用户id
     * @return
     */
    @RequestMapping(value = "resetPassword")
    @ResponseBody
    public RespModel resetPasswordById(Long id) {
    	LOGGER.info("#####PermAccountController###resetPassword##id:"+id);
    	try {
    		if(CommUtil.parseLong(id)<=0){
    			return RespModel.failure("该用户不存在！");
    		}
    		CrmUserParam param = new CrmUserParam();
    		param.setId(id);
    		param.setPassword(MD5.md5("123456"));
    		iCrmUserService.modifyPassword(param);
    		return RespModel.success("操作成功！");
    	} catch (Exception e) {
    		LOGGER.error("PermAccountController:delete"+e.getMessage());
    	}
    	LOGGER.info("PermAccountController:delete"+RespModel.success("操作失败！").toString());
    	return RespModel.success("操作失败！");
    }
    
	
	/**
     * 显示管理员列表
     * @author 黄霄仪
     * @date 2017年6月14日 上午9:36:07
     */
    @RequestMapping(value="/adminManagement")
    public ModelAndView adminManagement(){
    	ModelAndView model = new  ModelAndView("/perm/admin_management");
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
     *
     * @return 
     *
     */
    @RequestMapping(value = "addPermAccount", method = RequestMethod.GET)
	public ModelAndView addPermAccount(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(
				"/perm/mainAddOrEditPermAccount");
//		Cookie[] cookies = request.getCookies();
//		if(BeanUtils.isNotEmpty(cookies)){
//			for (Cookie cookie : cookies) {
//				if("SESSION_LOGIN_USERNAME".equals(cookie.getName())||"SESSION_LOGIN_PASSWORD".equals(cookie.getName())){
//					cookie.setValue(null);
//					cookie.setPath("/");//根据你创建cookie的路径进行填写       
//					response.addCookie(cookie);   
//				}
//			}
//		}
//		response.addCookie(new Cookie(name, value));
		CrmPermRoleParam crmpermroleParam = new CrmPermRoleParam();
		crmpermroleParam.setStatus(1);// 启用
		crmpermroleParam.setType(CrmControllerHelper.getSessionType());
		crmpermroleParam.setUserType(1);//暂时只能新增管理员角色
		if(CrmAccountUserType.AGENT.getCode()==CrmControllerHelper.getSessionUserType()){
			crmpermroleParam.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
       	}
		List<CrmPermRolePO> crmPermRolePOs = iCrmPermRoleService
				.listByParam(crmpermroleParam);
		modelAndView.addObject("crmPermRoleVOs", crmPermRolePOs);
		return modelAndView;
	}
    
    /**
     * 编辑用户
     * @author 黄霄仪
     * @date 2017年7月3日 下午4:03:25
     */
    @RequestMapping(value = "editPermAccount/{id}", method = RequestMethod.GET)
    @ResponseBody
	public ModelAndView editPermAccount(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView(
				"/perm/mainAddOrEditPermAccount");
		//角色信息
		CrmPermRoleParam crmpermroleParam = new CrmPermRoleParam();
		crmpermroleParam.setStatus(1);// 启用
		
		List<CrmPermRolePO> crmPermRolePOs = iCrmPermRoleService
				.listByParam(crmpermroleParam);
		modelAndView.addObject("crmPermRoleVOs", crmPermRolePOs);
		//用户信息
		CrmUserPO crmUserPO = iCrmUserService.get(CrmUserPO.class, id);
		CrmUserVO crmUserVO=BeanConvertor.copy(crmUserPO, CrmUserVO.class);
		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(id);
		if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
			crmUserVO.setRoleId(crmPermUserRolePO.getRoleId());
		}
		modelAndView.addObject("crmUserVO", crmUserVO);
		return modelAndView;
	}
    
    /**
     * 添加用户
     * @author 黄霄仪
     * @date 2017年7月3日 下午4:20:25
     */
    @RequestMapping(value = "doSave" ,method=RequestMethod.POST)
    @ResponseBody
    public RespModel doSave(@RequestBody CrmUserParam crmUserParam, HttpServletRequest request) {
    	Long sessionUserId = CrmControllerHelper.getSessionUserId();//角色ID
    	crmUserParam.setCreateId(sessionUserId);
    	crmUserParam.setUpdateId(sessionUserId);
    	crmUserParam.setType(CrmControllerHelper.getSessionType());
    	if(CrmAccountUserType.AGENT.getCode()==CrmControllerHelper.getSessionUserType()){
    		crmUserParam.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
       	}
    	try {
    		return iCrmUserService.saveClient(crmUserParam);
		} catch (Exception e) {
			e.printStackTrace();
			return RespModel.failure(e.getMessage());
		}
    }
    /**
     * 修改
     * @author 黄霄仪
     * @date 2017年7月3日 下午4:20:30
     */
	@RequestMapping(value = "doUpdate",method=RequestMethod.POST)
	@ResponseBody
	public RespModel doUpdate(@RequestBody CrmUserParam crmUserParam, HttpServletRequest request) {
		Long sessionUserId = CrmControllerHelper.getSessionUserId();
		RespModel res = RespModel.success("修改成功");
		crmUserParam.setUpdateId(sessionUserId);
		crmUserParam.setUpdateTime(new Date());
		crmUserParam.setType(CrmControllerHelper.getSessionType());
    	if(CrmAccountUserType.AGENT.getCode()==CrmControllerHelper.getSessionUserType()){
    		crmUserParam.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
       	}
		try {
			iCrmUserService.updateEntity(crmUserParam);
		} catch (Exception e) {
			e.printStackTrace();
			return RespModel.failure(e.getMessage());
		}
		return res;
	}
	/**
	 *
	 * 修改密码页面
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/modifyPasswordShow")
	@ResponseBody
	public ModelAndView modifyPasswordShow() {
		LOGGER.info("com.hd.kzscrm.manager.controller.user.CrmUserController.modifyPasswordShow()");
		ModelAndView view = new ModelAndView("/system_set/change_password");
		return view;
	}
	/**
	 * 修改密码
	 * @author 黄霄仪
	 * @date 2017年6月8日 下午8:14:48
	 */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public RespModel moidfyPassword(CrmUserParam crmUserParam){
    	LOGGER.info("com.hd.kzscrm.manager.controller.user.CrmUserController.moidfyPassword(CrmUserParam):crmUserParam"+crmUserParam);
    	RespModel respModel = RespModel.setRespCode(RespCode.SUCCESS);
    	String password = crmUserParam.getPassword();
    	Integer userType = crmUserParam.getUserType();
    	String mobilephone = crmUserParam.getMobilephone();
    	if(BeanUtils.isEmptyAnd(password,userType,mobilephone)){
    		return RespModel.failure("信息不能为空");
    	}
    	String md5 = MD5.md5(password);
    	crmUserParam.setPassword(md5);
    	iCrmUserService.modifyPassword(crmUserParam);
    	return respModel;
    }
    /**
     * 生成修改密码短信验证码
     * @author 黄霄仪
     * @date 2017年6月8日 下午2:45:55
     */
    @RequestMapping("/moidfyPasswordVerificationCode")
	@ResponseBody
	public RespModel generateModifyPasswordVerificationCode(@RequestBody CrmUserParam crmUserParam){
		LOGGER.info("com.hd.qzs.portal.controller.user.UserController.generateVerificationCode(crmUserParam)"+crmUserParam);
		
		String mobilephone = crmUserParam.getMobilephone();//手机号码
		Integer smsType = crmUserParam.getSmsType();//短信类型
		//根据手机号码调用验证码生成接口
		iCrmUserService.sendVerificationCode(mobilephone,smsType);
//		UserDTO userDTO= BeanConvertor.copyInclude(userParam, UserDTO.class, "smsCode");
		return RespModel.setRespCode(RespCode.SUCCESS);
	}
    
    @Resource
	private CacheService cacheService;
    /**
     * 验证短信验证码的正确性
     * @author 黄霄仪
     * @date 2017年6月8日 下午2:45:55
     */
    @RequestMapping("/validVerificationCode")
	@ResponseBody
	public RespModel validVerificationCode(CrmUserParam crmUserParam){
		LOGGER.info("com.hd.qzs.portal.controller.user.UserController.validVerificationCode(crmUserParam)"+crmUserParam);
		String smsCode = crmUserParam.getSmsCode();//输入的验证码
		// 生成验证码放入缓存
		String objType = QzsWebConstants.SYS_KEY_MAIN + ":"
				+ QzsWebConstants.OBJTYPE_USER_SENDVERICODEMSG;
		String mobilephone = crmUserParam.getMobilephone();//手机号码
		//根据手机号码调用验证码生成接口
		String verificationCode = cacheService.getString(objType, mobilephone);
		if(debugModel&&BeanUtils.isEmpty(verificationCode)){
			verificationCode=defaltAuthCode;
		}
		if(BeanUtils.isEmpty(verificationCode)){
			return RespModel.failure("请先获取验证码");
		}else if(BeanUtils.isEmpty(smsCode)){
			return RespModel.failure("请输入验证码");
		}
		if(!smsCode.equals(verificationCode)){
			return RespModel.failure("验证码输入有误，请核对后重试");
		}
		cacheService.del(objType, mobilephone);//删除验证码信息
//		UserDTO userDTO= BeanConvertor.copyInclude(userParam, UserDTO.class, "smsCode");
		return RespModel.setRespCode(RespCode.SUCCESS);
	}
}