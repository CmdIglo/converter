package com.archiving;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.archiving.models.Product;
import com.archiving.models.Tags;

/**
 * Parser for XML file
 * 
 * @author Maxwell Leu
 */
public class Parser {
    
    /** List of tags as Strings */
    private String[] tags = {
        "a001",
        "a002",
        "productidentifier",
        "b221",
        "b244",
        "b012",
        "b211",
        "series",
        "seriesidentifier",
        "b273",
        "b018",
        "b019",
        "b020",
        "n338",
        "title",
        "b202",
        "b203",
        "b029",
        "contributor",
        "b034",
        "b035",
        "b036",
        "b037",
        "b047",
        "b044",
        "b057",
        "n386",
        "language",
        "b253",
        "b252",
        "b061",
        "b255",
        "mainsubject",
        "b191",
        "b068",
        "b069",
        "b064",
        "b065",
        "subject",
        "b067",
        "othertext",
        "d102",
        "d103",
        "d104",
        "imprint",
        "b079",
        "publisher",
        "b291",
        "b081",
        "b241",
        "b243",
        "b209",
        "b083",
        "b003",
        "salesrights",
        "b089",
        "b090",
        "b388",
        "relatedproduct",
        "h208",
        "supplydetail",
        "j141",
        "j396",
        "j142",
        "j143",
        "price",
        "j148",
        "j151",
        "j152",
        "b251",
        "j153",
        "j161",
        "j162"
    };

    /** List of tags as Tags Enum Objects */
    private Tags[] _tags = {
        Tags.a001,
        Tags.a002,
        Tags.productidentifier,
        Tags.b221,
        Tags.b244,
        Tags.b012,
        Tags.b211,
        Tags.series,
        Tags.seriesidentifier,
        Tags.b273,
        Tags.b018,
        Tags.b019,
        Tags.b020,
        Tags.n338,
        Tags.title,
        Tags.b202,
        Tags.b203,
        Tags.b029,
        Tags.contributor,
        Tags.b034,
        Tags.b035,
        Tags.b036,
        Tags.b037,
        Tags.b047,
        Tags.b044,
        Tags.b057,
        Tags.n386,
        Tags.language,
        Tags.b253,
        Tags.b252,
        Tags.b061,
        Tags.b255,
        Tags.mainsubject,
        Tags.b191,
        Tags.b068,
        Tags.b069,
        Tags.b064,
        Tags.b065,
        Tags.subject,
        Tags.b067,
        Tags.othertext,
        Tags.d102,
        Tags.d103,
        Tags.d104,
        Tags.imprint,
        Tags.b079,
        Tags.publisher,
        Tags.b291,
        Tags.b081,
        Tags.b241,
        Tags.b243,
        Tags.b209,
        Tags.b083,
        Tags.b003,
        Tags.salesrights,
        Tags.b089,
        Tags.b090,
        Tags.b388,
        Tags.relatedproduct,
        Tags.h208,
        Tags.supplydetail,
        Tags.j141,
        Tags.j396,
        Tags.j142,
        Tags.j143,
        Tags.price,
        Tags.j148,
        Tags.j151,
        Tags.j152,
        Tags.b251,
        Tags.j153,
        Tags.j161,
        Tags.j162
    };

    /** File to be parsed */
    private File file;

    public Parser() {
    } 
    
    /**
     * Set file class variable
     * @param file  The file to be parsed
     */
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
            
