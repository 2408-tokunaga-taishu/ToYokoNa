package com.example.ToYokoNA.Validation;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.logging.log4j.util.Strings.isBlank;


public class PastValidator implements ConstraintValidator<Past, String> {

    @Override
    public boolean isValid(String limit, ConstraintValidatorContext context) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        if (limit.compareTo(dateFormat.format(new Date())) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}