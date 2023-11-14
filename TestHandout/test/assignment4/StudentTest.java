/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

import java.lang.reflect.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jeppjleemoritzled
 */
public class StudentTest
{

    @Test
    public void testGetSetMethods()
    {
        Method[] methods = Student.class.getMethods();

        ArrayList<String> methodsMustBe = new ArrayList(
                Arrays.asList(
                        "getStudentId", "setStudentId",
                        "getName", "setName",
                        "getMultiplier", "setMultiplier"
                )
        );

        methodsMustBe.forEach((String mm) ->
        {
            boolean found = false;
            for (Method method : methods)
            {
                if (method.getName().equals(mm))
                {
                    found = true;
                }
            }
            if (!found)
            {
                fail("Missing get/set method in Student: " + mm);
            }
        });

        for (Method m : Student.class.getMethods())
        {
            Parameter[] params = m.getParameters();
            if (m.getName().startsWith("get"))
            {
                if (params.length != 0)
                {
                    fail("No parameters allowed in get method: " + m.getName());
                }
            }
        }

        for (Method m : Student.class.getMethods())
        {
            Parameter[] params = m.getParameters();
            if (m.getName().startsWith("set"))
            {
                if (params.length == 0)
                {
                    fail("Parameters in set method missing: " + m.getName());
                }
            }
        }

    }

    /**
     * Test of get and setMultiplier method, of class Student.
     */
    @Test
    public void testGetSetMultiplier()
    {
        double badMultiplier = 0.06;
        Student instance = new Student(1, "Piotr Stegonovskij");
        assertFalse("Should return false if value is incorrect", instance.setMultiplier(badMultiplier));
        assertFalse("Should not change value if incorrect", instance.getMultiplier() == badMultiplier);

        instance = new Student(2, "Jeppe Led");
        double multiplier = 1.06 * 1.08;
        assertTrue("Correct value not allowed?", instance.setMultiplier(multiplier));
        assertTrue("Value did not change although correct", instance.getMultiplier() == multiplier);
        multiplier = 1.06;
        assertTrue("Correct value not allowed?", instance.setMultiplier(multiplier));
        assertTrue("Value did not change although correct", instance.getMultiplier() == multiplier);
        multiplier = 1.08;
        assertTrue("Correct value not allowed?", instance.setMultiplier(multiplier));
        assertTrue("Value did not change although correct", instance.getMultiplier() == multiplier);
    }

    /**
     * Test of get and setGrade method, of class Student.
     */
    @Test
    public void testSetGrade()
    {
        double mult1 = 1.06;
        double mult2 = 1.08;

        int grade = 12;
        double result = grade * mult1 * mult2;
        Student instance = new Student(3, "Trine Thomsen");
        instance.setMultiplier(mult1 * mult2);
        assertTrue("Should return true when value is correct", instance.setGrade(grade));
        assertEquals("Should not change value if incorrect", instance.getComputedGrade(), result, 0.01);

        grade = -3;
        result = grade * mult1 * mult2;
        instance = new Student(4, "Bent Pedersen");
        instance.setMultiplier(mult1 * mult2);
        assertTrue("Should return false if value is incorrect", instance.setGrade(grade));
        assertEquals("Should not change value if incorrect",  instance.getComputedGrade(), result, 0.01);

        instance = new Student(5, "Henrik Kuhl");
        grade = 12;
        assertTrue("Correct value allowed?", instance.setGrade(grade));
        assertEquals("Value has not changed, although it is correct?", instance.getComputedGrade(), grade * mult1, 0.0);

        grade = 10;
        assertTrue("Correct value allowed?", instance.setGrade(grade));
        assertTrue("Value has not changed, although it is correct?", instance.getComputedGrade() == grade * mult1);

        grade = 7;
        assertTrue("Correct value allowed?", instance.setGrade(grade));
        assertTrue("Value has not changed, although it is correct?", instance.getComputedGrade() == grade * mult1);

        grade = 4;
        assertTrue("Correct value allowed?", instance.setGrade(grade));
        assertTrue("Value has not changed, although it is correct?", instance.getComputedGrade() == grade * mult1);

        grade = 2;
        assertTrue("Correct value allowed?", instance.setGrade(grade));
        assertTrue("Value has not changed, although it is correct?", instance.getComputedGrade() == grade * mult1);

        grade = 0;
        assertTrue("Correct value allowed?", instance.setGrade(grade));
        assertTrue("Value has not changed, although it is correct?", instance.getComputedGrade() == grade * mult1);

        grade = -3;
        assertTrue("Correct value allowed?", instance.setGrade(grade));
        assertTrue("Value has not changed, although it is correct?", instance.getComputedGrade() == grade * mult1);

        grade = 8;
        assertFalse("Incorrect value not allowed?", instance.setGrade(grade));
    }

    /**
     * Test of get and setName method, of class Student.
     */
    @Test
    public void testSetName()
    {
        char[] invalidChars = ".\\/,()&%€#!$".toCharArray();
        Student instance = new Student(6, "Ole Eriksen");
        String name = "";
        assertFalse("Should return false if value is incorrect", instance.setName(name));
        assertFalse("Should not change value if incorrect", name.equals(instance.getName()));

        assertFalse("Should return false if value is incorrect", instance.setName(null));

        for (char invalidChar : invalidChars)
        {
            instance = new Student(7, "Stig Iversen");
            name = invalidChar + "";
            assertFalse("Should return false if value is incorrect", instance.setName(name));
            assertFalse("Should not change value if incorrect", name.equals(instance.getName()));

            instance = new Student(8, "Anne-Mette Tonnesen");
            name = "" + invalidChar + " check";
            assertFalse("Should return false if value is incorrect", instance.setName(name));
            assertFalse("Should not change value if incorrect", name.equals(instance.getName()));

            instance = new Student(9, "Lektor Blomme");
            name = "start " + invalidChar + " end";
            assertFalse("Should return false if value is incorrect", instance.setName(name));
            assertFalse("Should not change value if incorrect", name.equals(instance.getName()));
        }

        instance = new Student(10, "Dumbledore Potter");
        name = "InvalidNameOnlyOneWord";
        assertFalse("Should return false if value is incorrect", instance.setName(name));
        assertFalse("Should not change value if incorrect", name.equals(instance.getName()));

        instance = new Student(11, "Petronov Steginova");
        name = "Valid Name=";
        assertTrue("Correct value not allowed?", instance.setName(name));
        assertTrue("Value has not changed, although it is correct?", name.equals(instance.getName()));
        name = "Valid Name Many Spaces No Problem Att all uncaps";
        assertTrue("Correct value not allowed?", instance.setName(name));
        assertTrue("Value has not changed, although it is correct?", name.equals(instance.getName()));
        name = "a e";
        assertTrue("Correct value not allowed?", instance.setName(name));
        assertTrue("Value has not changed, although it is correct?", name.equals(instance.getName()));

    }

    /**
     * Test of setStudentId method, of class Student.
     */
    @Test
    public void testSetStudentId()
    {
        int studentId = 0;
        Student instance = new Student(studentId, "Pete Farmsman Steggano");
        assertFalse("Student id must be >= 0", instance.setStudentId(-1));
        assertTrue("Student id must not change if not valid", instance.getStudentId() == studentId);
        assertTrue("StudentId 0 is valid, but returns false", instance.setStudentId(0));
        assertTrue("StudentId 65536 is valid, but returns false", instance.setStudentId(65536));
    }

}
