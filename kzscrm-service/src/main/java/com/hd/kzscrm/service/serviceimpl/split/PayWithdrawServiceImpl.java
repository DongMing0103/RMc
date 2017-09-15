package com.hd.kzscrm.service.serviceimpl.split;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.split.PayCanteenCashflowPO;
import com.hd.kzscrm.dao.entity.split.PayWithdrawPO;
import com.hd.kzscrm.service.param.split.PayWithdrawParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.split.PayCanteenCashflowService;
import com.hd.kzscrm.service.serviceInter.split.PayWithdrawService;
import com.hd.kzscrm.service.vo.split.PayWithdrawVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * 我的账单
 * @author Administrator
 *
 */
@Service
public class PayWithdrawServiceImpl extends BaseServiceImpl implements PayWithdrawService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PayWithdrawServiceImpl.class);
	
	/**
	 * 流水表
	 */
	@Autowired
	private PayCanteenCashflowService payCanteenCashflowService;
	
	/**
	 * 团队表
	 */
	@Autowired
	private ICrmTeamService crmTeamService;
	
	/**
	 * 业务员表
	 */
	@Autowired
	private ICrmBusinessService crmBusinessService;
	
	/**
	 * 代理商表
	 */
	@Autowired
	private ICrmAgentService crmAgentService;
	
	/**
	 * 食堂基本信息表
	 */
	@Autowired
	private ICrmCanteenBaseInfoService canteenBaseInfoService;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<PayWithdrawPO> findAll() {
		
		return commonDao.listByParams(PayWithdrawPO.class,"PayWithdrawMapper.findAll" ,new ParamMap());
	}
	/**
	 * 查询全部
	 * @param param
	 * @return
	 * @author jyt 2017年4月19日 上午11:19:18
	 */
	@Override
	public List<PayWithdrawPO> findByParams(PayWithdrawParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(PayWithdrawPO.class,"PayWithdrawMapper.findByParam",paramMap);
	}
	
	/**
	 * 模板查询全部   页面数据查询
	 * 提现角色：userType :  4 平台   5  代理商  6  业务员
	 */
	@Override
	public Page<PayWithdrawVO> findPageByParams(PayWithdrawParam param) {
		//根据条件模糊查询数据
		if (BeanUtils.isNotEmpty(param.getCondition()) && param.getSelectNum().equals("4")) {
		    List<Long> teamIds = new ArrayList<>();
		    List<CrmTeamPO> crmTeamPOs = crmTeamService.findLikeByTeamName(param.getCondition()); //平台名称模糊查询
		    if (CollectionUtils.isNotEmpty(crmTeamPOs)) {
		    	for ( CrmTeamPO po : crmTeamPOs) {
					if (BeanUtils.isNotEmpty(po)) {
						teamIds.add(po.getId());
					}
				}
		    	param.setTeamids(teamIds);
			}else {
				Page<PayWithdrawVO> pageResult = new Page<PayWithdrawVO>();
				pageResult.result = null;
				pageResult.setTotalResult(0);
				return pageResult;
			}
		}
		//代理商模糊查询
		if (BeanUtils.isNotEmpty(param.getCondition()) && param.getSelectNum().equals("5")) { //代理商
			List<Long> userIds = new ArrayList<>();
			List<CrmAgentPO> crmAgentPOs = crmAgentService.findLikeByAgentName(param.getCondition());
			if (CollectionUtils.isNotEmpty(crmAgentPOs)) {
				for ( CrmAgentPO po : crmAgentPOs) {
					if (BeanUtils.isNotEmpty(po)) {
						userIds.add(po.getId());
					}
				}
				param.setUserIds(userIds);
			}
			else {
				Page<PayWithdrawVO> pageResult = new Page<PayWithdrawVO>();
				pageResult.result = null;
				pageResult.setTotalResult(0);
				return pageResult;
			}
		}
		
		//根据业务员模糊查询
		if (BeanUtils.isNotEmpty(param.getCondition()) && param.getSelectNum().equals("6")) {    //业务员
			List<Long> userIds = new ArrayList<>();
			List<CrmBusinessPO> crmBusinessPOs = crmBusinessService.findLikeByBusinessName(param.getCondition());
			if (CollectionUtils.isNotEmpty(crmBusinessPOs)) {
				for ( CrmBusinessPO po : crmBusinessPOs ){
					if (BeanUtils.isNotEmpty(po)) {
						userIds.add(po.getUserId());
					}
				}
				param.setUserIds(userIds);
			}else {
				Page<PayWithdrawVO> pageResult = new Page<PayWithdrawVO>();
				pageResult.result = null;
				pageResult.setTotalResult(0);
				return pageResult;
			}
		}
		
		//页面数据显示
		param.setDelFlag(1);
		ParamMap paramMap = new ParamMap(param);
		Page<PayWithdrawPO> payWithdrawList = findPageByParams(PayWithdrawPO.class,new Page<PayWithdrawPO>(param.getOffset(),param.getLimit()),"PayWithdrawMapper.findByParam",paramMap);
		List<PayWithdrawVO> rows = new ArrayList<PayWithdrawVO>();
		List<PayWithdrawPO> payWithdrawPOs = payWithdrawList.result;
		
		int total = 0;
		if (CollectionUtils.isNotEmpty(payWithdrawPOs)) {
			for (int i = 0; i < payWithdrawPOs.size(); i++) {//循环查询结果
				PayWithdrawPO payWithdrawPO = payWithdrawPOs.get(i);
				PayWithdrawVO payWithdrawVO = BeanConvertor.convertBean(payWithdrawPO, PayWithdrawVO.class);
				
				if(BeanUtils.isNotEmpty(payWithdrawVO)){
					if(payWithdrawVO.getUserType().equals(4)){
						payWithdrawVO.setUserTypeName("平台");
					}else if(payWithdrawVO.getUserType().equals(5)){
						payWithdrawVO.setUserTypeName("代理商");
					}else if(payWithdrawVO.getUserType().equals(6)){
						payWithdrawVO.setUserTypeName("业务员");
					}
				}
				if(BeanUtils.isNotEmpty(payWithdrawVO)){
					if(payWithdrawVO.getStatus().equals(1)){
						payWithdrawVO.setStatusName("申请中");
					}else if(payWithdrawVO.getStatus().equals(2)){
						payWithdrawVO.setStatusName("已通过");
					}else if(payWithdrawVO.getStatus().equals(3)){
						payWithdrawVO.setStatusName("未通过");
					}else if(payWithdrawVO.getStatus().equals(4)){
						payWithdrawVO.setStatusName("成功");
					}
				}
				
				if (BeanUtils.isNotEmpty(payWithdrawPO)) {
					//根据ID查询现金流水号(pay_canteen_cashflow)
					Long id = payWithdrawPO.getPayCanteenCashFlowId();  //pay_canteen_cashflow_id  对应的是pay_withdraw表
					if(BeanUtils.isNotEmpty(id)){
						PayCanteenCashflowPO cashflowPO = payCanteenCashflowService.findById(id);
						if (BeanUtils.isNotEmpty(cashflowPO)) {
							payWithdrawVO.setCashFlowNo(cashflowPO.getCashFlowNo());  //获取现金流水号
							payWithdrawVO.setBalance(cashflowPO.getBalance());
						}
					}
					//根据user_id查询团队，业务员，代理商的名称
					Long userId = payWithdrawPO.getUserId();   //获取对应的id (业务员ID，代理商ID)
					if (BeanUtils.isNotEmpty(userId)) {
//						payWithdrawVO.setUserId(payWithdrawPO.getUserId());
						if (BeanUtils.isNotEmpty(userId)) {
							//查询平台名称
//							if (payWithdrawVO.getUserType().equals(4)) {      
//								CrmCanteenBaseInfoPO canteenBaseInfoPO = canteenBaseInfoService.findByUserId(userId);
//								payWithdrawVO.setAllName(canteenBaseInfoPO.getName());      //获取食堂(平台)名称
//							}
							if (payWithdrawVO.getUserType().equals(5)) {    //5  代表代理商
								CrmAgentPO agentPO = crmAgentService.findByUserId(userId);
								payWithdrawVO.setAllName(agentPO.getName()); //获取代理商名称
							}
							if (payWithdrawVO.getUserType().equals(6)) {      //6 代表业务员
								CrmBusinessPO businessPO = crmBusinessService.findByUserId(userId);
							    payWithdrawVO.setAllName(businessPO.getName());    //获取业务员名称
							}
						}
					}
				}
				rows.add(i, payWithdrawVO);
			}
		}
		total = payWithdrawList.getTotalResult();
		Page<PayWithdrawVO> pageResult = new Page<PayWithdrawVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}
	
	
	@Override
	public Page<PayWithdrawVO> getListData(PayWithdrawParam param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PayWithdrawPO findByUserId(Long userId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("userId", userId);
		List<PayWithdrawPO> payList = listByParams(PayWithdrawPO.class, "PayWithdrawMapper.findByUserId", paramMap);
		if (BeanUtils.isNotEmpty(payList)) {
			return payList.get(0);
		}
		return null;
	}
	
}
