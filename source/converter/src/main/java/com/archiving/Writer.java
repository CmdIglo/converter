package com.archiving;

import java.util.ArrayList;
import java.util.Arrays;

import com.archiving.utils.Functions;
import com.archiving.utils.Tags;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    /** The required tags for ProQuest XML */
    private ArrayList<String> reqtags = new ArrayList<String>(Arrays.asList(Tags.reqTags));

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
        this.contents = prepareContent(this.contents);
        for(ArrayList<String> list : this.contents) {
            for(String elem : list) {
                try{
                    //String prodId = elem.split("<b244>")[1].split("</b244>")[0] + ".xml";
                    BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt", true));
                    writer.append("\n");
                    writer.append(buildXML(elem));
                    writer.close();
                } catch(IOException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("yolo");
                }
            }
        }
    }

    /**
     * Prepare the xml content by adding missing tags
     * @param content   The XML content
     * @return          Prepared content with missing informations
     */
    private ArrayList<ArrayList<String>> prepareContent(ArrayList<ArrayList<String>> content) {
        
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

        for(ArrayList<String> product : content) {
            ArrayList<String> prodtags = new ArrayList<String>();   //all tags in the product
            for(String line : product) {
                prodtags.add(Functions.getTag(line));
            }
            ArrayList<String> missingtags = new ArrayList<String>();
            for(String tag : reqtags) {
                if(!(prodtags.contains(tag))) {                     //if a required tag is missing from the product 
                    //add the tag to the file with missing information fetched from the database
                    missingtags.add(tag);
                }
            }
            StringBuilder missinglabel = new StringBuilder();
            missinglabel.append("\nMissing Tags: ");
            for(String tag : missingtags) {
                missinglabel.append(tag);
            }
            product.add(missinglabel + "\n");       //TODO look into missing tags and blank lines -> work out, why there is no product sometimes
            result.add(product);
        }

        return result;
    }

    /**
     * Builds the XML file for a book
     * @param content   The content fetched from the parser
     * @return          String representation of XML
     */
    private String buildXML(String content) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Format the current date to YYYYMMDD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        String xmlhead = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>\r\n" + //
                "<!DOCTYPE ONIXmessage SYSTEM \"https://www.editeur.org/onix/2.1/short/onix-international.dtd\">\r\n" + //
                "<ONIXmessage  release=\"2.1\">\r\n" + //
                "\t<header>\r\n" + //
                "\t\t<m174>Verlag Dr. Kovac</m174>\r\n" + //
                "\t\t<m175>Michael Leu, (+49) 40 39 88 80 - 25</m175>\r\n" + //
                "\t\t<m283>info@verlagdrkovac.de</m283>\r\n" + //
                "\t\t<m182>" + formattedDate + "</m182>\r\n" + //
                "\t</header>\r\n" + //
                "\t<salesrights>\r\n" + //
                "\t<b089>02</b089>\r\n" + //
                "\t<b388>WORLD</b388>\r\n" + //
                "\t</salesrights>\r\n" + //
                "\t<product>\n";
        String xmlend = "\t</product>\r\n" + //
                "</ONIXmessage>\n";
        String xmlfile = xmlhead + content + xmlend;
        return xmlfile;
    }

}
