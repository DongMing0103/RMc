package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.dao.entity.business.CanteenHealthPicPO;
import com.hd.kzscrm.service.param.business.CanteenHealthPicParam;
import com.hd.wolverine.service.BaseService;
/**
 * 商家卫生许可证
 * @author lcl
 *	2017年5月12日
 */
public interface CanteenHealthPicService extends BaseService {
		/**
		 * 通过商家id删除数据（真删）
		 * @param id 商家id
		 */
		void deleteByCanteenId(Long id);
		/**
		 * 根据商家id查询卫生许可证
		* @Title: findByCanteenId 
		* @author : lcl
		* @time : 2017年5月15日 上午10:44:34
		* @return List<CanteenHealthPicPO>    返回类型 ：商家为什么许可证
		* @throws
		 */
		List<CanteenHealthPicPO> findByCanteenId(Long canteenId);
		/**
		 * 
		 * @Title: deleteByCrmCanteenId 
		 * @Description: 通过crmCanteenId删除卫生许可证 
		 * @param @param id  
		 * @return void    返回类型 
		 * @throws 
		 * @author LuGaogao
		 * @date 2017年8月3日 下午7:52:59
		 */
		void deleteByCrmCanteenId(Long id);
		/**
		 * 
		 * @Title: updateByparam 
		 * @Description: 更新卫生许可证(嵌入CanteenId,即qzs食堂id) 
		 * @param @param canteenHealthPicParam  
		 * @return void    返回类型 
		 * @throws 
		 * @author LuGaogao
		 * @date 2017年8月4日 上午11:59:19
		 */
		void updateByparam(CanteenHealthPicParam canteenHealthPicParam);
		/**
		 * 
		 * @Title: commonQuery 
		 * @Description: 通用查询 
		 * @param @param canteenHealthPicParam
		 * @param @return  
		 * @return List<CanteenHealthPicPO>    返回类型 
		 * @throws 
		 * @author LuGaogao
		 * @date 2017年8月7日 下午5:03:56
		 */
		List<CanteenHealthPicPO> commonQuery(
				CanteenHealthPicParam canteenHealthPicParam);
}
