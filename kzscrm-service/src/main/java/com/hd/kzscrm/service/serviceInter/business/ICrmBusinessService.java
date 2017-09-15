package com.hd.kzscrm.service.serviceInter.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmBusinessService extends BaseService {
    
        
    public Page<CrmBusinessVO> queryPage(CrmBusinessParam param); 
    
        
        
    
    public List<CrmBusinessPO> listByParam(CrmBusinessParam crmbusinessParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmBusinessParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmBusinessPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmBusinessPO param);

	public CrmBusinessPO getById(Long id);
	/**
	 * 根据业务员id查询对象
	* @Title: findByBusinessId 
	* @author : lcl
	* @time : 2017年5月23日 上午10:46:23
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	public CrmBusinessPO findByBusinessId(Long businessId);
	
	public CrmBusinessPO findByUserId(Long userId);
	
	/**
	 * 根据负责人id查询对象（负责人也是业务员）
	* @Title: findByPrincipalBusinessId 
	* @author : lcl
	* @time : 2017年5月25日 下午2:18:40
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	public CrmBusinessPO findByPrincipalBusinessId(Long principalBusinessId);



	/**
	 * @Title: commonQuery 
	 * @Description: 查询业务员信息 
	 * @param @param crmBusinessParam
	 * @param @return  
	 * @return List<CrmBusinessPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年5月31日 上午11:04:25
	 */
	public List<CrmBusinessPO> commonQuery(CrmBusinessParam crmBusinessParam);




	Page<CrmBusinessVO> queryPageList(CrmBusinessParam param);



	public List<CrmBusinessPO> findAll(CrmBusinessParam param);
	

    public List<CrmBusinessPO> findByBusinessName(String businessName);


	/**
	 * @author 黄霄仪
	 * @date 2017年6月1日 下午2:05:20
	 */
	CrmBusinessPO getByUserId(Long userId);




	List<CrmBusinessPO> findByTeamId(Long id);




	List<CrmBusinessPO> findByTeamId(CrmBusinessParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月5日 上午9:51:38
	 */
	List<Long> getBusinessIdsByType(Integer type);
	
	public List<Long> getBusinessIdsByParam(CrmBusinessParam param);

	/**
	 * 获取团队人数
	 * @param crmBusinessParam
	 * @return
	 */
	public BigDecimal getTeamNumber (CrmBusinessParam crmBusinessParam);



	/**
	 * 
	 * @Title: queryBusinessIdByParam 
	 * @Description: 根据参数查询BusinessId 
	 * @param @param crmBusinessParam
	 * @param @return  
	 * @return List<Long>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 下午3:28:49
	 */
	public List<Long> queryBusinessIdByParam(CrmBusinessParam crmBusinessParam);



    /**
     * 根据业务员名称模糊查询方法
     * @param condition
     * @return
     */
	public List<CrmBusinessPO> findLikeByBusinessName(String condition);
	/**
	 * 根据主键查询对象 
	* @Title: findByCanteenId 
	* @author : lcl
	* @time : 2017年6月29日 上午11:05:52
	* @return CrmCanteenBaseInfoPO    返回类型 
	* @throws
	 */
	public CrmCanteenBaseInfoPO findByCanteenId(Long canteenId);
	/**
	 * 根据手机号查询业务员是否存在
	* @Title: findByMobilPhone 
	* @author : lcl
	* @time : 2017年7月24日 下午8:22:29
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	public CrmBusinessPO findByMobilPhone(String mobilephone);
	public List<CrmBusinessPO> findAllByPhone(String mobilephone);



	/**
	 * 
	 * @Title: salesmanListInit 
	 * @Description: 业务员列表初始化 
	 * @param @param accountPO
	 * @param @param modelAndView  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月31日 下午8:43:40
	 */
	public void salesmanListInit(CrmAccountPO accountPO,
			ModelAndView modelAndView);



	/**
	 * 
	 * @Title: countOccupationStatus 
	 * @Description: 统计职业状态 
	 * @param @param crmBusinessParam
	 * @param @return  
	 * @return Map<Integer,Integer>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年8月1日 上午9:53:18
	 */
	public Map<Integer, Integer> countOccupationStatus(
			CrmBusinessParam crmBusinessParam);



	/**
	 * 
	 * @Title: salesmanListDetail 
	 * @Description: 业务员列表详情  
	 * @param @param crmBusinessParam
	 * @param @param userPO
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年8月1日 上午11:34:49
	 */
	public Boolean salesmanListDetail(CrmBusinessParam crmBusinessParam,
			CrmAccountPO userPO, PageRespModel pageRespModel);

	/**
	 * 
	 * @Title: queryPageByParam 
	 * @Description: 分页查询 
	 * @param @param crmBusinessParam
	 * @param @return  
	 * @return Page<CrmBusinessPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年8月1日 下午1:58:31
	 */
	public Page<CrmBusinessPO> queryPageByParam(CrmBusinessParam crmBusinessParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年8月2日 下午2:35:44
	 */
	List<CrmBusinessPO> findByTeamIdAndUserType(Long id, Integer userType);




	/**
	 * @author 黄霄仪
	 * @date 2017年8月2日 下午3:02:12
	 */
	void updateTeamBusinessRelation(Long teamId);
	/**
	 * x修改代理商中的团队id
	* @Title: updateBusinessAgentRelationTeamId 
	* @author : lcl
	* @time : 2017年8月3日 上午9:57:57
	* @return void    返回类型 
	* @throws
	 */
	public void updateBusinessAgentRelationTeamId(CrmBusinessPO po, Long teamId);



	/**
	 * 根据业务员id和 userType查询
	* @Title: findByParamm 
	* @author : lcl
	* @time : 2017年8月3日 下午4:40:21
	* @return CrmBusinessPO    返回类型 
	* @throws
	 */
	public CrmBusinessPO findByParamm(CrmAgentParam paramm);




	/**
	 * @author 黄霄仪
	 * @date 2017年8月3日 下午3:50:07
	 */
	List<CrmBusinessPO> findByTeamIdsAndUserType(List<Long> teamIds,
			Integer userType);




	/**
	 * @author 黄霄仪
	 * @date 2017年8月3日 下午5:03:49
	 */
	List<CrmBusinessPO> findByParentId(Long parentId);
}
