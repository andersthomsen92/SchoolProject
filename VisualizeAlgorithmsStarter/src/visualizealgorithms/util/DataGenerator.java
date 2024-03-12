
package visualizealgorithms.util;

//Java imports
import java.util.Random;

//Project imports
import visualizealgorithms.be.User;
import visualizealgorithms.bll.DataType;

/**
 * @author Søren Spangsberg
 */
public class DataGenerator {

    private static DataGenerator _instance = null;

    private DataGenerator() {}

    public static DataGenerator getInstance() {
        if (_instance == null) {
            _instance = new DataGenerator();
        }
        return _instance;
    }


    /**
     * Convenience method to generate random test data
     * (either Integer or BE-entities (User))
     *
     * @param type
     * @param numberOfInputs
     * @return
     */
    public Comparable[] generateTestData(DataType type, int numberOfInputs) {

        Comparable[] output = null;

        switch (type) {
            case Integer:
                output = generateRandomIntegersComparable(numberOfInputs, 1);
                break;
            case User:
                output = generateRandomBEs(numberOfInputs);
                break;
            default:
                output = new Comparable[0];
                break;
        }
        return output;
    }


    /**
     * @param size
     * @param multiplier
     * @return Array of Integers (Comparable)
     */

    public Comparable[] generateRandomIntegersComparable(int size, int multiplier) {
        Integer[] randomNumbers = new Integer[size];
        Random r = new Random();

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = r.nextInt(size * multiplier);
            //System.out.print(randomNumbers[i] + ",");
        }
        return randomNumbers;
    }


    /**
     * @param size
     * @param multiplier
     * @return Array of Integers (Comparable)
     */
    public int[] generateRandomIntegers(int size, int multiplier) {
        int[] randomNumbers = new int[size];
        Random r = new Random();

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = r.nextInt(size * multiplier);
            //System.out.print(randomNumbers[i] + ",");
        }
        return randomNumbers;
    }


    /**
     * @return
     */
    public Comparable[] generateRandomBEs(int size) {
        User[] randomBEs = new User[size];
        Random r = new Random();

        for (int i = 0; i < randomBEs.length; i++) {
            randomBEs[i] = new User(getRandomCharacters(10), getRandomCharacters(10), r.nextInt(100));
        }
        return randomBEs;
    }


    /**
     * @param size
     * @return
     */
    private String getRandomCharacters(int size) {

        Random r = new Random();
        char[] subset = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

        char buf[] = new char[size];

        for (int i = 0; i < buf.length; i++) {
            int index = r.nextInt(subset.length);
            buf[i] = subset[index];
        }

        return new String(buf);
    }
}
