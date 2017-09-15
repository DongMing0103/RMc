package com.hd.kzscrm.service.serviceimpl.canteen;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hd.kzscrm.dao.entity.canteen.SupplyCategoryPO;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmSupplyCategoryService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @Description: 时段供应类目表
 * @author LuGaogao
 * @date 2017年6月27日 上午9:33:31
 */
@Service
public class CrmSupplyCategoryServiceImpl extends BaseServiceImpl implements
		ICrmSupplyCategoryService {

	/**
	 * 根据供应类目id查询对象
	 */
	@Override
	public List<SupplyCategoryPO> findBySupplyId(Long supplyId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("supplyId", supplyId);
		List<SupplyCategoryPO> supplyCategoryPOs =commonDao.listByParams(SupplyCategoryPO.class, "SupplyCategoryMapper.findBySupplyId", paramMap);
			if(supplyCategoryPOs!=null &&supplyCategoryPOs.size()>0 ){
				
				return supplyCategoryPOs;
			}
			return null;
	}
}
