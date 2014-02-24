package com.ub.core.base.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class ExcludeCoreMenu {

    protected String id = getClass().getCanonicalName();
    protected List<CoreMenu> menu = new ArrayList<CoreMenu>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CoreMenu> getMenu() {
        return menu;
    }

    public void setMenu(List<CoreMenu> menu) {
        this.menu = menu;
    }
}
