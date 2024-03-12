
package visualizealgorithms.bll.algorithm;

/**
 *
 * @author Søren Spangsberg
 */
public interface IAlgorithm {
    
    /**
     * The implementation of the algorithm
     */
    public void doWork();

    /**
     * Get algorithm related data (unsorted array etc.)
     * 
     * @return 
     */
    public Object getData();
    
    
    /**
     * Set algorithm related data (unsorted array etc.)
     * 
     * @param data 
     */
    public void setData(Object data);
    
    
    /**
     * 
     * @return 
     */
    public AlgorithmType getType();
}
