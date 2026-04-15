package com.bazylev.arrayapp.comparator;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import java.util.Comparator;

public class ArrayByFirstElementComparator implements Comparator<AbstractArrayWrapper> {

    @Override
    public int compare(AbstractArrayWrapper arr1, AbstractArrayWrapper arr2) {
        int[] array1 = arr1.getArray();
        int[] array2 = arr2.getArray();

        int first1 = (array1 != null && array1.length > 0) ? array1[0] : Integer.MIN_VALUE;
        int first2 = (array2 != null && array2.length > 0) ? array2[0] : Integer.MIN_VALUE;

        return Integer.compare(first1, first2);
    }
}