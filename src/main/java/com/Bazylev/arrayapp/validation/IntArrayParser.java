package com.bazylev.arrayapp.validation;

import com.bazylev.arrayapp.exception.ArrayProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IntArrayParser implements ArrayParser {
    private static final Logger LOGGER = LogManager.getLogger(IntArrayParser.class);
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[,\\-;\\s]+");

    @Override
    public int[] parse(String line) throws ArrayProcessingException {
        LOGGER.info("Parsing line: {}", line);

        if (line == null || line.isBlank()) {
            throw new ArrayProcessingException("Cannot parse null or empty line");
        }

        String[] parts = SEPARATOR_PATTERN.split(line.strip());
        List<Integer> numbers = new ArrayList<>();

        for (String part : parts) {
            try {
                int number = Integer.parseInt(part);
                numbers.add(number);
            } catch (NumberFormatException e) {
                LOGGER.warn("Failed to parse number: {}", part);
                throw new ArrayProcessingException("Failed to parse number: " + part, e);
            }
        }

        if (numbers.isEmpty()) {
            throw new ArrayProcessingException("No numbers found in line: " + line);
        }

        int[] result = numbers.stream().mapToInt(Integer::intValue).toArray();
        LOGGER.info("Parsed {} numbers", result.length);
        return result;
    }
}