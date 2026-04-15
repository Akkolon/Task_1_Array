package com.bazylev.arrayapp.comparator;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import java.util.Comparator;

public class ArrayByNameComparator implements Comparator<AbstractArrayWrapper> {

    @Override
    public int compare(AbstractArrayWrapper arr1, AbstractArrayWrapper arr2) {
        return arr1.getName().compareTo(arr2.getName());
    }
}