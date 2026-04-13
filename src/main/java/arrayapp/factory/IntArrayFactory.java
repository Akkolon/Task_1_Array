package arrayapp.factory;

import arrayapp.entity.AbstractArrayWrapper;
import arrayapp.entity.IntArrayWrapper;

public class IntArrayFactory implements ArrayFactory {

    @Override
    public AbstractArrayWrapper createArray(int[] elements) {
        return new IntArrayWrapper(elements);
    }
}