package com.neoris.jba.apiuser.infrastructure.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PhoneDtoTest {

    @Test
    void testPhoneDtoGettersAndSetters() {
        // Arrange
        PhoneDto phoneDto = new PhoneDto();

        // Act
        phoneDto.setId("phone123");
        phoneDto.setNumber("123456789");
        phoneDto.setCityCode("1");
        phoneDto.setCountryCode("44");

        // Assert
        assertEquals("phone123", phoneDto.getId());
        assertEquals("123456789", phoneDto.getNumber());
        assertEquals("1", phoneDto.getCityCode());
        assertEquals("44", phoneDto.getCountryCode());
    }

    @Test
    void testPhoneDtoAllArgsConstructor() {
        // Arrange
        PhoneDto phoneDto = new PhoneDto("phone123", "123456789", "1", "44");

        // Assert
        assertEquals("phone123", phoneDto.getId());
        assertEquals("123456789", phoneDto.getNumber());
        assertEquals("1", phoneDto.getCityCode());
        assertEquals("44", phoneDto.getCountryCode());
    }

    @Test
    void testPhoneDtoNoArgsConstructor() {
        // Act
        PhoneDto phoneDto = new PhoneDto();

        // Assert
        assertNull(phoneDto.getId());
        assertNull(phoneDto.getNumber());
        assertNull(phoneDto.getCityCode());
        assertNull(phoneDto.getCountryCode());
    }

}
