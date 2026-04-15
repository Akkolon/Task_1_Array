package com.bazylev.arrayapp.comparator;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.bazylev.arrayapp.warehouse.Warehouse;
import java.util.Comparator;

public class ArrayBySumComparator implements Comparator<AbstractArrayWrapper> {
    private final Warehouse warehouse;

    public ArrayBySumComparator() {
        this.warehouse = Warehouse.getInstance();
    }

    @Override
    public int compare(AbstractArrayWrapper arr1, AbstractArrayWrapper arr2) {
        Integer sum1 = warehouse.getSum(arr1.getId());
        Integer sum2 = warehouse.getSum(arr2.getId());

        sum1 = (sum1 != null) ? sum1 : 0;
        sum2 = (sum2 != null) ? sum2 : 0;

        return Integer.compare(sum1, sum2);
    }
}