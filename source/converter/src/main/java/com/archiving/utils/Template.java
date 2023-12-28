package com.archiving.utils;

public class Template {
    public static String xml = "\t\t<a001></a001>\r\n" + //
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

    private String identifier = "";
    private String prod_b221;
    private String prod_b244;
    private String series_id_b273;
    private String series_id_b244;
    private String series_t_b202;
    private String series_t_b203;
    private String series_b018;
    private String series_b019;
    private String title_b202;
    private String title_b203;
    private String title_b029;
    private String b368;
    private String b369;
    private String b370;
    private String contr_b035;
    private String contr_b340;
    private String contr_b036;
    private String contr_b037;
    private String contr_b039;
    private String contr_b040;
    private String b057;
    private String b058;
    private String lang_b253;
    private String lang_b252;
    private String b255;
    private String mainsub_b069;
    private String mainsub_b070;
    private String sub_b070;
    private String b073;
    private String other_d102;
    private String other_d103;
    private String other_d104;
    private String other_d105;
    private String other_d106;
    private String rel_prod_id_b244;
    private String b003;
    private String supp_supp_id_j345;
    private String supp_supp_id_b244;
    private String supp_j292;
    private String supp_j396;
    private String supp_j260;
    private String supp_j142;
    private String supp_price_j148;
    private String supp_price_j266;
    private String supp_price_j151;
    private String supp_price_j152;
    private String supp_price_b251;
    private String supp_price_j153;

    public Template(String identifier) {
        this.identifier = identifier;
    }

    // Getters and setters for class variables

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getProd_b221() {
        return prod_b221;
    }

    public void setProd_b221(String prod_b221) {
        this.prod_b221 = prod_b221;
    }

    public String getProd_b244() {
        return prod_b244;
    }

    public void setProd_b244(String prod_b244) {
        this.prod_b244 = prod_b244;
    }

    public String getSeries_id_b273() {
        return series_id_b273;
    }

    public void setSeries_id_b273(String series_id_b273) {
        this.series_id_b273 = series_id_b273;
    }

    public String getSeries_id_b244() {
        return series_id_b244;
    }

    public void setSeries_id_b244(String series_id_b244) {
        this.series_id_b244 = series_id_b244;
    }

    public String getSeries_t_b202() {
        return series_t_b202;
    }

    public void setSeries_t_b202(String series_t_b202) {
        this.series_t_b202 = series_t_b202;
    }

    public String getSeries_t_b203() {
        return series_t_b203;
    }

    public void setSeries_t_b203(String series_t_b203) {
        this.series_t_b203 = series_t_b203;
    }

    public String getSeries_b018() {
        return series_b018;
    }

    public void setSeries_b018(String series_b018) {
        this.series_b018 = series_b018;
    }

    public String getSeries_b019() {
        return series_b019;
    }

    public void setSeries_b019(String series_b019) {
        this.series_b019 = series_b019;
    }

    public String getTitle_b202() {
        return title_b202;
    }

    public void setTitle_b202(String title_b202) {
        this.title_b202 = title_b202;
    }

    public String getTitle_b203() {
        return title_b203;
    }

    public void setTitle_b203(String title_b203) {
        this.title_b203 = title_b203;
    }

    public String getTitle_b029() {
        return title_b029;
    }

    public void setTitle_b029(String title_b029) {
        this.title_b029 = title_b029;
    }

    public String getB368() {
        return b368;
    }

    public void setB368(String b368) {
        this.b368 = b368;
    }

    public String getB369() {
        return b369;
    }

    public void setB369(String b369) {
        this.b369 = b369;
    }

    public String getB370() {
        return b370;
    }

    public void setB370(String b370) {
        this.b370 = b370;
    }

    public String getContr_b035() {
        return contr_b035;
    }

    public void setContr_b035(String contr_b035) {
        this.contr_b035 = contr_b035;
    }

    public String getContr_b340() {
        return contr_b340;
    }

    public void setContr_b340(String contr_b340) {
        this.contr_b340 = contr_b340;
    }

    public String getContr_b036() {
        return contr_b036;
    }

    public void setContr_b036(String contr_b036) {
        this.contr_b036 = contr_b036;
    }

    public String getContr_b037() {
        return contr_b037;
    }

    public void setContr_b037(String contr_b037) {
        this.contr_b037 = contr_b037;
    }

    public String getContr_b039() {
        return contr_b039;
    }

    public void setContr_b039(String contr_b039) {
        this.contr_b039 = contr_b039;
    }

    public String getContr_b040() {
        return contr_b040;
    }

    public void setContr_b040(String contr_b040) {
        this.contr_b040 = contr_b040;
    }

    public String getB057() {
        return b057;
    }

