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
    //private static List<String> reqtags = Arrays.asList(Tags.reqTags);
    private static List<String> preftags = Arrays.asList(Tags.tags);

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
     * @param product   Product from origin XML
     * @return          List of all missing tags for that product
     */
    public static ArrayList<String> getMissingTags(List<String> product) {
        ArrayList<String> result = new ArrayList<String>();
        for(String tag : preftags) {
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
        /** List of missing tags, where replaced tags can be deleted from */
        List<String> temp_missing = missingtags;
        for(String tag : missingtags) {
            String text = "";
            switch (tag) {
                case "a001":    //probably unnecessary ; Eindeutiges Kennzeichen für den Titeldatensatz
                    String a001 = "";
                    text = "\t\t<a001>" + a001 + "</a001>\r\n";
                    xmlstring.append(text);
                    break;
                case "a002":    //probably unnecessary ; Kennzeichen für durchzuführende Aktion (01|02 = NEW, 03 = UPDATE, 05 = DELETE)
                    String a002 = "";
                    text = "\t\t<a002>" + a002 + "</a002>\r\n";
                    xmlstring.append(text);
                    break;
                case "productidentifier":       //kann mehrfach vorkommen, wird mindestens einmal mit ISBN13-Angabe erwartet (optional: EAN, ISBN10) 
                    String b221_pi = "";        //15 (ISBN13) oder EAN oder ISBN10 oder alles
                    String b244_pi = "";        //ISBN13-/EAN-/ISBN10-Wert 
                    text = "\t\t<productidentifier>\r\n" + //
                           "\t\t\t<b221>" + b221_pi + "</b221>\r\n" + //
                           "\t\t\t<b244>" + b244_pi + "</b244>\r\n" + //
                           "\t\t</productidentifier>\r\n";
                    temp_missing.remove("productidentifier");
                    xmlstring.append(text);
                    break;
                case "series":
                    String b273_ser = "04";       //01 (proprietär), 02 (ISSN-basiert) oder 04 (VLB-Systematik) (-> see TODO)
                    String b244_ser = "";         //Reihen-ID 
                    String b202_ser = "01";       //01 (Haupttitel)
                    String b203_ser = "";         //Langtitel 
                    String b018_ser = "";         //Reihentitel (Alternative Eingabe über <b202> und <b203>)
                    String b019_ser = "";         //Bandnummer innerhalb der Reihe (optional)
                    text = "\t\t<series>\r\n" + //
                           "\t\t\t<seriesidentifier>\r\n" + //
                           "\t\t\t\t<b273>" + b273_ser + "</b273>\r\n" + //
                           "\t\t\t\t<b244>" + b244_ser + "</b244>\r\n" + //
                           "\t\t\t</seriesidentifier>\r\n" + //
                           "\t\t\t<title>\r\n" + //
                           "\t\t\t\t<b202>" + b202_ser + "</b202>\r\n" + //
                           "\t\t\t\t<b203>" + b203_ser + "</b203>\r\n" + //
                           "\t\t\t</title>\r\n" + //
                           "\t\t\t<b018>" + b018_ser + "</b018>\r\n" + //
                           "\t\t\t<b019>" + b019_ser + "</b019>\r\n" + //
                           "\t\t</series>\r\n";
                    temp_missing.remove("series");
                    xmlstring.append(text);
                    break;
                case "title":
                    String b202_tt = "01";            //01 (Haupttitel)
                    String b203_tt = "";              //Langtitel 
                    String b029_tt = "";              //Untertitel (optional)
                    text = "\t\t<title>\r\n" + //
                           "\t\t\t<b202>" + b202_tt + "</b202>\r\n" + //
                           "\t\t\t<b203>" + b203_tt + "</b203>\r\n" + //
                           "\t\t\t<b029>" + b029_tt + "</b029>\r\n" + //
                           "\t\t</title>\r\n";
                    temp_missing.remove("title");
                    xmlstring.append(text);
                    break;
                case "b368":                            //02 | Nicht angegeben in techn. dok.
                    String b368 = "";
                    text = "\t\t<b368>" + b368 + "</b368>\r\n";
                    xmlstring.append(text);
                    break;
                case "b369":                            //Universität Augsburg | Nicht angegeben in techn. dok.
                    String b369 = "";
                    text = "\t\t<b369>" + b369 + "</b369>\r\n";
                    xmlstring.append(text);
                    break;
                case "b370":                            //2022 | Nicht angegeben in techn. dok.
                    String b370 = "";
                    text = "\t\t<b370>" + b370 + "</b370>\r\n";
                    xmlstring.append(text);
                    break;
                case "contributor":                     //optional, kann mehrfach vorkommen (mindestens einmal erwünscht)
                    String b035_con = "";               //Rolle des/der Beteiligten (gemäß ONIX Codelist 17)
                    String b340_con = "";               //1 | Nicht angegeben in techn. dok.
                    String b036_con = "";               //Name als "Vorname Nachname" (optional) 
                    String b037_con = "";               //Name invertiert, d.h. als "Nachname, Vorname" 
                    String b039_con = "";               //Michael | Nicht angegeben in techn. dok.  (-> Vorname)
                    String b040_con = "";               //Bauer | Nicht angegeben in techn. dok.    (-> Nachname)
                    text = "\t\t<contributor>\r\n" + //
                           "\t\t\t<b035>" + b035_con + "</b035>\r\n" + //
                           "\t\t\t<b340>" + b340_con + "</b340>\r\n" + //
                           "\t\t\t<b036>" + b036_con + "</b036>\r\n" + //
                           "\t\t\t<b037>" + b037_con + "</b037>\r\n" + //
                           "\t\t\t<b039>" + b039_con + "</b039>\r\n" + //
                           "\t\t\t<b040>" + b040_con + "</b040>\r\n" + //
                           "\t\t</contributor>\r\n";
                    temp_missing.remove("contributor");
                    xmlstring.append(text);
                    break;
                case "b057":                    //Auflagennummer (optional, keine Angabe wird als „1. Auflage“ interpretiert) 
                    String b057 = "";
                    text = "\t\t<b057>" + b057 + "</b057>\r\n";
                    xmlstring.append(text);
                    break;
                case "b058":                    //1. Aufl. | Nicht angegeben in techn. dok.
                    String b058 = "";
                    text = "\t\t<b058>" + b058 + "</b058>\r\n";
                    xmlstring.append(text);    
                    break;
                case "language":                  //optional, kann mehrfach vorkommen (mindestens einmal erwünscht)
                    String b253_lg = "01";        //01 (Textsprache) 
                    String b252_lg = "";          //Sprachcode in Kleinschreibung (gemäß ONIX Codelist 74)
                    text = "\t\t<language>\r\n" + //
                           "\t\t\t<b253>" + b253_lg + "</b253>\r\n" + //
                           "\t\t\t<b252>" + b252_lg + "</b252>\r\n" + //
                           "\t\t</language>\r\n";
                    temp_missing.remove("language");
                    xmlstring.append(text);
                    break;
                case "b255":                    //ungefähre Seitenzahl (optional) 
                    String b255 = "";
                    text = "\t\t<b255>" + b255 + "</b255>\r\n";
                    xmlstring.append(text);    
                    break;
                case "mainsubject":               //kann mehrfach vorkommen (einmal pro Klassifizierungssystem) 
                    String b191_ms = "26";        //ID für Klassifizierungssystem (gemäß ONIX Codelist 26); unterstützt wird 26 (WGS) 
                    String b068_ms = "2.0";       //2.0 (erwartete Version des Klassifizierungssystems)
                    String b069_ms = "";          //Code für Klassifizierungsgruppe des bezogenen Klassifizierungssystems (z.B. 9770)
                    String b070_ms = "";          //Nicht angegeben in techn. dok. (-> Subject) (z.B. TB/Recht)
                    text = "\t\t<mainsubject>\r\n" + //
                           "\t\t\t<b191>" + b191_ms + "</b191>\r\n" + //
                           "\t\t\t<b068>" + b068_ms + "</b068>\r\n" + //
                           "\t\t\t<b069>" + b069_ms + "</b069>\r\n" + //
                           "\t\t\t<b070>" + b070_ms + "</b070>\r\n" + //
                           "\t\t</mainsubject>\r\n";
                    temp_missing.remove("mainsubject");
                    xmlstring.append(text);
                    break;
                case "subject":                     //optional, kann mehrfach vorkommen 
                    String b067_sb = "20";          //ID für Klassifizierungssystem (gemäß ONIX Codelist 27)
                    String b070_sb = "";            //Nicht angegeben in techn. dok. (-> Subject)
                    text = "\t\t<subject>\r\n" + //
                           "\t\t\t<b067>" + b067_sb + "</b067>\r\n" + //
                           "\t\t\t<b070>" + b070_sb + "</b070>\r\n" + //
                           "\t\t</subject>\r\n";
                    temp_missing.remove("subject");
                    xmlstring.append(text);
                    break;
                case "othertext":                   //optional, kann mehrfach vorkommen (mindestens einmal erwünscht) 
                    String d102_ot = "";            //01|03 (Hauptbeschreibung), 02 (Kurzbeschreibung), 04 (Inhaltsverzeichnis), 07|08 (Rezension), 23 (Textauszug) 
                    String d103_ot = "";            //unterstützte Werte: 00, 06, 07 (alle drei: ASCII) bzw. 02 (HTML) 
                    String d104_ot = "";            //Textbeschreibung (vorzugsweise mit HTML-Formatierungen 
                    String d105_ot = "";            //01 Nicht angegeben in techn. dok.
                    String d106_ot = "";            //https://www.verlagdrkovac.de/ISBN.htm Nicht angegeben in techn. dok.
                    text = "\t\t<othertext>\r\n" + //
                           "\t\t\t<d102>" + d102_ot + "</d102>\r\n" + //
                           "\t\t\t<d103>" + d103_ot + "</d103>\r\n" + //
                           "\t\t\t<d104>" + d104_ot + "</d104>\r\n" + //
                           "\t\t\t<d105>" + d105_ot + "</d105>\r\n" + //
                           "\t\t\t<d106>" + d106_ot + "</d106>\r\n" + //
                           "\t\t</othertext>\r\n";
                    temp_missing.remove("othertext");
                    xmlstring.append(text);
                    break;
                case "publisher":
                    String b291_pb = "01";                                  //01 (Hauptverlag)
                    String b241_pb = "05";                                  //Akzeptiert werden 04 (Börsenverein Verkehrsnummer), 05 (ISBN-Agentur-Nummer), 01 (Proprietäre Kennung)
                    String b243_pb = "92083";                               //Nummer des Verlages
                    String b081_pb = "Verlag Dr. Kovac GmbH";               //Verlagsbezeichnung 
                    text = "\t\t<publisher>\r\n" + //
                           "\t\t\t<b291>" + b291_pb + "</b291>\r\n" + //
                           "\t\t\t<b241>" + b241_pb + "</b241>\r\n" + //
                           "\t\t\t<b243>" + b243_pb + "</b243>\r\n" + //
                           "\t\t\t<b081>" + b081_pb + "</b081>\r\n" + //
                           "\t\t</publisher>\r\n";
                    temp_missing.remove("publisher");
                    xmlstring.append(text);
                    break;
                case "b209":
                    text = "\t\t<b209>Hamburg</b209>\r\n";      //Veröffentlichungsort
                    xmlstring.append(text);
                    break;
                case "b003":            //Veröffentlichungsdatum (im Format yyyyMMdd bzw. yyyyMM bzw. yyyy) 
                    String b003 = "";
                    text = "\t\t<b003>" + b003 + "</b003>\r\n";
                    xmlstring.append(text);
                    break;
                case "relatedproduct":
                    String h208_rp = "13";        
                    String b221_rp = "15";     //ISBN13
                    String b244_rp = "";       //ISBN13-Angabe zum bezogenen Print-Titel 
                    text = "\t\t<relatedproduct>\r\n" + //
                           "\t\t\t<h208>" + h208_rp + "</h208>\r\n" + //
                           "\t\t\t<productidentifier>\r\n" + //
                           "\t\t\t\t<b221>" + b221_rp + "</b221>\r\n" + //
                           "\t\t\t\t<b244>" + b244_rp + "</b244>\r\n" + //
                           "\t\t\t</productidentifier>\r\n" + //
                           "\t\t</relatedproduct>\r\n";
                    temp_missing.remove("relatedproduct");
                    xmlstring.append(text);
                    break;
                case "supplydetail":
                    String j345_sd = "";                        //05 Nicht gegeben in techn. dok.
                    String b244_sd = "";                        //Nicht gegeben in techn. dok.
                    String j137_sd = "Verlag Dr. Kovac GmbH";
                    String j292_sd = "";                        //01 Nicht gegeben in techn. dok.
                    String j396_sd = "";                        //10 Nicht gegeben in techn. dok.
                    String j260_sd = "";                        //01 Nicht gegeben in techn. dok.
                    String j142_sd = "";                        //Datum der Auslieferung yymmdd (optional)
                    String j148_sd = "";                        //Preisart gemäß ONIX Codelist 58 (unterstützt werden Bruttopreistypen 02, 04, 12, 14)  | Für US Dollar: 01
                    String j266_sd = "";                        //02 Nicht gegeben in techn. dok.                                                       | Für US Dollar: -
                    String j151_sd = "";                        //Preisangabe als Fließkommazahl („.“ als Dezimaltrenner!)                              | Für US Dollar: Preisangabe als Fließkommazahl („.“ als Dezimaltrenner!) 
                    String j152_sd = "";                        //EUR (Währungscode für Euro)                                                           | Für US Dollar: USD (Währungscode für US Dollar) 
                    String b251_sd = "";                        //DE (Ländercode passend zur Angabe des MwSt.-Satzes)                                   | Für US Dollar: US (Ländercode passend zur Angabe des MwSt.-Satzes) 
                    String j153_sd = "";                        //R (ermäßigter Satz gilt für alle eBooks)                                              | Für US Dollar: Z (Nettopreis)
                    text = "\t\t<supplydetail>\r\n" + //
                           "\t\t\t<supplieridentifier>\r\n" + //
                           "\t\t\t\t<j345>" + j345_sd + "</j345>\r\n" + //
                           "\t\t\t\t<b244>" + b244_sd + "</b244>\r\n" + //
                           "\t\t\t</supplieridentifier>\r\n" + //
                           "\t\t\t<j137>" + j137_sd + "</j137>\r\n" + //
                           "\t\t\t<j292>" + j292_sd + "</j292>\r\n" + //
                           "\t\t\t<j396>" + j396_sd + "</j396>\r\n" + //
                           "\t\t\t<j260>" + j260_sd + "</j260>\r\n" + //
                           "\t\t\t<j142>" + j142_sd + "</j142>\r\n" + //
                           "\t\t\t<price>\r\n" + //
                           "\t\t\t\t<j148>" + j148_sd + "</j148>\r\n" + //
                           "\t\t\t\t<j266>" + j266_sd + "</j266>\r\n" + //
                           "\t\t\t\t<j151>" + j151_sd + "</j151>\r\n" + //
                           "\t\t\t\t<j152>" + j152_sd + "</j152>\r\n" + //
                           "\t\t\t\t<b251>" + b251_sd + "</b251>\r\n" + //
                           "\t\t\t\t<j153>" + j153_sd + "</j153>\r\n" + //
                           "\t\t\t</price>\r\n" + //
                           "\t\t</supplydetail>\r\n";
                    temp_missing.remove("supplydetail");
                    xmlstring.append(text);
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

/*
 * TODO WICHTIG Sachen wie "Ort der Veröffentlichung" "Verlagsnummer" im Settings Menü festlegen lassen -> nicht Hard coden
 */
