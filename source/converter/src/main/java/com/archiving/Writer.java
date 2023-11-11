package com.archiving;

import java.util.ArrayList;
import java.util.Arrays;

import com.archiving.utils.Functions;
import com.archiving.utils.Tags;

import java.io.BufferedWriter;
import java.io.File;
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
     * Used for Tests 
     */
    public void write() {
        this.contents = prepareContent(this.contents);
        for(ArrayList<String> list : this.contents) {
            for(String elem : list) {
                try{
                    //String prodId = elem.split("<b244>")[1].split("</b244>")[0] + ".xml";
                    BufferedWriter writer = new BufferedWriter(new FileWriter("books\\temp.txt", true));
                    writer.append("\n");
                    writer.append(buildXML(elem));
                    writer.close();
                } catch(IOException | ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Write the files into the given folder     
     */
    public void write(String target) {
        this.contents = prepareContent(this.contents);
        for(ArrayList<String> list : this.contents) {
            for(String elem : list) {
                try{

                    //TODO figure out what happens, when a prod ID occurs more than once -> something like for i++ if xml name has prod ID in it and (i) in it try naming it next i

                    String prodId = elem.split("<b244>")[1].split("</b244>")[0] + ".xml";
                    File targetLocDir = new File(target+"\\books");
                    if(!targetLocDir.exists()) {
                       targetLocDir.mkdirs(); 
                    }                    
                    File BookfIle = new File(target + "\\books\\" + prodId);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(BookfIle, false));
                    writer.append("\n");
                    writer.append(buildXML(elem));
                    writer.close();
                } catch(IOException | ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println(elem);
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

        for(ArrayList<String> product : content) {          //"product" is an array of products, where line (see below) is a product
            for(String line : product) {
                ArrayList<String> prodtags = Functions.getTags(line);
                ArrayList<String> missingtags = new ArrayList<String>();
                for(String tag : reqtags) {
                    if(!(prodtags.contains(tag))) {                     //if a required tag is missing from the product 
                        //add the tag to the file with missing information fetched from the database
                        missingtags.add(tag);
                    }
                }
                StringBuilder missinglabel = new StringBuilder();
                ArrayList<String> _tags = missingtags;                  //so that one array can be changed for the functions in the switch statement to look up if a tag hase already been set
                missinglabel.append("\nMissing Tags: ");
                for(String tag : missingtags) {
                    missinglabel.append(tag);           //for development and testing
                    if(_tags.contains(tag)) {
                        switch (tag) {
                            case "a001":
                                break;
                            case "a002":
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
                }
                //line.concat(missinglabel.toString());
                //line.concat("Eigentlich funktionierts");
            }
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
