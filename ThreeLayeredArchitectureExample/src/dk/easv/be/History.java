package dk.easv.be;

/**
 * This class takes care of everything according to historical calculations.
 * */
public class History {
    private final double Value;
    private final double Vat;
    private final double Result;

    public double GetValue()
    {
        return Value;
    }

    public double GetVat()
    {
        return Vat;
    }

    public double GetResult()
    {
        return Result;
    }

    public History(double value, double vat, double result)
    {
        this.Value = value;
        this.Vat = vat;
        this.Result = result;
    }
}
