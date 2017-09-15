package com.hd.kzscrm.service.serviceimpl.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.dao.entity.UserPO;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessOrderPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.business.OrderGoodsItemPO;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.canteen.GoodImgPO;
import com.hd.kzscrm.dao.entity.canteen.SupplyCategoryPO;
import com.hd.kzscrm.dao.entity.enterprise.EnterpriseDepartmentPO;
import com.hd.kzscrm.dao.entity.enterprise.EnterpriseEmployeesLinkPO;
import com.hd.kzscrm.dao.entity.enterprise.EnterprisePO;
import com.hd.kzscrm.dao.entity.enterprise.EnterpriseUserPositionPO;
import com.hd.kzscrm.dao.entity.split.PayCanteenCashflowPO;
import com.hd.kzscrm.service.param.agent.CrmSplitAssignSetParam;
import com.hd.kzscrm.service.param.business.CrmBusinessSplitDetailParam;
import com.hd.kzscrm.service.param.business.OrderGoodsItemParam;
import com.hd.kzscrm.service.param.business.OrderParam;
import com.hd.kzscrm.service.param.canteen.GoodImgParam;
import com.hd.kzscrm.service.param.enterprise.EnterpriseEmployeesLinkParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderGoodsItemService;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmGoodImgService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmSupplyCategoryService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseDepartmentService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseEmployeesLinkService;
import com.hd.kzscrm.service.serviceInter.enterprise.ICrmEnterpriseUserPositionService;
import com.hd.kzscrm.service.serviceInter.split.PayCanteenCashflowService;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.vo.business.OrderDTO;
import com.hd.kzscrm.service.vo.business.OrderGoodsItemDTO;
import com.hd.kzscrm.service.vo.business.OrderVO;
import com.hd.kzscrm.service.vo.enterprise.EnterpriseEmployeesLinkDTO;
import com.hd.wolverine.common.log.LogHelper;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * 订单基本信息
 * @author 黄霄仪
 *
 */
