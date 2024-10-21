package com.neoris.jba.apiuser.infrastructure.validation;

import com.neoris.jba.apiuser.infrastructure.validation.anotations.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Value("${regexEmail}")
    private String EMAIL_REGEX;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
