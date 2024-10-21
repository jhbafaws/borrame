package com.neoris.jba.apiuser.infrastructure.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void testUserDtoGettersAndSetters() {
        // Arrange
        Date now = new Date();
        List<PhoneDto> phones = Arrays.asList(
                new PhoneDto("1", "123456789", "1", "44"),
                new PhoneDto("2", "987654321", "2", "44")
        );
        UserDto userDto = new UserDto();

        // Act
        userDto.setId("user123");
        userDto.setName("Jorge Bravo");
        userDto.setEmail("jbravo@example.com");
        userDto.setPassword("Jbravo1@");
        userDto.setIsActive(true);
        userDto.setCreate(now);
        userDto.setModified(now);
        userDto.setLastLogin(now);
        userDto.setToken("someToken");
        userDto.setPhones(phones);

        // Assert
        assertEquals("user123", userDto.getId());
        assertEquals("Jorge Bravo", userDto.getName());
        assertEquals("jbravo@example.com", userDto.getEmail());
        assertEquals("Jbravo1@", userDto.getPassword());
        assertTrue(userDto.getIsActive());
        assertEquals(now, userDto.getCreate());
        assertEquals(now, userDto.getModified());
        assertEquals(now, userDto.getLastLogin());
        assertEquals("someToken", userDto.getToken());
        assertEquals(phones, userDto.getPhones());
    }

    @Test
    void testUserDtoAllArgsConstructor() {
        // Arrange
        Date now = new Date();
        List<PhoneDto> phones = Arrays.asList(
                new PhoneDto("1", "123456789", "1", "44"),
                new PhoneDto("2", "987654321", "2", "44")
        );
        UserDto userDto = new UserDto("user123", "Jorge Bravo", "jbravo@example.com", "Jbravo1@", true, now, now, now, "someToken", phones);

        // Assert
        assertEquals("user123", userDto.getId());
        assertEquals("Jorge Bravo", userDto.getName());
        assertEquals("jbravo@example.com", userDto.getEmail());
        assertEquals("Jbravo1@", userDto.getPassword());
        assertTrue(userDto.getIsActive());
        assertEquals(now, userDto.getCreate());
        assertEquals(now, userDto.getModified());
        assertEquals(now, userDto.getLastLogin());
        assertEquals("someToken", userDto.getToken());
        assertEquals(phones, userDto.getPhones());
    }

    @Test
    void testUserDtoNoArgsConstructor() {
        // Act
        UserDto userDto = new UserDto();

        // Assert
        assertNull(userDto.getId());
        assertNull(userDto.getName());
        assertNull(userDto.getEmail());
        assertNull(userDto.getPassword());
        assertNull(userDto.getIsActive());
        assertNull(userDto.getCreate());
        assertNull(userDto.getModified());
        assertNull(userDto.getLastLogin());
        assertNull(userDto.getToken());
        assertNull(userDto.getPhones());
    }


}