@Service
public class CrmOrderServiceImpl  extends BaseServiceImpl  implements ICrmOrderService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmOrderServiceImpl.class);
	protected final LogHelper logHelper = LogHelper.getLogger(this.getClass());
	@Resource
    private SqlSessionTemplate sqlSession;
	/**
	 * 现金流水表
	 */
	@Resource
	private PayCanteenCashflowService payCanteenCashflowService;
	//抽成设置表
	@Autowired
	private ICrmSplitAssignSetService splitAssignSetService;
	//现金流水表
	@Autowired
	private PayCanteenCashflowService pCashflowService;
	//食堂基本表
	@Autowired
	private ICrmCanteenBaseInfoService  baseInfoService;
	
	@Autowired
	private ICrmBusinessOrderService crmBusinessOrderService;
	
	/**
	 * 订单附属表
	 */
	@Resource
	ICrmOrderGoodsItemService crmOrderGoodsItemService;
	
	/**
	 * 业务员表
	 */
	@Resource
	ICrmBusinessService crmBusinessService;
	
	/**
	 * 单位员工表
	 */
	@Resource
	ICrmEnterpriseEmployeesLinkService crmEnterpriseEmployeesLinkService;
	/**
	 * 单位部门表
	 */
	@Resource
	ICrmEnterpriseDepartmentService crmEnterpriseDepartmentService;
	
	/**
	 * 岗位职务表
	 */
	@Resource
	ICrmEnterpriseUserPositionService crmEnterpriseUserPositionService;
	
	/**
	 * 供应类目表
	 */
	@Resource
	ICrmSupplyCategoryService crmSupplyCategoryService;
	/**
	 * 商品图片表
	 */
	@Resource
	ICrmGoodImgService crmGoodImgService;
	
	 @Value("${img.view.address}")
	 private String imgViewAddress;
	 
	 /**
	  * 用户表
	  */
	 @Resource
	 private UserService userService;
	/**
	 * 根据时间统计
	 * @param orderParam
	 * @return
	 * @author suchangsong
	 * @date 2017年5月22日 下午7:58:01
	 */
	public List<OrderPO> findAllByPayTime(OrderParam orderParam){
		return commonDao.listByParams(OrderPO.class, "OrderMapper.findAllByPayTime",new  ParamMap(orderParam));
	}
	
	/**
	 * 获取全部信息
	 */
	@Override
	public List<OrderPO> findAll(OrderParam orderParam) {
		return commonDao.listByParams(OrderPO.class, "OrderMapper.findAll",new  ParamMap(orderParam));
	}

	/**
	 * 获取已下单，但未支付的数据,供DelayQueue启动时使用
	 * @author 黄霄仪
	 * @date 2017年4月14日 下午7:21:47
	 */
	@Override
	public List<OrderPO> getAlreadyOrder(){
		ParamMap paramMap=new  ParamMap();
		paramMap.put("status",1);//已下单
		List<OrderPO> orderPOs = commonDao.listByParams(OrderPO.class, "OrderMapper.findAll",paramMap);
		return orderPOs;
	}

	/**
	 * 根据订单ID获取订单信息
	 */
	@Override
	public OrderPO findById(Long orderId){
		ParamMap paramMap=new  ParamMap();
		paramMap.put("id", orderId);
		List<OrderPO> orderPOs = commonDao.listByParams(OrderPO.class, "OrderMapper.findById",paramMap);
		if(BeanUtils.isNotEmpty(orderPOs)&&orderPOs.size()==1){
			return orderPOs.get(0);
		}
		return null; 
	}
	
	/**
	 * 根据订单IDS获取订单信息
	 */
	@Override
	public OrderPO findByIds(List<Long> orderIds){
		ParamMap paramMap=new  ParamMap();
		paramMap.put("ids", orderIds);
		List<OrderPO> orderPOs = commonDao.listByParams(OrderPO.class, "OrderMapper.findAll",paramMap);
		if(BeanUtils.isNotEmpty(orderPOs)&&orderPOs.size()==1){
			return orderPOs.get(0);
		}
		return null; 
	}
	
	public  OrderPO findByIdAndStatus(Long orderIds){
		ParamMap paramMap=new  ParamMap();
		paramMap.put("id", orderIds);
		List<OrderPO> orderPOs = commonDao.listByParams(OrderPO.class, "OrderMapper.findByIdAndStatus",paramMap);
		if(BeanUtils.isNotEmpty(orderPOs)&&orderPOs.size()==1){
			return orderPOs.get(0);
		}
		return null;
	}
	
	/**
	 * 无条件查询全部 有条件按条件查询
	 *@author : lcl
	 *@time : 2017年3月6日 下午2:30:25
	 */
	@Override
	public Page<OrderPO> findByParam(OrderParam param) {
		ParamMap paramMap = new ParamMap(param);
		return findPageByParams(OrderPO.class, new Page<OrderPO>(param.getOffset(), param.getLimit()), "OrderMapper.findByParam", paramMap);
	}

	/**
	 * 根据参数查询订单信息
	 */
	@Override
	public Page<OrderPO> findByParams(OrderParam orderParam) {
		ParamMap paramMap = new ParamMap(orderParam);
		return findPageByParams(OrderPO.class, new Page<OrderPO>(orderParam.getOffset(), orderParam.getLimit()), "OrderMapper.findByParams", paramMap);
	}
	
	/**
	 * 根据商品id ,供应id ,就餐时间消费状态等关联查询订单附属表,获取订单信息
	 */
	@Override
	public List<OrderPO> findListbyParam(OrderParam orderParam) {
		ParamMap paramMap = new ParamMap(orderParam);
		return commonDao.listByParams(OrderPO.class, "OrderMapper.findByParams", paramMap);
	}

	
	/**
	 * 提供公共的调用类
	* @author 苏常松
	* @date 2017年3月18日 下午3:52:23
	 */
	public List<Map> getData(List<String> list,List<Map> mapList){
		List<Map> maps=new ArrayList<Map>();
		for(String str:list){
			Map m=new HashMap();
			m.put("time",str);
			m.put("money",0);
			m.put("num", 0);
			if(CollectionUtils.isNotEmpty(mapList)){
				for(Map map:mapList){
					if(map.get("time").toString().trim().equals(str.trim().toString())){
						if(map.get("money")!=null){
							m.put("money",map.get("money"));
						}
						if(map.get("num")!=null){
							m.put("num",map.get("num"));
						}
					};
				}
			}
			maps.add(m);
		}
		return maps;
	}
	
	@Override
	public OrderPO findByorderNo(OrderParam orderParam) {
		ParamMap paramMap  =  new  ParamMap(orderParam);
		List<OrderPO> listByParams = commonDao.listByParams(OrderPO.class, "OrderMapper.findAll", paramMap);
		if(BeanUtils.isNotEmpty(listByParams) && listByParams.size() >0){
			return listByParams.get(0);
		}
		return null;
	}
	/**
	 * 根据 订单获取数据
	 * @author 黄霄仪
	 * @date 2017年4月18日 下午4:05:53
	 */
	@Override
	public OrderPO findByOrderNo(String orderNo) {
		ParamMap paramMap  =  new  ParamMap();
		paramMap.put("orderNo", orderNo);
		List<OrderPO> listByParams = commonDao.listByParams(OrderPO.class, "OrderMapper.findAll", paramMap);
		if(BeanUtils.isNotEmpty(listByParams) && listByParams.size() >0){
			return listByParams.get(0);
		}
		return null;
	}
	@Override
	public OrderPO findByOrderNo(Long orderNo) {
		ParamMap paramMap  =  new  ParamMap();
		paramMap.put("orderNo", orderNo);
		List<OrderPO> listByParams = commonDao.listByParams(OrderPO.class, "OrderMapper.findAll", paramMap);
		if(BeanUtils.isNotEmpty(listByParams) && listByParams.size() >0){
			return listByParams.get(0);
		}
		return null;
	}
	
	/**
	 * TODO
	 * 订单支付通用接口
	 * @author 黄霄仪
	 * @date 2017年4月18日 下午2:31:18
	 */
	public void orderPayCommon(List<OrderPO> orderPOs){
		
	}
	/**
	 * 根据订单编号列表去查询订单信息
	 * @author 黄霄仪
	 * @date 2017年3月28日 下午8:31:34
	 */
	@Override
	public List<OrderPO> findByorderNos(List<String> orderNos) {
		ParamMap paramMap  =  new  ParamMap();
		paramMap.put("orderNos", orderNos);
		List<OrderPO> listByParams = commonDao.listByParams(OrderPO.class, "OrderMapper.findAll", paramMap);
		return listByParams;
	}
	/**
	 * 批量添加
	 * @author 黄霄仪
	 * @date 2017年3月28日 下午3:44:52
	 */
	@Override
	public int batchInsert(List<OrderParam> orderParams){
		ParamMap paramMap=new ParamMap();
		paramMap.put("orders", orderParams);
		return commonDao.execute("OrderMapper.batchInsert", paramMap);
	}
	
	
	
	/**
	 * 设置订单金额字段
	 * @author 黄霄仪
	 * @date 2017年5月9日 下午12:01:04
	 */
	private void setOrderMoneyField(OrderParam orderParam,
			BigDecimal orderTotalMoney, BigDecimal realMoney,
			BigDecimal discountMoney, BigDecimal cutMoney,
			BigDecimal cutPercent, BigDecimal channelMoney,
			BigDecimal orderRealMoney) {
		orderParam.setTotalMoney(orderTotalMoney);// 订单总价
		orderParam.setRealMoney(realMoney);// 实价,指扣掉了抽成价格的金额
		orderParam.setDiscountMoney(discountMoney);// 减免价格
		orderParam.setCutMoney(cutMoney);// 抽成价
		orderParam.setCutPercent(cutPercent);// 抽成比例
		orderParam.setChannelMoney(channelMoney);// 通道费
		orderParam.setOrderRealMoney(orderRealMoney);// 订单实际金额
	}
	
	/**
	 * 取消订单
	 * @author 黄霄仪
	 * @date 2017年4月14日 下午6:03:48
	 */
	@Override
	public int cancelOrder(Long id){
		if(BeanUtils.isEmpty(id)){
			return 0;
		}
		ParamMap paramMap=new ParamMap();
		paramMap.put("id", id);
		return commonDao.execute("OrderMapper.cancelOrder", paramMap);
	}
	
	/**
	 * 更新支付成功的相关信息
	 * @author 黄霄仪
	 * @date 2017年4月10日 下午6:59:25
	 */
	@Override
	public int updateOrderPaySuccessInfo(OrderParam orderParam){
		ParamMap paramMap=new ParamMap(orderParam);
		return commonDao.execute("OrderMapper.updateOrderPayInfo", paramMap);
	}
	
	
	
	/**
	 * 支付宝orderInfo
	 * @author 黄霄仪
	 * @date 2017年3月28日 下午2:47:10
	 * @param orderNos 订单号
	 * @param totalAmount 金额
	 * @param alypayNotifyType 支付宝回调接口类型，1.用户充值，2.订单支付
	 */
	@Override
	public String alipayOrderInfo(List<String> orderNos,BigDecimal totalAmount,String alipayNotifyUrl,AlipayClient alipayClient){
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.serverUrl, AlipayConfig.partner, AlipayConfig.privateKey, "json", AlipayConfig.input_charset, AlipayConfig.publicKey, "RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("我是测试数据");
		model.setSubject("App支付测试Java");
		model.setOutTradeNo(StringUtils.join(orderNos,","));
		model.setTimeoutExpress("30m");
		model.setTotalAmount(totalAmount.setScale(2,BigDecimal.ROUND_DOWN).toString());
		model.setGoodsType("1");//实物
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(alipayNotifyUrl);
//		request.setReturnUrl("支付成功后的回调地址");
		try {
	        //这里和普通的接口调用不同，使用的是sdkExecute
	        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
	        String body = response.getBody();
	        System.out.println("支付宝验签"+body);
			return body;//就是orderString 可以直接给客户端请求，无需再做处理。
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    }
		return null;
	}

	/** 
	* 根据ids进行查询基本信息
	* @author create 郁圆圆
	* @date create 2017年4月8日 下午2:50:11 
	*/
	@Override
	public List<OrderPO> findByPay(OrderParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(OrderPO.class, "OrderMapper.findByPay", paramMap);
	}

	/** 
	* 计算支付和退款个数
	* @author create 郁圆圆
	* @date create 2017年4月10日 下午2:25:14 
	*/
	@Override
	public List<Map> findNumber(OrderParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams("OrderMapper.findNumber",paramMap);
	}
	
	
	/**
	 * 统计昨日订单
	 */
	@Override
	public Integer totalOrderNumYesterday(OrderParam orderParam) {
		ParamMap paramMap = new ParamMap(orderParam);
		Integer orderNum = sqlSession.selectOne("OrderMapper.totalOrderNum", paramMap);
		if(BeanUtils.isEmpty(orderNum)){
			return 0;
		}else{
			return orderNum;
		}
	}

	/** 
	* 评价
	* @author create 郁圆圆
	* @date create 2017年4月13日 下午3:19:53 
	*/
	@Override
	public List<OrderDTO> findBySellMoney(OrderParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(OrderDTO.class,"OrderMapper.findBySellMoney", paramMap);
	}

	
	@Override
	public Page<OrderDTO> findOrderAll(OrderParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OrderPO> findAllByPage(OrderParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OrderPO> findAllByParam(OrderParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO orderDetail(OrderParam orderParam) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<OrderDTO> getCanTakeFoodOrderDTOs(OrderParam orderParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean orderRefund(Long orderId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getHistoryOrderDetail(Long orderId, ModelAndView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelAndView getInitStatisticsMsg(Long canteenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView details(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderPO> findByOrderId(OrderParam param) {
		// TODO Auto-generated method stub
		return null;
	}

		@Override
	public OrderPO findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderPO> findTotal(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * PO 转换为 VO
	 * @param orderList
	 * @return
	 */
	private List<OrderVO> convertPOToVO (List<OrderPO> orderList) {
		List<OrderVO> orderVoList = new ArrayList<OrderVO>();
		if (CollectionUtils.isEmpty(orderList)) {
			return orderVoList;
		}
		for (OrderPO tag : orderList) {
			OrderVO tagVo = BeanConvertor.copy(tag, OrderVO.class);
			orderVoList.add(tagVo);
		}
		return orderVoList;
	}
	
	/**
	 * 导出查询
	 */
	@Override
	public Page<OrderVO> findPageSelect(OrderParam param) {
		ParamMap paramMap = new ParamMap(param);
		Page<OrderPO> pages = findPageByParams(OrderPO.class, new Page<OrderPO>(param.getOffset(),param.getLimit()),"OrderMapper.findAll", paramMap);
		List<OrderVO> rows = new ArrayList<OrderVO>();
		int total = 0;
		if (pages.result != null) {
			rows = convertPOToVO(pages.result);
			total = pages.getTotalResult();
		}
		Page<OrderVO> pageResult = new Page<OrderVO>();
		pageResult.result= rows;
		pageResult.setTotalResult(total);
		return pageResult;
		
	}

	/**
	 * 根据订单id 查询订单附属信息
	 */
	@Override
	public List<OrderGoodsItemPO> findByOrderId(Long id) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("canteenOrderId", id);
		List<OrderGoodsItemPO> orderGoodsItemPOs = listByParams(OrderGoodsItemPO.class, "OrderGoodsItemMapper.findByOrderId", paramMap);
		if (orderGoodsItemPOs != null) {
			return orderGoodsItemPOs;
		}
		return null;
	}

	/**
	 * 普通查询 
	* @Title: findListByParam 
	* @author : lcl
	* @time : 2017年6月23日 下午2:35:07
	* @return List<OrderPO>    返回类型 
	* @throws
	 */
	@Override
	public List<OrderPO> findListByParam(OrderParam orderParam) {
		ParamMap paramMap = new ParamMap(orderParam);
		List<OrderPO> orderPOs = listByParams(OrderPO.class, "OrderMapper.findListByParam", paramMap);
		if(CollectionUtils.isNotEmpty(orderPOs)){
			return orderPOs;
		}
		return null;
	}

	/**
	 * 订单列表详情
	 */
	@Override
	public Boolean orderListDetails(OrderParam orderParam,PageRespModel pageRespModel) {
		Page<OrderPO> orderPOPage = this.findByParam(orderParam);//查询订单信息
		List<OrderDTO> orderDTOs = BeanConvertor.convertBean(orderPOPage.result, ArrayList.class);
		if(CollectionUtils.isEmpty(orderDTOs)){
			pageRespModel.setCode(RespModel.RespCode.NOT_DATA.getCode());
			pageRespModel.setRows(null);
			pageRespModel.setTotal(0);
			pageRespModel.setDesc("暂无数据");
			return true;
		}
		Map<Long,String> userIdWithUserMobilephone = new HashMap<Long, String>();//用户Id:用户手机号
		for(int i = 0 ; i < orderDTOs.size() ; i++){
			OrderDTO orderDTO = BeanConvertor.convertBean(orderDTOs.get(i), OrderDTO.class);
			
			Long orderId = orderDTO.getId();
			String cashFlowNo = payCanteenCashflowService.queryOrderCashFlowNo(orderId);//通过订单Id获取订单现金流水号
			orderDTO.setCashFlowNo(cashFlowNo);
			
			//获取userid
			Long userId = orderDTO.getUserId();
			if(!userIdWithUserMobilephone.containsKey(userId)){
				userIdWithUserMobilephone.put(userId, null);
			}
			
			orderDTOs.set(i,orderDTO);
		}
		
		//查询用户手机号
		Set<Long> userIdSet = userIdWithUserMobilephone.keySet();
		if(CollectionUtils.isNotEmpty(userIdSet)){
			List<UserPO> userPOs = userService.queryByIds(new LinkedList<Long>(userIdSet));
			userIdWithUserMobilephone.clear();
			for (UserPO userPO : userPOs) {
				userIdWithUserMobilephone.put(userPO.getId(), userPO.getMobilephone());
			}
		}
		
		
		//设置用户手机号
		if(null != userIdWithUserMobilephone && userIdWithUserMobilephone.size() >0){
			for(OrderDTO orderDTO : orderDTOs) {
				Long userId = orderDTO.getUserId();
				if(userIdWithUserMobilephone.containsKey(userId)){
					orderDTO.setUserMobilephone(userIdWithUserMobilephone.get(userId));
				}
			}
		}
		
		pageRespModel.setCode(RespModel.RespCode.SUCCESS.getCode());
		pageRespModel.setRows(orderDTOs);
		pageRespModel.setTotal(orderPOPage.getTotalResult());
		return false;
	}

	/**
	 * 根据商家id查询订单
	* @Title: findByCanteendId 
	* @author : lcl
	* @time : 2017年6月26日 上午11:23:03
	* @return List<OrderPO>    返回类型 
	* @throws
	 */
	@Override
	public List<OrderPO> findByCanteendId(Long clientId) {
		ParamMap paramMap = new  ParamMap();
		paramMap.put("clientId", clientId);
		
		List<OrderPO> orderPOs = listByParams(OrderPO.class,"OrderMapper.findByCanteendId", paramMap);
		if(CollectionUtils.isNotEmpty(orderPOs)){
			return orderPOs;
		}
		return null;
	}
	/**
	 * 根据商家id查询订单
	* @Title: findByCanteendId 
	* @author : lcl
	* @time : 2017年6月26日 上午11:52:03
	* @return List<OrderPO>    返回类型 
	* @throws
	 */
	@Override
	public Page<OrderVO> findByCanteenId(CrmBusinessSplitDetailParam param) {
		
		ParamMap paramMap = new  ParamMap(param);
		
		Page<OrderPO> orderPOs = findPageByParams(OrderPO.class,new Page<OrderPO>(param.getOffset(),param.getLimit()),"OrderMapper.findPageByCanteenId", paramMap);
		List<OrderPO> orderList = orderPOs.result;
		List<OrderVO> orderVOs = new ArrayList<OrderVO>();
		if(CollectionUtils.isNotEmpty(orderList)){
			for(int i =0;i<orderList.size();i++){
				OrderPO orderPO = orderList.get(i);
				OrderVO orderVO = BeanConvertor.convertBean(orderPO, OrderVO.class);
				if(BeanUtils.isNotEmpty(orderPO)){
					//订单的金额
					
					BigDecimal cutMoney  = orderPO.getCutMoney();//抽成金额
				
					if(BeanUtils.isNotEmpty(cutMoney)){
						orderVO.setCutMoney(cutMoney.setScale(2,BigDecimal.ROUND_DOWN));
					}else{
						orderVO.setCutMoney(new BigDecimal("0"));
					}
					//根据orderId查询 抽成设置
					Long orderId = orderPO.getId();
					//现金流水
					if(BeanUtils.isNotEmpty(orderId)){
						CrmSplitAssignSetParam splitAssignSetParam = new CrmSplitAssignSetParam();
						splitAssignSetParam.setOrderId(orderId);
						List<CrmSplitAssignSetPO> assignSetPOs = splitAssignSetService.commonQuery(splitAssignSetParam);
						if(CollectionUtils.isNotEmpty(assignSetPOs)){
							BigDecimal realMoney = orderPO.getRealMoney();//实付金额
							if(BeanUtils.isNotEmpty(realMoney)){
								orderVO.setRealMoney(realMoney.setScale(2,BigDecimal.ROUND_DOWN));
							}else{
								orderVO.setRealMoney(new BigDecimal("0"));
							}
							CrmSplitAssignSetPO splitAssignSetPO = assignSetPOs.get(0);
							if(BeanUtils.isNotEmpty(splitAssignSetPO)){
								//分账金额
								BigDecimal cutRatio  = splitAssignSetPO.getCanteenSplitPercent().divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
								if(BeanUtils.isNotEmpty(cutRatio)){
									if(BeanUtils.isNotEmpty(realMoney)){
										BigDecimal splitMoney = cutRatio.multiply(realMoney);
										orderVO.setCanteenSplitPercent(cutRatio);//分账比例
										orderVO.setSplitMoney(splitMoney.setScale(2,BigDecimal.ROUND_DOWN));//分账金额
									}
								}
							}
							
						}
						PayCanteenCashflowPO cashflowPO = pCashflowService.findByOrderId(orderId);
						if(BeanUtils.isNotEmpty(cashflowPO)){
							orderVO.setCashFlowNo(cashflowPO.getCashFlowNo());//现金流水表 流水号
						}
						
						//食堂名称
						Long canteenId = orderPO.getCanteenId();//商家id
						if(BeanUtils.isNotEmpty(canteenId)){
							CrmCanteenBaseInfoPO baseInfoPO = baseInfoService.findByCanteenId(canteenId);
							if(BeanUtils.isNotEmpty(baseInfoPO)){
								orderVO.setCanteenName(baseInfoPO.getName());//食堂名称
							}
						}
						
					}
					
					
				}
				orderVOs.add(orderVO);
			}
		}
		Page<OrderVO> oPage = new  Page<OrderVO>();
		oPage.result = orderVOs;
		oPage.setTotalResult(orderPOs.getTotalResult());
		return oPage;
	}

	/**
	 * 查看订单详情
	 */
	@Override
	public Boolean orderDetails(Long orderId, ModelAndView modelAndView) {
		
		//查询订单信息
		OrderPO orderPO =this.findById(orderId);
		OrderDTO orderDTO = BeanConvertor.convertBean(orderPO,OrderDTO.class);
		//查询供应类目
		Long supplyCategoryId = orderPO.getSupplyCategoryId();
		List<SupplyCategoryPO> supplyCategoryPOs = crmSupplyCategoryService.findBySupplyId(supplyCategoryId);
		if(CollectionUtils.isNotEmpty(supplyCategoryPOs)){
			SupplyCategoryPO supplyCategoryPO = supplyCategoryPOs.get(0);
			orderDTO.setSupplyCategoryName(supplyCategoryPO.getTimeName());//设置供应类目名
		}
		
		//格式化支付状态
		Integer status = orderPO.getStatus();
		if(null != status){
			switch (status) {
			case 1:
				orderDTO.setStatusName("未支付 ");
				break;
			case 2:
				orderDTO.setStatusName("已支付 ");
				break;
			case 3:
				orderDTO.setStatusName("已接单");
				break;
			case 4:
				orderDTO.setStatusName("已送达 ");
				break;
			case 5:
				orderDTO.setStatusName("已完成 ");
				break;
			case 6:
				orderDTO.setStatusName("已评价 ");
				break;
			case 7:
				orderDTO.setStatusName("已取消 ");
				break;
			case 8:
				orderDTO.setStatusName("待评价 ");
				break;
			case 9:
				orderDTO.setStatusName("待取餐 ");
				break;
			default:
				break;
			}
		}
		//格式化就餐状态
		Integer consumeStatus = orderPO.getConsumeStatus();//消费状态 1食堂就餐 2 外来用餐 3外卖 4其他
		if(null != consumeStatus){
			switch (consumeStatus) {
			case 1:orderDTO.setConsumeStatusName("食堂就餐");
				break;
				
			case 2:orderDTO.setConsumeStatusName("外来用餐");
				break;
				
			case 3:orderDTO.setConsumeStatusName("外卖");
				break;
				
			case 4:orderDTO.setConsumeStatusName("其他");
				break;

			default:
				break;
			}
		}
		
		modelAndView.addObject("orderDTO", orderDTO);
		
		
		//查询订单附属信息
		OrderGoodsItemParam orderGoodsItemParam = new OrderGoodsItemParam();
		orderGoodsItemParam.setCanteenOrderId(orderId);
		List<OrderGoodsItemPO> orderGoodsItemPOs = crmOrderGoodsItemService.findByParams(orderGoodsItemParam);
		List<OrderGoodsItemDTO>  orderGoodsItemDTOs = new ArrayList<OrderGoodsItemDTO>();
		for (OrderGoodsItemPO orderGoodsItemPO : orderGoodsItemPOs) {
			Long goodsId = orderGoodsItemPO.getGoodsId();
			GoodImgParam goodImgParam = new GoodImgParam();
			goodImgParam.setGoodId(goodsId);
			List<GoodImgPO> goodImgPOs = crmGoodImgService.commonSelect(goodImgParam );
			OrderGoodsItemDTO orderGoodsItemDTO = BeanConvertor.convertBean(orderGoodsItemPO, OrderGoodsItemDTO.class);
			if(CollectionUtils.isNotEmpty(goodImgPOs)){
				GoodImgPO goodImgPO = goodImgPOs.get(0);
				orderGoodsItemDTO.setGoodsImg(imgViewAddress+goodImgPO.getGoodImg());
			}
			orderGoodsItemDTOs.add(orderGoodsItemDTO);
		}
		
		modelAndView.addObject("orderGoodsItemDTOs", orderGoodsItemDTOs);
		
		
		Long userId = orderPO.getUserId();
		//查询用户信息
		UserPO userPO = new UserPO();
		userPO.setId(userId);
		userPO = (UserPO) commonDao.getByExample(userPO);
		//查询企业员工表信息
		EnterpriseEmployeesLinkParam enterpriseEmployeesLinkParam = new EnterpriseEmployeesLinkParam();
		enterpriseEmployeesLinkParam.setUserId(userId);
		List<EnterpriseEmployeesLinkPO> enterpriseEmployeesLinkPOs = crmEnterpriseEmployeesLinkService.findAll(enterpriseEmployeesLinkParam);
		if(CollectionUtils.isEmpty(enterpriseEmployeesLinkPOs)){
			return true;
		}
		
		//查询部门及岗位职务信息
		EnterpriseEmployeesLinkPO enterpriseEmployeesLinkPO = enterpriseEmployeesLinkPOs.get(0);
		//获取企业信息
		Long enterpriseId = enterpriseEmployeesLinkPO.getEnterpriseId();
		EnterprisePO enterprisePO = new EnterprisePO();
		enterprisePO.setId(enterpriseId);
		enterprisePO = (EnterprisePO) commonDao.getByExample(enterprisePO);
		
		
		//查询部门信息
		Long departmentId = enterpriseEmployeesLinkPO.getDepartmentId();
		EnterpriseDepartmentPO enterpriseDepartmentPO = new EnterpriseDepartmentPO();
		enterpriseDepartmentPO.setId(departmentId);
		enterpriseDepartmentPO = (EnterpriseDepartmentPO) crmEnterpriseDepartmentService.getByExample(enterpriseDepartmentPO);
		
		
		//查询岗位职务信息
		Long positionId = enterpriseEmployeesLinkPO.getPositionId();
		EnterpriseUserPositionPO enterpriseUserPositionPO = new EnterpriseUserPositionPO();
		enterpriseUserPositionPO.setId(positionId);
		enterpriseUserPositionPO = (EnterpriseUserPositionPO) crmEnterpriseUserPositionService.getByExample(enterpriseUserPositionPO);
		
		EnterpriseEmployeesLinkDTO enterpriseEmployeesLinkDTO = BeanConvertor.convertBean(enterpriseEmployeesLinkPO, EnterpriseEmployeesLinkDTO.class);
		if(BeanUtils.isNotEmpty(userPO)){
			enterpriseEmployeesLinkDTO.setUserNo(userPO.getUserNo());//用户编号
		}
		
		if(BeanUtils.isNotEmpty(enterprisePO)){
			enterpriseEmployeesLinkDTO.setEnterpriseName(enterprisePO.geteName());//企业名
		}
		
		if(BeanUtils.isNotEmpty(enterpriseDepartmentPO)){
			enterpriseEmployeesLinkDTO.setDepartmentName(enterpriseDepartmentPO.getdName());//部门名称
		}
		
		if(BeanUtils.isNotEmpty(enterpriseUserPositionPO)){
			enterpriseEmployeesLinkDTO.setPositionName(enterpriseUserPositionPO.getName());//设置职务名
		}
		
		modelAndView.addObject("enterpriseEmployeesLinkDTO", enterpriseEmployeesLinkDTO);
		
		
		return false;
	}

	/**
	 * 订单列表导出
	 */
	@Override
	public List<OrderDTO> orderListDetailsExcelOut(OrderParam orderParam) {
		List<OrderPO> orderPOs = this.findAll(orderParam);//查询订单信息
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		Map<Long,String> userIdWithUserMobilephone = new HashMap<Long, String>();//用户Id:用户手机号
		for (OrderPO orderPO : orderPOs) {
			OrderDTO orderDTO = BeanConvertor.convertBean(orderPO, OrderDTO.class);
			
			Long orderId = orderPO.getId();
			String cashFlowNo = payCanteenCashflowService.queryOrderCashFlowNo(orderId);//通过订单Id获取订单现金流水号
			orderDTO.setCashFlowNo(cashFlowNo);
			
			//获取userid
			Long userId = orderPO.getUserId();
			if(!userIdWithUserMobilephone.containsKey(userId)){
				userIdWithUserMobilephone.put(userId, null);
			}
			
			//格式化支付状态
			Integer status = orderPO.getStatus();
			if(null != status){
				switch (status) {
				case 1:
					orderDTO.setStatusName("未支付 ");
					break;
				case 2:
					orderDTO.setStatusName("已支付 ");
					break;
				case 3:
					orderDTO.setStatusName("已接单");
					break;
				case 4:
					orderDTO.setStatusName("已送达 ");
					break;
				case 5:
					orderDTO.setStatusName("已完成 ");
					break;
				case 6:
					orderDTO.setStatusName("已评价 ");
					break;
				case 7:
					orderDTO.setStatusName("已取消 ");
					break;
				case 8:
					orderDTO.setStatusName("待评价 ");
					break;
				case 9:
					orderDTO.setStatusName("待取餐 ");
					break;
				default:orderDTO.setStatusName("--");
					break;
				}
			}
			
			//支付方式 1,余额，2.支付宝，3.微信
			Integer payModel = orderPO.getPayModel();
			if(null != payModel){
				switch (payModel) {
				case 1:
					orderDTO.setPayModelStr("余额");
					break;
				case 2:
					orderDTO.setPayModelStr("支付宝");
					break;
				case 3:
					orderDTO.setPayModelStr("微信");
					break;
				default:orderDTO.setPayModelStr("--");
					break;
				}
			}
			
			orderDTOs.add(orderDTO);
		}
		
		//查询用户手机号
		Set<Long> userIdSet = userIdWithUserMobilephone.keySet();
		if(CollectionUtils.isNotEmpty(userIdSet)){
			List<UserPO> userPOs = userService.queryByIds(new LinkedList<Long>(userIdSet));
			userIdWithUserMobilephone.clear();
			for (UserPO userPO : userPOs) {
				userIdWithUserMobilephone.put(userPO.getId(), userPO.getMobilephone());
			}
		}
		
		//设置用户手机号
		if(null != userIdWithUserMobilephone && userIdWithUserMobilephone.size() >0){
			for(OrderDTO orderDTO : orderDTOs) {
				Long userId = orderDTO.getUserId();
				if(userIdWithUserMobilephone.containsKey(userId)){
					orderDTO.setUserMobilephone(userIdWithUserMobilephone.get(userId));
				}
			}
		}
		
		return orderDTOs;
	}

	
	/**
	 * 我的财富页面数据展示
	 * @author xu
	 * @param
	 *  param
	 *  userId ：当前登录的业务员id
	 */
	@Override
	public Page<OrderVO> queryPage(OrderParam param, Long userId) {
		CrmBusinessPO businessPO = crmBusinessService.findByUserId(userId);
		List<Long> canteenIds= new ArrayList<>();
		Long businessId = businessPO.getId();
		
		
		//根据食堂名称模糊查询
		if(BeanUtils.isEmpty(param.getCondition())){
			List<CrmCanteenBaseInfoPO> baseInfoPOs = baseInfoService.findByBusinessIds(businessId);
			
			if(CollectionUtils.isNotEmpty(baseInfoPOs)){
				for(int i =0;i<baseInfoPOs.size();i++){
					canteenIds.add(baseInfoPOs.get(i).getCanteenId());
				}
			}
		}
		if(BeanUtils.isNotEmpty(param.getCondition()) && param.getSelectNum().equals("1")) {
			param.setBusinessId(businessId);
			List<CrmCanteenBaseInfoPO> canteenBaseInfoPOs = baseInfoService.findLikeByName(param.getCondition(),businessId);
			if(CollectionUtils.isNotEmpty(canteenBaseInfoPOs) ){
				
				for(CrmCanteenBaseInfoPO cp: canteenBaseInfoPOs){
					if (BeanUtils.isNotEmpty(cp)) {
						canteenIds.add(cp.getCanteenId());
					}
				}
//				param.setCanteenIds(canteenIds);
			}else{
				Page<OrderVO> pageResult = new Page<OrderVO>();
				pageResult.result =null;
				pageResult.setTotalResult(0);
				return pageResult;
			}
		}
		
		param.setCanteenIds(canteenIds);
		ParamMap paramMap = new ParamMap(param);
		
		Page<OrderPO> orderPoList = findPageByParams(OrderPO.class, new Page<OrderPO>(param.getOffset(), param.getLimit()), "OrderMapper.findByCanteenId",paramMap);
		List<OrderVO> rows = new ArrayList<>(); 
		List<OrderPO> orderPOs = orderPoList.result;
		
		int total = 0;
		if (BeanUtils.isNotEmpty(orderPOs)) {
			for (int i = 0; i < orderPOs.size(); i++) {
				OrderPO orderPO = orderPOs.get(i);
				OrderVO orderVO = BeanConvertor.convertBean(orderPO, OrderVO.class);
				if (BeanUtils.isNotEmpty(orderPO)) {
					orderVO.setOrderNo(orderPO.getOrderNo()); 
					orderVO.setRealMoney(orderPO.getRealMoney());   //订单金额
					orderVO.setOrderFlowNo(orderPO.getOrderFlowNo());   // 现金流水号
				
				}
				//根据canteen_base_info表查询食堂名称
				Long canteenId = orderPO.getCanteenId();
				Long orderId = orderPO.getId();        //订单id
//				Long businessId = orderPO.
				CrmCanteenBaseInfoPO canteenBaseInfoPO = baseInfoService.findByCanteenId(canteenId);
				if (BeanUtils.isNotEmpty(canteenBaseInfoPO)) {
					orderVO.setCanteenName(canteenBaseInfoPO.getName()); //获取商家名称(食堂名称)
				}
//				CrmSplitAssignSetPO splitAssignSetPO = splitAssignSetService.findByCanteenId(canteenId);
				CrmSplitAssignSetPO splitAssignSetPO = splitAssignSetService.findByOrderId(orderId); 
				if (BeanUtils.isNotEmpty(splitAssignSetPO)) {
					orderVO.setCreateTime(splitAssignSetPO.getCreaterTime());  //分账时间
					orderVO.setBusinessSplitPercent(splitAssignSetPO.getBusinssSplitPercent());
					//计算每个订单的平台抽成金额
					BigDecimal businessSplitPercent = splitAssignSetPO.getBusinssSplitPercent().divide(new BigDecimal(100));
					//平台抽成比例
					BigDecimal canteenSplitPercent = splitAssignSetPO.getCanteenSplitPercent().divide(new BigDecimal(100));
					BigDecimal money = (orderPO.getRealMoney()).multiply(canteenSplitPercent);  
					BigDecimal realMoney = orderPO.getRealMoney();
					BigDecimal splitMoney = realMoney.subtract(money);     //业务员参与分账的金额
					orderVO.setSplitMoney(splitMoney.multiply(businessSplitPercent));  //业务员分账金额
				}
				
				rows.add(i, orderVO);
				}
		}
		total = orderPoList.getTotalResult();
		Page<OrderVO> pageResult = new Page<>();
		pageResult.result = rows;
		pageResult.setTotalResult(total);
		return pageResult;
	}

	/**
	 * 统计订单量及订单金额 
	 */
	@Override
	public Map<Long, List<Object>> statisticsOrderTotalNumAndOrderTotalMoney(
			OrderParam orderParam) {
		ParamMap paramMap = new ParamMap(orderParam);
		List<Map> canteenIdWithOrderTotalNumAndOrderTotalMoney = commonDao.listByParams("OrderMapper.statisticsOrderTotalNumAndOrderTotalMoney", paramMap );
		if(!(null == canteenIdWithOrderTotalNumAndOrderTotalMoney || canteenIdWithOrderTotalNumAndOrderTotalMoney.size() <= 0)){
			Map<Long, List<Object>> orderTotalNumAndOrderTotalMoney = new HashMap<Long, List<Object>>();
			for (Map map : canteenIdWithOrderTotalNumAndOrderTotalMoney) {
				Long canteenId = (Long) map.get("canteenId");
				Integer orderNum = CommUtil.parseInteger(map.get("orderNum"));
				BigDecimal orderTotalMoney = (BigDecimal) map.get("orderTotalMoney");
				List<Object> orderTotalNumAndOrderTotalMoneyList = new ArrayList<Object>();
				orderTotalNumAndOrderTotalMoneyList.add(0, orderNum);
				orderTotalNumAndOrderTotalMoneyList.add(1, orderTotalMoney);
				orderTotalNumAndOrderTotalMoney.put(canteenId, orderTotalNumAndOrderTotalMoneyList);
			}
			return orderTotalNumAndOrderTotalMoney;
		}
		return null;
	}

}
