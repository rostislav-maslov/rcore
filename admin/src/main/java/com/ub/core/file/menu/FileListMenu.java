package com.ub.core.file.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.file.FileRoutes;

public class FileListMenu extends CoreMenu {
    public FileListMenu(){
        this.name = "Все";
        this.parent = new FileMenu();
        this.url = FileRoutes.LIST;
    }
}
