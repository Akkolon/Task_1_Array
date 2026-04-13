package arrayapp.validation;

import arrayapp.exception.ArrayProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IntArrayValidator implements DataValidator {
    private static final Logger LOGGER = LogManager.getLogger(IntArrayValidator.class);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[,\\-;\\s]+");

    @Override
    public boolean validateLine(String line) {
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

    @Override
    public int[] parseAndValidate(String line) throws ArrayProcessingException {
        LOGGER.info("Parsing and validating line: {}", line);

        if (line == null || line.isBlank()) {
            throw new ArrayProcessingException("Line is null or empty");
        }

        if (!validateLine(line)) {
            throw new ArrayProcessingException("Invalid line format: " + line);
        }

        String[] parts = SEPARATOR_PATTERN.split(line.strip());
        List<Integer> validNumbers = new ArrayList<>();

        for (String part : parts) {
            try {
                int number = Integer.parseInt(part);
                validNumbers.add(number);
            } catch (NumberFormatException e) {
                LOGGER.warn("Failed to parse number: {}", part);
                throw new ArrayProcessingException("Failed to parse number: " + part, e);
            }
        }

        int[] result = validNumbers.stream().mapToInt(Integer::intValue).toArray();

        if (result.length == 0) {
            throw new ArrayProcessingException("No valid numbers found in line: " + line);
        }

        LOGGER.info("Parsed {} valid numbers", result.length);
        return result;
    }
}