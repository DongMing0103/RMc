package com.hd.kzscrm.service.serviceimpl.enterprise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hd.kzscrm.dao.entity.enterprise.EnterpriseUserPositionPO;
import com.hd.kzscrm.service.param.enterprise.EnterpriseUserPositionParam;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseUserPositionService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @Description: 部门岗位职务表
 * @author LuGaogao
 * @date 2017年6月26日 下午3:35:18
 */
@Service
public class CrmEnterpriseUserPositionServiceImpl extends BaseServiceImpl implements ICrmEnterpriseUserPositionService {

	protected static final Logger logger = LoggerFactory.getLogger(CrmEnterpriseUserPositionServiceImpl.class);
	
	/**
	 * 通用查询
	 */
	@Override
	public List<EnterpriseUserPositionPO> commonSearch(EnterpriseUserPositionParam enterpriseUserPositionParam) {
		ParamMap paramMap=new  ParamMap(enterpriseUserPositionParam);
		return commonDao.listByParams(EnterpriseUserPositionPO.class, "EnterpriseUserPositionMapper.commonSearch",paramMap);
	}
}
