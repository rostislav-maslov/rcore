package com.ub.core.seo.robotsCore.menu;

import com.ub.core.base.menu.CoreMenu;
import com.ub.core.menu.models.fields.MenuIcons;
import com.ub.core.seo.robotsCore.menu.SeoCoreMenu;
import com.ub.core.seo.robotsCore.routes.RobotsCoreAdminRoutes;

public class RobotsCoreMenu extends CoreMenu{
    public RobotsCoreMenu() {
        this.name = "Robots.txt";
        this.icon = MenuIcons.ENTYPO_DOC_TEXT;
        this.parent = new SeoCoreMenu();
        this.url = RobotsCoreAdminRoutes.EDIT;
    }
}
