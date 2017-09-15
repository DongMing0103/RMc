package com.hd.kzscrm.service.serviceInter.canteen;

import java.util.List;

import com.hd.kzscrm.dao.entity.canteen.SupplyCategoryPO;
import com.hd.wolverine.service.BaseService;

/**
 * 
 * @ClassName: ICrmSupplyCategoryService 
 * @Description: 时段供应类目表 
 * @author LuGaogao 
 * @date 2017年6月27日 上午9:32:36 
 *
 */
public interface ICrmSupplyCategoryService extends BaseService {
    
	/**
	 * 
	 * @Title: findBySupplyId 
	 * @Description: 根据供应类目id查询对象 
	 * @param @param supplyId
	 * @param @return  
	 * @return List<SupplyCategoryPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月27日 上午9:39:44
	 */
	public List<SupplyCategoryPO> findBySupplyId(Long supplyId);
}
