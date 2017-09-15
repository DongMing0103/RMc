package com.hd.kzscrm.service.serviceimpl.canteen;

//crmcanteenapply
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenApplyPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.service.param.canteen.CrmCanteenApplyParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenApplyService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.client.ICrmClientResourceService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenApplyVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmCanteenApply 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmCanteenApplyServiceImpl extends BaseServiceImpl implements ICrmCanteenApplyService {
	/**
	 * 客户资源表
	 */
	@Resource
	ICrmClientResourceService crmClientResourceService;
	
	/**
	 * 食堂基础信息表
	 */
	@Resource
	ICrmCanteenBaseInfoService iCrmCanteenBaseInfoService;
	/**
	 * 业务员表
	 */
	@Resource
	ICrmBusinessService iCrmBusinessService;
	
	/**
	 * 抽成设置分账比例表
	 */
	@Resource
	ICrmSplitAssignSetService iCrmSplitAssignSetService;
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmCanteenApplyServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmCanteenApplyServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmCanteenApplyVO> convertPOToVO(List<CrmCanteenApplyPO> crmcanteenapplyList){
    	List<CrmCanteenApplyVO> crmcanteenapplyVoList = new ArrayList<CrmCanteenApplyVO>();
    	if (CollectionUtils.isEmpty(crmcanteenapplyList)) {
    		return crmcanteenapplyVoList;
    	}
    	for (CrmCanteenApplyPO tag : crmcanteenapplyList) {
    		CrmCanteenApplyVO tagVo = BeanConvertor.copy(tag,CrmCanteenApplyVO.class);
    		crmcanteenapplyVoList.add(tagVo);
    	}
    	return crmcanteenapplyVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmCanteenApplyVO> queryPage(CrmCanteenApplyParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmCanteenApplyName",param.getCrmCanteenApplyName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmCanteenApplyPO> crmcanteenapplyList = findPageByParams(CrmCanteenApplyPO.class,new Page<CrmCanteenApplyPO>(param.getOffset(),param.getLimit()),"CrmCanteenApplyPOMapper.queryPage",paramMap);
    	List<CrmCanteenApplyVO> rows = new ArrayList<CrmCanteenApplyVO>();
    	int total = 0;
    	if(crmcanteenapplyList.result != null){
    		rows = convertPOToVO(crmcanteenapplyList.result);
    		total = crmcanteenapplyList.getTotalResult();
    	}    	
    	Page<CrmCanteenApplyVO> pageResult = new Page<CrmCanteenApplyVO>();
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
    public List<CrmCanteenApplyPO> listByParam(CrmCanteenApplyParam crmcanteenapplyParam){
    	ParamMap paramMap = new ParamMap(crmcanteenapplyParam);
    	return commonDao.listByParams(CrmCanteenApplyPO.class,"CrmCanteenApplyPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmCanteenApplyPO po = this.get(CrmCanteenApplyPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmCanteenApplyPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmCanteenApplyPO> listPo = new ArrayList<CrmCanteenApplyPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmCanteenApplyPO po = this.get(CrmCanteenApplyPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmCanteenApplyPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmCanteenApplyParam param){
    	CrmCanteenApplyPO crmcanteenapplyPO = BeanConvertor.copy(param,CrmCanteenApplyPO.class);
    	this.save(crmcanteenapplyPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmCanteenApplyPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_canteen_apply));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmCanteenApplyPO po){
     	this.update(po);
    }

	@Override
	public CrmCanteenApplyPO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 食堂入驻申请信息保存 
	 * @throws ParseException 
	 */
	@Override
	public Boolean saveCanteenEnterApply(
			CrmCanteenApplyParam crmCanteenApplyParam, RespModel respModel) throws ParseException {
		Long clientId = crmCanteenApplyParam.getClientId();//获取客户资源Id
		Long createUid = crmCanteenApplyParam.getCreateUid();
		//查询客户资源信息
		CrmClientResourcePO crmClientResourcePO = new CrmClientResourcePO();
		crmClientResourcePO.setId(clientId);
		crmClientResourcePO = (CrmClientResourcePO) crmClientResourceService.getByExample(crmClientResourcePO);
		Integer clientType = crmClientResourcePO.getClientType();//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
		Integer checkStatus = crmClientResourcePO.getCheckStatus();//审核状态,0.申请中，1.申请通过，2.申请失败
		if(1 != clientType  && (null == checkStatus || 2 == checkStatus)){
			//日期解析
			String enterTimeStr = crmCanteenApplyParam.getEnterTimeStr();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date enterTime = sdf.parse(enterTimeStr);
			crmCanteenApplyParam.setEnterTime(enterTime);
			
			Date currentDate = new Date();
			Long canteenId = crmClientResourcePO.getAgentCanteenId();
			//新增申请信息
			CrmCanteenApplyPO crmCanteenApplyPO = BeanConvertor.convertBean(crmCanteenApplyParam,CrmCanteenApplyPO.class);
			crmCanteenApplyPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_canteen_apply));
			crmCanteenApplyPO.setApplyTime(currentDate);
			crmCanteenApplyPO.setCheckStatus(0);//审核状态 ,0.申请中，1.申请通过，2.申请失败
			crmCanteenApplyPO.setCanteenId(canteenId);
			crmCanteenApplyPO.setDelFlag(1);
			logger.info("#####CrmCanteenApplyServiceImpl###saveCanteenEnterApply##保存crmCanteenApplyPO:"+crmCanteenApplyPO.toString());
			this.save(crmCanteenApplyPO);
			
			//更新客户信息
			crmClientResourcePO.setApplyTime(currentDate);
			crmClientResourcePO.setCheckStatus(0);//申请中
			logger.info("#####CrmCanteenApplyServiceImpl###saveCanteenEnterApply##更新crmClientResourcePO:"+crmClientResourcePO.toString());
			crmClientResourceService.update(crmClientResourcePO);
			
			//更新新食堂信息
			CrmCanteenBaseInfoPO crmCanteenBaseInfoPO = new CrmCanteenBaseInfoPO();
			crmCanteenBaseInfoPO.setId(canteenId);//设置食堂Id
			crmCanteenBaseInfoPO.setStatus(5);//状态（1未审核、2已通过 、3已注销 4.未通过 5.申请中）
			crmCanteenBaseInfoPO.setUpdaterUid(createUid);//修改人
			crmCanteenBaseInfoPO.setUpdateTime(currentDate);//修改时间
			logger.info("#####CrmCanteenApplyServiceImpl###saveCanteenEnterApply##更新crmCanteenBaseInfoPO:"+crmCanteenBaseInfoPO.toString());
			iCrmCanteenBaseInfoService.update(crmCanteenBaseInfoPO);
			
			//新增抽成分配设置信息
			CrmSplitAssignSetPO crmSplitAssignSetPO = new CrmSplitAssignSetPO();
			crmSplitAssignSetPO.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_split_assign_set));
			crmSplitAssignSetPO.setCanteenId(canteenId);//食堂id
			BigDecimal canteenSplitPercent = crmCanteenApplyParam.getCanteenSplitPercent();
			crmSplitAssignSetPO.setCanteenSplitPercent(canteenSplitPercent.divide(new BigDecimal(100)));//分账比例
			crmSplitAssignSetPO.setDelFlag(1);
			Long businessId = crmClientResourcePO.getBusinessId();
			crmSplitAssignSetPO.setBusinessId(businessId);//业务员Id
			//查询业务员信息
			CrmBusinessPO crmBusinessPO = new CrmBusinessPO();
			crmBusinessPO.setId(businessId);
			crmBusinessPO.setDelFlag(1);
			crmBusinessPO = (CrmBusinessPO) iCrmBusinessService.getByExample(crmBusinessPO);
			
			Integer type = crmBusinessPO.getType();//业务员类型，1.内部业务员，2.外部业务员
			if(2 == type){
				crmSplitAssignSetPO.setAgentId(crmBusinessPO.getAgentId());//代理商Id
			}
			logger.info("#####CrmCanteenApplyServiceImpl###saveCanteenEnterApply##保存crmSplitAssignSetPO:"+crmSplitAssignSetPO.toString());
			iCrmSplitAssignSetService.save(crmSplitAssignSetPO);
			
			
			}else{
				respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
				respModel.setDesc("申请中，不能重复申请");
				respModel.setRows(null);
				return true;
			}
		
		
		return false;
	}

	/**
	 * 根据参数查询食堂入驻申请信息
	 */
	@Override
	public List<CrmCanteenApplyPO> commonQuery(
			CrmCanteenApplyParam crmCanteenApplyParam) {
		ParamMap paramMap = new ParamMap(crmCanteenApplyParam);
		return commonDao.listByParams(CrmCanteenApplyPO.class, "CrmCanteenApplyPOMapper.commonQuery", paramMap );
	}

	/**
	 * 根据参数删除申请信息(delFlag置0)
	 */
	@Override
	public void deleteCanteenApplyEntity(
			CrmCanteenApplyParam crmCanteenApplyParam) {
		ParamMap paramMap = new ParamMap(crmCanteenApplyParam);
		commonDao.execute("CrmCanteenApplyPOMapper.deleteCanteenApplyEntity", paramMap );
		
	}
}

