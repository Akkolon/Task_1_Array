package com.Bazylev.arrayapp;

import com.Bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.Bazylev.arrayapp.exception.ArrayProcessingException;
import com.Bazylev.arrayapp.factory.ArrayFactory;
import com.Bazylev.arrayapp.factory.IntArrayFactory;
import com.Bazylev.arrayapp.reader.FileReaderService;
import com.Bazylev.arrayapp.reader.FileReaderServiceImpl;
import com.Bazylev.arrayapp.service.ArrayAnalyzer;
import com.Bazylev.arrayapp.service.ArrayAnalyzerImpl;
import com.Bazylev.arrayapp.service.ArraySorter;
import com.Bazylev.arrayapp.service.ArraySorterImpl;
import com.Bazylev.arrayapp.validation.DataValidator;
import com.Bazylev.arrayapp.validation.IntArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "data/arrays.txt";

        FileReaderService fileReader = new FileReaderServiceImpl();
        DataValidator validator = new IntArrayValidator();
        ArrayFactory factory = new IntArrayFactory();
        ArrayAnalyzer analyzer = new ArrayAnalyzerImpl();
        ArraySorter sorter = new ArraySorterImpl();

        try {
            List<String> lines = fileReader.readLines(filePath);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                LOGGER.info("Processing line {}: {}", i + 1, line);

                try {
                    int[] parsedArray = validator.parseAndValidate(line);
                    processArray(parsedArray, analyzer, sorter, factory);
                } catch (ArrayProcessingException e) {
                    LOGGER.error("Failed to process line {}: {}", i + 1, e.getMessage());
                }
            }

        } catch (ArrayProcessingException e) {
            LOGGER.error("Application failed: {}", e.getMessage(), e);
        }
    }

    private static void processArray(int[] parsedArray, ArrayAnalyzer analyzer,
                                     ArraySorter sorter, ArrayFactory factory) {
        AbstractArrayWrapper arrayWrapper = factory.createArray(parsedArray);
        LOGGER.info("Array created: {}", arrayWrapper);

        Optional<Integer> min = analyzer.findMin(arrayWrapper.getArray());
        Optional<Integer> max = analyzer.findMax(arrayWrapper.getArray());
        Optional<Integer> sum = analyzer.calculateSum(arrayWrapper.getArray());
        Optional<Double> average = analyzer.calculateAverage(arrayWrapper.getArray());

        min.ifPresent(value -> LOGGER.info("Min: {}", value));
        max.ifPresent(value -> LOGGER.info("Max: {}", value));
        sum.ifPresent(value -> LOGGER.info("Sum: {}", value));
        average.ifPresent(value -> LOGGER.info("Average: {}", value));

        int[] bubbleSorted = sorter.bubbleSort(arrayWrapper.getArray());
        LOGGER.info("Bubble sort result: {}", java.util.Arrays.toString(bubbleSorted));

        int[] quickSorted = sorter.quickSort(arrayWrapper.getArray());
        LOGGER.info("Quick sort result: {}", java.util.Arrays.toString(quickSorted));
    }
}