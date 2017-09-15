package com.hd.kzscrm.service.serviceInter.agent;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.agent.CrmAgentAreaPO;
import com.hd.kzscrm.service.param.agent.CrmAgentAreaParam;
import com.hd.kzscrm.service.vo.agent.CrmAgentAreaVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmAgentAreaService extends BaseService {
    
        
    public Page<CrmAgentAreaVO> queryPage(CrmAgentAreaParam param); 
    
        
        
    
    public List<CrmAgentAreaPO> listByParam(CrmAgentAreaParam crmagentareaParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmAgentAreaParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmAgentAreaPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmAgentAreaPO param);
    
    public CrmAgentAreaPO getById(Long id);
    /**
     * 根据代理商id查询 代理商区域列表
    * @Title: findByAgentId 
    * @author : lcl
    * @time : 2017年6月27日 下午9:12:41
    * @return CrmAgentAreaPO    返回类型 
    * @throws
     */
	public CrmAgentAreaPO findByAgentId(Long agentId);
}
