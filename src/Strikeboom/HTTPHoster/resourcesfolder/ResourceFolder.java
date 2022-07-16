package Strikeboom.HTTPHoster.resourcesfolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ResourceFolder {
    File resourceFolder;
    Map<File,String> resourceFiles = new HashMap<>();
    public ResourceFolder() {
        resourceFolder = new File("resources/");
        //create the resource folder if it doesn't exist
        if (!resourceFolder.exists()) {
            resourceFolder.mkdirs();
        }
        try {
            //Add files to hashmap
            Files.walk(resourceFolder.toPath())
                    .filter(Files::isRegularFile)
                    .forEach(path -> resourceFiles.put(path.toFile(),path.toString().substring("resources".length()).replaceAll("\\\\","/")));
            if (resourceFiles.isEmpty()) {
                System.out.println("Starting without resource folder because folder is empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<File, String> getResourceFiles() {
        return resourceFiles;
    }
}
