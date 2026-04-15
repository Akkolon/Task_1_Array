package com.bazylev.arrayapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ArrayAnalyzerImplTest {
    private ArrayAnalyzer analyzer;
    private int[] testArray;

    @BeforeEach
    void setUp() {
        analyzer = new ArrayAnalyzerImpl();
        testArray = new int[]{5, 2, 8, 1, 9, 3};
    }

    @Test
    void findMinShouldReturnSmallestValue() {
        Optional<Integer> result = analyzer.findMin(testArray);

        assertTrue(result.isPresent());
        assertEquals(1, result.get());
    }

    @Test
    void findMinWithEmptyArrayShouldReturnEmpty() {
        Optional<Integer> result = analyzer.findMin(new int[0]);

        assertTrue(result.isEmpty());
    }

    @Test
    void findMinWithNullArrayShouldReturnEmpty() {
        Optional<Integer> result = analyzer.findMin(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void findMaxShouldReturnLargestValue() {
        Optional<Integer> result = analyzer.findMax(testArray);

        assertTrue(result.isPresent());
        assertEquals(9, result.get());
    }

    @Test
    void findMaxWithEmptyArrayShouldReturnEmpty() {
        Optional<Integer> result = analyzer.findMax(new int[0]);

        assertTrue(result.isEmpty());
    }

    @Test
    void findMaxWithNullArrayShouldReturnEmpty() {
        Optional<Integer> result = analyzer.findMax(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void calculateSumShouldReturnCorrectSum() {
        Optional<Integer> result = analyzer.calculateSum(testArray);

        assertTrue(result.isPresent());
        assertEquals(28, result.get());
    }

    @Test
    void calculateSumWithEmptyArrayShouldReturnEmpty() {
        Optional<Integer> result = analyzer.calculateSum(new int[0]);

        assertTrue(result.isEmpty());
    }

    @Test
    void calculateAverageShouldReturnCorrectAverage() {
        Optional<Double> result = analyzer.calculateAverage(testArray);

        assertTrue(result.isPresent());
        assertEquals(4.666666666666667, result.get(), 0.0001);
    }

    @Test
    void calculateAverageWithEmptyArrayShouldReturnEmpty() {
        Optional<Double> result = analyzer.calculateAverage(new int[0]);

        assertTrue(result.isEmpty());
    }
}