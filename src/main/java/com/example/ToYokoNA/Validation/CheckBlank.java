package com.example.ToYokoNA.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={CheckBlankValidator.class})
public @interface CheckBlank {
    String message() default "タスクを入力してください";
    //特定のバリデーショングループがカスタマイズできるような設定
    Class<?>[] groups() default {};
    //チェック対象のオブジェクトになんらかのメタ情報を与えるためだけの宣言
    Class<? extends Payload>[] payload() default {};
}
