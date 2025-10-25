import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class AbstractHangman implements HangmanInterface{
    protected ArrayList<String> dictionary;
    protected ArrayList<String> words;

    /**
     *
     */
    //TODO: put this into my abstract class
    protected void readDictionary(String path) {
        Path Directory = Paths.get(path);
        File inputFile = Directory.toFile();
        if (Files.notExists(inputFile.toPath())){
            System.out.println("File does not exist");
        }
        try {
            BufferedReader read = new BufferedReader(new FileReader(inputFile));
            words = new ArrayList<>();
            while (read.ready()) {
                words.add(read.readLine());
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
