package com.hd.kzscrm.service.serviceimpl.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hd.kzscrm.dao.entity.business.CanteenExtInfoPO;
import com.hd.kzscrm.service.param.business.CanteenExtInfoParam;
import com.hd.kzscrm.service.serviceInter.business.CanteenExtInfoService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @Description: TODO
 * @author LuGaogao
 * @date 2017年8月4日 上午10:45:14
 */
@Service
public class CanteenExtInfoServiceImpl extends BaseServiceImpl implements CanteenExtInfoService {
	
	/**
	 * 按条件查询商家扩展表 
	 * @param param 
	 * @return
	 * @author jyt 2017年3月7日 上午11:37:21
	 */
	@Override
	public List<CanteenExtInfoPO> findAllByParam(CanteenExtInfoParam param){
		ParamMap paramMap = new ParamMap(param);
		return commonDao.listByParams(CanteenExtInfoPO.class, "CanteenExtInfoMapper.getAll", paramMap);
	}
}
