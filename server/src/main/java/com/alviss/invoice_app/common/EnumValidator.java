package com.alviss.invoice_app.common;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidateEnum, String> {

    private Set<String> allowedValues;

    @Override
    public void initialize(ValidateEnum constraintAnnotation) {
        Class<? extends Enum> enumSelected = constraintAnnotation.targetClassType();
        allowedValues = (Set<String>) EnumSet.allOf(enumSelected).stream().map(e -> ((Enum<? extends Enum<?>>) e).toString())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null || value.trim().isEmpty()) || allowedValues.contains(value.toUpperCase());
    }
}

