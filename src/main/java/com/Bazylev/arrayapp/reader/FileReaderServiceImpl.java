package com.Bazylev.arrayapp.reader;

import com.Bazylev.arrayapp.exception.ArrayProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService {
    private static final Logger LOGGER = LogManager.getLogger(FileReaderServiceImpl.class);

    @Override
    public List<String> readLines(String filePath) throws ArrayProcessingException {
        LOGGER.info("Reading lines from file: {}", filePath);
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filePath);

        try {
            if (!Files.exists(path)) {
                throw new ArrayProcessingException("File not found: " + filePath);
            }

            List<String> allLines = Files.readAllLines(path);

            for (String line : allLines) {
                if (line != null && !line.isBlank()){
                    lines.add(line);
                } else {
                    LOGGER.debug("Skipping empty line");
                }
            }

            LOGGER.info("Successfully read {} non-empty lines", lines.size());

            if (lines.isEmpty()) {
                throw new ArrayProcessingException("No valid data lines found in file: " + filePath);
            }

        } catch (IOException e) {
            LOGGER.error("Failed to read file: {}", filePath, e);
            throw new ArrayProcessingException("IO error while reading file: " + filePath, e);
        }

        return lines;
    }
}