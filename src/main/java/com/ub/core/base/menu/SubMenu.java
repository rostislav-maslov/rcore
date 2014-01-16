package com.ub.core.base.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class SubMenu {
    protected String menuId = getClass().getCanonicalName();
    protected List<SubMenu> subMenus = new ArrayList<SubMenu>();
    protected String name;
    protected String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
