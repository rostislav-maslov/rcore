package com.ub.core.user.controllers;

import com.ub.core.user.service.UserService;
import com.ub.core.user.views.AddEditRoleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 1/17/14
 * Time: 7:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/role/")
public class RoleAdminController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/add")
    public String addRole(ModelMap modelMap){
        AddEditRoleView addEditRoleView = new AddEditRoleView();
        modelMap.addAttribute("addEditRoleView", addEditRoleView);
        modelMap.addAttribute("backUrl", "/admin/role/addPost");

        return "com.ub.core.admin.role.addEdit";
    }

    @RequestMapping(value = "addPost", method = RequestMethod.POST)
    public String addRolePost(ModelMap modelMap, @ModelAttribute @Valid AddEditRoleView addEditRoleView, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.role.addEdit";
        }
        else{
            userService.saveRole(addEditRoleView);

        }

        return "redirect:/admin/user/list";
    }

//    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
//    public String addUserPost(@ModelAttribute @Valid AddEditUserView addEditUserView, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//            return "com.ub.core.admin.user.addEdit";
//        }
//        else{
//            try {
//                userService.saveEmailUser(addEditUserView);
//            } catch (UserServiceException e) {
//                ObjectError error = new ObjectError("role",e.getMessage());
//                bindingResult.addError(error);
//            }
//        }
//
//        return "redirect:/admin/user/list";
//    }

}
