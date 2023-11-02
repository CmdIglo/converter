package com.archiving.models.tags;

/**
 * Relatedproduct tag
 * 
 * @author Maxwell Leu
 */
public class RelatedProduct {
    /** 13|06 (wird der Wert 06 verwendet, muss weiter unten durch <b012> ergänzt werden */
    public String h208;      
    /** ergänzend zu <h208> (erlaubte Werte=B* Siehe Onix-Codelist 7) */
    public String b012;     
    public ProductIdentifier identifier = new ProductIdentifier();   
}
