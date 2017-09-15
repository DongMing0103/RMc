package com.hd.kzscrm.service.serviceInter.enterprise;

import java.util.List;

import com.hd.kzscrm.dao.entity.enterprise.EnterpriseUserPositionPO;
import com.hd.kzscrm.service.param.enterprise.EnterpriseUserPositionParam;
import com.hd.wolverine.service.BaseService;

/**
 * 
 * @ClassName: ICrmEnterpriseUserPositionService 
 * @Description: 部门岗位职务表 
 * @author LuGaogao 
 * @date 2017年6月26日 下午3:36:11 
 *
 */
public interface ICrmEnterpriseUserPositionService extends BaseService {

	/**
	 * 
	 * @Title: commonSearch 
	 * @Description: 通用查询 
	 * @param @param enterpriseUserPositionParam
	 * @param @return  
	 * @return List<EnterpriseUserPositionPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月26日 下午3:39:58
	 */
	List<EnterpriseUserPositionPO> commonSearch(EnterpriseUserPositionParam enterpriseUserPositionParam);
}
