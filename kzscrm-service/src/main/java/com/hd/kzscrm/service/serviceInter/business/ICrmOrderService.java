package com.hd.kzscrm.service.serviceInter.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayClient;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.dao.entity.business.OrderGoodsItemPO;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.service.param.business.CrmBusinessSplitDetailParam;
import com.hd.kzscrm.service.param.business.OrderParam;
import com.hd.kzscrm.service.vo.business.OrderDTO;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;
/**
 * 订单基本信息 
 * @author 黄霄仪
 *
 */
/**
 * 订单基本信息 
 * @author 黄霄仪
 *
 */
public interface ICrmOrderService extends BaseService{
	
	/**
	 * 根据时间统计
	 * @param orderParam
	 * @return
	 * @author suchangsong
	 * @date 2017年5月22日 下午7:58:01
	 */
	public List<OrderPO> findAllByPayTime(OrderParam orderParam);
	/**
	 * 查询全部内部
	 * @return
	 */
	List<OrderPO> findAll(OrderParam orderParam);

	/**
	 * 根据订单ID获取订单信息
	 * @param orderId 订单ID
	 * @return
	 */
	OrderPO findById(Long orderId);
	OrderPO findByorderNo(OrderParam orderParam);
	
	/**
	 * 无条件查询全部 有条件按条件查询
	 *@Description : TODO
	 *@author : lcl
	 *@time : 2017年3月6日 下午2:30:25
	 */
	Page<OrderPO> findByParam(OrderParam param);

	
	/** 
	* 动态查询
	* @return Page<OrderPO> 
	* @author create 郁圆圆
	* @date create 2017年3月7日 下午09:12:20 
	*/
	Page<OrderDTO> findOrderAll(OrderParam param);
	/**
	* 查询全部订单（分页）
	* @param param
	* @return
	* @return Page<OrderPO> 
	* @author create 苏常松
	* @date create 2017年3月10日 下午2:02:10
	 */
	Page<OrderPO> findAllByPage(OrderParam param);
	
	/**
	 * 修改hxy查询全部 待分页
	 * @param param
	 * @return
	 * @author jyt 2017年3月10日 下午5:31:56
	 */
	public Page<OrderPO> findAllByParam(OrderParam param);
	/**
	 * 根据参数查询订单信息
	 * @param orderParam
	 * @return
	 */
	Page<OrderPO> findByParams(OrderParam orderParam);
	
	/**
	 * 统计昨日订单
	 */
	Integer  totalOrderNumYesterday(OrderParam orderParam);
	
	/**
	 * 订单详情
	 * @author 黄霄仪
	 * @date 2017年3月23日 上午10:54:50
	 */
	OrderDTO orderDetail(OrderParam orderParam);
	
	/**
	 * 批量添加
	 * @author 黄霄仪
	 * @date 2017年3月28日 下午4:06:51
	 */
	int batchInsert(List<OrderParam> orderParams);

	/**
	 * 生成支付宝orderInfo
	 * @author 黄霄仪
	 * @date 2017年3月28日 下午4:58:51
	 */
	String alipayOrderInfo(List<String> orderNos, BigDecimal totalAmount,String alipayNotifyUrl,AlipayClient alipayClient);

	/**
	 * 根据订单编号列表去查询订单信息
	 * @author 黄霄仪
	 * @date 2017年3月28日 下午8:31:52
	 */
	List<OrderPO> findByorderNos(List<String> orderNos);

	/**
	 *  根据订单IDS获取订单信息
	 * @author 黄霄仪
	 * @date 2017年3月28日 下午8:38:09
	 */
	OrderPO findByIds(List<Long> orderIds);

	/** 
	* 根据ids进行查询基本信息
	* @return List<OrderPO> 
	* @author create 郁圆圆
	* @date create 2017年4月8日 下午2:49:27 
	*/
	public List<OrderPO> findByPay(OrderParam param);
	
	public List<Map> findNumber(OrderParam param);

	/**
	 * 获取可取餐
	 * @author 黄霄仪
	 * @date 2017年4月10日 下午5:48:26
	 */
	List<OrderDTO> getCanTakeFoodOrderDTOs(OrderParam orderParam);

	/**
	 * 更新支付成功的相关信息
	 * @author 黄霄仪
	 * @date 2017年4月10日 下午6:59:38
	 */
	int updateOrderPaySuccessInfo(OrderParam orderParam);
	
	
	/** 
	* 评价
	* @return List<OrderDTO> 
	* @author create 郁圆圆
	* @date create 2017年4月13日 下午3:19:32 
	*/
	public 	List<OrderDTO> findBySellMoney(OrderParam param);
	

	/**
	 * 退款
	 * @author 黄霄仪
	 * @date 2017年4月13日 下午4:29:48
	 */
	boolean orderRefund(Long orderId);

	/**
	 * 取消订单
	 * @author 黄霄仪
	 * @date 2017年4月14日 下午6:03:48
	 */
	int cancelOrder(Long id);

