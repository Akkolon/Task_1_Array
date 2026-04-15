package com.bazylev.arrayapp.warehouse;

public class ArrayData {
    private final int min;
    private final int max;
    private final int sum;
    private final double average;

    public ArrayData(int min, int max, int sum, double average) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return String.format("ArrayData{min=%d, max=%d, sum=%d, average=%.2f}",
                min, max, sum, average);
    }
}