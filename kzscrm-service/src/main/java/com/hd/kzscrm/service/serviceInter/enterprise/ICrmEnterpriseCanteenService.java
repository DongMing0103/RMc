/**
 * 
 */
package com.hd.kzscrm.service.serviceInter.enterprise;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterpriseCanteenPO;
import com.hd.kzscrm.service.param.enterprise.CrmEnterpriseCanteenParam;
import com.hd.kzscrm.service.vo.enterprise.CrmEnterpriseCanteenVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;

/**
 * @author 黄霄仪
 * @date 2017年6月22日 下午4:35:43
 * 
 */
public interface ICrmEnterpriseCanteenService extends BaseService {

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:38:11
	 */
	Page<CrmEnterpriseCanteenVO> queryPage(CrmEnterpriseCanteenParam param);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:41:13
	 */
	List<CrmEnterpriseCanteenPO> listByParam(
			CrmEnterpriseCanteenParam crmEnterpriseCanteenParam);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:41:33
	 */
	void deleteById(Long id) throws BizException;

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:42:16
	 */
	void deleteByIds(String ids) throws BizException;

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:43:59
	 */
	void add(CrmEnterpriseCanteenParam param);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:44:33
	 */
	void saveEntity(CrmEnterpriseCanteenPO po);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:44:44
	 */
	void updateEntity(CrmEnterpriseCanteenPO po);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:44:47
	 */
	CrmEnterpriseCanteenPO getById(Long id);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午7:38:05
	 */
	CrmEnterpriseCanteenPO findByCanteenIdAndEnterpriseId(Long canteenId,
			Long enterpriseId);

	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 根据参数查询企业商家映射信息 
	 * @param @param crmEnterpriseCanteenParam
	 * @param @return  
	 * @return List<CrmEnterpriseCanteenPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月27日 下午4:01:53
	 */
	List<CrmEnterpriseCanteenPO> commonQuery(
			CrmEnterpriseCanteenParam crmEnterpriseCanteenParam);

	/**
	 * @author 黄霄仪
	 * @date 2017年7月24日 上午10:21:22
	 */
	List<CrmEnterpriseCanteenPO> findByCanteenId(Long canteenId);

}
