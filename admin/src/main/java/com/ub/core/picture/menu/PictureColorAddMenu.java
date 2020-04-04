package com.ub.core.picture.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.picture.routes.PictureColorAdminRoutes;

public class PictureColorAddMenu extends CoreMenu {
    public PictureColorAddMenu() {
        this.name ="Добавить цвет";
        this.parent = new PictureMenu();
        this.url = PictureColorAdminRoutes.ADD;
    }
}
