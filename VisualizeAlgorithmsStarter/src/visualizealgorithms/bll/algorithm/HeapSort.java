package visualizealgorithms.bll.algorithm;

public class HeapSort extends GenericAlgorithm {

    public HeapSort(){
        super("HeapSort", "Efficient O(N log N) sorting algorithm", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {
        int[] array = (int[]) super.getData();
        int n = array.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Call max heapify on the reduced heap
            heapify(array, i, 0);
        }
    }

    private void heapify(int[] array, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(array, n, largest);
        }
    }
    //Description
    /*  Heap Sort is chosen for its efficiency with an average time complexity of O(N log N).
        The algorithm first builds a max heap from the input array.
        It then repeatedly extracts the maximum element (root of the heap) and reconstructs the heap until the array is sorted.
        Heap Sort utilizes the heap data structure to maintain the partially sorted property.

        Pros:

        Efficient sorting algorithm with a time complexity of O(N log N) in all cases.
        In-place sorting algorithm, meaning it doesn't require extra space proportional to the size of the input array.
        Guarantees stable sorting if implemented carefully.

        Cons:

        Heap Sort is not a stable sorting algorithm, meaning the relative order of equal elements might not be preserved.
        While it performs well for large datasets, its recursive nature may lead to stack overflow errors for extremely large arrays.
        Heap Sort is slower in practice compared to Quick Sort for most applications due to its larger constant factors.

        Limitations and Optimization:

        This implementation can be optimized by using a bottom-up approach to build the initial heap, which reduces the number of comparisons required during heap construction.
        To mitigate the worst-case time complexity, you can implement techniques like using a min heap instead of a max heap or using a randomized version of Heap Sort.
        Consider adding checks for edge cases, such as empty arrays or arrays with only one element, to improve robustness.*/

}
