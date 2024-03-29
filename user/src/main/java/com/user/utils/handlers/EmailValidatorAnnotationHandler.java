package com.user.utils.handlers;

import com.user.utils.annotations.EmailValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

import java.util.Objects;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class EmailValidatorAnnotationHandler implements ConstraintValidator<EmailValidator, String> {

    private EmailValidator email;

    @Override
    public void initialize(EmailValidator emailValidator) {
        this.email = emailValidator;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email.isEmpty() || email.isBlank())
            return Objects.isNull(email) || validateEmail(email);
        else
            return Objects.nonNull(email) && validateEmail(email);
    }

    private boolean validateEmail(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

}
