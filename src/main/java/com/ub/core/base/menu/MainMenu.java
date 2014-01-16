package com.ub.core.base.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class MainMenu {

    protected String menuId = getClass().getCanonicalName();
    protected List<SubMenu> subMenus = new ArrayList<SubMenu>();
    protected String name;

    public List<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getMenuId(){
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
