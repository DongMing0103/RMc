package com.hd.kzscrm.service.serviceimpl.business;

//crmbusinesscanteen
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
import com.hd.kzscrm.dao.entity.business.CrmBusinessCanteenPO;
import com.hd.kzscrm.service.param.business.CrmBusinessCanteenParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessCanteenService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessCanteenVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmBusinessCanteen 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmBusinessCanteenServiceImpl extends BaseServiceImpl implements ICrmBusinessCanteenService {
	
	@Resource
    private SqlSessionTemplate sqlSession;
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessCanteenServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmBusinessCanteenServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmBusinessCanteenVO> convertPOToVO(List<CrmBusinessCanteenPO> crmbusinesscanteenList){
    	List<CrmBusinessCanteenVO> crmbusinesscanteenVoList = new ArrayList<CrmBusinessCanteenVO>();
    	if (CollectionUtils.isEmpty(crmbusinesscanteenList)) {
    		return crmbusinesscanteenVoList;
    	}
    	for (CrmBusinessCanteenPO tag : crmbusinesscanteenList) {
    		CrmBusinessCanteenVO tagVo = BeanConvertor.copy(tag,CrmBusinessCanteenVO.class);
    		crmbusinesscanteenVoList.add(tagVo);
    	}
    	return crmbusinesscanteenVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmBusinessCanteenVO> queryPage(CrmBusinessCanteenParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmBusinessCanteenName",param.getCrmBusinessCanteenName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmBusinessCanteenPO> crmbusinesscanteenList = findPageByParams(CrmBusinessCanteenPO.class,new Page<CrmBusinessCanteenPO>(param.getOffset(),param.getLimit()),"CrmBusinessCanteenPOMapper.queryPage",paramMap);
    	List<CrmBusinessCanteenVO> rows = new ArrayList<CrmBusinessCanteenVO>();
    	int total = 0;
    	if(crmbusinesscanteenList.result != null){
    		rows = convertPOToVO(crmbusinesscanteenList.result);
    		total = crmbusinesscanteenList.getTotalResult();
    	}    	
    	Page<CrmBusinessCanteenVO> pageResult = new Page<CrmBusinessCanteenVO>();
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
    public List<CrmBusinessCanteenPO> listByParam(CrmBusinessCanteenParam crmbusinesscanteenParam){
    	ParamMap paramMap = new ParamMap(crmbusinesscanteenParam);
    	return commonDao.listByParams(CrmBusinessCanteenPO.class,"CrmBusinessCanteenPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmBusinessCanteenPO po = this.get(CrmBusinessCanteenPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmBusinessCanteenPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmBusinessCanteenPO> listPo = new ArrayList<CrmBusinessCanteenPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmBusinessCanteenPO po = this.get(CrmBusinessCanteenPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmBusinessCanteenPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmBusinessCanteenParam param){
    	CrmBusinessCanteenPO crmbusinesscanteenPO = BeanConvertor.copy(param,CrmBusinessCanteenPO.class);
    	this.save(crmbusinesscanteenPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmBusinessCanteenPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business_canteen));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmBusinessCanteenPO po){
     	this.update(po);
    }

	@Override
	public CrmBusinessCanteenPO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 根据参数count(0)
	 */
	@Override
	public Integer commonCount(CrmBusinessCanteenParam businessCanteenParam) {
		ParamMap paramMap = new ParamMap(businessCanteenParam);
		Integer num = sqlSession.selectOne("CrmBusinessCanteenPOMapper.commonCount", paramMap);
		if(BeanUtils.isEmpty(num)){
			return 0;
		}
		return num;
	}
}

