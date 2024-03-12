
package visualizealgorithms.bll.algorithm;

/**
 *
 * @author Søren Spangsberg
 */
public abstract class GenericAlgorithm implements IAlgorithm {
        
    private AlgorithmType type = AlgorithmType.NOT_SET;
    private String name = "";
    private String description = "";
    private Object data;

    /**
     * 
     * @return 
     */
    @Override
    public AlgorithmType getType() {
        return type;
    }

    
    /**
         * 
     * @param name
     * @param description 
     */
    public GenericAlgorithm(String name, String description, AlgorithmType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }    

    /**
     * Get a friendly string representation of the algorithm
     * 
     * @return 
     */
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }  

    /**
     * Get algorithm related data (unsorted array etc.)
     * 
     * @return 
     */
    @Override
    public Object getData() {
        return data;
    }
    
    
    /**
     * Set algorithm related data (unsorted array etc.)
     * 
     * @param data 
     */
    @Override
    public void setData(Object data) {
        this.data = data;
    }
}
