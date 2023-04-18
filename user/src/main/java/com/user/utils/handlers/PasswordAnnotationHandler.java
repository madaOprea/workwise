package com.user.utils.handlers;

import com.user.utils.annotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PasswordAnnotationHandler implements ConstraintValidator<PasswordValidator, String> {

    private PasswordValidator validPassword;

    @Override
    public void initialize(PasswordValidator validPassword) {
        this.validPassword = validPassword;
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext arg1) {
        if (password.isEmpty() || password.isBlank())
            return Objects.isNull(password) || validatePassword(password);
        else
            return Objects.nonNull(password) && validatePassword(password);

    }

    private boolean validatePassword(String password) {
        // contains digits
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        // contains at least one upper-case and one lower-case letter
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z]).{1,}$")) {
            return false;
        }
        //  contains at least one special character from the list: [@, #, $, %, ^, &, +, =, !]
        return password.matches("^(?=.*[@#$%^&+=!]).{1,}$");
    }

}
