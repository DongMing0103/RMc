package com.hd.kzscrm.service.serviceInter.canteen;

import java.text.ParseException;
import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenApplyPO;
import com.hd.kzscrm.service.param.canteen.CrmCanteenApplyParam;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenApplyVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmCanteenApplyService extends BaseService {
    
        
    public Page<CrmCanteenApplyVO> queryPage(CrmCanteenApplyParam param); 
    
        
        
    
    public List<CrmCanteenApplyPO> listByParam(CrmCanteenApplyParam crmcanteenapplyParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmCanteenApplyParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmCanteenApplyPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmCanteenApplyPO param);

	public CrmCanteenApplyPO getById(Long id);



	/**
	 * 
	 * @Title: saveCanteenEnterApply 
	 * @Description: 食堂入驻申请信息保存 
	 * @param @param crmCanteenApplyParam
	 * @param @param respModel
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws ParseException
	 * @author LuGaogao
	 * @date 2017年6月15日 下午4:04:22
	 */
	public Boolean saveCanteenEnterApply(
			CrmCanteenApplyParam crmCanteenApplyParam, RespModel respModel)throws ParseException;



	/**
	 * 
	 * @Title: commonQuery 
	 * @Description: 根据参数查询食堂入驻申请信息 
	 * @param @param crmCanteenApplyParam
	 * @param @return  
	 * @return List<CrmCanteenApplyPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月21日 上午11:18:06
	 */
	public List<CrmCanteenApplyPO> commonQuery(
			CrmCanteenApplyParam crmCanteenApplyParam);



	/**
	 * 
	 * @Title: deleteCanteenApplyEntity 
	 * @Description: 根据参数删除申请信息(delFlag置0) 
	 * @param @param crmCanteenApplyParam  
	 * @return void    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月28日 上午9:36:47
	 */
	public void deleteCanteenApplyEntity(
			CrmCanteenApplyParam crmCanteenApplyParam);
}
