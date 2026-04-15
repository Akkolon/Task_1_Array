package com.bazylev.arrayapp.warehouse;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static final Logger LOGGER = LogManager.getLogger(Warehouse.class);
    private static Warehouse instance;
    private final Map<String, ArrayData> storageMap;

    private Warehouse() {
        storageMap = new HashMap<>();
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void updateArrayData(AbstractArrayWrapper arrayWrapper) {
        LOGGER.debug("Warehouse updating data for array: {}", arrayWrapper.getId());
        recalculateArrayData(arrayWrapper);
    }

    public void addArray(AbstractArrayWrapper arrayWrapper) {
        LOGGER.info("Adding array to warehouse: {}", arrayWrapper.getId());
        recalculateArrayData(arrayWrapper);
    }

    public void removeArray(String arrayId) {
        LOGGER.info("Removing array from warehouse: {}", arrayId);
        storageMap.remove(arrayId);
    }

    private void recalculateArrayData(AbstractArrayWrapper arrayWrapper) {
        int[] array = arrayWrapper.getArray();

        if (array == null || array.length == 0) {
            LOGGER.warn("Cannot recalculate empty or null array");
            return;
        }

        int min = array[0];
        int max = array[0];
        int sum = 0;

        for (int value : array) {
            sum += value;
            if (value < min) min = value;
            if (value > max) max = value;
        }

        double average = (double) sum / array.length;

        ArrayData data = new ArrayData(min, max, sum, average);
        storageMap.put(arrayWrapper.getId(), data);

        LOGGER.info("Recalculated for array {}: min={}, max={}, sum={}, avg={}",
                arrayWrapper.getId(), min, max, sum, average);
    }

    public ArrayData getArrayData(String arrayId) {
        return storageMap.get(arrayId);
    }

    public Integer getMin(String arrayId) {
        ArrayData data = storageMap.get(arrayId);
        return data != null ? data.getMin() : null;
    }

    public Integer getMax(String arrayId) {
        ArrayData data = storageMap.get(arrayId);
        return data != null ? data.getMax() : null;
    }

    public Integer getSum(String arrayId) {
        ArrayData data = storageMap.get(arrayId);
        return data != null ? data.getSum() : null;
    }

    public Double getAverage(String arrayId) {
        ArrayData data = storageMap.get(arrayId);
        return data != null ? data.getAverage() : null;
    }

    public void clear() {
        storageMap.clear();
        LOGGER.info("Warehouse cleared");
    }

    public int size() {
        return storageMap.size();
    }
}