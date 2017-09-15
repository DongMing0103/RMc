package com.hd.kzscrm.manager.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.kzscrm.common.util.AppUtil;
import com.hd.kzscrm.common.util.DateUtil;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.kzscrm.manage.common.SplitConstants;
import com.hd.kzscrm.manager.delayed.SplitOrderEnum;
import com.hd.kzscrm.manager.singleton.SplitSingleton;
import com.hd.kzscrm.service.param.canteen.CrmPayCanteenCashflowParam;
import com.hd.kzscrm.service.param.split.SplitCutInfoParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessRedioService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessSplitDetailService;
import com.hd.kzscrm.service.serviceInter.split.ISplitCutInfoService;
import com.hd.kzscrm.service.util.RedisManager;
 
/**
 * 订单分账明细处理
 * @description TODO
 * @author zyg
 *
 * @date 2017年6月1日 上午11:14:30
 */
public class SplitExecuteBussinessTask implements Runnable{
	/**
	 * 分账明细
	 */
	private SplitCutInfoParam  splitCutInfoParam;
	//分账单例类
	private static SplitSingleton splitSingleton = SplitSingleton.getInstance();
	private static ICrmSplitAssignSetService iCrmSplitAssignSetService = AppUtil.getBean(ICrmSplitAssignSetService.class); 
	private static ICrmBusinessSplitDetailService iCrmBusinessSplitDetailService = AppUtil.getBean(ICrmBusinessSplitDetailService.class);
	//订单抽成明细业务处理类
	private static ISplitCutInfoService sSplitCutInfoService = AppUtil.getBean(ISplitCutInfoService.class);
	
	ICrmBusinessRedioService iCrmBusinessRedioService = AppUtil.getBean(ICrmBusinessRedioService.class);
	//日志 
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	public SplitExecuteBussinessTask(SplitCutInfoParam  splitCutInfoParam){
		this.splitCutInfoParam = splitCutInfoParam;
	}
	
