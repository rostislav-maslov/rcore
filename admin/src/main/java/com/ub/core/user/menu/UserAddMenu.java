package com.ub.core.user.menu;

import com.ub.core.base.menu.CoreMenu;

public class UserAddMenu extends CoreMenu{
    public UserAddMenu(){
        this.name = "Добавить";
        this.url = "/admin/user/add";
        this.parent = new UserMenu();
    }
}
