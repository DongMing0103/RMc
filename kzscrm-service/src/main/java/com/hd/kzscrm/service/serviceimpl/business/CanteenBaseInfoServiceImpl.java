package com.hd.kzscrm.service.serviceimpl.business;


import org.springframework.stereotype.Service;

import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.PinyinUtil;
import com.hd.kzscrm.dao.entity.area.BaseAreaPO;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.serviceInter.business.CanteenBaseInfoService;
import com.hd.wolverine.service.BaseServiceImpl;

/**
 * 商家信息
 * @author Administrator
 *
 */
@Service
public class CanteenBaseInfoServiceImpl extends BaseServiceImpl implements CanteenBaseInfoService{
	private IBaseAreaService iBaseAreaService;
	/**
	 * 苏常松
	 * 食堂编码【食堂性质-所属省份拼音-所属市拼音首字母-长度为4的自然数】
	 * @param id 生产编码id
	 * @param length  后自然数长度
	 * @param areaCode 区编码
	 * @return
	 */
	@Override
	public String getCanteenNo(Long id, int length, String eStyle,Long areaCode) {
		String nums = CommUtil.getNumByFigures(id.toString(),length);
		String cityName=this.getAreaName(areaCode);
		if(BeanUtils.isNotEmpty(cityName)){
			int cityIndex=cityName.indexOf("/");
			String cityShengUpper=PinyinUtil.getPinYinHeadChar(cityName.substring(0,2));
			String citySUpper=PinyinUtil.getPinYinHeadChar(cityName.substring(cityIndex+1,cityIndex+3));
			String enterPriceNO = eStyle.concat("-").concat(cityShengUpper).concat("-").concat(citySUpper).concat("-").concat(nums);
			return enterPriceNO.toUpperCase();
		}
		return null;
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
}
