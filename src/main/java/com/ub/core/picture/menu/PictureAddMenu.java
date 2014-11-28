package com.ub.core.picture.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.picture.routes.PicturesAdminRoutes;

public class PictureAddMenu extends CoreMenu {
    public PictureAddMenu(){
        this.name = "Добавить изображения";
        this.url = PicturesAdminRoutes.ADD;
        this.parent = new PictureMenu();
    }
}
