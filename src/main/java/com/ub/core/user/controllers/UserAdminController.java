package com.ub.core.user.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.role.service.RoleService;
import com.ub.core.security.annotationSecurity.AvailableForRoles;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.roles.AvailableChangePassword;
import com.ub.core.user.routes.UserAdminRoutes;
import com.ub.core.user.service.UserLogsService;
import com.ub.core.user.service.UserService;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserNotExistException;
import com.ub.core.user.views.AddEditUserView;
import com.ub.core.user.views.modalUserSearch.all.SearchUserAdminRequest;
import com.ub.core.user.views.modalUserSearch.all.SearchUserAdminResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("")
public class UserAdminController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;
    @Autowired private UserLogsService userLogsService;

    @AvailableForRoles(AvailableChangePassword.class)
    @RequestMapping(value = UserAdminRoutes.EDIT_PASSWORD, method = RequestMethod.POST)
    public String editPassword(@RequestParam ObjectId userId, @RequestParam String password, RedirectAttributes ra) {
        userService.changePassword(userId,password);
        ra.addAttribute("id", userId);
        return RouteUtils.redirectTo(UserAdminRoutes.EDIT);
    }

    @RequestMapping(value = UserAdminRoutes.LIST, method = RequestMethod.GET)
    public String userList(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                           @RequestParam(required = false, defaultValue = "") String query,
                           ModelMap modelMap) {

        SearchUserAdminRequest searchUserAdminRequest = new SearchUserAdminRequest(currentPage);
        searchUserAdminRequest.setQuery(query);
        modelMap.addAttribute("url", UserAdminRoutes.LIST);
        modelMap.addAttribute("active", UserStatusEnum.ACTIVE);
        modelMap.addAttribute("block", UserStatusEnum.BLOCK);
        SearchUserAdminResponse searchUserAdminResponse = userService.findAll(searchUserAdminRequest);
        modelMap.addAttribute("searchUserAdminResponse", searchUserAdminResponse);
        return "com.ub.core.admin.user.list";
    }

    @RequestMapping(value = UserAdminRoutes.LIST_BLOCK, method = RequestMethod.GET)
    public String userListBlock(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                                @RequestParam(required = false, defaultValue = "") String query,
                                ModelMap modelMap) {

        SearchUserAdminRequest searchUserAdminRequest = new SearchUserAdminRequest(currentPage);
        searchUserAdminRequest.setQuery(query);
        modelMap.addAttribute("url", UserAdminRoutes.LIST_BLOCK);
        modelMap.addAttribute("active", UserStatusEnum.ACTIVE);
        modelMap.addAttribute("block", UserStatusEnum.BLOCK);
        SearchUserAdminResponse searchUserAdminResponse = userService.findAll(searchUserAdminRequest, UserStatusEnum.BLOCK);
        modelMap.addAttribute("searchUserAdminResponse", searchUserAdminResponse);
        return "com.ub.core.admin.user.list";
    }

    @RequestMapping(value = UserAdminRoutes.LIST_ACTIVE, method = RequestMethod.GET)
    public String userListActive(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                                 @RequestParam(required = false, defaultValue = "") String query,
                                 ModelMap modelMap) {

        SearchUserAdminRequest searchUserAdminRequest = new SearchUserAdminRequest(currentPage);
        searchUserAdminRequest.setQuery(query);
        modelMap.addAttribute("url", UserAdminRoutes.LIST_ACTIVE);
        modelMap.addAttribute("active", UserStatusEnum.ACTIVE);
        modelMap.addAttribute("block", UserStatusEnum.BLOCK);
        SearchUserAdminResponse searchUserAdminResponse = userService.findAll(searchUserAdminRequest, UserStatusEnum.ACTIVE);
        modelMap.addAttribute("searchUserAdminResponse", searchUserAdminResponse);
        return "com.ub.core.admin.user.list";
    }

    @RequestMapping(value = UserAdminRoutes.ADD, method = RequestMethod.GET)
    public String addUserGet(ModelMap modelMap) {
        AddEditUserView addEditUserView = new AddEditUserView();
        modelMap.addAttribute("addEditUserView", addEditUserView);
        modelMap.addAttribute("roles", roleService.findAllRoles());
        modelMap.addAttribute("backUrl", "/admin/user/addPost");
        return "com.ub.core.admin.user.addEdit";
    }

    @RequestMapping(value = "/admin/user/addPost", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute @Valid AddEditUserView addEditUserView,
                              BindingResult bindingResult) {

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

    @RequestMapping(value = UserAdminRoutes.EDIT, method = RequestMethod.GET)
    public String editUserGet(@RequestParam("id") ObjectId id, Model model) {

        if (userService.getUser(id) != null) {
            model.addAttribute("userLogs", userLogsService.findByUserId(id));
            model.addAttribute("userDoc", userService.getUser(id));
            model.addAttribute("userDocHack", userService.getUser(id));
            model.addAttribute("roles", roleService.findAllRoles());
            model.addAttribute("backUrl", "/admin/user/edit");
            return "com.ub.core.admin.user.editUserInfo";
        } else {
            return "redirect:/admin/user/list";
        }

    }

    @RequestMapping(value = UserAdminRoutes.EDIT, method = RequestMethod.POST)
    public String editUserPost(@ModelAttribute @Valid UserDoc userDoc, BindingResult bindingResult,
                               RedirectAttributes ra) {

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
            ra.addAttribute("id", userDoc.getId());
            return RouteUtils.redirectTo(UserAdminRoutes.EDIT);
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

    @RequestMapping(value = UserAdminRoutes.LIST_MODAL_SEARCH, method = RequestMethod.GET)
    public String modalSearch(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                              @RequestParam(required = false, defaultValue = "") String query,
                              Model model) {
        SearchUserAdminRequest searchCompanyAdminRequest = new SearchUserAdminRequest(currentPage);
            searchCompanyAdminRequest.setQuery(query);
        model.addAttribute("searchUserAdminResponse", userService.findAll(searchCompanyAdminRequest));
        return "com.ub.core.admin.user.modal.search.content";
    }

}
