package com.ub.core.pictureTask.menu;

import com.ub.core.pictureTask.routes.PictureTaskAdminRoutes;
import com.ub.core.base.menu.CoreMenu;

public class PictureTaskAllMenu extends CoreMenu {
    public PictureTaskAllMenu() {
        this.name = "Все";
        this.parent = new PictureTaskMenu();
        this.url = PictureTaskAdminRoutes.ALL;
    }
}
