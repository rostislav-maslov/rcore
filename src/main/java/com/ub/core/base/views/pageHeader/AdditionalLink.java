package com.ub.core.base.views.pageHeader;

import static com.ub.core.base.views.pageHeader.AddLinkButtonType.DEFAULT;

public class AdditionalLink {

    private String link;
    private String title;
    private AddLinkButtonType type = DEFAULT;

    public AdditionalLink(String link, String title) {
        this.link = link;
        this.title = title;
    }

    public AdditionalLink(String link, String title, AddLinkButtonType type) {
        this.link = link;
        this.title = title;
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AddLinkButtonType getType() {
        return type;
    }

    public void setType(AddLinkButtonType type) {
        this.type = type;
    }
}
