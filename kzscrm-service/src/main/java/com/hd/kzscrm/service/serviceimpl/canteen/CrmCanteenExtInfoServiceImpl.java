package com.hd.kzscrm.service.serviceimpl.canteen;

//crmcanteenbaseinfo
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.DeleteFlagEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenExtInfoPO;
import com.hd.kzscrm.service.param.canteen.CrmCanteenExtInfoParam;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenExtInfoService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenExtInfoVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmCanteenBaseInfo 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmCanteenExtInfoServiceImpl extends BaseServiceImpl implements ICrmCanteenExtInfoService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmCanteenExtInfoServiceImpl.class);
	 
	 @Resource
	 private SqlSessionTemplate sqlSession;
     
     /**
     *  默认构造函数
     */
     
	 public CrmCanteenExtInfoServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
	 /**
	  * 根据食堂ID获取数据
	  * @author 黄霄仪
	  * @date 2017年6月22日 下午3:51:43
	  */
	 @Override
	 public CrmCanteenExtInfoVO findByBaseInfoId(Long canteenId){
		 Assert.notNull(canteenId,"com.hd.kzscrm.service.serviceimpl.canteen.CrmCanteenExtInfoServiceImpl.findByBaseInfoId(Long),食堂ID不能为空");
		 CrmCanteenExtInfoParam param=new CrmCanteenExtInfoParam();
		 param.setBaseInfoId(canteenId);
		 List<CrmCanteenExtInfoPO> crmCanteenExtInfoPOs = this.listByParam(param);
		 if(BeanUtils.isNotEmpty(crmCanteenExtInfoPOs)){
			 Assert.isTrue(crmCanteenExtInfoPOs.size()==1,"食堂扩展表信息数据不能大于1");
			 return BeanConvertor.copy(crmCanteenExtInfoPOs.get(0),CrmCanteenExtInfoVO.class);
		 }
		 return null;
	 }
        
    /**
     * 查询
     */
    @Override
    public Page<CrmCanteenExtInfoVO> queryPage(CrmCanteenExtInfoParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmCanteenBaseInfoName",param.getCrmCanteenBaseInfoName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmCanteenExtInfoPO> crmcanteenextinfoList = findPageByParams(CrmCanteenExtInfoPO.class,new Page<CrmCanteenExtInfoPO>(param.getOffset(),param.getLimit()),"CrmCanteenExtInfoPOMapper.getAll",paramMap);
    	List<CrmCanteenExtInfoVO> rows = new ArrayList<CrmCanteenExtInfoVO>();
    	int total = 0;
    	if(crmcanteenextinfoList.result != null){
    		rows = BeanConvertor.copyList(crmcanteenextinfoList.result,CrmCanteenExtInfoVO.class);
    		total = crmcanteenextinfoList.getTotalResult();
    	}    	
    	Page<CrmCanteenExtInfoVO> pageResult = new Page<CrmCanteenExtInfoVO>();
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
    public List<CrmCanteenExtInfoPO> listByParam(CrmCanteenExtInfoParam CrmCanteenExtInfoParam){
    	ParamMap paramMap = new ParamMap(CrmCanteenExtInfoParam);
    	return commonDao.listByParams(CrmCanteenExtInfoPO.class,"CrmCanteenExtInfoPOMapper.getAll",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmCanteenExtInfoPO po = this.get(CrmCanteenExtInfoPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmCanteenExtInfoPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmCanteenExtInfoPO> listPo = new ArrayList<CrmCanteenExtInfoPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmCanteenExtInfoPO po = this.get(CrmCanteenExtInfoPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmCanteenExtInfoPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmCanteenExtInfoParam param){
    	Long id = param.getId();
    	if(BeanUtils.isEmpty(id)){
    		param.setId(ServiceUtil.genNextIDValue(DatabaseTableNameEnum.crm_canteen_ext_info));
    	}
    	CrmCanteenExtInfoPO crmCanteenExtInfoPO = BeanConvertor.copy(param,CrmCanteenExtInfoPO.class);
    	this.save(crmCanteenExtInfoPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmCanteenExtInfoPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_canteen_ext_info));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmCanteenExtInfoPO po){
     	this.update(po);
    }

	@Override
	public CrmCanteenExtInfoPO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

