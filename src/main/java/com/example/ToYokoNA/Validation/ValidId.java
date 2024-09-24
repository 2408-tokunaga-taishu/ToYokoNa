package com.example.ToYokoNA.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = IdValidator.class)
// @Targetはアノテーションを適用する場所を示す。今回はメソッドに適用する/フィールドに適用するの2つ。
@Target( { ElementType.PARAMETER, ElementType.FIELD })
// @Retentionはアノテーションを保持する範囲。今回はアノテーションを実行時も保持する。
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidId {
    String message() default "不正なパラメータです";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
