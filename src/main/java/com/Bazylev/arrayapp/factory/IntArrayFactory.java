package com.bazylev.arrayapp.factory;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.bazylev.arrayapp.entity.IntArrayWrapper;

public class IntArrayFactory implements ArrayFactory {

    @Override
    public AbstractArrayWrapper createArray(int[] elements) {
        return new IntArrayWrapper(elements);
    }
}