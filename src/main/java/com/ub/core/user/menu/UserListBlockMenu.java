package com.ub.core.user.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.user.routes.UserAdminRoutes;

public class UserListBlockMenu extends CoreMenu {
    public UserListBlockMenu(){
        this.name = "Заблокированные";
        this.url = UserAdminRoutes.LIST_BLOCK;
        this.parent = new UserMenu();
    }
}
