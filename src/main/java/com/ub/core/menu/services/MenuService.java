package com.ub.core.menu.services;

import com.ub.core.base.utils.ClassMapping;
import com.ub.core.base.utils.StringUtils;
import com.ub.core.menu.form.MenuForm;
import com.ub.core.menu.models.MenuDoc;
import com.ub.core.menu.views.MenuView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuService {

    @Autowired
    private IMenuService menuService;
    private ClassMapping<MenuForm, MenuDoc> mapFormToDoc;
    private ClassMapping<MenuDoc, MenuForm> mapDocToForm;
    private ClassMapping<MenuDoc, MenuView> mapDocToView;

    public MenuService() {
        mapFormToDoc = new ClassMapping<MenuForm, MenuDoc>(MenuForm.class, MenuDoc.class);
        mapDocToForm = new ClassMapping<MenuDoc, MenuForm>(MenuDoc.class, MenuForm.class);
        mapDocToView = new ClassMapping<MenuDoc, MenuView>(MenuDoc.class, MenuView.class);
    }

    public void update(MenuForm menuForm) {
        MenuDoc menuDoc = new MenuDoc();
        mapFormToDoc.mapping(menuForm, menuDoc);

        if (StringUtils.isEmpty(menuForm.getId()) == false)
            menuDoc.setId(new ObjectId(menuForm.getId()));
        if (StringUtils.isEmpty(menuForm.getParent()) == false)
            menuDoc.setParent(new MenuDoc(new ObjectId(menuForm.getParent())));

        menuService.save(menuDoc);
    }

    public void delete(ObjectId objectId) {
        menuService.delete(objectId);
    }

    private MenuView getView(MenuDoc menuDoc) {
        MenuView menuView = new MenuView();
        mapDocToView.mapping(menuDoc, menuView);
        return menuView;
    }

    public List<MenuView> getAllForAdd(String idExept) {
        List<MenuView> menuViews = getAllForList();
        List<MenuView> result = new ArrayList<MenuView>();
        for (MenuView menuView : menuViews) {
            if (!menuView.getId().toString().equals(idExept))
                result.add(menuView);
        }
        return result;
    }

    public List<MenuView> getAllForList() {
        Iterable<MenuDoc> menuDocs = menuService.findAll();
        List<MenuView> menuViews = new ArrayList<MenuView>();
        for (MenuDoc menuDoc : menuDocs) {
            MenuView menuView = new MenuView();
            mapDocToView.mapping(menuDoc, menuView);
            if (menuDoc.getParent() != null) {
                menuView.setParentName(menuDoc.getParent().getName());
                menuView.setParentId(menuDoc.getParent().getId().toString());
            }
            menuViews.add(menuView);
        }
        return menuViews;
    }

    public List<MenuView> getAllForClient() {
        return getAllForClient(null);
    }

    private List<MenuView> getAllForClient(String parent) {
        Iterable<MenuDoc> menuDocs = menuService.findAll();
        List<MenuView> menuViews = new ArrayList<MenuView>();
        for (MenuDoc menuDoc : menuDocs) {
            MenuView menuView = new MenuView();
            mapDocToView.mapping(menuDoc, menuView);
            if (menuDoc.getParent() != null) {
                menuView.setParentName(menuDoc.getParent().getName());
                menuView.setParentId(menuDoc.getParent().getId().toString());
            }
            if ((parent == null && menuView.getParentId() == null)
                    || (menuView.getParentId() != null && menuView.getParentId().equals(parent) )) {

                List<MenuView> childM = getAllForClient(menuView.getId().toString());
                menuView.setChild(childM);
                menuViews.add(menuView);
            }
        }
        return menuViews;
    }

    public MenuDoc getElement(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return menuService.findOne(objectId);
        } catch (Exception e) {
        }
        return null;
    }

    public MenuDoc getElement(ObjectId id) {
        return menuService.findOne(id);
    }

    public MenuForm getForm(ObjectId objectId) {
        MenuDoc menuDoc = getElement(objectId);
        MenuForm menuForm = new MenuForm();
        mapDocToForm.mapping(menuDoc, menuForm);
        menuForm.setId(menuDoc.getId().toString());
        if (menuDoc.getParent() != null && menuDoc.getParent().getId() != null)
            menuForm.setParent(menuDoc.getParent().getId().toString());
        return menuForm;
    }

    public MenuForm getForm(String id) {
        MenuForm menuForm = new MenuForm();
        try {
            ObjectId objectId = new ObjectId(id);
            menuForm = getForm(objectId);
        } catch (Exception e) {
        }
        return menuForm;
    }

}
