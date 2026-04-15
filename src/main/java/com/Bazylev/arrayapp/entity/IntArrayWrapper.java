package com.bazylev.arrayapp.entity;

import java.util.Arrays;

public class IntArrayWrapper extends AbstractArrayWrapper {
    private int[] array;

    public IntArrayWrapper(int[] array) {
        super();
        this.array = array.clone();
        notifyObservers();
    }

    public IntArrayWrapper(int[] array, String name) {
        super(name);
        this.array = array.clone();
        notifyObservers();
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
        notifyObservers();
    }

    public void setElement(int index, int value) {
        if (index >= 0 && index < array.length) {
            array[index] = value;
            notifyObservers();
        }
    }

    public int getElement(int index) {
        if (index >= 0 && index < array.length) {
            return array[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public String toString() {
        return "IntArrayWrapper{id='" + getId() + "', name='" + getName() +
                "', array=" + Arrays.toString(array) + "}";
    }
}