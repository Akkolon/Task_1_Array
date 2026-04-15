package com.bazylev.arrayapp;

import com.bazylev.arrayapp.comparator.ArrayByFirstElementComparator;
import com.bazylev.arrayapp.comparator.ArrayByIdComparator;
import com.bazylev.arrayapp.comparator.ArrayByLengthComparator;
import com.bazylev.arrayapp.comparator.ArrayByNameComparator;
import com.bazylev.arrayapp.comparator.ArrayBySumComparator;
import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.bazylev.arrayapp.entity.IntArrayWrapper;
import com.bazylev.arrayapp.exception.ArrayProcessingException;
import com.bazylev.arrayapp.reader.FileReaderService;
import com.bazylev.arrayapp.reader.FileReaderServiceImpl;
import com.bazylev.arrayapp.repository.ArrayRepositoryImpl;
import com.bazylev.arrayapp.validation.ArrayParser;
import com.bazylev.arrayapp.validation.ArrayValidator;
import com.bazylev.arrayapp.validation.IntArrayParser;
import com.bazylev.arrayapp.validation.IntArrayValidator;
import com.bazylev.arrayapp.warehouse.ArrayData;
import com.bazylev.arrayapp.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "data/arrays.txt";

        FileReaderService fileReader = new FileReaderServiceImpl();
        ArrayValidator validator = new IntArrayValidator();
        ArrayParser parser = new IntArrayParser();
        ArrayRepositoryImpl repository = ArrayRepositoryImpl.getInstance();
        Warehouse warehouse = Warehouse.getInstance();

        try {
            List<String> lines = fileReader.readLines(filePath);
            int arrayCounter = 0;

            LOGGER.info("--- READING AND ADDING ARRAYS TO REPOSITORY ---");
            for (String line : lines) {
                if (!validator.isValid(line)) {
                    LOGGER.warn("Skipping invalid line: {}", line);
                    continue;
                }

                try {
                    int[] parsedArray = parser.parse(line);
                    String arrayName = "Array_" + (++arrayCounter);
                    IntArrayWrapper arrayWrapper = new IntArrayWrapper(parsedArray, arrayName);
                    repository.add(arrayWrapper);
                    LOGGER.info("Added: {}", arrayWrapper);

                    ArrayData data = warehouse.getArrayData(arrayWrapper.getId());
                    if (data != null) {
                        LOGGER.info("  Warehouse: min={}, max={}, sum={}, avg={}",
                                data.getMin(), data.getMax(), data.getSum(), data.getAverage());
                    }
                } catch (ArrayProcessingException e) {
                    LOGGER.error("Failed to parse line: {}", e.getMessage());
                }
            }

            LOGGER.info("========================================");
            LOGGER.info("ALL ARRAYS IN REPOSITORY ({} total):", repository.size());
            for (AbstractArrayWrapper arr : repository.findAll()) {
                LOGGER.info("  {}", arr);
                ArrayData data = warehouse.getArrayData(arr.getId());
                LOGGER.info("    Warehouse: {}", data);
            }

            LOGGER.info("========================================");
            LOGGER.info("SEARCH TESTS:");

            List<AbstractArrayWrapper> highSumArrays = repository.findBySumGreaterThan(50);
            LOGGER.info("Arrays with sum > 50: {} found", highSumArrays.size());
            for (AbstractArrayWrapper arr : highSumArrays) {
                Integer sum = warehouse.getSum(arr.getId());
                LOGGER.info("  {} -> sum = {}", arr.getName(), sum);
            }

            LOGGER.info("========================================");
            LOGGER.info("SORTING TESTS (using Comparator):");

            List<AbstractArrayWrapper> allArrays = repository.findAll();

            LOGGER.info("--- Sorted by ID ---");
            repository.sort(allArrays, new ArrayByIdComparator());
            allArrays.forEach(arr -> LOGGER.info("  {} -> {}", arr.getId(), arr.getName()));

            LOGGER.info("--- Sorted by Name ---");
            repository.sort(allArrays, new ArrayByNameComparator());
            allArrays.forEach(arr -> LOGGER.info("  {}", arr.getName()));

            LOGGER.info("--- Sorted by Length ---");
            repository.sort(allArrays, new ArrayByLengthComparator());
            allArrays.forEach(arr -> LOGGER.info("  {} -> length: {}", arr.getName(), arr.getLength()));

            LOGGER.info("--- Sorted by First Element ---");
            repository.sort(allArrays, new ArrayByFirstElementComparator());
            allArrays.forEach(arr -> {
                int[] array = arr.getArray();
                int first = (array.length > 0) ? array[0] : 0;
                LOGGER.info("  {} -> first: {}", arr.getName(), first);
            });

            LOGGER.info("--- Sorted by Sum ---");
            repository.sort(allArrays, new ArrayBySumComparator());
            allArrays.forEach(arr -> {
                Integer sum = warehouse.getSum(arr.getId());
                LOGGER.info("  {} -> sum: {}", arr.getName(), sum);
            });

            LOGGER.info("========================================");
            LOGGER.info("OBSERVER PATTERN:");

            if (repository.size() > 0) {
                AbstractArrayWrapper firstArray = repository.findAll().get(0);
                LOGGER.info("Before modification: {}", firstArray);
                LOGGER.info("Warehouse data BEFORE: {}", warehouse.getArrayData(firstArray.getId()));

                if (firstArray instanceof IntArrayWrapper) {
                    IntArrayWrapper intArray = (IntArrayWrapper) firstArray;

                    LOGGER.info("--- Changing first element ---");
                    int oldValue = intArray.getElement(0);
                    int newValue = 999;
                    LOGGER.info("Changing element[0] from {} to {}", oldValue, newValue);
                    intArray.setElement(0, newValue);

                    LOGGER.info("After modification: {}", intArray);
                    LOGGER.info("Warehouse data AFTER (should be updated automatically):");
                    ArrayData newData = warehouse.getArrayData(firstArray.getId());
                    LOGGER.info("  {}", newData);
                }
            }

            LOGGER.info("========================================");
            LOGGER.info("REMOVE TEST:");
            if (repository.size() > 0) {
                String idToRemove = repository.findAll().get(0).getId();
                LOGGER.info("Removing array with id: {}", idToRemove);
                repository.remove(idToRemove);
                LOGGER.info("Repository size after removal: {}", repository.size());
            }

        } catch (ArrayProcessingException e) {
            LOGGER.error("Application failed: {}", e.getMessage(), e);
        }
    }

}