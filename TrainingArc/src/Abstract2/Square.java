package Abstract2;

public class Square extends Shape{

    private double sideA, sideB;

   public Square(double sideA, double sideB){
       this.sideA = sideA;
       this.sideB = sideB;
   }

    @Override
    public double calculatePerimeter() {
        return sideA*sideB;
    }

    @Override
    public double calculateArea() {
        return (sideA+sideB)*2;
    }
}
