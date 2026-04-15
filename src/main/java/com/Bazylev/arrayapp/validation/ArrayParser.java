package com.bazylev.arrayapp.validation;

import com.bazylev.arrayapp.exception.ArrayProcessingException;

public interface ArrayParser {
    int[] parse(String line) throws ArrayProcessingException;
}