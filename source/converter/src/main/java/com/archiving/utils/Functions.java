package com.archiving.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSplitPaneUI;

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

    /**
     * Get the tag of the file line
     * @param line  The line of which the tag is being returned
     * @return      The tag of the line
     */
    public static String getTag(String line) {
       if(line != "") {
            String[] temp = line.split("<")[1].split(">");
            if(temp.length > 0) {
                return temp[0];
            } else {
                return "";
            }
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
        char[] lineArr = new char[line.length()];
        for(int i = 0; i < line.toCharArray().length; i++) {
            lineArr[i] = line.toCharArray()[i];
        }
        if((line != "") && (hasValue(lineArr, '/')) && (countOcc(lineArr, '<') == 2)) {              //check if line has a closing tag
            String[] temp = line.split(">")[1].split("<");
            if(temp.length > 0) {
                return temp[0];
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * Checks if a given char array has the given value
     * @param string    Char Array
     * @param chr       Character 
     * @return          True or False, if value in string
     */
    public static boolean hasValue(char[] string, char chr) {
        for(char elem : string) {
            if(elem == chr) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts occurences of given character in given string
     * @param string    String
     * @param chr       Character to be counted
     * @return          Number of occurences
     */
    public static int countOcc(char[] string, char chr) {
        int occ = 0;
        for(char c : string) {
            if(c == chr) {
                occ++;
            }
        }
        return occ;
    }

    /**
     * Gets the format of the line (necessary to determine on which level of the dom tree the tag is)
     * @param line      Line of which the level is being determined
     * @return          Format of the line (Format in front of the opening tag)
     */
    public static String getFormat(String line) {
        return line.split("<")[0];    
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
     * Sets the content for a tag in the line
     * @param line          Line for which the content is being set
     * @param content       Content for the line
     * @return              The line with the content between the tags
     */
    public static String setContentBtwTags(String line, String content) {
        String line_beg = line.split("</")[0];
        String line_end = line.split(">")[1];
        String newLine = line_beg + content + line_end;
        return newLine;
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
        /** The identifier of the given book */
        String identifier = getContentByTag("a001", xmlcon);
        String temp_xml = Template.xml;
        ArrayList<String> temp_list = buildTempList(temp_xml);
        /** Statement for SQL Query execution */
        //Statement ConnectionStat = connectToDatabase(database_path);
        /** The ISBN of the given book */
        //String isbn = getIsbnFromIsbn13(getContentByTag("b244", xmlcon), ConnectionStat);
        //if(isbn != "") {
        //    String isbn_str = "'" + isbn + "'";
        //    String query_b244 = "SELECT SR FROM Produktion_orig WHERE ISBN = " + isbn_str;
        //    ArrayList<String> result = makeQuery(query_b244, ConnectionStat);
        //    String resultstr = result.size() > 0 ? result.get(0) : "";               //works perfectly
        //}
        for(int i = 0; i < temp_list.size(); i++) {
            String tempLine = temp_list.get(i);
            if(countOcc(tempLine.toCharArray(), '<') == 2) {
                for(int j = 0; j < xmlcon.size(); j++) {
                    String xmlLine = xmlcon.get(j);
                    if((countOcc(xmlLine.toCharArray(), '<') == 2) && (getTag(tempLine).equals(getTag(xmlLine))) && (getFormat(tempLine).equals(getFormat(xmlLine)))) {
                        temp_list.set(i, setContentBtwTags(tempLine, getContent(xmlLine)));
                    } 
                }
            } 
        }
        StringBuilder Product = new StringBuilder();
        for(String line : temp_list) {
            if(getContent(line) != "") {
                Product.append(line);
                Product.append("\n");
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
 * TODO Baue XML basierend auf xml template string -> einfach Tags populaten
 */
