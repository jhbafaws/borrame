package com.neoris.jba.apiuser.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    @Test
    void testUserEntity() {
        // Arrange
        Date now = new Date();
        Phone phone1 = Phone.builder()
                .id("phone1-id")
                .number("123456789")
                .cityCode("1")
                .countryCode("44")
                .build();

        Phone phone2 = Phone.builder()
                .id("phone2-id")
                .number("987654321")
                .cityCode("2")
                .countryCode("44")
                .build();

        List<Phone> phones = Arrays.asList(phone1, phone2);

        User user = User.builder()
                .id("user-id")
                .name("Jorge Bravo")
                .email("jbravo@example.com")
                .password("JbravoA1@")
                .isActive(true)
                .create(now)
                .modified(now)
                .lastLogin(now)
                .token("some-token")
                .phones(phones)
                .build();

        // Act & Assert
        // Verificando getters
        assertEquals("user-id", user.getId());
        assertEquals("Jorge Bravo", user.getName());
        assertEquals("jbravo@example.com", user.getEmail());
        assertEquals("JbravoA1@", user.getPassword());
        assertEquals(true, user.getIsActive());
        assertEquals(now, user.getCreate());
        assertEquals(now, user.getModified());
        assertEquals(now, user.getLastLogin());
        assertEquals("some-token", user.getToken());
        assertEquals(phones, user.getPhones());

        // Verificando la relación con Phone
        assertEquals(2, user.getPhones().size());
        assertEquals("phone1-id", user.getPhones().get(0).getId());
        assertEquals("phone2-id", user.getPhones().get(1).getId());

        // Verificando el constructor por defecto
        User emptyUser = new User();
        assertNull(emptyUser.getId());
        assertNull(emptyUser.getName());
        assertNull(emptyUser.getEmail());
        assertNull(emptyUser.getPassword());
        assertNull(emptyUser.getIsActive());
        assertNull(emptyUser.getCreate());
        assertNull(emptyUser.getModified());
        assertNull(emptyUser.getLastLogin());
        assertNull(emptyUser.getToken());
        assertNull(emptyUser.getPhones());
    }

    @Test
    void testUserSettersAndGetters() {
        // Arrange
        Date now = new Date();
        User user = new User();

        // Act
        user.setId("user-id");
        user.setName("Jorge Bravo");
        user.setEmail("jbravo@example.com");
        user.setPassword("JbravoA1@");
        user.setIsActive(true);
        user.setCreate(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setToken("some-token");

        Phone phone1 = new Phone();
        phone1.setId("phone1-id");
        phone1.setNumber("123456789");
        phone1.setCityCode("1");
        phone1.setCountryCode("44");

        Phone phone2 = new Phone();
        phone2.setId("phone2-id");
        phone2.setNumber("987654321");
        phone2.setCityCode("2");
        phone2.setCountryCode("44");

        List<Phone> phones = Arrays.asList(phone1, phone2);
        user.setPhones(phones);

        // Assert
        assertEquals("user-id", user.getId());
        assertEquals("Jorge Bravo", user.getName());
        assertEquals("jbravo@example.com", user.getEmail());
        assertEquals("JbravoA1@", user.getPassword());
        assertEquals(true, user.getIsActive());
        assertEquals(now, user.getCreate());
        assertEquals(now, user.getModified());
        assertEquals(now, user.getLastLogin());
        assertEquals("some-token", user.getToken());
        assertEquals(phones, user.getPhones());

        // Verificando la relación con Phone
        assertEquals(2, user.getPhones().size());
        assertEquals("phone1-id", user.getPhones().get(0).getId());
        assertEquals("phone2-id", user.getPhones().get(1).getId());
    }
}
