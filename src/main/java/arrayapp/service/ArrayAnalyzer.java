package arrayapp.service;

import java.util.Optional;

public interface ArrayAnalyzer {

    Optional<Integer> findMin(int[] array);

    Optional<Integer> findMax(int[] array);

    Optional<Integer> calculateSum(int[] array);

    Optional<Double> calculateAverage(int[] array);
}