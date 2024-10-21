package com.neoris.jba.apiuser.domain.validation;

import com.neoris.jba.apiuser.infrastructure.dto.UserDto;
import com.neoris.jba.apiuser.infrastructure.usecase.IUserCase;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UniqueEmailValidatorTest {

    @Mock
    private IUserCase iUserCase;

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private UniqueEmailValidator uniqueEmailValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenEmailIsNull_thenValidationSucceeds() {
        // Act
        boolean isValid = uniqueEmailValidator.isValid(null, context);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void whenEmailIsBlank_thenValidationSucceeds() {
        // Act
        boolean isValid = uniqueEmailValidator.isValid("", context);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void whenEmailAlreadyExists_thenValidationFails() {
        // Arrange
        UserDto existingUser = new UserDto();
        when(iUserCase.getByEmailUseCase(anyString())).thenReturn(existingUser);

        // Act
        boolean isValid = uniqueEmailValidator.isValid("existing@example.com", context);

        // Assert
        assertFalse(isValid);
        verify(iUserCase, times(1)).getByEmailUseCase("existing@example.com");
    }

    @Test
    void whenEmailDoesNotExist_thenValidationSucceeds() {
        // Arrange
        when(iUserCase.getByEmailUseCase(anyString())).thenReturn(null);

        // Act
        boolean isValid = uniqueEmailValidator.isValid("new@example.com", context);

        // Assert
        assertTrue(isValid);
        verify(iUserCase, times(1)).getByEmailUseCase("new@example.com");
    }
}
