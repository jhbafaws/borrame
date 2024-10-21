package com.neoris.jba.apiuser.domain.service.impl;

import com.neoris.jba.apiuser.domain.entity.User;
import com.neoris.jba.apiuser.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_ShouldReturnSavedUser() {
        // Arrange
        User user = new User();
        user.setId("1L");
        user.setEmail("jbravo@example.com");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserByEmail_ShouldReturnUserWhenEmailExists() {
        // Arrange
        String email = "jbravo@example.com";
        User user = new User();
        user.setId("1L");
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = userService.getUserByEmail(email);

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getUserByEmail_ShouldReturnEmptyWhenEmailDoesNotExist() {
        // Arrange
        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        Optional<User> foundUser = userService.getUserByEmail(email);

        // Assert
        assertTrue(foundUser.isEmpty());
        verify(userRepository, times(1)).findByEmail(email);
    }
}
