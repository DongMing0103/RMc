package com.hd.kzscrm.service.serviceInter.agent;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.service.param.agent.CrmSplitAssignSetParam;
import com.hd.kzscrm.service.vo.agent.CrmSplitAssignSetVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmSplitAssignSetService extends BaseService {
    
        
    public Page<CrmSplitAssignSetVO> queryPage(CrmSplitAssignSetParam param); 
    
    public CrmSplitAssignSetVO getById(Long id);    
        
    
    
    public List<CrmSplitAssignSetPO> listByParam(CrmSplitAssignSetParam crmsplitassignsetParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmSplitAssignSetParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmSplitAssignSetPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmSplitAssignSetPO param);

    
    /**
     * 通过商家编号获取
     * @param canteenId
     * @return
     */
    public CrmSplitAssignSetPO queryCrmSplitAssignByCanteenId(Long canteenId);


    /**
     * 查询所有的方法
     * @param param
     * @return
     */
	public List<CrmSplitAssignSetPO> findAll(CrmSplitAssignSetParam param);
	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 查询抽成分配设置信息 
	 * @param @param crmSplitAssignSetParam
	 * @param @return  
	 * @return List<CrmSplitAssignSetPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月8日 下午4:29:58
	 */
	public List<CrmSplitAssignSetPO> commonQuery(
			CrmSplitAssignSetParam crmSplitAssignSetParam);

	/**
	 * 根据id查询详情
	 * @return
	 */
	public List<CrmSplitAssignSetPO> findById(Long id);
	
	/**
	 * 查询订单详情
	 * @param request
	 * @return
	 */
	public ModelAndView details(HttpServletRequest request);
	/**
	 * 根据主键查询对象
	* @Title: findByResourceId 
	* @author : lcl
	* @time : 2017年6月20日 下午2:14:52
	* @return CrmSplitAssignSetPO    返回类型 
	* @throws
	 */
	public CrmSplitAssignSetPO findByResourceId(Long clientResourceId);

	/**
	 * 
	 * @Title: getEffectiveSplitMsg 
	 * @Description: 查询最近生效时间的抽成信息 
	 * @param @param crmSplitAssignSetParam
	 * @param @return  
	 * @return CrmSplitAssignSetPO    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月21日 下午4:18:31
	 */
	public CrmSplitAssignSetPO getLastEffectiveSplitMsg(
			CrmSplitAssignSetParam crmSplitAssignSetParam);

	/**
	 * 
	 * @Title: saveAdjustSplitPercent 
	 * @Description: 保存调整食堂分账比 
	 * @param @param crmSplitAssignSetParam
	 * @param @param respModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月21日 下午5:45:03
	 */
	public Boolean saveAdjustSplitPercent(
			CrmSplitAssignSetParam crmSplitAssignSetParam, RespModel respModel)throws ParseException;
	/**
	 * 查询食堂分账列表
	* @Title: findPageByParam 
	* @author : lcl
	* @time : 2017年6月22日 下午7:36:13
	* @return Page<CrmSplitAssignSetPO>    返回类型 
	* @throws
	 */
	//public Page<CrmSplitAssignSetPO> findPageByParam(CrmSplitAssignSetParam crmSplitAssignSetParam);
	/**
	 * 根据 客户id查询对象 
	* @Title: findByCanteenId 
	* @author : lcl
	* @time : 2017年6月23日 上午9:44:40
	* @return CrmSplitAssignSetPO    返回类型 
	* @throws
	 */
	public CrmSplitAssignSetPO findByCanteenId(Long canteenId);

	/**
	 * 根据订单ID查询对象
	 * @param orderId
	 * @return
	 */
	public CrmSplitAssignSetPO findByOrderId(Long orderId);
	
	/**
	 * 根据业务员id获取业务员分账比例
	 * @param businessId
	 * @return
	 */
	public CrmSplitAssignSetPO findByBusinessId(Long businessId);

	/**
	 * @author 黄霄仪
	 * @date 2017年7月19日 下午2:03:24
	 */
	CrmSplitAssignSetPO findOneByCanteenId(Long canteenId);
	
}
