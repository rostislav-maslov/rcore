package com.ub.core.base.views.pageHeader;

/**
 * Created by fyzu on 19.10.16.
 */
public enum AddLinkButtonType {
    DEFAULT("default"),
    PRIMARY("primary"),
    SUCCESS("success"),
    INFO("info"),
    WARNING("warning"),
    DANGER("danger"),
    LINK("link");

    AddLinkButtonType(String title) {
        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
