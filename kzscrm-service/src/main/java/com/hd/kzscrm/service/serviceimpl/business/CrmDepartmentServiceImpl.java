package com.hd.kzscrm.service.serviceimpl.business;

//crmdepartment
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
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.business.CrmDepartmentPO;
import com.hd.kzscrm.service.param.business.CrmDepartmentParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmDepartmentService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmDepartmentVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmDepartment 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmDepartmentServiceImpl extends BaseServiceImpl implements ICrmDepartmentService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmDepartmentServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmDepartmentServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmDepartmentVO> convertPOToVO(List<CrmDepartmentPO> crmdepartmentList){
    	List<CrmDepartmentVO> crmdepartmentVoList = new ArrayList<CrmDepartmentVO>();
    	if (CollectionUtils.isEmpty(crmdepartmentList)) {
    		return crmdepartmentVoList;
    	}
    	for (CrmDepartmentPO tag : crmdepartmentList) {
    		CrmDepartmentVO tagVo = BeanConvertor.copy(tag,CrmDepartmentVO.class);
    		crmdepartmentVoList.add(tagVo);
    	}
    	return crmdepartmentVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmDepartmentVO> queryPage(CrmDepartmentParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmDepartmentName",param.getCrmDepartmentName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmDepartmentPO> crmdepartmentList = findPageByParams(CrmDepartmentPO.class,new Page<CrmDepartmentPO>(param.getOffset(),param.getLimit()),"CrmDepartmentPOMapper.queryPage",paramMap);
    	List<CrmDepartmentVO> rows = new ArrayList<CrmDepartmentVO>();
    	int total = 0;
    	if(crmdepartmentList.result != null){
    		rows = convertPOToVO(crmdepartmentList.result);
    		total = crmdepartmentList.getTotalResult();
    	}    	
    	Page<CrmDepartmentVO> pageResult = new Page<CrmDepartmentVO>();
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
    public List<CrmDepartmentPO> listByParam(CrmDepartmentParam crmdepartmentParam){
    	ParamMap paramMap = new ParamMap(crmdepartmentParam);
    	return commonDao.listByParams(CrmDepartmentPO.class,"CrmDepartmentPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmDepartmentPO po = this.get(CrmDepartmentPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmDepartmentPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmDepartmentPO> listPo = new ArrayList<CrmDepartmentPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmDepartmentPO po = this.get(CrmDepartmentPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmDepartmentPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmDepartmentParam param){
    	CrmDepartmentPO crmdepartmentPO = BeanConvertor.copy(param,CrmDepartmentPO.class);
    	this.save(crmdepartmentPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmDepartmentPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_department));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmDepartmentPO po){
     	this.update(po);
    }

	@Override
	public CrmDepartmentPO getById(Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		List<CrmDepartmentPO> cList = commonDao.listByParams(CrmDepartmentPO.class, "CrmDepartmentPOMapper.findById", paramMap);
		if(CollectionUtils.isNotEmpty(cList)){
			return cList.get(0);
		}
		return null;
	}

	/**
	 * 根据agentId 获取部门信息
	 */
	@Override
	public  CrmDepartmentPO findByAgentId(Long agentId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentId", agentId);
		List<CrmDepartmentPO> clist = commonDao.listByParams(CrmDepartmentPO.class, "CrmDepartmentPOMapper.findByAgentId", paramMap);
		if (clist != null) {
			return clist.get(0);
		}
		return null;
	}

}

