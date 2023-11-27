package com.archiving.utils;

public class Template {
    private static String xml = "\t\t<a001></a001>\r\n" + //
         "\t\t<a002>02</a002>\r\n" + //
         "\t\t<productidentifier>\r\n" + //
         "\t\t\t<b221>15</b221>\r\n" + //
         "\t\t\t<b244></b244>\r\n" + //
         "\t\t</productidentifier>\r\n" + //
         "\t\t<productidentifier>\r\n" + //
         "\t\t\t<b221></b221>\r\n" + //
         "\t\t\t<b244></b244>\r\n" + //
         "\t\t</productidentifier>\r\n" + //
         "\t\t<b012>DG</b012>\r\n" + //
         "\t\t<b211>02</b211>\r\n" + //
         "\t\t<series>\r\n" + //
         "\t\t\t<seriesidentifier>\r\n" + //
         "\t\t\t\t<b273></b273>\r\n" + //
         "\t\t\t\t<b244></b244>\r\n" + //
         "\t\t\t</seriesidentifier>\r\n" + //
         "\t\t\t<title>\r\n" + //
         "\t\t\t\t<b202></b202>\r\n" + //
         "\t\t\t\t<b203></b203>\r\n" + //
         "\t\t\t</title>\r\n" + //
         "\t\t\t<b018></b018>\r\n" + //
         "\t\t\t<b019></b019>\r\n" + //
         "\t\t</series>\r\n" + //
         "\t\t<title>\r\n" + //
         "\t\t\t<b202></b202>\r\n" + //
         "\t\t\t<b203></b203>\r\n" + //
         "\t\t\t<b029></b029>\r\n" + //
         "\t\t</title>\r\n" + //
         "\t\t<b368></b368>\r\n" + //
         "\t\t<b369></b369>\r\n" + //
         "\t\t<b370></b370>\r\n" + //
         "\t\t<contributor>\r\n" + //
         "\t\t\t<b035></b035>\r\n" + //
         "\t\t\t<b340></b340>\r\n" + //
         "\t\t\t<b036></b036>\r\n" + //  Vorname Nachname
         "\t\t\t<b037></b037>\r\n" + // Nachname, Vorname
         "\t\t\t<b039></b039>\r\n" + // Vorname
         "\t\t\t<b040></b040>\r\n" + //   Nachname
         "\t\t</contributor>\r\n" + //
         "\t\t<b057></b057>\r\n" + //   e.g. 1
         "\t\t<b058></b058>\r\n" + //   e.g. 1. Aufl.
         "\t\t<language>\r\n" + //
         "\t\t\t<b253></b253>\r\n" + //
         "\t\t\t<b252></b252>\r\n" + //
         "\t\t</language>\r\n" + //
         "\t\t<b255></b255>\r\n" + //    ungefaehre Seitenzahl
         "\t\t<mainsubject>\r\n" + //
         "\t\t\t<b191>26</b191>\r\n" + //
         "\t\t\t<b068>2.0</b068>\r\n" + //
         "\t\t\t<b069></b069>\r\n" + //         e.g. 9770
         "\t\t\t<b070></b070>\r\n" + //     e.g. TB/Recht
         "\t\t</mainsubject>\r\n" + //
         "\t\t<subject>\r\n" + //
         "\t\t\t<b067>20</b067>\r\n" + //
         "\t\t\t<b070></b070>\r\n" + //
         "\t\t</subject>\r\n" + //
         "\t\t<b073></b073>\r\n" + //
         "\t\t<othertext>\r\n" + //
         "\t\t\t<d102></d102>\r\n" + //
         "\t\t\t<d103></d103>\r\n" + //
         "\t\t\t<d104></d104>\r\n" + //
         "\t\t\t<d105></d105>\r\n" + //
         "\t\t\t<d106></d106>\r\n" + //
         "\t\t</othertext>\r\n" + //
         "\t\t<relatedproduct>\r\n" + //
         "\t\t\t<h208>13</h208>\r\n" + //
         "\t\t\t<productidentifier>\r\n" + //
         "\t\t\t\t<b221>15</b221>\r\n" + //
         "\t\t\t\t<b244></b244>\r\n" + //
         "\t\t\t</productidentifier>\r\n" + //
         "\t\t</relatedproduct>\r\n" + //
         "\t\t<publisher>\r\n" + //
         "\t\t\t<b291>01</b291>\r\n" + //
         "\t\t\t<b241>05</b241>\r\n" + //
         "\t\t\t<b243>92083</b243>\r\n" + //
         "\t\t\t<b081>Verlag Dr. Kovac GmbH</b081>\r\n" + //
         "\t\t</publisher>\r\n" + //
         "\t\t<b209>Hamburg</b209>\r\n" + //
         "\t\t<b003></b003>\r\n" + //
         "\t\t<supplydetail>\r\n" + //
         "\t\t\t<supplieridentifier>\r\n" + //
         "\t\t\t\t<j345></j345>\r\n" + //
         "\t\t\t\t<b244></b244>\r\n" + //
         "\t\t\t</supplieridentifier>\r\n" + //
         "\t\t\t<j137>Verlag Dr. Kovac GmbH</j137>\r\n" + //
         "\t\t\t<j292></j292>\r\n" + //
         "\t\t\t<j396></j396>\r\n" + //
         "\t\t\t<j260></j260>\r\n" + //
         "\t\t\t<j142></j142>\r\n" + //
         "\t\t\t<price>\r\n" + //
         "\t\t\t\t<j148></j148>\r\n" + //
         "\t\t\t\t<j266></j266>\r\n" + //
         "\t\t\t\t<j151></j151>\r\n" + //
         "\t\t\t\t<j152></j152>\r\n" + //
         "\t\t\t\t<b251></b251>\r\n" + //
         "\t\t\t\t<j153></j153>\r\n" + //
         "\t\t\t</price>\r\n" + //
         "\t\t</supplydetail>\r\n";
}
