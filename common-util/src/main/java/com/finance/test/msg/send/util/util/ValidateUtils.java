package com.finance.test.msg.send.util.util;


import com.finance.test.msg.send.util.enums.TestBizCode;
import com.finance.test.msg.send.util.exception.ServiceException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 描述：
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

}
