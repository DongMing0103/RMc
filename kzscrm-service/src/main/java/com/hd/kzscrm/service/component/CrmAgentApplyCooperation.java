/**
 * 
 */
package com.hd.kzscrm.service.component;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.agent.CrmAgentApplyPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.service.param.agent.CrmAgentApplyParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.user.CrmUserParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;

/**
 * @author 黄霄仪
 * @date 2017年7月29日 下午4:58:10
 * 
 */
@Component
public class CrmAgentApplyCooperation {
	@Resource
	private ICrmAgentApplyService iCrmAgentApplyService;
	@Resource
	private ICrmUserService iCrmUserService;
	@Resource
	private ICrmClientResourceService iCrmClientResourceService;
	@Resource
	private ICrmAgentService iCrmAgentService;

	/**
	 * 
	 */
	//每天晚上0点更新
	@Scheduled(cron = "0 0 0 * * ?")  
	public void cooperationEnd() {
		System.out.println(iCrmAgentApplyService);
		CrmAgentApplyParam crmAgentApplyParam=new CrmAgentApplyParam();
		crmAgentApplyParam.setCheckStatus(1);//审核通过的
		List<CrmAgentApplyPO> listByParam = iCrmAgentApplyService.listByParam(crmAgentApplyParam);
		for (CrmAgentApplyPO crmAgentApplyPO : listByParam) {
			Long agentId = crmAgentApplyPO.getAgentId();
			boolean whetherExpire = iCrmAgentApplyService.whetherExpire(agentId);
			//如果过了签约期
			if(whetherExpire){
				//crm_agent:authentication_status置空，agent_status=0，
				//crm_client_resource：client_nature为1，check_status=null,client_num=0
				//crm_agent_apply删除
				//crm_user:del_flag为0（删除）
				CrmAgentPO crmAgentPO = iCrmAgentService.get(CrmAgentPO.class, agentId);
				if(BeanUtils.isNotEmpty(crmAgentPO)){
					Long userId = crmAgentPO.getUserId();
					CrmUserParam crmUserParam=new CrmUserParam();
					crmUserParam.setId(userId);
					crmUserParam.setDelFlag(CrmCommonDelFlag.DELETE.getCode());//注销
					
					
					CrmAgentApplyParam crmAgentApplyParamTemp=new CrmAgentApplyParam();
					crmAgentApplyParamTemp.setDelFlag(CrmCommonDelFlag.DELETE.getCode());
					crmAgentApplyParamTemp.setId(crmAgentApplyPO.getId());
					crmAgentApplyParamTemp.setCheckStatus(-1);
					
//					CrmAgentParam crmAgentParam=new CrmAgentParam(); 
//					crmAgentParam.setDelFlag(CrmCommonDelFlag.DELETE.getCode());
//					crmAgentParam.setId(agentId);
					CrmClientResourceParam crmClientResourceParam=new CrmClientResourceParam();
					crmClientResourceParam.setClientNature(1);
					crmClientResourceParam.setClientNum(0l);
					crmClientResourceParam.setCheckStatus(-1);
					crmClientResourceParam.setId(iCrmClientResourceService.findByAgentCanteenId(agentId).getId());
					
					crmAgentPO.setAuthenticationStatus(-1);
					crmAgentPO.setAgentStatus(0);
					crmAgentPO.setSignContractStatus(2);//解约
					
					iCrmUserService.update(BeanConvertor.copy(crmUserParam,CrmUserPO.class));
					iCrmAgentApplyService.update(BeanConvertor.copy(crmAgentApplyParamTemp,CrmAgentApplyPO.class));
					iCrmClientResourceService.update(BeanConvertor.copy(crmClientResourceParam,CrmClientResourcePO.class));
					iCrmAgentService.update(crmAgentPO);
					
				}
			}
		}
		System.out.println("每天晚上0点更新:");
	}

}
