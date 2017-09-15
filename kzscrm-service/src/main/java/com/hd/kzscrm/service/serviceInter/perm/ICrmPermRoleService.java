package com.hd.kzscrm.service.serviceInter.perm;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.perm.CrmPermRolePO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleParam;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPermRoleService extends BaseService {
    
        
    public Page<CrmPermRoleVO> queryPage(CrmPermRoleParam param); 
    
        
        
    
    public List<CrmPermRolePO> listByParam(CrmPermRoleParam crmpermroleParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPermRoleParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPermRolePO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmPermRoleParam crmPermRoleParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月28日 下午7:40:31
	 */
	void doSave(CrmPermRoleParam crmPermRoleParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月21日 上午10:52:56
	 */
	List<CrmPermRolePO> findByUserType(Integer userType);
}
