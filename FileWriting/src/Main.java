import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static String filePath = "D:\\Datamatiker\\Projects\\SchoolProject\\FileWriting\\src\\dal\\test.txt";
    // filepath is just a string that holds the path to the text.file
    public static void main(String[] args) {
        printFileContent();  // Prints the file into console
        addToFile("Some line");  // this adds a line at the end of the file
        //addTextAtLine("x" , 0);  // cant append at the first line, 0 == 2nd line in the txt.
        printFileContent();

    }

    public static void printFileContent() {
        System.out.println("-------");

        try {
            FileReader fileReader = new FileReader(filePath);  // reads the file.
            Scanner scanner = new Scanner(fileReader);      // scans what the fileReader reads i guess.

            while (scanner.hasNext()) { // While scanner has a nextline, then it prints the line.
                System.out.println(scanner.nextLine());
            }
            fileReader.close();         // remember to close the file again.
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addToFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // fileWriter writes into the file.
            fileWriter.append(text + "\n");  // it writes at the end of the file. and then jumps down a line.
            fileWriter.close();     // Remember to close the file :)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addTextAtLine(String text, int lineNumber) {
        FileReader fileReader = null;
        try {
            String fileInput = "";  // sets the input to empty
            fileReader = new FileReader(filePath);      // reads the file
            Scanner scanner = new Scanner(fileReader);
            int countLines = 0;     // sets line count to zero
            while(scanner.hasNext()){       // same as the previous method.
                fileInput += scanner.nextLine() + "\n";     // rewrites the whole document.
                if (countLines == lineNumber)   // until the line wanted comes up
                    fileInput+=text + "\r\n";       // then we append the fileInput, and continue.
                countLines++;   // counts the line in the while-loop.
            }
            fileReader.close();
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.append(fileInput);
            fileWriter.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}