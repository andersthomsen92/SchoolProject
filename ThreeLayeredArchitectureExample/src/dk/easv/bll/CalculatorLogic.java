package dk.easv.bll;

public class CalculatorLogic {

    public double CalculateNewValueAddedPercentage(double value, double vat)
    {
        double percentage = (vat + 100)/100;
        return value * percentage;
    }
}
