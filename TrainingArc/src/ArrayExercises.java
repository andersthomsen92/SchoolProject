import java.util.Arrays;

public class ArrayExercises {


    // 1. Write a Java program to sort a numeric array and a string array.
    public void Exercise1(){
        int[] intArray = {1,3,4,1,-10,200,41664,444};
        String[] stringArray = {"a","c","d","b","indelible"};

        System.out.println(Arrays.toString(intArray));  // need to use a toString method.
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));

        System.out.println(Arrays.toString(stringArray));
        Arrays.sort(stringArray);
        System.out.println(Arrays.toString(stringArray));

    }

    //2. Write a Java program to sum values of an array.
    public void Exercise2(){
        int[] intArray = {1,3,4,1,-1,2,3,4};
        int sum = 0;
        for (int i : intArray)
            sum += i;
        System.out.println(sum);
    }
    //3. Write a Java program to print the following grid.
    public void Exercise3(){
        int[][] gridArray = new int[10][10]; // creates a two-dimensional array with size 10,10
        for (int i = 0; i < 10; i++) {  // iterates through rows 10 times
            for (int j = 0; j < 10; j++)    // iterates through columns 10 times for each time the i-loop is run.
            {
                System.out.print(" - ");
            }
            System.out.println(" ");
        }
    }
    //4.Write a Java program to calculate the average value of array elements.
    public void Exercise4(){
        int[] intArray = {1,3,4,1,-1,3,3,4};
        int sum = 0;

        for (int i : intArray)  //for (int i : intArray):the variable "i" takes on the value of each element from array
            sum += i;       // adds value inside the "i" to sum. The loop does this for each item in the array
        System.out.println("Average of the array is: " + (sum / intArray.length));
    }
    //5. Write a Java program to test if an array contains a specific value.
    public boolean Exercise5()
    {
        int[] intArray = {1, 3, 4, 1, -1, 3, 8, 4};
        int target = 8;
        boolean contains = false;
        for (int i : intArray)
            if (i == target) {
                contains = true;
                break;
            }

        if (contains){  // == true
            System.out.println("Current array contains: " + target);}
        else{
            System.out.println("Current array doesn't contain " + target);}
        return contains;
    }
    //6.Write a Java program to find the index of an array element.
    public void Exercise6()
    {
        int[] intArray = {1, 3, 4, 1, -1, 3, 8, 4,1,2,5};
        int target = 5;
        int index = -1;

        for (int i = 0; i < intArray.length; i++)
            if (intArray[i] == target) {
                index = i;
                break;
            }
        if (index != -1)
        {
            System.out.println(target + " found at index: " + index);
        } else System.out.println(target + " not found");
    }
    // 7. Write a Java program to remove a specific element from an array.
    public void Exercise7()
    {
        int[] intArray = {1, 3, 4, 1, -1, 3, 8, 4,1,2,5};
        System.out.println("Original Array: " + Arrays.toString(intArray));
        System.out.println(intArray.length);
        int removalTarget = 1;

        for (int i = removalTarget; i < intArray.length - 1; i++)
        {
         intArray[i] = intArray[i + 1];
        }
        System.out.println("Modified Array: " + Arrays.toString(intArray));
        System.out.println(intArray.length);
    }
    /* This solution is very buggy, I've copied the solution fromm the web, yet it still only replaces
        and doesnt resize the array. this is for nr 7.*/

    //8. Write a Java program to copy an array by iterating the array.
    public void Exercise8(){
        int[] intArray = {1, 3, 4, 1};
        int[] newIntArray = new int[4];

        System.out.println(Arrays.toString(intArray));
        for (int i = 0; i < intArray.length; i++)
        {
            newIntArray[i] = intArray[i];
        }
        System.out.println(Arrays.toString(newIntArray));
        /*
        newIntArray[i] = intArray[i];
        iterates through the intArray for the length of the intArray.
        and each time it iterates through the loop,
        it says index 0 of newIntArray is = to index 0 of intArray
        */

    }
    //9. Write a Java program to insert an element (specific position) into an array.
    public void Exercise9() {
        int[] intArray = {1, 3, 4, 1};
        int indexPos = 3;
        int newValue = 100;

        // Create a new array with a larger size
        int[] newArray = new int[intArray.length + 1];
        System.out.println("Original Array: " + Arrays.toString(intArray));


        for (int i = 0; i < indexPos; i++) {        // This loop copies intArray, up to the IndexPos, into newArray
            newArray[i] = intArray[i];
        }

        newArray[indexPos] = newValue;      // Inserts the new value into the required pos.

        for (int i = indexPos; i < intArray.length; i++) {  // Copies the rest of intArray into the newArray.
            newArray[i + 1] = intArray[i];
        }

        intArray = newArray; // sets intArray equal to newArray, this mean newArray is a "temp/unused" array.

        System.out.println("Modified Array: " + Arrays.toString(intArray));
    }
    //27. Write a Java program to find the number of even and odd integers in a given array of integers.
    public void Exercise10(){
        int[] intArray = {1,4,2,5,7,1,2,6,8,4,1,5};
        int counter = 0;
        for (int i : intArray)
            if (i % 2 == 0) {
                counter++;
            }

        System.out.println("This is how many Odd numbers: " + (intArray.length - counter));
        System.out.println("This is how many Even numbers: " + counter);

    }
    //28. Write a Java program to get the difference between the largest and smallest values in an array of integers.
    public void Exercise11(){
        int[] intArray = {1,2,4,7,5,4,100};
        int maxVal = intArray[1];
        int minVal = intArray[1];

        for (int i : intArray)  // i takes on the value of the elements in the array
            if (i > maxVal)  // if the number in i is > than 0
            {
                maxVal = i;   // it sets maxVal to the current i value
            }
        else       // otherwise the opposite happens
        {
            minVal = i;
        }
        System.out.println("Max value: " + maxVal + "\nMin Value: " + minVal);
        System.out.println("Difference between the two is: " + (maxVal-minVal));
    }
    //33. Write a Java program to remove duplicate elements from a given array and return the updated array length.
    public void Exercise12()
    {
        int[] intArray = {20,20,30,40,50,50,50};
        System.out.println("Original array length: " + intArray.length);
        System.out.println("Original array: " + Arrays.toString(intArray));

        int newLength = Exercise12Sort(intArray);
        System.out.println("Sorted array length: " + newLength);

        // Access the sorted array up to the newLength
        int[] sortedArray = Arrays.copyOf(intArray, newLength);
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));

    }
    private int Exercise12Sort(int[] intArray){
      int index = 1;

        for (int i = 1; i < intArray.length; i++)  // we start at 1, because index 0 is always considered unique
        {
            if (intArray[i] != intArray[index - 1])  // if "i" isnt the equal to previous i.
                intArray[index++] = intArray[i];    // then we count i up once.
        }
        return index;
    }
    // 39. Write a Java program to print all the LEADERS in the array.
    //Note: An element is leader if it is greater than all the elements to its right side.
    public void Exercise13()
    {

    }

    public String stringReverse(String str) {
        String reverseStr = "";
        for (int i = 0; i < str.length(); i++) {
            reverseStr = str.charAt(i) + reverseStr;
        }
        return reverseStr;
    }
}

