package com.healthcare.doctor_service.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ValueInListValidator implements ConstraintValidator<ValueInList, String[]> {

    private List<String> allowedValues;

    @Override
    public void initialize(ValueInList constraintAnnotation) {
        allowedValues = Arrays.asList(constraintAnnotation.allowedValues());
    }

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        if (value == null || value.length == 0) {
            return true;
        }
        return Arrays.stream(value).allMatch(allowedValues::contains);
    }
}
