package com.hd.kzscrm.service.serviceimpl.split;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.split.PayCanteenCashflowPO;
import com.hd.kzscrm.service.param.split.PayCanteenCashflowParam;
import com.hd.kzscrm.service.serviceInter.split.PayCanteenCashflowService;
import com.hd.kzscrm.service.vo.split.PayCanteenCashflowVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * 商家现金流水表
 * 
 * @author Administrator
 *
 */
@Service
public class PayCanteenCashflowServiceImpl extends BaseServiceImpl implements PayCanteenCashflowService {

	/**
	 * 分页查询全部流水
	 */
	@Override
	public Page<PayCanteenCashflowPO> findPageByParams(
			PayCanteenCashflowParam param) {
		ParamMap paramMap = new ParamMap(param);

		return findPageByParams(
				PayCanteenCashflowPO.class,
				new Page<PayCanteenCashflowPO>(param.getOffset(), param
						.getLimit()), "PayCanteenCashflowMapper.findAllSupply",
				paramMap);
	}

	
	/**
	 * 时间段
	 */
	@Override
	public Page<PayCanteenCashflowPO> findPageByTimeSlot(
			PayCanteenCashflowParam param) {
		PayCanteenCashflowPO payPo = BeanConvertor.copy(param,
				PayCanteenCashflowPO.class);
		ParamMap paramMap = new ParamMap();
		// 根据时间段查询
		paramMap.put("starTime", param.getStarTime());
		paramMap.put("endTime", param.getEndTime());
		/*
		 * System.out.println(param.getStarTime());
		 * System.out.println(param.getEndTime());
		 */
		return findPageByParams(
				PayCanteenCashflowPO.class,
				new Page<PayCanteenCashflowPO>(param.getOffset(), param
						.getLimit()),
				"PayCanteenCashflowMapper.findByTimeSlot", paramMap);
	}
	/**
	 * 时间段
	 */
	@Override
	public List<BigDecimal> findPeriodTime(PayCanteenCashflowParam param){
		ParamMap paramMap = new ParamMap(param);
		
		return  commonDao.listByParams(BigDecimal.class,
						"PayCanteenCashflowMapper.findPeriodTime", paramMap);
	}
	/**
	 * 求和
	 */
	@Override
	public List<BigDecimal> findSum(PayCanteenCashflowParam param) {
		ParamMap paramMap = new ParamMap(param);
		return commonDao.listByParams(BigDecimal.class,
				"PayCanteenCashflowMapper.findBySum", paramMap);
	}

	/**
	 * 获取现金流水信息
	 */
	@Override
	public PayCanteenCashflowPO findBypayCanteenCashflowId(
			Long payCanteenCashflowId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", payCanteenCashflowId);
		List<PayCanteenCashflowPO> payCanteenCashflowPOs = listByParams(PayCanteenCashflowPO.class,"PayCanteenCashflowMapper.findAllSupply", paramMap);
		if (CollectionUtils.isNotEmpty(payCanteenCashflowPOs)) {
			return payCanteenCashflowPOs.get(0);
		}
		return null;
	}

	/**
	 * 导出
	 */
	@Override
	public List<PayCanteenCashflowPO> findAll(PayCanteenCashflowParam param) {
		ParamMap paramMap = new ParamMap(param);

		return commonDao.listByParams(PayCanteenCashflowPO.class,
				"PayCanteenCashflowMapper.findAllSupply", paramMap);

	}

	/**
	 * 通用插入
	 */
	@Override
	public int commonInsert(PayCanteenCashflowParam param) {
		// 用户流水表数据
		ParamMap paramMap = new ParamMap(param);
		return commonDao.execute("PayCanteenCashflowMapper.commonInsert",
				paramMap);
	}
	/**
	 * 批量插入
	 */
	@Override
	public int batchInsert(List<PayCanteenCashflowParam> payCanteenCashflowParams){
		for (PayCanteenCashflowParam payCanteenCashflowParam : payCanteenCashflowParams) {
			commonInsert(payCanteenCashflowParam);
		}
		return 0;
	}