	@Override
	public void run() {
		try{
			Long orderId = splitCutInfoParam.getId();
			String orderNo = splitCutInfoParam.getOrderNo();
			String splitExecuteBussinessKey = SplitConstants.SPLITBUSINESSMANAGER+orderNo;
			Long i = RedisManager.lock(splitExecuteBussinessKey,String.valueOf(1),10);
			if(i ==1){ 
				SplitCutInfoPO splitCutInfoPO = sSplitCutInfoService.get(SplitCutInfoPO.class, orderId); 
				if (splitCutInfoParam.getCutStatus() != splitCutInfoPO.getCutStatus()) {
					LOG.info("---error end---处理订单业务分账，数据库订单状态不匹配，订单号为：SplitExecuteBussinessTask."+orderNo);
					RedisManager.unlock(splitExecuteBussinessKey);
					return;
				}
				int num = iCrmBusinessSplitDetailService.queryCrmBusinessSplitDetail(orderNo);
				if(num>0){
					LOG.info("---error end---处理订单业务分账，订单已经存在，订单号为：SplitExecuteBussinessTask."+orderNo);
					RedisManager.unlock(splitExecuteBussinessKey);
					return;
				} 
				
				BigDecimal cutMoney = splitCutInfoParam.getCutMoney();
				//代理商分账比例信息
				CrmSplitAssignSetPO crmSplitAssignSetPO = iCrmSplitAssignSetService.queryCrmSplitAssignByCanteenId(splitCutInfoParam.getCanteenId());  
				if(crmSplitAssignSetPO==null){
					LOG.info("---error end---处理订单业务分账，分账比例不存在，订单号为：SplitExecuteBussinessTask."+orderNo); 
					RedisManager.unlock(splitExecuteBussinessKey);
					return;
				} 
				// 百分比
				BigDecimal percent = new BigDecimal(100); 
				//业务员流水
				BigDecimal businessRedio = crmSplitAssignSetPO.getBusinssSplitPercent();
				BigDecimal businessMoney = BigDecimal.ZERO;
				if(businessRedio!=null){
					businessMoney = cutMoney.multiply(businessRedio).divide(percent, 6, RoundingMode.HALF_UP);  
					BigDecimal businessYearRedio = percent;  
					Integer year = DateUtil.getMinusYear(crmSplitAssignSetPO.getCreaterTime());
					if(year>0){
						businessYearRedio = iCrmBusinessRedioService.queryBusinessRedio(year, 1);
					}
					businessMoney = businessMoney.multiply(businessYearRedio).divide(percent, 6, RoundingMode.HALF_UP);
				}
				//代理商
				BigDecimal agentRedio =  crmSplitAssignSetPO.getAgentSplitPercent();
				BigDecimal agentMoney = BigDecimal.ZERO;
				if(agentRedio!=null){
					agentMoney =cutMoney.multiply(agentRedio).divide(percent, 6, RoundingMode.HALF_UP); 
					BigDecimal agentYearRedio = percent;  
					Integer year = DateUtil.getMinusYear(crmSplitAssignSetPO.getCreaterTime());
					if(year>0){
						agentYearRedio = iCrmBusinessRedioService.queryBusinessRedio(year, 2);
					}
					agentMoney = agentMoney.multiply(agentYearRedio).divide(percent, 6, RoundingMode.HALF_UP);
					
				}
				//平台资金
				BigDecimal platformMoney = cutMoney.subtract(businessMoney).subtract(agentMoney); 
				//添加记录 
				iCrmBusinessSplitDetailService.saveCrmBusinessSplitDetail(orderNo, crmSplitAssignSetPO.getBusinessId(), crmSplitAssignSetPO.getAgentId(), businessMoney, agentMoney, platformMoney,splitCutInfoParam.getPayModel());
				
				if(businessMoney.doubleValue()>0){
					CrmPayCanteenCashflowParam crmPayCanteenCashflowParam =new CrmPayCanteenCashflowParam();
					crmPayCanteenCashflowParam.setOrderNo(orderNo);
					crmPayCanteenCashflowParam.setUserId(crmSplitAssignSetPO.getBusinessId());
					crmPayCanteenCashflowParam.setCashFlowMoney(businessMoney);
					splitSingleton.put(10L, new SplitBusinessMoneyTask(crmPayCanteenCashflowParam), crmSplitAssignSetPO.getBusinessId(),SplitOrderEnum.SPLIT_ORDER_BUSINESS_FINISH);	
				}
				if(agentMoney.doubleValue()>0){
					CrmPayCanteenCashflowParam crmPayCanteenCashflowParam =new CrmPayCanteenCashflowParam();
					crmPayCanteenCashflowParam.setOrderNo(orderNo);
					crmPayCanteenCashflowParam.setCashFlowMoney(agentMoney);
					crmPayCanteenCashflowParam.setUserId(crmSplitAssignSetPO.getAgentId());
		 			splitSingleton.put(10L, new SplitAgentMoneyTask(crmPayCanteenCashflowParam),  crmSplitAssignSetPO.getAgentId(),SplitOrderEnum.SPLIT_ORDER_AGENT_FINISH);	
				} 
				CrmPayCanteenCashflowParam crmPayCanteenCashflowParam =new CrmPayCanteenCashflowParam();
				crmPayCanteenCashflowParam.setOrderNo(orderNo);
				crmPayCanteenCashflowParam.setUserId(null);
				crmPayCanteenCashflowParam.setCashFlowMoney(platformMoney);
			 	splitSingleton.put(10L, new SplitPlatformMoneyTask(crmPayCanteenCashflowParam), splitCutInfoParam.getId(),SplitOrderEnum.SPLIT_ORDER_PLATFROM_FINISH);
			 	
			 	SplitCutInfoParam param = new SplitCutInfoParam();
			 	param.setBusinessCutStatus(1);
			 	param.setId(splitCutInfoParam.getId());
			 	sSplitCutInfoService.updateSplitCutInfoStatus(param);
				RedisManager.unlock(splitExecuteBussinessKey);
			}
		} catch (Throwable t) {
			// TODO: handle exception
			LOG.error("SplitExecuteBussinessTask 异常"+t);
		}
	}
	 

}
