package com.ub.core.menu.controllers;

import com.ub.core.menu.services.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/menu/")
public class MenuAdminController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){

        return null;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap){
//        AddUserView addUserView = new AddUserView();
//        modelMap.addAttribute("addUserView", addUserView);
//        return "com.ub.core.admin.user.add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute @Valid AddUserView addUserView, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.user.add";
        }

        return "com.ub.core.admin.user.add";
    }
}
