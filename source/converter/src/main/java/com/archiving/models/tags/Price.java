package com.archiving.models.tags;

/**
 * Price tag
 * 
 * @author Maxwell Leu
 */
public class Price {
    /** Preisart gemäß ONIX Codelist 58 (unterstützt werden Bruttopreistypen 02, 04, 12, 14) */
    public String j148;     
    /** Preisangabe als Fließkommazahl („.“ als Dezimaltrenner!)  */
    public String j151;     
    /** EUR (Währungscode für Euro)  */
    public String j152;    
    /** DE (Ländercode passend zur Angabe des MwSt.-Satzes)  */
    public String b251;    
    /** R (ermäßigter Satz gilt für alle eBooks)  */
    public String j153;     
    /** Startdatum für Gültigkeitszeitraum im Format yyyyMMdd (optional)  */
    public String j161;    
    /** Enddatum für Gültigkeitszeitraum im Format yyyyMMdd (optional)  */
    public String j162;    
}
