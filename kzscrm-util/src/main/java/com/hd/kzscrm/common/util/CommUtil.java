package com.hd.kzscrm.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommUtil {
	private static final Logger logger = LoggerFactory.getLogger(CommUtil.class);

	public static String getRandomKey(int length) {
		Random r = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buf.append((char) (0x30 + r.nextInt(74)));
		}
		return buf.toString();
	}

	public static String getRandomCode(int length) {
		Random r = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buf.append((char) (0x30 + r.nextInt(10)));
		}
		return buf.toString();
	}

	public static String getFileName(String fullPath) {
		int nPos = fullPath.lastIndexOf('/');
		if (nPos != -1) {
			return fullPath.substring(nPos + 1);
		} else {
			return fullPath;
		}
	}

	/**
	 * 复制bean同名同类型的属性
	 * 
	 * @param SourceObj
	 * @param targetObj
	 * @return
	 */
	public static boolean copyBeanProp(Object SourceObj, Object targetObj) {
		try {
			BeanUtils.copyProperties(targetObj, SourceObj);
		} catch (IllegalAccessException e) {
			logger.error("e", e);
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			logger.error("e", e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 将hashMap的数据变为bean的值 BeanUtils.getProperty(Object object, String key)
	 * 
	 * @param objMap
	 * @param bean
	 * @return
	 */
	public static Object setMapToBean(HashMap objMap, Object bean) {
		Field[] fs = bean.getClass().getDeclaredFields();
		Method[] ms = bean.getClass().getDeclaredMethods();
		String fieldName = "";
		String methodName = "";
		Object[] keySet = objMap.keySet().toArray();
		Object value = null;
		for (Object key : keySet) {
			value = objMap.get(key.toString());
			for (Field f : fs) {
				fieldName = f.getName();
				if (fieldName.equals(key.toString())) {
					for (Method m : ms) {
						methodName = m.getName();
						if (null != methodName
								&& methodName.startsWith("set")
								&& (methodName.substring(3).toLowerCase().equals(fieldName.toLowerCase()) || methodName.substring(3)
										.toLowerCase().equals(fieldName.substring(2).toLowerCase()))) {
							if (value instanceof Long) {
								for (Method m2 : ms) {
									methodName = m2.getName();
									if (null != methodName && methodName.startsWith("get")
											&& methodName.substring(3).toLowerCase().equals(fieldName.toLowerCase())) {
										if (m2.getReturnType().getName().equals("int")) {
											value = Integer.valueOf(value.toString());
										} else if (m2.getReturnType().getName().equals("short")) {
											value = Short.valueOf(value.toString());
										}
										break;
									} else if (null != methodName && methodName.startsWith("get")
											&& methodName.substring(3).toLowerCase().equals(fieldName.toLowerCase())) {
										if (m2.getReturnType().getName().equals("int")) {
											value = Integer.valueOf(value.toString());
										} else if (m2.getReturnType().getName().equals("short")) {
											value = Short.valueOf(value.toString());
										}
										break;
									}
								}
							}
							Object[] inArgs = new Object[1];
							inArgs[0] = value;
							try {
								m.invoke(bean, inArgs);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
			}
		}
		return bean;
	}

	/**
	 * getAppPath需要一个当前程序使用的Java类的class属性参数，它可以返回打包过的
	 * Java可执行文件（jar，war）所处的系统目录名或非打包Java程序所处的目录
	 *
	 * @return 返回值为该类所在的Java程序运行的目录
	 */
	public static String getAppPath(Class cls) {
		// 检查用户传入的参数是否为空
		if (cls == null)
			throw new IllegalArgumentException("参数不能为空！");
		ClassLoader loader = cls.getClassLoader();
		// 获得类的全名，包括包名
		String clsName = cls.getName() + ".class";
		// 获得传入参数所在的包
		Package pack = cls.getPackage();
		String path = "";
		// 如果不是匿名包，将包名转化为路径
		if (pack != null) {
			String packName = pack.getName();
			// 此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库
			if (packName.startsWith("java.") || packName.startsWith("javax."))
				throw new IllegalArgumentException("不要传送系统类！");
			// 在类的名称中，去掉包名的部分，获得类的文件名
			clsName = clsName.substring(packName.length() + 1);
			// 判定包名是否是简单包名，如果是，则直接将包名转换为路径，
			if (packName.indexOf(".") < 0)
				path = packName + "/";
			else {// 否则按照包名的组成部分，将包名转换为路径
				int start = 0, end = 0;
				end = packName.indexOf(".");
				while (end != -1) {
					path = path + packName.substring(start, end) + "/";
					start = end + 1;
					end = packName.indexOf(".", start);
				}
				path = path + packName.substring(start) + "/";
			}
		}
		// 调用ClassLoader的getResource方法，传入包含路径信息的类文件名
		java.net.URL url = loader.getResource(path + clsName);
		// 从URL对象中获取路径信息
		String realPath = url.getPath();
		// 去掉路径信息中的协议名"file:"
		int pos = realPath.indexOf("file:");
		if (pos > -1)
			realPath = realPath.substring(pos + 5);
		// 去掉路径信息最后包含类文件信息的部分，得到类所在的路径
		pos = realPath.indexOf(path + clsName);
		realPath = realPath.substring(0, pos - 1);
		// 如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名
		if (realPath.endsWith("!"))
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
		/*------------------------------------------------------------ 
		 ClassLoader的getResource方法使用了utf-8对路径信息进行了编码，当路径 
		  中存在中文和空格时，他会对这些字符进行转换，这样，得到的往往不是我们想要 
		  的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的 
		  中文及空格路径 
		-------------------------------------------------------------*/
		try {
			realPath = java.net.URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return realPath;
	}// getAppPath定义结束

	

	/**
	 * 转为整数
	 * 
	 * @param objValue
	 * @return
	 */
	public static int parseInt(Object objValue) {
		if (objValue == null)
			return 0;
		try {
			return Integer.parseInt(objValue.toString());
		} catch (Exception e) {

		}
		return 0;
	}

	public static Integer parseInt(Object objValue, boolean isAD) {
		if (objValue == null || "".equals(objValue)) {
			if (isAD) {
				return -1;
			}
			return 0;
		}
		try {
			return Integer.parseInt(objValue.toString());
		} catch (Exception e) {

		}
		if (isAD) {
			return -1;
		}
		return -1;

	}

	public static Integer parseInteger(Object objValue) {
		if (objValue == null)
			return -1;
		try {
			return Integer.parseInt(objValue.toString());
		} catch (Exception e) {

		}
		return -1;
	}

	public static Integer parseInteger(Object value, boolean isAD) {
		if (value == null || "".equals(value)) {
			if (isAD) {
				return -1;
			}
			return 0;
		}
		try {
			return Integer.parseInt(value.toString());
		} catch (Exception e) {

		}
		if (isAD) {
			return -1;
		}
		return -1;
	}

	public static double parseDoule(Object objValue) {
		if (objValue == null)
			return 0.0;
		try {
			return Double.parseDouble(objValue.toString());
		} catch (Exception e) {
		}
		return 0.0;
	}

	public static Object parseDoule(Object objValue, boolean isAD) {
		if (objValue == null || "".equals(objValue)) {
			if (isAD) {
				return -1;
			}
			return 0;
		}
		try {
			return Double.parseDouble(objValue.toString());
		} catch (Exception e) {
		}
		if (isAD) {
			return -1;
		}
		return 0;
	}

	/**
	 * 
	 * 将对象转为json data
	 * 
	 * @param value
	 * @param <E>
	 * @return
	 */
	public static <E> String offerJson(E value) {
		String text = null;
		try {
			text = JSON.toJSONString(value);
		} catch (Exception e) {
			logger.error("!!!FAILED!! value={}, e={}", e);
			logger.error("!!!FAILED!! value={}, e={}", value, e.getMessage());
		}

		return text;
	}

	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.parseObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<Object> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = (JSONObject) it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * 将json数据转为object
	 * 
	 * 如果不能转换为指定类型则返回null.
	 * 
	 * @param clazz
	 * @param <E>
	 * @return
	 */
	public static <E> E poll2Obj(String data, Class<E> clazz) {
		try {

			E result;
			if (!StringUtils.isEmpty(data)) {
				try {
					result = JSON.parseObject(data, clazz);
				} catch (Exception e) {
					logger.error("json parse {} error, " + e.getMessage(), data, e);
					result = null;
				}
			} else {
				result = null;
			}
			return result;
		} catch (Exception e) {
			logger.error("!!!FAILED!! e={}", e);
		}
		return null;
	}

	protected Object byte2Object(byte[] bytes) {
		if (bytes == null || bytes.length == 0)
			return null;

		try {
			ObjectInputStream inputStream;
			inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object obj = inputStream.readObject();

			return obj;
		} catch (IOException e) {
			logger.error("e", e);
		} catch (ClassNotFoundException e) {
			logger.error("e", e);
		}

		return null;
	}

	// fastjson支持序列化时写入类型信息，从而使得反序列化时不至于类型信息丢失。例如：
	// Color color = Color.RED;
	// String text = JSON.toJSONString(color, SerializerFeature.WriteClassName);
	// System.out.println(text);
	// JSON.toJSONString(o, SerializerFeature.BrowserCompatible);
	// @JSONField(name="ID")
	// ，希望序列化采用之后采用"ID"，而不是"id"，你
	// public int getId() { ... }
	// List<User> users = JSON.parseArray(text, User.class);
	// Map<String, User> userMap = JSON.parseObject(text, new
	// TypeReference<Map<String, User>>() {});
	// 组合类型集合的反序列化
	// Type[] types = new Type[] {Header.class, Body.class};
	// List<Object> list = JSON.parseArray(text, types);
	// Header header = (Header) list.get(0);
	// Body body = (Body) list.get(1);
	//
	// JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS");
	// 把JSON文本反序列化为一个原型接口

	public static Long parseLong(Object objValue) {
		if (objValue == null)
			return 0l;
		try {
			return Long.parseLong(objValue.toString());
		} catch (Exception e) {
			// 批量添加时用点判断
			// return -1L;
		}
		return 0l;
	}

	public static Long parseLongfilter(String settleType) {

		return parseLong(filterIllChar(settleType));
	}

	public static Long parseLong(Object objValue, boolean isAD) {
		if (objValue == null || "".equals(objValue)) {
			if (isAD)
				return -1L;
			return 0l;
		}
		try {
			return Long.parseLong(objValue.toString());
		} catch (Exception e) {
			if (isAD)
				return -1L;
		}
		return 0l;
	}

	public static Long parselong(Object objValue) {
		if (objValue == null)
			return 0l;
		try {
			return Long.parseLong(objValue.toString());
		} catch (Exception e) {

		}
		return -1L;
	}

	public static Integer parseInteger(String strValue) {
		if (strValue == null)
			return 0;
		try {
			return Integer.parseInt(strValue);
		} catch (Exception e) {

		}
		return 0;
	}

	public static Double parseDouble(String strValue) {
		if (strValue == null)
			return 0.0;
		try {
			return Double.parseDouble(strValue);
		} catch (Exception e) {

		}
		return 0.0;
	}

	public static Float parsetFloat(String strValue) {
		try {
			return Float.parseFloat(strValue);
		} catch (Exception e) {

		}
		return 0f;
	}

	public static Boolean parsetBoolean(String strValue) {
		try {
			return Boolean.parseBoolean(strValue);
		} catch (Exception e) {

		}
		return false;
	}

	// public static Date timeStrToDate(String value, boolean isAD) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	/**
	 * 取前面20字符
	 * 
	 * @param content
	 * @return
	 */

	public static String getPreString(String content) {
		if (content == null)
			return null;
		if (content.length() > 20)
			return content.substring(0, 20);
		return content;
	}

	/**
	 * json字符串转化为对像
	 * 
	 * @param jsonStr
	 * @return
	 * @return
	 */
	public static <T extends Object> T tranjsonStrToObject(String jsonStr, Class<T> clazz) {

		return (T) JSONObject.parseObject(jsonStr, clazz);

	}

	/**
	 * 获陬用户的真实ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 将double类型点数据转换成int
	 * 
	 * @param objValue
	 * @return
	 */
	public static int parseDouleToInt(double objValue) {
		int i = (int) objValue;
		return i;
	}

	// 升贴水批量添加判断
	public static Long parseLongDill(Object objValue) {
		if (objValue == null)
			return 0l;
		try {
			return Long.parseLong(objValue.toString());
		} catch (Exception e) {
			// 批量添加时用点判断
			return -1L;
		}
		// return 0l;
	}

	public static boolean parseBoolean(String strValue) {
		boolean isValue = false;
		try {
			isValue = Boolean.parseBoolean(strValue);
		} catch (Exception e) {
		}
		return isValue;
	}

	/**
	 * 取当前时间截,生成文件名
	 * 
	 * @param originalFileName
	 * @return
	 */
	public static String createRandomFileName(String originalFileName) {
		return System.nanoTime() + originalFileName.substring(originalFileName.lastIndexOf("."));
	}



	public static String getFilterParameter(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (value == null)
			return value;
		return filterIllChar(paramName, value);
	}

	/**
	 * 过滤非法字符
	 * 
	 * @param value
	 */
	public static String filterIllChar(String value) {
		try {
			if(value==null)return value;
			value = value.replaceAll(";", "；");
			value = value.replaceAll("&", "&amp;");
			// value= value.replaceAll(" ", "&nbsp;");
			value = value.replaceAll("<", "&lt;");
			value = value.replaceAll(">", "&gt;");
			// value= value.replaceAll("=", "＝");
			value = value.replaceAll("'", "''");
			value = value.replaceAll("\"", "''");
			if (value != null) { 
				value = value.toLowerCase().replaceAll("script", "**script*"); 
				value = value.toLowerCase().replaceAll("iframe", "***iframe*");
				value = value.toLowerCase().replaceAll("src", "***src*");
			}
			return value;

		} catch (Exception x) {
			x.printStackTrace();
		}
		return null;

	}
	
	/**
	 * 过滤非法字符
	 * 
	 * @param paramName
	 * @param value
	 */
	public static String filterIllChar(String paramName, String value) {
		try {

			// if (paramName.toLowerCase().equals("content") &&
			// paramName.toLowerCase().equals("giftdesc")) {
			// } else {
			if(value==null)return null;
			value = value.replaceAll(";", "；");
			value = value.replaceAll("&", "&amp;");
			// value= value.replaceAll(" ", "&nbsp;");
			value = value.replaceAll("<", "&lt;");
			value = value.replaceAll(">", "&gt;");
			// value= value.replaceAll("=", "＝");
			value = value.replaceAll("'", "''");
			value = value.replaceAll("\"", "''");
			if (value != null
					&& (value.toLowerCase().indexOf("script") >= 0 || value.toLowerCase().indexOf("iframe") >= 0 || value.toLowerCase()
							.indexOf("src") >= 0) && !"psw".equals(paramName) && !"account".equals(paramName)) {
				value = value.toLowerCase().replaceAll("script", "**script*");
				if (paramName != null && !paramName.equals("action"))
					value = value.toLowerCase().replaceAll("iframe", "***iframe*");
				value = value.toLowerCase().replaceAll("src", "***src*");
			}
			// }

			return value;

		} catch (Exception x) {
			x.printStackTrace();
		}
		return null;

	}
	
	/**
	 * 判断手机号是否正确
	 */
	public static boolean chargeMobile(String mobile){
		 Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");       
	     Matcher m = p.matcher(mobile);       
		 return m.matches();
	}
	
	/**
	 * 判断是否只有字母和中文
	 */
	public static boolean chargeTextIllChar(String text){
		Pattern p = Pattern.compile("[(a-zA-Z0-9\\u4e00-\\u9fa5)]+");       
	    Matcher m = p.matcher(text);       
		return m.matches();
	}
	
	/**
	 * Long类型为空，默认为0
	 * @return
	 */
    public static long longNullToZero(Long val)
    {
    	if(val == null)
    	{
    		return 0L;
    	}
    	else
    	{
    		return val.longValue();
    	}
    }	
    
	/**
	 * Long类型为空，默认为0
	 * @return
	 */
    public static int integerNullToZero(Integer val)
    {
    	if(val == null)
    	{
    		return 0;
    	}
    	else
    	{
    		return val.intValue();
    	}
    }
    
	public static Long stringToLong(String str) {
		if (str == null)
			return null;
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			// 批量添加时用点判断
			// return -1L;
		}
		return -1L;
	}
	
	public static <T> List<T> listRemoveNull(List<T> list){
		
		if(list == null || list.size() <= 0){
			return list;
		}
		else{
		    Vector<Object> col = new Vector<Object>();
		    col.add(null);
	    	list.removeAll(col);
	    	return list;
		}
		
	}
	
    public static void main(String[] args) {
//    	System.out.println(chargeMobile("15222222222"));
//    	System.out.println(chargeTextIllChar("pp中国*"));

//		System.out.println(subName("汽配用品/汽车配件/汽车配件/滤清器"));
		System.out.println(subName("/车配件/滤清器"));
	}

	/**
	 * 名称截取(不严谨的统计方法)
	 * @param name 需要截取的字符串
	 * @param split 字符串中的分隔符
	 * @param length 从后往前截取的长度
	 * @return
     */
    public static String subName(String name, String split, int length) {
		String subName = name;
		if (!StringUtils.isBlank(subName) && subName.length() > length) {
			subName = subName.substring(subName.length() - length, subName.length());
			int index = subName.indexOf(split);
			if (index > 0) {
				//截取到分隔符
				subName = subName.substring(index);
			}
		}

		return subName;
	}

	/**
	 * 名称截取，默认末尾往前截取15位，分隔符为/
	 * @param name
	 * @return
	 */
	public static String subName(String name) {
		return subName(name, "/", 15);
	}

	/**
	 * @param num     目标数值的最大值(不包含num)---[0,num)
	 * @param figures 位数
	 * @return 返回0到num之间figures位的随机数(前面补零)
	 * 注：不支持位数截取
	 */
	public static String getRandomNum(int num, int figures) {

		if (num < 0)
			return null;

		// 生成 [0,num) 之间的随机数(整数)
		Random random = new Random();
		String ret = String.valueOf(random.nextInt(num));

		// 位数不够的场合，前面补零
		return getNumByFigures(ret,figures);
	}

	/**
	 *
	 * @param num 待转换数值(字符型)
	 * @param figures 位数
	 * @return 返回figures位的字符串(数字前面补零)
	 */
	public static String getNumByFigures(String num, int figures) {

		if (org.springframework.util.StringUtils.isEmpty(num) || figures < 1)
			return null;

		// 位数不够的场合，前面补零
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < figures - num.length(); i++) {
			buf.append("0");
		}

		return buf.toString() + num;

	}

	/**
	 * Long 装换为string
	 * @param id
	 * @return
     */
	public static String convertLongToString(Long id) {
		if (id == null) {
			return null;
		}

		return String.valueOf(id);
	}
	/**
	 * 日志参数
	 * 
	 * @param request
	 */
	public static void showRequests(HttpServletRequest request) {
		Enumeration enu = request.getParameterNames();
		logger.info("请求参数输出：reuqest start ========================================");
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			logger.info("request name:" + name + " value: " + request.getParameter(name));
		}
		logger.info("reuqest end ========================================");
	}
}
