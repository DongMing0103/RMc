/**
 * 
 */
package com.hd.kzscrm.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;

/**
 * BEAN字段映射注解：在MYBATIS环境下进行查询并注入,该注解需要放在参考字段上方能有效
 * @author 黄霄仪
 * @date 2017年3月25日 下午4:06:20
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanRelevanceField {
	
	/**
	 * tableNames,tableFieldBorders,tableSearchFieldNames关系描述：
	 * 1.这三个表的数组长度应为等长，否则报错.
	 * 2.<code>tableFieldBorders</code>用示描述<code>tableNames</code>表中<code>tableFieldNames</code>的字段索引位置+1的最终位置，例如有<code>tableNames</code>有A和B两个表，两表的<code>tableFieldNames</code>
	 * 有3个字段为field1,field2,field3，相对应的<code>tableFieldBorders</code>的配置应是{"2","3"}来表示每个表的最终字段索引位
	 * 3.tableSearchFieldNames为<code>tableNames</code>某表的where条件字段名称,默认全是id
	 */
	//表名,可以注入多个表，数组长度要与tableFieldBorders,tableSearchFieldNames等长
	DatabaseTableNameEnum[] tableNames();
	//如果tableNames有多个，必须列出tableFieldNames对应的字段边界,为数组索引+1
	String[] tableFieldBorders();
	//where条件字段默认为ID(所注解的BEAN字段映射到<tableName>表的名称)
	String[] tableSearchFieldNames() default {};
	
	/**
	 * tableFieldNames与injectBeanFieldNames关系描述：tableFieldNames为tableNames变量描述的表中字段，查出数据后转换成injectBeanFieldNames对应的字段，方便被该注解注释的BEAN中的字段映射获取值，也就是说
	 * injectBeanFieldNames对应着被该注解对应的实际字段名称
	 */
	//注意：tableFieldNames与injectBeanFieldNames必须等大，并且一一对应，例如表中的字段是name，Bean中的是enterpriseName
	//tableName中的字段名称,查询出来的值注入到所属BEAN的<injectBeanFieldName>字段中
	String[] tableFieldNames();
	//需要被注入的字段
	String[] injectBeanFieldNames();
	
}
