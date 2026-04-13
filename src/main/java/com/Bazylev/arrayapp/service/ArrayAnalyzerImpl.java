package com.Bazylev.arrayapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

public class ArrayAnalyzerImpl implements ArrayAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger(ArrayAnalyzerImpl.class);

    @Override
    public Optional<Integer> findMin(int[] array) {
        LOGGER.debug("Finding minimum value in array of length: {}",
                array == null ? "null" : array.length);

        if (array == null || array.length == 0) {
            LOGGER.warn("Array is null or empty, returning empty Optional");
            return Optional.empty();
        }

        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        LOGGER.debug("Minimum value found: {}", min);
        return Optional.of(min);
    }

    @Override
    public Optional<Integer> findMax(int[] array) {
        LOGGER.debug("Finding maximum value in array of length: {}",
                array == null ? "null" : array.length);

        if (array == null || array.length == 0) {
            LOGGER.warn("Array is null or empty, returning empty Optional");
            return Optional.empty();
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        LOGGER.debug("Maximum value found: {}", max);
        return Optional.of(max);
    }

    @Override
    public Optional<Integer> calculateSum(int[] array) {
        LOGGER.debug("Calculating sum of array elements");

        if (array == null || array.length == 0) {
            LOGGER.warn("Array is null or empty, returning empty Optional");
            return Optional.empty();
        }

        int sum = 0;
        for (int value : array) {
            sum += value;
        }

        LOGGER.debug("Sum calculated: {}", sum);
        return Optional.of(sum);
    }

    @Override
    public Optional<Double> calculateAverage(int[] array) {
        LOGGER.debug("Calculating average of array elements");

        if (array == null || array.length == 0) {
            LOGGER.warn("Array is null or empty, returning empty Optional");
            return Optional.empty();
        }

        Optional<Integer> sumOptional = calculateSum(array);
        if (sumOptional.isPresent()) {
            double average = (double) sumOptional.get() / array.length;
            LOGGER.debug("Average calculated: {}", average);
            return Optional.of(average);
        }

        return Optional.empty();
    }
}