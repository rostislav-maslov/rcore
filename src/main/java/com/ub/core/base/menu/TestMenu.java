package com.ub.core.base.menu;

public class TestMenu extends MainMenu{
    public TestMenu(){
        this.name = "Тестовое меню";
        this.subMenus.add(new SubTestMenu());
    }
}
