package visualizealgorithms.bll.algorithm;

public class TowersOfHanoi extends GenericAlgorithm {

    public TowersOfHanoi() {
        super("TowersOfHanoi", "Classic recursive algorithm for solving the Towers of Hanoi problem", AlgorithmType.MISC);
    }

    @Override
    public void doWork() {
        Integer n = (Integer) super.getData(); // Cast to Integer
        moveTower(n, 'A', 'C', 'B');
    }

    private void moveTower(Integer n, char source, char destination, char auxiliary) { // Use Integer instead of int
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
        } else {
            moveTower(n - 1, source, auxiliary, destination);
            System.out.println("Move disk " + n + " from " + source + " to " + destination);
            moveTower(n - 1, auxiliary, destination, source);
        }
    }

    public static void move(int n, char sourceTower, char targetTower, char tmpTower) {
        if (n > 1)
            move(n - 1, sourceTower, tmpTower, targetTower);

        System.out.println("Moves one disc from " + sourceTower + " to " + targetTower);

        if (n > 1)
            move(n - 1, tmpTower, targetTower, sourceTower);
    }
}
/*
    //Description
    The Towers of Hanoi is a classic recursive algorithm used to solve the Towers of Hanoi problem.
        The problem consists of three rods and a number of disks of different sizes that can slide onto any rod.
        The objective is to move the entire stack of disks from one rod to another, with the following rules:
        Only one disk can be moved at a time.
        Each move consists of taking the top disk from one stack and placing it onto another stack.
        No disk may be placed on top of a smaller disk.

        Pros:

        The algorithm is simple and easy to understand.
        It is guaranteed to solve the Towers of Hanoi problem optimally in 2n−12n−1 moves, where nn is the number of disks.
        The recursive nature of the algorithm closely mirrors the problem's structure.

        Cons:

        The Towers of Hanoi algorithm has an exponential time complexity of O(2n)O(2n), making it inefficient for large numbers of disks.
        It requires a recursive approach, which can lead to stack overflow errors for large input sizes.

        Limitations and Optimization:

        For large numbers of disks, consider optimizing the algorithm to reduce the number of recursive calls and improve performance.
        Implementing an iterative solution or using dynamic programming techniques can help optimize the algorithm's performance for larger input sizes.
        Consider parallelizing the algorithm to take advantage of multi-core processors and improve performance.
        Experiment with different data structures or algorithms to solve the Towers of Hanoi problem more efficiently in specific scenarios.*/
