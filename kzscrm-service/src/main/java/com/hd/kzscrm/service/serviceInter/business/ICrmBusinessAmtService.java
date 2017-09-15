package com.hd.kzscrm.service.serviceInter.business;

import java.math.BigDecimal;
import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmBusinessAmtPO;
import com.hd.kzscrm.service.param.business.CrmBusinessAmtParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessAmtVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmBusinessAmtService extends BaseService {
    
        
    public Page<CrmBusinessAmtVO> queryPage(CrmBusinessAmtParam param); 
    
        
        
    
    public List<CrmBusinessAmtPO> listByParam(CrmBusinessAmtParam crmbusinessamtParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmBusinessAmtParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmBusinessAmtPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmBusinessAmtPO param);

	public CrmBusinessAmtPO getById(Long id);
	/**
	 * 根据业务编号查询资金
	 * @param businessId
	 * @return
	 */
	public CrmBusinessAmtPO findByBusinessId(Long businessId,Integer usertype);
	
	/**
	 * 修改金额
	 * @param param
	 * @return
	 */
	public Integer updateCrmBusinessAmt(Long businessId,BigDecimal canWithdrawDeposit,Integer userType);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月24日 下午2:07:52
	 */
	CrmBusinessAmtPO findByUserId(Long userId);
}
