package com.hd.kzscrm.common.annotation;

import java.lang.annotation.*;

/**
 * 我的收藏拦截器注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface FavoriteAnnotation {
  String description()  default "";
}