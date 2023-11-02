package com.archiving.models;

import com.archiving.models.tags.*;

/**
 * Product model for XML file writing
 * 
 * @author Maxwell Leu
 */
public class Product {
    /** Eindeutiges Kennzeichen für den Titeldatensatz */
    public String a001;     
    /** Kennzeichen für durchzuführende Aktion (01|02 = NEW, 03 = UPDATE, 05 = DELETE) */
    public String a002;      
    /** DG (feste Belegung, da ausschließlich eBooks übergeben werden) */
    public String b012;     
    /** E-Book-Format: Unterstützt werden die Werte „02“ (PDF) und „29“ (EPUB) */
    public String b211;      
    /** explizite Angabe "ohne Reihenzuordnung" (optional bzw. alternativ zu series) */
    public String n338;      
    /** Auflagennummer (optional, keine Angabe wird als „1. Auflage“ interpretiert) */
    public String b057;     
    /** explizite Angabe "ohne Auflageninformation" (optional bzw. alternativ zu b057) */
    public String n386;     
    /** ungefähre Seitenzahl (optional)  */
    public String b061;     
    /** kann alternativ zu <b061> verwendet werden */
    public String b255;     
    /** Code für primäre BISAC Kategorie (optional) */
    public String b064;      
    /** Code für primäre BIC Kategorie (optional) */
    public String b065;     
    /** Ort der Veröffentlichung (optional) */
    public String b209;      
    /** Land der Veröffentlichung gemäß ONIX Codelist 91 (optional) */
    public String b083;      
    /** Veröffentlichungsdatum (im Format yyyyMMdd bzw. yyyyMM bzw. yyyy) */
    public String b003;      
    public ProductIdentifier productidentifier = new ProductIdentifier();
    public Series series = new Series();
    public Title title = new Title();
    public Contributor contributor = new Contributor();
    public Language language = new Language();
    public MainSubject mainsubject = new MainSubject();
    public Subject subject = new Subject();
    public Othertext othertext = new Othertext();
    public Imprint imprint = new Imprint();
    public Publisher publisher = new Publisher();
    public SalesRights salesrights = new SalesRights();
    public RelatedProduct relatedproduct = new RelatedProduct();
    public SupplyDetail supplydetail = new SupplyDetail();
}
