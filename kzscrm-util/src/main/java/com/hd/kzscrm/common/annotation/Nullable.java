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
 * @date 2017年3月10日 下午3:15:58
 * 判断传入参数是否为空
 * @see com.hd.kzscrm.portal.common.interceptor.NullableInterceptor 在拦截器中实现该接口，目前只适用于Controller，service层dubbo内部已实现
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
public @interface Nullable {
	public boolean[] value() default{};//默认可为空
	/**
	 * @describe 指定判断的字段,非指定只判断当前的对象是否为空,指定后表示当前对象（除基本类型、字符串,因为它们不存在内部字段）中的字段判断是否为空
	 * @author 黄霄仪
	 * @date 2017年3月10日 下午3:25:11
	 */
	public String[] field() default {};
	public String[] fieldDesc() default {};//字段为空时报错提示
}
