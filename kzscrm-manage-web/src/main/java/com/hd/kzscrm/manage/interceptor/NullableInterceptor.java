/**
 * 
 */
package com.hd.kzscrm.manage.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hd.kzscrm.common.annotation.Nullable;
import com.hd.kzscrm.common.model.RespModel.RespCode;
import com.hd.kzscrm.common.model.portal.PortalRespModel;
import com.hd.kzscrm.common.util.BeanUtils;

/**
 * @author 黄霄仪
 * @date 2017年3月10日 下午3:25:45
 * 
 */
@SuppressWarnings("all")
public class NullableInterceptor extends HandlerInterceptorAdapter {

	public static String getRequestPostStr(HttpServletRequest request)
			throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i,
					contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 默认失败
		PortalRespModel portalRespModel = new PortalRespModel(RespCode.PARAMETER_NULL.getStatusCode(),null);
		String requestMethod = request.getMethod();// 请求方式
		String jsonValue = "";
		//根据GET和POST，给出JSON字符串
		if (requestMethod.equals("GET")) {
			String queryString = request.getQueryString();
			if(BeanUtils.isEmpty(queryString)){
				return true;
			}
			jsonValue = new String(queryString.getBytes(
					"iso-8859-1"), "utf-8").replaceAll("%22", "\"");
			//TODO GET未测试通过
			return true;
		} else {
			jsonValue = getRequestPostStr(request);
		}
		
		
		// 使用@ModelAttribute注解时会使用InvocableHandlerMethod
		if (handler instanceof HandlerMethod) {
			String contentType = request.getContentType();// 请求类型

			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// 被拦截的方法
			Method method = handlerMethod.getMethod();
			// 获取被拦截方法的参数注解
			Annotation[][] parameterAnnotations = method
					.getParameterAnnotations();
			
			//从被拦截的方法中，获取被Nullable注解注释的参数
			Map<Class, Nullable> classNullableMap = new HashMap<>();// 参数类Nullable与注解映射表
			Class<?>[] parameterTypes = method.getParameterTypes();// 输入参数的class文件
			int i = 0;
			// 将输入参数与对应注解相关联
			for (Annotation[] annotations : parameterAnnotations) {
				for (Annotation annotation : annotations) {
					if (annotation instanceof Nullable) {
						classNullableMap.put(parameterTypes[i],
								(Nullable) annotation);
						i++;
					}
				}
			}
			// 迭代输入参数
			Set<Class> inputParamClazzs = classNullableMap.keySet();
			for (Class inputParamClazz : inputParamClazzs) {
				Nullable nullable = classNullableMap.get(inputParamClazz);
				// 如果有Nullable注解的，就根据要求判断是否为空
				if (BeanUtils.isEmpty(nullable)) {
					continue;
				}
				boolean[] values = nullable.value();
				String[] fields = nullable.field();
				String[] fieldDescs = nullable.fieldDesc();
				if(fields.length!=fieldDescs.length){
					throw new RuntimeException(method.toString()+"：注解Nullable的field,fieldDesc必须相等");
				}
				if(values.length>0){
					if(values.length!=fieldDescs.length){
						throw new RuntimeException(method.toString()+"：注解Nullable的values,field,fieldDesc必须相等");
					}
				}
				// if(BeanUtils.isEmpty(fields)||value==true||"{}".equals(sb.toString())){
				// continue;
				// }
				// 输入参数的全路私类名
				String parameterFullName = inputParamClazz.getName();// 参数的全路径类名称
				Class<?> parameterClazz = Class.forName(parameterFullName);// 输入参数的class文件
				ObjectMapper om = new ObjectMapper();
				Object parameterObject = om.readValue(jsonValue,parameterClazz);// 参数实例对象
				JsonNode readTree = om.readTree(om
						.writeValueAsBytes(parameterObject));
				// 如果是字符串，Long,String并且不可为空
				if ((readTree.isTextual() || readTree.isLong() || readTree
						.isBigDecimal()) && !values[0]) {
					if (readTree.isNull()) {
						//解决乱码问题
						response.setCharacterEncoding("UTF-8");
						response.setContentType("application/json; charset=UTF-8");  
						PrintWriter writer = response.getWriter();
						portalRespModel.setDesc(fieldDescs[0]);
						writer.write(om.writeValueAsString(portalRespModel));
						return false;
					}
				} else {
					boolean array = readTree.isArray();
					boolean object = readTree.isObject();
					// 遍历非空
					for (int j=0;j<fields.length;j++) {
						if(values.length>0){
							//允许为空
							if(values[j]==true){
								continue;
							}
						}
						String field=fields[j];
						String[] splitFields = field.split("\\.");
						boolean findNull = this.findNull(readTree, splitFields);
						//为空的返回
						if (!findNull) {
							portalRespModel.setDesc(fieldDescs[j]);
							//解决乱码问题
							response.setCharacterEncoding("UTF-8");
							response.setContentType("application/json; charset=UTF-8");  
							PrintWriter writer = response.getWriter();
							writer.write(om.writeValueAsString(portalRespModel));
							return false;
						}

					}
				}
			}
		}
		return true;
	}

	/**
	 * 遍历Object和Array，查找为空的字段
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月10日 下午8:39:17
	 * @describe 只判断对象和数组，不判断单值类型：8个基本类型与包装类型，还有String
	 * @param jsonNode节点
	 * @param splitFields 节点中的属性
	 */
	public boolean findNull(JsonNode jsonNode, String[] splitFields) {
		JsonNode jsonNodeOne = jsonNode.get(splitFields[0]);
		//默认非空
		boolean findNull = true;
		//单参数时，判断里面的值是否为空，如果是，直接返回
		if (splitFields.length == 1) {
			if (jsonNodeOne == null || jsonNodeOne.isNull()||(jsonNodeOne.isArray()&&!jsonNodeOne.has(0))) {
//				System.out.println(splitFields[0] + "null");
				return false;
			}
			return true;
		}
		//如果为空，返回
		if (jsonNodeOne == null||jsonNodeOne.isNull()) {
//			System.out.println(splitFields[0] + "null");
			return false;
		}
		//如果是LONG或字段串，
//		if (jsonNodeOne.isLong() || jsonNodeOne.isTextual()
//				|| jsonNodeOne.isBigDecimal()) {
//			return false;
//		}
		//判断数组，进行递归判断是否为空
		if (jsonNodeOne.isArray()) {
			for (int i = 0; jsonNodeOne.has(i); i++) {
				String[] splitFieldsTemp = new String[splitFields.length - 1];
				System.arraycopy(splitFields, 1, splitFieldsTemp, 0,
						splitFields.length - 1);
				findNull = findNull(jsonNodeOne.get(i), splitFieldsTemp);
				if (!findNull) {
					return false;
				}
			}
		} 
		//如果是对象，进行递归判断是否为空
		else if (jsonNodeOne.isObject()) {
			//给出对象中的字段，放到splitFieldsTemp中
			String[] splitFieldsTemp = new String[splitFields.length - 1];
			System.arraycopy(splitFields, 1, splitFieldsTemp, 0,
					splitFields.length - 1);
			//递归判断是否为空
			findNull = findNull(jsonNodeOne, splitFieldsTemp);
			if (!findNull) {
				return findNull;
			}
		}
		if (!findNull) {
			return false;
		}
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	}

}
