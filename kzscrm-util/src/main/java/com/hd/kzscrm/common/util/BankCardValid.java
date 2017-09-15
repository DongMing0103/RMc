package com.hd.kzscrm.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.hfepay.utils.Cipher3DES;
import com.hfepay.utils.HttpRequestClient;
import com.hfepay.utils.RsaSignCoder;

/**
 * 银行卡校验
* @ClassName: BankCardValid 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xiongchangjing 
* @date 2017年4月15日 下午3:15:14 
*
 */
public class BankCardValid {

	//1.商户签名私钥
	private static String signPrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAjz9tTFYGXma+wpYzhASUpE3fA/VkbzHpOIVSvl8Ca1T+vUQfoYAm+ndiKSv2esWsWP2+RnR4BiXuIaQt/SO/1QIDAQABAkBCClXTaZ2KDAhKIYEijhIRJa15X7qr4PDKk/yvs5Gj9VykHENcePA19Xc3cGks3T41mK1WxGJB7WVLUQYOWxiJAiEAybs2RO2Rjab8nV2c3G2xLc+dsSepYczyzCemTk/Y128CIQC1yJVSyppbCu/5CAZTvZ4ixKCJ+0b8WDalp/0Dyyza+wIgf1LeCds4BwPiZSB0pNvG/HcRvIKf8c59YEWWPc8/uZkCIAJTwg2gSF+YAUB36NTTnZ2mVMPPcrYyTujKAMQ2qqlXAiEAjvdNGgz2Z2S9fTFM6fo1RdcIfBgBYv1pdc7oFNweqkg=";
	
	//2.固定的平台签名公钥
	private static String signPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJJY/kusrKic6XAhHMakIX06nEnMnvceRV5m8dLIKQcz0RNkDXiEQ/EIV0hZQNQlTIyB5f6OzQeDjJlDUGgkyD8CAwEAAQ==";
	
	public static String valid(String realName,String idCard,String bankCard){
		
		/**接口参数(注：正式接口参数在这里改)**/
		//3.商户编号，即用户编号,唯一编号
		String userCode="HFJK20170415111043";
		
		//4.应用编号,唯一编号
		String sysCode="HFJKAPP20170415111303";
		
		//请求原因
		String qryReason="提现绑定银行卡";
		
		//5.平台提供的加密秘钥appkey
		String appkey = "wpj9QCba+zg7dlIy6rC8ZIWd";
		
		//用户自己生成的 随机8位数字或者字母
		String vector = "12345678";  
		
		//6.请求接口URL，生产地址删除test.
//	    String url="http://test.api.hfdatas.com/superapi/super/auth/smrz2";//银行卡二要素
	    String url="http://api.hfdatas.com/superapi/super/auth/smrz3";//银行卡三要素
	    //	    String url="http://test.api.hfdatas.com/superapi/super/auth/smrz4";//银行卡四要素

		//验证参数	
		/*String  realName= "熊长景";//姓名
		String  idCard= "362202xxxxxx7657";//身份证号码
		String  bankCard= "6221661008996877";//银行卡号码
*/		//String  mobile= "18267117230";//手机号码

		
		//获取日期
		SimpleDateFormat datasf=new SimpleDateFormat("yyyyMMdd");
		String qryDate = datasf.format(new Date());  
		
		//获取时间
		SimpleDateFormat timesf=new SimpleDateFormat("HHmmss");
		String qryTime = timesf.format(new Date());
		
		//生成商户批次号：唯一，不超过20位（14位时间戳 + 6位随机数）
	    String qryBatchNo;  
	    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");  
	    String temp = sf.format(new Date());  
	    int random=(int) (Math.random()*10000);  
	    qryBatchNo=temp+random;
	    System.out.println("商户批次号："+qryBatchNo);
	    
	    /** 生成请求报文**/
		//组装请求报文
		JSONObject headerJson = new JSONObject();
		JSONObject conditionJson = new JSONObject();
		headerJson.put("qryBatchNo", qryBatchNo);//验证批次号  用户生成的唯一编号
		headerJson.put("userCode", userCode);//商户编号，即用户编号
		headerJson.put("sysCode", sysCode);//应用编号
		headerJson.put("qryReason", qryReason);//原因
		headerJson.put("qryDate", qryDate);//格式：yyyyMMdd
		headerJson.put("qryTime", qryTime);//格式：hhmmss
		headerJson.put("version", "2.0");//版本号
		
		conditionJson.put("realName", realName);//姓名
		conditionJson.put("idCard", idCard);//身份证号码
		conditionJson.put("bankCard", bankCard);//银行卡号码
		//conditionJson.put("mobile", mobile);//手机号码号码
		
		JSONObject allJson = new JSONObject();
		allJson.put("header", headerJson);
		allJson.put("condition", conditionJson);
		String data = allJson.toString();
		System.out.println("请求报文："+data);	

		//加密请求报文
		String encrData = null;
		try {
			encrData = Cipher3DES.encrypt(data, appkey, vector);
		} catch (Exception e) {
				e.printStackTrace();
		}
		System.out.println("加密报文："+encrData);
		
		//对请求报文进行签名
		String signature = null;
		try {
			signature = RsaSignCoder.sign(encrData, signPrivateKey);
		} catch (Exception e) {
				e.printStackTrace();
		} 
		System.out.println("签名值："+signature);
		
		/** 组装请求参数 调用实名认证接口**/
		//请求API对应的接口
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("condition", encrData));
		parameters.add(new BasicNameValuePair("userCode", userCode));
		parameters.add(new BasicNameValuePair("signature", signature));
		parameters.add(new BasicNameValuePair("vector", vector));
		String reslult = null;
		try {
			reslult = HttpRequestClient.invoke_http(url, parameters, "UTF-8");
		} catch (Exception e) {
				e.printStackTrace();
		}
		System.out.println("返回报文："+reslult);
		
		/** 解析平台返回报文**/
		//获取接口返回报文
		Map<String, String> resultMap = new HashMap<String, String>();
		
	    //将json字符串转换成jsonObject  
		JSONObject requestJson = JSONObject.parseObject(reslult);
		Iterator<String> it = requestJson.keySet().iterator(); 
		
        //遍历jsonObject数据，添加到Map对象  
        while (it.hasNext()){  
           String key = String.valueOf(it.next());  
           String value = String.valueOf(requestJson.get(key));  
           resultMap.put(key, value);  
        }  
        String sign = resultMap.get("signature");
        String datas = resultMap.get("contents");
		
		/** 验签及解密报文**/
		//验证加密内容报文的签名
		boolean isTrue = false;
		try {
			isTrue = RsaSignCoder.verify(datas, signPublicKey, sign);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String str = null;
		//确认解签是否通过  返回true则表示验证通过 下一步进行解密报文体加密数据（签名主要用于验证报文数据是否被篡改）
		if(isTrue){
			//验签通过则进行解密返回的加密报文
			
			try {
				str = Cipher3DES.decrypt(datas, appkey, vector);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("解密结果："+str);
		}
		return str;

	}   
	
	
	public static void main(String[] args) {
		try {
			//BankCardValid.valid();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
