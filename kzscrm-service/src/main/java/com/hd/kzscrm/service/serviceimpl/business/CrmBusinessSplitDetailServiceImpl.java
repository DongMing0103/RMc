package com.hd.kzscrm.service.serviceimpl.business;

//crmbusinesssplitdetail
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessSplitDetailPO;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.kzscrm.service.param.business.CrmBusinessSplitDetailParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessSplitDetailService;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderService;
import com.hd.kzscrm.service.serviceInter.split.ISplitCutInfoService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessSplitDetailVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

import scala.annotation.meta.param;

/**
 *
 * 
 * @description crmBusinessSplitDetail 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmBusinessSplitDetailServiceImpl extends BaseServiceImpl implements ICrmBusinessSplitDetailService {

	// 日志服务对象
	protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessSplitDetailServiceImpl.class);
	@Resource
	private SqlSessionTemplate sqlSession;

	// 地理信息表
	@Resource
	private IBaseAreaService iBaseAreaService;
	
	/**
	 * 订单表（order表）
	 */
	@Autowired
	private ICrmOrderService orderService;
	
	/**
	 * 抽成分配设置表
	 */
	@Autowired
	private ICrmSplitAssignSetService splitAssignSetService;
	
	@Autowired
	ISplitCutInfoService iSplitCutInfoService; // 抽成明细
	/**
	 * 默认构造函数
	 */

	public CrmBusinessSplitDetailServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	/**
	 * PO转换为VO
	 * 
	 * @param param
	 * @return
	 */
	private List<CrmBusinessSplitDetailVO> convertPOToVO(List<CrmBusinessSplitDetailPO> crmbusinesssplitdetailList) {
		List<CrmBusinessSplitDetailVO> crmbusinesssplitdetailVoList = new ArrayList<CrmBusinessSplitDetailVO>();
		if (CollectionUtils.isEmpty(crmbusinesssplitdetailList)) {
			return crmbusinesssplitdetailVoList;
		}
		for (CrmBusinessSplitDetailPO tag : crmbusinesssplitdetailList) {
			CrmBusinessSplitDetailVO tagVo = BeanConvertor.copy(tag, CrmBusinessSplitDetailVO.class);
			crmbusinesssplitdetailVoList.add(tagVo);
		}
		return crmbusinesssplitdetailVoList;
	}

	/**
	 * 查询
	 * 分账明细
	 */
	@Override
	public Page<CrmBusinessSplitDetailVO> queryPage(CrmBusinessSplitDetailParam param) {

		// 查省份时 转换成 areaCode
		if (BeanUtils.isEmpty(param.getAreaCode()) && BeanUtils.isNotEmpty(param.getProvCode())) {
			BaseAreaPO baseAreaPO = new BaseAreaPO();
			baseAreaPO.setParentCode(param.getProvCode().longValue());// 设置父类的code
			List<BaseAreaPO> baseAreaPOs = iBaseAreaService.getCityByParentCode(baseAreaPO);
			if (BeanUtils.isNotEmpty(baseAreaPOs)) {
				List<Integer> areaCodes = new ArrayList<Integer>();
				for (BaseAreaPO b : baseAreaPOs) {
					BaseAreaPO baseAreaPO2 = new BaseAreaPO();
					baseAreaPO2.setParentCode(b.getAreaCode());
					List<BaseAreaPO> baList = iBaseAreaService.getCityByParentCode(baseAreaPO2);
					if (CollectionUtils.isNotEmpty(baList)) {
						for (BaseAreaPO b1 : baList) {
							areaCodes.add(b1.getAreaCode().intValue());
						}
					}
				}
				param.setAreaCodes(areaCodes);// 查询省下面的areaCode
			}
		}
		// 判断是否是市区
		if (BeanUtils.isEmpty(param.getAreaCode()) && BeanUtils.isNotEmpty(param.getCityCode())) {
			BaseAreaPO baseAreaPO = new BaseAreaPO();
			baseAreaPO.setParentCode(param.getCityCode().longValue());
			List<BaseAreaPO> baList = iBaseAreaService.getCityByParentCode(baseAreaPO);
			List<Integer> areaCodes = new ArrayList<Integer>();
			if (CollectionUtils.isNotEmpty(baList)) {
				for (BaseAreaPO b : baList) {
					areaCodes.add(b.getAreaCode().intValue());
				}
			}
			param.setAreaCodes(areaCodes);
		}

		ParamMap paramMap = new ParamMap(param);
		Page<CrmBusinessSplitDetailPO> crmbusinesssplitdetailList = findPageByParams(CrmBusinessSplitDetailPO.class,
				new Page<CrmBusinessSplitDetailPO>(param.getOffset(), param.getLimit()),
				"CrmBusinessSplitDetailPOMapper.queryPage", paramMap);
		List<CrmBusinessSplitDetailVO> rows = new ArrayList<CrmBusinessSplitDetailVO>();
		int total = 0;
		if (crmbusinesssplitdetailList.result != null) {
			rows = convertPOToVO(crmbusinesssplitdetailList.result);
			total = crmbusinesssplitdetailList.getTotalResult();
		}
		Page<CrmBusinessSplitDetailVO> pageResult = new Page<CrmBusinessSplitDetailVO>();
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
	public List<CrmBusinessSplitDetailPO> listByParam(CrmBusinessSplitDetailParam crmbusinesssplitdetailParam) {
		ParamMap paramMap = new ParamMap(crmbusinesssplitdetailParam);
		return commonDao.listByParams(CrmBusinessSplitDetailPO.class, "CrmBusinessSplitDetailPOMapper.queryList",
				paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmBusinessSplitDetailPO po =
		 * this.get(CrmBusinessSplitDetailPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmBusinessSplitDetailPOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmBusinessSplitDetailPO> listPo = new ArrayList<CrmBusinessSplitDetailPO>(idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmBusinessSplitDetailPO po = this.get(CrmBusinessSplitDetailPO.class, idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmBusinessSplitDetailPO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmBusinessSplitDetailParam param) {
		CrmBusinessSplitDetailPO crmbusinesssplitdetailPO = BeanConvertor.copy(param, CrmBusinessSplitDetailPO.class);
		this.save(crmbusinesssplitdetailPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmBusinessSplitDetailPO po) {
		po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business_split_detail));
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmBusinessSplitDetailPO po) {
		this.update(po);
	}

	@Override
	public CrmBusinessSplitDetailPO getById(Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		List<CrmBusinessSplitDetailPO> clist= commonDao.listByParams(CrmBusinessSplitDetailPO.class, "CrmBusinessSplitDetailPOMapper.findById", paramMap);
		if (BeanUtils.isNotEmpty(clist)) {
			return clist.get(0);
		}
		return null;
	}

	@Override
	public Integer queryCrmBusinessSplitDetail(String orderNo) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("orderNo", orderNo);
		Integer totalOrders = sqlSession.selectOne("CrmBusinessSplitDetailPOMapper.queryOrderNoISNull", paramMap);
		if (BeanUtils.isEmpty(totalOrders)) {
			return 0;
		} else {
			return totalOrders;
		}
	}

	public Integer queryByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer saveCrmBusinessSplitDetail(String orderNo, Long bussinessId, Long agentId, BigDecimal businessMoney,
			BigDecimal agentMoney, BigDecimal platformMoney,Integer payModel) {
		CrmBusinessSplitDetailPO crmbusinesssplitdetailPO = new CrmBusinessSplitDetailPO();
		crmbusinesssplitdetailPO
				.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business_split_detail));
		crmbusinesssplitdetailPO.setOrderNo(orderNo);
		crmbusinesssplitdetailPO.setBusinessUserId(bussinessId);
		crmbusinesssplitdetailPO.setAgentUserId(agentId);
		crmbusinesssplitdetailPO.setAgentMoney(agentMoney);
		crmbusinesssplitdetailPO.setBusinessMoney(businessMoney);
		crmbusinesssplitdetailPO.setPlatformMoney(platformMoney);
		crmbusinesssplitdetailPO.setCreateTime(new Date());
		crmbusinesssplitdetailPO.setPayModel(payModel);
		this.save(crmbusinesssplitdetailPO);
		return 0;
	}

	@Override
	public Integer updateCrmBusinessSplitDetail(String orderNo, Integer status) {

		ParamMap paramMap = new ParamMap();
		paramMap.put("orderNo", orderNo);
		switch (status) {
		case 4:
			paramMap.put("businessStatus", 1);
			break;
		case 5:
			paramMap.put("agentStatus", 1);
			break;
		case 6:
			paramMap.put("platformStatus", 1);
			break;
		default:
			break;
		}
		return commonDao.execute("CrmBusinessSplitDetailPOMapper.updateCrmBusinessSplitDetail", paramMap);
	}

	/**
	 * 导出查询
	 */
	@Override
	public Page<CrmBusinessSplitDetailVO> findPageSelect(CrmBusinessSplitDetailParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmBusinessSplitDetailPO> crmList = findPageByParams(CrmBusinessSplitDetailPO.class,
				new Page<CrmBusinessSplitDetailPO>(param.getOffset(), param.getLimit()),
				"CrmBusinessSplitDetailPOMapper.queryPage", paramMap);
		List<CrmBusinessSplitDetailVO> rows = new ArrayList<CrmBusinessSplitDetailVO>();
		int total = 0;
		if (crmList.result != null) {
			for (int i = 0; i < crmList.result.size(); i++) {
				CrmBusinessSplitDetailPO crmBusinessSplitDetailPO = this.getById(crmList.result.get(i).getId());
				CrmBusinessSplitDetailPO targetPO = crmList.result.get(i);
				if (BeanUtils.isNotEmpty(crmBusinessSplitDetailPO)) {
					CrmBusinessSplitDetailVO businessSplitDetailVO = new CrmBusinessSplitDetailVO();
					businessSplitDetailVO = BeanConvertor.convertBean(targetPO, CrmBusinessSplitDetailVO.class);
//					businessSplitDetailVO.setOrderNo(crmBusinessSplitDetailPO.getOrderNo());
					rows.add(i, businessSplitDetailVO);
				}
			}
			total = crmList.getTotalResult();
		}
		Page<CrmBusinessSplitDetailVO> pageResult = new Page<CrmBusinessSplitDetailVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}

	/**
	 * 查询平台分账明细    页面数据查询
	 */
	@Override
	public Page<CrmBusinessSplitDetailVO> findPageByParams(CrmBusinessSplitDetailParam param) {
		//根据订单编号模糊查询
		if (BeanUtils.isNotEmpty(param.getCondition())) {
			List<Long> ids = new ArrayList<>();
			List<CrmBusinessSplitDetailPO> splitDetailPOs = this.findLikeByOrderNo(param.getCondition());
			if (CollectionUtils.isNotEmpty(splitDetailPOs)) {
				for (CrmBusinessSplitDetailPO po : splitDetailPOs){
					if (BeanUtils.isNotEmpty(po)) {
						ids.add(po.getId());
					}
				}
				param.setOrderIds(ids);
			}else {
				Page<CrmBusinessSplitDetailVO> pageResult = new Page<CrmBusinessSplitDetailVO>();
				pageResult.result = null;
				pageResult.setTotalResult(0);
				return pageResult;
			}
		}
		
		//页面数据显示
		param.setDelFlag(1);
		ParamMap paramMap = new ParamMap(param);
		Page<CrmBusinessSplitDetailPO> companySplitList = findPageByParams(CrmBusinessSplitDetailPO.class, 
				               new Page<CrmBusinessSplitDetailPO>(param.getOffset(),param.getLimit()),"CrmBusinessSplitDetailPOMapper.findByParam",paramMap);
		List<CrmBusinessSplitDetailVO> rows = new ArrayList<>();
		List<CrmBusinessSplitDetailPO> splitDetailPOs = companySplitList.result;
		
		int total = 0;
		//循环查询结果
		if (CollectionUtils.isNotEmpty(splitDetailPOs)) {
			for (int i = 0; i < splitDetailPOs.size(); i++) {   
				CrmBusinessSplitDetailPO splitDetailPO = splitDetailPOs.get(i);
				CrmBusinessSplitDetailVO splitDetailVO = BeanConvertor.convertBean(splitDetailPO, CrmBusinessSplitDetailVO.class);
				if (BeanUtils.isNotEmpty(splitDetailPO)) {
					//根据订单编号查询其他信息
					String orderNo = splitDetailPO.getOrderNo();
					OrderPO orderPO = orderService.findByOrderNo(orderNo);
					Long orderId = orderPO.getId();
					if (BeanUtils.isNotEmpty(orderId)) {
						splitDetailVO.setOrderId(orderId);
					}
					//查询食堂分账金额
					SplitCutInfoPO splitCutInfoPO = iSplitCutInfoService.findByOrderNo(orderNo);  //根据订单查询抽成明细
					if (BeanUtils.isNotEmpty(splitCutInfoPO)) {
						BigDecimal cutRatio = splitCutInfoPO.getCutRatio();
						BigDecimal orderRealMoney = splitCutInfoPO.getOrderRealMoney();
						splitDetailVO.setCanteenSplitMoney(orderRealMoney.multiply(cutRatio));
					}
					
					//查询分账比例
//					CrmSplitAssignSetPO assignSetPO = splitAssignSetService.findByOrderId(orderId);
//					BigDecimal businessSplitPercent = assignSetPO.getBusinssSplitPercent();
//					BigDecimal agentSplitPercent = assignSetPO.getAgentSplitPercent();
					splitDetailVO.setPayModel(splitDetailPO.getPayModel());   //获取支付方式
					CrmBusinessSplitDetailPO crmBusinessSplitDetailPO = this.findByOrderNo(orderNo);
					if (BeanUtils.isNotEmpty(crmBusinessSplitDetailPO)) {
						splitDetailVO.setCreateTime(crmBusinessSplitDetailPO.getCreateTime()); //获取分账时间
						splitDetailVO.setOrderNo(orderNo);  
						splitDetailVO.setPlatformMoney(crmBusinessSplitDetailPO.getPlatformMoney());    //平台分账金额
						splitDetailVO.setAgentMoney(crmBusinessSplitDetailPO.getAgentMoney());
						splitDetailVO.setBusinessMoney(crmBusinessSplitDetailPO.getBusinessMoney());    
//						splitDetailVO.setBusinessSplitMoney((crmBusinessSplitDetailPO.getBusinessMoney()).multiply(businessSplitPercent).divide(new BigDecimal(100)));
//						splitDetailVO.setAgentSplitMoney((crmBusinessSplitDetailPO.getAgentMoney()).multiply(agentSplitPercent).divide(new BigDecimal(100)));
					}
					if (BeanUtils.isNotEmpty(orderNo)) {
						if (BeanUtils.isNotEmpty(orderPO)) {
							splitDetailVO.setOrderFlowNo(orderPO.getOrderFlowNo()); //获取支付流水号
							splitDetailVO.setChannelMoney(orderPO.getChannelMoney()); //获取通道费
							
						}
					}
				}
				rows.add(i, splitDetailVO);
			}
		}
		total = companySplitList.getTotalResult();
		Page<CrmBusinessSplitDetailVO> pageResult = new Page<CrmBusinessSplitDetailVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}

	/**
	 * 状态查询
	 */
	@Override
	public Page<CrmBusinessSplitDetailVO> queryPageList(CrmBusinessSplitDetailParam param) {
		Page<CrmBusinessSplitDetailVO> crmBusinessSplitDetailVO = queryPage(param);
		List<CrmBusinessSplitDetailVO> crmBusinessSplitDetailVOs = crmBusinessSplitDetailVO.result;
		if (BeanUtils.isNotEmpty(crmBusinessSplitDetailVOs)) {
			CrmBusinessSplitDetailVO businessSplitDetailVO = crmBusinessSplitDetailVOs.get(0);
			businessSplitDetailVO.setOnJobNum(1);
			businessSplitDetailVO.setQuitJobNum(1);
		}
		return crmBusinessSplitDetailVO;
	}
	
	@Override
	public CrmBusinessSplitDetailPO findByOrderNo(String orderNo) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("orderNo", orderNo);
		List<CrmBusinessSplitDetailPO> listByParams = commonDao.listByParams(CrmBusinessSplitDetailPO.class, "CrmBusinessSplitDetailPOMapper.findByOrderNo", paramMap);
		if (BeanUtils.isNotEmpty(listByParams) && listByParams.size() > 0) {
			return listByParams.get(0);
		}
		return null;
	}
	
	/**
	 * 根据orderNo查询
	 */
	@Override
	public List<CrmBusinessSplitDetailPO> findByOrderNos(String orderNo) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("orderNo", orderNo);
		List<CrmBusinessSplitDetailPO> orderList = listByParams(CrmBusinessSplitDetailPO.class, "CrmBusinessSplitDetailPOMapper.findByOrderNo", paramMap);
		return orderList;
	}

	
	/**
	 * 根据代理商编号查询代理商金额
	 */
	@Override
	public CrmBusinessSplitDetailPO findByAgentNo(String agentNo) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentNo", agentNo);
		List<CrmBusinessSplitDetailPO> clist = commonDao.listByParams(CrmBusinessSplitDetailPO.class, "CrmBusinessSplitDetailPOMapper.findByAgentNo", paramMap);
		if (BeanUtils.isNotEmpty(clist)) {
			return clist.get(0);
		}
		return null;
	}

	@Override
	public List<CrmBusinessSplitDetailPO> findLikeByOrderNo(String condition) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("condition", condition);
		List<CrmBusinessSplitDetailPO> orderList = listByParams(CrmBusinessSplitDetailPO.class, "CrmBusinessSplitDetailPOMapper.findLikeByOrderNo", paramMap);
		return orderList;
	}
}
