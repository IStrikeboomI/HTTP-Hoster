package Strikeboom.HTTPHoster;

import Strikeboom.HTTPHoster.filehoster.FileHoster;
import Strikeboom.HTTPHoster.filehoster.mimeheader.MimeHeaders;
import Strikeboom.HTTPHoster.gui.Gui;
import Strikeboom.HTTPHoster.portfile.PortFile;
import Strikeboom.HTTPHoster.readmefile.CopyReadMeFile;
import Strikeboom.HTTPHoster.resourcesfolder.ResourceFolder;

import javax.swing.*;

public class Main {
    public static String ip;
    public static int port;
    public static FileHoster fileHoster;
    public static boolean isRunning;
    public static void main(String[] args) {
        MimeHeaders.init();
        //copy the read me file
        CopyReadMeFile.copy();
        //make the port file or get the port
        PortFile portFile = new PortFile();
        port = portFile.getPort();
        //create resource folder
        ResourceFolder rf = new ResourceFolder();
        fileHoster = new FileHoster(port);
        //for each resource make a host
        rf.getResourceFiles().forEach(fileHoster::host);
        //create landing page after hosting everything
        fileHoster.createLandingPage();
        //start gui
        Gui gui = new Gui();
        SwingUtilities.invokeLater(gui::init);
    }
    //for errors
    public static void sendErrorPrompt(String error) {
        JOptionPane.showMessageDialog(null,error);
        System.exit(0);
    }
}
