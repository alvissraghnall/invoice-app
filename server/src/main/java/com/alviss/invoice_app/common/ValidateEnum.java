package com.alviss.invoice_app.common;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface ValidateEnum {
    String message () default "";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> targetClassType();
}

