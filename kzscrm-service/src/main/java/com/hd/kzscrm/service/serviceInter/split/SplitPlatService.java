package com.hd.kzscrm.service.serviceInter.split;

import java.util.List;
import java.util.Map;

import com.hd.kzscrm.service.param.split.SplitCutInfoParam;

/**
 * 
 * @description TODO
 * @author zyg
 *
 * @date 2017年6月1日 下午2:07:54
 */
public interface SplitPlatService {
	
	/**
	 * 返回已经结算过的订单
	 * @return
	 */
	public List<SplitCutInfoParam> queryCutInfo(SplitCutInfoParam splitCutInfoParam);
	
	/**
	 * 获取商家比例 业务员比例 代理商比例
	 * @return
	 */
	public Map<String,String> queryCanteen();
	
	/**
	 * 生成资金流水
	 * @return
	 */
	public Integer saveCapitalFlow();
	
	/**
	 * 分账信息存储
	 * @return
	 */
	public Integer saveSplitInfo();
}
	
