package com.ub.facebook.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.facebook.routes.AppPropertiesRoutes;

public class FbAppPropMenu extends CoreMenu {
    public FbAppPropMenu(){
        this.name = "Настройки приложения";
        this.url = AppPropertiesRoutes.EDIT;
        this.parent = new FbMenu();
    }
}
