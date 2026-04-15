package com.bazylev.arrayapp.repository;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.bazylev.arrayapp.exception.ArrayProcessingException;
import com.bazylev.arrayapp.observer.WarehouseUpdater;
import com.bazylev.arrayapp.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayRepositoryImpl implements ArrayRepository {
    private static final Logger LOGGER = LogManager.getLogger(ArrayRepositoryImpl.class);
    private static ArrayRepositoryImpl instance;
    private final Map<String, AbstractArrayWrapper> storageMap;
    private final Warehouse warehouse;
    private final WarehouseUpdater warehouseUpdater;

    private ArrayRepositoryImpl() {
        storageMap = new HashMap<>();
        warehouse = Warehouse.getInstance();
        warehouseUpdater = new WarehouseUpdater();
    }

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(AbstractArrayWrapper arrayWrapper) {
        LOGGER.info("Adding array to repository: id={}, name={}",
                arrayWrapper.getId(), arrayWrapper.getName());

        arrayWrapper.addObserver(warehouseUpdater);
        storageMap.put(arrayWrapper.getId(), arrayWrapper);
        warehouse.addArray(arrayWrapper);
    }

    @Override
    public void remove(String id) throws ArrayProcessingException {
        LOGGER.info("Removing array from repository: id={}", id);

        AbstractArrayWrapper removed = storageMap.remove(id);
        if (removed == null) {
            throw new ArrayProcessingException("Array with id " + id + " not found");
        }

        removed.removeObserver(warehouseUpdater);
        warehouse.removeArray(id);
    }

    @Override
    public void update(AbstractArrayWrapper arrayWrapper) throws ArrayProcessingException {
        LOGGER.info("Updating array in repository: id={}", arrayWrapper.getId());

        if (!storageMap.containsKey(arrayWrapper.getId())) {
            throw new ArrayProcessingException("Array with id " + arrayWrapper.getId() + " not found");
        }

        arrayWrapper.addObserver(warehouseUpdater);
        storageMap.put(arrayWrapper.getId(), arrayWrapper);
    }

    @Override
    public Optional<AbstractArrayWrapper> findById(String id) {
        LOGGER.debug("Finding array by id: {}", id);
        return Optional.ofNullable(storageMap.get(id));
    }

    @Override
    public List<AbstractArrayWrapper> findByName(String name) {
        LOGGER.debug("Finding arrays by name: {}", name);
        return storageMap.values().stream()
                .filter(arr -> arr.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findBySumGreaterThan(int sum) {
        LOGGER.debug("Finding arrays with sum greater than: {}", sum);
        return storageMap.values().stream()
                .filter(arr -> {
                    Integer arraySum = warehouse.getSum(arr.getId());
                    return arraySum != null && arraySum > sum;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findBySumLessThan(int sum) {
        LOGGER.debug("Finding arrays with sum less than: {}", sum);
        return storageMap.values().stream()
                .filter(arr -> {
                    Integer arraySum = warehouse.getSum(arr.getId());
                    return arraySum != null && arraySum < sum;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findBySumEqualTo(int sum) {
        LOGGER.debug("Finding arrays with sum equal to: {}", sum);
        return storageMap.values().stream()
                .filter(arr -> {
                    Integer arraySum = warehouse.getSum(arr.getId());
                    return arraySum != null && arraySum == sum;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findByAverageGreaterThan(double average) {
        LOGGER.debug("Finding arrays with average greater than: {}", average);
        return storageMap.values().stream()
                .filter(arr -> {
                    Double arrayAvg = warehouse.getAverage(arr.getId());
                    return arrayAvg != null && arrayAvg > average;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findByAverageLessThan(double average) {
        LOGGER.debug("Finding arrays with average less than: {}", average);
        return storageMap.values().stream()
                .filter(arr -> {
                    Double arrayAvg = warehouse.getAverage(arr.getId());
                    return arrayAvg != null && arrayAvg < average;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findByMinGreaterThan(int min) {
        LOGGER.debug("Finding arrays with min greater than: {}", min);
        return storageMap.values().stream()
                .filter(arr -> {
                    Integer arrayMin = warehouse.getMin(arr.getId());
                    return arrayMin != null && arrayMin > min;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findByMaxLessThan(int max) {
        LOGGER.debug("Finding arrays with max less than: {}", max);
        return storageMap.values().stream()
                .filter(arr -> {
                    Integer arrayMax = warehouse.getMax(arr.getId());
                    return arrayMax != null && arrayMax < max;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractArrayWrapper> findAll() {
        LOGGER.debug("Finding all arrays, count: {}", storageMap.size());
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public void sort(List<AbstractArrayWrapper> arrays, Comparator<AbstractArrayWrapper> comparator) {
        LOGGER.debug("Sorting arrays with comparator: {}", comparator.getClass().getSimpleName());
        arrays.sort(comparator);
    }

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    public void clear() {
        LOGGER.info("Clearing repository");
        for (AbstractArrayWrapper wrapper : storageMap.values()) {
            wrapper.removeObserver(warehouseUpdater);
        }
        storageMap.clear();
        warehouse.clear();
    }
}