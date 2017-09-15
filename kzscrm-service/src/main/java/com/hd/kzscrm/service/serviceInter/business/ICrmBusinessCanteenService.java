package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmBusinessCanteenPO;
import com.hd.kzscrm.service.param.business.CrmBusinessCanteenParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessCanteenVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmBusinessCanteenService extends BaseService {
    
        
    public Page<CrmBusinessCanteenVO> queryPage(CrmBusinessCanteenParam param); 
    
        
        
    
    public List<CrmBusinessCanteenPO> listByParam(CrmBusinessCanteenParam crmbusinesscanteenParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmBusinessCanteenParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmBusinessCanteenPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmBusinessCanteenPO param);

	public CrmBusinessCanteenPO getById(Long id);



	/**
	 * 
	 * @Title: commonCount 
	 * @Description: 根据参数count(0) 
	 * @param @param businessCanteenParam
	 * @param @return  
	 * @return Integer    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年5月31日 下午5:42:10
	 */
	public Integer commonCount(CrmBusinessCanteenParam businessCanteenParam);
}
