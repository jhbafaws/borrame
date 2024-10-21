package com.neoris.jba.apiuser.infrastructure.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @InjectMocks
    private PasswordValidator passwordValidator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Setting the PASSWORD_REGEX using ReflectionTestUtils
        ReflectionTestUtils.setField(passwordValidator, "PASSWORD_REGEX", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    @Test
    void whenPasswordIsNull_thenValidationFails() {
        // Act
        boolean isValid = passwordValidator.isValid(null, context);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void whenPasswordIsInvalid_thenValidationFails() {
        // Act
        boolean isValid = passwordValidator.isValid("invalid", context);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void whenPasswordIsValid_thenValidationSucceeds() {
        // Act
        boolean isValid = passwordValidator.isValid("Jbravo1@", context);

        // Assert
        assertTrue(isValid);
    }
}
