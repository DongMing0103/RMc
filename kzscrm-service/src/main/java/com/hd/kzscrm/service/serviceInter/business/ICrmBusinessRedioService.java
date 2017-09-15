package com.hd.kzscrm.service.serviceInter.business;

import java.math.BigDecimal;
import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmBusinessRedioPO;
import com.hd.kzscrm.service.param.business.CrmBusinessRedioParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessRedioVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmBusinessRedioService extends BaseService {
    
        
    public Page<CrmBusinessRedioVO> queryPage(CrmBusinessRedioParam param); 
    
        
        
    
    public List<CrmBusinessRedioPO> listByParam(CrmBusinessRedioParam crmbusinessredioParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmBusinessRedioParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmBusinessRedioPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmBusinessRedioPO param);
    
    /**
     * 根据业务获取比例
     * @param year
     * @param type
     */
    public BigDecimal queryBusinessRedio(Integer year, Integer type);
    
    public Integer queryByOrderNo(String orderNo);
}
