package be;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public void start(){
        List<Person> persons = new ArrayList<>();


        PersonManager pm = new PersonManager();


        for (Person currentPerson: persons)     // Redundant now.
        {
            System.out.println(currentPerson);
        }

        Teacher teacher = new Teacher(202, "Bent H. Pedersen","bhp");
        teacher.setEmail("bhp@easv.dk");
        teacher.addSubjects("Programming");
        teacher.addSubjects("ITO");
        pm.addPerson(teacher);

        Student student = new Student(105, "Bo Ibsen", "CS");
        student.setEmail("bib@easv.dk");
        pm.addPerson(student);
        student.addGrade(new GradeInfo(7, "CS"));
        student.addGrade(new GradeInfo(10,"ITO"));


        System.out.println("ID            Name                   Email");
        System.out.println("All Persons\n" + pm.getAllPersons());
        System.out.println("All Teachers\n" + pm.getAllTeachers());
        System.out.println("All Students\n" + pm.getAllStudents());
        new MainMenu().run();

        /*      This doesnt work now, since the Person Class is a Person abstract class now. And abstract classes cant be made into an object
        List<Person> persons = new ArrayList<>();
        Person p = new Person(100, "Hans Nielsen");
        p.setEmail("hni@easv.dk");
        Person p2 = new Person(101, "Niels Hansen");
        p2.setEmail("nha@easv.dk");
        Person p3 = new Person(102, "Ib Boesen");
        p3.setEmail("ibo@easv.dk");

        PersonManager pm = new PersonManager();

        pm.addPerson(p);
        pm.addPerson(p2);
        pm.addPerson(p3);


        System.out.println(pm);
        System.out.println("Getting Person ID: "  + pm.getPerson(100)); */

    }
}