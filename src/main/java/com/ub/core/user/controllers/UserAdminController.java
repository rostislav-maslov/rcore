package com.ub.core.user.controllers;

import com.ub.core.user.service.UserService;
import com.ub.core.user.service.exceptions.UserServiceException;
import com.ub.core.user.views.AddEditUserView;
import com.ub.core.user.views.UserListView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/user/")
@Secured(value = "ROLE_ADMIN")
public class UserAdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String userList(ModelMap modelMap){
        modelMap.addAttribute("userList", userService.getEmailUsers());
        modelMap.addAttribute("userListTitle", new UserListView());
        return "com.ub.core.admin.user.list";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserGet(ModelMap modelMap){
        AddEditUserView addEditUserView = new AddEditUserView();
        modelMap.addAttribute("addEditUserView", addEditUserView);
        modelMap.addAttribute("roles", userService.getAllRoles());
        modelMap.addAttribute("backUrl", "/UBCore/admin/user/addPost");


        return "com.ub.core.admin.user.addEdit";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute @Valid AddEditUserView addEditUserView, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.user.addEdit";
        }
        else{
            try {
                userService.saveEmailUser(addEditUserView);
            } catch (UserServiceException e) {
                ObjectError error = new ObjectError("role",e.getMessage());
                bindingResult.addError(error);
            }
        }

        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUserGet(ModelMap modelMap, @PathVariable("id") ObjectId id){

        if(userService.getUser(id) != null){
            modelMap.addAttribute("addEditUserView", userService.getUser(id));
            modelMap.addAttribute("roles", userService.getAllRoles());
            modelMap.addAttribute("backUrl", "/UBCore/admin/user/editPost/" + id);
            return "com.ub.core.admin.user.addEdit";
        }
        else {
            return "redirect:/admin/user/list";
        }

    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.POST)
    public String editUserPost(@ModelAttribute @Valid AddEditUserView addEditUserView, BindingResult bindingResult, @PathVariable("id") ObjectId id){

        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.user.addEdit";
        }
        else{
            try {
                userService.updateUser(id, addEditUserView);
            } catch (UserServiceException e) {
                ObjectError error = new ObjectError("role",e.getMessage());
                bindingResult.addError(error);
            }
            return "redirect:/admin/user/list";
        }

    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") ObjectId id){



        userService.deleteUser(id);
        return "redirect:/admin/user/list";
    }
}
