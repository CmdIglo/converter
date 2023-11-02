package com.archiving.models.tags;

/**
 * Supplydetail tag
 * 
 * @author Maxwell Leu
 */
public class SupplyDetail {
    /** Verfügbarkeit gemäß ONIX Codelist 54 (optional bzw. alternativ/ergänzend zu j396) */
    public String j141;       
    /** Verfügbarkeit gemäß ONIX Codelist 65 (bevorzugte Angabe bzw. alternativ/ergänzend zu j141) */
    public String j396;      
    /** Auslieferungsdatum im Format yyyyMMdd bzw. yyyyMM (optional) */
    public String j142;     
    /** Datum des Verkaufsstarts im Format yyyyMMdd (optional) */
    public String j143;      
    /** Preisart gemäß ONIX Codelist 58 (für USD 01) */
    public String j148;      
    /** Preisangabe als Fließkommazahl („.“ als Dezimaltrenner!)  */
    public String j151;     
    /** USD (Währungscode für US Dollar) */
    public String j152;     
    /** US (Ländercode passend zur Angabe des MwSt.-Satzes) */
    public String b251;     
    /** Z (Nettopreis)  */
    public String j153;     
    /** Startdatum für Gültigkeitszeitraum im Format yyyyMMdd (optional) */
    public String j161;      
    /** Enddatum für Gültigkeitszeitraum im Format yyyyMMdd (optional) */
    public String j162;     
    public Price price = new Price();
}
