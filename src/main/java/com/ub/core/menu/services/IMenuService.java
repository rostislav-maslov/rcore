package com.ub.core.menu.services;

import com.ub.core.menu.form.MenuForm;
import com.ub.core.menu.models.MenuDoc;
import com.ub.core.menu.views.MenuView;
import org.bson.types.ObjectId;

import java.util.List;

public interface IMenuService {

    public MenuDoc getElement(ObjectId id);
    public List<MenuView> getAll();
    public void update(MenuForm menuForm);

}
