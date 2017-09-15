package com.hd.kzscrm.service.serviceimpl.agent;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessOrderPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.service.param.agent.CrmSplitAssignSetParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmPayCanteenCashflowService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.agent.CrmSplitAssignSetVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmSplitAssignSet 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmSplitAssignSetServiceImpl extends BaseServiceImpl implements ICrmSplitAssignSetService {

	// 日志服务对象
	protected static final Logger logger = LoggerFactory.getLogger(CrmSplitAssignSetServiceImpl.class);
		/**
		 * 业务员跟踪记录表
		 */
	   @Autowired
	   ICrmBusinessOrderService businessOrderService;
	   //客户资源表
	   @Autowired
	   ICrmClientResourceService  clientResourceService;
	   /**
	    * 业务员表
	    */
	   @Autowired
	   ICrmBusinessService businessService;
	   /**
	    * 食堂基本信息
	    */
	   @Autowired
	   ICrmCanteenBaseInfoService canteenBaseInfoService;	
	   /**
	    * 订单表
	    */
	   @Autowired
	   ICrmBusinessOrderService crmBusinessOrderService;
	   
	   /**
	    * 资金流水表
	    */
	   @Autowired
	   ICrmPayCanteenCashflowService crmPayCanteenCashflowService;
	   //订单表
	   @Autowired
	   ICrmOrderService orderService;
	/**
	 * 默认构造函数
	 */

	public CrmSplitAssignSetServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	/**
	 * 
	 * @author 黄霄仪
	 * @date 2017年7月19日 下午1:58:27
	 */
	@Override
	public CrmSplitAssignSetPO findOneByCanteenId(Long canteenId){
		Assert.notNull(canteenId, "canteenId不能为空");
		CrmSplitAssignSetParam crmSplitAssignSetParam=new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenId(canteenId);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = this.listByParam(crmSplitAssignSetParam);
		if(BeanUtils.isEmpty(crmSplitAssignSetPOs)){
			return null;
		}
		Assert.isTrue(crmSplitAssignSetPOs.size()==1,"不能大于1个");
		return crmSplitAssignSetPOs.get(0);
	}
	/**
	 * 查询
	 */
	@Override
	public Page<CrmSplitAssignSetVO> queryPage(CrmSplitAssignSetParam param) {
		//根据食堂名称模糊查询
		if (BeanUtils.isNotEmpty(param.getCondition()) && param.getSelectNum().equals("1")) {
			List<Long> canteenIds = new ArrayList<>();
			List<CrmCanteenBaseInfoPO> canteenBaseInfoPOs = canteenBaseInfoService.findLikeByName(param.getCondition(),null);
			if(CollectionUtils.isNotEmpty(canteenBaseInfoPOs) ){
				
				for(CrmCanteenBaseInfoPO cp: canteenBaseInfoPOs){
					if (BeanUtils.isNotEmpty(cp)) {
						canteenIds.add(cp.getId());
					}
				}
				param.setCanteenIds(canteenIds);
			}else{
				Page<CrmSplitAssignSetVO> pageResult = new Page<CrmSplitAssignSetVO>();
				pageResult.result =null;
				pageResult.setTotalResult(0);
				return pageResult;
			}
		}
		
		//根据订单编号查询订单
		if(BeanUtils.isNotEmpty(param.getCondition()) && param.getSelectNum().equals("2")){
			List<Long> orderIds = new ArrayList<>();
			List<CrmBusinessOrderPO> crmBusinessOrderPOs  = crmBusinessOrderService.findLikeByOrderId(param.getCondition());
			if(CollectionUtils.isNotEmpty(crmBusinessOrderPOs)){
				for(CrmBusinessOrderPO  op:crmBusinessOrderPOs){
					if(BeanUtils.isNotEmpty(op)){
						orderIds.add(op.getId());
					}
				}
				param.setOrderIds(orderIds);
			}else{
				Page<CrmSplitAssignSetVO> pageResult = new Page<CrmSplitAssignSetVO>();
				pageResult.result =null;
				pageResult.setTotalResult(0);
				return pageResult;
			}
		}
		
		//页面数据查询显示
		ParamMap paramMap = new ParamMap(param);
		Page<CrmSplitAssignSetPO> crmsplitassignsetList = findPageByParams(CrmSplitAssignSetPO.class,
				new Page<CrmSplitAssignSetPO>(param.getOffset(), param.getLimit()), "CrmSplitAssignSetPOMapper.queryPage", paramMap);
		List<CrmSplitAssignSetVO> rows = new ArrayList<CrmSplitAssignSetVO>();
		List<CrmSplitAssignSetPO> cAssignSetPOs = crmsplitassignsetList.result;
		int total = 0;
		if (CollectionUtils.isNotEmpty(cAssignSetPOs)) {//查询结果循环渲染
				for(int i=0;i< cAssignSetPOs.size();i++){
					CrmSplitAssignSetPO cAssignSetPO = cAssignSetPOs.get(i);
					CrmSplitAssignSetVO cAssignSetVO = BeanConvertor.convertBean(cAssignSetPO, CrmSplitAssignSetVO.class);
					if(BeanUtils.isNotEmpty(cAssignSetPO)){
						//根据  业务员id 查询业务员表 和 业务员订单表
						Long businessId = cAssignSetPO.getBusinessId();
						CrmBusinessOrderPO cOrderPO= businessOrderService.findByBusinessId(businessId);//根据业务员id查询订单
//						CrmPayCanteenCashflowPO cashflowPO = crmPayCanteenCashflowService.getById(id)
						if(BeanUtils.isNotEmpty(cOrderPO)){
							BigDecimal splitPercent = cAssignSetPO.getBusinssSplitPercent();//获取业务员分账比例
							cAssignSetVO.setOrderNum(cOrderPO.getOrderNo());//获取订单id
							cAssignSetVO.setRealMoney(cOrderPO.getOrderRealMoney());//订单的总金额
							cAssignSetVO.setCreaterTime(cOrderPO.getCreateTime());//分账时间
//							cAssignSetVO.setCashFlowNo(CrmPayCanteenCashflowPO);
							if(BeanUtils.isNotEmpty(cOrderPO.getOrderRealMoney())&& BeanUtils.isNotEmpty(splitPercent) && splitPercent.compareTo(BigDecimal.ZERO)>0){
								//BigDecimal的乘法计算
								cAssignSetVO.setSplitMoney((cOrderPO.getOrderRealMoney().multiply(splitPercent)).divide(new BigDecimal(100)));
							}
							//查询现金流水号
							
						}
						
						Long canteenId = cAssignSetPO.getCanteenId();//获取食堂信息
						
						CrmCanteenBaseInfoPO cpInfoPO =canteenBaseInfoService.findByCanteenId(canteenId); 
						if(BeanUtils.isNotEmpty(cpInfoPO)){//查询食堂名称
							cAssignSetVO.setName(cpInfoPO.getName());
						}
					}
					rows.add(i, cAssignSetVO);
				}
		}
		total = crmsplitassignsetList.getTotalResult();
		Page<CrmSplitAssignSetVO> pageResult = new Page<CrmSplitAssignSetVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;

	}

	/**
	 * 根据主键查询详情
	 * 
	 * @param param
	 * @return
	 */

	@Override
	public List<CrmSplitAssignSetPO> listByParam(CrmSplitAssignSetParam crmsplitassignsetParam) {
		ParamMap paramMap = new ParamMap(crmsplitassignsetParam);
		return commonDao.listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.queryList", paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmSplitAssignSetPO po =
		 * this.get(CrmSplitAssignSetPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmSplitAssignSetPOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmSplitAssignSetPO> listPo = new ArrayList<CrmSplitAssignSetPO>(idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmSplitAssignSetPO po = this.get(CrmSplitAssignSetPO.class, idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmSplitAssignSetPO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmSplitAssignSetParam param) {
		CrmSplitAssignSetPO crmsplitassignsetPO = BeanConvertor.copy(param, CrmSplitAssignSetPO.class);
		this.save(crmsplitassignsetPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmSplitAssignSetPO po) {
		po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_split_assign_set));
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmSplitAssignSetPO po) {
		this.update(po);
	}

	@Override
	public CrmSplitAssignSetVO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CrmSplitAssignSetPO queryCrmSplitAssignByCanteenId(Long canteenId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("canteenId", canteenId);  
		List<CrmSplitAssignSetPO> list =  commonDao.listByParams(CrmSplitAssignSetPO.class,"CrmSplitAssignSetPOMapper.queryList",paramMap);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<CrmSplitAssignSetPO> findAll(CrmSplitAssignSetParam param) {
		ParamMap paramMap = new ParamMap();
		List<CrmSplitAssignSetPO> splitPOs = listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.findByAll", paramMap);
		if (splitPOs != null) {
			return splitPOs;
		}
		return null;
	}

	/**
	 * 查询抽成分配设置信息
	 */
	@Override
	public List<CrmSplitAssignSetPO> commonQuery(
			CrmSplitAssignSetParam crmSplitAssignSetParam) {
		ParamMap paramMap = new ParamMap(crmSplitAssignSetParam);
		return listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.commonQuery", paramMap);
	}

	@Override
	public List<CrmSplitAssignSetPO> findById(Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("orderId", id);
		List<CrmSplitAssignSetPO> idPos = listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.findById", paramMap);
		if (idPos != null) {
			return idPos;
		}
		return null;
	}

	/**
	 * 查询订单详情，  订单分账
	 */
	@Override
	public ModelAndView details(HttpServletRequest request) {
		
		return null;
	}

	/**
	 * 根据主键查询对象
	* @Title: findByResourceId 
	* @author : lcl
	* @time : 2017年6月20日 下午2:14:52
	* @return CrmSplitAssignSetPO    返回类型 
	* @throws
	 */
	@Override
	public CrmSplitAssignSetPO findByResourceId(Long clientResourceId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", clientResourceId);
		List<CrmSplitAssignSetPO> cList = listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.findByResourceId", paramMap);
		if(CollectionUtils.isNotEmpty(cList)){
			return cList.get(0);
		}
		return null;
	}

	/**
	 * 查询最近生效时间的抽成信息
	 */
	@Override
	public CrmSplitAssignSetPO getLastEffectiveSplitMsg(
			CrmSplitAssignSetParam crmSplitAssignSetParam) {
		ParamMap paramMap = new ParamMap(crmSplitAssignSetParam);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = commonDao.listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.getLastEffectiveSplitMsg", paramMap);
		if(CollectionUtils.isNotEmpty(crmSplitAssignSetPOs) && crmSplitAssignSetPOs.size()>0){
			return crmSplitAssignSetPOs.get(0);
		}
		return null;
	}

	/**
	 * 保存调整食堂分账比
	 * @throws ParseException 
	 */
	@Override
	public Boolean saveAdjustSplitPercent(
			CrmSplitAssignSetParam crmSplitAssignSetParam, RespModel respModel) throws ParseException {
		
		//查询该食堂的分账信息(该食堂未来是否调整分账比例)
		CrmSplitAssignSetParam splitAssignSetParam = new CrmSplitAssignSetParam();
		splitAssignSetParam.setCanteenId(crmSplitAssignSetParam.getCanteenId());
		splitAssignSetParam.setDelFlag(1);
		splitAssignSetParam.setJudgeEffectiveTime(new Date());
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = this.commonQuery(crmSplitAssignSetParam);
		if(BeanUtils.isEmpty(crmSplitAssignSetPOs)||crmSplitAssignSetPOs.size()!=1){
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("数据异常");
			respModel.setRows(null);
			return true;
		}
		Date effectiveTime =new Date();
		String effectiveTimeStr = crmSplitAssignSetParam.getEffectiveTimeStr();
		if(BeanUtils.isNotEmpty(effectiveTimeStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			effectiveTime = sdf.parse(effectiveTimeStr);
		}
		
		
		//若未来时间未调整分账比例则新增分账比例数据
		if(CollectionUtils.isEmpty(crmSplitAssignSetPOs)){
			CrmSplitAssignSetPO crmSplitAssignSetPO = BeanConvertor.convertBean(crmSplitAssignSetParam, CrmSplitAssignSetPO.class);
			crmSplitAssignSetPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_split_assign_set));
			crmSplitAssignSetPO.setEffectiveTime(effectiveTime);
			crmSplitAssignSetPO.setCreaterTime(new Date());
			logger.info("#####CrmSplitAssignSetServiceImpl###saveAdjustSplitPercent##保存crmSplitAssignSetPO:"+crmSplitAssignSetPO.toString());
			this.save(crmSplitAssignSetPO);
			return true;
		}
		
		
		//若未来存在一条调整分账比例数据,则更新
		CrmSplitAssignSetPO crmSplitAssignSetPO = crmSplitAssignSetPOs.get(0);
		crmSplitAssignSetPO.setCreaterUid(crmSplitAssignSetParam.getCreaterUid());
		crmSplitAssignSetPO.setCreaterTime(new Date());
		crmSplitAssignSetPO.setEffectiveTime(effectiveTime);
		crmSplitAssignSetPO.setCanteenSplitPercent(crmSplitAssignSetParam.getCanteenSplitPercent());
		crmSplitAssignSetPO.setAgentSplitPercent(crmSplitAssignSetParam.getAgentSplitPercent());
		crmSplitAssignSetPO.setBusinssSplitPercent(crmSplitAssignSetParam.getBusinssSplitPercent());
		logger.info("#####CrmSplitAssignSetServiceImpl###saveAdjustSplitPercent##更新crmSplitAssignSetPO:"+crmSplitAssignSetPO.toString());
		this.update(crmSplitAssignSetPO);
		
		return false;
	}

	/**
	 * 根据 客户id查询对象 
	* @Title: findByCanteenId 
	* @author : lcl
	* @time : 2017年6月23日 上午9:44:40
	* @return CrmSplitAssignSetPO    返回类型 
	* @throws
	 */
	@Override
	public CrmSplitAssignSetPO findByCanteenId(Long canteenId) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("canteenId", canteenId);
		List<CrmSplitAssignSetPO> cAssignSetPOs = listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.findByCanteenId", paramMap);
		if(CollectionUtils.isNotEmpty(cAssignSetPOs)){
			return cAssignSetPOs.get(0);
		}
		
		return null;
	}

	/**
	 * 根据订单Id查询分账信息
	 */
	@Override
	public CrmSplitAssignSetPO findByOrderId(Long orderId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("orderId", orderId);
		List<CrmSplitAssignSetPO> splitAssignSetPOs = listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.findByOrderId", paramMap);
		if (CollectionUtils.isNotEmpty(splitAssignSetPOs)) {
			return splitAssignSetPOs.get(0);
		}
		return null;
	}
	/**
	 * 根据业务员id获取业务员分账比例
	 */
	@Override
	public CrmSplitAssignSetPO findByBusinessId(Long businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId);
		List<CrmSplitAssignSetPO> clist = listByParams(CrmSplitAssignSetPO.class, "CrmSplitAssignSetPOMapper.findByBussiness", paramMap);
		if (BeanUtils.isNotEmpty(clist)) {
			return clist.get(0);
		}
		return null;
	}



}
