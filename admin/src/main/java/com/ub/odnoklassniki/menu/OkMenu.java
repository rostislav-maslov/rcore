package com.ub.odnoklassniki.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.social.vk.menu.SocialMenu;

public class OkMenu extends CoreMenu {
    public OkMenu() {
        this.name = "Одноклассники";
        this.parent = new SocialMenu();
    }
}
