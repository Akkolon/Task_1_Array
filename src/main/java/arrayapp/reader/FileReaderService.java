package arrayapp.reader;

import arrayapp.exception.ArrayProcessingException;
import java.util.List;

public interface FileReaderService {

    List<String> readLines(String filePath) throws ArrayProcessingException;
}