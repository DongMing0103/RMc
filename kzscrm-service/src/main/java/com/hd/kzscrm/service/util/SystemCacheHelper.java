package com.hd.kzscrm.service.util;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.wolverine.cache.CacheServiceImpl;

/**
 * cache 工具类
 * 
 * @author wing
 *
 */
public class SystemCacheHelper {

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

	 
	
}
