package com.hd.kzscrm.service.serviceInter.business;

import java.math.BigDecimal;
import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmBusinessOrderPO;
import com.hd.kzscrm.service.param.business.CrmBusinessOrderParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessOrderVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmBusinessOrderService extends BaseService {
    
        
    public Page<CrmBusinessOrderVO> queryPage(CrmBusinessOrderParam param); 
    
        
        
    
    public List<CrmBusinessOrderPO> listByParam(CrmBusinessOrderParam crmbusinessorderParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmBusinessOrderParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmBusinessOrderPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmBusinessOrderPO param);




	public CrmBusinessOrderPO getById(Long id);



	/**
	 * 
	 * @Title: sumOrderRealMoney 
	 * @Description: 根据条件统计订单实际付款金额 
	 * @param @param businessOrderParam
	 * @param @return  
	 * @return BigDecimal    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年5月31日 下午6:03:57
	 */
	public BigDecimal sumOrderRealMoney(CrmBusinessOrderParam businessOrderParam);
	
	/**
	 * 获取订单数量
	 */
	public BigDecimal countOrderId(Long businessId);

	/**
	 * 根据业务员ID查询对象
	 * @param businessId
	 * @return
	 */
	public CrmBusinessOrderPO findByBusinessId(Long businessId);

	/**
	 * 根据订单编号模糊查询对象
	 * @param condition
	 * @return
	 */
	public List<CrmBusinessOrderPO> findLikeByOrderId(String condition);
}
