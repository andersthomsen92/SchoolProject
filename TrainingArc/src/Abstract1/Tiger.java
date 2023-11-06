package Abstract1;

public class Tiger extends Animal {



    @Override
    public void sound() {
        System.out.println("Tiger growls!");
    }

    @Override
    public void eat() {
        System.out.println("Tiger eats the meat in the jungle");
    }

    @Override
    public void sleep() {
        System.out.println("The tiger sleeps in the jungle, hidden");

    }

    @Override
    public void action() {
        sound();
        eat();
        sleep();
    }
}
