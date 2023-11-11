package com.archiving.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.archiving.Parser;

/**
 * Helper functions to avoid redundancy
 * 
 * @author Maxwell Leu
 */
public class Functions {
    
    /**
     * Get the tag of the file line
     * @param line  The line of which the tag is being returned
     * @return      The tag of the line
     */
    public static String getTag(String line) {
        return line.split("<")[1].split(">")[0];
    }

    /**
     * Gets all tags in a product-line
     * @param product   The product represented as one string
     * @return          All tags occuring in the product string
     */
    public static ArrayList<String> getTags(String product) {
        ArrayList<String> tags = new ArrayList<String>();
        // Regular expression pattern to match text between '<' and '>'
        Pattern pattern = Pattern.compile("<(.*?)>");
        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(product);
        // Find all matches
        while (matcher.find()) {
            String extractedText = matcher.group(1);
            tags.add(extractedText);
        }
        return tags;
    }

    /**
     * Get all files in given directory
     * @param path  Path of Directory
     * @return  List of files
     */
    public static ArrayList<File> getFiles(String path) {

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
    public static ArrayList<ArrayList<String>> readFiles(ArrayList<File> files) {

        ArrayList<ArrayList<String>> products = new ArrayList<ArrayList<String>>();
        Parser fileparser = new Parser(); 
        for(File file : files) {
            fileparser.setFile(file);
            products.add(fileparser.getContents());
        }
        
        return products;
    }

}