            Product NewProduct = new Product();
            boolean first = true;
            for(String line : _products) {
                if(line.equals("Next Product") && (!first)) {
                    NewProduct = new Product();
                } else if(line.equals("Next Product") && (first)){
                    first = false;
                } else if(line.equals("End Product")) {
                    products.add(NewProduct);
                } else if(isTagLine(line)){
                    String content = splitLine(line);
                    switch(getTag(line)) {
                        case a001:
                            NewProduct.a001 = content;
                            break;
                        case a002:
                            NewProduct.a002 = content;
                            break;
                        case b003:
                            NewProduct.b003 = content;
                            break;
                        case b012:
                            NewProduct.b012 = content;
                            break;
                        case b018:
                            NewProduct.series.b018 = content;
                            break;
                        case b019:
                            NewProduct.series.b019 = content;
                            break;
                        case b020:
                            NewProduct.series.b020 = content;
                            break;
                        case b029:
                            NewProduct.title.b029 = content;
                            break;
                        case b034:
                            NewProduct.contributor.b034 = content;
                            break;
                        case b035:
                            NewProduct.contributor.b035 = content;
                            break;
                        case b036:
                            NewProduct.contributor.b036 = content;
                            break;
                        case b037:
                            NewProduct.contributor.b037 = content;
                            break;
                        case b044:
                            NewProduct.contributor.b044 = content;
                            break;
                        case b047:
                            NewProduct.contributor.b047 = content;
                            break;
                        case b057:
                            NewProduct.b057 = content;
                            break;
                        case b061:
                            NewProduct.b061 = content;
                            break;
                        case b064:
                            NewProduct.b064 = content;
                            break;
                        case b065:
                            NewProduct.b065 = content;
                            break;
                        case b067:
                            NewProduct.subject.b067 = content;
                            break;
                        case b068:
                            NewProduct.mainsubject.b068 = content;
                            break;
                        case b069:
                            NewProduct.mainsubject.b069 = content;
                            break;
                        case b079:
                            NewProduct.imprint.b079 = content;
                            break;
                        case b081:
                            NewProduct.publisher.b081 = content;
                            break;
                        case b083:
                            NewProduct.b083 = content;
                            break;
                        case b089:
                            NewProduct.salesrights.b089 = content;
                            break;
                        case b090:
                            NewProduct.salesrights.b090 = content;
                            break;
                        case b191:
                            NewProduct.mainsubject.b191 = content;
                            break;
                        case b202:
                            NewProduct.title.b202 = content;
                            break;
                        case b203:
                            NewProduct.title.b203 = content;
                            break;
                        case b209:
                            NewProduct.b209 = content;
                            break;
                        case b211:
                            NewProduct.b211 = content;
                            break;
                        case b221:
                            NewProduct.productidentifier.b221 = content;
                            break;
                        case b241:
                            NewProduct.publisher.b241 = content;
                            break;
                        case b243:
                            NewProduct.publisher.b243 = content;
                            break;
                        case b244:
                            NewProduct.productidentifier.b244 = content;
                            break;
                        case b251:
                            NewProduct.supplydetail.price.b251 = content;
                            NewProduct.supplydetail.b251 = content;
                            break;
                        case b252:
                            NewProduct.language.b252 = content;
                            break;
                        case b253:
                            NewProduct.language.b253 = content;
                            break;
                        case b255:
                            NewProduct.b255 = content;
                            break;
                        case b273:
                            NewProduct.series.identifier.b273 = content;
                            break;
                        case b291:
                            NewProduct.publisher.b291 = content;
                            break;
                        case b388:
                            NewProduct.salesrights.b388 = content;
                            break;
                        case contributor:
                            break;
                        case d102:
                            NewProduct.othertext.d102 = content;
                            break;
                        case d103:
                            NewProduct.othertext.d103 = content;
                            break;
                        case d104:
                            NewProduct.othertext.d104 = content;
                            break;
                        case h208:
                            NewProduct.relatedproduct.h208 = content;
                            break;
                        case imprint:
                            break;
                        case j141:
                            NewProduct.supplydetail.j141 = content;
                            break;
                        case j142:
                            NewProduct.supplydetail.j142 = content;
                            break;
                        case j143:
                            NewProduct.supplydetail.j143 = content;
                            break;
                        case j148:
                            NewProduct.supplydetail.j148 = content;
                            NewProduct.supplydetail.price.j148 = content;
                            break;
                        case j151:
                            NewProduct.supplydetail.j151 = content;
                            NewProduct.supplydetail.price.j151 = content;
                            break;
                        case j152:
                            NewProduct.supplydetail.j152 = content;
                            NewProduct.supplydetail.price.j152 = content;
                            break;
                        case j153:
                            NewProduct.supplydetail.j153 = content;
                            NewProduct.supplydetail.price.j153 = content;
                            break;
                        case j161:
                            NewProduct.supplydetail.j161 = content;
                            NewProduct.supplydetail.price.j161 = content;
                            break;
                        case j162:
                            NewProduct.supplydetail.j162 = content;
                            NewProduct.supplydetail.price.j162 = content;
                            break;
                        case j396:
                            NewProduct.supplydetail.j396 = content;
                            break;
                        case language:
                        case mainsubject:
                            break;
                        case n338:
                            NewProduct.n338 = content;
                            break;
                        case n386:
                            NewProduct.n386 = content;
                            break;
                        case othertext:
                        case price:
                        case productidentifier:
                        case publisher:
                        case relatedproduct:
                        case salesrights:
                        case series:
                        case seriesidentifier:
                        case subject:
                        case supplydetail:
                        case title:
                            break;
                        case notincluded:
                            break;
                    }
                } 
            } 

        } catch(Exception e) {
            e.printStackTrace();
        }

        return products;

    }

    /**
     * Checks if a line is a "tagline" (a line where info is stored in a tag)
     * @param line  The line to be checked
     * @return      If line is a "tagline"
     */
    private boolean isTagLine(String line) {
        return((countOcc('<', line) == 2) && (countOcc('>', line) == 2) && (countOcc('/', line) == 1));
    }

    /**
     * Counts occurences of a certain character in a given string
     * @param chr   The character
     * @param str   The string
     * @return      Number of occurrences of the character in the string
     */
    private int countOcc(char chr, String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == chr) {
                count++;
            }
        }
        return count;
    }

    /**
     * Splits line and gets the content between the two tags of the line
     * @param line  The line to be split
     * @return      The content between the tags
     */
    public String splitLine(String line) {
        String content = line.split(">")[1].split("<")[0];
        return content;
    }

    /**
     * Gets the tag of the line (i.e. what info is written in the line)
     * @param line  The line where the tag is to be fetched
     * @return      The tag of the line
     */
    public Tags getTag(String line) {
        String tag = line.split("<")[1].split(">")[0];
        if(Arrays.asList(tags).contains(tag)) {
            int index = Arrays.asList(tags).indexOf(tag);
            return _tags[index];
        }
        return Tags.notincluded;
    }

    /**
     * Print a Product
     * @param prod  Product to be printed
     */
    public void printProduct(Product prod) {
        System.out.println("A001: " + prod.a001);
        System.out.println(prod.title.b203);
    }

    /**
     * Returns the file name of the file stored in the parser
     * @return  File Name
     */
    public String getFile() {
        return this.file.getName();
    }

}
