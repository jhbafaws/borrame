package com.neoris.jba.apiuser.domain.validation.anotation;

import com.neoris.jba.apiuser.domain.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Este correo electrónico ya está registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
