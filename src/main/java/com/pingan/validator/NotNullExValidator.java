package com.pingan.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullExValidator implements ConstraintValidator<NotNullEx, Object> {

    @Override
    public void initialize(NotNullEx annotation) {
    }

    @Override
    public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null) {
            return false;
        }
        // 字符串还需要检验“”
        if (str instanceof String) {
            return StringUtils.isNotBlank((String)str);
        }
        return true;
    }
}