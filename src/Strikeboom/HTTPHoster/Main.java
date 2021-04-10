package Strikeboom.HTTPHoster;

import Strikeboom.HTTPHoster.filehoster.FileHoster;
import Strikeboom.HTTPHoster.portfile.PortFile;
import Strikeboom.HTTPHoster.readmefile.CopyReadMeFile;
import Strikeboom.HTTPHoster.resourcesfolder.ResourceFolder;

import javax.swing.*;

public class Main {
    public static int port;
    public static boolean fileCreated = false;
    public static void main(String[] args) {
        CopyReadMeFile.copy();
        PortFile portFile = new PortFile();
        port = portFile.getPort();
        ResourceFolder rf = new ResourceFolder();
        if (fileCreated) {
            sendErrorPrompt("New files created, restart program");
        }
        FileHoster fh = new FileHoster(port);
        rf.getResourceFiles().forEach(fh::host);
        fh.start();
    }
    public static void sendErrorPrompt(String error) {
        JOptionPane.showMessageDialog(null,error);
        System.exit(0);
    }
}
