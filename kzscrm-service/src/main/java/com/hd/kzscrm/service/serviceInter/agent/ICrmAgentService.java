package com.hd.kzscrm.service.serviceInter.agent;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.service.param.agent.CrmAgentParam;
import com.hd.kzscrm.service.vo.agent.CrmAgentVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmAgentService extends BaseService {
    
        
    public Page<CrmAgentVO> queryPage(CrmAgentParam param); 
    
    public CrmAgentPO getById(Long id);    
        
    
    public List<CrmAgentPO> listByParam(CrmAgentParam crmagentParam);
    
    public CrmAgentPO findByAgentId(Long agentId);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmAgentParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmAgentPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmAgentPO param);
    /**
     * 
     * @Title: commonCount 
     * @Description: 根据条件count(0) 
     * @param @param crmAgentParam
     * @param @return  
     * @return Integer    返回类型 
     * @throws 
     * @author LuGaogao
     * @date 2017年5月31日 下午4:51:08
     */
	public Integer commonCount(CrmAgentParam crmAgentParam);


    /**
     * 创建查询所有方法
     * @param param2
     * @return
     */
	public List<CrmAgentPO> findAll(CrmAgentParam param);
	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 根据条件查询代理商信息 
	 * @param @param crmAgentParam
	 * @param @return  
	 * @return List<CrmAgentPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午1:43:16
	 */
	public List<CrmAgentPO> commonQuery(CrmAgentParam crmAgentParam);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月1日 下午1:56:43
	 */
	CrmAgentPO getByUserId(Long userId);
	/**
	 * 根据 代理商名称查询 对象集合
	 * @param agentName
	 * @return
	 */
	public List<CrmAgentPO> findByAgentName(String agentName);
	
	/**
	 * 导出excel方法
	 */
	public Page<CrmAgentVO> findPageSelect(CrmAgentParam param);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月6日 下午2:26:50
	 */
	Page<CrmAgentVO> queryPageBasic(CrmAgentParam param);
	/**
	 * 
	 * @Title: getChildAgent 
	 * @Description: 查询该代理商的子孙代理商 
	 * @param @param agentParam
	 * @param @return  
	 * @return List<CrmAgentPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月6日 上午11:34:54
	 */
	public List<CrmAgentPO> getChildAgent(CrmAgentParam agentParam);
	
	/**
	 * 根据businessID统计代理商数量
	 * @param businessId
	 * @return
	 */
	public BigDecimal countAgentNum (CrmAgentParam crmAgentParam);
	/**
	 * 解约和续约 状态的修改
	* @Title: updateStatus 
	* @author : lcl
	* @time : 2017年6月9日 下午4:20:18
	* @return CommResponse    返回类型 
	* @throws
	 */
	public RespModel updateStatus(List<Long> agentIds);
	/**
	 * 编辑 新增的渲染 
	* @Title: addAgent 
	* @author : lcl
	* @time : 2017年6月13日 上午9:27:43
	* @return ModelAndView    返回类型 
	* @throws
	 */
	public Boolean addAgent(CrmAgentParam param,ModelAndView model);
	/**
	 * 本月工作目标
	* @Title: monthGoals 
	* @author : lcl
	* @time : 2017年6月13日 下午4:55:57
	* @return ModelAndView    返回类型 
	* @throws
	 */
	public ModelAndView monthGoals(CrmAgentParam param);
	/**
	 * 分页查询工作目标
	* @Title: findMonthGoalsList 
	* @author : lcl
	* @time : 2017年6月13日 下午7:25:33
	* @return PageRespModel    返回类型 
	* @throws
	 */
	public PageRespModel findMonthGoalsList(CrmAgentParam param);
	/**
	 * 查询下级代理集合
	* @Title: getLowerAgentList 
	* @author : lcl
	* @time : 2017年6月15日 上午10:08:12
	* @return PageRespModel    返回类型 
	* @throws
	 */
	public PageRespModel getLowerAgentList(CrmAgentParam param);
	/**
	 * 查询下级代理
	* @Title: findLowerAgentList 
	* @author : lcl
	* @time : 2017年6月15日 上午11:40:09
	* @return List<CrmAgentPO>    返回类型 
	* @throws
	 */
	public List<CrmAgentPO> findLowerAgentList(CrmAgentParam param);
	
	/**
	 * 
	 * @Title: getChildAgentIds 
	 * @Description: 根据parentId参数查询子孙agentId 
	 * @param @param crmAgentParam
	 * @param @return  
	 * @return List<Long>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 下午4:43:11
	 */
	public List<Long> getChildAgentIds(CrmAgentParam crmAgentParam);
	
	/**
	 * 根据代理商编号查询代理商信息
	 * @param agentUserId
	 * @return
	 */
	public  CrmAgentPO findByAgentNo(Long agentUserId);

	/**
	 * 根据userId查询对象
	 * @param userId
	 * @return
	 */
	public CrmAgentPO findByUserId(Long userId);

	/**
	 * 根据代理商名称模糊查询方法
	 * @param condition
	 * @return
	 */
	public List<CrmAgentPO> findLikeByAgentName(String condition);
	
	/**
	 * 
	 * @Title: editAgent 
	 * @Description: 编辑代理商 
	 * @param @param clientId
	 * @param @param userType
	 * @param @param modelAndView
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月3日 上午11:12:36
	 */
	public Boolean editAgent(Long clientId,
			ModelAndView modelAndView);

	/**
	 * 
	 * @Title: updateAgentEditInfo 
	 * @Description: 更新代理商编辑信息 
	 * @param @param crmAgentParam
	 * @param @param respModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月3日 上午11:23:53
	 */
	public Boolean updateAgentEditInfo(CrmAgentParam crmAgentParam,Long userId,
			RespModel respModel);

	/**
	 * @author 黄霄仪
	 * @date 2017年7月5日 上午10:28:34
	 */
	CrmAgentVO findById(Long id);

	/**
	 * 
	 * @Title: changeProtectedClientToIndividualTraveler 
	 * @Description: 保护客户更新为散客 
	 * @param @param crmAgentParam
	 * @param @return  
	 * @return int    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月25日 上午10:07:54
	 */
	public int changeProtectedClientToIndividualTraveler(
			CrmAgentParam crmAgentParam);
	/**
	 * 根据登录账号和
	* @Title: findByBusienssAndUserId 
	* @author : lcl
	* @time : 2017年8月3日 上午10:18:12
	* @return List<CrmAgentPO>    返回类型 
	* @throws
	 */
	public List<CrmAgentPO> findByBusienssAndUserId(CrmAgentParam cAgentParam);
}
