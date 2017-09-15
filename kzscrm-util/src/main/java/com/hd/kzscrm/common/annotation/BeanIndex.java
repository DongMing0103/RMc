/**
 * 
 */
package com.hd.kzscrm.common.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author 黄霄仪
 * @date 2017年3月29日 下午9:26:40
 * Bean字段的索引位置,不能有重复，从0开起始
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanIndex {
	int value();//字段索引
}
