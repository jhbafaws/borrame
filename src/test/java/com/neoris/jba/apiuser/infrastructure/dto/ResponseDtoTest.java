package com.neoris.jba.apiuser.infrastructure.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDtoTest {

    @Test
    void testResponseDtoGettersAndSetters() {
        // Arrange
        Date now = new Date();
        ResponseDto responseDto = new ResponseDto();

        // Act
        responseDto.setId("123");
        responseDto.setCreate(now);
        responseDto.setModified(now);
        responseDto.setLastLogin(now);
        responseDto.setToken("someToken");
        responseDto.setIsActive(true);

        // Assert
        assertEquals("123", responseDto.getId());
        assertEquals(now, responseDto.getCreate());
        assertEquals(now, responseDto.getModified());
        assertEquals(now, responseDto.getLastLogin());
        assertEquals("someToken", responseDto.getToken());
        assertTrue(responseDto.getIsActive());
    }

    @Test
    void testResponseDtoAllArgsConstructor() {
        // Arrange
        Date now = new Date();
        ResponseDto responseDto = new ResponseDto("123", now, now, now, "someToken", true);

        // Assert
        assertEquals("123", responseDto.getId());
        assertEquals(now, responseDto.getCreate());
        assertEquals(now, responseDto.getModified());
        assertEquals(now, responseDto.getLastLogin());
        assertEquals("someToken", responseDto.getToken());
        assertTrue(responseDto.getIsActive());
    }

    @Test
    void testResponseDtoNoArgsConstructor() {
        // Act
        ResponseDto responseDto = new ResponseDto();

        // Assert
        assertNull(responseDto.getId());
        assertNull(responseDto.getCreate());
        assertNull(responseDto.getModified());
        assertNull(responseDto.getLastLogin());
        assertNull(responseDto.getToken());
        assertNull(responseDto.getIsActive());
    }

    @Test
    void testResponseDtoJsonSerialization() throws JsonProcessingException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();
        Date now = new Date();
        ResponseDto responseDto = new ResponseDto("123", now, now, now, "someToken", true);

        // Act
        String json = objectMapper.writeValueAsString(responseDto);

        // Assert
        assertTrue(json.contains("\"id\":\"123\""));
        assertTrue(json.contains("\"created\":" + now.getTime()));
        assertTrue(json.contains("\"modified\":" + now.getTime()));
        assertTrue(json.contains("\"last_login\":" + now.getTime()));
        assertTrue(json.contains("\"token\":\"someToken\""));
        assertTrue(json.contains("\"isActive\":true"));
    }

    @Test
    void testResponseDtoJsonDeserialization() throws JsonProcessingException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();
        Date now = new Date();
        String json = "{\"id\":\"123\",\"created\":" + now.getTime() +
                ",\"modified\":" + now.getTime() + ",\"last_login\":" + now.getTime() +
                ",\"token\":\"someToken\",\"isActive\":true}";

        // Act
        ResponseDto responseDto = objectMapper.readValue(json, ResponseDto.class);

        // Assert
        assertEquals("123", responseDto.getId());
        assertEquals(now, responseDto.getCreate());
        assertEquals(now, responseDto.getModified());
        assertEquals(now, responseDto.getLastLogin());
        assertEquals("someToken", responseDto.getToken());
        assertTrue(responseDto.getIsActive());
    }
}
