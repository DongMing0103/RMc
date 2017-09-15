package com.hd.kzscrm.service.serviceInter.split;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.dao.entity.split.PayCanteenCashflowPO;
import com.hd.kzscrm.service.param.split.PayCanteenCashflowParam;
import com.hd.kzscrm.service.vo.split.PayCanteenCashflowVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;

/**
 * 商家现金流水表
 * @author Administrator
 *
 */
public interface PayCanteenCashflowService extends BaseService{
	/**
	 * 分页查询流水
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<PayCanteenCashflowPO> findPageByParams(PayCanteenCashflowParam param);
	/**
	 * 导出
	 *@Description : TODO
	 *@author : WCF
	 *@time : 2017年3月17日 下午10:22:59
	 */
	public List<PayCanteenCashflowPO> findAll(PayCanteenCashflowParam param);
	/**
	 * 求和
	 *@Description : TODO
	 *@author : WCF
	 *@time : 2017年3月17日 下午6:21:56
	 */
	public List<BigDecimal> findSum (PayCanteenCashflowParam param);
	
	/**
	 * 根据提现id查询
	 */
	public PayCanteenCashflowPO findById(Long id);
	
	/**
	 * 时间段金额
	 *@Description : TODO
	 *@author : WCF
	 *@time : 2017年5月9日 下午4:07:08
	 */
	/*Page<PayCanteenCashflowPO> findPeriodTime(PayCanteenCashflowParam param);*/
	public List<BigDecimal> findPeriodTime (PayCanteenCashflowParam param);
	

	/**
	 * 时间段
	 * @param param
	 * @return
	 */
	Page<PayCanteenCashflowPO> findPageByTimeSlot(PayCanteenCashflowParam param);	

	/**
	 * 获取现金流水信息
	 * @param payCanteenCashflowId
	 * @return
	 */
	public PayCanteenCashflowPO findBypayCanteenCashflowId(
			Long payCanteenCashflowId);
	/**
	 * 通用插入
	 * @author 黄霄仪
	 * @date 2017年4月5日 上午10:32:34
	 */
	int commonInsert(PayCanteenCashflowParam param);
	
	/** 
	* 账单总计
	* @return Page<PayCanteenCashflowPO> 
	* @author create 郁圆圆
	* @date create 2017年4月8日 上午10:39:24 
	*/
	public Page<PayCanteenCashflowPO> findByStatus(PayCanteenCashflowParam param);
	
	/** 
	* 导出
	* @return List<PayCanteenCashflowPO> 
	* @author create 郁圆圆
	* @date create 2017年4月10日 下午3:43:32 
	*/
	public List<PayCanteenCashflowPO> excelPay(PayCanteenCashflowParam param);
	
	/** 
	* 提现记录动态查询
	* @return Page<PayCanteenCashflowPO> 
	* @author create 郁圆圆
	* @date create 2017年4月11日 上午10:48:21 
	*/
	public Page<PayCanteenCashflowPO> findByRecord(PayCanteenCashflowParam param);
	
	/** 
	* 统计成功失败个数
	* @return List<Map> 
	* @author create 郁圆圆
	* @date create 2017年4月11日 下午2:22:46 
	*/
	public List<Map> findCountNumber();
	
	/** 
	* 提现记录导出
	* @return List<PayCanteenCashflowPO> 
	* @author create 郁圆圆
	* @date create 2017年4月11日 下午3:43:18 
	*/
	public List<PayCanteenCashflowPO> excelRecord(PayCanteenCashflowParam param);
	
	/** 
	* 动态查询  账单总计
	* @return PageRespModel 
	* @author create 郁圆圆
	* @date create 2017年4月18日 上午10:36:30 
	*/
	public PageRespModel listData(PayCanteenCashflowParam param);
	
	/** 
	* 初始化 账单总计
	* @return ModelAndView 
	* @author create 郁圆圆
	* @date create 2017年4月18日 上午10:38:36 
	*/
	public ModelAndView init();
	
	/** 
	* 提现记录  动态查询
	* @return PageRespModel 
	* @author create 郁圆圆
	* @date create 2017年4月18日 上午10:43:06 
	*/
	public PageRespModel listDataRecord(PayCanteenCashflowParam param);
	
	/** 
	* 初始化 账单总计
	* @return ModelAndView 
	* @author create 郁圆圆
	* @date create 2017年4月18日 上午10:38:36 
	*/
	public ModelAndView initRecord();
	/**
	 * @author 黄霄仪
	 * @date 2017年4月18日 下午3:13:22
	 */
	int batchInsert(List<PayCanteenCashflowParam> payCanteenCashflowParams);
	/**
	 * 获取现金流水信息供导出
	 *@author LuGaogao
	 *@date 2017年4月19日上午9:33:33
	 * @param param
	 * @return
	 */
	public List<PayCanteenCashflowVO> getCashflowMsgForExcel(
			PayCanteenCashflowParam param);
	
