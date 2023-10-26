package be;

import java.util.ArrayList;
import java.util.List;

public class PersonManager {

    List<Person> persons = new ArrayList<>();
    public Person getPerson(int id){
        for(Person p : persons)
            if (p.getId() == id)
                return p;
        return null;
    }

    public void addPerson(Person p){
        for (Person pe : persons)   // for each loop. Person pe, declares variable pe of the type Person, into a list persons.
            if (pe.equals(p))       // uses the overridden equals method from Person.Class. So it compares ID instead of RAM-memory location
                return;             // Return early if they are equal otherwise the person p, is added to the list persons.
        persons.add(p);
    }
    public void removePerson(int id){
        Person toBeRemoved = null;  // initializes a variable toBeRemoved, stores the person that is being removed
        for (Person p : persons) // for each loop, Person p, declares a variable "p" of the type "Person". "persons" is the list name.
            if(p.getId() == id) {
                toBeRemoved = p;
                break;
            }

        persons.remove(toBeRemoved);
    }

    public List<Person> getAllPersons(){
        return persons;
    }

    // Creates a list and checks if the object is of the Teacher.Class and then adds it to the list. and returns the full list.
    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<>(); // Created a new list to contain the teachers.
        for(Person p : persons)
            if (p instanceof Teacher)  // Checks if the p(Person) is an instance of the Teacher Class.
                teachers.add((Teacher) p); // If it's a Teacher object, then we add it to the list. It needs to be typecast
                                        // It needs to be typecast "((Teacher) p);" because what if its not a teacher, then it doesnt know.
        return teachers;        // Name of the list
    }

    // Creates a list for Student objects. this method checks if this Person, is a Subclass Student, and then adds it to the list. and returns the full list.
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>(); // Created a new list to contain the teachers.
        for(Person p : persons)
            if (p instanceof Student)  // Checks if the p(Person) is an instance of the Student Class.
                students.add((Student) p); // If its a Student object, then we add it to the list. It needs to be typecast
        // It needs to be typecast "((Student) p);" because what if its not a student, then it doesnt know.
        return students;        // Name of the list
    }
    public String toString(){
        String out = "ID            Name                   Email\n";
        for (Person currentPerson : persons) {
            out+= currentPerson + "\n";
        }
        return out;
    }

}
