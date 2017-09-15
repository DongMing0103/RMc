package com.hd.kzscrm.service.serviceInter.enterprise;

import java.util.List;

import com.hd.kzscrm.dao.entity.enterprise.EnterpriseDepartmentPO;
import com.hd.kzscrm.service.param.enterprise.EnterpriseDepartmentParam;
import com.hd.wolverine.service.BaseService;

/**
 * 
 * @ClassName: ICrmEnterpriseDepartmentService 
 * @Description: 单位部门表 
 * @author LuGaogao 
 * @date 2017年6月26日 下午3:14:42 
 *
 */
public interface ICrmEnterpriseDepartmentService extends BaseService {
	
	/**
	 * 
	 * @Title: findByParam 
	 * @Description: 查询企业部门信息 
	 * @param @param enterpriseDepartmentParam
	 * @param @return  
	 * @return List<EnterpriseDepartmentPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月26日 下午3:20:42
	 */
	List<EnterpriseDepartmentPO> findByParam(EnterpriseDepartmentParam enterpriseDepartmentParam);
}
