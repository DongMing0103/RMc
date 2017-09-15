package com.hd.kzscrm.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.hd.kzscrm.common.util.AppUtil;
import com.hd.wolverine.cache.WolverineJedisCluster;

/**
 * redis 工具类
 * 
 * @description TODO
 * @author zyg
 *
 * @date 2017年5月2日 下午2:46:38
 */
public class RedisManager {

	private static  WolverineJedisCluster wolverineJedisCluster = AppUtil
			.getBean(WolverineJedisCluster.class);
	
	private static Gson gson = new Gson();
	private static final String lock_postfix = "_lock"; 
	/**
	 * redis 添加锁 返回锁状态 0有 1无
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static Long lock(String key, String value) {
		return wolverineJedisCluster.setnx(key+lock_postfix, value);
	}
	
	/** 
	 * 延迟解锁
	 * @param key
	 * @param time
	 */
	public static void delayUnLock(String key,Integer time){
		wolverineJedisCluster.expire(key+lock_postfix, time);
	}
	
	/** 
	 * 设置锁过期时间 返回锁状态 0有 1无
	 * @author 黄霄仪
	 * @date 2017年5月4日 下午3:03:08
	 */
	public static Long lock(String key, String value,int timeOut) {
		Long setnx = wolverineJedisCluster.setnx(key+lock_postfix, value);
		//如果是0就返回0
		if(setnx==0){
			return 0l;
		}
		wolverineJedisCluster.expire(key+lock_postfix, timeOut);
		return setnx;
	}
	/** 
	 * 0.Key不存在或者不能为key设置生存时间返回0，1.成功返回1
	 * @author 黄霄仪
	 * @date 2017年5月4日 下午2:28:45
	 */
	public static Long unlock(String key){
		return wolverineJedisCluster.expire(key+lock_postfix,-1);
	}
	/**
	 * redis 添加Map
	 * 
	 * @param key
	 * @param hash
	 */
	public static void setMap(String key, Map<String, String> hash) {
		wolverineJedisCluster.hmset(key, hash);
	}
	/**
	 * 添加Map到redis
	 * 
	 * @param key
	 * @param hash
	 */
	public static void setMap(Map<String, String> hash, String paramKey,
			String prefix, String postfix) {
		setMap(prefix + hash.get(paramKey), hash); 
		setKeyList(prefix + postfix, prefix + hash.get(paramKey));
	}
	
	
	
	/**
	 * redis 添加object
	 * @param object
	 * @param key
	 * @return
	 */
	public static String setObject(String key,Object object) {
		return wolverineJedisCluster.set(key, gson.toJson(object));
		//return wolverineJedisCluster.set(key.getBytes(), SerializeUtil.serialize(object));
	}

	/**
	 * redis 添加object
	 * @param object 参数entity 
	 * @param paramKey 参数值  如： orderid 主键编号 
	 * @param prefix  前缀 
	 * @param postfix 后缀
	 */
	public static void setObject(Object object, String paramKey,
			String prefix, String postfix) {
		setObject(prefix + paramKey, object);
		wolverineJedisCluster.expire(prefix + paramKey,3*24*60*60*1000);
		setKeyList(prefix + postfix, prefix + paramKey);
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public static boolean exists(String key){
		return wolverineJedisCluster.exists(key);
	}
	/**\
	 * 从redis查询 object 
	 * @param key
	 * @return
	 */
	public static <T> T getObject(String key, Class<T> type) {
		String value = wolverineJedisCluster.get(key);
		return gson.fromJson(value, type);
		//return SerializeUtil.unserialize(value);
	}

	/** delete a key **/
	public static boolean del(String key) {
		return wolverineJedisCluster.del(key.getBytes()) > 0;
	}

	/**
	 * 根据list内值删除
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 */
	public static Long delListByKey(String key,Long count,String value){
		return wolverineJedisCluster.lrem(key, count, value);
	}
	
	/**
	 * 查询listkey
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getList(String key,Long start,Long end){
		return wolverineJedisCluster.lrange(key, start, end);
	}
	
	
	/**
	 * key list 存储
	 * 
	 * @param key
	 * @param value
	 */
	public static void setKeyList(String key, String value) {
		wolverineJedisCluster.lpush(key, value);
	}

	/**
	 * 从redis 获取单个Map
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, String> getMap(String key) {
		return wolverineJedisCluster.hgetAll(key);

	}

	 
	/**
	 * 添加list到 redis
	 * 
	 * @param list
	 * @param paramKey
	 *            list map 参数 例 订单编号 主键编号
	 * @param prefix
	 *            前缀
	 */
	public static void setList(List<Map<String, String>> list, String paramKey,
			String prefix, String postfix) {
		for (Map<String, String> map : list) {
			setMap(prefix + map.get(paramKey), map);
			setKeyList(prefix + postfix, prefix + map.get(paramKey));
		}
	}
	/**
	 * 添加list到 redis
	 * 
	 * @param list
	 * @param paramKey
	 *            list map 参数 例 订单编号 主键编号
	 * @param prefix
	 *            前缀
	 */
	// public static void setList(List<Map<String,String>> list,String
	// paramKey,String prefix){
	// for (Map<String,String> map:list) {
	// wolverineJedisCluster.hmset(prefix+map.get(paramKey),map);
	// }
	// }
	/**
	 * 通过前缀获取 List
	 * 
	 * @param prefix
	 *            前缀
	 * @param lenth
	 *            条数
	 * @return
	 */
	public static List<Map<String, String>> getList(String prefix, Long length,
			String postfix) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Long num = getListLength(prefix + postfix);
		if (length >= num || length == 0) {
			length = num;
		}
		List<String> keyList = wolverineJedisCluster.lrange(prefix + postfix,
				0, length);
		for (String key : keyList) {
			list.add(getMap(key));
		}
		return list;
	}

	/**
	 * 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
	 * 
	 * @param key
	 * @return String
	 */
	public static String lpop(String key) {
		return wolverineJedisCluster.lpop(key);
	}

	/**
	 * 通过前缀获取 List
	 * 
	 * @param prefix
	 *            前缀
	 * @param lenth
	 *            条数
	 * @return
	 */
	// public static List<Map<String,String>> getList(String prefix, Integer
	// lenth) {
	// List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	// Set<String> set = wolverineJedisCluster.keys(prefix +"*");
	// Iterator<String> it = set.iterator();
	// int i= 0;
	// while(it.hasNext()){
	// if(lenth!=null && lenth!=0 && lenth<=i){
	// break;
	// }
	// String keyStr = it.next();
	// Map<String, String> map = wolverineJedisCluster.hgetAll(keyStr);
	// list.add(map);
	// i++;
	// }
	// return list;
	// }

	/**
	 * 获取list 长度
	 * 
	 * @param key
	 * @return
	 */
	public static Long getListLength(String key) {
		return wolverineJedisCluster.llen(key);
	}

	/**
	 * 删除key
	 * 
	 * @param key
	 */
	public static void delKey(String key) {
		wolverineJedisCluster.del(key);
	}

}
