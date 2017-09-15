package com.hd.kzscrm.manager.controller.login4Business;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.model.CommResponse;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.Consts;
import com.hd.kzscrm.common.util.StringUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentAreaPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessAmtPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermMenuPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleMenuPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.manage.interceptor.Interceptor;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.perm.CrmPermMenuParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentAreaService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessAmtService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.vo.perm.CrmPermMenuVO;
import com.hd.wolverine.controller.base.BaseController;

/**
 * @ClassName: Login4CrmController
 * @Description:登陆控制器
 * @author: crm
 * @since:
 */
@Controller
@RequestMapping("/")
public class Login4CrmController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Login4CrmController.class);

	/**
	 * 用户表
	 */
	@Autowired
	private UserService userService;
	/**
	 * 资源表（页面上的按钮控件）
	 */
	@Autowired
	private ICrmPermSourcesService permSourcesService;

	/**
	 * 业务员资源表
	 */
	@Autowired
	private ICrmClientResourceService crmClientResourceService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
	private ICrmPermSourcesService  pSourcesService;
	/**
	 * 业务员人余额表(包括代理商和业务员)
	 */
	@Resource
	private ICrmBusinessAmtService iCrmBusinessAmtService;
	
	@Resource
	private ICrmBusinessService iCrmBusinessService;
	
	@Resource
	private ICrmTeamService iCrmTeamService;
	@Resource
	private ICrmAgentAreaService iCrmAgentAreaService; 

	/**
	 * @return ModelAndView 返回类型
	 */
	@RequestMapping("/")
	public ModelAndView index() {
		CrmAccountPO accountPO = (CrmAccountPO) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.SESSION_USER_KEY);
		if (accountPO != null) {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("redirect:/login");
	}

	@Resource
	private ICrmPermUserRoleService iCrmPermUserRoleService;
	@Resource
	private ICrmPermRoleMenuService iCrmPermRoleMenuService;
	@Resource
	private ICrmPermMenuService iCrmPermMenuService;
	@Autowired
	private ICrmPermRoleSourcesService roleSourcesService;
	@Resource
	private IBaseAreaService iBaseAreaService;
	/**
	 * @Description:登陆首页
	 * @param request
	 * @return ModelAndView 返回类型
	 */
	@RequestMapping("/index")
	public ModelAndView Index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("adminMgrHome/adminMgrHome");
		Long canteenId = 1L;// CanteenControllerHelper.getCanteenId();
		userService.toIndex(model, canteenId);
		CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
		Date currentDate=new Date();
		Integer userType = sessionUser.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Long userId=sessionUser.getId();
		
		
		if(userType==CrmAccountUserType.AGENT.getCode()){
//			iCrmAgentAreaService
			CrmAgentPO sessionAgentUser = CrmControllerHelper.getSessionAgentUser();
			if(BeanUtils.isNotEmpty(sessionAgentUser)){
				CrmAgentAreaPO crmAgentAreaPO = iCrmAgentAreaService.findByAgentId(sessionAgentUser.getId());
				Long areaCode = crmAgentAreaPO.getAreaCode();
				if(BeanUtils.isNotEmpty(areaCode)){
					List<String> areaNames = iBaseAreaService.getAreaNamesByAreaCode(areaCode);
					if(areaNames.size()>1){
						areaNames.remove(0);
					}
					model.addObject("agentArea", StringUtils.join(areaNames,"-"));
				}
			}
		}
		
		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(userId);//用户，角色映射
		
		//获取用户的可用菜单ID
		Set<Long> menuIdsSet=new HashSet<>();//菜单 IDS
		if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
			Long roleId = crmPermUserRolePO.getRoleId();
			List<CrmPermRoleMenuPO> crmPermRoleMenuPOs = iCrmPermRoleMenuService.findByRoleId(roleId);
			for (CrmPermRoleMenuPO crmPermRoleMenuPO : crmPermRoleMenuPOs) {
				menuIdsSet.add(crmPermRoleMenuPO.getMenuId());
			}
		}
		
		if(userType.equals(3) || userType.equals(4)){
			String userName = new String();
			CrmBusinessPO cPo = iCrmBusinessService.findByUserId(userId);
			if(BeanUtils.isNotEmpty(cPo) && BeanUtils.isNotEmpty(cPo.getTeamId())){
				CrmTeamPO crmTeam = iCrmTeamService.get(CrmTeamPO.class, cPo.getTeamId());
				if(BeanUtils.isNotEmpty(crmTeam) && BeanUtils.isNotEmpty(crmTeam.getName())){
					userName=sessionUser.getUserName()+"<br>"+crmTeam.getName();
					model.addObject("userName", userName);
				}
			}
		}
		model.addObject("crmAccountPO", sessionUser);
		
		if(3 == userType || 4 == userType){////用户类型 3.业务员，4.业务经理
			//查询相应业务员
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setUserId(userId);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
			CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
			List<Long> businessIds = new LinkedList<Long>();
			if(3 == userType){//3.业务员
				Long businessId = crmBusinessPO.getId();
				businessIds.add(businessId);
				
			}else{//4.业务经理
				//查询业务经理所在团队及子孙及团队
				Long teamId = crmBusinessPO.getTeamId();
				CrmTeamParam crmTeamParam = new CrmTeamParam();
				crmTeamParam.setParentId(teamId);
				crmTeamParam.setDelFlag(1);
				List<CrmTeamPO> crmTeamPOs = iCrmTeamService.getChildTeam(crmTeamParam);
				List<Long> teamIds = new LinkedList<Long>();
				for (CrmTeamPO crmTeamPO : crmTeamPOs) {
					teamIds.add(crmTeamPO.getId());
				}
				//查询这些团队的业务员
				CrmBusinessParam businessParam = new CrmBusinessParam();
				businessParam.setTeamIds(teamIds);
				businessParam.setDelFlag(1);
				List<CrmBusinessPO> businessPOs = iCrmBusinessService.commonQuery(businessParam);
				for (CrmBusinessPO businessPO : businessPOs) {
					businessIds.add(businessPO.getId());
				}
			}
				
			//统计相应的保护客户和正式客户
			//查询保护客户
			CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
			crmClientResourceParam.setBusinessIds(businessIds);
			crmClientResourceParam.setDelFlag(1);
			crmClientResourceParam.setClientNature(2);//客户性质 1.散客，2.保护客户，3.正式客户
			
			Integer protectClientNum = crmClientResourceService.countClientNum(crmClientResourceParam);
			
			//查询正式客户
			CrmClientResourceParam clientResourceParam = new CrmClientResourceParam();
			clientResourceParam.setBusinessIds(businessIds);
			clientResourceParam.setDelFlag(1);
			clientResourceParam.setClientNature(3);//客户性质 1.散客，2.保护客户，3.正式客户
			Integer[] status = {2,3,4};//客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			clientResourceParam.setClientTypes(Arrays.asList(status));
			
			Integer formalClientNum = crmClientResourceService.countClientNum(clientResourceParam);
			
			
			Map<String, Object> map = new HashMap<>();
			map.put("protectNum", protectClientNum);
			map.put("formalNum", formalClientNum);
			
			model.addObject("map", map);
		}
		
		
		
		
		//查看当前权限可使用的菜单
		CrmPermMenuParam crmPermMenuParam=new CrmPermMenuParam();
		crmPermMenuParam.setDelFlagAll(1);
		List<CrmPermMenuPO> crmPermMenuPOs = iCrmPermMenuService.listByParam(crmPermMenuParam);
		List<CrmPermMenuVO> crmPermMenuVOs = BeanConvertor.copyList(crmPermMenuPOs, CrmPermMenuVO.class);
		for (CrmPermMenuVO crmPermMenuVO : crmPermMenuVOs) {
			Long id = crmPermMenuVO.getId();
			if(menuIdsSet.contains(id)){
				crmPermMenuVO.setChecked(true);
			}else{
				crmPermMenuVO.setChecked(false);
			}
		}
		
		//创建余额表帐号
		CrmBusinessAmtPO crmBusinessAmtPOTemp = iCrmBusinessAmtService.findByUserId(userId);
		if(BeanUtils.isEmpty(crmBusinessAmtPOTemp)){
			if(BeanUtils.isNotEmpty(userType)&&userType>1){
				CrmBusinessAmtPO crmBusinessAmtPO=new CrmBusinessAmtPO();
				crmBusinessAmtPO.setUserId(userId);
				crmBusinessAmtPO.setBalance(BigDecimal.ZERO);
				crmBusinessAmtPO.setCanWithdrawDeposit(BigDecimal.ZERO);
				crmBusinessAmtPO.setCreateTime(currentDate);
				crmBusinessAmtPO.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
				crmBusinessAmtPO.setCreateUid(userId);
				crmBusinessAmtPO.setUpdateUid(userId);
				crmBusinessAmtPO.setCreateTime(currentDate);
				crmBusinessAmtPO.setUpdateTime(currentDate);
				crmBusinessAmtPO.setTotalConsume(BigDecimal.ZERO);
				crmBusinessAmtPO.setTotalIncome(BigDecimal.ZERO);
				crmBusinessAmtPO.setFrozenMoney(BigDecimal.ZERO);
				if(userType==2){//如果是2就是代理商，否则就是业务员和业务经理
					crmBusinessAmtPO.setUserType(4);
					crmBusinessAmtPO.setAgentId(CrmControllerHelper.getSessionAgentUser().getId());
				}else{
					crmBusinessAmtPO.setUserType(5);
					crmBusinessAmtPO.setBusinessId(CrmControllerHelper.getSessionBusinessUser().getId());
				}
				iCrmBusinessAmtService.saveEntity(crmBusinessAmtPO);
			}
		}
		
		model.addObject("crmPermMenuVOs", crmPermMenuVOs);
		
	
		return model;
	}

	/**
	 * @Description:登陆页
	 * @param request
	 * @return ModelAndView 返回类型
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		CrmAccountPO accountPO = (CrmAccountPO) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.SESSION_USER_KEY);
		if (accountPO != null) {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView();
	}

	/**
	 * @Description:登陆
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return CommResponse 返回类型
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public CommResponse doLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			//角色
			Integer userType= Interceptor.threadLocal.get();
			SecurityUtils.getSubject().login(token);
			String traderLogin = request.getParameter("traderLogin");
			if (StringUtil.isNotEmpty(traderLogin) && traderLogin.equals("1")) {
				String host = request.getServerName();
				Cookie cookie = new Cookie("SESSION_LOGIN_USERNAME", username); // 保存用户名到Cookie
				cookie.setPath("/");
				cookie.setDomain(host);
				cookie.setMaxAge(7 * 24 * 60 * 60); // 7天
				response.addCookie(cookie);
				// 保存密码到Cookie，
				cookie = new Cookie("SESSION_LOGIN_PASSWORD", password);
				cookie.setPath("/");
				cookie.setDomain(host);
				cookie.setMaxAge(7 * 24 * 60 * 60);
				response.addCookie(cookie);
				
				// 保存角色类型到Cookie，
				cookie = new Cookie("SESSION_LOGIN_USERTYPE",userType.toString());
				cookie.setPath("/");
				cookie.setDomain(host);
				cookie.setMaxAge(7 * 24 * 60 * 60);
				response.addCookie(cookie);
			}else{
				//清除COOKIE
				Cookie cookie = new Cookie("SESSION_LOGIN_USERNAME", username);
				cookie.setMaxAge(-1);
				cookie.setPath("/");
				response.addCookie(cookie);
				cookie = new Cookie("SESSION_LOGIN_PASSWORD", password);
				cookie.setPath("/");
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
				cookie = new Cookie("SESSION_LOGIN_USERTYPE", password);
				cookie.setPath("/");
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
			}

			return CommResponse.success(4);
		} catch (Exception ex) {
			LOGGER.error("Login4CenteenController:doLogin:" + ex.getMessage());
			return CommResponse.failure(ex.getCause().getMessage());
		}
	}

	/**
	 * @Description:登出
	 * @return String 返回类型
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/doLogout")
	public String doLogout(HttpServletResponse response) {
		SecurityUtils.getSubject().logout();
		SecurityUtils.getSubject().getSession().removeAttribute(Consts.SESSION_USER_KEY);

		Cookie cookie = new Cookie("SESSION_LOGIN_USERNAME", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		cookie = new Cookie("SESSION_LOGIN_PASSWORD", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		return "redirect:/login";
	}

	/**
	 * @Description:判断用户是否登录
	 * @author LuGaogao
	 * @date 2017年4月26日下午4:10:02
	 * @return ModelAndView 返回类型
	 */
	@RequestMapping(value = "/judgeLogin")
	public ModelAndView judgeLogin() {
		// SecurityUtils.getSubject().getSession().removeAttribute(Consts.SESSION_USER_KEY);
		Object attribute = SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER_KEY);
		if (BeanUtils.isEmpty(attribute)) {
			return new ModelAndView("redirect:/index");
		}
		return null;
	}

}
