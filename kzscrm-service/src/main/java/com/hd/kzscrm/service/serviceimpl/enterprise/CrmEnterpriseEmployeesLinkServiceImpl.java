package com.hd.kzscrm.service.serviceimpl.enterprise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hd.kzscrm.dao.entity.enterprise.EnterpriseEmployeesLinkPO;
import com.hd.kzscrm.service.param.enterprise.EnterpriseEmployeesLinkParam;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseEmployeesLinkService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @Description: 单位员工表
 * @author LuGaogao
 * @date 2017年6月26日 下午3:27:25
 */
@Service
public class CrmEnterpriseEmployeesLinkServiceImpl extends BaseServiceImpl implements ICrmEnterpriseEmployeesLinkService {

	protected static final Logger logger = LoggerFactory.getLogger(CrmEnterpriseEmployeesLinkServiceImpl.class);
	
	/**
	 * 查询员工信息 
	 */
	@Override
	public List<EnterpriseEmployeesLinkPO> findAll(EnterpriseEmployeesLinkParam param){
		ParamMap paramMap=new ParamMap(param);
		return commonDao.listByParams(EnterpriseEmployeesLinkPO.class, "EnterpriseEmployeesLinkMapper.findAll", paramMap);
	}
}
