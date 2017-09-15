package com.hd.kzscrm.service.serviceimpl.perm;

//crmpermuserrole
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.service.param.perm.CrmPermUserRoleParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.perm.CrmPermUserRoleVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmPermUserRole 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmPermUserRoleServiceImpl extends BaseServiceImpl implements
		ICrmPermUserRoleService {

	// 日志服务对象
	protected static final Logger logger = LoggerFactory
			.getLogger(CrmPermUserRoleServiceImpl.class);

	/**
	 * 默认构造函数
	 */

	public CrmPermUserRoleServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	/**
	 * PO转换为VO
	 * 
	 * @param param
	 * @return
	 */
	private List<CrmPermUserRoleVO> convertPOToVO(
			List<CrmPermUserRolePO> crmpermuserroleList) {
		List<CrmPermUserRoleVO> crmpermuserroleVoList = new ArrayList<CrmPermUserRoleVO>();
		if (CollectionUtils.isEmpty(crmpermuserroleList)) {
			return crmpermuserroleVoList;
		}
		for (CrmPermUserRolePO tag : crmpermuserroleList) {
			CrmPermUserRoleVO tagVo = BeanConvertor.copy(tag,
					CrmPermUserRoleVO.class);
			crmpermuserroleVoList.add(tagVo);
		}
		return crmpermuserroleVoList;
	}

	/**
	 * 查询
	 */
	@Override
	public Page<CrmPermUserRoleVO> queryPage(CrmPermUserRoleParam param) {
		ParamMap paramMap = new ParamMap(param);
		/*
		 * paramMap.put("CrmPermUserRoleName",param.getCrmPermUserRoleName());
		 * paramMap.put("status",param.getStatus()); String sortStr =
		 * param.getSort(); if(param.getSortConditionMap().size() == 0){
		 * if(StringUtil.isEmpty(sortStr)){ paramMap.addOrder("sort_no",asc); }
		 * }
		 */
		Page<CrmPermUserRolePO> crmpermuserroleList = findPageByParams(
				CrmPermUserRolePO.class,
				new Page<CrmPermUserRolePO>(param.getOffset(), param.getLimit()),
				"CrmPermUserRolePOMapper.queryPage", paramMap);
		List<CrmPermUserRoleVO> rows = new ArrayList<CrmPermUserRoleVO>();
		int total = 0;
		if (crmpermuserroleList.result != null) {
			rows = convertPOToVO(crmpermuserroleList.result);
			total = crmpermuserroleList.getTotalResult();
		}
		Page<CrmPermUserRoleVO> pageResult = new Page<CrmPermUserRoleVO>();
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
	public List<CrmPermUserRolePO> listByParam(
			CrmPermUserRoleParam crmpermuserroleParam) {
		ParamMap paramMap = new ParamMap(crmpermuserroleParam);
		return commonDao.listByParams(CrmPermUserRolePO.class,
				"CrmPermUserRolePOMapper.queryList", paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmPermUserRolePO po =
		 * this.get(CrmPermUserRolePO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmPermUserRolePOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmPermUserRolePO> listPo = new ArrayList<CrmPermUserRolePO>(
				idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmPermUserRolePO po = this.get(CrmPermUserRolePO.class, idl[i]);
			if (po != null) {
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmPermUserRolePO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmPermUserRoleParam param) {
		CrmPermUserRolePO crmpermuserrolePO = BeanConvertor.copy(param,
				CrmPermUserRolePO.class);
		this.save(crmpermuserrolePO);
	}
	/**
	 * 
	 * @author 黄霄仪
	 * @date 2017年7月3日 下午4:58:40
	 * @param userId crm_user表的ID
	 */
	@Override
	public CrmPermUserRolePO findByUserId(Long userId){
		CrmPermUserRoleParam crmpermuserroleParam=new CrmPermUserRoleParam();
		crmpermuserroleParam.setUserId(userId);
		List<CrmPermUserRolePO> crmPermUserRolePOs = this.listByParam(crmpermuserroleParam);
		if(BeanUtils.isEmpty(crmPermUserRolePOs)){
			return null;
		}else{
			Assert.isTrue(crmPermUserRolePOs.size()==1,"获取的数据大于1");
			return crmPermUserRolePOs.get(0);
		}
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmPermUserRoleParam crmPermUserRoleParam) {
		Assert.notNull(crmPermUserRoleParam, "对象不能为空");
		Long id = crmPermUserRoleParam.getId();
		if(BeanUtils.isEmpty(id)){
			crmPermUserRoleParam.setId(SystemCacheHelper
					.genNextIDValue(DatabaseTableNameEnum.crm_perm_user_role));
		}
		CrmPermUserRolePO po=BeanConvertor.convertBean(crmPermUserRoleParam, CrmPermUserRolePO.class);
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmPermUserRolePO po) {
		this.update(po);
	}
}
