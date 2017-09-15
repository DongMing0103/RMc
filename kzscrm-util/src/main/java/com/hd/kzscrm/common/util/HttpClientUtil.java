package com.hd.kzscrm.common.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * post方式提交表单
	 */
	public static String doPost(String url, Map<String, String> map,
			String Charset) {
		// 创建默认的httpClient实例.
		logger.info("start doPost url :" + url);
		HttpClient httpclient = new DefaultHttpClient();
		UrlEncodedFormEntity uefEntity;
		InputStream in = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 设置超时时间
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
			// 设置数据传输超时时间
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 30000);
			// 创建httppost
			HttpPost httppost = new HttpPost(url);
			// 创建参数队列
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();

			for (Map.Entry<String, String> entry : map.entrySet()) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			uefEntity = new UrlEncodedFormEntity(formparams, Charset);
			httppost.setEntity(uefEntity);
			HttpResponse response;
			response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					in = entity.getContent();
					in = new BufferedInputStream(in);
					Reader r = new InputStreamReader(in, Charset);
					int c;
					while ((c = r.read()) != -1)
						buffer.append((char) c);
					in.close();
				} else {
					return "";
				}
			} else {
				logger.error(url + " 连接失败");
				return "";
			}
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpclient.getConnectionManager().shutdown();
			try {
				if(in!=null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}
		System.out.println(buffer.toString());
		logger.info("start post  result :" + buffer.toString());
		return buffer.toString();

	}

	/**
	 * 发送 get请求
	 */
	public static String doGet(String url, String Charset) {
		logger.info("start get url :" + url);
		HttpClient httpclient = new DefaultHttpClient();
		InputStream in = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 设置超时时间
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
			// 设置数据传输超时时间
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 30000);
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			long startTime = System.currentTimeMillis();

			HttpResponse response = httpclient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					in = entity.getContent();
					in = new BufferedInputStream(in);
					Reader r = new InputStreamReader(in, Charset);
					int c;
					while ((c = r.read()) != -1)
						buffer.append((char) c);
					in.close();
				} else {
					return "";
				}
			} else {
				logger.error(url + " 连接失败");
				return "";
			}
			long endTime = System.currentTimeMillis();
			int seconds = (int) ((endTime-startTime)/1000);
			System.out.println("needTime:="+(seconds));

		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpclient.getConnectionManager().shutdown();
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return "";
			}
		}
		logger.info("start get result :" + buffer.toString());
		System.out.println(buffer.toString());
		return buffer.toString();
	}
}
