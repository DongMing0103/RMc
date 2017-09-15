package com.hd.kzscrm.service.serviceimpl.agent;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
//crmagent
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
import com.hd.kzscrm.common.util.AreaCodeUtils;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.MapUtils;
import com.hd.kzscrm.dao.entity.agent.CrmAgentApplyPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.agent.CrmWorkTargetPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.agent.CrmWorkTargetParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentAreaService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmWorkTargetService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.agent.CrmAgentVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmAgent 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmAgentServiceImpl extends BaseServiceImpl implements ICrmAgentService {
	
	//业务员
	@Resource
	private ICrmBusinessService iCrmBusinessService;
	@Resource
	private ICrmTeamService iCrmTeamService;
	//地理信息表
	@Resource
	private IBaseAreaService iBaseAreaService;
	@Resource
    private SqlSessionTemplate sqlSession;
	//用户登录账号
	@Autowired
	private ICrmUserService crmUserService;
	//工作目标
	@Autowired
	private ICrmWorkTargetService  crmWorkTargetService;
	//代理商区域表
	@Autowired
	private ICrmAgentAreaService  agentAreaService;
	//代理商申请表
	@Autowired
	private ICrmAgentApplyService  agentApplyService;
	//客户资源表
	@Autowired
	private ICrmClientResourceService  clientResourceService;
	// 日志服务对象
	protected static final Logger logger = LoggerFactory.getLogger(CrmAgentServiceImpl.class);

	/**
	 * 默认构造函数
	 */

	public CrmAgentServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	@Override
	public CrmAgentVO findById(Long id){
		CrmAgentParam crmAgentParam =new CrmAgentParam();
		crmAgentParam.setId(id);
		List<CrmAgentPO> crmAgentPOs = this.listByParam(crmAgentParam);
		if(BeanUtils.isEmpty(crmAgentPOs)){
			return null;
		}
		int size = crmAgentPOs.size();
		CrmAgentPO crmAgentPO = crmAgentPOs.get(0);
		CrmAgentVO crmAgentVO = BeanConvertor.copy(crmAgentPO, CrmAgentVO.class);
		Assert.isTrue(size==1, "实际数据量为"+size);
		Long businessId = crmAgentVO.getBusinessId();//业务员ID
		Long areaCode = crmAgentVO.getAreaCode();//地理区域
		List<Long> agentAreaCodes = AreaCodeUtils.getAllOfAreaCode(areaCode);
		for (int i=agentAreaCodes.size();i<5;i++) {
			agentAreaCodes.add(null);
		}
		crmAgentVO.setAgentAreaCodes(agentAreaCodes);
		if(BeanUtils.isNotEmpty(businessId)){
			CrmBusinessPO crmBusinessPO = iCrmBusinessService.getById(businessId);
			crmAgentVO.setBusinessName(crmBusinessPO.getName());
			Long teamId = crmBusinessPO.getTeamId();//团队ID
			if(BeanUtils.isNotEmpty(teamId)){
				CrmTeamPO crmTeamPO = iCrmTeamService.getById(teamId);
				if(BeanUtils.isNotEmpty(crmTeamPO)){
					crmAgentVO.setBusinessTeamName(crmTeamPO.getName());
				}
			}
		}
		return crmAgentVO;
	}
	/**
	 * 查询代理商信息
	 */
	@Override
	public Page<CrmAgentVO> queryPageBasic(CrmAgentParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmAgentPO> queryPage = findPageByParams(CrmAgentPO.class, new Page<CrmAgentPO>(param.getOffset(), param.getLimit()),
				"CrmAgentPOMapper.queryList", paramMap);
		Page<CrmAgentVO> crmAgentVOPage=BeanConvertor.convertBean(queryPage, Page.class);
		if(BeanUtils.isNotEmpty(queryPage)){
			crmAgentVOPage.result=BeanConvertor.copyList(queryPage.result, CrmAgentVO.class);
			List<CrmAgentVO> crmAgentVOs=crmAgentVOPage.result;
			for (CrmAgentVO crmAgentVO : crmAgentVOs) {
				Long areaCode = crmAgentVO.getAreaCode();//负责区域
				if(BeanUtils.isNotEmpty(areaCode)){
					List<Long> areaCodes = AreaCodeUtils.getAllOfAreaCode(areaCode);
					//获取区域名称
					List<BaseAreaPO> baseAreaPOs = iBaseAreaService.findByAreaCodes(areaCodes);
					List<String> areaNames=new LinkedList<>();
					for (BaseAreaPO baseAreaPO : baseAreaPOs) {
						areaNames.add(baseAreaPO.getAreaName());
					}
					crmAgentVO.setAreaName(StringUtils.join(areaNames,"-"));
				}
				Long businessId = crmAgentVO.getBusinessId();//业务员ID
				if(BeanUtils.isNotEmpty(businessId)){
					CrmBusinessPO crmBusinessPO = iCrmBusinessService.getById(businessId);
					crmAgentVO.setBusinessName(crmBusinessPO.getName());
					Long teamId = crmBusinessPO.getTeamId();//团队ID
					if(BeanUtils.isNotEmpty(teamId)){
						CrmTeamPO crmTeamPO = iCrmTeamService.getById(teamId);
						if(BeanUtils.isNotEmpty(crmTeamPO)){
							crmAgentVO.setBusinessTeamName(crmTeamPO.getName());
						}
					}
				}
			}
		}
		return crmAgentVOPage;
	}

	/**
	 * 查询
	 */
	@SuppressWarnings(value = "all")
	@Override
	public Page<CrmAgentVO> queryPage(CrmAgentParam param) {

		// 查省份时 转换成 areaCode

		if (BeanUtils.isEmpty(param.getAreaCode()) && BeanUtils.isNotEmpty(param.getProvCode())) {
			BaseAreaPO baseAreaPO = new BaseAreaPO();
			baseAreaPO.setParentCode(param.getProvCode().longValue());// 设置父类的code
			List<BaseAreaPO> baseAreaPOs = iBaseAreaService.getCityByParentCode(baseAreaPO);
			if (BeanUtils.isNotEmpty(baseAreaPOs)) {
				List<Integer> areaCodes = new ArrayList<Integer>();
				areaCodes.add(param.getProvCode());
				for (BaseAreaPO b : baseAreaPOs) {
					BaseAreaPO baseAreaPO2 = new BaseAreaPO();
					baseAreaPO2.setParentCode(b.getAreaCode());
					List<BaseAreaPO> baList = iBaseAreaService.getCityByParentCode(baseAreaPO2);
					if (CollectionUtils.isNotEmpty(baList)) {
						for (BaseAreaPO b1 : baList) {
							areaCodes.add(b1.getAreaCode().intValue());
						}
					}
				}
				param.setAreaCodes(areaCodes);// 查询省下面的areaCode
			}
		}
		// 判断是否是市区
		if (BeanUtils.isEmpty(param.getAreaCode()) && BeanUtils.isNotEmpty(param.getCityCode())) {
			BaseAreaPO baseAreaPO = new BaseAreaPO();
			baseAreaPO.setParentCode(param.getCityCode().longValue());
			List<BaseAreaPO> baList = iBaseAreaService.getCityByParentCode(baseAreaPO);
			List<Integer> areaCodes = new ArrayList<Integer>();
			areaCodes.add(param.getCityCode());
			if (CollectionUtils.isNotEmpty(baList)) {
				for (BaseAreaPO b : baList) {
					areaCodes.add(b.getAreaCode().intValue());
				}
			}
			param.setAreaCodes(areaCodes);
		}
		
		ParamMap paramMap =MapUtils.mapNotEmptyValue(new ParamMap(param));
		Page<CrmAgentPO> crmagentList = findPageByParams(CrmAgentPO.class,new Page<CrmAgentPO>(param.getOffset(), param.getLimit()),"CrmAgentPOMapper.queryPage", paramMap);
		List<CrmAgentVO> rows = new ArrayList<CrmAgentVO>();
		int total = 0;
		if (crmagentList.result != null) {
			for (int i = 0; i < crmagentList.result.size(); i++) {
				CrmAgentPO crmAgentPO = crmagentList.result.get(i);
				// 根据业务员id查询对象
				CrmAgentVO agentVO = BeanConvertor.convertBean(crmAgentPO,
						CrmAgentVO.class);
				if (BeanUtils.isNotEmpty(agentVO)) {
//					if (Integer.valueOf(1).equals(agentVO.getAgentNature())) {
//						agentVO.setAgentNatureName("企业法人");
//					} else if (Integer.valueOf(2).equals(
//							agentVO.getAgentNature())) {
//						agentVO.setAgentNatureName("非企业法人");
						if (CommUtil.parseInteger(agentVO.getAgentNature()).equals(1)) {
							agentVO.setAgentNatureName("企业法人");
						} else if (CommUtil.parseInteger(agentVO.getAgentNature()).equals(2)) {
							agentVO.setAgentNatureName("非企业法人");
						} else {
							agentVO.setAgentNatureName("暂无数据");
						}
						
	/*					if (BeanUtils.isNotEmpty(agentVO.getAgentStatus())) {
							if (agentVO.getAgentStatus().equals(0)) {
								agentVO.setAgentStatusName("失效");
							} else if (agentVO.getAgentStatus().equals(1)) {
								agentVO.setAgentStatusName("有效");
							} else {
								agentVO.setAgentNatureName("暂无数据");
							}*/

							if (BeanUtils.isNotEmpty(agentVO.getAgentStatus())) {
								if (agentVO.getAgentStatus().equals(0)) {
									agentVO.setAgentStatusName("失效");
								} else if (agentVO.getAgentStatus().equals(1)) {
									agentVO.setAgentStatusName("有效");
								} else {
									agentVO.setAgentStatusName("暂无数据");
								}
							}
							if (crmAgentPO.getBusinessId() != null && param.getUserType() !=null) {
								// 查询的是业务员
								CrmAgentParam paramm = new  CrmAgentParam();
								paramm.setBusinessId(crmAgentPO.getBusinessId());
								paramm.setUserType(param.getUserType());
								CrmBusinessPO cBusinessPO = iCrmBusinessService.findByParamm(paramm);
								if (BeanUtils.isNotEmpty(cBusinessPO)) {
									agentVO.setBusinessName(cBusinessPO.getName());
								}
							}
							// 区域查询 待修改 是否要
							Long areaCode = crmAgentPO.getAreaCode();

							if (BeanUtils.isNotEmpty(areaCode)) {
								String areaName = iBaseAreaService.getAreaName(areaCode);
								if(BeanUtils.isNotEmpty(areaName)){
									if(areaName.indexOf("/")>-1){
										areaName = areaName.replaceAll("/", "");
									}
									agentVO.setAreaName(areaName);
								}else{
									BaseAreaPO baseAreaPO = iBaseAreaService.findByAreaCode(areaCode);
									if (BeanUtils.isNotEmpty(baseAreaPO)) {
										agentVO.setAreaName(baseAreaPO.getAreaName());
									}
								}

								// 代理商区域代理表
								Long agentId = crmAgentPO.getId();
								if(BeanUtils.isNotEmpty(agentId)){
									CrmAgentApplyPO cAgentApplyPO = agentApplyService.findByAgentId(agentId);
									if(BeanUtils.isNotEmpty(cAgentApplyPO)){
										agentVO.setSignTime(cAgentApplyPO.getCheckTime());//签约时间为审核通过的时间，表达方式是2017-6-25
										agentVO.setCooperationEndTime(cAgentApplyPO.getCooperationEndTime());
									}
								}
								
/*								if (BeanUtils.isNotEmpty(agentId)) {
									// 根据代理商id查询代理商区域表
									CrmAgentAreaPO areaPO = agentAreaService
											.findByAgentId(agentId);
									if (BeanUtils.isNotEmpty(areaPO)) {
										agentVO.setSignTime(areaPO
												.getCreateTime());// 区域建立时的时间
																	// 就是签约时间
									}
								}*/
							}
							
							if(BeanUtils.isEmpty(agentVO.getContactRealname())){
								agentVO.setContactRealname(agentVO.getPrincipalName());
								agentVO.setContactPhone(agentVO.getMobilephoe());
							}
							
						}
						rows.add(agentVO);
					}
				}
	
		total = crmagentList.getTotalResult();
		Page<CrmAgentVO> pageResult = new Page<CrmAgentVO>();
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
	public List<CrmAgentPO> listByParam(CrmAgentParam crmagentParam) {
		ParamMap paramMap = new ParamMap(crmagentParam);
		return commonDao.listByParams(CrmAgentPO.class, "CrmAgentPOMapper.queryList", paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		/*
		 * Long id = Long.parseLong(ids); CrmAgentPO po =
		 * this.get(CrmAgentPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmAgentPOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmAgentPO> listPo = new ArrayList<CrmAgentPO>(idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmAgentPO po = this.get(CrmAgentPO.class, idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmAgentPO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmAgentParam param) {
		CrmAgentPO crmagentPO = BeanConvertor.copy(param, CrmAgentPO.class);
		this.save(crmagentPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmAgentPO po) {
		Long id = po.getId();
		if(BeanUtils.isEmpty(id)||CommUtil.parseLong(id)<=0){
			po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_agent));
		}
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmAgentPO po) {
		this.update(po);
	}

	@Override
	public CrmAgentPO getById(Long id) {
		// TODO Auto-generated method stub
		return commonDao.get(CrmAgentPO.class, id);
	}
	
	/**
	 * 根据用户ID获取代理商信息
	 * @author 黄霄仪
	 * @date 2017年6月1日 下午1:56:23
	 */
	@Override
	public CrmAgentPO getByUserId(Long userId){
		ParamMap paramMap=new ParamMap();
		paramMap.put("userId",userId);
		List<CrmAgentPO> listByParams = commonDao.listByParams(CrmAgentPO.class, "CrmAgentPOMapper.queryList", paramMap);
		if(BeanUtils.isNotEmptyUniqueList(listByParams)){
			return listByParams.get(0);
		}
		return null;
	}
	/**
	 * 根据条件count(0)
	 */
	@Override
	public Integer commonCount(CrmAgentParam crmAgentParam) {
		ParamMap paramMap = new ParamMap(crmAgentParam);
		Integer num = sqlSession.selectOne("CrmAgentPOMapper.commonCount", paramMap);
		if(BeanUtils.isEmpty(num)){
			return 0;
		}
		return num;
	}

	@Override
	public List<CrmAgentPO> findAll(CrmAgentParam param) {
		ParamMap paramMap = new ParamMap(param);
		List<CrmAgentPO> crmAgentPOs = listByParams(CrmAgentPO.class, "CrmAgentPOMapper.queryList", paramMap);
     	if (crmAgentPOs != null) {
     		return crmAgentPOs;
		}	
		return null;
	}

	@Override
	public CrmAgentPO findByAgentId(Long agentId) {
		Assert.notNull(agentId, "代理商ID不能为空");
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentId", agentId);
		List<CrmAgentPO> caList = commonDao.listByParams(CrmAgentPO.class, "CrmAgentPOMapper.findByAgent", paramMap);
		if(BeanUtils.isEmpty(caList)){
			return null;
		}
		Assert.isTrue(caList.size()==1,"不能大于1个");
		return caList.get(0);
	}

	/**
	 * 根据 代理商名称查询 对象集合
	 * @param agentName
	 * @return
	 */
	@Override
	public List<CrmAgentPO> findByAgentName(String agentName) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentName", agentName);
		List<CrmAgentPO> agentPOs = listByParams(CrmAgentPO.class, "CrmAgentPOMapper.findByAgentName", paramMap);
		if(agentPOs!= null){
			return agentPOs;
		}
		return null;
	}
	/**
	 * 根据条件查询代理商信息
	 */
	@Override
	public List<CrmAgentPO> commonQuery(CrmAgentParam crmAgentParam) {
		ParamMap paramMap = new ParamMap(crmAgentParam);
		return listByParams(CrmAgentPO.class, "CrmAgentPOMapper.commonQuery", paramMap);
	}

	@Override
	public Page<CrmAgentVO> findPageSelect(CrmAgentParam param) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查询该代理商的子孙代理商
	 */
	@Override
	public List<CrmAgentPO> getChildAgent(CrmAgentParam agentParam) {
		ParamMap paramMap = new ParamMap(agentParam);
		return listByParams(CrmAgentPO.class, "CrmAgentPOMapper.getChildAgent", paramMap);
	}

	/**
	 * 根据businessId统计代理商数量
	 */
	@Override
	public BigDecimal countAgentNum(CrmAgentParam crmAgentParam) {
		ParamMap paramMap = new ParamMap(crmAgentParam);
		BigDecimal total = sqlSession.selectOne("CrmAgentPOMapper.countAgentNum", paramMap);
		if (BeanUtils.isEmpty(total)) {
			return total.ZERO;
		}
		return total;
	}

	/**
	 * 解约和续约 状态的修改
	* @Title: updateStatus 
	* @author : lcl
	* @time : 2017年6月9日 下午4:20:18
	* @return CommResponse    返回类型 
	* @throws
	 */
	@Override
	public RespModel updateStatus(List<Long> agentIds) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentIds",agentIds);
		//表示解约
		for(int i=0;i<agentIds.size();i++){
			CrmAgentPO crmAgentPO = findByAgentId(agentIds.get(i));
			if(BeanUtils.isEmpty(crmAgentPO)){
				continue;
			}
			if(BeanUtils.isEmpty(crmAgentPO.getUserId())){
				continue;
			}
			if(BeanUtils.isNotEmpty(crmAgentPO) && BeanUtils.isNotEmpty(crmAgentPO.getUserId())){
				try {
					//根据 userId修改 用户状态  只修改状态 
					crmUserService.deleteById(crmAgentPO.getUserId());
					//修改 代理商的状态
					execute("CrmAgentPOMapper.updateStatusAndDelflg", paramMap);
					return RespModel.success("解约成功");
				} catch (BizException e) {
					e.printStackTrace();
					return RespModel.success(e.getMessage());
				}
			}else{//表示续约
				
			}
			
		}
		return RespModel.failure("操作失败");
	}

	/**
	 * 编辑 新增的渲染  代理商
	* @Title: addAgent 
	* @author : lcl
	* @time : 2017年6月13日 上午9:27:43
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@Override
	public Boolean addAgent(CrmAgentParam param,ModelAndView model) {
		ParamMap paramMap = new  ParamMap();
		//ModelAndView model = new ModelAndView("/index/add_agent");
		Integer userType = param.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Long userId = param.getUserId();
		CrmTeamParam crmTeamParam = new CrmTeamParam();
		CrmBusinessPO crmBusinessPO = null;
		CrmBusinessParam businessParam = new CrmBusinessParam();
	    if(1 == userType){//1 管理员
			crmTeamParam.setType(1);
			
			businessParam.setType(1);//1.平台业务员，2.代理商业务员
			businessParam.setBusinessId(param.getBusinessId());
		}else if(2 == userType){//2.代理商
			
			CrmAgentParam crmAgentParam = new CrmAgentParam();
			crmAgentParam.setUserId(userId);
			crmAgentParam.setDelFlag(1);
			List<CrmAgentPO> crmAgentPOs = this.commonQuery(crmAgentParam);
			CrmAgentPO crmAgentPO = crmAgentPOs.get(0);
			
			Long agentId = crmAgentPO.getId();
			crmTeamParam.setType(2);
			crmTeamParam.setAgentId(agentId);
			
			businessParam.setType(2);
			businessParam.setAgentId(agentId);
			
		}else if(3 == userType || 4 == userType){//3.业务员，4.业务经理
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setUserId(userId);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
			crmBusinessPO = crmBusinessPOs.get(0);
			Integer type = crmBusinessPO.getType();//1.平台业务员，2.代理商业务员
			if(1 == type){
				crmTeamParam.setType(1);
				
				businessParam.setType(1);//1.平台业务员，2.代理商业务员
			}else{
				Long agentId = crmBusinessPO.getAgentId();
				
				crmTeamParam.setType(2);
				crmTeamParam.setAgentId(agentId);
				
				businessParam.setType(2);
				businessParam.setAgentId(agentId);
			}
		}else{
			return true;
		}
		
		List<CrmTeamPO> cTeamPOs = this.iCrmTeamService.commonQuery(crmTeamParam);//查询团队信息
		List<CrmBusinessPO> CrmBusinessPOs = iCrmBusinessService.commonQuery(businessParam);//查询业务员信息
		Map<Long,Integer> businessIdWithProtectedNum = new HashMap<Long, Integer>();//业务员id和其保护客户数量
		for (CrmBusinessPO businessPO : CrmBusinessPOs) {
			//统计业务员的保护客户数量
			Long businessId = businessPO.getId();
			businessIdWithProtectedNum.put(businessId, null);
		}
		//查询保护客户数量
		CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
		crmClientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdWithProtectedNum.keySet()));
		crmClientResourceParam.setDelFlag(1);
		crmClientResourceParam.setClientNature(2);//客户性质	1.散客，2.保护客户，3.正式客户
		//businessIdWithProtectedNum.clear();
		Map<Long,Integer> businessIdWithProtectedNum2 = clientResourceService.countProtectedClientNum(crmClientResourceParam);//统计业务员的保护客户数量
		if(BeanUtils.isNotEmpty(businessIdWithProtectedNum2)){
			for (Long bId : businessIdWithProtectedNum2.keySet()) {
				Integer num = CommUtil.parseInteger(businessIdWithProtectedNum2.get(bId));//得到每个key多对用value的值
				businessIdWithProtectedNum.put(bId, num);
	        }
		}
		

		try {		
			//客户资源表id
			Long id  = param.getId();
			//查询全部团队
			//CrmTeamParam crmTeamParam = new CrmTeamParam();
			//List<CrmTeamPO> cTeamPOs = this.iCrmTeamService.commonQuery(crmTeamParam);
			//List<CrmBusinessPO> CrmBusinessPOs = iCrmBusinessService.commonQuery(businessParam);//查询业务员信息
			Long businessId = -1L;
			if(BeanUtils.isNotEmpty(id)){
			//	CrmClientResourcePO cResourcePO = clientResourceService.findById(id);
				//if(BeanUtils.isNotEmpty(cResourcePO)&& BeanUtils.isNotEmpty(cResourcePO.getAgentCanteenId())){

				//	paramMap.put("id",cResourcePO.getAgentCanteenId());
					paramMap.put("id", id);
					List<CrmAgentPO> agentPOs = listByParams(CrmAgentPO.class, "CrmAgentPOMapper.findById", paramMap);
					
					CrmAgentPO agentPO = new CrmAgentPO();
					if(BeanUtils.isNotEmpty(agentPOs)){
						agentPO = agentPOs.get(0);
						//获取到区 
						if(BeanUtils.isNotEmpty(agentPO.getAreaCode())){
							//获取到区
							BaseAreaPO cityQ =  iBaseAreaService.getCityById(agentPO.getAreaCode());
							//获取到市
							if(BeanUtils.isNotEmpty(cityQ)){
								BaseAreaPO cityS = iBaseAreaService.getCityById(cityQ.getParentCode());
								model.addObject("prov",cityS.getParentCode());
								model.addObject("city", cityS.getAreaCode());
								model.addObject("dist", cityQ.getAreaCode());
							}
						}
						if(BeanUtils.isEmpty(agentPO.getContactRealname())){
							agentPO.setContactRealname(agentPO.getPrincipalName());
							agentPO.setContactPhone(agentPO.getMobilephoe());
						}
					//	model.addObject("cResourcePO", cResourcePO);
						model.addObject("cAgentPO", agentPO);
						
						
						businessId = agentPOs.get(0).getBusinessId();
						if(BeanUtils.isNotEmpty(businessId)){
							CrmBusinessPO businessPO = this.iCrmBusinessService.findByBusinessId(businessId);
							if(BeanUtils.isNotEmpty(businessPO)){
								param.setBusinessName(businessPO.getName());//业务员
								model.addObject("businessName", businessPO.getName());
							}
						}
					
				}
				
				//model.addObject("cTeamPOs", cTeamPOs);
				}
				
			
			//根据 代理商id查询 
			Map<Integer,String>  pMap = new  HashMap<Integer, String>();
			pMap.put(0, "请选择");
			pMap.put(1, "企业法人");
			pMap.put(2, "非企业法人");
			model.addObject("pMap",pMap);
			
			
			
			//筛选业务员
			List<CrmBusinessPO> businessPOs = new ArrayList<CrmBusinessPO>();
			for (CrmBusinessPO businessPO : CrmBusinessPOs) {
				//统计业务员的保护客户数量
				Long bId = businessPO.getId();
				if(businessIdWithProtectedNum.containsKey(bId)){
					Integer protectedNum = CommUtil.parseInteger(businessIdWithProtectedNum.get(bId));
					if(CommUtil.parseLong(businessId)>0 && businessId.equals(bId)){
						businessPOs.add(businessPO);
					}else if(protectedNum < 10){
						businessPOs.add(businessPO);
					}
				}
			}
			
			
			if(BeanUtils.isEmpty(param.getId())){
				//获取登录者
				model.addObject("userId", param.getUserId());
				model.addObject("userName", param.getUserName());
				//查询全部团队
				//CrmTeamParam crmTeamParam = new CrmTeamParam();
				//List<CrmTeamPO> cTeamPOs = this.iCrmTeamService.commonQuery(crmTeamParam);//查询团队信息
				//List<CrmBusinessPO> CrmBusinessPOs = iCrmBusinessService.commonQuery(businessParam);//查询业务员信息
				
			//	model.addObject("cSourcesVOs", param.getcSourcesVOs());
				model.addObject("cTeamPOs", cTeamPOs);
				model.addObject("userType", userType);
				model.addObject("businessPO", crmBusinessPO);
				model.addObject("businessPOs", CrmBusinessPOs);
				return  true;
			}
			
			model.addObject("cTeamPOs", cTeamPOs);
			model.addObject("businessPOs", businessPOs);
			
		} catch (Exception e) {
		}
		model.addObject("id", param.getId());
		model.addObject("cSourcesVOs", param.getcSourcesVOs());
		return false;
	}


	/**
	 * 本月工作目标
	* @Title: monthGoals 
	* @author : lcl
	* @time : 2017年6月13日 下午4:55:57
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@Override
	public ModelAndView monthGoals(CrmAgentParam param) {
		ModelAndView model = new ModelAndView("/system_operate/work_targe");
		if(BeanUtils.isNotEmpty(param) &&  BeanUtils.isNotEmpty(param.getAgentId())){
			//查询 代理商的工作目标
			Long agentId = param.getAgentId();//获取代理商id
			if(BeanUtils.isNotEmpty(agentId)){
				CrmWorkTargetParam  crmworktargetParam = new CrmWorkTargetParam();
				crmworktargetParam.setAgentId(agentId);//设置代理商id
				crmworktargetParam.setTargetType(3);//目标类型(1.团队 2.个人,3.代理商)
				List<CrmWorkTargetPO>  crmWorkTargetPOs = crmWorkTargetService.listByParam(crmworktargetParam);
				if(CollectionUtils.isNotEmpty(crmWorkTargetPOs)){
					model.addObject("crmWorkTargetPOs", crmWorkTargetPOs);
				}
			}
			model.addObject("agentId", agentId);
		}
		//查询 业务员的 个人目标 
		Long businessId = param.getBusinessId();//获取业务员id
		if(BeanUtils.isNotEmpty(businessId)){
			CrmWorkTargetParam  crmworktargetParam = new CrmWorkTargetParam();
			crmworktargetParam.setBusinessId(businessId);//业务员id
			crmworktargetParam.setTargetType(2);//目标类型(1.团队 2.个人,3.代理商)
			List<CrmWorkTargetPO>  crmWorkTargetPOs = crmWorkTargetService.listByParam(crmworktargetParam);
			if(CollectionUtils.isNotEmpty(crmWorkTargetPOs)){
				model.addObject("crmWorkTargetPOs", crmWorkTargetPOs);
			}
			model.addObject("businessId",businessId);
		}
		SimpleDateFormat format = new  SimpleDateFormat("yyyy年MM月");
		Date date = new  Date();
		String workMonth = format.format(date);
		model.addObject("workMonth",workMonth);
		
		return model;
	}

	 /** 分页查询工作目标
	* @Title: findMonthGoalsList 
	* @author : lcl
	* @time : 2017年6月13日 下午7:25:33
	* @return PageRespModel    返回类型 
	* @throws
	 */
	@Override
	public PageRespModel findMonthGoalsList(CrmAgentParam param) {
		PageRespModel pModel = new PageRespModel();
		Long agentId = param.getAgentId();//获取代理商id
		ParamMap paramMap = new ParamMap();
		
		if(BeanUtils.isNotEmpty(agentId)){
			CrmWorkTargetParam  crmworktargetParam = new CrmWorkTargetParam();
			crmworktargetParam.setAgentId(agentId);//设置代理商id
			crmworktargetParam.setTargetType(3);//目标类型(1.团队 2.个人,3.代理商)
			paramMap.put("agentId",agentId);
			paramMap.put("targetType",3);
		}
		Long businessId = param.getBusinessId();
		if(BeanUtils.isNotEmpty(businessId)){
			CrmWorkTargetParam  crmworktargetParam = new CrmWorkTargetParam();
			crmworktargetParam.setBusinessId(businessId);//设置业务员id
			crmworktargetParam.setTargetType(2);//目标类型(1.团队 2.个人,3.代理商)
			paramMap.put("businessId",businessId);
			paramMap.put("targetType",2);
		}
		String workMonth = param.getWorkMonth();
		if(BeanUtils.isNotEmpty(workMonth)){
			paramMap.put("workMonth", workMonth);
		}
		if(BeanUtils.isEmpty(paramMap)){
			pModel.setRows(0);
			pModel.setTotal(0);
			return pModel;
		}
		Page<CrmWorkTargetPO> cPage = crmWorkTargetService.findPageByParams(CrmWorkTargetPO.class,new Page<CrmWorkTargetPO>(param.getOffset(), param.getLimit()), "CrmWorkTargetPOMapper.queryPage", paramMap);
		List<CrmWorkTargetPO> cList = cPage.result;
		if(CollectionUtils.isNotEmpty(cList)){
			for(CrmWorkTargetPO cPo : cList){
				if(BeanUtils.isNotEmpty(cPo)){
					if(BeanUtils.isEmpty(cPo.getAgentNum())){
						cPo.setAgentNum(0L);
					}
					if(BeanUtils.isEmpty(cPo.getCanteenNum())){
						cPo.setCanteenNum(0L);
					}
					if(BeanUtils.isEmpty(cPo.getOrderMoney())){
						cPo.setOrderMoney(new BigDecimal(0));
					}
				}
			}
		}
		pModel.setRows(cList);
		pModel.setTotal(cPage.getTotalResult());
		return pModel;
	}
	
	/**
	 * 查询下级代理集合
	* @Title: getLowerAgentList 
	* @author : lcl
	* @time : 2017年6月15日 上午10:08:12
	* @return PageRespModel    返回类型 
	* @throws
	 */
	@Override
	public PageRespModel getLowerAgentList(CrmAgentParam param) {
		
		if(BeanUtils.isNotEmpty(param.getProvCode()) && BeanUtils.isEmpty(param.getCityCode()) &&BeanUtils.isEmpty(param.getAreaCode())){//传入省份的areaCode
			BaseAreaPO baseAreaPO = new BaseAreaPO();
			baseAreaPO.setParentCode(param.getProvCode().longValue());//设置父类的areaCode
			List<BaseAreaPO> cAreaPOs = iBaseAreaService.getCityByParentCode(baseAreaPO);//获取到市的 code集合
			if(CollectionUtils.isNotEmpty(cAreaPOs)){
				List<Integer> areaPOs = new ArrayList<Integer>();
				for(BaseAreaPO ap : cAreaPOs){
					BaseAreaPO baseAreaPO2 = new BaseAreaPO();
					baseAreaPO2.setParentCode(ap.getAreaCode());//通过市级的areaCode 查询区级 
					List<BaseAreaPO> cAreaPOs2 = iBaseAreaService.getCityByParentCode(baseAreaPO2);
					if(CollectionUtils.isNotEmpty(cAreaPOs2)){
						for(BaseAreaPO ap2: cAreaPOs2){
							areaPOs.add(ap2.getAreaCode().intValue());
						}
					}
				}
				param.setAreaCodes(areaPOs);//把省对应的 区级 areaCode存到集合中
			}
		}
		
		if(BeanUtils.isNotEmpty(param.getCityCode()) && BeanUtils.isEmpty(param.getAreaCode())){
			BaseAreaPO baseAreaPO = new BaseAreaPO();
			baseAreaPO.setParentCode(param.getCityCode().longValue());//获取市级的 citycode
			List<BaseAreaPO> cAreaPOs = iBaseAreaService.getCityByParentCode(baseAreaPO);
			List<Integer>  areaCodes = new  ArrayList<Integer>();
			if(CollectionUtils.isNotEmpty(cAreaPOs)){
				for(BaseAreaPO b : cAreaPOs){
					areaCodes.add(b.getAreaCode().intValue());
				}
			}
			param.setAreaCodes(areaCodes);
		}
 
		PageRespModel pModel = new PageRespModel();
	//	param.setLevel(1);//业务经理
		ParamMap paramMap = new ParamMap(param);
		Page<CrmAgentPO> cPage = findPageByParams(CrmAgentPO.class, new Page<CrmAgentPO>(param.getOffset(),param.getLimit()),"CrmAgentPOMapper.getLowerAgentList", paramMap);
		//转化成VO
		List<CrmAgentPO> crmagentList = cPage.result;
		//List<CrmAgentVO> crmAgentVOs = BeanConvertor.copyList(cAgentPOs, CrmAgentVO.class);
		List<CrmAgentVO> rows = new ArrayList<CrmAgentVO>();
		int total = 0;
		if (crmagentList != null) {
				for(int i =0;i<crmagentList.size();i++){
					CrmAgentPO crmAgentPO = crmagentList.get(i);
					//根据业务员id查询对象
					CrmAgentVO agentVO = BeanConvertor.convertBean(crmAgentPO, CrmAgentVO.class);
					if(BeanUtils.isNotEmpty(agentVO)){
						if(crmAgentPO.getBusinessId()!=null){
							//查询的是业务员
							CrmBusinessPO cBusinessPO = iCrmBusinessService.findByBusinessId(crmAgentPO.getBusinessId());
							if(BeanUtils.isNotEmpty(cBusinessPO)){
								agentVO.setBusinessName(cBusinessPO.getName());
							}
						}
//					/*//区域查询
						Long areaCode = crmAgentPO.getAreaCode();
						/*if (areaCode != null) {
						BeanUtils.databaseInjectField(agentVO, "areaName", new String[]{"area_code="+areaCode}, 
								new String[]{"area_name areaName"});
					}*/
						if(BeanUtils.isNotEmpty(areaCode)){
							BaseAreaPO baseAreaPO = iBaseAreaService.findByAreaCode(areaCode);
							if(BeanUtils.isNotEmpty(baseAreaPO)){
								agentVO.setAreaName(baseAreaPO.getAreaName());
							}
						}
						
					}
					rows.add(i, agentVO);
				}
		
		}
		pModel.setRows(rows);
		pModel.setTotal(cPage.getTotalResult());
		return pModel;
	}

	/**
	 * 查询下级代理
	* @Title: findLowerAgentList 
	* @author : lcl
	* @time : 2017年6月15日 上午11:40:09
	* @return List<CrmAgentPO>    返回类型 
	* @throws
	 */
	@Override
	public List<CrmAgentPO> findLowerAgentList(CrmAgentParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmAgentPO> cPage = findPageByParams(CrmAgentPO.class, new Page<CrmAgentPO>(param.getOffset(),param.getLimit()),"CrmAgentPOMapper.getLowerAgentList", paramMap);
		if(CollectionUtils.isNotEmpty(cPage.result)){
		  return cPage.result;
		}
		return null;
	}

	/**
	 * 根据parentId参数查询子孙agentId
	 */
	@Override
	public List<Long> getChildAgentIds(CrmAgentParam crmAgentParam) {
		ParamMap paramMap = new ParamMap(crmAgentParam);
		return commonDao.listByParams(Long.class, "CrmAgentPOMapper.getChildAgentIds", paramMap );
	}

	/**
	 * 根据代理商编号查询代理商信息
	 */
	@Override
	public CrmAgentPO findByAgentNo(Long agentUserId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentUserId", agentUserId);
		List<CrmAgentPO> crmAgentPOs = listByParams(CrmAgentPO.class, "CrmAgentPOMapper.findByAgentNo", paramMap);
		if (CollectionUtils.isNotEmpty(crmAgentPOs)) {
			return crmAgentPOs.get(0);
		}
		return null;
	}

	@Override
	public CrmAgentPO findByUserId(Long userId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("userId", userId);
		List<CrmAgentPO> agList = commonDao.listByParams(CrmAgentPO.class, "CrmAgentPOMapper.findByUserId", paramMap);
		if (CollectionUtils.isNotEmpty(agList)) {
			return agList.get(0);
		}
		return null;
	}

	@Override
	public List<CrmAgentPO> findLikeByAgentName(String condition) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("condition", condition);
		List<CrmAgentPO> crmAgentPOs = listByParams(CrmAgentPO.class, "CrmAgentPOMapper.findLikeByAgentName",  paramMap);
		if (CollectionUtils.isNotEmpty(crmAgentPOs)) {
			return crmAgentPOs;
		}
		return null;
	}
	
	/**
	 * 编辑代理商
	 */
	@Override
	public Boolean editAgent(Long clientId,
			ModelAndView modelAndView) {
		
		//查询客户资源
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) clientResourceService.getByExample(crmClientResourcePO);
		
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		if(1 != clientType){
			return true;
		}
		
		//查询代理商信息
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();
		CrmAgentPO crmAgentPO = new CrmAgentPO();
		crmAgentPO.setId(agentCanteenId);
		crmAgentPO = (CrmAgentPO) this.getByExample(crmAgentPO);
		if(BeanUtils.isEmpty(crmAgentPO)){
			return true;
		}
		if(BeanUtils.isEmpty(crmAgentPO.getContactRealname())){
			crmAgentPO.setContactRealname(crmClientResourcePO.getContact());
			crmAgentPO.setContactPhone(crmClientResourcePO.getMobile());
			
		}
		modelAndView.addObject("cAgentPO",crmAgentPO);
		List<Long> agentAreaCodes = AreaCodeUtils.getAllOfAreaCode(crmAgentPO.getAreaCode());
		if(CollectionUtils.isNotEmpty(agentAreaCodes)){
			modelAndView.addObject("agentAreaCodes", agentAreaCodes);
		}
		modelAndView.addObject("clientId", clientId);
		
		//查询业务员信息
		Long businessId = crmClientResourcePO.getBusinessId();
		
		/*Integer userType = userPO.getUserType();//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Long userId = userPO.getId();*/
		//查询业务员信息
		CrmBusinessPO crmBusinessPO = new CrmBusinessPO();
		crmBusinessPO.setId(businessId);
		crmBusinessPO = (CrmBusinessPO) iCrmBusinessService.getByExample(crmBusinessPO);
		
		Integer type = crmBusinessPO.getType();//1.平台业务员，2.代理商业务员
		CrmTeamParam crmTeamParam = null;
		CrmBusinessParam crmBusinessParam = null;
		if(1 == type){//1.平台业务员
			//查询平台的所有团队
			crmTeamParam = new CrmTeamParam();
			crmTeamParam.setType(1);//类型: 1.平台，2.代理商
			
			//查询平台的所有业务员
			crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setType(1);//1.平台业务员，2.代理商业务员
			
		}else if(2 == type){//2.代理商业务员
			//查询该业务员所在代理商所有团队
			Long agentId = crmBusinessPO.getAgentId();
			crmTeamParam = new CrmTeamParam();
			crmTeamParam.setType(2);
			crmTeamParam.setAgentId(agentId);
			
			//查询该业务员所在代理商所有业务员
			crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setType(2);
			crmBusinessParam.setAgentId(agentId);
			
		}else{
			return true;
		}
		
		//查询团队
		if(null != crmTeamParam){
			crmTeamParam.setDelFlag(1);
			crmTeamParam.setOrderBy("level");
			List<CrmTeamPO> crmTeamPOs = iCrmTeamService.commonQuery(crmTeamParam);
			modelAndView.addObject("teamPOs", crmTeamPOs);
		}
		//查询业务员
		if(null != crmBusinessParam){
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
			modelAndView.addObject("businessPOs", crmBusinessPOs);
		}
		//查询该业务员所在团队及父级团队
		Long teamId = crmBusinessPO.getTeamId();
		CrmTeamPO crmTeamPO = new CrmTeamPO();
		crmTeamPO.setId(teamId);
		crmTeamPO = (CrmTeamPO) iCrmTeamService.getByExample(crmTeamPO);
		if(BeanUtils.isNotEmpty(crmTeamPO)){
			modelAndView.addObject("level", crmTeamPO.getLevel());//传入等级
			modelAndView.addObject("businessId", crmBusinessPO.getId());
			String parentIds = crmTeamPO.getParentIds();
			
			//查询父级团队(包括本身)
			CrmTeamParam teamParam = new CrmTeamParam();
			teamParam.setParentIds(parentIds);
			teamParam.setDelFlag(1);
			List<CrmTeamPO> crmTeamPOs = iCrmTeamService.queryParentsTeam(teamParam );
			modelAndView.addObject("TPOs", crmTeamPOs);
		}
		
		
		//查询最基级团队
		/*CrmTeamParam crmTeamParam = null;
		if(1 != userType){
			if(1 == userType){//1 管理员
				crmTeamParam = new CrmTeamParam();
				crmTeamParam.setType(1);//类型: 1.平台，2.代理商
				
			}else if(2 == userType){//2.代理商
				//查询该代理商信息
				CrmAgentParam agentParam = new CrmAgentParam();
				agentParam.setUserId(userId);
				agentParam.setDelFlag(1);//存在
				List<CrmAgentPO> crmAgentPOs = this.commonQuery(agentParam);
				
				if(1 == crmAgentPOs.size()){//若只查到一条代理商记录
					CrmAgentPO agentPO = crmAgentPOs.get(0);
					crmTeamParam = new CrmTeamParam();
					crmTeamParam.setType(2);
					crmTeamParam.setAgentId(agentPO.getId());//传入代理商Id
				}
				
			}else if(4 == userType){//4.业务经理
				//查询该业务员信息(业务经理也是业务员,平台业务员还是代理商业务员)
				CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
				crmBusinessParam.setUserId(userId);
				crmBusinessParam.setDelFlag(1);
				List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
				if(1 == crmBusinessPOs.size()){//若只查到一条业务员记录
					CrmBusinessPO businessPO = crmBusinessPOs.get(0);
					Long teamId = businessPO.getTeamId();
					Long agentId = businessPO.getAgentId();//若为平台业务员,代理商Id为null
					crmTeamParam = new CrmTeamParam();
					crmTeamParam.setParentId(teamId);
					crmTeamParam.setAgentId(agentId);
				}
			}

			//查询团队信息
			
			
			modelAndView.addObject("teamId", crmBusinessPO.getTeamId());
		}else{
			modelAndView.addObject("businessName", crmBusinessPO.getName());
		}
		modelAndView.addObject("businessId", businessId);*/
		
		return false;
	}

	/**
	 * 更新代理商编辑信息
	 */
	@Override
	public Boolean updateAgentEditInfo(CrmAgentParam crmAgentParam,Long userId,
			RespModel respModel) {
		Long clientId = crmAgentParam.getClientId();
		//查询客户资源信息并更新
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) clientResourceService.getByExample(crmClientResourcePO);
		
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();//获取代理商Id
		
		//更新代理商信息
		CrmAgentPO crmAgentPO = BeanConvertor.convertBean(crmAgentParam, CrmAgentPO.class);
		crmAgentPO.setId(agentCanteenId);
		crmAgentPO.setUpdateId(userId);
		logger.info("#####CrmAgentServiceImpl###updateAgentEditInfo##更新crmAgentPO:"+crmAgentPO.toString());
		this.update(crmAgentPO);
		
		//更新客户资源信息
		crmClientResourcePO.setName(crmAgentParam.getName());
		crmClientResourcePO.setAddress(crmAgentParam.getAddress());
		crmClientResourcePO.setAreaCode(crmAgentParam.getAreaCode());
		crmClientResourcePO.setContact(crmAgentParam.getPrincipalName());
		crmClientResourcePO.setMobile(crmAgentParam.getMobilephoe());
		logger.info("#####CrmAgentServiceImpl###updateAgentEditInfo##更新crmClientResourcePO:"+crmClientResourcePO.toString());
		clientResourceService.update(crmClientResourcePO);
		
		return false;
	}

	/**
	 * 保护客户更新为散客
	 */
	@Override
	public int changeProtectedClientToIndividualTraveler(
			CrmAgentParam crmAgentParam) {
		ParamMap paramMap = new ParamMap(crmAgentParam);
		return commonDao.execute("CrmAgentPOMapper.changeProtectedClientToIndividualTraveler", paramMap);
	}
	/**
	 * 根据业务员id查询代理商
	 */
	@Override
	public List<CrmAgentPO> findByBusienssAndUserId(CrmAgentParam cAgentParam) {
		ParamMap paramMap = new ParamMap(cAgentParam);
		List<CrmAgentPO> crmAgentPOs = listByParams(CrmAgentPO.class, "CrmAgentPOMapper.findByBusiness", paramMap);
		if(CollectionUtils.isNotEmpty(crmAgentPOs)){
			return crmAgentPOs;
		}
		
		return null;
	}
}
