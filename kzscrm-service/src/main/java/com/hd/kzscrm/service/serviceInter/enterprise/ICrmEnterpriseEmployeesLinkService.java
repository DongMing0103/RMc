package com.hd.kzscrm.service.serviceInter.enterprise;

import java.util.List;

import com.hd.kzscrm.dao.entity.enterprise.EnterpriseEmployeesLinkPO;
import com.hd.kzscrm.service.param.enterprise.EnterpriseEmployeesLinkParam;
import com.hd.wolverine.service.BaseService;

/**
 * 
 * @ClassName: ICrmEnterpriseEmployeesLinkService 
 * @Description: 单位员工表 
 * @author LuGaogao 
 * @date 2017年6月26日 下午3:28:22 
 *
 */
public interface ICrmEnterpriseEmployeesLinkService extends BaseService {
	
	/**
	 * 
	 * @Title: findAll 
	 * @Description: 查询员工信息 
	 * @param @param enterpriseEmployeesLinkParam
	 * @param @return  
	 * @return List<EnterpriseEmployeesLinkPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月26日 下午3:31:20
	 */
	List<EnterpriseEmployeesLinkPO> findAll(EnterpriseEmployeesLinkParam enterpriseEmployeesLinkParam);
}
