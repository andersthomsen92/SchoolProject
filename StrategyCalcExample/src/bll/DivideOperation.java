package bll;

public class DivideOperation implements IStrategy{
    @Override
    public double doOperation(double num1, double num2) {
        if (num2 == 0){
            throw new IllegalArgumentException("Cannot divide by 0");
        }
        return num1/num2;
    }
}
