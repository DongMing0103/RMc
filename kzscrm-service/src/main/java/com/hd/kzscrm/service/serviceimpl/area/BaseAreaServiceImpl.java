package com.hd.kzscrm.service.serviceimpl.area;

//basearea
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.AreaCodeUtils;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.service.param.area.BaseAreaParam;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.area.BaseAreaVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   baseArea 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class BaseAreaServiceImpl extends BaseServiceImpl implements IBaseAreaService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(BaseAreaServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public BaseAreaServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<BaseAreaVO> convertPOToVO(List<BaseAreaPO> baseareaList){
    	List<BaseAreaVO> baseareaVoList = new ArrayList<BaseAreaVO>();
    	if (CollectionUtils.isEmpty(baseareaList)) {
    		return baseareaVoList;
    	}
    	for (BaseAreaPO tag : baseareaList) {
    		BaseAreaVO tagVo = BeanConvertor.copy(tag,BaseAreaVO.class);
    		baseareaVoList.add(tagVo);
    	}
    	return baseareaVoList;
    }
    
    /**
     * 根据区域编号集合，获取区域信息
     * @author 黄霄仪
     * @date 2017年6月6日 下午3:24:42
     */
    @Override
    public List<BaseAreaPO> findByAreaCodes(List<Long> areaCodes){
    	ParamMap paramMap=new ParamMap();
    	paramMap.put("areaCodes", areaCodes);
    	return listByParams(BaseAreaPO.class, "BaseAreaPOMapper.queryList", paramMap);
    	
    }
    /**
     * 根据区域编号，获取地理全名
     * @author 黄霄仪
     * @date 2017年8月1日 下午1:44:55
     */
    @Override
    public List<String> getAreaNamesByAreaCode(Long areaCode){
    	List<Long> areaCodes = AreaCodeUtils.getAllOfAreaCode(areaCode);
    	//获取区域名称
		List<BaseAreaPO> baseAreaPOs = this.findByAreaCodes(areaCodes);
		List<String> areaNames=new LinkedList<>();
		for (BaseAreaPO baseAreaPO : baseAreaPOs) {
			areaNames.add(baseAreaPO.getAreaName());
		}
		return areaNames;
    }
        
    /**
     * 查询
     */
    @Override
    public Page<BaseAreaVO> queryPage(BaseAreaParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("BaseAreaName",param.getBaseAreaName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<BaseAreaPO> baseareaList = findPageByParams(BaseAreaPO.class,new Page<BaseAreaPO>(param.getOffset(),param.getLimit()),"BaseAreaPOMapper.queryPage",paramMap);
    	List<BaseAreaVO> rows = new ArrayList<BaseAreaVO>();
    	int total = 0;
    	if(baseareaList.result != null){
    		rows = convertPOToVO(baseareaList.result);
    		total = baseareaList.getTotalResult();
    	}    	
    	Page<BaseAreaVO> pageResult = new Page<BaseAreaVO>();
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
    public List<BaseAreaPO> listByParam(BaseAreaParam baseareaParam){
    	ParamMap paramMap = new ParamMap(baseareaParam);
    	return commonDao.listByParams(BaseAreaPO.class,"BaseAreaPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	BaseAreaPO po = this.get(BaseAreaPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("BaseAreaPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<BaseAreaPO> listPo = new ArrayList<BaseAreaPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		BaseAreaPO po = this.get(BaseAreaPO.class, idl[i]);
    		if (po != null) {
//    			po.setDelFlag(DeleteFlagEnum.DELETED.getNormalCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (BaseAreaPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(BaseAreaParam param){
    	BaseAreaPO baseareaPO = BeanConvertor.copy(param,BaseAreaPO.class);
    	this.save(baseareaPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(BaseAreaPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.base_area));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(BaseAreaPO po){
     	this.update(po);
    }
    
    
    /**
	 * 获取省级信息
	 * 
	 * */
	@Override
	public List<BaseAreaPO> getProvince() {
		BaseAreaPO baseAreaPO = new BaseAreaPO();
		baseAreaPO.setAreaLevel(new Long(2));
		return listByExample(baseAreaPO);
	}

	/**
	 * 获取市级信息
	 * 
	 * */
	@Override
	public List<BaseAreaPO> getCityByParentCode(BaseAreaPO areaPO) {
		return listByExample(areaPO);
	}

	/**
	 * 
	* 通过id获取位置信息 
	* @param id
	* @return BaseAreaPO 
	* @author create 苏常松
	* @date create 2017年3月11日 上午10:28:04
	 */
	@Override
	public BaseAreaPO getCityById(Long id){
		return commonDao.get(BaseAreaPO.class,id);
	};
	
	/**
	* 递归方式获取到所有的城市
	* @param baDto
	* @return List<BaseAreaDTO> 
	* @author create 苏常松
	* @date create 2017年3月10日 下午6:10:54
	 */
	/*@Override
	public List<Map> getCityAll(BaseAreaDTO baDto) {
		ParamMap paramMap=new ParamMap();
		paramMap.put("areaLevel",baDto.getAreaLevel());
		paramMap.put("parentCode",baDto.getParentCode());
		List<Map> mapList=commonDao.listByParams("BaseAreaPOMapper.getCityByParentCodeLevel", paramMap);
		for(Map map:mapList){
			map.put("p",map.get("n"));
			map.remove("n");
			map.remove("level");
			map.put("c",getChild(map));
		}
		return mapList;
	}*/
	public List<Map> getChild(Map map){
		Long areaCode=(Long) map.get("id");
		ParamMap paramMap=new ParamMap();
		paramMap.put("parentCode",areaCode);
		List<Map> mapList=commonDao.listByParams("BaseAreaPOMapper.getCityByParentCodeLevel", paramMap);
		if(mapList!=null && mapList.size()>0){
			for(Map map1:mapList){
				if(Long.parseLong(map1.get("level").toString())==3){
					map1.get("n");
					map1.remove("level");
					map1.put("a",getChild(map1));
				}else if(Long.parseLong(map1.get("level").toString())==4){
					map1.remove("level");
					map1.put("s",map1.get("n"));
					map1.remove("n");
				}
			}
		}
		return mapList;
	}

	/**
	 *通过区id获取全名称 
	* @author 苏常松
	* @date 2017年3月20日 上午10:10:04
	 */
	@Override
	public String getAreaName(Long id) {
		BaseAreaPO bapo=commonDao.get(BaseAreaPO.class,id);
		String cityName="";
		if(bapo!=null){
			cityName=bapo.getAreaName();
			if(bapo!=null && bapo.getParentCode()!=null){
				BaseAreaPO bapo1=commonDao.get(BaseAreaPO.class,bapo.getParentCode());
				cityName=bapo1.getAreaName()+"/"+cityName;
				if(bapo1!=null && bapo1.getParentCode()!=null){
					BaseAreaPO bapo2=commonDao.get(BaseAreaPO.class,bapo1.getParentCode());
					cityName=bapo2.getAreaName()+"/"+cityName;
				}
			}
		}
		return cityName;
	}

	/**
	 * 根据areaCode查询对象
	 */
	@Override
	public BaseAreaPO findByAreaCode(Long areaCode) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("areaCode", areaCode);
		List<BaseAreaPO> areaPOs = listByParams(BaseAreaPO.class, "BaseAreaPOMapper.findByAreaCode", paramMap);
		if(CollectionUtils.isNotEmpty(areaPOs)){
			return areaPOs.get(0);
		}
		return null;
	}

	/** 
	 * 查询上级主管
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 上午11:20:35 
	*/
	@Override
	public List<BaseAreaPO> findByAreaCodes(String areaCodes) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("areaCode", areaCodes);
		return listByParams(BaseAreaPO.class,"BaseAreaPOMapper.findByAreaCodes" ,paramMap);
	}
	
}

