package com.archiving;

import java.util.ArrayList;

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
                "\t<product>";
        String xmlend = "\t</product>\r\n" + //
                "</ONIXmessage>";
        String xmlfile = xmlhead + content + xmlend;
        return xmlfile;
    }

}
