package com.hd.kzscrm.service.serviceInter.perm;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.perm.CrmPermMenuPO;
import com.hd.kzscrm.service.param.perm.CrmPermMenuParam;
import com.hd.kzscrm.service.vo.perm.CrmPermMenuVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPermMenuService extends BaseService {
    
        
    public Page<CrmPermMenuVO> queryPage(CrmPermMenuParam param); 
    
        
        
    
    public List<CrmPermMenuPO> listByParam(CrmPermMenuParam crmpermmenuParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPermMenuParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPermMenuPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmPermMenuPO param);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月29日 下午4:39:51
	 */
	void addMenu(CrmPermMenuParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月30日 下午4:12:56
	 */
	List<CrmPermMenuVO> getMenuAndSource(CrmPermMenuParam crmpermmenuParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月3日 下午1:54:38
	 */
	List<CrmPermMenuVO> getAuthority(CrmPermMenuParam crmpermmenuParam);
	/**
	 * 根据菜单id或 父id查询
	* @Title: findByMemuId 
	* @author : lcl
	* @time : 2017年7月6日 上午9:27:09
	* @return CrmPermMenuPO    返回类型 
	* @throws
	 */
	public CrmPermMenuPO findByMemuIdOrParentId(Long menuIdOrParentId);
}
