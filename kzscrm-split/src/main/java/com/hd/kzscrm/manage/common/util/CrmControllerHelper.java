package com.hd.kzscrm.manage.common.util;

import org.apache.shiro.SecurityUtils;

import com.hd.kzscrm.common.util.Consts;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
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
	
	 /* 获取 session userId
	 * 
	 * @return
	 */
	public static Long getSessionUserId() {
		return getSessionUser().getId();
	}

}
