package com.hd.kzscrm.service.serviceimpl.perm;

//crmpermrolesources
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleSourcesParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleSourcesVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmPermRoleSources 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmPermRoleSourcesServiceImpl extends BaseServiceImpl implements ICrmPermRoleSourcesService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmPermRoleSourcesServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmPermRoleSourcesServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmPermRoleSourcesVO> convertPOToVO(List<CrmPermRoleSourcesPO> crmpermrolesourcesList){
    	List<CrmPermRoleSourcesVO> crmpermrolesourcesVoList = new ArrayList<CrmPermRoleSourcesVO>();
    	if (CollectionUtils.isEmpty(crmpermrolesourcesList)) {
    		return crmpermrolesourcesVoList;
    	}
    	for (CrmPermRoleSourcesPO tag : crmpermrolesourcesList) {
    		CrmPermRoleSourcesVO tagVo = BeanConvertor.copy(tag,CrmPermRoleSourcesVO.class);
    		crmpermrolesourcesVoList.add(tagVo);
    	}
    	return crmpermrolesourcesVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmPermRoleSourcesVO> queryPage(CrmPermRoleSourcesParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmPermRoleSourcesName",param.getCrmPermRoleSourcesName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmPermRoleSourcesPO> crmpermrolesourcesList = findPageByParams(CrmPermRoleSourcesPO.class,new Page<CrmPermRoleSourcesPO>(param.getOffset(),param.getLimit()),"CrmPermRoleSourcesPOMapper.queryPage",paramMap);
    	List<CrmPermRoleSourcesVO> rows = new ArrayList<CrmPermRoleSourcesVO>();
    	int total = 0;
    	if(crmpermrolesourcesList.result != null){
    		rows = convertPOToVO(crmpermrolesourcesList.result);
    		total = crmpermrolesourcesList.getTotalResult();
    	}    	
    	Page<CrmPermRoleSourcesVO> pageResult = new Page<CrmPermRoleSourcesVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
    	
    }
    
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<CrmPermRoleSourcesPO> listByParam(CrmPermRoleSourcesParam crmpermrolesourcesParam){
    	ParamMap paramMap = new ParamMap(crmpermrolesourcesParam);
    	return commonDao.listByParams(CrmPermRoleSourcesPO.class,"CrmPermRoleSourcesPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmPermRoleSourcesPO po = this.get(CrmPermRoleSourcesPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmPermRoleSourcesPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmPermRoleSourcesPO> listPo = new ArrayList<CrmPermRoleSourcesPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmPermRoleSourcesPO po = this.get(CrmPermRoleSourcesPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(CrmCommonDelFlag.DELETE.getCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmPermRoleSourcesPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmPermRoleSourcesParam param){
    	CrmPermRoleSourcesPO crmpermrolesourcesPO = BeanConvertor.copy(param,CrmPermRoleSourcesPO.class);
    	this.save(crmpermrolesourcesPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmPermRoleSourcesPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_role_sources));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmPermRoleSourcesPO po){
     	this.update(po);
    }

    /**
     * 根据角色id查询对象
    * @Title: findByRoleId 
    * @author : lcl
    * @time : 2017年7月6日 下午5:18:22
    * @return List<CrmPermRoleSourcesPO>    返回类型 
    * @throws
     */
	@Override
	public List<CrmPermRoleSourcesPO> findByRoleId(Long roleId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("roleId", roleId);
		List<CrmPermRoleSourcesPO> roleSourcesPOs = listByParams(CrmPermRoleSourcesPO.class, "CrmPermRoleSourcesPOMapper.findByRoleId", paramMap);
		if(CollectionUtils.isNotEmpty(roleSourcesPOs)){
			return roleSourcesPOs;
		}
		return null;
	}
}

