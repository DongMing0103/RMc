package com.hd.kzscrm.service.serviceimpl.sms;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hd.kzscrm.common.util.HttpClientUtil;
import com.hd.kzscrm.service.serviceInter.sms.ISmsService;

/**
 * 短信发送业务实现类
* @ClassName: SmsServiceImp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xiongchangjing 
* @date 2017年4月27日 下午7:04:01 
*
 */
@Service
public class SmsServiceImp implements ISmsService{
	//信达优创密码短信发送地址
	@Value("${sendsms_url}")
	private String sendsms_url;
	@Override
	public String sendsms(String mobile, String content,String url, int isLong) {
		if(url==null ||"".equals(url)) url = sendsms_url;
		String result = "";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mobile", mobile);
		paramMap.put("content", content);					
		if(isLong==0){		
			result= HttpClientUtil.doPost(url, paramMap, "gbk");
		}else{
			result= HttpClientUtil.doPost(url, paramMap, "gbk");
		}
		return result;
	}

}
