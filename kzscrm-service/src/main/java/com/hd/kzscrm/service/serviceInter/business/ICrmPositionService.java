package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.CrmPositionPO;
import com.hd.kzscrm.service.param.business.CrmPositionParam;
import com.hd.kzscrm.service.vo.business.CrmPositionVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmPositionService extends BaseService {
    
        
    public Page<CrmPositionVO> queryPage(CrmPositionParam param); 
    
        
    public CrmPositionPO getById(Long id);    
    
    public List<CrmPositionPO> listByParam(CrmPositionParam crmpositionParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmPositionParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmPositionPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmPositionPO param);
    
    /**
     * 查询全部
    * @Title: findAll 
    * @author : lcl
    * @time : 2017年5月26日 上午11:53:16
    * @return List<CrmPositionPO>    返回类型 
    * @throws
     */
	public List<CrmPositionPO> findAll();


	/**
	 * @author 黄霄仪
	 * @date 2017年6月7日 下午3:30:28
	 */
	Integer addOrUpdatePosition(CrmPositionParam crmPositionParam);


	/**
	 * @author 黄霄仪
	 * @date 2017年6月7日 下午3:35:48
	 */
	void update(CrmPositionParam crmPositionParam);
	
	/**
	 * 查询岗位名称
	 * @return
	 */
	public List<CrmPositionPO> getPositionByIds();
	
	/**
	 * 根据agentID 获取岗位名称
	 * @param agentId
	 * @return
	 */
	public CrmPositionPO findByAgentId (Long agentId);


	/**
	 * @author 黄霄仪
	 * @date 2017年7月20日 下午2:56:33
	 */
	List<CrmPositionPO> getPositionByTeamId(Long teamId);

	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 通用查询 
	 * @param @param crmPositionParam
	 * @param @return  
	 * @return List<CrmPositionPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年8月1日 下午2:14:03
	 */
	public List<CrmPositionPO> commonQuery(CrmPositionParam crmPositionParam);


	/**
	 * @author 黄霄仪
	 * @date 2017年8月2日 下午4:39:04
	 */
	List<CrmPositionPO> findByTypeAndAgentId(Integer type, Long agentId);
}
