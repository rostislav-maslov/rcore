package com.ub.core.seo.sitemap.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class UrlElement {
    @XmlElement(name = "loc")
    private String loc;
    @XmlElement(name = "priority")
    private String priority = "0.5";

    public UrlElement(String loc){
        this.loc = loc;
    }

    public UrlElement(String loc, String priority){
        this.loc = loc;
        this.priority = priority;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
