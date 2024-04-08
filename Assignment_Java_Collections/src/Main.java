import java.time.LocalDate;
import java.util.Iterator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Person person1 = new Person(1,"Alice", LocalDate.of(1990 ,5, 12));
        Person person2 = new Person(2,"Bob", LocalDate.of(1980 ,4, 15));
        Person person3 = new Person(3,"Charlie", LocalDate.of(1944 ,6, 11));
        Person person4 = new Person(4,"Frank", LocalDate.of(1999 ,2, 24));
        Person person5 = new Person(5,"David", LocalDate.of(2004 ,7, 2));

        PersonManager personManager = PersonManager.getInstance();

        personManager.addPersons(person1,person2,person3,person4,person5);

        // goes through the whole list, and removes anyone with the name Bob.
        Iterator<Person> iterator = personManager.getPersonQueue().iterator();
        while(iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getName().equals("Bob")) {
                iterator.remove();
            }
        }

        // IntelliJ Lambda version of the above method.
        personManager.getPersonQueue().removeIf(person -> person.getName().equals("Bob"));


        // Goes through the whole list.
       for (Person person : personManager.getPersonQueue()){
            System.out.println(person);}




    }
}