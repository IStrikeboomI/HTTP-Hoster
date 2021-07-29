package Strikeboom.HTTPHoster.portfile;

import Strikeboom.HTTPHoster.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PortFile {
    File portFile;
    public PortFile() {
        portFile = new File("port.txt");
        if (!portFile.exists()) {
            try {
                portFile.createNewFile();
                FileWriter writer = new FileWriter(portFile);
                writer.write("55566");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public int getPort() {
        try {
            Scanner reader = new Scanner(portFile);
            String portString = reader.nextLine().trim();
            reader.close();
            if (isNumber(portString)) {
                int port = Integer.parseInt(portString);
                if (isPortValid(port)) {
                    return port;
                } else {
                    Main.sendErrorPrompt("ERROR: port in port.txt is not a valid port! Program Closing");
                }
            } else {
                Main.sendErrorPrompt("ERROR: port in port.txt is not a number! Program Closing");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e ) {
            return false;
        }
        return true;
    }
    private boolean isPortValid(int port) {
        return port >= 0 && port <= 65535;
    }
}