	/**
	 *  获取已下单，但未支付的数据,供DelayQueue启动时使用
	 * @author 黄霄仪
	 * @date 2017年4月14日 下午7:23:52
	 */
	List<OrderPO> getAlreadyOrder();

	
	/**
	 * 获取单条历史订单详情
	 *@author LuGaogao
	 *@date 2017年4月18日上午11:25:48
	 * @param orderId
	 * @return
	 */
	void getHistoryOrderDetail(Long orderId,ModelAndView view);
	
		/**
	 * @author 黄霄仪
	 * @date 2017年4月18日 下午4:06:01
	 */
	OrderPO findByOrderNo(String orderNo);
	OrderPO findByOrderNo(Long orderNo);
		
	
	/**
	 * 获取 食堂端页面初始化统计(昨日收入,今日订单量,今日就餐人数,products)
	 *@author LuGaogao
	 *@date 2017年4月18日下午8:49:33
	 * @param canteenId
	 * @return
	 */
	ModelAndView getInitStatisticsMsg(Long canteenId);
	
	
	/** 
	* 历史订单，近日订单订单详情
	* @return ModelAndView 
	* @author create 郁圆圆
	* @date create 2017年4月25日 上午11:42:41 
	*/
	public ModelAndView details(HttpServletRequest request);
	
	
	/** 
	* 根据id查订单编号
	* @return List<OrderPO> 
	* @author create 郁圆圆
	* @date create 2017年5月2日 上午10:58:47 
	*/
	public List<OrderPO> findByOrderId(OrderParam param);


	/**
	 * 根据用户id查询多有订单
	* @Title: findByUserId 
	* @author : lcl
	* @time : 2017年5月16日 下午8:20:01
	* @return OrderPO    返回类型 
	* @throws
	 */
	OrderPO findByUserId(Long userId);

	
	/**
	 * 查询用户的订单总数
	* @Title: findTotal 
	* @author : lcl
	* @time : 2017年5月17日 下午2:18:30
	* @return List<OrderPO>    返回类型 
	* @throws
	 */
	List<OrderPO> findTotal(Long userId);
	
	

	List<OrderPO> findListbyParam(OrderParam param);
	
	/**
	 * 导出查询
	 * @param param
	 * @return
	 */
	public Page<OrderVO> findPageSelect(OrderParam param);
	
	/**
	 * 根据订单id查询订单附属表信息
	 * @param id
	 * @return
	 */
	public List<OrderGoodsItemPO> findByOrderId (Long id);
	/**
	 * 普通查询 
	* @Title: findListByParam 
	* @author : lcl
	* @time : 2017年6月23日 下午2:35:07
	* @return List<OrderPO>    返回类型 
	* @throws
	 */
	public List<OrderPO> findListByParam(OrderParam orderParam);
	
	
	/**
	 * 
	 * @Title: orderListDetails 
	 * @Description: 订单列表详情 
	 * @param @param orderParam
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月22日 下午2:13:49
	 */
	public Boolean orderListDetails(OrderParam orderParam,PageRespModel pageRespModel);
	/**
	 * 根据商家id查询订单
	* @Title: findByCanteendId 
	* @author : lcl
	* @time : 2017年6月26日 上午11:23:03
	* @return List<OrderPO>    返回类型 
	* @throws
	 */
	public List<OrderPO> findByCanteendId(Long clientId);
	/**
	 * 根据商家id查询订单
	* @Title: findByCanteendId 
	* @author : lcl
	* @time : 2017年6月26日 上午11:52:03
	* @return List<OrderPO>    返回类型 
	* @throws
	 */
	public Page<OrderVO> findByCanteenId(CrmBusinessSplitDetailParam param);
	/**
	 * 
	 * @Title: orderDetails 
	 * @Description: 查看订单详情 
	 * @param @param orderId
	 * @param @param modelAndView
	 * @param @return  
	 * @return Boolean    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月26日 上午11:34:25
	 */
	public Boolean orderDetails(Long orderId, ModelAndView modelAndView);
	
	/**
	 * 
	 * @Title: orderListDetailsExcelOut 
	 * @Description: 订单列表导出 
	 * @param @param orderParam
	 * @param @return  
	 * @return List<OrderDTO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月29日 下午2:10:38
	 */
	public List<OrderDTO> orderListDetailsExcelOut(OrderParam orderParam);
	
	/**
	 * 我的财富页面查询方法
	 * @param param
	 * @return
	 */
	public Page<OrderVO> queryPage(OrderParam param, Long userId);
	/**
	 * 
	 * @Title: statisticsOrderTotalNumAndOrderTotalMoney 
	 * @Description: 统计订单量及订单金额 
	 * @param @param orderParam
	 * @param @return  
	 * @return Map<Long,List<Object>>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月17日 上午8:57:10
	 */
	public Map<Long, List<Object>> statisticsOrderTotalNumAndOrderTotalMoney(
			OrderParam orderParam);
}

