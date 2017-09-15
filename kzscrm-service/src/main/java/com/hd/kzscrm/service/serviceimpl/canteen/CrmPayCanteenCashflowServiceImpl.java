package com.hd.kzscrm.service.serviceimpl.canteen;

//crmcanteenapply
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.enums.OrderFlowNum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.business.CrmBusinessAmtPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenApplyPO;
import com.hd.kzscrm.dao.entity.canteen.CrmPayCanteenCashflowPO;
import com.hd.kzscrm.service.param.canteen.CrmPayCanteenCashflowParam;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessAmtService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmPayCanteenCashflowService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.canteen.CrmPayCanteenCashflowVO;
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
public class CrmPayCanteenCashflowServiceImpl extends BaseServiceImpl implements ICrmPayCanteenCashflowService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmPayCanteenCashflowServiceImpl.class);
	 @Resource
	 private SqlSessionTemplate sqlSession;
	 @Autowired	
	 private ICrmBusinessAmtService iCrmBusinessAmtService;
     /**
     *  默认构造函数
     */
     
	 public CrmPayCanteenCashflowServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmPayCanteenCashflowVO> convertPOToVO(List<CrmPayCanteenCashflowPO> crmcanteenapplyList){
    	List<CrmPayCanteenCashflowVO> crmcanteenapplyVoList = new ArrayList<CrmPayCanteenCashflowVO>();
    	if (CollectionUtils.isEmpty(crmcanteenapplyList)) {
    		return crmcanteenapplyVoList;
    	}
    	for (CrmPayCanteenCashflowPO tag : crmcanteenapplyList) {
    		CrmPayCanteenCashflowVO tagVo = BeanConvertor.copy(tag,CrmPayCanteenCashflowVO.class);
    		crmcanteenapplyVoList.add(tagVo);
    	}
    	return crmcanteenapplyVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmPayCanteenCashflowVO> queryPage(CrmPayCanteenCashflowParam param) {
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
    	Page<CrmPayCanteenCashflowPO> crmcanteenapplyList = findPageByParams(CrmPayCanteenCashflowPO.class,new Page<CrmPayCanteenCashflowPO>(param.getOffset(),param.getLimit()),"CrmPayCanteenCashflowPOMapper.queryPage",paramMap);
    	List<CrmPayCanteenCashflowVO> rows = new ArrayList<CrmPayCanteenCashflowVO>();
    	int total = 0;
    	if(crmcanteenapplyList.result != null){
    		rows = convertPOToVO(crmcanteenapplyList.result);
    		total = crmcanteenapplyList.getTotalResult();
    	}    	
    	Page<CrmPayCanteenCashflowVO> pageResult = new Page<CrmPayCanteenCashflowVO>();
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
    public List<CrmPayCanteenCashflowPO> listByParam(CrmPayCanteenCashflowParam crmcanteenapplyParam){
    	ParamMap paramMap = new ParamMap(crmcanteenapplyParam);
    	return commonDao.listByParams(CrmPayCanteenCashflowPO.class,"CrmPayCanteenCashflowPOMapper.queryList",paramMap);
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
        this.execute("CrmPayCanteenCashflowPOMapper.deleteById", paramMap);
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
    public void add(CrmPayCanteenCashflowParam param){
    	CrmPayCanteenCashflowPO crmcanteenapplyPO = BeanConvertor.copy(param,CrmPayCanteenCashflowPO.class);
    	this.save(crmcanteenapplyPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmPayCanteenCashflowParam param){
    	CrmPayCanteenCashflowPO po=BeanConvertor.convertBean(param, CrmPayCanteenCashflowPO.class);
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_pay_canteen_cashflow));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmPayCanteenCashflowParam param){
    	CrmPayCanteenCashflowPO po=BeanConvertor.convertBean(param, CrmPayCanteenCashflowPO.class);
     	this.update(po);
    }

	@Override
	public CrmPayCanteenCashflowPO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 添加流水 修改金额
	 * @return
	 */
	public boolean savePayCanteenCashflow(CrmPayCanteenCashflowParam crmPayCanteenCashflowParam){
		crmPayCanteenCashflowParam.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_pay_canteen_cashflow));
		crmPayCanteenCashflowParam.setCashFlowNo(ServiceUtil.getOrderFlowNumByOrderFlowNum(OrderFlowNum.PAY_CANTEEN_CASHFLOW,10));
		crmPayCanteenCashflowParam.setCreateTime(new Date());   
		if(crmPayCanteenCashflowParam.getUserType()==6){
			CrmPayCanteenCashflowPO crmPayCanteenCashflowPO = BeanConvertor.copy(crmPayCanteenCashflowParam,CrmPayCanteenCashflowPO.class);
			this.save(crmPayCanteenCashflowPO);
		}else{
			CrmBusinessAmtPO crmBusinessAmtPO= iCrmBusinessAmtService.findByBusinessId(crmPayCanteenCashflowParam.getUserId(), crmPayCanteenCashflowParam.getUserType());
			crmPayCanteenCashflowParam.setBalance(crmBusinessAmtPO.getCanWithdrawDeposit().add(crmPayCanteenCashflowParam.getCashFlowMoney()));
			crmPayCanteenCashflowParam.setPayTimestamp(crmBusinessAmtPO.getPayTimestamp()); 
			//商家用户现金流水表添加数据 
			crmPayCanteenCashflowParam.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_pay_canteen_cashflow));
			crmPayCanteenCashflowParam.setCashFlowNo(ServiceUtil.getOrderFlowNumByOrderFlowNum(OrderFlowNum.PAY_CANTEEN_CASHFLOW,10));
			crmPayCanteenCashflowParam.setCreateTime(new Date());  
			CrmPayCanteenCashflowPO crmPayCanteenCashflowPO = BeanConvertor.copy(crmPayCanteenCashflowParam,CrmPayCanteenCashflowPO.class);
			this.save(crmPayCanteenCashflowPO); 
			crmBusinessAmtPO.setCanWithdrawDeposit(crmPayCanteenCashflowParam.getCashFlowMoney());
			crmBusinessAmtPO.setBalance(crmPayCanteenCashflowParam.getCashFlowMoney());
			crmBusinessAmtPO.setTotalIncome(crmPayCanteenCashflowParam.getCashFlowMoney());
			iCrmBusinessAmtService.updateCrmBusinessAmt(crmPayCanteenCashflowParam.getUserId(), crmPayCanteenCashflowParam.getCashFlowMoney(), crmPayCanteenCashflowParam.getUserType());
		}
		
		return  true;
	} 
	/**
	 * 查询流水
	 * @param orderid
	 * @param userid
	 * @return
	 */
	public Integer queryPayCanteenCashflow(String orderNo,Long userid){
		// TODO Auto-generated method stub
		ParamMap paramMap = new ParamMap(); 
		paramMap.put("orderNo", orderNo);
		paramMap.put("userId", userid);
		Integer totalOrders=sqlSession.selectOne("CrmPayCanteenCashflowPOMapper.queryPayCanteenCashflow", paramMap);
		if(BeanUtils.isEmpty(totalOrders)){
			return 0;
		}else{
			return totalOrders;
		} 
	}

	@Override
	public CrmPayCanteenCashflowPO findByUserId(Long userId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("userId", userId);
		List<CrmPayCanteenCashflowPO> cashflowPOs = listByParams(CrmPayCanteenCashflowPO.class,"CrmPayCanteenCashflowPOMapper.findByUserId", paramMap);
		if (BeanUtils.isNotEmpty(cashflowPOs)) {
			return cashflowPOs.get(0);
		}
		return null;
	}
	
}

