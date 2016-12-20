package com.ub.core.pictureTask.menu;

import com.ub.core.pictureTask.routes.PictureTaskAdminRoutes;
import com.ub.core.base.menu.CoreMenu;

public class PictureTaskAddMenu extends CoreMenu {
    public PictureTaskAddMenu() {
        this.name = "Добавить";
        this.parent = new PictureTaskMenu();
        this.url = PictureTaskAdminRoutes.ADD;
    }
}
