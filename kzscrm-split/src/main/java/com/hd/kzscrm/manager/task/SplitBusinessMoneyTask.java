package com.hd.kzscrm.manager.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.kzscrm.common.util.AppUtil;
import com.hd.kzscrm.manage.common.SplitConstants;
import com.hd.kzscrm.manager.delayed.SplitOrderEnum;
import com.hd.kzscrm.manager.singleton.SplitSingleton;
import com.hd.kzscrm.service.param.canteen.CrmCanteenApplyParam;
import com.hd.kzscrm.service.param.canteen.CrmPayCanteenCashflowParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessSplitDetailService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmPayCanteenCashflowService;
import com.hd.kzscrm.service.util.RedisManager;
 
/**
 * 代理商分账
 * @description TODO
 * @author zyg
 *
 * @date 2017年6月7日 下午8:23:29
 */
public class SplitBusinessMoneyTask implements Runnable{
	private CrmPayCanteenCashflowParam crmPayCanteenCashflowParam; 
	private static ICrmBusinessSplitDetailService iCrmBusinessSplitDetailService = AppUtil.getBean(ICrmBusinessSplitDetailService.class);
	private static  ICrmPayCanteenCashflowService iCrmPayCanteenCashflowService = AppUtil.getBean(ICrmPayCanteenCashflowService.class);
	//分账单例类
	private static SplitSingleton splitSingleton = SplitSingleton.getInstance();
	//日志 
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());	
	public SplitBusinessMoneyTask(CrmPayCanteenCashflowParam crmPayCanteenCashflowParam){
		this.crmPayCanteenCashflowParam = crmPayCanteenCashflowParam;
	}
	
	@Override
	public void run() {
		try{
			String splitBusinessMoneyKey =  SplitConstants.SPLITBUSINESSMANAGER+crmPayCanteenCashflowParam.getUserId();
			Long i = RedisManager.lock(splitBusinessMoneyKey,String.valueOf(1),10); 
			if(i==1){
				//判断流水是否存在 
				Integer num = iCrmPayCanteenCashflowService.queryPayCanteenCashflow(crmPayCanteenCashflowParam.getOrderNo(),crmPayCanteenCashflowParam.getUserId());
				if(num>0){ 
					RedisManager.unlock(splitBusinessMoneyKey);
					return; 
				} 
				crmPayCanteenCashflowParam.setUserType(4);
				//判断流水是否生成
				iCrmPayCanteenCashflowService.savePayCanteenCashflow(crmPayCanteenCashflowParam);
				iCrmBusinessSplitDetailService.updateCrmBusinessSplitDetail(crmPayCanteenCashflowParam.getOrderNo(), 4);
				RedisManager.unlock(splitBusinessMoneyKey);
			}else{
				splitSingleton.put(100L, new SplitBusinessMoneyTask(crmPayCanteenCashflowParam),  crmPayCanteenCashflowParam.getId(),SplitOrderEnum.SPLIT_ORDER_BUSINESS_FINISH);
			}
			
		} catch (Throwable t) {
			// TODO: handle exception
			LOG.error("SplitGenerateOrderCutTask 异常"+t);
		}
	}


}
