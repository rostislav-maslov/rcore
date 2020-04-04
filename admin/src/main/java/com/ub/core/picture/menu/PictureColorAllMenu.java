package com.ub.core.picture.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.picture.routes.PictureColorAdminRoutes;

public class PictureColorAllMenu extends CoreMenu {
    public PictureColorAllMenu() {
        this.name ="Все цвета";
        this.parent = new PictureMenu();
        this.url = PictureColorAdminRoutes.ALL;
    }
}
