/**
 * 
 */
package com.hd.kzscrm.service.serviceimpl.enterprise;

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
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterpriseCanteenPO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.service.param.enterprise.CrmEnterpriseCanteenParam;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseCanteenService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.enterprise.CrmEnterpriseCanteenVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @author 黄霄仪
 * @date 2017年6月22日 下午4:36:07
 * 
 */
@Service
@Transactional
public class CrmEnterpriseCanteenServiceImpl extends BaseServiceImpl implements ICrmEnterpriseCanteenService{

	protected static final Logger logger = LoggerFactory.getLogger(CrmEnterpriseCanteenServiceImpl.class);
	/**
     * 查询
     */
    @Override
    public Page<CrmEnterpriseCanteenVO> queryPage(CrmEnterpriseCanteenParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	Page<CrmEnterpriseCanteenPO> crmEnterpriseCanteenList = findPageByParams(CrmEnterpriseCanteenPO.class,new Page<CrmEnterpriseCanteenPO>(param.getOffset(),param.getLimit()),"CrmEnterpriseCanteenPOMapper.queryPage",paramMap);
    	List<CrmEnterpriseCanteenVO> rows = new ArrayList<CrmEnterpriseCanteenVO>();
    	int total = 0;
    	if(crmEnterpriseCanteenList.result != null){
    		rows = BeanConvertor.copyList(crmEnterpriseCanteenList.result,CrmEnterpriseCanteenVO.class);
    		total = crmEnterpriseCanteenList.getTotalResult();
    	}    	
    	Page<CrmEnterpriseCanteenVO> pageResult = new Page<CrmEnterpriseCanteenVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
    	
    }
    /**
     * 根据商家ID与企业ID获取数据
     */
    @Override
    public CrmEnterpriseCanteenPO findByCanteenIdAndEnterpriseId(Long canteenId,Long enterpriseId){
    	CrmEnterpriseCanteenParam crmEnterpriseCanteenParam=new CrmEnterpriseCanteenParam();
    	crmEnterpriseCanteenParam.setCanteenId(canteenId);
    	crmEnterpriseCanteenParam.setEnterpriseId(enterpriseId);
    	List<CrmEnterpriseCanteenPO> crmEnterpriseCanteenPOs = this.listByParam(crmEnterpriseCanteenParam);
    	if(BeanUtils.isNotEmpty(crmEnterpriseCanteenPOs)){
    		Assert.isTrue(crmEnterpriseCanteenPOs.size()==1,"企业信息与食堂不能出现多个");
    		return crmEnterpriseCanteenPOs.get(0);
    	}
    	return null;
    }
    
    /**
     * 根据商家ID与企业ID获取数据
     */
    @Override
    public List<CrmEnterpriseCanteenPO> findByCanteenId(Long canteenId){
    	CrmEnterpriseCanteenParam crmEnterpriseCanteenParam=new CrmEnterpriseCanteenParam();
    	crmEnterpriseCanteenParam.setCanteenId(canteenId);
    	List<CrmEnterpriseCanteenPO> crmEnterpriseCanteenPOs = this.listByParam(crmEnterpriseCanteenParam);
    	return crmEnterpriseCanteenPOs;
    }
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<CrmEnterpriseCanteenPO> listByParam(CrmEnterpriseCanteenParam crmEnterpriseCanteenParam){
    	ParamMap paramMap = new ParamMap(crmEnterpriseCanteenParam);
    	return commonDao.listByParams(CrmEnterpriseCanteenPO.class,"CrmEnterpriseCanteenPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmEnterpriseCanteenPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmEnterprisePO> listPo = new ArrayList<CrmEnterprisePO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
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
    public void add(CrmEnterpriseCanteenParam param){
    	Long id = param.getId();
    	if(BeanUtils.isEmpty(id)){
    		param.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_enterprise_canteen));
    	}
    	CrmEnterpriseCanteenPO crmEnterpriseCanteenPO = BeanConvertor.copy(param,CrmEnterpriseCanteenPO.class);
    	this.save(crmEnterpriseCanteenPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmEnterpriseCanteenPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_enterprise_canteen));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmEnterpriseCanteenPO po){
     	this.update(po);
    }

	@Override
	public CrmEnterpriseCanteenPO getById(Long id) {
		Assert.notNull(id,"ID不能为空");
		return commonDao.get(CrmEnterpriseCanteenPO.class, id);
	}
	
	/**
	 * 根据参数查询企业商家映射信息
	 */
	@Override
	public List<CrmEnterpriseCanteenPO> commonQuery(
			CrmEnterpriseCanteenParam crmEnterpriseCanteenParam) {
		ParamMap paramMap = new ParamMap(crmEnterpriseCanteenParam);
    	return commonDao.listByParams(CrmEnterpriseCanteenPO.class,"CrmEnterpriseCanteenPOMapper.commonQuery",paramMap);
	}
}
