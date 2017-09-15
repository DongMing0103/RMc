package com.hd.kzscrm.common.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mybatis.spring.SqlSessionTemplate;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.util.annotation.DataInject;

/**
 * Bean工具类
 * 
 * @author 黄霄仪
 *
 */
public class BeanUtils {

	// 判断字符，集合，Map为空
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			Object[] tempO = (Object[]) o;
			if (tempO.length == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	// 判断字符，集合，Map不为空
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	// 判断List不为空，并且只有一个
	public static boolean isNotEmptyUniqueList(List list) {
		if (isNotEmpty(list) && list.size() == 1) {
			return true;
		}
		return false;
	}
	// 批量判断字符，集合，Map为空
	public static boolean isEmptyAnd(Object... objects) {
		for (Object object : objects) {
			if(!isEmpty(object)){
				return false;
			}
		}
		return true;
	}
	// 批量判断字符，集合，Map不为空
	public static boolean isNotEmptyAnd(Object... objects) {
		for (Object object : objects) {
			if(isEmpty(object)){
				return false;
			}
		}
		return true;
	}
	/**
	 * 判断表达式中是否有一个为true的
	 * @author 黄霄仪
	 * @date 2017年6月2日 下午1:55:30
	 */
	public static boolean isOr(Boolean ... bools) {
		for (Boolean bool : bools) {
			if(bool){
				return true;
			}
		}
		return false;
	}
	/**
	 * 如果表达式都为true才返回true
	 * @author 黄霄仪
	 * @date 2017年6月2日 下午1:56:01
	 */
	public static boolean isAnd(Boolean ... bools) {
		
		for (Boolean bool : bools) {
			if(!bool){
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param bean
	 *            实体BEAN
	 * @param fieldName
	 *            包含有DataInject注解的字段名
	 * @param condition
	 *            查询条件
	 * @param tableAndModelFieldMapping
	 *            数据库表字段与实体BEAN的映射字段
	 * @example BeanUtils.databaseInjectField(userDTO,"userName",new
	 *          String[]{"del_flag=1","id=1"},new
	 *          String[]{"del_flag delFlag","id,name,mobilephone,address"});
	 */
	@SuppressWarnings("all")
	public static <T> void databaseInjectField(T bean, String fieldName,
			String[] condition, String[] tableAndModelFieldMapping) {
		SqlSessionTemplate sqlSessionTemplate = AppUtil
				.getBean(SqlSessionTemplate.class);
		StringBuilder stringBuilder = new StringBuilder();
		Class clazz = bean.getClass();
		try {
			Field field = clazz.getDeclaredField(fieldName);
			if (field.isAnnotationPresent(DataInject.class)) {
				DataInject dataInject = field.getAnnotation(DataInject.class);
				// 表名称数组
				DatabaseTableNameEnum[] tables = dataInject.value();
				if (tables.length != condition.length
						|| tables.length != tableAndModelFieldMapping.length) {
					throw new RuntimeException("数量不匹配");
				}
				for (int i = 0; i < condition.length; i++) {
					if (BeanUtils.isEmpty(condition[i])) {
						throw new RuntimeException(tables[i] + "条件不能为空");
					}
					if (BeanUtils.isEmpty(tableAndModelFieldMapping[i])) {
						throw new RuntimeException(tables[i] + "表字段映射不能为空");
					}

					// 拼接SQL语句
					stringBuilder.append("select ")
							.append(tableAndModelFieldMapping[i])
							.append(" from ").append(tables[i])
							.append(" where ").append(condition[i]).append(";");

					// SQL语句
					Map<String, Object> sqlMap = new HashMap<>();
					sqlMap.put("sql", stringBuilder.toString());

					// 获取数据
					Map<String, Object> fieldValueMap = new HashMap<>();
					fieldValueMap = (HashMap<String, Object>) sqlSessionTemplate
							.selectOne("CommonMapper.dynamicSql", sqlMap);// 查询获取数据
					stringBuilder.delete(0, stringBuilder.length());// 清空语句

					if (BeanUtils.isNotEmpty(fieldValueMap)) {
						Set<Entry<String, Object>> entrySet = fieldValueMap
								.entrySet();
						for (Entry<String, Object> entry : entrySet) {
							String beanField = entry.getKey();
							System.out.println(beanField);
							Object beanValue = entry.getValue();
							System.out.println(beanValue);
							Field declaredBeanField = clazz
									.getDeclaredField(beanField);
							declaredBeanField.setAccessible(true);
							declaredBeanField.set(bean, beanValue);
						}
					}

				}
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
