package com.ub.twitter.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.menu.models.fields.MenuIcons;
import com.ub.core.social.vk.menu.SocialMenu;

/**
 * Created by Eduard on 02.10.2015.
 */
public class TwitterMenu extends CoreMenu {
    public TwitterMenu() {
        this.name = "Twitter";
        this.icon = MenuIcons.ENTYPO_TWITTER;
        this.parent = new SocialMenu();
    }
}
