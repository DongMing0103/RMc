package com.hd.kzscrm.service.serviceInter.enterprise;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.enterprise.CrmEnterprisePO;
import com.hd.kzscrm.service.param.enterprise.CrmEnterpriseParam;
import com.hd.kzscrm.service.vo.enterprise.CrmEnterpriseVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmEnterpriseService extends BaseService {

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:00:56
	 */
	Page<CrmEnterpriseVO> queryPage(CrmEnterpriseParam param);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:02:59
	 */
	List<CrmEnterprisePO> listByParam(CrmEnterpriseParam crmEnterpriseParam);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:03:03
	 */
	void deleteById(Long id) throws BizException;

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:03:15
	 */
	void deleteByIds(String ids) throws BizException;

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:04:22
	 */
	void add(CrmEnterpriseParam param);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:04:39
	 */
	void saveEntity(CrmEnterprisePO po);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:05:24
	 */
	void updateEntity(CrmEnterprisePO po);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月14日 下午8:05:30
	 */
	CrmEnterprisePO getById(Long id);
    
	/**
	 * 根据userId 获取企业信息
	 * @param userId
	 * @return
	 */
    CrmEnterprisePO getByUserId(Long userId);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月27日 上午11:57:37
	 */
	CrmEnterprisePO findById(Long id);

	/**
	 * 
	 * @Title: viewEnterpriseInformation 
	 * @Description: 获取企业信息 
	 * @param @param clientId
	 * @param @param modelAndView
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月27日 下午3:43:28
	 */
	Boolean viewEnterpriseInformation(Long clientId, ModelAndView modelAndView);
}
