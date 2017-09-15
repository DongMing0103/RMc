/**
 * 
 */
package com.hd.kzscrm.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.OrderFlowNum;
import com.hd.kzscrm.common.model.portal.PortalRespModel;
import com.hd.kzscrm.common.model.portal.PortalRespModel.RespCode;
import com.hd.wolverine.cache.CacheServiceImpl;
import com.hd.wolverine.cache.WolverineJedisCluster;

/**
 * @author 黄霄仪
 * @date 2017年3月13日 上午11:13:21
 * 
 */
public class ServiceUtil {
	// 用户信息放入session,"session_user"应该定义成常量
	static CacheServiceImpl cacheService = new CacheServiceImpl();


	/**
	 * 据KEY生成全局ID 
	 * @param keyIn table名称
	 * @return
	 */
	public static Long genNextIDValue(DatabaseTableNameEnum databaseTableName) {
		return cacheService.genNextIDValue("KZS", databaseTableName.name());

	}
	
	/**
	 * 自定义消息
	 * @author 黄霄仪
	 * @param respCode 响应状态
	 * @date 2017年3月7日 下午1:59:54
	 */
	public static PortalRespModel getPortalRespModel(RespCode respCode,Object rows,String customMessage){
		PortalRespModel portalRespModel=new PortalRespModel();
		portalRespModel.setDesc(customMessage);
		portalRespModel.setStatusCode(respCode.getStatusCode());
		portalRespModel.setRows(rows);
		return portalRespModel;
	}
	
	/**
	 * 获取基本PortalRespModel
	 * @author 黄霄仪
	 * @param respCode 响应状态
	 * @date 2017年3月7日 下午1:59:54
	 */
	public static PortalRespModel getPortalRespModel(RespCode respCode,Object rows){
		PortalRespModel portalRespModel=new PortalRespModel();
		portalRespModel.setDesc(respCode.getDesc());
		portalRespModel.setStatusCode(respCode.getStatusCode());
		portalRespModel.setRows(rows);
		return portalRespModel;
	}
	/**
	 * 自定义单据前缀流水号拼接
	 * @author 黄霄仪
	 * @date 2017年4月10日 下午7:11:47
	 * @param orderFlowNum 流水单项目
	 * @param prefix 单据前缀
	 * @param prefixPad 前缀补足字符
	 * @param prefixPadSize 被补足的总字符数
	 */
	public static String getOrderFlowNum(OrderFlowNum orderFlowNum,String prefix,String prefixPad,int prefixPadSize){
		Long incValue = cacheService.genNextIDValue("orderFlowNum", orderFlowNum.name());
		return prefix+StringUtils.leftPad(incValue.toString(), prefixPadSize, prefixPad);
	}
	/**
	 * 加上时间的单据流水号
	 * @author 黄霄仪
	 * @date 2017年4月10日 下午7:17:00
	 */
	public static String getOrderFlowNumRule(OrderFlowNum orderFlowNum,String prefixPad,int prefixPadSize){
		return getOrderFlowNum(orderFlowNum,DateUtils.dateToString(new Date(),"yyyyMMddHHmmss"),prefixPad,prefixPadSize); 
	}
	/**
	 * 根据单据类型，生成单据号
	 * @author 黄霄仪
	 * @date 2017年4月10日 下午7:17:00
	 * @param padSize 填充大小
	 * @param orderFlowNum 填充类型
	 */
	public static synchronized String getOrderFlowNumByOrderFlowNum(OrderFlowNum orderFlowNum,int padSize){
		WolverineJedisCluster wolverineJedisCluster=AppUtil.getBean(WolverineJedisCluster.class);
		String currentDate = DateUtils.dateToString(new Date(),"yyyyMMdd");//当前时间的年月日
		return currentDate+StringUtils.leftPad(wolverineJedisCluster.incr(orderFlowNum.name()+currentDate).toString(), padSize, "0");
	}
	/**
	 * 提现批次号,用于支付宝提现功能
	 * @author 黄霄仪
	 * @date 2017年4月24日 上午9:48:15
	 * 格式：当天日期[8位]+序列号[3至16位]，如：201008010000001，最高支持除年月日外的16位序列号
	 */
	public static synchronized String alipayBatchPayNo(){
		WolverineJedisCluster wolverineJedisCluster=AppUtil.getBean(WolverineJedisCluster.class);
		String currentDate = DateUtils.dateToString(new Date(),"yyyyMMdd");//当前时间的年月日
		String key = "alipayBatchPay"+currentDate;
		String alipayBatchPayNo = currentDate+StringUtils.leftPad(wolverineJedisCluster.incr(key).toString(), 16, "0");
		wolverineJedisCluster.expire(key, 32*60*60);//32小时后过期
		return alipayBatchPayNo;
	}
	/**
	 * 提现流水号,用于支付宝提现功能的客户提现流水号
	 * @author 黄霄仪
	 * @date 2017年4月24日 上午9:48:15
	 * 格式：当天日期[8位]+序列号[3至16位]，如：201008010000001，最高支持除年月日外的16位序列号
	 */
	public static synchronized String alipayBatchPayFlowNo(){
		WolverineJedisCluster wolverineJedisCluster=AppUtil.getBean(WolverineJedisCluster.class);
		String currentDate = DateUtils.dateToString(new Date(),"yyyyMMdd");//当前时间的年月日
		String key = "alipayBatchPayFlowNo"+currentDate;
		String alipayBatchPayNo = currentDate+StringUtils.leftPad(wolverineJedisCluster.incr(key).toString(), 16, "0");
		wolverineJedisCluster.expire(key, 32*60*60);//32小时后过期
		return alipayBatchPayNo;
	}
	
	/**
	 * 支付宝回调参数封装
	 * @author 黄霄仪
	 * @date 2017年4月11日 下午8:25:41
	 */
	public static Map<String,String> alipayParam(HttpServletRequest request){
		Map<String, String> params = new HashMap<>();
		Map requestParams = request.getParameterMap();
		System.out.println(requestParams);
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		return params;
	}
}
