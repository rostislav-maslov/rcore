package com.ub.google.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.menu.models.fields.MenuIcons;
import com.ub.core.social.vk.menu.SocialMenu;

/**
 * Created by Eduard on 02.10.2015.
 */
public class GoogleMenu extends CoreMenu {
    public GoogleMenu() {
        this.name = "Google plus";
        this.icon = MenuIcons.ENTYPO_GOOGLE;
        this.parent = new SocialMenu();
    }
}
