
package com.hd.kzscrm.service.serviceInter.business;
import java.util.List;

import com.hd.kzscrm.dao.entity.business.CanteenExtInfoPO;
import com.hd.kzscrm.service.param.business.CanteenExtInfoParam;
import com.hd.wolverine.service.BaseService;

/**
 * 商家扩展表
 * @author Administrator
 *
 */
public interface CanteenExtInfoService extends BaseService {
	/**
	 * 按条件查询商家扩展表 
	 * @param param 
	 * @return
	 * @author jyt 2017年3月7日 上午11:37:21
	 */
	List<CanteenExtInfoPO> findAllByParam(CanteenExtInfoParam param);

}
