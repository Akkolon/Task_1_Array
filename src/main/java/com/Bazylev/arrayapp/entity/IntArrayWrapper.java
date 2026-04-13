package com.Bazylev.arrayapp.entity;

import java.util.Arrays;

public class IntArrayWrapper extends AbstractArrayWrapper {
    private int[] array;

    public IntArrayWrapper(int[] array) {
        this.array = array.clone();
    }

    @Override
    public int getLength() {
        return array.length;
    }

    @Override
    public int[] getArray() {
        return array.clone();
    }

    @Override
    public void setArray(int[] array) {
        this.array = array.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}