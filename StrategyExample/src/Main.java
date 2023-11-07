
public class Main {
    public static void main(String[] args) {

        //Context calc = new Context(new OperationAdd());
        Context calc = Context.getInstance();
        Context.getInstance().setStrategy(new OperationAdd());
        calc.executeOperation(10,20);

        double result = Context.getInstance().executeOperation(10,20);

        System.out.println("Result: " + result);

        calc.setStrategy(new OperationAdd());

        Context.getInstance().setStrategy(new OperationDivide());

        double result2 = calc.executeOperation(100,10);

        System.out.println("Result: " + result2);


    }
}