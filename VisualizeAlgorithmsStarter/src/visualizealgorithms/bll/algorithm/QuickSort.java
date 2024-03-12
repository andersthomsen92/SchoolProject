package visualizealgorithms.bll.algorithm;

public class QuickSort extends GenericAlgorithm {

    public QuickSort(){
        super("QuickSort", "Efficient O(N log N) sorting algorithm", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {
        int[] array = (int[]) super.getData();
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
// Description
/*
    The Quick Sort algorithm is chosen for its efficiency with an average time complexity of O(N log N).
    The algorithm divides the array into two sub-arrays recursively and sorts them independently.
    It selects a pivot element and partitions the array around the pivot, such that elements smaller than the pivot are on the left, and elements greater than the pivot are on the right.
    This implementation uses the last element as the pivot and follows the Hoare partition scheme.

            Pros:

    Efficient sorting algorithm with an average time complexity of O(N log N).
    In-place sorting algorithm, meaning it doesn't require extra space proportional to the size of the input array.
    Suitable for large datasets.

    Cons:

    Quick Sort's worst-case time complexity is O(N^2) when the pivot selection is poor, such as selecting the smallest or largest element as the pivot.
    It's not a stable sorting algorithm, meaning the relative order of equal elements might not be preserved.
    It may require careful implementation to avoid stack overflow errors for very large arrays due to recursive calls.

    Limitations and Optimization:

    This implementation follows the standard recursive approach, which might encounter stack overflow errors for large arrays. To optimize it, you can implement an iterative version of Quick Sort using a stack or use tail recursion optimization.
    To mitigate the worst-case time complexity, you can implement techniques like random pivot selection or median-of-three pivot selection.
            While this implementation uses the Hoare partition scheme, you can experiment with other partitioning schemes like the Lomuto partition scheme or Dutch National Flag algorithm for better performance in certain cases.
    Consider adding checks for edge cases, such as empty arrays or arrays with only one element, to improve robustness.*/
}
