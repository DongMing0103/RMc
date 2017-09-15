package com.hd.kzscrm.service.serviceimpl.enterprise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hd.kzscrm.dao.entity.enterprise.EnterpriseDepartmentPO;
import com.hd.kzscrm.service.param.enterprise.EnterpriseDepartmentParam;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseDepartmentService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @Description: 单位部门表
 * @author LuGaogao
 * @date 2017年6月26日 下午3:13:12
 */
@Service
public class CrmEnterpriseDepartmentServiceImpl extends BaseServiceImpl implements ICrmEnterpriseDepartmentService {
	
	protected static final Logger logger = LoggerFactory.getLogger(CrmEnterpriseDepartmentServiceImpl.class);
	
	/**
	 * 查询企业部门信息
	 */
	@Override
	public List<EnterpriseDepartmentPO> findByParam(EnterpriseDepartmentParam param) {
		ParamMap paramMap = new ParamMap(param);
		return commonDao.listByParams(EnterpriseDepartmentPO.class, "EnterpriseDepartmentMapper.findByParam", paramMap);
	}
}
