package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.business.CrmBusinessTailRecordPO;
import com.hd.kzscrm.service.param.business.CrmBusinessTailRecordParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessTailRecordVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmBusinessTailRecordService extends BaseService {
    
        
    public Page<CrmBusinessTailRecordVO> queryPage(CrmBusinessTailRecordParam param); 
    /**
     * 根据客户id查询对应的跟踪记录
    * @Title: queryList 
    * @author : lcl
    * @time : 2017年6月21日 下午4:52:29
    * @return Page<CrmBusinessTailRecordVO>    返回类型 
    * @throws
     */
    public Page<CrmBusinessTailRecordVO> queryList(CrmBusinessTailRecordParam param); 
    
     CrmBusinessTailRecordPO  getById(Long id);   
        
    
    public List<CrmBusinessTailRecordPO> listByParam(CrmBusinessTailRecordParam crmbusinesstailrecordParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmBusinessTailRecordParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmBusinessTailRecordPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmBusinessTailRecordPO param);
    /**
     * 
     * @Title: trackRegisterDetails 
     * @Description: 跟踪记录详情 
     * @param @param crmBusinessTailRecordParam
     * @param @param pageRespModel
     * @param @return  
     * @return Boolean    返回类型 
     * @throws 
     * @author LuGaogao
     * @date 2017年6月23日 下午5:08:18
     */
	public Boolean trackRegisterDetails(
			CrmBusinessTailRecordParam crmBusinessTailRecordParam,
			PageRespModel pageRespModel);
	
	/**
	 * 
	 * @Title: saveTrackRegisterMsg 
	 * @Description: 保存跟踪等级信息 
	 * @param @param crmBusinessTailRecordParam
	 * @param @param respModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月23日 下午5:09:46
	 */
	public Boolean saveTrackRegisterMsg(
			CrmBusinessTailRecordParam crmBusinessTailRecordParam,
			RespModel respModel);
	
	/**
	 * 
	 * @Title: commonQueryPage 
	 * @Description: 通用分页查询 
	 * @param @param businessTailRecordParam
	 * @param @return  
	 * @return Page<CrmBusinessTailRecordPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月27日 上午11:45:00
	 */
	public Page<CrmBusinessTailRecordPO> commonQueryPage(
			CrmBusinessTailRecordParam businessTailRecordParam);
}
