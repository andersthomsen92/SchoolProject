
public class Main {
    public static void main(String[] args) {

        Context calc = new Context(new OperationAdd());

        calc.executeOperation(10,20);

        double result = calc.executeOperation(10,20);

        System.out.println("Result: " + result);

        calc = new Context(new OperationSubtract());

        double result2 = calc.executeOperation(10,20);

        System.out.println("Result: " + result2);


    }
}