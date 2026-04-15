package com.bazylev.arrayapp.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntArrayValidatorTest {
    private ArrayValidator validator;

    @BeforeEach
    void setUp() {
        validator = new IntArrayValidator();
    }

    @Test
    void isValidWithCommaSeparatedNumbersShouldReturnTrue() {
        boolean result = validator.isValid("1, 2, 3, 4, 5");
        assertTrue(result);
    }

    @Test
    void isValidWithDashSeparatedNumbersShouldReturnTrue() {
        boolean result = validator.isValid("10-20-30-40-50");
        assertTrue(result);
    }

    @Test
    void isValidWithInvalidCharactersShouldReturnFalse() {
        boolean result = validator.isValid("1, 2, x3, 6..5, 77");
        assertFalse(result);
    }

    @Test
    void isValidEmptyLineShouldReturnFalse() {
        boolean result = validator.isValid("");
        assertFalse(result);
    }

    @Test
    void isValidNullLineShouldReturnFalse() {
        boolean result = validator.isValid(null);
        assertFalse(result);
    }
}