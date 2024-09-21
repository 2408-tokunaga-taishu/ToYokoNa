package com.example.ToYokoNA.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdValidator implements
        ConstraintValidator<ValidId, String> {

    @Override
    public void initialize(ValidId contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("^[0-9]*$");
    }

}