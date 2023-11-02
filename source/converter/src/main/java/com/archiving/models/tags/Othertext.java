package com.archiving.models.tags;

/**
 * Othertext tag
 * 
 * @author Maxwell Leu
 */
public class Othertext {
    /** 01|03 (Hauptbeschreibung), 02 (Kurzbeschreibung), 04 (Inhaltsverzeichnis), 07|08 (Rezension), 23 (Textauszug) */
    public String d102;      
    /** unterstützte Werte: 00, 06, 07 (alle drei: ASCII) bzw. 02 (HTML) */
    public String d103;    
    /** Textbeschreibung (vorzugsweise mit HTML-Formatierungen) */
    public String d104;     
}
