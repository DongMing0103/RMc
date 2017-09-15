package com.hd.kzscrm.service.serviceimpl.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hd.kzscrm.dao.entity.business.OrderGoodsItemPO;
import com.hd.kzscrm.service.param.business.OrderGoodsItemParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderGoodsItemService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @Description: 订单附属详情
 * @author LuGaogao
 * @date 2017年6月26日 下午2:05:27
 */
@Service
public class CrmOrderGoodsItemServiceImpl extends BaseServiceImpl implements ICrmOrderGoodsItemService {

	private static final Logger logger = LoggerFactory.getLogger(CrmOrderGoodsItemServiceImpl.class);
	/**
	 * 订单附属详情
	 */
	@Override
	public List<OrderGoodsItemPO> findByParams(OrderGoodsItemParam itemParam) {
		ParamMap paramMap = new ParamMap(itemParam);
		return commonDao.listByParams(OrderGoodsItemPO.class, "OrderGoodsItemMapper.findAll", paramMap);
	}
}
