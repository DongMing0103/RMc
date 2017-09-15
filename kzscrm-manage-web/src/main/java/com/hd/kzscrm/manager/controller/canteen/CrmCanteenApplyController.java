package com.hd.kzscrm.manager.controller.canteen;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.canteen.CrmCanteenBaseInfoEnum.CrmCanteenBaseInfoClientType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.DateUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.UserPO;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.business.BusinessInfoPO;
import com.hd.kzscrm.dao.entity.business.CanteenExtInfoPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.dao.entity.canteen.CanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenApplyPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterpriseCanteenPO;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.dao.entity.enterprise.EnterprisePO;
import com.hd.kzscrm.dao.entity.user.UserAmtPO;
import com.hd.kzscrm.dao.entity.user.UserCanteenAccountPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.agent.CrmSplitAssignSetParam;
import com.hd.kzscrm.service.param.business.CanteenExtInfoParam;
import com.hd.kzscrm.service.param.business.CanteenHealthPicParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenApplyParam;
import com.hd.kzscrm.service.param.user.UserParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentApplyService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.business.BusinessInfoService;
import com.hd.kzscrm.service.serviceInter.business.CanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.business.CanteenExtInfoService;
import com.hd.kzscrm.service.serviceInter.business.CanteenHealthPicService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmTeamService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenApplyService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenExtInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.serviceInter.enterprise.EnterpriseService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseCanteenService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseService;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.serviceInter.user.UserAmtService;
import com.hd.kzscrm.service.serviceInter.user.UserCanteenAccountService;
import com.hd.kzscrm.service.vo.agent.CrmSplitAssignSetVO;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenApplyVO;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenBaseInfoVO;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenExtInfoVO;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.MD5;
import com.hd.wolverine.util.ParamMap;


