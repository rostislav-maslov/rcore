package com.ub.odnoklassniki.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.odnoklassniki.routes.AppPropertiesOkRoutes;

public class OkAppPropMenu extends CoreMenu {
    public OkAppPropMenu(){
        this.name = "Настройки приложения";
        this.url = AppPropertiesOkRoutes.EDIT;
        this.parent = new OkMenu();
    }
}
