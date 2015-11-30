package com.ub.linkedin.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.menu.models.fields.MenuIcons;
import com.ub.core.social.vk.menu.SocialMenu;

/**
 * Created by Eduard on 02.10.2015.
 */
public class LinkedinMenu extends CoreMenu {
    public LinkedinMenu() {
        this.name = "Linked In";
        this.icon = MenuIcons.ENTYPO_LINKEDIN;
        this.parent = new SocialMenu();
    }
}
