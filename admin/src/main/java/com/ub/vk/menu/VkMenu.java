package com.ub.vk.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.menu.models.fields.MenuIcons;
import com.ub.core.social.vk.menu.SocialMenu;

public class VkMenu extends CoreMenu {
    public VkMenu() {
        this.name = "Вконтакте";
        this.icon = MenuIcons.ENTYPO_VKONTAKTE;
        this.parent = new SocialMenu();
    }
}
