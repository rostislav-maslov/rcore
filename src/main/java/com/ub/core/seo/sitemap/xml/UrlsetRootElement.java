package com.ub.core.seo.sitemap.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "urlset")
@XmlAccessorType(XmlAccessType.FIELD)
public class UrlsetRootElement {
    @javax.xml.bind.annotation.XmlElement(name = "url")
    private List<UrlElement> urlElements = new ArrayList<UrlElement>();

    @XmlAttribute
    private String xmlns = "http://www.sitemaps.org/schemas/sitemap/0.9";

    public List<UrlElement> getUrlElements() {
        return urlElements;
    }

    public void setUrlElements(List<UrlElement> urlElements) {
        this.urlElements = urlElements;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
}
