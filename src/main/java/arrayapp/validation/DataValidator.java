package arrayapp.validation;

import arrayapp.exception.ArrayProcessingException;

public interface DataValidator {

    boolean validateLine(String line);

    int[] parseAndValidate(String line) throws ArrayProcessingException;
}