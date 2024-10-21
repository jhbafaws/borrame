package com.neoris.jba.apiuser.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ValidationExceptionHandlerTest {

    @InjectMocks
    private ValidationExceptionHandler validationExceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleValidationExceptions_ReturnsBadRequestAndErrorMessage() {
        // Arrange
        FieldError fieldError1 = new FieldError("objectName", "name", "must not be blank");
        FieldError fieldError2 = new FieldError("objectName", "email", "must be a well-formed email address");
        List<ObjectError> fieldErrors = Arrays.asList(fieldError1, fieldError2);

        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(fieldErrors);

        // Act
        ResponseEntity<Object> responseEntity = validationExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Realizar un cast del body a Map<String, String>
        @SuppressWarnings("unchecked")
        Map<String, String> responseBody = (Map<String, String>) responseEntity.getBody();
        assertEquals("name must not be blank, email must be a well-formed email address", responseBody.get("mensaje"));
    }
}
