package org.rmtly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolicyJSONValidatorTest {

    @Test
    void validateIfAsteriskTest() {
        String path = "src/test/resources/asterisk.json";
        assertFalse(PolicyJSONValidator.validate(path));
    }

    @Test
    void validateIfNotAsteriskTest() {
        String path = "src/test/resources/notAsterisk.json";
        assertTrue(PolicyJSONValidator.validate(path));
    }

    @Test
    void damagedJSONFileTest() {
        String path = "src/test/resources/damaged.json";
        assertThrows(RuntimeException.class, () -> PolicyJSONValidator.validate(path));
    }

    @Test
    void invalidJSONSchemaTest() {
        String path = "src/test/resources/invalidSchema.json";
        assertThrows(RuntimeException.class, () -> PolicyJSONValidator.validate(path));
    }
}