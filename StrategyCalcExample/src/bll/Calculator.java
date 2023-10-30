package bll;

public class Calculator {
    private IStrategy strategy;

    public Calculator(IStrategy strategy){
        this.strategy = strategy;
    }

    public double doOperation(double num1, double num2){
        return strategy.doOperation(num1, num2); // This runs the current strategy(Current calculation)
    }

}
