package com.ub.core.seo.sitemap.view;


import java.util.ArrayList;
import java.util.List;

public class SiteMapView {
    private String url = "";
    private String title = "";
    private Boolean needLink = true;
    private List<SiteMapView> child = new ArrayList<SiteMapView>();


    public SiteMapView() {
    }

    public SiteMapView(String url, String title, Boolean needLink) {
        this.url = url;
        this.title = title;
        this.needLink = needLink;
    }

    public SiteMapView(String url, String title, Boolean needLink, List<SiteMapView> child) {
        this.url = url;
        this.title = title;
        this.needLink = needLink;
        this.child = child;
    }

    public SiteMapView(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getNeedLink() {
        return needLink;
    }

    public void setNeedLink(Boolean needLink) {
        this.needLink = needLink;
    }

    public List<SiteMapView> getChild() {
        return child;
    }

    public void setChild(List<SiteMapView> child) {
        this.child = child;
    }
}
