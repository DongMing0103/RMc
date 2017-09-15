package com.hd.kzscrm.service.serviceInter.agent;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.agent.CrmAgentApplyPO;
import com.hd.kzscrm.service.param.agent.CrmAgentApplyParam;
import com.hd.kzscrm.service.vo.agent.CrmAgentApplyVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmAgentApplyService extends BaseService {
    
        
    public Page<CrmAgentApplyVO> queryPage(CrmAgentApplyParam param); 
    
        
    public CrmAgentApplyPO getById(Long id);    
    
    public List<CrmAgentApplyPO> listByParam(CrmAgentApplyParam crmagentapplyParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmAgentApplyParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmAgentApplyPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmAgentApplyPO param);

    /**
     * 
     * @Title: saveAgentJoinApply 
     * @Description: 代理商加盟申请信息保存 
     * @param @param crmAgentApplyParam
     * @param @param respModel
     * @param @return  
     * @return Boolean    返回类型 
     * @throws ParseException
     * @author LuGaogao
     * @date 2017年6月15日 下午4:08:24
     */
	public Boolean saveAgentJoinApply(CrmAgentApplyParam crmAgentApplyParam,
			RespModel respModel)throws ParseException;

	/**
	 * 
	 * @Title: agentContractExtension 
	 * @Description: 代理商续约 
	 * @param @param clientId
	 * @param @param modelAndView
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月30日 上午11:40:55
	 */
	public Boolean agentContractExtension(Long clientId,
			ModelAndView modelAndView);
	
	public Boolean agentExtension(Long agentId,
			ModelAndView modelAndView);
	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 通用查询 
	 * @param @param crmAgentApplyParam
	 * @param @return  
	 * @return List<CrmAgentApplyPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月30日 下午4:31:21
	 */
	public List<CrmAgentApplyPO> commonQuery(
			CrmAgentApplyParam crmAgentApplyParam);
	
	/**
	 * 代理商审核查看
	 * @param param
	 * @return
	 */
	Page<CrmAgentApplyVO> queryPageBasic(CrmAgentApplyParam param);

	/**
	 * 根据agentId查询对象
	* @Title: findByAgentId 
	* @author : lcl
	* @time : 2017年7月24日 下午4:28:31
	* @return CrmAgentApplyPO    返回类型 
	* @throws
	 */
	public CrmAgentApplyPO findByAgentId(Long agentId,Long userId);

	/**
	 * 
	 * @Title: deleteAgentApplyEntity 
	 * @Description: 根据参数删除申请信息(delFlag置0) 
	 * @param @param crmAgentApplyParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月28日 上午9:32:26
	 */
	public void deleteAgentApplyEntity(CrmAgentApplyParam crmAgentApplyParam);

	/**
	 * 根据代理商id查询对象
	* @Title: findByAgentId 
	* @author : lcl
	* @time : 2017年7月28日 下午4:37:02
	* @return CrmAgentApplyPO    返回类型 
	* @throws
	 */
	public CrmAgentApplyPO findByAgentId(Long agentId);


	/**
	 * @author 黄霄仪
	 * @date 2017年7月29日 下午4:30:41
	 */
	boolean whetherExpire(Long agentId);

}
