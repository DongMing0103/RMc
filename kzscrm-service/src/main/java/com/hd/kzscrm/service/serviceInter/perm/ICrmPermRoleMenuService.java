package com.hd.kzscrm.service.serviceInter.perm;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleMenuPO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleMenuParam;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleMenuVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPermRoleMenuService extends BaseService {
    
        
    public Page<CrmPermRoleMenuVO> queryPage(CrmPermRoleMenuParam param); 
    
        
        
    
    public List<CrmPermRoleMenuPO> listByParam(CrmPermRoleMenuParam crmpermrolemenuParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPermRoleMenuParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPermRoleMenuPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmPermRoleMenuPO param);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月3日 上午11:52:21
	 */
	void delByRoleId(Long roleId);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月3日 下午1:50:19
	 */
	List<CrmPermRoleMenuPO> findByRoleId(Long roleId);
}
