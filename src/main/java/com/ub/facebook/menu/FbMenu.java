package com.ub.facebook.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.menu.models.fields.MenuIcons;
import com.ub.core.social.vk.menu.SocialMenu;

public class FbMenu extends CoreMenu {
    public FbMenu() {
        this.name = "Facebook";
        this.icon = MenuIcons.ENTYPO_FACEBBOK;
        this.parent = new SocialMenu();
    }
}
