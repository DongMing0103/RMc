package com.hd.kzscrm.common.util;

//import com.jumore.ucenter.dubbo.service.api.base.dto.ResponseDTO.ResponseCode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.collections.CollectionUtils;

import com.hd.kzscrm.common.exceptions.ValidationException;

/**
 * 参数校验工具类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class ValidationUtils {

    //初始化validator实例
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 对象校验
     * 此方法对加了注解的属性进行校验
     *
     * @param obj 被校验对象
     * @return ValidationResult
     * @history
     */
    public static <T> ValidationResult validateEntity(T obj) {
        return validateEntity(obj, Default.class);
    }

    /**
     * 对象校验
     * 此方法对加入group的参数进行校验
     *
     * @param obj 被校验对象
     * @return ValidationResult
     * @history
     */
    public static <T> ValidationResult validateEntity(T obj, Class<?>... cls) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, cls);
        assembly(result, set);
        return result;
    }

    /**
     * 对象中指定参数校验
     *
     * @param obj          被校验对象
     * @param propertyName 校验参数
     * @return ValidationResult
     * @history
     */
    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        assembly(result, set);
        return result;
    }

    /**
     * 对象校验,抛出校验异常
     * 此方法对加了注解的属性进行校验
     *
     * @param obj 被校验对象
     * @throws ValidationException
     * @history
     */
    public static <T> void validateEntityThrows(T obj) throws ValidationException {
        ValidationResult result = validateEntity(obj, Default.class);
        throwParamException(result);
    }

    /**
     * 对象校验，抛出异常
     * 此方法对加入group的参数进行校验
     *
     * @param obj 被校验对象
     * @return ValidationResult
     * @history
     */
    public static <T> void validateEntityThrows(T obj, Class<?>... cls) throws ValidationException {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, cls);
        assembly(result, set);
        throwParamException(result);
    }

    /**
     * 对象中指定参数校验，抛出异常
     *
     * @param obj          被校验对象
     * @param propertyName 校验参数
     * @return ValidationResult
     * @history
     */
    public static <T> void validatePropertyThrows(T obj, String propertyName) throws ValidationException {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        assembly(result, set);
        throwParamException(result);
    }

    /**
     * 参数组装
     *
     * @param result 返回对象
     * @param set    校验结果
     * @history
     */
    private static <T> void assembly(ValidationResult result, Set<ConstraintViolation<T>> set) {
        if (CollectionUtils.isEmpty(set)) {
            return;
        }
        result.setHasErrors(true);
        Map<String, String> errorMsg = new HashMap<String, String>();
        for (ConstraintViolation<T> cv : set) {
            errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
        }
        result.setErrorMsg(errorMsg);
    }

    /**
     * 抛出参数异常
     *
     * @param result 校验类
     * @throws ValidationException
     * @history
     */
    private static void throwParamException(ValidationResult result) throws ValidationException {
        if (result.isHasErrors()) {
            //// TODO: 2016/9/13  先注释掉，需要调用dubbo服务
            //  throw new ValidationException(ResponseCode.EXCEPTION.name(), result.getMessage());
        }
    }
}
