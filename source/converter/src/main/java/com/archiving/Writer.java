package com.archiving;

import java.util.ArrayList;

/**
 * Writer Class
 * 
 * @author Maxwell Leu
 */
public class Writer {
    
    /** The path the writer writes to */
    private String path;
    /** List of all products in the directory */
    private ArrayList<ArrayList<String>> contents;

    /**
     * Class constructor
     * @param path  The path the writer writes to
     */
    public Writer(String path) {
        this.path = path;
    }

    /**
     * Set contents class variable
     * @param contents  The value the class variable shall be set to
     */
    public void setContents(ArrayList<ArrayList<String>> contents) {
        this.contents = contents;
    }

    /**
     * Write the files into the folder     
     */
    public void write() {
        
    }

}
