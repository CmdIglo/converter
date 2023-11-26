package com.archiving.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

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
    /** For Development purposes -> change to a config system */
    private static String database_path = "jdbc:ucanaccess://C:\\Users\\maxwe\\OneDrive\\Desktop\\Projects\\DB\\ONIX-eBook - ProQuest.accdb";

    private static String xml = "\t\t<a001></a001>\r\n" + //
         "\t\t<a002>02</a002>\r\n" + //
         "\t\t<productidentifier>\r\n" + //
         "\t\t\t<b221>15</b221>\r\n" + //
         "\t\t\t<b244></b244>\r\n" + //
         "\t\t</productidentifier>\r\n" + //
         "\t\t<productidentifier>\r\n" + //
         "\t\t\t<b221></b221>\r\n" + //
         "\t\t\t<b244></b244>\r\n" + //
         "\t\t</productidentifier>\r\n" + //
         "\t\t<productidentifier>\r\n" + //
         "\t\t\t<b221></b221>\r\n" + //
         "\t\t\t<b244></b244>\r\n" + //
         "\t\t</productidentifier>\r\n" + //
         "\t\t<b012>DG</b012>\r\n" + //
         "\t\t<b211>02</b211>\r\n" + //
         "\t\t<series>\r\n" + //
         "\t\t\t<seriesidentifier>\r\n" + //
         "\t\t\t\t<b273></b273>\r\n" + //
         "\t\t\t\t<b244></b244>\r\n" + //
         "\t\t\t</seriesidentifier>\r\n" + //
         "\t\t\t<title>\r\n" + //
         "\t\t\t\t<b202></b202>\r\n" + //
         "\t\t\t\t<b203></b203>\r\n" + //
         "\t\t\t</title>\r\n" + //
         "\t\t\t<b018></b018>\r\n" + //
         "\t\t\t<b019></b019>\r\n" + //
         "\t\t</series>\r\n" + //
         "\t\t<title>\r\n" + //
         "\t\t\t<b202></b202>\r\n" + //
         "\t\t\t<b203></b203>\r\n" + //
         "\t\t\t<b029></b029>\r\n" + //
         "\t\t</title>\r\n" + //
         "\t\t<b368></b368>\r\n" + //
         "\t\t<b369></b369>\r\n" + //
         "\t\t<b370></b370>\r\n" + //
         "\t\t<contributor>\r\n" + //
         "\t\t\t<b035></b035>\r\n" + //
         "\t\t\t<b340></b340>\r\n" + //
         "\t\t\t<b036></b036>\r\n" + //  Vorname Nachname
         "\t\t\t<b037></b037>\r\n" + // Nachname, Vorname
         "\t\t\t<b039></b039>\r\n" + // Vorname
         "\t\t\t<b040></b040>\r\n" + //   Nachname
         "\t\t</contributor>\r\n" + //
         "\t\t<b057></b057>\r\n" + //   e.g. 1
         "\t\t<b058></b058>\r\n" + //   e.g. 1. Aufl.
         "\t\t<language>\r\n" + //
         "\t\t\t<b253></b253>\r\n" + //
         "\t\t\t<b252></b252>\r\n" + //
         "\t\t</language>\r\n" + //
         "\t\t<b255></b255>\r\n" + //    ungefaehre Seitenzahl
         "\t\t<mainsubject>\r\n" + //
         "\t\t\t<b191>26</b191>\r\n" + //
         "\t\t\t<b068>2.0</b068>\r\n" + //
         "\t\t\t<b069></b069>\r\n" + //         e.g. 9770
         "\t\t\t<b070></b070>\r\n" + //     e.g. TB/Recht
         "\t\t</mainsubject>\r\n" + //
         "\t\t<subject>\r\n" + //
         "\t\t\t<b067>20</b067>\r\n" + //
         "\t\t\t<b070></b070>\r\n" + //
         "\t\t</subject>\r\n" + //
         "\t\t<b073></b073>\r\n" + //
         "\t\t<othertext>\r\n" + //
         "\t\t\t<d102></d102>\r\n" + //
         "\t\t\t<d103></d103>\r\n" + //
         "\t\t\t<d104></d104>\r\n" + //
         "\t\t\t<d105></d105>\r\n" + //
         "\t\t\t<d106></d106>\r\n" + //
         "\t\t</othertext>\r\n" + //
         "\t\t<relatedproduct>\r\n" + //
         "\t\t\t<h208>13</h208>\r\n" + //
         "\t\t\t<productidentifier>\r\n" + //
         "\t\t\t\t<b221>15</b221>\r\n" + //
         "\t\t\t\t<b244></b244>\r\n" + //
         "\t\t\t</productidentifier>\r\n" + //
         "\t\t</relatedproduct>\r\n" + //
         "\t\t<publisher>\r\n" + //
         "\t\t\t<b291>01</b291>\r\n" + //
         "\t\t\t<b241>05</b241>\r\n" + //
         "\t\t\t<b243>92083</b243>\r\n" + //
         "\t\t\t<b081>Verlag Dr. Kovac GmbH</b081>\r\n" + //
         "\t\t</publisher>\r\n" + //
         "\t\t<b209>Hamburg</b209>\r\n" + //
         "\t\t<b003></b003>\r\n" + //
         "\t\t<supplydetail>\r\n" + //
         "\t\t\t<supplieridentifier>\r\n" + //
         "\t\t\t\t<j345></j345>\r\n" + //
         "\t\t\t\t<b244></b244>\r\n" + //
         "\t\t\t</supplieridentifier>\r\n" + //
         "\t\t\t<j137>Verlag Dr. Kovac GmbH</j137>\r\n" + //
         "\t\t\t<j292></j292>\r\n" + //
         "\t\t\t<j396></j396>\r\n" + //
         "\t\t\t<j260></j260>\r\n" + //
         "\t\t\t<j142></j142>\r\n" + //
         "\t\t\t<price>\r\n" + //
         "\t\t\t\t<j148></j148>\r\n" + //
         "\t\t\t\t<j266></j266>\r\n" + //
         "\t\t\t\t<j151></j151>\r\n" + //
         "\t\t\t\t<j152></j152>\r\n" + //
         "\t\t\t\t<b251></b251>\r\n" + //
         "\t\t\t\t<j153></j153>\r\n" + //
         "\t\t\t</price>\r\n" + //
         "\t\t</supplydetail>\r\n";


    /**
     * Get the tag of the file line
     * @param line  The line of which the tag is being returned
     * @return      The tag of the line
     */
    public static String getTag(String line) {
        if(line != "") {
            return line.split("<")[1].split(">")[0];
        } else {
            return "";
        }
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
     * Gets content between tags
     * @param line  The line of which the content is being returned
     * @return      Content between the tags of the line
     */
    public static String getContent(String line) {
        if(line != "") {
            return line.split(">")[1].split("<")[0];
        } else {
            return "";
        }
    }

    /**
     * Gets the content of a given tag in a given product
     * @param tag       The tag of which the content is to be returned
     * @param xmlcon    The product as list of lines
     * @return          The content of the given tag
     */
    public static String getContentByTag(String tag, List<String> xmlcon) {
        for(String line : xmlcon) {
            if(getTag(line).equals(tag)) {
                return getContent(line);
            } 
        }
        return "";
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
     * Connects to a database and returns a Statement Object for executing SQL queries
     * @param url               The URL of the Database
     * @return                  A Statement for SQL Query execution
     * @throws SQLException     SQL Exception for when no Connection could be established
     */
    public static Statement connectToDatabase(String url) throws SQLException {
        try{
            Connection Connection = DriverManager.getConnection(url);
            Statement SQLQuery = Connection.createStatement();
            return SQLQuery;
        } catch(SQLException e) {
            throw new SQLException("Could not establish connection to database");
        }
    }

    /**
     * Makes a query to the database
     * @param query     The SQL query to be executed
     * @param stQuery   The statement object from the connection
     * @return          Result from DB
     */
    public static ArrayList<String> makeQuery(String query, Statement stQuery) throws SQLException{
        ArrayList<String> Result = new ArrayList<String>();
        try{
            ResultSet SQLResultSet = stQuery.executeQuery(query);
            while(SQLResultSet.next()) {
                Result.add(SQLResultSet.getString(1));
            }
        } catch(Exception e) {
            throw new SQLException("Could not establish connection to Database:\n" + e);
        }
        return Result;
    }

    /**
     * Get the ISBN for the given ISBN13
     * @param isbn13    ISBN13
     * @param query     Statement for Database Connection
     * @return          Matching ISBN for given ISBN13
     */
    public static String getIsbnFromIsbn13(String isbn13, Statement query) {
        String result = "";
        String isbn_str = "'" + isbn13 + "'";
        String queryString = "SELECT ISBN FROM ISBNzuEISBN WHERE ISBN13 = " + isbn_str;
        try {
            ArrayList<String> resultList = makeQuery(queryString, query);
            result = resultList.size() > 0 ? resultList.get(0) : "";               //works perfectly
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Make a list-representation of template string
     * @param template      Template as string
     * @return              Template as List
     */
    public static ArrayList<String> buildTempList(String template) {
        String[] result = template.split("\r\n");
        return new ArrayList<String>(Arrays.asList(result));
    }

    //TODO Build this function last
    /**
     * Validate tags in the product xml 
     * @param xmlstring     The product as one string
     * @param xmlcon        The product content as list
     * @return              The modified stringbuilder
     */
    public static StringBuilder validateProduct(StringBuilder xmlstring, List<String> xmlcon) {
        return xmlstring;
    }

    /**
     * Add the missing tags to the xmlstring
     * @param xmlstring     The string, where the missing tags content is supposed to be appended to
     * @param missingtags   The missing tags for the product
     * @param xmlcon        The xmlstring content as List
     * @return              The modified stringbuilder
     * @throws SQLException
     */
    public static StringBuilder addMissingTags(StringBuilder xmlstring, List<String> missingtags, List<String> xmlcon) throws SQLException {
        /** List of missing tags, where replaced tags can be deleted from */
        List<String> temp_missing = new ArrayList<String>();
        temp_missing.addAll(missingtags);
        /** The identifier of the given book */
        String identifier = getContentByTag("a001", xmlcon);
        /** Statement for SQL Query execution */
        Statement ConnectionStat = connectToDatabase(database_path);
        /** The ISBN of the given book */
        String isbn = getIsbnFromIsbn13(getContentByTag("b244", xmlcon), ConnectionStat);
        if(isbn != "") {
            String isbn_str = "'" + isbn + "'";
            String query_b244 = "SELECT SR FROM Produktion_orig WHERE ISBN = " + isbn_str;
            ArrayList<String> result = makeQuery(query_b244, ConnectionStat);
            String resultstr = result.size() > 0 ? result.get(0) : "";               //works perfectly
            String query_b244_2 = "SELECT"; //TODO query for other series info
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
 * TODO Baue XML basierend auf xml template string -> einfach Tags populaten
 */
