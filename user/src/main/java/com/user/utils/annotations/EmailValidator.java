package com.user.utils.annotations;

import com.user.utils.handlers.EmailValidatorAnnotationHandler;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EmailValidatorAnnotationHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailValidator {

    String message() default "Invalid email format!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
