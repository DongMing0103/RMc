package com.hd.kzscrm.service.serviceInter.canteen;

import java.util.List;

import com.hd.kzscrm.dao.entity.canteen.GoodImgPO;
import com.hd.kzscrm.service.param.canteen.GoodImgParam;
import com.hd.wolverine.service.BaseService;

/**
 * 
 * @ClassName: ICrmGoodImgService 
 * @Description: 商品图片表 
 * @author LuGaogao 
 * @date 2017年6月26日 下午5:41:23 
 *
 */
public interface ICrmGoodImgService extends BaseService {
    
	/**
	 * 
	 * @Title: commonSelect 
	 * @Description: 通用查询 
	 * @param @param param
	 * @param @return  
	 * @return List<GoodImgPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月26日 下午5:45:28
	 */
	List<GoodImgPO> commonSelect(GoodImgParam param);
	
	/**
	 * 根据goodsId查询信息
	 * @param goodsId
	 * @return
	 */
	List<GoodImgPO> findByGoodsId(Long goodId);
}
