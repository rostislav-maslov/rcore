package com.ub.core.picture.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.picture.routes.PicturesAdminRoutes;

public class PictureAllMenu extends CoreMenu {
    public PictureAllMenu(){
        this.name = "Все изображения";
        this.parent = new PictureMenu();
        this.url = PicturesAdminRoutes.ALL;
    }
}
