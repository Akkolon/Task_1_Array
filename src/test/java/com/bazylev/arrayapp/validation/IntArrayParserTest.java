package com.bazylev.arrayapp.validation;

import com.bazylev.arrayapp.exception.ArrayProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntArrayParserTest {
    private ArrayParser parser;

    @BeforeEach
    void setUp() {
        parser = new IntArrayParser();
    }

    @Test
    void parseWithCommaSeparatedNumbersShouldReturnIntArray() throws ArrayProcessingException {
        int[] result = parser.parse("1, 2, 3, 4, 5");
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    void parseWithDashSeparatedNumbersShouldReturnIntArray() throws ArrayProcessingException {
        int[] result = parser.parse("10-20-30-40-50");
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, result);
    }

    @Test
    void parseWithSemicolonSeparatedNumbersShouldReturnIntArray() throws ArrayProcessingException {
        int[] result = parser.parse("1;2;3;4;5");
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    void parseWithMixedSeparatorsShouldReturnIntArray() throws ArrayProcessingException {
        int[] result = parser.parse("1, 2-3;4 5");
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    void parseWithNullLineShouldThrowException() {
        assertThrows(ArrayProcessingException.class, () -> {
            parser.parse(null);
        });
    }

    @Test
    void parseWithEmptyLineShouldThrowException() {
        assertThrows(ArrayProcessingException.class, () -> {
            parser.parse("");
        });
    }

    @Test
    void parseWithInvalidNumberShouldThrowException() {
        assertThrows(ArrayProcessingException.class, () -> {
            parser.parse("1, 2, x3, 4");
        });
    }
}