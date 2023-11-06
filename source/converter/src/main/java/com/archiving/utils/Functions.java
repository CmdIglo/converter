package com.archiving.utils;

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

}
