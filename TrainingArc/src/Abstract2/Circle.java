package Abstract2;

public class Circle extends Shape{
    private double radius;

   public Circle(double radius){
       this.radius = radius;
   }

    @Override
    public double calculatePerimeter() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculateArea() {
        return 2 * Math.PI * radius;
    }
}
