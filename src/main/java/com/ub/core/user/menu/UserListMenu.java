package com.ub.core.user.menu;

import com.ub.core.base.menu.CoreMenu;

public class UserListMenu extends CoreMenu {
    public UserListMenu(){
        this.name = "Все";
        this.url = "/admin/user/list";
        this.parent = new UserMenu();
    }
}
