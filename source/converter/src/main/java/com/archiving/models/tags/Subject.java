package com.archiving.models.tags;

/**
 * Subject tag
 * 
 * @author Maxwell Leu
 */
public class Subject {
    /** ID für Klassifizierungssystem (gemäß ONIX Codelist 27); unterstützt werden 10 (für BISAC) bzw. 12 (für BIC), 01 (Dewey), 03 (LCCN), 04 (LCSH), 23 (Kennzeichnung von Lehrbüchern + <b171>ProgramLine</b171>+<b070>Studium</b070>)) */
    public String b067;     
    /** Code für zusätzliche BISAC bzw. BIC Kategorie bzw. Klassifizierungen nach DEWEY, LCCN, LCSH */
    public String b069;     
}
