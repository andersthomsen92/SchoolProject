package visualizealgorithms.bll.algorithm;

public class BitonicSort extends GenericAlgorithm {

    public BitonicSort() {
        super("BitonicSort", "Efficient O(N log^2 N) sorting algorithm", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {
        int[] array = (int[]) super.getData();
        bitonicSort(array, 0, array.length, true);
    }

    private void bitonicSort(int[] array, int low, int count, boolean dir) {
        if (count > 1) {
            int k = count / 2;
            bitonicSort(array, low, k, true);
            bitonicSort(array, low + k, k, false);
            bitonicMerge(array, low, count, dir);
        }
    }

    private void bitonicMerge(int[] array, int low, int count, boolean dir) {
        if (count > 1) {
            int k = greatestPowerOfTwoLessThan(count);
            for (int i = low; i < low + count - k; i++) {
                compare(array, i, i + k, dir);
            }
            bitonicMerge(array, low, k, dir);
            bitonicMerge(array, low + k, count - k, dir);
        }
    }

    private void compare(int[] array, int i, int j, boolean dir) {
        if (dir == (array[i] > array[j])) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private int greatestPowerOfTwoLessThan(int n) {
        int k = 1;
        while (k > 0 && k < n) {
            k = k << 1;
        }
        return k >> 1;
    }
}
    //Description
    /*    Bitonic Sort is a highly parallelizable sorting algorithm that works by recursively sorting two subsequences of the input sequence in opposite order, and then merging the results.
            It is particularly efficient on parallel architectures.
            Bitonic Sort has a time complexity of O(N log^2 N), where N is the number of elements.

            Pros:

            Bitonic Sort is highly parallelizable, making it suitable for parallel architectures and parallel processing environments.
            It has a predictable time complexity of O(N log^2 N), making it suitable for large datasets.
            Bitonic Sort is stable when sorting elements with unique keys.

            Cons:

            Bitonic Sort is less efficient than some other sorting algorithms, such as quicksort or mergesort, on single-threaded architectures.
            It may not perform optimally on small datasets or datasets with specific characteristics.

            Limitations and Optimization:

            Bitonic Sort is primarily designed for parallel architectures, so its performance may vary depending on the underlying hardware and parallel processing capabilities.
            Consider optimizing the implementation for specific parallel architectures or hardware configurations to maximize performance.
            Experiment with different partitioning strategies and merge algorithms to optimize performance for different input sizes and distributions.
            Evaluate the performance of Bitonic Sort against other sorting algorithms in the context of your specific use case to determine its suitability and potential for optimization.*/
