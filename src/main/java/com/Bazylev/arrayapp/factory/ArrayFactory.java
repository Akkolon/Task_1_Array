package com.Bazylev.arrayapp.factory;

import com.Bazylev.arrayapp.entity.AbstractArrayWrapper;

public interface ArrayFactory {

    AbstractArrayWrapper createArray(int[] elements);
}