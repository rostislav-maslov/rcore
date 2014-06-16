package com.ub.vk.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.vk.routes.AppPropertiesRoutes;

public class VkAppPropMenu extends CoreMenu{
    public VkAppPropMenu(){
        this.name = "Настройки приложения";
        this.url = AppPropertiesRoutes.EDIT;
        this.parent = new VkMenu();
    }
}