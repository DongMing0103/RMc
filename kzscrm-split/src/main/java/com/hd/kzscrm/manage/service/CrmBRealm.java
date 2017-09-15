package com.hd.kzscrm.manage.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.Consts;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.manage.interceptor.Interceptor;
import com.hd.wolverine.common.BusinessException;
import com.hd.wolverine.common.i18n.TranslatorHelper;
import com.hd.wolverine.service.BaseService;
import com.hd.wolverine.util.Const;

//import com.hd.kzscrm.dao.entity.perm.AccountPO;

public class CrmBRealm extends SimpleAccountRealm {
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmBRealm.class);

	@Resource(name = "baseService")
	private BaseService baseService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		CrmAccountPO po = (CrmAccountPO) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER_KEY);
		return doAuthorization(po);
	}

	private AuthorizationInfo doAuthorization(CrmAccountPO account) {
		if (null == account) {
			return null;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 从数据库查询用户角色和权限
		info.addRole("default");
		return info;
	}

	public void clearAuthorizationCache(Long userId) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		principals.add(userId, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BusinessException(TranslatorHelper.getText("用户名或密码不能为空"));
		}
		//角色
		Integer userType= Interceptor.threadLocal.get();
		CrmAccountPO accountPO = new CrmAccountPO();
		// accountPO.setAccount(username);
		accountPO.setMobilephone(username);
		accountPO.setUserType(userType);//CRM
		accountPO.setPassword(password);
		////// wing mark password accountPO.setPassword(password);
		// accountPO.setDelFlag(1);
		// demo 默认登录成功
		CrmAccountPO po = (CrmAccountPO) baseService.getByExample(accountPO);
		if (po == null) {
			throw new BusinessException("用户名或密码不正确");
		} else if (CommUtil.parseLong(po.getDelFlag()) == 0) {
			throw new BusinessException("用户信息已删除或被注销");
		} else if (1 != po.getUserStatus()) {
			throw new BusinessException("账号已被停用，请联系管理员");
		}

		// 用户信息放入session,"session_user"应该定义成常量
		SecurityUtils.getSubject().getSession().setAttribute(Consts.SESSION_USER_KEY, po);
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	public static BaseService getBaseService() {
		if (Const.Application_context.containsBean("baseService")) {
			return (BaseService) Const.Application_context.getBean("baseService");
		} else {
			return (BaseService) Const.Web_Application_context.getBean("baseService");
		}
	}
}
