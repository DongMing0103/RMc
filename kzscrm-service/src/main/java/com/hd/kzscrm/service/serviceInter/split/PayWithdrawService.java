package com.hd.kzscrm.service.serviceInter.split;

import java.util.List;
import java.util.Map;

import com.hd.kzscrm.dao.entity.split.PayWithdrawPO;
import com.hd.kzscrm.service.param.split.PayWithdrawParam;
import com.hd.kzscrm.service.vo.split.PayWithdrawVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;
/**
 * 我的账单
 * @author Administrator
 *
 */
public interface PayWithdrawService extends BaseService{
	/**
	 * 查询全部账单
	 * @return
	 */
	List<PayWithdrawPO> findAll();
	
	
	/**
	 * 查询全部
	 * @param param
	 * @return
	 * @author jyt 2017年4月19日 上午11:19:18
	 */
	public List<PayWithdrawPO> findByParams(PayWithdrawParam param) ;
	
	/**
	 * 查询账单明细
	 *@Description : TODO
	 *@author : WCF
	 *@time : 2017年3月17日 下午6:22:05
	 */
	public Page<PayWithdrawVO> findPageByParams(PayWithdrawParam param);
	
	/**
	 * 提现记录查询 
	 * @param param 提现列表查询
	 * @author suchangsong
	 * @date 2017年5月15日 上午11:00:03
	 */
	Page<PayWithdrawVO> getListData(PayWithdrawParam param);

	
	/**
	 * 根据userId查询对象
	 */
	public PayWithdrawPO findByUserId(Long userId);
	
}
