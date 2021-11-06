package Strikeboom.HTTPHoster.gui;

import Strikeboom.HTTPHoster.Main;
import Strikeboom.HTTPHoster.filehoster.FileHoster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Gui {
    JFrame frame;
    public void init() {
        try {
            //make it look like the os gui
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //base frame
        frame = new JFrame("HTTP Hoster");

        //left side (urls)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

        //right side (start button and settings)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GroupLayout(rightPanel));

        //scroll bars
        JScrollPane scrollPane = new JScrollPane(leftPanel);

        //split panes in half
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,rightPanel);
        splitPane.setDividerLocation(290);
        splitPane.setEnabled(false);

        //make url for each resource url
        for (String url : Main.fh.getFileURLs()) {
            //create a label
            JLabel label = new JLabel(url);
            makeUrl(label,label.getText());
            //add to panel
            leftPanel.add(label);
            //create spacing
            leftPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        }
        JLabel ipLabel = new JLabel("Your IP: " + Main.ip);
        ipLabel.setBounds(5,0,290,30);
        rightPanel.add(ipLabel);

        JLabel portLabel = new JLabel( "Port: "  + Main.port);
        portLabel.setBounds(5,20,290,30);
        rightPanel.add(portLabel);

        JLabel landingPageLabel = new JLabel( "Landing Page: http://" + Main.ip + ":" + Main.port);
        landingPageLabel.setBounds(5,40,290,30);
        makeUrl(landingPageLabel,"http://" + Main.ip + ":" + Main.port);
        rightPanel.add(landingPageLabel);

        JLabel isRunningLabel = new JLabel("Status: Not Running");
        isRunningLabel.setBounds(100,325,200,30);
        rightPanel.add(isRunningLabel);

        JButton startButton = new JButton("Start");
        startButton.setBounds(100,350,80,40);
        rightPanel.add(startButton);
        //change text when started running
        startButton.addActionListener(e -> {
            Main.fh.start();
            isRunningLabel.setText("Status: Running");
        });

        JButton openResourcesFolderButton = new JButton("Open Resources Folder");
        openResourcesFolderButton.setBounds(60,500,160,40);
        rightPanel.add(openResourcesFolderButton);
        //on click on the open resources open the folder
        openResourcesFolderButton.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    //open the folder
                    Desktop.getDesktop().open(new File("resources/"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        frame.setContentPane(splitPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .3), (int) (Toolkit.getDefaultToolkit().getScreenSize().height * .64));
        frame.setResizable(false);
        //set to middle
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void makeUrl(JLabel label, String urlText) {
        //adds underline and blue to make it look a link
        label.setForeground(Color.BLUE);
        Font font = label.getFont();
        Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));

        //add a mouse listener
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //open in browser when url is clicked
                if (Desktop.isDesktopSupported()) {
                    try {
                        URL url = new URL(urlText);
                        //format urls for common replacements
                        Desktop.getDesktop().browse(new URI(url.toString().replace(url.getPath(),"") + URLEncoder.encode(url.getPath(), StandardCharsets.UTF_8.displayName()).replaceAll("%2F","/").replaceAll("\\+", "%20").replaceAll("%21", "!").replaceAll("%27", "'").replaceAll("%28", "(").replaceAll("%29", ")").replaceAll("%7E", "~")));
                    } catch (IOException | URISyntaxException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            //change cursor and mouse enter and leave, so it looks clickable
            @Override
            public void mouseEntered(MouseEvent e) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
