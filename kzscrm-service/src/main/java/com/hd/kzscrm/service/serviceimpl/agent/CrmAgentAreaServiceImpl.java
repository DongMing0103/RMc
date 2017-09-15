package com.hd.kzscrm.service.serviceimpl.agent;

//crmagentarea
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.agent.CrmAgentAreaPO;
import com.hd.kzscrm.service.param.agent.CrmAgentAreaParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentAreaService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.agent.CrmAgentAreaVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmAgentArea 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmAgentAreaServiceImpl extends BaseServiceImpl implements ICrmAgentAreaService {

	// 日志服务对象
	protected static final Logger logger = LoggerFactory.getLogger(CrmAgentAreaServiceImpl.class);

	/**
	 * 默认构造函数
	 */

	public CrmAgentAreaServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	/**
	 * PO转换为VO
	 * 
	 * @param param
	 * @return
	 */
	private List<CrmAgentAreaVO> convertPOToVO(List<CrmAgentAreaPO> crmagentareaList) {
		List<CrmAgentAreaVO> crmagentareaVoList = new ArrayList<CrmAgentAreaVO>();
		if (CollectionUtils.isEmpty(crmagentareaList)) {
			return crmagentareaVoList;
		}
		for (CrmAgentAreaPO tag : crmagentareaList) {
			CrmAgentAreaVO tagVo = BeanConvertor.copy(tag, CrmAgentAreaVO.class);
			crmagentareaVoList.add(tagVo);
		}
		return crmagentareaVoList;
	}

	/**
	 * 查询
	 */
	@Override
	public Page<CrmAgentAreaVO> queryPage(CrmAgentAreaParam param) {
		ParamMap paramMap = new ParamMap(param);
		/*
		 * paramMap.put("CrmAgentAreaName",param.getCrmAgentAreaName());
		 * paramMap.put("status",param.getStatus()); String sortStr =
		 * param.getSort(); if(param.getSortConditionMap().size() == 0){
		 * if(StringUtil.isEmpty(sortStr)){ paramMap.addOrder("sort_no",asc); }
		 * }
		 */
		Page<CrmAgentAreaPO> crmagentareaList = findPageByParams(CrmAgentAreaPO.class,
				new Page<CrmAgentAreaPO>(param.getOffset(), param.getLimit()), "CrmAgentAreaPOMapper.queryPage", paramMap);
		List<CrmAgentAreaVO> rows = new ArrayList<CrmAgentAreaVO>();
		int total = 0;
		if (crmagentareaList.result != null) {
			rows = convertPOToVO(crmagentareaList.result);
			total = crmagentareaList.getTotalResult();
		}
		Page<CrmAgentAreaVO> pageResult = new Page<CrmAgentAreaVO>();
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
	public List<CrmAgentAreaPO> listByParam(CrmAgentAreaParam crmagentareaParam) {
		ParamMap paramMap = new ParamMap(crmagentareaParam);
		return commonDao.listByParams(CrmAgentAreaPO.class, "CrmAgentAreaPOMapper.queryList", paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmAgentAreaPO po =
		 * this.get(CrmAgentAreaPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmAgentAreaPOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmAgentAreaPO> listPo = new ArrayList<CrmAgentAreaPO>(idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmAgentAreaPO po = this.get(CrmAgentAreaPO.class, idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmAgentAreaPO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmAgentAreaParam param) {
		CrmAgentAreaPO crmagentareaPO = BeanConvertor.copy(param, CrmAgentAreaPO.class);
		this.save(crmagentareaPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmAgentAreaPO po) {
		po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_agent_area));
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmAgentAreaPO po) {
		this.update(po);
	}

	@Override
	public CrmAgentAreaPO getById(Long id) {
		return null;
	}
	   /**
     * 根据代理商id查询 代理商区域列表
    * @Title: findByAgentId 
    * @author : lcl
    * @time : 2017年6月27日 下午9:12:41
    * @return CrmAgentAreaPO    返回类型 
    * @throws
     */
	@Override
	public CrmAgentAreaPO findByAgentId(Long agentId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentId", agentId);
		List<CrmAgentAreaPO> agentAreaPOs = listByParams(CrmAgentAreaPO.class, "CrmAgentAreaPOMapper.findByAgentId", paramMap);
		if(CollectionUtils.isNotEmpty(agentAreaPOs)){
			return agentAreaPOs.get(0);
		}
		return null;
	}
}
