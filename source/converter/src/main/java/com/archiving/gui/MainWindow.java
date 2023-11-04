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

public class MainWindow {
    
    private JFrame frame;
    private JLabel storageLocationLabel;
    private JLabel targetFolderLabel;
    private JProgressBar progressBar;
    private JLabel statusLabel;

    private String storagelocation;
    private String targetlocation;

    public MainWindow(int width, int height, String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.getContentPane().setBackground(Color.WHITE); // Set background color

        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Einstellungen");
        JMenu documentationMenu = new JMenu("Dokumentation");
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
                // Start button logic
            }
        });

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
    }

    public JFrame getWindow() {
        return this.frame;
    }

    public void updateLabel(String info) {
        this.statusLabel.setText(info);
    }

}

