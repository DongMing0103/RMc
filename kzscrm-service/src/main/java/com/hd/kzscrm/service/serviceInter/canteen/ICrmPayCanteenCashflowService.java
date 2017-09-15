package com.hd.kzscrm.service.serviceInter.canteen;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.canteen.CrmPayCanteenCashflowPO;
import com.hd.kzscrm.service.param.canteen.CrmPayCanteenCashflowParam;
import com.hd.kzscrm.service.vo.canteen.CrmPayCanteenCashflowVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPayCanteenCashflowService extends BaseService {
    
        

	public Page<CrmPayCanteenCashflowVO> queryPage(CrmPayCanteenCashflowParam param); 
     
    public List<CrmPayCanteenCashflowPO> listByParam(CrmPayCanteenCashflowParam crmcanteenapplyParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPayCanteenCashflowParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPayCanteenCashflowParam param);
    
    /**
    update  save
    */
    public void updateEntity(CrmPayCanteenCashflowParam param);

	public CrmPayCanteenCashflowPO getById(Long id);
	
	public CrmPayCanteenCashflowPO findByUserId(Long userId);
	
	/**
	 * 添加流水 修改金额
	 * @return
	 */
	public boolean savePayCanteenCashflow(CrmPayCanteenCashflowParam CrmPayCanteenCashflowParam); 
	/**
	 * 查询流水
	 * @param orderid
	 * @param userid
	 * @return
	 */
	public Integer queryPayCanteenCashflow(String orderNo,Long userid);
}