	/** 
	* 查询账单总计个数
	* @return List<PayCanteenCashflowPO> 
	* @author create 郁圆圆
	* @date create 2017年4月28日 下午1:52:27 
	*/
	public List<PayCanteenCashflowPO> findByStatusNum(PayCanteenCashflowParam param);
	/**
	 * @author 黄霄仪
	 * @date 2017年5月9日 下午2:11:00
	 */
	PayCanteenCashflowPO setAllField(Long id, String cashFlowNo, Long userId,
			Date createTime, Integer cashFlowType, BigDecimal cashFlowMoney,
			BigDecimal balance, Long canteenId, Long canteenOrderId,
			Integer cashFlowStatus, Integer userType, Integer cashflowType,Integer payModel,Integer refundType,String payTimestamp);

	/**
	 * 根据参数查询金额
	 * @param param
	 * @return
	 */
	public List<BigDecimal> findPeriodParam(PayCanteenCashflowParam param);

	
	
	/**
	 * 根据userid 查询现金流水个数
	* @Title: findByUserId 
	* @author : lcl
	* @time : 2017年5月11日 上午11:21:29
	* @return List<Map>    返回类型 
	* @throws
	 */
	public List<Map> findByUserId(Long userId);
	/**
	 * 根据userId分页查询 用户的现金流水
	* @Title: findPageByUserId 
	* @author : lcl
	* @time : 2017年5月11日 下午3:54:21
	* @return Page<PayCanteenCashflowPO>    返回类型 
	* @throws
	 */
	public Page<PayCanteenCashflowPO> findPageByUserId(PayCanteenCashflowParam param);
	
	/** 
	* 余额消费
	* @return List<PayCanteenCashflowPO> 
	* @param    
	* @author create 郁圆圆
	* @date create 2017年5月12日 下午7:03:22 
	*/
	public List<PayCanteenCashflowPO> findByConsumption(PayCanteenCashflowParam param);

	
	/** 
	* 余额消费导出
	* @return List<PayCanteenCashflowPO> 
	* @param    
	* @author create 郁圆圆
	* @date create 2017年5月13日 下午2:25:16 
	*/
	public List<PayCanteenCashflowPO> findByConsumptionExcel(PayCanteenCashflowParam param);
	/**
	 * excel 导出
	 * @param param 
	 * @author create 郁圆圆
	 */
	public void excelCountPay(HttpServletResponse response,PayCanteenCashflowParam param) throws Exception ;
	
	/** 
	* 账单总计动态查询
	* @param  传入参数
	* @author create 郁圆圆
	* @date 2017年5月16日 下午1:50:43 
	*/
	public List<PayCanteenCashflowPO> listByStatus(PayCanteenCashflowParam param);
	
	/**
	 * 查询用户的订单列表
	* @Title: userOrderTotal 
	* @author : lcl
	* @time : 2017年5月16日 下午8:08:06
	* @return PageRespModel    返回类型 
	* @throws
	 */
	public PageRespModel userOrderTotal(PayCanteenCashflowParam param);

	
	/** 
	 * 账单明细
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年5月17日 上午9:41:00 
	*/
	public List<PayCanteenCashflowPO> findByTypes(PayCanteenCashflowParam param);
	
	/** 
	 * 账单明细-动态查询
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年5月17日 下午3:21:51 
	*/
	public List<PayCanteenCashflowPO> findByStatus1(PayCanteenCashflowParam param);
	
	/**
	 * 账单明细统计
	 * @param param 查询条件
	 * @return 
	 * @author jyt 2017年5月17日 下午3:43:43
	 */
	public String findByCountNew(PayCanteenCashflowParam param);
	
	/**
	 * 计算账单一月流水【 不区分支付退款】
	 * @param param 条件
	 * @return
	 * @author jyt 2017年5月17日 下午8:29:01
	 */
	public List<BigDecimal> findPeriodParamByCanteen(PayCanteenCashflowParam param);
	/**
	 * 
	 * @Title: queryOrderCashFlowNo 
	 * @Description: 通过订单Id获取订单现金流水号 
	 * @param @param orderId
	 * @param @return  
	 * @return String    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月22日 下午3:16:00
	 */
	public String queryOrderCashFlowNo(Long orderId);
	/**
	 * 根据订单id查询流水表
	* @Title: findByOrderId 
	* @author : lcl
	* @time : 2017年6月26日 下午2:07:57
	* @return PayCanteenCashflowPO    返回类型 
	* @throws
	 */
	public PayCanteenCashflowPO findByOrderId(Long orderId);
	
}
