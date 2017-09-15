package com.hd.kzscrm.service.serviceInter.area;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.service.param.area.BaseAreaParam;
import com.hd.kzscrm.service.vo.area.BaseAreaVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface IBaseAreaService extends BaseService {
	
	

	/**
	 * 获取全部省级信息
	 * 
	 * */
	List<BaseAreaPO> getProvince();
	
	/**
	 * 获取市级信息
	 * 
	 * */
	List<BaseAreaPO> getCityByParentCode(BaseAreaPO areaPO);
	/**
	* 级联查询
	* @param leval
	* @return List<BaseAreaDTO> 
	* @author create 苏常松
	* @date create 2017年3月10日 下午6:10:54
	 */
	//List<Map> getCityAll(BaseAreaDTO baDto);
	/**
	 * 
	* 通过id获取位置信息 
	* @param id
	* @return BaseAreaPO 
	* @author create 苏常松
	* @date create 2017年3月11日 上午10:28:04
	 */
	BaseAreaPO getCityById(Long id);
	
	
	/**
	 *通过区id获取全名称 
	* @author 苏常松
	* @date 2017年3月20日 上午10:10:04
	 */
	String getAreaName(Long id);
	
    
        
    public Page<BaseAreaVO> queryPage(BaseAreaParam param); 
    
        
        
    
    public List<BaseAreaPO> listByParam(BaseAreaParam baseareaParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(BaseAreaParam param);
    
    /**
    add save
    */
    public void saveEntity(BaseAreaPO param);
    
    /**
    update  save
    */
    public void updateEntity(BaseAreaPO param);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月6日 下午3:24:37
	 */
	List<BaseAreaPO> findByAreaCodes(List<Long> areaCodes);
	/**
	 * 根据areaCode查询对象
	* @Title: findByAreaCode 
	* @author : lcl
	* @time : 2017年6月7日 下午3:06:23
	* @return BaseAreaPO    返回类型 
	* @throws
	 */
	BaseAreaPO findByAreaCode(Long areaCode);
	
	/** 
	 * 查询上级主管
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 上午11:20:13 
	*/
	public List<BaseAreaPO> findByAreaCodes(String areaCodes);

	/**
	 * @author 黄霄仪
	 * @date 2017年8月1日 下午1:45:13
	 */
	List<String> getAreaNamesByAreaCode(Long areaCode);
}
