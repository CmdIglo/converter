package com.archiving.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileSystemView;

import com.archiving.Parser;
import com.archiving.Writer;
import com.archiving.utils.Functions;

/**
 * The main window class building the gui
 * 
 * @author Maxwell Leu
 */
public class MainWindow {
    
    /** Main frame */
    private JFrame frame;
    /** Label that shows the storage location */
    private JLabel storageLocationLabel;
    /** Label that shows the target folder location */
    private JLabel targetFolderLabel;
    /** Progress bar displaying conversion process progress */
    private JProgressBar progressBar;
    /** Label of the progress bar -> Logger */
    private JLabel statusLabel;

    /** The storage location set by the user */
    private String storagelocation;
    /** The target folder location set by the user */
    private String targetlocation;
    /** The database location set by the user */
    private String dblocation;
    /** The location of the folder containing the ebooks */
    private String ebooklocation;
    /** The location of the folder containing the covers */
    private String coverslocation;

    /** Gui connector for functionality */
    //private GuiConnector Connector;

    /**
     * Class constructor
     * @param width     Width of window
     * @param height    Height of window
     * @param title     Title of window
     */
    public MainWindow(int width, int height, String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.getContentPane().setBackground(Color.WHITE); // Set background color

        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Einstellungen");
        JMenu documentationMenu = new JMenu("Dokumentation");



        settingsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuBar.add(settingsMenu);
        menuBar.add(documentationMenu);
        frame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10)); // Added padding between components
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to all sides

        JButton openStorageLocationButton = new JButton("Speicherort öffnen");
        JButton openTargetFolderButton = new JButton("Zielordner öffnen");
        JButton startButton = new JButton("Start");

        // Set preferred button size
        Dimension buttonSize = new Dimension(150, 20);
        openStorageLocationButton.setPreferredSize(buttonSize);
        openTargetFolderButton.setPreferredSize(buttonSize);
        startButton.setPreferredSize(buttonSize);

        // Add padding to labels
        storageLocationLabel = new JLabel("Speicherort:");
        storageLocationLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add left padding
        targetFolderLabel = new JLabel("Zielordner:");
        targetFolderLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add left padding

        progressBar = new JProgressBar();
        statusLabel = new JLabel("Bereit");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add left padding

        mainPanel.add(openStorageLocationButton);
        mainPanel.add(storageLocationLabel);
        mainPanel.add(openTargetFolderButton);
        mainPanel.add(targetFolderLabel);
        mainPanel.add(startButton);
        mainPanel.add(progressBar);

        openStorageLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    storagelocation = fileChooser.getSelectedFile().getAbsolutePath();
                    storageLocationLabel.setText("Speicherort:"+storagelocation);
                    storageLocationLabel.setToolTipText("Speicherort:"+storagelocation);
                }
            }
        });

        openTargetFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    targetlocation = fileChooser.getSelectedFile().getAbsolutePath();
                    targetFolderLabel.setText("Zielordner:"+targetlocation);
                    targetFolderLabel.setToolTipText("Zielordner:"+targetlocation);
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((storagelocation != "") && (targetlocation != "")) {
                    Writer FileWriter = new Writer(targetlocation);
                    Parser Formatter = new Parser();
                    FileWriter.setContents(Formatter.formatContents(Functions.readFiles(Functions.getFiles(storagelocation))));
                    FileWriter.write(targetlocation);
                }
            }
        });

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
    }

    /**
     * Get the window reference
     * @return  The window reference
     */
    public JFrame getWindow() {
        return this.frame;
    }

    /**
     * Update the status label for the status bar
     * @param info  The text of the label
     */
    public void updateLabel(String info) {
        this.statusLabel.setText(info);
    }

    /**
     * Get the storage location
     * @return  Storage location of XMLs
     */
    public String getStorageLoc() {
        return this.storagelocation;
    }

    /**
     * Get target folder location
     * @return  Target folder location
     */
    public String getTargetLoc() {
        return this.targetlocation;
    }

    /**
     * Get database location
     * @return  Database location
     */
    public String getDbLoc() {
        return this.dblocation;
    }

    /**
     * Get eBook storage folder location
     * @return  eBook storage folder location
     */
    public String getEbookLoc() {
        return this.ebooklocation;
    }

    /**
     * Get covers storage folder location
     * @return  Covers storage folder location
     */
    public String getCoversLoc() {
        return this.coverslocation;
    }

}


