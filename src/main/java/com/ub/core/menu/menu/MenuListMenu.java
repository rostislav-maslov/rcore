package com.ub.core.menu.menu;

import com.ub.core.base.menu.CoreMenu;

public class MenuListMenu extends CoreMenu{
    public MenuListMenu(){
        this.name = "Все";
        this.url = "/admin/menu/list";
        this.parent = new MenuEditMenu();
    }
}
