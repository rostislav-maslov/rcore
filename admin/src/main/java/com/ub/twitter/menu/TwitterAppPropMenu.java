package com.ub.twitter.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.twitter.routes.TwitterRoutes;

/**
 * Created by Eduard on 02.10.2015.
 */
public class TwitterAppPropMenu extends CoreMenu {
    public TwitterAppPropMenu() {
        this.name = "Настройки приложения";
        this.url = TwitterRoutes.EDIT;
        this.parent = new TwitterMenu();
    }
}
