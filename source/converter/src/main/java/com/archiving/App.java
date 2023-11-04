package com.archiving;

import java.util.ArrayList;
import java.util.Scanner;

import com.archiving.gui.MainWindow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main Class
 *
 * @author Maxwell Leu
 */
public class App {

    /** Location of the book collection xmls */
    private static String filelocation;
    /** Location where the ProQuest XMLs shall be stored */
    private static String targetlocation;

    public static void main( String[] args ) {
        
        Scanner scannerLoc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter file location: ");
        filelocation = scannerLoc.nextLine(); 
        Scanner scannerTgt = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter target location: ");
        targetlocation = scannerTgt.nextLine(); 
        MainWindow Win = new MainWindow(400, 400, "Konvertierer");
        Win.getWindow().setResizable(false);
        Win.getWindow().setVisible(true);
        
        Writer FileWriter = new Writer(targetlocation);
        Parser Formatter = new Parser();
        FileWriter.setContents(Formatter.formatContents(readFiles(getFiles(filelocation))));
        FileWriter.write();
    }

    /**
     * Get all files in given directory
     * @param path  Path of Directory
     * @return  List of files
     */
    private static ArrayList<File> getFiles(String path) {

        ArrayList<File> files = new ArrayList<File>();

        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                files.add(child);
            }
        } else {
            System.out.println("Directory provided is empty");
        }

        return files;
    }

    /**
     * Read all files
     * @param files List of files to be read
     * @return      List of contents of files
     */
    private static ArrayList<ArrayList<String>> readFiles(ArrayList<File> files) {

        ArrayList<ArrayList<String>> products = new ArrayList<ArrayList<String>>();
        Parser fileparser = new Parser(); 
        for(File file : files) {
            fileparser.setFile(file);
            products.add(fileparser.getContents());
        }
        
        return products;
    }

}
