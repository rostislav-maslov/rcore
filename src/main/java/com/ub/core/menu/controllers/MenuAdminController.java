package com.ub.core.menu.controllers;

import com.ub.core.menu.form.MenuForm;
import com.ub.core.menu.models.MenuDoc;
import com.ub.core.menu.route.MenuRoute;
import com.ub.core.menu.services.MenuService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class MenuAdminController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = MenuRoute.LIST, method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("menuList", menuService.getAllForList());
        return "com.ub.core.admin.menu.list";
    }

    @RequestMapping(value = MenuRoute.ADD, method = RequestMethod.GET)
    public String add(@RequestParam(value = "id", defaultValue = "")String id, Model modelMap){
        MenuForm menuForm = menuService.getForm(id);
        modelMap.addAttribute("menuForm", menuForm);

        modelMap.addAttribute("allMenu", menuService.getAllForAdd(id));

        return "com.ub.core.admin.menu.add";
    }

    @RequestMapping(value = MenuRoute.ADD, method = RequestMethod.POST)
    public String addPost(@ModelAttribute @Valid MenuForm menuForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.menu.add";
        }
        menuService.update(menuForm);

        return "redirect:" + MenuRoute.DELETE;
    }

    @RequestMapping(value = MenuRoute.DELETE, method = RequestMethod.GET)
    protected String delete(@RequestParam(value = "id")String id, Model model) throws Exception {
        model.addAttribute("id", id);
        ObjectId objectId = new ObjectId(id);
        MenuDoc menuDoc = menuService.getElement(objectId);
        model.addAttribute("name", menuDoc.getName());
        return "com.ub.core.admin.menu.delete";
    }

    @RequestMapping(value = MenuRoute.DELETE, method = RequestMethod.POST)
    protected String deletePost(@RequestParam(value = "id")String id, Model model) throws Exception {
        ObjectId objectId = new ObjectId(id);
        menuService.delete(objectId);
        return "redirect:"+MenuRoute.LIST;
    }
}
