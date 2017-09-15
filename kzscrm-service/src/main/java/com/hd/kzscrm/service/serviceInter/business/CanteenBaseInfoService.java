package com.hd.kzscrm.service.serviceInter.business;

import com.hd.wolverine.service.BaseService;

/**
 * 商家信息
 * @author Administrator
 *
 */
public interface CanteenBaseInfoService extends BaseService{

	/**
	 * @author 黄霄仪
	 * @date 2017年8月1日 下午7:39:16
	 */
	String getCanteenNo(Long id, int length, String eStyle, Long areaCode);

	/**
	 * @author 黄霄仪
	 * @date 2017年8月1日 下午7:41:34
	 */
	String getAreaName(Long id);
	
}
