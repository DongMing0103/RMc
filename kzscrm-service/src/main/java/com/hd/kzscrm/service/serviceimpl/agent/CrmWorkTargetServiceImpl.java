package com.hd.kzscrm.service.serviceimpl.agent;

//crmworktarget
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.enums.agent.CrmWorkTargetEnum.CrmWorkTargetApplyStatus;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.agent.CrmWorkTargetPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.agent.CrmWorkTargetParam;
import com.hd.kzscrm.service.param.business.CrmBusinessCanteenParam;
import com.hd.kzscrm.service.param.business.CrmBusinessOrderParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmWorkTargetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessCanteenService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.agent.CrmWorkTargetVO;
import com.hd.kzscrm.service.vo.business.CrmTeamVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmWorkTarget 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmWorkTargetServiceImpl extends BaseServiceImpl implements ICrmWorkTargetService {

	// 日志服务对象
	protected static final Logger logger = LoggerFactory.getLogger(CrmWorkTargetServiceImpl.class);
	/**
	 * 团队表
	 */
	@Autowired
	private ICrmTeamService crmTeamService;
	/**
	 * 业务员表
	 */
	@Autowired
	private ICrmBusinessService crmBusinessService;
	/**
	 * 业务员跟踪记录表
	 */
	@Autowired
	private ICrmBusinessOrderService crmBusinessOrderService;
	/**
	 * 业务员发展商家表
	 */
	@Autowired
	private ICrmBusinessCanteenService crmBusinessCanteenService;
	/**
	 * 代理商表
	 */
	@Autowired
	private ICrmAgentService crmAgentService;
	
	@Resource
	private ICrmUserService crmUserService;
	/**
	 * 默认构造函数
	 */

	public CrmWorkTargetServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	/**
	 * PO转换为VO
	 * 
	 * @param param
	 * @return
	 */
	private List<CrmWorkTargetVO> convertPOToVO(List<CrmWorkTargetPO> crmworktargetList) {
		List<CrmWorkTargetVO> crmworktargetVoList = new ArrayList<CrmWorkTargetVO>();
		if (CollectionUtils.isEmpty(crmworktargetList)) {
			return crmworktargetVoList;
		}
		for (CrmWorkTargetPO tag : crmworktargetList) {
			CrmWorkTargetVO tagVo = BeanConvertor.copy(tag, CrmWorkTargetVO.class);
			crmworktargetVoList.add(tagVo);
		}
		return crmworktargetVoList;
	}
	
	@Override
	public Page<CrmWorkTargetVO> findByBusinessIdsAndApplyStatus(List<Long> businessIds,Integer applyStatus) {
		CrmWorkTargetParam crmWorkTargetParam=new CrmWorkTargetParam();
		crmWorkTargetParam.setBusinessIds(businessIds);
		crmWorkTargetParam.setApplyStatus(applyStatus);
		return this.queryPage(crmWorkTargetParam);
	}

	/**
	 * 查询
	 */
	@Override
	public Page<CrmWorkTargetVO> queryPage(CrmWorkTargetParam param) {
		ParamMap paramMap = new ParamMap(param);

		Page<CrmWorkTargetPO> crmworktargetList = findPageByParams(CrmWorkTargetPO.class,
				new Page<CrmWorkTargetPO>(param.getOffset(), param.getLimit()), "CrmWorkTargetPOMapper.queryPage",
				paramMap);
		List<CrmWorkTargetVO> rows = new ArrayList<CrmWorkTargetVO>();
		int total = 0;
		for (int i = 0; i < crmworktargetList.result.size(); i++) {
			CrmWorkTargetPO workTargetPO = crmworktargetList.result.get(i);
			CrmWorkTargetVO crmWorkTargetVO= BeanConvertor.convertBean(workTargetPO, CrmWorkTargetVO.class);
			if(workTargetPO!=null && workTargetPO.getTeamId()!=null){
				CrmTeamPO crmTeamPO= crmTeamService.findByTeamId(workTargetPO.getTeamId());//根据team_id查询对象
				if(crmTeamPO!=null){
					crmWorkTargetVO.setTeamName(crmTeamPO.getName());//团队名称
				}
			}
			if (workTargetPO != null && workTargetPO.getBusinessId() != null) {
				CrmBusinessPO crmBusinessPO = crmBusinessService.findByBusinessId(workTargetPO.getBusinessId());  //根据business_id查询对象
                if (crmBusinessPO != null) {
					crmWorkTargetVO.setBusinessName(crmBusinessPO.getName()); //获取业务员名称
				}				
			}
			if (workTargetPO != null && workTargetPO.getAgentId() != null) {
				//write later
			}
			rows.add(i, crmWorkTargetVO);
		}
		
 		
		total = crmworktargetList.getTotalResult();
		Page<CrmWorkTargetVO> pageResult = new Page<CrmWorkTargetVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}
	@Override
	public Page<CrmWorkTargetVO> workTargetCheckQuery(CrmWorkTargetParam crmWorkTargetParam) {
		Integer userType = crmWorkTargetParam.getUserType();
		Integer type = crmWorkTargetParam.getType();
		Long agentId = crmWorkTargetParam.getAgentId();;
		Long userId = crmWorkTargetParam.getUserId();
		
		//平台或代理商下的团队
		List<CrmTeamVO> platformAndAgentTeams = new LinkedList<>();
		List<Long> platformAndAgentTeamIds=new LinkedList<>();
		List<Long> haveBusinessManagerTeamIds=new LinkedList<>();//有业务经理的团队
		List<Long> noneBusinessManagerTeamIds=new LinkedList<>();//没有业务经理的团队
		//获取平台或代理商下的团队
		if(userType==CrmAccountUserType.AGENT.getCode()||userType==CrmAccountUserType.ADMIN.getCode()){
			platformAndAgentTeams=crmTeamService.findByTypeAndAgentId(type, agentId);//平台和代理商团队
			//按团队级别排序
			Collections.sort(platformAndAgentTeams, new Comparator<CrmTeamVO>(){
				@Override
				public int compare(CrmTeamVO o1, CrmTeamVO o2) {
					if(o1.getLevel()>o2.getLevel()){
						return 1;
					}else if(o1.getLevel()<o2.getLevel()){
						return -1;
					}
					return 0;
				}
				
			});
			List<Long> highestBusinessManager=new LinkedList<>();//离管理员最近的业务经理
			Integer highestBusinessManagerLevel=null;//离管理员最近的业务经理,用该变量找出平级的业务经理
			for (CrmTeamVO platformAndAgentTeam : platformAndAgentTeams) {
				Long id = platformAndAgentTeam.getId();
				platformAndAgentTeamIds.add(id);
				List<CrmBusinessPO> businessManagers = crmBusinessService.findByTeamIdAndUserType(id,2);//业务经理
				if(BeanUtils.isNotEmpty(businessManagers)){
					//如果最高级的团队有业务经理的等级为空，或者最高级的团队的业务经理等于当前遍历的等级，就添加到需要被查询出工作目标审核记录的业务员
					if(highestBusinessManagerLevel==null||highestBusinessManagerLevel==platformAndAgentTeam.getLevel()){
						highestBusinessManagerLevel=platformAndAgentTeam.getLevel();
						highestBusinessManager.add(businessManagers.get(0).getId());
					}
					haveBusinessManagerTeamIds.add(id);
				}else{
					noneBusinessManagerTeamIds.add(id);
				}
			}
//			List<CrmBusinessPO> businessManagers = crmBusinessService.findByTeamIdsAndUserType(noneBusinessManagerTeamIds, 2);//业务经理
			List<CrmBusinessPO> businesses = crmBusinessService.findByTeamIdsAndUserType(noneBusinessManagerTeamIds, 1);//业务员
			List<Long> businessIds=new LinkedList<>();
			for (CrmBusinessPO crmBusiness : businesses) {
				businessIds.add(crmBusiness.getId());
			}
			businessIds.addAll(highestBusinessManager);
			return this.findByBusinessIdsAndApplyStatus(businessIds,1);
		}
		if(userType==CrmAccountUserType.AGENT.getCode()){
			
		}else if(userType==CrmAccountUserType.ADMIN.getCode()){
			//获取平台的所有团队
		}else if(userType==CrmAccountUserType.BUSINESS_MANAGER.getCode()){
			CrmBusinessPO crmBusinessPO = crmBusinessService.findByUserId(userId);
			Long teamId = crmBusinessPO.getTeamId();
			
			//查询业务经理下的业务员,以下级团队的业务经理
			List<CrmBusinessPO> crmBusinesses =crmBusinessService.findByParentId(crmBusinessPO.getId());
			if(BeanUtils.isEmpty(crmBusinesses)){
				return new Page<CrmWorkTargetVO>();
			}
			List<Long> businessIds=new LinkedList<>();
			for (CrmBusinessPO crmBusiness : crmBusinesses) {
				businessIds.add(crmBusiness.getId());
			}
			return this.findByBusinessIdsAndApplyStatus(businessIds,1);
		}else{
			return new Page<CrmWorkTargetVO>();
		}
		return new Page<CrmWorkTargetVO>();
	}
	
	/**
	 * 基础数据查询
	 */
	@Override
	public Page<CrmWorkTargetVO> queryPageBasic(CrmWorkTargetParam param) {
		//获取业务员IDS
		List<Long> businessIds = new ArrayList<Long>();
		if(CommUtil.parseLong(param.getTeamId())>0){
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setType(param.getType());
			crmBusinessParam.setUserType(1);
			crmBusinessParam.setName(param.getBusinessName());
			crmBusinessParam.setTeamId(param.getTeamId());
			businessIds = crmBusinessService.getBusinessIdsByParam(crmBusinessParam);	
		}else{
			if(param.getUserType().equals(CrmAccountEnum.CrmAccountUserType.ADMIN.getCode())){
				//管理员只查询一级经理工作目标
				CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
				crmBusinessParam.setType(param.getType());
				crmBusinessParam.setUserType(2);//用户类型	1.业务员，2.业务员经理,3.代理商总监
				crmBusinessParam.setIsHighest(1);
				businessIds = crmBusinessService.getBusinessIdsByParam(crmBusinessParam);
			}else{
				businessIds = crmBusinessService.getBusinessIdsByType(param.getType());
			}
		}
		param.setTeamId(null);
		param.setBusinessIds(businessIds);
		if(BeanUtils.isEmpty(businessIds)){
			return new  Page<CrmWorkTargetVO>();
		}
		Page<CrmWorkTargetVO> queryPage = queryPage(param);
		if(BeanUtils.isNotEmpty(queryPage)){
			List<CrmWorkTargetVO> crmWorkTargetVOs=queryPage.result;
			if(BeanUtils.isNotEmpty(crmWorkTargetVOs)){
				for (CrmWorkTargetVO crmWorkTargetVO : crmWorkTargetVOs) {
					Long applyUserId = crmWorkTargetVO.getApplyUserId();//申请人ID
					CrmUserPO crmUserPO = crmUserService.getById(applyUserId);
					if(BeanUtils.isNotEmpty(crmUserPO)){
						crmWorkTargetVO.setApplyUserName(crmUserPO.getUserName());
					}
				}
			}
		}
		return queryPage;
	}

	/**
	 * 根据主键查询详情
	 * 
	 * @param param
	 * @return
	 */

	@Override
	public List<CrmWorkTargetPO> listByParam(CrmWorkTargetParam crmworktargetParam) {
		ParamMap paramMap = new ParamMap(crmworktargetParam);
		return commonDao.listByParams(CrmWorkTargetPO.class, "CrmWorkTargetPOMapper.queryList", paramMap);
	}

	@Override
	public Integer update(CrmWorkTargetParam crmWorktargetParam) {
		ParamMap paramMap = new ParamMap(crmWorktargetParam);
		return commonDao.execute("CrmWorkTargetPOMapper.update", paramMap);
	}
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmWorkTargetPO po =
		 * this.get(CrmWorkTargetPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmWorkTargetPOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmWorkTargetPO> listPo = new ArrayList<CrmWorkTargetPO>(idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmWorkTargetPO po = this.get(CrmWorkTargetPO.class, idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmWorkTargetPO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmWorkTargetParam param) {
		Long id = param.getId();
		if(BeanUtils.isEmpty(id)){
			param.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_work_target));
		}
		param.setCreatTime(new Date());//创建时间
		param.setUpdateTime(new Date());//更新时间
		param.setApplyStatus(CrmWorkTargetApplyStatus.APPLYING.getCode());//申请中
		param.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
		CrmWorkTargetPO crmworktargetPO = BeanConvertor.copy(param, CrmWorkTargetPO.class);
		this.save(crmworktargetPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmWorkTargetPO po) {
		po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_work_target));
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmWorkTargetPO po) {
		this.update(po);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hd.kzscrm.service.serviceInter.agent.ICrmWorkTargetService#getById(
	 * java.lang.Long)
	 */
	@Override
	public CrmWorkTargetVO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @Title: queryPageByParam 
	 * @Description: 分页查询 
	 * @param @param crmworktargetParam
	 * @param @return  
	 * @return Page<CrmWorkTargetPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年5月28日 下午3:43:38
	 */
	@Override
	public Page<CrmWorkTargetPO> queryPageByParam(
			CrmWorkTargetParam crmworktargetParam) {
		ParamMap paramMap = new ParamMap(crmworktargetParam);
		/*List<String> keys=new LinkedList<>();
		Map<String,Object> map=new HashMap<>();
		map.put("keys",keys);
		paramMap.put("orders",map);*/
		return findPageByParams(CrmWorkTargetPO.class, new Page<CrmWorkTargetPO>(crmworktargetParam.getOffset(),
				crmworktargetParam.getLimit()), "CrmWorkTargetPOMapper.queryPageByParam", paramMap);
	}
	/**
	 * 获取团队目标信息
	 */
	@Override
	public Boolean getTeamTargetDetails(CrmWorkTargetParam crmWorkTargetParam,
			PageRespModel pageRespModel) {
		//查询登录者信息
		Long userId = crmWorkTargetParam.getUserId();//用户Id
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(userId);
		crmUserPO = (CrmUserPO) crmUserService.getByExample(crmUserPO);//获取登录者信息
		Integer userType = crmUserPO.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		if(3 == userType){//业务员无权限
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("操作有误");
			pageRespModel.setTotal(0);
			return true;
		}
		//判断传参
		Long cTeamId = crmWorkTargetParam.getTeamId();
		if(BeanUtils.isNotEmpty(cTeamId)){
			crmWorkTargetParam.setTeamIdsStr(null);
		}
		if(BeanUtils.isNotEmpty(cTeamId) || BeanUtils.isNotEmpty(crmWorkTargetParam.getTeamIdsStr())){
		//查询工作目标信息
		crmWorkTargetParam.setTargetType(1);//团队
		crmWorkTargetParam.setDelFlag(1);//存在
		crmWorkTargetParam.setApplyStatus(2);//申请状态(1.申请中 2通过)
		Page<CrmWorkTargetPO> crmWorkTargetPOPage = this.queryPageByParam(crmWorkTargetParam);
		List<CrmWorkTargetVO> crmWorkTargetVOs = BeanConvertor.convertBean(crmWorkTargetPOPage.result, ArrayList.class);
		//Map<CrmWorkTargetPO,Long> workTargetPOWithTeamId = new HashMap<CrmWorkTargetPO,Long>();//工作目标 : 团队Id
		Map<Long,Integer> teamIdMap = new HashMap<Long,Integer>();//团队Id:1
		for (int i = 0; i < crmWorkTargetVOs.size(); i++) {
			CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetVOs.get(i),CrmWorkTargetVO.class);
			Long teamId = crmWorkTargetVO.getTeamId();
			if(BeanUtils.isNotEmpty(teamId)){
				//workTargetPOWithTeamId.put(crmWorkTargetPO,teamId);
				teamIdMap.put(teamId, 1);
			}
			crmWorkTargetVOs.set(i, crmWorkTargetVO);
		}
		//Collection<Long> values = workTargetPOWithTeamId.values();
		Set<Long> teamIds = teamIdMap.keySet();
		
		//查询团队表(获取团队名)
		CrmTeamParam crmTeamParam = new CrmTeamParam();
		crmTeamParam.setDelFlag(1);//存在
		crmTeamParam.setIds(new LinkedList<Long>(teamIds));
		List<CrmTeamPO> crmTeamPOs = crmTeamService.commonQuery(crmTeamParam);
		
		Map<Long,String> crmTeamIdWithCrmTeamName = new HashMap<Long,String>();//团队Id:团队名
		for (CrmTeamPO crmTeamPO : crmTeamPOs) {
			crmTeamIdWithCrmTeamName.put(crmTeamPO.getId(), crmTeamPO.getName());
		}
		
		//指明团队子父关系Map<Long,List<Long>>teamId:子孙teamId集合(包含自己)
		Map<Long,LinkedList<Long>> teamIdWithChildsIds = new HashMap<Long, LinkedList<Long>>();
		for (CrmTeamPO crmTeamPO : crmTeamPOs) {
			Long id = crmTeamPO.getId();
			CrmTeamParam teamParam = new CrmTeamParam();
			teamParam.setParentId(id);
			teamParam.setDelFlag(1);
			List<CrmTeamPO> teamPOs = crmTeamService.getChildTeam(teamParam);//查询子孙团队
			LinkedList<Long> teamIdList = new LinkedList<Long>();
			for (CrmTeamPO teamPO : teamPOs) {
				teamIdList.add(teamPO.getId());
			}
			if(!teamIdWithChildsIds.containsKey(id)){
				teamIdWithChildsIds.put(id, teamIdList);
			}
		}
		
		/*for (Long teamId : teamIds) {
			for (CrmTeamPO crmTeamPO : crmTeamPOs) {
				Long id = crmTeamPO.getId();
				String parentIds = crmTeamPO.getParentIds();
				List<Long> parentIdList = StringUtils.idsStrToList(parentIds);
				
				if(teamIdWithChildsIds.containsKey(teamId)){
					
				}else{
					if(parentIdList.contains(teamId)){
						LinkedList<Long> ids = new LinkedList<Long>();
						ids.add(id);
					}
				}
			}
		}*/
		
		//查询业务员信息(业务员归属于哪个团队,获取团队的业务员Id集合)
		Map<Long,LinkedList<Long>> teamIdWithBusinessIds = new HashMap<Long,LinkedList<Long>>();//团队Id:业务员ids
		CrmBusinessParam crmBusinessParam = null;
		DecimalFormat df = new DecimalFormat("######0.00");
		LinkedList<Long> businessIdList = null;
		for(Long tId : teamIdWithChildsIds.keySet()){
			//crmBusinessParam.setDelFlag(1);//存在
			LinkedList<Long> tIdList = teamIdWithChildsIds.get(tId);
			crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setTeamIds(tIdList);
			List<CrmBusinessPO> CrmBusinessPOs = crmBusinessService.commonQuery(crmBusinessParam);
			businessIdList = new LinkedList<Long>();
			for (CrmBusinessPO crmBusinessPO : CrmBusinessPOs) {
				Long id = crmBusinessPO.getId();
				businessIdList.add(id);
				/*Long teamId = crmBusinessPO.getTeamId();
				if(BeanUtils.isNotEmpty(teamId)){
					if(teamIdWithBusinesssIds.containsKey(teamId)){
						LinkedList<Long> businesssIds = teamIdWithBusinesssIds.get(teamId);
						businesssIds.add(id);
						teamIdWithBusinesssIds.put(teamId, businesssIds);
					}else{
						LinkedList<Long> businesssIds = new LinkedList<Long>();
						businesssIds.add(id);
						teamIdWithBusinesssIds.put(teamId,businesssIds);
					}
				}*/
			}
			if(!teamIdWithBusinessIds.containsKey(tId)){
				teamIdWithBusinessIds.put(tId, businessIdList);
			}
		}
		
			Double preComplete = null;
		for (CrmWorkTargetVO crmWorkTargetVO : crmWorkTargetVOs) {
			Date applyMonth = crmWorkTargetVO.getApplyMonth();
			Long teamId = crmWorkTargetVO.getTeamId();
			if(teamIdWithBusinessIds.containsKey(teamId)){
				preComplete = 0.00;
				LinkedList<Long> businessIds = teamIdWithBusinessIds.get(teamId);
				
				
			if(CollectionUtils.isNotEmpty(businessIds)){
					
				//查询业务员发展代理商数量(完成量)
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setApplyMonth(applyMonth);
				crmAgentParam.setDelFlag(1);
				crmAgentParam.setBusinessIds(businessIds);//业务员Ids
				Integer CompleteAgentNum =crmAgentService.commonCount(crmAgentParam);//查询业务员发展代理商数量
				crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
				Long agentNum2 = crmWorkTargetVO.getAgentNum();
				Integer agentNum = CommUtil.parseInteger(agentNum2);
				
				if(null == agentNum2 || CompleteAgentNum >= agentNum){
					preComplete +=33.33;
				}
				
				//查询业务员发展商家数量完成量
				CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
				businessCanteenParam.setApplyMonth(applyMonth);
				businessCanteenParam.setDelFlag(1);
				businessCanteenParam.setBusinessIds(businessIds);
				Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
				crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
				Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
				Integer canteenNum = CommUtil.parseInteger(canteenNum2);
				if(null == canteenNum2 || completeCanteenNum >= canteenNum){
					preComplete +=33.33;
				}
				
				//查询业务员跟踪记录表关于订单金额完成量
				CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
				businessOrderParam.setDelFlag(1);
				businessOrderParam.setApplyMonth(applyMonth);
				businessOrderParam.setBusinessIds(businessIds);
				BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
				crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
				BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
				
				if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
					preComplete +=33.33;
				}
				
				//设置完成百分比
				if(99.99 == preComplete){
					crmWorkTargetVO.setPercentageCompletion("100.00");
				}else{
					crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
				}
				
			 }else{
				 crmWorkTargetVO.setCompleteAgentNum(0L);//
				 crmWorkTargetVO.setCompleteCanteenNum(0L);//查询业务员发展商家数量
				 crmWorkTargetVO.setCompleteOrderMoney(BigDecimal.ZERO);//订单金额
				 crmWorkTargetVO.setPercentageCompletion("0.00");
			 }
			
			}
			//设置团队名
			if(crmTeamIdWithCrmTeamName.containsKey(teamId)){
				crmWorkTargetVO.setTeamName(crmTeamIdWithCrmTeamName.get(teamId));//业务员发展代理商数量
				
			}
		}
		
		
		pageRespModel.setTotal(crmWorkTargetPOPage.getTotalResult());
		pageRespModel.setRows(crmWorkTargetVOs);
		
		}
		return false;
	}
	
	/**
	 * 获取代理商目标信息
	 */
	@Override
	public Boolean getAgentTargetDetails(CrmWorkTargetParam crmWorkTargetParam,
			PageRespModel pageRespModel) {
		//获取登录者信息判断
		Long userId = crmWorkTargetParam.getUserId();//登录者Id
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(userId);
		crmUserPO = (CrmUserPO) crmUserService.getByExample(crmUserPO);//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmUserPO.getUserType();
		if(3 == userType || 4 == userType){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("操作有误");
			pageRespModel.setTotal(0);
			return true;
		}
		
		
		//查询直属代理商
		CrmAgentParam crmAgentParam = new CrmAgentParam();
		//若为管理员登录查询代理商目标
		if(1 == userType){
			//获取平台直属代理商
			crmAgentParam.setLevel(1);
		}
		//若为代理商登录查询代理商目标
		if(2 == userType){
			//获取代理商下直属代理商
			CrmAgentParam agentParam = new CrmAgentParam();
			agentParam.setUserId(userId);
			agentParam.setDelFlag(1);
			List<CrmAgentPO> agentPOs = crmAgentService.commonQuery(agentParam);
			if(1 == agentPOs.size()){//仅查出一条团队记录
				CrmAgentPO crmAgentPO = agentPOs.get(0);
				crmAgentParam.setParentId(crmAgentPO.getId());
			}else{
				pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
				pageRespModel.setRows(null);
				pageRespModel.setDesc("出错了");
				pageRespModel.setTotal(0);
				return true;
			}
			
		}
		crmAgentParam.setDelFlag(1);
		crmAgentParam.setNameLike(crmWorkTargetParam.getAgentNameLike());
		List<CrmAgentPO> crmAgentPOs = crmAgentService.commonQuery(crmAgentParam);//查询代理商信息
		
		//指明团队子父关系Map<Long,List<Long>> agentId:子孙agentId集合(包含自己)
		Map<Long,LinkedList<Long>> agentIdWithChildsIds = new HashMap<Long, LinkedList<Long>>();
		//获取代理商ids
		List<Long> agentIds = new LinkedList<Long>();
		LinkedList<Long> childAgentIds = null;//子孙agentId集合(包含自己)
		Map<Long,String> agentIdWithAgentName = new HashMap<Long, String>();//agentId:agentName
		for (CrmAgentPO crmAgentPO : crmAgentPOs) {
			Long crmAgentId = crmAgentPO.getId();
			agentIds.add(crmAgentId);
			agentIdWithAgentName.put(crmAgentId, crmAgentPO.getName());
			//查询该代理商的子孙代理商(包含本代理商)
			CrmAgentParam agentParam = new CrmAgentParam();
			agentParam.setParentId(crmAgentId);
			agentParam.setDelFlag(1);
			childAgentIds = new LinkedList<Long>();
			List<CrmAgentPO> agentPOs = crmAgentService.getChildAgent(agentParam);//查询子孙代理商(包含自己)
			for (CrmAgentPO crmAgentPO2 : agentPOs) {
				childAgentIds.add(crmAgentPO2.getId());
			}
			if(!agentIdWithChildsIds.containsKey(crmAgentId)){
				agentIdWithChildsIds.put(crmAgentId, childAgentIds);
			}
			
		}
		
		//查询业务员信息(查询代理商及子孙代理商的业务员)
		Map<Long,LinkedList<Long>> agentIdWithBusinesssIds = new HashMap<Long,LinkedList<Long>>();//代理商Id:业务员ids
		CrmBusinessParam crmBusinessParam = null;
		DecimalFormat df = new DecimalFormat("######0.00");
		LinkedList<Long> businessIdList = null;
		for(Long agentId : agentIdWithChildsIds.keySet()){
			LinkedList<Long> agentIdList = agentIdWithChildsIds.get(agentId);
			
			//查询业务员信息
			crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setAgentIds(agentIdList);
			crmBusinessParam.setDelFlag(1);
			crmBusinessParam.setType(2);//代理商业务员
			List<CrmBusinessPO> CrmBusinessPOs = crmBusinessService.commonQuery(crmBusinessParam);
			
			businessIdList = new LinkedList<Long>();
			for (CrmBusinessPO crmBusinessPO : CrmBusinessPOs) {
				Long id = crmBusinessPO.getId();
				businessIdList.add(id);
			}
			if(!agentIdWithBusinesssIds.containsKey(agentId)){
				agentIdWithBusinesssIds.put(agentId, businessIdList);
			}
			businessIdList.clear();
		}
		
		
		crmWorkTargetParam.setAgentIds(agentIds);//代理商ids
		crmWorkTargetParam.setDelFlag(1);//存在
		crmWorkTargetParam.setTargetType(3);//代理商
		crmWorkTargetParam.setApplyStatus(2);//申请状态(1.申请中 2通过)
		//查询代理商工作目标
		Page<CrmWorkTargetPO> crmWorkTargetPOPage = this.queryPageByParam(crmWorkTargetParam);
		
		List<CrmWorkTargetVO> crmWorkTargetVOs = BeanConvertor.convertBean(crmWorkTargetPOPage.result, ArrayList.class);
		
		Double preComplete = null;//完成率
		for (int i = 0; i < crmWorkTargetVOs.size(); i++) {
			CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetVOs.get(i),CrmWorkTargetVO.class);
			Date applyMonth = crmWorkTargetVO.getApplyMonth();//工作月
			Long agentId = crmWorkTargetVO.getAgentId();//代理商Id
			if(agentIdWithBusinesssIds.containsKey(agentId)){
				preComplete = 0.00;
				LinkedList<Long> businesssIds = agentIdWithBusinesssIds.get(agentId);
				
			if(CollectionUtils.isNotEmpty(businesssIds)){
					
				//查询业务员发展代理商数量(完成量)
				CrmAgentParam agentParam = new CrmAgentParam();
				agentParam.setApplyMonth(applyMonth);
				agentParam.setBusinessIds(businesssIds);//业务员Ids
				Integer CompleteAgentNum =crmAgentService.commonCount(agentParam);//查询业务员发展代理商数量
				
				crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
				Long agentNum2 = crmWorkTargetVO.getAgentNum();
				Integer agentNum = CommUtil.parseInteger(agentNum2);
				
				if(null == agentNum2 || CompleteAgentNum >= agentNum){
					preComplete +=33.33;
				}
				
				
				//查询业务员发展商家数量完成量
				CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
				businessCanteenParam.setApplyMonth(applyMonth);
				businessCanteenParam.setBusinessIds(businesssIds);
				Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
				crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
				Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
				Integer canteenNum = CommUtil.parseInteger(canteenNum2);
				
				if(null == canteenNum2 || completeCanteenNum >= canteenNum){
					preComplete +=33.33;
				}
				
				//查询业务员跟踪记录表关于订单金额完成量
				CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
				businessOrderParam.setDelFlag(1);
				businessOrderParam.setApplyMonth(applyMonth);
				businessOrderParam.setBusinessIds(businesssIds);
				BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
				crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
				BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
				
				if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
					preComplete +=33.33;
				}
				
				//设置完成百分比
				if(99.99 == preComplete){
					crmWorkTargetVO.setPercentageCompletion("100.00");
				}else{
					crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
				}
				
			}else{
				crmWorkTargetVO.setCompleteAgentNum(0L);//
				crmWorkTargetVO.setCompleteCanteenNum(0L);//查询业务员发展商家数量
				crmWorkTargetVO.setCompleteOrderMoney(BigDecimal.ZERO);//订单金额
				crmWorkTargetVO.setPercentageCompletion("0.00");
			}
				
			}
			
			
			//设置代理商名
			if(agentIdWithAgentName.containsKey(agentId)){
				crmWorkTargetVO.setAgentName(agentIdWithAgentName.get(agentId));
			}
			
			
			crmWorkTargetVOs.set(i, crmWorkTargetVO);
		}
		
		
		pageRespModel.setTotal(crmWorkTargetPOPage.getTotalResult());
		pageRespModel.setRows(crmWorkTargetVOs);
		return false;
	}
	/**
	 * 获取业务员(业务员个人,不含下属业务员业绩)目标信息 
	 */
	@Override
	public Boolean getBusinessTargetDetails(
			CrmWorkTargetParam crmWorkTargetParam, PageRespModel pageRespModel) {
		//获取登录者信息判断
		Long userId = crmWorkTargetParam.getUserId();//登录者Id
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(userId);
		crmUserPO = (CrmUserPO) crmUserService.getByExample(crmUserPO);//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmUserPO.getUserType();
		if(3 == userType){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("操作有误");
			pageRespModel.setTotal(0);
			return true;
		}
		
		Long teamId = crmWorkTargetParam.getTeamId();
		//String businessNameLike = crmWorkTargetParam.getBusinessNameLike();
		String teamIdsStr = crmWorkTargetParam.getTeamIdsStr();
		
		//根据传参判断(teamId与teamIdsStr)查询业务员
		CrmBusinessParam businessParam = null;
		if(BeanUtils.isNotEmpty(teamId)){
			crmWorkTargetParam.setTeamIdsStr(null);
			businessParam = new CrmBusinessParam();
			businessParam.setTeamId(teamId);
		}else{
			if(BeanUtils.isNotEmpty(teamIdsStr)){
				businessParam = new CrmBusinessParam();
				businessParam.setTeamIdsStr(teamIdsStr);
			}
		}
		//List<CrmBusinessPO> crmBusinessPOs = null;
		/*if(BeanUtils.isNotEmpty(teamId)){
			businessParam = new CrmBusinessParam();
			businessParam.setTeamId(teamId);
			businessParam.setDelFlag(1);
			if(BeanUtils.isNotEmpty(businessNameLike)){
				businessParam.setNameLike(businessNameLike);
			}
		}else{
			//若管理员登录
			if(1 == userType){
				businessParam = new CrmBusinessParam();
				businessParam.setDelFlag(1);
				businessParam.setType(1);//'1.平台业务员，2.代理商业务员'
				
			}
			//若代理商登录
			if(2 == userType){
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setDelFlag(1);
				crmAgentParam.setUserId(userId);
				List<CrmAgentPO> crmAgentPOs = crmAgentService.commonQuery(crmAgentParam);
				if(1 == crmAgentPOs.size()){//仅查出一条记录
					CrmAgentPO crmAgentPO = crmAgentPOs.get(0);
					
					businessParam = new CrmBusinessParam();
					businessParam.setAgentId(crmAgentPO.getId());//业务员所属代理商
					businessParam.setType(2);//'1.平台业务员，2.代理商业务员'
					
					
				}else{
					pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
					pageRespModel.setRows(null);
					pageRespModel.setDesc("出错了");
					pageRespModel.setTotal(0);
					return true;
				}
				
			}
		}*/
		
		if(null != businessParam){
			
			businessParam.setDelFlag(1);
			/*if(BeanUtils.isNotEmpty(businessNameLike)){
				businessParam.setNameLike(businessNameLike);
			}*/
			Long businessId2 = crmWorkTargetParam.getBusinessId();
			if(BeanUtils.isNotEmpty(businessId2)){
				businessParam.setId(businessId2);
			}
			
			//查询代理商或平台员工信息
			Map<Long,CrmBusinessPO> businessIdWithCrmBusinessPO = new HashMap<Long, CrmBusinessPO>();//businessId : CrmBusinessPO
			Map<Long,Integer> businessIdWithTeamId = new HashMap<Long, Integer>();//teamId : 1
			List<CrmBusinessPO> crmBusinessPOs = crmBusinessService.commonQuery(businessParam);
			if(CollectionUtils.isEmpty(crmBusinessPOs)){
				pageRespModel.setRows(null);
				pageRespModel.setTotal(0);
				return true;
			}
			
			for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
				Long crmBusinessId = crmBusinessPO.getId();
				Long tId = crmBusinessPO.getTeamId();
				
				businessIdWithCrmBusinessPO.put(crmBusinessId, crmBusinessPO);
				
				businessIdWithTeamId.put(tId, 1);
			}
			
			//查询业务员所在团队名
			Collection<Long> teamCollection = businessIdWithTeamId.keySet();
			CrmTeamParam crmTeamParam = new CrmTeamParam();
			crmTeamParam.setIds(new LinkedList<Long>(teamCollection));
			crmTeamParam.setDelFlag(1);
			List<CrmTeamPO> crmTeamPOs = crmTeamService.commonQuery(crmTeamParam);
			Map<Long,String> teamIdWithTeamName = new HashMap<Long, String>();
			for (CrmTeamPO crmTeamPO : crmTeamPOs) {
				teamIdWithTeamName.put(crmTeamPO.getId(), crmTeamPO.getName());
			}
			
			
			//查询业务员目标记录
			Set<Long> businessSet = businessIdWithCrmBusinessPO.keySet();//业务员ids
			crmWorkTargetParam.setTeamId(null);//将teamId置空
			crmWorkTargetParam.setTeamIdsStr(null);//将teamIdsStr置空
			crmWorkTargetParam.setDelFlag(1);
			crmWorkTargetParam.setTargetType(2);//目标类型(1.团队 2.个人,3.代理商)
			crmWorkTargetParam.setBusinessIds(new LinkedList<Long>(businessSet));
			crmWorkTargetParam.setApplyStatus(2);//申请状态(1.申请中 2通过)
			Page<CrmWorkTargetPO> crmWorkTargetPOPage = this.queryPageByParam(crmWorkTargetParam);
			List<CrmWorkTargetVO> crmWorkTargetVOs = BeanConvertor.convertBean(crmWorkTargetPOPage.result, ArrayList.class);
			DecimalFormat df = new DecimalFormat("######0.00");
			Double preComplete = null;//完成率
			for (int i = 0; i < crmWorkTargetVOs.size(); i++) {
				CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetVOs.get(i),CrmWorkTargetVO.class);
				Date applyMonth = crmWorkTargetVO.getApplyMonth();//工作月
				Long businessId = crmWorkTargetVO.getBusinessId();//业务员Id
				
			if(BeanUtils.isNotEmpty(businessId)){
				preComplete = 0.00;
				//查询业务员发展代理商数量(完成量)
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setApplyMonth(applyMonth);
				crmAgentParam.setBusinessId(businessId);
				Integer CompleteAgentNum =crmAgentService.commonCount(crmAgentParam);//查询业务员发展代理商数量
				crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
				Long agentNum2 = crmWorkTargetVO.getAgentNum();
				Integer agentNum = CommUtil.parseInteger(agentNum2);
				
				if(null == agentNum2 || CompleteAgentNum >= agentNum){
					preComplete +=33.33;
				}
				
				//查询业务员发展商家数量完成量
				CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
				businessCanteenParam.setApplyMonth(applyMonth);
				businessCanteenParam.setBusinessId(businessId);
				Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
				crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
				Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
				Integer canteenNum = CommUtil.parseInteger(canteenNum2);
				if(null == canteenNum2 || completeCanteenNum >= canteenNum){
					preComplete +=33.33;
				}
				
				//查询业务员跟踪记录表关于订单金额完成量
				CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
				businessOrderParam.setDelFlag(1);
				businessOrderParam.setApplyMonth(applyMonth);
				businessOrderParam.setBusinessId(businessId);
				BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
				crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
				BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
				
				if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
					preComplete +=33.33;
				}
				
				//设置完成百分比
				if(99.99 == preComplete){
					crmWorkTargetVO.setPercentageCompletion("100.00");
				}else{
					crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
				}
				
			}
				
				//业务员名
				if(businessIdWithCrmBusinessPO.containsKey(businessId)){
					CrmBusinessPO crmBusinessPO = businessIdWithCrmBusinessPO.get(businessId);
					String Businessname = crmBusinessPO.getName();
					crmWorkTargetVO.setBusinessName(Businessname);
					Long teamID = crmBusinessPO.getTeamId();
					//团队名
					if(null != teamID && teamIdWithTeamName.containsKey(teamID)){
						String teamName = teamIdWithTeamName.get(teamID);
						crmWorkTargetVO.setTeamName(teamName);
					}
				}
				
				
				crmWorkTargetVOs.set(i, crmWorkTargetVO);
			}
			pageRespModel.setTotal(crmWorkTargetPOPage.getTotalResult());
			pageRespModel.setRows(crmWorkTargetVOs);
		}else{
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
		}
		
		return false;
	}

	/**
	 * 获取个人(业务员个人,不含下属业务员业绩)目标信息
	 */
	@Override
	public Boolean getPersonalTargetDetails(
			CrmWorkTargetParam crmWorkTargetParam, PageRespModel pageRespModel) {
		//获取登录者信息判断
		Long userId = crmWorkTargetParam.getUserId();//登录者Id
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(userId);
		crmUserPO = (CrmUserPO) crmUserService.getByExample(crmUserPO);//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmUserPO.getUserType();
		if(3 != userType){//管理员,代理商,业务经理无此权限
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("操作有误");
			pageRespModel.setTotal(0);
			return true;
		}
		
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setUserId(userId);
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPOs = crmBusinessService.commonQuery(crmBusinessParam);
		if(1 == crmBusinessPOs.size()){//仅查询一条业务员信息
			CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
			Long businessId = crmBusinessPO.getId();
			
			//查询工作目标信息
			crmWorkTargetParam.setBusinessId(businessId);
			crmWorkTargetParam.setTargetType(2);//目标类型(1.团队 2.个人,3.代理商)
			crmWorkTargetParam.setDelFlag(1);//存在
			crmWorkTargetParam.setApplyStatus(2);//申请状态(1.申请中 2通过)
			Page<CrmWorkTargetPO> crmWorkTargetPOPage = this.queryPageByParam(crmWorkTargetParam);
			List<CrmWorkTargetVO> crmWorkTargetVOs = BeanConvertor.convertBean(crmWorkTargetPOPage.result, ArrayList.class);
			DecimalFormat df = new DecimalFormat("######0.00");
			Double preComplete = null;//完成率
			for (int i = 0; i < crmWorkTargetVOs.size(); i++) {
				preComplete = 0.00;
				CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetVOs.get(i),CrmWorkTargetVO.class);
				Date applyMonth = crmWorkTargetVO.getApplyMonth();//工作月
				Long businessid = crmWorkTargetVO.getBusinessId();//业务员Id
				
				//查询业务员发展代理商数量(完成量)
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setApplyMonth(applyMonth);
				crmAgentParam.setBusinessId(businessid);
				Integer CompleteAgentNum =crmAgentService.commonCount(crmAgentParam);//查询业务员发展代理商数量
				crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
				Long agentNum2 = crmWorkTargetVO.getAgentNum();
				Integer agentNum = CommUtil.parseInteger(agentNum2);
				
				if(null == agentNum2 || CompleteAgentNum >= agentNum){
					preComplete +=33.33;
				}
				
				//查询业务员发展商家数量完成量
				CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
				businessCanteenParam.setApplyMonth(applyMonth);
				businessCanteenParam.setBusinessId(businessid);
				Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
				crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
				Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
				Integer canteenNum = CommUtil.parseInteger(canteenNum2);
				if(null == canteenNum2 || completeCanteenNum >= canteenNum){
					preComplete +=33.33;
				}
				
				//查询业务员跟踪记录表关于订单金额完成量
				CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
				businessOrderParam.setDelFlag(1);
				businessOrderParam.setApplyMonth(applyMonth);
				businessOrderParam.setBusinessId(businessid);
				BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
				crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
				BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
				
				if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
					preComplete +=33.33;
				}
				
				//设置完成百分比
				if(99.99 == preComplete){
					crmWorkTargetVO.setPercentageCompletion("100.00");
				}else{
					crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
				}
				
				
				crmWorkTargetVOs.set(i, crmWorkTargetVO);
			}
			
			pageRespModel.setTotal(crmWorkTargetPOPage.getTotalResult());
			pageRespModel.setRows(crmWorkTargetVOs);
			
		}else{
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
		}
		
		
		return false;
	}

	/**
	 * 根据团队id查询团队工作目标信息
	 */
	@Override
	public CrmWorkTargetPO getByTeamId(Long teamId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("teamId", teamId);
		List<CrmWorkTargetPO> list = this.listByParams(CrmWorkTargetPO.class, "CrmWorkTargetPOMapper.findByTeamId", paramMap);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	/***
	 * 团队目标记录初始化(包含团队考核)
	 */
	@Override
	public void teamTargetInit(Long sessionUserId, ModelAndView modelAndView) {
		// TODO Auto-generated method stub
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(sessionUserId);
		crmUserPO = (CrmUserPO) crmUserService.getByExample(crmUserPO);//获取登录者信息
		Integer userType = crmUserPO.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		
		//根据用户类型不同查询不同
		if(1 == userType || 2 == userType || 4 == userType){//业务员无权查看团队目标
			//查询团队
			CrmTeamParam crmteamParam = null;
			
			
			if(1 == userType){// 管理员:查看平台所有团队
				crmteamParam = new CrmTeamParam();
				//crmteamParam.setAgentId(null);//crm_agent表主键ID，如果为空，是平台团队，否则就是代理商团队
				crmteamParam.setType(1);//1:平台团队
				modelAndView.addObject("teamCheckTitle", "团队考核");
			}
			if(2 == userType){//代理商:查询该代理商所有团队
				//查询该代理商信息
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setUserId(sessionUserId);
				crmAgentParam.setDelFlag(1);//存在
				List<CrmAgentPO> crmAgentPOs = crmAgentService.commonQuery(crmAgentParam);
				
				if(1 == crmAgentPOs.size()){//若只查到一条代理商记录
					CrmAgentPO crmAgentPO = crmAgentPOs.get(0);
					crmteamParam = new CrmTeamParam();
					crmteamParam.setAgentId(crmAgentPO.getId());//传入代理商Id
				}
			}
			if(4 == userType){//业务经理:查询该业务经理所在团队
				//查询该业务员信息(业务经理也是业务员,平台业务员还是代理商业务员)
				CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
				crmBusinessParam.setUserId(sessionUserId);
				crmBusinessParam.setDelFlag(1);
				List<CrmBusinessPO> crmBusinessPOs = crmBusinessService.commonQuery(crmBusinessParam);
				if(1 == crmBusinessPOs.size()){//若只查到一条业务员记录
					CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
					Long teamId = crmBusinessPO.getTeamId();
					//Long agentId = crmBusinessPO.getAgentId();//若为平台业务员,代理商Id为null
					CrmTeamParam teamParam = new CrmTeamParam();
					teamParam.setParentId(teamId);
					teamParam.setDelFlag(1);
					List<CrmTeamPO> childTeamPOs = crmTeamService.getChildTeam(teamParam);
					List<Long> teamIds = new LinkedList<Long>();
					if(CollectionUtils.isNotEmpty(childTeamPOs)){
						for (CrmTeamPO crmTeamPO : childTeamPOs) {
							teamIds.add(crmTeamPO.getId());
						}
						crmteamParam = new CrmTeamParam();
						crmteamParam.setIds(teamIds);
					}
					/*crmteamParam = new CrmTeamParam();
					crmteamParam.setId(teamId);
					crmteamParam.setAgentId(agentId);*/
				}
				
			}
			
			if(BeanUtils.isNotEmpty(crmteamParam)){
				crmteamParam.setDelFlag(1);
				//查询相关团队信息
				List<CrmTeamPO> crmTeamPOs = crmTeamService.queryTeamMsg(crmteamParam);
				if(CollectionUtils.isNotEmpty(crmTeamPOs)){
					modelAndView.addObject("crmTeamPOs", crmTeamPOs);
					//获取团队Ids(避免第二次查询)
					String teamIds = "";
					for (CrmTeamPO crmTeamPO : crmTeamPOs) {
						teamIds += crmTeamPO.getId() + ",";
					}
					teamIds = teamIds.substring(0, teamIds.length()-1);
					modelAndView.addObject("teamIds", teamIds);
				}
			}
			
		}
	}
	/**
	 * 业务员目标记录初始化(包含业务员考核)
	 */
	@Override
	public void businessTargetInit(Long sessionUserId, ModelAndView modelAndView) {
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(sessionUserId);
		crmUserPO = (CrmUserPO) crmUserService.getByExample(crmUserPO);//获取登录者信息
		Integer userType = crmUserPO.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		//根据用户类型不同查询不同
		if(1 == userType || 2 == userType || 4 == userType){//业务员无权
			//查询团队
			CrmTeamParam crmteamParam = null;
			
			if(1 == userType){// 管理员:查看平台所有团队
				crmteamParam = new CrmTeamParam();
				crmteamParam.setType(1);
				//crmteamParam.setAgentId(null);//crm_agent表主键ID，如果为空，是平台团队，否则就是代理商团队
				modelAndView.addObject("businessCheckTitle", "业务员考核");
			}
			if(2 == userType){//代理商:查询该代理商所有团队
				//查询该代理商信息
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setUserId(sessionUserId);
				crmAgentParam.setDelFlag(1);//存在
				List<CrmAgentPO> crmAgentPOs = crmAgentService.commonQuery(crmAgentParam);
				
				if(1 == crmAgentPOs.size()){//若只查到一条代理商记录
					CrmAgentPO crmAgentPO = crmAgentPOs.get(0);
					crmteamParam = new CrmTeamParam();
					crmteamParam.setAgentId(crmAgentPO.getId());//传入代理商Id
				}
			}
			
			if(4 == userType){//业务经理:查询管辖团队(子孙团队)下业务员
				CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
				crmBusinessParam.setUserId(sessionUserId);//userId
				crmBusinessParam.setDelFlag(1);
				List<CrmBusinessPO> crmBusinessPOs = crmBusinessService.commonQuery(crmBusinessParam);
				if(1 == crmBusinessPOs.size()){
					CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
					Long teamId = crmBusinessPO.getTeamId();
					CrmTeamParam teamParam = new CrmTeamParam();
					teamParam.setParentId(teamId);
					teamParam.setDelFlag(1);
					List<CrmTeamPO> childTeamPOs = crmTeamService.getChildTeam(teamParam);
					List<Long> teamIds = new LinkedList<Long>();
					if(CollectionUtils.isNotEmpty(childTeamPOs)){
						for (CrmTeamPO crmTeamPO : childTeamPOs) {
							teamIds.add(crmTeamPO.getId());
						}
						crmteamParam = new CrmTeamParam();
						crmteamParam.setIds(teamIds);
					}
				}
			}
			
			
			if(BeanUtils.isNotEmpty(crmteamParam)){
				crmteamParam.setDelFlag(1);
				//查询相关团队信息
				List<CrmTeamPO> crmTeamPOs = crmTeamService.queryTeamMsg(crmteamParam);
				if(CollectionUtils.isNotEmpty(crmTeamPOs)){
					modelAndView.addObject("crmTeamPOs", crmTeamPOs);
					//获取团队Ids(避免第二次查询)
					String teamIds = "";
					List<Long> teamIdList = new LinkedList<Long>();
					for (CrmTeamPO crmTeamPO : crmTeamPOs) {
						Long teamId = crmTeamPO.getId();
						teamIds += teamId + ",";
						teamIdList.add(teamId);
					}
					teamIds = teamIds.substring(0, teamIds.length()-1);
					modelAndView.addObject("teamIds", teamIds);
					
					//查询业务员信息
					CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
					crmBusinessParam.setTeamIds(teamIdList);
					crmBusinessParam.setDelFlag(1);
					List<CrmBusinessPO> crmBusinessPOs = crmBusinessService.commonQuery(crmBusinessParam);
					modelAndView.addObject("businessPOs", crmBusinessPOs);
				}
			}
			
		}
	}
	
	/**
	 * 团队目标(包括团队考核)详情导出
	 */
	@Override
	public List<CrmWorkTargetVO> teamTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam) {
		
		//查询团队的目标记录
		List<CrmWorkTargetPO> crmWorkTargetPOs = this.commonQuery(crmWorkTargetParam);
		List<CrmWorkTargetVO> crmWorkTargetVOs = new ArrayList<CrmWorkTargetVO>();
		Map<Long,List<Long>> teamIdWithChildTeams = new HashMap<Long,List<Long>>();//teamId:子孙团队Id(包括本身)
		for (CrmWorkTargetPO crmWorkTargetPO : crmWorkTargetPOs) {
			CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetPO, CrmWorkTargetVO.class);
			
			//查询团队子孙团队Id(包括本身Id)
			Long teamId = crmWorkTargetVO.getTeamId();
			if(!teamIdWithChildTeams.containsKey(teamId)){
				CrmTeamParam crmTeamParam = new CrmTeamParam();
				crmTeamParam.setParentId(teamId);
				crmTeamParam.setDelFlag(1);
				//查询子孙Ids
				List<Long> childTeamIds = crmTeamService.getChildTeamIds(crmTeamParam);
				teamIdWithChildTeams.put(teamId, childTeamIds);
			}
			
			crmWorkTargetVOs.add(crmWorkTargetVO);
		}
		
		
		
		Map<Long,List<Long>> teamIdWithBusinessIds = new HashMap<Long, List<Long>>();//teamId:businessIds
		//查询子孙团队业务员Ids
		for(Long teamId : teamIdWithChildTeams.keySet()){
			
			List<Long> teamIds = teamIdWithChildTeams.get(teamId);
			//根据teamIds查询业务员Ids
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setTeamIds(teamIds);
			crmBusinessParam.setDelFlag(1);
			List<Long> businessIds = crmBusinessService.queryBusinessIdByParam(crmBusinessParam);
			teamIdWithBusinessIds.put(teamId, businessIds);
		}
		
		//查询团队名
		Set<Long> teamIdSet = teamIdWithChildTeams.keySet();//团队Id
		CrmTeamParam crmTeamParam = new CrmTeamParam();
		crmTeamParam.setIds(new LinkedList<Long>(teamIdSet));
		crmTeamParam.setDelFlag(1);
		List<CrmTeamPO> crmTeamPOs = crmTeamService.commonQuery(crmTeamParam);
		Map<Long,String> teamIdWithTeamName = new HashMap<Long, String>();//teamId:teamName
		for (CrmTeamPO crmTeamPO : crmTeamPOs) {
			teamIdWithTeamName.put(crmTeamPO.getId(), crmTeamPO.getName());
		}
		
		//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
		Double preComplete = null;
		DecimalFormat df = new DecimalFormat("######0.00");
		for(CrmWorkTargetVO crmWorkTargetVO : crmWorkTargetVOs){
			Long teamId = crmWorkTargetVO.getTeamId();
			Date applyMonth = crmWorkTargetVO.getApplyMonth();
			
			//设置团队名
			if(teamIdWithTeamName.containsKey(teamId)){
				crmWorkTargetVO.setTeamName(teamIdWithTeamName.get(teamId));
			}
			
			//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
			if(teamIdWithBusinessIds.containsKey(teamId)){
				
				List<Long> businessIds = teamIdWithBusinessIds.get(teamId);
				
				if(CollectionUtils.isNotEmpty(businessIds)){
					preComplete = 0.00;
					//查询业务员发展代理商数量(完成量)
					CrmAgentParam crmAgentParam = new CrmAgentParam();
					crmAgentParam.setApplyMonth(applyMonth);
					crmAgentParam.setDelFlag(1);
					crmAgentParam.setBusinessIds(businessIds);//业务员Ids
					Integer CompleteAgentNum =crmAgentService.commonCount(crmAgentParam);//查询业务员发展代理商数量
					crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
					Long agentNum2 = crmWorkTargetVO.getAgentNum();
					Integer agentNum = CommUtil.parseInteger(agentNum2);
					
					if(null == agentNum2 || CompleteAgentNum >= agentNum){
						preComplete +=33.33;
					}
					
					//查询业务员发展商家数量完成量
					CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
					businessCanteenParam.setApplyMonth(applyMonth);
					businessCanteenParam.setDelFlag(1);
					businessCanteenParam.setBusinessIds(businessIds);
					Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
					crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
					Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
					Integer canteenNum = CommUtil.parseInteger(canteenNum2);
					if(null == canteenNum2 || completeCanteenNum >= canteenNum){
						preComplete +=33.33;
					}
					
					//查询业务员跟踪记录表关于订单金额完成量
					CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
					businessOrderParam.setDelFlag(1);
					businessOrderParam.setApplyMonth(applyMonth);
					businessOrderParam.setBusinessIds(businessIds);
					BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
					crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
					BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
					
					if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
						preComplete +=33.33;
					}
					
					//设置完成百分比
					if(99.99 == preComplete){
						crmWorkTargetVO.setPercentageCompletion("100.00");
					}else{
						crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
					}
					
				 }else{
					 crmWorkTargetVO.setCompleteAgentNum(0L);//
					 crmWorkTargetVO.setCompleteCanteenNum(0L);//查询业务员发展商家数量
					 crmWorkTargetVO.setCompleteOrderMoney(BigDecimal.ZERO);//订单金额
					 crmWorkTargetVO.setPercentageCompletion("0.00");
				 }
			}
			
			
		}
		return crmWorkTargetVOs;
	}

	/**
	 * 代理商目标(包括代理商考核)详情导出
	 */
	@Override
	public List<CrmWorkTargetVO> agentTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam) {
		
		//查询代理商的目标记录
		List<CrmWorkTargetPO> crmWorkTargetPOs = this.commonQuery(crmWorkTargetParam);
		
		List<CrmWorkTargetVO> crmWorkTargetVOs = new ArrayList<CrmWorkTargetVO>();
		Map<Long,List<Long>> agentIdWithChildAgentIds = new HashMap<Long,List<Long>>();//agentId:子孙代理商Id(包括本身)
		for (CrmWorkTargetPO crmWorkTargetPO : crmWorkTargetPOs) {
			CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetPO, CrmWorkTargetVO.class);
			
			Long agentId = crmWorkTargetVO.getAgentId();
			if(!agentIdWithChildAgentIds.containsKey(agentId)){
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setParentId(agentId);
				crmAgentParam.setDelFlag(1);
				//查询子孙agentId(包括本身)
				List<Long> childAgentIds = crmAgentService.getChildAgentIds(crmAgentParam);
				agentIdWithChildAgentIds.put(agentId, childAgentIds);
			}
			
			crmWorkTargetVOs.add(crmWorkTargetVO);
		}
		
		//获取已知代理商与其及子孙代理商业务员Id关系
		Map<Long,List<Long>> agentIdWithBusinessIds = new HashMap<Long, List<Long>>();//agentId:businessIds
		for(Long agentId : agentIdWithChildAgentIds.keySet()){
			
			List<Long> childAgentIds = agentIdWithChildAgentIds.get(agentId);
			//根据参数查询业务员Ids
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setAgentIds(childAgentIds);
			crmBusinessParam.setDelFlag(1);
			crmBusinessParam.setType(2);//1.平台业务员，2.代理商业务员
			List<Long> businessIds = crmBusinessService.queryBusinessIdByParam(crmBusinessParam);
			
			agentIdWithBusinessIds.put(agentId, businessIds);
		}
		
		//查询代理商名称
		Set<Long> agentIdSet = agentIdWithChildAgentIds.keySet();
		CrmAgentParam crmAgentParam = new CrmAgentParam();
		crmAgentParam.setIds(new LinkedList<Long>(agentIdSet));
		List<CrmAgentPO> crmAgentPOs = crmAgentService.commonQuery(crmAgentParam);
		Map<Long,String> agentIdWithAgentName = new HashMap<Long, String>();//agentId:agentName
		for (CrmAgentPO crmAgentPO : crmAgentPOs) {
			agentIdWithAgentName.put(crmAgentPO.getId(), crmAgentPO.getName());
		}
		
		
		//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
		Double preComplete = null;
		DecimalFormat df = new DecimalFormat("######0.00");
		for(CrmWorkTargetVO crmWorkTargetVO : crmWorkTargetVOs){
			Long agentId = crmWorkTargetVO.getAgentId();
			Date applyMonth = crmWorkTargetVO.getApplyMonth();
			
			//设置代理商名
			if(agentIdWithAgentName.containsKey(agentId)){
				crmWorkTargetVO.setTeamName(agentIdWithAgentName.get(agentId));
			}
			
			//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
			if(agentIdWithBusinessIds.containsKey(agentId)){
				
				List<Long> businessIds = agentIdWithBusinessIds.get(agentId);
				
				if(CollectionUtils.isNotEmpty(businessIds)){
					preComplete = 0.00;
					//查询业务员发展代理商数量(完成量)
					CrmAgentParam agentParam = new CrmAgentParam();
					agentParam.setApplyMonth(applyMonth);
					agentParam.setDelFlag(1);
					agentParam.setBusinessIds(businessIds);//业务员Ids
					Integer CompleteAgentNum =crmAgentService.commonCount(agentParam);//查询业务员发展代理商数量
					crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
					Long agentNum2 = crmWorkTargetVO.getAgentNum();
					Integer agentNum = CommUtil.parseInteger(agentNum2);
					
					if(null == agentNum2 || CompleteAgentNum >= agentNum){
						preComplete +=33.33;
					}
					
					//查询业务员发展商家数量完成量
					CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
					businessCanteenParam.setApplyMonth(applyMonth);
					businessCanteenParam.setDelFlag(1);
					businessCanteenParam.setBusinessIds(businessIds);
					Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
					crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
					Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
					Integer canteenNum = CommUtil.parseInteger(canteenNum2);
					if(null == canteenNum2 || completeCanteenNum >= canteenNum){
						preComplete +=33.33;
					}
					
					//查询业务员跟踪记录表关于订单金额完成量
					CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
					businessOrderParam.setDelFlag(1);
					businessOrderParam.setApplyMonth(applyMonth);
					businessOrderParam.setBusinessIds(businessIds);
					BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
					crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
					BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
					
					if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
						preComplete +=33.33;
					}
					
					//设置完成百分比
					if(99.99 == preComplete){
						crmWorkTargetVO.setPercentageCompletion("100.00");
					}else{
						crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
					}
					
				 }else{
					 crmWorkTargetVO.setCompleteAgentNum(0L);//
					 crmWorkTargetVO.setCompleteCanteenNum(0L);//查询业务员发展商家数量
					 crmWorkTargetVO.setCompleteOrderMoney(BigDecimal.ZERO);//订单金额
					 crmWorkTargetVO.setPercentageCompletion("0.00");
				 }
			}
		}
		
		return crmWorkTargetVOs;
	}

	/**
	 * 业务员目标(包括业务员考核)详情导出
	 */
	@Override
	public List<CrmWorkTargetVO> businessTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam) {
		
		//查询业务员的目标记录
		List<CrmWorkTargetPO> crmWorkTargetPOs = this.commonQuery(crmWorkTargetParam);
		List<CrmWorkTargetVO> crmWorkTargetVOs = new ArrayList<CrmWorkTargetVO>();
		Map<Long,Integer> businessIdMap = new HashMap<Long, Integer>();//businessId:1
		for (CrmWorkTargetPO crmWorkTargetPO : crmWorkTargetPOs) {
			CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetPO, CrmWorkTargetVO.class);
			Long businessId = crmWorkTargetVO.getBusinessId();
			if(!businessIdMap.containsKey(businessId)){
				businessIdMap.put(businessId, 1);
			}
			crmWorkTargetVOs.add(crmWorkTargetVO);
		}
		
		//查询业务员姓名
		Set<Long> businessIdSet = businessIdMap.keySet();
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setIds(new LinkedList<Long>(businessIdSet));
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPOs = crmBusinessService.commonQuery(crmBusinessParam);
		Map<Long,Long> businessIdWithTeamId = new HashMap<Long, Long>();//业务员Id:团队Id
		Map<Long,CrmBusinessPO> businessIdWithBusinessPO = new HashMap<Long, CrmBusinessPO>();//业务员Id:业务员name
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			Long id = crmBusinessPO.getId();
			
			//业务员Id:团队Id
			if(!businessIdWithTeamId.containsKey(id)){
				Long teamId = crmBusinessPO.getTeamId();
				businessIdWithTeamId.put(id, teamId);
			}
			
			//业务员Id:业务员name
			if(!businessIdWithBusinessPO.containsKey(id)){
				businessIdWithBusinessPO.put(id, crmBusinessPO);
			}
		}
		
		//查询团队名
		Collection<Long> teamIdValues = businessIdWithTeamId.values();
		//查询业务员所在团队名
		CrmTeamParam crmTeamParam = new CrmTeamParam();
		crmTeamParam.setIds(new LinkedList<Long>(teamIdValues));
		crmTeamParam.setDelFlag(1);
		List<CrmTeamPO> crmTeamPOs = crmTeamService.commonQuery(crmTeamParam);
		Map<Long,String> teamIdWithTeamName = new HashMap<Long, String>();
		for (CrmTeamPO crmTeamPO : crmTeamPOs) {
			teamIdWithTeamName.put(crmTeamPO.getId(), crmTeamPO.getName());
		}
		
		
		//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
		Double preComplete = null;
		DecimalFormat df = new DecimalFormat("######0.00");
		for(CrmWorkTargetVO crmWorkTargetVO : crmWorkTargetVOs){
			Long businessId = crmWorkTargetVO.getBusinessId();
			Date applyMonth = crmWorkTargetVO.getApplyMonth();
			
			//设置业务员名
			if(businessIdWithBusinessPO.containsKey(businessId)){
				CrmBusinessPO crmBusinessPO = businessIdWithBusinessPO.get(businessId);
				String name = crmBusinessPO.getName();
				crmWorkTargetVO.setBusinessName(name);
				Long teamId = crmBusinessPO.getTeamId();
				
				//设置团队名
				if(teamIdWithTeamName.containsKey(teamId)){
					crmWorkTargetVO.setTeamName(teamIdWithTeamName.get(teamId));
				}
			}
			
			
			//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
			if(BeanUtils.isNotEmpty(businessId)){
				preComplete = 0.00;
				//查询业务员发展代理商数量(完成量)
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setApplyMonth(applyMonth);
				crmAgentParam.setBusinessId(businessId);
				Integer CompleteAgentNum =crmAgentService.commonCount(crmAgentParam);//查询业务员发展代理商数量
				crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
				Long agentNum2 = crmWorkTargetVO.getAgentNum();
				Integer agentNum = CommUtil.parseInteger(agentNum2);
				
				if(null == agentNum2 || CompleteAgentNum >= agentNum){
					preComplete +=33.33;
				}
				
				//查询业务员发展商家数量完成量
				CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
				businessCanteenParam.setApplyMonth(applyMonth);
				businessCanteenParam.setBusinessId(businessId);
				Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
				crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
				Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
				Integer canteenNum = CommUtil.parseInteger(canteenNum2);
				if(null == canteenNum2 || completeCanteenNum >= canteenNum){
					preComplete +=33.33;
				}
				
				//查询业务员跟踪记录表关于订单金额完成量
				CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
				businessOrderParam.setDelFlag(1);
				businessOrderParam.setApplyMonth(applyMonth);
				businessOrderParam.setBusinessId(businessId);
				BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
				crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
				BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
				
				if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
					preComplete +=33.33;
				}
				
				//设置完成百分比
				if(99.99 == preComplete){
					crmWorkTargetVO.setPercentageCompletion("100.00");
				}else{
					crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
				}
				
			}else{
				crmWorkTargetVO.setCompleteAgentNum(0L);//
				crmWorkTargetVO.setCompleteCanteenNum(0L);//查询业务员发展商家数量
				crmWorkTargetVO.setCompleteOrderMoney(BigDecimal.ZERO);//订单金额
				crmWorkTargetVO.setPercentageCompletion("0.00");
			}
		}
		
		return crmWorkTargetVOs;
	}

	/**
	 * 个人目标详情导出
	 */
	@Override
	public List<CrmWorkTargetVO> personalTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam) {
		
		//查询个人的目标记录
		List<CrmWorkTargetPO> crmWorkTargetPOs = this.commonQuery(crmWorkTargetParam);
		List<CrmWorkTargetVO> crmWorkTargetVOs = new ArrayList<CrmWorkTargetVO>();
		
		
		//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
		Double preComplete = null;
		DecimalFormat df = new DecimalFormat("######0.00");
		for (CrmWorkTargetPO crmWorkTargetPO : crmWorkTargetPOs) {
			CrmWorkTargetVO crmWorkTargetVO = BeanConvertor.convertBean(crmWorkTargetPO, CrmWorkTargetVO.class);
			Long businessId = crmWorkTargetVO.getBusinessId();
			Date applyMonth = crmWorkTargetVO.getApplyMonth();
			
			//查询发展代理商完成量,发展食堂完成量,订单总金额完成量及完成度
			if(BeanUtils.isNotEmpty(businessId)){
				preComplete = 0.00;
				//查询业务员发展代理商数量(完成量)
				CrmAgentParam crmAgentParam = new CrmAgentParam();
				crmAgentParam.setApplyMonth(applyMonth);
				crmAgentParam.setBusinessId(businessId);
				Integer CompleteAgentNum =crmAgentService.commonCount(crmAgentParam);//查询业务员发展代理商数量
				crmWorkTargetVO.setCompleteAgentNum(CommUtil.parselong(CompleteAgentNum));
				Long agentNum2 = crmWorkTargetVO.getAgentNum();
				Integer agentNum = CommUtil.parseInteger(agentNum2);
				
				if(null == agentNum2 || CompleteAgentNum >= agentNum){
					preComplete +=33.33;
				}
				
				//查询业务员发展商家数量完成量
				CrmBusinessCanteenParam businessCanteenParam = new CrmBusinessCanteenParam();
				businessCanteenParam.setApplyMonth(applyMonth);
				businessCanteenParam.setBusinessId(businessId);
				Integer completeCanteenNum= crmBusinessCanteenService.commonCount(businessCanteenParam);
				crmWorkTargetVO.setCompleteCanteenNum(CommUtil.parselong(completeCanteenNum));
				Long canteenNum2 = crmWorkTargetVO.getCanteenNum();
				Integer canteenNum = CommUtil.parseInteger(canteenNum2);
				if(null == canteenNum2 || completeCanteenNum >= canteenNum){
					preComplete +=33.33;
				}
				
				//查询业务员跟踪记录表关于订单金额完成量
				CrmBusinessOrderParam businessOrderParam = new CrmBusinessOrderParam();
				businessOrderParam.setDelFlag(1);
				businessOrderParam.setApplyMonth(applyMonth);
				businessOrderParam.setBusinessId(businessId);
				BigDecimal completeOrderMoney = crmBusinessOrderService.sumOrderRealMoney(businessOrderParam);
				crmWorkTargetVO.setCompleteOrderMoney(completeOrderMoney);
				BigDecimal orderMoney = crmWorkTargetVO.getOrderMoney();
				
				if(null == orderMoney || -1 !=completeOrderMoney.compareTo(orderMoney)){
					preComplete +=33.33;
				}
				
				//设置完成百分比
				if(99.99 == preComplete){
					crmWorkTargetVO.setPercentageCompletion("100.00");
				}else{
					crmWorkTargetVO.setPercentageCompletion(df.format(preComplete));
				}
				
			}else{
				crmWorkTargetVO.setCompleteAgentNum(0L);//
				crmWorkTargetVO.setCompleteCanteenNum(0L);//查询业务员发展商家数量
				crmWorkTargetVO.setCompleteOrderMoney(BigDecimal.ZERO);//订单金额
				crmWorkTargetVO.setPercentageCompletion("0.00");
			}
			
			crmWorkTargetVOs.add(crmWorkTargetVO);
		}
		
		return crmWorkTargetVOs;
	}
	
	/**
	 * 查询工作目标信息
	 */
	@Override
	public List<CrmWorkTargetPO> commonQuery(
			CrmWorkTargetParam crmworktargetParam) {
		ParamMap paramMap = new ParamMap(crmworktargetParam);
		return commonDao.listByParams(CrmWorkTargetPO.class, "CrmWorkTargetPOMapper.commonQuery", paramMap);
	}
	/**
	 * 根据主键查询对象
	 */
	@Override
	public CrmWorkTargetPO findById(Long id) {
		ParamMap paramMap = new ParamMap();
		if(BeanUtils.isNotEmpty(id)){
			paramMap.put("id", id);
			List<CrmWorkTargetPO> crmWorkTargetPOs = listByParams(CrmWorkTargetPO.class, "CrmWorkTargetPOMapper.findById", paramMap);
			if(BeanUtils.isNotEmpty(crmWorkTargetPOs)){
				return crmWorkTargetPOs.get(0);
			}
		}
		return null;
	}
	
	
}
