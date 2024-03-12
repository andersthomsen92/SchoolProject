package visualizealgorithms.bll.algorithm;

public class BubbleSort extends GenericAlgorithm {

    public BubbleSort(){
        super("BubbleSort","Slow 0(2^N) sorting algorithm", AlgorithmType.SORTING);
    }


    @Override
    public void doWork() {

        int[]b = (int[]) super.getData();

        for (int i = 1; i < b.length; i++) {
            for (int j = 0; j < b.length - i; j++){
                if (b[j] > b[j + 1]) {
                    int tmp =b[j];
                    b[j] = b[j+1];
                    b[j+1] = tmp;
                }
            }
        }
    }
}
