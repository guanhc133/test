package com.finance.test.msg.send.util.util;


import com.finance.test.msg.send.util.enums.TestBizCode;
import com.finance.test.msg.send.util.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.Contracts;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 描述：解析excel（poi）
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/7 ProjectName:test Version:
 */
public class ValidateUtils {
    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static void validateObjects(Object... objects) {
        for (Object obj : objects) {
            validateObject(obj);
        }
    }

    public static void validateObject(Object object) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (violations.size() == 0) return;
        for (ConstraintViolation<Object> violation : violations)
            throw new ServiceException(TestBizCode.BIZ_CODE_400002.getBizCode(), violation.getMessage());
    }

    /**
     * 字符串  验证是否为空字符串
     *
     * @param parameter 请求参数
     * @param message   错误描述
     */
    public static void validateNotBlank(String parameter, String message) {
        if (StringUtils.isBlank(parameter)) {
            throw new ServiceException(TestBizCode.BIZ_CODE_400002.getBizCode(), message);
        }
    }

    /**
     * 单个字符串，指定错误描述 参数验证
     *
     * @param parameter 请求参数
     * @param message   错误描述
     */
    public static void checkNotNull(Object parameter, String message) {
        try {
            Contracts.assertNotNull(parameter, message);
        } catch (IllegalArgumentException e) {
            throw new ServiceException(TestBizCode.BIZ_CODE_400002.getBizCode(), message);
        }
    }

    /**
     * 单个字符串，指定错误描述 参数验证
     *
     * @param parameter 请求参数
     * @param message   错误描述
     */
    public static void checkNotNull(Object parameter, String messageCode, String message) {
        try {
            Contracts.assertNotNull(parameter, message);
        } catch (IllegalArgumentException e) {
            throw new ServiceException(messageCode, message);
        }
    }

    /**
     * 字符串验证非空 参数验证
     *
     * @param parameter 请求参数
     * @param message   错误描述
     */
    public static void validateString(String parameter, String message) {
        if (StringUtils.isBlank(parameter)) {
            throw new ServiceException(TestBizCode.BIZ_CODE_400002.getBizCode(), message);
        }
    }

}
