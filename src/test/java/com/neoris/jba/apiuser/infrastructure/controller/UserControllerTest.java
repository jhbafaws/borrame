package com.neoris.jba.apiuser.infrastructure.controller;

import com.neoris.jba.apiuser.infrastructure.dto.MessageDto;
import com.neoris.jba.apiuser.infrastructure.dto.ResponseDto;
import com.neoris.jba.apiuser.infrastructure.dto.UserDto;
import com.neoris.jba.apiuser.infrastructure.usecase.IUserCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private IUserCase iUserCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId("someUserId");
        responseDto.setCreate(new Date());
        responseDto.setModified(new Date());
        responseDto.setLastLogin(new Date());
        responseDto.setToken("someToken");
        responseDto.setIsActive(true);

        when(iUserCase.addUserUseCase(any(UserDto.class))).thenReturn(responseDto);

        // Act
        ResponseEntity<Object> response = userController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void createUser_InternalServerError() {
        // Arrange
        UserDto userDto = new UserDto();
        String errorMessage = "Internal Server Error";

        doThrow(new RuntimeException(errorMessage)).when(iUserCase).addUserUseCase(any(UserDto.class));

        // Act
        ResponseEntity<Object> response = userController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(new MessageDto(errorMessage), response.getBody());

    }
}