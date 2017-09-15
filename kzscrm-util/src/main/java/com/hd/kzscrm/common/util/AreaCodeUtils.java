/**
 * 
 */
package com.hd.kzscrm.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄霄仪
 * @date 2017年6月6日 下午3:11:42
 * 
 */
public class AreaCodeUtils {
	/**
	 * 区域编码集合
	 * @author 黄霄仪
	 * @date 2017年6月6日 下午3:09:39
	 */
	public static List<Long> getAllOfAreaCode(Long areaCode){
		List<Long> areaCodes=new ArrayList<>();
		StringBuilder areaCodeSb=new StringBuilder(areaCode.toString());
		//如果不等于0，说明省级的是1位
		if(areaCodeSb.toString().length()%2!=0){
			areaCodeSb.insert(4,"0");
		}
		char[] charArray = areaCodeSb.toString().toCharArray();
		for (int i = 0; i < charArray.length; i++) {
				//等于2就是省级的
			String areaCodeStr = areaCodeSb.toString().substring(0,i+1);
			if(areaCodes.size()<2){
				if((i+1)%2==0){
					areaCodes.add(Long.parseLong(areaCodeStr));
				}
			}else{
				//处理省级以后的区域编号
				if((i+1)%2==0){
					StringBuilder areaCodeSbTemp=new StringBuilder(areaCodeStr);
					areaCodeSbTemp.deleteCharAt(4);
					areaCodes.add(Long.parseLong(areaCodeSbTemp.toString()));
				}
			}
		}
		return areaCodes;
	}
}
