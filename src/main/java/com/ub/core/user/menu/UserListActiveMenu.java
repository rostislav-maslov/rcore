package com.ub.core.user.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.user.routes.UserAdminRoutes;

public class UserListActiveMenu extends CoreMenu {
    public UserListActiveMenu(){
        this.name = "Активные";
        this.url = UserAdminRoutes.LIST_ACTIVE;
        this.parent = new UserMenu();
    }
}
