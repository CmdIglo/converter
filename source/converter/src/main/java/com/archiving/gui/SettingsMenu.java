package com.archiving.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

/**
 * Settings menu window class
 * 
 * @author Maxwell Leu
 */
public class SettingsMenu extends JFrame{

    /** Main Panel for the window */
    private JPanel panel = new JPanel();
    /** Button to set the cover location */
    private JButton coverBtn = new JButton("Cover Speicherort");
    /** Button to set the eBook location */
    private JButton ebookBtn = new JButton("eBooks Speicherort");
    /** Button to set the database location */
    private JButton dbBtn = new JButton("Datenbank Speicherort");
    /** Close the window */
    private JButton closeWin = new JButton("Fertig");
    /** Label for displaying the directories that have been selected  */
    private JLabel coverDirLabel = new JLabel("Cover Speicherort: ");
    private JLabel eBookDirLabel = new JLabel("eBook Speicherort: ");
    private JLabel dbDirLabel = new JLabel("Datenbank Speicherort: ");

    private String coverLoc = "";
    private String eBookLoc = "";
    private String dbLoc = "";

    /**
     * Class Cunstructor
     * @param width     The width of the window
     * @param height    The height of the window
     */
    public SettingsMenu(int width, int height) {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        coverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    coverLoc = fileChooser.getSelectedFile().getAbsolutePath();
                    coverDirLabel.setText("Cover Speicherort: "+coverLoc);
                    coverDirLabel.setToolTipText(coverLoc);
                }
            }
        });

        ebookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    eBookLoc = fileChooser.getSelectedFile().getAbsolutePath();
                    eBookDirLabel.setText("eBook Speicherort: "+eBookLoc);
                    eBookDirLabel.setToolTipText(eBookLoc);
                }
            }
        });

        dbBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    dbLoc = fileChooser.getSelectedFile().getAbsolutePath();
                    dbDirLabel.setText("Datenbank Speicherort: "+dbLoc);
                    dbDirLabel.setToolTipText(dbLoc);
                }
            }
        });

        closeWin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeThis();
            }
        });

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(coverBtn, gbc);
        gbc.gridx = 1;
        panel.add(coverDirLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(ebookBtn, gbc);
        gbc.gridx = 1;
        panel.add(eBookDirLabel, gbc);

        gbc.gridx = 0;        
        gbc.gridy = 2;
        panel.add(dbBtn, gbc);
        gbc.gridx = 1;
        panel.add(dbDirLabel, gbc);  
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(closeWin, gbc);

        this.add(panel);
        this.pack();
    }

    /**
     * Gets the covers storage folder location set by user
     * @return  The covers storage location
     */
    public String getCovLoc() {
        return "";
    }

    /**
     * Gets the ebooks storage folder location set by user
     * @return  The eBooks storage location
     */
    public String getEbookLoc() {
        return "";
    }

    /**
     * Closes the window
     */
    private void closeThis() {
        this.dispose();
    }
}
