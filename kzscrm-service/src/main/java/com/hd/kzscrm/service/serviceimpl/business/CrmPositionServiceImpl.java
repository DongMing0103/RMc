package com.hd.kzscrm.service.serviceimpl.business;

//crmposition
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
import com.hd.kzscrm.service.param.business.CrmPositionParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmPositionService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmPositionVO;
import com.hd.wolverine.cache.Cache;
import com.hd.wolverine.cache.WolverineJedisCluster;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmPosition 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmPositionServiceImpl extends BaseServiceImpl implements ICrmPositionService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmPositionServiceImpl.class);
	 @Resource
     private ICrmTeamService iCrmTeamService;
	 @Resource
	 private ICrmBusinessService iCrmBusinessService;
	 @Resource
	 private WolverineJedisCluster wolverineJedisCluster;
     /**
     *  默认构造函数
     */
     
	 public CrmPositionServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmPositionVO> convertPOToVO(List<CrmPositionPO> crmpositionList){
    	List<CrmPositionVO> crmpositionVoList = new ArrayList<CrmPositionVO>();
    	if (CollectionUtils.isEmpty(crmpositionList)) {
    		return crmpositionVoList;
    	}
    	for (CrmPositionPO tag : crmpositionList) {
    		CrmPositionVO tagVo = BeanConvertor.copy(tag,CrmPositionVO.class);
    		crmpositionVoList.add(tagVo);
    	}
    	return crmpositionVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmPositionVO> queryPage(CrmPositionParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmPositionName",param.getCrmPositionName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmPositionPO> crmpositionList = findPageByParams(CrmPositionPO.class,new Page<CrmPositionPO>(param.getOffset(),param.getLimit()),"CrmPositionPOMapper.queryPage",paramMap);
    	List<CrmPositionVO> rows = new ArrayList<CrmPositionVO>();
    	int total = 0;
    	if(crmpositionList.result != null){
    		rows = convertPOToVO(crmpositionList.result);
    		total = crmpositionList.getTotalResult();
    	}    	
    	Page<CrmPositionVO> pageResult = new Page<CrmPositionVO>();
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
    public List<CrmPositionPO> listByParam(CrmPositionParam crmpositionParam){
    	ParamMap paramMap = new ParamMap(crmpositionParam);
    	return commonDao.listByParams(CrmPositionPO.class,"CrmPositionPOMapper.queryList",paramMap);
    }
    
    @Override
    public Integer addOrUpdatePosition(CrmPositionParam crmPositionParam){
    	Long id = crmPositionParam.getId();
		//如果ID是空，就添加，否则就是更新
		if(BeanUtils.isEmpty(id)){
			this.add(crmPositionParam);
		}else{
			this.update(crmPositionParam);
		}
    	return 0;
    }
    
    @Override
    public void update(CrmPositionParam crmPositionParam){
    	crmPositionParam.setUpdateTime(new Date());
    	CrmPositionPO crmpositionPO = BeanConvertor.copy(crmPositionParam,CrmPositionPO.class);
    	commonDao.update(crmpositionPO);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmPositionPO po = this.get(CrmPositionPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	if(BeanUtils.isEmpty(id)){
    		return;
    	}
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmPositionPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmPositionPO> listPo = new ArrayList<CrmPositionPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmPositionPO po = this.get(CrmPositionPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmPositionPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmPositionParam param){
    	Date currentTime = new Date();
		param.setCreateTime(currentTime);
    	param.setUpdateTime(currentTime);
    	param.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
    	if(BeanUtils.isEmpty(param.getId())){
    		param.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_position));
    	}
    	CrmPositionPO crmpositionPO = BeanConvertor.copy(param,CrmPositionPO.class);
    	try {
    		this.save(crmpositionPO);
		} catch (DuplicateKeyException e) {//主键冲突
			List<Long> maxIds= this.commonDao.listByParams(Long.class, "CrmPositionPOMapper.maxId",null);
			Long maxId=0l;
			if(BeanUtils.isNotEmpty(maxIds)){
				maxId=maxIds.get(0);
			}
			String idKey = Cache.getCacheKey("KZS", DatabaseTableNameEnum.crm_position.name());
			wolverineJedisCluster.set(idKey, maxId.toString());
			param.setId(null);
			this.add(param);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmPositionPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_position));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmPositionPO po){
     	this.update(po);
    }

	@Override
	public CrmPositionPO getById(Long id) {
		ParamMap paramMap =  new ParamMap();
		paramMap.put("id", id);
		List<CrmPositionPO> crmPositionPOs = listByParams(CrmPositionPO.class, "CrmPositionPOMapper.getById", paramMap);
		if(CollectionUtils.isNotEmpty(crmPositionPOs)){
			return crmPositionPOs.get(0);
		}
		return null;
	}
	/**
	 * 根据类型和代理商ID获取岗位，如果代理商ID为空，就是平台的
	 */
	@Override
	public List<CrmPositionPO> findByTypeAndAgentId(Integer type,Long agentId){
		ParamMap paramMap =  new ParamMap();
		paramMap.put("type", type);
		paramMap.put("agentId", agentId);
		List<CrmPositionPO> crmPositionPOs = listByParams(CrmPositionPO.class, "CrmPositionPOMapper.queryList", paramMap);
		return crmPositionPOs;
	}
    /**
     * 查询全部
    * @Title: findAll 
    * @author : lcl
    * @time : 2017年5月26日 上午11:53:16
    * @return List<CrmPositionPO>    返回类型 
    * @throws
     */
	@Override
	public List<CrmPositionPO> findAll() {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("type", 1);
		List<CrmPositionPO> cList = listByParams(CrmPositionPO.class, "CrmPositionPOMapper.findAll", paramMap);
		if(CollectionUtils.isNotEmpty(cList)){
			return cList;
		}
		return null;
	}
	/**
	 * 根据团队ID，找到业务员的岗位
	 */
	@Override
	public List<CrmPositionPO> getPositionByTeamId(Long teamId){
		Assert.notNull(teamId, "团队ID不能为空");
		List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.findByTeamId(teamId);
		List<Long> positionIds=new LinkedList<>();
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			positionIds.add(crmBusinessPO.getPositionId());
		}
		CrmPositionParam crmpositionParam=new CrmPositionParam();
		if(BeanUtils.isEmpty(positionIds)){
			return ListUtils.EMPTY_LIST;
		}
		crmpositionParam.setIds(positionIds);
		return this.listByParam(crmpositionParam);
	}

	/**
	 * 查询岗位名称
	 */
	@Override
	public List<CrmPositionPO> getPositionByIds() {
		ParamMap paramMap = new ParamMap();
		paramMap.put("del_flag", 1);
		List<CrmPositionPO> crmPositionPOs =listByParams(CrmPositionPO.class, "CrmPositionPOMapper.getPositionName", paramMap);
		return crmPositionPOs;
	}

	/**
	 * 根据agentId 获取岗位名称
	 */
	@Override
	public CrmPositionPO findByAgentId(Long agentId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentId", agentId);
		List<CrmPositionPO> clist = commonDao.listByParams(CrmPositionPO.class, "CrmPositionPOMapper.findByAgentId", paramMap);
		if (BeanUtils.isNotEmpty(clist)) {
			return clist.get(0);
		}
		return null;
	}

	/**
	 * 通用查询
	 */
	@Override
	public List<CrmPositionPO> commonQuery(CrmPositionParam crmPositionParam) {
		ParamMap paramMap = new ParamMap(crmPositionParam);
		return commonDao.listByParams(CrmPositionPO.class, "CrmPositionPOMapper.commonQuery", paramMap );
	}
}

