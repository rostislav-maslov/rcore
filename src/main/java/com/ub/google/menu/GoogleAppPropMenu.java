package com.ub.google.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.google.routes.GooglePlusRoutes;

/**
 * Created by Eduard on 02.10.2015.
 */
public class GoogleAppPropMenu extends CoreMenu {
    public GoogleAppPropMenu() {
        this.name = "Настройки приложения";
        this.url = GooglePlusRoutes.EDIT;
        this.parent = new GoogleMenu();
    }
}
