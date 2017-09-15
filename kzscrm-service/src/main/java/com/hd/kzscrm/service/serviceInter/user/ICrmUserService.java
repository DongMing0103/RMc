package com.hd.kzscrm.service.serviceInter.user;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.service.param.user.CrmUserParam;
import com.hd.kzscrm.service.vo.user.CrmUserVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmUserService extends BaseService {
    
        
    public Page<CrmUserVO> queryPage(CrmUserParam param); 
    
        
        
    
    public List<CrmUserPO> listByParam(CrmUserParam crmuserParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmUserParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年5月31日 下午7:05:37
	 */
    CrmUserPO getById(Long id);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月8日 下午2:03:38
	 */
	void sendVerificationCode(String mobilephone, Integer smsType);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月9日 下午2:22:31
	 */
	int modifyPassword(CrmUserParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月3日 下午8:00:20
	 */
	void saveEntity(CrmUserParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月3日 下午8:27:16
	 */
	CrmUserPO findByAccount(String account);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月4日 上午9:28:39
	 */
	void updateEntity(CrmUserParam crmUserParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月20日 下午7:50:05
	 */
	RespModel saveClient(CrmUserParam crmUserParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月21日 上午11:22:53
	 */
	CrmUserPO findByAccountAndUserType(String account, Integer userType);
    

}
