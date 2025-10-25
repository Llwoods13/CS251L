import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Project 5 Line Sort Class.
 * This class reads in a txt file and sorts the lines from least to greatest and
 * also sorts the lines in reverse alphabetical order all while ignoring lines
 * that start with $. EX: def is prioritized over abc
 * @author Lane Woods
 */
public class LineSort {
    public static void main(String[] args) throws IOException {
        int longest = 0;
        int shortest = Integer.MAX_VALUE;
        int ignored = 0;
        Path directory = Paths.get(args[0]);
        File input = directory.toFile();
        if (Files.notExists(input.toPath())) {
            System.out.println("Input file does not exist");
        } else {
            try {
                //creates an output file
                Path outFile = Files.createFile(Paths.get("output.txt"));
                //Creates my list to be added to.
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(input));
                BufferedWriter writer = new BufferedWriter
                        (new FileWriter(outFile.toFile()));

                //Reads and adds all the lines to the list
                while (reader.ready()) {
                    lines.add(reader.readLine());
                }
                System.out.println(lines);
                System.out.println(lines.size());
                //Sorts the lines from least to greatest and ignores lines that
                //start with $.
                Collections.sort(lines, new SortReverse());;
                for (String line : lines) {
                    //Probably not the most optimal approach.
                    if (line.startsWith("$")) {
                        ignored++;
                    }
                    /*
                     * I added the additional check to ignore any lines
                     * with a special character that may be larger than the
                     * largest line. Had an issue it counted lines with special
                     * characters as the largest line.
                     */
                    if (line.length() > longest && !line.startsWith("$")) {
                        longest = line.length();
                    }
                    /*
                     * I added the additional check to ignore any lines
                     * with a special character that may be smaller than the
                     * shortest line. Had an issue it counted lines with special
                     * characters as the shortest line.
                     */
                    if (line.length() < shortest && !line.startsWith("$")) {
                        shortest = line.length();
                    }
                    if (!(line.startsWith("$"))) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                writer.write("shortest: " + shortest + "\n" + "longest: " +
                        longest + "\n" + "ignored: " + ignored);
                writer.close();
                //Catches if the file already exists.
            } catch (FileAlreadyExistsException e) {
                System.out.println("This file already exists");
                //Catches if there is an error with the file.
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Did you enter a directory?");
            }
        }
    }

    /**
     * This class sorts the lines from least to greatest while also
     * sorting the lines in reverse alphabetical order.
     * NOTE: This was something new to me, so the provided documentation after
     * this is for my understanding. Comparator works by taking in two objects in
     * this case for my list and my SortReverse class. It then compares the
     * length, and if object1 is greater than object2, it returns 1 if object1 is
     * less than object2, it returns -1, and if they are equal, it returns 0.
     * A return of -1 means less, a return of 0 is equal, and a return of 1 means
     * greater. These return values are used to sort the list.
     */
    public static class SortReverse implements Comparator<String> {
        @Override
        public int compare(String object1, String object2) {
            if (object1.length() > object2.length()) {
                return 1;
            } else if (object1.length() < object2.length()) {
                return -1;
            } else {
                return object2.compareTo(object1);
            }
        }
    }
}