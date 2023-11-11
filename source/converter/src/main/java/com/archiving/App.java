package com.archiving;

import java.util.ArrayList;
import java.util.Scanner;

import com.archiving.gui.MainWindow;
import com.archiving.utils.Functions;

import java.io.File;

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
        /*
        Scanner scannerLoc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter file location: ");
        filelocation = scannerLoc.nextLine(); 
        Scanner scannerTgt = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter target location: ");
        targetlocation = scannerTgt.nextLine(); 
        scannerLoc.close();
        scannerTgt.close(); */
        MainWindow Win = new MainWindow(400, 400, "Konvertierer");
        Win.getWindow().setResizable(false);
        Win.getWindow().setVisible(true);
    }

}
