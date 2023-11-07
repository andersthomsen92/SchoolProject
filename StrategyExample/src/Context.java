public class Context {
    // Calculator




    private static Context singleInstance = null;

    public static Context getInstance(){
        if (singleInstance == null)
            singleInstance = new Context(new OperationAdd());
        return singleInstance;
    }


    private IStrategy strategy;
    private Context(IStrategy strategy){
        this.strategy = strategy;
    }


    // Execute current strategy
    public double executeOperation(double num1, double num2) {
        return strategy.doOperation(num1, num2);  // Runs the current strategy(operation) and does the thing with the two nums
    }

    public void setStrategy(IStrategy strategy)
    {
        this.strategy = strategy;
    }

}
