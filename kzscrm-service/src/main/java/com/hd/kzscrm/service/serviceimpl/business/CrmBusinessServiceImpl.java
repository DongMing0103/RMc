package com.hd.kzscrm.service.serviceimpl.business;

import java.math.BigDecimal;
//crmbusiness
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.MapUtils;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmDepartmentPO;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmPositionParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmDepartmentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmPositionService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.kzscrm.service.vo.business.CrmTeamVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmBusiness 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmBusinessServiceImpl extends BaseServiceImpl implements ICrmBusinessService {

	/**
	 * 部门表
	 */
	@Autowired
	ICrmDepartmentService iCrmDepartmentService;
	/**
	 * 岗位表
	 */
	@Autowired
	ICrmPositionService iCrmPositionService;
	/**
	 * 团队表
	 */
	@Autowired
	ICrmTeamService iCrmTeamService;
	/**
	 * 代理商id
	 */
	@Autowired
	ICrmAgentService agentService;
	
	@Resource
	private SqlSessionTemplate sqlSession;
	
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmBusinessServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmBusinessVO> convertPOToVO(List<CrmBusinessPO> crmbusinessList){
    	List<CrmBusinessVO> crmbusinessVoList = new ArrayList<CrmBusinessVO>();
    	if (CollectionUtils.isEmpty(crmbusinessList)) {
    		return crmbusinessVoList;
    	}
    	for (CrmBusinessPO tag : crmbusinessList) {
    		CrmBusinessVO tagVo = BeanConvertor.copy(tag,CrmBusinessVO.class);
    		crmbusinessVoList.add(tagVo);
    	}
    	return crmbusinessVoList;
    }
    
    /**
     * 根据业务员类型，获取业务员ID
     * @author 黄霄仪
     * @date 2017年6月5日 上午9:51:18
     */
    @Override
    public List<Long> getBusinessIdsByType(Integer type){
    	ParamMap paramMap=new ParamMap();
		paramMap.put("type",type);
		List<Long> businessIds = commonDao.listByParams(Long.class, "CrmBusinessPOMapper.getBusinessIdsByType", paramMap);
    	return businessIds;
    }

    /**
     * 根据业务员类型，获取业务员ID
     * @author 黄霄仪
     * @date 2017年6月5日 上午9:51:18
     */
    @Override
    public List<Long> getBusinessIdsByParam(CrmBusinessParam param){
    	ParamMap paramMap=new ParamMap(param);
    	MapUtils.mapNotEmptyValue(paramMap);
		List<Long> businessIds = commonDao.listByParams(Long.class, "CrmBusinessPOMapper.getBusinessIdsByType", paramMap);
    	return businessIds;
    }
    
    /**
     * 查询
     */
    @Override
    public Page<CrmBusinessVO> queryPageList(CrmBusinessParam param) {
    	
    	Page<CrmBusinessVO> crmBusinessVOPage = queryPage(param);
    	List<CrmBusinessVO> crmBusinessVOs = crmBusinessVOPage.result;
    	if(BeanUtils.isNotEmpty(crmBusinessVOs)){
    		CrmBusinessVO crmBusinessVO = crmBusinessVOs.get(0);
    		crmBusinessVO.setOnJobNum(1);
    		crmBusinessVO.setQuitJobNum(1);
    	}
    	return crmBusinessVOPage;
    }
    
    @Override
	public CrmBusinessPO getByUserId(Long userId){
		ParamMap paramMap=new ParamMap();
		paramMap.put("userId",userId);
		List<CrmBusinessPO> listByParams = commonDao.listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.queryList", paramMap);
		if(BeanUtils.isNotEmptyUniqueList(listByParams)){
			return listByParams.get(0);
		}
		return null;
	}
        
    /**
     * 查询
     */
    @Override
    public Page<CrmBusinessVO> queryPage(CrmBusinessParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	Page<CrmBusinessPO> crmbusinessList = findPageByParams(CrmBusinessPO.class,new Page<CrmBusinessPO>(param.getOffset(),param.getLimit()),"CrmBusinessPOMapper.queryPage",paramMap);
    	List<CrmBusinessVO> rows = new ArrayList<CrmBusinessVO>();
    	int total = 0;
    	if(crmbusinessList.result != null){
    		for(int i=0;i<crmbusinessList.result.size();i++){
    			CrmBusinessPO cBusinessPO = crmbusinessList.result.get(i);
    			CrmBusinessVO  crmBusinessVO =BeanConvertor.convertBean(cBusinessPO, CrmBusinessVO.class);
    			CrmDepartmentPO cDo = null;
    			CrmPositionPO cPo = null;
    			
    			//查询团队
    			if(BeanUtils.isNotEmpty(cBusinessPO.getTeamId())){
    				CrmTeamPO tPo = iCrmTeamService.findByTeamId(cBusinessPO.getTeamId());
    				if(BeanUtils.isNotEmpty(tPo)){
    					crmBusinessVO.setTeamName(tPo.getName());//设置团队名称
    				}else{
    					crmBusinessVO.setTeamName("-");
    				}
    			}
    			
    			if(BeanUtils.isNotEmpty(cBusinessPO.getDepartmentId())){
        			cDo = iCrmDepartmentService.getById(cBusinessPO.getDepartmentId());//根据departmentid查询对象
    			}


    			if(BeanUtils.isNotEmpty(cBusinessPO.getPositionId())){
        			cPo = iCrmPositionService.getById(cBusinessPO.getPositionId());//根据positionid查询对象
    			}
    			StringBuffer sb = new StringBuffer();
    			if(BeanUtils.isNotEmpty(cDo)){//查询部门名称
    				sb.append(cDo.getDName()+"-");
    			}
    			if(BeanUtils.isNotEmpty(cPo)){//查询职务名称
    				sb.append(cPo.getName());
    			}
				crmBusinessVO.setPositionName(sb.toString());//设置岗位名称
    			rows.add(i,crmBusinessVO);
    		}
    		total = crmbusinessList.getTotalResult();
    	}    	
    	Page<CrmBusinessVO> pageResult = new Page<CrmBusinessVO>();
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
    public List<CrmBusinessPO> listByParam(CrmBusinessParam crmbusinessParam){
    	ParamMap paramMap = new ParamMap(crmbusinessParam);
    	return commonDao.listByParams(CrmBusinessPO.class,"CrmBusinessPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmBusinessPO po = this.get(CrmBusinessPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmBusinessPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmBusinessPO> listPo = new ArrayList<CrmBusinessPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmBusinessPO po = this.get(CrmBusinessPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmBusinessPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmBusinessParam param){
    	CrmBusinessPO crmbusinessPO = BeanConvertor.copy(param,CrmBusinessPO.class);
    	this.save(crmbusinessPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmBusinessPO po){
    	Long id = po.getId();
    	if(BeanUtils.isEmpty(id)){
    		po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business));
    	}
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmBusinessPO po){
     	this.update(po);
    }

	@Override
	public CrmBusinessPO getById(Long id) {
		if(BeanUtils.isEmpty(id)){
			return null;
		}
		return commonDao.get(CrmBusinessPO.class, id);
	}

	/**
	 * 根据业务员id查询对象
	* @Title: findByBusinessId 
	* @author : lcl
	* @time : 2017年5月23日 上午10:46:23
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	@Override
	public CrmBusinessPO findByBusinessId(Long businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId);
		List<CrmBusinessPO> caList = commonDao.listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByBusiness", paramMap);
		if(CollectionUtils.isNotEmpty(caList)){
			return caList.get(0);
		}
		return null;
	}
	
	/**
	 * 根据userId查询对象
	 */
	@Override
	public CrmBusinessPO findByUserId(Long userId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("userId", userId);
		List<CrmBusinessPO> useList = commonDao.listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByUserId", paramMap);
		if (CollectionUtils.isNotEmpty(useList)) {
			return useList.get(0);
		}
		return null;
	}
	
	/**
	 * 根据负责人id查询对象（负责人也是业务员）
	* @Title: findByPrincipalBusinessId 
	* @author : lcl
	* @time : 2017年5月25日 下午2:18:40
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	@Override
	public CrmBusinessPO findByPrincipalBusinessId(Long principalBusinessId) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("",principalBusinessId);
		List<CrmBusinessPO> cList = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByBusiness", paramMap);
		if(CollectionUtils.isNotEmpty(cList)){
			return cList.get(0);
		}
		return null;
	}
	/**
	 * 查询业务员信息
	 */
	@Override
	public List<CrmBusinessPO> commonQuery(CrmBusinessParam crmBusinessParam) {
		ParamMap paramMap = new  ParamMap(crmBusinessParam);
		return commonDao.listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.commonQuery", paramMap);
	}

	/**
	 * 查询所有业务员
	 */
	@Override
	public List<CrmBusinessPO> findAll(CrmBusinessParam param) {
		ParamMap paramMap = new ParamMap(param);
		List<CrmBusinessPO> crmBusinessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByAll", paramMap);
		if (crmBusinessPOs != null) {
			return crmBusinessPOs;
		}
		return null;
	}
	
	/**
	 * 根据团队id查询
	 */
	@Override
	public List<CrmBusinessPO> findByTeamId (Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("teamId", id);
		paramMap.put("jobStatus", 1);
		List<CrmBusinessPO> crmBusinessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findTeamById", paramMap);
		return crmBusinessPOs;
	}
	/**
	 * 更新团队业务员关系
	 * @author 黄霄仪
	 * @date 2017年8月2日 下午2:47:58
	 */
	@Override
	public void updateTeamBusinessRelation(Long teamId){
		CrmTeamPO crmTeamPO = iCrmTeamService.get(CrmTeamPO.class, teamId);
		String parentIds = crmTeamPO.getParentIds();//父级团队
		Long superiorBusinessManager=null;//业务经理的上级领导
		if(BeanUtils.isNotEmpty(parentIds)){
			String[] parentIdsSplit = parentIds.split(",");
			List<Long> parentIdsTemp=new LinkedList<>();
			for (int i = 0; i < parentIdsSplit.length; i++) {
				if(i!=parentIdsSplit.length-1){
					parentIdsTemp.add(Long.valueOf(parentIdsSplit[i]));
				}
			}
			List<CrmTeamVO> crmTeamVOs = iCrmTeamService.findByIds(parentIdsTemp);
			//从大到小排列
			Collections.sort(crmTeamVOs,new Comparator<CrmTeamVO>(){
				@Override
				public int compare(CrmTeamVO o1, CrmTeamVO o2) {
					if(o1.getLevel()>o2.getLevel()){
						return -1;
					}else if(o1.getLevel()<o2.getLevel()){
						return 1;
					}
					return 0;
				}
			});
			//临近团队等级的业务经理就是下级业务经理的领导
			for (CrmTeamVO crmTeamVO : crmTeamVOs) {
				List<CrmBusinessPO> crmBusinessManagers= this.findByTeamIdAndUserType(crmTeamVO.getId(),2);//业务经理
				if(BeanUtils.isNotEmpty(crmBusinessManagers)){
					superiorBusinessManager=crmBusinessManagers.get(0).getId();
				}
			}
		}
		List<CrmBusinessPO> crmBusinessManagers= this.findByTeamIdAndUserType(teamId,2);//业务经理
		if(BeanUtils.isNotEmpty(crmBusinessManagers)){
			Assert.isTrue(crmBusinessManagers.size()==1, "只能有一个业务经理");
		}
		if(BeanUtils.isNotEmpty(crmBusinessManagers)){
			//如果有上一级的业务经理，他的领导就是他的上一级，否则就是自己
			CrmBusinessPO businessManager=crmBusinessManagers.get(0);
			if(superiorBusinessManager==null){
				businessManager.setParentId(businessManager.getId());
			}else{
				businessManager.setParentId(superiorBusinessManager);
			}
			this.updateEntity(businessManager);
		}
		
		//更新业务员的parentId
		List<CrmBusinessPO> crmBusinesses= this.findByTeamIdAndUserType(teamId,1);//业务员
		//如果没有业务经理，就设置最近一级的团队的经理为他的领导
		for (CrmBusinessPO crmBusinessPO : crmBusinesses) {
			if (BeanUtils.isEmpty(crmBusinessManagers)) {
				if(superiorBusinessManager==null){
					superiorBusinessManager=crmBusinessPO.getId();
				}
				crmBusinessPO.setParentId(superiorBusinessManager);
			} else {
				crmBusinessPO.setParentId(crmBusinessManagers.get(0).getId());
			}
			this.updateEntity(crmBusinessPO);
		}
	}
	//修改业务员 级联修改代理的teamId
	@Override
	public void updateBusinessAgentRelationTeamId(CrmBusinessPO po, Long teamId){
		CrmAgentParam cAgentParam = new CrmAgentParam();
		//cAgentParam.setUserId(po.getUserId());
		cAgentParam.setTeamId(teamId);
		cAgentParam.setBusinessId(po.getId());
		List<CrmAgentPO> cAgentPOs = agentService.findByBusienssAndUserId(cAgentParam);
		List<Long> agentIds = new ArrayList<Long>();
		if(CollectionUtils.isNotEmpty(cAgentPOs)){
			for(CrmAgentPO cAgentPO : cAgentPOs){
				agentIds.add(cAgentPO.getId());
			}
			
			//修改代理商的teamId
			ParamMap paramMap = new ParamMap();
			paramMap.put("agentIds", agentIds);
			paramMap.put("teamId", teamId);
			paramMap.put("userId", po.getUserId());
			execute("CrmAgentPOMapper.updateTeamId", paramMap);
		}
		
		
	}
	
	
	/**
	 * 根据父ID查找业务员
	 */
	@Override
	public List<CrmBusinessPO> findByParentId (Long parentId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("parentId", parentId);
		paramMap.put("jobStatus", 1);

		List<CrmBusinessPO> crmBusinessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.queryList", paramMap);
		return crmBusinessPOs;
	}
	/**
	 * 根据团队id查询
	 */
	@Override
	public List<CrmBusinessPO> findByTeamIdAndUserType (Long id,Integer userType) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("teamId", id);
		paramMap.put("jobStatus", 1);
		paramMap.put("userType", userType);

		List<CrmBusinessPO> crmBusinessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.queryList", paramMap);
		return crmBusinessPOs;
	}
	@Override
	public List<CrmBusinessPO> findByTeamIdsAndUserType (List<Long> teamIds,Integer userType) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("teamIds", teamIds);
		paramMap.put("jobStatus", 1);
		paramMap.put("userType", userType);

		List<CrmBusinessPO> crmBusinessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.queryList", paramMap);
		return crmBusinessPOs;
	}
	@Override
	public List<CrmBusinessPO> findByBusinessName(String businessName) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessName", businessName);
		List<CrmBusinessPO> businessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByBusinessName", paramMap);
		if(businessPOs!=null){
			return businessPOs;
		}
		return null;
		
	}

	@Override
	public List<CrmBusinessPO> findByTeamId(CrmBusinessParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取团队人数
	 */
	@Override
	public BigDecimal getTeamNumber(CrmBusinessParam crmBusinessParam) {
		ParamMap paramMap = new ParamMap(crmBusinessParam);
		BigDecimal total = sqlSession.selectOne("CrmBusinessPOMapper.getTeamNumber", paramMap);
		if (BeanUtils.isEmpty(total)) {
			return BigDecimal.ZERO;
		}
		return total;
	}
	
	/**
	 * 根据参数查询BusinessId
	 */
	@Override
	public List<Long> queryBusinessIdByParam(CrmBusinessParam crmBusinessParam) {
		ParamMap paramMap = new ParamMap(crmBusinessParam);
		return commonDao.listByParams(Long.class, "CrmBusinessPOMapper.queryBusinessIdByParam", paramMap);
	}

	@Override
	public List<CrmBusinessPO> findLikeByBusinessName(String condition) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("condition", condition);
		List<CrmBusinessPO> crmBusinessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findLikeByBusinessName", paramMap);
		if (CollectionUtils.isNotEmpty(crmBusinessPOs)) {
			return crmBusinessPOs;
		}
		return null;
	}
	/**
	 * 根据主键查询对象 
	* @Title: findByCanteenId 
	* @author : lcl
	* @time : 2017年6月29日 上午11:05:52
	* @return CrmCanteenBaseInfoPO    返回类型 
	* @throws
	 */
	@Override
	public CrmCanteenBaseInfoPO findByCanteenId(Long canteenId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", canteenId);
		List<CrmCanteenBaseInfoPO> cInfoPOs = listByParams(CrmCanteenBaseInfoPO.class, "CrmBusinessPOMapper.findByCanteenId", paramMap);
		if(CollectionUtils.isNotEmpty(cInfoPOs)){
			return cInfoPOs.get(0);
		}
		return null;
	}

	/**
	 * 根据手机号查询业务员是否存在
	* @Title: findByMobilPhone 
	* @author : lcl
	* @time : 2017年7月24日 下午8:22:29
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	@Override
	public CrmBusinessPO findByMobilPhone(String mobilephone) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("mobilephone", mobilephone);
		List<CrmBusinessPO> businessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByMobilPhone", paramMap);
		if(CollectionUtils.isNotEmpty(businessPOs)){
			return businessPOs.get(0);
		}
	
		return null;
	}
	@Override
	public List<CrmBusinessPO> findAllByPhone(String mobilephone) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("mobilephone", mobilephone);
		List<CrmBusinessPO> businessPOs = listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByMobilPhone", paramMap);
		if(CollectionUtils.isNotEmpty(businessPOs)){
			return businessPOs;
		}
		return null;
	}

	/**
	 * 业务员列表初始化
	 */
	@Override
	public void salesmanListInit(CrmAccountPO accountPO,
			ModelAndView modelAndView) {
		Integer userType = accountPO.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		modelAndView.addObject("userType", userType);
		
		if(1 == userType || 0==userType){//1 管理员
			//查询平台所有的团队
			CrmTeamParam crmTeamParam = new CrmTeamParam();
			crmTeamParam.setType(1);//类型: 1.平台，2.代理商
			crmTeamParam.setDelFlag(1);
			List<CrmTeamPO> crmTeamPOs = iCrmTeamService.commonQuery(crmTeamParam);
			modelAndView.addObject("teamPOs", crmTeamPOs);
			
			
		}else if(4 == userType){//4.业务经理
			Long userId = accountPO.getId();
			//查询
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setUserId(userId);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = this.commonQuery(crmBusinessParam);
			if(CollectionUtils.isNotEmpty(crmBusinessPOs) && crmBusinessPOs.size() == 1){
				CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
				Long teamId = crmBusinessPO.getTeamId();
				CrmTeamParam crmTeamParam = new CrmTeamParam();
				crmTeamParam.setParentId(teamId);
				crmTeamParam.setDelFlag(1);
				List<CrmTeamPO> crmTeamPOs = iCrmTeamService.getChildTeam(crmTeamParam);
				if(CollectionUtils.isNotEmpty(crmTeamPOs) && crmTeamPOs.size() == 1){
					modelAndView.addObject("oneTeamFlag", 1);
				}else{
					modelAndView.addObject("teamPOs", crmTeamPOs);
				}
			}else{
				modelAndView.addObject("oneTeamFlag", 1);
			}
		}
		
	}

	/**
	 * 统计职业状态
	 */
	@Override
	public Map<Integer, Integer> countOccupationStatus(
			CrmBusinessParam crmBusinessParam) {
		ParamMap paramMap = new ParamMap(crmBusinessParam);
		List<Map> OccupationStatusMap = commonDao.listByParams("CrmBusinessPOMapper.countOccupationStatus", paramMap );
		Map<Integer, Integer> occupationMap = new HashMap<Integer, Integer>();
		if(CollectionUtils.isNotEmpty(OccupationStatusMap)){
			Integer totalNum = 0;
			for (Map map : OccupationStatusMap) {
				Integer jobStatus = CommUtil.parseInteger(map.get("jobStatus")) ;
				Integer statusNum =CommUtil.parseInteger(map.get("statusNum"));
				if(null != jobStatus){
					occupationMap.put(jobStatus, statusNum);
					totalNum += statusNum;
				}
			}
			occupationMap.put(2, totalNum);
			
			return occupationMap;
		}
		
		return null;
	}

	/**
	 * 业务员列表详情 
	 */
	@Override
	public Boolean salesmanListDetail(CrmBusinessParam crmBusinessParam,
			CrmAccountPO userPO, PageRespModel pageRespModel) {
		Integer userType = userPO.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		if(!(1 == userType || 4 == userType)){
			pageRespModel.setDesc("权限不匹配");
			pageRespModel.setCode(RespModel.RespCode.NO_PRIVILEGE.getCode());
			return true;
		}
		
		//CrmTeamParam crmTeamParam = null;
		Long teamId = crmBusinessParam.getTeamId();//判断是否传入teamId
		Map<Long,String> teamIdWithTeamName = new HashMap<Long,String>();//团队Id:团队名
		if(1 == userType){//管理员
			if(null == teamId){
				CrmTeamParam crmTeamParam = new CrmTeamParam();
				crmTeamParam.setType(1);//类型: 1.平台，2.代理商
				crmTeamParam.setDelFlag(1);
				List<CrmTeamPO> crmTeamPOs = iCrmTeamService.commonQuery(crmTeamParam);
				for (CrmTeamPO crmTeamPO : crmTeamPOs) {
					teamIdWithTeamName.put(crmTeamPO.getId(), crmTeamPO.getName());
				}
			}
			
			crmBusinessParam.setType(1);//业务员类型，1.平台业务员，2.代理商业务员
		}else{//4.业务经理
		
			if(null == teamId){
				Long userId = userPO.getId();
				CrmBusinessParam businessParam = new CrmBusinessParam();
				businessParam.setUserId(userId);
				businessParam.setDelFlag(1);
				
				List<CrmBusinessPO> crmBusinessPOs = this.commonQuery(businessParam);
				if(CollectionUtils.isEmpty(crmBusinessPOs) || crmBusinessPOs.size() != 1){
					pageRespModel.setDesc("未查到业务员信息或业务员信息不止一条");
					pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
					return true;
				}
				CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
				//查询该业务经理子孙团队及所在团队
				Long tId = crmBusinessPO.getTeamId();
				CrmTeamParam teamParam = new CrmTeamParam();
				teamParam.setParentId(tId);
				teamParam.setDelFlag(1);
				List<CrmTeamPO> crmTeamPOs = iCrmTeamService.getChildTeam(teamParam);
				for (CrmTeamPO crmTeamPO : crmTeamPOs) {
					teamIdWithTeamName.put(crmTeamPO.getId(), crmTeamPO.getName());
				}
			}
			
			crmBusinessParam.setJobStatus(1);//在职状态 0.离职，1.在职
		}
		
		if(null != teamId){
			CrmTeamPO crmTeamPO = new CrmTeamPO();
			crmTeamPO.setId(teamId);
			crmTeamPO.setDelFlag(1);
			crmTeamPO = (CrmTeamPO) iCrmTeamService.getByExample(crmTeamPO);
			if(BeanUtils.isNotEmpty(crmTeamPO)){
				teamIdWithTeamName.put(teamId, crmTeamPO.getName());
			}
		}
		
		//查询业务员信息
		crmBusinessParam.setDelFlag(1);
		if(null != teamIdWithTeamName && teamIdWithTeamName.size() >= 1 && 4 == userType){
			crmBusinessParam.setTeamId(null);
			crmBusinessParam.setTeamIds(new LinkedList<>(teamIdWithTeamName.keySet()));
		}
		Page<CrmBusinessPO> crmBusinessPOPage = this.queryPageByParam(crmBusinessParam);
		
		
		List<CrmBusinessPO> crmBusinessPOs =  crmBusinessPOPage.result;
		List<CrmBusinessVO> CrmBusinessVOs = new  ArrayList<CrmBusinessVO>();
		
		Map<Long,String> positionIdWithName = new HashMap<Long, String>();
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			CrmBusinessVO crmBusinessVO = BeanConvertor.convertBean(crmBusinessPO, CrmBusinessVO.class);
			Long positionId = crmBusinessPO.getPositionId();
			positionIdWithName.put(positionId,null);
			CrmBusinessVOs.add(crmBusinessVO);
		}
		
		//查询岗位职务名
		CrmPositionParam crmPositionParam = new CrmPositionParam();
		crmPositionParam.setIds(new LinkedList<Long>(positionIdWithName.keySet()));
		crmPositionParam.setDelFlag(1);
		List<CrmPositionPO> crmPositionPOs = iCrmPositionService.commonQuery(crmPositionParam);
		positionIdWithName.clear();
		for (CrmPositionPO crmPositionPO : crmPositionPOs) {
			positionIdWithName.put(crmPositionPO.getId(), crmPositionPO.getName());
		}
		
		//设置岗位职务及所属团队
		for (CrmBusinessVO crmBusinessVO : CrmBusinessVOs) {
			
			Long tId = crmBusinessVO.getTeamId();
			if(tId != null &&teamIdWithTeamName.containsKey(tId)){
				crmBusinessVO.setTeamName(teamIdWithTeamName.get(tId));
			}else{
				crmBusinessVO.setTeamName("-");
			}
			
			
			Long positionId = crmBusinessVO.getPositionId();
			if(positionId != null && positionIdWithName.containsKey(positionId)){
				crmBusinessVO.setPositionName(positionIdWithName.get(positionId));
			}else{
				crmBusinessVO.setPositionName("-");
			}
		}
		
		pageRespModel.setRows(CrmBusinessVOs);
		pageRespModel.setTotal(crmBusinessPOPage.getTotalResult());
		
		return false;
	}

	/**
	 * 分页查询 
	 */
	@Override
	public Page<CrmBusinessPO> queryPageByParam(
			CrmBusinessParam param) {
		ParamMap paramMap = new ParamMap(param);
		return commonDao.findPageByParams(CrmBusinessPO.class, new Page<CrmBusinessPO>(param.getOffset(),param.getLimit()), "CrmBusinessPOMapper.commonQuery", paramMap );
	}

	/**
	 * 根据业务员id和 userType查询
	* @Title: findByParamm 
	* @author : lcl
	* @time : 2017年8月3日 下午4:40:21
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	@Override
	public CrmBusinessPO findByParamm(CrmAgentParam paramm) {
		ParamMap paramMap = new ParamMap(paramm);
		List<CrmBusinessPO> cBusinessPOs = commonDao.listByParams(CrmBusinessPO.class, "CrmBusinessPOMapper.findByParamm", paramMap);
		if(CollectionUtils.isNotEmpty(cBusinessPOs)){
			return cBusinessPOs.get(0);
		}
		return null;
	}

	
}

