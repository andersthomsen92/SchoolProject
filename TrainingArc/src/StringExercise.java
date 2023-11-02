

// https://www.javatpoint.com/java-main-method
// Taken from this website.

import java.util.LinkedList;
import java.util.List;

public class StringExercise {

    /* 1. Write a Java program to get the character at the given index within the string.
    Original String = Java Exercises!
    The character at position 0 is J
    The character at position 10 is i  */
    public static void Exercise1() {
        String original = "Java Exercises!";
        int index1 = original.charAt(0);
        int index2 = original.charAt(10);
        // Gets the character at the index indicated.
        System.out.println("Char at index 0 :" + (char) index1);
        System.out.println("Char at index 10: " + (char) index2);
        // converts the index to a char, so that it can print it, otherwise its a number.
    }

    /*
    2. Write a Java program to get the character (Unicode code point) at the given index within the string.
    Original String : w3resource.com
    Character(unicode point) = 51
    Character(unicode point) = 101
     */
    public static void Exercise2() {
        String original = "w3resource.com";
        System.out.println(original);
        int index1 = original.codePointAt(1);
        int index2 = original.codePointAt(9);
        System.out.println("Unicode code point at index 1 : " + index1);
        System.out.println("Unicode code point at index 9 : " + index2);
    }

    /*
    3. Write a Java program to get the character (Unicode code point) before the specified index within the string.
    Original String : w3resource.com
    Character(unicode point) = 119
    Character(unicode point) = 99
    */
    public static void Exercise3() {
        String original = "w3resource.com";
        System.out.println(original);
        int index1 = original.codePointBefore(1);
        int index2 = original.codePointBefore(9);
        System.out.println("Unicode code point before index 1 : " + index1);
        System.out.println("Unicode code point before index 9 : " + index2);

    }

    /*
    4. Write a Java program to count Unicode code points in the specified text range of a string.
    Original String : w3rsource.com
    Codepoint count = 9
    */
    public static void Exercise4() {
        String original = "w3resource.com";
        System.out.println(original);
        int codeCounter = original.codePointCount(1, 10);
        System.out.println("Codepoint count = " + codeCounter);
    }

    /*
    5. Write a Java program to compare two strings lexicographically.
    Two strings are lexicographically equal if they are the same length and contain the same characters in the same positions.
    Sample Output:
    String 1: This is Exercise 1
    String 2: This is Exercise 2
    "This is Exercise 1" is less than "This is Exercise 2"
    */
    public static void Exercise5() {
        String string1 = "This is Exercise";
        String string2 = "This is Exercise2";
        int result = string1.compareTo(string2); // returns -1, 0 , 1 depending on outcome of the comparison.
        if (result < 0)     // if... then string1 is the first lexicographically(Alphabetically)
        {
            System.out.println("\"" + string1 + "\"" +
                    " is less than " +
                    "\"" + string2 + "\"");
        } else if (result == 0)   // if... then they are the same
        {
            System.out.println("\"" + string1 + "\"" +
                    " is equal to " +
                    "\"" + string2 + "\"");
        } else // else... string2 is the first in order.
        {
            System.out.println("\"" + string1 + "\"" +
                    " is greater than " +
                    "\"" + string2 + "\"");
        }
    }

    /*
    6. Write a Java program to compare two strings lexicographically, ignoring case differences.
    Sample Output:
    String 1: This is exercise 1
    String 2: This is Exercise 1
    "This is exercise 1" is equal to "This is Exercise 1"
    */
    public static void Exercise6() {
        String string1 = "tHiS iS ExErCiSe1";
        String string2 = "This is Exercise1";
        int result = string1.compareToIgnoreCase(string2); // returns -1, 0 , 1 depending on outcome of the comparison.
        if (result < 0)     // if... then string1 is the first lexicographically(Alphabetically)
        {
            System.out.println("\"" + string1 + "\"" +
                    " is less than " +
                    "\"" + string2 + "\"");
        } else if (result == 0)   // if... then they are the same
        {
            System.out.println("\"" + string1 + "\"" +
                    " is equal to " +
                    "\"" + string2 + "\"");
        } else // else... string2 is the first in order.
        {
            System.out.println("\"" + string1 + "\"" +
                    " is greater than " +
                    "\"" + string2 + "\"");
        }
    }

