/**
 * 
 */
package com.hd.kzscrm.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author 黄霄仪 前端传入时
 * @date 2017年3月15日 下午9:47:25
 * 
 */
public class MyDateJsonDeserializerAndroid extends JsonDeserializer<Date> {
	/**
	 * json日期反序列化,安卓需要的格式：yyyy-MM-dd
	 */
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = jp.getValueAsString();
		if(BeanUtils.isNotEmpty(value)){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return dateFormat.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
