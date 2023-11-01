package com.archiving;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import com.archiving.models.Product;

/**
 * Parser for XML file
 * 
 * @author Maxwell Leu
 */
public class Parser {
    
    private File file;

    public Parser() {
    } 
    
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Get the content of a file
     * @param file  File to be read
     * @return  List of products listed in the file
     */
    public ArrayList<Product> getContents() {

        ArrayList<Product> products = new ArrayList<Product>();

        try {
            Scanner filescanner = new Scanner(this.file);
            int filelength = (int) Files.lines(this.file.toPath()).count();
            int i = 0;   //for normal functionality of while loop
            int prodidx = 0;    //index of beginning of a new product
            boolean end = false;    //if end of a product is reached (so that the last part of the XML is cut off)
            boolean header = true;  //if header part of XML is being read (so that the top part of the xml is cut off)
            ArrayList<String> _products = new ArrayList<String>(); 
            while (i < filelength) {
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
                i++;
            }
            filescanner.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return products;

    }

}
