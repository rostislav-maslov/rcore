package com.ub.core.yandexmetrica.menu;

import com.ub.core.base.menu.CoreMenu;

public class YMEditMenu extends CoreMenu {
    public YMEditMenu(){
        this.name = "Редактировать скрипт";
        this.url = "/admin/yandexmetrica/update";
        this.parent = new YMMenu();
    }
}
