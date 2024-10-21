package com.neoris.jba.apiuser.jwt;


import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtUtilsTest {

    private final String secretKey = "76397924423F4528482B4D6251655468576D5A7134743777217A25432A46294A"; // Base64 encoded key
    private final String timeExpiration = "300000"; // 5 minutes in milliseconds
    @InjectMocks
    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Usamos ReflectionTestUtils para inyectar valores en los campos @Value
        ReflectionTestUtils.setField(jwtUtils, "secretKey", secretKey);
        ReflectionTestUtils.setField(jwtUtils, "timeExpiration", timeExpiration);
    }

    @Test
    void generateAccessToken_ShouldGenerateValidToken() {
        // Arrange
        String email = "test@example.com";

        // Act
        String token = jwtUtils.generateAccessToken(email);

        // Assert
        assertNotNull(token);
        String subject = Jwts.parserBuilder()
                .setSigningKey(jwtUtils.getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        assertEquals(email, subject);
    }


}
