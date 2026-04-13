package arrayapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class ArraySorterImpl implements ArraySorter {
    private static final Logger LOGGER = LogManager.getLogger(ArraySorterImpl.class);

    @Override
    public int[] bubbleSort(int[] array) {
        LOGGER.debug("Performing bubble sort on array");

        if (array == null) {
            LOGGER.warn("Array is null, returning empty array");
            return new int[0];
        }

        int[] result = array.clone();
        int length = result.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (result[j] > result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        LOGGER.debug("Bubble sort completed. Result: {}", Arrays.toString(result));
        return result;
    }

    @Override
    public int[] quickSort(int[] array) {
        LOGGER.debug("Performing quick sort on array");

        if (array == null) {
            LOGGER.warn("Array is null, returning empty array");
            return new int[0];
        }

        int[] result = array.clone();
        quickSortRecursive(result, 0, result.length - 1);

        LOGGER.debug("Quick sort completed. Result: {}", Arrays.toString(result));
        return result;
    }

    private void quickSortRecursive(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);
            quickSortRecursive(array, low, partitionIndex - 1);
            quickSortRecursive(array, partitionIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}