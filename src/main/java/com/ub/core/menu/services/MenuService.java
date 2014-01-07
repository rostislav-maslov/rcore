package com.ub.core.menu.services;

import com.ub.core.menu.form.MenuForm;
import com.ub.core.menu.models.MenuDoc;
import com.ub.core.menu.models.fields.MenuFields;
import com.ub.core.menu.views.MenuView;
import com.ub.core.base.utils.ClassMapping;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        mapFormToDoc.mapping(menuForm,menuDoc);

        if (menuForm.getId() != null)
            menuDoc.setId(new ObjectId(menuForm.getId()));
        if (menuForm.getParent() != null)
            menuDoc.setParent(new MenuDoc(new ObjectId()));

        menuService.save(menuDoc);
    }

    public void delete(ObjectId objectId){
        menuService.delete(objectId);
    }

    private MenuView getView(MenuDoc menuDoc){
        MenuView menuView = new MenuView();
        mapDocToView.mapping(menuDoc, menuView);
        return menuView;
    }

    public List<MenuView> getAllForList(){
        Iterable<MenuDoc> menuDocs = menuService.findAll();
        List<MenuView> menuViews = new ArrayList<MenuView>();
        for(MenuDoc menuDoc : menuDocs){
            MenuView menuView = new MenuView();
            mapDocToView.mapping(menuDoc,menuView);
            menuViews.add(menuView);
        }
        return menuViews;
    }

    public List<MenuView> getAll() {
        Query query = new Query(Criteria.where(MenuFields.PARENT).is(null));
        //menuService. findAll(query);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public MenuDoc getElement(ObjectId id) {
        return menuService.findOne(id);
    }

    public MenuForm getForm(ObjectId objectId){
        MenuDoc menuDoc = getElement(objectId);
        MenuForm menuForm = new MenuForm();
        mapDocToForm.mapping(menuDoc,menuForm);
        menuForm.setId(menuDoc.getId().toString());
        menuForm.setParent(menuDoc.getParent().getId().toString());
        return menuForm;
    }
}
