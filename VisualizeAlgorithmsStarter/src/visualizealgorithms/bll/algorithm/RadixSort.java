package visualizealgorithms.bll.algorithm;

import java.util.ArrayList;
import java.util.List;

public class RadixSort extends GenericAlgorithm {

    public RadixSort() {
        super("RadixSort", "Efficient O(N*K) sorting algorithm for integers", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {
        int[] array = (int[]) super.getData();
        int maxDigitCount = getMaxDigitCount(array);

        for (int i = 0; i < maxDigitCount; i++) {
            List<Integer>[] digitBuckets = new ArrayList[10];
            for (int j = 0; j < 10; j++) {
                digitBuckets[j] = new ArrayList<>();
            }

            for (int num : array) {
                int digit = getDigit(num, i);
                digitBuckets[digit].add(num);
            }

            int index = 0;
            for (List<Integer> bucket : digitBuckets) {
                for (int num : bucket) {
                    array[index++] = num;
                }
            }
        }
    }

    private int getMaxDigitCount(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            int digitCount = getDigitCount(num);
            if (digitCount > max) {
                max = digitCount;
            }
        }
        return max;
    }

    private int getDigit(int num, int place) {
        return (int) (num / Math.pow(10, place)) % 10;
    }

    private int getDigitCount(int num) {
        if (num == 0) {
            return 1;
        }
        return (int) Math.log10(Math.abs(num)) + 1;
    }
}
    //Description
    /*    Radix Sort is a non-comparative integer sorting algorithm that sorts numbers by processing individual digits.
        It sorts numbers digit by digit, starting from the least significant digit (rightmost) to the most significant digit (leftmost).
        Radix Sort is efficient for sorting numbers with a fixed number of digits.

        Pros:

        Radix Sort has a time complexity of O(N*K), where N is the number of elements and K is the number of digits in the largest number.
        It is a stable sorting algorithm, preserving the relative order of equal elements.
        Radix Sort is suitable for sorting numbers with a fixed number of digits, such as integers.

        Cons:

        Radix Sort requires additional space for creating digit buckets, which can be significant for large arrays or numbers with many digits.
        It is not suitable for sorting numbers with variable lengths or non-integer data types.

        Limitations and Optimization:

        Ensure that the input array contains only non-negative integers, as the implementation assumes non-negative numbers.
        Consider optimizing the implementation to handle negative numbers as well, either by modifying the algorithm or preprocessing the input data.
        Experiment with different methods for distributing elements into digit buckets, such as counting sort or bucket sort, to optimize performance.
        Test the algorithm with various input sizes and distributions to evaluate its performance and identify potential areas for optimization.*/
