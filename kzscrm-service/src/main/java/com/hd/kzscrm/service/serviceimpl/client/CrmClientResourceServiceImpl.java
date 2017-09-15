package com.hd.kzscrm.service.serviceimpl.client;

import java.math.BigDecimal;
//crmclientresource
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.enums.canteen.CrmCanteenBaseInfoEnum.CrmCanteenBaseInfoClientType;
import com.hd.kzscrm.common.enums.canteen.CrmCanteenBaseInfoEnum.CrmCanteenBaseInfoStatus;
import com.hd.kzscrm.common.enums.client.CrmClientResourceEnum.CrmClientResourceClientNature;
import com.hd.kzscrm.common.enums.client.CrmClientResourceEnum.CrmClientResourceClientType;
import com.hd.kzscrm.common.enums.enterprise.CrmEnterpriseEnum.CrmEnterpriseStatus;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.AreaCodeUtils;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.DateUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.common.util.StringUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentAreaPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.dao.entity.business.CanteenHealthPicPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenExtInfoPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterpriseCanteenPO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.service.param.agent.CrmAgentApplyParam;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.agent.CrmSplitAssignSetParam;
import com.hd.kzscrm.service.param.business.CanteenHealthPicParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.param.business.OrderParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenApplyParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenExtInfoParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.param.enterprise.CrmEnterpriseCanteenParam;
import com.hd.kzscrm.service.param.enterprise.CrmEnterpriseParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentAreaService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.CanteenHealthPicService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenApplyService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenExtInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseCanteenService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenBaseInfoVO;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenExtInfoVO;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;
import com.hd.wolverine.cache.WolverineJedisCluster;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmClientResource 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmClientResourceServiceImpl extends BaseServiceImpl implements ICrmClientResourceService {
	 //业务员
	 @Autowired
     private ICrmBusinessService icrmBusinessService;
	 @Resource
	 private ICrmTeamService iCrmTeamService;
	 //用户表
	 @Autowired
	 ICrmUserService icrmUserService;
	 //代理商表
	 @Resource
	 ICrmAgentService icrmAgentService;
	 //食堂基本信息
	 @Autowired
	 ICrmCanteenBaseInfoService icrmCanteenBaseInfoService;
	 @Resource
	 private ICrmCanteenExtInfoService iCrmCanteenExtInfoService;
	 @Resource
	 private ICrmEnterpriseService iCrmEnterpriseService;
	 @Resource
	 private ICrmEnterpriseCanteenService iCrmEnterpriseCanteenService;
	 //抽成分配设置表
	 @Autowired
	 ICrmSplitAssignSetService icrmSplitAssignSetService;
		//地理信息表
	 @Resource
	private IBaseAreaService iBaseAreaService;
	 
	@Resource
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 食堂扩展信息
	 */
	/*@Resource
	private ICrmCanteenExtInfoService crmCanteenExtInfoService;*/
	
	/**
	 * 企业商家映射表
	 */
	/*@Resource
	private ICrmEnterpriseCanteenService crmEnterpriseCanteenService;*/
	/**
	 * 企业信息
	 */
	/*@Resource
	private ICrmEnterpriseService crmEnterpriseService;*/
	
	@Value("${img.view.address}")
    private String imgViewAddress;
	 //订单表
	@Autowired
	private ICrmOrderService orderService;
	
	/**
	 * 代理商代理区域表
	 */
	@Resource
	ICrmAgentAreaService iCrmAgentAreaService;
	
	/**
	 * 代理商申请
	 */
	@Resource
	ICrmAgentApplyService iCrmAgentApplyService;
	
	/**
	 * 食堂申请
	 */
	@Resource
	ICrmCanteenApplyService iCrmCanteenApplyService;
	
	@Resource
	private WolverineJedisCluster wolverineJedisCluster;
	/**
	 * 卫生许可证
	 */
	@Resource
	CanteenHealthPicService canteenHealthPicService;
	
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmClientResourceServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmClientResourceServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
        
    /**
	 * 新增食堂信息（企业，食堂扩展，客户资源）
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月21日 下午3:35:03
	 * @keypoint 默认1个月的保护期
	 */
	@Override
	public RespModel addCrmClientResourceByCanteen(CrmCanteenBaseInfoParam param) {
		Long businessId = param.getBusinessId();//业务员ID
		List<Long> agentIds = param.getAgentIds();//代理商ID
		Long createrUid = param.getCreaterUid();//创建人ID
		String address = param.getAddress();//地址
		Long areaCode = param.getAreaCode();//区域代码
		Integer clientType = param.getClientType();//客户类型
		Date currentDate = new Date();//当前时间
		
		//如果分配给的是业务员
		if(BeanUtils.isNotEmpty(businessId)){
			CrmClientResourceParam crmClientResourceParam=new CrmClientResourceParam();
			crmClientResourceParam.setBusinessId(businessId);
			if(this.listByParam(crmClientResourceParam).size()>10){
				return RespModel.failure("保护客户不能大于10个");
			}
		}
		
		// 食堂扩展表
		CrmCanteenExtInfoParam crmCanteenExtInfoParam = param.getCrmCanteenExtInfoParam();
		//判断负责人手机号是否重复
		CrmCanteenExtInfoParam crmCEIParam=new CrmCanteenExtInfoParam();
		crmCEIParam.setHeadPhone(crmCanteenExtInfoParam.getHeadPhone());
		List<CrmCanteenExtInfoPO> listByParam = iCrmCanteenExtInfoService.listByParam(crmCEIParam);
		if(BeanUtils.isNotEmpty(listByParam)){
			return RespModel.failure("商家负责人手机号码已使用！");
		}
		
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
			currentDateTime.add(Calendar.MONTH,1);//默认保护期为1个月
			crmClientResourceParam.setProtectDeadline(currentDateTime.getTime());
		}
				
		Long enterpriseId = ServiceUtil
				.genNextIDValue(DatabaseTableNameEnum.crm_enterprise);//企业ID
				
		//食堂信息数据初始化
		param.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
		param.setCreateTime(currentDate);
		param.setUpdateTime(currentDate);
		param.setCanteenCategoryId(1l);//默认餐饮类
		param.setAddress(address);
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
		//crmClientResourceParam.setApplyTime(currentDate);
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
		//crmClientResourceParam.setCheckStatus(CrmClientResourceCheckStatus.APPLYING_PASS.getCode());//审核状态,0.申请中，1.申请通过，2.申请失败
		crmClientResourceParam.setApplyApproveTime(currentDate);
		

		//添加客户资源表数据
		this.add(crmClientResourceParam);
		//保存商家信息及许可证信息
		icrmCanteenBaseInfoService.add(param);
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
	}
	/**
	 * 编辑食堂信息
	 * @author 黄霄仪
	 * @date 2017年6月27日 上午11:06:15
	 */
	@Override
	public RespModel editCrmClientResourceByCanteen(CrmCanteenBaseInfoParam crmCanteenBaseInfoparam){
		logger.info("com.hd.kzscrm.service.serviceimpl.client.CrmClientResourceServiceImpl.editCrmClientResourceByCanteen(CrmCanteenBaseInfoParam)",crmCanteenBaseInfoparam);
		try {
			Long updaterUid = crmCanteenBaseInfoparam.getUpdaterUid();
			Date currentDate=new Date();
			Integer clientType = crmCanteenBaseInfoparam.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			CrmCanteenExtInfoParam crmCanteenExtInfoParam = crmCanteenBaseInfoparam
					.getCrmCanteenExtInfoParam();// 扩展表
			CrmClientResourceParam crmClientResourceParam = crmCanteenBaseInfoparam
					.getCrmClientResourceParam();// 客户资源表
			CrmEnterpriseParam crmEnterpriseParam = crmCanteenBaseInfoparam
					.getCrmEnterpriseParam();// 企业表
			Long id = crmClientResourceParam.getId();
			Long areaCode = crmCanteenBaseInfoparam.getAreaCode();
			String address = crmCanteenBaseInfoparam.getAddress();
			Integer customerAttribute = crmCanteenBaseInfoparam.getCustomerAttribute();//客户属性  1 代理商客户 2平台客户
			Long businessId = crmCanteenBaseInfoparam.getBusinessId();
			List<Long> agentIds = crmCanteenBaseInfoparam.getAgentIds();
			
			
			CrmClientResourcePO crmClientResourcePO = this.getById(id);//客户资源信息
			Assert.notNull(crmClientResourcePO, "客户资源信息不能为空");
			Assert.notNull(crmCanteenExtInfoParam, "客户扩展信息不能为空");
			Long enterpriseId = crmClientResourcePO.getEnterpriseId();
			
			//修改客户资源表的值
			CrmClientResourceParam crmClientResourceParamTemp=BeanConvertor.copy(crmClientResourcePO,CrmClientResourceParam.class);
			/**
			 * 删除从非独立食堂到独立食堂的相关信息
			 */
			//如果是独立食堂，并且有企业ID，就删除企业信息
			if (clientType==CrmCanteenBaseInfoClientType.INDEPENDENT_CANTEEN.getCode()&&BeanUtils.isNotEmpty(enterpriseId)) {
				iCrmEnterpriseCanteenService.deleteById(enterpriseId);
				
				CrmEnterprisePO crmEnterprisePO=new CrmEnterprisePO();
				crmEnterprisePO.setId(enterpriseId);
				crmEnterprisePO.setDelFlag(CrmCommonDelFlag.DELETE.getCode());
				crmEnterprisePO.setUpdateTime(currentDate);
				crmEnterprisePO.setUpdateUid(updaterUid);
				iCrmEnterpriseService.updateEntity(crmEnterprisePO);
				
				crmClientResourceParamTemp.setEnterpriseIdNull(1l);//任何非空都能清空
			}
			//如果企业ID是空，但crmEnterpriseParam不为空，就新增企业信息
			if (BeanUtils.isEmpty(enterpriseId)&&BeanUtils.isNotEmpty(crmEnterpriseParam)){
				enterpriseId=ServiceUtil
						.genNextIDValue(DatabaseTableNameEnum.crm_enterprise);//企业ID
				crmClientResourceParamTemp.setEnterpriseId(enterpriseId);
				crmEnterpriseParam.setId(enterpriseId);
				iCrmEnterpriseService.add(crmEnterpriseParam);
			}
			//如果企业ID不是空，但crmEnterpriseParam不为空，就修改企业信息
			if (BeanUtils.isNotEmpty(enterpriseId)&&BeanUtils.isNotEmpty(crmEnterpriseParam) && clientType!=CrmCanteenBaseInfoClientType.INDEPENDENT_CANTEEN.getCode()){
				crmClientResourceParamTemp.setEnterpriseId(enterpriseId);
				crmEnterpriseParam.setId(enterpriseId);
				CrmEnterprisePO crmEnterprisePO = new CrmEnterprisePO(); 
				crmEnterprisePO=BeanConvertor.convertBean(crmEnterpriseParam, CrmEnterprisePO.class);
				iCrmEnterpriseService.update(crmEnterprisePO);
			}
			
			Integer clientNature=CrmClientResourceClientNature.SCATTER_CLIENT.getCode();//散客
			if(BeanUtils.isNotEmpty(agentIds)||BeanUtils.isNotEmpty(businessId)){
				clientNature=CrmClientResourceClientNature.PROTECT_CLIENT.getCode();//保护客户
				if(BeanUtils.isNotEmpty(agentIds)){
					crmClientResourceParamTemp.setAgentId(agentIds.get(agentIds.size()-1));//最后一级是最小等级的代理商
					crmClientResourceParamTemp.setBusinessIdNull(1l);//清空业务员
				}else if(BeanUtils.isNotEmpty(businessId)){
					crmClientResourceParamTemp.setBusinessId(businessId);
					crmClientResourceParamTemp.setAgentIdNull(1l);//清空代理商
				}
				Calendar currentDateTime=Calendar.getInstance();//当前时间
				currentDateTime.add(Calendar.DAY_OF_MONTH,1);//默认保护期为1个月
				crmClientResourceParamTemp.setProtectDeadline(currentDateTime.getTime());
			}else{
				crmClientResourceParamTemp.setProtectDeadlineNull(currentDate);//非空就置空该置
				crmClientResourceParamTemp.setAgentIdNull(1l);//非空就置空该置
				crmClientResourceParamTemp.setBusinessIdNull(1l);//非空就置空该置
			}
			//更新客户资源信息
			crmClientResourceParamTemp.setCreateTime(currentDate);
			//crmClientResourceParamTemp.setApplyTime(currentDate);
			crmClientResourceParamTemp.setAddress(address);
			crmClientResourceParamTemp.setAreaCode(areaCode);
			crmClientResourceParamTemp.setClientType(clientType);
			//如果不是正式客户，就更新客户类型
			if(crmClientResourcePO.getClientNature()!=3){
				crmClientResourceParamTemp.setClientNature(clientNature);
				crmCanteenBaseInfoparam.setClientNature(clientNature);
			}
			crmClientResourceParamTemp.setName(crmCanteenBaseInfoparam.getName());
			crmClientResourceParamTemp.setContact(crmCanteenBaseInfoparam.getContactRealname());
			crmClientResourceParamTemp.setMobile(crmCanteenBaseInfoparam.getContactPhone());
			crmClientResourceParamTemp.setTailNum(0l);//默认0次
			crmClientResourceParamTemp.setCustomerAttribute(customerAttribute);//客户属性  1 代理商客户 2平台客户
			//crmClientResourceParamTemp.setCheckStatus(CrmClientResourceCheckStatus.APPLYING_PASS.getCode());//审核状态,0.申请中，1.申请通过，2.申请失败
			//crmClientResourceParamTemp.setApplyApproveTime(currentDate);
			
			//食堂扩展表
			crmCanteenExtInfoParam.setAreaCode(areaCode);
			crmCanteenExtInfoParam.setUpdaterUid(updaterUid);
			crmCanteenExtInfoParam.setUpdateTime(currentDate);
			
			this.commonUpdate(crmClientResourceParamTemp);
			
			//食堂信息表数据
			crmCanteenBaseInfoparam.setCreaterUid(null);//不更新创建人
			crmCanteenBaseInfoparam.setUpdaterUid(updaterUid);
			crmCanteenBaseInfoparam.setUpdateTime(currentDate);
			icrmCanteenBaseInfoService.updateEntity(BeanConvertor.copy(crmCanteenBaseInfoparam,CrmCanteenBaseInfoPO.class));
			
			iCrmCanteenExtInfoService.updateEntity(BeanConvertor.copy(crmCanteenExtInfoParam,CrmCanteenExtInfoPO.class));
		
		} catch (BizException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return RespModel.success("成功");
	}
        
	
	/**
	 * 获取编辑食堂客户资源需要的信息（企业，食堂扩展，客户资源）
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月21日 下午3:35:03
	 */
	@Override
	public Map<String,Object> getCrmCanteenBaseInfo(CrmCanteenBaseInfoParam param){
		
		Map<String,Object> map=new HashMap<>();

		CrmClientResourceParam crmClientResourceParam = param.getCrmClientResourceParam();
		Assert.notNull(crmClientResourceParam,"客户资源数据为空");
		Long crmClientResourceId = crmClientResourceParam.getId();//客户资源ID
		
		CrmClientResourcePO crmClientResourcePO = this.getById(crmClientResourceId);
		Assert.notNull(crmClientResourcePO,"客户资源信息为空");
		CrmClientResourceVO crmClientResourceVO=BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
		Integer clientType = crmClientResourceVO.getClientType();
		Long businessId = crmClientResourceVO.getBusinessId();//业务员ID
		Long agentId = crmClientResourceVO.getAgentId();//代理商ID
		//客户性质	1.散客，2.保护客户，3.正式客户
		Integer clientNature = crmClientResourceVO.getClientNature();
		Long enterpriseId = crmClientResourceVO.getEnterpriseId();//企业ID
		if(BeanUtils.isNotEmpty(businessId) && CommUtil.parseLong(businessId)>0){
			param.setBusinessId(businessId);
			CrmBusinessPO crmBusinessPO = icrmBusinessService.get(CrmBusinessPO.class, businessId);
			if(BeanUtils.isNotEmpty(crmBusinessPO)){
				Long teamId = crmBusinessPO.getTeamId();
				if(BeanUtils.isEmpty(teamId)){
					crmClientResourceVO.setBusinessId(null);
				}else{
					crmClientResourceVO.setBusinessTeamId(teamId);
				}
				
			}
		}else if(BeanUtils.isNotEmpty(agentId)){
			CrmAgentPO crmAgentPO = icrmAgentService.get(CrmAgentPO.class, agentId);
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
		
		map.putAll(icrmCanteenBaseInfoService.getAgentAndTeam(param));//获取代理商和业务员信息
		
		//如果是代理商，不允许变更
		Assert.isTrue(clientType!=CrmClientResourceClientType.AGENT.getCode(), "代理商信息不能变更");
		Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//商家ID
		CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = icrmCanteenBaseInfoService.getById(agentCanteenId);
		if(BeanUtils.isNotEmpty(crmCanteenBaseInfoPO)){
			
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
			
			
			//查询卫生许可证
			CanteenHealthPicParam canteenHealthPicParam = new CanteenHealthPicParam();
			canteenHealthPicParam.setCrmCanteenId(agentCanteenId);
			canteenHealthPicParam.setDelFlag(1);
			List<CanteenHealthPicPO> canteenHealthPicPOs = canteenHealthPicService.commonQuery(canteenHealthPicParam);
			map.put("canteenHealthPicList",JSONObject.toJSONString(canteenHealthPicPOs));
		}
		CrmCanteenExtInfoVO crmCanteenExtInfoVO = iCrmCanteenExtInfoService.findByBaseInfoId(agentCanteenId);
		if(BeanUtils.isNotEmpty(crmCanteenExtInfoVO)){
			map.put("crmCanteenExtInfoVO", crmCanteenExtInfoVO);
		}
		
		if(BeanUtils.isNotEmpty(enterpriseId)){
			CrmEnterprisePO crmEnterprisePO = iCrmEnterpriseService.findById(enterpriseId);
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
	}
    /**
     * 查询
     */
    @Override
    public Page<CrmClientResourceVO> queryPage(CrmClientResourceParam param) {
    	
    	// 查省份时 转换成 areaCode
    			if (BeanUtils.isEmpty(param.getAreaCode()) && BeanUtils.isNotEmpty(param.getProvCode())) {
    				BaseAreaPO baseAreaPO = new BaseAreaPO();
    				baseAreaPO.setParentCode(param.getProvCode().longValue());// 设置父类的code
    				List<BaseAreaPO> baseAreaPOs = iBaseAreaService.getCityByParentCode(baseAreaPO);
    				if (BeanUtils.isNotEmpty(baseAreaPOs)) {
    					List<Long> areaCodes = new ArrayList<Long>();
    					for (BaseAreaPO b : baseAreaPOs) {
    						BaseAreaPO baseAreaPO2 = new BaseAreaPO();
    						baseAreaPO2.setParentCode(b.getAreaCode());
    						List<BaseAreaPO> baList = iBaseAreaService.getCityByParentCode(baseAreaPO2);
    						if (CollectionUtils.isNotEmpty(baList)) {
    							for (BaseAreaPO b1 : baList) {
    								areaCodes.add((long) b1.getAreaCode().intValue());
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
    				List<Long> areaCodes = new ArrayList<Long>();
    				if (CollectionUtils.isNotEmpty(baList)) {
    					for (BaseAreaPO b : baList) {
    						areaCodes.add((long) b.getAreaCode().intValue());
    					}
    				}
    				param.setAreaCodes(areaCodes);
    			}


		if(BeanUtils.isNotEmpty(param.getNameLike())){
			param.setNameLike(StringUtil.likeStr(param.getNameLike()));
		}
    	
		param.setAgentNotFormalClientFlag(1);
		param.setClientNature(3);
    	ParamMap paramMap = new ParamMap(param);
    	Page<CrmClientResourcePO> crmclientresourceList = findPageByParams(CrmClientResourcePO.class,new Page<CrmClientResourcePO>(param.getOffset(),param.getLimit()),"CrmClientResourcePOMapper.queryList",paramMap);
    	List<CrmClientResourceVO> rows = new ArrayList<CrmClientResourceVO>();
    	
    	int total = 0;
    	if(crmclientresourceList.result != null){
    		//根据业务员id查询业务员姓名
    		//根据业务员id查询业务员姓名
    		//rows = BeanConvertor.convertBean(crmclientresourceList.result,ArrayList.class);
    		for(int i =0;i<crmclientresourceList.result.size();i++){
    			CrmBusinessPO crmBusinessPO = icrmBusinessService.findByBusinessId(crmclientresourceList.result.get(i).getBusinessId());
    			CrmClientResourcePO clientResourcePO = crmclientresourceList.result.get(i);
    			CrmClientResourceVO cVo = new CrmClientResourceVO();
    			cVo = BeanConvertor.convertBean(clientResourcePO, CrmClientResourceVO.class);
    			if(BeanUtils.isNotEmpty(crmBusinessPO)){
    				Integer clientNature = clientResourcePO.getClientNature();//客户性质	1.散客，2.保护客户，3.正式客户
    				if(2 != clientNature){
    					cVo.setProtectDeadline(null);
    					if(1 != clientNature){
    						cVo.setBusinessName(crmBusinessPO.getName());
    					}else{
    						cVo.setBusinessName("-");
    					}
    				}
    				if(clientNature.equals(2)){
    					cVo.setBusinessName(crmBusinessPO.getName());
    				}
    			}
    			rows.add(cVo);
    		}
    		total = crmclientresourceList.getTotalResult();
    	}    	
    	Page<CrmClientResourceVO> pageResult = new Page<CrmClientResourceVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
    	
    }
    @Override
    public Page<CrmClientResourceVO> queryPageBasic(CrmClientResourceParam param) {
		Page<CrmClientResourceVO> crmClientResourceVOPage = queryPage(param);
		if (BeanUtils.isNotEmpty(crmClientResourceVOPage)) {
			List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
			if (BeanUtils.isNotEmpty(crmClientResourceVOs)) {
				for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
					Long businessId = crmClientResourceVO.getBusinessId();// 业务员
					CrmBusinessPO crmBusinessPO = icrmBusinessService
							.getById(businessId);
					if (BeanUtils.isNotEmpty(crmBusinessPO)) {
						Long teamId = crmBusinessPO.getTeamId();
						crmClientResourceVO.setBusinessName(crmBusinessPO.getName());//业务员姓名
						crmClientResourceVO.setBusinessTeamId(teamId);//团队ID
						if (BeanUtils.isNotEmpty(teamId)) {
							CrmTeamPO crmTeamPO = iCrmTeamService
									.getById(teamId);
							if (BeanUtils.isNotEmpty(crmTeamPO)) {
								crmClientResourceVO.setTeamName(crmTeamPO
										.getName());//团队名称
							}
							System.out.println(crmTeamPO);
						}
					}
				}
			}
		}
		return crmClientResourceVOPage;
	}
    
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<CrmClientResourcePO> listByParam(CrmClientResourceParam crmclientresourceParam){
    	ParamMap paramMap = new ParamMap(crmclientresourceParam);
    	return commonDao.listByParams(CrmClientResourcePO.class,"CrmClientResourcePOMapper.queryList",paramMap);
    }
    /**
     * 根据食堂或代理商ID，查找数据
     * @author 黄霄仪
     * @date 2017年7月19日 上午10:57:29
     */
    @Override
    public CrmClientResourcePO findByAgentCanteenIdAndClientTypes(Long agentCanteenId,Integer ... clientTypes){
    	CrmClientResourceParam crmclientresourceParam=new CrmClientResourceParam();
    	crmclientresourceParam.setAgentCanteenId(agentCanteenId);
    	crmclientresourceParam.setClientTypes(Arrays.asList(clientTypes));
    	List<CrmClientResourcePO> crmClientResourcePOs = listByParam(crmclientresourceParam);
    	if(BeanUtils.isEmpty(crmClientResourcePOs)){
    		return null;
    	}
    	Assert.isTrue(crmClientResourcePOs.size()==1,"不能大于1个");
    	return crmClientResourcePOs.get(0);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmClientResourcePO po = this.get(CrmClientResourcePO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmClientResourcePOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmClientResourcePO> listPo = new ArrayList<CrmClientResourcePO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmClientResourcePO po = this.get(CrmClientResourcePO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmClientResourcePO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmClientResourceParam param){
    	Long id = param.getId();
    	if(BeanUtils.isEmpty(id)){
    		param.setId(ServiceUtil
    				.genNextIDValue(DatabaseTableNameEnum.crm_client_resource));
    	}
    	CrmClientResourcePO crmclientresourcePO = BeanConvertor.copy(param,CrmClientResourcePO.class);
    	this.save(crmclientresourcePO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmClientResourcePO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_client_resource));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmClientResourcePO po){
     	this.update(po);
    }
    @Override
    public Integer commonUpdate(CrmClientResourceParam crmClientResourceParam){
    	ParamMap paramMap=new ParamMap(crmClientResourceParam);
    	return commonDao.execute("CrmClientResourcePOMapper.commonUpdate", paramMap);
    }
	@Override
	public CrmClientResourcePO getById(Long id) {
		Assert.notNull(id, "id不能为空");
		return commonDao.get(CrmClientResourcePO.class, id);
	}
	
	/**
	 * 获取保护客户信息
	 */
	@Override
	public Boolean getProtectCustomerDetails(
			CrmClientResourceParam clientResourceParam,
			PageRespModel pageRespModel) {
		Long userId = clientResourceParam.getUserId();//登录UserId
		//获取登录用户信息
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(userId);
		crmUserPO = (CrmUserPO) icrmUserService.getByExample(crmUserPO);//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmUserPO.getUserType();
		if(1 == userType || 2 == userType){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("管理员或代理商不能操作该功能");
			pageRespModel.setTotal(0);
			return true;
		}
		
		//查询业务员信息表
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setUserId(userId);
		crmBusinessParam.setDelFlag(1);//存在
		List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		if(CollectionUtils.isEmpty(crmBusinessPOs) || crmBusinessPOs.size() > 1){//若查询结果为空或者不止一条数据
			pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("为空或不能大于1");
			pageRespModel.setTotal(0);
			return true;
		}
		
		CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
		Long businessId = crmBusinessPO.getId();//获取businessId
		Map<Long,String> businessIdWithBusinessName = null;
		//业务员(获取个人id)
		if(3 == userType){
			businessIdWithBusinessName = new HashMap<Long,String>();//业务员id:业务员Name
			businessIdWithBusinessName.put(businessId, crmBusinessPO.getName());
		}
		//业务经理(查询该业务经理所在团队及子孙团队id)
		List<Long> businessManagerClientIds = new ArrayList<Long>();//业务经理所服务客户的Id集合
		if(4 == userType){
			
			Long teamId = crmBusinessPO.getTeamId();
			/*CrmBusinessParam businessParam = new CrmBusinessParam();
			businessParam.setTeamId(teamId);
			List<CrmBusinessPO> businessPOs = crmBusinessService.commonQuery(businessParam);
			for (CrmBusinessPO businessPO : businessPOs) {
				businessIds.add(businessPO.getId());
			}*/
			//查询该业务经理所服务客户
			CrmClientResourceParam crmClientResourceParam = new CrmClientResourceParam();
			crmClientResourceParam.setBusinessId(businessId);
			crmClientResourceParam.setClientNature(2);//crmClientResourceParam
			crmClientResourceParam.setDelFlag(1);
			List<CrmClientResourcePO> crmClientResourcePOs = this.commonQuery(crmClientResourceParam);
			for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
				businessManagerClientIds.add(crmClientResourcePO.getId());
			}
			
			//对传入参数进行判断
			Long bId = clientResourceParam.getBusinessId();
			Long tId = clientResourceParam.getTeamId();
			if(null != bId || null != tId){
				CrmBusinessParam businessParam = new CrmBusinessParam();
				if(null != bId && null != tId){//传入参数包含业务员Id
					businessParam.setId(bId);
				}else if(null == bId && null != tId){//只包含teamid不包含businessId
					businessParam.setTeamId(tId);
				}
				List<CrmBusinessPO> businessPOs = icrmBusinessService.commonQuery(businessParam);
				businessIdWithBusinessName = new HashMap<Long,String>();//业务员id:业务员Name
				for (CrmBusinessPO businessPO : businessPOs) {
					businessIdWithBusinessName.put(businessPO.getId(), businessPO.getName());
				}
			}else{
			
				//查询该业务经理所在团队及子孙团队id
				CrmTeamParam crmTeamParam = new CrmTeamParam();
				crmTeamParam.setParentId(teamId);
				crmTeamParam.setDelFlag(1);
				List<CrmTeamPO> childTeamPOs = iCrmTeamService.getChildTeam(crmTeamParam);
				List<Long> crmTeamIds = new LinkedList<Long>();
				for (CrmTeamPO crmTeamPO : childTeamPOs) {
					crmTeamIds.add(crmTeamPO.getId());
				}
				
				//查询这些团队下所有员工
				CrmBusinessParam businessParam = new CrmBusinessParam();
				businessParam.setTeamIds(crmTeamIds);
				businessParam.setDelFlag(1);
				/*String condition =  clientResourceParam.getCondition();	// 查询业务员名称
				if (BeanUtils.isNotEmpty(condition)) {
					businessParam.setNameLike(condition);
				}*/
				List<CrmBusinessPO> businessPOs = icrmBusinessService.commonQuery(businessParam);
				businessIdWithBusinessName = new HashMap<Long,String>();//业务员id:业务员Name
				for (CrmBusinessPO businessPO : businessPOs) {
					businessIdWithBusinessName.put(businessPO.getId(), businessPO.getName());
				}
			}
		}
		if(null == businessIdWithBusinessName || businessIdWithBusinessName.size() < 1){
			pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		
		Set<Long> businessIdSet = businessIdWithBusinessName.keySet();
		clientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdSet));
		clientResourceParam.setClientNature(2);//保护客户
		Page<CrmClientResourceVO> crmClientResourceVOPage = this.findPageByParam(clientResourceParam);
		List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Long id = crmClientResourceVO.getId();
			//设置业务员姓名
			Long crmBusinessId = crmClientResourceVO.getBusinessId();
			if(businessIdWithBusinessName.containsKey(crmBusinessId)){
				crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(crmBusinessId));
			}
			
			//---------------设置地址开始---------------------
			String areaName="";
			if(BeanUtils.isNotEmpty(crmClientResourceVO.getAreaCode())){
				areaName=this.iBaseAreaService.getAreaName(crmClientResourceVO.getAreaCode());
			}
			if(areaName.length()>0){
				if (BeanUtils.isNotEmpty(crmClientResourceVO.getAddress())){
					crmClientResourceVO.setAddress(areaName+"/"+crmClientResourceVO.getAddress());
				}else{
					crmClientResourceVO.setAddress(areaName);
				}
			}
			//----------------设置地址结束-------------------
			if(BeanUtils.isEmpty(crmClientResourceVO.getTailNum())){
				crmClientResourceVO.setTailNum(0L);
			}
			
			
			if(4 == userType){
				//个人客户标志(用于区分业务经理的客户与其所管辖业务员客户)
				if(!businessManagerClientIds.contains(id)){
					crmClientResourceVO.setSelfClientFlag(1);
				}
			}
			
		}
		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourceVOPage.getTotalResult());
		pageRespModel.setCode(RespModel.RespCode.SUCCESS.getCode());
		return false;
	}

	/**
	 *导出查询
	* @Title: findPageSelect 
	* @author : lcl
	* @time : 2017年6月1日 上午9:47:29
	* @return Page<CrmClientResourcePO>    返回类型 
	* @throws
	 */
	@Override
	public Page<CrmClientResourceVO> findPageSelect(CrmClientResourceParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<CrmClientResourcePO> crmclientresourceList = findPageByParams(CrmClientResourcePO.class,new Page<CrmClientResourcePO>(param.getOffset(),param.getLimit()),"CrmClientResourcePOMapper.queryPage",paramMap);
    	List<CrmClientResourceVO> rows = new ArrayList<CrmClientResourceVO>();
    	
    	int total = 0;
    	if(crmclientresourceList.result != null){
    		//根据业务员id查询业务员姓名
    		//根据业务员id查询业务员姓名
    		//rows = BeanConvertor.convertBean(crmclientresourceList.result,ArrayList.class);
    		for(int i =0;i<crmclientresourceList.result.size();i++){
    			CrmBusinessPO crmBusinessPO = icrmBusinessService.findByBusinessId(crmclientresourceList.result.get(i).getBusinessId());
    			CrmClientResourcePO clientResourcePO = crmclientresourceList.result.get(i);
    			if(BeanUtils.isNotEmpty(crmBusinessPO)){
    				CrmClientResourceVO cVo = new CrmClientResourceVO();
    				cVo = BeanConvertor.convertBean(clientResourcePO, CrmClientResourceVO.class);
    				cVo.setBusinessName(crmBusinessPO.getName());
    				rows.add(i, cVo);
    				
    			}
    		}
    		total = crmclientresourceList.getTotalResult();
    	}    	
    	Page<CrmClientResourceVO> pageResult = new Page<CrmClientResourceVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
	}
	/**
	 * 分页查询
	 */
	@Override
	public Page<CrmClientResourceVO> findPageByParam(
			CrmClientResourceParam clientResourceParam) {
		ParamMap paramMap = new ParamMap(clientResourceParam);
    	Page<CrmClientResourcePO> crmclientresourceList = findPageByParams(CrmClientResourcePO.class,new Page<CrmClientResourcePO>(clientResourceParam.getOffset(),clientResourceParam.getLimit()),"CrmClientResourcePOMapper.commonQuery",paramMap);
    	List<CrmClientResourceVO> rows = new ArrayList<CrmClientResourceVO>();
    	
    	int total = 0;
    	if(crmclientresourceList.result != null){
    		for(int i=0 ; i < crmclientresourceList.result.size() ; i++){
    			CrmClientResourcePO clientResourcePO = crmclientresourceList.result.get(i);
    			if(BeanUtils.isNotEmpty(clientResourcePO) && BeanUtils.isEmpty(clientResourcePO.getTailNum())){
    				clientResourcePO.setTailNum(0L);
    			}
    			CrmClientResourceVO clientResourceVO = BeanConvertor.convertBean(clientResourcePO, CrmClientResourceVO.class);
    			rows.add(clientResourceVO);
    		}
    		total = crmclientresourceList.getTotalResult();
    	}
    	
    	Page<CrmClientResourceVO> pageResult = new Page<CrmClientResourceVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
	}

	/**
	 * 获取正式客户信息 
	 */
	@Override
	public Boolean getFormalCustomerDetails(
			CrmClientResourceParam clientResourceParam,
			PageRespModel pageRespModel) {
		Long userId = clientResourceParam.getUserId();//登录UserId
		//获取登录用户信息
		CrmUserPO crmUserPO = new CrmUserPO();
		crmUserPO.setId(userId);
		crmUserPO = (CrmUserPO) icrmUserService.getByExample(crmUserPO);//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
		Integer userType = crmUserPO.getUserType();
		if(1 == userType || 2 == userType){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("权限不匹配");
			pageRespModel.setTotal(0);
			return true;
		}
		//查询业务员信息表
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setUserId(userId);
		crmBusinessParam.setDelFlag(1);//存在
		List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		if(CollectionUtils.isEmpty(crmBusinessPOs) || crmBusinessPOs.size() > 1){//若查询结果为空或者不止一条数据
			pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
		Long businessId = crmBusinessPO.getId();//获取businessId
		
		
		Map<Long,String> businessIdWithBusinessName = null;
		//业务员(获取个人id)
		if(3 == userType){
			businessIdWithBusinessName = new HashMap<Long,String>();//业务员id:业务员Name
			businessIdWithBusinessName.put(businessId, crmBusinessPO.getName());
		}
		//业务经理(查询该业务经理所在团队及子孙团队id)
		if(4 == userType){
			
			//对传入参数进行判断
			Long bId = clientResourceParam.getBusinessId();
			Long tId = clientResourceParam.getTeamId();
			if(null != bId || null != tId){
				CrmBusinessParam businessParam = new CrmBusinessParam();
				if(null != bId && null != tId){//传入参数包含业务员Id
					businessParam.setId(bId);
				}else if(null == bId && null != tId){//只包含teamid不包含businessId
					businessParam.setTeamId(tId);
				}
				List<CrmBusinessPO> businessPOs = icrmBusinessService.commonQuery(businessParam);
				businessIdWithBusinessName = new HashMap<Long,String>();//业务员id:业务员Name
				for (CrmBusinessPO businessPO : businessPOs) {
					businessIdWithBusinessName.put(businessPO.getId(), businessPO.getName());
				}
			}else{
			
				Long teamId = crmBusinessPO.getTeamId();
				//查询该业务经理所在团队及子孙团队id
				CrmTeamParam crmTeamParam = new CrmTeamParam();
				crmTeamParam.setParentId(teamId);
				crmTeamParam.setDelFlag(1);
				List<CrmTeamPO> childTeamPOs = iCrmTeamService.getChildTeam(crmTeamParam);
				List<Long> crmTeamIds = new LinkedList<Long>();
				for (CrmTeamPO crmTeamPO : childTeamPOs) {
					crmTeamIds.add(crmTeamPO.getId());
				}
				
				//查询这些团队下所有员工
				CrmBusinessParam businessParam = new CrmBusinessParam();
				businessParam.setTeamIds(crmTeamIds);
				businessParam.setDelFlag(1);
				String condition = clientResourceParam.getCondition();	// 查询业务员名称
				if (BeanUtils.isNotEmpty(condition)) {
					businessParam.setNameLike(condition);
				}
				List<CrmBusinessPO> businessPOs = icrmBusinessService.commonQuery(businessParam);
				businessIdWithBusinessName = new HashMap<Long,String>();//业务员id:业务员Name
				for (CrmBusinessPO businessPO : businessPOs) {
					businessIdWithBusinessName.put(businessPO.getId(), businessPO.getName());
				}
			}
		}
		
		if(null == businessIdWithBusinessName || businessIdWithBusinessName.size() < 1){
			pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		
		Set<Long> businessIdSet = businessIdWithBusinessName.keySet();
		
		clientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdSet));
		clientResourceParam.setClientNature(3);//客户性质	1.散客，2.保护客户，3.正式客户
		clientResourceParam.setDelFlag(1);
		Integer[] status = {2,3,4};//客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		clientResourceParam.setClientTypes(Arrays.asList(status));
		clientResourceParam.setUserId(null);
		Page<CrmClientResourceVO> crmClientResourceVOPage = this.findPageByParam(clientResourceParam);
		List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
		List<Long> canteenClientIds = new LinkedList<Long>();
 		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			
			//设置业务员姓名
			Long crmBusinessId = crmClientResourceVO.getBusinessId();
			if(businessIdWithBusinessName.containsKey(crmBusinessId)){
				crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(crmBusinessId));
			}
			
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			if(1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				canteenClientIds.add(agentCanteenId);
			}
			
		}
		
 		//查询食堂的分账比例
 		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
 		crmSplitAssignSetParam.setCanteenIds(canteenClientIds);
 		crmSplitAssignSetParam.setDelFlag(1);
 		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
 		Map<Long,BigDecimal> canteenIdWithcanteenSplitPercent = new HashMap<>();//食堂Id:食堂分账比例
 		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
 			Long canteenId = crmSplitAssignSetPO.getCanteenId();
 			BigDecimal canteenSplitPercent = crmSplitAssignSetPO.getCanteenSplitPercent();
 			canteenIdWithcanteenSplitPercent.put(canteenId, canteenSplitPercent);
		}
 		
 		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
 			
 			//设置食堂分账比例
 			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			if(1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				if(canteenIdWithcanteenSplitPercent.containsKey(agentCanteenId)){
					crmClientResourceVO.setCanteenSplitPercent(canteenIdWithcanteenSplitPercent.get(agentCanteenId));
				}
			}
 		}
 		
 		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourceVOPage.getTotalResult());
		pageRespModel.setCode(RespModel.RespCode.SUCCESS.getCode());
		return false;
	}
	
	/**
	 * 客户列表初始化
	 * @throws ParseException 
	 */
	@Override
	public void clientListInit(CrmClientResourceParam crmClientResourceParam,
			ModelAndView modelAndView) throws ParseException {
		
		String applyMonth = crmClientResourceParam.getApplyMonth();//工作月
		modelAndView.addObject("applyMonth", applyMonth);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(BeanUtils.isNotEmpty(applyMonth)){
			
			//传该工作月起止日期,供时间查询限制
			String[] startDayAndEndDayOfMonth = DateUtil.getStartDayAndEndDayOfMonth(sdf.parse(applyMonth));
			modelAndView.addObject("startDay", startDayAndEndDayOfMonth[0]);
			modelAndView.addObject("endDay", startDayAndEndDayOfMonth[1]);
		}
		
		//根据请求参数判断
		Long agentId = crmClientResourceParam.getAgentId();//代理商Id
		Long teamId = crmClientResourceParam.getTeamId();//团队Id
		Long businessId = crmClientResourceParam.getBusinessId();//业务员Id
		if(BeanUtils.isNotEmpty(agentId) && BeanUtils.isEmptyAnd(teamId,businessId)){//代理商客户列表
			modelAndView.addObject("agentId", agentId);
		}
		if(BeanUtils.isNotEmpty(teamId) && BeanUtils.isEmptyAnd(agentId,businessId)){//团队客户列表
			modelAndView.addObject("teamId", teamId);
		}
		if(BeanUtils.isNotEmpty(businessId) && BeanUtils.isEmptyAnd(agentId,teamId)){//业务员客户列表
			modelAndView.addObject("businessId", businessId);
		}
		
	}
	@Override
	public void businessClientListInit(CrmClientResourceParam crmClientResourceParam,
			ModelAndView modelAndView) throws ParseException {

		
		//根据请求参数判断
		Long agentId = crmClientResourceParam.getAgentId();//代理商Id
		Long teamId = crmClientResourceParam.getTeamId();//团队Id
		Long businessId = crmClientResourceParam.getBusinessId();//业务员Id
		if(BeanUtils.isNotEmpty(agentId) && BeanUtils.isEmptyAnd(teamId,businessId)){//代理商客户列表
			modelAndView.addObject("agentId", agentId);
		}
		if(BeanUtils.isNotEmpty(teamId) && BeanUtils.isEmptyAnd(agentId,businessId)){//团队客户列表
			modelAndView.addObject("teamId", teamId);
		}
		if(BeanUtils.isNotEmpty(businessId) && BeanUtils.isEmptyAnd(agentId,teamId)){//业务员客户列表
			modelAndView.addObject("businessId", businessId);
		}
		
	}

	/**
	 * 获取客户列表信息
	 */
	@Override
	public Boolean getClientList(CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel) {
		Long agentId = crmClientResourceParam.getAgentId();
		Long teamId = crmClientResourceParam.getTeamId();
		Long businessId = crmClientResourceParam.getBusinessId();
		String applyMonth = crmClientResourceParam.getApplyMonth();
		String stratTimes = crmClientResourceParam.getStratTimes();
		String endTimes = crmClientResourceParam.getEndTimes();
		if(BeanUtils.isEmptyAnd(agentId,teamId,businessId) || BeanUtils.isEmptyAnd(applyMonth,stratTimes,endTimes)){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		//根据请求参数判断
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long,String>();
		if(BeanUtils.isNotEmpty(agentId) && BeanUtils.isEmptyAnd(teamId,businessId)){//代理商客户列表
			//展示该代理商自身的客户列表,查询参数
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setAgentId(agentId);
			crmBusinessParam.setDelFlag(1);
			crmBusinessParam.setType(2);//1.平台业务员，2.代理商业务员
			List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
			for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
				businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
			}
			
			
			crmClientResourceParam.setAgentId(null);
		}
		if(BeanUtils.isNotEmpty(teamId) && BeanUtils.isEmptyAnd(agentId,businessId)){//团队客户列表
			//展示该团队及子孙客户列表,查询参数
			
			//查询子孙团队及本身
			CrmTeamParam crmTeamParam = new CrmTeamParam();
			crmTeamParam.setParentId(teamId);
			crmTeamParam.setDelFlag(1);
			List<CrmTeamPO> crmTeamPOs = iCrmTeamService.getChildTeam(crmTeamParam);
			List<Long> cramTeamIds = new LinkedList<Long>();
			for (CrmTeamPO crmTeamPO : crmTeamPOs) {
				cramTeamIds.add(crmTeamPO.getId());
			}
			
			//查询团队的业务员
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setTeamIds(cramTeamIds);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
			for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
				businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
			}
			
			
			crmClientResourceParam.setTeamId(null);
		}
		if(BeanUtils.isNotEmpty(businessId) && BeanUtils.isEmptyAnd(agentId,teamId)){//业务员客户列表
			//展示该业务员自身客户列表,查询参数
			CrmBusinessPO crmBusinessPO = new CrmBusinessPO();
			crmBusinessPO.setId(businessId);
			crmBusinessPO = (CrmBusinessPO) icrmBusinessService.getByExample(crmBusinessPO);
			businessIdWithBusinessName.put(businessId, crmBusinessPO.getName());
			
			crmClientResourceParam.setBusinessId(null);
		}
		
		if(null == businessIdWithBusinessName || businessIdWithBusinessName.size() <1){
			pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		//查询时间设置(工作月和起止时间两者选其一,防止用户将日期查询条件清空)
		if(BeanUtils.isNotEmptyAnd(stratTimes,endTimes)){
			crmClientResourceParam.setApplyMonth(null);
		}else{
			if(BeanUtils.isNotEmpty(applyMonth)){
				crmClientResourceParam.setStratTimes(null);
				crmClientResourceParam.setEndTimes(null);
			}
		}
		
		//模糊查询参数设置
		Integer searchCriteria = crmClientResourceParam.getSearchCriteria();//搜索条件 1单位名称,2单位编号
		String searchContent = crmClientResourceParam.getSearchContent();//搜索内容
		if(BeanUtils.isNotEmptyAnd(searchCriteria,searchContent)){
			
			switch (searchCriteria) {
			case 1:crmClientResourceParam.setNameLike(searchContent);
				break;
			case 2:crmClientResourceParam.setClientNumLike(searchContent);
				break;
			default:
				break;
			}
		}
		
		//查询客户资源
		Set<Long> businessIdSet = businessIdWithBusinessName.keySet();
		crmClientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdSet));
		crmClientResourceParam.setClientNature(3);//客户性质	1.散客，2.保护客户，3.正式客户
		crmClientResourceParam.setDelFlag(1);
		Page<CrmClientResourceVO> crmClientResourceVOPage = this.findPageByParam(crmClientResourceParam);//查询客户资源
		List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
		List<Long> canteenIds = new LinkedList<Long>();
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				canteenIds.add(agentCanteenId);
			}
			
			//业务员名
			Long crmBusinessId = crmClientResourceVO.getBusinessId();
			if(businessIdWithBusinessName.containsKey(crmBusinessId)){
				crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(crmBusinessId));
			}
		}
		
		//查询分账比例
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(canteenIds);
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		Map<Long,BigDecimal> canteenIdWithcanteenSplitPercent = new HashMap<>();//食堂ID:食堂分账比例
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			canteenIdWithcanteenSplitPercent.put(crmSplitAssignSetPO.getCanteenId(), crmSplitAssignSetPO.getCanteenSplitPercent());
		}
		
		//统计订单量和订单金额  param:stratTimes endTimes canteenId  查crm_business_order  (暂时不做)
		
		//设置分账比例
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				if(canteenIdWithcanteenSplitPercent.containsKey(agentCanteenId)){
					crmClientResourceVO.setCanteenSplitPercent(canteenIdWithcanteenSplitPercent.get(agentCanteenId));
				}
			}
		}
		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourceVOPage.getTotalResult());
		
		return false;
	}

	/**
	 * 代理商客户列表详情
	 */
	@Override
	public Boolean agentCustomerDetails(
			CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel,CrmAgentPO crmAgentPO) {
		
		//查询该代理商直属子代理商
		CrmAgentParam crmAgentParam = new CrmAgentParam();
		crmAgentParam.setParentId(crmAgentPO.getId());
		List<CrmAgentPO> CrmAgentPOs = icrmAgentService.commonQuery(crmAgentParam);//查询该代理商直属子代理商
		if(CollectionUtils.isEmpty(CrmAgentPOs) || CrmAgentPOs.size() <1){
			pageRespModel.setCode(RespModel.RespCode.NOT_DATA.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("暂无数据");
			pageRespModel.setTotal(0);
			return true;
		}
		Map<Long,String> agentIdWithAgentName = new HashMap<Long,String>();
		for (CrmAgentPO agentPO : CrmAgentPOs) {
			agentIdWithAgentName.put(agentPO.getId(), agentPO.getName());
		}
		
		 //查询各代理商业务员
		Set<Long> agentIdSet = agentIdWithAgentName.keySet();
		
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setAgentIds(new LinkedList<Long>(agentIdSet));
		crmBusinessParam.setDelFlag(1);
		crmBusinessParam.setType(2);//1.平台业务员，2.代理商业务员
		List<CrmBusinessPO> CrmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		
		Map<Long,String> businessIdWithAgentName = new HashMap<Long,String>();//业务员Id:业务员所在代理商name
		//List<Long> businessIdList = new LinkedList<Long>();
		for (CrmBusinessPO crmBusinessPO : CrmBusinessPOs) {
			Long businessId = crmBusinessPO.getId();//业务员id
			//businessIdList.add(businessId);
			Long agentId = crmBusinessPO.getAgentId();
			if(agentIdWithAgentName.containsKey(agentId)){
				businessIdWithAgentName.put(businessId, agentIdWithAgentName.get(agentId));
			}
		}
		
		//查询客户资源详情
		Set<Long> businessIdSet = businessIdWithAgentName.keySet();
		if(CollectionUtils.isEmpty(businessIdSet) || businessIdSet.size() < 1){
			pageRespModel.setCode(RespModel.RespCode.NOT_DATA.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("暂无数据");
			pageRespModel.setTotal(0);
			return true;
		}
		crmClientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdSet));
		crmClientResourceParam.setClientNature(3);//客户性质 1.散客，2.保护客户，3.正式客户
		Page<CrmClientResourceVO> crmClientResourceVOPage = this.findPageByParam(crmClientResourceParam);//查询客户资源
		List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
		List<Long> canteenIds = new LinkedList<Long>();
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				canteenIds.add(agentCanteenId);
			}
		}
		
		//查询分账比例
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(canteenIds);
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		Map<Long,BigDecimal> canteenIdWithcanteenSplitPercent = new HashMap<>();//食堂ID:食堂分账比例
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			canteenIdWithcanteenSplitPercent.put(crmSplitAssignSetPO.getCanteenId(), crmSplitAssignSetPO.getCanteenSplitPercent());
		}
		
		
		//设置分账比例
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				if(canteenIdWithcanteenSplitPercent.containsKey(agentCanteenId)){
					crmClientResourceVO.setCanteenSplitPercent(canteenIdWithcanteenSplitPercent.get(agentCanteenId));
				}
			}
			
			//设置代理商名
			Long businessId = crmClientResourceVO.getBusinessId();
			if(businessIdWithAgentName.containsKey(businessId)){
				crmClientResourceVO.setAgentName(businessIdWithAgentName.get(businessId));
			}
			
		}
		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourceVOPage.getTotalResult());
		return false;
	}

	/**
	 * 业务员客户详情
	 */
	@Override
	public Boolean salesmanCustomerDetails(
			CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel,CrmAgentPO crmAgentPO) {
		//查询该代理商下所有员工
		Long agentId = crmAgentPO.getId();
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setDelFlag(1);
		crmBusinessParam.setType(2);//1.平台业务员，2.代理商业务员
		crmBusinessParam.setAgentId(agentId);
		List<CrmBusinessPO> CrmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		if(CollectionUtils.isEmpty(CrmBusinessPOs) || CrmBusinessPOs.size() < 1){
			pageRespModel.setCode(RespModel.RespCode.NOT_DATA.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("暂无数据");
			pageRespModel.setTotal(0);
			return true;
		}
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long,String>();
		for (CrmBusinessPO crmBusinessPO : CrmBusinessPOs) {
			businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
		}
		
		Set<Long> businessIdSet = businessIdWithBusinessName.keySet();//业务员Ids
		if(CollectionUtils.isEmpty(businessIdSet) || businessIdSet.size() < 1){
			pageRespModel.setCode(RespModel.RespCode.NOT_DATA.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("暂无数据");
			pageRespModel.setTotal(0);
			return true;
		}
		
		crmClientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdSet));
		crmClientResourceParam.setClientNature(3);//客户性质 1.散客，2.保护客户，3.正式客户
		Page<CrmClientResourceVO> crmClientResourceVOPage = this.findPageByParam(crmClientResourceParam);//查询客户资源
		List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
		List<Long> canteenIds = new LinkedList<Long>();
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				canteenIds.add(agentCanteenId);
			}
		}
		
		//查询分账比例
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(canteenIds);
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		Map<Long,BigDecimal> canteenIdWithcanteenSplitPercent = new HashMap<>();//食堂ID:食堂分账比例
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			canteenIdWithcanteenSplitPercent.put(crmSplitAssignSetPO.getCanteenId(), crmSplitAssignSetPO.getCanteenSplitPercent());
		}
		
		
		//设置分账比例
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				if(canteenIdWithcanteenSplitPercent.containsKey(agentCanteenId)){
					crmClientResourceVO.setCanteenSplitPercent(canteenIdWithcanteenSplitPercent.get(agentCanteenId));
				}
			}
			
			//设置业务员名
			Long businessId = crmClientResourceVO.getBusinessId();
			if(null != businessId && businessIdWithBusinessName.containsKey(businessId)){
				crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(businessId));
			}
		}
		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourceVOPage.getTotalResult());
		return false;
	}
	
	/**
	 * 
	* @Title: findPageList 
	* @author : lcl
	* @time : 2017年6月16日 下午4:43:24
	* @return Page<CrmClientResourcePO>    返回类型 
	* @throws
	 */
	@Override
	public Page<CrmClientResourcePO> findPageList(CrmClientResourceParam param) {
		
		if(BeanUtils.isNotEmpty(param.getProvCode()) && BeanUtils.isEmpty(param.getCityCode()) &&BeanUtils.isEmpty(param.getAreaCode())){//传入省份的areaCode
			BaseAreaPO baseAreaPO = new BaseAreaPO();
			baseAreaPO.setParentCode(param.getProvCode().longValue());//设置父类的areaCode
			List<BaseAreaPO> cAreaPOs = iBaseAreaService.getCityByParentCode(baseAreaPO);//获取到市的 code集合
			if(CollectionUtils.isNotEmpty(cAreaPOs)){
				List<Long> areaPOs = new ArrayList<Long>();
				for(BaseAreaPO ap : cAreaPOs){
					BaseAreaPO baseAreaPO2 = new BaseAreaPO();
					baseAreaPO2.setParentCode(ap.getAreaCode());//通过市级的areaCode 查询区级 
					List<BaseAreaPO> cAreaPOs2 = iBaseAreaService.getCityByParentCode(baseAreaPO2);
					if(CollectionUtils.isNotEmpty(cAreaPOs2)){
						for(BaseAreaPO ap2: cAreaPOs2){
							areaPOs.add(ap2.getAreaCode());
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
			List<Long>  areaCodes = new  ArrayList<>();
			if(CollectionUtils.isNotEmpty(cAreaPOs)){
				for(BaseAreaPO b : cAreaPOs){
					areaCodes.add(b.getAreaCode());
				}
			}
			param.setAreaCodes(areaCodes);
		}
		ParamMap paramMap = new ParamMap(param);
		Page<CrmClientResourcePO> page = findPageByParams(CrmClientResourcePO.class,new Page<CrmClientResourcePO>(param.getOffset(),param.getLimit()), "CrmClientResourcePOMapper.queryPage", paramMap);
		if(CollectionUtils.isNotEmpty(page.result)){
			return page;
		}
		return null;
	}

	/**
	 * 客户列表详情导出 
	 */
	@Override
	public List<CrmClientResourceVO> clientListExcelOut(
			CrmClientResourceParam crmClientResourceParam) {
		//查询客户信息
		List<CrmClientResourcePO> crmClientResourcePOs = this.commonQuery(crmClientResourceParam);
		
		List<CrmClientResourceVO> crmClientResourceVOs = new ArrayList<CrmClientResourceVO>();
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long, String>();//业务员Id:业务员名
		Map<Long,BigDecimal> canteenIdWithCanteenSplitPercent = new HashMap<>();//食堂Id:食堂分账比
		for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
			CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
			//获取业务员Id
			Long businessId = crmClientResourceVO.getBusinessId();
			if(!businessIdWithBusinessName.containsKey(businessId)){
				businessIdWithBusinessName.put(businessId,null);
			}
			
			//获取食堂Id
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				canteenIdWithCanteenSplitPercent.put(agentCanteenId,null);
			}
			
			crmClientResourceVOs.add(crmClientResourceVO);
		}
		
		//查询业务员名
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setIds(new LinkedList<Long>(businessIdWithBusinessName.keySet()));
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		businessIdWithBusinessName.clear();
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
		}
		
		//查询食堂分账比例
		
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(new LinkedList<Long>(canteenIdWithCanteenSplitPercent.keySet()));
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		canteenIdWithCanteenSplitPercent.clear();
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			Long canteenId = crmSplitAssignSetPO.getCanteenId();
			if(null != canteenId && !canteenIdWithCanteenSplitPercent.containsKey(canteenId)){
				BigDecimal canteenSplitPercent = crmSplitAssignSetPO.getCanteenSplitPercent();
				canteenIdWithCanteenSplitPercent.put(canteenId, canteenSplitPercent);
			}
		}
		
		//设置业务员名及食堂分账比例
		DecimalFormat df = new DecimalFormat("#####0.00");
		for(CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs){
			//设置业务员名
			Long businessId = crmClientResourceVO.getBusinessId();
			if(null != businessId && businessIdWithBusinessName.containsKey(businessId)){
				crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(businessId));
			}
			
			//设置食堂分账比
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				if(null != agentCanteenId && canteenIdWithCanteenSplitPercent.containsKey(agentCanteenId)){
					String canteenSplitPercentStr = df.format(canteenIdWithCanteenSplitPercent.get(agentCanteenId));
					crmClientResourceVO.setCanteenSplitPercentStr(canteenSplitPercentStr);
				}else{
					crmClientResourceVO.setCanteenSplitPercentStr("_");
				}
			}else{
				crmClientResourceVO.setCanteenSplitPercentStr("_");
			}
			
			//格式化客户分类,合作状态,分账比例
			String clientTypeStr ="";
			switch (clientType) {
			case 1:clientTypeStr = "代理商";
				break;
				
			case 2:clientTypeStr = "厂内食堂";
				break;
				
			case 3:clientTypeStr = "校内食堂";
				break;
				
			case 4:clientTypeStr = "独立食堂";
			break;

			default:clientTypeStr = "-";
				break;
			}
			crmClientResourceVO.setClientTypeStr(clientTypeStr);
			
			Integer clientNature = crmClientResourceVO.getClientNature();
			String   clientNatureStr = "";
			switch (clientNature) {
			case 1:clientNatureStr = "散客";
				break;
				
			case 2:clientNatureStr = "保护客户";
				break;
				
			case 3:clientNatureStr = "正式客户";
				break;

			default:clientNatureStr = "-";
				break;
			}
			crmClientResourceVO.setClientNatureStr(clientNatureStr);
			
			
		}
		
		return crmClientResourceVOs;
	}
	
	/**
	 * 根据参数查询客户资源信息
	 */
	@Override
	public List<CrmClientResourcePO> commonQuery(
			CrmClientResourceParam crmClientResourceParam) {
		ParamMap paramMap = new ParamMap(crmClientResourceParam);
		return commonDao.listByParams(CrmClientResourcePO.class, "CrmClientResourcePOMapper.commonQuery", paramMap);
	}

	/**
	 * 保护客户导出
	 */
	@Override
	public List<CrmClientResourceVO> protectCustomerDetailsExcelOut(
			CrmClientResourceParam crmClientResourceParam, Integer userType) {
		//查询客户详情
		List<CrmClientResourcePO> crmClientResourcePOs = this.commonQuery(crmClientResourceParam);
		List<CrmClientResourceVO> crmClientResourceVOs = new ArrayList<CrmClientResourceVO>();
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long, String>();//业务员Id:业务员名
		for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
			CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
			if(4 == userType){//获取业务员Id
				Long businessId = crmClientResourceVO.getBusinessId();
				if(null != businessId && !businessIdWithBusinessName.containsKey(businessId)){
					businessIdWithBusinessName.put(businessId,null);
				}
			}
			
			//格式化客户型性质
			Integer clientType = crmClientResourceVO.getClientType();
			String clientTypeStr ="";
			switch (clientType) {
			case 1:clientTypeStr = "代理商";
				break;
				
			case 2:clientTypeStr = "厂内食堂";
				break;
				
			case 3:clientTypeStr = "校内食堂";
				break;
				
			case 4:clientTypeStr = "独立食堂";
			break;

			default:clientTypeStr = "-";
				break;
			}
			
			crmClientResourceVO.setClientTypeStr(clientTypeStr);
			
			crmClientResourceVOs.add(crmClientResourceVO);
		}
		
		//查询并设置业务员名
		if(4 == userType && businessIdWithBusinessName.size() > 0){
			
			//查询业务员名
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setIds(new LinkedList<Long>(businessIdWithBusinessName.keySet()));
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
			businessIdWithBusinessName.clear();
			for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
				businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
			}
			
			//设置业务员名
			for(CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs){
				Long businessId = crmClientResourceVO.getBusinessId();
				if(null != businessId && businessIdWithBusinessName.containsKey(businessId)){
					crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(businessId));
				}
			}
		}
		
		return crmClientResourceVOs;
	}

	/**
	 * 正式客户导出
	 */
	@Override
	public List<CrmClientResourceVO> formalCustomerDetailsExcelOut(
			CrmClientResourceParam crmClientResourceParam, Integer userType) {
		//查询客户详情
		List<CrmClientResourcePO> crmClientResourcePOs = this.commonQuery(crmClientResourceParam);
		List<CrmClientResourceVO> crmClientResourceVOs = new ArrayList<CrmClientResourceVO>();
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long, String>();//业务员Id:业务员名
		Map<Long,BigDecimal> canteenIdWithCanteenSplitPercent = new HashMap<>();//食堂Id:食堂分账比
		for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
			CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
			
			if(4 == userType){//获取业务员Id
				Long businessId = crmClientResourceVO.getBusinessId();
				if(null != businessId && !businessIdWithBusinessName.containsKey(businessId)){
					businessIdWithBusinessName.put(businessId,null);
				}
			}
			
			//获取食堂Id
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				canteenIdWithCanteenSplitPercent.put(agentCanteenId,null);
			}
			
			crmClientResourceVOs.add(crmClientResourceVO);
		}
		
		
		
		//查询食堂分账比例
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(new LinkedList<Long>(canteenIdWithCanteenSplitPercent.keySet()));
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		canteenIdWithCanteenSplitPercent.clear();
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			Long canteenId = crmSplitAssignSetPO.getCanteenId();
			if(null != canteenId && !canteenIdWithCanteenSplitPercent.containsKey(canteenId)){
				BigDecimal canteenSplitPercent = crmSplitAssignSetPO.getCanteenSplitPercent();
				canteenIdWithCanteenSplitPercent.put(canteenId, canteenSplitPercent);
			}
		}
		
		
		
		//查询业务员名
		if(4 == userType && businessIdWithBusinessName.size() > 0){
			
			//查询业务员名
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setIds(new LinkedList<Long>(businessIdWithBusinessName.keySet()));
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
			businessIdWithBusinessName.clear();
			for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
				businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
			}
		}
		
		
		
		
		//设置业务员名及食堂分账比例
		DecimalFormat df = new DecimalFormat("#####0.00");
		for(CrmClientResourceVO clientResourceVO : crmClientResourceVOs){
			
			if(4 == userType && businessIdWithBusinessName.size() > 0){
				//设置业务员名
				Long businessId = clientResourceVO.getBusinessId();
				if(null != businessId && businessIdWithBusinessName.containsKey(businessId)){
					clientResourceVO.setBusinessName(businessIdWithBusinessName.get(businessId));
				}
			}
			
			
			//设置食堂分账比
			Integer cType = clientResourceVO.getClientType();
			if(null != cType && 1 != cType){
				Long agentCanteenId = clientResourceVO.getAgentCanteenId();
				if(null != agentCanteenId && canteenIdWithCanteenSplitPercent.containsKey(agentCanteenId)){
					String canteenSplitPercentStr = df.format(canteenIdWithCanteenSplitPercent.get(agentCanteenId));
					clientResourceVO.setCanteenSplitPercentStr(canteenSplitPercentStr);
				}else{
					clientResourceVO.setCanteenSplitPercentStr("-");
				}
			}else{
				clientResourceVO.setCanteenSplitPercentStr("-");
			}
			
			//格式化客户型性质
			String clientTypeStr ="";
			switch (cType) {
			case 1:clientTypeStr = "代理商";
				break;
				
			case 2:clientTypeStr = "厂内食堂";
				break;
				
			case 3:clientTypeStr = "校内食堂";
				break;
				
			case 4:clientTypeStr = "独立食堂";
			break;

			default:clientTypeStr = "-";
				break;
			}
			
			clientResourceVO.setClientTypeStr(clientTypeStr);
		}
		
		
		return crmClientResourceVOs;
	}

	/**
	 * 代理商客户导出
	 */
	@Override
	public List<CrmClientResourceVO> agentCustomerDetailsExcleOut(
			CrmClientResourceParam crmClientResourceParam) {
		//查询客户信息
		List<CrmClientResourcePO> crmClientResourcePOs = this.commonQuery(crmClientResourceParam);
		
		List<CrmClientResourceVO> crmClientResourceVOs = new ArrayList<CrmClientResourceVO>();
		Map<Long,BigDecimal> canteenIdWithCanteenSplitPercent = new HashMap<>();//食堂Id:食堂分账比
		Map<Long,String> businessIdWithAgentName = new HashMap<Long, String>();//业务员Id:业务员所属代理商名
		for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
			CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
			
			//获取业务员Id
			Long businessId = crmClientResourceVO.getBusinessId();
			if(null != businessId && !businessIdWithAgentName.containsKey(businessId)){
				businessIdWithAgentName.put(businessId, null);
			}
			
			
			//获取食堂Id
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				canteenIdWithCanteenSplitPercent.put(agentCanteenId,null);
			}
			
			crmClientResourceVOs.add(crmClientResourceVO);
		}
		
		
		
		
		//查询业务员信息
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setIds(new LinkedList<Long>(businessIdWithAgentName.keySet()));
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		Map<Long,Long> businessIdWithAngentId = new HashMap<Long, Long>();//业务员Id:所属代理id
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			Long agentId = crmBusinessPO.getAgentId();
			Long id = crmBusinessPO.getId();
			businessIdWithAngentId.put(id, agentId);
		}
		
		//查询代理商信息
		Collection<Long> values = businessIdWithAngentId.values();
		CrmAgentParam crmAgentParam = new CrmAgentParam();
		crmAgentParam.setIds(new LinkedList<Long>(values));
		crmAgentParam.setDelFlag(1);
		List<CrmAgentPO> crmAgentPOs = icrmAgentService.commonQuery(crmAgentParam);
		Map<Long,String> agentIdWithAgentName = new HashMap<Long, String>();//代理商Id:代理商名
		for (CrmAgentPO crmAgentPO : crmAgentPOs) {
			Long id = crmAgentPO.getId();
			String name = crmAgentPO.getName();
			agentIdWithAgentName.put(id, name);
		}
		//整理业务员与代理关系
		businessIdWithAgentName.clear();
		for(Long BusinessId : businessIdWithAngentId.keySet()){
			Long agentId = businessIdWithAngentId.get(BusinessId);
			if(agentIdWithAgentName.containsKey(agentId)){
				String agentName = agentIdWithAgentName.get(agentId);
				businessIdWithAgentName.put(BusinessId, agentName);
			}
		}
		
		//查询食堂分账比例
		
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(new LinkedList<Long>(canteenIdWithCanteenSplitPercent.keySet()));
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		canteenIdWithCanteenSplitPercent.clear();
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			Long canteenId = crmSplitAssignSetPO.getCanteenId();
			if(null != canteenId && !canteenIdWithCanteenSplitPercent.containsKey(canteenId)){
				BigDecimal canteenSplitPercent = crmSplitAssignSetPO.getCanteenSplitPercent();
				canteenIdWithCanteenSplitPercent.put(canteenId, canteenSplitPercent);
			}
		}
		
		
		
		//设置食堂分账比例及代理商名
		DecimalFormat df = new DecimalFormat("#####0.00");
		for(CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs){
			
			//设置食堂分账比
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				if(null != agentCanteenId && canteenIdWithCanteenSplitPercent.containsKey(agentCanteenId)){
					String canteenSplitPercentStr = df.format(canteenIdWithCanteenSplitPercent.get(agentCanteenId));
					crmClientResourceVO.setCanteenSplitPercentStr(canteenSplitPercentStr);
				}else{
					crmClientResourceVO.setCanteenSplitPercentStr("_");
				}
			}else{
				crmClientResourceVO.setCanteenSplitPercentStr("_");
			}
			
			//设置业务员所在代理商
			Long businessId = crmClientResourceVO.getBusinessId();
			if(null != businessId && businessIdWithAgentName.containsKey(businessId)){
				crmClientResourceVO.setAgentName(businessIdWithAgentName.get(businessId));
			}
			
			//格式化客户分类,合作状态,分账比例
			String clientTypeStr ="";
			switch (clientType) {
			case 1:clientTypeStr = "代理商";
				break;
				
			case 2:clientTypeStr = "厂内食堂";
				break;
				
			case 3:clientTypeStr = "校内食堂";
				break;
				
			case 4:clientTypeStr = "独立食堂";
			break;

			default:clientTypeStr = "-";
				break;
			}
			crmClientResourceVO.setClientTypeStr(clientTypeStr);
			
			
		}
		
		
		return crmClientResourceVOs;
	}

	/**
	 * 业务员客户导出
	 */
	@Override
	public List<CrmClientResourceVO> salesmanCustomerDetailsExcleOut(
			CrmClientResourceParam crmClientResourceParam) {
		//查询客户信息
		List<CrmClientResourcePO> crmClientResourcePOs = this.commonQuery(crmClientResourceParam);
		
		List<CrmClientResourceVO> crmClientResourceVOs = new ArrayList<CrmClientResourceVO>();
		Map<Long,BigDecimal> canteenIdWithCanteenSplitPercent = new HashMap<>();//食堂Id:食堂分账比
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long, String>();//业务员Id:业务员名
		for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
			CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
			
			//获取业务员Id
			Long businessId = crmClientResourceVO.getBusinessId();
			if(null != businessId && !businessIdWithBusinessName.containsKey(businessId)){
				businessIdWithBusinessName.put(businessId, null);
			}
			
			
			//获取食堂Id
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				canteenIdWithCanteenSplitPercent.put(agentCanteenId,null);
			}
			
			crmClientResourceVOs.add(crmClientResourceVO);
		}
		
		
		
		//查询业务员信息
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setIds(new LinkedList<Long>(businessIdWithBusinessName.keySet()));
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		businessIdWithBusinessName.clear();
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
		}
		
		//查询食堂分账比例
		
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(new LinkedList<Long>(canteenIdWithCanteenSplitPercent.keySet()));
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		canteenIdWithCanteenSplitPercent.clear();
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			Long canteenId = crmSplitAssignSetPO.getCanteenId();
			if(null != canteenId && !canteenIdWithCanteenSplitPercent.containsKey(canteenId)){
				BigDecimal canteenSplitPercent = crmSplitAssignSetPO.getCanteenSplitPercent();
				canteenIdWithCanteenSplitPercent.put(canteenId, canteenSplitPercent);
			}
		}
		
		
		//设置业务员名及食堂分账比例
		DecimalFormat df = new DecimalFormat("#####0.00");
		for(CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs){
			//设置业务员名
			Long businessId = crmClientResourceVO.getBusinessId();
			if(null != businessId && businessIdWithBusinessName.containsKey(businessId)){
				crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(businessId));
			}
			
			//设置食堂分账比
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				if(null != agentCanteenId && canteenIdWithCanteenSplitPercent.containsKey(agentCanteenId)){
					String canteenSplitPercentStr = df.format(canteenIdWithCanteenSplitPercent.get(agentCanteenId));
					crmClientResourceVO.setCanteenSplitPercentStr(canteenSplitPercentStr);
				}else{
					crmClientResourceVO.setCanteenSplitPercentStr("_");
				}
			}else{
				crmClientResourceVO.setCanteenSplitPercentStr("_");
			}
			
			//格式化客户分类,合作状态,分账比例
			String clientTypeStr ="";
			switch (clientType) {
			case 1:clientTypeStr = "代理商";
				break;
				
			case 2:clientTypeStr = "厂内食堂";
				break;
				
			case 3:clientTypeStr = "校内食堂";
				break;
				
			case 4:clientTypeStr = "独立食堂";
			break;

			default:clientTypeStr = "-";
				break;
			}
			crmClientResourceVO.setClientTypeStr(clientTypeStr);
			
		}
		
		return crmClientResourceVOs;
	}

	/**
	 * 根据代理商id统计客户数量
	 */
	@Override
	public BigDecimal getAgentNumber(CrmClientResourceParam crmClientResourceParam) {
		ParamMap paramMap = new ParamMap(crmClientResourceParam);
		BigDecimal total = sqlSession.selectOne("CrmClientResourcePOMapper.getAgentNumber", paramMap);
		if (BeanUtils.isEmpty(total)) {
			return BigDecimal.ZERO;
		}
		return total;
	}


	/**
	 * 食堂分账 
	* @Title: findPageBycanteenClien 
	* @author : lcl
	* @time : 2017年6月23日 上午9:23:12
	* @return Page<CrmClientResourceVO>    返回类型 
	* @throws
	 */
	@Override
	public Page<CrmClientResourceVO> findPageBycanteenClien(CrmClientResourceParam crmClientResourceParam) {
			ParamMap paramMap = new ParamMap(crmClientResourceParam);
			Page<CrmClientResourcePO> page = findPageByParams(CrmClientResourcePO.class,new Page<CrmClientResourcePO>(crmClientResourceParam.getOffset(),crmClientResourceParam.getLimit()), "CrmClientResourcePOMapper.findPageBycanteenClien", paramMap);
			List<CrmClientResourcePO> cList = page.result;
			List<CrmClientResourceVO> resourceVOs = new ArrayList<CrmClientResourceVO>();
			if(CollectionUtils.isNotEmpty(cList)){//循环封装VO
				
				for(int i=0;i<cList.size();i++){
					if(cList.get(i).getClientType()>1){
						CrmClientResourceVO clientResourceVO = BeanConvertor.convertBean(cList.get(i), CrmClientResourceVO.class);
						if(BeanUtils.isNotEmpty(clientResourceVO.getCustomerAttribute()) && clientResourceVO.getCustomerAttribute().equals(1)){
							clientResourceVO.setCustomerAttributeName("代理商客户");
						}else if( BeanUtils.isNotEmpty(clientResourceVO.getCustomerAttribute()) &&clientResourceVO.getCustomerAttribute().equals(2)){
							clientResourceVO.setCustomerAttributeName("平台客户");
						}
						
						if(clientResourceVO.getClientType().equals(2)){
							clientResourceVO.setClientTypeName("厂内食堂");
						}else if(clientResourceVO.getClientType().equals(3)){
							clientResourceVO.setClientTypeName("校内食堂");
						}else if(clientResourceVO.getClientType().equals(4)){
							clientResourceVO.setClientTypeName("独立食堂");
						}
						
						Long canteenId = clientResourceVO.getId();
						
						//分账金额 = 实付金额*分账比例
						if(BeanUtils.isNotEmpty(canteenId)){
							//分账比例  分账抽成表 商家id
							CrmSplitAssignSetPO assignSetPO = icrmSplitAssignSetService.findByCanteenId(canteenId);
							if(BeanUtils.isNotEmpty(assignSetPO)){
								clientResourceVO.setCanteenSplitPercent(assignSetPO.getCanteenSplitPercent());//食堂分账比例
							}else{
								clientResourceVO.setCanteenSplitPercent(BigDecimal.ZERO);//食堂分账比例
							}
							//查询订单数量
							OrderParam orderParam = new  OrderParam();
							orderParam.setCanteenId(canteenId);
							List<OrderPO> orderPOs = orderService.findListByParam(orderParam);
							//计算实付总金额
							if(CollectionUtils.isNotEmpty(orderPOs)){
								clientResourceVO.setOrderNum((long)orderPOs.size());//统计订单数量
								BigDecimal sumMomey = new BigDecimal("0");
								for(int j = 0 ; j < orderPOs.size() ; j++){//不知道合不合理
									sumMomey=sumMomey.add(orderPOs.get(j).getRealMoney());
								}
								if(BeanUtils.isNotEmpty(assignSetPO)){
									BigDecimal splitMoney =assignSetPO.getCanteenSplitPercent().divide(new BigDecimal(100));//转化成百分比
									clientResourceVO.setSplitMomey(sumMomey.multiply(splitMoney));//总金额*分账比例
									clientResourceVO.setSumMomey(sumMomey);//订单实收总金额
									
								}
							}else{
								clientResourceVO.setOrderNum(0L);//统计订单数量
								clientResourceVO.setSplitMomey(BigDecimal.ZERO);//总金额*分账比例
								clientResourceVO.setSumMomey(BigDecimal.ZERO);//订单实收总金额
							}
						}
						
						
						resourceVOs.add(clientResourceVO);
						
					}
					
				}
					}
			Page<CrmClientResourceVO> cPage = new Page<CrmClientResourceVO>();
			cPage.result=resourceVOs;
			cPage.setTotalResult(page.getTotalResult());
			return cPage;
	}

	
	


	/**
	 * 根据businessId 统计正式客户数量
	 */
	@Override
	public BigDecimal getBusinessNumber(CrmClientResourceParam crmClientResourceParam) {
		ParamMap paramMap = new ParamMap(crmClientResourceParam);
		BigDecimal total = sqlSession.selectOne("CrmClientResourcePOMapper.getBusinessNumber", paramMap);
		if (BeanUtils.isEmpty(total)) {
			return BigDecimal.ZERO;
		}
		return total;
	}

	/**
	 * 客户资源查看
	 */
	@Override
	public Boolean customerResourceLook(Long clientId,ModelAndView modelAndView) {
		
		if(null == clientId){
			return true;
		}
		//查询客户资源信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) this.getByExample(crmClientResourcePO);
		if(BeanUtils.isEmpty(crmClientResourcePO)){
			return true;
		}
		//查询业务员及代理商名
		Long businessId = crmClientResourcePO.getBusinessId();
		CrmBusinessPO crmBusinessPO = new CrmBusinessPO();
		if(CommUtil.parseLong(businessId)>0){
			crmBusinessPO.setId(businessId);
			crmBusinessPO = (CrmBusinessPO) icrmBusinessService.getByExample(crmBusinessPO);
		}
		String businessName=null;
		Long agentId=null;
		if(BeanUtils.isNotEmpty(crmBusinessPO)){
			businessName= crmBusinessPO.getName();
			agentId = crmBusinessPO.getAgentId();
		}
		String agentName = null;
		if(null != agentId){
			CrmAgentPO crmAgentPO = new CrmAgentPO();
			crmAgentPO.setId(agentId);
			crmAgentPO = (CrmAgentPO) icrmAgentService.getByExample(crmAgentPO);
			agentName = crmAgentPO.getName();
		}
		
		
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		String clientTypeStr = "";
		switch (clientType) {
		case 1:clientTypeStr = "代理商";
			break;
		case 2:clientTypeStr = "厂内食堂";
			break;
		
		case 3:clientTypeStr = "校内食堂";
			break;
		
		case 4:clientTypeStr = "独立食堂";
			break;
		
		default:clientTypeStr = "-";
			break;
		}
		
		Integer clientNature = crmClientResourcePO.getClientNature();//客户性质	1.散客，2.保护客户，3.正式客户
		String clientNatureStr = "";
		switch (clientNature) {
		case 1:clientNatureStr = "散客";
			break;
		case 2:clientNatureStr = "保护客户";
			break;
		
		case 3:clientNatureStr = "正式客户";
			break;
		
		default:clientNatureStr = "-";
			break;
		}
		CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
		crmClientResourceVO.setClientTypeStr(clientTypeStr);
		crmClientResourceVO.setClientNatureStr(clientNatureStr);
		crmClientResourceVO.setBusinessName(businessName);
		crmClientResourceVO.setAgentName(agentName);
		//---------------设置地址开始---------------------
		String areaName="";
		if(BeanUtils.isNotEmpty(crmClientResourceVO.getAreaCode())){
			areaName=this.iBaseAreaService.getAreaName(crmClientResourceVO.getAreaCode());
		}
		if(areaName.length()>0){
			if (BeanUtils.isNotEmpty(crmClientResourceVO.getAddress())){
				crmClientResourceVO.setAddress(areaName+"/"+crmClientResourceVO.getAddress());
			}else{
				crmClientResourceVO.setAddress(areaName);
			}
		}
		//----------------设置地址结束-------------------
		modelAndView.addObject("clientResourceVO", crmClientResourceVO);
		
		
		
		if(!(null != clientType && null != clientNature)){
			return true;
		}
		modelAndView.addObject("imgViewAddress", imgViewAddress);
		
		//查看食堂信息或者代理商信息
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();
			if(1 == clientType){//查询代理商信息
				CrmAgentPO crmAgentPO = new CrmAgentPO();
				crmAgentPO.setId(agentCanteenId);
				crmAgentPO = (CrmAgentPO) icrmAgentService.getByExample(crmAgentPO);
				if(BeanUtils.isNotEmpty(crmAgentPO)){
					modelAndView.addObject("agentPO",crmAgentPO);
				}
			}else{//查询食堂信息
				CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = new CrmCanteenBaseInfoPO();
				crmCanteenBaseInfoPO.setId(agentCanteenId);
				crmCanteenBaseInfoPO = (CrmCanteenBaseInfoPO) icrmCanteenBaseInfoService.getByExample(crmCanteenBaseInfoPO);
				if(BeanUtils.isNotEmpty(crmCanteenBaseInfoPO)){
					CrmCanteenBaseInfoVO crmCanteenBaseInfoVO = BeanConvertor.convertBean(crmCanteenBaseInfoPO, CrmCanteenBaseInfoVO.class);
					//查询食堂扩展信息
					CrmCanteenExtInfoParam crmCanteenExtInfoParam = new CrmCanteenExtInfoParam();
					crmCanteenExtInfoParam.setBaseInfoId(agentCanteenId);
					List<CrmCanteenExtInfoPO> crmCanteenExtInfoPOs = iCrmCanteenExtInfoService.listByParam(crmCanteenExtInfoParam);
					if(CollectionUtils.isNotEmpty(crmCanteenExtInfoPOs)){
						CrmCanteenExtInfoPO crmCanteenExtInfoPO = crmCanteenExtInfoPOs.get(0);
						crmCanteenBaseInfoVO.setLogo(crmCanteenExtInfoPO.getLogo());
						crmCanteenBaseInfoVO.setHeadRealname(crmCanteenExtInfoPO.getHeadRealname());
						crmCanteenBaseInfoVO.setHeadPhone(crmCanteenExtInfoPO.getHeadPhone());
						crmCanteenBaseInfoVO.setHeadIdcard(crmCanteenExtInfoPO.getHeadIdcard());
						crmCanteenBaseInfoVO.setHeadIdcardPic(crmCanteenExtInfoPO.getHeadIdcardPic());
						crmCanteenBaseInfoVO.setBusinessPic(crmCanteenExtInfoPO.getBusinessPic());
					}
					
					//查询服务企业或学校信息
					if(2 == clientType || 3 == clientType){
						CrmEnterpriseCanteenParam crmEnterpriseCanteenParam = new CrmEnterpriseCanteenParam();
						crmEnterpriseCanteenParam.setCanteenId(agentCanteenId);
						crmEnterpriseCanteenParam.setDelFlag(1);
						List<CrmEnterpriseCanteenPO> crmEnterpriseCanteenPOs = iCrmEnterpriseCanteenService.listByParam(crmEnterpriseCanteenParam);
						if(CollectionUtils.isNotEmpty(crmEnterpriseCanteenPOs)){
							CrmEnterpriseCanteenPO crmEnterpriseCanteenPO = crmEnterpriseCanteenPOs.get(0);
							CrmEnterprisePO crmEnterprisePO = new CrmEnterprisePO();
							crmEnterprisePO.setId(crmEnterpriseCanteenPO.getEnterpriseId());
							crmEnterprisePO = (CrmEnterprisePO) iCrmEnterpriseService.getByExample(crmEnterprisePO);
							if(BeanUtils.isNotEmpty(crmEnterprisePO)){
								crmCanteenBaseInfoVO.setEname(crmEnterprisePO.geteName());
								Integer geteStyle = CommUtil.parseInteger(crmEnterprisePO.geteStyle());//单位类型 0.公司/企业/工厂 1.学校，2.政府机构，3.事业单位，4.其它
								String eStyle = "";
								switch (geteStyle) {
								case 0: eStyle="公司/企业/工厂";
									break;
									
								case 1:eStyle="学校";
									break;
									
								case 2:eStyle="政府机构";
									break;
									
								case 3:eStyle="事业单位";
									break;
									
								case 4:eStyle="其它";
									break;

								default:eStyle="-";
									break;
								}
								crmCanteenBaseInfoVO.setEstyle(eStyle);
								crmCanteenBaseInfoVO.setEaddress(crmEnterprisePO.getAddress());
							}
						}
					}
					modelAndView.addObject("canteenBaseInfoVO", crmCanteenBaseInfoVO);
				}
			}
		
		return false;
	}

	/**
	 * 踢出(保护客户)
	 */
	@Transactional
	@Override
	public Boolean kickOutClient(Long clientId,Long userId,RespModel respModel) {
		//判断clientId
		if(null == clientId){
			respModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			respModel.setDesc("参数异常");
			return true;
		}

		//查询客户信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) this.getByExample(crmClientResourcePO);
		if(BeanUtils.isEmpty(crmClientResourcePO)){
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("系统出错");
			return true;
		}
		
		Integer clientNature = crmClientResourcePO.getClientNature();//客户性质	1.散客，2.保护客户，3.正式客户
		if(null == clientNature || 2 != clientNature){
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("系统出错");
			return true;
		}
		//更新客户信息
		CrmClientResourceParam clientResourceParam = new CrmClientResourceParam();
		clientResourceParam.setId(clientId);
		clientResourceParam.setClientNature(1);//设置为散客
		//clientResourceParam.setLastProtectBusinessId(crmClientResourcePO.getBusinessId());//上一次保护关联的业务员Id
		//clientResourceParam.setRelieveProtectTime(new Date());//解除保护关系(踢出,成为散客)时间
		//客户类型,业务员Id,代理商Id等需置空
		logger.info("#####CrmClientResourceServiceImpl###kickOutClient##更新clientResourceParam:"+clientResourceParam.toString());
		this.changeProtectedClientToIndividualTraveler(clientResourceParam);
		Long businessId = crmClientResourcePO.getBusinessId();
		wolverineJedisCluster.set("protectedClientOut"+clientId+"businessId"+businessId, businessId.toString());
		wolverineJedisCluster.setex("protectedClientOut"+clientId+"businessId"+businessId, 72*60*60, businessId.toString());
		//cacheService.set(String, "protectedClientOut"+clientId+"businessId"+businessId, businessId, 72*60*60);
		
		
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();
		if(1 == clientType){//代理商
			CrmAgentParam crmAgentParam = new CrmAgentParam();
			crmAgentParam.setId(agentCanteenId);
			crmAgentParam.setAgentStatus(0);
			crmAgentParam.setJuniorDivide(0);
			logger.info("#####CrmClientResourceServiceImpl###kickOutClient##更新crmAgentParam:"+crmAgentParam.toString());
			icrmAgentService.changeProtectedClientToIndividualTraveler(crmAgentParam);
			
			//如存在申请信息,则删除
			CrmAgentApplyParam crmAgentApplyParam = new CrmAgentApplyParam();
			crmAgentApplyParam.setAgentId(agentCanteenId);
			crmAgentApplyParam.setDelFlag(0);
			logger.info("#####CrmClientResourceServiceImpl###kickOutClient##删除crmAgentApplyParam:"+crmAgentApplyParam.toString());
			iCrmAgentApplyService.deleteAgentApplyEntity(crmAgentApplyParam);

			
		}else{//食堂
			CrmCanteenBaseInfoParam crmCanteenBaseInfoParam = new CrmCanteenBaseInfoParam();
			crmCanteenBaseInfoParam.setId(agentCanteenId);
			crmCanteenBaseInfoParam.setUpdaterUid(userId);
			crmCanteenBaseInfoParam.setUpdateTime(new Date());
			crmCanteenBaseInfoParam.setClientNature(1);//客户性质	1.散客，2.保护客户，3.正式客户
			logger.info("#####CrmClientResourceServiceImpl###kickOutClient##更新crmCanteenBaseInfoParam:"+crmCanteenBaseInfoParam.toString());
			icrmCanteenBaseInfoService.changeProtectedClientToIndividualTraveler(crmCanteenBaseInfoParam);
			
			//如存在申请信息,则删除
			CrmCanteenApplyParam crmCanteenApplyParam = new CrmCanteenApplyParam();
			crmCanteenApplyParam.setCanteenId(agentCanteenId);
			crmCanteenApplyParam.setDelFlag(0);
			logger.info("#####CrmClientResourceServiceImpl###kickOutClient##删除crmCanteenApplyParam:"+crmCanteenApplyParam.toString());
			iCrmCanteenApplyService.deleteCanteenApplyEntity(crmCanteenApplyParam);
			
		}
		
		
		return false;
	}
	
	/**
	 * 保护客户更新为散客
	 */
	@Override
	public int changeProtectedClientToIndividualTraveler(
			CrmClientResourceParam clientResourceParam) {
		ParamMap paramMap = new ParamMap(clientResourceParam);
//		paramMap.put("id", clientResourcePO.getId());
//		paramMap.put("clientNature", clientResourcePO.getClientNature());
		return commonDao.execute("CrmClientResourcePOMapper.changeProtectedClientToIndividualTraveler", paramMap );
	}

	/**
	 * 根据主键查询
	* @Title: findById 
	* @author : lcl
	* @time : 2017年6月29日 上午10:41:04
	* @return CrmClientResourcePO    返回类型 
	* @throws
	 */
	@Override
	public CrmClientResourcePO findById(Long id) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("id", id);
		List<CrmClientResourcePO> clientResourcePOs = listByParams(CrmClientResourcePO.class, "CrmClientResourcePOMapper.findById", paramMap);
		if(CollectionUtils.isNotEmpty(clientResourcePOs)){
			return clientResourcePOs.get(0);
		}
		
		return null;
	}
	//根据业务员id查询客户资源
	@Override
	public List<CrmClientResourcePO> findByBusinessId(Long id) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("businessId", id);
		List<CrmClientResourcePO> clientResourcePOs = listByParams(CrmClientResourcePO.class, "CrmClientResourcePOMapper.findByBusinessId", paramMap);
		if(CollectionUtils.isNotEmpty(clientResourcePOs)){
			return clientResourcePOs;
		}
		return null;
	}
	//根据业务员id查询客户资源
	@Override
	public  List<CrmClientResourcePO> getByBusinessId(CrmClientResourceParam clientResourceParam) {
		clientResourceParam.setClientNature(3);
		ParamMap paramMap = new  ParamMap(clientResourceParam);
		List<CrmClientResourcePO> clientResourcePOs = listByParams(CrmClientResourcePO.class, "CrmClientResourcePOMapper.findByBusinessId", paramMap);
		if(CollectionUtils.isNotEmpty(clientResourcePOs)){
			return clientResourcePOs;
		}
		return null;
	}

	/**
	 * 业务员目标(业务员考核)查看客户列表详情
	 */
	@Override
	public Boolean getBusinessClientList(
			CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel) {
		Long businessId = crmClientResourceParam.getBusinessId();
		if(BeanUtils.isEmpty(businessId)){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		
		//模糊查询参数设置
		crmClientResourceParam.setNameLike(crmClientResourceParam.getSearchContent());
		
		//查询客户资源
		crmClientResourceParam.setClientNature(3);//客户性质	1.散客，2.保护客户，3.正式客户
		crmClientResourceParam.setDelFlag(1);
		Page<CrmClientResourceVO> crmClientResourceVOPage = this.findPageByParam(crmClientResourceParam);//查询客户资源
		if(CollectionUtils.isEmpty(crmClientResourceVOPage.result)){
			return true;
		}
		List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
		List<Long> canteenIds = new LinkedList<Long>();
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				canteenIds.add(agentCanteenId);
			}
		}
		
		//查询分账比例
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(canteenIds);
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		Map<Long,BigDecimal> canteenIdWithcanteenSplitPercent = new HashMap<>();//食堂ID:食堂分账比例
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			canteenIdWithcanteenSplitPercent.put(crmSplitAssignSetPO.getCanteenId(), crmSplitAssignSetPO.getCanteenSplitPercent());
		}
		
		//统计订单量和订单金额  param:stratTimes endTimes canteenId  查crm_business_order  (暂时不做)
		CrmCanteenBaseInfoParam crmCanteenBaseInfoParam = new CrmCanteenBaseInfoParam();
		crmCanteenBaseInfoParam.setIds(canteenIds);
		List<CrmCanteenBaseInfoPO> crmCanteenBaseInfoPOs = icrmCanteenBaseInfoService.commonQuery(crmCanteenBaseInfoParam);
		List<Long> canteenIdList = new LinkedList<Long>();//canteen_base_info的主键Id
		for (CrmCanteenBaseInfoPO crmCanteenBaseInfoPO : crmCanteenBaseInfoPOs) {
			Long canteenId = crmCanteenBaseInfoPO.getCanteenId();
			canteenIdList.add(canteenId);
		}
		
		//查询订单信息
		OrderParam orderParam = new OrderParam();
		orderParam.setApplyMonth(crmClientResourceParam.getApplyMonth());
		orderParam.setCanteenIds(canteenIdList);
		Integer[] status = {2,3,4,5,6,8,9};//查询2、已支付  3、已接单、4已送达、5已完成、6已评价、8待评价、9待取餐
		orderParam.setStatusList(Arrays.asList(status));
		Map<Long,List<Object>> canteenIdWithOrderTotalNumAndOrderTotalMoney = orderService.statisticsOrderTotalNumAndOrderTotalMoney(orderParam);
		Map<Long,List<Object>> crmCanteenIdWithOrderTotalNumAndOrderTotalMoney = new HashMap<Long, List<Object>>();
		for (CrmCanteenBaseInfoPO crmCanteenBaseInfoPO : crmCanteenBaseInfoPOs) {
			Long canteenId = crmCanteenBaseInfoPO.getCanteenId();
			if(canteenIdWithOrderTotalNumAndOrderTotalMoney.containsKey(canteenId)) {
				Long id = crmCanteenBaseInfoPO.getId();
				crmCanteenIdWithOrderTotalNumAndOrderTotalMoney.put(id, canteenIdWithOrderTotalNumAndOrderTotalMoney.get(canteenId));
			}
		}
		canteenIdWithOrderTotalNumAndOrderTotalMoney.clear();
		canteenIdWithOrderTotalNumAndOrderTotalMoney = null;
		
		//设置分账比例与订单量订单金额
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				//设置分账比例
				if(canteenIdWithcanteenSplitPercent.containsKey(agentCanteenId)){
					crmClientResourceVO.setCanteenSplitPercent(canteenIdWithcanteenSplitPercent.get(agentCanteenId));
				}
				//设置订单量订单金额
				if(crmCanteenIdWithOrderTotalNumAndOrderTotalMoney.containsKey(agentCanteenId)){
					List<Object> list = crmCanteenIdWithOrderTotalNumAndOrderTotalMoney.get(agentCanteenId);
					Integer orderNum = (Integer) list.get(0);
					BigDecimal orderTotalMoney = (BigDecimal) list.get(1);
					crmClientResourceVO.setOrderTotalNum(orderNum);
					crmClientResourceVO.setOrderTotalMoney(orderTotalMoney);
				}
			}
		}
		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourceVOPage.getTotalResult());
		
		return false;
	}

	/**
	 * 业务员客户列表(导出) 
	 */
	@Override
	public List<CrmClientResourceVO> businessClientListExcleOut(
			CrmClientResourceParam crmClientResourceParam) {
		//查询客户信息
		List<CrmClientResourcePO> crmClientResourcePOs = this.commonQuery(crmClientResourceParam);
		
		List<CrmClientResourceVO> crmClientResourceVOs = new ArrayList<CrmClientResourceVO>();
		Map<Long,BigDecimal> canteenIdWithCanteenSplitPercent = new HashMap<>();//食堂Id:食堂分账比
		for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
			CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
			
			//获取食堂Id
			Integer clientType = crmClientResourceVO.getClientType();
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				canteenIdWithCanteenSplitPercent.put(agentCanteenId,null);
			}
			
			crmClientResourceVOs.add(crmClientResourceVO);
		}
		
		//查询食堂分账比例
		Set<Long> canteenIdSet = canteenIdWithCanteenSplitPercent.keySet();
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(new LinkedList<Long>(canteenIdSet));
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		canteenIdWithCanteenSplitPercent.clear();
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			Long canteenId = crmSplitAssignSetPO.getCanteenId();
			if(null != canteenId && !canteenIdWithCanteenSplitPercent.containsKey(canteenId)){
				BigDecimal canteenSplitPercent = crmSplitAssignSetPO.getCanteenSplitPercent();
				canteenIdWithCanteenSplitPercent.put(canteenId, canteenSplitPercent);
			}
		}
		
		
		
		//统计订单量和订单金额  
		CrmCanteenBaseInfoParam crmCanteenBaseInfoParam = new CrmCanteenBaseInfoParam();
		crmCanteenBaseInfoParam.setIds(new LinkedList<Long>(canteenIdSet));
		List<CrmCanteenBaseInfoPO> crmCanteenBaseInfoPOs = icrmCanteenBaseInfoService.commonQuery(crmCanteenBaseInfoParam);
		List<Long> canteenIdList = new LinkedList<Long>();//canteen_base_info的主键Id
		for (CrmCanteenBaseInfoPO crmCanteenBaseInfoPO : crmCanteenBaseInfoPOs) {
			Long canteenId = crmCanteenBaseInfoPO.getCanteenId();
			canteenIdList.add(canteenId);
		}
		
		//查询订单信息
		OrderParam orderParam = new OrderParam();
		orderParam.setApplyMonth(crmClientResourceParam.getApplyMonth());
		orderParam.setCanteenIds(canteenIdList);
		Integer[] status = {2,3,4,5,6,8,9};//查询2、已支付  3、已接单、4已送达、5已完成、6已评价、8待评价、9待取餐
		orderParam.setStatusList(Arrays.asList(status));
		Map<Long,List<Object>> canteenIdWithOrderTotalNumAndOrderTotalMoney = orderService.statisticsOrderTotalNumAndOrderTotalMoney(orderParam);
		Map<Long,List<Object>> crmCanteenIdWithOrderTotalNumAndOrderTotalMoney = new HashMap<Long, List<Object>>();
		for (CrmCanteenBaseInfoPO crmCanteenBaseInfoPO : crmCanteenBaseInfoPOs) {
			Long canteenId = crmCanteenBaseInfoPO.getCanteenId();
			if(canteenIdWithOrderTotalNumAndOrderTotalMoney.containsKey(canteenId)) {
				Long id = crmCanteenBaseInfoPO.getId();
				crmCanteenIdWithOrderTotalNumAndOrderTotalMoney.put(id, canteenIdWithOrderTotalNumAndOrderTotalMoney.get(canteenId));
			}
		}
		canteenIdWithOrderTotalNumAndOrderTotalMoney.clear();
		canteenIdWithOrderTotalNumAndOrderTotalMoney = null;
		
		
		
		
		
		//设置食堂分账比例与订单量订单金额
		DecimalFormat df = new DecimalFormat("#####0.00");
		for(CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs){
			
			Integer clientType = crmClientResourceVO.getClientType();
			
			if(null != clientType && 1 != clientType){
				Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();
				
				//设置食堂分账比
				if(null != agentCanteenId && canteenIdWithCanteenSplitPercent.containsKey(agentCanteenId)){
					String canteenSplitPercentStr = df.format(canteenIdWithCanteenSplitPercent.get(agentCanteenId));
					crmClientResourceVO.setCanteenSplitPercentStr(canteenSplitPercentStr);
				}else{
					crmClientResourceVO.setCanteenSplitPercentStr("_");
				}
				
				//设置订单量订单金额
				if(crmCanteenIdWithOrderTotalNumAndOrderTotalMoney.containsKey(agentCanteenId)){
					List<Object> list = crmCanteenIdWithOrderTotalNumAndOrderTotalMoney.get(agentCanteenId);
					Integer orderNum = (Integer) list.get(0);
					BigDecimal orderTotalMoney = (BigDecimal) list.get(1);
					crmClientResourceVO.setOrderTotalNum(orderNum);
					crmClientResourceVO.setOrderTotalMoney(orderTotalMoney);
				}
				
			}else{
				crmClientResourceVO.setCanteenSplitPercentStr("_");
			}
			
			//格式化客户分类,合作状态,分账比例
			String clientTypeStr ="";
			switch (clientType) {
			case 1:clientTypeStr = "代理商";
				break;
				
			case 2:clientTypeStr = "厂内食堂";
				break;
				
			case 3:clientTypeStr = "校内食堂";
				break;
				
			case 4:clientTypeStr = "独立食堂";
			break;

			default:clientTypeStr = "-";
				break;
			}
			crmClientResourceVO.setClientTypeStr(clientTypeStr);
		}
		
		return crmClientResourceVOs;
	}

	/**
	 * 统计业务员的保护客户数量
	 */
	@Override
	public Map<Long, Integer> countProtectedClientNum(
			CrmClientResourceParam crmClientResourceParam) {
		ParamMap paramMap = new ParamMap(crmClientResourceParam);
		Map<Long,Integer> businessIdWithProtectedNum = new HashMap<Long, Integer>();//业务员id和其保护客户数量
		List<Map> businessWithProtectedNum = commonDao.listByParams("CrmClientResourcePOMapper.countProtectedClientNum", paramMap );
		for (Map map : businessWithProtectedNum) {
			Long businessId = (Long) map.get("businessId");
			Integer protectedNum = CommUtil.parseInteger( map.get("protectedNum"));
			businessIdWithProtectedNum.put(businessId, protectedNum);
		}
		
		return businessIdWithProtectedNum;
	}

	@Override
	public CrmClientResourcePO findByAgentCanteenId(Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentCanteenId", id);
		List<CrmClientResourcePO> clientResourcePOs = listByParams(CrmClientResourcePO.class, "CrmClientResourcePOMapper.findByAgentCanteenId", paramMap);
		if(CollectionUtils.isNotEmpty(clientResourcePOs)){
			return clientResourcePOs.get(0);
		}
		return null;
	}

	/**
	 * 根据平台类型，查找数据
	 * @author 黄霄仪
	 * @date 2017年7月31日 下午4:46:56
	 * @type 客户类型，1.代理商，2.食堂
	 */
	@Override
	public CrmClientResourcePO findByAgentCanteenIdAndClientType(Long id,Integer clientType) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("agentCanteenId", id);
		List<Integer> clientTypes=new LinkedList<>();
		if(clientType==1){
			clientTypes.add(clientType);
		}else{
			Collections.addAll(clientTypes, 2,3,4);
		}
		paramMap.put("clientTypes", clientTypes);
		List<CrmClientResourcePO> clientResourcePOs = listByParams(CrmClientResourcePO.class, "CrmClientResourcePOMapper.findByAgentCanteenIdAndClientType", paramMap);
		if(CollectionUtils.isNotEmpty(clientResourcePOs)){
			return clientResourcePOs.get(0);
		}
		return null;
	}
	/**
	 * (散客)客户保护 
	 * @throws ParseException 
	 */
	@Override
	public Boolean clientProtect(Long clientId, Long userId, RespModel respModel) throws ParseException {
		//查询当前操作者对应业务员信息
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setUserId(userId);
		crmBusinessParam.setDelFlag(1);
		List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		if(CollectionUtils.isEmpty(crmBusinessPOs) || crmBusinessPOs.size() != 1){
			respModel.setDesc("数据异常");
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			return true;
		}
		//查询业务员保护客户数量
		CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
		Long businessId = crmBusinessPO.getId();
		CrmClientResourceParam clientResourceParam = new CrmClientResourceParam();
		clientResourceParam.setBusinessId(businessId);
		clientResourceParam.setDelFlag(1);
		clientResourceParam.setClientNature(2);//客户性质	1.散客，2.保护客户，3.正式客户
		Map<Long, Integer> countProtectedClientNum = this.countProtectedClientNum(clientResourceParam);
		if(BeanUtils.isNotEmpty(countProtectedClientNum)){
			if(countProtectedClientNum.containsKey(businessId)){
				Integer protectedClientNum = countProtectedClientNum.get(businessId);
				if(protectedClientNum >= 10){
					respModel.setDesc("对不起，你的保护客户数量已满。");
					respModel.setCode(RespModel.RespCode.PARAMETER_NULL.getCode());
					return true;
				}
			}
		}
		
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) this.getByExample(crmClientResourcePO);
		//Long lastProtectBusinessId = crmClientResourcePO.getLastProtectBusinessId();
		Boolean exists = wolverineJedisCluster.exists("protectedClientOut"+clientId+"businessId"+businessId);
		if(exists){
			respModel.setDesc("对不起，踢出保护客户，三日内不可进行保护。");
			respModel.setCode(RespModel.RespCode.PARAMETER_NULL.getCode());
			return true;
		}
		/*Date relieveProtectTime = crmClientResourcePO.getRelieveProtectTime();
		if(null != lastProtectBusinessId && null != relieveProtectTime){
			if(businessId == lastProtectBusinessId){
				int daysBetween = DateUtil.daysBetween(relieveProtectTime,new Date());
				if(daysBetween <= 3){
					respModel.setDesc("对不起，踢出保护客户，三日内不可进行保护。");
					respModel.setCode(RespModel.RespCode.PARAMETER_NULL.getCode());
					return true;
				}
			}
		}*/
		
		
		crmClientResourcePO.setBusinessId(businessId);
		crmClientResourcePO.setClientNature(2);//客户性质	1.散客，2.保护客户，3.正式客户
		//设置默认保护时间
		Calendar currentDateTime=Calendar.getInstance();//当前时间
		currentDateTime.add(Calendar.MONTH,1);//默认保护期为1个月
		crmClientResourcePO.setProtectDeadline(currentDateTime.getTime());
		
		Integer type = crmBusinessPO.getType();//业务员类型，1.内部业务员，2.外部业务员
		if(1 == type){
			crmClientResourcePO.setCustomerAttribute(2);//客户属性 1 代理商客户 2平台客户
		}else{
			crmClientResourcePO.setCustomerAttribute(1);
			crmClientResourcePO.setAgentId(crmBusinessPO.getAgentId());
		}
		logger.info("####CrmClientResourceServiceImpl###clientProtect##更新crmClientResourcePO:"+crmClientResourcePO.toString());
		this.update(crmClientResourcePO);
		respModel.setDesc("操作成功");
		
		return false;
	}
	
	@Override
	public Boolean byBusinessGetClientList(CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel) {
		Long agentId = crmClientResourceParam.getAgentId();
		Long teamId = crmClientResourceParam.getTeamId();
		Long businessId = crmClientResourceParam.getBusinessId();
		String applyMonth = crmClientResourceParam.getApplyMonth();
		String stratTimes = crmClientResourceParam.getStratTimes();
		String endTimes = crmClientResourceParam.getEndTimes();
		if(BeanUtils.isEmptyAnd(agentId,teamId,businessId)){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		//根据请求参数判断
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long,String>();
		if(BeanUtils.isNotEmpty(agentId) && BeanUtils.isEmptyAnd(teamId,businessId)){//代理商客户列表
			//展示该代理商自身的客户列表,查询参数
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setAgentId(agentId);
			crmBusinessParam.setDelFlag(1);
			crmBusinessParam.setType(2);//1.平台业务员，2.代理商业务员
			List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
			for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
				businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
			}
			
			
			crmClientResourceParam.setAgentId(null);
		}
		if(BeanUtils.isNotEmpty(teamId) && BeanUtils.isEmptyAnd(agentId,businessId)){//团队客户列表
			//展示该团队及子孙客户列表,查询参数
			
			//查询子孙团队及本身
			CrmTeamParam crmTeamParam = new CrmTeamParam();
			crmTeamParam.setParentId(teamId);
			crmTeamParam.setDelFlag(1);
			List<CrmTeamPO> crmTeamPOs = iCrmTeamService.getChildTeam(crmTeamParam);
			List<Long> cramTeamIds = new LinkedList<Long>();
			for (CrmTeamPO crmTeamPO : crmTeamPOs) {
				cramTeamIds.add(crmTeamPO.getId());
			}
			
			//查询团队的业务员
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setTeamIds(cramTeamIds);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
			for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
				businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
			}
			
			
			crmClientResourceParam.setTeamId(null);
		}
		if(BeanUtils.isNotEmpty(businessId) && BeanUtils.isEmptyAnd(agentId,teamId)){//业务员客户列表
			//展示该业务员自身客户列表,查询参数
			CrmBusinessPO crmBusinessPO = new CrmBusinessPO();
			crmBusinessPO.setId(businessId);
			crmBusinessPO = (CrmBusinessPO) icrmBusinessService.getByExample(crmBusinessPO);
			businessIdWithBusinessName.put(businessId, crmBusinessPO.getName());
			
			crmClientResourceParam.setBusinessId(null);
		}
		
		if(null == businessIdWithBusinessName || businessIdWithBusinessName.size() <1){
			pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setDesc("出错了");
			pageRespModel.setTotal(0);
			return true;
		}
		
		//查询时间设置(工作月和起止时间两者选其一,防止用户将日期查询条件清空)
		if(BeanUtils.isNotEmptyAnd(stratTimes,endTimes)){
			crmClientResourceParam.setApplyMonth(null);
		}else{
			if(BeanUtils.isNotEmpty(applyMonth)){
				crmClientResourceParam.setStratTimes(null);
				crmClientResourceParam.setEndTimes(null);
			}
		}
		
		//模糊查询参数设置
		Integer searchCriteria = crmClientResourceParam.getSearchCriteria();//搜索条件 1单位名称,2单位编号
		String searchContent = crmClientResourceParam.getSearchContent();//搜索内容
		if(BeanUtils.isNotEmptyAnd(searchCriteria,searchContent)){
			
			switch (searchCriteria) {
			case 1:crmClientResourceParam.setNameLike(searchContent);
				break;
			case 2:crmClientResourceParam.setClientNumLike(searchContent);
				break;
			default:
				break;
			}
		}
		
		//查询客户资源
		Set<Long> businessIdSet = businessIdWithBusinessName.keySet();
		crmClientResourceParam.setBusinessIds(new LinkedList<Long>(businessIdSet));
		crmClientResourceParam.setClientNature(3);//客户性质	1.散客，2.保护客户，3.正式客户
		crmClientResourceParam.setDelFlag(1);
		Page<CrmClientResourceVO> crmClientResourceVOPage = this.findPageByParam(crmClientResourceParam);//查询客户资源
		List<CrmClientResourceVO> crmClientResourceVOs = crmClientResourceVOPage.result;
		List<Long> canteenIds = new LinkedList<Long>();
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				canteenIds.add(agentCanteenId);
			}
			
			//业务员名
			Long crmBusinessId = crmClientResourceVO.getBusinessId();
			if(businessIdWithBusinessName.containsKey(crmBusinessId)){
				crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(crmBusinessId));
			}
		}
		
		//查询分账比例
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenIds(canteenIds);
		crmSplitAssignSetParam.setDelFlag(1);
		List<CrmSplitAssignSetPO> crmSplitAssignSetPOs = icrmSplitAssignSetService.commonQuery(crmSplitAssignSetParam);
		Map<Long,BigDecimal> canteenIdWithcanteenSplitPercent = new HashMap<>();//食堂ID:食堂分账比例
		for (CrmSplitAssignSetPO crmSplitAssignSetPO : crmSplitAssignSetPOs) {
			canteenIdWithcanteenSplitPercent.put(crmSplitAssignSetPO.getCanteenId(), crmSplitAssignSetPO.getCanteenSplitPercent());
		}
		
		//统计订单量和订单金额  param:stratTimes endTimes canteenId  查crm_business_order  (暂时不做)
		
		//设置分账比例
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientType = crmClientResourceVO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			Long agentCanteenId = crmClientResourceVO.getAgentCanteenId();//客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
			if(1 != clientType && BeanUtils.isNotEmpty(agentCanteenId)){
				if(canteenIdWithcanteenSplitPercent.containsKey(agentCanteenId)){
					crmClientResourceVO.setCanteenSplitPercent(canteenIdWithcanteenSplitPercent.get(agentCanteenId));
				}
			}
		}
		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourceVOPage.getTotalResult());
		
		return false;
	}
	
	

	/**
	 * 根据参数统计客户数量 
	 */
	@Override
	public Integer countClientNum(CrmClientResourceParam crmClientResourceParam) {
		ParamMap paramMap = new ParamMap(crmClientResourceParam);
		return sqlSession.selectOne("CrmClientResourcePOMapper.countClientNum", paramMap );
	}
	
	/**
	 * 业务员解约后 业务员下的客户变为散客
	* @Title: updateClientIds 
	* @author : lcl
	* @time : 2017年7月27日 下午8:31:42
	* @return void    返回类型 
	* @throws
	 */
	@Override
	public void updateClientIds(List<Long> clientIds) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("ids", clientIds);
		execute("CrmClientResourcePOMapper.updateClientIds", paramMap);
	}

	/**
	 * 客户资源库详情
	 */
	@Override
	public Boolean queryCustomsResource(CrmClientResourceParam param,
			CrmAccountPO userPO, PageRespModel pageRespModel) {
		Integer userType = userPO.getUserType();////用户类型 0.超级管理员，1 管理员，2.代理商，3.业务员，4.业务经理
		Long userId = userPO.getId();
		if(0 == userType || 1 == userType){//查询全部客户资源
		}else if(2 == userType){//查询代理商代理区域客户资源
			//查询代理商
			CrmAgentParam crmAgentParam = new CrmAgentParam();
			crmAgentParam.setUserId(userId);
			crmAgentParam.setDelFlag(1);
			List<CrmAgentPO> crmAgentPOs = icrmAgentService.commonQuery(crmAgentParam);
			if(CollectionUtils.isEmpty(crmAgentPOs) || crmAgentPOs.size() != 1){
				pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
				pageRespModel.setDesc("未查到代理商信息或者代理商信息不止一个");
				return true;
			}
			CrmAgentPO crmAgentPO = crmAgentPOs.get(0);
			CrmAgentAreaPO crmAgentAreaPO = iCrmAgentAreaService.findByAgentId(crmAgentPO.getId());
			if(null == crmAgentAreaPO || null == crmAgentAreaPO.getAreaCode()){
				pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
				pageRespModel.setDesc("未查到该代理商代理区域信息");
				return true;
			}
			//设置代理区域
			param.setAreaCodeList(new LinkedList<Long>(Arrays.asList(crmAgentAreaPO.getAreaCode())));
			
			
		}else if(3 == userType || 4 == userType){//查询相应团队代理区域客户资源
			//查询业务员信息
			CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
			crmBusinessParam.setUserId(userId);
			crmBusinessParam.setDelFlag(1);
			List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
			if(CollectionUtils.isEmpty(crmBusinessPOs) || crmBusinessPOs.size() != 1){
				pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
				pageRespModel.setDesc("未查到业务信息或者业务信息不止一个");
				return true;
			}
			
			CrmBusinessPO crmBusinessPO = crmBusinessPOs.get(0);
			Long teamId = crmBusinessPO.getTeamId();
			
			
			if(3 == userType){//3.业务员
				CrmTeamPO crmTeamPO = new CrmTeamPO();
				crmTeamPO.setId(teamId);
				crmTeamPO.setDelFlag(1);
				crmTeamPO = (CrmTeamPO) iCrmTeamService.getByExample(crmTeamPO);
				if(null == crmTeamPO || null == crmTeamPO.getAreaCode()){
					pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
					pageRespModel.setDesc("未查到该业务员所属团队代理区域信息");
					return true;
				}
				//设置代理区域
				param.setAreaCodeList(new LinkedList<Long>(Arrays.asList(crmTeamPO.getAreaCode())));
				
				
			}else{//4.业务经理
				CrmTeamParam crmTeamParam = new CrmTeamParam();
				crmTeamParam.setParentId(teamId);
				crmTeamParam.setDelFlag(1);
				List<CrmTeamPO> childTeamPOs = iCrmTeamService.getChildTeam(crmTeamParam);
				List<Long> areaCodeList = new LinkedList<Long>();
				for (CrmTeamPO crmTeamPO : childTeamPOs) {
					Long areaCode = crmTeamPO.getAreaCode();
					if(null != areaCode){
						areaCodeList.add(areaCode);
					}
				}
				if(CollectionUtils.isEmpty(areaCodeList)){
					pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
					pageRespModel.setDesc("未查到该业务经理所属团队及子级团队代理区域信息");
					return true;
				}
				param.setAreaCodeList(areaCodeList);
			}
			
		}else{
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setDesc("无权限");
			return true;
		}
		
		Integer selectNum = param.getSelectNum();
		if(null != selectNum){
			if(1 == selectNum){//代理商
				param.setClientType(1);
			}else{//食堂
				Integer[] status = {2,3,4};//客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
				param.setClientTypes(Arrays.asList(status));
			}
		}
		//查询客户资源信息
		param.setDelFlag(1);
		param.setAgentNotFormalClientFlag(1);
		Integer provCode = param.getProvCode();
		Integer cityCode = param.getCityCode();
		Long areaCode = param.getAreaCode();
		if(BeanUtils.isEmpty(areaCode) && BeanUtils.isNotEmpty(cityCode)){
			param.setAreaCode(CommUtil.parselong(cityCode));
		}
		if(BeanUtils.isEmpty(cityCode) && BeanUtils.isNotEmpty(provCode)){
			param.setAreaCode(CommUtil.parselong(provCode));
		}
		List<Integer> clientNatures=new LinkedList<>();
		Collections.addAll(clientNatures,1,2);//只查散客和保护客户
		param.setClientNatures(clientNatures);
		Page<CrmClientResourcePO> crmClientResourcePOPage = this.findCustomsResource(param);
		List<CrmClientResourcePO> crmClientResourcePOs = crmClientResourcePOPage.result;
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long,String>();
		List<CrmClientResourceVO> crmClientResourceVOs = new  ArrayList<CrmClientResourceVO>();
		for (CrmClientResourcePO crmClientResourcePO : crmClientResourcePOs) {
			CrmClientResourceVO crmClientResourceVO = BeanConvertor.convertBean(crmClientResourcePO, CrmClientResourceVO.class);
			Integer clientNature = crmClientResourcePO.getClientNature();//客户性质 1.散客，2.保护客户，3.正式客户
			//Integer clientType = crmClientResourcePO.getClientType();
			if(2 != clientNature){
				crmClientResourceVO.setProtectDeadline(null);
			}
			
			if(1 != clientNature){
				Long businessId = crmClientResourcePO.getBusinessId();
				if(null != businessId){
					businessIdWithBusinessName.put(businessId, null);
				}
			}
			
			//代理商联系人在代理商表
			Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
			if(CommUtil.parseInteger(clientType) == 1){
				CrmAgentPO crmAgentPO = icrmAgentService.get(CrmAgentPO.class, crmClientResourcePO.getAgentCanteenId());
				if(BeanUtils.isNotEmpty(crmAgentPO)){
					if(BeanUtils.isNotEmpty(crmAgentPO.getContactRealname())){
						crmClientResourceVO.setContact(crmAgentPO.getContactRealname());
						crmClientResourceVO.setMobile(crmAgentPO.getContactPhone());
					}
				}
			}
			//---------------设置地址开始---------------------
			String areaName="";
			if(BeanUtils.isNotEmpty(crmClientResourceVO.getAreaCode())){
				areaName=this.iBaseAreaService.getAreaName(crmClientResourceVO.getAreaCode());
			}
			if(areaName.length()>0){
				if (BeanUtils.isNotEmpty(crmClientResourceVO.getAddress())){
					crmClientResourceVO.setAddress(areaName+"/"+crmClientResourceVO.getAddress());
				}else{
					crmClientResourceVO.setAddress(areaName);
				}
			}
			//----------------设置地址结束-------------------
			crmClientResourceVOs.add(crmClientResourceVO);
			
		}

		//查询业务员信息
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setDelFlag(1);
		crmBusinessParam.setIds(new LinkedList<Long>(businessIdWithBusinessName.keySet()));
		List<CrmBusinessPO> crmBusinessPOs = icrmBusinessService.commonQuery(crmBusinessParam);
		businessIdWithBusinessName.clear();
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
		}
		
		//设置业务员名
		for (CrmClientResourceVO crmClientResourceVO : crmClientResourceVOs) {
			Integer clientNature = crmClientResourceVO.getClientNature();//客户性质 1.散客，2.保护客户，3.正式客户
			if(1 != clientNature){
				Long businessId = crmClientResourceVO.getBusinessId();
				if(businessIdWithBusinessName.containsKey(businessId)){
					crmClientResourceVO.setBusinessName(businessIdWithBusinessName.get(businessId));
				}
				
			}else{
				crmClientResourceVO.setBusinessName("-");
			}
		}
		
		pageRespModel.setRows(crmClientResourceVOs);
		pageRespModel.setTotal(crmClientResourcePOPage.getTotalResult());
		return false;
	}

	/**
	 * 查询客户资源信息
	 */
	@Override
	public Page<CrmClientResourcePO> findCustomsResource(
			CrmClientResourceParam param) {
		ParamMap paramMap = new ParamMap(param);
		return commonDao.findPageByParams(CrmClientResourcePO.class, new Page<CrmClientResourcePO>(param.getOffset(),param.getLimit()), "CrmClientResourcePOMapper.findCustomsResource", paramMap );
	}
	
	/**
	 * 根据businessID统计食堂数量
	 */
	@Override
	public BigDecimal countCanteen(CrmClientResourceParam clientResourceParam) {
		ParamMap paramMap = new ParamMap(clientResourceParam);
		BigDecimal total = sqlSession.selectOne(
				"CrmClientResourcePOMapper.countCanteen", paramMap);
		if (BeanUtils.isEmpty(total)) {
			return BigDecimal.ZERO;
		}
		return total;
	}
}

