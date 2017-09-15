package com.hd.kzscrm.service.serviceimpl.business;

//crmbusinessredio
import java.math.BigDecimal;
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
import com.hd.kzscrm.dao.entity.business.CrmBusinessRedioPO;
import com.hd.kzscrm.service.param.business.CrmBusinessRedioParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessRedioService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessRedioVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmBusinessRedio 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmBusinessRedioServiceImpl extends BaseServiceImpl implements ICrmBusinessRedioService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessRedioServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmBusinessRedioServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmBusinessRedioVO> convertPOToVO(List<CrmBusinessRedioPO> crmbusinessredioList){
    	List<CrmBusinessRedioVO> crmbusinessredioVoList = new ArrayList<CrmBusinessRedioVO>();
    	if (CollectionUtils.isEmpty(crmbusinessredioList)) {
    		return crmbusinessredioVoList;
    	}
    	for (CrmBusinessRedioPO tag : crmbusinessredioList) {
    		CrmBusinessRedioVO tagVo = BeanConvertor.copy(tag,CrmBusinessRedioVO.class);
    		crmbusinessredioVoList.add(tagVo);
    	}
    	return crmbusinessredioVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmBusinessRedioVO> queryPage(CrmBusinessRedioParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmBusinessRedioName",param.getCrmBusinessRedioName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmBusinessRedioPO> crmbusinessredioList = findPageByParams(CrmBusinessRedioPO.class,new Page<CrmBusinessRedioPO>(param.getOffset(),param.getLimit()),"CrmBusinessRedioPOMapper.queryPage",paramMap);
    	List<CrmBusinessRedioVO> rows = new ArrayList<CrmBusinessRedioVO>();
    	int total = 0;
    	if(crmbusinessredioList.result != null){
    		rows = convertPOToVO(crmbusinessredioList.result);
    		total = crmbusinessredioList.getTotalResult();
    	}    	
    	Page<CrmBusinessRedioVO> pageResult = new Page<CrmBusinessRedioVO>();
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
    public List<CrmBusinessRedioPO> listByParam(CrmBusinessRedioParam crmbusinessredioParam){
    	ParamMap paramMap = new ParamMap(crmbusinessredioParam);
    	return commonDao.listByParams(CrmBusinessRedioPO.class,"CrmBusinessRedioPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmBusinessRedioPO po = this.get(CrmBusinessRedioPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmBusinessRedioPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmBusinessRedioPO> listPo = new ArrayList<CrmBusinessRedioPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmBusinessRedioPO po = this.get(CrmBusinessRedioPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmBusinessRedioPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmBusinessRedioParam param){
    	CrmBusinessRedioPO crmbusinessredioPO = BeanConvertor.copy(param,CrmBusinessRedioPO.class);
    	this.save(crmbusinessredioPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmBusinessRedioPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business_redio));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmBusinessRedioPO po){
     	this.update(po);
    }

//	@Override
	public Integer queryByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal queryBusinessRedio(Integer year, Integer type) {
		List<CrmBusinessRedioPO> list = this.listByParam(null);
		for (CrmBusinessRedioPO redio:list) {
			if(redio.getYear()==year && redio.getBusinessType()==type){
				return redio.getRedio();
			}
		}
		return null;
		
	}
}

