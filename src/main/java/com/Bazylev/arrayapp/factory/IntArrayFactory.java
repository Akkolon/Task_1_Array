package com.Bazylev.arrayapp.factory;

import com.Bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.Bazylev.arrayapp.entity.IntArrayWrapper;

public class IntArrayFactory implements ArrayFactory {

    @Override
    public AbstractArrayWrapper createArray(int[] elements) {
        return new IntArrayWrapper(elements);
    }
}