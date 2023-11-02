package com.archiving.models.tags;

/**
 * Series tag
 * 
 * @author Maxwell Leu
 */
public class Series {
    /** Reihentitel (Alternative Eingabe über <b202> und <b203>) */
    public String b018;
    /** Bandnummer innerhalb der Reihe (optional)  */
    public String b019;
    /**  Erscheinungsjahr (optional)  */
    public String b020;
    public SeriesIdentifier identifier = new SeriesIdentifier();
}
