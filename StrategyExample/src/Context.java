public class Context {
    // Calculator
    private IStrategy strategy;
    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    // Execute current strategy
    public double executeOperation(double num1, double num2) {
        return strategy.doOperation(num1, num2);  // Runs the current strategy(operation) and does the thing with the two nums
    }

}
