package com.ub.core.user.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.menu.models.fields.MenuIcons;

public class UserMenu extends CoreMenu {
    public UserMenu(){
        this.name = "Пользователи";
        this.icon = MenuIcons.ENTYPO_USERS;
    }
}
