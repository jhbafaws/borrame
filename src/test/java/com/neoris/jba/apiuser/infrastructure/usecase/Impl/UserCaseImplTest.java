package com.neoris.jba.apiuser.infrastructure.usecase.Impl;

import com.neoris.jba.apiuser.domain.entity.Phone;
import com.neoris.jba.apiuser.domain.entity.User;
import com.neoris.jba.apiuser.domain.service.IUser;
import com.neoris.jba.apiuser.infrastructure.dto.PhoneDto;
import com.neoris.jba.apiuser.infrastructure.dto.ResponseDto;
import com.neoris.jba.apiuser.infrastructure.dto.UserDto;
import com.neoris.jba.apiuser.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserCaseImplTest {

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private IUser iUser;

    @InjectMocks
    private UserCaseImpl userCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUserUseCase_Success() {
        // Arrange
        UserDto userDto = UserDto.builder()
                .name("Jorge Bravo")
                .email("jbravo@example.com")
                .password("Jbravo1@")
                .isActive(true)
                .phones(Collections.singletonList(new PhoneDto("123456789", "1", "1")))
                .build();

        String token = "generatedToken";
        when(jwtUtils.generateAccessToken(anyString())).thenReturn(token);

        User savedUser = User.builder()
                .id("someUserId")
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .isActive(userDto.getIsActive())
                .create(Timestamp.from(Instant.now()))
                .modified(Timestamp.from(Instant.now()))
                .lastLogin(Timestamp.from(Instant.now()))
                .token(token)
                .phones(Collections.singletonList(
                        Phone.builder()
                                .number("123456789")
                                .cityCode("1")
                                .countryCode("1")
                                .build()
                ))
                .build();

        when(iUser.saveUser(any(User.class))).thenReturn(savedUser);

        // Act
        ResponseDto responseDto = userCase.addUserUseCase(userDto);

        // Assert
        assertEquals("someUserId", responseDto.getId());
        assertEquals(token, responseDto.getToken());
        assertEquals(true, responseDto.getIsActive());
        verify(iUser, times(1)).saveUser(any(User.class));
        verify(jwtUtils, times(1)).generateAccessToken(anyString());
    }

    @Test
    void getByEmailUseCase_UserFound() {
        // Arrange
        String email = "jbravo@example.com";
        User user = User.builder()
                .id("someUserId")
                .name("Jorge Bravo")
                .email(email)
                .password("Jbravo1@")
                .isActive(true)
                .build();

        when(iUser.getUserByEmail(email)).thenReturn(Optional.of(user));

        // Act
        UserDto result = userCase.getByEmailUseCase(email);

        // Assert
        assertEquals("someUserId", result.getId());
        assertEquals("Jorge Bravo", result.getName());
        assertEquals(email, result.getEmail());
        assertEquals("Jbravo1@", result.getPassword());
        assertEquals(true, result.getIsActive());
        verify(iUser, times(1)).getUserByEmail(email);
    }

    @Test
    void getByEmailUseCase_UserNotFound() {
        // Arrange
        String email = "jbravo@example.com";
        when(iUser.getUserByEmail(email)).thenReturn(Optional.empty());

        // Act
        UserDto result = userCase.getByEmailUseCase(email);

        // Assert
        assertNull(result);
        verify(iUser, times(1)).getUserByEmail(email);
    }
}
