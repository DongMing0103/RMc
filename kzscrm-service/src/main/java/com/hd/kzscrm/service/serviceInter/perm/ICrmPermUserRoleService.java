package com.hd.kzscrm.service.serviceInter.perm;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.service.param.perm.CrmPermUserRoleParam;
import com.hd.kzscrm.service.vo.perm.CrmPermUserRoleVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPermUserRoleService extends BaseService {
    
        
    public Page<CrmPermUserRoleVO> queryPage(CrmPermUserRoleParam param); 
    
        
        
    
    public List<CrmPermUserRolePO> listByParam(CrmPermUserRoleParam crmpermuserroleParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPermUserRoleParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPermUserRoleParam crmPermUserRoleParam);
    
    /**
    update  save
    */
    public void updateEntity(CrmPermUserRolePO param);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月3日 下午5:01:27
	 * @param userId crm_user表的ID
	 */
	CrmPermUserRolePO findByUserId(Long userId);
}
