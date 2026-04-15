package com.bazylev.arrayapp.reader;

import com.bazylev.arrayapp.exception.ArrayProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderServiceImplTest {
    private FileReaderService fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new FileReaderServiceImpl();
    }

    @Test
    void readLinesWithNonExistentFileShouldThrowException() {
        ArrayProcessingException exception = assertThrows(ArrayProcessingException.class, () -> {
            fileReader.readLines("non_existent_file.txt");
        });

        assertTrue(exception.getMessage().contains("File not found"));
    }
}