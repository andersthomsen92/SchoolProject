package be;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public void start(){
        List<Person> persons = new ArrayList<>();
        Person p = new Person(100, "Hans Nielsen");
        p.setEmail("hni@easv.dk");
        Person p2 = new Person(101, "Niels Hansen");
        p2.setEmail("nha@easv.dk");
        Person p3 = new Person(102, "Ib Boesen");
        p3.setEmail("ibo@easv.dk");

        persons.add(p);
        persons.add(p2);
        persons.add(p3);

        for (Person currentPerson: persons)
        {
            System.out.println(currentPerson);
        }

        Teacher teacher = new Teacher(202, "Bent H. Pedersen","bhp");
        teacher.setEmail("bhp@easv.dk");
        teacher.addSubjects("Programming");
        teacher.addSubjects("ITO");
        persons.add(teacher);

        Student student = new Student(105, "Bo Ibsen", "CS");
        student.setEmail("bib@easv.dk");
        persons.add(student);
        student.addGrade(new GradeInfo(7, "CS"));
        student.addGrade(new GradeInfo(10,"ITO"));


        System.out.println("ID            Name                   Email");
        for (Person currentPerson: persons)
        {
            System.out.println(currentPerson);
        }


    }
}
