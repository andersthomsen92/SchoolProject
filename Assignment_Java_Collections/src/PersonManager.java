import java.util.*;

public class PersonManager {

    private static PersonManager INSTANCE = null;

    private List<Person> personList;
    private Deque<Person> personQueue;


    private PersonManager(){
        personList = new ArrayList<>();
        personQueue = new LinkedList<>();
    }

    public static PersonManager getInstance(){
        if (INSTANCE != null)
            return INSTANCE;
        else
            return INSTANCE = new PersonManager();
    }

    public void addPerson(Person person){
        personQueue.add(person);
    }


    public void addPersons(Person...people){
        personQueue.addAll(Arrays.asList(people));
    }

    public Person getNextPerson(){
        return personQueue.poll();
    }

    public Person whoIsNext(){
        return personQueue.peek();
    }

    public Deque<Person> getPersonQueue() {
        return personQueue;
    }

    public void setPersonQueue(LinkedList<Person> personQueue) {
        this.personQueue = personQueue;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
