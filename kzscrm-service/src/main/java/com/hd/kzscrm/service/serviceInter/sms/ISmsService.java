package com.hd.kzscrm.service.serviceInter.sms;

/**
 * 短信发送接口
* @ClassName: ISmsService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xiongchangjing 
* @date 2017年4月27日 下午6:58:51 
*
 */
public interface ISmsService {
	/**
	 * 发送短信  短信内容最多70个字
	 * @param mobile  接收号码  同时发送给多个号码时,号码之间用英文半角逗号分隔(,)如:13972827282,13072827282,02185418874  GET　 方式每次最多可以提交50条号码   POST方式每次最多可以提交1000条号码
	 * @param content 短信内容
	 * @param isLong 是否长短信  0 否 1是    长短信是营销类短信
	 * @return
	 */
	public String sendsms(String mobile,String content,String url,int isLong);
	
}
