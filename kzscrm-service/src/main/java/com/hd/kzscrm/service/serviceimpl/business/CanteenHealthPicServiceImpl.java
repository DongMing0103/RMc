package com.hd.kzscrm.service.serviceimpl.business;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.hd.kzscrm.dao.entity.business.CanteenHealthPicPO;
import com.hd.kzscrm.service.param.business.CanteenHealthPicParam;
import com.hd.kzscrm.service.serviceInter.business.CanteenHealthPicService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;
/**
 * 卫生许可证
 * @author lcl
 *	2017年5月12日
 */
@Service
public class CanteenHealthPicServiceImpl extends BaseServiceImpl implements CanteenHealthPicService{

	@Override
	public void deleteByCanteenId(Long canteenId) {
		ParamMap paramMap=new ParamMap();
		paramMap.put("canteenId", canteenId);
		this.commonDao.execute("CanteenHealthPicMapper.deleteByCanteenId",paramMap);
	}

	/**
	 * 根据商家id查询卫生许可证
	* @Title: findByCanteenId 
	* @author : lcl
	* @time : 2017年5月15日 上午10:44:34
	* @return List<CanteenHealthPicPO>    返回类型 ：商家为什么许可证
	* @throws
	 */
	@Override
	public List<CanteenHealthPicPO> findByCanteenId(Long canteenId) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("canteenId", canteenId);
		List<CanteenHealthPicPO> canteenHealthPicPOs = commonDao.listByParams(CanteenHealthPicPO.class, "CanteenHealthPicMapper.findByCanteenId", paramMap);
		if(CollectionUtils.isNotEmpty(canteenHealthPicPOs)){
			return canteenHealthPicPOs;
		}
		return null;
	}

	/**
	 * 通过crmCanteenId删除卫生许可证
	 */
	@Override
	public void deleteByCrmCanteenId(Long id) {
		
		ParamMap paramMap = new ParamMap();
		paramMap.put("crmCanteenId", id);
		paramMap.put("delFlag", 0);
		this.commonDao.execute("CanteenHealthPicMapper.deleteByCrmCanteenId",paramMap );
	}

	/***
	 * 更新卫生许可证(嵌入CanteenId,即qzs食堂id)
	 */
	@Override
	public void updateByparam(CanteenHealthPicParam canteenHealthPicParam) {
		ParamMap paramMap = new ParamMap(canteenHealthPicParam);
		this.commonDao.execute("CanteenHealthPicMapper.updateByparam",paramMap );
		
	}

	/**
	 * 通用查询
	 */
	@Override
	public List<CanteenHealthPicPO> commonQuery(
			CanteenHealthPicParam canteenHealthPicParam) {
		ParamMap paramMap = new ParamMap(canteenHealthPicParam);
		return commonDao.listByParams(CanteenHealthPicPO.class, "CanteenHealthPicMapper.commonQuery", paramMap );
	}

}
