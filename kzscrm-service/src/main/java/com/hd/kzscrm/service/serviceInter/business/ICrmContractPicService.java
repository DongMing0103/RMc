package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmContractPicPO;
import com.hd.kzscrm.service.param.business.CrmContractPicParam;
import com.hd.kzscrm.service.vo.business.CrmContractPicVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmContractPicService extends BaseService {
    
        
    public Page<CrmContractPicVO> queryPage(CrmContractPicParam param); 
    
        
    public CrmContractPicVO getById(Long id);    
    
    public List<CrmContractPicPO> listByParam(CrmContractPicParam crmcontractpicParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmContractPicParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmContractPicPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmContractPicPO param);
}
