package assignment1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogicsAndNumbersStuffTest {

    @Test
    public void diffMultipliedByC() {
        int a = 5;
        int b = 3;
        int c = 21;
        int expected = 42;
        LogicsAndNumbersStuff instance = new LogicsAndNumbersStuff();
        int result = instance.diffMultipliedByC(a,b,c);
        assertEquals("The expected result is 42, since (5-3)*21=42",expected, result);

        a = 4;
        b = 8;
        c = 10;
        expected = -40;
        result = instance.diffMultipliedByC(a,b,c);
        assertEquals("The expected result is -4 since (4-8)*10 = -40",expected, result);
    }

    @Test
    public void canYouStayInBed() {
        boolean weekday = true;
        boolean vacation = false;
        boolean expected = false;
        LogicsAndNumbersStuff instance = new LogicsAndNumbersStuff();
        boolean actual = instance.canYouStayInBed(weekday, vacation);
        assertEquals("Can't stay in bed on weekdays if you are not on vacation",expected, actual);

        weekday = false;
        vacation = false;
        expected = true;
        actual = instance.canYouStayInBed(weekday, vacation);
        assertEquals("If it's not a weekday you can always stay in bed",expected, actual);

        weekday = true;
        vacation = true;
        expected = true;
        actual = instance.canYouStayInBed(weekday, vacation);
        assertEquals("If you are on vacation you can always stay in bed",expected, actual);

        weekday = false;
        vacation = true;
        expected = true;
        actual = instance.canYouStayInBed(weekday, vacation);
        assertEquals("If you are on vacation you can always stay in bed",expected, actual);
    }

    @Test
    public void sumOfNumbers() {
        int[] numbers = {2,4,4};
        int expected = 10;
        LogicsAndNumbersStuff instance = new LogicsAndNumbersStuff();
        int actual = instance.sumOfNumbers(numbers);
        assertEquals(expected, actual);

        int[] numbersTwo = {2,-4,4,8,32};
        expected = 42;
        actual = instance.sumOfNumbers(numbersTwo);
        assertEquals(expected, actual);
    }

    @Test
    public void averageOfNumber() {
        int[] numbers = {2,4,4,6};
        double expected = 4.0;
        LogicsAndNumbersStuff instance = new LogicsAndNumbersStuff();
        double actual = instance.averageOfNumber(numbers);
        assertEquals(expected, actual, 0);

        int[] numbersTwo = {2,-4,4,6,2};
        expected = 2.0;
        actual = instance.averageOfNumber(numbersTwo);
        assertEquals(expected, actual, 0);
    }

    @Test
    public void numberWithMostOccurrences() {
        int[] numbers = {2,4,4,6};
        int expected = 4;
        LogicsAndNumbersStuff instance = new LogicsAndNumbersStuff();
        int actual = instance.numberWithMostOccurrences(numbers);
        assertEquals(expected, actual);

        int[] numbersTwo = {2,1983,4,6,6,6,6,1983,1983,1983};
        expected = 1983;
        actual = instance.numberWithMostOccurrences(numbersTwo);
        assertEquals(expected, actual);
    }

}