	/**
	 * 账单总计动态查询
	 * 
	 * @author create 郁圆圆
	 * @date create 2017年4月8日 上午10:40:13
	 */
	@Override
	public Page<PayCanteenCashflowPO> findByStatus(PayCanteenCashflowParam param) {
		ParamMap paramMap = new ParamMap(param);
		return findPageByParams(PayCanteenCashflowPO.class,new Page<PayCanteenCashflowPO>(param.getOffset(), param.getLimit()), "PayCanteenCashflowMapper.findByStatus",paramMap);
	}
	/**
	 * 账单总计动态查询
	 * 
	 * @author create 郁圆圆
	 * @date create 2017年4月8日 上午10:40:13
	 */
	@Override
	public List<PayCanteenCashflowPO> listByStatus(PayCanteenCashflowParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(PayCanteenCashflowPO.class,"PayCanteenCashflowMapper.findByStatus",paramMap); 
	}

	/**
	 * 导出
	 * 
	 * @author create 郁圆圆
	 * @date create 2017年4月10日 下午3:43:44
	 */
	@Override
	public List<PayCanteenCashflowPO> excelPay(PayCanteenCashflowParam param) {
		param.setCashFlowStatus(1);
		ParamMap paramMap = new ParamMap(param);
		return listByParams(PayCanteenCashflowPO.class,
				"PayCanteenCashflowMapper.findByStatus", paramMap);
	}

	/**
	 * 导出
	 * 
	 * @author create 郁圆圆
	 * @date create 2017年4月11日 下午3:44:43
	 */
	@Override
	public List<PayCanteenCashflowPO> excelRecord(PayCanteenCashflowParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(PayCanteenCashflowPO.class,
				"PayCanteenCashflowMapper.findByRecord", paramMap);
	}

	/** 
	* 初始化 提现记录
	* @author create 郁圆圆
	* @date create 2017年4月18日 上午10:45:33 
	*/
	@Override
	public ModelAndView initRecord() {
		List<Map> pMaps =  this.findCountNumber();
		Map<String,Object> map1 = new HashMap<String, Object>();
		String all="";
		for(Map<String,Object> map:pMaps){
			if(BeanUtils.isNotEmpty(map.get("cashFlowStatus"))){
				if(map.get("cashFlowStatus").equals(1)){
					all=all+Integer.parseInt(map.get("count").toString());
					map1.put("pay", map.get("count"));
				}else if(map.get("cashFlowStatus").equals(2)){
					all=all+Integer.parseInt(map.get("count").toString());
					map1.put("noPay", map.get("count"));
				}
			}
		}
		ModelAndView mv=new ModelAndView("order/withdrawal_record");
		mv.addObject("map",map1);
		mv.addObject("all",all);
		return mv;
	}
	
	/** 
	* 计算账单总计个数
	* @author create 郁圆圆
	* @date create 2017年4月28日 下午1:53:40 
	*/
	@Override
	public List<PayCanteenCashflowPO> findByStatusNum(
			PayCanteenCashflowParam param) {
		ParamMap paramMap = new ParamMap();
		return listByParams(PayCanteenCashflowPO.class, "PayCanteenCashflowMapper.findByStatus", paramMap);
	}
	
