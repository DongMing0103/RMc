package com.hd.kzscrm.service.serviceimpl.business;

//crmbusinessorder
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
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
import com.hd.kzscrm.dao.entity.business.CrmBusinessOrderPO;
import com.hd.kzscrm.service.param.business.CrmBusinessOrderParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessOrderVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmBusinessOrder 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmBusinessOrderServiceImpl extends BaseServiceImpl implements ICrmBusinessOrderService {
	@Resource
    private SqlSessionTemplate sqlSession;
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessOrderServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmBusinessOrderServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmBusinessOrderVO> convertPOToVO(List<CrmBusinessOrderPO> crmbusinessorderList){
    	List<CrmBusinessOrderVO> crmbusinessorderVoList = new ArrayList<CrmBusinessOrderVO>();
    	if (CollectionUtils.isEmpty(crmbusinessorderList)) {
    		return crmbusinessorderVoList;
    	}
    	for (CrmBusinessOrderPO tag : crmbusinessorderList) {
    		CrmBusinessOrderVO tagVo = BeanConvertor.copy(tag,CrmBusinessOrderVO.class);
    		crmbusinessorderVoList.add(tagVo);
    	}
    	return crmbusinessorderVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmBusinessOrderVO> queryPage(CrmBusinessOrderParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmBusinessOrderName",param.getCrmBusinessOrderName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmBusinessOrderPO> crmbusinessorderList = findPageByParams(CrmBusinessOrderPO.class,new Page<CrmBusinessOrderPO>(param.getOffset(),param.getLimit()),"CrmBusinessOrderPOMapper.queryPage",paramMap);
    	List<CrmBusinessOrderVO> rows = new ArrayList<CrmBusinessOrderVO>();
    	int total = 0;
    	if(crmbusinessorderList.result != null){
    		rows = convertPOToVO(crmbusinessorderList.result);
    		total = crmbusinessorderList.getTotalResult();
    	}    	
    	Page<CrmBusinessOrderVO> pageResult = new Page<CrmBusinessOrderVO>();
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
    public List<CrmBusinessOrderPO> listByParam(CrmBusinessOrderParam crmbusinessorderParam){
    	ParamMap paramMap = new ParamMap(crmbusinessorderParam);
    	return commonDao.listByParams(CrmBusinessOrderPO.class,"CrmBusinessOrderPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmBusinessOrderPO po = this.get(CrmBusinessOrderPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmBusinessOrderPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmBusinessOrderPO> listPo = new ArrayList<CrmBusinessOrderPO>(idl.length);
    	
    	for (int i=0; i<idl.length; i++) {
    		CrmBusinessOrderPO po = this.get(CrmBusinessOrderPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmBusinessOrderPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmBusinessOrderParam param){
    	CrmBusinessOrderPO crmbusinessorderPO = BeanConvertor.copy(param,CrmBusinessOrderPO.class);
    	this.save(crmbusinessorderPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmBusinessOrderPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business_order));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmBusinessOrderPO po){
     	this.update(po);
    }

	@Override
	public CrmBusinessOrderPO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 根据条件统计订单实际付款金额 
	 */
	@Override
	public BigDecimal sumOrderRealMoney(CrmBusinessOrderParam businessOrderParam) {
		ParamMap paramMap = new ParamMap(businessOrderParam);
		BigDecimal total = sqlSession.selectOne("CrmBusinessOrderPOMapper.sumOrderRealMoney", paramMap);
		if(BeanUtils.isEmpty(total)){
			return BigDecimal.ZERO;
		}
		return total;
	}
	
	/**
	 * 获取订单数量
	 */
	@Override
	public BigDecimal countOrderId(Long businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId);
		BigDecimal total = sqlSession.selectOne("CrmBusinessOrderPOMapper.countOrderId", paramMap);
		if (BeanUtils.isEmpty(total)) {
			return BigDecimal.ZERO;
		}
		return total;
	}

	/**
	 * 根据业务员ID查询对象
	 * @param businessId
	 * @return
	 */
	@Override
	public CrmBusinessOrderPO findByBusinessId(Long businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId);
		List<CrmBusinessOrderPO> cList = listByParams(CrmBusinessOrderPO.class, "CrmBusinessOrderPOMapper.findByBusinessId", paramMap);
		if (CollectionUtils.isNotEmpty(cList)) {
			return cList.get(0);
		}
		return null;
	}

	/**
	 * 根据订单编号模糊查询对象
	 * @param condition
	 * @return
	 */
	@Override
	public List<CrmBusinessOrderPO> findLikeByOrderId(String condition) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("condition", condition);
		List<CrmBusinessOrderPO> crmBusinessOrderPOs = listByParams(CrmBusinessOrderPO.class, "CrmBusinessOrderPOMapper.findLikeByOrderId", paramMap);
		if(CollectionUtils.isNotEmpty(crmBusinessOrderPOs)){
			return crmBusinessOrderPOs;
		}
		return null;
	}
}

