package com.ub.core.user.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.routes.UserAdminRoutes;
import com.ub.core.user.service.UserService;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserNotExistException;
import com.ub.core.user.views.AddEditUserView;
import com.ub.core.user.views.UserListView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@RequestMapping("")
public class UserAdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public String userList(ModelMap modelMap) {
        modelMap.addAttribute("active", UserStatusEnum.ACTIVE);
        modelMap.addAttribute("block", UserStatusEnum.BLOCK);
        modelMap.addAttribute("userList", userService.getAllUsers());
        modelMap.addAttribute("userListTitle", new UserListView());
        return "com.ub.core.admin.user.list";

    }

    @RequestMapping(value = UserAdminRoutes.LIST_BLOCK, method = RequestMethod.GET)
    public String userListBlock(ModelMap modelMap) {
        modelMap.addAttribute("active", UserStatusEnum.ACTIVE);
        modelMap.addAttribute("block", UserStatusEnum.BLOCK);

        ArrayList<UserDoc> res = new ArrayList<UserDoc>();
        for (UserDoc emailUserDoc : userService.getAllUsers())
            if (emailUserDoc.getUserStatus().equals(UserStatusEnum.BLOCK))
                res.add(emailUserDoc);
        modelMap.addAttribute("userList", res);
        modelMap.addAttribute("userListTitle", new UserListView());
        return "com.ub.core.admin.user.list";

    }

    @RequestMapping(value = UserAdminRoutes.LIST_ACTIVE, method = RequestMethod.GET)
    public String userListActive(ModelMap modelMap) {
        modelMap.addAttribute("active", UserStatusEnum.ACTIVE);
        modelMap.addAttribute("block", UserStatusEnum.BLOCK);
        ArrayList<UserDoc> res = new ArrayList<UserDoc>();
        for (UserDoc emailUserDoc : userService.getAllUsers())
            if (emailUserDoc.getUserStatus().equals(UserStatusEnum.ACTIVE))
                res.add(emailUserDoc);
        modelMap.addAttribute("userList", res);
        modelMap.addAttribute("userListTitle", new UserListView());
        return "com.ub.core.admin.user.list";

    }

    @RequestMapping(value = "/admin/user/add", method = RequestMethod.GET)
    public String addUserGet(ModelMap modelMap) {
        AddEditUserView addEditUserView = new AddEditUserView();
        modelMap.addAttribute("addEditUserView", addEditUserView);
        modelMap.addAttribute("roles", userService.getAllRoles());
        modelMap.addAttribute("backUrl", "/admin/user/addPost");


        return "com.ub.core.admin.user.addEdit";
    }

    @RequestMapping(value = "/admin/user/addPost", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute @Valid AddEditUserView addEditUserView, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "com.ub.core.admin.user.addEdit";
        } else {
            try {
                userService.createUserByEmail(addEditUserView);
            } catch (UserExistException e) {
                ObjectError error = new ObjectError("role", "Данный пользователь уже существует");
                bindingResult.addError(error);
            }
        }

        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    public String editUserGet(@RequestParam("id") ObjectId id, ModelMap modelMap) {

        if (userService.getUser(id) != null) {
            modelMap.addAttribute("userDoc", userService.getUser(id));
            modelMap.addAttribute("roles", userService.getAllRoles());
            modelMap.addAttribute("backUrl", "/admin/user/edit");
            return "com.ub.core.admin.user.editUserInfo";
        } else {
            return "redirect:/admin/user/list";
        }

    }

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.POST)
    public String editUserPost(@ModelAttribute @Valid UserDoc userDoc, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "com.ub.core.admin.user.editUserInfo";
        } else {
            try {
                userService.updateUserInfo(userDoc);
                //userService.updateUserInfo();updateUser(addEditUserView.getEmail(), addEditUserView);
            } catch (UserNotExistException e) {
                ObjectError error = new ObjectError("role", "Такого пользователя не существует");
                bindingResult.addError(error);
            }
            return "redirect:/admin/user/list";
        }

    }


    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") ObjectId id) {
        userService.deleteUser(id);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = UserAdminRoutes.BLOCK, method = RequestMethod.POST)
    public String blockUser(@RequestParam ObjectId id) {
        userService.block(id);
        return RouteUtils.redirectTo(UserAdminRoutes.LIST);
    }

    @RequestMapping(value = UserAdminRoutes.ACTIVE, method = RequestMethod.POST)
    public String activeUser(@RequestParam ObjectId id) {
        userService.active(id);
        return RouteUtils.redirectTo(UserAdminRoutes.LIST);
    }


}
