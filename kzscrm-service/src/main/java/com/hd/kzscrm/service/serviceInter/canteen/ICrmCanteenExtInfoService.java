package com.hd.kzscrm.service.serviceInter.canteen;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenExtInfoPO;
import com.hd.kzscrm.service.param.canteen.CrmCanteenExtInfoParam;
import com.hd.kzscrm.service.vo.canteen.CrmCanteenExtInfoVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;


public interface ICrmCanteenExtInfoService extends BaseService {
    
        
    public Page<CrmCanteenExtInfoVO> queryPage(CrmCanteenExtInfoParam param); 
    
        
        
    
    public List<CrmCanteenExtInfoPO> listByParam(CrmCanteenExtInfoParam CrmCanteenExtInfoParam);
    
    
    public void deleteById(Long id)throws BizException;
    
    
    public void deleteByIds(String id)throws BizException;
    
    /**add*/
    public void add(CrmCanteenExtInfoParam param);
    
    /**
    add save
    */
    public void saveEntity(CrmCanteenExtInfoPO param);
    
    /**
    update  save
    */
    public void updateEntity(CrmCanteenExtInfoPO param);

	public CrmCanteenExtInfoPO getById(Long id);




	/**
	 * @author 黄霄仪
	 * @date 2017年6月22日 下午4:06:26
	 */
	CrmCanteenExtInfoVO findByBaseInfoId(Long canteenId);
	
	
}
