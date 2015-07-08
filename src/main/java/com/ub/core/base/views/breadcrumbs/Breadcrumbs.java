package com.ub.core.base.views.breadcrumbs;

import java.util.ArrayList;
import java.util.List;

public class Breadcrumbs {
    private List<BreadcrumbsLink> links = new ArrayList<BreadcrumbsLink>();
    private String currentPageTitle = "";

    public String cutBreadcrumbsCurrentTitle(Integer size){
        if( currentPageTitle.length() <= size )return currentPageTitle;
        return currentPageTitle.substring(0, size-1) + "...";
    }

    public void addLink(String link, String title){
        links.add(new BreadcrumbsLink(link,title));
    }

    public List<BreadcrumbsLink> getLinks() {
        return links;
    }

    public void setLinks(List<BreadcrumbsLink> links) {
        this.links = links;
    }

    public String getCurrentPageTitle() {
        return currentPageTitle;
    }

    public void setCurrentPageTitle(String currentPageTitle) {
        this.currentPageTitle = currentPageTitle;
    }
}
