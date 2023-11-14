import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static String fileStringPath = "D:\\Datamatiker\\Projects\\SchoolProject\\FileWriting\\src\\dal\\MovieList.txt";
    private static Path filePath = Path.of(fileStringPath);
    private static String fileStringFollowAlongPath = "D:\\Datamatiker\\Projects\\SchoolProject\\FileWriting\\src\\dal\\FollowAlong.txt";
    private static Path followAlongFilePath = Path.of(fileStringFollowAlongPath);

    private static List<String> allMovies = new ArrayList<>();
    private static Scanner searcher;
    public static void main(String[] args) {

        printFileContent();  // Prints the file into console
        //addToFile("Some line");  // this adds a line at the end of the file
        addTextAtLine("TESTING" , 2);  // cant append at the first line, 0 == 2nd line in the txt.
        printFileContent();



        // My implementation.
       // loadFileContent();
       // promtSearch();

    }


    public static void printFileContent() {

        System.out.println("-------");
        try {
            //Files.lines(Path.of(followAlongFilePath)).forEach(line -> System.out.println(line)); read file line by line
            System.out.println(Files.readString((followAlongFilePath)));  // Reads the entire file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


/*
        try (FileReader fileReader = new FileReader(followAlongFilePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String line;
            while((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        /*
            fileReader.close();

            // reads the file.
             Scanner scanner = new Scanner(fileReader);      // scans what the fileReader reads i guess.

            while (scanner.hasNext()) { // While scanner has a nextline, then it prints the line.
                System.out.println(scanner.nextLine());
            }
            fileReader.close();         // remember to close the file again.
            */
    }

    private static void addToFile(String text) {
        try {
            Files.write(followAlongFilePath, text.getBytes() , APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /*        try (FileWriter fileWriter = new FileWriter(fileStringFollowAlongPath,true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            bufferedWriter.append(text + "\r\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    private static void addTextAtLine(String text, int lineNumber) {

        String fileInput = "";
        try {
            // Read the old file
            List<String> allLines = Files.readAllLines(followAlongFilePath);
            allLines.add(lineNumber,text);

            // Create new temp file
            Path tempFilePath = Path.of(fileStringPath + "_TEMP");
            Files.createFile(tempFilePath);

            // add all the lines including new line to temp file.
            int countLines = 0;
            for (String line : allLines) {
                Files.write(tempFilePath, (line + "\r\n").getBytes(), APPEND);
                countLines++;

            }
            // Overwrite old file with temp file and delete temp
            Files.copy(tempFilePath,followAlongFilePath, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(tempFilePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /* try (FileReader fileReader = new FileReader(fileStringFollowAlongPath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String fileInput = "";
            int countLines = 0;

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                if (countLines == lineNumber)
                    fileInput += text + "\r\n";
                fileInput += line + "\r\n";
                countLines++;
            }
            try (FileWriter fileWriter = new FileWriter(fileStringFollowAlongPath);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
            {
                bufferedWriter.append(fileInput);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //////////////////////////
    ///// MOVIE DATABASE /////
    //////////////////////////
    private static void loadFileContent(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileStringPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } // reads all lines from the file, & adds it to the allWords List
            allMovies.add(line);
        }
    }
    private static void promtSearch(){
        String searchedItem;
        int lineCount = 0;
        System.out.println();
        System.out.println("Enter the movie you wish to search for: ");
        searcher = new Scanner(System.in);
        searchedItem = searcher.nextLine();
        System.out.println("Searching for... " + searchedItem );

        boolean found = false;
        for (String movie : allMovies) {
            String[] parts = movie.split(",");
            lineCount ++;
            if (parts.length >= 3) {
                String title = parts[2].toLowerCase().trim();
                if (title.contains(searchedItem))   {
                    System.out.println("Match found at: " + movie +"\n found at line: " + lineCount);
                    found = true;
                }
            }

        }
        if (!found)
            System.out.println("No match found.");

    }
    private static void locateMovie(String movie){
        int lineCount = 0;
        String movieTitle = "";

        try {
            FileReader fileReader = new FileReader(fileStringPath);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNext()){
                movieTitle += scanner.nextLine() + "\n";
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}