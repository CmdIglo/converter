package com.archiving.utils;

/**
 * Tag class for OOP repr. of a tag in the xml file
 * 
 * @author Maxwell Leu
 */
public class Tag {

    String tagname;
    String tagvalue;

    /**
     * Constructor
     * @param tagname   Name of tag
     */
    public Tag(String tagname) {
        this.tagname = tagname;
    }

    /**
     * Sets the value of the tag
     * @param value     Value of tag
     */
    public void setValue(String value) {
        this.tagvalue = value;
    }

    /**
     * Gets the name of the tag
     * @return      Name of tag
     */
    public String getName() {
        return this.tagname;
    }

    /**
     * Gets the value of the tag
     * @return  	Value of tag
     */
    public String getValue() {
        return this.tagvalue;
    }
    
}
