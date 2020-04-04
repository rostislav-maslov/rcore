package com.ub.linkedin.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.linkedin.routes.LinkedinRoutes;

/**
 * Created by Eduard on 02.10.2015.
 */
public class LinkedinAppPropMenu extends CoreMenu {
    public LinkedinAppPropMenu() {
        this.name = "Настройки приложения";
        this.url = LinkedinRoutes.EDIT;
        this.parent = new LinkedinMenu();
    }
}
