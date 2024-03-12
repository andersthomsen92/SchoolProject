package visualizealgorithms.bll.algorithm;

import java.util.Arrays;

public class TimSort extends GenericAlgorithm {

    private static final int MIN_MERGE = 32;

    public TimSort() {
        super("TimSort", "Efficient O(N log N) sorting algorithm", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {
        int[] array = (int[]) super.getData();
        int n = array.length;

        int minRun = minRunLength(n);

        // Sort individual subarrays of size MIN_MERGE
        for (int i = 0; i < n; i += minRun) {
            insertionSort(array, i, Math.min(i + minRun - 1, n - 1));
        }

        // Merge runs
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                merge(array, left, mid, right);
            }
        }
    }

    private int minRunLength(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= left && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = Arrays.copyOfRange(array, left, left + n1);
        int[] rightArray = Arrays.copyOfRange(array, mid + 1, mid + 1 + n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}

// Description
       /*    TimSort is a hybrid sorting algorithm that combines the strengths of merge sort and insertion sort.
    It sorts small subarrays with insertion sort and then merges them efficiently using merge sort.
    TimSort identifies "runs" in the array, which are sequences of elements that are already sorted.
    It then merges these runs together using a modified merge sort algorithm.

    Pros:

    Efficient sorting algorithm with an average time complexity of O(N log N).
    Stable sorting algorithm, meaning the relative order of equal elements is preserved.
            Well-suited for sorting real-world data as it performs well on partially sorted arrays and small datasets.

            Cons:

    Requires additional space proportional to the size of the input array for merging the runs.
    While it performs well for most use cases, TimSort may not be as efficient as other sorting algorithms like Quick Sort for certain types of data.

    Limitations and Optimization:

    This implementation follows the standard TimSort algorithm, but you can experiment with different values for the minimum run length to optimize performance for specific inputs.
    To mitigate the space complexity issue, you can implement an in-place version of TimSort or use an alternative sorting algorithm for memory-constrained environments.
    Consider adding checks for edge cases, such as empty arrays or arrays with only one element, to improve robustness.*/

