package com.ub.core.menu.menu;

import com.ub.core.base.menu.CoreMenu;

public class MenuAddMenu extends CoreMenu{
    public MenuAddMenu(){
        this.name = "Добавить";
        this.url = "/admin/menu/add";
        this.parent = new MenuEditMenu();
    }
}
