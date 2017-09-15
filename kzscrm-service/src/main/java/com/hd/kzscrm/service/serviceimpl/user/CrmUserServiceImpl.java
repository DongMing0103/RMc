package com.hd.kzscrm.service.serviceimpl.user;

//crmuser
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.enums.user.CrmAccountEnum.CrmAccountUserType;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.common.util.StringUtil;
import com.hd.kzscrm.dao.entity.perm.CrmPermRolePO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.service.constants.QzsWebConstants;
import com.hd.kzscrm.service.param.perm.CrmPermUserRoleParam;
import com.hd.kzscrm.service.param.user.CrmUserParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.sms.ISmsService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.vo.user.CrmUserVO;
import com.hd.wolverine.cache.CacheService;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.MD5;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmUser 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmUserServiceImpl extends BaseServiceImpl implements ICrmUserService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmUserServiceImpl.class);
	 @Resource
	 private ISmsService iSmsService;
	 
	 @Resource
	 private CacheService cacheService;
	 
	 @Resource
	 private ICrmPermRoleService iCrmPermRoleService;
	 
	 @Resource
	 private ICrmPermUserRoleService iCrmPermUserRoleService;
     
     /**
     *  默认构造函数
     */
     
	 public CrmUserServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmUserVO> convertPOToVO(List<CrmUserPO> crmuserList){
    	List<CrmUserVO> crmuserVoList = new ArrayList<CrmUserVO>();
    	if (CollectionUtils.isEmpty(crmuserList)) {
    		return crmuserVoList;
    	}
    	for (CrmUserPO tag : crmuserList) {
    		CrmUserVO tagVo = BeanConvertor.copy(tag,CrmUserVO.class);
    		crmuserVoList.add(tagVo);
    	}
    	return crmuserVoList;
    }
    
    @Override
    public CrmUserPO getById(Long id){
    	return commonDao.get(CrmUserPO.class, id);
    }
    /**
     * 修改密码
     * @author 黄霄仪
     * @date 2017年6月9日 下午2:22:27
     */
    @Override     
    public int modifyPassword(CrmUserParam param){
    	ParamMap paramMap=new ParamMap(param);
    	return commonDao.execute("CrmUserPOMapper.modifyPassword", paramMap);
    }
    /**
     * 查询
     */
    @Override
    public Page<CrmUserVO> queryPage(CrmUserParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmUserName",param.getCrmUserName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmUserPO> crmuserList = findPageByParams(CrmUserPO.class,new Page<CrmUserPO>(param.getOffset(),param.getLimit()),"CrmUserPOMapper.queryPage",paramMap);
    	List<CrmUserVO> rows = new ArrayList<CrmUserVO>();
    	int total = 0;
    	if(crmuserList.result != null){
    		rows = convertPOToVO(crmuserList.result);
    		total = crmuserList.getTotalResult();
    	}    	
    	Page<CrmUserVO> pageResult = new Page<CrmUserVO>();
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
    public List<CrmUserPO> listByParam(CrmUserParam crmuserParam){
    	ParamMap paramMap = new ParamMap(crmuserParam);
    	return commonDao.listByParams(CrmUserPO.class,"CrmUserPOMapper.queryList",paramMap);
    }
    @Override
    public CrmUserPO findByAccount(String account){
    	CrmUserParam crmuserParam=new CrmUserParam();
    	crmuserParam.setAccount(account);
    	List<CrmUserPO> crmUserPOs = this.listByParam(crmuserParam);
    	if(BeanUtils.isEmpty(crmUserPOs)){
    		return null;
    	}
    	Assert.isTrue(crmUserPOs.size()==1, "帐号:"+account+"已存在");
    	return crmUserPOs.get(0);
    }
    
    @Override
    public CrmUserPO findByAccountAndUserType(String account,Integer userType){
    	CrmUserParam crmuserParam=new CrmUserParam();
    	crmuserParam.setAccount(account);
    	crmuserParam.setUserType(userType);
    	List<CrmUserPO> crmUserPOs = this.listByParam(crmuserParam);
    	if(BeanUtils.isEmpty(crmUserPOs)){
    		return null;
    	}
    	Assert.isTrue(crmUserPOs.size()==1, "帐号:"+account+"已存在");
    	return crmUserPOs.get(0);
    }
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmUserPO po = this.get(CrmUserPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmUserPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmUserPO> listPo = new ArrayList<CrmUserPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmUserPO po = this.get(CrmUserPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmUserPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmUserParam param){
    	Long id = param.getId();
    	if(BeanUtils.isEmpty(id)){
    		param.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_user));
    	}
    	CrmUserPO crmuserPO = BeanConvertor.copy(param,CrmUserPO.class);
    	this.save(crmuserPO);
    }
    
    @Resource
    private ICrmAgentService iCrmAgentService;
    @Resource
    private ICrmBusinessService iCrmBusinessService;
    /**
     * 保存用户
     * @author 黄霄仪
     * @date 2017年7月20日 下午7:49:50
     */
    @Override
    public RespModel saveClient(CrmUserParam crmUserParam){
    	Long createId = crmUserParam.getCreateId();
    	Integer type = crmUserParam.getType();//帐户类型(平台类型)1.平台，2.代理商
    	Long userId=ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_user);
    	crmUserParam.setId(userId);
    	Date currentDate=new Date();
    	Long roleId = crmUserParam.getRoleId();
    	Assert.notNull(roleId,"角色不能为空");
    	CrmPermRolePO crmPermRolePO = iCrmPermRoleService.get(CrmPermRolePO.class, roleId);
    	Assert.notNull(crmPermRolePO,"该角色不存在");
    	crmUserParam.setCreateTime(currentDate);
    	crmUserParam.setUpdateTime(currentDate);
    	Integer userType = crmPermRolePO.getUserType();
    	if(userType!=1){
    		return RespModel.failure("只能添加管理员角色的用户");
    	}
		crmUserParam.setUserType(userType);
    	crmUserParam.setPassword("123456");
    	crmUserParam.setUserStatus(1);//启用
        RespModel res = RespModel.success("保存成功");
        String mobilephone = crmUserParam.getMobilephone();
        crmUserParam.setAccount(mobilephone);//代理商用户使用手机号登录
        try {
        	//代理商
        	String userName = crmUserParam.getUserName();
        	if(userType==CrmAccountUserType.AGENT.getCode()){
        	}
        	CrmUserPO crmUserPO = this.findByAccount(crmUserParam.getAccount());
        	if(BeanUtils.isNotEmpty(crmUserPO)){
        		return RespModel.failure("帐号已存在");
        	}
			/*if(userType==CrmAccountUserType.AGENT.getCode()){
        		CrmAgentParam crmAgentParam=new CrmAgentParam();
        		crmAgentParam.setUserId(userId);
        		crmAgentParam.setPrincipalName(userName);
        		crmAgentParam.setRegisterTime(currentDate);
        		crmAgentParam.setUpdateId(createId);
        		crmAgentParam.setCreateId(createId);
        		crmAgentParam.setUpdateTime(currentDate);
        		crmAgentParam.setCreateTime(currentDate);
        		crmAgentParam.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
        		crmAgentParam.setAuthenticationStatus(0);//申请中
        		crmAgentParam.setAgentStatus(0);//失效
        		crmAgentParam.setSignContractStatus(2);//未签约
        		iCrmAgentService.saveEntity(BeanConvertor.copy(crmAgentParam, CrmAgentPO.class));
        	}
        	//业务员或业务经理
        	else if(BeanUtils.isOr(userType==CrmAccountUserType.BUSINESS.getCode(),userType==CrmAccountUserType.BUSINESS_MANAGER.getCode())){
        		CrmBusinessParam crmBusinessParam=new CrmBusinessParam();
        		crmBusinessParam.setUserId(userId);
        		crmBusinessParam.setName(userName);
        		crmBusinessParam.setType(type);
        		crmBusinessParam.setUserType(userType==CrmAccountUserType.BUSINESS.getCode()?1:2);
        		crmBusinessParam.setCreateTime(currentDate);
        		crmBusinessParam.setWorkTime(currentDate);
        		crmBusinessParam.setUpdateTime(currentDate);
        		crmBusinessParam.setCreateId(createId);
        		crmBusinessParam.setUpdateId(createId);
        		crmBusinessParam.setJobStatus(CrmBusinessJobStatus.ON_JOB.getCode());
        		crmBusinessParam.setRegisterTime(currentDate);
        		crmBusinessParam.setAuthenticationStatus(1);//通过
        		crmBusinessParam.setDelFlag(CrmCommonDelFlag.EXISTS.getCode());
        		crmBusinessParam.setAddress(crmUserParam.getAddress());
        		iCrmBusinessService.saveEntity(BeanConvertor.copy(crmBusinessParam, CrmBusinessPO.class));
        	}*/
        	this.saveEntity(crmUserParam);
		} catch (Exception e) {
			e.printStackTrace();
			return RespModel.failure(e.getMessage());
		}
    	return res;
    }
    
    @Override
    public void saveEntity(CrmUserParam param){
    	Long userId = param.getId();
    	if(BeanUtils.isEmpty(userId)){
    		userId=ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_user);
    	}
    	Long roleId = param.getRoleId();
    	if(BeanUtils.isNotEmpty(roleId)){
    		CrmPermUserRoleParam crmPermUserRoleParam=new CrmPermUserRoleParam();
    		crmPermUserRoleParam.setUserId(userId);
    		crmPermUserRoleParam.setRoleId(roleId);
    		iCrmPermUserRoleService.saveEntity(crmPermUserRoleParam);
    	}
    	param.setId(userId);
    	String password = param.getPassword();
    	String md5CheckPassword = MD5.md5(password);
    	param.setPassword(md5CheckPassword);
    	this.add(param);
    }
    
    @Override
    public void updateEntity(CrmUserParam crmUserParam){
    	Assert.notNull(crmUserParam, "对象不能为空");
    	Long roleId = crmUserParam.getRoleId();
    	Assert.notNull(roleId, "用户角色不能为空");
    	Long userId = crmUserParam.getId();
    	Assert.notNull(userId, "用户ID不能为空");
    	CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(userId);
    	if(BeanUtils.isEmpty(crmPermUserRolePO)){
    		CrmPermUserRoleParam crmPermUserRoleParam=new CrmPermUserRoleParam();
    		crmPermUserRoleParam.setUserId(userId);
    		crmPermUserRoleParam.setRoleId(roleId);
    		iCrmPermUserRoleService.saveEntity(crmPermUserRoleParam);
    	}else{
    		crmPermUserRolePO.setRoleId(roleId);
    		iCrmPermUserRoleService.update(crmPermUserRolePO);
    	}
    	this.update(BeanConvertor.copy(crmUserParam, CrmUserPO.class));
    }
 
    /**
     * 发送短信验证码
     * @author 黄霄仪
     * @date 2017年6月8日 下午2:02:39
     * @param mobilephone 手机号
     * @param smsType 短信类型 1.修改密码
     */
    @Override
    public void sendVerificationCode(final String mobilephone,final Integer smsType){
    	new Thread(new Runnable(){
    		@Override
			public void run() {
				// TODO Auto-generated method stub
				
    			logger.info("com.hd.kzscrm.service.serviceimpl.user.CrmUserServiceImpl.sendVerificationCode(String, Integer)-mobilephone:"+mobilephone+",smsType:"+smsType);
    			final int EXPIRE_TIME=600;//600秒,失效时间
    			String content="";
    			//生成验证码
    			String veriCode = StringUtil.getRandomNum2();
    			System.out.println("verificationCode 短信验证码 : " + veriCode);
    			if(smsType==1){
    				content="【浙江惠点】验证码是"+veriCode+"，"+EXPIRE_TIME/60+"分钟有效[筷子说]";
    			}else if(smsType==2){
    				content="【浙江惠点】验证码是"+veriCode+"，"+EXPIRE_TIME/60+"分钟有效[筷子说]";
    			}else{
    				return;
    			}
    			
    			// 生成验证码放入缓存
    			String objType = QzsWebConstants.SYS_KEY_MAIN + ":"
    					+ QzsWebConstants.OBJTYPE_USER_SENDVERICODEMSG;
    			
    			
    			cacheService.set(objType, mobilephone, veriCode,EXPIRE_TIME);
    			// 发送短信
//		 iSmsService.sendsms(mobilephone,
//		 "【浙江惠点】验证码是"+veriCode+"，10分钟有效[筷子说]", "",0);
    			iSmsService.sendsms(mobilephone, content, "", 0);
			}
    	}).start();
    }
   
}

