package com.ub.core.pages.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.pages.routes.PagesAdminRoutes;

public class PageAddMenu extends CoreMenu {
    public PageAddMenu(){
        this.name = "Добавить";
        this.url = PagesAdminRoutes.ADD;
        this.parent = new PageMenu();
    }
}
