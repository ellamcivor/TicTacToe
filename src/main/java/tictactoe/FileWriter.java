package tictactoe;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Class to write to a file.
 * @author Isabella McIvor
*/
public class FileWriter {

    /**
     * Writes to a file.
     * @param filepath - String representation of the path of file to write to.
     * @param toSave - String to write to file
    */
    public static boolean writeFile(String filepath, String toSave) {
        Path path = Paths.get(filepath);
        boolean done = false;
        try {
            Files.writeString(path, toSave);
            done = true;
        } catch (IOException e) {
            done = false;
        }
        return done;
    }
}