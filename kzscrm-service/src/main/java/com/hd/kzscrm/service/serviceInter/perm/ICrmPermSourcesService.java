package com.hd.kzscrm.service.serviceInter.perm;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPermSourcesService extends BaseService {
    
        
    public Page<CrmPermSourcesVO> queryPage(CrmPermSourcesParam param); 
    
        
        
    
    public List<CrmPermSourcesPO> listByParam(CrmPermSourcesParam crmpermsourcesParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPermSourcesParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPermSourcesParam param);
    
    /**
    update  save
    */
    public void updateEntity(CrmPermSourcesPO param);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月4日 下午5:53:09
	 */
	Page<CrmPermSourcesVO> queryPageBasic(CrmPermSourcesParam param);
	/**
	 * 根据角色id查询 全部按钮资源
	* @Title: findByRoleId 
	* @author : lcl
	* @time : 2017年7月6日 下午4:56:07
	* @return List<CrmPermSourcesPO>    返回类型 
	* @throws
	 */
	public List<CrmPermSourcesPO> findByRoleId(Long roleId);
}
