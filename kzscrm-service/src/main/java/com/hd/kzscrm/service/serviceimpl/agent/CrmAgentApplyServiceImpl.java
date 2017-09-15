package com.hd.kzscrm.service.serviceimpl.agent;

//crmagentapply
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.agent.CrmAgentApplyPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.service.param.agent.CrmAgentApplyParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.agent.CrmAgentApplyVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmAgentApply 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmAgentApplyServiceImpl extends BaseServiceImpl implements ICrmAgentApplyService {
	/**
	 * 客户资源表
	 */
	@Resource
	ICrmClientResourceService crmClientResourceService;
	
	/**
	 * 代理商表
	 */
	@Resource
	ICrmAgentService iCrmAgentService;
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmAgentApplyServiceImpl.class);
     
	/**
	 * 地理信息
	 */
	 @Resource
	 private IBaseAreaService iBaseAreaService;
	 
	/**
	 * 业务员
	 */
	@Resource
	private ICrmBusinessService iCrmBusinessService;
	/**
	 * 团队
	 */
	@Resource
	private ICrmTeamService iCrmTeamService;

     /**
     *  默认构造函数
     */
     
	 public CrmAgentApplyServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmAgentApplyVO> convertPOToVO(List<CrmAgentApplyPO> crmagentapplyList){
    	List<CrmAgentApplyVO> crmagentapplyVoList = new ArrayList<CrmAgentApplyVO>();
    	if (CollectionUtils.isEmpty(crmagentapplyList)) {
    		return crmagentapplyVoList;
    	}
    	for (CrmAgentApplyPO tag : crmagentapplyList) {
    		CrmAgentApplyVO tagVo = BeanConvertor.copy(tag,CrmAgentApplyVO.class);
    		crmagentapplyVoList.add(tagVo);
    	}
    	return crmagentapplyVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmAgentApplyVO> queryPage(CrmAgentApplyParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmAgentApplyName",param.getCrmAgentApplyName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmAgentApplyPO> crmagentapplyList = findPageByParams(CrmAgentApplyPO.class,new Page<CrmAgentApplyPO>(param.getOffset(),param.getLimit()),"CrmAgentApplyPOMapper.queryPage",paramMap);
    	List<CrmAgentApplyVO> rows = new ArrayList<CrmAgentApplyVO>();
    	int total = 0;
    	if(crmagentapplyList.result != null){
    		rows = convertPOToVO(crmagentapplyList.result);
    		total = crmagentapplyList.getTotalResult();
    	}    	
    	Page<CrmAgentApplyVO> pageResult = new Page<CrmAgentApplyVO>();
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
    public List<CrmAgentApplyPO> listByParam(CrmAgentApplyParam crmagentapplyParam){
    	ParamMap paramMap = new ParamMap(crmagentapplyParam);
    	return commonDao.listByParams(CrmAgentApplyPO.class,"CrmAgentApplyPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmAgentApplyPO po = this.get(CrmAgentApplyPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmAgentApplyPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmAgentApplyPO> listPo = new ArrayList<CrmAgentApplyPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmAgentApplyPO po = this.get(CrmAgentApplyPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmAgentApplyPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmAgentApplyParam param){
    	CrmAgentApplyPO crmagentapplyPO = BeanConvertor.copy(param,CrmAgentApplyPO.class);
    	this.save(crmagentapplyPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmAgentApplyPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_agent_apply));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmAgentApplyPO po){
     	this.update(po);
    }

    /**
     * 根据id查询
     */
	@Override
	public CrmAgentApplyPO getById(Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		List<CrmAgentApplyPO> clist = listByParams(CrmAgentApplyPO.class, "CrmAgentApplyPOMapper.getById", paramMap);
		if (BeanUtils.isNotEmpty(clist)) {
			return clist.get(0);
		}
		return null;
	}

	/**
	 * 代理商加盟申请信息保存
	 * @throws ParseException 
	 */
	@Override
	public Boolean saveAgentJoinApply(CrmAgentApplyParam crmAgentApplyParam,
			RespModel respModel) throws ParseException {
		Long clientId = crmAgentApplyParam.getClientId();//获取客户资源Id
		
		//查询客户资源信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) crmClientResourceService.getByExample(crmClientResourcePO);
		if(BeanUtils.isNotEmpty(crmClientResourcePO)){
			
			Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Integer checkStatus = crmClientResourcePO.getCheckStatus();//审核状态,0.申请中，1.申请通过，2.申请失败
			if(1 == clientType && ( null == checkStatus || 2 == checkStatus)){
				//日期解析
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String cooperationEndTimeStr = crmAgentApplyParam.getCooperationEndTimeStr();
				crmAgentApplyParam.setCooperationEndTime(sdf.parse(cooperationEndTimeStr));
				
				String cooperationStartTimeStr = crmAgentApplyParam.getCooperationStartTimeStr();
				crmAgentApplyParam.setCooperationStartTime(sdf.parse(cooperationStartTimeStr));
				
				Date now = new Date();//当前时间
				//新增申请信息
				CrmAgentApplyPO crmAgentApplyPO = BeanConvertor.convertBean(crmAgentApplyParam, CrmAgentApplyPO.class);
				crmAgentApplyPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_agent_apply));
				crmAgentApplyPO.setApplyTime(now);
				crmAgentApplyPO.setClientId(clientId);
				crmAgentApplyPO.setCheckStatus(0);//审核状态 ,0.申请中，1.申请通过，2.申请失败
				crmAgentApplyPO.setDelFlag(1);
				crmAgentApplyPO.setAgentId(crmClientResourcePO.getAgentCanteenId());//代理商id
				logger.info("#####CrmAgentApplyServiceImpl###saveAgentJoinApply##保存crmAgentApplyPO:"+crmAgentApplyPO.toString());
				this.save(crmAgentApplyPO);
				
				//更新客户资源信息
				crmClientResourcePO.setApplyTime(now);
				crmClientResourcePO.setCheckStatus(0);//申请中
				logger.info("#####CrmAgentApplyServiceImpl###saveAgentJoinApply##更新crmClientResourcePO:"+crmClientResourcePO.toString());
				crmClientResourceService.update(crmClientResourcePO);
				
				//更新代理商信息
				CrmAgentPO agentPO = new CrmAgentPO();
				agentPO.setId(crmClientResourcePO.getAgentCanteenId());
				agentPO.setUpdateTime(now);
				agentPO.setBusinessId(crmClientResourcePO.getBusinessId());
				agentPO.setAuthenticationStatus(0);//认证状态	0.申请中，1.认证通过，2.认证失败,3.未申请
				agentPO.setUpdateId(crmAgentApplyParam.getCreateUid());
				logger.info("#####CrmAgentApplyServiceImpl###saveAgentJoinApply##更新agentPO:"+agentPO.toString());
				iCrmAgentService.update(agentPO);
				
			}else{
				respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
				respModel.setDesc("出错了");
				respModel.setRows(null);
				return true;
			}
			
		}
		
		return false;
	}

	/**
	 * 代理商是否到期
	 * @param agentId 代理商加盟申请ID
	 */
	@Override
	public boolean whetherExpire(Long agentId){
		CrmAgentApplyPO crmAgentApplyPO = this.findByAgentId(agentId);
		Date cooperationEndTime = crmAgentApplyPO.getCooperationEndTime();//到期时间
		if(BeanUtils.isNotEmpty(cooperationEndTime)){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(cooperationEndTime);
			calendar.add(Calendar.DAY_OF_MONTH,1);
			//如果现在时间等于或大于签约到期时间，返回true
			if(new Date().compareTo(calendar.getTime())>=0){
				return true;
			}
		}
		return false;
	}
	/**
	 * 代理商续约 
	 */
	@Override
	public Boolean agentContractExtension(Long clientId,
			ModelAndView modelAndView) {
		//查询客户资源信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) crmClientResourceService.getByExample(crmClientResourcePO);
		CrmAgentApplyParam crmAgentApplyParam = new CrmAgentApplyParam();
		if(BeanUtils.isNotEmpty(crmClientResourcePO)){
			Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Integer clientNature = crmClientResourcePO.getClientNature();//客户性质	1.散客，2.保护客户，3.正式客户
			if(1 != clientType && 3 != clientNature){
				return true;
			}
			Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();//代理商Id
			//查询代理商申请表
			crmAgentApplyParam.setAgentId(agentCanteenId);
			//crmAgentApplyParam.setClientId(clientId);
		}
		
		/*//查询代理商信息
		CrmAgentPO crmAgentPO = new CrmAgentPO();
		crmAgentPO.setId(agentCanteenId);
		crmAgentPO = (CrmAgentPO) iCrmAgentService.getByExample(crmAgentPO);
		if(BeanUtils.isEmpty(crmAgentPO)){
			return true;
		}
		Integer authenticationStatus = crmAgentPO.getAuthenticationStatus();//认证状态	0.申请中，1.认证通过，2.认证失败
		Integer agentStatus = crmAgentPO.getAgentStatus();//代理状态	0.失效，1.有效
		Integer signContractStatus = crmAgentPO.getSignContractStatus();//签约状态	1.正常状态，2.解约状态
		if(1 != authenticationStatus && 1 != agentStatus && 1 != signContractStatus){
			return true;
		}*/
		crmAgentApplyParam.setDelFlag(1);
		crmAgentApplyParam.setCheckStatus(1);//审核状态,0.申请中，1.申请通过，2.申请失败
		List<CrmAgentApplyPO> crmAgentApplyPOs = this.commonQuery(crmAgentApplyParam);
		if(CollectionUtils.isEmpty(crmAgentApplyPOs) || crmAgentApplyPOs.size() != 1){
			return true;
		}
		CrmAgentApplyPO crmAgentApplyPO = crmAgentApplyPOs.get(0);
		modelAndView.addObject("agentApplyPO", crmAgentApplyPO);
		
		return false;
	}

	@Override
	public List<CrmAgentApplyPO> commonQuery(
			CrmAgentApplyParam crmAgentApplyParam) {
		ParamMap paramMap = new ParamMap(crmAgentApplyParam);
		return commonDao.listByParams(CrmAgentApplyPO.class, "CrmAgentApplyPOMapper.commonQuery", paramMap );
	}
	

	/**
	 * 查询审核信息
	 */
	@Override
	public Page<CrmAgentApplyVO> queryPageBasic(CrmAgentApplyParam param){
		ParamMap paramMap = new ParamMap(param);
		Page<CrmAgentApplyPO> queryPage = findPageByParams(CrmAgentApplyPO.class, new Page<CrmAgentApplyPO>(param.getOffset(), param.getLimit()),
				"CrmAgentApplyPOMapper.queryList", paramMap);
		Page<CrmAgentApplyVO> crmAgentApplyVOPage=BeanConvertor.convertBean(queryPage, Page.class);
		if(BeanUtils.isNotEmpty(queryPage)){
			crmAgentApplyVOPage.result=BeanConvertor.copyList(queryPage.result, CrmAgentApplyVO.class);
			List<CrmAgentApplyVO> crmAgentApplyVOs=crmAgentApplyVOPage.result;
			for (CrmAgentApplyVO crmAgentApplyVO : crmAgentApplyVOs) {
				Long areaCode = crmAgentApplyVO.getAreaCode();//负责区域
				if(BeanUtils.isNotEmpty(areaCode)){
					//获取区域名称
					crmAgentApplyVO.setAreaName(StringUtils.join(iBaseAreaService.getAreaNamesByAreaCode(areaCode),"-"));
				}
				Long agentId = crmAgentApplyVO.getAgentId();	// 获取agentId
				CrmAgentPO crmAgentPO = new CrmAgentPO();
				if(CommUtil.parseLong(agentId)>0){
					crmAgentPO = iCrmAgentService.findByAgentId(agentId);
				}
				if(BeanUtils.isNotEmpty(crmAgentPO)){
					crmAgentApplyVO.setName(crmAgentPO.getName());
					String address = crmAgentPO.getAddress();
					if(BeanUtils.isNotEmpty(address)){
						crmAgentApplyVO.setAddress(StringUtils.join(iBaseAreaService.getAreaNamesByAreaCode(areaCode),"")+address);
					}
					//crmAgentApplyVO.setRegisterTime(crmAgentPO.getRegisterTime());
					Long businessId = crmAgentPO.getBusinessId();//业务员ID
					if(BeanUtils.isNotEmpty(businessId)){
						CrmBusinessPO crmBusinessPO = iCrmBusinessService.getById(businessId);
						crmAgentApplyVO.setBusinessName(crmBusinessPO.getName());
						Long teamId = crmBusinessPO.getTeamId();//团队ID
						if(BeanUtils.isNotEmpty(teamId)){
							CrmTeamPO crmTeamPO = iCrmTeamService.getById(teamId);
							if(BeanUtils.isNotEmpty(crmTeamPO)){
								crmAgentApplyVO.setBusinessTeamName(crmTeamPO.getName());
							}
						}
					}
				}
				
			}
		}
		return crmAgentApplyVOPage;	
		}

	@Override
	public Boolean agentExtension(Long agentId, ModelAndView modelAndView) {
		// TODO Auto-generated method stub
		CrmAgentApplyParam crmAgentApplyParam = new CrmAgentApplyParam();
		crmAgentApplyParam.setAgentId(agentId);
		crmAgentApplyParam.setDelFlag(1);
		crmAgentApplyParam.setCheckStatus(1);//审核状态,0.申请中，1.申请通过，2.申请失败
		List<CrmAgentApplyPO> crmAgentApplyPOs = this.commonQuery(crmAgentApplyParam);
		if(CollectionUtils.isEmpty(crmAgentApplyPOs) || crmAgentApplyPOs.size() != 1){
			return true;
		}
		CrmAgentApplyPO crmAgentApplyPO = crmAgentApplyPOs.get(0);
		modelAndView.addObject("agentApplyPO", crmAgentApplyPO);
		CrmAgentPO crmAgentPO = this.iCrmAgentService.findByAgentId(agentId);
		if(BeanUtils.isNotEmpty(crmAgentPO)){
			Long areaCode = crmAgentPO.getAreaCode();
			if(BeanUtils.isNotEmpty(areaCode)){
				BaseAreaPO cityQ =  iBaseAreaService.getCityById(areaCode);
				//获取到市
				if(BeanUtils.isNotEmpty(cityQ)){
					BaseAreaPO cityS = iBaseAreaService.getCityById(cityQ.getParentCode());
					modelAndView.addObject("prov",cityS.getParentCode());
					modelAndView.addObject("city", cityS.getAreaCode());
					modelAndView.addObject("dist", cityQ.getAreaCode());
				}
			}
		}
		
		return false;
	}


	/**
	 * 根据agentId查询对象
	* @Title: findByAgentId 
	* @author : lcl
	* @time : 2017年7月24日 下午4:28:31
	* @return CrmAgentApplyPO    返回类型 
	* @throws
	 */
	@Override
	public CrmAgentApplyPO findByAgentId(Long agentId,Long userId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentId", agentId);
		paramMap.put("createUid", userId);
		List<CrmAgentApplyPO> agentApplyPOs = listByParams(CrmAgentApplyPO.class, "CrmAgentApplyPOMapper.findByAgentId", paramMap);
		if(CollectionUtils.isNotEmpty(agentApplyPOs)){
			return agentApplyPOs.get(0);
		}
		return null;
	}

	/**
	 * 根据参数删除申请信息(delFlag置0)
	 */
	@Override
	public void deleteAgentApplyEntity(CrmAgentApplyParam crmAgentApplyParam) {
		ParamMap paramMap = new ParamMap(crmAgentApplyParam);
		commonDao.execute("CrmAgentApplyPOMapper.deleteAgentApplyEntity", paramMap );
		
	}

	/**
	 * 根据代理商id查询对象
	* @Title: findByAgentId 
	* @author : lcl
	* @time : 2017年7月28日 下午4:37:02
	* @return CrmAgentApplyPO    返回类型 
	* @throws
	 */
	@Override
	public CrmAgentApplyPO findByAgentId(Long agentId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentId", agentId);
		List<CrmAgentApplyPO> agentApplyPOs = listByParams(CrmAgentApplyPO.class, "CrmAgentApplyPOMapper.findByAgentId", paramMap);
		if(CollectionUtils.isNotEmpty(agentApplyPOs)){
			return agentApplyPOs.get(0);
		}
		
		return null;
	}
}

