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

class EmailValidatorTest {

    @InjectMocks
    private EmailValidator emailValidator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Setting the EMAIL_REGEX using ReflectionTestUtils
        ReflectionTestUtils.setField(emailValidator, "EMAIL_REGEX", "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    @Test
    void whenEmailIsNull_thenValidationFails() {
        // Act
        boolean isValid = emailValidator.isValid(null, context);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void whenEmailIsInvalid_thenValidationFails() {
        // Act
        boolean isValid = emailValidator.isValid("invalid-email", context);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void whenEmailIsValid_thenValidationSucceeds() {
        // Act
        boolean isValid = emailValidator.isValid("jbravoe@example.com", context);

        // Assert
        assertTrue(isValid);
    }
}
