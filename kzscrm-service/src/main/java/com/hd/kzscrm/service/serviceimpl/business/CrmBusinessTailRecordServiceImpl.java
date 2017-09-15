package com.hd.kzscrm.service.serviceimpl.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessTailRecordPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmBusinessTailRecordParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessTailRecordService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.business.CrmBusinessTailRecordVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmBusinessTailRecord 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmBusinessTailRecordServiceImpl extends BaseServiceImpl implements ICrmBusinessTailRecordService {
  
	@Autowired
	private ICrmBusinessService iCrmBusinessService;
	//客户资源表
	@Autowired
	private ICrmClientResourceService clientResourceService;
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmBusinessTailRecordServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmBusinessTailRecordServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmBusinessTailRecordVO> convertPOToVO(List<CrmBusinessTailRecordPO> crmbusinesstailrecordList){
    	List<CrmBusinessTailRecordVO> crmbusinesstailrecordVoList = new ArrayList<CrmBusinessTailRecordVO>();
    	if (CollectionUtils.isEmpty(crmbusinesstailrecordList)) {
    		return crmbusinesstailrecordVoList;
    	}
    	for (CrmBusinessTailRecordPO tag : crmbusinesstailrecordList) {
    		CrmBusinessTailRecordVO tagVo = BeanConvertor.copy(tag,CrmBusinessTailRecordVO.class);
    		crmbusinesstailrecordVoList.add(tagVo);
    	}
    	return crmbusinesstailrecordVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmBusinessTailRecordVO> queryPage(CrmBusinessTailRecordParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	Page<CrmBusinessTailRecordPO> crmbusinesstailrecordList=findPageByParams(CrmBusinessTailRecordPO.class,new Page<CrmBusinessTailRecordPO>(param.getOffset(),param.getLimit()),"CrmBusinessTailRecordPOMapper.queryPage",paramMap);
    	List<CrmBusinessTailRecordVO> rows = new ArrayList<CrmBusinessTailRecordVO>();
    	int total = 0;
    	if(crmbusinesstailrecordList.result != null){
    		for(int i=0;i<crmbusinesstailrecordList.result.size();i++){
    			CrmBusinessTailRecordPO recordPO = crmbusinesstailrecordList.result.get(i);
    			//查询业务员名字
    			CrmBusinessPO cBusinessPO = iCrmBusinessService.findByBusinessId(recordPO.getBusinessId());
    			if(cBusinessPO!=null){
    				CrmBusinessTailRecordVO cVo = BeanConvertor.convertBean(recordPO, CrmBusinessTailRecordVO.class);
    				cVo.setBusinessName(cBusinessPO.getName());//业务员名字
    				rows.add(i,cVo);
    			}
    		}
    		total = crmbusinesstailrecordList.getTotalResult();
    	}    	
    	Page<CrmBusinessTailRecordVO> pageResult = new Page<CrmBusinessTailRecordVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
    	
    }
    /**
     * 根据客户id查询对应的跟踪记录
    * @Title: queryList 
    * @author : lcl
    * @time : 2017年6月21日 下午4:52:29
    * @return Page<CrmBusinessTailRecordVO>    返回类型 
    * @throws
     */
    @Override
    public Page<CrmBusinessTailRecordVO> queryList(CrmBusinessTailRecordParam param) {
    	Page<CrmBusinessTailRecordVO> pageResult = new Page<CrmBusinessTailRecordVO>();
    	//根据customerId  查询 对象的 agent_canteen_id 
    	/*if(BeanUtils.isNotEmpty(param.getCustomerId())){
    		CrmClientResourceParam crmclientresourceParam = new  CrmClientResourceParam();
    		crmclientresourceParam.setId(param.getCustomerId());
    		List<CrmClientResourcePO> clientResourcePOs = clientResourceService.listByParam(crmclientresourceParam);
    		if(CollectionUtils.isNotEmpty(clientResourcePOs)){
    			CrmClientResourcePO clientResourcePO = clientResourcePOs.get(0);
    			param.setAccessAgentCanteenId(clientResourcePO.getAgentCanteenId());//客户Id 
    			
    			//设置客户类型
    			Integer clientType = clientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
    			if(1 == clientType){
    				param.setAccessType(1);//客户类型 1代理商 2 食堂
    			}else{
    				param.setAccessType(2);//客户类型 1代理商 2 食堂
    			}
    		}
    	}
    	*/
    	
    	ParamMap paramMap = new ParamMap(param);
    	if(BeanUtils.isNotEmpty(param.getAccessAgentCanteenId()) ){
    		
    		Page<CrmBusinessTailRecordPO> crmbusinesstailrecordList=findPageByParams(CrmBusinessTailRecordPO.class,new Page<CrmBusinessTailRecordPO>(param.getOffset(),param.getLimit()),"CrmBusinessTailRecordPOMapper.queryPage",paramMap);
    		List<CrmBusinessTailRecordVO> rows = new ArrayList<CrmBusinessTailRecordVO>();
    		int total = 0;
    		if(crmbusinesstailrecordList.result != null){
    			for(int i=0;i<crmbusinesstailrecordList.result.size();i++){
    				CrmBusinessTailRecordPO recordPO = crmbusinesstailrecordList.result.get(i);
    				//查询业务员名字
    				
    				CrmBusinessPO cBusinessPO = iCrmBusinessService.findByBusinessId(recordPO.getBusinessId());
    				CrmBusinessTailRecordVO cVo = BeanConvertor.convertBean(recordPO, CrmBusinessTailRecordVO.class);
    				if(cBusinessPO!=null){
    					cVo.setBusinessName(cBusinessPO.getName());//业务员名字
    					rows.add(i,cVo);
    				}
    				if(BeanUtils.isNotEmpty(recordPO.getTailNature())){
    					if(recordPO.getTailNature().equals(1)){
    						cVo.setTailNatureName("电话联系");
    					}
    					if(recordPO.getTailNature().equals(2)){
    						cVo.setTailNatureName("上门拜访");
    					}
    				}
    				if(BeanUtils.isNotEmpty(cVo.getTailTimeStart()) && BeanUtils.isNotEmpty(cVo.getTailTimeEnd())){
    					SimpleDateFormat format = new SimpleDateFormat("MM-dd hh:mm");
    					String data1 = format.format(cVo.getTailTimeStart());
    					String data2 = format.format(cVo.getTailTimeEnd());
    					cVo.setTailTimeStartAndEnd(data1+"——"+data2);
    				}
    				
    				
    			}
    			total = crmbusinesstailrecordList.getTotalResult();
    			pageResult.result = rows;
    			pageResult.setTotalResult(total);
    		}    	
    	}
    	return pageResult;
    	
    }
    
    
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<CrmBusinessTailRecordPO> listByParam(CrmBusinessTailRecordParam crmbusinesstailrecordParam){
    	ParamMap paramMap = new ParamMap(crmbusinesstailrecordParam);
    	return commonDao.listByParams(CrmBusinessTailRecordPO.class,"CrmBusinessTailRecordPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmBusinessTailRecordPO po = this.get(CrmBusinessTailRecordPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmBusinessTailRecordPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmBusinessTailRecordPO> listPo = new ArrayList<CrmBusinessTailRecordPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmBusinessTailRecordPO po = this.get(CrmBusinessTailRecordPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmBusinessTailRecordPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmBusinessTailRecordParam param){
    	CrmBusinessTailRecordPO crmbusinesstailrecordPO = BeanConvertor.copy(param,CrmBusinessTailRecordPO.class);
    	this.save(crmbusinesstailrecordPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmBusinessTailRecordPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_business_tail_record));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmBusinessTailRecordPO po){
     	this.update(po);
    }

	@Override
	public CrmBusinessTailRecordPO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 跟踪记录详情
	 */
	@Override
	public Boolean trackRegisterDetails(
			CrmBusinessTailRecordParam crmBusinessTailRecordParam,
			PageRespModel pageRespModel) {
		Long customerId = crmBusinessTailRecordParam.getCustomerId();//客户资源表Id
		if(null == customerId){
			pageRespModel.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
			pageRespModel.setDesc("参数有误");
			pageRespModel.setRows(null);
			return true;
		}
		//查询客户信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(customerId);
		crmClientResourcePO = (CrmClientResourcePO) clientResourceService.getByExample(crmClientResourcePO);
		if(BeanUtils.isEmpty(crmClientResourcePO)){
			pageRespModel.setCode(RespModel.RespCode.NOT_DATA.getCode());
			pageRespModel.setDesc("出错了");
			pageRespModel.setRows(null);
			return true;
		}
		
		
		
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		if(!(null != agentCanteenId && null !=clientType)){
			pageRespModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			pageRespModel.setDesc("出错了");
			pageRespModel.setRows(null);
			return true;
		}
		
		//查询跟踪记录信息
		CrmBusinessTailRecordParam businessTailRecordParam = new CrmBusinessTailRecordParam();
		businessTailRecordParam.setOffset(crmBusinessTailRecordParam.getOffset());
		businessTailRecordParam.setLimit(crmBusinessTailRecordParam.getLimit());
		businessTailRecordParam.setAccessAgentCanteenId(agentCanteenId);
		if(1 == clientType){
			businessTailRecordParam.setAccessType(1);//1代理商，2.食堂
		}else{
			businessTailRecordParam.setAccessType(2);
		}
		businessTailRecordParam.setDelFlag(1);
		
		//Page<CrmBusinessTailRecordVO> crmBusinessTailRecordVOPage = this.queryPage(businessTailRecordParam);
		Page<CrmBusinessTailRecordPO> crmBusinessTailRecordPOPage = this.commonQueryPage(businessTailRecordParam);
		List<CrmBusinessTailRecordPO> crmBusinessTailRecordPOs =  crmBusinessTailRecordPOPage.result;
		List<CrmBusinessTailRecordVO> crmBusinessTailRecordVOs = new ArrayList<CrmBusinessTailRecordVO>();
		Map<Long,String> businessIdWithBusinessName = new HashMap<Long,String>();//业务员Id:业务员名
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm");
		for (CrmBusinessTailRecordPO crmBusinessTailRecordPO : crmBusinessTailRecordPOs) {
			CrmBusinessTailRecordVO crmBusinessTailRecordVO = BeanConvertor.convertBean(crmBusinessTailRecordPO, CrmBusinessTailRecordVO.class);
			//获取业务员Id
			Long businessId = crmBusinessTailRecordPO.getBusinessId();
			businessIdWithBusinessName.put(businessId, null);
			
			//获取拜访开始与结束时间
			Date tailTimeStart = crmBusinessTailRecordPO.getTailTimeStart();
			String tailTimeStartStr = sdf.format(tailTimeStart);
			Date tailTimeEnd = crmBusinessTailRecordPO.getTailTimeEnd();
			String tailTimeEndStr = sdf.format(tailTimeEnd);
			String tailTimeQuantum = tailTimeStartStr+"—"+tailTimeEndStr;
			crmBusinessTailRecordVO.setTailTimeQuantum(tailTimeQuantum);
			
			crmBusinessTailRecordVOs.add(crmBusinessTailRecordVO);
		}
		
		//查询业务员名
		CrmBusinessParam crmBusinessParam = new CrmBusinessParam();
		crmBusinessParam.setIds(new LinkedList<Long>(businessIdWithBusinessName.keySet()));
		businessIdWithBusinessName.clear();
		List<CrmBusinessPO> crmBusinessPOs = iCrmBusinessService.commonQuery(crmBusinessParam);
		for (CrmBusinessPO crmBusinessPO : crmBusinessPOs) {
			businessIdWithBusinessName.put(crmBusinessPO.getId(), crmBusinessPO.getName());
		}
		
		//设置业务员名
		for(CrmBusinessTailRecordVO crmBusinessTailRecordVO : crmBusinessTailRecordVOs){
			Long businessId = crmBusinessTailRecordVO.getBusinessId();
			if(businessIdWithBusinessName.containsKey(businessId)){
				crmBusinessTailRecordVO.setBusinessName(businessIdWithBusinessName.get(businessId));
			}
		}
		
		
		
		pageRespModel.setCode(RespModel.RespCode.SUCCESS.getCode());
		pageRespModel.setRows(crmBusinessTailRecordVOs);
		pageRespModel.setTotal(crmBusinessTailRecordPOPage.getTotalResult());
		return false;
	}
	
	/***
	 * 通用分页查询 
	 */
	@Override
	public Page<CrmBusinessTailRecordPO> commonQueryPage(
			CrmBusinessTailRecordParam businessTailRecordParam) {
		ParamMap paramMap = new ParamMap(businessTailRecordParam);
		return findPageByParams(CrmBusinessTailRecordPO.class,new Page<CrmBusinessTailRecordPO>(businessTailRecordParam.getOffset(),businessTailRecordParam.getLimit()),"CrmBusinessTailRecordPOMapper.queryPage",paramMap);
	}

	/**
	 * 保存跟踪等级信息
	 */
	@Override
	public Boolean saveTrackRegisterMsg(
			CrmBusinessTailRecordParam crmBusinessTailRecordParam,
			RespModel respModel) {
		//查询客户信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(crmBusinessTailRecordParam.getCustomerId());
		crmClientResourcePO = (CrmClientResourcePO) clientResourceService.getByExample(crmClientResourcePO);
		if(BeanUtils.isEmpty(crmClientResourcePO)){
			respModel.setCode(RespModel.RespCode.NOT_DATA.getCode());
			respModel.setDesc("出错了");
			return true;
		}
		
		Long agentCanteenId = crmClientResourcePO.getAgentCanteenId();
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		if(!(null != agentCanteenId && null !=clientType)){
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc("出错了");
			return true;
		}
		
		//保存跟踪信息
		CrmBusinessTailRecordPO crmBusinessTailRecordPO = BeanConvertor.convertBean(crmBusinessTailRecordParam, CrmBusinessTailRecordPO.class);
		crmBusinessTailRecordPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_business_tail_record));
		crmBusinessTailRecordPO.setAccessAgentCanteenId(agentCanteenId);//客户Id
		if(1 == clientType){//客户类型
			crmBusinessTailRecordPO.setAccessType(1);
		}else{
			crmBusinessTailRecordPO.setAccessType(2);
		}
		crmBusinessTailRecordPO.setDelFlag(1);
		crmBusinessTailRecordPO.setCreateTime(new Date());
		crmBusinessTailRecordPO.setBusinessId(crmClientResourcePO.getBusinessId());//业务员Id
		logger.info("#####CrmBusinessTailRecordServiceImpl###saveTrackRegisterMsg##保存crmBusinessTailRecordPO:"+crmBusinessTailRecordPO.toString());
		this.save(crmBusinessTailRecordPO);
		
		//将资源表跟踪次数加1
		Long tailNum = crmClientResourcePO.getTailNum();
		if(null == tailNum){
			crmClientResourcePO.setTailNum(1L);
		}else{
			crmClientResourcePO.setTailNum(tailNum + 1);
		}
		logger.info("#####CrmBusinessTailRecordServiceImpl###saveTrackRegisterMsg##更新crmClientResourcePO:"+crmClientResourcePO.toString());
		clientResourceService.update(crmClientResourcePO);
		
		return false;
	}
}

