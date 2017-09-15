package com.hd.kzscrm.service.serviceInter.perm;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleSourcesParam;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleSourcesVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPermRoleSourcesService extends BaseService {
    
        
    public Page<CrmPermRoleSourcesVO> queryPage(CrmPermRoleSourcesParam param); 
    
        
        
    
    public List<CrmPermRoleSourcesPO> listByParam(CrmPermRoleSourcesParam crmpermrolesourcesParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPermRoleSourcesParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPermRoleSourcesPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmPermRoleSourcesPO param);
    /**
     * 根据角色id查询对象
    * @Title: findByRoleId 
    * @author : lcl
    * @time : 2017年7月6日 下午5:18:22
    * @return List<CrmPermRoleSourcesPO>    返回类型 
    * @throws
     */
	public List<CrmPermRoleSourcesPO> findByRoleId(Long roleId);
}