/**
 * crmCanteenApply CRMCANTEENAPPLY
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmcanteenapply")
public class CrmCanteenApplyController  extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(CrmCanteenApplyController.class);
    @Autowired
    private ICrmCanteenApplyService iCrmCanteenApplyService;
    
    @Autowired
    private ICrmAgentApplyService iCrmAgentApplyService;
    
    @Autowired
    private ICrmCanteenBaseInfoService iCrmCanteenBaseInfoService;
    
    @Autowired
    private ICrmAgentService iCrmAgentService;
    
    @Resource
    private ICrmEnterpriseService iCrmEnterpriseService;
    
    @Autowired
    private ICrmClientResourceService iCrmClientResourceService;
    
    @Resource
    private CanteenBaseInfoService canteenBaseInfoService;
    
    @Resource
    private EnterpriseService enterpriseService;
    
    @Autowired
    private ICrmBusinessService iCrmBusinessService;
    
    @Autowired
    private ICrmTeamService iCrmTeamService;
    @Autowired
    private ICrmSplitAssignSetService iCrmSplitAssignSetService;
    @Resource
    private ICrmEnterpriseCanteenService iCrmEnterpriseCanteenService;
    /**
     * crm食堂扩展信息
     */
    @Resource
    private ICrmCanteenExtInfoService iCrmCanteenExtInfoService;
    /**
     * qzs食堂扩展信息
     */
    @Resource
    private CanteenExtInfoService canteenExtInfoService;
    /**
     * qzs用户表
     */
    @Resource
    private UserService userService;
    /**
     * 卫生许可证
     */
    @Resource
    private CanteenHealthPicService canteenHealthPicService;
    /**
     * 商家子帐号表
     */
    @Resource
    private UserCanteenAccountService userCanteenAccountService;
    
    /**
     * 账户余额表
     */
    @Resource
    private UserAmtService userAmtService;
    
    /**
     * 商家合作表
     */
    @Resource
    private BusinessInfoService businessInfoService;
     
    /**
     ** 预留实现
     */
    public void initControler(HttpServletRequest request, Map<String, Object> map) {
     
    }
    /**
     * 新客户审核
     * @author 黄霄仪
     * @date 2017年7月19日 上午10:06:47
     */
    @RequestMapping("/newClientCheck")
    @ResponseBody
    public ModelAndView newClientCheck() {
  	   return new ModelAndView("/index/user_audit");
    }
    /**
     * 查看企业或学校信息
     * @author 黄霄仪
     * @date 2017年7月21日 下午5:47:06
     * @param 食堂入驻申请表ID CrmCanteenApply
     */
    @RequestMapping("/lookupEnterpriseOrSchool/{id}")
    @ResponseBody
    public ModelAndView lookupEnterpriseOrSchool(@PathVariable Long id){
    	ModelAndView modelAndView=new ModelAndView("/index/lookup_enterprise_or_school");
    	CrmCanteenApplyPO crmCanteenApplyPO = iCrmCanteenApplyService.get(CrmCanteenApplyPO.class, id);
    	if(BeanUtils.isNotEmpty(crmCanteenApplyPO)){
    		Long canteenId = crmCanteenApplyPO.getCanteenId();
    		CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = iCrmCanteenBaseInfoService.get(CrmCanteenBaseInfoPO.class, canteenId);
    		if(BeanUtils.isNotEmpty(crmCanteenBaseInfoPO)){
    			List<CrmEnterpriseCanteenPO> crmEnterpriseCanteenPOs = iCrmEnterpriseCanteenService.findByCanteenId(canteenId);
    			//暂时一个企业，一个食堂
    			if(BeanUtils.isNotEmpty(crmEnterpriseCanteenPOs)&&crmEnterpriseCanteenPOs.size()==1){
    				Long enterpriseId = crmEnterpriseCanteenPOs.get(0).getEnterpriseId();
    				CrmEnterprisePO crmEnterprisePO = iCrmEnterpriseService.get(CrmEnterprisePO.class, enterpriseId);
    				if(BeanUtils.isNotEmpty(crmEnterprisePO)){
    					modelAndView.addObject("crmEnterpriseVO", crmEnterprisePO);
    				}
    			}else if(crmEnterpriseCanteenPOs.size()>1){
    				modelAndView.addObject("desc", "暂不支持一个食堂，多个食堂");
    			}
    		}
    	}
    	return modelAndView;
    }
    /**
     * 新客户申请(食堂)
     * @author 黄霄仪
     * @date 2017年7月19日 上午10:04:39
     */
    @RequestMapping(value="/newClientApply",method=RequestMethod.POST)
    @ResponseBody
    public PageRespModel newClientApply(@RequestBody CrmCanteenApplyParam crmCanteenApplyParam){
    	int limit = crmCanteenApplyParam.getLimit();
    	int offset = crmCanteenApplyParam.getOffset();
    	
    	Page<CrmCanteenApplyVO> crmCanteenApplyVOPage=iCrmCanteenApplyService.queryPage(crmCanteenApplyParam);
    	if(BeanUtils.isNotEmpty(crmCanteenApplyVOPage)){
    		List<CrmCanteenApplyVO> crmCanteenApplyVOs=crmCanteenApplyVOPage.result;
    		for (CrmCanteenApplyVO crmCanteenApplyVO : crmCanteenApplyVOs) {
    			Long canteenId = crmCanteenApplyVO.getCanteenId();
    			CrmClientResourceVO crmClientResourceVO = BeanConvertor.copy(iCrmClientResourceService.findByAgentCanteenIdAndClientTypes(canteenId, 2,3,4),CrmClientResourceVO.class);
    			Long businessId = crmClientResourceVO.getBusinessId();
    			crmCanteenApplyVO.setClientType(crmClientResourceVO.getClientType());
    			if(BeanUtils.isNotEmpty(businessId)){
    				CrmBusinessPO crmBusinessPO = iCrmBusinessService.get(CrmBusinessPO.class, businessId);
    				Long teamId = crmBusinessPO.getTeamId();
    				crmClientResourceVO.setBusinessName(crmBusinessPO.getName());
    				if(BeanUtils.isNotEmpty(teamId)){
    					CrmTeamPO crmTeamPO = iCrmTeamService.get(CrmTeamPO.class, teamId);
    					crmClientResourceVO.setTeamName(crmTeamPO.getName());
    				}
    			}
    			crmCanteenApplyVO.setCrmClientResourceVO(crmClientResourceVO);
    			CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = iCrmCanteenBaseInfoService.get(CrmCanteenBaseInfoPO.class, canteenId);
    			if(BeanUtils.isNotEmpty(crmCanteenBaseInfoPO)){
    				crmCanteenApplyVO.setCrmCanteenBaseInfoVO(BeanConvertor.copy(crmCanteenBaseInfoPO, CrmCanteenBaseInfoVO.class));
    			}
    		}
    	}
    	
    	
    	
    	PageRespModel model = new PageRespModel();
    	model.setTotal(crmCanteenApplyVOPage.getTotalResult());
    	model.setRows(crmCanteenApplyVOPage.result);
    	return model;
    }
    /**
     * 新客户审核的审核功能（食堂）
     * @author 黄霄仪
     * @date 2017年7月19日 上午11:22:16
     * @param id CrmAgentApply,CrmCanteenApply的ID,食堂入驻申请表ID
     */
    /**
     * @author 黄霄仪
     * @date 2017年7月19日 下午2:15:27
     */
    @RequestMapping("/newClientCheckOfCheck/{id}")
    @ResponseBody
	public ModelAndView newClientCheckOfCheck(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView(
				"/system_operate/new_client_check_of_check");
		CrmCanteenApplyPO crmCanteenApplyPO = iCrmCanteenApplyService.get(CrmCanteenApplyPO.class, id);
		if(BeanUtils.isNotEmpty(crmCanteenApplyPO)){
			CrmCanteenApplyVO crmCanteenApplyVO=BeanConvertor.copy(crmCanteenApplyPO, CrmCanteenApplyVO.class);
			Long canteenId = crmCanteenApplyPO.getCanteenId();//食堂ID
			CrmSplitAssignSetPO crmSplitAssignSetPO = iCrmSplitAssignSetService.findOneByCanteenId(canteenId);
			if(BeanUtils.isNotEmpty(crmSplitAssignSetPO)){
				crmCanteenApplyVO.setCrmSplitAssignSetVO(BeanConvertor.copy(crmSplitAssignSetPO, CrmSplitAssignSetVO.class));
			}
			modelAndView.addObject("crmCanteenApplyVO", crmCanteenApplyVO);
		}
		return modelAndView;
	}
    /**
     * 新客户审核功能(审核通过)
     * @author 黄霄仪
     * @date 2017年7月19日 下午5:06:17
     */
    @RequestMapping("/checkCanteenApply")
    @ResponseBody
    public RespModel checkCanteenApply(@RequestBody CrmCanteenApplyParam crmCanteenApplyParam){
    	CrmCanteenApplyPO crmCanteenApplyPO = BeanConvertor.copy(crmCanteenApplyParam, CrmCanteenApplyPO.class);
    	Long id = crmCanteenApplyParam.getId();
    	Assert.notNull(id, "申请ID不能为空");
    	CrmCanteenApplyPO crmCanteenApplyPOTemp = iCrmCanteenApplyService.get(CrmCanteenApplyPO.class, id);
    	Assert.notNull(crmCanteenApplyPOTemp, "申请对象为空");
    	CrmClientResourcePO crmClientResourcePO = iCrmClientResourceService.findByAgentCanteenIdAndClientType(crmCanteenApplyPOTemp.getCanteenId(),2);//查找食堂类型的客户
    	Assert.notNull(crmClientResourcePO, "客户资源信息为空");
    	Date currentDate = new Date();
    	crmCanteenApplyPO.setEnterTime(DateUtil.timeStrToDate(crmCanteenApplyParam.getEnterTimeStr(), "yyyy年MM月"));
    	crmCanteenApplyPO.setCheckTime(currentDate);
    	crmCanteenApplyPO.setCheckUserId(CrmControllerHelper.getSessionUserId());
    	crmCanteenApplyPO.setCheckStatus(1);//审核通过
		iCrmCanteenApplyService.update(crmCanteenApplyPO);
		
		
    	CrmSplitAssignSetParam crmSplitAssignSetParam = crmCanteenApplyParam.getCrmSplitAssignSetParam();
    	if(BeanUtils.isNotEmpty(crmSplitAssignSetParam)){
    		String effectiveTimeStr = crmSplitAssignSetParam.getEffectiveTimeStr();
    		if(BeanUtils.isNotEmpty(effectiveTimeStr)){
    			crmSplitAssignSetParam.setEffectiveTime(DateUtil.timeStrToDate(effectiveTimeStr, "yyyy年MM月dd日"));
    		}
    		iCrmSplitAssignSetService.update(BeanConvertor.copy(crmSplitAssignSetParam, CrmSplitAssignSetPO.class));
    	}
    	
    	
    	//更新客户资源列表
    	CrmClientResourcePO crmClientResourcePOTemp=new CrmClientResourcePO();
    	crmClientResourcePOTemp.setCheckStatus(1);
    	crmClientResourcePOTemp.setClientNature(3);//正式客户
    	crmClientResourcePOTemp.setId(crmClientResourcePO.getId());
    	iCrmClientResourceService.update(crmClientResourcePOTemp);
    	
    	Long canteenId = crmCanteenApplyPOTemp.getCanteenId();
    	CrmCanteenBaseInfoPO crmCanteenBaseInfoPO=iCrmCanteenBaseInfoService.get(CrmCanteenBaseInfoPO.class, canteenId);
    	Assert.notNull(crmCanteenBaseInfoPO, "信息食堂为空");
    	
    	
    	
    	//查询扩展信息更新qzs食堂扩展信息,并参与校验
    	Long crmCanteenId = crmCanteenBaseInfoPO.getId();//crm食堂Id
    	CrmCanteenExtInfoVO crmCanteenExtInfoVO = iCrmCanteenExtInfoService.findByBaseInfoId(crmCanteenId);
    	Assert.notNull(crmCanteenExtInfoVO, "食堂扩展信息为空");
    	
    	String headPhone = crmCanteenExtInfoVO.getHeadPhone();
    	//商家联系人电话是否存在
		CanteenExtInfoParam canteenExtInfo=new CanteenExtInfoParam();
		canteenExtInfo.setHeadPhone(headPhone);
		canteenExtInfo.setDelFlag(1);
		List<CanteenExtInfoPO> canteenExtInfoPOs = canteenExtInfoService.findAllByParam(canteenExtInfo);
		if(CollectionUtils.isNotEmpty(canteenExtInfoPOs)){
			return RespModel.failure("商家负责人手机号码已使用！");
		}
		
		//校验qzs的user表是否存在该用户电话
		UserParam userParam=new UserParam();
		userParam.setMobilephone(headPhone);
		userParam.setDelFlag(1);
		userParam.setUserType(2);
		List<UserPO> userPos=this.userService.findAllByParam(userParam);
		if(CollectionUtils.isNotEmpty(userPos)){
			return RespModel.failure("该负责人手机号码已使用！");
		}
    	
    	//客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
    	Integer clientType = crmCanteenBaseInfoPO.getClientType();
    	//食堂:qzs新建厂内食堂和校内食堂需信息同时新建企业信息(同时建立内部合作关系),独立食堂只需新建食堂信息;代理商无需这些操作
    	if(BeanUtils.isNotEmpty(crmCanteenBaseInfoPO)){
    		Long canteenIdTemp= ServiceUtil.genNextIDValue(DatabaseTableNameEnum.canteen_base_info);
    		Long userId = ServiceUtil.genNextIDValue(DatabaseTableNameEnum.user);//食堂登录qzs用户Id
    		crmCanteenBaseInfoPO.setCanteenId(canteenIdTemp);
    		crmCanteenBaseInfoPO.setBusinessId(crmCanteenBaseInfoPO.getBusinessId());
    		crmCanteenBaseInfoPO.setClientNature(3);//正式客户
    		
    		CanteenBaseInfoPO canteenBaseInfoPO = BeanConvertor.copy(crmCanteenBaseInfoPO, CanteenBaseInfoPO.class);
    		canteenBaseInfoPO.setPlatformType(1);//CRM端
    		canteenBaseInfoPO.setId(canteenIdTemp);
    		canteenBaseInfoPO.setUserId(userId);//qzs用户Id
    		canteenBaseInfoPO.setCanteenCategoryId(1L);//默认餐饮类
    		canteenBaseInfoPO.setStatus(2);//状态（1未审核、2已通过 、3已注销 4.未通过）
    		canteenBaseInfoPO.setCanteenNo(canteenBaseInfoService.getCanteenNo(canteenIdTemp, 4, "F", crmCanteenBaseInfoPO.getAreaCode()));//食堂编号
    		canteenBaseInfoPO.setCreateTime(currentDate);
    		crmCanteenBaseInfoPO.setCanteenNo(canteenBaseInfoPO.getCanteenNo());
    		iCrmCanteenBaseInfoService.updateEntity(crmCanteenBaseInfoPO);
    		canteenBaseInfoService.save(canteenBaseInfoPO);
    		
    		
    		//新建qzs食堂扩展信息
    		CanteenExtInfoPO canteenExtInfoPO = BeanConvertor.copy(crmCanteenExtInfoVO, CanteenExtInfoPO.class);
    		canteenExtInfoPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.canteen_ext_info));
    		canteenExtInfoPO.setBaseInfoId(canteenIdTemp);
    		canteenExtInfoPO.setCreateTime(currentDate);
    		canteenExtInfoPO.setUserId(userId);
    		canteenExtInfoService.save(canteenExtInfoPO);
    		
    		//更新卫生许可证(嵌入CanteenId,即qzs食堂id)
    		CanteenHealthPicParam canteenHealthPicParam = new CanteenHealthPicParam();
    		canteenHealthPicParam.setCanteenId(canteenIdTemp);//qzs食堂id
    		canteenHealthPicParam.setCrmCanteenId(crmCanteenId);//crm食堂Id
    		canteenHealthPicParam.setDelFlag(1);
    		canteenHealthPicService.updateByparam(canteenHealthPicParam);
    		
    		//创建qzs食堂登录用user信息
    		UserPO userPO=new UserPO();
			userPO.setId(userId);
			userPO.setMobilephone(headPhone);
			userPO.setUserName(crmCanteenExtInfoVO.getHeadRealname());
			userPO.setPassword(MD5.md5("123456"));//以后需要修改
			userPO.setRegiserTime(currentDate);
			userPO.setUserType(2);
			userPO.setUserStatus(1);
			userPO.setDelFlag(1);
			userPO.setUserNo(CommUtil.getNumByFigures(userPO.getId().toString(), 8));
			userService.save(userPO);
    		
			//添加子帐户
			UserCanteenAccountPO uAccountPO = new UserCanteenAccountPO();
			uAccountPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.user_canteen_account));
			uAccountPO.setUserId(userId);// 子账号
			uAccountPO.setCanteenId(canteenIdTemp);
			uAccountPO.setCreateTime(currentDate);
			uAccountPO.setStatus(1);
			uAccountPO.setDelFlag(1);
			uAccountPO.setIsMain(1);
			userCanteenAccountService.save(uAccountPO);
			
			//添加用户余额表
			UserAmtPO userAmtPO = new UserAmtPO();
			userAmtPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.user_amt));
			userAmtPO.setUserId(userId);
			userAmtPO.setTotalIncome(BigDecimal.ZERO);
			userAmtPO.setTotalConsume(BigDecimal.ZERO);
			userAmtPO.setBalance(BigDecimal.ZERO);
			userAmtPO.setCanWithdrawDeposit(BigDecimal.ZERO);
			userAmtService.save(userAmtPO);
			
			
			//是否创建商家分账抽成表???
    		
    		//如果是厂内或校内食堂，一定有企业信息
    		if(clientType==CrmCanteenBaseInfoClientType.FACTORY_CANTEEN.getCode()||clientType==CrmCanteenBaseInfoClientType.SCHOOL_CANTEEN.getCode()){
    			List<CrmEnterpriseCanteenPO> crmEnterpriseCanteenPOs = iCrmEnterpriseCanteenService.findByCanteenId(crmCanteenId);
    			//目前只有一个食堂，一个企业的关系 
    			if(BeanUtils.isNotEmpty(crmEnterpriseCanteenPOs)&&crmEnterpriseCanteenPOs.size()==1){
    				Long enterpriseId = crmEnterpriseCanteenPOs.get(0).getEnterpriseId();//企业ID
    				CrmEnterprisePO crmEnterprisePO = iCrmEnterpriseService.get(CrmEnterprisePO.class, enterpriseId);
    				Assert.notNull(crmEnterprisePO, "企业信息不能为空");
    				Long enterpriseIdTemp= ServiceUtil.genNextIDValue(DatabaseTableNameEnum.enterprise);
    				Long EnterpriseUserId = ServiceUtil.genNextIDValue(DatabaseTableNameEnum.user);//企业登录qzs用户Id
    				crmEnterprisePO.setEnterpriseId(enterpriseIdTemp);
    				EnterprisePO enterprisePO = BeanConvertor.copy(crmEnterprisePO, EnterprisePO.class);
    				enterprisePO.setId(enterpriseIdTemp);
    				enterprisePO.setUserId(EnterpriseUserId);
    				iCrmEnterpriseService.updateEntity(crmEnterprisePO);
    				enterpriseService.save(enterprisePO);
    				
    				//建立合作表(食堂和企业内部合作,内部食堂)
    				BusinessInfoPO businessInfoPO = new BusinessInfoPO();
    				businessInfoPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.business_info));
    				businessInfoPO.setEnterpriseId(enterpriseIdTemp);
    				businessInfoPO.setCanteenId(canteenIdTemp);
    				businessInfoPO.setCreateTime(currentDate);
    				businessInfoPO.setCooperationType(1);//合作类型：1内部供餐 2议价供餐
    				businessInfoPO.setDelFlag(1);
    				businessInfoService.save(businessInfoPO);
    				
    				//创建企业登录帐号
    				UserPO uPO = new UserPO();
    				uPO.setId(EnterpriseUserId);
    				uPO.setUserName(enterprisePO.getContact());
    				uPO.setPassword(MD5.md5("123456"));
    				uPO.setRegiserTime(currentDate);
    				uPO.setCreateTime(currentDate);
    				uPO.setMobilephone(enterprisePO.getTelephone());
    				uPO.setUserType(1);
    				uPO.setUserStatus(1);
    				uPO.setDelFlag(1);
    				uPO.setUserNo(CommUtil.getNumByFigures(uPO.getId().toString(), 8));
    				userService.save(uPO);
    			}
    		}else if(clientType==CrmCanteenBaseInfoClientType.INDEPENDENT_CANTEEN.getCode()){
    			
    			
    			
    		}else{
    			return RespModel.failure("暂不支持此客户类型");
    		}
    	}
    	return RespModel.success("审核成功");
    }
    
    /**
     *
     * 初始化
     * @param param
     * @return
     */
    @RequestMapping("/init")
    @ResponseBody
    public ModelAndView init() {
     	
    	ModelAndView view = new ModelAndView("/crmcanteenapply/mainCrmCanteenApply");
    	ParamMap param =  new ParamMap();
    	 
    	//菜单
    	view.addObject("active","CrmCanteenApply");
    	//行业
    	 
		return view;
    }
    
    /**
     * 新增
     *
     * @return 
     *
     */
    @RequestMapping(value = "addCrmCanteenApply", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addCrmCanteenApply() {
         ModelAndView modelAndView = new ModelAndView("/crmcanteenapply/mainAddOrEditCrmCanteenApply");
		 CrmCanteenApplyParam CrmCanteenApplyparam = new CrmCanteenApplyParam();
		 modelAndView.addObject("type","add");      
         return modelAndView;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "/editCrmCanteenApply/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCrmCanteenApply(@PathVariable Long id) {
         ModelAndView modelAndView = new ModelAndView("/crmcanteenapply/mainAddOrEditCrmCanteenApply");
         CrmCanteenApplyParam CrmCanteenApplyparam = new CrmCanteenApplyParam();
         CrmCanteenApplyPO po = iCrmCanteenApplyService.get(CrmCanteenApplyPO.class,id );
         modelAndView.addObject("po",po);
         modelAndView.addObject("type","update");
         return modelAndView;
    }
   /**
     * 删除
     *
     * @param 
     * @return
     */
    @RequestMapping(value = "doDelete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public RespModel delete(@PathVariable Long id) {
        RespModel res = new RespModel();
        try {
			iCrmCanteenApplyService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
        return res;
    }
    
    
    @RequestMapping(value = "doSave")
    @ResponseBody
    public RespModel doSave(CrmCanteenApplyParam param, HttpServletRequest request) {
        RespModel res = new RespModel();
       	CrmCanteenApplyPO po=BeanConvertor.convertBean(param, CrmCanteenApplyPO.class);
        iCrmCanteenApplyService.saveEntity(po);
        return res;
    }
    
    @RequestMapping(value = "doUpdate")
    @ResponseBody
    public RespModel doUpdate(CrmCanteenApplyParam param, HttpServletRequest request) {
       RespModel res = new RespModel();
       CrmCanteenApplyPO po=BeanConvertor.convertBean(param, CrmCanteenApplyPO.class);
       iCrmCanteenApplyService.updateEntity(po);
       return res;
    }
    
    /**
     * 一览查询
     * @param param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public PageRespModel queryPage(CrmCanteenApplyParam param) throws Exception {
    	PageRespModel model = new PageRespModel();

    	//翻页查询
    	
    	Page<CrmCanteenApplyVO> pages = iCrmCanteenApplyService.queryPage(param);
    	//model.setRows(Lists.transform(pages.result,trans));
    	model.setTotal(pages.getTotalResult());
    	model.setRows(pages.result);
    	
    	return model;
    }
    
		
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmCanteenApplyParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmCanteenApply/CrmCanteenApply_Detail");
//		SplitAccountRolePO po = crmCanteenApplyService.get(SplitAccountRolePO.class, id);
//		if (po != null  ) {
//	       view.addObject("firstPO", po);
//		} else {
//			view.addObject("firstPo", new CrmCanteenApplyPO());
//			return view;
//		}
		return view;
	} 
	
	
	/**
	 * 
	 * @Title: canteenEnterApplyInit 
	 * @Description: 食堂入驻申请初始化 
	 * @param @param clientId
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月14日 下午4:11:56
	 */
	@RequestMapping(value="/canteenEnterApplyInit")
	public ModelAndView canteenEnterApplyInit(Long clientId){
		logger.info("#####CrmCanteenApplyController###canteenEnterApplyInit##clientId:"+clientId);
		ModelAndView modelAndView = new ModelAndView("salesman_operate/in_apply");
		modelAndView.addObject("clientId", clientId);
		modelAndView.addObject("enterTimeStr", DateUtil.getNowDate());
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: saveCanteenEnterApply 
	 * @Description: 食堂入驻申请信息保存
	 * @param @param crmCanteenApplyParam
	 * @param @return  
	 * @return RespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月14日 下午4:12:05
	 */
	@RequestMapping(value="/saveCanteenEnterApply", method = RequestMethod.POST)
	@ResponseBody
	public RespModel saveCanteenEnterApply(CrmCanteenApplyParam crmCanteenApplyParam){
		logger.info("#####CrmCanteenApplyController###saveCanteenEnterApply##crmCanteenApplyParam:"+crmCanteenApplyParam.toString());
		
		RespModel respModel = new RespModel();
		crmCanteenApplyParam.setCreateUid(CrmControllerHelper.getSessionUserId());//登录者userId
		
		try {
			//信息保存
			Boolean flag = iCrmCanteenApplyService.saveCanteenEnterApply(crmCanteenApplyParam,respModel);
			if(flag) return respModel;
		} catch (Exception e) {
			logger.error("CrmCanteenApplyController:saveCanteenEnterApply:"+e);
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc(e.getMessage());
			return respModel;
		}
		
		return respModel;
	}
	
	 
}