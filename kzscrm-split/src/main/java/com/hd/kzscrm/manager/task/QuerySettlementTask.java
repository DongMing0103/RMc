package com.hd.kzscrm.manager.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.kzscrm.common.enums.SplitOrderStatusEnum;
import com.hd.kzscrm.common.util.AppUtil;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.DateUtil;
import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.kzscrm.manager.delayed.SplitOrderEnum;
import com.hd.kzscrm.manager.singleton.SplitSingleton;
import com.hd.kzscrm.service.param.split.SplitCutInfoParam;
import com.hd.kzscrm.service.serviceInter.split.ISplitCutInfoService;

/**
 * 处理业务员代理商分账
 * @description TODO
 * @author zyg
 *
 * @date 2017年6月1日 上午10:47:21
 */
public class QuerySettlementTask implements Runnable{
	//分账单例类
	private static SplitSingleton splitSingleton = SplitSingleton.getInstance();
	//订单抽成明细业务处理类
	private static ISplitCutInfoService sSplitCutInfoService = AppUtil.getBean(ISplitCutInfoService.class);
	//日志 
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Override
	public void run() {
		try{
			SplitCutInfoParam splitCutInfoParam = new SplitCutInfoParam(); 
			//t1 一天前当前时间
			splitCutInfoParam.setCreateTime(DateUtil.getYesterday());
			//t1 前天以前的数据不管了
			splitCutInfoParam.setStratTimes(DateUtil.getYesterdayS()); 
			splitCutInfoParam.setBusinessCutStatus(0);
			//结算状态
			splitCutInfoParam.setCutStatus(SplitOrderStatusEnum.ORDER_CUT_OVER.getStatusCode());
			splitCutInfoParam.setLimit(200);
			List<SplitCutInfoPO> splitCutInfoPOList =  sSplitCutInfoService.listByParam(splitCutInfoParam);
			int i = 0;
			for(SplitCutInfoPO po:splitCutInfoPOList){
				SplitCutInfoParam param = BeanConvertor.copy(po,SplitCutInfoParam.class);
				i ++;
				//放入队列
				splitSingleton.put(i*10, new SplitExecuteBussinessTask(param), param.getId(),SplitOrderEnum.SPLIT_QUERYORDER_BUSINESS_FINISH);
				
			  
			}
		} catch (Throwable t) {
			// TODO: handle exception
			LOG.error("SplitGenerateOrderCutTask 异常"+t);
		}
	}

}
