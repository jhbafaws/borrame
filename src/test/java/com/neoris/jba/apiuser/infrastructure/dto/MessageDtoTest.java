package com.neoris.jba.apiuser.infrastructure.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageDtoTest {

    @Test
    void testMessageDtoGettersAndSetters() {
        // Arrange
        MessageDto messageDto = new MessageDto();

        // Act
        messageDto.setMensaje("Test message");

        // Assert
        assertEquals("Test message", messageDto.getMensaje());
    }

    @Test
    void testMessageDtoAllArgsConstructor() {
        // Arrange
        MessageDto messageDto = new MessageDto("Test message");

        // Assert
        assertEquals("Test message", messageDto.getMensaje());
    }

    @Test
    void testMessageDtoNoArgsConstructor() {
        // Act
        MessageDto messageDto = new MessageDto();

        // Assert
        assertNull(messageDto.getMensaje());
    }

    @Test
    void testMessageDtoJsonSerialization() throws JsonProcessingException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();
        MessageDto messageDto = new MessageDto("Test message");

        // Act
        String json = objectMapper.writeValueAsString(messageDto);

        // Assert
        assertTrue(json.contains("\"mensaje\":\"Test message\""));
    }

    @Test
    void testMessageDtoJsonDeserialization() throws JsonProcessingException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"mensaje\":\"Test message\"}";

        // Act
        MessageDto messageDto = objectMapper.readValue(json, MessageDto.class);

        // Assert
        assertEquals("Test message", messageDto.getMensaje());
    }
}
