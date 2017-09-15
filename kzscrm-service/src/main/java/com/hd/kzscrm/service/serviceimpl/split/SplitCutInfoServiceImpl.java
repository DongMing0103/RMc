package com.hd.kzscrm.service.serviceimpl.split;

//splitcutinfo
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.canteen.CanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.split.SplitCutInfoPO;
import com.hd.kzscrm.service.constants.SplitConstants;
import com.hd.kzscrm.service.param.business.OrderParam;
import com.hd.kzscrm.service.param.split.SplitCutInfoParam;
import com.hd.kzscrm.service.serviceInter.business.CanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.split.ISplitCutInfoService;
import com.hd.kzscrm.service.util.RedisManager;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.kzscrm.service.vo.split.SplitCutInfoVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;
/**
 *
   
 * @description   splitCutInfo 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class SplitCutInfoServiceImpl extends BaseServiceImpl implements ISplitCutInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SplitCutInfoServiceImpl.class);
      //日志服务对象
     //static Logger logger = Logger.getLogger(SplitCutInfoServiceImpl.class);
     //@Autowired
     //private IndustryService industryService;
 
	@Resource
    private SqlSessionTemplate sqlSession;
	
	@Resource
	private CanteenBaseInfoService canteenBaseInfoService;
     /**
     *  默认构造函数
     */
     
	 public SplitCutInfoServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<SplitCutInfoVO> convertPOToVO(List<SplitCutInfoPO> splitcutinfoList){
    	List<SplitCutInfoVO> splitcutinfoVoList = new ArrayList<SplitCutInfoVO>();
    	if (CollectionUtils.isEmpty(splitcutinfoList)) {
    		return splitcutinfoVoList;
    	}
    	for (SplitCutInfoPO tag : splitcutinfoList) {
    		SplitCutInfoVO tagVo = BeanConvertor.copy(tag,SplitCutInfoVO.class);
    		splitcutinfoVoList.add(tagVo);
    	}
    	return splitcutinfoVoList;
    }

    /**
     * 查询
     */
    @Override
    public Page<SplitCutInfoVO> queryPage(SplitCutInfoParam param) {
    	 return null;
    	
    }
    
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<SplitCutInfoPO> listByParam(SplitCutInfoParam splitcutinfoParam){
    	ParamMap paramMap = new ParamMap(splitcutinfoParam);
    	return commonDao.listByParams(SplitCutInfoPO.class,"SplitCutInfoPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	SplitCutInfoPO po = this.get(SplitCutInfoPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("SplitCutInfoPOMapper.deleteById", paramMap);
        LOGGER.info("#####SplitCutInfoServiceImpl###deleteById##删除SplitCutInfoPOMapper:"+paramMap.toString());
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<SplitCutInfoPO> listPo = new ArrayList<SplitCutInfoPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		SplitCutInfoPO po = this.get(SplitCutInfoPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (SplitCutInfoPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(SplitCutInfoParam param){ 
    	param.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.split_cut_info));
    	ParamMap paramMap = new ParamMap(param); 
    	this.execute("SplitCutInfoPOMapper.isnertSplitCutInfo", paramMap);  
    }
    
    
    /**
     * 根据order编号添加退款记录
     */ 
    public void isnertSplitCutInfoByOrderNO(String orderNo){ 
    	ParamMap paramMap = new ParamMap(); 
    	paramMap.put("id", ServiceUtil.genNextIDValue(DatabaseTableNameEnum.split_cut_info));
    	paramMap.put("orderNo", orderNo);  
    	this.execute("SplitCutInfoPOMapper.isnertSplitCutInfoByOrderNO", paramMap);  
    }
    /**
     * 更新分成明细状态
     */
	@Override
	public int updateSplitCutInfoStatus(SplitCutInfoParam param) { 
    	ParamMap paramMap = new ParamMap(param); 
    	return this.execute("SplitCutInfoPOMapper.updateSplitCutInfoStatus", paramMap); 
	}
    

	@Override
	public Integer queryOrderNoISNull(SplitCutInfoParam param) {
		// TODO Auto-generated method stub
		ParamMap paramMap = new ParamMap(param); 
		Integer totalOrders=sqlSession.selectOne("SplitCutInfoPOMapper.queryOrderNoISNull", paramMap);
		if(BeanUtils.isEmpty(totalOrders)){
			return 0;
		}else{
			return totalOrders;
		} 
	}
	
	/**
	 * 分账查询
	 */
	@Override
	public Page<SplitCutInfoParam> queryErrorPage(SplitCutInfoParam param) {
		Page<SplitCutInfoParam> pageResult = new Page<SplitCutInfoParam>();
		String key = SplitConstants.SPLITCUTT1+SplitConstants.ERROR_POSTTFIX+SplitConstants.REDISLIST_POSTFIX;
		List<SplitCutInfoParam> splitCutInfoList = new ArrayList<SplitCutInfoParam>();
		Long totalResult = RedisManager.getListLength(key);
		if(totalResult!=0){
			List<String> list = RedisManager.getList(key, (long)param.getOffset(), (long)(param.getOffset()+param.getLimit()));
			for(String orderSuccessKey:list){
				SplitCutInfoParam splitCutInfoParam = RedisManager.getObject(orderSuccessKey, SplitCutInfoParam.class);
				if(splitCutInfoParam!=null){
					CanteenBaseInfoPO cPo = canteenBaseInfoService.get(CanteenBaseInfoPO.class, splitCutInfoParam.getCanteenId());
					if(BeanUtils.isNotEmpty(cPo) && BeanUtils.isNotEmpty(cPo.getName())){
						splitCutInfoParam.setCanteenName(cPo.getName());
					}
					splitCutInfoList.add(splitCutInfoParam);
				}else{
					RedisManager.delListByKey(orderSuccessKey, 10L, orderSuccessKey);
				}
			}
		}
		pageResult.result = splitCutInfoList;
    	pageResult.setTotalResult(totalResult.intValue());
		return pageResult;
	}

	@Override
	public BigDecimal queryMoneyByCanteenId(Long canteenId, String payTimestamp) {
		ParamMap paramMap = new ParamMap(); 
		paramMap.put("canteenId", canteenId);
		paramMap.put("payTimestamp", payTimestamp);
		BigDecimal canteenMoney=sqlSession.selectOne("SplitCutInfoPOMapper.queryMoneyByCanteenId", paramMap);
		return canteenMoney;
	}

	@Override
	public Page<SplitCutInfoPO> querySplitOrderCutInfo(SplitCutInfoParam splitCutInfoParam) {
		ParamMap paramMap=new ParamMap(splitCutInfoParam);
		return findPageByParams(SplitCutInfoPO.class, new Page<SplitCutInfoPO>(splitCutInfoParam.getOffset(), splitCutInfoParam.getLimit()), "SplitCutInfoPOMapper.querySplitOrder", paramMap);
	}

	@Override
	public List<SplitCutInfoPO> listSplitOrderCutInfo(SplitCutInfoParam splitCutInfoParam) {
		// TODO Auto-generated method stub
		ParamMap paramMap=new ParamMap(splitCutInfoParam);
		return this.listByParams(SplitCutInfoPO.class,"SplitCutInfoPOMapper.querySplitOrder",paramMap);
	}
	
	/**
	 * 查询订单基础信息
	 */
	@Override
	public Page<OrderVO> queryPage (OrderParam param) {
		Page<OrderVO> page = new Page<OrderVO>();
		List<OrderVO> list = new ArrayList<OrderVO>();
		
		ParamMap paramMap = new ParamMap(param);
		Page<OrderPO> orderList = findPageByParams(OrderPO.class, new Page<OrderPO>(param.getOffset(),param.getLimit()), "OrderMapper.findAll", paramMap);
		list = BeanConvertor.copyList(orderList.result, OrderVO.class);
		Iterator<OrderVO> i = list.iterator();
		while (i.hasNext()) {
			OrderVO orderVO = i.next();
			if (BeanUtils.isNotEmpty(orderVO.getChannelMoney())) {
				orderVO.setRealMoney(orderVO.getRealMoney().add(orderVO.getChannelMoney()));
			}
			// 商家信息
			if (BeanUtils.isNotEmpty(orderVO.getCanteenId())) {
				CanteenBaseInfoPO cPo = canteenBaseInfoService.get(CanteenBaseInfoPO.class, orderVO.getCanteenId());
				if (BeanUtils.isNotEmpty(cPo) && BeanUtils.isNotEmpty(cPo.getName())) {
					orderVO.setCanteenName(cPo.getName());
				}
			}
			// 获取商家金额
			if (BeanUtils.isNotEmpty(orderVO.getOrderNo())) {
				SplitCutInfoPO scp = this.findByOrderNo(orderVO.getOrderNo());
				if (BeanUtils.isNotEmpty(scp) && BeanUtils.isNotEmpty(scp.getRealMoney())) {
					orderVO.setCanteenMoney(scp.getRealMoney());
				}
			}
		}
		
		page.result = list;
		page.setTotalResult(orderList.getTotalResult());
		return page;
	}

	/**
	 * 根据订单编号查询
	 */
	@Override
	public SplitCutInfoPO findByOrderNo(String orderNo) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("orderNo", orderNo);
		List<SplitCutInfoPO> list = commonDao.listByParams(SplitCutInfoPO.class, "SplitCutInfoPOMapper.queryPage", paramMap);
		if (BeanUtils.isNotEmpty(list) && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
 
}