	/** 
	* 计算账单总计个数
	* @author create   
	* @date create 2017年4月28日 下午1:53:40 
	*/
	@Override
	public List<BigDecimal> findPeriodParam(PayCanteenCashflowParam param) {
		ParamMap paramMap=new ParamMap(param);
		this.listByParams(BigDecimal.class,"PayCanteenCashflowMapper.findPeriodParam",paramMap);
		
		return null;
		
		
		/*
		 if(BeanUtils.isNotEmpty(param.getNames())){
			UserParam user=new UserParam();
			user.setMobilephone(param.getNames());
			List<UserPO> userPOs=this.userService.findAllByParam(user);
			if(CollectionUtils.isNotEmpty(userPOs)){
				String userIds="";
				for(UserPO po:userPOs){
					if(BeanUtils.isNotEmpty(po) && BeanUtils.isNotEmpty(po.getId())){
						userIds+=po.getId()+",";
					}
				}
				param.setUserIds(userIds.substring(0,userIds.length()-1));
			}
			
			CanteenExtInfoParam canteenExtInfo=new CanteenExtInfoParam();
			canteenExtInfo.setHeadPhone(param.getNames());
			List<CanteenExtInfoPO> canteenExtInfoPOs=canteenExtInfoService.findAllByParam(canteenExtInfo);
			if(CollectionUtils.isNotEmpty(canteenExtInfoPOs)){
				String canteenIds="";
				for(CanteenExtInfoPO po:canteenExtInfoPOs){
					if(BeanUtils.isNotEmpty(po) && BeanUtils.isNotEmpty(po.getBaseInfoId())){
						canteenIds+=po.getBaseInfoId()+",";
					}
				}
				param.setCanteenids(canteenIds.substring(0,canteenIds.length()-1));
			}
			if(CollectionUtils.isEmpty(userPOs)&&CollectionUtils.isEmpty(canteenExtInfoPOs)){
				return null;
			}
		}
		ParamMap paramMap=new ParamMap(param);
		return listByParams(BigDecimal.class,"PayCanteenCashflowMapper.findPeriodParam",paramMap);
		*/
	}

	
	

	@Override
	public Page<PayCanteenCashflowPO> findByRecord(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map> findCountNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageRespModel listData(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageRespModel listDataRecord(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayCanteenCashflowVO> getCashflowMsgForExcel(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayCanteenCashflowPO setAllField(Long id, String cashFlowNo, Long userId, Date createTime,
			Integer cashFlowType, BigDecimal cashFlowMoney, BigDecimal balance, Long canteenId, Long canteenOrderId,
			Integer cashFlowStatus, Integer userType, Integer cashflowType2, Integer payModel, Integer refundType,
			String payTimestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PayCanteenCashflowPO> findPageByUserId(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayCanteenCashflowPO> findByConsumption(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayCanteenCashflowPO> findByConsumptionExcel(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excelCountPay(HttpServletResponse response, PayCanteenCashflowParam param) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageRespModel userOrderTotal(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayCanteenCashflowPO> findByTypes(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayCanteenCashflowPO> findByStatus1(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findByCountNew(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BigDecimal> findPeriodParamByCanteen(PayCanteenCashflowParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过订单Id获取订单现金流水号
	 */
	@Override
	public String queryOrderCashFlowNo(Long orderId) {
		
		ParamMap paramMap = new ParamMap();
		paramMap.put("canteenOrderId", orderId);
		List<String> cashFlowNos = commonDao.listByParams(String.class, "PayCanteenCashflowMapper.queryOrderCashFlowNo", paramMap);
		if(CollectionUtils.isNotEmpty(cashFlowNos)){
			return cashFlowNos.get(0);
		}
		
		return null;
	}

	@Override
	public PayCanteenCashflowPO findById(Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		List<PayCanteenCashflowPO> cashflowList = listByParams(PayCanteenCashflowPO.class, "PayCanteenCashflowMapper.findById",paramMap);
		if (CollectionUtils.isNotEmpty(cashflowList)) {
			return cashflowList.get(0);
		}
		return null;
	}

	/**
	 * 根据订单id查询流水表
	* @Title: findByOrderId 
	* @author : lcl
	* @time : 2017年6月26日 下午2:07:57
	* @return PayCanteenCashflowPO    返回类型 
	* @throws
	 */
	@Override
	public PayCanteenCashflowPO findByOrderId(Long orderId) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("orderId", orderId);
		List<PayCanteenCashflowPO> cashflowList = listByParams(PayCanteenCashflowPO.class, "PayCanteenCashflowMapper.findByOrderId",paramMap);
 		if(CollectionUtils.isNotEmpty(cashflowList)){
 			
 			return cashflowList.get(0);
 		}
		return null;
	}
}
