package com.hd.kzscrm.common.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataInject {
	public DatabaseTableNameEnum[] value();
}
