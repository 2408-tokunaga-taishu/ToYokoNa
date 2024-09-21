package com.example.ToYokoNA.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdValidator implements
        ConstraintValidator<ValidId, String> {

    // isValidが呼ばれるための初期化処理
    @Override
    public void initialize(ValidId contactNumber) {
    }

    // 実際のバリデーションロジックを実装。
    @Override
    public boolean isValid(String id,
                           ConstraintValidatorContext cxt) {
        return id != null && id.matches("^[0-9]*$");
    }
}