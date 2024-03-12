package visualizealgorithms.bll.algorithm;

public class SelectionSort extends GenericAlgorithm{

    public SelectionSort(){
        super("SelectionSort", "Simple 0(N^2) sorting algorithm", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {

        int[]b = (int[]) super.getData();

        for (int i = 0; i < b.length - 1; i++)
        {
            int minIndex = i;
            for (int j = i +1; j < b.length; j++) {
                if (b[j] < b[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = b[i];
            b[i] = b[minIndex];
            b[minIndex] = tmp;
        }
    }
}
