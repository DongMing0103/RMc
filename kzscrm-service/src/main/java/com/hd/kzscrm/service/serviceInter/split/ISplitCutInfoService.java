package com.hd.kzscrm.service.serviceInter.split;

import java.math.BigDecimal;
import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.kzscrm.service.param.business.OrderParam;
import com.hd.kzscrm.service.param.split.SplitCutInfoParam;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.kzscrm.service.vo.split.SplitCutInfoVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;

public interface ISplitCutInfoService extends BaseService {
    
        
    public Page<SplitCutInfoVO> queryPage(SplitCutInfoParam param); 
    
        
        
    /**鏍规嵁涓婚敭鏌ヨ璇︽儏*/
    public List<SplitCutInfoPO> listByParam(SplitCutInfoParam splitcutinfoParam);
    
    /**鏍规嵁id鍒犻櫎*/
    public void deleteById(Long id)throws BizException;
    
    /**涓彴鏍规嵁id鍒犻櫎*/
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(SplitCutInfoParam param);
    /**
     * 根据订单编号新增退款信息
     * @param orderNo
     */
    public void isnertSplitCutInfoByOrderNO(String orderNo);
    
    /**
     * 更新抽成明细状态
     * @param param
     * @return
     */
    public int updateSplitCutInfoStatus(SplitCutInfoParam param);
    
    /**
     * 获取时间戳到当前时间的结算后的商家金额
     * @param canteenId 商家编号
     * @param payTimestamp 时间戳
     * @return
     */
    public BigDecimal queryMoneyByCanteenId(Long canteenId,String payTimestamp);
    /**
   	 * 查询订单号是否存在
   	 * @param splitOrderSuccessParam
   	 * @return
   	 */
   	public Integer queryOrderNoISNull(SplitCutInfoParam param); 
   	
	/**
   	 * 异常列表
   	 * @param param
   	 * @return
   	 */
   	public  Page<SplitCutInfoParam> queryErrorPage(SplitCutInfoParam param);


   	/**
   	 * 获取分账订单【审核提现】
   	 * @param splitCutInfoParam 分账参数
   	 * @return
   	 */
	public Page<SplitCutInfoPO> querySplitOrderCutInfo(SplitCutInfoParam splitCutInfoParam);

	/**
   	 * 获取分账订单【审核提现】
   	 * @param splitCutInfoParam 分账参数
   	 * @return
   	 */
	public List<SplitCutInfoPO> listSplitOrderCutInfo(SplitCutInfoParam splitCutInfoParam); 
	
	/**
	 * 查询订单基础信息
	 */
	public Page<OrderVO> queryPage(OrderParam param);
	
	/**
	 * 根据订单编号查询
	 * @param orderNo
	 * @return
	 */
	SplitCutInfoPO findByOrderNo (String orderNo);
}
