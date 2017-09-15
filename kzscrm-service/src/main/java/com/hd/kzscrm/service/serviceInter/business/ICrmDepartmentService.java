package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmDepartmentPO;
import com.hd.kzscrm.service.param.business.CrmDepartmentParam;
import com.hd.kzscrm.service.vo.business.CrmDepartmentVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmDepartmentService extends BaseService {
    
        
    public Page<CrmDepartmentVO> queryPage(CrmDepartmentParam param); 
    
        
    
    public List<CrmDepartmentPO> listByParam(CrmDepartmentParam crmdepartmentParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmDepartmentParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmDepartmentPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmDepartmentPO param);

	CrmDepartmentPO getById(Long id);
	
	/**
	 * 根据agentId 获取部门信息
	 * @param agentId
	 * @return
	 */
	public  CrmDepartmentPO findByAgentId(Long agentId);
}
