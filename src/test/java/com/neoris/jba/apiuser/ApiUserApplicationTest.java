package com.neoris.jba.apiuser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ApiUserApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring se carga correctamente
    }

    @Test
    void main_shouldStartApplicationWithoutExceptions() {
        // Verifica que el método main se ejecuta sin lanzar excepciones
        assertDoesNotThrow(() -> ApiUserApplication.main(new String[]{}));
    }
}
