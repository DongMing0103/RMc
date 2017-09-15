package com.hd.kzscrm.service.serviceimpl.canteen;

import java.math.BigDecimal;
//crmcanteenbaseinfo
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.enums.agent.CrmAgentEnum.CrmAgentAgentStatus;
import com.hd.kzscrm.common.enums.agent.CrmAgentEnum.CrmAgentSignContractStatus;
import com.hd.kzscrm.common.enums.business.CrmBusinessEnum.CrmBusinessJobStatus;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.business.CanteenHealthPicPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.user.CrmAccountParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.CanteenHealthPicService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessCanteenService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenExtInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseCanteenService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.agent.CrmAgentVO;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.kzscrm.service.vo.business.CrmTeamVO;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenBaseInfoVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmCanteenBaseInfo 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmCanteenBaseInfoServiceImpl extends BaseServiceImpl implements
		ICrmCanteenBaseInfoService {

	// 日志服务对象
	protected static final Logger logger = LoggerFactory
			.getLogger(CrmCanteenBaseInfoServiceImpl.class);

	@Resource
	private SqlSessionTemplate sqlSession;
	/**
	 * 代理商
	 */
	@Resource
	private ICrmAgentService iCrmAgentService;
	/**
	 * 团队
	 */
	@Resource
	private ICrmTeamService iCrmTeamService;
	/**
	 * 地区
	 */
	@Resource
	private IBaseAreaService iBaseAreaService;
	/**
	 * 业务员
	 */
	@Resource
	private ICrmBusinessService iCrmBusinessService;
	/**
	 * 企业信息
	 */
	@Resource
	private ICrmEnterpriseService iCrmEnterpriseService;
	/**
	 * 客户资源表
	 */
	@Resource
	private ICrmClientResourceService iCrmClientResourceService;
	/**
	 * 食堂扩展信息
	 */
	@Resource
	private ICrmCanteenExtInfoService iCrmCanteenExtInfoService;
	/**
	 * 企业食堂映射
	 */
	@Resource
	private ICrmEnterpriseCanteenService iCrmEnterpriseCanteenService;

	@Resource
	private ICrmBusinessCanteenService iCrmBusinessCanteenService;
	
	/**
	 * 卫生许可证
	 */
	@Resource
	CanteenHealthPicService canteenHealthPicService;
	/**
	 * 默认构造函数
	 */

	public CrmCanteenBaseInfoServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	/**
	 * 获取编辑食堂需要的信息（企业，食堂扩展，客户资源）
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月21日 下午3:35:03
	 */
	/*@Override
	public Map<String,Object> getCrmCanteenBaseInfo(CrmCanteenBaseInfoParam param){
		
		Map<String,Object> map=new HashMap<>();
		map.putAll(this.getAgentAndTeam(param));//获取代理商和业务员信息
		
		CrmClientResourceParam crmClientResourceParam = param.getCrmClientResourceParam();
		Assert.notNull(crmClientResourceParam,"客户资源数据为空");
		Long crmClientResourceId = crmClientResourceParam.getId();//客户资源ID
		
		CrmClientResourcePO crmClientResourcePO = iCrmClientResourceService.getById(crmClientResourceId);
		Assert.notNull(crmClientResourcePO,"客户资源信息为空");
		CrmClientResourceVO crmClientResourceVO=BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
		Integer clientType = crmClientResourceVO.getClientType();
		Long businessId = crmClientResourceVO.getBusinessId();//业务员ID
		Long agentId = crmClientResourceVO.getAgentId();//代理商ID
		//客户性质	1.散客，2.保护客户，3.正式客户
		Integer clientNature = crmClientResourceVO.getClientNature();
		Long enterpriseId = crmClientResourceVO.getEnterpriseId();//企业ID
		if(BeanUtils.isNotEmpty(businessId)){
			CrmBusinessPO crmBusinessPO = iCrmBusinessService.get(CrmBusinessPO.class, businessId);
			if(BeanUtils.isNotEmpty(crmBusinessPO)){
				Long teamId = crmBusinessPO.getTeamId();
				if(BeanUtils.isEmpty(teamId)){
					crmClientResourceVO.setBusinessId(null);
				}else{
					crmClientResourceVO.setBusinessTeamId(teamId);
				}
				
			}
		}else if(BeanUtils.isNotEmpty(agentId)){
			CrmAgentPO crmAgentPO = iCrmAgentService.get(CrmAgentPO.class, agentId);
			if(BeanUtils.isNotEmpty(crmAgentPO)){
				String parentIds = crmAgentPO.getParentIds();
				String[] parentIdsSplit = parentIds.split(",");
				List<Long> parentIdsTemp=new LinkedList<>();
				for (String parentId : parentIdsSplit) {
					parentIdsTemp.add(Long.valueOf(parentId));
				}
				crmClientResourceVO.setAgentIds(parentIdsTemp);
			}
		}
		
		map.put("crmClientResourceVO", crmClientResourceVO);
		
		//如果是代理商，不允许变更
		Assert.isTrue(clientType!=CrmClientResourceClientType.AGENT.getCode(), "代理商信息不能变更");
		Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//商家ID
		CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = this.getById(agentCanteenId);
		Assert.notNull(crmCanteenBaseInfoPO,"食堂信息为空");
		List<Long> canteenAreaCodes = AreaCodeUtils.getAllOfAreaCode(crmCanteenBaseInfoPO.getAreaCode());
		BaseAreaPO canteenBaseAreaPO = iBaseAreaService.findByAreaCode(crmCanteenBaseInfoPO.getAreaCode());
		String address = crmCanteenBaseInfoPO.getAddress();
		if(BeanUtils.isNotEmpty(address)&&BeanUtils.isNotEmpty(canteenBaseAreaPO)){
			String areaName = canteenBaseAreaPO.getAreaName();
			int areaNameIndex = address.indexOf(areaName);
			if(areaNameIndex!=-1){
				crmCanteenBaseInfoPO.setAddress(address.substring(areaNameIndex+areaName.length()));
			}
		}
		
		map.put("crmCanteenBaseInfoVO", crmCanteenBaseInfoPO);
		map.put("canteenAreaCodes", canteenAreaCodes);
		CrmCanteenExtInfoVO crmCanteenExtInfoVO = iCrmCanteenExtInfoService.findByBaseInfoId(agentCanteenId);
		Assert.notNull(crmCanteenExtInfoVO,"食堂扩展信息为空");
		map.put("crmCanteenExtInfoVO", crmCanteenExtInfoVO);
		
		if(BeanUtils.isNotEmpty(enterpriseId)){
			CrmEnterprisePO crmEnterprisePO = iCrmEnterpriseService.getById(enterpriseId);
			Assert.notNull(crmEnterprisePO,"企业信息为空");
			List<Long> enterpriseAreaCodes = AreaCodeUtils.getAllOfAreaCode(Long.valueOf(crmEnterprisePO.getAreaCode()));
			BaseAreaPO enterpriseBaseAreaPO = iBaseAreaService.findByAreaCode(Long.valueOf(crmEnterprisePO.getAreaCode()));

			String enterproseAddress=crmEnterprisePO.getAddress();
			if(BeanUtils.isNotEmpty(enterproseAddress)&&BeanUtils.isNotEmpty(enterpriseBaseAreaPO)){
				String areaName = enterpriseBaseAreaPO.getAreaName();
				int areaNameIndex = enterproseAddress.indexOf(areaName);
				if(areaNameIndex!=-1){
					crmEnterprisePO.setAddress(enterproseAddress.substring(areaNameIndex+areaName.length()));
				}
			}
			map.put("crmEnterpriseVO", crmEnterprisePO);
			map.put("enterpriseAreaCodes", enterpriseAreaCodes);
		}
		//不是独立食堂，就查找企业信息
		if(clientType!=CrmClientResourceClientType.INDEPENDENT_CANTEEN.getCode()){
			
		}
		return map;
	}*/
	/**
	 * 新增食堂信息（企业，食堂扩展，客户资源）
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月21日 下午3:35:03
	 */
	/*@Override
	public RespModel addOrUpdateCrmCanteenBaseInfo(CrmCanteenBaseInfoParam param) {
		Long id = param.getId();
		Long businessId = param.getBusinessId();//业务员ID
		List<Long> agentIds = param.getAgentIds();//代理商ID
		Long createrUid = param.getCreaterUid();//创建人ID
		Long updaterUid = param.getUpdaterUid();//更新人ID
		String address = param.getAddress();//地址
		Long areaCode = param.getAreaCode();//区域代码
		Integer clientType = param.getClientType();//客户类型
		Date currentDate = new Date();//当前时间
		// 食堂扩展表
		CrmCanteenExtInfoParam crmCanteenExtInfoParam = param
				.getCrmCanteenExtInfoParam();
		// 企业表
		CrmEnterpriseParam crmEnterpriseParam = param.getCrmEnterpriseParam();
		Integer customerAttribute = param.getCustomerAttribute();//客户属性  1 代理商客户 2平台客户
		//客户资源对象
		CrmClientResourceParam crmClientResourceParam=new CrmClientResourceParam();
		Integer clientNature=CrmClientResourceClientNature.SCATTER_CLIENT.getCode();//散客
		if(BeanUtils.isNotEmpty(agentIds)||BeanUtils.isNotEmpty(businessId)){
			clientNature=CrmClientResourceClientNature.PROTECT_CLIENT.getCode();//保护客户
			if(BeanUtils.isNotEmpty(agentIds)){
				crmClientResourceParam.setAgentId(agentIds.get(agentIds.size()-1));//最后一级是最小等级的代理商
			}else if(BeanUtils.isNotEmpty(businessId)){
				crmClientResourceParam.setBusinessId(businessId);
			}
			Calendar currentDateTime=Calendar.getInstance();//当前时间
			currentDateTime.add(Calendar.DAY_OF_MONTH,1);//默认保护期为1个月
			crmClientResourceParam.setProtectDeadline(currentDateTime.getTime());
		}
				
		Long enterpriseId = ServiceUtil
				.genNextIDValue(DatabaseTableNameEnum.crm_enterprise);//企业ID
				
		//如果不为空，就更新
		if(BeanUtils.isNotEmpty(id)){
			//更新食堂
			param.setUpdateTime(currentDate);
			param.setClientNature(clientNature);
			param.setBusinessId(null);//没成为正式客户前，是没有业务员的
			this.updateEntity(BeanConvertor.copy(param, CrmCanteenBaseInfoPO.class));
			
			if(BeanUtils.isEmpty(crmCanteenExtInfoParam.getId())){
				RespModel.failure("食堂扩展信息ID不能为空");
			}
			//更新食堂扩展表
			crmCanteenExtInfoParam.setUpdaterUid(updaterUid);
			crmCanteenExtInfoParam.setUpdateTime(currentDate);
			iCrmCanteenExtInfoService.updateEntity(BeanConvertor.copy(crmCanteenExtInfoParam, CrmCanteenExtInfoPO.class));
			
			if(BeanUtils.isEmpty(crmEnterpriseParam.getId())){
				RespModel.failure("食堂扩展信息ID不能为空");
			}
			//更新企业信息
			if(BeanUtils.isNotEmpty(crmEnterpriseParam)){
				crmEnterpriseParam.setUpdateTime(currentDate);
				crmEnterpriseParam.setUpdateUid(updaterUid);
				iCrmEnterpriseService.updateEntity(BeanConvertor.copy(crmEnterpriseParam, CrmEnterprisePO.class));
			}
			
			
			if(BeanUtils.isEmpty(crmClientResourceParam.getId())){
				RespModel.failure("食堂扩展信息ID不能为空");
			}
			//更新客户资源表
			crmClientResourceParam.setClientNature(clientNature);
			crmClientResourceParam.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
			crmClientResourceParam.setAddress(address);
			crmClientResourceParam.setClientType(clientType);
			crmClientResourceParam.setName(param.getName());
			crmClientResourceParam.setAreaCode(areaCode);
			crmClientResourceParam.setContact(param.getContactRealname());
			crmClientResourceParam.setMobile(param.getContactPhone());
			crmClientResourceParam.setCustomerAttribute(customerAttribute);//客户属性  1 代理商客户 2平台客户
			iCrmClientResourceService.updateEntity(BeanConvertor.copy(crmClientResourceParam, CrmClientResourcePO.class));

			return  RespModel.success("食堂更新成功");
		}
		//食堂信息数据初始化
		param.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
		param.setCreateTime(currentDate);
		param.setUpdateTime(currentDate);
		param.setStatus(CrmCanteenBaseInfoStatus.NO_CHECK.getCode());//未审核
		

		RespModel respModel = RespModel.success("食堂添加成功");
		Long canteenBaseInfoId = ServiceUtil
				.genNextIDValue(DatabaseTableNameEnum.crm_canteen_base_info);
		param.setId(canteenBaseInfoId);
		param.setBusinessId(null);//没成为正式客户前，是没有业务员的

		//食堂信息添加
		crmCanteenExtInfoParam.setBaseInfoId(canteenBaseInfoId);
		crmCanteenExtInfoParam.setAreaCode(areaCode);
		crmCanteenExtInfoParam.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
		crmCanteenExtInfoParam.setCreaterUid(createrUid);
		crmCanteenExtInfoParam.setCreateTime(currentDate);
		crmCanteenExtInfoParam.setUpdaterUid(createrUid);
		crmCanteenExtInfoParam.setUpdateTime(currentDate);
		
		
		//更新客户资源信息
		crmClientResourceParam.setCreateTime(currentDate);
		crmClientResourceParam.setApplyTime(currentDate);
		crmClientResourceParam.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
		crmClientResourceParam.setAddress(address);
		crmClientResourceParam.setAreaCode(areaCode);
		crmClientResourceParam.setClientType(clientType);
		crmClientResourceParam.setClientNature(clientNature);
		param.setClientNature(clientNature);
		crmClientResourceParam.setName(param.getName());
		if (BeanUtils.isNotEmpty(crmEnterpriseParam)){
			crmClientResourceParam.setEnterpriseId(enterpriseId);
		}
		crmClientResourceParam.setContact(param.getContactRealname());
		crmClientResourceParam.setMobile(param.getContactPhone());
		crmClientResourceParam.setAgentCanteenId(canteenBaseInfoId);//食堂ID
		crmClientResourceParam.setTailNum(0l);//默认0次
		crmClientResourceParam.setCustomerAttribute(customerAttribute);//客户属性  1 代理商客户 2平台客户
		crmClientResourceParam.setCheckStatus(CrmClientResourceCheckStatus.APPLYING_PASS.getCode());//审核状态,0.申请中，1.申请通过，2.申请失败
		crmClientResourceParam.setApplyApproveTime(currentDate);
		

		//添加客户资源表数据
		iCrmClientResourceService.add(crmClientResourceParam);
		this.add(param);
		iCrmCanteenExtInfoService.add(crmCanteenExtInfoParam);

		
		if (BeanUtils.isNotEmpty(crmEnterpriseParam)) {
			
			crmEnterpriseParam.setId(enterpriseId);
			crmEnterpriseParam.setCreateUid(createrUid);
			crmEnterpriseParam.setCreateTime(currentDate);
			crmEnterpriseParam.setUpdateTime(currentDate);
			crmEnterpriseParam.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
			crmEnterpriseParam.setUpdateUid(createrUid);
			crmEnterpriseParam.setStatus(CrmEnterpriseStatus.PASSING.getCode());//已通过
			iCrmEnterpriseService.add(crmEnterpriseParam);
			
			CrmEnterpriseCanteenParam crmEnterpriseCanteenParam=new CrmEnterpriseCanteenParam();
			crmEnterpriseCanteenParam.setEnterpriseId(enterpriseId);
			crmEnterpriseCanteenParam.setCanteenId(canteenBaseInfoId);
			crmEnterpriseCanteenParam.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
			iCrmEnterpriseCanteenService.add(crmEnterpriseCanteenParam);
			
		}

		return respModel;
	}*/

	@Override
	public Map<String, Object> getAgentAndTeam(CrmCanteenBaseInfoParam param) {
		CrmAgentParam crmAgentParam = param.getCrmAgentParam();
		CrmAccountParam crmAccountParam = param.getCrmAccountParam();
		Integer userType = crmAccountParam.getUserType();
		
		Long userId = param.getUserId();
		Long businessId = param.getBusinessId();
		param.setUserId(-1L);
		param.setBusinessId(null);

		Map<String, Object> map = new HashMap<>();

		// 获取代理商信息
		CrmAgentParam crmAgentParamTeam = new CrmAgentParam();
		Integer startLevel = 0;
		if (BeanUtils.isNotEmpty(crmAgentParam)) {
			// 代理商获取他的下一级，平台的获取所有
			if (userType == CrmAccountUserType.AGENT.getCode() && BeanUtils.isNotEmpty(crmAgentParam.getLevel())) {
				startLevel = crmAgentParam.getLevel() + 1;
			}
		}
		crmAgentParamTeam.setStartLevel(startLevel);
		crmAgentParamTeam.setAgentStatus(CrmAgentAgentStatus.EFFECTIVE
				.getCode());
		crmAgentParamTeam
				.setSignContractStatus(CrmAgentSignContractStatus.NORMAL
						.getCode());
		// 如果是管理员，就查询所有1级代理商
		// crmAgentParam.setLevel(userType==CrmAccountUserType.ADMIN.getCode()?0:null);
		List<CrmAgentPO> crmAgentPOs = iCrmAgentService
				.findAll(crmAgentParamTeam);
		List<CrmAgentVO> crmAgentVOs = BeanConvertor.copyList(crmAgentPOs,CrmAgentVO.class);
		// 根据代理商等级升序
		Collections.sort(crmAgentVOs, new Comparator<CrmAgentVO>() {

			@Override
			public int compare(CrmAgentVO o1, CrmAgentVO o2) {
				// TODO Auto-generated method stub
				if (o1.getLevel() > o2.getLevel())
					return 1;
				if (o1.getLevel() < o2.getLevel())
					return -1;
				return 0;
			}

		});
		map.put("crmAgentVOs", crmAgentVOs);

		CrmTeamParam crmTeamParam = new CrmTeamParam();
		
		crmTeamParam.setAgentId(userType == CrmAccountUserType.AGENT.getCode() ? crmAgentParam
				.getId() : null);
		crmTeamParam.setType(param.getType());
		List<CrmTeamPO> crmTeamPOs = iCrmTeamService.commonSearch(crmTeamParam);
		List<CrmTeamVO> crmTeamVOs = BeanConvertor.copyList(crmTeamPOs,CrmTeamVO.class);

		// 查询业务员的条件
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setJobStatus(CrmBusinessJobStatus.ON_JOB.getCode());
		// 如果是管理员，就是平台业务员，否则就是代理商业务员
		crmBusinessParam.setType(param.getType());
		// 如果是代理商角色登录，就把当前登录的代理商ID给agentId字段
		crmBusinessParam.setAgentId(userType == CrmAccountUserType.AGENT.getCode() ? crmAgentParam.getId() : null);

		for (Iterator<CrmTeamVO> iterator = crmTeamVOs.iterator(); iterator.hasNext();) {
			CrmTeamVO crmTeamVO = iterator.next();
			crmBusinessParam.setTeamId(crmTeamVO.getId());
			List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.listByParam(crmBusinessParam);
			// 如果为空，就删除团队信息
			if (BeanUtils.isNotEmpty(crmBusinessPOs)) {
				//判断业务员保护客户个数
				Iterator<CrmBusinessPO> crsIter =  crmBusinessPOs.iterator();
				while(crsIter.hasNext()){
					CrmBusinessPO cPo = crsIter.next();
					/*CrmBusinessCanteenParam cBCparam = new CrmBusinessCanteenParam();
					cBCparam.setBusinessId(cPo.getId());
					Integer count = iCrmBusinessCanteenService.commonCount(cBCparam);
					*/
					CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
					crmClientResourceParam.setBusinessId(cPo.getId());
					crmClientResourceParam.setDelFlag(1);
					crmClientResourceParam.setClientNature(2);//客户性质 1.散客，2.保护客户，3.正式客户
					
					Integer protectClientNum = iCrmClientResourceService.countClientNum(crmClientResourceParam);
					
					businessId = CommUtil.parseLong(businessId);
					map.put("isRemove", "false");
					if(businessId>0 && businessId.equals(cPo.getId())){
						
					}else if(protectClientNum>=10){
						crsIter.remove();
						if(cPo.getUserId().equals(CommUtil.parseLong(userId))){
							map.put("isRemove", "true");
						}
					}
				}
				crmTeamVO.setCrmBusinessVOs(BeanConvertor.copyList(crmBusinessPOs, CrmBusinessVO.class));
			} else {
				iterator.remove();
			}
		}
		map.put("crmTeamVOs", crmTeamVOs);
		return map;
	}

	/**
	 * 查询
	 */
	@Override
	public Page<CrmCanteenBaseInfoVO> queryPage(CrmCanteenBaseInfoParam param) {
		ParamMap paramMap = new ParamMap(param);
		/*
		 * paramMap.put("CrmCanteenBaseInfoName",param.getCrmCanteenBaseInfoName(
		 * )); paramMap.put("status",param.getStatus()); String sortStr =
		 * param.getSort(); if(param.getSortConditionMap().size() == 0){
		 * if(StringUtil.isEmpty(sortStr)){ paramMap.addOrder("sort_no",asc); }
		 * }
		 */
		Page<CrmCanteenBaseInfoPO> crmcanteenbaseinfoList = findPageByParams(
				CrmCanteenBaseInfoPO.class, new Page<CrmCanteenBaseInfoPO>(
						param.getOffset(), param.getLimit()),
				"CrmCanteenBaseInfoPOMapper.queryPage", paramMap);
		List<CrmCanteenBaseInfoVO> rows = new ArrayList<CrmCanteenBaseInfoVO>();
		int total = 0;
		if (crmcanteenbaseinfoList.result != null) {
			rows = BeanConvertor.copyList(crmcanteenbaseinfoList.result,
					CrmCanteenBaseInfoVO.class);
			total = crmcanteenbaseinfoList.getTotalResult();
		}
		Page<CrmCanteenBaseInfoVO> pageResult = new Page<CrmCanteenBaseInfoVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;

	}

	/**
	 * 根据主键查询详情
	 * 
	 * @param param
	 * @return
	 */

	@Override
	public List<CrmCanteenBaseInfoPO> listByParam(
			CrmCanteenBaseInfoParam crmcanteenbaseinfoParam) {
		ParamMap paramMap = new ParamMap(crmcanteenbaseinfoParam);
		return commonDao.listByParams(CrmCanteenBaseInfoPO.class,
				"CrmCanteenBaseInfoPOMapper.queryList", paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmCanteenBaseInfoPO po =
		 * this.get(CrmCanteenBaseInfoPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmCanteenBaseInfoPOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmCanteenBaseInfoPO> listPo = new ArrayList<CrmCanteenBaseInfoPO>(
				idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmCanteenBaseInfoPO po = this.get(CrmCanteenBaseInfoPO.class,
					idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmCanteenBaseInfoPO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmCanteenBaseInfoParam param) {
		Long id = param.getId();
		List<String> canteenHealthPicList = param.getCanteenHealthPicList();
		if (BeanUtils.isEmpty(id)) {
			param.setId(ServiceUtil
					.genNextIDValue(DatabaseTableNameEnum.crm_canteen_base_info));
			
		}else{
			//删除原卫生许可证
			canteenHealthPicService.deleteByCrmCanteenId(id);
		}
		
		//保存卫生许可证
		CanteenHealthPicPO canteenHealthPicPO = null;
		Date currentDate = new Date();
		for (String canteenHealthPic : canteenHealthPicList) {
			canteenHealthPicPO = new CanteenHealthPicPO();
			canteenHealthPicPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.canteen_health_pic));
			canteenHealthPicPO.setCrmCanteenId(id);
			canteenHealthPicPO.setBusinessHealthPic(canteenHealthPic);
			canteenHealthPicPO.setDelFlag(1);
			canteenHealthPicPO.setCreateTime(currentDate);
			canteenHealthPicService.save(canteenHealthPicPO);
			}
		CrmCanteenBaseInfoPO crmcanteenbaseinfoPO = BeanConvertor.copy(param,
				CrmCanteenBaseInfoPO.class);
		this.save(crmcanteenbaseinfoPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmCanteenBaseInfoPO po) {
		po.setId(SystemCacheHelper
				.genNextIDValue(DatabaseTableNameEnum.crm_canteen_base_info));
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmCanteenBaseInfoPO po) {
		this.update(po);
	}

	@Override
	public CrmCanteenBaseInfoPO getById(Long id) {
		Assert.notNull(id,"商家ID不能为空");
		return commonDao.get(CrmCanteenBaseInfoPO.class, id);
	}

	/**
	 * 根据业务员id查询食堂信息
	 */
	@Override
	public CrmCanteenBaseInfoPO findByBusinessId(Long businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId);
		List<CrmCanteenBaseInfoPO> ccList = commonDao.listByParams(
				CrmCanteenBaseInfoPO.class,
				"CrmCanteenBaseInfoPOMapper.findByBusinessId", paramMap);
		if (CollectionUtils.isNotEmpty(ccList)) {
			return ccList.get(0);
		}
		return null;
	}

	/**
	 * 查询食堂基本信息
	 */
	@Override
	public List<CrmCanteenBaseInfoPO> commonQuery(
			CrmCanteenBaseInfoParam crmCanteenBaseInfoParam) {
		ParamMap paramMap = new ParamMap(crmCanteenBaseInfoParam);
		return listByParams(CrmCanteenBaseInfoPO.class,
				"CrmCanteenBaseInfoPOMapper.commonQuery", paramMap);
	}

	/**
	 * 根据canteenId查询对象
	 */
	@Override
	public CrmCanteenBaseInfoPO findByCanteenId(Long canteenId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("canteenId", canteenId);
		List<CrmCanteenBaseInfoPO> cInfoPOs = listByParams(
				CrmCanteenBaseInfoPO.class,
				"CrmCanteenBaseInfoPOMapper.findByCanteenId", paramMap);
		if (CollectionUtils.isNotEmpty(cInfoPOs)) {
			return cInfoPOs.get(0);
		}
		return null;
	}

	/**
	 * 根据商家名称 模糊查询集合
	 * 
	 * @param condition
	 * @return
	 */
	@Override
	public List<CrmCanteenBaseInfoPO> findLikeByName(String condition,Long  businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("condition", condition);
		paramMap.put("businessId", businessId);
		List<CrmCanteenBaseInfoPO> canteenBaseInfoPOs = listByParams(
				CrmCanteenBaseInfoPO.class,
				"CrmCanteenBaseInfoPOMapper.findLikeByName", paramMap);
		if (BeanUtils.isNotEmpty(canteenBaseInfoPOs)) {
			return canteenBaseInfoPOs;
		}
		return null;
	}

	/**
	 * 根据businessID统计食堂数量
	 */
	@Override
	public BigDecimal countCanteen(Long businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId);
		BigDecimal total = sqlSession.selectOne(
				"CrmCanteenBaseInfoPOMapper.countCanteen", paramMap);
		if (BeanUtils.isEmpty(total)) {
			return BigDecimal.ZERO;
		}
		return total;
	}

	/**
	 * 查询食堂名称
	 */
	@Override
	public List<CrmCanteenBaseInfoPO> getCanteenByIds() {
		ParamMap paramMap = new ParamMap();
		paramMap.put("del_flag", 1);
		List<CrmCanteenBaseInfoPO> crmCanteenBaseInfoPOs = listByParams(
				CrmCanteenBaseInfoPO.class,
				"CrmCanteenBaseInfoPOMapper.getCanteenName", paramMap);

		return crmCanteenBaseInfoPOs;
	}

	@Override
	public CrmCanteenBaseInfoPO findByUserId(Long userId) {
		ParamMap paramMap = new ParamMap();
	    paramMap.put("userId", userId);
	    List<CrmCanteenBaseInfoPO> canteenBaseInfoPOs = listByParams(CrmCanteenBaseInfoPO.class,
	    		                            "CrmCanteenBaseInfoPOMapper.findByUserId", paramMap);
	    if (BeanUtils.isNotEmpty(canteenBaseInfoPOs)) {
			return canteenBaseInfoPOs.get(0);
		}
		return null;
	}

	/**
	 * 根据businessId查询canteenIds
	 */
	@Override
	public List<CrmCanteenBaseInfoPO> findByBusinessIds(Long businessId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("businessId", businessId);
		List<CrmCanteenBaseInfoPO> canteenBaseInfoPOs = listByParams(CrmCanteenBaseInfoPO.class, "CrmCanteenBaseInfoPOMapper.findByBusinessIds",paramMap);
				//listByParams(CrmCanteenBaseInfoPO.class, "CrmCanteenBaseInfoPOMapper.findByBusinessId", paramMap);
		
		return canteenBaseInfoPOs;
	}

	/**
	 * 保护客户更新为散客
	 */
	@Override
	public int changeProtectedClientToIndividualTraveler(
			CrmCanteenBaseInfoParam crmCanteenBaseInfoParam) {
		ParamMap paramMap = new ParamMap(crmCanteenBaseInfoParam);
		return commonDao.execute("CrmCanteenBaseInfoPOMapper.changeProtectedClientToIndividualTraveler", paramMap);
	}

}
