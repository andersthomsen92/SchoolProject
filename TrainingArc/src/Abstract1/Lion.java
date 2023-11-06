package Abstract1;

public class Lion extends Animal{
    @Override
    public void sound() {
        System.out.println("Lion Roars!");
    }

    @Override
    public void eat() {
        System.out.println("The Lion eats meat");
    }

    @Override
    public void sleep() {
        System.out.println("The Lion sleeps in the savannah");
    }

    @Override
    public void action() {

    }
}
