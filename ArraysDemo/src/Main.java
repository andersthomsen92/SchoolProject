import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)
    {
        //funWithClassesAndArraysAndForeEachLoop();

        ArrayList<Student> students = new ArrayList<>();
        /* List is an interface that extends the collection.
         so an alternative way of doing it is:
         List<Student> students = new LinkedList<>();
         produces the same result, but in a different way.
         LinkedList are better when you need to make changes to the list often.
         And Arrays/ArrayList is better when finding specific indexes, as it doesn't need to jump around to find it.
         Rule of thumb, when in doubt, go with an arraylist. :)  */

        System.out.println("Arr size: " + students.size());

        students.add(new Student("Willy"));

        System.out.println("Arr size: " + students.size());

        students.add(new Student("Wonka"));
        students.add(new Student("Jeppe"));


        for (Student student: students)
        {
            System.out.println(student.getName());
        }

        Student stud = students.get(0);
        System.out.println("Student nr. 0 = " + stud.getName());

        System.out.println("REMOVING ELEMENT");
        students.remove(1);
        System.out.println("Arr size: " + students.size());
        for (Student student: students)
        {
            System.out.println(student.getName());
        }




    }

        public static void funWithClassesAndArraysAndForeEachLoop()
        {
            Student[] students = new Student[3];

            students[0] = new Student("David Larry");
            students[1] = new Student("Luke Perry");
            students[2] = new Student("The Platypus, Perry");

            //System.out.println("First student = " + students[2].getName());

            //  Local Variable(matches the Arrayname : Objects in the arraylist
            for (Student oneOfTheStudents: students)
            {
                System.out.println(oneOfTheStudents.getName());
            }


        }
        // ArrayList cannnot store simple datatypes.


       /* int[] arrInt = {40,55,63,17,22,68,89,97,89};

        int[] arrInt2 = new int[9];
        arrInt2[0] = 40;
        arrInt2[1] = 55;
        // Etc... These are two of the ways to create arrays

        System.out.println("number 0 in array: " + arrInt2[0]);
        */



}