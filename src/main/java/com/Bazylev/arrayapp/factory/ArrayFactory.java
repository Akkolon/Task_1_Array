package com.bazylev.arrayapp.factory;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;

public interface ArrayFactory {

    AbstractArrayWrapper createArray(int[] elements);
}