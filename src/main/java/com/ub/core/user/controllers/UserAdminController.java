package com.ub.core.user.controllers;

import com.ub.core.user.service.EmailUserDocService;
import com.ub.core.user.views.AddUserView;
import com.ub.core.user.views.UserListView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

//TODO: переименовать. заменить template add на addEdit. Заменить имя сервиса на userService

@Controller
@RequestMapping("/admin/user/")
public class UserAdminController {

    @Autowired
    EmailUserDocService emailUserDocService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String userList(ModelMap modelMap){
        modelMap.addAttribute("userList", emailUserDocService.getEmailUsers());
        modelMap.addAttribute("userListTitle", new UserListView());
        return "com.ub.core.admin.user.list";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserGet(ModelMap modelMap){
        AddUserView addUserView = new AddUserView();
        modelMap.addAttribute("addUserView", addUserView);
        modelMap.addAttribute("roles", emailUserDocService.getAllRoles());
        modelMap.addAttribute("backUrl", "/UBCore/admin/user/addPost");


        return "com.ub.core.admin.user.add";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute @Valid AddUserView addUserView, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.user.add";
        }
        else{
            emailUserDocService.saveEmailUser(addUserView);
        }

        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUserGet(ModelMap modelMap, @PathVariable("id") ObjectId id){

        if(emailUserDocService.getUser(id) != null){
            modelMap.addAttribute("addUserView", emailUserDocService.getUser(id));
            modelMap.addAttribute("roles", emailUserDocService.getAllRoles());
            modelMap.addAttribute("backUrl", "/UBCore/admin/user/editPost/" + id);
            return "com.ub.core.admin.user.add";
        }
        else {
            return "redirect:/admin/user/list";
        }

    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.POST)
    public String editUserPost(@ModelAttribute @Valid AddUserView addUserView, BindingResult bindingResult, @PathVariable("id") ObjectId id){

        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.user.add";
        }
        else{
            emailUserDocService.updateUser(id, addUserView);
            return "redirect:/admin/user/list";
        }

    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") ObjectId id){



        emailUserDocService.deleteUser(id);
        return "redirect:/admin/user/list";
    }
}
