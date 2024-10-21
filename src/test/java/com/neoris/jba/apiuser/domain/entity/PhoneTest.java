package com.neoris.jba.apiuser.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PhoneTest {

    @Test
    void testPhoneEntity() {
        // Arrange
        User user = User.builder()
                .id("user-id")
                .name("Jorge Bravo")
                .email("jbravo@example.com")
                .build();

        Phone phone = Phone.builder()
                .id("phone-id")
                .number("123456789")
                .cityCode("1")
                .countryCode("44")
                .user(user)
                .build();

        // Act & Assert
        // Verificando getters
        assertEquals("phone-id", phone.getId());
        assertEquals("123456789", phone.getNumber());
        assertEquals("1", phone.getCityCode());
        assertEquals("44", phone.getCountryCode());
        assertEquals(user, phone.getUser());

        // Verificando la relación inversa
        assertEquals("user-id", phone.getUser().getId());

        // Verificando el constructor por defecto
        Phone emptyPhone = new Phone();
        assertNull(emptyPhone.getId());
        assertNull(emptyPhone.getNumber());
        assertNull(emptyPhone.getCityCode());
        assertNull(emptyPhone.getCountryCode());
        assertNull(emptyPhone.getUser());
    }

    @Test
    void testPhoneSettersAndGetters() {
        // Arrange
        Phone phone = new Phone();

        // Act
        phone.setId("phone-id");
        phone.setNumber("123456789");
        phone.setCityCode("1");
        phone.setCountryCode("44");

        User user = new User();
        user.setId("user-id");
        phone.setUser(user);

        // Assert
        assertEquals("phone-id", phone.getId());
        assertEquals("123456789", phone.getNumber());
        assertEquals("1", phone.getCityCode());
        assertEquals("44", phone.getCountryCode());
        assertEquals("user-id", phone.getUser().getId());
    }
}