    public void setB057(String b057) {
        this.b057 = b057;
    }

    public String getB058() {
        return b058;
    }

    public void setB058(String b058) {
        this.b058 = b058;
    }

    public String getLang_b253() {
        return lang_b253;
    }

    public void setLang_b253(String lang_b253) {
        this.lang_b253 = lang_b253;
    }

    public String getLang_b252() {
        return lang_b252;
    }

    public void setLang_b252(String lang_b252) {
        this.lang_b252 = lang_b252;
    }

    public String getB255() {
        return b255;
    }

    public void setB255(String b255) {
        this.b255 = b255;
    }

    public String getMainsub_b069() {
        return mainsub_b069;
    }

    public void setMainsub_b069(String mainsub_b069) {
        this.mainsub_b069 = mainsub_b069;
    }

    public String getMainsub_b070() {
        return mainsub_b070;
    }

    public void setMainsub_b070(String mainsub_b070) {
        this.mainsub_b070 = mainsub_b070;
    }

    public String getSub_b070() {
        return sub_b070;
    }

    public void setSub_b070(String sub_b070) {
        this.sub_b070 = sub_b070;
    }

    public String getB073() {
        return b073;
    }

    public void setB073(String b073) {
        this.b073 = b073;
    }

    public String getOther_d102() {
        return other_d102;
    }

    public void setOther_d102(String other_d102) {
        this.other_d102 = other_d102;
    }

    public String getOther_d103() {
        return other_d103;
    }

    public void setOther_d103(String other_d103) {
        this.other_d103 = other_d103;
    }

    public String getOther_d104() {
        return other_d104;
    }

    public void setOther_d104(String other_d104) {
        this.other_d104 = other_d104;
    }

    public String getOther_d105() {
        return other_d105;
    }

    public void setOther_d105(String other_d105) {
        this.other_d105 = other_d105;
    }

    public String getOther_d106() {
        return other_d106;
    }

    public void setOther_d106(String other_d106) {
        this.other_d106 = other_d106;
    }

    public String getRel_prod_id_b244() {
        return rel_prod_id_b244;
    }

    public void setRel_prod_id_b244(String rel_prod_id_b244) {
        this.rel_prod_id_b244 = rel_prod_id_b244;
    }

    public String getB003() {
        return b003;
    }

    public void setB003(String b003) {
        this.b003 = b003;
    }

    public String getSupp_supp_id_j345() {
        return supp_supp_id_j345;
    }

    public void setSupp_supp_id_j345(String supp_supp_id_j345) {
        this.supp_supp_id_j345 = supp_supp_id_j345;
    }

    public String getSupp_supp_id_b244() {
        return supp_supp_id_b244;
    }

    public void setSupp_supp_id_b244(String supp_supp_id_b244) {
        this.supp_supp_id_b244 = supp_supp_id_b244;
    }

    public String getSupp_j292() {
        return supp_j292;
    }

    public void setSupp_j292(String supp_j292) {
        this.supp_j292 = supp_j292;
    }

    public String getSupp_j396() {
        return supp_j396;
    }

    public void setSupp_j396(String supp_j396) {
        this.supp_j396 = supp_j396;
    }

    public String getSupp_j260() {
        return supp_j260;
    }

    public void setSupp_j260(String supp_j260) {
        this.supp_j260 = supp_j260;
    }

    public String getSupp_j142() {
        return supp_j142;
    }

    public void setSupp_j142(String supp_j142) {
        this.supp_j142 = supp_j142;
    }

    public String getSupp_price_j148() {
        return supp_price_j148;
    }

    public void setSupp_price_j148(String supp_price_j148) {
        this.supp_price_j148 = supp_price_j148;
    }

    public String getSupp_price_j266() {
        return supp_price_j266;
    }

    public void setSupp_price_j266(String supp_price_j266) {
        this.supp_price_j266 = supp_price_j266;
    }

    public String getSupp_price_j151() {
        return supp_price_j151;
    }

    public void setSupp_price_j151(String supp_price_j151) {
        this.supp_price_j151 = supp_price_j151;
    }

    public String getSupp_price_j152() {
        return supp_price_j152;
    }

    public void setSupp_price_j152(String supp_price_j152) {
        this.supp_price_j152 = supp_price_j152;
    }

    public String getSupp_price_b251() {
        return supp_price_b251;
    }

    public void setSupp_price_b251(String supp_price_b251) {
        this.supp_price_b251 = supp_price_b251;
    }

    public String getSupp_price_j153() {
        return supp_price_j153;
    }

    public void setSupp_price_j153(String supp_price_j153) {
        this.supp_price_j153 = supp_price_j153;
    }

    public StringBuilder makeXml() {
        StringBuilder res = new StringBuilder();
        return res;
    }
}
