package com.example.ToYokoNA.Validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class PastValidator implements ConstraintValidator<Past, String> {

    @Override
    public boolean isValid(String limit, ConstraintValidatorContext context) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (sdFormat.parse(limit).before(new Date())){
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
