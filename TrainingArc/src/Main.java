import Abstract1.Animal;
import Abstract1.Deer;
import Abstract1.Lion;
import Abstract1.Tiger;
import Abstract2.Circle;
import Abstract2.Square;
import Abstract2.Triangle;

public class Main {
    public static void main(String[] args) {


        StringExercise stringExercise = new StringExercise();
        StringExercise.stringReverse("THIS IS A TEST");

        /*
        - Remember to use the correct for loops, if you need to add and replace often.
        use an ArrayList instead of an Array. Arrays are more optimized but less flexible.
        - Remember toString, otherwise you will just get the RAM address.
        */
        ArrayExercises arrayExercise = new ArrayExercises();
        //arrayExercise.Exercise12();


        //TODO Abstract Classes!
        /*
        - Remember to create an constructor that can take the inputs
        - Remember to create variables also :)
         */
        //FIXME Abstract Exercise 1 and 4
/*        Animal lion = new Lion();
        lion.sound();
        lion.eat();
        lion.sleep();
        Animal tiger = new Tiger();
        tiger.sound();
        tiger.action();
        Animal deer = new Deer();
        deer.eat();
        deer.sound();
        deer.sleep();*/
        //FIXME Abstract Exercise 2
        /*
        double radius = 4.0;
        Circle circle = new Circle(radius);
        double side1 = 3.0, side2 = 4.0, side3 = 5.0;
        Triangle triangle = new Triangle(side1, side2, side3);
        // Two ways to parse values into the object.
        Square square = new Square(4.0,6.0);
        System.out.println("Radius of the Circle"+radius);
        System.out.println("Area of the Circle: " + circle.calculateArea());
        System.out.println("Perimeter of the Circle: " + circle.calculatePerimeter());
        System.out.println("\nSides of the Triangle are: "+side1+','+side2+','+side3);
        System.out.println("Area of the Triangle: " + triangle.calculateArea());
        System.out.println("Perimeter of the Triangle: " + triangle.calculatePerimeter());
        System.out.println();
        System.out.println("Perimeter of Square: " + square.calculatePerimeter());
        System.out.println("Area of Square: " + square.calculateArea());
        */
    }
}
