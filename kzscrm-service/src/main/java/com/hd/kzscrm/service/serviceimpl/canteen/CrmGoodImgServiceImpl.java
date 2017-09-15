package com.hd.kzscrm.service.serviceimpl.canteen;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.canteen.GoodImgPO;
import com.hd.kzscrm.service.param.canteen.GoodImgParam;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmGoodImgService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @Description: 商品图片表
 * @author LuGaogao
 * @date 2017年6月26日 下午5:40:29
 */
@Service
public class CrmGoodImgServiceImpl extends BaseServiceImpl implements
		ICrmGoodImgService {

	/**
	 * 通用查询
	 */
	@Override
	public List<GoodImgPO> commonSelect(GoodImgParam param){
		ParamMap paramMap = new ParamMap(param);
		return commonDao.listByParams(GoodImgPO.class, "GoodImgPOMapper.commonSelect", paramMap);
	}

	/**
	 * 根据goodsId查询信息
	 * @param goodsId
	 * @return
	 */
	@Override
	public List<GoodImgPO> findByGoodsId(Long goodId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("goodId", goodId);
		List<GoodImgPO> lGoodImgPOs = listByParams(GoodImgPO.class, "GoodImgPOMapper.findImgById", paramMap);
		if (BeanUtils.isNotEmpty(lGoodImgPOs)) {
			return lGoodImgPOs;
		}
		return null;
	}
	
	
}
