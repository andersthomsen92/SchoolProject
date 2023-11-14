package assignment1;

import assignment1.StringHandler;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class StringHandlerTest {
    private StringHandler handler;

    @Before
    public void setUp() {
        handler = new StringHandler();
    }

    //opgave 1a
    @Test
    public void testStringManipulation() {
        String str  = "citron";
        String expected = "sitron";
        String actual = handler.stringManipulation(str);
        assertEquals(expected, actual);
    }
    
     //opgave 1a
    @Test
    public void testStringManipulationSeveralMatches() {
        String str  = "cacao";
        String expected = "sasao";
        String actual = handler.stringManipulation(str);
        assertEquals(expected, actual);
    }
    
     //opgave 1a
    @Test
    public void testStringNoManipulation() {
        String str  = "si";
        String expected = "si";
        String actual = handler.stringManipulation(str);
        assertEquals(expected, actual);
    }

    //opgave 1b
    @Test
    public void testStringLength() {
        int expected = 5;
        int actual = handler.stringLength("hello");
        assertEquals(expected, actual);
    }

    //opgave 1b
    @Test
    public void testEmptyStringLength() {
        int expected = 0;
        int actual = handler.stringLength("");
        assertEquals(expected, actual);
    }
    
     //opgave 1b
    @Test
    public void testNullStringLength() {
        int expected = 0;
        int actual = handler.stringLength(null);
        assertEquals(expected, actual);
    }
    

    //opgave 1c
    @Test
    public void testStringLengthInArrayOneString() {
        int expected = 5;
        String[] arr = {"hello"};
        int actual = handler.stringArrayLength(arr);
        assertEquals(expected, actual);
    }

    //opgave 1c
    @Test
    public void testStringLengthInArrayTwoStrings() {
        int expected = 10;
        String[] arr = {"hello", "world"};
        int actual = handler.stringArrayLength(arr);
        assertEquals(expected, actual);
    }

    //opgave 1d
    @Test
    public void testStringReverse() {
        String expected = "olleh";
        String actual = handler.stringReverse("hello");
        assertEquals(expected, actual);
    }
    
    //opgave 1e
    @Test
    public void testCharCount(){
        int expected = 3;
        int actual = handler.charCount("Ouagadougou", 'o');
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCharCountZero(){
        int expected = 0;
        int actual = handler.charCount("Hello", 'x');
        assertEquals(expected, actual);
    }

    @Test
    public void testStringOfChars() {
        char c = 'q';
        int amount = 4;
        String expected = "qqqq";
        String actual = handler.stringOfChars(amount, c);
        assertEquals(expected, actual);

        c = ' ';
        amount = 42;
        expected = "                                          ";
        actual = handler.stringOfChars(amount, c);
        assertEquals(expected, actual);
    }

}
