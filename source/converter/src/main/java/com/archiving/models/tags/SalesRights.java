package com.archiving.models.tags;

/**
 * Salesrights tag
 * 
 * @author Maxwell Leu
 */
public class SalesRights{
    /** Rechteart gemäß ONIX Codelist 46 */
    public String b089;     
    /** Leerzeichen-separierte Liste der relevanten Ländercodes gemäß ONIX Codelist 91 (alternativ zu b388) */
    public String b090;     
    /** Leerzeichen-separierte Liste der relevanten Territorien gemäß ONIX Codelist 49 (alternativ zu b090) */
    public String b388;     
}
