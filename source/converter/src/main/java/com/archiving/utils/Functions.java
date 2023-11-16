package com.archiving.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.archiving.Parser;

/**
 * Helper functions to avoid redundancy
 * 
 * @author Maxwell Leu
 */
public class Functions {
    
    /** The required tags for a ProQuest XML */
    private static List<String> reqtags = Arrays.asList(Tags.reqTags);

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
     * Returns a list of missing tags for complete proQuest xml
     * @param product
     * @return
     */
    public static ArrayList<String> getMissingTags(List<String> product) {
        ArrayList<String> result = new ArrayList<String>();
        for(String tag : reqtags) {
            boolean found = false;
            for(String line : product) {
                if(getTag(line).equals(tag)) {
                    found = true;
                }
            }
            if(!found) {
                result.add(tag);
            }
        }
        return result;
    }

    /**
     * Add the missing tags to the xmlstring
     * @param xmlstring     The string, where the missing tags content is supposed to be appended to
     * @param missingtags   The missing tags for the product
     * @param xmlcon        The xmlstring content as List
     * @return              The modified stringbuilder
     */
    public static StringBuilder addMissingTags(StringBuilder xmlstring, List<String> missingtags, List<String> xmlcon) {
        for(String tag : missingtags) {
            switch (tag) {
                case "a001":    //probably unnecessary
                    break;
                case "a002":    //probably unnecessary
                    break;
                case "productidentifier":
                    break;
                case "b221":
                    break;
                case "b244":
                    break;
                case "b012":
                    break;
                case "b211":
                    break;
                case "series":
                    break;
                case "seriesidentifier":
                    break;
                case "b273":
                    break;
                case "b018":
                    break;
                case "n338":
                    break;
                case "title":
                    break;
                case "b202":
                    break;
                case "b203":
                    break;
                case "contributor":
                    break;
                case "b034":
                    break;
                case "b035":
                    break;
                case "b036":
                    break;
                case "b037":
                    break;
                case "b047":
                    break;
                case "b044":
                    break;
                case "language":
                    break;
                case "b253":
                    break;
                case "b252":
                    break;
                case "mainsubject":
                    break;
                case "b191":
                    break;
                case "b068":
                    break;
                case "b069":
                    break;
                case "subject":
                    break;
                case "b067":
                    break;
                case "othertext":
                    break;
                case "d102":
                    break;
                case "d103":
                    break;
                case "d104":
                    break;
                case "imprint":
                    break;
                case "b079":
                    break;
                case "publisher":
                    break;
                case "b291":
                    break;
                case "b081":
                    break;
                case "b241":
                    break;
                case "b243":
                    break;
                case "b003":
                    break;
                case "salesrights":
                    break;
                case "b089":
                    break;
                case "b090":
                    break;
                case "b388":
                    break;
                case "relatedproduct":
                    break;
                case "h208":
                    break;
                case "supplydetail":
                    break;
                case "j141":
                    break;
                case "j396":
                    break;
                case "price":
                    break;
                case "j148":
                    break;
                case "j151":
                    break;
                case "j152":
                    break;
                case "b251":
                    break;
                case "j153":
                    break;
                default:
                    break;
            }
        }
        return xmlstring;
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
