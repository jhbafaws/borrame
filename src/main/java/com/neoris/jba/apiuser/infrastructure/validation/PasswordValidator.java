package com.neoris.jba.apiuser.infrastructure.validation;

import com.neoris.jba.apiuser.infrastructure.validation.anotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Value("${regexPassword}")
    private String PASSWORD_REGEX;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
}
