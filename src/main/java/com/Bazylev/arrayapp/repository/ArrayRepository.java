package com.bazylev.arrayapp.repository;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.bazylev.arrayapp.exception.ArrayProcessingException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayRepository {
    void add(AbstractArrayWrapper arrayWrapper);
    void remove(String id) throws ArrayProcessingException;
    void update(AbstractArrayWrapper arrayWrapper) throws ArrayProcessingException;

    Optional<AbstractArrayWrapper> findById(String id);
    List<AbstractArrayWrapper> findByName(String name);

    List<AbstractArrayWrapper> findBySumGreaterThan(int sum);
    List<AbstractArrayWrapper> findBySumLessThan(int sum);
    List<AbstractArrayWrapper> findBySumEqualTo(int sum);

    List<AbstractArrayWrapper> findByAverageGreaterThan(double average);
    List<AbstractArrayWrapper> findByAverageLessThan(double average);

    List<AbstractArrayWrapper> findByMinGreaterThan(int min);
    List<AbstractArrayWrapper> findByMaxLessThan(int max);

    List<AbstractArrayWrapper> findAll();

    void sort(List<AbstractArrayWrapper> arrays, Comparator<AbstractArrayWrapper> comparator);

    int size();
    void clear();
}