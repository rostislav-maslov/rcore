package com.ub.core.user.controllers;

import com.ub.core.user.service.IEmailUserDocService;
import com.ub.core.user.views.AddUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/user/")
public class UserAdminController {

    @Autowired
    private IEmailUserDocService emailUserDocService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String userList(ModelMap modelMap){

        return null;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserGet(ModelMap modelMap){
        AddUserView addUserView = new AddUserView();
        modelMap.addAttribute("addUserView", addUserView);
        return "com.ub.core.admin.user.add";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute @Valid AddUserView addUserView, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.user.add";
        }

        return "com.ub.core.admin.user.add";
    }
}
