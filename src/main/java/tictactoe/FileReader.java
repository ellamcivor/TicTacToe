package tictactoe;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Class to read a file.
 * @author Isabella McIvor
*/
public class FileReader {

    /** 
     * Reads a file.
     * @param filepath - String representation of the file path
     * @return A String respresentation of the file contents
    */
    public static String readFile(String filepath) {
        Path path = Paths.get(filepath);
        String lines = null;
        try {
            lines = Files.readString(path);
        } catch (IOException e) {
            return null;
        }
        return lines;
    }

}