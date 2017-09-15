package com.hd.kzscrm.service.serviceimpl.business;

//crmteam
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.AreaCodeUtils;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.ICrmPositionService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.kzscrm.service.vo.business.CrmTeamVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 *
 * 
 * @description crmTeam 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmTeamServiceImpl extends BaseServiceImpl implements ICrmTeamService {

	// @Resource
	// ICrmTeamService iCrmTeamService;

	@Resource
	private IBaseAreaService iBaseAreaService;
	@Autowired
	private ICrmClientResourceService clientResourceService;

	@Resource
	private ICrmPositionService iCrmPositionService;
	// 日志服务对象
	protected static final Logger logger = LoggerFactory.getLogger(CrmTeamServiceImpl.class);

	/**
	 * 默认构造函数
	 */

	public CrmTeamServiceImpl() {

	}

	// 自定义方法
	// *****************************************************************************************************************

	/**
	 * PO转换为VO
	 * 
	 * @param param
	 * @return
	 */
	private List<CrmTeamVO> convertPOToVO(List<CrmTeamPO> crmteamList) {
		List<CrmTeamVO> crmteamVoList = new ArrayList<CrmTeamVO>();
		if (CollectionUtils.isEmpty(crmteamList)) {
			return crmteamVoList;
		}
		for (CrmTeamPO tag : crmteamList) {
			CrmTeamVO tagVo = BeanConvertor.copy(tag, CrmTeamVO.class);
			crmteamVoList.add(tagVo);
		}
		return crmteamVoList;
	}
	
	@Override
	public List<CrmTeamVO> findByIds(List<Long> ids){
		CrmTeamParam crmTeamParam=new CrmTeamParam();
		crmTeamParam.setIds(ids);
		List<CrmTeamPO> crmTeamPOs = this.listByParam(crmTeamParam);
		return BeanConvertor.copyList(crmTeamPOs, CrmTeamVO.class);
	}
	/**
	 * 根据平台，获取团队,如果代理商ID是空，就获取平台的
	 * @author 黄霄仪
	 * @date 2017年8月3日 下午2:51:24
	 */
	@Override
	public List<CrmTeamVO> findByTypeAndAgentId(Integer type,Long agentId){
		CrmTeamParam crmTeamParam=new CrmTeamParam();
		crmTeamParam.setType(type);
		crmTeamParam.setAgentId(agentId);
		List<CrmTeamPO> crmTeamPOs = this.listByParam(crmTeamParam);
		return BeanConvertor.copyList(crmTeamPOs, CrmTeamVO.class);
	}

	/**
	 * 根据ID获取团队信息
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月1日 下午4:59:47
	 */
	@Override
	public CrmTeamPO getById(Long id) {
		if (BeanUtils.isEmpty(id)) {
			return null;
		}
		return commonDao.get(CrmTeamPO.class, id);
	}

	public CrmTeamVO showTeamSet() {
		return null;
	}

	/**
	 * 查询
	 */
	@Override
	public Page<CrmTeamVO> queryPage(CrmTeamParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmTeamPO> crmteamList = findPageByParams(CrmTeamPO.class,
				new Page<CrmTeamPO>(param.getOffset(), param.getLimit()), "CrmTeamPOMapper.queryList", paramMap);
		List<CrmTeamVO> rows = new ArrayList<CrmTeamVO>();
		int total = 0;
		if (crmteamList.result != null) {
			rows = convertPOToVO(crmteamList.result);
			total = crmteamList.getTotalResult();
		}
		Page<CrmTeamVO> pageResult = new Page<CrmTeamVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;

	}

	/**
	 * 团队结构
	 */
	@Override
	public Page<CrmTeamVO> showTeamStrctrue(CrmTeamParam param) {
		Page<CrmTeamVO> crmTeamVOPage = queryPage(param);
		List<CrmTeamVO> CrmTeamVOs = crmTeamVOPage.result;
		for (CrmTeamVO crmTeamVO : CrmTeamVOs) {
			Long parentId = crmTeamVO.getParentId();// 父ID
			if (BeanUtils.isNotEmpty(parentId)) {
				CrmTeamPO crmTeamPOParent = this.getById(parentId);
				if (BeanUtils.isNotEmpty(crmTeamPOParent)) {
					crmTeamVO.setHigherManager(crmTeamPOParent.getName());// 上级主管
				}
			}

			// 获取区域名称
			Long areaCode = crmTeamVO.getAreaCode();
			if (BeanUtils.isNotEmpty(areaCode)) {
				List<Long> areaCodes = AreaCodeUtils.getAllOfAreaCode(areaCode);
				List<BaseAreaPO> baseAreaPOs = iBaseAreaService.findByAreaCodes(areaCodes);
				List<String> areaNames = new LinkedList<>();
				for (BaseAreaPO baseAreaPO : baseAreaPOs) {
					areaNames.add(baseAreaPO.getAreaName());
				}
				crmTeamVO.setAreaName(StringUtils.join(areaNames, "-"));
			}

		}
		return crmTeamVOPage;

	}

	/**
	 * 根据主键查询详情
	 * 
	 * @param param
	 * @return
	 */

	@Override
	public List<CrmTeamPO> listByParam(CrmTeamParam crmteamParam) {
		ParamMap paramMap = new ParamMap(crmteamParam);
		return commonDao.listByParams(CrmTeamPO.class, "CrmTeamPOMapper.queryList", paramMap);
	}
	
	@Override
	public List<CrmTeamPO> commonSearch(CrmTeamParam crmTeamParam) {
		ParamMap paramMap = new ParamMap(crmTeamParam);
		return commonDao.listByParams(CrmTeamPO.class, "CrmTeamPOMapper.commonSearch", paramMap);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws BizException {
		if (BeanUtils.isEmpty(id)) {
			return;
		}
		/*
		 * Long id = Long.parseLong(ids); CrmTeamPO po =
		 * this.get(CrmTeamPO.class,id); if(po != null){
		 * po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
		 * this.update(po); }else{ throw new
		 * BizException(BaseExceptionEnum.UPDATE_FAILURE); }
		 */
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", id);
		this.execute("CrmTeamPOMapper.deleteById", paramMap);
	}

	/**
	 * 中台删除
	 */
	@Override
	public void deleteByIds(String ids) throws BizException {
		Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
		List<CrmTeamPO> listPo = new ArrayList<CrmTeamPO>(idl.length);
		for (int i = 0; i < idl.length; i++) {
			CrmTeamPO po = this.get(CrmTeamPO.class, idl[i]);
			if (po != null) {
				po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
				listPo.add(po);
			} else {
				throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
			}
		}
		for (CrmTeamPO po : listPo) {
			this.update(po);
		}

	}

	/**
	 * 新增
	 */
	@Override
	public void add(CrmTeamParam param) {
		CrmTeamPO crmteamPO = BeanConvertor.copy(param, CrmTeamPO.class);
		this.save(crmteamPO);
	}

	/**
	 * 新增
	 */
	@Override
	public void saveEntity(CrmTeamPO po) {
		Long id = po.getId();
		if(BeanUtils.isEmpty(id)){
			po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_team));
		}
		this.save(po);
	}

	/**
	 * update
	 */
	@Override
	public void updateEntity(CrmTeamPO po) {
		this.update(po);
	}

	/**
	 * 跟主键查询对象
	 * 
	 * @param teamId
	 * @return
	 */
	@Override
	public CrmTeamPO findByTeamId(Long teamId) {
		// 用来传参
		ParamMap paramMap = new ParamMap();
		paramMap.put("teamId", teamId);
		List<CrmTeamPO> crmTeamPOs = listByParams(CrmTeamPO.class, "CrmTeamPOMapper.findById", paramMap);
		if (CollectionUtils.isNotEmpty(crmTeamPOs)) {
			return crmTeamPOs.get(0);
		}
		return null;
	}

	@Override
	public List<CrmTeamPO> findAll(CrmTeamParam param) {
		ParamMap paramMap = new ParamMap(param);
		List<CrmTeamPO> crmTeamPOs = listByParams(CrmTeamPO.class, "CrmTeamPOMapper.findByAll", paramMap);
		if (crmTeamPOs != null) {
			return crmTeamPOs;
		}
		return null;
	}

	/**
	 * 查询团队信息
	 */
	@Override
	public List<CrmTeamPO> commonQuery(CrmTeamParam crmTeamParam) {
		ParamMap paramMap = new ParamMap(crmTeamParam);
		return commonDao.listByParams(CrmTeamPO.class, "CrmTeamPOMapper.commonQuery", paramMap);
	}

	/**
	 * 查询该团队下的终端团队id(效率低下,作废)
	 */
	@Override
	public void getLowestLevelChileanTeam(List<Long> crmTeamIds, CrmTeamParam crmTeamParam) {
		ParamMap paramMap = new ParamMap(crmTeamParam);
		List<CrmTeamPO> teamPOs = listByParams(CrmTeamPO.class, "CrmTeamPOMapper.commonQuery", paramMap);
		if (CollectionUtils.isNotEmpty(teamPOs)) {
			for (CrmTeamPO crmTeamPO : teamPOs) {
				crmTeamParam.setParentId(crmTeamPO.getId());
				getLowestLevelChileanTeam(crmTeamIds, crmTeamParam);
			}
		} else {
			crmTeamIds.add(crmTeamParam.getParentId());
		}

	}

	/**
	 * 导出查询
	 */
	@Override
	public Page<CrmTeamVO> findPageSelect(CrmTeamParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmTeamPO> crmteamList = findPageByParams(CrmTeamPO.class,
				new Page<CrmTeamPO>(param.getOffset(), param.getLimit()), "CrmTeamPOMapper.queryPage", paramMap);
		List<CrmTeamVO> rows = new ArrayList<CrmTeamVO>();

		int total = 0;
		if (crmteamList.result != null) {
			for (int i = 0; i < crmteamList.result.size(); i++) {
				CrmTeamPO crmTeamPO = this.findByTeamId(crmteamList.result.get(i).getId());
				CrmTeamPO targetPO = crmteamList.result.get(i);
				if (BeanUtils.isNotEmpty(crmTeamPO)) {
					CrmTeamVO teamVO = new CrmTeamVO();
					teamVO = BeanConvertor.convertBean(targetPO, CrmTeamVO.class);
					teamVO.setName(crmTeamPO.getName());
					rows.add(i, teamVO);
				}
			}
			total = crmteamList.getTotalResult();
		}
		Page<CrmTeamVO> pageResult = new Page<CrmTeamVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}

	@Override
	public Page<CrmTeamVO> queryPageteam(CrmTeamParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询团队相关信息
	 */
	@Override
	public List<CrmTeamPO> queryTeamMsg(CrmTeamParam crmteamParam) {
		ParamMap paramMap = new ParamMap(crmteamParam);
		return commonDao.listByParams(CrmTeamPO.class, "CrmTeamPOMapper.queryTeamMsg", paramMap);
	}

	/**
	 * 获取团队的所有子团队(包括本身)
	 */
	@Override
	public List<CrmTeamPO> getChildTeam(CrmTeamParam teamParam) {
		ParamMap paramMap = new ParamMap(teamParam);
		return commonDao.listByParams(CrmTeamPO.class, "CrmTeamPOMapper.getChildTeam", paramMap);
	}

	/**
	 * 查询所有团队信息
	 */
	@Override
	public Page<CrmTeamVO> queryPages(CrmTeamParam param) {
		ParamMap paramMap = new ParamMap();
		Page<CrmTeamPO> list = findPageByParams(CrmTeamPO.class,
				new Page<CrmTeamPO>(param.getOffset(), param.getLimit()), "CrmTeamPOMapper.queryPages", paramMap);
		List<CrmTeamVO> rows = new ArrayList<CrmTeamVO>();
		int total = 0;
		if (list.result != null) {
			rows = convertPOToVO(list.result);
			total = list.getTotalResult();
		}
		Page<CrmTeamVO> pageResult = new Page<CrmTeamVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}

	/**
	 * 查询成员信息
	 */
	@Override
	public Page<CrmBusinessVO> queryBusiness(CrmBusinessParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmBusinessPO> crmbusinessList = findPageByParams(CrmBusinessPO.class,
				new Page<CrmBusinessPO>(param.getOffset(), param.getLimit()), "CrmBusinessPOMapper.queryPage",
				paramMap);
		List<CrmBusinessVO> rows = new ArrayList<CrmBusinessVO>();
		int total = 0;
		if (crmbusinessList.result != null) {
			for (int i = 0; i < crmbusinessList.result.size(); i++) {
				CrmBusinessPO crmBusinessPO = crmbusinessList.result.get(i);
				CrmBusinessVO crmBusinessVO = BeanConvertor.convertBean(crmBusinessPO, CrmBusinessVO.class);
				// CrmDepartmentPO cDp = null;
				CrmPositionPO cpp = null;
				// 查询团队
				if (BeanUtils.isNotEmpty(crmBusinessPO.getTeamId())) {
					CrmTeamPO tpo = findByTeamId(crmBusinessPO.getTeamId());
					if (BeanUtils.isNotEmpty(tpo)) {
						crmBusinessVO.setTeamName(tpo.getName());
					} else {
						crmBusinessVO.setTeamName("-");
					}
				}

				// 根据position 查询对象
				if (BeanUtils.isNotEmpty(crmBusinessPO.getPositionId())) {
					cpp = iCrmPositionService.getById(crmBusinessPO.getPositionId());
					if (BeanUtils.isNotEmpty(cpp)) {
						String positionName = cpp.getName();
						crmBusinessVO.setPositionName(positionName);
					}
				}
				rows.add(i, crmBusinessVO);
			}
			total = crmbusinessList.getTotalResult();
		}
		Page<CrmBusinessVO> pageResult = new Page<CrmBusinessVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}
	
	/**
	 * 获取子孙团队Id(包括自身Id)
	 */
	@Override
	public List<Long> getChildTeamIds(CrmTeamParam crmTeamParam) {
		ParamMap paramMap = new ParamMap(crmTeamParam);
		return commonDao.listByParams(Long.class, "CrmTeamPOMapper.getChildTeamIds", paramMap );
	}

	/**
	 * 根据团队名称模糊查询
	 */
	@Override
	public List<CrmTeamPO> findLikeByTeamName(String condition) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("condition", condition);
		List<CrmTeamPO> crmTeamPOs = listByParams(CrmTeamPO.class, "CrmTeamPOMapper.findLikeByTeamName", paramMap);
		if (CollectionUtils.isNotEmpty(crmTeamPOs)) {
			return crmTeamPOs;
		}
		return null;
	}

	/**
	 * 查询团队名称
	 */
	@Override
	public List<CrmTeamPO> getTeamByIds() {
		ParamMap paramMap = new ParamMap();
		paramMap.put("del_flag", 1);
		List<CrmTeamPO> clist = listByParams(CrmTeamPO.class, "CrmTeamPOMapper.getTeamName", paramMap);
		return clist;
	}

	@Override
	public Page<CrmBusinessVO> queryBusinessById(CrmBusinessParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmBusinessPO> crmbusinessList = findPageByParams(CrmBusinessPO.class,
				new Page<CrmBusinessPO>(param.getOffset(), param.getLimit()), "CrmBusinessPOMapper.queryPage",
				paramMap);
		List<CrmBusinessVO> rows = new ArrayList<CrmBusinessVO>();
		int total = 0;
		if (crmbusinessList.result != null) {
			for (int i = 0; i < crmbusinessList.result.size(); i++) {
				CrmBusinessPO crmBusinessPO = crmbusinessList.result.get(i);
				//根据业务员id查询保护客户数量
				CrmBusinessVO crmBusinessVO = BeanConvertor.convertBean(crmBusinessPO, CrmBusinessVO.class);
				if(BeanUtils.isNotEmpty(crmBusinessPO.getId())){
					//循环一次 查询一次 是否满足保护客户小于10个的
					List<CrmClientResourcePO> clientResourcePOs = clientResourceService.findByBusinessId(crmBusinessPO.getId());
					if(CollectionUtils.isEmpty(clientResourcePOs) || clientResourcePOs.size()<10){
						rows.add(crmBusinessVO);
						//进来一次加一
						total+=1;
					}
						
					}
					
					
				}
		}
		Page<CrmBusinessVO> pageResult = new Page<CrmBusinessVO>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}
	
	

	/**
	 * 根据用户类型查询团队信息
	 */
	@Override
	public List<CrmTeamPO> getType(Integer type) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("type", type);
		List<CrmTeamPO> clist = listByParams(CrmTeamPO.class, "CrmTeamPOMapper.getType", paramMap);
		if (BeanUtils.isNotEmpty(clist)) {
			return clist;
		}
		return null;
	}

	/**
	 * 查询父级团队(包括本身)
	 */
	@Override
	public List<CrmTeamPO> queryParentsTeam(CrmTeamParam teamParam) {
		ParamMap paramMap = new ParamMap(teamParam);
		return listByParams(CrmTeamPO.class, "CrmTeamPOMapper.queryParentsTeam", paramMap );
	}
	@Override
	public CrmTeamVO findByTypeAndLevel(Integer type,Integer level){
		CrmTeamParam crmTeamParam=new CrmTeamParam();
		crmTeamParam.setType(type);
		crmTeamParam.setLevel(level);
		List<CrmTeamPO> crmTeamPOs = this.listByParam(crmTeamParam);
		if(BeanUtils.isEmpty(crmTeamPOs)){
			return null;
		}
		Assert.isTrue(crmTeamPOs.size()==1,"团队数量大于1");
		return BeanConvertor.convertBean(crmTeamPOs.get(0),CrmTeamVO.class);
	}
	/**
	 * 
	 * @author 黄霄仪
	 * @date 2017年8月1日 下午4:26:01
	 * @type 平台类型，1.平台，2.代理商
	 * @agentId 如果没有代理商，就是平台
	 */
	@Override
	public CrmTeamVO findByAreaCodeAndTypeAndAgentId(Long areaCode,Integer type,Long agentId){
		CrmTeamParam crmTeamParam=new CrmTeamParam();
		crmTeamParam.setAreaCode(areaCode);
		crmTeamParam.setType(type);
		crmTeamParam.setAgentId(agentId);
		List<CrmTeamPO> crmTeamPOs = this.listByParam(crmTeamParam);
		if(BeanUtils.isEmpty(crmTeamPOs)){
			return null;
		}
		Assert.isTrue(crmTeamPOs.size()==1,"团队数量大于1");
		return BeanConvertor.convertBean(crmTeamPOs.get(0),CrmTeamVO.class);
	}
	/**
	 * @deprecated 参考findByAreaCodeAndType，因为代理商也可以创建团队，所以根据负责范围，不可能有一个
	 */
	@Override
	@Deprecated
	public CrmTeamVO findByAreaCode(Long areaCode){
		CrmTeamParam crmTeamParam=new CrmTeamParam();
		crmTeamParam.setAreaCode(areaCode);
		List<CrmTeamPO> crmTeamPOs = this.listByParam(crmTeamParam);
		if(BeanUtils.isEmpty(crmTeamPOs)){
			return null;
		}
		Assert.isTrue(crmTeamPOs.size()==1,"团队数量大于1");
		return BeanConvertor.convertBean(crmTeamPOs.get(0),CrmTeamVO.class);
	}
	/** 
	 * 新增团队
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 上午10:27:46 
	*/
	@Override
	public RespModel addTeam(CrmTeamParam param) {
		Integer level = param.getLevel();
		Integer type = param.getType();
		Long areaCode = param.getAreaCode();
		Long parentId = param.getParentId();
		Long agentId = param.getAgentId();
		if(BeanUtils.isEmptyAnd(parentId,areaCode)){//默认第1级是中国
			areaCode=86l;
			param.setAreaCode(areaCode);
		}else if(BeanUtils.isEmpty(areaCode)){
			return RespModel.failure("请输入业务范围");
		}
		//如果区域代码不等于9位数，并且团队中已有该团队，则不能添加
		if(String.valueOf(areaCode).length()!=9&&BeanUtils.isNotEmpty(this.findByAreaCodeAndTypeAndAgentId(areaCode,type,agentId))){
			return RespModel.failure("该区域已有团队负责");
		}
		if(BeanUtils.isNotEmpty(parentId)){
			if(BeanUtils.isEmpty(areaCode)){
				return RespModel.failure("请选择业务范围");
			}
		}
		BaseAreaPO baseAreaPO = iBaseAreaService.findByAreaCode(areaCode);
		Long areaLevel = baseAreaPO.getAreaLevel();//现在创建的团队等级
		//查看现在创建的团队等级是否已创建上一级
		CrmTeamPO parentCrmTeamPO=null;
		if(BeanUtils.isEmpty(parentId)){
			level=1;
		}else{
			parentCrmTeamPO=this.get(CrmTeamPO.class, parentId);
			if(BeanUtils.isEmpty(parentCrmTeamPO)){
				return RespModel.failure("父级团队不存在");
			}else{
				level=parentCrmTeamPO.getLevel()+1;
			}
		}
		List<Long> allOfAreaCodes = AreaCodeUtils.getAllOfAreaCode(areaCode);
		if(allOfAreaCodes.size()>1){
			CrmTeamVO parentCrmTeamPOTeam=this.findByAreaCodeAndTypeAndAgentId(allOfAreaCodes.get(allOfAreaCodes.size()-2),type,agentId);
			if(BeanUtils.isEmpty(parentCrmTeamPOTeam)){
				return RespModel.failure("请先创建上级团队");
			}
		}
//		if(areaLevel>=Long.valueOf(level)){
//			return RespModel.failure("请先创建上级团队");
//		}
		if(BeanUtils.isNotEmpty(parentCrmTeamPO)){
			Long parentAreaCode = parentCrmTeamPO.getAreaCode();
			if(level!=1){
				
			}
		}
		RespModel respModel = RespModel.success("添加成功");
		param.setCreaterTime(new Date());
		if(BeanUtils.isEmpty(param.getName())){
			return RespModel.failure("团队名称不能为空");
		}
		if(BeanUtils.isEmpty(areaCode)){
			return RespModel.failure("业务范围不能为空");
		}
		Long teamId=ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_team);
		param.setId(teamId);
		param.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
		//如果为空就是顶级
		if(BeanUtils.isNotEmpty(parentId)){
			
			param.setLevel(level);
			param.setTopParentId(parentCrmTeamPO.getTopParentId());
			param.setParentId(parentCrmTeamPO.getId());
			param.setParentIds(parentCrmTeamPO.getParentIds()+","+teamId);
		}else{
			param.setLevel(level);
			param.setTopParentId(teamId);
			param.setParentId(teamId);
			param.setParentIds(teamId+"");
		}
		try {
			/*CrmTeamVO crmTeamVO = this.findByTypeAndLevel(type, level);
			if(BeanUtils.isNotEmpty(crmTeamVO)){
				return RespModel.failure("已有"+level+"级团队:"+crmTeamVO.getName());
			}*/
			this.saveEntity(BeanConvertor.copy(param,CrmTeamPO.class));
			return respModel;
		} catch (Exception e) {
			e.printStackTrace();
			return RespModel.failure(e.getMessage());
		}
	}

	/** 
	 * 检索出省
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 下午1:19:52 
	*/
	@Override
	public List<CrmTeamPO> selectByAreaCodeLike(String areaCode) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("areaCode", areaCode);
		return listByParams(CrmTeamPO.class,"CrmTeamPOMapper.selectByAreaCodeLike", paramMap);
	}

	/** 
	 * 筛选团队
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 下午3:10:48 
	*/
	@Override
	public List<CrmTeamPO> selectByAreaCode(CrmTeamParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(CrmTeamPO.class,"CrmTeamPOMapper.selectByAreaCode", paramMap);
	}

	/** 
	 * 修改团队
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月19日 上午9:52:04 
	*/
	@Override
	public RespModel updateTeam(CrmTeamParam param) {
		RespModel respModel = RespModel.success("添加成功");
		//前端传递过来的参数
		String name = param.getName();
		Long id = param.getId();
		Long areaCode = param.getAreaCode();
		Long parentId = param.getParentId();
		Long agentId = param.getAgentId();
		
		Integer type = param.getType();
		CrmTeamPO crmTeamPO = this.get(CrmTeamPO.class, id);
		if(BeanUtils.isEmpty(crmTeamPO)){
			return RespModel.failure("没有找到该团队的信息");
		}
		Integer level=crmTeamPO.getLevel();
		if(BeanUtils.isEmptyAnd(parentId,areaCode)){//默认第1级是中国
			areaCode=86l;
			param.setAreaCode(areaCode);
		}else if(BeanUtils.isEmpty(areaCode)){
			return RespModel.failure("请输入业务范围");
		}
		//如果没有改变负责区域，就不用判断团队是否有该团队
		if(crmTeamPO.getAreaCode().compareTo(areaCode)!=0){
			//如果区域代码不等于9位数，并且团队中已有该团队，则不能添加
			if(String.valueOf(areaCode).length()!=9&&BeanUtils.isNotEmpty(this.findByAreaCodeAndTypeAndAgentId(areaCode,type,agentId))){
				return RespModel.failure("该区域已有团队负责");
			}
		}
		if(BeanUtils.isNotEmpty(parentId)){
			if(BeanUtils.isEmpty(areaCode)){
				return RespModel.failure("请选择业务范围");
			}
		}
		List<Long> allOfAreaCodes = AreaCodeUtils.getAllOfAreaCode(areaCode);
		
		BaseAreaPO baseAreaPO = iBaseAreaService.findByAreaCode(areaCode);
		Long areaLevel = baseAreaPO.getAreaLevel();//现在创建的团队等级
		level=areaLevel.intValue()+1;
		//查看现在创建的团队等级是否已创建上一级
		CrmTeamVO parentCrmTeamPO=this.findByAreaCodeAndTypeAndAgentId(allOfAreaCodes.get(allOfAreaCodes.size()-2),type,agentId);
		//一般来说，level会比areaLevel小,如果areaLvel大于level，说明没有创建上级团队
		if(BeanUtils.isEmpty(parentCrmTeamPO)){
			return RespModel.failure("请先创建上级团队");
		}
		String parentIds = parentCrmTeamPO.getParentIds();
		param.setParentIds(parentIds+","+id);
		param.setLevel(level);
		param.setCreaterTime(new Date());
		if(BeanUtils.isEmpty(name)){
			return RespModel.failure("团队名称不能为空");
		}
		if(BeanUtils.isEmpty(areaCode)){
			return RespModel.failure("业务范围不能为空");
		}
		
		try {
			commonDao.update(BeanConvertor.copy(param, CrmTeamPO.class));
			respModel.setDesc("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			respModel.setDesc("保存失败");
		}
		return respModel;
	}

	/** 
	 * 查询是否有子团队
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月28日 上午11:30:39 
	*/
	@Override
	public List<CrmTeamPO> findByParentId(Long parentId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("parentId", parentId);
		return listByParams(CrmTeamPO.class, "CrmTeamPOMapper.findByParentId", paramMap);
	}

	
}
