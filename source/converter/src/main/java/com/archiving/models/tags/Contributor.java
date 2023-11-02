package com.archiving.models.tags;

/**
 * Contributor tag
 * 
 * @author Maxwell Leu
 */
public class Contributor {
    /** Reihenfolge */
    public String b034;
    /** Rolle des/der Beteiligten */
    public String b035;     
    /** Name als "Vorname Nachname" (optional) */
    public String b036;     
    /** Name invertiert "Nachname Vorname" */
    public String b037;    
    /** Unternehmen/Institution (alt. zu b037/b036) */
    public String b047;
    /** Hinweise zur Biographie des/der Beteiligten */
    public String b044;   
}
