package com.hd.kzscrm.service.serviceimpl.enterprise;

//crmclientresource
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.enums.EnterpriseStyleEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterpriseCanteenPO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.service.param.enterprise.CrmEnterpriseCanteenParam;
import com.hd.kzscrm.service.param.enterprise.CrmEnterpriseParam;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseCanteenService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.enterprise.CrmEnterpriseVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmClientResource 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmEnterpriseServiceImpl extends BaseServiceImpl implements
		ICrmEnterpriseService {
	//客户资源表
	@Resource
	ICrmClientResourceService iCrmClientResourceService;
	
	//企业与商家映射表
	@Resource
	ICrmEnterpriseCanteenService iCrmEnterpriseCanteenService;
	
	/**
	 * 城市信息
	 */
	@Resource
	private IBaseAreaService iBaseAreaService;
	// 日志服务对象
	protected static final Logger logger = LoggerFactory
			.getLogger(CrmEnterpriseServiceImpl.class);

	/**
	 * 默认构造函数
	 */

	public CrmEnterpriseServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	@Override
	public CrmEnterprisePO findById(Long id) {
		CrmEnterpriseParam param=new CrmEnterpriseParam();
		param.setId(id);
		List<CrmEnterprisePO> crmEnterprisePOs = listByParam(param);
		if (BeanUtils.isEmpty(crmEnterprisePOs)) {
			return null;
		}
		Assert.isTrue(crmEnterprisePOs.size() == 1, "预期值为1个，实际为"
				+ crmEnterprisePOs.size());
		return crmEnterprisePOs.get(0);
	}

	/**
	 * 查询
	 */
	@Override
	public Page<CrmEnterpriseVO> queryPage(CrmEnterpriseParam param) {
		ParamMap paramMap = new ParamMap(param);
		/*
		 * paramMap.put("CrmCanteenApplyName",param.getCrmCanteenApplyName());
		 * paramMap.put("status",param.getStatus()); String sortStr =
		 * param.getSort(); if(param.getSortConditionMap().size() == 0){
		 * if(StringUtil.isEmpty(sortStr)){ paramMap.addOrder("sort_no",asc); }
		 * }
		 */
		Page<CrmEnterprisePO> crmEnterpriseList = findPageByParams(
				CrmEnterprisePO.class,
				new Page<CrmEnterprisePO>(param.getOffset(), param.getLimit()),
				"CrmEnterprisePOMapper.queryPage", paramMap);
		List<CrmEnterpriseVO> rows = new ArrayList<CrmEnterpriseVO>();
		int total = 0;
		if (crmEnterpriseList.result != null) {
			rows = BeanConvertor.copyList(crmEnterpriseList.result,
					CrmEnterpriseVO.class);
			total = crmEnterpriseList.getTotalResult();
		}
		Page<CrmEnterpriseVO> pageResult = new Page<CrmEnterpriseVO>();
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
	public List<CrmEnterprisePO> listByParam(
			CrmEnterpriseParam crmEnterpriseParam) {
		ParamMap paramMap = new ParamMap(crmEnterpriseParam);
		return commonDao.listByParams(CrmEnterprisePO.class,
				"CrmEnterprisePOMapper.queryList", paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmCanteenApplyPO po =
		 * this.get(CrmCanteenApplyPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmEnterprisePOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmEnterprisePO> listPo = new ArrayList<CrmEnterprisePO>(
				idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmEnterprisePO po = this.get(CrmEnterprisePO.class, idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmEnterprisePO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmEnterpriseParam param) {
		Long id = param.getId();
		if (BeanUtils.isEmpty(id)) {
			param.setId(ServiceUtil
					.genNextIDValue(DatabaseTableNameEnum.crm_enterprise));
		}
		CrmEnterprisePO crmcanteenapplyPO = BeanConvertor.copy(param,
				CrmEnterprisePO.class);
		this.save(crmcanteenapplyPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmEnterprisePO po) {
		po.setId(SystemCacheHelper
				.genNextIDValue(DatabaseTableNameEnum.crm_enterprise));
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmEnterprisePO po) {
		this.update(po);
	}

	@Override
	public CrmEnterprisePO getById(Long id) {
		Assert.notNull(id, "id不能为空");
		// TODO Auto-generated method stub
		return commonDao.get(CrmEnterprisePO.class, id);
	}

	/**
	 * 根据userId 获取企业信息
	 */
	@Override
	public CrmEnterprisePO getByUserId(Long userId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("userId", userId);
		List<CrmEnterprisePO> clist = commonDao.listByParams(
				CrmEnterprisePO.class, "CrmEnterprisePOMapper.getByUsesrId",
				paramMap);
		if (clist != null) {
			return clist.get(0);
		}
		return null;
	}

	/**
	 * 获取企业信息
	 */
	@Override
	public Boolean viewEnterpriseInformation(Long clientId,
			ModelAndView modelAndView) {
		//查询客户资源信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) iCrmClientResourceService.getByExample(crmClientResourcePO);
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();
		if(BeanUtils.isEmpty(crmClientResourcePO) || null == clientType || 1 == clientType || 4 == clientType || null == agentCanteenId){
			return true;
		}
		
		//查询企业与商家映射表
		CrmEnterpriseCanteenParam crmEnterpriseCanteenParam = new CrmEnterpriseCanteenParam();
		crmEnterpriseCanteenParam.setCanteenId(agentCanteenId);
		crmEnterpriseCanteenParam.setDelFlag(1);
		List<CrmEnterpriseCanteenPO> crmEnterpriseCanteenPOs = iCrmEnterpriseCanteenService.commonQuery(crmEnterpriseCanteenParam);
		if(CollectionUtils.isEmpty(crmEnterpriseCanteenPOs)){
			return true;
		}
		
		//查询企业信息
		CrmEnterpriseCanteenPO crmEnterpriseCanteenPO = crmEnterpriseCanteenPOs.get(0);
		Long enterpriseId = crmEnterpriseCanteenPO.getEnterpriseId();
		
		CrmEnterprisePO crmEnterprisePO = new CrmEnterprisePO();
		crmEnterprisePO.setId(enterpriseId);
		crmEnterprisePO = (CrmEnterprisePO) this.getByExample(crmEnterprisePO);
		//获取详细地址信息
		//Integer areaCode = crmEnterprisePO.getAreaCode();
		String area = iBaseAreaService.getAreaName(CommUtil.parselong(crmEnterprisePO.getAreaCode()));
		crmEnterprisePO.setAddress(area+"/"+crmEnterprisePO.getAddress());
		
		/*if(crmEnterprisePO!=null && crmEnterprisePO.getAreaCode()!=null){
			//获取到区
			BaseAreaPO cityQ=this.iBaseAreaService.getCityById((long)crmEnterprisePO.getAreaCode());
			//获取到市
			BaseAreaPO cityS=this.iBaseAreaService.getCityById(cityQ.getParentCode());
			
			modelAndView.addObject("prov",cityS.getParentCode());
			modelAndView.addObject("city",cityS.getAreaCode());
			modelAndView.addObject("dist",cityQ.getAreaCode());
		}*/
		modelAndView.addObject("epo",crmEnterprisePO);
		modelAndView.addObject("enterpriseStyleEnum",EnterpriseStyleEnum.getEnumList());
		return false;
	}
}
