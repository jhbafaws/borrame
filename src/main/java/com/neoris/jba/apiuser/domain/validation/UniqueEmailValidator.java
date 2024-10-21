package com.neoris.jba.apiuser.domain.validation;

import com.neoris.jba.apiuser.domain.validation.anotation.UniqueEmail;
import com.neoris.jba.apiuser.infrastructure.dto.UserDto;
import com.neoris.jba.apiuser.infrastructure.usecase.IUserCase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final IUserCase iUserCase;

    // Inyección del servicio o caso de uso para verificar el correo electrónico
    public UniqueEmailValidator(IUserCase iUserCase) {
        this.iUserCase = iUserCase;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        // Esta función se puede utilizar para inicializar el validador, si es necesario
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return true; // La validez se maneja por otras anotaciones como @NotBlank
        }
        UserDto existingUser = iUserCase.getByEmailUseCase(email);
        return existingUser == null; // Devuelve true si el correo no existe (válido), false si ya está registrado
    }
}
