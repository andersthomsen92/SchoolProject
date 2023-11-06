package Abstract1;

public class Deer extends Animal{
    @Override
    public void sound() {
        System.out.println("A deer silently passes through the woods");
    }

    @Override
    public void eat() {
        System.out.println("A Deer eats grass");
    }

    @Override
    public void sleep() {
        System.out.println("The deer sleeps with one eye open.");
    }

    @Override
    public void action() {

    }
}
