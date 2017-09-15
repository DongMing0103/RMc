package com.hd.kzscrm.service.serviceimpl.business;

//crmcontractpic
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
import com.hd.kzscrm.dao.entity.business.CrmContractPicPO;
import com.hd.kzscrm.service.param.business.CrmContractPicParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmContractPicService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmContractPicVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmContractPic 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmContractPicServiceImpl extends BaseServiceImpl implements ICrmContractPicService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmContractPicServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmContractPicServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmContractPicVO> convertPOToVO(List<CrmContractPicPO> crmcontractpicList){
    	List<CrmContractPicVO> crmcontractpicVoList = new ArrayList<CrmContractPicVO>();
    	if (CollectionUtils.isEmpty(crmcontractpicList)) {
    		return crmcontractpicVoList;
    	}
    	for (CrmContractPicPO tag : crmcontractpicList) {
    		CrmContractPicVO tagVo = BeanConvertor.copy(tag,CrmContractPicVO.class);
    		crmcontractpicVoList.add(tagVo);
    	}
    	return crmcontractpicVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmContractPicVO> queryPage(CrmContractPicParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmContractPicName",param.getCrmContractPicName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmContractPicPO> crmcontractpicList = findPageByParams(CrmContractPicPO.class,new Page<CrmContractPicPO>(param.getOffset(),param.getLimit()),"CrmContractPicPOMapper.queryPage",paramMap);
    	List<CrmContractPicVO> rows = new ArrayList<CrmContractPicVO>();
    	int total = 0;
    	if(crmcontractpicList.result != null){
    		rows = convertPOToVO(crmcontractpicList.result);
    		total = crmcontractpicList.getTotalResult();
    	}    	
    	Page<CrmContractPicVO> pageResult = new Page<CrmContractPicVO>();
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
    public List<CrmContractPicPO> listByParam(CrmContractPicParam crmcontractpicParam){
    	ParamMap paramMap = new ParamMap(crmcontractpicParam);
    	return commonDao.listByParams(CrmContractPicPO.class,"CrmContractPicPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmContractPicPO po = this.get(CrmContractPicPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmContractPicPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmContractPicPO> listPo = new ArrayList<CrmContractPicPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmContractPicPO po = this.get(CrmContractPicPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmContractPicPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmContractPicParam param){
    	CrmContractPicPO crmcontractpicPO = BeanConvertor.copy(param,CrmContractPicPO.class);
    	this.save(crmcontractpicPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmContractPicPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_contract_pic));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmContractPicPO po){
     	this.update(po);
    }

	@Override
	public CrmContractPicVO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

