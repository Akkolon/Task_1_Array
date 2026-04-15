package com.bazylev.arrayapp.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Pattern;

public class IntArrayValidator implements ArrayValidator {
    private static final Logger LOGGER = LogManager.getLogger(IntArrayValidator.class);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[,\\-;\\s]+");

    @Override
    public boolean isValid(String line) {
        LOGGER.debug("Validating line: {}", line);

        if (line == null || line.isBlank()) {
            LOGGER.debug("Line is null or empty - invalid");
            return false;
        }

        String[] parts = SEPARATOR_PATTERN.split(line.strip());

        for (String part : parts) {
            if (!NUMBER_PATTERN.matcher(part).matches()) {
                LOGGER.debug("Invalid number found: {}", part);
                return false;
            }
        }

        LOGGER.debug("Line is valid");
        return true;
    }
}