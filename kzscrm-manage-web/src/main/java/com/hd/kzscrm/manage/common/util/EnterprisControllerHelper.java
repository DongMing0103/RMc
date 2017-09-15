package com.hd.kzscrm.manage.common.util;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.SessionUtil;
import com.hd.kzscrm.dao.entity.perm.EnterpriseAccountPO;
import com.hd.wolverine.cache.CacheServiceImpl;

/**
 * 企业端控制器工具类
 * 
 * @author wing
 *
 */
public class EnterprisControllerHelper {

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
	 * 获取 session 商户端用户信息
	 * 
	 * @return
	 */
	public static EnterpriseAccountPO getSessionUser() {
		EnterpriseAccountPO accountPO = (EnterpriseAccountPO) SessionUtil.getUserSession();

		return accountPO;
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
	 * 获取 session 企业id
	 * 
	 * @return
	 */
	public static Long getEnterprisId() {
		return getSessionUser().getEnterprisePO().getId();
	}

}
