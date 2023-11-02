package com.archiving;
import com.archiving.models.Product;

import java.util.ArrayList;
import java.util.Scanner;

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
        
        Scanner scannerLoc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter file location: ");
        filelocation = scannerLoc.nextLine(); 
        Scanner scannerTgt = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter target location: ");
        targetlocation = scannerTgt.nextLine(); 
    
        readFiles(getFiles(filelocation));

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
     */
    private static void readFiles(ArrayList<File> files) {

        ArrayList<ArrayList<Product>> products = new ArrayList<ArrayList<Product>>();
        Parser fileparser = new Parser(); 
        for(File file : files) {
            fileparser.setFile(file);
            products.add(fileparser.getContents());
        }
        
        //for(ArrayList<Product> elem : products) {
        //    for(Product prod : elem) {
        //        fileparser.printProduct(prod);
        //    }
        //}
    }

}
