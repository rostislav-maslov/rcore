package com.ub.core.picture.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.file.menu.FileMenu;

public class PictureMenu extends CoreMenu {
    public PictureMenu(){
        this.name = "Картинки";
        this.parent = new FileMenu();
    }
}
