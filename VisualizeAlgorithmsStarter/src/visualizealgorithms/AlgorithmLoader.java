package visualizealgorithms;

//Project imports
import visualizealgorithms.bll.algorithm.*;

//Java imports
import java.util.ArrayList;

/**
 * Convenience class that adds algorithms and handles inputs
 */
public class AlgorithmLoader {

    private String inputs = "";
    private ArrayList<IAlgorithm> algorithms = new ArrayList<>();

    public AlgorithmLoader() {
        //Modify default inputs
        inputs = "1000;2000;4000;8000;16000;32000;64000;128000;256000;512000;1024000";

        //Add implemented/selected algorithms here..
        algorithms.add(new BubbleSort());
        algorithms.add(new SelectionSort());
        algorithms.add(new InsertionSort());
        algorithms.add(new QuickSort());
        algorithms.add(new MergeSort());
        algorithms.add(new TimSort());
        algorithms.add(new HeapSort());
        algorithms.add(new RadixSort());
        algorithms.add(new BitonicSort());

        //algorithms.add(new TowersOfHanoi());

        algorithms.add(new RecursiveMergeSort());

        //more algorithms here :)
    }

    public String getInputs() {
        return inputs;
    }

    public ArrayList<IAlgorithm> getAlgorithms() {
        return algorithms;
    }
}
