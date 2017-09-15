package com.hd.kzscrm.manage.common.util;

import org.apache.shiro.SecurityUtils;

import com.hd.kzscrm.common.util.Consts;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.wolverine.cache.CacheServiceImpl;

/**
 * 商户端 控制器工具类
 * 
 * @author wing
 *
 */
public class CrmControllerHelper {

	// 用户信息放入session,"session_user"应该定义成常量

	static CacheServiceImpl cacheService = new CacheServiceImpl();

	/**
	 * 据KEY生成全局ID
	 * 
	 * @param keyIn
	 *            table名称
	 * @return
	 */
	public static Long genNextIDValue(String keyIn) {
		return cacheService.genNextIDValue("KZS", keyIn);
	}
	/**
	 * 获取 session 商户端用户信息(对象)
	 * 
	 * @return
	 */
	public static CrmAccountPO getSessionUser() {
		CrmAccountPO accountPO = (CrmAccountPO) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.SESSION_USER_KEY);
		return accountPO;
	}
	
	/**
	 * 获取 session 当前登录的代理商用户
	 * 
	 * @return
	 */
	public static CrmAgentPO getSessionAgentUser() {
		CrmAgentPO crmAgentPO = (CrmAgentPO) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.SESSION_AGENT_KEY);
		return crmAgentPO;
	}
	/**
	 * 获取用户类型
	 * @author 黄霄仪
	 * @date 2017年6月5日 上午9:33:44
	 */
	public static Integer getSessionUserType(){
		return getSessionUser().getUserType();
	}
	/**
	 * 帐户类型：1.平台，2.代理商
	 * @author 黄霄仪
	 * @date 2017年6月5日 上午10:07:52
	 */
	public static Integer getSessionType(){
		return getSessionUser().getType();
	}
	/**
	 * 获取 session 当前登录的业务员用户
	 * 
	 * @return
	 */
	public static CrmBusinessPO getSessionBusinessUser() {
		CrmBusinessPO crmBusinessPO = (CrmBusinessPO) SecurityUtils.getSubject().getSession()
				.getAttribute(Consts.SESSION_BUSINESS_KEY);
		return crmBusinessPO;
	}
	
	 /* 获取 session userId
	 * 
	 * @return
	 */
	public static Long getSessionUserId() {
		return getSessionUser().getId();
	}

}
