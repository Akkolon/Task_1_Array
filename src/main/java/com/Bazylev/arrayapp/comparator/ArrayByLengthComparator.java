package com.bazylev.arrayapp.comparator;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import java.util.Comparator;

public class ArrayByLengthComparator implements Comparator<AbstractArrayWrapper> {

    @Override
    public int compare(AbstractArrayWrapper arr1, AbstractArrayWrapper arr2) {
        return Integer.compare(arr1.getLength(), arr2.getLength());
    }
}