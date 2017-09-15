package com.hd.kzscrm.service.serviceInter.business;

import java.math.BigDecimal;
import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmBusinessSplitDetailPO;
import com.hd.kzscrm.service.param.business.CrmBusinessSplitDetailParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessSplitDetailVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmBusinessSplitDetailService extends BaseService {
    
        
    public Page<CrmBusinessSplitDetailVO> queryPage(CrmBusinessSplitDetailParam param); 
    
        
        
    
    public List<CrmBusinessSplitDetailPO> listByParam(CrmBusinessSplitDetailParam crmbusinesssplitdetailParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmBusinessSplitDetailParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmBusinessSplitDetailPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmBusinessSplitDetailPO param);

	public CrmBusinessSplitDetailPO getById(Long id); 
	/**
	 * 根据订单查询订单是否存在
	 * @param orderNo
	 * @return
	 */
	public Integer queryCrmBusinessSplitDetail(String orderNo); 
	
	
	
	/**
	 * 新增订单业务分账明细
	 * @param orderNo
	 * @param bussinessId
	 * @param agentId
	 * @param bussinessMoney
	 * @param agentMoney
	 * @param platfromMoney
	 * @return
	 */
	public Integer saveCrmBusinessSplitDetail(String orderNo,Long bussinessId,Long agentId,BigDecimal businessMoney,BigDecimal agentMoney,BigDecimal platformMoney,Integer payModel);
	
	/**
	 * 更新订单状态
	 * @param orderNo
	 * @param status
	 * @return
	 */
	public Integer updateCrmBusinessSplitDetail(String orderNo,Integer status);
	
	/**
	 * 根据订单编号模糊查询
	 */
	public List<CrmBusinessSplitDetailPO> findLikeByOrderNo(String condition);
	
	/**
	 * 导出查询
	 * @param param
	 * @return
	 */
	public Page<CrmBusinessSplitDetailVO> findPageSelect(CrmBusinessSplitDetailParam param);
	
	/**
	 * 根据订单编号查询分账信息
	 */
	CrmBusinessSplitDetailPO findByOrderNo(String orderNo);
	List<CrmBusinessSplitDetailPO> findByOrderNos(String orderNo);


   /**
    * 查询平台分账明细
    * @param param
    * @return
    */
	public Page<CrmBusinessSplitDetailVO> findPageByParams(CrmBusinessSplitDetailParam param);
	
	/**
	 * 查询状态
	 * @param param
	 * @return
	 */
	Page<CrmBusinessSplitDetailVO> queryPageList(CrmBusinessSplitDetailParam param);
	
	/**
	 * 根据代理商编号查询代理商金额
	 * @param agentNo
	 * @return
	 */
	CrmBusinessSplitDetailPO findByAgentNo(String agentNo);
	
}
