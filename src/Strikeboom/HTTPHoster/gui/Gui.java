package Strikeboom.HTTPHoster.gui;

import Strikeboom.HTTPHoster.Main;
import Strikeboom.HTTPHoster.filehoster.FileHoster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class Gui {
    public static void init() {

                                try {
                                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                                    e.printStackTrace();
                                }

                                JFrame frame = new JFrame("HTTP Hoster");

                                JPanel leftPanel = new JPanel();
                                leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

                                JPanel rightPanel = new JPanel();
                                rightPanel.setLayout(new GroupLayout(rightPanel));

                                JScrollPane scrollPane = new JScrollPane(leftPanel);

                                JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,rightPanel);
                                splitPane.setDividerLocation(290);
                                splitPane.setEnabled(false);

                                for (int i = 0; i < Main.fh.getFileURLs().size();i++) {
                                    JLabel label = new JLabel(Main.fh.getFileURLs().get(i));

                                    //adds underline and blue to make it look a link
                                    label.setForeground(Color.BLUE);
                                    Font font = label.getFont();
                                    Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) font.getAttributes();
                                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                                    label.setFont(font.deriveFont(attributes));

                                    label.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            if (Desktop.isDesktopSupported()) {
                                                try {
                                                    Desktop.getDesktop().browse(new URI(label.getText().replaceAll(" ","%20")));
                                                } catch (IOException | URISyntaxException ioException) {
                                                    ioException.printStackTrace();
                                                }
                                            }
                                        }

                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                            frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                        }

                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                                        }
                                    });
                                    leftPanel.add(label);
                                    leftPanel.add(Box.createRigidArea(new Dimension(0, 2)));
                                }

                                JLabel ipLabel = new JLabel("Your IP: " + FileHoster.ip);
                                ipLabel.setBounds(5,0,290,30);
                                rightPanel.add(ipLabel);

                                JLabel portLabel = new JLabel( "Port: "  + Main.port);
                                portLabel.setBounds(5,20,290,30);
                                rightPanel.add(portLabel);

                                JLabel isRunningLabel = new JLabel("Status: Not Running");
                                isRunningLabel.setBounds(100,325,200,30);
                                rightPanel.add(isRunningLabel);

                                JButton startButton = new JButton("Start");
                                startButton.setBounds(100,350,80,40);
                                rightPanel.add(startButton);
                                startButton.addActionListener(e -> {
                                    Main.fh.start();
                                    isRunningLabel.setText("Status: Running");
                                });

                                JButton openResourcesFolderButton = new JButton("Open Resources Folder");
                                openResourcesFolderButton.setBounds(60,500,160,40);
                                rightPanel.add(openResourcesFolderButton);
                                openResourcesFolderButton.addActionListener(e -> {
                                    if (Desktop.isDesktopSupported()) {
                                        try {
                                            Desktop.getDesktop().open(new File("resources/"));
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }
                                    }
                                });

                                frame.setContentPane(splitPane);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.pack();
                                frame.setSize(580, 700);
                                frame.setResizable(false);
                                frame.setLocationRelativeTo(null);
                                frame.setVisible(true);
    }
}