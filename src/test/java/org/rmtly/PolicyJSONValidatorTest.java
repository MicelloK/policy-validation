package org.rmtly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolicyJSONValidatorTest {

    @Test
    void validateIfAsteriskTest() {
        String path = "asterisk.json";
        assertFalse(PolicyJSONValidator.validate(path));
    }

    @Test
    void validateIfNotAsteriskTest() {
        String path = "";
        assertTrue(PolicyJSONValidator.validate(path));
    }

    @Test
    void damagedJSONFileTest() {
        String path = "";
        assertThrows(RuntimeException.class, () -> PolicyJSONValidator.validate(path));
    }

    @Test
    void invalidJSONSchemaTest() {
        String path = "";
        assertThrows(RuntimeException.class, () -> PolicyJSONValidator.validate(path));
    }
}