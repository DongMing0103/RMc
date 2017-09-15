package com.hd.kzscrm.manage.common.util;

import org.apache.shiro.SecurityUtils;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.Consts;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.wolverine.cache.CacheServiceImpl;

/**
 * 系统后台控制器工具类
 * 
 * @author wing
 *
 */
public class SystemControllerHelper {

	// 用户信息放入session,"session_user"应该定义成常量
	static CacheServiceImpl cacheService = new CacheServiceImpl();


	/**
	 * 据KEY生成全局ID 
	 * @param keyIn table名称
	 * @return	
	 */
	public static Long genNextIDValue(DatabaseTableNameEnum databaseTableName) {
		return cacheService.genNextIDValue("KZS", databaseTableName.name());

	}

	 
	/**
	 * 获取 session userId
	 * 
	 * @return
	 */
	public static Long getSessionUserId() {
		return getSessionUser().getId();
	}
 

	/**
	 * 获取 session  用户信息
	 * 
	 * @return
	 */
	public static CrmAccountPO getSessionUser() {
		CrmAccountPO accountPO = (CrmAccountPO) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER_KEY);
		return accountPO;
	}
}
