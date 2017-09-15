package com.hd.kzscrm.common.operator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hd.kzscrm.common.enums.OperateEnum;

/**
 * 操作许可管理
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Operator {

    /**
     * 功能说明：指定操作的方法，用枚举来表示
     *
     * @return Class<?>
     */
    OperateEnum[] method() default OperateEnum.ALL;
}
