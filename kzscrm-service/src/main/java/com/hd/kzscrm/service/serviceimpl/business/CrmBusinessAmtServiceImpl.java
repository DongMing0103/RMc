package com.hd.kzscrm.service.serviceimpl.business;

//crmbusinessamt
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.business.CrmBusinessAmtPO;
import com.hd.kzscrm.service.param.business.CrmBusinessAmtParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessAmtService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessAmtVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmBusinessAmt 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmBusinessAmtServiceImpl extends BaseServiceImpl implements ICrmBusinessAmtService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessAmtServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmBusinessAmtServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
	@Override
	public CrmBusinessAmtPO findByUserId(Long userId) {
		Assert.notNull(userId, "用户ID不能为空");
		CrmBusinessAmtParam crmBusinessAmtParam = new CrmBusinessAmtParam();
		crmBusinessAmtParam.setUserId(userId);
		List<CrmBusinessAmtPO> crmBusinessAmtPOs = this
				.listByParam(crmBusinessAmtParam);
		if (BeanUtils.isNotEmpty(crmBusinessAmtPOs)) {
			Assert.isTrue(crmBusinessAmtPOs.size() == 1, "不能大于1");
			return crmBusinessAmtPOs.get(0);
		}
		return null;
	}
        
    /**
     * 查询
     */
    @Override
    public Page<CrmBusinessAmtVO> queryPage(CrmBusinessAmtParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmBusinessAmtName",param.getCrmBusinessAmtName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmBusinessAmtPO> crmbusinessamtList = findPageByParams(CrmBusinessAmtPO.class,new Page<CrmBusinessAmtPO>(param.getOffset(),param.getLimit()),"CrmBusinessAmtPOMapper.queryPage",paramMap);
    	List<CrmBusinessAmtVO> rows = new ArrayList<CrmBusinessAmtVO>();
    	int total = 0;
    	if(crmbusinessamtList.result != null){
    		rows = BeanConvertor.copyList(crmbusinessamtList.result,CrmBusinessAmtVO.class);
    		total = crmbusinessamtList.getTotalResult();
    	}    	
    	Page<CrmBusinessAmtVO> pageResult = new Page<CrmBusinessAmtVO>();
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
    public List<CrmBusinessAmtPO> listByParam(CrmBusinessAmtParam crmbusinessamtParam){
    	ParamMap paramMap = new ParamMap(crmbusinessamtParam);
    	return commonDao.listByParams(CrmBusinessAmtPO.class,"CrmBusinessAmtPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmBusinessAmtPO po = this.get(CrmBusinessAmtPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmBusinessAmtPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmBusinessAmtPO> listPo = new ArrayList<CrmBusinessAmtPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmBusinessAmtPO po = this.get(CrmBusinessAmtPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmBusinessAmtPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmBusinessAmtParam param){
    	CrmBusinessAmtPO crmbusinessamtPO = BeanConvertor.copy(param,CrmBusinessAmtPO.class);
    	this.save(crmbusinessamtPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmBusinessAmtPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business_amt));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmBusinessAmtPO po){
     	this.update(po);
    }

	@Override
	public CrmBusinessAmtPO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrmBusinessAmtPO findByBusinessId(Long businessId, Integer usertype) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId); 
		paramMap.put("userType", usertype);
		List<CrmBusinessAmtPO> list =  commonDao.listByParams(CrmBusinessAmtPO.class,"CrmBusinessAmtPOMapper.findByBusinessId",paramMap);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		 
	}

	@Override
	public Integer updateCrmBusinessAmt(Long businessId,BigDecimal canWithdrawDeposit,Integer userType) {
		ParamMap paramMap=new ParamMap();
		paramMap.put("userType", userType); 
		paramMap.put("businessId", businessId);
		paramMap.put("canWithdrawDeposit", canWithdrawDeposit);
		paramMap.put("totalIncome", canWithdrawDeposit); 
		paramMap.put("balance", canWithdrawDeposit); 
		return  commonDao.execute("CrmBusinessAmtPOMapper.commonAtomicUpdate", paramMap); 
	}
}

