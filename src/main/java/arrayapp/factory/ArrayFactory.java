package arrayapp.factory;

import arrayapp.entity.AbstractArrayWrapper;

public interface ArrayFactory {

    AbstractArrayWrapper createArray(int[] elements);
}