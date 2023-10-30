package bll;

public class SubstractOperation implements IStrategy{

    @Override
    public double doOperation(double num1, double num2) {
        System.out.println("This number " + num1 + " and this number" + num2);
        return num1-num2;

    }
}
