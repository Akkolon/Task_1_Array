package com.bazylev.arrayapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArraySorterImplTest {
    private ArraySorter sorter;
    private int[] testArray;
    private int[] expectedSorted;

    @BeforeEach
    void setUp() {
        sorter = new ArraySorterImpl();
        testArray = new int[]{5, 2, 8, 1, 9, 3};
        expectedSorted = new int[]{1, 2, 3, 5, 8, 9};
    }

    @Test
    void bubbleSortShouldSortArrayCorrectly() {
        int[] result = sorter.bubbleSort(testArray);

        assertArrayEquals(expectedSorted, result);
        assertNotSame(testArray, result);
    }

    @Test
    void bubbleSortWithEmptyArrayShouldReturnEmptyArray() {
        int[] result = sorter.bubbleSort(new int[0]);

        assertEquals(0, result.length);
    }

    @Test
    void bubbleSortWithNullShouldReturnEmptyArray() {
        int[] result = sorter.bubbleSort(null);

        assertEquals(0, result.length);
    }

    @Test
    void quickSortShouldSortArrayCorrectly() {
        int[] result = sorter.quickSort(testArray);

        assertArrayEquals(expectedSorted, result);
        assertNotSame(testArray, result);
    }

    @Test
    void quickSortWithEmptyArrayShouldReturnEmptyArray() {
        int[] result = sorter.quickSort(new int[0]);

        assertEquals(0, result.length);
    }

    @Test
    void quickSortWithNullShouldReturnEmptyArray() {
        int[] result = sorter.quickSort(null);

        assertEquals(0, result.length);
    }

    @Test
    void quickSortWithSingleElementShouldReturnSameArray() {
        int[] singleElement = new int[]{42};
        int[] result = sorter.quickSort(singleElement);

        assertArrayEquals(singleElement, result);
    }

    @Test
    void bubbleSortShouldNotModifyOriginalArray() {
        int[] originalCopy = testArray.clone();
        sorter.bubbleSort(testArray);

        assertArrayEquals(originalCopy, testArray);
    }

    @Test
    void quickSortShouldNotModifyOriginalArray() {
        int[] originalCopy = testArray.clone();
        sorter.quickSort(testArray);

        assertArrayEquals(originalCopy, testArray);
    }
}