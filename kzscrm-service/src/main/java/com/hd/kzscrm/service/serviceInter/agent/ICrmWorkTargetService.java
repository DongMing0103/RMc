package com.hd.kzscrm.service.serviceInter.agent;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.dao.entity.agent.CrmWorkTargetPO;
import com.hd.kzscrm.service.param.agent.CrmWorkTargetParam;
import com.hd.kzscrm.service.vo.agent.CrmWorkTargetVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmWorkTargetService extends BaseService {
    
        
    public Page<CrmWorkTargetVO> queryPage(CrmWorkTargetParam param); 
    
        
    public CrmWorkTargetVO getById(Long id);    
    
    public List<CrmWorkTargetPO> listByParam(CrmWorkTargetParam crmworktargetParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmWorkTargetParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmWorkTargetPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmWorkTargetPO param);
    
    /**
     * 
     * @Title: queryPageByParam 
     * @Description: 分页查询 
     * @param @param crmworktargetParam
     * @param @return  
     * @return Page<CrmWorkTargetPO>    返回类型 
     * @throws 
     * @author LuGaogao
     * @date 2017年5月28日 下午3:42:42
     */
    public Page<CrmWorkTargetPO> queryPageByParam(CrmWorkTargetParam crmworktargetParam);
    /**
     * 
     * @Title: getTeamTargetDetails 
     * @Description: 获取团队目标信息 
     * @param  crmWorkTargetParam
     * @param  respModel  
     * @return void    返回类型 
     * @author LuGaogao
     * @date 2017年5月28日 下午2:45:42
     */
	public Boolean getTeamTargetDetails(CrmWorkTargetParam crmWorkTargetParam,
			PageRespModel pageRespModel);

	/**
	 * 
	 * @Title: getAgentTargetDetails 
	 * @Description: 获取代理商目标信息 
	 * @param @param crmWorkTargetParam
	 * @param @param pageRespModel  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 上午9:41:19
	 */
	public Boolean getAgentTargetDetails(CrmWorkTargetParam crmWorkTargetParam,
			PageRespModel pageRespModel);


	/**
	 * @author 黄霄仪
	 * @date 2017年5月31日 下午3:09:32
	 */
	Page<CrmWorkTargetVO> queryPageBasic(CrmWorkTargetParam param);

	/**
	 * 
	 * @Title: getBusinessTargetDetails 
	 * @Description: 获取业务员(业务员个人,不含下属业务员业绩)目标信息  
	 * @param @param crmWorkTargetParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月1日 下午5:13:54
	 */
	public Boolean getBusinessTargetDetails(
			CrmWorkTargetParam crmWorkTargetParam, PageRespModel pageRespModel);

	/**
	 * 
	 * @Title: getPersonalTargetDetails 
	 * @Description: 获取个人(业务员个人,不含下属业务员业绩)目标信息 
	 * @param @param crmWorkTargetParam
	 * @param @param pageRespModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月3日 下午5:41:59
	 */
	public Boolean getPersonalTargetDetails(
			CrmWorkTargetParam crmWorkTargetParam, PageRespModel pageRespModel);
	
	
	/**
	 * 根据团队id查询团队工作目标信息
	 * @author dongming
	 * @date 2017年6月3日12:14:49
	 */
	public CrmWorkTargetPO getByTeamId (Long teamId);


	/**
	 * @author 黄霄仪
	 * @date 2017年6月5日 上午11:31:59
	 */
	Integer update(CrmWorkTargetParam crmWorktargetParam);

	/**
	 * 
	 * @Title: teamTargetInit 
	 * @Description: 团队目标记录初始化(包含团队考核) 
	 * @param @param sessionUserId
	 * @param @param modelAndView  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月5日 下午2:06:33
	 */
	public void teamTargetInit(Long sessionUserId, ModelAndView modelAndView);

	/**
	 * 
	 * @Title: businessTargetInit 
	 * @Description: 业务员目标记录初始化(包含业务员考核) 
	 * @param @param sessionUserId
	 * @param @param modelAndView  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月6日 下午3:16:41
	 */
	public void businessTargetInit(Long sessionUserId, ModelAndView modelAndView);

	/**
	 * 
	 * @Title: teamTargetDetailsExcelOut 
	 * @Description: 团队目标(包括团队考核)详情导出 
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return List<CrmWorkTargetVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午11:45:15
	 */
	public List<CrmWorkTargetVO> teamTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam);

	/**
	 * 
	 * @Title: agentTargetDetailsExcelOut 
	 * @Description: 代理商目标(包括代理商考核)详情导出 
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return List<CrmWorkTargetVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午11:46:06
	 */
	public List<CrmWorkTargetVO> agentTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam);

	
	/**
	 * 
	 * @Title: businessTargetDetailsExcelOut 
	 * @Description: 业务员目标(包括业务员考核)详情导出
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return List<CrmWorkTargetVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午11:47:02
	 */
	public List<CrmWorkTargetVO> businessTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam);


	/**
	 * 
	 * @Title: personalTargetDetailsExcelOut 
	 * @Description: 个人目标详情导出
	 * @param @param crmWorkTargetParam
	 * @param @return  
	 * @return List<CrmWorkTargetVO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 上午11:47:55
	 */
	public List<CrmWorkTargetVO> personalTargetDetailsExcelOut(
			CrmWorkTargetParam crmWorkTargetParam);
	
	
	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 查询工作目标信息 
	 * @param @param crmworktargetParam
	 * @param @return  
	 * @return List<CrmWorkTargetPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 下午2:12:34
	 */
	public List<CrmWorkTargetPO> commonQuery(CrmWorkTargetParam crmworktargetParam);


	public CrmWorkTargetPO findById(Long id);


	/**
	 * @author 黄霄仪
	 * @date 2017年8月3日 下午2:13:36
	 */
	Page<CrmWorkTargetVO> workTargetCheckQuery(CrmWorkTargetParam crmWorkTargetParam);


	/**
	 * @author 黄霄仪
	 * @date 2017年8月3日 下午2:34:08
	 */
	Page<CrmWorkTargetVO> findByBusinessIdsAndApplyStatus(List<Long> businessIds,Integer applyStatus);

}
