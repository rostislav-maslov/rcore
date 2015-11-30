package com.ub.core.base.views.pageHeader;

import com.ub.core.base.routes.BaseRoutes;
import com.ub.core.base.views.breadcrumbs.Breadcrumbs;
import com.ub.core.base.views.breadcrumbs.BreadcrumbsLink;


public class PageHeader {
    public static PageHeader defaultPageHeader() {
        return defaultPageHeader("Главная", "Добавить");
    }

    public static PageHeader defaultPageHeader(String mainPage, String addTitle) {
        PageHeader pageHeader = new PageHeader();
        Breadcrumbs breadcrumbs1 = new Breadcrumbs();
        breadcrumbs1.getLinks().add(new BreadcrumbsLink(BaseRoutes.ADMIN, mainPage));
        pageHeader.setBreadcrumbs(breadcrumbs1);
        pageHeader.setTitleAdd(addTitle);
        return pageHeader;
    }

    private Breadcrumbs breadcrumbs = new Breadcrumbs();
    private String titleAdd;
    private String linkAdd;

    public Breadcrumbs getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(Breadcrumbs breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public String getTitleAdd() {
        return titleAdd;
    }

    public void setTitleAdd(String titleAdd) {
        this.titleAdd = titleAdd;
    }

    public String getLinkAdd() {
        return linkAdd;
    }

    public void setLinkAdd(String linkAdd) {
        this.linkAdd = linkAdd;
    }
}
