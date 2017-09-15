package com.hd.kzscrm.service.serviceInter.client;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.client.CrmClientResourcePO;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.param.client.CrmClientResourceParam;
import com.hd.kzscrm.service.vo.client.CrmClientResourceVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmClientResourceService extends BaseService {
    
        
    public Page<CrmClientResourceVO> queryPage(CrmClientResourceParam param); 
    
        
        
    
    public List<CrmClientResourcePO> listByParam(CrmClientResourceParam crmclientresourceParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmClientResourceParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmClientResourcePO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmClientResourcePO param);

	public CrmClientResourcePO getById(Long id);

	/**
	 *导出查询
	* @Title: findPageSelect 
	* @author : lcl
	* @time : 2017年6月1日 上午9:47:29
	* @return Page<CrmClientResourcePO>    返回类型 
	* @throws
	 */
	public Page<CrmClientResourceVO> findPageSelect(CrmClientResourceParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月1日 上午11:26:55
	 */
	Page<CrmClientResourceVO> queryPageBasic(CrmClientResourceParam param);



	/**
	 * 
	 * @Title: getProtectCustomerDetails 
	 * @Description: 获取保护客户信息 
	 * @param @param clientResourceParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午7:59:05
	 */
	public Boolean getProtectCustomerDetails(
			CrmClientResourceParam clientResourceParam,
			PageRespModel pageRespModel);
	/**
	 * 
	 * @Title: findPageByParam 
	 * @Description: 分页查询 
	 * @param @param clientResourceParam
	 * @param @return  
	 * @return Page<CrmClientResourceVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月2日 上午11:37:54
	 */
	public Page<CrmClientResourceVO> findPageByParam(CrmClientResourceParam clientResourceParam);



	/**
	 * 
	 * @Title: getFormalCustomerDetails 
	 * @Description: 获取正式客户信息 
	 * @param @param clientResourceParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月2日 下午2:32:55
	 */
	public Boolean getFormalCustomerDetails(
			CrmClientResourceParam clientResourceParam,
			PageRespModel pageRespModel);



	/**
	 * 
	 * @Title: clientListInit 
	 * @Description: 客户列表初始化 
	 * @param @param crmWorkTargetParam
	 * @param @param modelAndView  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月7日 下午7:21:56
	 */
	public void clientListInit(CrmClientResourceParam crmClientResourceParam,
			ModelAndView modelAndView)throws ParseException;
	public void businessClientListInit(CrmClientResourceParam crmClientResourceParam,
			ModelAndView modelAndView)throws ParseException;



	/**
	 * 
	 * @Title: getClientList 
	 * @Description: 获取客户列表信息 
	 * @param @param crmClientResourceParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月8日 上午10:48:50
	 */
	public Boolean getClientList(CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel);
	public Boolean byBusinessGetClientList(CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel);



	/**
	 * 
	 * @Title: agentCustomerDetails 
	 * @Description: 代理商客户列表详情 
	 * @param @param crmClientResourceParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月9日 下午4:24:57
	 */
	public Boolean agentCustomerDetails(
			CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel,CrmAgentPO crmAgentPO);



	/***
	 * 
	 * @Title: salesmanCustomerDetails 
	 * @Description: 业务员客户详情 
	 * @param @param crmClientResourceParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月9日 下午4:25:50
	 */
	public Boolean salesmanCustomerDetails(
			CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel,CrmAgentPO crmAgentPO);

	/**
	 * 
	* @Title: findPageList 
	* @author : lcl
	* @time : 2017年6月16日 下午4:43:24
	* @return Page<CrmClientResourcePO>    返回类型 
	* @throws
	 */
	public Page<CrmClientResourcePO> findPageList(CrmClientResourceParam crmClientResourceParam);



	/**
	 * 
	 * @Title: clientListExcelOut 
	 * @Description: 客户列表详情导出  
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return List<CrmClientResourceVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月19日 上午10:27:58
	 */
	public List<CrmClientResourceVO> clientListExcelOut(
			CrmClientResourceParam crmClientResourceParam);
	
	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 根据参数查询客户资源信息 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return List<CrmClientResourcePO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月19日 上午10:58:18
	 */
	public List<CrmClientResourcePO> commonQuery(
			CrmClientResourceParam crmClientResourceParam);



	/**
	 * 
	 * @Title: protectCustomerDetailsExcelOut 
	 * @Description: 保护客户导出 
	 * @param @param crmClientResourceParam
	 * @param @param userType
	 * @param @return  
	 * @return List<CrmClientResourceVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月19日 下午5:10:57
	 */
	public List<CrmClientResourceVO> protectCustomerDetailsExcelOut(
			CrmClientResourceParam crmClientResourceParam, Integer userType);




	/**
	 * 
	 * @Title: formalCustomerDetailsExcelOut 
	 * @Description: 正式客户导出 
	 * @param @param crmClientResourceParam
	 * @param @param userType
	 * @param @return  
	 * @return List<CrmClientResourceVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月19日 下午5:11:54
	 */
	public List<CrmClientResourceVO> formalCustomerDetailsExcelOut(
			CrmClientResourceParam crmClientResourceParam, Integer userType);



	/**
	 * 
	 * @Title: agentCustomerDetailsExcleOut 
	 * @Description: 代理商客户导出 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return List<CrmClientResourceVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月20日 上午10:56:35
	 */
	public List<CrmClientResourceVO> agentCustomerDetailsExcleOut(
			CrmClientResourceParam crmClientResourceParam);




	/**
	 * 
	 * @Title: salesmanCustomerDetailsExcleOut 
	 * @Description: 业务员客户导出 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return List<CrmClientResourceVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月20日 上午10:57:50
	 */
	public List<CrmClientResourceVO> salesmanCustomerDetailsExcleOut(
			CrmClientResourceParam crmClientResourceParam);
	
	/**
	 * 统计代理商下客户数量
	 * @param crmClientResourceParam
	 * @return
	 */
	public BigDecimal getAgentNumber(CrmClientResourceParam crmClientResourceParam);
	/**
	 * 食堂分账 
	* @Title: findPageBycanteenClien 
	* @author : lcl
	* @time : 2017年6月23日 上午9:23:12
	* @return Page<CrmClientResourceVO>    返回类型 
	* @throws
	 */
	public Page<CrmClientResourceVO> findPageBycanteenClien(CrmClientResourceParam crmClientResourceParam);
	
	/**
	 * 根据businessId 统计正式客户数量
	 * @param crmClientResourceParam
	 * @return
	 */
	public BigDecimal getBusinessNumber(CrmClientResourceParam crmClientResourceParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月26日 下午8:49:53
	 */
	RespModel addCrmClientResourceByCanteen(CrmCanteenBaseInfoParam param);



	/**
	 * 
	 * @Title: customerResourceLook 
	 * @Description: 客户资源查看  
	 * @param @param clientId
	 * @param @param modelAndView
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月22日 下午8:23:25
	 */
	public Boolean customerResourceLook(Long clientId,ModelAndView modelAndView);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月27日 上午9:43:43
	 */
	Map<String, Object> getCrmCanteenBaseInfo(CrmCanteenBaseInfoParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月27日 上午11:06:42
	 */
	RespModel editCrmClientResourceByCanteen(
			CrmCanteenBaseInfoParam crmCanteenBaseInfoparam);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月27日 下午2:11:31
	 */
	Integer commonUpdate(CrmClientResourceParam crmClientResourceParam);



	/**
	 * 
	 * @Title: kickOutClient 
	 * @Description: 踢出(保护客户) 
	 * @param @param clientId
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月27日 下午5:44:53
	 */
	public Boolean kickOutClient(Long clientId,Long userId,RespModel respModel);
	
	/**
	 * 
	 * @Title: changeProtectedClientToIndividualTraveler 
	 * @Description: 保护客户更新为散客 
	 * @param @param clientResourcePO  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月25日 上午9:27:08
	 */
	public int changeProtectedClientToIndividualTraveler(
			CrmClientResourceParam clientResourceParam);
	/**
	 * 根据主键查询
	* @Title: findById 
	* @author : lcl
	* @time : 2017年6月29日 上午10:41:04
	* @return CrmClientResourcePO    返回类型 
	* @throws
	 */
	public CrmClientResourcePO findById(Long id);
	/**
	 * 根据businessId查询
	* @Title: findByBusinessId 
	* @author : lcl
	* @time : 2017年7月3日 上午9:21:12
	* @return List<CrmClientResourcePO>    返回类型 
	* @throws
	 */
	public List<CrmClientResourcePO> findByBusinessId(Long id);
	public List<CrmClientResourcePO> getByBusinessId(CrmClientResourceParam clientResourceParam);



	/**
	 * 
	 * @Title: getBusinessClientList 
	 * @Description: 业务员目标(业务员考核)查看客户列表详情 
	 * @param @param crmClientResourceParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月14日 下午4:56:55
	 */
	public Boolean getBusinessClientList(
			CrmClientResourceParam crmClientResourceParam,
			PageRespModel pageRespModel);



	/**
	 * 
	 * @Title: businessClientListExcleOut 
	 * @Description: 业务员客户列表(导出) 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return List<CrmClientResourceVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月17日 上午10:57:23
	 */
	public List<CrmClientResourceVO> businessClientListExcleOut(
			CrmClientResourceParam crmClientResourceParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月19日 上午10:57:44
	 */
	CrmClientResourcePO findByAgentCanteenIdAndClientTypes(Long agentCanteenId,
			Integer ... clientTypes);



	/**
	 * 
	 * @Title: countProtectedClientNum 
	 * @Description: 统计业务员的保护客户数量 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return Map<Long,Integer>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月24日 上午9:16:01
	 */
	public Map<Long, Integer> countProtectedClientNum(
			CrmClientResourceParam crmClientResourceParam);




	public CrmClientResourcePO findByAgentCanteenId(Long id);



	/**
	 * 
	 * @Title: clientProtect 
	 * @Description: (散客)客户保护  
	 * @param @param clientId
	 * @param @param userId
	 * @param @param respModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月24日 下午2:55:19
	 */
	public Boolean clientProtect(Long clientId, Long userId, RespModel respModel)  throws ParseException ;



	/**
	 * 
	 * @Title: countClientNum 
	 * @Description: 根据参数统计客户数量 
	 * @param @param crmClientResourceParam
	 * @param @return  
	 * @return Integer    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月26日 上午11:43:40
	 */
	public Integer countClientNum(CrmClientResourceParam crmClientResourceParam);
	/**
	 * 业务员解约后 业务员下的客户变为散客
	* @Title: updateClientIds 
	* @author : lcl
	* @time : 2017年7月27日 下午8:31:42
	* @return void    返回类型 
	* @throws
	 */
	public void updateClientIds(List<Long> clientIds);



	/**
	 * 
	 * @Title: queryCustomsResource 
	 * @Description: 客户资源库详情 
	 * @param @param param
	 * @param @param userPO
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月27日 上午10:52:37
	 */
	public Boolean queryCustomsResource(CrmClientResourceParam param,
			CrmAccountPO userPO, PageRespModel pageRespModel);
	
	/**
	 * 
	 * @Title: findCustomsResource 
	 * @Description: 查询客户资源信息 
	 * @param @param param
	 * @param @return  
	 * @return Page<CrmClientResourcePO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月27日 下午2:33:55
	 */
	public Page<CrmClientResourcePO> findCustomsResource(
			CrmClientResourceParam param);
	
	/**
	 * 根据businessID统计食堂数量
	 * @author dm
	 */
	public BigDecimal countCanteen(CrmClientResourceParam clientResourceParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年7月31日 下午4:47:12
	 */
	CrmClientResourcePO findByAgentCanteenIdAndClientType(Long id, Integer clientType);
}
