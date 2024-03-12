package visualizealgorithms.bll.algorithm;

public class MergeSort extends GenericAlgorithm {

    public MergeSort(){
        super("MergeSort", "Efficient O(N log N) sorting algorithm", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {
        int[] array = (int[]) super.getData();
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
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
    // Description
    /*    Merge Sort is chosen for its efficiency with an average time complexity of O(N log N).
        The algorithm recursively divides the array into halves until each sub-array has only one element, then merges the sorted sub-arrays.
        It utilizes a divide-and-conquer approach.

        Pros:

        Efficient sorting algorithm with a stable time complexity of O(N log N) in all cases.
        Guarantees stable sorting, preserving the relative order of equal elements.
                Well-suited for sorting linked lists due to its sequential access pattern.

                Cons:

        Merge Sort requires additional space proportional to the size of the input array for the temporary arrays used in the merging process.
        While it performs well for large datasets, its recursive nature may lead to stack overflow errors for extremely large arrays.

        Limitations and Optimization:

        This implementation can be optimized by avoiding the creation of temporary arrays in each recursion. Instead, you can create a single temporary array at the beginning and pass it through recursive calls to avoid frequent memory allocation and deallocation.
        To mitigate the space complexity issue, you can implement an iterative version of Merge Sort or use an in-place merge algorithm like the bottom-up merge sort variant.
        Consider adding checks for edge cases, such as empty arrays or arrays with only one element, to improve robustness.*/
}
