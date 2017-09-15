/**
 * 
 */
package com.hd.kzscrm.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author 黄霄仪 后台推出
 * @date 2017年3月15日 下午9:47:12
 * 
 */
public class MyDateJsonSerializerAndroid extends JsonSerializer<Date>{

	/**
	 * json序列化，安卓需要的格式：yyyy-MM-dd
	 */
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateStr = dateFormat.format(value);  
	        jgen.writeString(dateStr);  
		
	}

}