    /*
    7. Write a Java program to concatenate a given string to the end of another string.
    Sample Output:
    String 1: PHP Exercises and
    String 2: Python Exercises
    The concatenated string: PHP Exercises and Python Exercises
    */
    public static void Exercise7() {
        String string1 = "PHP Exercises and";
        String string2 = "Python Exercises";
        System.out.println("The concatenated string: " + string1 + " " + string2);
    }

    /*8. Write a Java program to test if a given string contains the specified sequence of char values.
    Sample Output:
    Original String: PHP Exercises and Python Exercises
    Specified sequence of char values: and
    true*/
    public static void Exercise8() {
        String string1 = "PHP Exercises and Python Exercises";
        String string2 = "and";
        System.out.println(string1);
        System.out.println(string1.contains(string2));
    }

    /*
    9. Write a Java program to compare a given string to the specified character sequence.
    Sample Output:
    Comparing example.com and example.com: true
    Comparing Example.com and example.com: false
    */
    public static void Exercise9() {
        String string1 = "example.com";
        String string2 = "Example.com";
        System.out.println("Comparing: " + string1 + " and " + string1 + " : " + string1.equals(string1));
        System.out.println("Comparing: " + string1 + " and " + string2 + " : " + string1.equals(string2));
    }
    /*
    10. Write a Java program to compare a given string to a specified string buffer.
    Sample Output:
    Comparing example.com and example.com: true
    Comparing Example.com and example.com: false
     Notes about StringBuffer Below */
    /*
    Mutable: You can modify the content of a StringBuffer after it's created.
    You can append, insert, replace, or delete characters in a StringBuffer without creating a new object.

    Efficient for String Concatenation: StringBuffer is often used when you
    need to perform a lot of string concatenation operations.
    Since it's mutable, it's more efficient than repeatedly creating
    and copying strings, as is the case with regular String objects.

    Thread-Safe: StringBuffer is designed to be thread-safe,
    which means it can be safely accessed and modified by multiple threads in a concurrent environment.
    However, this comes at a cost of performance. If you don't need thread safety,
    you can use the non-thread-safe StringBuilder class, which is similar to StringBuffer but more efficient.
    */

    public static void Exercise10() {
        String string1 = "example.com";
        String string2 = "Example.com";
        StringBuffer stringBuffer = new StringBuffer(string1); // StringBuffer is a container for a string object,
        System.out.println("Comparing: " + string1 + " and " + stringBuffer + " : " + string1.contentEquals(stringBuffer));
        System.out.println("Comparing: " + string2 + " and " + stringBuffer + " : " + string2.contentEquals(stringBuffer));
    }

    /*
    11. Write a Java program to create a String object with a character array.
    Sample Output:
    The book contains 234 pages.*/
    public static void Exercise11() {
        char[] arr_num = new char[]{'1', '2', '3', '4'};
        String string1 = String.copyValueOf(arr_num, 1, 3);   // Starts from index 1 and counts 3 indices forward.
        System.out.println("The book contains " + string1 + " Pages");
    }

    /*12. Write a Java program to check whether a given string ends with another string.
    Sample Output:
    "Python Exercises" ends with "se"? false
    "Python Exercise" ends with "se"? true
    */
    public static void Exercise12() {
        String string1 = "Python Exercises";
        String string2 = "Python Exercise";
        System.out.println(string1 + " ends with" + "se?" + " " + string1.endsWith("se"));
        System.out.println(string1 + " ends with" + "se?" + " " + string2.endsWith("se"));
    }

    // Exercise 13, uses string.equals();
    // Exercise 14, uses string.equalsIgnoreCase(); Thus it isn't case-sensitive
    // Exercise 15, uses Calendar c = Calendar.getInstance(); To get the calendar from the computer.
    // Exercise 16 FizzBuzz
    public static void Exercise16(){
        int[] FizzArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        for (int i = 0; i < FizzArray.length; i++)
        {
            if (FizzArray[i] % 15 == 0) {
                System.out.println("FizzBuzz");
            }else if (FizzArray[i] % 5 == 0) {
                System.out.println("Fizz");
            } else if (FizzArray[i] % 3 == 0) {
                System.out.println("Buzz");
            } else
                System.out.println(FizzArray[i]);
        }
    }
}