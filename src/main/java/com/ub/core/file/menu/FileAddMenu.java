package com.ub.core.file.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.file.FileRoutes;

public class FileAddMenu extends CoreMenu {
    public FileAddMenu(){
        this.name = "Добавить";
        this.url = FileRoutes.ADD;
        this.parent = new FileMenu();
    }
}
