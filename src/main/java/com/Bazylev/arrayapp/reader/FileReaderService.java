package com.bazylev.arrayapp.reader;

import com.bazylev.arrayapp.exception.ArrayProcessingException;
import java.util.List;

public interface FileReaderService {

    List<String> readLines(String filePath) throws ArrayProcessingException;
}