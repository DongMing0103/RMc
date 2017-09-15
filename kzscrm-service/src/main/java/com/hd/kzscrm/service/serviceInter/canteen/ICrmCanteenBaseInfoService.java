package com.hd.kzscrm.service.serviceInter.canteen;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.service.param.canteen.CrmCanteenBaseInfoParam;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenBaseInfoVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmCanteenBaseInfoService extends BaseService {
    
        
    public Page<CrmCanteenBaseInfoVO> queryPage(CrmCanteenBaseInfoParam param); 
    
        
        
    
    public List<CrmCanteenBaseInfoPO> listByParam(CrmCanteenBaseInfoParam crmcanteenbaseinfoParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmCanteenBaseInfoParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmCanteenBaseInfoPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmCanteenBaseInfoPO param);

	public CrmCanteenBaseInfoPO getById(Long id);
	
	/**
	 * 根据业务员id查询食堂信息
	 */
	public CrmCanteenBaseInfoPO findByBusinessId (Long businessId);

	/**
	 * 根据businessId查询canteenIds，返回List
	 */
	public List<CrmCanteenBaseInfoPO> findByBusinessIds(Long businessId);

	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 查询食堂基本信息
	 * @param @param crmCanteenBaseInfoParam
	 * @param @return  
	 * @return List<CrmCanteenBaseInfoPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月2日 下午3:33:55
	 */
	public List<CrmCanteenBaseInfoPO> commonQuery(
			CrmCanteenBaseInfoParam crmCanteenBaseInfoParam);

	/**
	 * 根据canteenId查询
	 * @param canteenId
	 * @return
	 */
	public CrmCanteenBaseInfoPO findByCanteenId(Long canteenId);

	/**
	 * 根据商家名称 模糊查询集合
	 * @param condition
	 * @return
	 */
	public List<CrmCanteenBaseInfoPO> findLikeByName(String condition,Long businessId);
	
	/**
	 * 根据businessID 统计食堂数量
	 * @param businessId
	 * @return
	 */
	public BigDecimal countCanteen (Long businessId);
	
	/**
	 * 查询食堂名称
	 * @return
	 */
	public List<CrmCanteenBaseInfoPO> getCanteenByIds();




	/**
	 * @author 黄霄仪
	 * @date 2017年6月21日 下午2:31:22
	 */
	Map<String, Object> getAgentAndTeam(CrmCanteenBaseInfoParam param);


    /**
     * 根据userId查询食堂名称
     * @param userId
     * @return
     * @author xu
     */

	public CrmCanteenBaseInfoPO findByUserId(Long userId);



	/**
	 * 
	 * @Title: changeProtectedClientToIndividualTraveler 
	 * @Description: 保护客户更新为散客 
	 * @param @param crmCanteenBaseInfoParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月25日 上午9:59:12
	 */
	public int changeProtectedClientToIndividualTraveler(
			CrmCanteenBaseInfoParam crmCanteenBaseInfoParam);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月21日 下午4:19:55
	 */
//	RespModel addOrUpdateCrmCanteenBaseInfo(CrmCanteenBaseInfoParam param);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 上午10:12:00
	 */
	/*Map<String, Object> getCrmCanteenBaseInfo(CrmCanteenBaseInfoParam param);*/
}
