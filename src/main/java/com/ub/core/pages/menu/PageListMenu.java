package com.ub.core.pages.menu;

import com.ub.core.pages.routes.PagesAdminRoutes;

public class PageListMenu extends PageMenu {
    public PageListMenu(){
        this.name = "Все";
        this.url = PagesAdminRoutes.ALL;
        this.parent = new PageMenu();
    }
}
