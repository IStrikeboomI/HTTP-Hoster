package Strikeboom.HTTPHoster.readmefile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyReadMeFile {
    /**
     * This creates the readme text file in the directory of the jar
     */
    public static void copy() {
        try {
            Files.copy(CopyReadMeFile.class.getResourceAsStream("readme.txt"), new File("readme.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
