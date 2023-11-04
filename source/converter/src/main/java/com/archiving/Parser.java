package com.archiving;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parser for XML file
 * 
 * @author Maxwell Leu
 */
public class Parser {
    
    /** File to be parsed */
    private File file;
    
    /**
     * Set file class variable
     * @param file  The file to be parsed
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Returns the file name of the file stored in the parser
     * @return  File Name
     */
    public String getFile() {
        return this.file.getName();
    }

    /**
     * Get the content of a file
     * @param file  File to be read
     * @return  List of products listed in the file
     */
    public ArrayList<String> getContents() {

        ArrayList<String> products = new ArrayList<String>();

        try {
            
            Scanner filescanner = new Scanner(this.file);
            int filelength = (int) Files.lines(this.file.toPath()).count();
            int i = 0;   //for normal functionality of while loop
            int prodidx = 0;    //index of beginning of a new product
            boolean end = false;    //if end of a product is reached (so that the last part of the XML is cut off)
            boolean header = true;  //if header part of XML is being read (so that the top part of the xml is cut off)
            ArrayList<String> _products = new ArrayList<String>(); 
            
            while (i < filelength && filescanner.hasNextLine()) {
                try {
                    String data = filescanner.nextLine();
                    if(data.equals("\t<product>")) {    //if beginning of product
                        header = false;     //header has been read and ignored
                        end = false;    //new product part in xml
                        prodidx = i;    //product start index
                        _products.add("Next Product");
                    } else if(data.equals("\t</product>")) {
                        end = true;     //end of product tag is reached
                        _products.add("End Product");
                    } else if((i > prodidx) && !end && !header) {   //if line is under the "<product>" tag, the "</product>" tag hasn't been reached and line is not part of header part
                        _products.add(data);
                    }
                } catch(NoSuchElementException e) {
                    System.out.println(this.file.getName());
                }
                i++;
            }
            
            filescanner.close();

            return _products;
            
        } catch(Exception e) {
            e.printStackTrace();
        }

        return products;

    }

    /**
     * Format XML files content
     * @param content   List of contents returned by the App.readFiles() function
     * @return          List of XML contents stored in another list containing the products' info as one format String
     */
    public ArrayList<ArrayList<String>> formatContents(ArrayList<ArrayList<String>> content) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        for(ArrayList<String> file : content) {
            ArrayList<Integer> prodindices = new ArrayList<Integer>();
            for(int i = 0; i < file.size(); i++) {
                if(file.get(i).equals("Next Product")) {
                    prodindices.add(i);
                }
            }
            ArrayList<String> products = new ArrayList<String>();
            for(int j = 0; j < prodindices.size(); j++) {
                StringBuilder xmlstring = new StringBuilder();
                if(j == (prodindices.size() - 1)) {
                    List<String> xmlcon = file.subList(prodindices.get(j)+1, file.size()-2);
                    for(String elem : xmlcon) {
                        xmlstring.append(elem);
                        xmlstring.append("\n");
                    }
                } else {
                    List<String> xmlcon = file.subList(prodindices.get(j)+1, prodindices.get(j+1)-1);
                    for(String elem : xmlcon) {
                        xmlstring.append(elem);
                        xmlstring.append("\n");
                    }
                }
                products.add(xmlstring.toString());
            }
            result.add(products);
        }
        return result;
    }

}
