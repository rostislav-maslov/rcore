package com.ub.core.base.menu;

import com.ub.core.menu.models.fields.MenuIcons;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreMenu {

    protected String id = getClass().getCanonicalName();
    protected CoreMenu parent;
    protected String name = "";
    protected String url = "";
    protected String icon = MenuIcons.ENTYPO_GAUGE;
    protected List<CoreMenu> child = new ArrayList<CoreMenu>();
    protected Integer position = 100;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoreMenu getParent() {
        return parent;
    }

    public void setParent(CoreMenu parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CoreMenu> getChild() {
        return child;
    }

    public void setChild(List<CoreMenu> child) {
        this.child = child;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
