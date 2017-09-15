package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.dao.entity.business.OrderGoodsItemPO;
import com.hd.kzscrm.service.param.business.OrderGoodsItemParam;
import com.hd.wolverine.service.BaseService;
/**
 * 
 * @ClassName: ICrmOrderGoodsItemService 
 * @Description: 订单附属详情
 * @author LuGaogao 
 * @date 2017年6月26日 下午2:07:07 
 *
 */
public interface ICrmOrderGoodsItemService extends BaseService{
	
	/**
	 * 
	 * @Title: findByParams 
	 * @Description: 订单附属详情 
	 * @param @param itemParam
	 * @param @return  
	 * @return List<OrderGoodsItemPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月26日 下午2:25:46
	 */
	List<OrderGoodsItemPO> findByParams(OrderGoodsItemParam itemParam);
}

