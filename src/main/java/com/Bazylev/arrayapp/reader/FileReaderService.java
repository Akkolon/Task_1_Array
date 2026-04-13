package com.Bazylev.arrayapp.reader;

import com.Bazylev.arrayapp.exception.ArrayProcessingException;
import java.util.List;

public interface FileReaderService {

    List<String> readLines(String filePath) throws ArrayProcessingException;
}