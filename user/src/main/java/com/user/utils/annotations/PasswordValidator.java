package com.user.utils.annotations;

import com.user.utils.handlers.PasswordAnnotationHandler;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordAnnotationHandler.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PasswordValidator {

    String message() default "Invalid password format!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
