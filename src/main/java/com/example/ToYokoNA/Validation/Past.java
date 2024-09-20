package com.example.ToYokoNA.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastValidator.class)
public @interface Past {
    String message() default "無効な日付です";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
