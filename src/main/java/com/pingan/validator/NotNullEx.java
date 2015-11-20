package com.pingan.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 增加验证用的validation，可以填写字段名称，不同字段的同类提示便于使用
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { NotNullExValidator.class })
// java用@interface Annotation{ } 定义一个注解 @Annotation，一个注解是一个类。
public @interface NotNullEx {
    // 增加field字段，传递字段名称
    String field() default "";

    String message() default "can't be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
