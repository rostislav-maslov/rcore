package com.ub.core.base.menu;

public class SubTestMenu extends SubMenu {

    public SubTestMenu(){
        name = "Тест под меню";
        this.subMenus.add(new SubMenulev2());
        this.url="/admin";
    }
}
